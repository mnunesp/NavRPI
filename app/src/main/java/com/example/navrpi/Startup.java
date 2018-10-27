package com.example.navrpi;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.os.Handler;

public class Startup extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_startup);
    }

    public void goto_map(View view){
        new Handler().post(new Runnable(){
            @Override
            public void run(){
                Intent selectIntent = new Intent(Startup.this, MapsActivity.class);
                startActivity(selectIntent);
                finish();
            }
        });
    }

    public void goto_building(View view){
        new Handler().post(new Runnable(){
            @Override
            public void run(){
                Intent selectIntent = new Intent(Startup.this, buildings.class);
                startActivity(selectIntent);
                finish();
            }
        });
    }


}
