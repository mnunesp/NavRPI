package com.example.navrpi;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.content.Context;

import com.github.barteksc.pdfviewer.PDFView;
import com.github.barteksc.pdfviewer.listener.OnDrawListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Drawer {

    private ArrayList<MapNode> nodes;
    private Context context;
    private PDFView pview;

    public Drawer(Context c, PDFView p) {
        context = c;
        nodes = new ArrayList<MapNode>();
        pview = p;
    }

    public Drawer(Context c, ArrayList<MapNode> n, PDFView p) {
        context = c;
        nodes = n;
        pview = p;
    }


    public OnDrawListener createDrawListener(final int floor) {


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
                    if (nodes.get(i).getFloor() == floor) {

                        // Calculate node positions based on current zoom
                        float pdfzoom = pview.getZoom();

                        float nodedrawpositionx = nodes.get(i).getX() * pdfzoom;
                        float nodedrawpositiony = nodes.get(i).getY() * pdfzoom;
                        float noderadius = 20;


                        // TODO: Move all this to separate function
                        // Draw icons based on node type
                        if (nodes.get(i).getNodeType().equals("hallway")) {
                            canvas.drawCircle(nodedrawpositionx, nodedrawpositiony, noderadius, dotcolor);

                        } else if (nodes.get(i).getNodeType().equals("bathroom")) {
                            Bitmap temp_bitmap = BitmapFactory.decodeResource(context.getResources(), R.drawable.restroom_icon_red);
                            // icon 100px x 100px, -50 to center
                            canvas.drawBitmap(temp_bitmap, nodedrawpositionx - 50, nodedrawpositiony - 50, dotcolor);
                        }

                        // Draw lines between connecting nodes
                        //HashMap<MapNode, Integer> adjacentNodes = nodes.get(i).getAdjacentNodes();
                        HashMap<MapNode, Integer> adjacentNodes = new HashMap<MapNode,Integer>();
                        for (MapNode key : adjacentNodes.keySet()) {

                            if (nodes.contains(key) && key.getFloor() == floor) {
                                float adjacentposx = key.getX() * pdfzoom;
                                float adjacentposy = key.getY() * pdfzoom;
                                canvas.drawLine(nodedrawpositionx, nodedrawpositiony, adjacentposx, adjacentposy, linecolor);
                            }



                        }

                    }
                }


            }
        };

        return DrawL;
    }
}
