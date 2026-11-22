package com.applovin.impl.sdk;

import android.content.DialogInterface;

/* JADX INFO: loaded from: classes.dex */
class as implements DialogInterface.OnClickListener {
    final /* synthetic */ ao a;

    as(ao aoVar) {
        this.a = aoVar;
    }

    @Override // android.content.DialogInterface.OnClickListener
    public void onClick(DialogInterface dialogInterface, int i) {
        dialogInterface.dismiss();
        this.a.a.b.a(this.a.a.g);
    }
}
