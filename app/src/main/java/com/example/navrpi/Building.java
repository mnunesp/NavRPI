package com.example.navrpi;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

import com.google.android.gms.maps.model.LatLng;

import java.util.ArrayList;

@Entity
public class Building {

    //simple class to hold buildings, that is their name and their location for the google maps marker

    @NonNull
    @PrimaryKey
    private String name;


    private double lat;
    private double lng;

    public Building() {};

    public Building(String name, double lat, double lng) {
        this.name = name;
        this.lat = lat;
        this.lng = lng;

    }

    public String getName() { return name; }

    public void setName(String n) {
        this.name = n;
    }

    public LatLng coordinate1() {
        return new LatLng(lat,lng);
    }

    public double getLat() {
        return lat;
    }

    public double getLng() {
        return lng;
    }

    public void setLat(double lat) {
        this.lat = lat;
    }

    public void setLng(double lng) {
        this.lng = lng;

    }

    public static String getBuildings(ArrayList<Building> buils){
        String result = "";
        for (int i = 0; i < buils.size(); i++){
            if (buils.get(i).getName().equals("Walker")){
                //nothing
            }
            else {
                result = result + buils.get(i).getName() + "\n";
            }
        }
        return result;
    }
}
