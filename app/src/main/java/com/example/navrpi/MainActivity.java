package com.example.navrpi;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.view.View;
import com.github.barteksc.pdfviewer.PDFView;

public class MainActivity extends AppCompatActivity {

    PDFView pdfView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final Button button = findViewById(R.id.button2);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                pdfView = findViewById(R.id.pdfView);
                pdfView.fromAsset("walkerlab6000.pdf")
                        .load();
            }
        });
    }
}
