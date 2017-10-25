package com.packt.nasa.domain.enums;

public enum Direction {
    FORWARD("M"),
    LEFT("L"),
    RIGHT("R");

    private String value;

    Direction(String value) {
        this.value = value;
    }

    public static Direction getDirection(final String direction) {
        switch (direction) {
            case "M":
                return FORWARD;
            case "L":
                return LEFT;
            case "R":
                return RIGHT;
            default:
                return null;
        }
    }
}
