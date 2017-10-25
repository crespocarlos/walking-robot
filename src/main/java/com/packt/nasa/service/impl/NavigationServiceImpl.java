package com.packt.nasa.service.impl;

import com.packt.nasa.domain.Point;
import com.packt.nasa.domain.enums.CardinalPoint;
import com.packt.nasa.domain.enums.Direction;
import com.packt.nasa.service.NavigationService;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class NavigationServiceImpl implements NavigationService {
    public static final int WIDTH = 9;
    public static final int HEIGHT = 9;
    private static final int START_X = WIDTH / 2;
    private static final int START_Y = HEIGHT / 2;
    public Point point;

    public boolean isInBoundaries() {
        return point.getX() < WIDTH && point.getY() < HEIGHT && point.getX() >= 0 && point.getY() >= 0;
    }

    @Override
    public void start() {
        this.point = new Point(START_X, START_Y, CardinalPoint.NORTH);
    }

    @Override
    public Point getCurrentLocation() {
        int finalX = point.getX() - START_X;
        int finalY = point.getY() - START_Y;

        return new Point(finalX, finalY, this.point.getCardinalPoint());
    }

    @Override
    public void changeLocation() {
        if (!this.isInBoundaries()) {
            throw new UnsupportedOperationException("You've reached the boundaries");
        }

        switch (this.point.getCardinalPoint()) {
            case NORTH:
                point.setY(this.point.getY() + 1);
                break;
            case SOUTH:
                point.setY(this.point.getY() - 1);
                break;
            case EAST:
                point.setX(this.point.getX() - 1);
                break;
            case WEST:
                point.setX(this.point.getX() + 1);
                break;
            default:
                throw new IllegalArgumentException("Invalid coordinates");
        }
    }

    @Override
    public void changeCardinalPoint(Direction directionTo) {
        if (Objects.isNull(directionTo)) {
            throw new IllegalArgumentException("Please inform valid directions");
        }

        switch (directionTo) {
            case LEFT:
                if (this.point.getCardinalPoint() == CardinalPoint.NORTH) {
                    this.point.setCardinalPoint(CardinalPoint.WEST);
                } else if (this.point.getCardinalPoint() == CardinalPoint.SOUTH) {
                    this.point.setCardinalPoint(CardinalPoint.EAST);
                } else if (this.point.getCardinalPoint() == CardinalPoint.WEST) {
                    this.point.setCardinalPoint(CardinalPoint.SOUTH);
                } else if (this.point.getCardinalPoint() == CardinalPoint.EAST) {
                    this.point.setCardinalPoint(CardinalPoint.NORTH);
                }

                break;
            case RIGHT:
                if (this.point.getCardinalPoint() == CardinalPoint.NORTH) {
                    this.point.setCardinalPoint(CardinalPoint.EAST);
                } else if (this.point.getCardinalPoint() == CardinalPoint.SOUTH) {
                    this.point.setCardinalPoint(CardinalPoint.WEST);
                } else if (this.point.getCardinalPoint() == CardinalPoint.WEST) {
                    this.point.setCardinalPoint(CardinalPoint.NORTH);
                } else if (this.point.getCardinalPoint() == CardinalPoint.EAST) {
                    this.point.setCardinalPoint(CardinalPoint.SOUTH);
                }

                break;
            case FORWARD:
            default:
                break;
        }
    }
}
