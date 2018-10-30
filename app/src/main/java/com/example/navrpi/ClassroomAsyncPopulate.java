package com.example.navrpi;

import android.os.AsyncTask;

public class ClassroomAsyncPopulate extends AsyncTask<Void, Void, Void> {

    private final ClassroomDao cDao;

    ClassroomAsyncPopulate(ClassroomDatabase db) {
        cDao = db.classroomDao();
    }

    @Override
    protected Void doInBackground(final Void... params){
        Classroom DCC113 = new Classroom();
        DCC113.setBuilding("DCC");
        DCC113.setNumber("113");
        cDao.insert(DCC113);
        return null;
    }
}
