package com.example.odtwarzacz;

import java.io.File;

class Utils {

    static String[] getFileList(String[] paths){
        String[] files;
        if (paths.length > 0){
            files = new String[paths.length];
            for (int i = 0; i < paths.length; i++){
                String[] helper = paths[i].split("/");
                files[i] = helper[helper.length - 1];
            }
        }
        else {
            files = new String[] { Constants.NOTHING };
        }
        return files;
    }

    static String[] getPathList(String[] fileSuffix, File[] file) {
        int actualFiles = 0;
        String[] paths;

        if (file.length > 0) {
            for (File aFile : file) {
                for (String suffix : fileSuffix) {
                    if (aFile.getPath().endsWith(suffix)) {
                        actualFiles++;
                    }
                }
            }

            paths = new String[actualFiles];
            int i = 0;
            for (File f : file) {
                for (String suffix : fileSuffix) {
                    if (f.getPath().endsWith(suffix)) {
                        paths[i] = f.getPath();
                        i++;
                    }
                }
            }
        }
        else {
            paths = new String[]{};
        }

        return paths;
    }

}
