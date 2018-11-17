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
        Building[] bds = {new Building("Walker",42.7308527,-73.6825036 ),
                new Building("Union",42.73005269,-73.67669504),
                new Building("West Hall",42.7316975,-73.6831504),
                new Building("Winslow",42.730946,-73.684265),
                new Building("Voorhees",42.7291963,-73.6817186),
                new Building("Troy",42.7310303,-73.680523),
                new Building("Sage Labs",42.730853,-73.6815985),
                new Building("Ricketts",42.7309095,-73.6796953),
                new Building("Pittsburgh",42.7311638,-73.6832867),
                new Building("Low",42.7292887,-73.6786747),
                new Building("Lally",42.7300623,-73.6819016),
                new Building("JEC",42.7296206,-73.6802931),
                new Building("J-Rowl",42.7288139,-73.6803894),
                new Building("Heffner",42.7328608,-73.6781532),
                new Building("87 Gym",42.73081123,-73.67883325),
                new Building("Gurley",42.7282884,-73.6926462),
                new Building("Greene",42.7300231,-73.6811639),
                new Building("Folsom",42.7293612,-73.6826054),
                new Building("EMPAC",42.7288767,-73.6838647),
                new Building("DCC",42.7295109,-73.6790586),
                new Building("Cogswell",42.7281871,-73.6812503),
                new Building("Carnegie",42.7304552,-73.6832111),
                new Building("Biotech",42.728255,-73.6784013),
                new Building("Amos Eaton",42.7301849,-73.682557),
                new Building("Academy",42.7272973,-73.6786666)};
        for (int i = 0; i < bds.length; i++) {
            bDao.insert(bds[i]);
        }

        return null;
    }
}
