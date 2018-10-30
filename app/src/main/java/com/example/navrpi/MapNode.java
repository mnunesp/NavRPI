package com.example.navrpi;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

@Entity
public class MapNode {

    private int x;
    private int y;
    private int floor;
    private String building;

    @PrimaryKey(autoGenerate = true)
    @NonNull
    private int id;

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
    public int getId() {
        return id;
    }

    public void setId(@NonNull int id) {
        this.id = id;
    }
}
