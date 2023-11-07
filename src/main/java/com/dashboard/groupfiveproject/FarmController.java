package com.dashboard.groupfiveproject;

import javafx.util.Duration;

import javafx.animation.PauseTransition;
import javafx.animation.TranslateTransition;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.shape.Circle;
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
    private Circle drone;

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
        public void initializescanFarmButton(){
        scanFarm.setOnAction(event -> setStartingPosition(-290,-110));
        double InitialSpotX = drone.getCenterX();
        double InitialSpotY = drone.getCenterY();
        System.out.println(InitialSpotX +" "+ "This is the x coordinate for the command center");
        System.out.println(InitialSpotY +" "+ "This is the Y coordinate for the command center");
        // Starting point is top left at x = -290 y = -110
        // Field has a total length of = 965
        // Field has a total width of = 670  
    }

    public void setStartingPosition(double targetX, double targetY) {
        double dx = targetX - drone.getCenterX();
        double dy = targetY - drone.getCenterY();
        System.out.println(dx + " " + "This is the starting x coordinate");
        System.out.println(dy + " " + "This is the starting y coordinate");
        Duration speed = Duration.seconds(1);
        TranslateTransition startPosition = new TranslateTransition(speed, drone);
        startPosition.setToX(dx);
        startPosition.setToY(dy);
        startPosition.setOnFinished(event -> {
            scan(dx,dy);
        });
        startPosition.play();
    }

    public void scan(double dx, double dy) {
        if(dx == -290){
            GoRight();
        }
        else if(dx == 1275.0){
            GoLeft();
        }
        else if(dx == 310.0){
            GoRight();
        }
        if(dy == 675.0){
            StopScan();
        }
    }

    public void GoRight(){
        Duration speed = Duration.seconds(1);
        TranslateTransition GoRight = new TranslateTransition(speed,drone);
        GoRight.setByX(965);
        GoRight.setOnFinished(event -> {
            GoDown();
        });
        GoRight.play();
    }

    public void GoDown(){
        Duration speed = Duration.seconds(1);
        Duration delayDuration = Duration.seconds(0.01);
        PauseTransition delay = new PauseTransition(delayDuration);
        TranslateTransition GoDown = new TranslateTransition(speed, drone);
        GoDown.setByY(20);
        delay.play();
        delay.setOnFinished(event -> {
            double dx = drone.localToScene(drone.getBoundsInLocal()).getCenterX();
            double dy = drone.localToScene(drone.getBoundsInLocal()).getCenterY();
            GoDown.play();
            System.out.println(dx + " " + "This is the current x coordinate");
            System.out.println(dy + " " + "This is the current y coordinate");
            scan(dx, dy);
        });
    }

    public void GoLeft(){
        Duration speed = Duration.seconds(1);
        TranslateTransition GoLeft = new TranslateTransition(speed,drone);
        GoLeft.setByX(-965);
        GoLeft.setOnFinished(event -> {
            GoDown();
        });
        GoLeft.play();
    }

    public void StopScan(){
        Duration speed = Duration.seconds(1);
        TranslateTransition BackToStartPosition = new TranslateTransition(speed, drone);
        BackToStartPosition.setToX(0);
        BackToStartPosition.setToY(0);
        BackToStartPosition.play();
    }

    @FXML
    private TreeView<FarmObject> treeView;

    @FXML
    private Button visitFarmItem;

    public void initialize() {
        commandChoiceBox.setItems(FXCollections.observableArrayList("Change Name", "Change Price",
                "Change Location X", "Change Location Y", "Change Length", "Change Width", "Change Height"));

        TreeItem rootItem = new TreeItem<>("Root");
        TreeItem barnItem = new TreeItem<>("Barn");
        TreeItem liveStockItem = new TreeItem<>("Live Stock Area");
        TreeItem cowItem = new TreeItem<>("Cow");
        TreeItem milkStorageItem = new TreeItem<>("Milk Storage");
        TreeItem commandCenter = new TreeItem<>("Command Center");
        TreeItem drone = new TreeItem<>("Drone");
        TreeItem crops = new TreeItem<>("Crops");

        treeView.setRoot(rootItem);

        rootItem.getChildren().addAll(barnItem, commandCenter, crops);
        barnItem.getChildren().addAll(liveStockItem, milkStorageItem, drone);
        liveStockItem.getChildren().add(cowItem);

    }

    /*
     * public void onSubmitButtonClicked() {
     * String selectedCommand = commandChoiceBox.getValue();
     * TreeItem<FarmObject> selectedItem = (TreeItem<FarmObject>)
     * FarmObject selectedObject = selectedItem.getValue();
     * }
     */

}
