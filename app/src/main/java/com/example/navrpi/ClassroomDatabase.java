package com.example.navrpi;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

@Database(entities = {Classroom.class}, version = 1)
public abstract class ClassroomDatabase extends RoomDatabase {
    public abstract ClassroomDao classroomDao();
}
