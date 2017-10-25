package com.packt.nasa.domain.enums;

public enum CardinalPoint {
    NORTH("N"),
    WEST("W"),
    SOUTH("S"),
    EAST("E");


    private String value;

    CardinalPoint(String value) {
        this.value = value;
    }

    public static CardinalPoint getDirection(final String cardinalPoint) {
        switch (cardinalPoint) {
            case "N":
                return NORTH;
            case "W":
                return WEST;
            case "S":
                return SOUTH;
            case "E":
                return EAST;
            default:
                return null;
        }
    }

}
