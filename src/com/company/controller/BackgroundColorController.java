package com.company.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.control.ColorPicker;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;

import java.net.URL;
import java.util.ResourceBundle;

public class BackgroundColorController implements Initializable {

    private GridPane gridPane = null;
    private Color currentColor = null;

    public BackgroundColorController(GridPane gridPane, Color currentColor){
        this.gridPane = gridPane;
        this.currentColor = currentColor;
    }

    public BackgroundColorController(){
    }

    @FXML
    ColorPicker colorPicker;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        colorPicker.setValue(currentColor);
    }


    public void handleColorChange(ActionEvent e){
        currentColor = ((ColorPicker)e.getSource()).getValue();
        colorPicker.setValue(currentColor);
        gridPane.setBackground(new Background(new BackgroundFill(currentColor, CornerRadii.EMPTY, Insets.EMPTY)));
    }

}
