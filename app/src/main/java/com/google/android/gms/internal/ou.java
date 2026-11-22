package com.google.android.gms.internal;

import android.os.Bundle;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GooglePlayServicesClient;

/* JADX INFO: loaded from: classes.dex */
public class ou implements GooglePlayServicesClient.ConnectionCallbacks, GooglePlayServicesClient.OnConnectionFailedListener {
    private final op.a ane;
    private ox amU = null;
    private boolean anf = true;

    public ou(op.a aVar) {
        this.ane = aVar;
    }

    public void T(boolean z) {
        this.anf = z;
    }

    public void a(ox oxVar) {
        this.amU = oxVar;
    }

    @Override // com.google.android.gms.common.GooglePlayServicesClient.ConnectionCallbacks
    public void onConnected(Bundle connectionHint) {
        this.amU.U(false);
        if (this.anf && this.ane != null) {
            this.ane.on();
        }
        this.anf = false;
    }

    @Override // com.google.android.gms.common.GooglePlayServicesClient.OnConnectionFailedListener
    public void onConnectionFailed(ConnectionResult result) {
        this.amU.U(true);
        if (this.anf && this.ane != null) {
            if (result.hasResolution()) {
                this.ane.d(result.getResolution());
            } else {
                this.ane.oo();
            }
        }
        this.anf = false;
    }

    @Override // com.google.android.gms.common.GooglePlayServicesClient.ConnectionCallbacks
    public void onDisconnected() {
        this.amU.U(true);
    }
}
