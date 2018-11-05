package com.example.navrpi;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

public class MapNode {

    private int x;
    private int y;
    private int floor;
    private int distance;
    private String building;
    private String nodeType;
    private HashMap<MapNode, Integer> adjacentNodes = new HashMap<>();
    private LinkedList<MapNode> shortestPath = new LinkedList<>();

    MapNode() {
        x = 0;
        y = 0;
        floor = 0;
        building = "";
        nodeType = "";
        distance = Integer.MAX_VALUE;
    }

    MapNode(int xi, int yi) {
        x = xi;
        y = yi;
        floor = 0;
        building = "";
        nodeType = "";
        distance = Integer.MAX_VALUE;
    }

    MapNode(int xi, int yi, int fl, String bld) {
        x = xi;
        y = yi;
        floor = fl;
        building = bld;
        nodeType = "";
        distance = Integer.MAX_VALUE;
    }

    public int getX() { return x;}

    public int getY() { return y;}

    public int getFloor() { return floor;}

    public HashMap<MapNode, Integer> getAdjacentNodes() { return adjacentNodes;}

    public String getBuilding() { return building;}

    public String getNodeType() { return nodeType; }

    public int getNumAdjacent() { return adjacentNodes.size();}

    public void addAdjacentNode(MapNode node, int distance) { adjacentNodes.put(node, distance); }


    public void setNodeType(String nodeType) {
        this.nodeType = nodeType;
    }

    public LinkedList<MapNode> getShortestPath() {
        if (!shortestPath.contains(this)) {
            shortestPath.add(this);
        }

        return shortestPath;
    }

    public void setShortestPath(LinkedList<MapNode> shortestPath) {
        this.shortestPath = shortestPath;
    }

    public Integer getDistance() { return distance; }

    public void setDistance(Integer distance) { this.distance = distance; }
}
