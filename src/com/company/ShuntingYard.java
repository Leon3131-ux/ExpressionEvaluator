package com.company;


import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class ShuntingYard {

    String[][] precedence = {{"+", "-"}, {"*", "/"}, {"^"}, {"M", "P"}};
    String[] rightAssociations = {"^", "M", "P"};

    public List<Token> toRpn(List<Token> tokens){

        List<Token> output = new ArrayList<>();
        Stack<Token> operators = new Stack<>();

        for (Token token : tokens) {
            if (token.getType() == TokenType.NUMBER) {
                output.add(token);
            } else if(token.getType() == TokenType.FUNCTION){
                operators.add(token);
            }else if (token.getType() == TokenType.OPERATOR) {
                while (!operators.isEmpty() && operators.peek().getType() != TokenType.LEFT_PARENTHESES){
                    Token topOfStack = operators.peek();
                    if (precedence(token) < precedence(topOfStack) || precedence(token) == precedence(topOfStack) && !isRightAssociative(topOfStack)) {
                        output.add(operators.pop());
                    }else {
                        break;
                    }
                }
                operators.add(token);
            } else if (token.getType() == TokenType.LEFT_PARENTHESES){
                operators.add(token);
            } else if (token.getType() == TokenType.RIGHT_PARENTHESES){
                while (!operators.isEmpty() && operators.peek().getType() != TokenType.LEFT_PARENTHESES){
                    output.add(operators.pop());
                }
                if(!operators.isEmpty()){
                    operators.pop();
                    if(!operators.isEmpty() && operators.peek().getType() == TokenType.FUNCTION){
                        output.add(operators.pop());
                    }
                }else {
                    throw new IllegalArgumentException("Parentheses Mismatch");
                }
            }
        }
        while (!operators.isEmpty()){
            if(operators.peek().getType() != TokenType.LEFT_PARENTHESES && operators.peek().getType()  != TokenType.RIGHT_PARENTHESES){
                output.add(operators.pop());
            }else {
                throw new IllegalArgumentException("Parentheses Mismatch");
            }
        }
        return output;
    }

    private int precedence(Token token){

        if(token.getType() == TokenType.FUNCTION){
            return 1;
        }
        for(int i = 0; i < precedence.length; i++) {
            for(int y = 0; y < precedence[i].length; y++){
                if(precedence[i][y].equals(token.getName())){
                    return i + 2;
                }
            }
        }
        return 0;
    }

    private boolean isRightAssociative(Token token){
        for (String rightAssociation: rightAssociations){
            if(rightAssociation.equals(token.getName())){
                return true;
            }
        }
        return false;
    }

}
