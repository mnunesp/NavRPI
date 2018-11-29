package com.example.navrpi;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.content.Intent;

import com.github.barteksc.pdfviewer.PDFView;
import com.github.barteksc.pdfviewer.listener.OnDrawListener;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class buildings extends AppCompatActivity {


    PDFView pdfView;
    TextView showValue;
    String building;
    int floor = 0;
    List<MapNode> nodes = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buildings);

        building = "Walker";

        pdfView = findViewById(R.id.pdfView);
        pdfView.fromAsset("walker.pdf").pages(floor).enableDoubletap(false).load();

        showValue = (TextView) findViewById(R.id.floor);

    }

    // Renders the current floor plan and nodes
    private void Draw(int floor) {

        NodeDao nDao = NodeDatabase.getDatabase(getApplicationContext()).nodeDao();
        VerticiesDao vDao = VerticiesDatabase.getDatabase(getApplicationContext()).VerticiesDao();

        nodes = nDao.searchBuildFloor(building);


        Drawer d = new Drawer(buildings.this, (ArrayList<MapNode>) nodes, pdfView);

        OnDrawListener DrawL = d.createDrawListener(floor, vDao);


        pdfView.fromAsset("walker.pdf").pages(floor).enableDoubletap(false).onDraw(DrawL).load();

    }


    //Up one floor
    public void Increase (View view) {

        if (floor == 5) return;
        floor++;
        showValue.setText(Integer.toString(floor+1)); //counting starts at 0...
        Draw(floor);


    }
    //Down one floor
    public void Decrease (View view) {
        if (floor == 0)return; //bottom floor
        floor--;
        showValue.setText(Integer.toString(floor+1)); //counting starts at 0...
        Draw(floor);

    }


    public void PreviewRoute (View view) {

        Intent intent = new Intent(buildings.this, RoutePreviewActivity.class);
        intent.putExtra("building_name", "walker");
        intent.putExtra("building", building);
        startActivity(intent);


    }

}
