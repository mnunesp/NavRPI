package com.example.navrpi;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;

public class Startup extends AppCompatActivity {

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
