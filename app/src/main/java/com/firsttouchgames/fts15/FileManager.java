package com.firsttouchgames.fts15;

import android.app.Activity;
import android.app.Application;
import java.io.File;
import java.io.FilenameFilter;

/* JADX INFO: loaded from: classes.dex */
public class FileManager {
    private static final String LOG_TAG = "FileManager";
    private static String[] listFiles;
    private static Activity mActivity;

    public static void Initialise(Activity activity) {
        mActivity = activity;
    }

    public static int GetFilesWithExtension(String extension) {
        File externalDir = mActivity.getExternalFilesDir(null);
        GenericExtFilter filter = new GenericExtFilter(extension);
        listFiles = null;
        listFiles = externalDir.list(filter);
        int iFilesCount = listFiles.length;
        return iFilesCount;
    }

    public static String GetFileNameWithExtension(String extension, int iNumber) {
        return listFiles[iNumber];
    }

    public static class GenericExtFilter implements FilenameFilter {
        private String ext;

        public GenericExtFilter(String ext) {
            this.ext = ext;
        }

        @Override // java.io.FilenameFilter
        public boolean accept(File dir, String name) {
            return name.endsWith(this.ext);
        }
    }

    public static void RenameFile(String sOldFilename, String sNewFilename) {
        Application app = mActivity.getApplication();
        File externalDir = app.getExternalFilesDir(null);
        if (externalDir == null) {
            externalDir = app.getFilesDir();
        }
        String externalDirectory = externalDir.toString();
        File oldFile = new File(externalDirectory + "/" + sOldFilename);
        if (oldFile.exists()) {
            File newFile = new File(externalDirectory + "/" + sNewFilename);
            oldFile.renameTo(newFile);
        }
    }
}
