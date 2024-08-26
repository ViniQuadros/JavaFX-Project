package javafxproject;

import java.io.IOException;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class HelloWorldFX extends Application {

    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("ProjectLayout.fxml"));
        Parent root = fxmlLoader.load();
        Scene tela = new Scene(root);

        stage.setTitle("JavaFX List");
        stage.setScene(tela);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }

}