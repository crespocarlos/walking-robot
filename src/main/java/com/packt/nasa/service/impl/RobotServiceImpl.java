package com.packt.nasa.service.impl;

import com.google.common.base.Strings;
import com.packt.nasa.domain.Point;
import com.packt.nasa.domain.enums.Direction;
import com.packt.nasa.service.NavigationService;
import com.packt.nasa.service.RobotService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RobotServiceImpl implements RobotService {

    @Autowired
    public NavigationService navigationService;

    @Override
    public Point walk(String direction) {
        if (Strings.isNullOrEmpty(direction)) {
            throw new IllegalArgumentException("Please inform the directions");
        }

        this.navigationService.start();

        direction.toUpperCase().chars()
                .mapToObj(c -> Direction.getDirection(String.valueOf((char)c)))
                .forEach(this::walk);

        return this.navigationService.finish();
    }


    private void walk(Direction direction) {
        this.navigationService.changeCardinalPoint(direction);

        if (direction != Direction.FORWARD) {
            return;
        }

        this.navigationService.changeLocation();
    }
}
