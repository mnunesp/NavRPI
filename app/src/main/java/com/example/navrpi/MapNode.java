package com.example.navrpi;


import java.util.ArrayList;
import java.util.Map;

public class MapNode {

    private int x;
    private int y;
    private int floor;
    private String building;
    private String nodeType;
    private ArrayList<MapNode> adjacentNodes = new ArrayList<>();

    MapNode() {
        x = 0;
        y = 0;
        floor = 0;
        building = "";
        nodeType = "";
    }

    MapNode(int xi, int yi) {
        x = xi;
        y = yi;
        floor = 0;
        building = "";
        nodeType = "";
    }

    MapNode(int xi, int yi, int fl, String bld) {
        x = xi;
        y = yi;
        floor = fl;
        building = bld;
        nodeType = "";
    }

    public int getX() { return x;}

    public int getY() { return y;}

    public int getFloor() { return floor;}

    public ArrayList<MapNode> getAdjacentNodes() { return adjacentNodes;}

    public String getBuilding() { return building;}

    public String getNodeType() { return nodeType; }

    public int getNumAdjacent() { return adjacentNodes.size();}

    public void addAdjacentNode(MapNode node) { adjacentNodes.add(node); }


    public void setNodeType(String nodeType) {
        this.nodeType = nodeType;
    }
}
