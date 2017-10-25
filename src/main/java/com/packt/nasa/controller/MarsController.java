package com.packt.nasa.controller;

import com.packt.nasa.domain.Point;
import com.packt.nasa.service.RobotService;
import groovy.util.logging.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/rest")
@RestController
@Validated
@Slf4j
public class MarsController {
    @Autowired
    public RobotService robotService;

    @RequestMapping(method = RequestMethod.GET, path = "/mars/{coordinates}")
    public Point getByCoordinates(@PathVariable("coordinates") final String coordinates) {
        return this.robotService.walk(coordinates);
    }
}
