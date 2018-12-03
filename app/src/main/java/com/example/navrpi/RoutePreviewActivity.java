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
import java.util.List;

public class RoutePreviewActivity extends AppCompatActivity {

    String building = "";
    int floor = 0;

    NodeDao nDao;
    VerticiesDao vDao;
    PDFView pdfView;

    ArrayList<RoutingMapNode> routenodes = new ArrayList<>();
    ArrayList<MapNode> finalnodes = new ArrayList<>();



    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_route_preview_activity);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });


        pdfView = findViewById(R.id.pdfView);
        building = this.getIntent().getStringExtra("building");



        nDao = NodeDatabase.getDatabase(getApplicationContext()).nodeDao();
        vDao = VerticiesDatabase.getDatabase(getApplicationContext()).VerticiesDao();

        List<MapNode> dbnodes = nDao.getNodesByBuilding(building);
        List<Verticies> dbverts = vDao.getAllEdges();



        // Convert MapNodes into routing node for BuildingNavigator
        List<RoutingMapNode> mapNodes = new ArrayList<>();
        for (MapNode n : dbnodes) {
            mapNodes.add(new RoutingMapNode(n.getX(), n.getY(), n.getFloor(),
                    n.getBuilding(), n.getNodeType()));
        }

        // Add verticies to the nodes in mapNodes
        for (Verticies v : dbverts) {
            RoutingMapNode tempsource = new RoutingMapNode(v.getSource());
            RoutingMapNode tempdest = new RoutingMapNode(v.getDest());

            if (mapNodes.contains(tempsource) && mapNodes.contains(tempdest)) {
                mapNodes.get(mapNodes.indexOf(tempsource)).addAdjacentNode(mapNodes.get(mapNodes.indexOf(tempdest)), v.getDistance());
            }
        }

        // Define start and destination nodes
        RoutingMapNode startNode = mapNodes.get(5);
        RoutingMapNode endNode = mapNodes.get(28);


        for (RoutingMapNode n : mapNodes) {
            System.out.println("Node " + n.getId() + " adjnodes: " + n.getAdjacentNodes().size());
        }

        // Perform navigation from start node to all nodes
        BuildingNavigator buildingNav = new BuildingNavigator();
        buildingNav.Navigate(startNode);

        // Get final node path for display
        routenodes.addAll(endNode.getShortestPath());

        for (RoutingMapNode n : routenodes) {
            finalnodes.add(new MapNode(n.getX(), n.getY(), n.getFloor(),
                    n.getBuilding(), n.getNodeType()));
        }

        

        // Draw the node path to pdfview
        Draw(floor);
    }


    private void Draw(int floor) {

        Drawer d = new Drawer(RoutePreviewActivity.this, finalnodes, pdfView);
        OnDrawListener DrawL = d.createDrawListener(floor, vDao);

        pdfView.fromAsset("walker.pdf").pages(floor).enableDoubletap(false).onDraw(DrawL).load();

    }

    //Up one floor
    public void Increase (View view) {

        if (floor == 5) return;
        floor++;
        Draw(floor);


    }
    //Down one floor
    public void Decrease (View view) {
        if (floor == 0)return; //bottom floor
        floor--;
        Draw(floor);

    }


}
