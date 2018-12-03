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
import java.util.List;
import java.util.Map;

public class Drawer {

    private ArrayList<? extends MapNode> nodes;
    private List<Verticies> verts;
    private Context context;
    private PDFView pview;

    public Drawer(Context c, PDFView p) {
        context = c;
        nodes = new ArrayList<MapNode>();
        verts = new ArrayList<Verticies>();
        pview = p;
    }

    public Drawer(Context c, ArrayList<? extends MapNode> n, PDFView p) {
        context = c;
        nodes = n;
        pview = p;
        verts = new ArrayList<Verticies>();

    }


    public OnDrawListener createDrawListener(final int floor, final VerticiesDao vDao) {


        OnDrawListener DrawL = new OnDrawListener() {
            @Override
            public void onLayerDrawn(Canvas canvas, float pageWidth, float pageHeight, int displayedPage) {
                //System.out.println("Testing");
                //System.out.println("PageWidth: " + pageWidth + " pageHeight: " + pageHeight + " Page: " + displayedPage);
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



                        // Draw icons based on node type
                        if (nodes.get(i).getNodeType().equals("hallway")) {
                            canvas.drawCircle(nodedrawpositionx, nodedrawpositiony, noderadius, dotcolor);

                        } else if (nodes.get(i).getNodeType().equals("bathroom")) {
                            Bitmap temp_bitmap = BitmapFactory.decodeResource(context.getResources(), R.drawable.restroom_icon_red);
                            // icon 100px x 100px, -50 to center
                            canvas.drawBitmap(temp_bitmap, nodedrawpositionx - 50, nodedrawpositiony - 50, dotcolor);
                        }

                        // Draw lines between connecting nodes
                        verts = vDao.getAllEdges();


                        for (Verticies vert : verts) {

                            MapNode tempsource = new MapNode(vert.getSource());
                            MapNode tempdest = new MapNode(vert.getDest());

                            // Only display vert if dest node is on same floor
                            if (nodes.get(i).equals(tempsource) && nodes.contains(tempdest) &&
                                    nodes.get(nodes.indexOf(tempdest)).getFloor() == floor) {

                                float adjacentposx = nodes.get(nodes.indexOf(tempdest)).getX() * pdfzoom;
                                float adjacentposy = nodes.get(nodes.indexOf(tempdest)).getY() * pdfzoom;
                                System.out.println("xpos: " + adjacentposx + " ypos: " + adjacentposy);
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
