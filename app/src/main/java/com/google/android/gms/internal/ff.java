package com.google.android.gms.internal;

import android.content.Context;
import android.os.Bundle;
import android.os.DeadObjectException;
import android.os.RemoteException;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;

/* JADX INFO: loaded from: classes.dex */
@ey
public abstract class ff extends gf {
    private final fh qh;
    private final fe.a tI;

    @ey
    public static final class a extends ff {
        private final Context mContext;

        public a(Context context, fh fhVar, fe.a aVar) {
            super(fhVar, aVar);
            this.mContext = context;
        }

        @Override // com.google.android.gms.internal.ff
        public void cJ() {
        }

        @Override // com.google.android.gms.internal.ff
        public fl cK() {
            Bundle bundleBN = ga.bN();
            return fq.a(this.mContext, new bm(bundleBN.getString("gads:sdk_core_location"), bundleBN.getString("gads:sdk_core_experiment_id"), bundleBN.getString("gads:block_autoclicks_experiment_id"), bundleBN.getString("gads:spam_app_context:experiment_id")), new co(), new fx());
        }
    }

    @ey
    public static final class b extends ff implements GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener {
        private final Object mH;
        private final fe.a tI;
        private final fg tJ;

        public b(Context context, fh fhVar, fe.a aVar) {
            super(fhVar, aVar);
            this.mH = new Object();
            this.tI = aVar;
            this.tJ = new fg(context, this, this, fhVar.lO.wU);
            this.tJ.connect();
        }

        @Override // com.google.android.gms.internal.ff
        public void cJ() {
            synchronized (this.mH) {
                if (this.tJ.isConnected() || this.tJ.isConnecting()) {
                    this.tJ.disconnect();
                }
            }
        }

        @Override // com.google.android.gms.internal.ff
        public fl cK() {
            fl flVarCL;
            synchronized (this.mH) {
                try {
                    flVarCL = this.tJ.cL();
                } catch (DeadObjectException | IllegalStateException e) {
                    flVarCL = null;
                }
            }
            return flVarCL;
        }

        @Override // com.google.android.gms.common.api.GoogleApiClient.ConnectionCallbacks
        public void onConnected(Bundle connectionHint) {
            start();
        }

        @Override // com.google.android.gms.common.api.GoogleApiClient.OnConnectionFailedListener, com.google.android.gms.common.GooglePlayServicesClient.OnConnectionFailedListener
        public void onConnectionFailed(ConnectionResult result) {
            this.tI.a(new fj(0));
        }

        @Override // com.google.android.gms.common.api.GoogleApiClient.ConnectionCallbacks
        public void onConnectionSuspended(int cause) {
            gr.S("Disconnected from remote ad request service.");
        }
    }

    public ff(fh fhVar, fe.a aVar) {
        this.qh = fhVar;
        this.tI = aVar;
    }

    private static fj a(fl flVar, fh fhVar) {
        try {
            return flVar.b(fhVar);
        } catch (RemoteException e) {
            gr.d("Could not fetch ad response from ad request service.", e);
            return null;
        } catch (NullPointerException e2) {
            gr.d("Could not fetch ad response from ad request service due to an Exception.", e2);
            return null;
        } catch (SecurityException e3) {
            gr.d("Could not fetch ad response from ad request service due to an Exception.", e3);
            return null;
        } catch (Throwable th) {
            ga.e(th);
            return null;
        }
    }

    public abstract void cJ();

    public abstract fl cK();

    @Override // com.google.android.gms.internal.gf
    public final void cx() {
        fj fjVarA;
        try {
            fl flVarCK = cK();
            if (flVarCK == null || (fjVarA = a(flVarCK, this.qh)) == null) {
                fjVarA = new fj(0);
            }
            cJ();
            this.tI.a(fjVarA);
        } catch (Throwable th) {
            cJ();
            throw th;
        }
    }

    @Override // com.google.android.gms.internal.gf
    public final void onStop() {
        cJ();
    }
}
