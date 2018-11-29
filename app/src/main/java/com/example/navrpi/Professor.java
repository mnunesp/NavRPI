package com.example.navrpi;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

@Entity
public class Professor {

    //simple class to old data about professors, including name and node (room)

    @NonNull
    @PrimaryKey
    private String firstName;

    private String lastName;

    private String node;

    public Professor(String firstName, String lastName, String node) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.node = node;
    }

    public String getFirstName() {return firstName;}

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {return lastName;}

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setNode(String node) {
        this.node = node;
    }

    public String getNode() {
        return node;
    }
}


