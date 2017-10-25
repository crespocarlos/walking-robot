package com.packt.nasa.service;

import com.packt.nasa.domain.Point;
import com.packt.nasa.domain.enums.CardinalPoint;
import com.packt.nasa.domain.enums.Direction;
import com.packt.nasa.service.impl.NavigationServiceImpl;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.runners.MockitoJUnitRunner;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

@RunWith(MockitoJUnitRunner.class)
public class NavigationServiceTest {
    @InjectMocks
    NavigationServiceImpl service;

    @Before
    public void before() {
        service.start();
    }

    @Test
    public void shouldReturnOutOfBoundariesBiggerThanNine() {
        service.point.setY(10);
        service.point.setX(10);
        assertFalse(service.isInBoundaries());
    }

    @Test
    public void shouldReturnOutOfBoundariesLessThanOne() {
        service.point.setY(-1);
        service.point.setX(-1);
        assertFalse(service.isInBoundaries());
    }

    @Test
    public void shouldChangeCardinalPointDirectionRight() {
        service.point.setCardinalPoint(CardinalPoint.NORTH);

        service.changeCardinalPoint(Direction.RIGHT);
        assertEquals(CardinalPoint.EAST, service.point.getCardinalPoint());

        service.changeCardinalPoint(Direction.RIGHT);
        assertEquals(CardinalPoint.SOUTH, service.point.getCardinalPoint());

        service.changeCardinalPoint(Direction.RIGHT);
        assertEquals(CardinalPoint.WEST, service.point.getCardinalPoint());

        service.changeCardinalPoint(Direction.RIGHT);
        assertEquals(CardinalPoint.NORTH, service.point.getCardinalPoint());
    }

    @Test
    public void shouldChangeCardinalPointDirectionLeft() {
        service.point.setCardinalPoint(CardinalPoint.NORTH);

        service.changeCardinalPoint(Direction.LEFT);
        assertEquals(CardinalPoint.WEST, service.point.getCardinalPoint());

        service.changeCardinalPoint(Direction.LEFT);
        assertEquals(CardinalPoint.SOUTH, service.point.getCardinalPoint());

        service.changeCardinalPoint(Direction.LEFT);
        assertEquals(CardinalPoint.EAST, service.point.getCardinalPoint());

        service.changeCardinalPoint(Direction.LEFT);
        assertEquals(CardinalPoint.NORTH, service.point.getCardinalPoint());
    }

    @Test
    public void shouldNotChangeCardinalPoint() {
        service.point.setCardinalPoint(CardinalPoint.NORTH);

        service.changeCardinalPoint(Direction.FORWARD);
        assertEquals(CardinalPoint.NORTH, service.point.getCardinalPoint());
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldThrowExceptionOnChangeCardinalPoint() {
        service.changeCardinalPoint(null);
    }

    @Test
    public void shouldChangeLocationNorth() {
        service.point.setCardinalPoint(CardinalPoint.NORTH);
        service.changeLocation();

        Point result = service.finish();
        assertEquals(0, result.getX());
        assertEquals(1, result.getY());
        assertEquals(CardinalPoint.NORTH, result.getCardinalPoint());
    }

    @Test
    public void shouldChangeLocationSouth() {
        service.point.setCardinalPoint(CardinalPoint.SOUTH);
        service.changeLocation();

        Point result = service.finish();
        assertEquals(0, result.getX());
        assertEquals(-1, result.getY());
        assertEquals(CardinalPoint.SOUTH, result.getCardinalPoint());
    }

    @Test
    public void shouldChangeLocationWest() {
        service.point.setCardinalPoint(CardinalPoint.WEST);
        service.changeLocation();

        Point result = service.finish();
        assertEquals(1, result.getX());
        assertEquals(0, result.getY());
        assertEquals(CardinalPoint.WEST, result.getCardinalPoint());
    }

    @Test
    public void shouldChangeLocationEast() {
        service.point.setCardinalPoint(CardinalPoint.EAST);
        service.changeLocation();

        Point result = service.finish();
        assertEquals(-1, result.getX());
        assertEquals(0, result.getY());
        assertEquals(CardinalPoint.EAST, result.getCardinalPoint());
    }

}
