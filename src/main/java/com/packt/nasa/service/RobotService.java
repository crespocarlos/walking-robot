package com.packt.nasa.service;

import com.packt.nasa.domain.Point;

public interface RobotService {
    /**
     * Makes the robot walk
     * @param direction
     * @return the point where the robot will be after finishing its journey
     */
    Point walk(String direction);
}
