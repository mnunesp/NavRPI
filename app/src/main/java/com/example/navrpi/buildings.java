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
    //String[] floors = {"walkerlab2000.pdf","walkerlab3000.pdf","walkerlab6000.pdf"};
    int floor = 2;
    List<MapNode> nodes = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buildings);


        //MapNode startNodes[] = new MapNode[] {new MapNode(525,250, 3, "Walker"), new MapNode(525,625, 3, "Walker"),
          //      new MapNode(1025,625, 3, "Walker"),new MapNode(665,350, 2, "Walker"),
            //    new MapNode(665,650, 2, "Walker"), new MapNode(1100,650, 2, "Walker")};
        //nodes.addAll(Arrays.asList(startNodes));
        NodeDao nDao = NodeDatabase.getDatabase(getApplicationContext()).nodeDao();
        //for (int i = 0; i < 6; i++) nDao.insert(startNodes[i]);
        List<MapNode> databaseNodes = nDao.searchBuildFloor("Walker");

        // Initial setup of nodes and connections. Hard coded for now
        ArrayList<MapNode> hallwayNodes = new ArrayList<>();




        //Bathroom nodes
        MapNode bathroomNodes[] = new MapNode[] {new MapNode(350,650, 3, "Walker")};
        bathroomNodes[0].addAdjacentNode(hallwayNodes.get(1),1);

        for (int i = 0; i < bathroomNodes.length; ++i) {
            bathroomNodes[i].setNodeType("bathroom");
        }

        nodes.addAll(hallwayNodes);
        nodes.addAll(Arrays.asList(bathroomNodes));

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
