package com.kata.automate.model;

import java.util.Objects;

public class Automate {

    private Coordinate coordinate;
    private Orientation orientation;
    private Grid grid;

    public Automate(Coordinate coordinate, Orientation orientation,Grid grid) {
        Objects.requireNonNull(coordinate, "Automate must have a coordinate!");
        Objects.requireNonNull(orientation, "Automate must have a orientation!");
        Objects.requireNonNull(grid, "Automate must have a grid!");
        this.coordinate = coordinate;
        this.orientation = orientation;
        this.grid =grid;
    }

    public void setCoordinate(Coordinate coordinate) {
        this.coordinate = coordinate;
    }

    public void setOrientation(Orientation orientation) {
        this.orientation = orientation;
    }

    public void setGrid(Grid grid) {
        this.grid = grid;
    }

    public Coordinate getCoordinate() {
        return coordinate;
    }

    public Orientation getOrientation() {
        return orientation;
    }

    public Grid getGrid() {
        return grid;
    }
}
