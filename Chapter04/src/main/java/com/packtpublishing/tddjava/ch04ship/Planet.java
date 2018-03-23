package com.packtpublishing.tddjava.ch04ship;

import java.util.List;

public class Planet {

    private final Point max;
    public Point getMax() {
        return max;
    }

    private List<Point> obstacles;
    public List<Point> getObstacles() {
        return obstacles;
    }
    public void setObstacles(List<Point> obstacles) {
        this.obstacles = obstacles;
    }

    public Planet(Point max) {
        this.max = max;
    }
    public Planet(Point max, List<Point> obstacles) {
        this.max = max;
        this.obstacles = obstacles;
    }

}
