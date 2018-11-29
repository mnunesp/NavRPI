package com.example.navrpi;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

@Entity
public class Classroom {

    @NonNull
    @PrimaryKey
    private String number;

    private String building;

    public String getNumber() { return number; }

    public void setBuilding(String building) {
        this.building = building;
    }

    public String getBuilding() { return building; }

    public void setNumber(String number) {
        this.number = number;
    }
}
