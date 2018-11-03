package com.example.navrpi;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

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



    MapNode() {
        x = 0;
        y = 0;
    }

    MapNode(int xi, int yi) {
        x = xi;
        y = yi;
        floor = 0;
        id = building + Integer.toString(floor) + Integer.toString(x) + Integer.toString(y);
    }

    MapNode(int xi, int yi, int fl, String bld) {
        x = xi;
        y = yi;
        floor = fl;
        building = bld;
        id = bld + Integer.toString(fl) + Integer.toString(xi) + Integer.toString(yi);
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getFloor() {
        return floor;
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


}
