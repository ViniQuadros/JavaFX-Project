package javafxproject;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.ResourceBundle;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.TextFieldListCell;
import javafx.scene.input.ClipboardContent;
import javafx.scene.input.DragEvent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.TransferMode;
import javafx.util.converter.DefaultStringConverter;

public class ListController implements Initializable {

    private UserInfo userInfo = new UserInfo();

    @FXML
    private Button btnAdd;
    @FXML
    private Button btnDelete;

    @FXML
    private MenuItem saveBtn;
    @FXML
    private MenuItem closeBtn;

    @FXML
    private ListView<String> listView;
    private List<String> list = new ArrayList<>();
    private ObservableList<String> obsList;

    @FXML
    private TextField txtAdd;

    public String getTxtAdd() {
        return txtAdd.getText();
    }

    @FXML
    private MenuItem sortAlphabetical;
    @FXML
    private MenuItem sortAlphabeticalBtn;

    @FXML
    void saveList(ActionEvent event) {
        userInfo.saveList(listView);

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Success");
        alert.setHeaderText("Save successful");
        alert.showAndWait();
    }

    @FXML
    void closeApp(ActionEvent event) {
        System.out.println("Closing App");
        Platform.exit();
    }

    @FXML
    void addListItem() throws IOException {
        String item = txtAdd.getText();

        if (!item.isEmpty()) {
            // Add the string to the Array List
            list.add(item);
        }

        // Covert ArrayList to Observable list
        obsList = FXCollections.observableArrayList(list);
        // Add Observable list to list view
        listView.setItems(obsList);

        txtAdd.clear();

        userInfo.saveList(listView);
    }

    @FXML
    void deleteListItem(ActionEvent event) {
        // Get index of the selected item in the list
        final int selectedIdx = listView.getSelectionModel().getSelectedIndex();

        if (selectedIdx != -1) {
            try {
                if (selectedIdx != -1) {
                    final int newSelectedIdx = (selectedIdx == listView.getItems().size() - 1)
                            ? selectedIdx - 1
                            : selectedIdx;

                    listView.getItems().remove(selectedIdx);
                    listView.getSelectionModel().select(newSelectedIdx);
                    list.remove(selectedIdx);

                    userInfo.saveList(listView);
                }
            } catch (Exception e) {
                System.out.println("Error deleting item: " + e.getMessage());
            }
        } else {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Warning");
            alert.setHeaderText("Select an item to delete");
            alert.showAndWait();
        }
    }

    @FXML
    void sortAlphabetical(ActionEvent event) {
        FXCollections.sort(obsList);
    }

    @FXML
    void sortAlphabeticalDown(ActionEvent event) {
        FXCollections.sort(obsList, Collections.reverseOrder());
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        listView.setEditable(true);

        // Edit row in the list
        listView.setCellFactory(TextFieldListCell.forListView(new DefaultStringConverter()));
        listView.setOnEditCommit(event -> {
            int index = event.getIndex();
            String newValue = event.getNewValue();
            obsList.set(index, newValue);
        });

        // Drag and Drop System
        listView.setCellFactory(lv -> {
            ListCell<String> cell = new ListCell<>() {
                @Override
                protected void updateItem(String item, boolean empty) {
                    super.updateItem(item, empty);
                    if (empty || item == null) {
                        setText(null);
                    } else {
                        setText(item);
                    }
                }
            };

            // Begin Drag
            cell.setOnDragDetected(event -> {
                if (cell.getItem() == null)
                    return;

                Dragboard dragboard = cell.startDragAndDrop(TransferMode.MOVE);

                ClipboardContent content = new ClipboardContent();
                content.putString(cell.getItem());
                dragboard.setContent(content);

                event.consume();
            });

            cell.setOnDragOver(event -> {
                if (event.getGestureSource() != cell && event.getDragboard().hasString()) {
                    event.acceptTransferModes(TransferMode.MOVE);
                }

                event.consume();
            });

            cell.setOnDragDropped(event -> {
                Dragboard dragboard = event.getDragboard();

                if (dragboard == null)
                    return;

                if (dragboard.hasString()) {
                    int draggedIndex = listView.getItems().indexOf(dragboard.getString());
                    int currentIndex = listView.getItems().indexOf(cell.getItem());

                    // Reorder list items
                    if (draggedIndex != currentIndex) {
                        String dragString = listView.getItems().remove(draggedIndex);
                        listView.getItems().add(currentIndex, dragString);
                    }

                    event.setDropCompleted(true);
                    listView.refresh();
                }
                event.consume();
            });

            cell.setOnDragDone(DragEvent::consume);

            return cell;
        });

        userInfo.loadList(list);
        obsList = FXCollections.observableArrayList(list);
        listView.setItems(obsList);
    }
}