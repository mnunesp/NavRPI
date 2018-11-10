package com.example.navrpi;

import android.os.AsyncTask;

public class VerticiesAsyncPopulate extends AsyncTask<Void, Void, Void> {

    private final VerticiesDao vDao;

    VerticiesAsyncPopulate(VerticiesDatabase db) { vDao = db.VerticiesDao(); }

    @Override
    protected Void doInBackground(final Void... params) {
        vDao.DeleteAll();
        //Verticies[] v = new Verticies[]{new Verticies("Walker3450200","Walker3450550",1), new Verticies("Walker3450200")};
        vDao.insert(new Verticies("Walker3450200","Walker3450550",1));
        vDao.insert(new Verticies("Walker3450200", "Walker3950550", 1));
        vDao.insert(new Verticies("Walker3450550","Walker3950550",1));
        vDao.insert(new Verticies("Walker3950550","Walker3950650",1));
        vDao.insert(new Verticies("Walker3950650","Walker31000650",1));

        return null;
    }
}