package com.dashboard.groupfiveproject;

import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.StrokeType;
import javafx.scene.text.Text;
import javafx.util.Duration;

import javafx.animation.PauseTransition;
import javafx.animation.TranslateTransition;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;

import static javafx.scene.paint.Color.*;

import java.util.ArrayList;
import java.util.List;

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
    private TextField entryFieldShape;

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

    public void initializescanFarmButton() {
        scanFarm.setOnAction(event -> setStartingPosition(-290, -110));
        double InitialSpotX = drone.getCenterX();
        double InitialSpotY = drone.getCenterY();
        System.out.println(InitialSpotX + " " + "This is the x coordinate for the command center");
        System.out.println(InitialSpotY + " " + "This is the Y coordinate for the command center");
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
            scan(dx, dy);
        });
        startPosition.play();
    }

    public void scan(double dx, double dy) {
        if (dx == -290) {
            GoRight();
        } else if (dx == 1275.0) {
            GoLeft();
        } else if (dx == 310.0) {
            GoRight();
        }
        if (dy == 675.0) {
            StopScan();
        }
    }

    public void GoRight() {
        Duration speed = Duration.seconds(1);
        TranslateTransition GoRight = new TranslateTransition(speed, drone);
        GoRight.setByX(965);
        GoRight.setOnFinished(event -> {
            GoDown();
        });
        GoRight.play();
    }

    public void GoDown() {
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

    public void GoLeft() {
        Duration speed = Duration.seconds(1);
        TranslateTransition GoLeft = new TranslateTransition(speed, drone);
        GoLeft.setByX(-965);
        GoLeft.setOnFinished(event -> {
            GoDown();
        });
        GoLeft.play();
    }

    public void StopScan() {
        Duration speed = Duration.seconds(1);
        TranslateTransition BackToStartPosition = new TranslateTransition(speed,
                drone);
        BackToStartPosition.setToX(0);
        BackToStartPosition.setToY(0);
        BackToStartPosition.play();
    }

    public void initializevisitFarmItemButton() {
        // double x = (double) farmObject.getLocationX();
        // double y = (double) farmObject.getLocationY();
        TreeItem<FarmObject> selectedItem = treeView.getSelectionModel().getSelectedItem();
        double x = selectedItem.getValue().getLocationX();
        double y = selectedItem.getValue().getLocationY();
        visitFarmItem.setOnAction(event -> setStartingPositionOfFarmItem(x, y));
        double InitialSpotX = drone.getCenterX();
        double InitialSpotY = drone.getCenterY();
        System.out.println(InitialSpotX + " " + "This is the x coordinate for the command center");
        System.out.println(InitialSpotY + " " + "This is the Y coordinate for the command center");
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
        if (crudChoiceBox.getValue() == null) {
            Additem();
        } else if (crudChoiceBox.getValue().equals("Delete Item")) {
            Deleteitem();
        } else if (crudChoiceBox.getValue().equals("Add Item Container")) {
            Additemcontainer();
        } else if (crudChoiceBox.getValue().equals("Delete Item Container")) {
            Deleteitemcontainer();
        } else if (crudChoiceBox.getValue().equals("Change Value(s)")) {
            Changestuff();
        } else if (crudChoiceBox.getValue().equals("Add Item")) {
            Additem();
        }

    }

    public void Additem() {
        String entryName = entryFieldName.getText().isBlank() ? "Left Blank" : entryFieldName.getText();
        float entryPrice = Float.parseFloat(entryFieldPrice.getText().isBlank() ? "0"
                : entryFieldPrice.getText());
        int entryLocationX = Integer
                .parseInt(entryFieldLocationX.getText().isBlank() ? "0" : entryFieldLocationX.getText());
        int entryLocationY = Integer
                .parseInt(entryFieldLocationY.getText().isBlank() ? "0" : entryFieldLocationY.getText());
        int entryLength = Integer.parseInt(entryFieldLength.getText().isBlank() ? "0"
                : entryFieldLength.getText());
        int entryWidth = Integer.parseInt(entryFieldWidth.getText().isBlank() ? "0" : entryFieldWidth.getText());
        int entryHeight = Integer.parseInt(entryFieldHeight.getText().isBlank() ? "0"
                : entryFieldHeight.getText());
        String entryShape = entryFieldShape.getText().isBlank() ? "Rectangle" : entryFieldShape.getText();
        TreeItem<FarmObject> selectedItem = treeView.getSelectionModel().getSelectedItem();

        if (entryLocationX < 300 || entryLocationX > 1280) {
            entryLocationX = 300;
        }
        if (entryLocationY < 0 || entryLocationY > 650) {
            entryLocationY = 0;
        }
        if (entryLength < 0 || entryLength > 325) {
            entryLength = 50;
        }
        if (entryHeight < 0 || entryHeight > 650) {
            entryHeight = 50;
        }
        if (entryWidth < 0 || entryWidth > 980) {
            entryWidth = 50;
        }

        if (selectedItem == null) {
            selectedItem = treeView.getRoot();
            treeView.getSelectionModel().select(selectedItem);
        }

        FarmObject farmObject = new FarmObject(entryName, entryPrice, entryLocationX,
                entryLocationY, entryLength,
                entryWidth, entryHeight, entryShape);

        TreeItem<FarmObject> object = new TreeItem<FarmObject>(farmObject);
        selectedItem.getChildren().add(object);

        if (entryShape.toLowerCase().equalsIgnoreCase("rectangle")) {
            Rectangle rectangle = new Rectangle(entryLocationX, entryLocationY,
                    entryWidth, entryHeight);
            anchorPane.getChildren().add(rectangle);
            rectangle.setStroke(Color.BLACK);
            rectangle.setFill(Color.TRANSPARENT);
            Text text = new Text(entryName);
            text.setLayoutX(entryLocationX);
            text.setLayoutY(entryLocationY + 20);
            anchorPane.getChildren().add(text);
        } else if (entryShape.toLowerCase().equalsIgnoreCase("circle")) {
            Circle circle = new Circle(entryLocationX + entryLength, entryLocationY + entryLength, entryLength);
            anchorPane.getChildren().add(circle);
            circle.setStroke(Color.BLACK);
            circle.setFill(Color.TRANSPARENT);
            Text text = new Text(entryName);
            text.setLayoutX(entryLocationX);
            text.setLayoutY(entryLocationY + 20);
            anchorPane.getChildren().add(text);
        } else {
            Rectangle rectangle = new Rectangle(entryLocationX, entryLocationY,
                    entryWidth, entryHeight);
            anchorPane.getChildren().add(rectangle);
            rectangle.setStroke(Color.BLACK);
            rectangle.setFill(Color.TRANSPARENT);
            Text text = new Text(entryName);
            text.setLayoutX(entryLocationX);
            text.setLayoutY(entryLocationY + 20);
            anchorPane.getChildren().add(text);
        }

    }

    private void traverseTree(TreeItem<FarmObject> item) {

        if (item != null) {
            if (!item.getValue().getName().equalsIgnoreCase("Root")) {
                if (item.getValue().getShape().equalsIgnoreCase("rectangle")) {
                    Rectangle rectangle = new Rectangle(item.getValue().getLocationX(),
                            item.getValue().getLocationY(),
                            item.getValue().getWidth(), item.getValue().getHeight());
                    rectangle.setStroke(Color.BLACK);
                    rectangle.setFill(Color.TRANSPARENT);
                    anchorPane.getChildren().add(rectangle);
                } else if (item.getValue().getShape().equalsIgnoreCase("circle")) {
                    int radius = item.getValue().getHeight();
                    Circle circle = new Circle(item.getValue().getLocationX(),
                            item.getValue().getLocationY(), radius);
                    circle.setStroke(Color.BLACK);
                    circle.setFill(Color.TRANSPARENT);
                    anchorPane.getChildren().add(circle);
                }
                Text text = new Text(item.getValue().getName());
                text.setLayoutX(item.getValue().getLocationX());
                text.setLayoutY(item.getValue().getLocationY() + 20);
                anchorPane.getChildren().add(text);
            }
            for (TreeItem<FarmObject> child : item.getChildren()) {
                traverseTree(child);
            }
        }
    }

    public void Deleteitem() {
        TreeItem<FarmObject> selectedItem = treeView.getSelectionModel().getSelectedItem();

        List<Node> nodesToRemove = new ArrayList<>();

        if (!selectedItem.equals(rootItem) && selectedItem != null) {
            selectedItem.getParent().getChildren().remove(selectedItem);

            for (Node node : anchorPane.getChildren()) {
                if (node instanceof Rectangle || node instanceof Circle || node instanceof Text) {
                    nodesToRemove.add(node);
                }
            }
            anchorPane.getChildren().removeAll(nodesToRemove);
            traverseTree(rootItem);
        } else {
            treeView.getRoot().getChildren().clear();
            for (Node node : anchorPane.getChildren()) {
                if (node instanceof Rectangle || node instanceof Circle || node instanceof Text) {
                    nodesToRemove.add(node);
                }
            }
            anchorPane.getChildren().removeAll(nodesToRemove);
        }

    }

    public void Additemcontainer() {
        String entryName = entryFieldName.getText().isBlank() ? "Left Blank" : entryFieldName.getText();
        float entryPrice = Float.parseFloat(entryFieldPrice.getText().isBlank() ? "0"
                : entryFieldPrice.getText());
        int entryLocationX = Integer
                .parseInt(entryFieldLocationX.getText().isBlank() ? "0" : entryFieldLocationX.getText());
        int entryLocationY = Integer
                .parseInt(entryFieldLocationY.getText().isBlank() ? "0" : entryFieldLocationY.getText());
        int entryLength = Integer.parseInt(entryFieldLength.getText().isBlank() ? "0"
                : entryFieldLength.getText());
        int entryWidth = Integer.parseInt(entryFieldWidth.getText().isBlank() ? "0" : entryFieldWidth.getText());
        int entryHeight = Integer.parseInt(entryFieldHeight.getText().isBlank() ? "0"
                : entryFieldHeight.getText());
        String entryShape = entryFieldShape.getText().isBlank() ? "Rectangle" : entryFieldShape.getText();
        TreeItem<FarmObject> selectedItem = treeView.getSelectionModel().getSelectedItem();

        if (entryLocationX < 300 || entryLocationX > 1280) {
            entryLocationX = 300;
        }
        if (entryLocationY < 0 || entryLocationY > 650) {
            entryLocationY = 0;
        }
        if (entryLength < 0 || entryLength > 325) {
            entryLength = 50;
        }
        if (entryHeight < 0 || entryHeight > 650) {
            entryHeight = 50;
        }
        if (entryWidth < 0 || entryWidth > 980) {
            entryWidth = 50;
        }

        if (selectedItem == null) {
            selectedItem = treeView.getRoot();
            treeView.getSelectionModel().select(selectedItem);
        }

        FarmObject farmObject = new FarmObject(entryName, entryPrice, entryLocationX,
                entryLocationY, entryLength,
                entryWidth, entryHeight, entryShape);

        TreeItem<FarmObject> object = new TreeItem<FarmObject>(farmObject);
        selectedItem.getChildren().add(object);

        if (entryShape.toLowerCase().equalsIgnoreCase("rectangle")) {
            Rectangle rectangle = new Rectangle(entryLocationX, entryLocationY,
                    entryWidth, entryHeight);
            anchorPane.getChildren().add(rectangle);
            rectangle.setStroke(Color.BLACK);
            rectangle.setFill(Color.TRANSPARENT);
            Text text = new Text(entryName);
            text.setLayoutX(entryLocationX);
            text.setLayoutY(entryLocationY + 20);
            anchorPane.getChildren().add(text);
        } else if (entryShape.toLowerCase().equalsIgnoreCase("circle")) {
            Circle circle = new Circle(entryLocationX, entryLocationY, entryLength);
            anchorPane.getChildren().add(circle);
            circle.setStroke(Color.BLACK);
            circle.setFill(Color.TRANSPARENT);
            Text text = new Text(entryName);
            text.setLayoutX(entryLocationX);
            text.setLayoutY(entryLocationY + 20);
            anchorPane.getChildren().add(text);
        } else {
            Rectangle rectangle = new Rectangle(entryLocationX, entryLocationY,
                    entryWidth, entryHeight);
            anchorPane.getChildren().add(rectangle);
            rectangle.setStroke(Color.BLACK);
            rectangle.setFill(Color.TRANSPARENT);
            Text text = new Text(entryName);
            text.setLayoutX(entryLocationX);
            text.setLayoutY(entryLocationY + 20);
            anchorPane.getChildren().add(text);
        }
    }

    public void Deleteitemcontainer() {
        TreeItem<FarmObject> selectedItem = treeView.getSelectionModel().getSelectedItem();

        List<Node> nodesToRemove = new ArrayList<>();

        if (!selectedItem.equals(rootItem) && selectedItem != null) {
            selectedItem.getParent().getChildren().remove(selectedItem);

            for (Node node : anchorPane.getChildren()) {
                if (node instanceof Rectangle || node instanceof Circle || node instanceof Text) {
                    nodesToRemove.add(node);
                }
            }
            anchorPane.getChildren().removeAll(nodesToRemove);
            traverseTree(rootItem);
        } else {
            treeView.getRoot().getChildren().clear();
            for (Node node : anchorPane.getChildren()) {
                if (node instanceof Rectangle || node instanceof Circle || node instanceof Text) {
                    nodesToRemove.add(node);
                }
            }
            anchorPane.getChildren().removeAll(nodesToRemove);
        }

    }

    public void Changestuff() {
        TreeItem<FarmObject> selectedItem = treeView.getSelectionModel().getSelectedItem();

        if (!selectedItem.equals(rootItem) && selectedItem != null
                && !selectedItem.getValue().getName().equalsIgnoreCase("Command Center")
                && !selectedItem.getValue().getName().equalsIgnoreCase("Drone")) {
            if (!entryFieldName.getText().isBlank()) {
                selectedItem.getValue().setName(entryFieldName.getText());
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

            List<Node> nodesToRemove = new ArrayList<>();

            for (Node node : anchorPane.getChildren()) {
                if (node instanceof Rectangle || node instanceof Circle || node instanceof Text) {
                    nodesToRemove.add(node);
                }
            }
            anchorPane.getChildren().removeAll(nodesToRemove);
            traverseTree(rootItem);
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
    }

    TreeItem<FarmObject> rootItem;

    public void initialize() {

        crudChoiceBox.setItems(FXCollections.observableArrayList("Add Item", "Delete Item", "Add Item Container",
                "Delete Item Container", "Change Value(s)"));
        rootItem = new TreeItem<>(new FarmObject("Root", 0, 0, 0,
                0, 0, 0, "Rectangle"));
        treeView.setRoot(rootItem);
        FarmObject cc = new FarmObject("Command Center", 10000, 400,
                50, 100,
                100, 100, "rectangle");

        FarmObject droneThing = new FarmObject("Drone", 10000, 435,
                100, 100,
                100, 100, "rectangle");

        TreeItem<FarmObject> commandCenter = new TreeItem<FarmObject>(cc);
        TreeItem<FarmObject> drone = new TreeItem<FarmObject>(droneThing);
        rootItem.getChildren().add(commandCenter);
        commandCenter.getChildren().add(drone);

        Rectangle rectangle = new Rectangle(400, 50,
                100, 100);
        anchorPane.getChildren().add(rectangle);
        rectangle.setStroke(Color.BLACK);
        rectangle.setFill(Color.TRANSPARENT);
        Text text = new Text("Command Center");
        text.setLayoutX(400);
        text.setLayoutY(50 + 20);
        anchorPane.getChildren().add(text);

        Rectangle rectangle2 = new Rectangle(435, 100,
                25, 25);
        anchorPane.getChildren().add(rectangle2);
        rectangle2.setStroke(Color.BLACK);
        rectangle2.setFill(Color.TRANSPARENT);
        Text text2 = new Text("Drone");
        text2.setLayoutX(435);
        text2.setLayoutY(100 + 20);
        anchorPane.getChildren().add(text2);

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
     * public Rectangle2D drawRectangle() {
     * TreeItem<FarmObject> selectedItem =
     * treeView.getSelectionModel().getSelectedItem();
     * double x = selectedItem.getValue().getLocationX();
     * double y = selectedItem.getValue().getLocationY();
     * double width = selectedItem.getValue().getWidth();
     * double height = selectedItem.getValue().getHeight();
     *
     * return new Rectangle2D(x, y, width, height);
     * }
     */

    @FXML
    public void drawRect(double x, double y, double width, double height) {
        // Rectangle2D r = new Rectangle2D(x, y, width, height);
        Color clear = new Color(0, 0, 0, 0);
        Rectangle rect = new Rectangle();
        rect.setX(x);
        rect.setY(y);
        rect.setWidth(width);
        rect.setHeight(height);
        rect.setStrokeType(StrokeType.INSIDE);
        rect.setStroke(BLACK);
        rect.setFill(clear);
        anchorPane.getChildren().add(rect);
    }
    /*
     * public void deleteRect(Rectangle rectangle){
     * double ex = rectangle.getX();
     * double why = rectangle.getY();
     *
     * ex += 5000;
     * why += 5000;
     * rectangle.setLayoutX(ex);
     * rectangle.setLayoutY(why);
     *
     * //rectangle.setWidth(0);
     * }
     */

}
