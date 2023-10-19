package com.dashboard.groupfiveproject;

import javafx.animation.TranslateTransition;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import java.net.URL;
import java.util.ResourceBundle;

public class ItemController implements Initializable {
    @FXML
    private Label welcomeText;

    @FXML
    protected void onHelloButtonClick() {
        welcomeText.setText("Welcome to JavaFX Application!");
    }
    
    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        //Translate
        TranslateTransition translate = new TranslateTransition();
        //translate.setNode(myImage);
    
    }
}