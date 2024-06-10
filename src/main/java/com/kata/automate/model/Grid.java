package com.kata.automate.model;


public class Grid {

    private int numRows;
    private int numColumns;



    public Grid(int numRows, int numColumns) {
        this.numRows = numRows;
        this.numColumns = numColumns;
    }



    public int getNumRows() {
        return numRows;
    }

    public int getNumColumns() {
        return numColumns;
    }
}