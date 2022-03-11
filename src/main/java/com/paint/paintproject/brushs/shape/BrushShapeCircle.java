package com.paint.paintproject.brushs.shape;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.paint.CycleMethod;
import javafx.scene.paint.RadialGradient;
import javafx.scene.paint.Stop;
import javafx.scene.shape.Circle;

public class BrushShapeCircle extends BrushShapeLine {
    public BrushShapeCircle(){
    }
    public BrushShapeCircle(double size, Color color, GraphicsContext graphicsContext){
        super(size, color, graphicsContext);
    }
    @Override
    public Image createBrush(double radius, Color color) {
        Circle brush = new Circle(radius); //cria um circulo passando o raio
        RadialGradient gradient1 = new RadialGradient(0, 0, 0, 0, radius, false, //cria uma gradient color para o pincel
                CycleMethod.NO_CYCLE,
                new Stop(0, color.deriveColor(1, 1, 1, 0.3)),
                new Stop(1, color.deriveColor(1, 1, 1, 0)));
        brush.setFill(Color.GRAY); //atribui a cor ao pincel
        return createImage(brush); // cria o pincel e retorna
    }

    @Override
    public String getName(){
        return "Brush of Circle";
    }

}
