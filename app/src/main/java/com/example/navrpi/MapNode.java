package com.example.navrpi;

public class MapNode {

    private int x;
    private int y;
    private int floor;
    private String building;

    MapNode() {
        x = 0;
        y = 0;
    }

    MapNode(int xi, int yi) {
        x = xi;
        y = yi;
        floor = 0;
        building = "";
    }

    MapNode(int xi, int yi, int fl, String bld) {
        x = xi;
        y = yi;
        floor = fl;
        building = bld;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getFloor() {
        return floor;
    }

    public String getBuilding() {
        return building;
    }
}
