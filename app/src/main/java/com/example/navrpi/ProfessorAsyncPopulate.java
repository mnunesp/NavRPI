package com.example.navrpi;

import android.os.AsyncTask;

public class ProfessorAsyncPopulate extends AsyncTask<Void, Void, Void> {

    private final ProfessorDao pDao;

    ProfessorAsyncPopulate(ProfessorDatabase db) {
        pDao = db.professorDao();
    }

    @Override
    protected Void doInBackground(final Void... params){
        Professor DanWeeks = new Professor();
        DanWeeks.setFirstName("Dan");
        DanWeeks.setLastName("Weeks");
        pDao.insert(DanWeeks);
        return null;
    }
}
