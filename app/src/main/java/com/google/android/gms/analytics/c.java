package com.google.android.gms.analytics;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.os.RemoteException;
import com.google.android.gms.common.GooglePlayServicesUtil;
import com.google.android.gms.internal.ha;
import com.google.android.gms.internal.hb;
import java.util.List;
import java.util.Map;

/* JADX INFO: loaded from: classes.dex */
class c implements com.google.android.gms.analytics.b {
    private Context mContext;
    private ServiceConnection xV;
    private b xW;
    private InterfaceC0035c xX;
    private hb xY;

    final class a implements ServiceConnection {
        a() {
        }

        @Override // android.content.ServiceConnection
        public void onServiceConnected(ComponentName component, IBinder binder) {
            ae.V("service connected, binder: " + binder);
            try {
                if ("com.google.android.gms.analytics.internal.IAnalyticsService".equals(binder.getInterfaceDescriptor())) {
                    ae.V("bound to service");
                    c.this.xY = hb.a.D(binder);
                    c.this.dT();
                    return;
                }
            } catch (RemoteException e) {
            }
            try {
                c.this.mContext.unbindService(this);
            } catch (IllegalArgumentException e2) {
            }
            c.this.xV = null;
            c.this.xX.a(2, null);
        }

        @Override // android.content.ServiceConnection
        public void onServiceDisconnected(ComponentName component) {
            ae.V("service disconnected: " + component);
            c.this.xV = null;
            c.this.xW.onDisconnected();
        }
    }

    public interface b {
        void onConnected();

        void onDisconnected();
    }

    /* JADX INFO: renamed from: com.google.android.gms.analytics.c$c, reason: collision with other inner class name */
    public interface InterfaceC0035c {
        void a(int i, Intent intent);
    }

    public c(Context context, b bVar, InterfaceC0035c interfaceC0035c) {
        this.mContext = context;
        if (bVar == null) {
            throw new IllegalArgumentException("onConnectedListener cannot be null");
        }
        this.xW = bVar;
        if (interfaceC0035c == null) {
            throw new IllegalArgumentException("onConnectionFailedListener cannot be null");
        }
        this.xX = interfaceC0035c;
    }

    private hb dR() {
        dS();
        return this.xY;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void dT() {
        dU();
    }

    private void dU() {
        this.xW.onConnected();
    }

    @Override // com.google.android.gms.analytics.b
    public void a(Map<String, String> map, long j, String str, List<ha> list) {
        try {
            dR().a(map, j, str, list);
        } catch (RemoteException e) {
            ae.T("sendHit failed: " + e);
        }
    }

    @Override // com.google.android.gms.analytics.b
    public void connect() {
        Intent intent = new Intent("com.google.android.gms.analytics.service.START");
        intent.setComponent(new ComponentName(GooglePlayServicesUtil.GOOGLE_PLAY_SERVICES_PACKAGE, "com.google.android.gms.analytics.service.AnalyticsService"));
        intent.putExtra("app_package_name", this.mContext.getPackageName());
        if (this.xV != null) {
            ae.T("Calling connect() while still connected, missing disconnect().");
            return;
        }
        this.xV = new a();
        boolean zBindService = this.mContext.bindService(intent, this.xV, 129);
        ae.V("connect: bindService returned " + zBindService + " for " + intent);
        if (zBindService) {
            return;
        }
        this.xV = null;
        this.xX.a(1, null);
    }

    @Override // com.google.android.gms.analytics.b
    public void dQ() {
        try {
            dR().dQ();
        } catch (RemoteException e) {
            ae.T("clear hits failed: " + e);
        }
    }

    protected void dS() {
        if (!isConnected()) {
            throw new IllegalStateException("Not connected. Call connect() and wait for onConnected() to be called.");
        }
    }

    @Override // com.google.android.gms.analytics.b
    public void disconnect() {
        this.xY = null;
        if (this.xV != null) {
            try {
                this.mContext.unbindService(this.xV);
            } catch (IllegalArgumentException e) {
            } catch (IllegalStateException e2) {
            }
            this.xV = null;
            this.xW.onDisconnected();
        }
    }

    public boolean isConnected() {
        return this.xY != null;
    }
}
