package com.paint.paintproject.brushs;

import com.paint.paintproject.brushs.brushInterface.BrushInterface;
import javafx.animation.AnimationTimer;
import javafx.geometry.Point2D;
import javafx.scene.Node;
import javafx.scene.SnapshotParameters;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.image.WritableImage;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.*;
import javafx.scene.shape.Circle;

public class Brush implements BrushInterface {
    private double brushWidthHalf; //deixa o mouse no centro da linha
    private double brushHeightHalf; //deixa o mouse no centro da linha
    private Point2D mouseLocation = new Point2D( 0, 0);
    private Point2D prevMouseLocation = new Point2D( 0, 0);
    private boolean mousePressed = false;
    private Image brush;

    public Brush(){
    }

    public Brush(double size, Color color, GraphicsContext graphicsContext){
        brush = createBrush(size, color);
        addListeners(graphicsContext.getCanvas());
        startAnimation(graphicsContext);
        centralizeBrush(brush);
    }

    @Override
    public void startAnimation(GraphicsContext graphicsContext) {
        AnimationTimer loop = new AnimationTimer() {
            @Override
            public void handle(long now) {
                if (mousePressed) {
                    bresenhamLine(prevMouseLocation.getX(), prevMouseLocation.getY(), mouseLocation.getX(), mouseLocation.getY(), graphicsContext);
                    if (mousePressed) mousePressed = false;
                }
                prevMouseLocation = new Point2D(mouseLocation.getX(), mouseLocation.getY());
            }
        };
        loop.start();
    }

    @Override
    public void bresenhamLine(double x0, double y0, double x1, double y1, GraphicsContext graphicsContext) {
        double dx = Math.abs(x1 - x0), sx = x0 < x1 ? 1. : -1.;
        double dy = -Math.abs(y1 - y0), sy = y0 < y1 ? 1. : -1.;
        double err = dx + dy, e2; /* error value e_xy */
        graphicsContext.beginPath();
        while (true) {
            graphicsContext.drawImage( brush, x0 - brushWidthHalf, y0 - brushHeightHalf);
            if (x0 == x1 && y0 == y1) break;
            e2 = 2. * err;
            if (e2 > dy) { err += dy; x0 += sx; }
            if (e2 < dx) { err += dx; y0 += sy; }
        }
    }

    @Override
    public void addListeners(Canvas canvas) {
        canvas.addEventFilter(MouseEvent.ANY, e -> {
            mouseLocation = new Point2D(e.getX(), e.getY());
            mousePressed = e.isPrimaryButtonDown();
        });
    }

    @Override
    public void createBrushVariations(double size, Color color) {
        brush = createBrush(size, color);
        centralizeBrush(brush);
    }

    @Override
    public Image createBrush(double radius, Color color) {
        Circle brush = new Circle(radius);
        RadialGradient gradient1 = new RadialGradient(0, 0, 0, 0, radius, false, CycleMethod.NO_CYCLE,
                new Stop(0, color.deriveColor(1, 1, 1, 0.3)),
                new Stop(1, color.deriveColor(1, 1, 1, 0)));
        brush.setFill(gradient1);
        return createImage(brush);
    }

    @Override
    public Image createImage(Node node) {
        WritableImage wi;
        SnapshotParameters parameters = new SnapshotParameters();
        parameters.setFill(Color.TRANSPARENT);
        int imageWidth = (int) node.getBoundsInLocal().getWidth();
        int imageHeight = (int) node.getBoundsInLocal().getHeight();
        wi = new WritableImage(imageWidth, imageHeight);
        node.snapshot(parameters, wi);
        return wi;
    }

    @Override
    public void centralizeBrush(Image brush) {
        brushWidthHalf = brush.getWidth() / 2.0; //deixa o mouse no centro da linha
        brushHeightHalf = brush.getHeight() / 2.0; //deixa o mouse no centro da linha
    }

    @Override
    public boolean equals(Object obj) {
        if(obj instanceof Brush)
            return  true;
        else
            return false;
    }

    @Override
    public String getName(){
        return "Brush";
    }
}
