package com.example.navrpi;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.view.View;
import android.widget.TextView;

import com.github.barteksc.pdfviewer.PDFView;

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
        if (floor == 5) return;  //top floor
        floor++;
        showValue.setText(Integer.toString(floor+1)); //counting starts at 0...
        pdfView = findViewById(R.id.pdfView);
        pdfView.fromAsset("walker.pdf").pages(floor).load(); //displays PDF of current floor

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
