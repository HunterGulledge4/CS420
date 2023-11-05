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

    public void initialize() {
        commandChoiceBox.setItems(FXCollections.observableArrayList("Change Name", "Change Price",
                "Change Location X", "Change Location Y", "Change Length", "Change Width", "Change Height"));
    }

    /*
    public void onSubmitButtonClicked() {
        String selectedCommand = commandChoiceBox.getValue();
        TreeItem<FarmObject> selectedItem = (TreeItem<FarmObject>)
        FarmObject selectedObject = selectedItem.getValue();
    }
    */

}
