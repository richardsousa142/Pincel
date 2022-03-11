package com.paint.paintproject.brushs.giz.enumGiz;

import com.paint.paintproject.brushs.Brush;
import com.paint.paintproject.brushs.brushInterface.GetBrush;
import com.paint.paintproject.brushs.giz.Giz1;
import com.paint.paintproject.brushs.giz.Giz2;

public enum GetInstanceOfBrushGiz implements GetBrush {
    GIZ1(new Giz1()),
    GIZ2(new Giz2());

    private Brush brush;

    GetInstanceOfBrushGiz(Brush brush) {
        this.brush = brush;
    }

    @Override
    public Brush getBrush() {
        return brush;
    }
}
