package com.example.navrpi;
import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

@Database(entities = {Professor.class}, version = 1)
public abstract class ProfessorDatabase extends RoomDatabase {
    public abstract ProfessorDao professorDao();
}

