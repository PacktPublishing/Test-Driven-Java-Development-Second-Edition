package com.packtpublishing.tddjava.ch04ship;

import org.testng.annotations.*;

import java.util.ArrayList;
import java.util.List;

import static org.testng.Assert.*;

@Test
public class LocationSpec {

    private final int x = 12;
    private final int y = 32;
    private final Direction direction = Direction.NORTH;
    private Point max;
    private Location location;
    private List<Point> obstacles;

    @BeforeMethod
    public void beforeTest() {
        max = new Point(50, 50);
        location = new Location(new Point(x, y), direction);
        obstacles = new ArrayList<Point>();
    }

    public void whenInstantiatedThenXIsStored() {
        assertEquals(location.getX(), x);
    }

    public void whenInstantiatedThenYIsStored() {
        assertEquals(location.getY(), y);
    }

    public void whenInstantiatedThenDirectionIsStored() {
        assertEquals(location.getDirection(), direction);
    }

    public void givenDirectionNWhenForwardThenYDecreases() {
        location.forward(max, obstacles);
        assertEquals(location.getY(), y - 1);
    }

    public void givenDirectionSWhenForwardThenYIncreases() {
        location.setDirection(Direction.SOUTH);
        location.forward(max, obstacles);
        assertEquals(location.getY(), y + 1);
    }

    public void givenDirectionEWhenForwardThenXIncreases() {
        location.setDirection(Direction.EAST);
        location.forward(max, obstacles);
        assertEquals(location.getX(), x + 1);
    }

    public void givenDirectionWWhenForwardThenXDecreases() {
        location.setDirection(Direction.WEST);
        location.forward(max, obstacles);
        assertEquals(location.getX(), x - 1);
    }

    public void givenDirectionNWhenBackwardThenYIncreases() {
        location.setDirection(Direction.NORTH);
        location.backward(max, obstacles);
        assertEquals(location.getY(), y + 1);
    }

    public void givenDirectionSWhenBackwardThenYDecreases() {
        location.setDirection(Direction.SOUTH);
        location.backward(max, obstacles);
        assertEquals(location.getY(), y - 1);
    }

    public void givenDirectionEWhenBackwardThenXDecreases() {
        location.setDirection(Direction.EAST);
        location.backward(max, obstacles);
        assertEquals(location.getX(), x - 1);
    }

    public void givenDirectionWWhenBackwardThenXIncreases() {
        location.setDirection(Direction.WEST);
        location.backward(max, obstacles);
        assertEquals(location.getX(), x + 1);
    }

    public void whenTurnLeftThenDirectionIsSet() {
        location.turnLeft();
        assertEquals(location.getDirection(), Direction.WEST);
    }

    public void whenTurnRightThenDirectionIsSet() {
        location.turnRight();
        assertEquals(location.getDirection(), Direction.EAST);
    }

    public void givenSameObjectsWhenEqualsThenTrue() {
        assertTrue(location.equals(location));
    }

    public void givenDifferentObjectWhenEqualsThenFalse() {
        assertFalse(location.equals("bla"));
    }

    public void givenDifferentXWhenEqualsThenFalse() {
        Location locationCopy = new Location(new Point(999, location.getY()), location.getDirection());
        assertFalse(location.equals(locationCopy));
    }

    public void givenDifferentYWhenEqualsThenFalse() {
        Location locationCopy = new Location(new Point(location.getX(), 999), location.getDirection());
        assertFalse(location.equals(locationCopy));
    }

    public void givenDifferentDirectionWhenEqualsThenFalse() {
        Location locationCopy = new Location(location.getPoint(), Direction.SOUTH);
        assertFalse(location.equals(locationCopy));
    }

    public void givenSameXYDirectionWhenEqualsThenTrue() {
        Location locationCopy = new Location(location.getPoint(), location.getDirection());
        assertTrue(location.equals(locationCopy));
    }

    public void whenCopyThenDifferentObject() {
        Location copy = location.copy();
        assertNotSame(location, copy);
    }

    public void whenCopyThenEquals() {
        Location copy = location.copy();
        assertEquals(copy, location);
    }

    public void givenDirectionEAndXEqualsMaxXWhenForwardThen1() {
        location.setDirection(Direction.EAST);
        location.getPoint().setX(max.getX());
        location.forward(max, obstacles);
        assertEquals(location.getX(), 1);
    }

    public void givenDirectionWAndXEquals1WhenForwardThenMaxX() {
        location.setDirection(Direction.WEST);
        location.getPoint().setX(1);
        location.forward(max, obstacles);
        assertEquals(location.getX(), max.getX());
    }

    public void givenDirectionNAndYEquals1WhenForwardThenMaxY() {
        location.setDirection(Direction.NORTH);
        location.getPoint().setY(1);
        location.forward(max, obstacles);
        assertEquals(location.getY(), max.getY());
    }

    public void givenDirectionSAndYEqualsMaxYWhenForwardThen1() {
        location.setDirection(Direction.SOUTH);
        location.getPoint().setY(max.getY());
        location.forward(max, obstacles);
        assertEquals(location.getY(), 1);
    }

    public void givenObstacleWhenForwardThenReturnFalse() {
        location.setDirection(Direction.EAST);
        obstacles.add(new Point(x + 1, y));
        assertFalse(location.forward(max, obstacles));
    }

    public void givenObstacleWhenBackwardThenReturnFalse() {
        location.setDirection(Direction.EAST);
        obstacles.add(new Point(x - 1, y));
        assertFalse(location.backward(max, obstacles));
    }

}
