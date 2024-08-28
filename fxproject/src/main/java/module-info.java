module javafxproject {
    requires javafx.controls;
    requires javafx.fxml;
    requires transitive javafx.graphics;

    opens javafxproject to javafx.fxml;
    exports javafxproject;
}