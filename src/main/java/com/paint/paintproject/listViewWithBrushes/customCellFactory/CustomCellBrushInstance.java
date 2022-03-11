package com.paint.paintproject.listViewWithBrushes.customCellFactory;

import com.paint.paintproject.brushs.Brush;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

public class CustomCellBrushInstance extends ListCell<Brush> {
    private ImageView iv;
    private Label nameOfBrush;
    private HBox hb;
    private VBox vb;
    private Button button;

    public CustomCellBrushInstance(){
        nameOfBrush = new Label();
        button = new Button("", loadImage(10, 10, "menu.png"));//REPENSAR BOTAO
        iv = loadImage(200,50, "brush5.jpg");
        hb = new HBox(iv, button);
        vb = new VBox(hb, nameOfBrush);
        vb.setSpacing(12); vb.setAlignment(Pos.BOTTOM_LEFT);
        //hb.setSpacing(10);
        hb.setAlignment(Pos.TOP_CENTER);
        //button.setBackground(new Background(new BackgroundFill(Color.GRAY, new CornerRadii(0), new Insets(0,0,0,0))));
        button.setOnMouseClicked(event -> {System.out.println(true);});
        HBox.setMargin(hb, new Insets(0,0,0,0));
        HBox.setMargin(vb, new Insets(0,0,0,0));
        HBox.setMargin(button, new Insets(0,0,0,8));
    }
    private ImageView loadImage(double width, double height,String name){
        ImageView iv = new ImageView();
        try {
            InputStream stream = new FileInputStream("E:\\workspace\\paint-project\\images\\"+name);
            Image image = new Image(stream, width, height, false, true);
            iv = new ImageView(image);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return iv;
    }

    @Override
    protected void updateItem(Brush brush, boolean b) {
        super.updateItem(brush, b);
        if(brush != null && !b){
            nameOfBrush.setText(brush.getName());
            setGraphic(vb);
        }else
            setGraphic(null);
    }

}
