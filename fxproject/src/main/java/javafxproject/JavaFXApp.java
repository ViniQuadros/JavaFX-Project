package javafxproject;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.stage.Stage;

public class JavaFXApp extends Application {

    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("ProjectLayout.fxml"));
        Parent root = fxmlLoader.load();
        ListController listController = fxmlLoader.getController();
        Scene scene = new Scene(root);

        scene.setOnKeyPressed(event -> {
            if (event.getCode() == KeyCode.ENTER && !listController.getTxtAdd().isEmpty()) {
                try {
                    listController.addListItem();
                } catch (IOException ex) {
                    System.out.println("Error on getting key event!");
                }
            }
        });
        scene.setOnKeyReleased(event -> {
            if (event.getCode() == KeyCode.ENTER) {
                System.out.println(listController.getTxtAdd());
            }
        });

        stage.setTitle("JavaFX List");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }

}