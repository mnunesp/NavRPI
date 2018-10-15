package com.example.navrpi;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.view.View;
import android.widget.TextView;

import com.github.barteksc.pdfviewer.PDFView;
import com.github.barteksc.pdfviewer.listener.OnDrawListener;

public class MainActivity extends AppCompatActivity {

    PDFView pdfView;
    TextView showValue;
    //String[] floors = {"walkerlab2000.pdf","walkerlab3000.pdf","walkerlab6000.pdf"};
    int floor = 2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        showValue = (TextView) findViewById(R.id.floor);

    }
    //Up one floor
    public void Increase (View view) {

        OnDrawListener x = new OnDrawListener() {
            @Override
            public void onLayerDrawn(Canvas canvas, float pageWidth, float pageHeight, int displayedPage) {
                Paint p = new Paint();
                p.setARGB(255, 255, 0, 0);
                canvas.drawCircle(20, 20,50, p);
            }
        };

        if (floor == 5) return;
        floor++;
        showValue.setText(Integer.toString(floor+1)); //counting starts at 0...
        pdfView = findViewById(R.id.pdfView);
        pdfView.fromAsset("walker.pdf").pages(floor).onDraw(x).load();

    }
    //Down one floor
    public void Decrease (View view) {
        if (floor == 0)return; //bottom floor
        floor--;
        showValue.setText(Integer.toString(floor+1)); //counting starts at 0...
        pdfView = findViewById(R.id.pdfView);
        pdfView.fromAsset("walker.pdf").pages(floor).load();
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
