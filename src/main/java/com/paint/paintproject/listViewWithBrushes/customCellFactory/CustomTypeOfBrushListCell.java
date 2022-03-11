package com.paint.paintproject.listViewWithBrushes.customCellFactory;

import com.paint.paintproject.enumAllTypeBrush.AllBrushTypeEnum;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

public class CustomTypeOfBrushListCell extends ListCell<AllBrushTypeEnum> {
    private ImageView iv;
    private Label nameOfBrush;
    private HBox vb;

    public CustomTypeOfBrushListCell(){
        nameOfBrush = new Label();
        iv = loadImage(20);
        vb = new HBox(iv, nameOfBrush);
        vb.setSpacing(12); vb.setAlignment(Pos.CENTER_LEFT);
    }

    private ImageView loadImage(double size){
        ImageView iv = new ImageView();
        try {
            InputStream stream = new FileInputStream("E:\\workspace\\paint-project\\images\\icons8-pencil-50.png");
            Image image = new Image(stream, size, size, false, true);
            iv = new ImageView(image);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return iv;
    }

    @Override
    protected void updateItem(AllBrushTypeEnum brush, boolean empty) {
        super.updateItem(brush, empty);
        if(brush != null && !empty){
            nameOfBrush.setText(brush.name());
            setGraphic(vb);
        }else
            setGraphic(null);
    }
}
