package com.flurry.sdk;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

/* JADX INFO: loaded from: classes.dex */
public class fj extends BroadcastReceiver {
    private static fj c;
    boolean a;
    boolean b;
    private boolean d;

    public static synchronized fj a() {
        if (c == null) {
            c = new fj();
        }
        return c;
    }

    public static synchronized void b() {
        if (c != null) {
            c.f();
        }
        c = null;
    }

    public enum a {
        NONE_OR_UNKNOWN(0),
        NETWORK_AVAILABLE(1),
        WIFI(2),
        CELL(3);

        private int e;

        a(int i) {
            this.e = i;
        }

        public int a() {
            return this.e;
        }
    }

    private fj() {
        this.d = false;
        Context contextC = fp.a().c();
        this.d = contextC.checkCallingOrSelfPermission("android.permission.ACCESS_NETWORK_STATE") == 0;
        this.b = a(contextC);
        if (this.d) {
            e();
        }
    }

    public boolean c() {
        return this.b;
    }

    private synchronized void e() {
        if (!this.a) {
            Context contextC = fp.a().c();
            this.b = a(contextC);
            contextC.registerReceiver(this, new IntentFilter("android.net.conn.CONNECTIVITY_CHANGE"));
            this.a = true;
        }
    }

    private synchronized void f() {
        if (this.a) {
            fp.a().c().unregisterReceiver(this);
            this.a = false;
        }
    }

    @Override // android.content.BroadcastReceiver
    public void onReceive(Context context, Intent intent) {
        boolean zA = a(context);
        if (this.b != zA) {
            this.b = zA;
            fi fiVar = new fi();
            fiVar.a = zA;
            fiVar.b = d();
            fiVar.b();
        }
    }

    private boolean a(Context context) {
        if (!this.d || context == null) {
            return true;
        }
        NetworkInfo activeNetworkInfo = g().getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }

    public a d() {
        if (!this.d) {
            return a.NONE_OR_UNKNOWN;
        }
        NetworkInfo activeNetworkInfo = g().getActiveNetworkInfo();
        if (activeNetworkInfo == null || !activeNetworkInfo.isConnected()) {
            return a.NONE_OR_UNKNOWN;
        }
        switch (activeNetworkInfo.getType()) {
            case 0:
            case 2:
            case 3:
            case 4:
            case 5:
                return a.CELL;
            case 1:
                return a.WIFI;
            case 6:
            case 7:
            default:
                if (activeNetworkInfo.isConnected()) {
                    return a.NETWORK_AVAILABLE;
                }
                return a.NONE_OR_UNKNOWN;
            case 8:
                return a.NONE_OR_UNKNOWN;
        }
    }

    private ConnectivityManager g() {
        return (ConnectivityManager) fp.a().c().getSystemService("connectivity");
    }
}
