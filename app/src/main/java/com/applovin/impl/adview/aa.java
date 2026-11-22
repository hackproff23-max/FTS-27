package com.applovin.impl.adview;

import android.view.View;

/* JADX INFO: loaded from: classes.dex */
class aa implements View.OnClickListener {
    final /* synthetic */ v a;

    aa(v vVar) {
        this.a = vVar;
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        this.a.dismiss();
    }
}
