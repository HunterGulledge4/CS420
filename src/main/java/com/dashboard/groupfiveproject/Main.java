package com.dashboard.groupfiveproject;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class Main extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("farm-dashboard-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 900, 1100);
        stage.setTitle("Welcome to Your AgriTech Farm Dashboard!");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}