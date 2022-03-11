package com.paint.paintproject.brushs.shape;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.paint.CycleMethod;
import javafx.scene.paint.RadialGradient;
import javafx.scene.paint.Stop;
import javafx.scene.shape.Ellipse;

public class BrushShapeEllipse extends BrushShapeLine {
    public BrushShapeEllipse(){

    }
    public BrushShapeEllipse(double size, Color color, GraphicsContext graphicsContext){
        super(size, color, graphicsContext);
    }
    @Override
    public Image createBrush(double radius, Color color) {
        Ellipse brush = new Ellipse(radius, radius/2); //cria um elipse passando o raio
        RadialGradient gradient1 = new RadialGradient(0, 0, 0, 0, radius, false, //cria uma gradient color para o pincel
                CycleMethod.NO_CYCLE,
                new Stop(0, color.deriveColor(1, 1, 1, 0.3)),
                new Stop(1, color.deriveColor(1, 1, 1, 0)));
        brush.setFill(gradient1); //atribui a cor ao pincel
        return createImage(brush); // cria o pincel e retorna
    }

    @Override
    public String getName(){
        return "Brush of Ellipse";
    }

}
