package com.example.navrpi;

import android.arch.persistence.db.SupportSQLiteDatabase;
import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

@Database(entities = {Verticies.class}, version = 1)
public abstract class VerticiesDatabase extends RoomDatabase {
    public abstract VerticiesDao VerticiesDao();

    private static volatile VerticiesDatabase INSTANCE;

    static VerticiesDatabase getDatabase( final Context context) {
        if (INSTANCE == null) {
            synchronized (VerticiesDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(), VerticiesDatabase.class, "verticies_database").fallbackToDestructiveMigration().allowMainThreadQueries().addCallback(sRoomDatabaseCallback).build();
                }
            }
        }
        return INSTANCE;
    }

    private static VerticiesDatabase.Callback sRoomDatabaseCallback =
            new VerticiesDatabase.Callback(){

                @Override
                public void onOpen (SupportSQLiteDatabase db){
                    super.onOpen(db);
                    new VerticiesAsyncPopulate(INSTANCE).execute();
                }
            };
}