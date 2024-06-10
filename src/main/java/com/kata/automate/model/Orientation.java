package com.kata.automate.model;


public enum Orientation {
    NORTH('N'), SOUTH('S'), EAST('E'), WEST('W');

    private final char value;

    Orientation(char value){
        this.value = value;
    }

    public char getValue() {
        return this.value;
    }


    public static Orientation valueOf(char value) {
        if(NORTH.getValue() == value)
            return NORTH;
        else if(SOUTH.getValue() == value)
            return SOUTH;
        else if(EAST.getValue() == value)
            return EAST;
        else if(WEST.getValue() == value)
            return WEST;
        else throw  new IllegalArgumentException("Not a Orientation value!");
    }

    @Override
    public String toString() {
        return String.valueOf(value);
    }
}