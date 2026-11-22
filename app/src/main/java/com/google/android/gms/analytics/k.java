package com.google.android.gms.analytics;

import android.content.Context;
import com.facebook.appevents.AppEventsConstants;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.UUID;

/* JADX INFO: loaded from: classes.dex */
class k implements q {
    private static final Object xO = new Object();
    private static k yD;
    private final Context mContext;
    private String yE;
    private boolean yF = false;
    private final Object yG = new Object();

    protected k(Context context) {
        this.mContext = context;
        ep();
    }

    private boolean ae(String str) {
        try {
            ae.V("Storing clientId.");
            FileOutputStream fileOutputStreamOpenFileOutput = this.mContext.openFileOutput("gaClientId", 0);
            fileOutputStreamOpenFileOutput.write(str.getBytes());
            fileOutputStreamOpenFileOutput.close();
            return true;
        } catch (FileNotFoundException e) {
            ae.T("Error creating clientId file.");
            return false;
        } catch (IOException e2) {
            ae.T("Error writing to clientId file.");
            return false;
        }
    }

    public static k el() {
        k kVar;
        synchronized (xO) {
            kVar = yD;
        }
        return kVar;
    }

    private String en() {
        if (!this.yF) {
            synchronized (this.yG) {
                if (!this.yF) {
                    ae.V("Waiting for clientId to load");
                    do {
                        try {
                            this.yG.wait();
                        } catch (InterruptedException e) {
                            ae.T("Exception while waiting for clientId: " + e);
                        }
                    } while (!this.yF);
                }
            }
        }
        ae.V("Loaded clientId");
        return this.yE;
    }

    private void ep() {
        new Thread("client_id_fetcher") { // from class: com.google.android.gms.analytics.k.1
            @Override // java.lang.Thread, java.lang.Runnable
            public void run() {
                synchronized (k.this.yG) {
                    k.this.yE = k.this.eq();
                    k.this.yF = true;
                    k.this.yG.notifyAll();
                }
            }
        }.start();
    }

    public static void y(Context context) {
        synchronized (xO) {
            if (yD == null) {
                yD = new k(context);
            }
        }
    }

    public boolean ac(String str) {
        return "&cid".equals(str);
    }

    String em() {
        String str;
        synchronized (this.yG) {
            this.yE = eo();
            str = this.yE;
        }
        return str;
    }

    protected String eo() {
        String lowerCase = UUID.randomUUID().toString().toLowerCase();
        try {
            return !ae(lowerCase) ? AppEventsConstants.EVENT_PARAM_VALUE_NO : lowerCase;
        } catch (Exception e) {
            return null;
        }
    }

    /* JADX WARN: Code duplicated, block: B:27:? A[RETURN, SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:7:0x002b  */
    String eq() {
        String str = null;
        try {
            FileInputStream fileInputStreamOpenFileInput = this.mContext.openFileInput("gaClientId");
            byte[] bArr = new byte[128];
            int i = fileInputStreamOpenFileInput.read(bArr, 0, 128);
            if (fileInputStreamOpenFileInput.available() > 0) {
                ae.T("clientId file seems corrupted, deleting it.");
                fileInputStreamOpenFileInput.close();
                this.mContext.deleteFile("gaClientId");
            } else if (i <= 0) {
                ae.T("clientId file seems empty, deleting it.");
                fileInputStreamOpenFileInput.close();
                this.mContext.deleteFile("gaClientId");
            } else {
                String str2 = new String(bArr, 0, i);
                try {
                    fileInputStreamOpenFileInput.close();
                    ae.V("Loaded client id from disk.");
                    str = str2;
                } catch (FileNotFoundException e) {
                    str = str2;
                } catch (IOException e2) {
                    str = str2;
                    ae.T("Error reading clientId file, deleting it.");
                    this.mContext.deleteFile("gaClientId");
                    if (str == null) {
                        return eo();
                    }
                    return str;
                }
            }
        } catch (FileNotFoundException e3) {
        } catch (IOException e4) {
        }
        if (str == null) {
            return eo();
        }
        return str;
    }

    @Override // com.google.android.gms.analytics.q
    public String getValue(String field) {
        if ("&cid".equals(field)) {
            return en();
        }
        return null;
    }
}
