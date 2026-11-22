package com.applovin.impl.sdk;

import android.app.AlertDialog;

/* JADX INFO: loaded from: classes.dex */
class ao implements Runnable {
    final /* synthetic */ an a;

    ao(an anVar) {
        this.a = anVar;
    }

    @Override // java.lang.Runnable
    public void run() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this.a.c);
        builder.setTitle((CharSequence) this.a.a.a(bb.X));
        builder.setMessage((CharSequence) this.a.a.a(bb.Y));
        builder.setCancelable(false);
        builder.setPositiveButton((CharSequence) this.a.a.a(bb.Z), new ap(this));
        builder.setNegativeButton((CharSequence) this.a.a.a(bb.aa), new as(this));
        builder.show();
    }
}
