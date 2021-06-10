package com.company.controller;

import com.company.evaluator.Evaluator;
import com.company.evaluator.ShuntingYard;
import com.company.evaluator.Token;
import com.company.evaluator.Tokenizer;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.List;
import java.util.Objects;

public class CalculatorController {

    @FXML
    Label outputLabel;

    @FXML
    GridPane gridPane;

    private final Tokenizer tokenizer = new Tokenizer();
    private final ShuntingYard yard = new ShuntingYard();
    private final Evaluator evaluator = new Evaluator();

    String outputString = "";

    public void handleButtonClick(ActionEvent e){
        outputString = outputString.concat(((Button)e.getSource()).getText());
        outputLabel.setText(outputString);
    }

    public void calculate(){
        try{
            List<Token> tokens = tokenizer.getTokens(outputString);
            tokens = yard.toRpn(tokens);
            Double result = evaluator.evaluate(tokens);

            outputString = Double.toString(result);
            outputLabel.setText(outputString);
        }catch (Exception e){
            outputLabel.setText("Syntax Error");
        }
    }

    public void clear(){
        outputString = "";
        outputLabel.setText(outputString);
    }

    public void openBackgroundColorWindow() throws IOException {
        try {
            Stage stage = new Stage();
            FXMLLoader loader = new FXMLLoader(Objects.requireNonNull(getClass().getResource("../views/BackgroundColor.fxml")));
            loader.setController(new BackgroundColorController(gridPane, (Color)gridPane.getBackground().getFills().get(0).getFill()));
            Pane root = loader.load();

            Scene scene = new Scene(root, 200, 200);
            scene.getStylesheets().add("../styles/BackgroundColor.css");

            stage.setTitle("Background Color");
            stage.setScene(scene);
            stage.show();
        }catch (Exception e){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setContentText("Couldn't open properties");
            alert.showAndWait();
            throw e;
        }
    }

}
