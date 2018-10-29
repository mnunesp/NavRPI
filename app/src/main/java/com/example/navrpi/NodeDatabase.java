package com.example.navrpi;

import android.arch.persistence.db.SupportSQLiteDatabase;
import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

@Database(entities = {MapNode.class}, version = 1)
public abstract class NodeDatabase extends RoomDatabase {
    public abstract NodeDao nodeDao();

    private static volatile NodeDatabase INSTANCE;

    static NodeDatabase getDatabase( final Context context) {
        if (INSTANCE == null) {
            synchronized (NodeDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(), NodeDatabase.class, "node_database").build();
                }
            }
        }
        return INSTANCE;
    }

    private static NodeDatabase.Callback sRoomDatabaseCallback =
            new RoomDatabase.Callback(){

                @Override
                public void onOpen (SupportSQLiteDatabase db){
                    super.onOpen(db);
                    new NodeAsyncPopulate(INSTANCE).execute();
                }
            };
}

