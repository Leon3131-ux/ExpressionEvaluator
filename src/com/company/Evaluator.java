package com.company;

import java.util.List;
import java.util.Stack;

public class Evaluator {

    public Double evaluate(List<Token> tokens){

        Stack<Double> values = new Stack<>();

        for (Token token: tokens){
            if(token.getType() == TokenType.NUMBER){
                values.add(Double.parseDouble(token.getName()));
            }else if(token.getType() == TokenType.OPERATOR){
                if(token.getName().equals("M") || token.getName().equals("P")){
                    values.add(evaluateUnary(values.pop(), token.getName()));
                }else {
                    values.add(evaluateOperator(values.pop(), values.pop(), token.getName()));
                }
            }
        }

        return values.pop();
    }

    private Double evaluateOperator(Double n1, Double n2, String operator){
        return switch (operator) {
            case "+" -> n2 + n1;
            case "-" -> n2 - n1;
            case "*" -> n2 * n1;
            case "/" -> n2 / n1;
            default -> 0D;
        };
    }

    private Double evaluateUnary(Double v1, String operator){
        return switch (operator){
            case "M" -> v1 * -1D;
            case "P" -> v1 * 1D;
            default -> throw new IllegalStateException("Unexpected value: " + operator);
        };
    }

}
