package com.google.android.gms.tagmanager;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

/* JADX INFO: loaded from: classes.dex */
class co implements Runnable {
    private volatile String aqI;
    private final String aqm;
    private final bn asA;
    private final String asB;
    private bg<com.google.android.gms.internal.c.j> asC;
    private volatile r asD;
    private volatile String asE;
    private final Context mContext;

    co(Context context, String str, bn bnVar, r rVar) {
        this.mContext = context;
        this.asA = bnVar;
        this.aqm = str;
        this.asD = rVar;
        this.asB = "/r?id=" + str;
        this.aqI = this.asB;
        this.asE = null;
    }

    public co(Context context, String str, r rVar) {
        this(context, str, new bn(), rVar);
    }

    private boolean qd() {
        NetworkInfo activeNetworkInfo = ((ConnectivityManager) this.mContext.getSystemService("connectivity")).getActiveNetworkInfo();
        if (activeNetworkInfo != null && activeNetworkInfo.isConnected()) {
            return true;
        }
        bh.V("...no network connectivity");
        return false;
    }

    private void qe() {
        if (!qd()) {
            this.asC.a(bg.a.NOT_AVAILABLE);
            return;
        }
        bh.V("Start loading resource from network ...");
        String strQf = qf();
        bm bmVarPO = this.asA.pO();
        try {
            try {
                InputStream inputStreamCF = bmVarPO.cF(strQf);
                try {
                    ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                    cr.b(inputStreamCF, byteArrayOutputStream);
                    com.google.android.gms.internal.c.j jVarB = com.google.android.gms.internal.c.j.b(byteArrayOutputStream.toByteArray());
                    bh.V("Successfully loaded supplemented resource: " + jVarB);
                    if (jVarB.gs == null && jVarB.gr.length == 0) {
                        bh.V("No change for container: " + this.aqm);
                    }
                    this.asC.l(jVarB);
                    bmVarPO.close();
                    bh.V("Load resource from network finished.");
                } catch (IOException e) {
                    bh.d("Error when parsing downloaded resources from url: " + strQf + " " + e.getMessage(), e);
                    this.asC.a(bg.a.SERVER_ERROR);
                    bmVarPO.close();
                }
            } catch (FileNotFoundException e2) {
                bh.W("No data is retrieved from the given url: " + strQf + ". Make sure container_id: " + this.aqm + " is correct.");
                this.asC.a(bg.a.SERVER_ERROR);
                bmVarPO.close();
            } catch (IOException e3) {
                bh.d("Error when loading resources from url: " + strQf + " " + e3.getMessage(), e3);
                this.asC.a(bg.a.IO_ERROR);
                bmVarPO.close();
            }
        } catch (Throwable th) {
            bmVarPO.close();
            throw th;
        }
    }

    void a(bg<com.google.android.gms.internal.c.j> bgVar) {
        this.asC = bgVar;
    }

    void cL(String str) {
        bh.S("Setting previous container version: " + str);
        this.asE = str;
    }

    void cw(String str) {
        if (str == null) {
            this.aqI = this.asB;
        } else {
            bh.S("Setting CTFE URL path: " + str);
            this.aqI = str;
        }
    }

    String qf() {
        String str = this.asD.pu() + this.aqI + "&v=a65833898";
        if (this.asE != null && !this.asE.trim().equals("")) {
            str = str + "&pv=" + this.asE;
        }
        return ce.qa().qb().equals(ce.a.CONTAINER_DEBUG) ? str + "&gtm_debug=x" : str;
    }

    @Override // java.lang.Runnable
    public void run() {
        if (this.asC == null) {
            throw new IllegalStateException("callback must be set before execute");
        }
        this.asC.ps();
        qe();
    }
}
