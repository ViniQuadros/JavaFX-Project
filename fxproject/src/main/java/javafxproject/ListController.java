import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class ListController {

    @FXML
    private Button btnAdd;

    @FXML
    private Button btnDelete;

    @FXML
    private TextField txtSearch;

    @FXML
    void addListItem(ActionEvent event) {
        btnAdd.getItems().add(item.getText()); // nao sei se assim funciona p adicionar qualquer tipo de item
    }

    @FXML
    void deleteListItem(ActionEvent event) {
        int selectedID = btnAdd.getSelectionModel().getSelectedIndex();
        btnAdd.getItems().remove(selectedID);
    }

}