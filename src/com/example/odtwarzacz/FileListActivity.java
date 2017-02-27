package com.example.odtwarzacz;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.*;
import android.widget.AdapterView.OnItemClickListener;
import net.sf.andpdf.pdfviewer.PdfViewerActivity;

import java.io.File;


public class FileListActivity extends ListActivity {

    private String storageState;
    private String mountedState;
    private String baseDir;

    private String[] files;

    private String[] paths;

    private String filesFolder = "/testaplikacji";

    private String activityType;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        String[] fileSuffix = getIntent().getStringArrayExtra(Constants.SUFFIX_LIST);
        activityType = getIntent().getStringExtra(Constants.ACTIVITY_TYPE);

        storageState = Environment.getExternalStorageState();
        mountedState = Environment.MEDIA_MOUNTED;
        baseDir = Environment.getExternalStorageDirectory().getAbsolutePath() + filesFolder;

        setListOfFiles(fileSuffix);

        setListAdapter(new ArrayAdapter<String>(this, R.layout.audio, files));

        ListView listView = getListView();
        listView.setTextFilterEnabled(true);

        listView.setOnItemClickListener(new OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                String clicked = (String) ((TextView) view).getText();

                if (clicked.equals(Constants.NOTHING)){

                    Toast.makeText(getApplicationContext(), "No compatible files to open!",Toast.LENGTH_SHORT).show();

                }
                else {
                    int index = -1;

                    for (int i = 0; i < files.length; i++) {
                        if (files[i].equals(clicked)) {
                            index = i;
                            break;
                        }
                    }

                    Bundle b;

                    switch (activityType) {
                        case Constants.AUDIO:
                            Intent audioIntent = new Intent(getApplicationContext(), AudioPlayerActivity.class);
                            b = new Bundle();
                            b.putString(Constants.FILE_PATH, paths[index]);
                            b.putString(Constants.FILE_NAME, files[index]);
                            audioIntent.putExtras(b);
                            startActivity(audioIntent);
                            break;

                        case Constants.PDF:
                            try {
                                Intent pdfIntent = new Intent(getApplicationContext(), PdfReaderActivity.class);
                                pdfIntent.putExtra(PdfViewerActivity.EXTRA_PDFFILENAME, paths[index]);
                                startActivity(pdfIntent);
                            }
                            catch (Exception e) {
                                e.printStackTrace();
                            }
                            break;

                        case Constants.IMAGE:
                            b = new Bundle();
                            b.putString(Constants.FILE_PATH, paths[index]);
                            Intent imageIntent = new Intent(getApplicationContext(), ImageViewerActivity.class);
                            imageIntent.putExtras(b);
                            startActivity(imageIntent);
                            break;

                    }
                }

            }
        });

    }

    private void setListOfFiles(String[] fileSuffix) {
        files = new String[] { Constants.NOTHING };
        paths = new String[] {};


        if (storageState.equals(mountedState)) {
            File dir = new File(baseDir);
            File[] file = dir.listFiles();

            paths = Utils.getPathList(fileSuffix, file);
            files = Utils.getFileList(paths);


        }
    }


}