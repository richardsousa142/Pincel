package com.paint.paintproject.brushs.simple;

import com.paint.paintproject.brushs.Brush;
import javafx.animation.AnimationTimer;
import javafx.geometry.Point2D;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;

public class Simple1 extends Brush {
    private Point2D mouseLocation = new Point2D( 0, 0);
    private Point2D prevMouseLocation = new Point2D( 0, 0);
    private boolean mousePressed = false;

    public Simple1(){

    }

    public Simple1(double size, Color color, GraphicsContext graphicsContext) {
        super(size, color, graphicsContext);
    }

    private Point2D swap(double a , double b){
        double temp = a;
        a = b;
        b = temp;
        return new Point2D(a, b);
    }

    private int ipart(double x){
        return (int) Math.floor(x);
    }

    private double round(double x) {
        return ipart(x + 0.5);
    }

    private double fpart(double x) {
        return x - Math.floor(x);
    }

    private double rfpart(double x){
        return 1.0 - fpart(x);
    }

    private void plot(GraphicsContext g, double x, double y, double c) {
        g.setFill(new Color(0f, 0f, 0f, (float)c));
        g.fillOval((int) x, (int) y, 4, 4);
    }

    private void drawLine(GraphicsContext g, double x0, double y0, double x1, double y1){
        boolean steep = Math.abs(y1 - y0) > Math.abs(x1 - x0);
        Point2D p0 = new Point2D(x0, y0);
        Point2D p1 = new Point2D(x1, y1);

        if (steep) {
            //swap(x0, y0);
            //swap(x1, y1);
            double temp1 = x0;
            x0 = y0;
            y0 = temp1;


            double temp2 = x1;
            x1 = y1;
            y1 = temp2;
        }
        if (x0 > x1) {
            //swap(x0, x1);
            //swap(y0, y1);
            double temp1 = x0;
            x0 = x1;
            x1 = temp1;

            double temp2 = y0;
            y0 = y1;
            y1 = temp2;
        }

        double dx = x1 - x0;
        double dy = y1 - y0;
        double gradient = dy / dx;

        // handle first endpoint
        double xend = round(x0);
        double yend = y0 + gradient * (xend - x0);
        double xgap = rfpart(x0 + 0.5);
        double xpxl1 = xend; // this will be used in the main loop
        double ypxl1 = ipart(yend);

        if (steep) {
            plot(g, ypxl1, xpxl1, rfpart(yend) * xgap);
            plot(g, ypxl1 + 1, xpxl1, fpart(yend) * xgap);
        } else {
            plot(g, xpxl1, ypxl1, rfpart(yend) * xgap);
            plot(g, xpxl1, ypxl1 + 1, fpart(yend) * xgap);
        }

        // first y-intersection for the main loop
        double intery = yend + gradient;

        // handle second endpoint
        xend = round(x1);
        yend = y1 + gradient * (xend - x1);
        xgap = fpart(x1 + 0.5);
        double xpxl2 = xend; // this will be used in the main loop
        double ypxl2 = ipart(yend);

        if (steep) {
            plot(g, ypxl2, xpxl2, rfpart(yend) * xgap);
            plot(g, ypxl2 + 1, xpxl2, fpart(yend) * xgap);
        } else {
            plot(g, xpxl2, ypxl2, rfpart(yend) * xgap);
            plot(g, xpxl2, ypxl2 + 1, fpart(yend) * xgap);
        }

        // main loop
        for (double x = xpxl1 + 1; x <= xpxl2 - 1; x++) {
            if(steep){
                plot(g, ipart(intery)  , x, rfpart(intery));
                plot(g, ipart(intery)+1, x,  fpart(intery));
                intery = intery + gradient;
            }else{
                plot(g, x, ipart(intery),  rfpart(intery));
                plot(g, x, ipart(intery)+1, fpart(intery));
                intery = intery + gradient;
            }
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
    public void startAnimation(GraphicsContext g) {
        AnimationTimer loop = new AnimationTimer() {
            @Override
            public void handle(long now) {
                if (mousePressed) {
                    drawLine(g, prevMouseLocation.getX(), prevMouseLocation.getY(), mouseLocation.getX(), mouseLocation.getY());
                    if (mousePressed) mousePressed = false;
                }
                prevMouseLocation = new Point2D(mouseLocation.getX(), mouseLocation.getY());
            }
        };
        loop.start();
    }
}




























