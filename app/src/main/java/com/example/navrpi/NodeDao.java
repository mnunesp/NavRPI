package com.example.navrpi;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import java.util.ArrayList;
import java.util.List;

@Dao
public interface NodeDao {

    @Query("SELECT * FROM mapNode WHERE 'floor' = :flor AND 'building' LIKE :build")
    List<MapNode> searchBuildFloor(String build, int flor);

    @Insert
    void insert(MapNode nod);
}
