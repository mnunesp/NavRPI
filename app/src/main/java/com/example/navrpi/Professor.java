package com.example.navrpi;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

import java.util.ArrayList;

@Entity
public class Professor {

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

    public static String getRest(ArrayList<Professor> profs) {
        String toReturn = "";
        String first = "";
        String last = "";
        for (int i = 0; i < profs.size(); i++) {
            if ((profs.get(i).getFirstName().equals("Michael")) || (profs.get(i).getFirstName().equals("Alex"))) {
                first = "";
                last = "";
            }
            else{
                first = profs.get(i).getFirstName();
                last = profs.get(i).getLastName();

            }
            toReturn = toReturn + (first + " " + last + "\n");
            System.out.print((first));
        }
        return toReturn;
    }
}


