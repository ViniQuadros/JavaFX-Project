package javafxproject;

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
import javafx.scene.control.TextField;

public class ListController implements Initializable{

    @FXML
    private Button btnAdd;

    @FXML
    private Button btnDelete;

    @FXML
    private ListView<String> listView;
    private List<String> list = new ArrayList<>();
    private ObservableList<String> obsList;

    @FXML
    private TextField txtSearch;

    @FXML
    void addListItem(ActionEvent event) {
        String item1 = "Teste";

        //Add the string to the Array List
        list.add(item1);
        //Covert ArrayList to Observable list
        obsList = FXCollections.observableArrayList(list);

        //Add Observable list to list view
        listView.setItems(obsList);
    }

    @FXML
    void deleteListItem(ActionEvent event) {

    }

    @Override
    public void initialize(URL url, ResourceBundle rb)
    {
        
    }
}