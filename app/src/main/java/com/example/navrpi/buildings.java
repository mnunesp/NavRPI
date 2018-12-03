package com.example.navrpi;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.widget.Button;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;
import android.content.Intent;
import android.support.v7.widget.Toolbar;
import org.w3c.dom.Text;
import static android.os.Build.VERSION_CODES.P;

import com.github.barteksc.pdfviewer.PDFView;
import com.github.barteksc.pdfviewer.listener.OnDrawListener;
import com.mikepenz.materialdrawer.DrawerBuilder;
import com.mikepenz.materialdrawer.model.DividerDrawerItem;
import com.mikepenz.materialdrawer.model.PrimaryDrawerItem;
import com.mikepenz.materialdrawer.model.SecondaryDrawerItem;
import com.mikepenz.materialdrawer.model.interfaces.IDrawerItem;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static com.example.navrpi.R.layout.drawerlayout;

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
        new DrawerBuilder().withActivity(this).build();


        final ProfessorDao pDao = ProfessorDatabase.getDatabase(getApplicationContext()).professorDao();
        ArrayList<Professor> profs = (ArrayList<Professor>) pDao.getAllProfessors();
        final String rest = Professor.getRest(profs);

        PrimaryDrawerItem item1 = new PrimaryDrawerItem().withIdentifier(1).withName("Menu");
        SecondaryDrawerItem item2 = new SecondaryDrawerItem().withIdentifier(2).withName("Professors");
        Toolbar toolbar = findViewById(R.id.toolbar);


        //create the drawer and remember the 'Drawer' result object
        com.mikepenz.materialdrawer.Drawer result = new DrawerBuilder()
                .withActivity(this)
                .withToolbar(toolbar)
                .addDrawerItems(
                        item1,
                        new DividerDrawerItem(),
                        item2,
                        new SecondaryDrawerItem().withName("Setting")
                )
                .withOnDrawerItemClickListener(new com.mikepenz.materialdrawer.Drawer.OnDrawerItemClickListener() {
                    @Override
                    public boolean onItemClick(View view, int position, IDrawerItem drawerItem) {
                       //do something with clicked item
                        setContentView(R.layout.professor_scroll);
                    }
                })
                .build();



        building = "Walker";

        //setContentView(R.layout.activity_buildings);
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

    public void backButton(View view){
        Intent intent = new Intent(buildings.this, buildings.class);
        startActivity(intent);
    }
    public void prof1Clicked(View view){
        setContentView(R.layout.activity_buildings);
        int profFloor = new MapNode("Walker3950550").getFloor();
        pdfView.fromAsset("walker.pdf").pages(2).enableDoubletap(false).load();
        showValue.setText(Integer.toString(2));
        Draw(floor);
    }

}
