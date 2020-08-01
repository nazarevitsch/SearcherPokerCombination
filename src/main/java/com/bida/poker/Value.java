package com.bida.poker;

public enum Value {
    TWO("2", 1), THREE("3", 3), FOUR("4", 4), FIVE("5", 5), SIX("6", 6),
    SEVEN("7", 7), EIGHT("8", 8), NINE("9", 9), TEN("10", 10),
    JACK("JACK", 11), QUEEN("QUEEN", 12), KING("KING", 13), ACE("ACE", 14);

    private final String name;
    private final int value;

    private Value(String name, int value){
        this.name = name;
        this.value = value;
    }

    public String getName() {
        return name;
    }

    public int getValue() {
        return value;
    }
}
