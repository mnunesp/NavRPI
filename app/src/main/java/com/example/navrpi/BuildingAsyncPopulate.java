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
        double[] n = new double[8];
        n[0] = 42.73087 - .00025;
        n[1] = 42.73087 + .0002;
        n[2] = 42.73087 - .00025;
        n[3] = 42.73087 + .0002;
        n[4] = -73.682535 - .00025;
        n[5] = -73.682535 - .00025;
        n[6] = -73.682535 + .0002;
        n[7] = -73.682535 + .0002;
        Building walker = new Building("Walker", n);
        bDao.insert(walker);
        return null;
    }
}
