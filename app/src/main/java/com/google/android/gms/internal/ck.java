package com.google.android.gms.internal;

import android.content.Context;
import android.os.Bundle;
import android.os.RemoteException;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GooglePlayServicesUtil;
import com.google.android.gms.common.api.GoogleApiClient;

/* JADX INFO: loaded from: classes.dex */
@ey
public final class ck {

    public static final class a implements GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener {
        private final Object mH;
        private final b qe;
        private final cl qf;

        public a(Context context, b bVar) {
            this(context, bVar, false);
        }

        a(Context context, b bVar, boolean z) {
            this.mH = new Object();
            this.qe = bVar;
            this.qf = new cl(context, this, this, GooglePlayServicesUtil.GOOGLE_PLAY_SERVICES_VERSION_CODE);
            if (z) {
                return;
            }
            this.qf.connect();
        }

        /* JADX WARN: Code duplicated, block: B:40:0x0083  */
        @Override // com.google.android.gms.common.api.GoogleApiClient.ConnectionCallbacks
        public void onConnected(Bundle connectionHint) {
            Bundle bundleBN;
            Bundle bundleBy = bn.by();
            synchronized (this.mH) {
                try {
                    try {
                        try {
                            cm cmVarBM = this.qf.bM();
                            bundleBN = cmVarBM != null ? cmVarBM.bN() : bundleBy;
                            if (this.qf.isConnected() || this.qf.isConnecting()) {
                                this.qf.disconnect();
                            }
                        } catch (IllegalStateException e) {
                            gr.d("Error when get Gservice values", e);
                            if (this.qf.isConnected() || this.qf.isConnecting()) {
                                this.qf.disconnect();
                                bundleBN = bundleBy;
                            } else {
                                bundleBN = bundleBy;
                            }
                        }
                    } catch (RemoteException e2) {
                        gr.d("Error when get Gservice values", e2);
                        if (this.qf.isConnected() || this.qf.isConnecting()) {
                            this.qf.disconnect();
                            bundleBN = bundleBy;
                        } else {
                            bundleBN = bundleBy;
                        }
                    }
                } catch (Throwable th) {
                    if (this.qf.isConnected() || this.qf.isConnecting()) {
                        this.qf.disconnect();
                    }
                    throw th;
                }
            }
            this.qe.a(bundleBN);
        }

        @Override // com.google.android.gms.common.api.GoogleApiClient.OnConnectionFailedListener, com.google.android.gms.common.GooglePlayServicesClient.OnConnectionFailedListener
        public void onConnectionFailed(ConnectionResult result) {
            this.qe.a(bn.by());
        }

        @Override // com.google.android.gms.common.api.GoogleApiClient.ConnectionCallbacks
        public void onConnectionSuspended(int cause) {
            gr.S("Disconnected from remote ad request service.");
        }
    }

    public interface b {
        void a(Bundle bundle);
    }

    public static void a(Context context, b bVar) {
        if (GooglePlayServicesUtil.isGooglePlayServicesAvailable(context) != 0) {
            bVar.a(bn.by());
        } else {
            new a(context, bVar);
        }
    }
}
