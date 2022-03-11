package com.paint.paintproject.brushs;

import java.awt.*;

public class Ponto {
    private Point point;
    private int width;

    public Ponto(Point point, int width){
        this.point = point;
        this.width = width;
    }
    public Point getPoint(){
        return point;
    }
    public int getWidth(){
        return width;
    }
}
