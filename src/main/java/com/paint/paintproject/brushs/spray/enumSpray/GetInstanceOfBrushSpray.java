package com.paint.paintproject.brushs.spray.enumSpray;

import com.paint.paintproject.brushs.Brush;
import com.paint.paintproject.brushs.brushInterface.GetBrush;
import com.paint.paintproject.brushs.spray.Spray1;
import com.paint.paintproject.brushs.spray.Spray2;
import com.paint.paintproject.brushs.spray.Spray3;

public enum GetInstanceOfBrushSpray implements GetBrush {
    SPRAY1(new Spray1()),
    SPRAY2(new Spray2()),
    SPRAY3(new Spray3());

    private Brush brush;

    GetInstanceOfBrushSpray(Brush brush) {
        this.brush = brush;
    }

    @Override
    public Brush getBrush() {
        return brush;
    }
}
