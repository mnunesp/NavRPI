package com.example.navrpi;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

@Entity
public class Professor {

    @NonNull
    @PrimaryKey
    private String firstName = "Dan";

    private String lastName = "Weeks";

    public String getFirstName() {return firstName;}

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {return lastName;}

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
}


