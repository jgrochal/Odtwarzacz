package com.example.odtwarzacz;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends Activity {


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        findViewById(R.id.audioButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent audioIntent = new Intent(getApplicationContext(),AudioActivity.class);
                startActivity(audioIntent);
            }
        });

        findViewById(R.id.videoButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent videoIntent = new Intent(getApplicationContext(),VideoActivity.class);
                startActivity(videoIntent);
            }
        });

        findViewById(R.id.pdfButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent pdfIntent = new Intent(getApplicationContext(),PdfActivity.class);
                startActivity(pdfIntent);
            }
        });

        findViewById(R.id.imageButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent imageIntent = new Intent(getApplicationContext(),ImageActivity.class);
                startActivity(imageIntent);
            }
        });
    }

}
