package com.dashboard.groupfiveproject;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class FarmController {
    @FXML
    private Label welcomeText;

    @FXML
    protected void onHelloButtonClick() {
        welcomeText.setText("Welcome to JavaFX Application!");
    }
}