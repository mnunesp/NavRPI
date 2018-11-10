package com.example.navrpi;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

import com.google.android.gms.maps.model.LatLng;

import java.util.ArrayList;

@Entity
public class Building {

    @NonNull
    @PrimaryKey
    private String name;

    private double x1;
    private double x2;
    private double x3;
    private double x4;
    private double y1;
    private double y2;
    private double y3;
    private double y4;

    public Building() {};

    public Building(String name, double[] coordinates) {
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

    public double[] getCoordinates() {
        double[] c = new double[8];
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

    public double getX1() {
        return x1;
    }

    public double getX2() {
        return x2;
    }

    public double getX3() {
        return x3;
    }

    public double getX4() {
        return x4;
    }

    public double getY1() {
        return y1;
    }

    public double getY2() {
        return y2;
    }

    public double getY3() {
        return y3;
    }

    public double getY4() {
        return y4;
    }

    public void setX1(double x1) {
        this.x1 = x1;
    }

    public void setX2(double x2) {
        this.x2 = x2;
    }

    public void setX3(double x3) {
        this.x3 = x3;
    }

    public void setX4(double x4) {
        this.x4 = x4;
    }

    public void setY1(double y1) {
        this.y1 = y1;
    }

    public void setY2(double y2) {
        this.y2 = y2;
    }

    public void setY3(double y3) {
        this.y3 = y3;
    }

    public void setY4(double y4) {
        this.y4 = y4;
    }

    public LatLng coordinate1() {
        return new LatLng(x1,y1);
    }

    public LatLng coordinate2() {
        return new LatLng(x2,y2);
    }

    public LatLng coordinate3() {
        return new LatLng(x3,y3);
    }

    public LatLng coordinate4() {
        return new LatLng(x4,y4);
    }
}
