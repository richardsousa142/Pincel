package com.paint.paintproject.brushs.brushInterface;

import javafx.scene.Node;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;

public interface BrushInterface {
    void startAnimation(GraphicsContext graphicsContext);
    void bresenhamLine(double x0, double y0, double x1, double y1, GraphicsContext graphicsContext);
    void addListeners(Canvas canvas);
    void createBrushVariations(double size, Color color);
    Image createImage(Node node);
    Image createBrush(double size, Color color);
    void centralizeBrush(Image brush);
    boolean equals(Object obj);
    String getName();
}
