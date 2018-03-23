package com.packtpublishing.tddjava.ch04ship;

public class Ship {

    private final Location location;
    public Location getLocation() {
        return location;
    }

    private Planet planet;
    public Planet getPlanet() {
        return planet;
    }

//    public Ship(Location location) {
//        this.location = location;
//    }
    public Ship(Location location, Planet planet) {
        this.location = location;
        this.planet = planet;
    }

    public boolean moveForward() {
//        return location.forward();
        return location.forward(planet.getMax(), planet.getObstacles());
    }

    public boolean moveBackward() {
//        return location.backward();
        return location.backward(planet.getMax(), planet.getObstacles());
    }

    public void turnLeft() {
        location.turnLeft();
    }

    public void turnRight() {
        location.turnRight();
    }

    public String receiveCommands(String commands) {
        StringBuilder output = new StringBuilder();
        for (char command : commands.toCharArray()) {
            boolean status = true;
            switch(command) {
                case 'f':
                    status = moveForward();
                    break;
                case 'b':
                    status = moveBackward();
                    break;
                case 'l':
                    turnLeft();
                    break;
                case 'r':
                    turnRight();
                    break;
            }
            if (status) {
                output.append("O");
            } else {
                output.append("X");
            }
        }
        return output.toString();
    }

}
