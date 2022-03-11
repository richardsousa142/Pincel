package com.paint.paintproject.brushs.shape;

import com.paint.paintproject.brushs.Brush;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.paint.CycleMethod;
import javafx.scene.paint.RadialGradient;
import javafx.scene.paint.Stop;
import javafx.scene.shape.Line;

public class BrushShapeLine extends Brush {
    public BrushShapeLine(){
    }
    public BrushShapeLine(double size, Color color, GraphicsContext graphicsContext){
        super(size, color, graphicsContext);
    }
    @Override
    public Image createBrush(double radius, Color color) {
        Line brush = new Line();
        RadialGradient gradient1 = new RadialGradient(0, 0, 0, 0, radius, false, //cria uma gradient color para o pincel
                CycleMethod.NO_CYCLE,
                new Stop(0, color.deriveColor(1, 1, 1, 0.3)),
                new Stop(1, color.deriveColor(1, 1, 1, 0)));
        //brush.setStrokeLineCap(StrokeLineCap.ROUND);
        //brush.setStrokeLineCap(StrokeLineCap.ROUND);
        brush.setStroke(gradient1); //atribui a cor ao pincel
        brush.setStrokeWidth(radius);
        return createImage(brush);
    }
    @Override
    public boolean equals(Object obj) {
        if(obj instanceof BrushShapeLine)
            return  true;
        else
            return false;
    }

    @Override
    public String getName(){
        return "Brush of Line";
    }

}
