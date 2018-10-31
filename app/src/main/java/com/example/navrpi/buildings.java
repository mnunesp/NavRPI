package com.example.navrpi;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Picture;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.github.barteksc.pdfviewer.PDFView;
import com.github.barteksc.pdfviewer.listener.OnDrawListener;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Map;

public class buildings extends AppCompatActivity {


    PDFView pdfView;
    TextView showValue;
    //String[] floors = {"walkerlab2000.pdf","walkerlab3000.pdf","walkerlab6000.pdf"};
    int floor = 2;
    ArrayList<MapNode> nodes = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buildings);

        // TODO: Query database for nodes and connections
        // Initial setup of nodes and connections. Hard coded for now
        MapNode hallwayNodes[] = new MapNode[] {new MapNode(450,200, 3, "Walker"), new MapNode(450,550, 3, "Walker"),
                new MapNode(950,550, 3, "Walker"), new MapNode(950,650, 3, "Walker"),
                new MapNode(1000,650, 3, "Walker")};

        for (int i = 0; i < hallwayNodes.length; ++i) {
            hallwayNodes[i].setNodeType("hallway");
            if (i != hallwayNodes.length -1)
                hallwayNodes[i].addAdjacentNode(hallwayNodes[i+1]);

        }

        //Bathroom nodes
        MapNode bathroomNodes[] = new MapNode[] {new MapNode(350,650, 3, "Walker")};
        bathroomNodes[0].addAdjacentNode(hallwayNodes[1]);

        for (int i = 0; i < bathroomNodes.length; ++i) {
            bathroomNodes[i].setNodeType("bathroom");
        }

        nodes.addAll(Arrays.asList(hallwayNodes));
        nodes.addAll(Arrays.asList(bathroomNodes));

        showValue = (TextView) findViewById(R.id.floor);

    }


    private void Draw() {

        OnDrawListener DrawL = new OnDrawListener() {
            @Override
            public void onLayerDrawn(Canvas canvas, float pageWidth, float pageHeight, int displayedPage) {
                System.out.println("Testing");
                System.out.println("PageWidth: " + pageWidth + " pageHeight: " + pageHeight + " Page: " + displayedPage);
                Paint dotcolor = new Paint();
                Paint linecolor = new Paint();

                dotcolor.setColor(Color.RED);
                linecolor.setColor(Color.RED);
                linecolor.setStrokeWidth(10);

                // Draw each node
                for (int i = 0; i < nodes.size(); ++i) {
                    if (nodes.get(i).getFloor()==floor) {

                        // Calculate node positions based on current zoom
                        float pdfzoom = pdfView.getZoom();

                        float nodedrawpositionx = nodes.get(i).getX() * pdfzoom;
                        float nodedrawpositiony = nodes.get(i).getY() * pdfzoom;
                        float noderadius = 20;


                        // TODO: Move all this to separate function
                        // Draw icons based on node type
                        if (nodes.get(i).getNodeType().equals("hallway")) {
                            canvas.drawCircle(nodedrawpositionx, nodedrawpositiony, noderadius, dotcolor);

                        } else if (nodes.get(i).getNodeType().equals("bathroom")) {
                            Bitmap temp_bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.restroom_icon_red);
                            // icon 100px x 100px, -50 to center
                            canvas.drawBitmap(temp_bitmap, nodedrawpositionx - 50, nodedrawpositiony - 50, dotcolor);
                        }

                        // Draw lines between connecting nodes
                        ArrayList<MapNode> adjacentNodes = nodes.get(i).getAdjacentNodes();
                        for (int q = 0; q < nodes.get(i).getNumAdjacent(); ++q) {

                            float adjacentposx = adjacentNodes.get(q).getX() * pdfzoom;
                            float adjacentposy = adjacentNodes.get(q).getY() * pdfzoom;

                            canvas.drawLine(nodedrawpositionx, nodedrawpositiony, adjacentposx, adjacentposy, linecolor);
                        }

                    }
                }


            }
        };


        pdfView.fromAsset("walker.pdf").pages(floor).enableDoubletap(false).onDraw(DrawL).load();

    }


    //Up one floor
    public void Increase (View view) {

        if (floor == 5) return;
        floor++;
        showValue.setText(Integer.toString(floor+1)); //counting starts at 0...
        pdfView = findViewById(R.id.pdfView);
        Draw();


    }
    //Down one floor
    public void Decrease (View view) {
        if (floor == 0)return; //bottom floor
        floor--;
        showValue.setText(Integer.toString(floor+1)); //counting starts at 0...
        pdfView = findViewById(R.id.pdfView);
        Draw();

    }




//            buttons[i].setOnClickListener(new View.OnClickListener() {
//                public void onClick(View v) {
//                    String current_floor = floors[j];
//                    pdfView = findViewById(R.id.pdfView);
//                    pdfView.fromAsset("walker.pdf").pages(j)
//                            .load();
//                }
//            });
//    }
}
