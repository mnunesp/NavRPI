package com.example.navrpi;

import android.os.AsyncTask;

public class VerticiesAsyncPopulate extends AsyncTask<Void, Void, Void> {

    private final VerticiesDao vDao;

    VerticiesAsyncPopulate(VerticiesDatabase db) { vDao = db.VerticiesDao(); }

    @Override
    protected Void doInBackground(final Void... params) {
        vDao.DeleteAll();
        //clear anything that's somehow already there (Room can crash if we try inserting the same thing twice)

        vDao.insert(new Verticies("Walker1555300","Walker1555370",1));
        vDao.insert(new Verticies("Walker1555370","Walker1555300",1));
        vDao.insert(new Verticies("Walker1555370","Walker1615370",1));
        vDao.insert(new Verticies("Walker1615370","Walker1555370",1));
        vDao.insert(new Verticies("Walker1615370","Walker1615520",1));
        vDao.insert(new Verticies("Walker1615520","Walker1615370",1));
        vDao.insert(new Verticies("Walker1615520","Walker1840520",1)); // Right Node
        vDao.insert(new Verticies("Walker1840520","Walker1615520",1));
        vDao.insert(new Verticies("Walker1615520","Walker1555520",1)); // Left Node
        vDao.insert(new Verticies("Walker1555520","Walker1615520",1));
        vDao.insert(new Verticies("Walker1555520","Walker1555570",1)); // Down Left Node
        vDao.insert(new Verticies("Walker1555570","Walker1555520",1));
        vDao.insert(new Verticies("Walker1840520","Walker1840650",1));
        vDao.insert(new Verticies("Walker1840650","Walker1840520",1));
        vDao.insert(new Verticies("Walker1840650","Walker1925650",1));
        vDao.insert(new Verticies("Walker1925650","Walker1840650",1));


        vDao.insert(new Verticies("Walker1555300","Walker2575300",1)); //Connection between floors
        vDao.insert(new Verticies("Walker2575300","Walker1555300",1));
        vDao.insert(new Verticies("Walker1925650","Walker1020650",1));
        vDao.insert(new Verticies("Walker1020650","Walker1925650",1));

        vDao.insert(new Verticies("Walker2575560","Walker2575300",1));
        vDao.insert(new Verticies("Walker2575300","Walker2575560",1));
        vDao.insert(new Verticies("Walker2575560","Walker2960560",1));
        vDao.insert(new Verticies("Walker2960560","Walker2575560",1));
        vDao.insert(new Verticies("Walker2960650","Walker2960560",1));
        vDao.insert(new Verticies("Walker2960560","Walker2960650",1));
        vDao.insert(new Verticies("Walker21020650","Walker2960650",1));
        vDao.insert(new Verticies("Walker2960650","Walker21020650",1));
        vDao.insert(new Verticies("Walker2500650","Walker2575560",1)); // Bathroom
        vDao.insert(new Verticies("Walker2575560","Walker2500650",1));

        vDao.insert(new Verticies("Walker2575300","Walker3450200",1)); //Connection between floors
        vDao.insert(new Verticies("Walker3450200","Walker2575300",1));
        vDao.insert(new Verticies("Walker21020650","Walker31000650",1));
        vDao.insert(new Verticies("Walker31000650","Walker21020650",1));

        vDao.insert(new Verticies("Walker3450550","Walker3450200",1));
        vDao.insert(new Verticies("Walker3450200","Walker3450550",1));
        vDao.insert(new Verticies("Walker3950550","Walker3450550",1));
        vDao.insert(new Verticies("Walker3450550","Walker3950550",1));
        vDao.insert(new Verticies("Walker3950650","Walker3950550",1));
        vDao.insert(new Verticies("Walker3950550","Walker3950650",1));
        vDao.insert(new Verticies("Walker31000650","Walker3950650",1));
        vDao.insert(new Verticies("Walker3950650","Walker31000650",1));
        vDao.insert(new Verticies("Walker3350650","Walker3450550",1)); // Bathroom
        vDao.insert(new Verticies("Walker3450550","Walker3350650",1));

        vDao.insert(new Verticies("Walker3450200","Walker4450200",1)); //Connection between floors
        vDao.insert(new Verticies("Walker4450200","Walker3450200",1));
        vDao.insert(new Verticies("Walker31000650","Walker41000650",1));
        vDao.insert(new Verticies("Walker41000650","Walker31000650",1));

        vDao.insert(new Verticies("Walker4450550","Walker4450200",1));
        vDao.insert(new Verticies("Walker4450200","Walker4450550",1));
        vDao.insert(new Verticies("Walker4950550","Walker4450550",1));
        vDao.insert(new Verticies("Walker4450550","Walker4950550",1));
        vDao.insert(new Verticies("Walker4950650","Walker4950550",1));
        vDao.insert(new Verticies("Walker4950550","Walker4950650",1));
        vDao.insert(new Verticies("Walker41000650","Walker4950650",1));
        vDao.insert(new Verticies("Walker4950650","Walker41000650",1));
        vDao.insert(new Verticies("Walker4350650","Walker4450550",1)); // Bathroom
        vDao.insert(new Verticies("Walker4450550","Walker4350650",1));

        vDao.insert(new Verticies("Walker4450200","Walker5450200",1)); //Connection between floors
        vDao.insert(new Verticies("Walker5450200","Walker4450200",1));
        vDao.insert(new Verticies("Walker41000650","Walker51000650",1));
        vDao.insert(new Verticies("Walker51000650","Walker41000650",1));

        vDao.insert(new Verticies("Walker5450550","Walker5450200",1));
        vDao.insert(new Verticies("Walker5450200","Walker5450550",1));
        vDao.insert(new Verticies("Walker5950550","Walker5450550",1));
        vDao.insert(new Verticies("Walker5450550","Walker5950550",1));
        vDao.insert(new Verticies("Walker5950650","Walker5950550",1));
        vDao.insert(new Verticies("Walker5950550","Walker5950650",1));
        vDao.insert(new Verticies("Walker51000650","Walker5950650",1));
        vDao.insert(new Verticies("Walker5950650","Walker51000650",1));
        vDao.insert(new Verticies("Walker5350650","Walker5450550",1)); // Bathroom
        vDao.insert(new Verticies("Walker5450550","Walker5350650",1));


        return null;
    }
}