package com.example.navrpi;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.IntentSender;
import android.content.ServiceConnection;
import android.content.SharedPreferences;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.content.res.AssetManager;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.UserHandle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.Display;

import org.junit.Test;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {
   // @Test
    //public void addition_isCorrect() {
      //  assertEquals(4, 2 + 2);
    //}

    @Test
    public void ProfessorTests() {
        ProfessorDao pDao = ProfessorDatabase.getDatabase(new Activity()).professorDao();
        pDao.insert(new Professor("Joe","Miller","na"));
        pDao.insert(new Professor("Dan","Miller","na"));
        pDao.insert(new Professor("Mike","Miller","na"));
        pDao.insert(new Professor("John","Smith","na"));
        pDao.insert(new Professor("Jack","Smith","na"));
        pDao.insert(new Professor("Kyle","Fleming","na"));
        pDao.insert(new Professor("Connor","Korver","na"));
        List<Professor> profs = pDao.searchLastName("Miller");
        for (int i = 0; i < profs.size(); i++) {
            assertEquals("Miller",profs.get(i).getLastName());
        }
        profs = pDao.searchLastName("Smith");
        assertEquals(profs.get(0).getLastName(), "Smith");
        assertNotEquals("Miller", profs.get(1).getLastName());
    }
}