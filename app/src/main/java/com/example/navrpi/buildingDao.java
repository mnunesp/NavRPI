package com.example.navrpi;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import java.util.ArrayList;
import java.util.List;

@Dao
public interface BuildingDao {

    @Query("SELECT * FROM building WHERE name LIKE :build")
    List<Building> searchBuild(String build);

    @Insert
    void insert(Building b);

    @Query("SELECT * FROM building")
    List<Building> getAllBuildings();

    @Query("DELETE FROM building")
    void deleteAll();
}
