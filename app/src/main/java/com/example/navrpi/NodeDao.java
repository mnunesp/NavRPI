package com.example.navrpi;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import java.util.ArrayList;
import java.util.List;

@Dao
public interface NodeDao {

    //Data access object for node database. Provides wrappers for standard SQL queries.
    //the top line defines the SQL, the bottom line the associated java function

    @Query("SELECT * FROM mapNode WHERE building LIKE :build")
    List<MapNode> searchBuildFloor(String build);

    @Insert
    void insert(MapNode nod);

    @Query("SELECT * FROM mapNode")
    List<MapNode> getAllNodes();

    @Query("DELETE FROM mapNode")
    void DeleteAll();

    @Query("SELECT * FROM mapNode WHERE id LIKE :i")
    List<MapNode> searchID(String i);

    @Query("SELECT * FROM mapNode WHERE nodeType LIKE :t")
    List<MapNode> searchType(String t);
}
