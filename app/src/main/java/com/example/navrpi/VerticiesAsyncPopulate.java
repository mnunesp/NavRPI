package com.example.navrpi;

import android.os.AsyncTask;

public class VerticiesAsyncPopulate extends AsyncTask<Void, Void, Void> {

    private final VerticiesDao vDao;

    VerticiesAsyncPopulate(VerticiesDatabase db) { vDao = db.VerticiesDao(); }

    @Override
    protected Void doInBackground(final Void... params) {
        vDao.DeleteAll();
        //clear anything that's somehow already there (Room can crash if we try inserting the same thing twice)
        vDao.insert(new Verticies("Walker3450550","Walker3450200",1));
        vDao.insert(new Verticies("Walker3450200","Walker3450550",1));
        vDao.insert(new Verticies("Walker3950550","Walker3450550",1));
        vDao.insert(new Verticies("Walker3450550","Walker3950550",1));
        vDao.insert(new Verticies("Walker3450550","Walker3350650",1));
        vDao.insert(new Verticies("Walker3350650","Walker3450550",1));
        vDao.insert(new Verticies("Walker3950650","Walker3950550",1));
        vDao.insert(new Verticies("Walker3950550","Walker3950650",1));
        vDao.insert(new Verticies("Walker31000650","Walker3950650",1));
        vDao.insert(new Verticies("Walker3950650","Walker31000650",1));

        vDao.insert(new Verticies("Walker3450550","Walker4450550",1)); //Connection between floors
        vDao.insert(new Verticies("Walker4450550","Walker3450550",1));

        vDao.insert(new Verticies("Walker4450550","Walker4450200",1));
        vDao.insert(new Verticies("Walker4450200","Walker4450550",1));
        vDao.insert(new Verticies("Walker4950550","Walker4450550",1));
        vDao.insert(new Verticies("Walker4450550","Walker4950550",1));
        vDao.insert(new Verticies("Walker4450550","Walker4350650",1));
        vDao.insert(new Verticies("Walker4350650","Walker4450550",1));
        vDao.insert(new Verticies("Walker4950650","Walker4950550",1));
        vDao.insert(new Verticies("Walker4950550","Walker4950650",1));
        vDao.insert(new Verticies("Walker41000650","Walker4950650",1));
        vDao.insert(new Verticies("Walker4950650","Walker41000650",1));

        return null;
    }
}