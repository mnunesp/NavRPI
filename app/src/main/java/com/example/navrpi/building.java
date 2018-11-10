package com.example.navrpi;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

import java.util.ArrayList;

@Entity
public class Building {

    @NonNull
    @PrimaryKey
    private String name;

    private float x1;
    private float x2;
    private float x3;
    private float x4;
    private float y1;
    private float y2;
    private float y3;
    private float y4;

    public Building() {};

    public Building(String name, float[] coordinates) {
        this.name = name;
        this.x1 = coordinates[0];
        this.x2 = coordinates[1];
        this.x3 = coordinates[2];
        this.x4 = coordinates[3];
        this.y1 = coordinates[4];
        this.y2 = coordinates[5];
        this.y3 = coordinates[6];
        this.y4 = coordinates[7];
    }

    public String getName() { return name; }

    public void setName(String n) {
        this.name = n;
    }

    public float[] getCoordinates() {
        float[] c = new float[8];
        c[0] = this.x1;
        c[1] = this.x2;
        c[2] = this.x3;
        c[3] = this.x4;
        c[4] = this.y1;
        c[5] = this.y2;
        c[6] = this.y3;
        c[7] = this.y4;
        return c;
    }

    public float getX1() {
        return x1;
    }

    public float getX2() {
        return x2;
    }

    public float getX3() {
        return x3;
    }

    public float getX4() {
        return x4;
    }

    public float getY1() {
        return y1;
    }

    public float getY2() {
        return y2;
    }

    public float getY3() {
        return y3;
    }

    public float getY4() {
        return y4;
    }

    public void setX1(float x1) {
        this.x1 = x1;
    }

    public void setX2(float x2) {
        this.x2 = x2;
    }

    public void setX3(float x3) {
        this.x3 = x3;
    }

    public void setX4(float x4) {
        this.x4 = x4;
    }

    public void setY1(float y1) {
        this.y1 = y1;
    }

    public void setY2(float y2) {
        this.y2 = y2;
    }

    public void setY3(float y3) {
        this.y3 = y3;
    }

    public void setY4(float y4) {
        this.y4 = y4;
    }
}
