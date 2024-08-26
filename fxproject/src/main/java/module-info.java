module javafxproject {
    requires javafx.controls;
    requires javafx.fxml;

    opens javafxproject to javafx.fxml;
    exports javafxproject;
}
