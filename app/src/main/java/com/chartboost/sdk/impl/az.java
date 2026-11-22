package com.chartboost.sdk.impl;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import com.chartboost.sdk.Libraries.CBLogging;
import java.util.Observable;

/* JADX INFO: loaded from: classes.dex */
public class az extends Observable {
    private static az c = null;
    private static b d = b.CONNECTION_UNKNOWN;
    private boolean a = true;
    private boolean b = false;
    private a e;

    private az() {
        this.e = null;
        this.e = new a();
    }

    public static az a() {
        if (c == null) {
            c = new az();
        }
        return c;
    }

    public enum b {
        CONNECTION_UNKNOWN(-1),
        CONNECTION_ERROR(0),
        CONNECTION_WIFI(1),
        CONNECTION_MOBILE(2);

        private int e;

        b(int i) {
            this.e = i;
        }

        public int a() {
            return this.e;
        }
    }

    public int b() {
        return d.a();
    }

    private class a extends BroadcastReceiver {
        public a() {
        }

        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            az azVarA = az.a();
            azVarA.a(context);
            azVarA.notifyObservers();
        }
    }

    public void a(Context context) {
        try {
            NetworkInfo activeNetworkInfo = ((ConnectivityManager) context.getSystemService("connectivity")).getActiveNetworkInfo();
            if (activeNetworkInfo != null && activeNetworkInfo.isConnectedOrConnecting()) {
                a(true);
                if (activeNetworkInfo.getType() == 1) {
                    d = b.CONNECTION_WIFI;
                    CBLogging.a("CBReachability", "NETWORK TYPE: TYPE_WIFI");
                    return;
                } else {
                    if (activeNetworkInfo.getType() == 0) {
                        d = b.CONNECTION_MOBILE;
                        CBLogging.a("CBReachability", "NETWORK TYPE: TYPE_MOBILE");
                        return;
                    }
                    return;
                }
            }
            a(false);
            d = b.CONNECTION_ERROR;
            CBLogging.a("CBReachability", "NETWORK TYPE: NO Network");
        } catch (SecurityException e) {
            d = b.CONNECTION_UNKNOWN;
            throw new SecurityException("Chartboost SDK requires 'android.permission.ACCESS_NETWORK_STATE' permission set in your AndroidManifest.xml");
        }
    }

    @Override // java.util.Observable
    public void notifyObservers() {
        if (this.a) {
            setChanged();
            super.notifyObservers(this);
        }
    }

    public void a(boolean z) {
        this.a = z;
    }

    public boolean c() {
        return this.a;
    }

    public Intent b(Context context) {
        if (context == null || this.b) {
            return null;
        }
        b(true);
        CBLogging.a("CBReachability", "Network broadcast successfully registered");
        return context.registerReceiver(this.e, new IntentFilter("android.net.conn.CONNECTIVITY_CHANGE"));
    }

    public void c(Context context) {
        if (context != null && this.b) {
            context.unregisterReceiver(this.e);
            b(false);
            CBLogging.a("CBReachability", "Network broadcast successfully unregistered");
        }
    }

    public void b(boolean z) {
        this.b = z;
    }
}
