package com.flurry.sdk;

import android.content.Context;
import android.os.Build;
import android.os.Environment;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;

/* JADX INFO: loaded from: classes.dex */
public final class ho {
    private static String a = ho.class.getSimpleName();

    public static File a(boolean z) {
        File externalFilesDir = null;
        Context contextC = fp.a().c();
        if (z && "mounted".equals(Environment.getExternalStorageState()) && (Build.VERSION.SDK_INT >= 19 || contextC.checkCallingOrSelfPermission("android.permission.WRITE_EXTERNAL_STORAGE") == 0)) {
            externalFilesDir = contextC.getExternalFilesDir(null);
        }
        if (externalFilesDir == null) {
            return contextC.getFilesDir();
        }
        return externalFilesDir;
    }

    public static File b(boolean z) {
        Context contextC = fp.a().c();
        File externalCacheDir = null;
        if (z && "mounted".equals(Environment.getExternalStorageState()) && (Build.VERSION.SDK_INT >= 19 || contextC.checkCallingOrSelfPermission("android.permission.WRITE_EXTERNAL_STORAGE") == 0)) {
            externalCacheDir = contextC.getExternalCacheDir();
        }
        if (externalCacheDir == null) {
            return contextC.getCacheDir();
        }
        return externalCacheDir;
    }

    public static boolean a(File file) {
        if (file == null || file.getAbsoluteFile() == null) {
            return false;
        }
        File parentFile = file.getParentFile();
        if (parentFile == null) {
            return true;
        }
        if (parentFile.mkdirs() || parentFile.isDirectory()) {
            return true;
        }
        gd.a(6, a, "Unable to create persistent dir: " + parentFile);
        return false;
    }

    public static boolean b(File file) {
        if (file != null && file.isDirectory()) {
            for (String str : file.list()) {
                if (!b(new File(file, str))) {
                    return false;
                }
            }
        }
        return file.delete();
    }

    @Deprecated
    public static String c(File file) throws Throwable {
        FileInputStream fileInputStream;
        Throwable th;
        StringBuilder sb;
        if (file == null || !file.exists()) {
            gd.a(4, a, "Persistent file doesn't exist.");
            return null;
        }
        gd.a(4, a, "Loading persistent data: " + file.getAbsolutePath());
        try {
            fileInputStream = new FileInputStream(file);
            try {
                try {
                    sb = new StringBuilder();
                    byte[] bArr = new byte[1024];
                    while (true) {
                        int i = fileInputStream.read(bArr);
                        if (i <= 0) {
                            break;
                        }
                        sb.append(new String(bArr, 0, i));
                    }
                    hp.a(fileInputStream);
                } catch (Throwable th2) {
                    th = th2;
                    gd.a(6, a, "Error when loading persistent file", th);
                    hp.a(fileInputStream);
                    sb = null;
                }
            } catch (Throwable th3) {
                th = th3;
                hp.a(fileInputStream);
                throw th;
            }
        } catch (Throwable th4) {
            fileInputStream = null;
            th = th4;
            hp.a(fileInputStream);
            throw th;
        }
        if (sb != null) {
            return sb.toString();
        }
        return null;
    }

    @Deprecated
    public static void a(File file, String str) throws Throwable {
        FileOutputStream fileOutputStream;
        if (file == null) {
            gd.a(4, a, "No persistent file specified.");
            return;
        }
        if (str == null) {
            gd.a(4, a, "No data specified; deleting persistent file: " + file.getAbsolutePath());
            file.delete();
            return;
        }
        gd.a(4, a, "Writing persistent data: " + file.getAbsolutePath());
        try {
            fileOutputStream = new FileOutputStream(file);
            try {
                try {
                    fileOutputStream.write(str.getBytes());
                    hp.a(fileOutputStream);
                } catch (Throwable th) {
                    th = th;
                    gd.a(6, a, "Error writing persistent file", th);
                    hp.a(fileOutputStream);
                }
            } catch (Throwable th2) {
                th = th2;
                hp.a(fileOutputStream);
                throw th;
            }
        } catch (Throwable th3) {
            th = th3;
            fileOutputStream = null;
            hp.a(fileOutputStream);
            throw th;
        }
    }
}
