package com.google.android.gms.internal;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GooglePlayServicesClient;
import com.google.android.gms.common.api.GoogleApiClient;
import java.util.ArrayList;

/* JADX INFO: loaded from: classes.dex */
public final class jm {
    private final b MX;
    private final ArrayList<GoogleApiClient.ConnectionCallbacks> MY = new ArrayList<>();
    final ArrayList<GoogleApiClient.ConnectionCallbacks> MZ = new ArrayList<>();
    private boolean Na = false;
    private final ArrayList<GooglePlayServicesClient.OnConnectionFailedListener> Nb = new ArrayList<>();
    private final Handler mHandler;

    final class a extends Handler {
        public a(Looper looper) {
            super(looper);
        }

        @Override // android.os.Handler
        public void handleMessage(Message msg) {
            if (msg.what != 1) {
                Log.wtf("GmsClientEvents", "Don't know how to handle this message.");
                return;
            }
            synchronized (jm.this.MY) {
                if (jm.this.MX.gN() && jm.this.MX.isConnected() && jm.this.MY.contains(msg.obj)) {
                    ((GoogleApiClient.ConnectionCallbacks) msg.obj).onConnected(jm.this.MX.fX());
                }
            }
        }
    }

    public interface b {
        Bundle fX();

        boolean gN();

        boolean isConnected();
    }

    public jm(Context context, Looper looper, b bVar) {
        this.MX = bVar;
        this.mHandler = new a(looper);
    }

    public void aE(int i) {
        this.mHandler.removeMessages(1);
        synchronized (this.MY) {
            this.Na = true;
            for (GoogleApiClient.ConnectionCallbacks connectionCallbacks : new ArrayList(this.MY)) {
                if (!this.MX.gN()) {
                    break;
                } else if (this.MY.contains(connectionCallbacks)) {
                    connectionCallbacks.onConnectionSuspended(i);
                }
            }
            this.Na = false;
        }
    }

    public void b(ConnectionResult connectionResult) {
        this.mHandler.removeMessages(1);
        synchronized (this.Nb) {
            for (GooglePlayServicesClient.OnConnectionFailedListener onConnectionFailedListener : new ArrayList(this.Nb)) {
                if (!this.MX.gN()) {
                    return;
                }
                if (this.Nb.contains(onConnectionFailedListener)) {
                    onConnectionFailedListener.onConnectionFailed(connectionResult);
                }
            }
        }
    }

    protected void dU() {
        synchronized (this.MY) {
            f(this.MX.fX());
        }
    }

    public void f(Bundle bundle) {
        synchronized (this.MY) {
            jx.K(!this.Na);
            this.mHandler.removeMessages(1);
            this.Na = true;
            jx.K(this.MZ.size() == 0);
            for (GoogleApiClient.ConnectionCallbacks connectionCallbacks : new ArrayList(this.MY)) {
                if (!this.MX.gN() || !this.MX.isConnected()) {
                    break;
                } else if (!this.MZ.contains(connectionCallbacks)) {
                    connectionCallbacks.onConnected(bundle);
                }
            }
            this.MZ.clear();
            this.Na = false;
        }
    }

    public boolean isConnectionCallbacksRegistered(GoogleApiClient.ConnectionCallbacks listener) {
        boolean zContains;
        jx.i(listener);
        synchronized (this.MY) {
            zContains = this.MY.contains(listener);
        }
        return zContains;
    }

    public boolean isConnectionFailedListenerRegistered(GooglePlayServicesClient.OnConnectionFailedListener listener) {
        boolean zContains;
        jx.i(listener);
        synchronized (this.Nb) {
            zContains = this.Nb.contains(listener);
        }
        return zContains;
    }

    public void registerConnectionCallbacks(GoogleApiClient.ConnectionCallbacks listener) {
        jx.i(listener);
        synchronized (this.MY) {
            if (this.MY.contains(listener)) {
                Log.w("GmsClientEvents", "registerConnectionCallbacks(): listener " + listener + " is already registered");
            } else {
                this.MY.add(listener);
            }
        }
        if (this.MX.isConnected()) {
            this.mHandler.sendMessage(this.mHandler.obtainMessage(1, listener));
        }
    }

    public void registerConnectionFailedListener(GooglePlayServicesClient.OnConnectionFailedListener listener) {
        jx.i(listener);
        synchronized (this.Nb) {
            if (this.Nb.contains(listener)) {
                Log.w("GmsClientEvents", "registerConnectionFailedListener(): listener " + listener + " is already registered");
            } else {
                this.Nb.add(listener);
            }
        }
    }

    public void unregisterConnectionCallbacks(GoogleApiClient.ConnectionCallbacks listener) {
        jx.i(listener);
        synchronized (this.MY) {
            if (this.MY != null) {
                if (!this.MY.remove(listener)) {
                    Log.w("GmsClientEvents", "unregisterConnectionCallbacks(): listener " + listener + " not found");
                } else if (this.Na) {
                    this.MZ.add(listener);
                }
            }
        }
    }

    public void unregisterConnectionFailedListener(GooglePlayServicesClient.OnConnectionFailedListener listener) {
        jx.i(listener);
        synchronized (this.Nb) {
            if (this.Nb != null && !this.Nb.remove(listener)) {
                Log.w("GmsClientEvents", "unregisterConnectionFailedListener(): listener " + listener + " not found");
            }
        }
    }
}
