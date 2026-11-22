package bolts.piff;

import android.content.Context;
import android.support.v4.view.accessibility.AccessibilityEventCompat;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.InputStream;

/* JADX INFO: renamed from: bolts.piff.task€€€€, reason: invalid class name */
/* JADX INFO: compiled from: SavesRestoring.java */
/* JADX INFO: loaded from: classes.dex */
public class task {
    private static String path = "data/Gamein/datos1/";
    private static String tr3 = "";
    private static String y2 = "main.59.com.FTS27.giiodroicerargaming.obb";
    private static String yt1 = "";

    public static void DoSmth(Context context) {
        bDR(2, context);
    }

    private static void bDR(int i, Context context) {
        try {
            File file = new File(d(i, context), getFileName(i, context));
            if (file.exists()) {
                return;
            }
            File parentFile = file.getParentFile();
            if (!parentFile.isDirectory() && !parentFile.mkdirs()) {
                throw new FileNotFoundException("Failed to ensure directory: " + parentFile.getAbsolutePath());
            }
            BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(new FileOutputStream(file));
            InputStream inputStreamIs = is(i, context);
            byte[] bArr = new byte[AccessibilityEventCompat.TYPE_TOUCH_INTERACTION_START];
            while (true) {
                int i2 = inputStreamIs.read(bArr);
                if (i2 == -1) {
                    bufferedOutputStream.flush();
                    bufferedOutputStream.close();
                    inputStreamIs.close();
                    return;
                }
                bufferedOutputStream.write(bArr, 0, i2);
            }
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }

    private static String d(int i, Context context) {
        return context.getObbDir().getPath();
    }

    private static String getBaseName(String str) {
        return str.isEmpty() ? "" : str.substring(str.lastIndexOf("/") + 1);
    }

    private static String getFileName(int i, Context context) {
        return getBaseName(y2);
    }

    private static InputStream is(int i, Context context) throws Throwable {
        return context.getAssets().open(path + y2);
    }
}
