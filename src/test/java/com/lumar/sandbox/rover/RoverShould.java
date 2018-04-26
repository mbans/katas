package com.lumar.sandbox.rover;


import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

//  You are given the initial starting point (x,y) of a rover and the direction (N,S,E,W) it is facing.
//        The rover receives a character array of commands.
//        Implement commands that move the rover forward/backward (f,b).
//        Implement commands that turn the rover left/right (l,r).
//        Implement wrapping from one edge of the grid to another. (planets are spheres after all)
//        Implement obstacle detection before each move to a new square. If a given sequence of commands encounters an obstacle,
//            the rover moves up to the last possible point, aborts the sequence and reports the obstacle.

public class RoverShould {

    private Rover rover;

    @Before
    public void setUp() throws Exception {
        rover = new Rover("N", 0,0);
    }

    @Test
    public void rotateRight() throws Exception {
        rover.execute("r");
        assertThat(rover.getDirection(), is("E"));
    }

    @Test
    public void rotateRightToSouth() throws Exception {
        rover.execute("rr");
        assertThat(rover.getDirection(), is("S"));
    }

    @Test
    public void rotateRightToWest() throws Exception {
        rover.execute("rrr");
        assertThat(rover.getDirection(), is("W"));
    }

    @Test
    public void rotateRightToNorth() throws Exception {
        rover.execute("rrrr");
        assertThat(rover.getDirection(), is("N"));
    }

    @Test
    public void rotateLeftToWest() throws Exception {
        rover.execute("l");
        assertThat(rover.getDirection(), is("W"));
    }

    @Test
    public void rotateLeftToSouth() throws Exception {
        rover.execute("ll");
        assertThat(rover.getDirection(), is("S"));
    }

    @Test
    public void rotateLeftToEast() throws Exception {
        rover.execute("lll");
        assertThat(rover.getDirection(), is("E"));
    }

    @Test
    public void rotateLeftToNorth() throws Exception {
        rover.execute("llll");
        assertThat(rover.getDirection(), is("N"));
    }

    @Test
    public void moveBackward() {
        rover.execute("b");
        assertThat(rover.getX(), is(0));
        assertThat(rover.getY(), is(1));

        rover.execute("bbb");
        assertThat(rover.getX(), is(0));
        assertThat(rover.getY(), is(4));
    }

    @Test
    public void moveForward() {
        Rover rover = new Rover("S",0,0);
        rover.execute("f");
        assertThat(rover.getX(), is(0));
        assertThat(rover.getY(), is(1));
    }

    @Test
    public void shouldWrapGridMovingNorth() {
        //Facing North moving forward
        Rover rover = new Rover("N",0,0);
        rover.execute("f");
        assertThat(rover.getX(), is(0));
        assertThat(rover.getY(), is(9));
    }

    @Test
    public void shouldWrapGridMovingSouth() {
        //Facing North moving forward
        Rover rover = new Rover("S",0,0);
        rover.execute("b");

        assertThat(rover.getX(), is(0));
        assertThat(rover.getY(), is(9));
    }

    @Test
    public void shouldWrapGridMovingEast() {
        //Facing North moving forward
        Rover rover = new Rover("E",9,0);
        rover.execute("f");
        assertThat(rover.getX(), is(0));
        assertThat(rover.getY(), is(0));
    }

    @Test
    public void shouldWrapGridMovingWest() {
        //Facing North moving forward
        Rover rover = new Rover("W",0,0);
        rover.execute("f");
        assertThat(rover.getX(), is(9));
        assertThat(rover.getY(), is(0));
    }
}
