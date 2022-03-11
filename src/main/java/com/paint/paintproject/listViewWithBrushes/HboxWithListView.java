package com.paint.paintproject.listViewWithBrushes;

import com.paint.paintproject.brushs.Brush;
import com.paint.paintproject.brushs.brushFactory.BrushFactory;
import com.paint.paintproject.enumAllTypeBrush.AllBrushTypeEnum;
import com.paint.paintproject.listViewWithBrushes.customCellFactory.CustomCellFactoryBrushInstances;
import com.paint.paintproject.listViewWithBrushes.customCellFactory.CustomTypeOfBrushCellFactory;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.ListView;
import javafx.scene.layout.HBox;

import java.util.ArrayList;

public class HboxWithListView extends HBox {
    private ObservableList<Brush> data2 = FXCollections.observableArrayList();

    public HboxWithListView() {
        getChildren().addAll(listViewOfTypeBrush(), listViewOfBrushInstances(AllBrushTypeEnum.SHAPE));
    }

    private ListView<AllBrushTypeEnum> listViewOfTypeBrush(){
        ObservableList<AllBrushTypeEnum> data = FXCollections.observableArrayList(AllBrushTypeEnum.values());
        ListView<AllBrushTypeEnum> lv = new ListView<>(data);
        lv.setPrefWidth(110); lv.setPrefHeight(655);
        lv.getSelectionModel().select(0);
        lv.setCellFactory(new CustomTypeOfBrushCellFactory());
        lv.setOnMouseClicked(event -> {
            AllBrushTypeEnum type =  lv.getSelectionModel().getSelectedItem();
            data2.clear();
            data2.addAll(FXCollections.observableArrayList(getArrayOfBrushInstance(type)));
        });
        return lv;
    }

    private ListView<Brush> listViewOfBrushInstances(AllBrushTypeEnum type){
        data2 = FXCollections.observableArrayList(getArrayOfBrushInstance(type));
        ListView<Brush> lv = new ListView<>(data2);
        lv.setPrefWidth(250); lv.setPrefHeight(655);
        lv.setCellFactory(new CustomCellFactoryBrushInstances());
        lv.setOnMouseClicked(event -> { Brush brush = (Brush) lv.getSelectionModel().getSelectedItem(); });
        lv.refresh();
        return lv;
    }

    private ArrayList<Brush> getArrayOfBrushInstance(AllBrushTypeEnum type){
        ArrayList<Brush> al = new ArrayList<>();
        for(Object o : type.enumValuesOfBrush())
            al.add(BrushFactory.createBrush(o));
        return al;
    }
}
