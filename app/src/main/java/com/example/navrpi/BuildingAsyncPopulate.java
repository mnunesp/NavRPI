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
        double lat = 42.73087;
        double lng = -73.682535;
        Building walker = new Building("Walker", lat, lng);
        bDao.insert(walker);
        return null;
    }
}
