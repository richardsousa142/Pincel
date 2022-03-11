package com.paint.paintproject.listViewWithBrushes.customCellFactory;

import com.paint.paintproject.brushs.Brush;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.util.Callback;

public class CustomCellFactoryBrushInstances implements Callback<ListView<Brush>, ListCell<Brush>> {
    @Override
    public ListCell<Brush> call(ListView<Brush> enumAllTypeOfBrushListView) {
        return new CustomCellBrushInstance();
    }
}
