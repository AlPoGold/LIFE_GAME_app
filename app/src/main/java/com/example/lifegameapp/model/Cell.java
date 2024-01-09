package com.example.lifegameapp.model;

public class Cell {
    private boolean isLive;
    private int x;
    private int y;

    public Cell(boolean isLive, int x, int y) {
        this.isLive = isLive;
        this.x = x;
        this.y = y;
    }

    public boolean isLive() {
        return isLive;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
}
