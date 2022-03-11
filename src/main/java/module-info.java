module com.paint.paintproject {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;


    opens com.paint.paintproject to javafx.fxml;
    exports com.paint.paintproject;
}