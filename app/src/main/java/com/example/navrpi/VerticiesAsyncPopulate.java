package com.example.navrpi;

import android.os.AsyncTask;

public class VerticiesAsyncPopulate extends AsyncTask<Void, Void, Void> {

    private final VerticiesDao vDao;

    VerticiesAsyncPopulate(VerticiesDatabase db) { vDao = db.VerticiesDao(); }

    @Override
    protected Void doInBackground(final Void... params) {
        vDao.DeleteAll();


        return null;
    }
}
