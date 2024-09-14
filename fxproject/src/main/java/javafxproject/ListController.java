package javafxproject;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.TextFieldListCell;
import javafx.util.converter.DefaultStringConverter;

public class ListController implements Initializable {

    @FXML
    private Button btnAdd;

    @FXML
    private Button btnDelete;

    @FXML
    private ListView<String> listView;
    private List<String> list = new ArrayList<>();
    private ObservableList<String> obsList;

    @FXML
    private TextField txtAdd;

    @FXML
    private MenuItem sortAlphabetical;

    @FXML
    void addListItem(ActionEvent event) throws IOException {
        String item = txtAdd.getText();

        if (!item.isEmpty()) {
            //Add the string to the Array List
            list.add(item);
        }

        //Covert ArrayList to Observable list
        obsList = FXCollections.observableArrayList(list);

        //Add Observable list to list view
        listView.setItems(obsList);

        txtAdd.clear();
    }

    @FXML
    void deleteListItem(ActionEvent event) {
        //Get index of the selected item in the list
        final int selectedIdx = listView.getSelectionModel().getSelectedIndex();

        //Check if the selected index is the last
        if (selectedIdx != -1) {
          final int newSelectedIdx =
            (selectedIdx == listView.getItems().size() - 1)
               ? selectedIdx - 1
               : selectedIdx;
 
          listView.getItems().remove(selectedIdx);
          listView.getSelectionModel().select(newSelectedIdx);
          list.remove(selectedIdx);
        }
    }

    @FXML
    void sortAlphabetical(ActionEvent event) {
        listView.setItems(obsList.sorted());
    }

    @Override
    public void initialize(URL url, ResourceBundle rb)
    {
        listView.setEditable(true);
        listView.setCellFactory(TextFieldListCell.forListView(new DefaultStringConverter()));
        listView.setOnEditCommit(event -> {
            int index = event.getIndex();
            String newValue = event.getNewValue();
            obsList.set(index, newValue);
        });
    }      
}