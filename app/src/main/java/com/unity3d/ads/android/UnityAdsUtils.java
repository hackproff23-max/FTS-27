package com.unity3d.ads.android;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.os.Environment;
import android.os.Handler;
import android.os.Looper;
import com.facebook.appevents.AppEventsConstants;
import com.flurry.android.Constants;
import com.unity3d.ads.android.campaign.UnityAdsCampaign;
import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.Iterator;
import javax.security.auth.x500.X500Principal;

/* JADX INFO: loaded from: classes.dex */
public class UnityAdsUtils {
    private static final X500Principal a = new X500Principal("CN=Android Debug,O=Android,C=US");

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r0v1 */
    /* JADX WARN: Type inference failed for: r0v15, types: [boolean] */
    /* JADX WARN: Type inference failed for: r0v16 */
    /* JADX WARN: Type inference failed for: r0v19 */
    /* JADX WARN: Type inference failed for: r0v2 */
    /* JADX WARN: Type inference failed for: r0v20 */
    /* JADX WARN: Type inference failed for: r0v21 */
    /* JADX WARN: Type inference failed for: r0v3 */
    /* JADX WARN: Type inference failed for: r0v5 */
    /* JADX WARN: Type inference failed for: r0v7 */
    /* JADX WARN: Type inference failed for: r0v9 */
    /* JADX WARN: Type inference failed for: r1v0 */
    /* JADX WARN: Type inference failed for: r1v11 */
    /* JADX WARN: Type inference failed for: r1v3 */
    /* JADX WARN: Type inference failed for: r1v4 */
    /* JADX WARN: Type inference failed for: r1v5 */
    /* JADX WARN: Type inference failed for: r1v6 */
    /* JADX WARN: Type inference failed for: r1v8, types: [boolean] */
    public static boolean isDebuggable(Context context) {
        boolean z;
        ?? Equals;
        ?? r1 = 0;
        if (context == null) {
            return false;
        }
        try {
            ApplicationInfo applicationInfo = context.getPackageManager().getApplicationInfo(context.getPackageName(), 0);
            int i = applicationInfo.flags & 2;
            applicationInfo.flags = i;
            z = false;
            Equals = i != 0;
        } catch (PackageManager.NameNotFoundException e) {
            z = true;
            Equals = 0;
        }
        if (z) {
            try {
                Signature[] signatureArr = context.getPackageManager().getPackageInfo(context.getPackageName(), 64).signatures;
                ?? r0 = Equals;
                while (true) {
                    try {
                        int i2 = r1 == true ? 1 : 0;
                        r1 = r0;
                        if (i2 >= signatureArr.length) {
                            Equals = r1 == true ? 1 : 0;
                            break;
                        }
                        Equals = ((X509Certificate) CertificateFactory.getInstance("X.509").generateCertificate(new ByteArrayInputStream(signatureArr[i2 == true ? 1 : 0].toByteArray()))).getSubjectX500Principal().equals(a);
                        if (Equals != 0) {
                            break;
                        }
                        r0 = Equals;
                        r1 = (i2 == true ? 1 : 0) + 1;
                    } catch (PackageManager.NameNotFoundException e2) {
                        Equals = r1;
                    } catch (CertificateException e3) {
                        Equals = r1;
                    }
                }
            } catch (PackageManager.NameNotFoundException e4) {
            } catch (CertificateException e5) {
            }
        }
        return Equals;
    }

    public static String SHA1(String str) throws NoSuchAlgorithmException {
        MessageDigest messageDigest = MessageDigest.getInstance("SHA-1");
        messageDigest.update(str.getBytes("iso-8859-1"), 0, str.length());
        byte[] bArrDigest = messageDigest.digest();
        StringBuilder sb = new StringBuilder();
        for (byte b : bArrDigest) {
            int i = (b >>> 4) & 15;
            int i2 = 0;
            while (true) {
                sb.append((i < 0 || i > 9) ? (char) ((i - 10) + 97) : (char) (i + 48));
                int i3 = b & 15;
                int i4 = i2 + 1;
                if (i2 > 0) {
                    break;
                }
                i2 = i4;
                i = i3;
            }
        }
        return sb.toString();
    }

    @SuppressLint({"DefaultLocale"})
    public static String Md5(String str) {
        MessageDigest messageDigest = null;
        try {
            messageDigest = MessageDigest.getInstance("MD5");
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        messageDigest.update(str.getBytes(), 0, str.length());
        byte[] bArrDigest = messageDigest.digest();
        String str2 = new String();
        for (byte b : bArrDigest) {
            int i = b & Constants.UNKNOWN;
            if (i <= 15) {
                str2 = str2 + AppEventsConstants.EVENT_PARAM_VALUE_NO;
            }
            str2 = str2 + Integer.toHexString(i);
        }
        return str2.toUpperCase();
    }

    public static String readFile(File file, boolean z) {
        String strConcat = "";
        if (file.exists() && file.canRead()) {
            try {
                BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
                while (true) {
                    String line = bufferedReader.readLine();
                    if (line != null) {
                        strConcat = strConcat.concat(line);
                        if (z) {
                            strConcat = strConcat.concat("\n");
                        }
                    } else {
                        try {
                            bufferedReader.close();
                            return strConcat;
                        } catch (Exception e) {
                            UnityAdsDeviceLog.error("Problem closing reader: " + e.getMessage());
                            return strConcat;
                        }
                    }
                }
            } catch (Exception e2) {
                UnityAdsDeviceLog.error("Problem reading file: " + e2.getMessage());
                return null;
            }
        } else {
            UnityAdsDeviceLog.error("File did not exist or couldn't be read");
            return null;
        }
    }

    public static boolean writeFile(File file, String str) {
        try {
            FileOutputStream fileOutputStream = new FileOutputStream(file);
            fileOutputStream.write(str.getBytes());
            fileOutputStream.flush();
            fileOutputStream.close();
            UnityAdsDeviceLog.debug("Wrote file: " + file.getAbsolutePath());
            return true;
        } catch (Exception e) {
            UnityAdsDeviceLog.error("Could not write file: " + e.getMessage());
            return false;
        }
    }

    public static void removeFile(String str) {
        if (str != null) {
            File file = new File(getCacheDirectory() + "/" + new File(str).getName());
            if (file.exists()) {
                if (!file.delete()) {
                    UnityAdsDeviceLog.error("Could not delete: " + file.getAbsolutePath());
                    return;
                } else {
                    UnityAdsDeviceLog.debug("Deleted: " + file.getAbsolutePath());
                    return;
                }
            }
            UnityAdsDeviceLog.debug("File: " + file.getAbsolutePath() + " doesn't exist.");
        }
    }

    public static long getSizeForLocalFile(String str) {
        File file = new File(getCacheDirectory() + "/" + new File(str).getName());
        if (!file.exists()) {
            return -1L;
        }
        return file.length();
    }

    public static String getCacheDirectory() {
        return Environment.getExternalStorageDirectory().toString() + "/UnityAdsVideoCache";
    }

    public static boolean canUseExternalStorage() {
        return Environment.getExternalStorageState().equals("mounted");
    }

    public static File createCacheDir() {
        File file = new File(getCacheDirectory());
        file.mkdirs();
        writeFile(new File(getCacheDirectory() + "/.nomedia"), "");
        return file;
    }

    public static boolean isFileRequiredByCampaigns(String str, ArrayList arrayList) {
        if (str == null || arrayList == null) {
            return false;
        }
        File file = new File(str);
        if (file.getName().equals(".nomedia")) {
            return true;
        }
        Iterator it = arrayList.iterator();
        while (it.hasNext()) {
            if (file.getName().equals(new File(((UnityAdsCampaign) it.next()).getVideoFilename()).getName())) {
                return true;
            }
        }
        return false;
    }

    public static boolean isFileInCache(String str) {
        return new File(getCacheDirectory() + "/" + new File(str).getName()).exists();
    }

    public static void runOnUiThread(Runnable runnable) {
        runOnUiThread(runnable, 0L);
    }

    public static void runOnUiThread(Runnable runnable, long j) {
        Handler handler = new Handler(Looper.getMainLooper());
        if (j > 0) {
            handler.postDelayed(runnable, j);
        } else {
            handler.post(runnable);
        }
    }
}
