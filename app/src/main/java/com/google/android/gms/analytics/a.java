package com.google.android.gms.analytics;

import android.content.Context;
import android.text.TextUtils;
import com.facebook.appevents.AppEventsConstants;
import com.google.android.gms.ads.identifier.AdvertisingIdClient;
import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import com.google.android.gms.common.GooglePlayServicesRepairableException;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.util.Locale;

/* JADX INFO: loaded from: classes.dex */
class a implements q {
    private static Object xO = new Object();
    private static a xP;
    private Context mContext;
    private AdvertisingIdClient.Info xQ;
    private long xR;
    private String xS;
    private boolean xT = false;
    private Object xU = new Object();

    a(Context context) {
        this.mContext = context.getApplicationContext();
    }

    private boolean a(AdvertisingIdClient.Info info, AdvertisingIdClient.Info info2) {
        String strEm;
        String id = info2 == null ? null : info2.getId();
        if (TextUtils.isEmpty(id)) {
            return true;
        }
        k.y(this.mContext);
        k kVarEl = k.el();
        String value = kVarEl.getValue("&cid");
        synchronized (this.xU) {
            if (!this.xT) {
                this.xS = x(this.mContext);
                this.xT = true;
            } else if (TextUtils.isEmpty(this.xS)) {
                String id2 = info != null ? info.getId() : null;
                if (id2 == null) {
                    return ab(id + value);
                }
                this.xS = aa(id2 + value);
            }
            String strAa = aa(id + value);
            if (TextUtils.isEmpty(strAa)) {
                return false;
            }
            if (strAa.equals(this.xS)) {
                return true;
            }
            if (TextUtils.isEmpty(this.xS)) {
                strEm = value;
            } else {
                ae.V("Resetting the client id because Advertising Id changed.");
                strEm = kVarEl.em();
                ae.V("New client Id: " + strEm);
            }
            return ab(id + strEm);
        }
    }

    static String aa(String str) {
        MessageDigest messageDigestAp = an.ap("MD5");
        if (messageDigestAp == null) {
            return null;
        }
        return String.format(Locale.US, "%032X", new BigInteger(1, messageDigestAp.digest(str.getBytes())));
    }

    private boolean ab(String str) {
        try {
            String strAa = aa(str);
            ae.V("Storing hashed adid.");
            FileOutputStream fileOutputStreamOpenFileOutput = this.mContext.openFileOutput("gaClientIdData", 0);
            fileOutputStreamOpenFileOutput.write(strAa.getBytes());
            fileOutputStreamOpenFileOutput.close();
            this.xS = strAa;
            return true;
        } catch (FileNotFoundException e) {
            ae.T("Error creating hash file.");
            return false;
        } catch (IOException e2) {
            ae.T("Error writing to hash file.");
            return false;
        }
    }

    public static q w(Context context) {
        if (xP == null) {
            synchronized (xO) {
                if (xP == null) {
                    xP = new a(context);
                }
            }
        }
        return xP;
    }

    static String x(Context context) {
        String str = null;
        try {
            FileInputStream fileInputStreamOpenFileInput = context.openFileInput("gaClientIdData");
            byte[] bArr = new byte[128];
            int i = fileInputStreamOpenFileInput.read(bArr, 0, 128);
            if (fileInputStreamOpenFileInput.available() > 0) {
                ae.W("Hash file seems corrupted, deleting it.");
                fileInputStreamOpenFileInput.close();
                context.deleteFile("gaClientIdData");
            } else if (i <= 0) {
                ae.U("Hash file is empty.");
                fileInputStreamOpenFileInput.close();
            } else {
                String str2 = new String(bArr, 0, i);
                try {
                    fileInputStreamOpenFileInput.close();
                    str = str2;
                } catch (FileNotFoundException e) {
                    str = str2;
                } catch (IOException e2) {
                    str = str2;
                    ae.W("Error reading Hash file, deleting it.");
                    context.deleteFile("gaClientIdData");
                    return str;
                }
            }
        } catch (FileNotFoundException e3) {
        } catch (IOException e4) {
        }
        return str;
    }

    AdvertisingIdClient.Info dP() {
        try {
            return AdvertisingIdClient.getAdvertisingIdInfo(this.mContext);
        } catch (GooglePlayServicesNotAvailableException e) {
            ae.W("GooglePlayServicesNotAvailableException getting Ad Id Info");
            return null;
        } catch (GooglePlayServicesRepairableException e2) {
            ae.W("GooglePlayServicesRepairableException getting Ad Id Info");
            return null;
        } catch (IOException e3) {
            ae.W("IOException getting Ad Id Info");
            return null;
        } catch (IllegalStateException e4) {
            ae.W("IllegalStateException getting Ad Id Info. If you would like to see Audience reports, please ensure that you have added '<meta-data android:name=\"com.google.android.gms.version\" android:value=\"@integer/google_play_services_version\" />' to your application manifest file. See http://goo.gl/naFqQk for details.");
            return null;
        } catch (Throwable th) {
            ae.W("Unknown exception. Could not get the ad Id.");
            return null;
        }
    }

    @Override // com.google.android.gms.analytics.q
    public String getValue(String field) {
        long jCurrentTimeMillis = System.currentTimeMillis();
        if (jCurrentTimeMillis - this.xR > 1000) {
            AdvertisingIdClient.Info infoDP = dP();
            if (a(this.xQ, infoDP)) {
                this.xQ = infoDP;
            } else {
                this.xQ = new AdvertisingIdClient.Info("", false);
            }
            this.xR = jCurrentTimeMillis;
        }
        if (this.xQ != null) {
            if ("&adid".equals(field)) {
                return this.xQ.getId();
            }
            if ("&ate".equals(field)) {
                return this.xQ.isLimitAdTrackingEnabled() ? AppEventsConstants.EVENT_PARAM_VALUE_NO : AppEventsConstants.EVENT_PARAM_VALUE_YES;
            }
        }
        return null;
    }
}
