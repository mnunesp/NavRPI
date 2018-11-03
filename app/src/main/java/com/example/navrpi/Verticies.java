package com.example.navrpi;


import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

@Entity
public class Verticies {

    private MapNode source;
    private MapNode dest;

    @PrimaryKey
    @NonNull
    private static int id = 0;

    public static void setId(@NonNull int id) {
        Verticies.id = id;
    }

    public Verticies(MapNode a, MapNode b) {
        this.dest = a;
        this.source = b;
        this.id = id++;
    }

}
