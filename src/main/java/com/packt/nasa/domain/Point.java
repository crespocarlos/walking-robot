package com.packt.nasa.domain;

import com.packt.nasa.domain.enums.CardinalPoint;

public class Point {
    private int x;
    private int y;
    private CardinalPoint cardinalPoint = CardinalPoint.NORTH;

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public CardinalPoint getCardinalPoint() {
        return this.cardinalPoint;
    }

    public void setCardinalPoint(CardinalPoint cardinalPoint) {
        this.cardinalPoint = cardinalPoint;
    }

    public Point(int x, int y, CardinalPoint cardinalPoint) {
        this.x = x;
        this.y = y;
        this.cardinalPoint = cardinalPoint;
    }
}
