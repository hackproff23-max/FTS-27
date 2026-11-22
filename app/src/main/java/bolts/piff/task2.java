package bolts.piff;

import android.content.Context;
import android.content.res.AssetManager;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

/* JADX INFO: renamed from: bolts.piff.task€€€€2, reason: invalid class name */
/* JADX INFO: compiled from: SavesRestoringFiles.java */
/* JADX INFO: loaded from: classes.dex */
public class task2 {
    private static String path = "data/Gamein/datos2/";
    private static String tr3 = "";
    private static String y2 = "datos.data.dat";
    private static String yt1 = "";

    public static void DoSmth(Context context) {
        for (int i = 0; i < 3; i++) {
            if (r(i, context)) {
                if (i != 2) {
                    bDR(i, context);
                } else if (!kytt(context)) {
                    bDR(i, context);
                }
            }
        }
    }

    private static void bDR(int i, Context context) {
        try {
            ZipInputStream zipInputStream = new ZipInputStream(new BufferedInputStream(is(i, context)));
            byte[] bArr = new byte[8192];
            while (true) {
                ZipEntry nextEntry = zipInputStream.getNextEntry();
                if (nextEntry == null) {
                    zipInputStream.close();
                    return;
                }
                File file = new File(d(i, context), nextEntry.getName());
                if (!file.exists()) {
                    File parentFile = nextEntry.isDirectory() ? file : file.getParentFile();
                    if (!parentFile.isDirectory() && !parentFile.mkdirs()) {
                        throw new FileNotFoundException("Failed to ensure directory: " + parentFile.getAbsolutePath());
                    }
                    if (!nextEntry.isDirectory()) {
                        FileOutputStream fileOutputStream = new FileOutputStream(file);
                        while (true) {
                            int i2 = zipInputStream.read(bArr);
                            if (i2 == -1) {
                                break;
                            } else {
                                fileOutputStream.write(bArr, 0, i2);
                            }
                        }
                        fileOutputStream.close();
                    }
                }
            }
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }

    private static boolean ch(Context context) {
        try {
            ZipInputStream zipInputStream = new ZipInputStream(new BufferedInputStream(is(2, context)));
            int i = 0;
            int i2 = 0;
            while (true) {
                ZipEntry nextEntry = zipInputStream.getNextEntry();
                if (nextEntry == null) {
                    break;
                }
                File file = new File(d(2, context), nextEntry.getName());
                i++;
                if (file.exists() && nextEntry.getSize() == file.length()) {
                    i2++;
                }
            }
            zipInputStream.close();
            return i == i2;
        } catch (Throwable th) {
            th.printStackTrace();
            return false;
        }
    }

    private static String d(int i, Context context) {
        String str = (i != 0 && i == 1) ? "/storage/emulated/0/Android/data/com.FTS27.giiodroicerargaming/files/cache" : "/storage/emulated/0/Android/data/com.FTS27.giiodroicerargaming/files/cache";
        return o(str);
    }

    private static InputStream is(int i, Context context) throws Throwable {
        String str;
        AssetManager assets = context.getAssets();
        StringBuilder sb = new StringBuilder();
        sb.append(path);
        if (i == 0) {
            str = tr3;
        } else {
            str = i == 1 ? yt1 : y2;
        }
        sb.append(str);
        return assets.open(sb.toString());
    }

    private static boolean kytt(Context context) {
        if (ch(context)) {
            return true;
        }
        File file = new File(d(2, context));
        if (file.exists() && file.list().length != 0) {
            for (File file2 : new File(d(2, context)).listFiles()) {
                if (file2.isFile()) {
                    file2.delete();
                }
            }
        }
        return false;
    }

    private static String o(String str) {
        return str.substring(0, str.length() - 6);
    }

    private static boolean r(int i, Context context) {
        try {
            return is(i, context).available() > 0;
        } catch (Throwable unused) {
            return false;
        }
    }
}
