package com.example.odtwarzacz;

import org.junit.Before;
import org.junit.Test;

import java.io.File;

import static org.junit.Assert.*;


public class UtilsTest {


    private File[] file;
    private String[] fileStrings;
    private String[] suffix1 = new String[]{ ".jpg", ".png" };
    private String[] suffix2 = new String[]{ ".mp3" };
    private String[] suffix3 = new String[]{ ".ogg" };


    @Before
    public void setUp() throws Exception {
        fileStrings = new String[]{"/directory/audiofile.mp3", "/directory/image1.png", "/directory/image2.jpg",
                "/directory/pdffile.pdf", "/directory/otherfile.zip" };
        file = new File[fileStrings.length];
        for (int i = 0; i < fileStrings.length; i++) {
            file[i] = new File(fileStrings[i]);
        }
    }

    @Test
    public void getFileList() throws Exception {
        String[] files = new String[] {"audiofile.mp3", "image1.png", "image2.jpg",
                "pdffile.pdf", "otherfile.zip" };
        assertArrayEquals(files, Utils.getFileList(fileStrings));
    }

    @Test
    public void getPathList() throws Exception {
        String[] test1 = new String[]{ "\\directory\\image1.png", "\\directory\\image2.jpg" };
        assertArrayEquals(test1, Utils.getPathList(suffix1, file));

        String[] test2 = new String[]{ "\\directory\\audiofile.mp3" };
        assertArrayEquals(test2, Utils.getPathList(suffix2, file));

        String[] test3 = new String[]{};
        assertArrayEquals(test3, Utils.getPathList(suffix3, file));
    }

}