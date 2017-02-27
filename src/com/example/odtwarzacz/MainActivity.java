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
                Bundle b = new Bundle();
                b.putString(Constants.ACTIVITY_TYPE, Constants.AUDIO);
                b.putStringArray(Constants.SUFFIX_LIST, new String[]{ ".mp3" });
                Intent audioIntent = new Intent(getApplicationContext(),FileListActivity.class);
                audioIntent.putExtras(b);
                startActivity(audioIntent);
            }
        });

        findViewById(R.id.videoButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Bundle b = new Bundle();
                b.putString(Constants.ACTIVITY_TYPE, Constants.VIDEO);
                b.putStringArray(Constants.SUFFIX_LIST, new String[]{ "none" });
                Intent videoIntent = new Intent(getApplicationContext(),FileListActivity.class);

                videoIntent.putExtras(b);
//                startActivity(videoIntent);
            }
        });

        findViewById(R.id.pdfButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Bundle b = new Bundle();
                b.putString(Constants.ACTIVITY_TYPE, Constants.PDF);
                b.putStringArray(Constants.SUFFIX_LIST, new String[]{ ".pdf" });
                Intent pdfIntent = new Intent(getApplicationContext(),FileListActivity.class);
                pdfIntent.putExtras(b);
                startActivity(pdfIntent);
            }
        });

        findViewById(R.id.imageButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Bundle b = new Bundle();
                b.putString(Constants.ACTIVITY_TYPE, Constants.IMAGE);
                b.putStringArray(Constants.SUFFIX_LIST, new String[]{ ".png", ".jpg" });
                Intent imageIntent = new Intent(getApplicationContext(),FileListActivity.class);
                imageIntent.putExtras(b);
                startActivity(imageIntent);
            }
        });
    }

}
