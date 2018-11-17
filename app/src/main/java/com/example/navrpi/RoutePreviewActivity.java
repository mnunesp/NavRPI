package com.example.navrpi;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.github.barteksc.pdfviewer.PDFView;
import com.github.barteksc.pdfviewer.listener.OnDrawListener;

import java.util.ArrayList;

public class RoutePreviewActivity extends AppCompatActivity {

    PDFView pdfView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_route_preview_activity);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });


        //ArrayList<MapNode> routenodes = (ArrayList<MapNode>) getIntent().getSerializableExtra("nodes");

        ArrayList<MapNode> mapnodes = new ArrayList<>();
        /*
        // TODO: Replace with DB lookup
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

        mapnodes.add(node1);
        mapnodes.add(node2);
        mapnodes.add(node3);
        mapnodes.add(node4);
        mapnodes.add(node5);

        for (int i = 0; i < mapnodes.size(); ++i) {
            mapnodes.get(i).setNodeType("hallway");
        }



        BuildingNavigator buildingNav = new BuildingNavigator();
        buildingNav.Navigate(node1);




        ArrayList<MapNode> routenodes = new ArrayList<>();
        routenodes.addAll(node5.getShortestPath());

        pdfView = findViewById(R.id.pdfView);
        Drawer d = new Drawer(RoutePreviewActivity.this, routenodes, pdfView);
        OnDrawListener DrawL = d.createDrawListener(3);


        pdfView.fromAsset("walker.pdf").pages(3).enableDoubletap(false).onDraw(DrawL).load();*/
    }


}
