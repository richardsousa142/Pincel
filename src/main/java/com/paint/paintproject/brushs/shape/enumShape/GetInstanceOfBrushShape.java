package com.paint.paintproject.brushs.shape.enumShape;

import com.paint.paintproject.brushs.Brush;
import com.paint.paintproject.brushs.brushInterface.GetBrush;
import com.paint.paintproject.brushs.shape.*;

public enum GetInstanceOfBrushShape implements GetBrush {
    BRUSH_SHAPE_LINE(new BrushShapeLine()),
    BRUSH_SHAPE_CIRCLE(new BrushShapeCircle()),
    BRUSH_SHAPE_ARC(new BrushShapeArc()),
    BRUSH_SHAPE_ELLIPSE(new BrushShapeEllipse()),
    BRUSH_SHAPE_CUBIC_CURVE(new BrushShapeCubicCurve());

    private Brush brush;

    GetInstanceOfBrushShape(Brush brush) {
        this.brush = brush;
    }

    @Override
    public Brush getBrush() {
        return brush;
    }

}
