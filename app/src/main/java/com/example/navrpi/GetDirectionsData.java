package com.example.navrpi;

import android.graphics.Color;
import android.os.AsyncTask;
import android.util.Log;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.Polyline;
import com.google.android.gms.maps.model.PolylineOptions;
import com.google.maps.android.PolyUtil;


import java.io.IOException;
import java.nio.DoubleBuffer;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.TimeUnit;


public class GetDirectionsData extends AsyncTask<Object,String,String> {

    GoogleMap mMap;
    String url;
    String googleDirectionsData;
    String duration, distance;
    LatLng latLng;
    List<Polyline> polylines;

    private static final String Tag = "GetDirectionsData";


    /**
     * This begins upon execute call in the MapsActivity. It
     * downloads the proper url from the directions data and
     * sends it to be read
     * @param objects
     * @return
     */
    @Override
    protected String doInBackground(Object... objects) {
        mMap = (GoogleMap)objects[0];
        url = (String)objects[1];
        latLng = (LatLng)objects[2];
        polylines = (List<Polyline>)objects[3];


        DownloadUrl downloadUrl = new DownloadUrl();
        try {
            Log.d(Tag, "Getting URL");
            googleDirectionsData = downloadUrl.readUrl(url);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return googleDirectionsData;
    }

    /**
     * the following is done after the execution of the above code
     * @param s
     */
    @Override
    protected void onPostExecute(String s) {

        String[] directionsList;
        DataParser parser = new DataParser();
        directionsList = parser.parseDirections(s);
        displayDirection(directionsList);
    }

    /**
     * Adds the polylines to the map to show the directions from current
     * position to requested position
     * @param directionsList
     */
    public void displayDirection(String[] directionsList)
    {

        int count = directionsList.length;

        for(int i = 0;i<count;i++)
        {
            PolylineOptions options = new PolylineOptions();
            options.color(Color.RED);
            options.width(10);
            options.addAll(PolyUtil.decode(directionsList[i]));

            Polyline poly = mMap.addPolyline(options);
            polylines.add(poly);
        }
    }






}