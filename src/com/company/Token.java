package com.company;

public class Token {

    public Token(String name, TokenType type) {
        this.name = name;
        this.type = type;
    }

    private final String name;

    private final TokenType type;

    public String getName() {
        return name;
    }

    public TokenType getType() {
        return type;
    }
}
