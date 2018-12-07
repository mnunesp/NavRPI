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
    private PDFView pdfView;

    public Drawer(Context c, PDFView p) {
        context = c;
        nodes = new ArrayList<MapNode>();
        verts = new ArrayList<Verticies>();
        pdfView = p;
    }


    public Drawer(Context c, ArrayList<? extends MapNode> n, PDFView p) {
        context = c;
        nodes = n;
        pdfView = p;
        verts = new ArrayList<Verticies>();

    }


    public OnDrawListener createDrawListener(final int floor, final VerticiesDao vDao) {


        OnDrawListener DrawL = new OnDrawListener() {
            @Override
            public void onLayerDrawn(Canvas canvas, float pageWidth, float pageHeight, int displayedPage) {
                //System.out.println("Testing");
                //System.out.println("PageWidth: " + pageWidth + " pageHeight: " + pageHeight + " Page: " + displayedPage);
                Paint dotColor = new Paint();
                Paint lineColor = new Paint();

                dotColor.setColor(Color.RED);
                lineColor.setColor(Color.RED);
                lineColor.setStrokeWidth(10);

                // Draw each node
                for (int i = 0; i < nodes.size(); ++i) {
                    if (nodes.get(i).getFloor() == floor) {

                        // Calculate node positions based on current zoom
                        float pdfZoom = pdfView.getZoom();

                        float nodeDrawPositionX = nodes.get(i).getX() * pdfZoom;
                        float nodeDrawPositionY = nodes.get(i).getY() * pdfZoom;
                        float nodeRadius = 20;



                        // Draw icons based on node type
                        if (nodes.get(i).getNodeType().equals("hallway")) {
                            canvas.drawCircle(nodeDrawPositionX, nodeDrawPositionY, nodeRadius, dotColor);

                        } else if (nodes.get(i).getNodeType().equals("bathroom")) {
                            Bitmap tempBitmap = BitmapFactory.decodeResource(context.getResources(), R.drawable.restroom_icon_red);
                            // icon 100px x 100px, -50 to center
                            canvas.drawBitmap(tempBitmap, nodeDrawPositionX - 50, nodeDrawPositionY - 50, dotColor);
                        }

                        // Draw lines between connecting nodes
                        verts = vDao.getAllEdges();


                        for (Verticies vert : verts) {

                            MapNode tempSource = new MapNode(vert.getSource());
                            MapNode tempDest = new MapNode(vert.getDest());

                            // Only display vert if dest node is on same floor
                            if (nodes.get(i).equals(tempSource) && nodes.contains(tempDest) &&
                                    nodes.get(nodes.indexOf(tempDest)).getFloor() == floor) {

                                float adjacentPosX = nodes.get(nodes.indexOf(tempDest)).getX() * pdfZoom;
                                float adjacentPosY = nodes.get(nodes.indexOf(tempDest)).getY() * pdfZoom;
                                System.out.println("xpos: " + adjacentPosX + " ypos: " + adjacentPosY);
                                canvas.drawLine(nodeDrawPositionX, nodeDrawPositionY, adjacentPosX, adjacentPosY, lineColor);
                            }

                        }

                    }
                }


            }
        };

        return DrawL;
    }
}
