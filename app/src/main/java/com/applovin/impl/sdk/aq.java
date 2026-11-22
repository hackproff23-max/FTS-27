package com.applovin.impl.sdk;

import java.util.TimerTask;

/* JADX INFO: loaded from: classes.dex */
class aq extends TimerTask {
    final /* synthetic */ ap a;

    aq(ap apVar) {
        this.a = apVar;
    }

    @Override // java.util.TimerTask, java.lang.Runnable
    public void run() {
        this.a.a.a.c.runOnUiThread(new ar(this));
    }
}
