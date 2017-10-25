package com.packt.nasa.service;

import com.packt.nasa.domain.Point;
import com.packt.nasa.domain.enums.Direction;

public interface NavigationService {
    /**
     * get the current location
     * @return Point with coordinates
     */
    Point finish();

    /**
     * change the current location based on the cardinal point
     */
    void changeLocation();

    /**
     * change the current cardinal point based on the new direction
     * @param directionTo
     */
    void changeCardinalPoint(Direction directionTo);

    /**
     * start navigation
     */
    void start();
}
