package com.example.navrpi;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import java.util.List;

@Dao
public interface VerticiesDao {

    @Insert
    void insert(Verticies v);

    @Query("DELETE FROM verticies")
    void DeleteAll();

    @Query("SELECT * FROM verticies WHERE 'source' LIKE :s OR 'dest' LIKE :s")
    List<Verticies> getInvolvedEdges(Verticies s);
}
