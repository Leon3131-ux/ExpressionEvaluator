package com.company;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.util.Objects;

public class Main extends Application {

    public static void main(String[] args){
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("views/Calculator.fxml")));

        Scene scene = new Scene(root);
        scene.getStylesheets().add("styles/Calculator.css");

        stage.setTitle("Calculator");
        stage.setScene(scene);
        stage.show();
    }

}
