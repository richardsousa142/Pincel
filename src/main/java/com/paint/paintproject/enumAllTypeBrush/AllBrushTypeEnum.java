package com.paint.paintproject.enumAllTypeBrush;

import com.paint.paintproject.brushs.giz.enumGiz.GetInstanceOfBrushGiz;
import com.paint.paintproject.brushs.shape.enumShape.GetInstanceOfBrushShape;
import com.paint.paintproject.brushs.simple.enumSimple.GetInstanceOfBrushSimple;
import com.paint.paintproject.brushs.spray.enumSpray.GetInstanceOfBrushSpray;

public enum AllBrushTypeEnum implements GetTypeOfBrush{

    SHAPE(GetInstanceOfBrushShape.class, GetInstanceOfBrushShape.values()),
    SPRAY(GetInstanceOfBrushSpray.class, GetInstanceOfBrushSpray.values()),
    SIMPLE(GetInstanceOfBrushSimple.class, GetInstanceOfBrushSimple.values()),
    GIZ(GetInstanceOfBrushGiz.class, GetInstanceOfBrushGiz.values());

    private Object brushType;
    private Enum[] values;

    AllBrushTypeEnum(Object brushType, Enum[] values) {
        this.brushType = brushType;
        this.values = values;
    }

    @Override
    public Object enumOfBrush() {
        return brushType;
    }

    @Override
    public Enum[] enumValuesOfBrush() {
        return values;
    }

}