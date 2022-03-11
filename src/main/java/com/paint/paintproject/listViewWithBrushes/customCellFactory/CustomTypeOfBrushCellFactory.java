package com.paint.paintproject.listViewWithBrushes.customCellFactory;

import com.paint.paintproject.enumAllTypeBrush.AllBrushTypeEnum;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.util.Callback;

public class CustomTypeOfBrushCellFactory implements Callback<ListView<AllBrushTypeEnum>, ListCell<AllBrushTypeEnum>> {
    @Override
    public ListCell<AllBrushTypeEnum> call(ListView<AllBrushTypeEnum> enumAllTypeOfBrushListView) {
        return new CustomTypeOfBrushListCell();
    }
}
