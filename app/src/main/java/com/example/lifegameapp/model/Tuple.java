package com.example.lifegameapp.model;

import java.util.Objects;

public class Tuple {
    int x;
    int y;

    public Tuple(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void setX(int x) {
        this.x = x;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Tuple tuple = (Tuple) o;
        return x == tuple.x && y == tuple.y;
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }

    public void setY(int y) {
        this.y = y;
    }

    @Override
    public String toString() {
        return
                "x=" + x +
                ", y=" + y;
    }
}
