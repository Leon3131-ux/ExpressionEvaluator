package com.company.controller;

import com.company.evaluator.Evaluator;
import com.company.evaluator.ShuntingYard;
import com.company.evaluator.Token;
import com.company.evaluator.Tokenizer;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

import java.util.List;

public class CalculatorController {

    private final Tokenizer tokenizer = new Tokenizer();
    private final ShuntingYard yard = new ShuntingYard();
    private final Evaluator evaluator = new Evaluator();

    @FXML
    Label outputLabel;

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
}
