package com.example.navrpi;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Button;

import com.github.barteksc.pdfviewer.PDFView;

public class buildings extends AppCompatActivity {

    PDFView pdfView;
    TextView showValue;
    //String[] floors = {"walkerlab2000.pdf","walkerlab3000.pdf","walkerlab6000.pdf"};
    int floor = 2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buildings);

        showValue = (TextView) findViewById(R.id.floor);
    }


    public void Increase (View view) {
        if (floor == 5) return;
        floor++;
        showValue.setText(Integer.toString(floor+1)); //counting starts at 0...
        pdfView = findViewById(R.id.pdfView);
        pdfView.fromAsset("walker.pdf").pages(floor).load();

    }
    public void Decrease (View view) {
        if (floor == 0)return;
        floor--;
        showValue.setText(Integer.toString(floor+1));
        pdfView = findViewById(R.id.pdfView);
        pdfView.fromAsset("walker.pdf").pages(floor).load();
    }
}
