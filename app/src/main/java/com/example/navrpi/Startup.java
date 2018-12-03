package com.example.navrpi;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;

public class Startup extends AppCompatActivity {

    /**
     * This is the first oncreate called. Send view to splash activity
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_startup);
        //getSupportActionBar().hide();
        LogoLauncher logolauncher = new LogoLauncher();
        logolauncher.start();

    }

    /**
     * This is the splash screen. It stays up for 3 seconds then goes to main activity
     */
    private class LogoLauncher extends Thread{
        public void run(){
            try{
                sleep(3000);
            }catch(InterruptedException e){
                e.printStackTrace();
            }

            Intent intent = new Intent(Startup.this, MainActivity.class);
            startActivity(intent);
            Startup.this.finish();
        }
    }


}
