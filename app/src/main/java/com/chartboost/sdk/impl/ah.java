package com.chartboost.sdk.impl;

import android.content.Context;
import android.graphics.Point;
import android.support.v4.view.ViewCompat;
import android.view.MotionEvent;
import android.widget.ImageView;
import android.widget.RelativeLayout;

/* JADX INFO: loaded from: classes.dex */
public class ah extends aj {
    private com.chartboost.sdk.Libraries.j k;
    private com.chartboost.sdk.Libraries.j l;

    public class a extends aj.a {
        protected bl b;
        protected ImageView c;

        protected a(Context context) {
            super(context);
            this.b = new bl(context) { // from class: com.chartboost.sdk.impl.ah.a.1
                @Override // com.chartboost.sdk.impl.bl
                protected void a(MotionEvent motionEvent) {
                    a.this.a(motionEvent.getX(), motionEvent.getY());
                }
            };
            a(this.b);
            this.c = new ImageView(context);
            this.c.setBackgroundColor(ViewCompat.MEASURED_STATE_MASK);
            addView(this.c);
            addView(this.b);
        }

        protected void a(float f, float f2) {
            ah.this.a((String) null, (com.chartboost.sdk.Libraries.e.a) null);
        }

        @Override // com.chartboost.sdk.impl.aj.a, com.chartboost.sdk.f.a
        protected void a(int i, int i2) {
            super.a(i, i2);
            boolean zB = ah.this.a().b();
            com.chartboost.sdk.Libraries.j jVar = zB ? ah.this.k : ah.this.l;
            RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(-2, -2);
            ah.this.a(layoutParams, jVar, ah.this.K);
            Point pointB = ah.this.b(zB ? "ad-portrait" : "ad-landscape");
            layoutParams.leftMargin = Math.round(((i - layoutParams.width) / 2.0f) + ((pointB.x / jVar.g()) * ah.this.K));
            layoutParams.topMargin = Math.round(((pointB.y / jVar.g()) * ah.this.K) + ((i2 - layoutParams.height) / 2.0f));
            this.c.setLayoutParams(layoutParams);
            this.b.setLayoutParams(layoutParams);
            this.b.a(ImageView.ScaleType.FIT_CENTER);
            this.b.a(jVar);
        }

        @Override // com.chartboost.sdk.impl.aj.a, com.chartboost.sdk.f.a
        public void b() {
            super.b();
            this.b = null;
            this.c = null;
        }
    }

    public ah(com.chartboost.sdk.Model.a aVar) {
        super(aVar);
        this.k = new com.chartboost.sdk.Libraries.j(this);
        this.l = new com.chartboost.sdk.Libraries.j(this);
    }

    @Override // com.chartboost.sdk.f
    protected com.chartboost.sdk.f.a b(Context context) {
        return new a(context);
    }

    @Override // com.chartboost.sdk.impl.aj, com.chartboost.sdk.f
    public boolean a(com.chartboost.sdk.Libraries.e.a aVar) {
        if (!super.a(aVar)) {
            return false;
        }
        if (this.e.b("ad-portrait")) {
            this.i = false;
        }
        if (this.e.b("ad-landscape")) {
            this.j = false;
        }
        this.l.a("ad-landscape");
        this.k.a("ad-portrait");
        return true;
    }

    @Override // com.chartboost.sdk.impl.aj, com.chartboost.sdk.f
    public void d() {
        super.d();
        this.l.d();
        this.k.d();
        this.l = null;
        this.k = null;
    }
}
