package com.google.android.gms.internal;

import android.content.Context;
import android.os.SystemClock;
import android.text.TextUtils;
import com.google.android.vending.expansion.downloader.Constants;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes.dex */
@ey
public class fa extends gf implements fe.a {
    private final Context mContext;
    private cr qi;
    private final ez.a ti;
    private final fh.a tk;
    private final k tl;
    private gf tm;
    private fj tn;
    private final Object tj = new Object();
    private final Object mH = new Object();

    @ey
    private static final class a extends Exception {
        private final int tq;

        public a(String str, int i) {
            super(str);
            this.tq = i;
        }

        public int getErrorCode() {
            return this.tq;
        }
    }

    public fa(Context context, fh.a aVar, k kVar, ez.a aVar2) {
        this.ti = aVar2;
        this.mContext = context;
        this.tk = aVar;
        this.tl = kVar;
    }

    private ay a(fh fhVar) throws a {
        if (this.tn.tZ == null) {
            throw new a("The ad response must specify one of the supported ad sizes.", 0);
        }
        String[] strArrSplit = this.tn.tZ.split("x");
        if (strArrSplit.length != 2) {
            throw new a("Could not parse the ad size from the ad response: " + this.tn.tZ, 0);
        }
        try {
            int i = Integer.parseInt(strArrSplit[0]);
            int i2 = Integer.parseInt(strArrSplit[1]);
            for (ay ayVar : fhVar.lS.or) {
                float f = this.mContext.getResources().getDisplayMetrics().density;
                int i3 = ayVar.width == -1 ? (int) (ayVar.widthPixels / f) : ayVar.width;
                int i4 = ayVar.height == -2 ? (int) (ayVar.heightPixels / f) : ayVar.height;
                if (i == i3 && i2 == i4) {
                    return new ay(ayVar, fhVar.lS.or);
                }
            }
            throw new a("The ad size from the ad response was not one of the requested sizes: " + this.tn.tZ, 0);
        } catch (NumberFormatException e) {
            throw new a("Could not parse the ad size from the ad response: " + this.tn.tZ, 0);
        }
    }

    private boolean c(long j) throws a {
        long jElapsedRealtime = Constants.WATCHDOG_WAKE_TIMER - (SystemClock.elapsedRealtime() - j);
        if (jElapsedRealtime <= 0) {
            return false;
        }
        try {
            this.mH.wait(jElapsedRealtime);
            return true;
        } catch (InterruptedException e) {
            throw new a("Ad request cancelled.", -1);
        }
    }

    private void cE() throws a {
        if (this.tn.errorCode == -3) {
            return;
        }
        if (TextUtils.isEmpty(this.tn.tU)) {
            throw new a("No fill from ad server.", 3);
        }
        ga.a(this.mContext, this.tn.tT);
        if (this.tn.tW) {
            try {
                this.qi = new cr(this.tn.tU);
            } catch (JSONException e) {
                throw new a("Could not parse mediation config: " + this.tn.tU, 0);
            }
        }
    }

    private void e(long j) throws a {
        while (c(j)) {
            if (this.tn != null) {
                synchronized (this.tj) {
                    this.tm = null;
                }
                if (this.tn.errorCode != -2 && this.tn.errorCode != -3) {
                    throw new a("There was a problem getting an ad response. ErrorCode: " + this.tn.errorCode, this.tn.errorCode);
                }
                return;
            }
        }
        throw new a("Timed out waiting for ad response.", 2);
    }

    private void t(boolean z) {
        ga.dc().x(z);
        an anVarL = ga.dc().l(this.mContext);
        if (anVarL == null || anVarL.isAlive()) {
            return;
        }
        gr.S("start fetching content...");
        anVarL.ba();
    }

    @Override // com.google.android.gms.internal.fe.a
    public void a(fj fjVar) {
        synchronized (this.mH) {
            gr.S("Received ad response.");
            this.tn = fjVar;
            this.mH.notify();
        }
    }

    @Override // com.google.android.gms.internal.gf
    public void cx() {
        ay ayVarA;
        long j;
        JSONObject jSONObject;
        synchronized (this.mH) {
            gr.S("AdLoaderBackgroundTask started.");
            fh fhVar = new fh(this.tk, this.tl.C().a(this.mContext));
            int errorCode = -2;
            long jElapsedRealtime = -1;
            try {
                long jElapsedRealtime2 = SystemClock.elapsedRealtime();
                gf gfVarA = fe.a(this.mContext, fhVar, this);
                synchronized (this.tj) {
                    this.tm = gfVarA;
                    if (this.tm == null) {
                        throw new a("Could not start the ad request service.", 0);
                    }
                }
                e(jElapsedRealtime2);
                jElapsedRealtime = SystemClock.elapsedRealtime();
                cE();
                ayVarA = fhVar.lS.or != null ? a(fhVar) : null;
                try {
                    t(this.tn.uh);
                    j = jElapsedRealtime;
                } catch (a e) {
                    e = e;
                    errorCode = e.getErrorCode();
                    if (errorCode == 3 || errorCode == -1) {
                        gr.U(e.getMessage());
                    } else {
                        gr.W(e.getMessage());
                    }
                    if (this.tn == null) {
                        this.tn = new fj(errorCode);
                    } else {
                        this.tn = new fj(errorCode, this.tn.qA);
                    }
                    gq.wR.post(new Runnable() { // from class: com.google.android.gms.internal.fa.1
                        @Override // java.lang.Runnable
                        public void run() {
                            fa.this.onStop();
                        }
                    });
                    j = jElapsedRealtime;
                }
            } catch (a e2) {
                e = e2;
                ayVarA = null;
            }
            if (TextUtils.isEmpty(this.tn.ue)) {
                jSONObject = null;
            } else {
                try {
                    jSONObject = new JSONObject(this.tn.ue);
                } catch (Exception e3) {
                    gr.b("Error parsing the JSON for Active View.", e3);
                    jSONObject = null;
                }
            }
            final fy.a aVar = new fy.a(fhVar, this.tn, this.qi, ayVarA, errorCode, j, this.tn.ua, jSONObject);
            gq.wR.post(new Runnable() { // from class: com.google.android.gms.internal.fa.2
                @Override // java.lang.Runnable
                public void run() {
                    synchronized (fa.this.mH) {
                        fa.this.ti.a(aVar);
                    }
                }
            });
        }
    }

    @Override // com.google.android.gms.internal.gf
    public void onStop() {
        synchronized (this.tj) {
            if (this.tm != null) {
                this.tm.cancel();
            }
        }
    }
}
