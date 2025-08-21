package javafxproject;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.List;

import javafx.scene.control.Alert;
import javafx.scene.control.ListView;

public class UserInfo {
    private final String fileName = "userList.txt";

    public void saveList(ListView<String> listView) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))) {
            for (String item : listView.getItems()) {
                writer.write(item);
                writer.newLine();
            }
        } catch (Exception e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Error on save: " + e.getMessage());
            alert.showAndWait();
        }
    }

    public void loadList(List<String> list) {
        File file = new File(fileName);
        if (!file.exists())
            return;

        // Limpa a lista antes de carregar para evitar duplicações
        list.clear();

        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String linha;
            while ((linha = reader.readLine()) != null) {
                list.add(linha);
            }
        } catch (Exception e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Error on load: " + e.getMessage());
            alert.showAndWait();
        }
    }
}
