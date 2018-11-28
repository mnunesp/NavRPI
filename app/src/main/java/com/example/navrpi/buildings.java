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
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;
import android.content.Intent;
import android.support.v7.widget.Toolbar;

import com.github.barteksc.pdfviewer.PDFView;
import com.github.barteksc.pdfviewer.listener.OnDrawListener;
import com.mikepenz.materialdrawer.DrawerBuilder;
import com.mikepenz.materialdrawer.model.DividerDrawerItem;
import com.mikepenz.materialdrawer.model.PrimaryDrawerItem;
import com.mikepenz.materialdrawer.model.SecondaryDrawerItem;
import com.mikepenz.materialdrawer.model.interfaces.IDrawerItem;

import java.util.ArrayList;
import java.util.Arrays;

import static com.example.navrpi.R.layout.drawerlayout;

public class buildings extends AppCompatActivity {


    PDFView pdfView;
    TextView showValue;
    //String[] floors = {"walkerlab2000.pdf","walkerlab3000.pdf","walkerlab6000.pdf"};
    int floor = 2;
    ArrayList<MapNode> nodes = new ArrayList<>();

    //private DrawerLayout mDrawerLayout;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buildings);
        new DrawerBuilder().withActivity(this).build();

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
                        return false;
                    }
                })
                .build();




        // TODO: Query database for nodes and connections
        // Initial setup of nodes and connections. Hard coded for now
        ArrayList<MapNode> hallwayNodes = new ArrayList<>();

        MapNode node1 = new MapNode(450,200, 3, "Walker");
        MapNode node2 = new MapNode(450,550, 3, "Walker");
        MapNode node3 = new MapNode(950,550, 3, "Walker");
        MapNode node4 = new MapNode(950,650, 3, "Walker");
        MapNode node5 = new MapNode(1000,650, 3, "Walker");

        node1.addAdjacentNode(node2,1);
        node1.addAdjacentNode(node3,1);
        node2.addAdjacentNode(node3,1);
        node3.addAdjacentNode(node4,1);
        node4.addAdjacentNode(node5,1);

        node1.setNodeType("hallway");
        node2.setNodeType("hallway");
        node3.setNodeType("hallway");
        node4.setNodeType("hallway");
        node5.setNodeType("hallway");

        hallwayNodes.add(node1);
        hallwayNodes.add(node2);
        hallwayNodes.add(node3);
        hallwayNodes.add(node4);
        hallwayNodes.add(node5);


        //Bathroom nodes
        MapNode bathroomNodes[] = new MapNode[] {new MapNode(350,650, 3, "Walker")};
        bathroomNodes[0].addAdjacentNode(hallwayNodes.get(1),1);

        for (int i = 0; i < bathroomNodes.length; ++i) {
            bathroomNodes[i].setNodeType("bathroom");
        }

        nodes.addAll(hallwayNodes);
        nodes.addAll(Arrays.asList(bathroomNodes));

        //setContentView(R.layout.activity_buildings);
        pdfView = findViewById(R.id.pdfView);
        pdfView.fromAsset("walker.pdf").pages(floor).enableDoubletap(false).load();

        showValue = (TextView) findViewById(R.id.floor);
    }


    // Renders the current floor plan and nodes
    private void Draw() {

        Drawer d = new Drawer(buildings.this, nodes, pdfView);
        OnDrawListener DrawL = d.createDrawListener(floor);

        pdfView.fromAsset("walker.pdf").pages(floor).enableDoubletap(false).onDraw(DrawL).load();

    }


    //Up one floor
    public void Increase (View view) {

        if (floor == 5) return;
        floor++;
        showValue.setText(Integer.toString(floor+1)); //counting starts at 0...
        //pdfView = findViewById(R.id.pdfView);
        Draw();


    }
    //Down one floor
    public void Decrease (View view) {
        if (floor == 0)return; //bottom floor
        floor--;
        showValue.setText(Integer.toString(floor+1)); //counting starts at 0...
        //pdfView = findViewById(R.id.pdfView);
        Draw();

    }


    public void PreviewRoute (View view) {

        Intent intent = new Intent(buildings.this, RoutePreviewActivity.class);
        intent.putExtra("building_name", "walker");
        //intent.putExtra("nodes", nodes);
        startActivity(intent);


    }

}
