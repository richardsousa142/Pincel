package com.paint.paintproject.brushs.simple;

import com.paint.paintproject.brushs.Brush;
import javafx.animation.AnimationTimer;
import javafx.geometry.Point2D;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;

public class Simple2 extends Brush {
    private Point2D mouseLocation = new Point2D( 0, 0);
    private Point2D prevMouseLocation = new Point2D( 0, 0);
    private boolean mousePressed = false;

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
    public void bresenhamLine(double x0, double y0, double x1, double y1, GraphicsContext g) {
        double dx = Math.abs(x1-x0), sx = x0<x1 ? 1 : -1;
        double dy = -Math.abs(y1-y0), sy = y0<y1 ? 1 : -1;
        double err = dx-dy, e2, x2;                       /* error value e_xy */
        double ed = dx+dy == 0 ? 1 : Math.sqrt((float)dx*dx+(float)dy*dy);
        for ( ; ; ){
            setPixelAA(g, x0, y0, Math.abs(err-dx+dy)/ed);
            e2 = err; x2 = x0;
            if (2*e2 >= -dx) {                                    /* x step */
                if (x0 == x1) break;
                if (e2+dy < ed) setPixelAA(g, x0,y0+sy, (e2+dy)/ed);
                err -= dy; x0 += sx;
            }
            if (2*e2 <= dy) {                                     /* y step */
                if (y0 == y1) break;
                if (dx-e2 < ed) setPixelAA(g,x2+sx,y0, (dx-e2)/ed);
                err += dx; y0 += sy;
            }
        }
    }

    private void setPixelAA(GraphicsContext g, double x, double y, double c) {
        g.setFill(new Color(0f, 0f, 0f, (float) c));
        g.fillOval((int) x, (int) y, 4, 4);
    }

    @Override
    public void addListeners(Canvas canvas) {
        canvas.addEventFilter(MouseEvent.ANY, e -> {
            mouseLocation = new Point2D(e.getX(), e.getY());
            mousePressed = e.isPrimaryButtonDown();
        });
    }
}
