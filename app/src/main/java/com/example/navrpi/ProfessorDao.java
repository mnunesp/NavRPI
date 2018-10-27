package com.example.navrpi;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Query;

import java.util.List;

@Dao
public interface ProfessorDao {

    @Query("SELECT * FROM professor WHERE lastName LIKE :search")
    Professor searchLastName(String search);

    @Query("SELECT * FROM professor")
    List<Professor> getAllProfessors();

}

