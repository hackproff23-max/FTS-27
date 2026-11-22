package com.google.android.gms.analytics;

import android.util.Log;

/* JADX INFO: loaded from: classes.dex */
class p implements Logger {
    private int yM = 2;

    p() {
    }

    private String af(String str) {
        return Thread.currentThread().toString() + ": " + str;
    }

    @Override // com.google.android.gms.analytics.Logger
    public void error(Exception exception) {
        if (this.yM <= 3) {
            Log.e("GAV4", null, exception);
        }
    }

    @Override // com.google.android.gms.analytics.Logger
    public void error(String msg) {
        if (this.yM <= 3) {
            Log.e("GAV4", af(msg));
        }
    }

    @Override // com.google.android.gms.analytics.Logger
    public int getLogLevel() {
        return this.yM;
    }

    @Override // com.google.android.gms.analytics.Logger
    public void info(String msg) {
        if (this.yM <= 1) {
            Log.i("GAV4", af(msg));
        }
    }

    @Override // com.google.android.gms.analytics.Logger
    public void setLogLevel(int level) {
        this.yM = level;
    }

    @Override // com.google.android.gms.analytics.Logger
    public void verbose(String msg) {
        if (this.yM <= 0) {
            Log.v("GAV4", af(msg));
        }
    }

    @Override // com.google.android.gms.analytics.Logger
    public void warn(String msg) {
        if (this.yM <= 2) {
            Log.w("GAV4", af(msg));
        }
    }
}
