package com.example.navrpi;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.view.MotionEvent;
import android.view.View;

import android.widget.TextView;
import android.content.Intent;
import android.support.v7.widget.Toolbar;

import com.github.barteksc.pdfviewer.PDFView;
import com.github.barteksc.pdfviewer.listener.OnDrawListener;
import com.github.barteksc.pdfviewer.listener.OnTapListener;
import com.mikepenz.materialdrawer.DrawerBuilder;
import com.mikepenz.materialdrawer.model.DividerDrawerItem;
import com.mikepenz.materialdrawer.model.PrimaryDrawerItem;
import com.mikepenz.materialdrawer.model.SecondaryDrawerItem;
import com.mikepenz.materialdrawer.model.interfaces.IDrawerItem;

import java.util.ArrayList;
import java.util.List;

public class BuildingsActivity extends AppCompatActivity {


    PDFView pdfView;
    TextView showValue;
    String building;
    String pdfString;
    int floor = 0;
    List<MapNode> nodes = new ArrayList<>();




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buildings);
        new DrawerBuilder().withActivity(this).build();


<<<<<<< HEAD:app/src/main/java/com/example/navrpi/buildings.java

        PrimaryDrawerItem item1 = new PrimaryDrawerItem().withIdentifier(1).withName("Menu"); // Top menu bar
        SecondaryDrawerItem item2 = new SecondaryDrawerItem().withIdentifier(2).withName("Professors"); //lower menu bar
        Toolbar toolbar = findViewById(R.id.toolbar);

        //Professor 'Drawer' contains professor names


        /**
         * create the drawer and remember the 'Drawer' result object
         */
=======
        PrimaryDrawerItem item1 = new PrimaryDrawerItem().withIdentifier(1).withName("Menu");
        SecondaryDrawerItem item2 = new SecondaryDrawerItem().withIdentifier(2).withName("Professors");
        Toolbar toolbar = findViewById(R.id.toolbar);

        // Professor 'Drawer' contains professor names
        // Create the drawer and remember the 'Drawer' result object
>>>>>>> 139f7fa5b51c03682bf88c42b7f9f9138def23a9:app/src/main/java/com/example/navrpi/BuildingsActivity.java
        com.mikepenz.materialdrawer.Drawer result = new DrawerBuilder()
                .withActivity(this)
                .withToolbar(toolbar)
                .addDrawerItems(
                        item1,
                        new DividerDrawerItem(),
                        item2
                )
                .withOnDrawerItemClickListener(new com.mikepenz.materialdrawer.Drawer.OnDrawerItemClickListener() {
                    @Override
                    public boolean onItemClick(View view, int position, IDrawerItem drawerItem) {
                       //do something with clicked item

                        setContentView(R.layout.professor_scroll);
                        return false;
                    }
                })
                .build();

        // Get correct pdf to display
        building = getIntent().getStringExtra("buildingName").toLowerCase();

        setContentView(R.layout.activity_buildings);
        pdfView = findViewById(R.id.pdfView);
        pdfString = building + ".pdf";
        pdfView.fromAsset(pdfString).pages(floor).load();

        showValue = (TextView) findViewById(R.id.floor);
    }


    // Renders the current floor plan and nodes
    private void Draw(int floor) {

        NodeDao nDao = NodeDatabase.getDatabase(getApplicationContext()).nodeDao();
        VerticiesDao vDao = VerticiesDatabase.getDatabase(getApplicationContext()).VerticiesDao();

        nodes = nDao.searchBuildFloor(building);
        Drawer d = new Drawer(BuildingsActivity.this, (ArrayList<MapNode>) nodes, pdfView);

        OnDrawListener DrawL = d.createDrawListener(floor, vDao);
        OnTapListener tapL = new OnTapListener() {
            @Override
            public boolean onTap(MotionEvent e) {

                return true;
            }
        };

        pdfView.fromAsset(pdfString).pages(floor).onTap(tapL).onDraw(DrawL).load();

    }


<<<<<<< HEAD:app/src/main/java/com/example/navrpi/buildings.java
    /**
     * increases floor by 1, if not on top level
     * changes floor plan to upper floor, if not on top level
     * @param view
     */
=======
    // Up one floor
>>>>>>> 139f7fa5b51c03682bf88c42b7f9f9138def23a9:app/src/main/java/com/example/navrpi/BuildingsActivity.java
    public void Increase (View view) {

        if (floor == 5) return;
        floor++;
        showValue.setText(Integer.toString(floor+1)); //counting starts at 0...
        Draw(floor);


    }
<<<<<<< HEAD:app/src/main/java/com/example/navrpi/buildings.java

    /**
     * decreases floor by 1, if not at the bottom level
     * changes floor plan to lower floor, if not at the bottom level
     * @param view
     */
=======
    // Down one floor
>>>>>>> 139f7fa5b51c03682bf88c42b7f9f9138def23a9:app/src/main/java/com/example/navrpi/BuildingsActivity.java
    public void Decrease (View view) {

        if (floor == 0)return; //bottom floor
        floor--;
        showValue.setText(Integer.toString(floor+1)); //counting starts at 0...
        Draw(floor);

    }

    // Starts RoutePreviewActivity
    public void PreviewRoute (View view) {

        Intent intent = new Intent(BuildingsActivity.this, RoutePreviewActivity.class);
        intent.putExtra("building_name", building);
        intent.putExtra("building", building);
        startActivity(intent);

    }

    /**
     * back button to leave professor list, back to floor plans
     * @param view
     */
    public void backButton(View view){

        Intent intent = new Intent(BuildingsActivity.this, BuildingsActivity.class);
        startActivity(intent);
    }

    /**
     * opens floor of clicked professor
     * @param view
     */
    public void prof1Clicked(View view){


        setContentView(R.layout.activity_buildings);
        int profFloor = new MapNode("Walker3950550").getFloor();
        pdfView.fromAsset("walker.pdf").pages(2).enableDoubletap(false).load();
        showValue.setText(Integer.toString(2));
        Draw(floor);

    }


}
