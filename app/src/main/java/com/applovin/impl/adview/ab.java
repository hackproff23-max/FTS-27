package com.applovin.impl.adview;

import android.view.View;

/* JADX INFO: loaded from: classes.dex */
class ab implements View.OnClickListener {
    final /* synthetic */ s a;
    final /* synthetic */ v b;

    ab(v vVar, s sVar) {
        this.b = vVar;
        this.a = sVar;
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        this.a.performClick();
    }
}
