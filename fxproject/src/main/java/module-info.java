module javafxproject {
    requires javafx.controls;
    requires javafx.fxml;
    requires transitive javafx.graphics;
    requires javafx.base;

    opens javafxproject to javafx.fxml;
    exports javafxproject;
}