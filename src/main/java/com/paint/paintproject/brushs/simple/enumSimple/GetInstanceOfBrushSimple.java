package com.paint.paintproject.brushs.simple.enumSimple;

import com.paint.paintproject.brushs.Brush;
import com.paint.paintproject.brushs.brushInterface.GetBrush;
import com.paint.paintproject.brushs.simple.Simple1;
import com.paint.paintproject.brushs.simple.Simple2;
import com.paint.paintproject.brushs.simple.Simple3;
import com.paint.paintproject.brushs.simple.Simple4;

public enum GetInstanceOfBrushSimple implements GetBrush {
    SIMPLE1(new Simple1()),
    SIMPLE2(new Simple2()),
    SIMPLE3(new Simple3()),
    SIMPLE4(new Simple4());

    private Brush brush;

    GetInstanceOfBrushSimple(Brush brush) {
        this.brush = brush;
    }

    @Override
    public Brush getBrush() {
        return brush;
    }
}
