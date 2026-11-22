package com.chartboost.sdk.impl;

import android.content.Context;
import android.view.View;

/* JADX INFO: loaded from: classes.dex */
public final class bj extends View {
    private boolean a;

    public bj(Context context) {
        super(context);
        this.a = false;
        setFocusable(false);
        setBackgroundColor(-1442840576);
    }

    public void a() {
        if (!this.a) {
            bi.a(true, this);
            this.a = true;
        }
    }
}
