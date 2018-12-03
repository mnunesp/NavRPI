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

                new MapNode(555,300, 1, "Walker", "hallway"),
                new MapNode(555,370, 1, "Walker", "hallway"),
                new MapNode(615,370, 1, "Walker", "hallway"),
                new MapNode(615,520, 1, "Walker", "hallway"),
                new MapNode(555,520, 1, "Walker", "hallway"), // Left Node
                new MapNode(555,570, 1, "Walker", "hallway"), // Down Left Node
                new MapNode(840,520, 1, "Walker", "hallway"), // Right Node
                new MapNode(840,650, 1, "Walker", "hallway"),
                new MapNode(925,650, 1, "Walker", "hallway"),



                new MapNode(575,300, 2, "Walker", "hallway"),
                new MapNode(575,560, 2, "Walker", "hallway"),
                new MapNode(960,560, 2, "Walker", "hallway"),
                new MapNode(960,650, 2, "Walker", "hallway"),
                new MapNode(1020,650, 2, "Walker", "hallway"),
                new MapNode(500,650, 2, "Walker", "bathroom"),

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
                new MapNode(350,650, 4, "Walker", "bathroom"),

                new MapNode(450,200, 5, "Walker", "hallway"),
                new MapNode(450,550, 5, "Walker", "hallway"),
                new MapNode(950,550, 5, "Walker", "hallway"),
                new MapNode(950,650, 5, "Walker", "hallway"),
                new MapNode(1000,650, 5, "Walker", "hallway"),
                new MapNode(350,650, 5, "Walker", "bathroom"),

        };
        for (MapNode m : startNodes)
        {
            nDao.insert(m);
        }

        return null;
    }
}
