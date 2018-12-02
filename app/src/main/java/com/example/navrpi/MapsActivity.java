package com.example.navrpi;


import android.Manifest;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;

import android.os.Build;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GoogleApiAvailability;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MapStyleOptions;
import com.google.android.gms.location.LocationListener;

import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.GoogleMap.OnInfoWindowClickListener;
import com.google.android.gms.maps.model.PolylineOptions;
import com.google.maps.DirectionsApi;
import com.google.maps.GeoApiContext;
import com.google.maps.android.PolyUtil;
import com.google.maps.errors.ApiException;
import com.google.maps.model.DirectionsResult;
import com.google.maps.model.TravelMode;

import org.joda.time.DateTime;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;


public class MapsActivity extends FragmentActivity implements OnMapReadyCallback,
        GoogleApiClient.ConnectionCallbacks,
        GoogleApiClient.OnConnectionFailedListener,
        LocationListener,
        OnInfoWindowClickListener,
        GoogleMap.OnMarkerClickListener,
        GoogleMap.OnMarkerDragListener,
        GoogleMap.OnMapClickListener
{

    private static final String Tag = "MapsActivity";
    private GoogleMap mMap;
    GoogleApiClient mGoogleApiClient;
    Location mLastLocation;
    Marker mCurrLocationMarker;
    LocationRequest mLocationRequest;
    int PROXIMITY_RADIUS = 10000;
    double latitude, longitude;
    double end_latitude, end_longitude;
    private static final Double lat = 42.730052689755404;
    private static final Double lng = -73.67669504076449;
    private static final LatLng union = new LatLng(lat,lng);
    private static final int DEFAULT_ZOOM = 15;
    private Button planButton;
    private Button directionButton;
    private Marker mMark;
    private String serverKey= "MIIEvQIBADANBgkqhkiG9w0BAQEFAASCBKcwggSjAgEAAoIBAQC7S9r2FAFDAD2PDTczw/OF3iZjNmKmeoq97acHYBOAUGGe1gxaYeWkpyBQB888EZxrhEYXOPdeYe3qxjg1UkocH4OwzTZn5y8iNh8uU6nxuX1XPP1gsrvNw2HvA7fz0PfkcmyVwdJAksYEBJ7BU9OMm7EOSe2LX/FxLa15phsMxI4wABgrgrPzvagVb3JMCNJmr5wbv7wOVDJtd34N2B2y2xsa7vvXZoREpXX0889KqAx7HXc7h0dyk0j0no9nGURRyINy4Qvx3SixUDjOVt2Nk8eF+U21tzBH9/AcPcV08E9c3dDhU7TXzONSFnx8BwCOEIPI0BNPLwM/G8FBMZMLAgMBAAECggEAPwsP50+hgxd0IRAeOiblc/RsMG3wc8AArmBtne5pcgHcchXzb4LPRQZaOoX+a+Yzo/8QhqWOoi7NYf6Zkd0ig+fZsGvKuduXwmi8QyA3Ll20wmYNlXxj+aUf9E9onkGDB1q6kQf+CO0+iMCzXTillwka5kfdFNJVFzb8Utul9Dwp+viu3yJPyP2+F/hVo+o5ENZaS1XiqMDs9lmBZnB0e9sq8s7S27lAD6C8d971WX6Jgcfju42d7AX8DCzr3Iv9sPj3VZm1OQNmdo7PgAH9h6jrD7MBr11y9VSl8wb416fW6GIJ86iA+aHMDhTvv7L7Lk7x0T9zyybVSW+F+6HZWQKBgQD8+c1RTWDmqp5nBpBpWVu1ZQmF7cZY3zwLnacLs/QGuPHkqi9yjaA+K8y0txLYt0tCa7KisEDAA5TKdezGjv0xTAjK0/+EIFiO2RxUoW9vrT3Sw9AQaaJ6mv5F8uGXR1oiu7+8itSMnnGRFeoSpV5aE8YkwCBHNAHBAm0+I1PBEwKBgQC9iQzZCBfwSGEJNOhJ5G7FhwUZ0kUBao0sd0wzAwYKf0v9ne9P4jRiZDM+WRHwfsPS9p5hIJ2rzUwYhSWu2u5vWiH3IUwW6Tm7UdBa0LDkJBGeG8edKV5tMYydgpX7ASMviKke33cIN0g2Io+4HM1qkErGpBR8xfi5RaAicgWdKQKBgCqbcCdHXxC6n98+TchQko+kqsvx1jxVrOlP7jicYHdZYvRebYtfqyONgPbW9selZ3mSZg3cnas5bzACWJTAtIg/BCQVPK3mPMQicREX94rZpNYAwORixkjcHgNt+uzdyaKb+Jkq0M22Se5jwH7Pd2q4deDuswELE1iMrhWPIaYdAoGAbs7wNvZ3YGBAcux+nayyYkMk5Uq8Uy6jKIr6fpxW7M4tdDHglnhuHdPs7ZePWGYUQIM0Zx51b9rPkUpOlKKkYW91ihDqdj6WJQCY6m8167t2nVQqaKSl8vrT9cZBvwSUOJcSIN2Orrv7OMMN+RrFsXZ4cRe+bpAjcNXW4Cx/QbkCgYEAqEqZ4/xd6kBxLaWbiYJ+c6gfswX1WCoIvSeY3Tfn1nVYmpGVXXcIuLC2PNaESts6jMfsytnjA8gTB1uMSQ4SfUVr7L5qj6uxM1wZFURkCh//N1XAa02SuH6zU1yGxOX3Hxk7Q+SKBwR3A7K0Cl7gTA+Lx/8/R3sPG/2f8zdKtZQ=";



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        planButton = findViewById(R.id.Inspect_Building);
        directionButton = findViewById(R.id.Directions);
        planButton.setVisibility(View.INVISIBLE);
        directionButton.setVisibility(View.INVISIBLE);

        if (android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            checkLocationPermission();
        }

        //Check if Google Play Services Available or not
        if (!CheckGooglePlayServices()) {
            Log.d("onCreate", "Finishing test case since Google Play Services are not available");
            finish();
        }
        else {
            Log.d("onCreate","Google Play Services available.");
        }

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

    }


    private boolean CheckGooglePlayServices() {
        GoogleApiAvailability googleAPI = GoogleApiAvailability.getInstance();
        int result = googleAPI.isGooglePlayServicesAvailable(this);
        if(result != ConnectionResult.SUCCESS) {
            if(googleAPI.isUserResolvableError(result)) {
                googleAPI.getErrorDialog(this, result,
                        0).show();
            }
            return false;
        }
        return true;
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
        mMap = googleMap;

        //Map styling
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

        //Initialize Google Play Services
        if (android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (ContextCompat.checkSelfPermission(this,
                    Manifest.permission.ACCESS_FINE_LOCATION)
                    == PackageManager.PERMISSION_GRANTED) {
                buildGoogleApiClient();
                mMap.setMyLocationEnabled(true);
            }
        } else {
            buildGoogleApiClient();
            mMap.setMyLocationEnabled(true);
        }

        mMap.setOnInfoWindowClickListener(this);
        mMap.setOnMarkerDragListener(this);
        mMap.setOnMarkerClickListener(this);
        mMap.setOnMapClickListener(this);
        moveCamera(union, DEFAULT_ZOOM, "RPI_UNION");
        addLocations();
    }



    protected synchronized void buildGoogleApiClient() {
        mGoogleApiClient = new GoogleApiClient.Builder(this)
                .addConnectionCallbacks(this)
                .addOnConnectionFailedListener(this)
                .addApi(LocationServices.API)
                .build();
        mGoogleApiClient.connect();
    }

    private void addLocations(){
        Log.d(Tag, "Adding Locations");
        BuildingDao bdao = BuildingDatabase.getDatabase(getApplicationContext()).buildingDao();
        ArrayList<Building> b = (ArrayList<Building>)bdao.getAllBuildings();


        for(int i=0; i<b.size();i++){
            Marker temp = mMap.addMarker(new MarkerOptions()
                    .position(b.get(i).coordinate1())
                    .title(b.get(i).getName())
                    .snippet(b.get(i).getName())
                    .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_AZURE))
            );
        }
    }

    public void onClick(View v)
    {
        Object dataTransfer[] = new Object[2];
        //GetNearbyPlacesData getNearbyPlacesData = new GetNearbyPlacesData();

        switch(v.getId()) {
            case R.id.B_search: {
                EditText tf_location = (EditText) findViewById(R.id.TF_location);
                String location = tf_location.getText().toString();
                List<Address> addressList = null;
                MarkerOptions markerOptions = new MarkerOptions();
                Log.d("location = ", location);

                if (!location.equals("")) {
                    Geocoder geocoder = new Geocoder(this);
                    try {
                        addressList = geocoder.getFromLocationName(location, 5);

                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                    if (addressList != null) {
                        for (int i = 0; i < addressList.size(); i++) {
                            Address myAddress = addressList.get(i);
                            LatLng latLng = new LatLng(myAddress.getLatitude(), myAddress.getLongitude());
                            markerOptions.position(latLng);
                            mMap.addMarker(markerOptions);
                            mMap.animateCamera(CameraUpdateFactory.newLatLng(latLng));
                        }
                    }
                }
                this.hideSoftKeyboard();
            }
            break;

            case R.id.Inspect_Building: {
                Log.d(Tag, "onCLick: clicked on Inspect Building");
                Intent intent = new Intent(MapsActivity.this, buildings.class);
                intent.putExtra("buildingName", (String)mMark.getTitle());
                startActivity(intent);
            }
            break;

            case R.id.Directions: {
                Log.d(Tag, "onCLick: clicked on Get Directions");
                //theDirections(new LatLng(latitude, longitude),new LatLng(end_latitude, end_longitude));

                dataTransfer = new Object[3];
                String url = getDirectionsUrl();
                GetDirectionsData getDirectionsData = new GetDirectionsData();
                dataTransfer[0] = mMap;
                dataTransfer[1] = url;
                dataTransfer[2] = new LatLng(end_latitude, end_longitude);
                getDirectionsData.execute(dataTransfer);

            }
            break;

        }
    }


    private String getDirectionsUrl()
    {
        StringBuilder googleDirectionsUrl = new StringBuilder("https://maps.googleapis.com/maps/api/directions/json?");
        googleDirectionsUrl.append("origin="+latitude+","+longitude);
        googleDirectionsUrl.append("&destination="+end_latitude+","+end_longitude);
        googleDirectionsUrl.append("&key="+"AIzaSyD4qtDyzvxAUWhliWev4yoJqJYu6Jc9lLI");

        return googleDirectionsUrl.toString();
    }

    private String getUrl(double latitude, double longitude, String nearbyPlace)
    {
        StringBuilder googlePlacesUrl = new StringBuilder("https://maps.googleapis.com/maps/api/place/nearbysearch/json?");
        googlePlacesUrl.append("location=" + latitude + "," + longitude);
        googlePlacesUrl.append("&radius=" + PROXIMITY_RADIUS);
        googlePlacesUrl.append("&type=" + nearbyPlace);
        googlePlacesUrl.append("&sensor=true");
        googlePlacesUrl.append("&key=" + serverKey);
        Log.d("getUrl", googlePlacesUrl.toString());
        return (googlePlacesUrl.toString());
    }


    @Override
    public void onConnected(Bundle bundle) {
        mLocationRequest = new LocationRequest();
        mLocationRequest.setInterval(1000);
        mLocationRequest.setFastestInterval(1000);
        mLocationRequest.setPriority(LocationRequest.PRIORITY_BALANCED_POWER_ACCURACY);
        if (ContextCompat.checkSelfPermission(this,
                Manifest.permission.ACCESS_FINE_LOCATION)
                == PackageManager.PERMISSION_GRANTED) {
            LocationServices.FusedLocationApi.requestLocationUpdates(mGoogleApiClient, mLocationRequest, this);
        }
    }



    @Override
    public void onConnectionSuspended(int i) {

    }

    @Override
    public void onLocationChanged(Location location) {
        Log.d("onLocationChanged", "entered");

        mLastLocation = location;
        if (mCurrLocationMarker != null) {
            mCurrLocationMarker.remove();
        }

        latitude = location.getLatitude();
        longitude = location.getLongitude();


        LatLng latLng = new LatLng(location.getLatitude(), location.getLongitude());
        MarkerOptions markerOptions = new MarkerOptions();
        markerOptions.position(latLng);
        markerOptions.draggable(true);
        markerOptions.title("Current Position");
        markerOptions.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_MAGENTA));
        mCurrLocationMarker = mMap.addMarker(markerOptions);

        //move map camera
        mMap.moveCamera(CameraUpdateFactory.newLatLng(latLng));
        mMap.animateCamera(CameraUpdateFactory.zoomTo(11));


        Toast.makeText(MapsActivity.this,"Your Current Location", Toast.LENGTH_LONG).show();


        //stop location updates
        if (mGoogleApiClient != null) {
            LocationServices.FusedLocationApi.removeLocationUpdates(mGoogleApiClient, this);
            Log.d("onLocationChanged", "Removing Location Updates");
        }

    }

    @Override
    public void onConnectionFailed(ConnectionResult connectionResult) {

    }

    public static final int MY_PERMISSIONS_REQUEST_LOCATION = 99;
    public boolean checkLocationPermission(){
        if (ContextCompat.checkSelfPermission(this,
                Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED) {

            // Asking user if explanation is needed
            if (ActivityCompat.shouldShowRequestPermissionRationale(this,
                    Manifest.permission.ACCESS_FINE_LOCATION)) {

                // Show an explanation to the user *asynchronously* -- don't block
                // this thread waiting for the user's response! After the user
                // sees the explanation, try again to request the permission.

                //Prompt the user once explanation has been shown
                ActivityCompat.requestPermissions(this,
                        new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                        MY_PERMISSIONS_REQUEST_LOCATION);


            } else {
                // No explanation needed, we can request the permission.
                ActivityCompat.requestPermissions(this,
                        new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                        MY_PERMISSIONS_REQUEST_LOCATION);
            }
            return false;
        } else {
            return true;
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           String permissions[], int[] grantResults) {
        switch (requestCode) {
            case MY_PERMISSIONS_REQUEST_LOCATION: {
                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {

                    // permission was granted. Do the
                    // contacts-related task you need to do.
                    if (ContextCompat.checkSelfPermission(this,
                            Manifest.permission.ACCESS_FINE_LOCATION)
                            == PackageManager.PERMISSION_GRANTED) {

                        if (mGoogleApiClient == null) {
                            buildGoogleApiClient();
                        }
                        mMap.setMyLocationEnabled(true);
                    }

                } else {

                    // Permission denied, Disable the functionality that depends on this permission.
                    Toast.makeText(this, "permission denied", Toast.LENGTH_LONG).show();
                }
                return;
            }

            // other 'case' lines to check for other permissions this app might request.
            // You can add here other case statements according to your requirement.
        }
    }


    @Override
    public boolean onMarkerClick(Marker marker) {
        mMark = marker;

        Log.d("Marker", "onClick: clicked on marker");
        planButton.setVisibility(View.VISIBLE);
        directionButton.setVisibility(View.VISIBLE);
        end_latitude=marker.getPosition().latitude;
        end_longitude=marker.getPosition().longitude;
        //marker.setDraggable(true);
        return false;
    }

    @Override
    public void onMapClick(LatLng point) {
        Log.d("Map", "onClick");
        planButton.setVisibility(View.INVISIBLE);
        directionButton.setVisibility(View.INVISIBLE);
        this.hideSoftKeyboard();
    }

    @Override
    public void onMarkerDragStart(Marker marker) {

    }

    @Override
    public void onMarkerDrag(Marker marker) {

    }

    @Override
    public void onMarkerDragEnd(Marker marker) {
        end_latitude = marker.getPosition().latitude;
        end_longitude =  marker.getPosition().longitude;

        Log.d("end_lat",""+end_latitude);
        Log.d("end_lng",""+end_longitude);
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

    private void hideSoftKeyboard(){
        Log.d(Tag, "Hiding keyboard");
        this.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);

    }

    @Override
    public void onInfoWindowClick(Marker marker) {
        Toast.makeText(this, "Info window clicked",
                Toast.LENGTH_SHORT).show();
    }
}