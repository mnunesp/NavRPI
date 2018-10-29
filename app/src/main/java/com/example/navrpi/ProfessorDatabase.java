package com.example.navrpi;

import android.arch.persistence.db.SupportSQLiteDatabase;
import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

@Database(entities = {Professor.class}, version = 1)
public abstract class ProfessorDatabase extends RoomDatabase {

    public abstract ProfessorDao professorDao();

    private static volatile ProfessorDatabase INSTANCE;

    static ProfessorDatabase getDatabase( final Context context) {
        if (INSTANCE == null) {
            synchronized (ProfessorDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(), ProfessorDatabase.class, "professor_database").build();
                }
            }
        }
        return INSTANCE;
    }

    private static RoomDatabase.Callback sRoomDatabaseCallback =
            new RoomDatabase.Callback(){

                @Override
                public void onOpen (SupportSQLiteDatabase db){
                    super.onOpen(db);
                    new ProfessorAsyncPopulate(INSTANCE).execute();
                }
            };
}
