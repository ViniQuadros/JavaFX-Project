module javafxproject {
    requires javafx.controls;
    requires javafx.fxml;
    requires transitive javafx.graphics;
    requires javafx.base;
    requires java.xml;

    opens javafxproject to javafx.fxml;
    exports javafxproject;
}