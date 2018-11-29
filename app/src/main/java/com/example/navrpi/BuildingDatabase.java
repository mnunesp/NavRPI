package com.example.navrpi;

import android.arch.persistence.db.SupportSQLiteDatabase;
import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

@Database(entities = {Building.class}, version = 1)
public abstract class BuildingDatabase extends RoomDatabase {
    public abstract BuildingDao buildingDao();

    private static volatile BuildingDatabase INSTANCE;

    static BuildingDatabase getDatabase( final Context context) {
        if (INSTANCE == null) {
            synchronized (BuildingDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(), BuildingDatabase.class, "building_database").fallbackToDestructiveMigration().allowMainThreadQueries().addCallback(sRoomDatabaseCallback).build();
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
                    new BuildingAsyncPopulate(INSTANCE).execute();
                }
            };
}

