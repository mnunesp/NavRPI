package com.example.navrpi;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

@Entity
public class Classroom {
    @NonNull
    @PrimaryKey
    private String number = "113";

    private String building = "Darrin Communication Center";
}
