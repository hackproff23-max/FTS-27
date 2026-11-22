package com.nuance.nmdp.speechkit.recognitionresult;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.media.AudioManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import android.telephony.TelephonyManager;
import android.util.Log;
import com.google.android.vending.expansion.downloader.impl.DownloaderService;
import com.millennialmedia.android.MMException;
import com.nuance.nmdp.speechkit.util.pdx.PdxValue;
import defpackage.aw;
import defpackage.bi;
import defpackage.cv;
import defpackage.cw;
import defpackage.fa;
import defpackage.fb;
import defpackage.j;
import defpackage.r;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.Map;

/* JADX INFO: loaded from: classes.dex */
public final class b {
    private boolean a = Build.PRODUCT.equals("sdk");
    private final Context b;

    public b(Object obj) {
        this.b = (Context) obj;
    }

    public static PdxValue.Dictionary a(cv cvVar) {
        PdxValue.Dictionary dictionary = new PdxValue.Dictionary(null);
        Enumeration enumerationA = cvVar.a();
        while (enumerationA.hasMoreElements()) {
            String str = (String) enumerationA.nextElement();
            switch (cvVar.c(str)) {
                case 4:
                    dictionary.put(str, cvVar.e(str));
                    break;
                case 16:
                    dictionary.put(str, a(cvVar.i(str)));
                    break;
                case MMException.DISPLAY_AD_NOT_FOUND /* 22 */:
                    dictionary.put(str, cvVar.g(str));
                    break;
                case DownloaderService.STATUS_RUNNING /* 192 */:
                    dictionary.put(str, cvVar.d(str));
                    break;
                case DownloaderService.STATUS_PAUSED_BY_APP /* 193 */:
                    dictionary.put(str, cvVar.f(str));
                    break;
                case 224:
                    dictionary.put(str, a(cvVar.h(str)));
                    break;
                default:
                    b("PdxValue", "Unsupported PDX type found in dictionary, skipping");
                    break;
            }
        }
        return dictionary;
    }

    private static PdxValue.Sequence a(cw cwVar) {
        PdxValue.Sequence sequence = new PdxValue.Sequence(null);
        for (int i = 0; i < cwVar.a(); i++) {
            switch (cwVar.b(i)) {
                case 4:
                    sequence.add(cwVar.d(i));
                    break;
                case 16:
                    sequence.add(a(cwVar.h(i)));
                    break;
                case MMException.DISPLAY_AD_NOT_FOUND /* 22 */:
                    sequence.add(cwVar.f(i));
                    break;
                case DownloaderService.STATUS_RUNNING /* 192 */:
                    sequence.add(cwVar.c(i));
                    break;
                case DownloaderService.STATUS_PAUSED_BY_APP /* 193 */:
                    sequence.add(cwVar.e(i));
                    break;
                case 224:
                    sequence.add(a(cwVar.g(i)));
                    break;
                default:
                    b("PdxValue", "Unsupported PDX type found in sequence, skipping");
                    break;
            }
        }
        return sequence;
    }

    public static cv a(j jVar, Map<String, r> map) {
        cv cvVarG = jVar.g();
        for (Map.Entry<String, r> entry : map.entrySet()) {
            String key = entry.getKey();
            r value = entry.getValue();
            switch (value.f()) {
                case 0:
                    cvVarG.b(key, value.c());
                    break;
                case 1:
                    cvVarG.b(key, value.d());
                    break;
                case 2:
                    cvVarG.a(key, a(jVar, value.a()));
                    break;
                case 3:
                    cvVarG.a(key, a(jVar, value.b()));
                    break;
                case 4:
                    cvVarG.a(key, value.e());
                    break;
            }
        }
        return cvVarG;
    }

    public static cw a(j jVar, List<r> list) {
        cw cwVarH = jVar.h();
        for (r rVar : list) {
            switch (rVar.f()) {
                case 0:
                    cwVarH.a(rVar.c());
                    break;
                case 1:
                    cwVarH.i(rVar.d());
                    break;
                case 2:
                    cwVarH.a(a(jVar, rVar.a()));
                    break;
                case 3:
                    cwVarH.a(a(jVar, rVar.b()));
                    break;
                case 4:
                    cwVarH.b(rVar.e());
                    break;
            }
        }
        return cwVarH;
    }

    public static List<DetailedResult> a(byte[] bArr) throws IllegalArgumentException {
        if (bArr == null || bArr.length < 4) {
            throw new IllegalArgumentException("Cannot parse results: The buffer length is too small to be containing any results.");
        }
        fa faVarA = fb.a(bArr);
        ArrayList arrayList = new ArrayList();
        for (int i = 0; i < faVarA.a(); i++) {
            arrayList.add(new c(faVarA.a(i)));
        }
        return arrayList;
    }

    public static void a(Object obj, String str) {
        Log.i("SpeechKit", d(obj, str));
    }

    public static void a(Object obj, String str, Throwable th) {
        Log.e("SpeechKit", d(obj, str), th);
    }

    public static void b(Object obj, String str) {
        Log.w("SpeechKit", d(obj, str));
    }

    public static void c(Object obj, String str) {
        Log.e("SpeechKit", d(obj, str));
    }

    private static String d(Object obj, String str) {
        String name;
        int i;
        if (aw.a || obj == null || (name = obj.getClass().getName()) == null || name.length() == 0) {
            return str;
        }
        int iLastIndexOf = name.lastIndexOf(46);
        if (iLastIndexOf >= 0 && (i = iLastIndexOf + 1) < name.length()) {
            name = name.substring(i);
        }
        return name + ": " + str;
    }

    public static String h() {
        return com.nuance.nmsp.client.sdk.oem.c.e().a();
    }

    public static String i() {
        return com.nuance.nmsp.client.sdk.oem.c.e().b();
    }

    public static String j() {
        return com.nuance.nmsp.client.sdk.oem.c.e().c();
    }

    public static String k() {
        return com.nuance.nmsp.client.sdk.oem.c.e().d();
    }

    public final String a() {
        String networkOperatorName = ((TelephonyManager) this.b.getSystemService("phone")).getNetworkOperatorName();
        return (networkOperatorName == null || networkOperatorName.length() == 0) ? "carrier" : networkOperatorName;
    }

    public final String b() {
        String typeName;
        NetworkInfo activeNetworkInfo = ((ConnectivityManager) this.b.getSystemService("connectivity")).getActiveNetworkInfo();
        if (activeNetworkInfo == null || (typeName = activeNetworkInfo.getTypeName()) == null || typeName.length() <= 0) {
            return null;
        }
        return activeNetworkInfo.getTypeName();
    }

    public final String c() {
        String strF = new com.nuance.nmsp.client.sdk.oem.c(this.b).f();
        if (strF == null || strF.length() != 0) {
            return strF;
        }
        return null;
    }

    public final bi d() {
        return (((AudioManager) this.b.getSystemService("audio")).isBluetoothScoOn() || this.a) ? bi.a : bi.b;
    }

    public final bi e() {
        return ((AudioManager) this.b.getSystemService("audio")).isBluetoothScoOn() ? bi.a : bi.b;
    }

    /* JADX WARN: Code duplicated, block: B:13:0x0030  */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:13:0x0030 -> B:14:0x0012). Please report as a decompilation issue!!! */
    public final byte[] f() {
        byte[] bytes;
        try {
            ApplicationInfo applicationInfo = this.b.getApplicationInfo();
            if (applicationInfo.name != null) {
                bytes = applicationInfo.name.getBytes("UTF-8");
            } else if (applicationInfo.packageName != null) {
                String[] strArrSplit = applicationInfo.packageName.split(".");
                if (strArrSplit.length > 1) {
                    bytes = strArrSplit[strArrSplit.length - 1].getBytes("UTF-8");
                } else {
                    bytes = new byte[0];
                }
            } else {
                bytes = new byte[0];
            }
        } catch (UnsupportedEncodingException e) {
        }
        return bytes;
    }

    public final byte[] g() {
        try {
            ApplicationInfo applicationInfo = this.b.getApplicationInfo();
            if (applicationInfo.packageName != null) {
                return applicationInfo.packageName.getBytes("UTF-8");
            }
        } catch (UnsupportedEncodingException e) {
        }
        return new byte[0];
    }
}
