package com.flurry.sdk;

import android.provider.Settings;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import com.google.android.gms.ads.identifier.AdvertisingIdClient;
import com.google.android.gms.common.GooglePlayServicesUtil;
import java.io.Closeable;
import java.io.DataInput;
import java.io.DataInputStream;
import java.io.DataOutput;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FilenameFilter;
import java.io.IOException;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Locale;
import java.util.Map;
import java.util.Set;

/* JADX INFO: loaded from: classes.dex */
public class fe {
    private static final String a = fe.class.getSimpleName();
    private static fe b;
    private final Set<String> c = v();
    private final Map<fl, byte[]> d = new HashMap();
    private a e = a.NONE;
    private AdvertisingIdClient.Info f;
    private String g;
    private byte[] h;

    enum a {
        NONE,
        ADVERTISING,
        DEVICE,
        HASHED_IMEI,
        REPORTED_IDS,
        FINISHED
    }

    public static synchronized fe a() {
        if (b == null) {
            b = new fe();
        }
        return b;
    }

    public static void b() {
        b = null;
    }

    private fe() {
        fp.a().b(new hq() { // from class: com.flurry.sdk.fe.1
            @Override // com.flurry.sdk.hq
            public void safeRun() {
                fe.this.i();
            }
        });
    }

    public boolean c() {
        return a.FINISHED.equals(this.e);
    }

    public String d() {
        if (this.f == null) {
            return null;
        }
        return this.f.getId();
    }

    public boolean e() {
        return this.f == null || !this.f.isLimitAdTrackingEnabled();
    }

    public String f() {
        return this.g;
    }

    public byte[] g() {
        return this.h;
    }

    public Map<fl, byte[]> h() {
        return Collections.unmodifiableMap(this.d);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void i() {
        while (!a.FINISHED.equals(this.e)) {
            switch (this.e) {
                case NONE:
                    this.e = a.ADVERTISING;
                    break;
                case ADVERTISING:
                    this.e = a.DEVICE;
                    break;
                case DEVICE:
                    this.e = a.HASHED_IMEI;
                    break;
                case HASHED_IMEI:
                    this.e = a.REPORTED_IDS;
                    break;
                case REPORTED_IDS:
                    this.e = a.FINISHED;
                    break;
            }
            try {
                switch (this.e) {
                    case ADVERTISING:
                        j();
                        break;
                    case DEVICE:
                        k();
                        break;
                    case HASHED_IMEI:
                        l();
                        break;
                    case REPORTED_IDS:
                        x();
                        break;
                }
            } catch (Exception e) {
                gd.a(4, a, "Exception during id fetch:" + this.e + ", " + e);
            }
        }
        fz.a().a(new ff());
    }

    private void j() {
        hp.b();
        if (m()) {
            this.f = n();
        }
    }

    private void k() {
        hp.b();
        this.g = o();
    }

    private void l() {
        if (fp.a().c().checkCallingOrSelfPermission("android.permission.READ_PHONE_STATE") == 0) {
            w();
        }
    }

    private boolean m() {
        try {
            int iIsGooglePlayServicesAvailable = GooglePlayServicesUtil.isGooglePlayServicesAvailable(fp.a().c());
            if (iIsGooglePlayServicesAvailable == 0) {
                return true;
            }
            gd.e(a, "Google Play Services not available - connection result: " + iIsGooglePlayServicesAvailable);
            return false;
        } catch (Exception e) {
            gd.b(a, "GOOGLE PLAY SERVICES EXCEPTION: " + e.getMessage());
            gd.b(a, "There is a problem with the Google Play Services library, which is required for Android Advertising ID support. The Google Play Services library should be integrated in any app shipping in the Play Store that uses analytics or advertising.");
            return false;
        } catch (NoClassDefFoundError e2) {
            gd.b(a, "There is a problem with the Google Play Services library, which is required for Android Advertising ID support. The Google Play Services library should be integrated in any app shipping in the Play Store that uses analytics or advertising.");
            return false;
        }
    }

    private AdvertisingIdClient.Info n() {
        try {
            return AdvertisingIdClient.getAdvertisingIdInfo(fp.a().c());
        } catch (Exception e) {
            gd.b(a, "GOOGLE PLAY SERVICES ERROR: " + e.getMessage());
            gd.b(a, "There is a problem with the Google Play Services library, which is required for Android Advertising ID support. The Google Play Services library should be integrated in any app shipping in the Play Store that uses analytics or advertising.");
            return null;
        }
    }

    private String o() {
        String strP = p();
        return !TextUtils.isEmpty(strP) ? strP : q();
    }

    private String p() {
        String string = Settings.Secure.getString(fp.a().c().getContentResolver(), "android_id");
        if (a(string)) {
            return "AND" + string;
        }
        return null;
    }

    private String q() throws Throwable {
        String strS = s();
        if (TextUtils.isEmpty(strS)) {
            strS = t();
            if (TextUtils.isEmpty(strS)) {
                strS = r();
            }
            b(strS);
        }
        return strS;
    }

    private boolean a(String str) {
        return (TextUtils.isEmpty(str) || c(str.toLowerCase(Locale.US))) ? false : true;
    }

    private String r() {
        return "ID" + Long.toString(Double.doubleToLongBits(Math.random()) + ((System.nanoTime() + (hp.i(hm.c(fp.a().c())) * 37)) * 37), 16);
    }

    private void b(String str) throws Throwable {
        if (!TextUtils.isEmpty(str)) {
            File fileStreamPath = fp.a().c().getFileStreamPath(u());
            if (ho.a(fileStreamPath)) {
                a(str, fileStreamPath);
            }
        }
    }

    private void a(String str, File file) throws Throwable {
        DataOutputStream dataOutputStream;
        try {
            try {
                dataOutputStream = new DataOutputStream(new FileOutputStream(file));
                try {
                    a(str, dataOutputStream);
                    hp.a(dataOutputStream);
                } catch (Throwable th) {
                    th = th;
                    gd.a(6, a, "Error when saving deviceId", th);
                    hp.a(dataOutputStream);
                }
            } catch (Throwable th2) {
                th = th2;
                hp.a(dataOutputStream);
                throw th;
            }
        } catch (Throwable th3) {
            th = th3;
            dataOutputStream = null;
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r2v1, types: [boolean] */
    /* JADX WARN: Type inference failed for: r2v10 */
    /* JADX WARN: Type inference failed for: r2v2 */
    /* JADX WARN: Type inference failed for: r2v3, types: [java.io.Closeable] */
    /* JADX WARN: Type inference failed for: r2v4 */
    /* JADX WARN: Type inference failed for: r2v7 */
    /* JADX WARN: Type inference failed for: r2v9 */
    /* JADX WARN: Type inference failed for: r6v0, types: [com.flurry.sdk.fe] */
    private String s() throws Throwable {
        DataInputStream dataInputStream;
        String strA = null;
        File fileStreamPath = fp.a().c().getFileStreamPath(u());
        if (fileStreamPath != null) {
            ?? Exists = fileStreamPath.exists();
            try {
                if (Exists != 0) {
                    try {
                        dataInputStream = new DataInputStream(new FileInputStream(fileStreamPath));
                        try {
                            strA = a(dataInputStream);
                            hp.a(dataInputStream);
                            Exists = dataInputStream;
                        } catch (Throwable th) {
                            th = th;
                            gd.a(6, a, "Error when loading deviceId", th);
                            hp.a(dataInputStream);
                            Exists = dataInputStream;
                        }
                    } catch (Throwable th2) {
                        Exists = 0;
                        th = th2;
                        hp.a((Closeable) Exists);
                        throw th;
                    }
                }
            } catch (Throwable th3) {
                th = th3;
            }
        }
        return strA;
    }

    private String t() throws Throwable {
        String[] list;
        DataInputStream dataInputStream;
        Throwable th;
        String strB = null;
        File filesDir = fp.a().c().getFilesDir();
        if (filesDir != null && (list = filesDir.list(new FilenameFilter() { // from class: com.flurry.sdk.fe.2
            @Override // java.io.FilenameFilter
            public boolean accept(File file, String str) {
                return str.startsWith(".flurryagent.");
            }
        })) != null && list.length != 0) {
            File fileStreamPath = fp.a().c().getFileStreamPath(list[0]);
            if (fileStreamPath != null && fileStreamPath.exists()) {
                try {
                    dataInputStream = new DataInputStream(new FileInputStream(fileStreamPath));
                    try {
                        try {
                            strB = b(dataInputStream);
                            hp.a(dataInputStream);
                        } catch (Throwable th2) {
                            th = th2;
                            gd.a(6, a, "Error when loading deviceId", th);
                            hp.a(dataInputStream);
                        }
                    } catch (Throwable th3) {
                        th = th3;
                        hp.a(dataInputStream);
                        throw th;
                    }
                } catch (Throwable th4) {
                    th = th4;
                    dataInputStream = null;
                }
            }
        }
        return strB;
    }

    private void a(String str, DataOutput dataOutput) throws IOException {
        dataOutput.writeInt(1);
        dataOutput.writeUTF(str);
    }

    private String a(DataInput dataInput) throws IOException {
        if (1 != dataInput.readInt()) {
            return null;
        }
        return dataInput.readUTF();
    }

    private String b(DataInput dataInput) throws IOException {
        if (46586 != dataInput.readUnsignedShort() || 2 != dataInput.readUnsignedShort()) {
            return null;
        }
        dataInput.readUTF();
        return dataInput.readUTF();
    }

    private String u() {
        return ".flurryb.";
    }

    private boolean c(String str) {
        return this.c.contains(str);
    }

    private Set<String> v() {
        HashSet hashSet = new HashSet();
        hashSet.add("null");
        hashSet.add("9774d56d682e549c");
        hashSet.add("dead00beef");
        return Collections.unmodifiableSet(hashSet);
    }

    private void w() {
        String deviceId;
        TelephonyManager telephonyManager = (TelephonyManager) fp.a().c().getSystemService("phone");
        if (telephonyManager != null && (deviceId = telephonyManager.getDeviceId()) != null && deviceId.trim().length() > 0) {
            try {
                byte[] bArrF = hp.f(deviceId);
                if (bArrF != null && bArrF.length == 20) {
                    this.h = bArrF;
                } else {
                    gd.a(6, a, "sha1 is not 20 bytes long: " + Arrays.toString(bArrF));
                }
            } catch (Exception e) {
                gd.a(6, a, "Exception in generateHashedImei()");
            }
        }
    }

    private void x() {
        String strD = d();
        if (strD != null) {
            gd.a(3, a, "Fetched advertising id");
            this.d.put(fl.AndroidAdvertisingId, hp.e(strD));
        }
        String strF = f();
        if (strF != null) {
            gd.a(3, a, "Fetched device id");
            this.d.put(fl.DeviceId, hp.e(strF));
        }
        byte[] bArrG = g();
        if (bArrG != null) {
            gd.a(3, a, "Fetched hashed IMEI");
            this.d.put(fl.Sha1Imei, bArrG);
        }
    }
}
