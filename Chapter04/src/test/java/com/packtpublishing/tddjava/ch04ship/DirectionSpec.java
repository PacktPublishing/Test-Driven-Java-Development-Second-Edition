package com.packtpublishing.tddjava.ch04ship;

import org.testng.annotations.*;
import static org.testng.Assert.*;

@Test
public class DirectionSpec {

    public void whenGetFromShortNameNThenReturnDirectionN() {
        Direction direction = Direction.getFromShortName('N');
        assertEquals(direction, Direction.NORTH);
    }

    public void whenGetFromShortNameWThenReturnDirectionW() {
        Direction direction = Direction.getFromShortName('W');
        assertEquals(direction, Direction.WEST);
    }

    public void whenGetFromShortNameBThenReturnNone() {
        Direction direction = Direction.getFromShortName('B');
        assertEquals(direction, Direction.NONE);
    }

    public void givenSWhenLeftThenE() {
        assertEquals(Direction.SOUTH.turnLeft(), Direction.EAST);
    }

    public void givenNWhenLeftThenW() {
        assertEquals(Direction.NORTH.turnLeft(), Direction.WEST);
    }

    public void givenSWhenRightThenW() {
        assertEquals(Direction.SOUTH.turnRight(), Direction.WEST);
    }

    public void givenWWhenRightThenN() {
        assertEquals(Direction.WEST.turnRight(), Direction.NORTH);
    }

}
