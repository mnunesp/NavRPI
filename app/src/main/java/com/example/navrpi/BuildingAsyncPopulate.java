package com.example.navrpi;

import android.os.AsyncTask;

public class BuildingAsyncPopulate extends AsyncTask<Void, Void, Void> {

    private final BuildingDao bDao;

    BuildingAsyncPopulate(BuildingDatabase db) {
        bDao = db.buildingDao();
    }

    @Override
    protected Void doInBackground(final Void... params){
        bDao.deleteAll();
        float[] n = new float[8];
        Building walker = new Building("Walker", n);
        bDao.insert(walker);
        return null;
    }
}
