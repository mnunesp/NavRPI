package com.example.navrpi;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Query;

import java.util.List;

@Dao
public interface ClassroomDao {

    @Query("SELECT * FROM classroom WHERE number LIKE :search")
    Classroom searchNumber(int search);

    @Query("SELECT * FROM classroom")
    List<Classroom> getAllRooms();
}
