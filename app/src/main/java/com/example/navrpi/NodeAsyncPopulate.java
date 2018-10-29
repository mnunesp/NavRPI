package com.example.navrpi;

import android.os.AsyncTask;

public class NodeAsyncPopulate extends AsyncTask<Void, Void, Void> {

    private final NodeDao nDao;

    NodeAsyncPopulate(NodeDatabase db) {
        nDao = db.nodeDao();
    }

    @Override
    protected Void doInBackground(final Void... params){
        MapNode first = new MapNode();
        nDao.insert(first);
        return null;
    }
}
