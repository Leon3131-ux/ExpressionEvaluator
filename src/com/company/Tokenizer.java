package com.company;

import java.util.ArrayList;
import java.util.List;

public class Tokenizer {

    private final String[] functions = {"sqrt"};

    public List<Token> getTokens(String expression){

        List<Token> tokens = new ArrayList<>();

        char[] chars = expression.toCharArray();

        TokenType previous = null;

        for(int i = 0; i < chars.length; i++){
            if(isNumber(chars[i])){
                StringBuilder stringBuilder = new StringBuilder();
                stringBuilder.append(chars[i]);
                int y = i + 1;
                while (y < chars.length && isNumber(chars[y]) || y < chars.length && chars[y] == '.'){
                    stringBuilder.append(chars[y]);
                    y++;
                }
                tokens.add(new Token(stringBuilder.toString(), TokenType.NUMBER));
                previous = TokenType.NUMBER;
                i = y - 1;
            } else if(isOperator(chars[i])){
                if(previous == TokenType.NUMBER || previous == TokenType.RIGHT_PARENTHESES){
                    tokens.add(new Token(String.valueOf(chars[i]), TokenType.OPERATOR));
                    previous = TokenType.OPERATOR;
                }else {
                    if(chars[i] == '-'){
                        tokens.add(new Token(String.valueOf('M'), TokenType.UNARY_OPERATOR));
                    }else {
                        tokens.add(new Token(String.valueOf('P'), TokenType.UNARY_OPERATOR));
                    }
                    previous = TokenType.UNARY_OPERATOR;
                }
            } else if(chars[i] == '('){
                tokens.add(new Token(String.valueOf(chars[i]), TokenType.LEFT_PARENTHESES));
                previous = TokenType.LEFT_PARENTHESES;
            } else if(chars[i] == ')') {
                tokens.add(new Token(String.valueOf(chars[i]), TokenType.RIGHT_PARENTHESES));
                previous = TokenType.RIGHT_PARENTHESES;
            } else if(!extractFunction(i, chars).equals("")){
                String function = extractFunction(i, chars);
                tokens.add(new Token(function, TokenType.FUNCTION));
                i = i + function.length() - 1;
                previous = TokenType.FUNCTION;
            } else {
                throw new IllegalArgumentException("Illegal Token: " + chars[i]);
            }
        }
        return tokens;

    }

    private boolean isNumber(char c){
        try{
            Integer.parseInt(String.valueOf(c));
            return true;
        }catch (Exception e){
            return false;
        }
    }

    private boolean isOperator(char c){
        return c == '+' || c == '-' || c == '*' || c == '/' || c == '^';
    }

    private String extractFunction(int index, char[] chars){
        for (String function : functions) {
            int y = function.length();
            if (new String(chars, index, y).equals(function)) {
                return new String(chars, index, y);
            }
        }
        return "";
    }

}
