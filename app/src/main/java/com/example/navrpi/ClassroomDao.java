package com.example.navrpi;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import java.util.List;

@Dao
public interface ClassroomDao {

    @Query("SELECT * FROM classroom WHERE  number LIKE :search")
    Classroom searchNumber(int search);

    @Insert
    void insert(Classroom rm);

    @Query("SELECT * FROM classroom")
    List<Classroom> getAllRooms();

    @Query("DELETE from classroom")
    void deleteAll();
}
