package com.example.navrpi;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import java.util.ArrayList;
import java.util.List;

@Dao
public interface ProfessorDao {

    //Data access object for professor database. Provides wrappers for standard SQL queries.
    //the top line defines the SQL, the bottom line the associated java function

    @Query("SELECT * FROM professor WHERE lastName LIKE :search")
    List<Professor> searchLastName(String search);

    @Insert
    void insert(Professor pro);

    @Query("SELECT * FROM professor")
    List<Professor> getAllProfessors();

    @Query("DELETE from professor")
    void deleteAll();

}

