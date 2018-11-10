package com.example.navrpi;

import android.os.AsyncTask;

public class NodeAsyncPopulate extends AsyncTask<Void, Void, Void> {

    private final NodeDao nDao;

    NodeAsyncPopulate(NodeDatabase db) {
        nDao = db.nodeDao();
    }

    @Override
    protected Void doInBackground(final Void... params) {
        nDao.DeleteAll();
        MapNode startNodes[] = new MapNode[]{/*new MapNode(525, 250, 3, "Walker"), new MapNode(525, 625, 3, "Walker"),
                new MapNode(1025, 625, 3, "Walker"), new MapNode(665, 350, 2, "Walker"),
                new MapNode(665, 650, 2, "Walker"), new MapNode(1100, 650, 2, "Walker")*/ new MapNode(450,200, 3, "Walker", "hallway"), new MapNode(450,550, 3, "Walker", "hallway"),
                new MapNode(950,550, 3, "Walker", "hallway"), new MapNode(950,650, 3, "Walker", "hallway"), new MapNode(1000,650, 3, "Walker", "hallway"),
                new MapNode(350,650, 3, "Walker", "bathroom")

        };
        for (MapNode m : startNodes)
        {
            nDao.insert(m);
        }

        return null;
    }
}
