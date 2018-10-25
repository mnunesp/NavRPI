package com.example.navrpi;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.github.barteksc.pdfviewer.PDFView;
import com.github.barteksc.pdfviewer.listener.OnDrawListener;

import java.util.ArrayList;
import java.util.Arrays;

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

        MapNode startNodes[] = new MapNode[] {new MapNode(525,250, 3, "Walker"), new MapNode(525,625, 3, "Walker"),
                new MapNode(1025,625, 3, "Walker"),new MapNode(665,350, 2, "Walker"),
                new MapNode(665,650, 2, "Walker"), new MapNode(1100,650, 2, "Walker")};
        nodes.addAll(Arrays.asList(startNodes));

        showValue = (TextView) findViewById(R.id.floor);

    }


    private void Draw() {

        OnDrawListener DrawL = new OnDrawListener() {
            @Override
            public void onLayerDrawn(Canvas canvas, float pageWidth, float pageHeight, int displayedPage) {
                System.out.println("Testing");
                System.out.println("PageWidth: " + pageWidth + " pageHeight: " + pageHeight + " Page: " + displayedPage);
                Paint p = new Paint();
                p.setColor(Color.RED);


                for (int i = 0; i < nodes.size(); ++i) {
                    if (nodes.get(i).getFloor()==floor) {

                        System.out.println("pdfView PositionOffSet: " + pdfView.getPositionOffset());
                        System.out.println("CurrentXOffset: " + pdfView.getCurrentXOffset() +
                                " CurrentYOffset: " + pdfView.getCurrentYOffset());

                        float pdfzoom = pdfView.getZoom();

                        float nodedrawpositionx = nodes.get(i).getX() * pdfzoom;
                        float nodedrawpositiony = nodes.get(i).getY() * pdfzoom;
                        float noderadius = 20;

                        System.out.println("Zoom: " + pdfView.getZoom());
                        System.out.println("NodeXPos: " + nodedrawpositionx + " NodeYPos: " + nodedrawpositiony);
                        System.out.println("Dist to Right: " + (pageWidth - nodedrawpositionx) + " Dist to Bottom: " + (pageHeight - nodedrawpositiony));

                        canvas.drawCircle(nodedrawpositionx, nodedrawpositiony, noderadius, p);
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
