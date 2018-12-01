package com.example.navrpi;

import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.List;

import static org.junit.Assert.*;

/**
 * Instrumented test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public class ExampleInstrumentedTest {
    @Test
    public void useAppContext() {
        // Context of the app under test.
        Context appContext = InstrumentationRegistry.getTargetContext();

        assertEquals("com.example.navrpi", appContext.getPackageName());
    }

    @Test
    public void ProfessorTests() {
        ProfessorDao pDao = ProfessorDatabase.getDatabase(InstrumentationRegistry.getTargetContext()).professorDao();
        pDao.insert(new Professor("Joe","Miller","Walker"));
        pDao.insert(new Professor("Danny","Miller","Walker"));
        pDao.insert(new Professor("Mike","Miller","Walker"));
        pDao.insert(new Professor("John","Smith","Walker"));
        pDao.insert(new Professor("Jack","Smith","Walker"));
        pDao.insert(new Professor("Kyle","Fleming","Walker"));
        pDao.insert(new Professor("Connor","Korver","Walker"));
        List<Professor> profs = pDao.getAllProfessors();
        for (int i = 0; i < profs.size(); i++) {
            assertEquals("Walker",profs.get(i).getNode().substring(0,6));
        }
        profs = pDao.searchLastName("Miller");
        assertTrue(profs.size() > 0);
        for (int i = 0; i < profs.size(); i++) {
            assertEquals("Miller", profs.get(i).getLastName());
        }
    }
}
