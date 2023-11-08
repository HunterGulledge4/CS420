package com.dashboard.groupfiveproject;

import javafx.geometry.Rectangle2D;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.StrokeType;
import javafx.util.Duration;
import javafx.animation.PauseTransition;
import javafx.animation.TranslateTransition;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;

import static javafx.scene.paint.Color.BLACK;


public class FarmController {

    @FXML
    private Button buttonSubmit;

    @FXML
    private Rectangle commandCenter;

    @FXML
    private ChoiceBox<String> crudChoiceBox;

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
    private Button scanFarm;

    @FXML
    private TreeView<FarmObject> treeView;

    @FXML
    private AnchorPane anchorPane;

    @FXML
    private Button visitFarmItem;
  
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

    public void initializevisitFarmItemButton(){
        //double x = (double) farmObject.getLocationX();
        //double y = (double) farmObject.getLocationY();
        TreeItem<FarmObject> selectedItem = treeView.getSelectionModel().getSelectedItem();
        double x = selectedItem.getValue().getLocationX();
        double y = selectedItem.getValue().getLocationY();
        visitFarmItem.setOnAction(event -> setStartingPositionOfFarmItem(x,y));
        double InitialSpotX = drone.getCenterX();
        double InitialSpotY = drone.getCenterY();
        System.out.println(InitialSpotX +" "+ "This is the x coordinate for the command center");
        System.out.println(InitialSpotY +" "+ "This is the Y coordinate for the command center");
    }


        public void setStartingPositionOfFarmItem(double targetX, double targetY) {
        double dx = targetX - drone.getCenterX();
        double dy = targetY - drone.getCenterY();
        System.out.println(dx + " " + "This is the starting x coordinate");
        System.out.println(dy + " " + "This is the starting y coordinate");
        Duration speed = Duration.seconds(1);
        TranslateTransition startPosition = new TranslateTransition(speed, drone);
        startPosition.setToX(dx);
        startPosition.setToY(dy);
        startPosition.play();
    }
  
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
        int entryLocationX = Integer.parseInt(entryFieldLocationX.getText().isBlank() ? "0" : entryFieldLocationX.getText());
        int entryLocationY = Integer.parseInt(entryFieldLocationY.getText().isBlank() ? "0" : entryFieldLocationY.getText());
        int entryLength = Integer.parseInt(entryFieldLength.getText().isBlank() ? "0" : entryFieldLength.getText());
        int entryWidth = Integer.parseInt(entryFieldWidth.getText().isBlank() ? "0" : entryFieldWidth.getText());
        int entryHeight = Integer.parseInt(entryFieldHeight.getText().isBlank() ? "0" : entryFieldHeight.getText());
        Rectangle rectangle2 = new Rectangle(entryLocationX, entryLocationY, entryWidth, entryHeight);

        TreeItem<FarmObject> selectedItem = treeView.getSelectionModel().getSelectedItem();

        FarmObject farmObject = new FarmObject(entryName, entryPrice, entryLocationX, entryLocationY, entryLength,
                entryWidth, entryHeight, rectangle2);

        TreeItem<FarmObject> object = new TreeItem<FarmObject>(farmObject);
        selectedItem.getChildren().add(object);

        drawRect(rectangle2, entryLocationX, entryLocationY, entryWidth, entryHeight);
    }

    public void Deleteitem() {
        TreeItem<FarmObject> selectedItem = treeView.getSelectionModel().getSelectedItem();
        selectedItem.getParent().getChildren().remove(selectedItem);
    }

    public void Additemcontainer() {
        String entryName = entryFieldName.getText().isBlank() ? "Left Blank" : entryFieldName.getText();
        float entryPrice = Float.parseFloat(entryFieldPrice.getText().isBlank() ? "0" : entryFieldPrice.getText());
        int entryLocationX = Integer.parseInt(entryFieldLocationX.getText().isBlank() ? "0" : entryFieldLocationX.getText());
        int entryLocationY = Integer.parseInt(entryFieldLocationY.getText().isBlank() ? "0" : entryFieldLocationY.getText());
        int entryLength = Integer.parseInt(entryFieldLength.getText().isBlank() ? "0" : entryFieldLength.getText());
        int entryWidth = Integer.parseInt(entryFieldWidth.getText().isBlank() ? "0" : entryFieldWidth.getText());
        int entryHeight = Integer.parseInt(entryFieldHeight.getText().isBlank() ? "0" : entryFieldHeight.getText());
        Rectangle rectangle3 = new Rectangle(entryLocationX, entryLocationY, entryWidth, entryHeight);

        TreeItem<FarmObject> selectedItem = treeView.getSelectionModel().getSelectedItem();

        FarmObject farmObject = new FarmObject(entryName, entryPrice, entryLocationX, entryLocationY, entryLength,
                entryWidth, entryHeight, rectangle3);

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
            selectedItem.getValue().setRectangle(selectedItem.getValue().getRectangle(), Integer.parseInt(entryFieldLocationX.getText()),
                    Integer.parseInt(entryFieldLocationY.getText()), Integer.parseInt(entryFieldWidth.getText()),
                    Integer.parseInt(entryFieldHeight.getText()));
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
        Rectangle commandCenterRectangle = new Rectangle(commandCenter.getLayoutX(), commandCenter.getLayoutY(), commandCenter.getWidth(), commandCenter.getHeight());
        anchorPane.getChildren().add(commandCenterRectangle);
        crudChoiceBox.setItems(FXCollections.observableArrayList("Add Item", "Delete Item", "Add Item Container", "Delete Item Container", "Change Value(s)"));
        TreeItem<FarmObject> rootItem = new TreeItem<>(new FarmObject("Root", 0, 0, 0,
                0, 0, 0, commandCenterRectangle));
        treeView.setRoot(rootItem);
     
        treeView.setCellFactory(param -> new TreeCell<FarmObject>() {
          
            @Override
            protected void updateItem(FarmObject item, boolean empty) {
                super.updateItem(item, empty);

                if (empty || item == null) {
                    setText(null);
                } else {
                    setText(item.getName());
                }
            }
        });
    }

    /*
    public Rectangle2D drawRectangle() {
        TreeItem<FarmObject> selectedItem = treeView.getSelectionModel().getSelectedItem();
        double x = selectedItem.getValue().getLocationX();
        double y = selectedItem.getValue().getLocationY();
        double width = selectedItem.getValue().getWidth();
        double height = selectedItem.getValue().getHeight();

        return new Rectangle2D(x, y, width, height);
    }
     */

    @FXML
    public void drawRect(Rectangle r, double x, double y, double width, double height){
        //Rectangle2D r = new Rectangle2D(x, y, width, height);
        //Rectangle r = new Rectangle(x, y, width, height);
        r.setX(x);
        r.setY(y);
        r.setWidth(width);
        r.setHeight(height);
        r.setStrokeType(StrokeType.INSIDE);
        r.setStroke(BLACK);
        anchorPane.getChildren().add(r);
    }


        //A2.getChildren().add(r);

}
