package com.applovin.impl.sdk;

import android.content.DialogInterface;

/* JADX INFO: loaded from: classes.dex */
class ap implements DialogInterface.OnClickListener {
    final /* synthetic */ ao a;

    ap(ao aoVar) {
        this.a = aoVar;
    }

    @Override // android.content.DialogInterface.OnClickListener
    public void onClick(DialogInterface dialogInterface, int i) {
        dialogInterface.dismiss();
        this.a.a.h.schedule(new aq(this), 200L);
    }
}
