package com.example.navrpi;

import android.app.Dialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GoogleApiAvailability;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";
    private static final int ERROR_DIALOG_REQUEST = 9001;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if(isServices()){
            init();
        }
    }

    public boolean isServices(){
        Log.d(TAG, "isServicesOK: checking google services version");

        int available = GoogleApiAvailability.getInstance().isGooglePlayServicesAvailable(MainActivity.this);

        if(available == ConnectionResult.SUCCESS){
            Log.d(TAG, "isServicesOK: Google Play Services is working");
            return true;
        }
        else if(GoogleApiAvailability.getInstance().isUserResolvableError(available)){
            Log.d(TAG, "isServicesOK: an error occured but we can fix it");
            Dialog dialog = GoogleApiAvailability.getInstance().getErrorDialog(MainActivity.this, available, ERROR_DIALOG_REQUEST);
            dialog.show();
        }
        else {
            Toast.makeText(this, "we can't make map request", Toast.LENGTH_SHORT).show();
        }
        return false;
    }

    private void init(){
        
    }
    public void goto_map(View view){
        new Handler().post(new Runnable(){
        @Override
        public void run(){
            Intent selectIntent = new Intent(MainActivity.this, MapsActivity.class);
            startActivity(selectIntent);
            MainActivity.this.finish();
         }
        });
    }

    public void goto_building(View view){
        new Handler().post(new Runnable(){
            @Override
            public void run(){
                Intent selectIntent = new Intent(MainActivity.this, buildings.class);
                startActivity(selectIntent);
                MainActivity.this.finish();
            }
        });
    }
}
