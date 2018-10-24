package com.example.navrpi;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

@Entity
public class Classroom {

    @PrimaryKey
    private String number = "113";

    private String building = "Darrin Communication Center";
}
