package com.example.navrpi;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import java.util.List;

@Dao
public interface ProfessorDao {

    @Query("SELECT * FROM professor WHERE lastName LIKE :search")
    Professor searchLastName(String search);

    @Insert
    void insert(Professor pro);

    @Query("SELECT * FROM professor")
    List<Professor> getAllProfessors();

    @Query("DELETE from professor")
    void deleteAll();

}

