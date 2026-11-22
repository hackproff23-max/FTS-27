package com.chartboost.sdk.impl;

import android.content.Context;
import android.view.MotionEvent;
import android.view.View;
import android.widget.RelativeLayout;

/* JADX INFO: loaded from: classes.dex */
public final class bq extends RelativeLayout {
    private com.chartboost.sdk.f.a a;
    private bj b;
    private bj c;
    private bp d;
    private com.chartboost.sdk.Model.a e;

    public bq(Context context, com.chartboost.sdk.Model.a aVar) {
        super(context);
        this.e = null;
        this.e = aVar;
        this.b = new bj(context);
        addView(this.b, new RelativeLayout.LayoutParams(-1, -1));
        this.c = new bj(context);
        addView(this.c, new RelativeLayout.LayoutParams(-1, -1));
        this.c.setVisibility(8);
    }

    public void a() {
        if (this.a == null) {
            this.a = this.e.l();
            if (this.a != null) {
                addView(this.a, new RelativeLayout.LayoutParams(-1, -1));
                this.a.a();
            }
        }
        c();
    }

    public void b() {
        boolean z = !this.e.j;
        this.e.j = true;
        if (this.d == null) {
            this.d = new bp(getContext());
            this.d.setVisibility(8);
            addView(this.d, new RelativeLayout.LayoutParams(-1, -1));
        } else {
            this.c.bringToFront();
            this.c.setVisibility(0);
            this.c.a();
            bi.a(false, this.b);
            this.d.bringToFront();
            this.d.a();
        }
        if (!g()) {
            this.d.setVisibility(0);
            if (z) {
                e().a();
                bi.a(true, this.d);
            }
        }
    }

    public void c() {
        if (this.d != null) {
            this.d.clearAnimation();
            this.d.setVisibility(8);
        }
    }

    public void d() {
    }

    @Override // android.view.View
    public boolean onTouchEvent(MotionEvent ev) {
        return true;
    }

    public bj e() {
        return this.b;
    }

    public View f() {
        return this.a;
    }

    public boolean g() {
        return this.d != null && this.d.getVisibility() == 0;
    }

    public com.chartboost.sdk.Model.a h() {
        return this.e;
    }
}
