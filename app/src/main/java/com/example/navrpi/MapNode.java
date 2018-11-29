package com.example.navrpi;


import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

import java.util.HashMap;


@Entity
public class MapNode {

    @PrimaryKey
    @NonNull
    private String id;

    @NonNull
    private String building;

    private int x;
    private int y;
    private int floor;
    private int distance;
    private String nodeType;

    MapNode() {
        x = 0;
        y = 0;
        floor = 0;
        building = "";
    }

    MapNode(String i) {
        x = 0;
        y = 0;
        floor = 0;
        building = "";
        id = i;
    }

    MapNode(int xi, int yi) {
        x = xi;
        y = yi;
        floor = 0;
        building = "";
        distance = Integer.MAX_VALUE;
        id = building + Integer.toString(floor) + Integer.toString(x) + Integer.toString(y);

    }

    MapNode(int xi, int yi, int fl, String bld, String type) {
        x = xi;
        y = yi;
        floor = fl;
        building = bld;
        nodeType = type;
        distance = Integer.MAX_VALUE;
        id = bld + Integer.toString(fl) + Integer.toString(xi) + Integer.toString(yi);

    }

    @Override
    public boolean equals(Object obj) {

        if (this == obj) {
            return true;
        }

        if (obj == null || obj.getClass()!= this.getClass()) {
            return false;
        }

        MapNode n = (MapNode) obj;

        return (this.id.equals(n.getId()));

    }

    public int getX() { return x;}

    public int getY() { return y;}

    public int getFloor() { return floor;}

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public void setFloor(int floor) {
        this.floor = floor;
    }

    public String getBuilding() {
        return building;
    }

    public void setBuilding(String building) {
        this.building = building;
    }

    @NonNull
    public String getId() {
        return id;
    }

    public void setId(@NonNull String id) {
        this.id = id;
    }

    public void setNodeType(String nodeType) {
        this.nodeType = nodeType;
    }

    public String getNodeType() {
        return nodeType;
    }

    public int getDistance() {
        return distance;
    }

    public void setDistance(int dist) {
        this.distance = dist;
    }
}
