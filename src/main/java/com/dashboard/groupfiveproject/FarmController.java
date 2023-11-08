package com.dashboard.groupfiveproject;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.shape.Rectangle;

public class FarmController {

    @FXML
    private Rectangle barn;

    @FXML
    private Button buttonSubmit;

    @FXML
    private Rectangle commandCenter;

    @FXML
    private ChoiceBox<String> crudChoiceBox;

    @FXML
    private ChoiceBox<String> commandChoiceBox;

    @FXML
    private Rectangle cow;

    @FXML
    private Rectangle cropField;

    @FXML
    private TextField entryFieldHeight;

    @FXML
    private TextField entryFieldLength;

    @FXML
    private TextField entryFieldLocationX;

    @FXML
    private TextField entryFieldLocationY;

    @FXML
    private TextField entryFieldName;

    @FXML
    private TextField entryFieldPrice;

    @FXML
    private TextField entryFieldWidth;

    @FXML
    private Label labelHeight;

    @FXML
    private Label labelLength;

    @FXML
    private Label labelLocationX;

    @FXML
    private Label labelLocationY;

    @FXML
    private Label labelName;

    @FXML
    private Label labelPrice;

    @FXML
    private Label labelWidth;

    @FXML
    private Rectangle liveStockArea;

    @FXML
    private Rectangle milkStorage;

    @FXML
    private Button scanFarm;

    @FXML
    private TreeView<FarmObject> treeView;

    @FXML
    private Button visitFarmItem;

    public void Executecommand() {

        if (crudChoiceBox.getValue().equals("Add Item")) {
            Additem();
        } else if (crudChoiceBox.getValue().equals("Delete Item")) {
            Deleteitem();
        } else if (crudChoiceBox.getValue().equals("Add Item Container")) {
            Additemcontainer();
        } else if (crudChoiceBox.getValue().equals("Delete Item Container")) {
            Deleteitemcontainer();
        } else {
            Changestuff();
        }

    }

    public void Additem() {
        String entryName = entryFieldName.getText().isBlank() ? "Left Blank" : entryFieldName.getText();
        float entryPrice = Float.parseFloat(entryFieldPrice.getText().isBlank() ? "0" : entryFieldPrice.getText());
        int entryLocationX = Integer
                .parseInt(entryFieldLocationX.getText().isBlank() ? "0" : entryFieldLocationX.getText());
        int entryLocationY = Integer
                .parseInt(entryFieldLocationY.getText().isBlank() ? "0" : entryFieldLocationY.getText());
        int entryLength = Integer.parseInt(entryFieldLength.getText().isBlank() ? "0" : entryFieldLength.getText());
        int entryWidth = Integer.parseInt(entryFieldWidth.getText().isBlank() ? "0" : entryFieldWidth.getText());
        int entryHeight = Integer.parseInt(entryFieldHeight.getText().isBlank() ? "0" : entryFieldHeight.getText());

        TreeItem<FarmObject> selectedItem = treeView.getSelectionModel().getSelectedItem();

        FarmObject farmObject = new FarmObject(entryName, entryPrice, entryLocationX, entryLocationY, entryLength,
                entryWidth, entryHeight);

        TreeItem<FarmObject> object = new TreeItem<FarmObject>(farmObject);
        selectedItem.getChildren().add(object);

    }

    public void Deleteitem() {
        TreeItem<FarmObject> selectedItem = treeView.getSelectionModel().getSelectedItem();
        selectedItem.getParent().getChildren().remove(selectedItem);
    }

    public void Additemcontainer() {
        String entryName = entryFieldName.getText().isBlank() ? "Left Blank" : entryFieldName.getText();
        float entryPrice = Float.parseFloat(entryFieldPrice.getText().isBlank() ? "0" : entryFieldPrice.getText());
        int entryLocationX = Integer
                .parseInt(entryFieldLocationX.getText().isBlank() ? "0" : entryFieldLocationX.getText());
        int entryLocationY = Integer
                .parseInt(entryFieldLocationY.getText().isBlank() ? "0" : entryFieldLocationY.getText());
        int entryLength = Integer.parseInt(entryFieldLength.getText().isBlank() ? "0" : entryFieldLength.getText());
        int entryWidth = Integer.parseInt(entryFieldWidth.getText().isBlank() ? "0" : entryFieldWidth.getText());
        int entryHeight = Integer.parseInt(entryFieldHeight.getText().isBlank() ? "0" : entryFieldHeight.getText());

        TreeItem<FarmObject> selectedItem = treeView.getSelectionModel().getSelectedItem();

        FarmObject farmObject = new FarmObject(entryName, entryPrice, entryLocationX, entryLocationY, entryLength,
                entryWidth, entryHeight);

        TreeItem<FarmObject> object = new TreeItem<FarmObject>(farmObject);
        selectedItem.getChildren().add(object);
    }

    public void Deleteitemcontainer() {
        TreeItem<FarmObject> selectedItem = treeView.getSelectionModel().getSelectedItem();
        selectedItem.getParent().getChildren().remove(selectedItem);
    }

    public void Changestuff() {
        TreeItem<FarmObject> selectedItem = treeView.getSelectionModel().getSelectedItem();

        if (!entryFieldName.getText().isBlank()) {
            selectedItem.getValue().setName(entryFieldName.getText());
            System.out.println(!entryFieldName.getText().isBlank());
        }
        if (!entryFieldPrice.getText().isBlank()) {
            selectedItem.getValue().setPrice(Float.parseFloat(entryFieldPrice.getText()));
        }
        if (!entryFieldLocationX.getText().isBlank()) {
            selectedItem.getValue().setLocationX(Integer.parseInt(entryFieldLocationX.getText()));
        }
        if (!entryFieldLocationY.getText().isBlank()) {
            selectedItem.getValue().setLocationY(Integer.parseInt(entryFieldLocationY.getText()));
        }
        if (!entryFieldLength.getText().isBlank()) {
            selectedItem.getValue().setLength(Integer.parseInt(entryFieldLength.getText()));
        }
        if (!entryFieldWidth.getText().isBlank()) {
            selectedItem.getValue().setWidth(Integer.parseInt(entryFieldWidth.getText()));
        }
        if (!entryFieldHeight.getText().isBlank()) {
            selectedItem.getValue().setHeight(Integer.parseInt(entryFieldHeight.getText()));
        }

    }

    public void initialize() {

        crudChoiceBox.setItems(FXCollections.observableArrayList("Add Item", "Delete Item", "Add Item Container", "Delete Item Container", "Change Value(s)");
        // Initialize the root TreeItem and set it as the root of the TreeView
        TreeItem<FarmObject> rootItem = new TreeItem<>(new FarmObject("Root", 0, 0, 0, 0, 0, 0)); // You can set default
                                                                                                  // values
        treeView.setRoot(rootItem);

        // Set a custom CellFactory to display the name of FarmObjects
        treeView.setCellFactory(param -> new TreeCell<FarmObject>() {
            @Override
            protected void updateItem(FarmObject item, boolean empty) {
                super.updateItem(item, empty);

                if (empty || item == null) {
                    setText(null);
                } else {
                    setText(item.getName()); // Display the name of the FarmObject
                }
            }
        });
    }
}
