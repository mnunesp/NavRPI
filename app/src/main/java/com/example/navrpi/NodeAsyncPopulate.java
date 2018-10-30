package com.example.navrpi;

import android.os.AsyncTask;

public class NodeAsyncPopulate extends AsyncTask<Void, Void, Void> {

    private final NodeDao nDao;

    NodeAsyncPopulate(NodeDatabase db) {
        nDao = db.nodeDao();
    }

    @Override
    protected Void doInBackground(final Void... params) {
        MapNode startNodes[] = new MapNode[]{new MapNode(525, 250, 3, "Walker"), new MapNode(525, 625, 3, "Walker"),
                new MapNode(1025, 625, 3, "Walker"), new MapNode(665, 350, 2, "Walker"),
                new MapNode(665, 650, 2, "Walker"), new MapNode(1100, 650, 2, "Walker")};
        for (int i = 0; i < startNodes.length; i++){
            nDao.insert(startNodes[i]);
        }
        return null;
    }
}
