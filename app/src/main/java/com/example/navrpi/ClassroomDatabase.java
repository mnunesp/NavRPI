package com.example.navrpi;

import android.arch.persistence.db.SupportSQLiteDatabase;
import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

@Database(entities = {Classroom.class}, version = 2)
public abstract class ClassroomDatabase extends RoomDatabase {
    public abstract ClassroomDao classroomDao();

    private static volatile ClassroomDatabase INSTANCE;

    static ClassroomDatabase getDatabase( final Context context) {
        if (INSTANCE == null) {
            synchronized (ClassroomDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(), ClassroomDatabase.class, "classroom_database").fallbackToDestructiveMigration().allowMainThreadQueries().addCallback(sRoomDatabaseCallback).build();
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
                    new ClassroomAsyncPopulate(INSTANCE).execute();
                }
            };
}

