package com.bida.poker;

public enum Suit {
    SPADE(String.valueOf((char) 9828)), HEART(String.valueOf((char) 9825)),  CLUB(String.valueOf((char) 9831)), DIAMOND(String.valueOf((char) 9826));

    private final String name;

    private Suit(String name){
        this.name = name;
    }

    public String getName(){
        return name;
    }

    @Override
    public String toString() {
        return name;
    }
}
