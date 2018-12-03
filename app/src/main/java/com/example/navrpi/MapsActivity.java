package com.example.navrpi;


import android.Manifest;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.KeyEvent;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.UiSettings;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MapStyleOptions;

import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.mikepenz.materialdrawer.DrawerBuilder;
import com.mikepenz.materialdrawer.model.DividerDrawerItem;
import com.mikepenz.materialdrawer.model.PrimaryDrawerItem;
import com.mikepenz.materialdrawer.model.SecondaryDrawerItem;
import com.mikepenz.materialdrawer.model.interfaces.IDrawerItem;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private static final String Tag = "MapsActivity";

    private static final String FINE_LOCATION = Manifest.permission.ACCESS_FINE_LOCATION;
    private static final String COURSE_LOCATION = Manifest.permission.ACCESS_COARSE_LOCATION;
    private static final int LOCATION_PERMISSION_REQUEST_CODE = 1234;
    private FusedLocationProviderClient mFusedLocationProviderClient;
    private static final int DEFAULT_ZOOM = 15;

    //Widgets
    private EditText mSearchText;
    private ImageView mGps;
    private ImageButton mMenu;

    //VARS
    private Boolean mLocationPermissionsGranted = false;
    private static final Double lat = 42.730052689755404;
    private static final Double lng = -73.67669504076449;
    private static final LatLng union = new LatLng(lat,lng);

    private GoogleMap mMap;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        mSearchText =  findViewById(R.id.input_search);
        mGps =  findViewById(R.id.ic_gps);
        mMenu =  findViewById(R.id.menuButton);

        getLocationPermission();

        final BuildingDao bDao = BuildingDatabase.getDatabase(getApplicationContext()).buildingDao();
        ArrayList<Building> buils = (ArrayList<Building>) bDao.getAllBuildings();
        final String rest = Building.getBuildings(buils);

        PrimaryDrawerItem item1 = new PrimaryDrawerItem().withIdentifier(1).withName("Menu");
        SecondaryDrawerItem item2 = new SecondaryDrawerItem().withIdentifier(2).withName("Buildings");
        Toolbar toolbar = findViewById(R.id.toolbar);


        //create the drawer and remember the 'Drawer' result object
        com.mikepenz.materialdrawer.Drawer result = new DrawerBuilder()
                .withActivity(this)
                .withToolbar(toolbar)
                .addDrawerItems(
                        item1,
                        new DividerDrawerItem(),
                        item2
                        //new SecondaryDrawerItem().withName("Buildings")
                )
                .withOnDrawerItemClickListener(new com.mikepenz.materialdrawer.Drawer.OnDrawerItemClickListener() {
                    @Override
                    public boolean onItemClick(View view, int position, IDrawerItem drawerItem) {
                        //do something with clicked item
                        setContentView(R.layout.building_scroll);
                        TextView txtView = findViewById(R.id.restBuil);
                        txtView.setText(rest);
                        return false;
                    }
                })
                .build();
    }



    private void init(){
        Log.d(Tag, "init: initializing");
        mSearchText.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_SEARCH
                        || actionId == EditorInfo.IME_ACTION_DONE
                        || event.getAction() == event.ACTION_DOWN
                        || event.getAction() == event.KEYCODE_ENTER) {

                    //execute search method
                    hideSoftKeyboard();
                    geoLocate();
                }

                return false;
            }
        });
        mGps.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(Tag, "onClick: clicked gps icon");
                getDeviceLocation();
            }
        });

        //Menu Button CLickable
        mMenu.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){

                Log.d(Tag, "onClick: click on Menu");
                PopupMenu popupMenu = new PopupMenu(MapsActivity.this, mMenu);
                popupMenu.getMenuInflater().inflate(R.menu.popup_menu, popupMenu.getMenu());

                popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        Toast.makeText(MapsActivity.this, "" + item.getTitle(), Toast.LENGTH_SHORT).show();
                        return true;
                    }
                });

                popupMenu.show();

            }
        });

        //Clickable for Pointer (go to this building)
        mMap.setOnMarkerClickListener(new GoogleMap.OnMarkerClickListener() {
            @Override
            public boolean onMarkerClick(Marker marker) {
                Log.d(Tag, "onCLick: clicked on polygon");
                Intent intent = new Intent(MapsActivity.this, buildings.class);
                intent.putExtra("buildingName", (String)marker.getTitle());
                startActivity(intent);
                return true;
            }
        });

        hideSoftKeyboard();
    }

    private void geoLocate(){
        Log.d(Tag, "geoLocate: geolocating");

        String searchString = mSearchText.getText().toString();
        Geocoder geocoder = new Geocoder(MapsActivity.this);
        List<Address> list = new ArrayList<>();
        try{
            list = geocoder.getFromLocationName(searchString, 1);
        }catch (IOException e){
            Log.e(Tag, "geoLocate: IOException: " + e.getMessage());
        }

        if(list.size() > 0 ){
            Address address = list.get(0);

            Log.d(Tag, "geoLocate: found a location " + address.toString());
            //Toast.makeText(this, address.toString(), Toast.LENGTH_SHORT).show();
            moveCamera(new LatLng(address.getLatitude(),address.getLongitude()), DEFAULT_ZOOM, address.getAddressLine(0));
        }
        hideSoftKeyboard();
    }

    private void getDeviceLocation() {
        Log.d(Tag, "getDeviceLocation: getting the devices current Location");

        mFusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(this);
        try {
            if (mLocationPermissionsGranted) {
                Task location = mFusedLocationProviderClient.getLastLocation();
                location.addOnCompleteListener(new OnCompleteListener() {
                    @Override
                    public void onComplete(@NonNull Task task) {
                        if (task.isSuccessful()) {
                            Log.d(Tag, "onComplete: found location");
                            Location currentLocation = (Location) task.getResult();

                            moveCamera(new LatLng(currentLocation.getLatitude(), currentLocation.getLongitude()), DEFAULT_ZOOM, "My_location");
                        } else {
                            Log.d(Tag, "onComplete: current location is null");
                            Toast.makeText(MapsActivity.this, "unable to get current location", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        } catch (SecurityException e) {
            Log.d(Tag, "getDeviceLocation: SecurityException: " + e.getMessage());
        }
    }

    private void moveCamera(LatLng latLng, float zoom, String title) {
        Log.d(Tag, "moveCamera :moving the camera to: lat: " + latLng.latitude + ", lng: " + latLng.longitude);
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(latLng, zoom));

        if(!title.equals("My Location")){
            MarkerOptions options = new MarkerOptions()
                    .position(latLng)
                    .title(title);
            mMap.addMarker(options);
            hideSoftKeyboard();
        }


    }

    private void initMap() {
        Log.d(Tag, "Initializing map");
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }


    private void getLocationPermission() {
        Log.d(Tag, "getLocationPermission: called");
        String[] permissions = {Manifest.permission.ACCESS_FINE_LOCATION,
                Manifest.permission.ACCESS_COARSE_LOCATION};

        if (ContextCompat.checkSelfPermission(this.getApplicationContext(), FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
            if (ContextCompat.checkSelfPermission(this.getApplicationContext(), COURSE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
                mLocationPermissionsGranted = true;
                initMap();
            } else {
                ActivityCompat.requestPermissions(this, permissions, LOCATION_PERMISSION_REQUEST_CODE);
            }
        } else {
            ActivityCompat.requestPermissions(this, permissions, LOCATION_PERMISSION_REQUEST_CODE);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        Log.d(Tag, "onRequestPermissionsResult: called");
        mLocationPermissionsGranted = false;

        switch (requestCode) {
            case LOCATION_PERMISSION_REQUEST_CODE: {
                if (grantResults.length > 0) {
                    for (int i = 0; i < grantResults.length; i++) {
                        if (grantResults[i] != PackageManager.PERMISSION_GRANTED) {
                            mLocationPermissionsGranted = false;
                            Log.d(Tag, "Permission Denied");
                            return;
                        }
                    }
                    Log.d(Tag, "Permission Granted");
                    mLocationPermissionsGranted = true;
                    //initialize our map;
                    initMap();
                }
            }
        }
    }


    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        Toast.makeText(this, "Map is Ready", Toast.LENGTH_SHORT).show();
        mMap = googleMap;
        try {
            // Customise the styling of the base map using a JSON object defined
            // in a raw resource file.
            boolean success = googleMap.setMapStyle(
                    MapStyleOptions.loadRawResourceStyle(
                            this, R.raw.mapstyle));

            if (!success) {
                Log.e(Tag, "Style parsing failed.");
            }
        } catch (Resources.NotFoundException e) {
            Log.e(Tag, "Can't find style. Error: ", e);}

        // Add at my location
        if (mLocationPermissionsGranted) {
            //getDeviceLocation();
            moveCamera(union, DEFAULT_ZOOM, "RPI_UNION");

            if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
                    != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this,
                    Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                return;
            }
            UiSettings uiSettings = googleMap.getUiSettings();
            uiSettings.setCompassEnabled(true);
            mMap.setMyLocationEnabled(true);
            mMap.getUiSettings().setMyLocationButtonEnabled(false);

            addLocations();


            init();
        }
        //mMap.addMarker(new MarkerOptions().position(rpi).title("Marker in RPI"));
        //mMap.moveCamera(CameraUpdateFactory.newLatLng(rpi));
    }


    //Hide the keyboard when not being used for typing in search
    private void hideSoftKeyboard(){
        Log.d(Tag, "Hiding keyboard");
        this.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);

    }

    //add Building points from the Data base
    private void addLocations(){
        Log.d(Tag, "Adding Locations");
        BuildingDao bdao = BuildingDatabase.getDatabase(getApplicationContext()).buildingDao();
        ArrayList<Building> b = (ArrayList<Building>)bdao.getAllBuildings();


        for(int i=0; i<b.size();i++){
            Marker temp = mMap.addMarker(new MarkerOptions()
                    .position(b.get(i).coordinate1())
                    .title(b.get(i).getName()));
        }
    }

    public void walkerClicked(View view){
        Intent intent = new Intent(MapsActivity.this, buildings.class);
        startActivity(intent);
    }

    public void backButton(View view){

        Intent intent = new Intent(MapsActivity.this, MapsActivity.class);
        startActivity(intent);
    }


}