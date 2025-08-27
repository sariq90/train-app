package com.example.trainapp.domain.model;

public enum WagonType {
    ZERO('0'),
    A('A'),
    B('B');

    public final char code;

    WagonType(char code) { this.code = code; }

    public static WagonType fromChar(char c) {
        return switch (Character.toUpperCase(c)) {
            case '0' -> ZERO;
            case 'A' -> A;
            case 'B' -> B;
            default -> throw new IllegalArgumentException("Unknown wagon type: " + c);
        };
    }

}