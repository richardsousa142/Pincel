package com.paint.paintproject.brushs.brushFactory;

import com.paint.paintproject.brushs.Brush;
import com.paint.paintproject.brushs.brushInterface.GetBrush;

public class BrushFactory {
    public static Brush createBrush(Object brush){
        GetBrush gb = (GetBrush) brush;
        return gb.getBrush();
    }
}
