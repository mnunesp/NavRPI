package com.example.navrpi;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;


import java.util.List;
//import java.util.Set;

@Dao
public interface VerticiesDao {

    @Insert
    void insert(Verticies v);

    @Query("SELECT * FROM verticies")
    List<Verticies> getAllEdges();

    @Query("DELETE FROM verticies")
    void DeleteAll();

    @Query("SELECT * FROM verticies WHERE source = :s")
    List<Verticies> getEdgesBySource(String s);

    @Query("SELECT * FROM verticies WHERE dest = :d")
    List<Verticies> getEdgesByDest(String d);
}
