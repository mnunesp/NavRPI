package com.example.navrpi;

import android.app.Dialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GoogleApiAvailability;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";
    private static final int ERROR_DIALOG_REQUEST = 9001;

    /**
     * initialize the actitities
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if(isServices()){
            init();
        }
    }

    /**
     * checks to make sure that all google services are functioning
     * @return true is funcitoning esle false
     */
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

    /**
     * sets up the two buttons following the splash screen
     */
    private void init(){
        Button btnMap = findViewById(R.id.btnMap);
        Button btnBuilding =  findViewById(R.id.btnBuilding);
        btnMap.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                Log.d(TAG, "Maps pushed");
                Intent intent = new Intent(MainActivity.this, MapsActivity.class);
                startActivity(intent);
                //MainActivity.this.finish();
            }
        });

        btnBuilding.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                Intent intent = new Intent(MainActivity.this, buildings.class);
                intent.putExtra("buildingName", "Walker");
                startActivity(intent);
                //MainActivity.this.finish();
            }
        });

    }

}

