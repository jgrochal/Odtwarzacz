package com.example.odtwarzacz;


import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.widget.ImageView;


public class ImageViewerActivity extends Activity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.image);

        String imageFilePath = getIntent().getStringExtra(Constants.FILE_PATH);

        Bitmap myBitmap = BitmapFactory.decodeFile(imageFilePath);

        ImageView image = (ImageView) findViewById(R.id.image);
        image.setImageBitmap(myBitmap);

    }
}