package com.example.navrpi;

import android.os.AsyncTask;

public class NodeAsyncPopulate extends AsyncTask<Void, Void, Void> {

    private final NodeDao nDao;

    NodeAsyncPopulate(NodeDatabase db) {
        nDao = db.nodeDao();
    }

    @Override
    protected Void doInBackground(final Void... params) {
        nDao.DeleteAll(); ////clear anything that's somehow already there (Room can crash if we try inserting the same thing twice)
        MapNode startNodes[] = new MapNode[]{
                new MapNode(450,200, 3, "Walker", "hallway"),
                new MapNode(450,550, 3, "Walker", "hallway"),
                new MapNode(950,550, 3, "Walker", "hallway"),
                new MapNode(950,650, 3, "Walker", "hallway"),
                new MapNode(1000,650, 3, "Walker", "hallway"),
                new MapNode(350,650, 3, "Walker", "bathroom"),


                new MapNode(450,200, 4, "Walker", "hallway"),
                new MapNode(450,550, 4, "Walker", "hallway"),
                new MapNode(950,550, 4, "Walker", "hallway"),
                new MapNode(950,650, 4, "Walker", "hallway"),
                new MapNode(1000,650, 4, "Walker", "hallway"),

        };
        for (MapNode m : startNodes)
        {
            nDao.insert(m);
        }

        return null;
    }
}
