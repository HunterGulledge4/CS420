package com.dashboard.groupfiveproject;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import java.net.URL;

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
        translate.setNode(myImage);
    
    }
}