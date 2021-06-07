package com.company;

import java.util.List;

public class Main {

    public static void main(String[] args) {

        Tokenizer tokenizer = new Tokenizer();
        ShuntingYard yard = new ShuntingYard();
        Evaluator evaluator = new Evaluator();

        List<Token> tokens = tokenizer.getTokens("sqrt(4)--2");

        tokens.forEach(token -> System.out.print(token.getName()));
        System.out.println();
        tokens = yard.toRpn(tokens);
        tokens.forEach(token -> System.out.print(token.getName()));
        System.out.println();
        System.out.println(evaluator.evaluate(tokens));

    }
}
