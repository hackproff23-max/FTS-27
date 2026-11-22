package com.google.android.gms.analytics;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;

/* JADX INFO: loaded from: classes.dex */
class u extends BroadcastReceiver {
    static final String yR = u.class.getName();
    private final aj yS;

    u(aj ajVar) {
        this.yS = ajVar;
    }

    @Override // android.content.BroadcastReceiver
    public void onReceive(Context ctx, Intent intent) {
        String action = intent.getAction();
        if ("android.net.conn.CONNECTIVITY_CHANGE".equals(action)) {
            this.yS.C(intent.getBooleanExtra("noConnectivity", false) ? false : true);
        } else {
            if (!"com.google.analytics.RADIO_POWERED".equals(action) || intent.hasExtra(yR)) {
                return;
            }
            this.yS.ey();
        }
    }

    public void z(Context context) {
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("android.net.conn.CONNECTIVITY_CHANGE");
        context.registerReceiver(this, intentFilter);
        IntentFilter intentFilter2 = new IntentFilter();
        intentFilter2.addAction("com.google.analytics.RADIO_POWERED");
        intentFilter2.addCategory(context.getPackageName());
        context.registerReceiver(this, intentFilter2);
    }
}
