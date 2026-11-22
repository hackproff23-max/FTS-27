package com.firsttouchgames.util;

import android.util.Log;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Enumeration;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

/* JADX INFO: loaded from: classes.dex */
public class ZipHelper {
    static boolean zipError = false;

    public static boolean isZipError() {
        return zipError;
    }

    public static void setZipError(boolean zipError2) {
        zipError = zipError2;
    }

    public static void unzip(String archive, File outputDir) {
        try {
            Log.d("control", "ZipHelper.unzip() - File: " + archive);
            ZipFile zipfile = new ZipFile(archive);
            Enumeration<? extends ZipEntry> e = zipfile.entries();
            while (e.hasMoreElements()) {
                ZipEntry entry = e.nextElement();
                unzipEntry(zipfile, entry, outputDir);
            }
        } catch (Exception e2) {
            Log.d("control", "ZipHelper.unzip() - Error extracting file " + archive + ": " + e2);
            setZipError(true);
        }
    }

    public static long copyLarge(InputStream input, OutputStream output) throws IOException {
        byte[] buffer = new byte[4096];
        long count = 0;
        while (true) {
            int n = input.read(buffer);
            if (-1 != n) {
                output.write(buffer, 0, n);
                count += (long) n;
            } else {
                return count;
            }
        }
    }

    private static void unzipEntry(ZipFile zipfile, ZipEntry entry, File outputDir) throws IOException {
        if (entry.isDirectory()) {
            createDirectory(new File(outputDir, entry.getName()));
            return;
        }
        File outputFile = new File(outputDir, entry.getName());
        if (!outputFile.getParentFile().exists()) {
            createDirectory(outputFile.getParentFile());
        }
        Log.d("control", "ZipHelper.unzipEntry() - Extracting: " + entry);
        BufferedInputStream inputStream = new BufferedInputStream(zipfile.getInputStream(entry));
        BufferedOutputStream outputStream = new BufferedOutputStream(new FileOutputStream(outputFile));
        try {
            copyLarge(inputStream, outputStream);
        } catch (Exception e) {
            Log.d("control", "ZipHelper.unzipEntry() - Error: " + e);
            setZipError(true);
        } finally {
            outputStream.close();
            inputStream.close();
        }
    }

    private static void createDirectory(File dir) {
        Log.d("control", "ZipHelper.createDir() - Creating directory: " + dir.getName());
        if (!dir.exists()) {
            if (!dir.mkdirs()) {
                throw new RuntimeException("Can't create directory " + dir);
            }
        } else {
            Log.d("control", "ZipHelper.createDir() - Exists directory: " + dir.getName());
        }
    }
}
