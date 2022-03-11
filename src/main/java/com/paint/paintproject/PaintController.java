package com.paint.paintproject;

import com.paint.paintproject.brushs.Brush;
import com.paint.paintproject.brushs.simple.Simple2;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;

import java.net.URL;
import java.util.ResourceBundle;

public class PaintController implements Initializable{
    @FXML private ColorPicker colorPicker;
    @FXML private TextField bsize;
    @FXML private Canvas canvas;
    @FXML private Button chooseBrush, buttonBrush;
    @FXML private Pane brushList;

    boolean buttonSelected = false;
    GraphicsContext brushTool;
    Brush brush = new Brush();
    private Color color = Color.BLACK;
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        brushTool = canvas.getGraphicsContext2D();
        //brush = new Brush(4, Color.BLACK, brushTool);
        Simple2 b = new Simple2();
        b.addListeners(canvas);
        b.startAnimation(brushTool);

        /*colorPicker.setValue(Color.BLACK);

        bsize.setOnAction(event -> {
            brush.createBrushVariations(Double.parseDouble(bsize.getText()), color);
        });

        colorPicker.setOnAction(event -> {
            color = colorPicker.getValue();
            if(bsize.getText().isEmpty()) brush.createBrushVariations(4, colorPicker.getValue());
            else brush.createBrushVariations(Double.parseDouble(bsize.getText()), colorPicker.getValue());
        });

        chooseBrush.setOnAction(event -> {
            if(!buttonSelected){
                buttonSelected = true;
                brushList.getChildren().add(new HboxWithListView());
                brushList.setVisible(true);
            }else{
                brushList.setVisible(false);
                buttonSelected=false;
            }
        });*/
        buttonBrush.setOnMouseClicked(event -> {
            brushTool.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
        });
    }

}

































