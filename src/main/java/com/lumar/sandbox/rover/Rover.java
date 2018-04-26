package com.lumar.sandbox.rover;

import java.util.AbstractMap.SimpleEntry;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import static com.lumar.sandbox.rover.Rover.HorizontalMovement.*;

public class Rover {

    private String direction = "N";
    private int x;
    private int y;

    private static final int gridSize=10;

    public Rover(final String direction, int x, int y) {
        this.direction = direction;
        this.x = x;
        this.y = y;
    }

    public void execute(String commands) {
        for(char command : commands.toCharArray()) {
            if(command == 'r') {
               direction = MOVE_RIGHT.move(this.direction);
            }
            else if (command == 'l') {
                direction = MOVE_LEFT.move(this.direction);
            }
            else if(command == 'b') {
                SimpleEntry<Integer, Integer> newLocation = moveBack(direction, x, y);
                x = newLocation.getKey();
                y = newLocation.getValue();
            }
            else if (command == 'f') {
                SimpleEntry<Integer, Integer> newLocation = moveForward(direction, x, y);
                x = newLocation.getKey();
                y = newLocation.getValue();
            }
        }
    }

    public String getDirection() {
        return direction;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public enum HorizontalMovement {
        MOVE_RIGHT(new SimpleEntry("N","E"), new SimpleEntry("E","S"), new SimpleEntry("S","W"), new SimpleEntry("W","N")),
        MOVE_LEFT(new SimpleEntry("N","W"), new SimpleEntry("W","S"), new SimpleEntry("S","E"), new SimpleEntry("E","N"));
        private Map<String,String> transitions = new HashMap<String, String>();

        HorizontalMovement(Entry... entries) {
            for(Entry entry : entries) {
                transitions.put((String)entry.getKey(), (String)entry.getValue());
            }
        }

        public String move(final String currentDirection) {
            return transitions.get(currentDirection);
        }
    }

    public SimpleEntry<Integer,Integer> moveBack(final String direction, Integer x, Integer y) {
        if ("N".equals(direction)) {
            y++;
            y = (y >= gridSize - 1) ? 0 : y;
        } else if ("E".equals(direction)) {
            x--;
            x = (x < 0) ? gridSize - 1 : x;
        } else if ("S".equals(direction)) {
            y--;
            y = (y < 0) ? gridSize - 1 : y;
        } else if ("W".equals(direction)) {
            x++;
            x = (x >= gridSize - 1) ? 0 : x;
        }
        return new SimpleEntry(x,y);
    }

    public SimpleEntry<Integer,Integer> moveForward(String direction, Integer x, Integer y) {
        if("N".equals(direction)) {
            y--;
            y = (y<0) ? gridSize -1 :y;
        }
        else if("E".equals(direction)) {
            x++;
            x = (x>=gridSize-1) ? 0 :x;
        }
        else if("S".equals(direction)) {
            y++;
            y = (y>=gridSize-1) ? 0 :y;

        }
        else if("W".equals(direction)) {
            x--;
            x = (x<0) ? gridSize -1 :x;
        }
        return new SimpleEntry(x,y);
    }

}
