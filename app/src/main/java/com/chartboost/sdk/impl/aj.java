package com.chartboost.sdk.impl;

import android.content.Context;
import android.graphics.Point;
import android.view.MotionEvent;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;

/* JADX INFO: loaded from: classes.dex */
public abstract class aj extends com.chartboost.sdk.f {
    protected com.chartboost.sdk.Libraries.j G;
    protected com.chartboost.sdk.Libraries.j H;
    protected com.chartboost.sdk.Libraries.e.a I;
    protected String J;
    protected float K;
    private com.chartboost.sdk.Libraries.j k;
    private com.chartboost.sdk.Libraries.j l;

    public abstract class a extends com.chartboost.sdk.f.a {
        private boolean b;
        protected bk f;
        protected bl g;

        protected a(Context context) {
            super(context);
            this.b = false;
            setBackgroundColor(0);
            setLayoutParams(new RelativeLayout.LayoutParams(-1, -1));
            this.f = new bk(context);
            addView(this.f, new RelativeLayout.LayoutParams(-1, -1));
        }

        protected void d() {
            this.g = new bl(getContext()) { // from class: com.chartboost.sdk.impl.aj.a.1
                @Override // com.chartboost.sdk.impl.bl
                protected void a(MotionEvent motionEvent) {
                    a.this.g();
                }
            };
            addView(this.g);
        }

        @Override // com.chartboost.sdk.f.a
        protected void a(int i, int i2) {
            int iRound;
            int iRound2;
            if (!this.b) {
                d();
                this.b = true;
            }
            boolean zB = aj.this.a().b();
            com.chartboost.sdk.Libraries.j jVar = zB ? aj.this.k : aj.this.l;
            com.chartboost.sdk.Libraries.j jVar2 = zB ? aj.this.G : aj.this.H;
            RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(-2, -2);
            RelativeLayout.LayoutParams layoutParams2 = new RelativeLayout.LayoutParams(-2, -2);
            aj.this.a(layoutParams, jVar, 1.0f);
            aj.this.K = Math.min(Math.min(i / layoutParams.width, i2 / layoutParams.height), 1.0f);
            layoutParams.width = (int) (layoutParams.width * aj.this.K);
            layoutParams.height = (int) (layoutParams.height * aj.this.K);
            Point pointB = aj.this.b(zB ? "frame-portrait" : "frame-landscape");
            layoutParams.leftMargin = Math.round(((i - layoutParams.width) / 2.0f) + ((pointB.x / jVar.g()) * aj.this.K));
            layoutParams.topMargin = Math.round(((pointB.y / jVar.g()) * aj.this.K) + ((i2 - layoutParams.height) / 2.0f));
            aj.this.a(layoutParams2, jVar2, 1.0f);
            Point pointB2 = aj.this.b(zB ? "close-portrait" : "close-landscape");
            if (pointB2.x == 0 && pointB2.y == 0) {
                iRound = Math.round((-layoutParams2.width) / 2.0f) + layoutParams.leftMargin + layoutParams.width;
                iRound2 = layoutParams.topMargin + Math.round((-layoutParams2.height) / 2.0f);
            } else {
                iRound = Math.round(((layoutParams.leftMargin + (layoutParams.width / 2.0f)) + pointB2.x) - (layoutParams2.width / 2.0f));
                iRound2 = Math.round((pointB2.y + (layoutParams.topMargin + (layoutParams.height / 2.0f))) - (layoutParams2.height / 2.0f));
            }
            layoutParams2.leftMargin = Math.min(Math.max(0, iRound), i - layoutParams2.width);
            layoutParams2.topMargin = Math.min(Math.max(0, iRound2), i2 - layoutParams2.height);
            this.f.setLayoutParams(layoutParams);
            this.g.setLayoutParams(layoutParams2);
            this.f.setScaleType(ImageView.ScaleType.FIT_CENTER);
            this.f.a(jVar);
            this.g.a(jVar2);
        }

        protected void g() {
            aj.this.h();
        }

        @Override // com.chartboost.sdk.f.a
        public void b() {
            super.b();
            this.f = null;
            this.g = null;
        }
    }

    public aj(com.chartboost.sdk.Model.a aVar) {
        super(aVar);
        this.K = 1.0f;
        this.k = new com.chartboost.sdk.Libraries.j(this);
        this.l = new com.chartboost.sdk.Libraries.j(this);
        this.G = new com.chartboost.sdk.Libraries.j(this);
        this.H = new com.chartboost.sdk.Libraries.j(this);
    }

    @Override // com.chartboost.sdk.f
    public boolean a(com.chartboost.sdk.Libraries.e.a aVar) {
        if (!super.a(aVar)) {
            return false;
        }
        this.J = aVar.e("ad_id");
        this.I = aVar.a("ux");
        if (this.e.b("frame-portrait") || this.e.b("close-portrait")) {
            this.i = false;
        }
        if (this.e.b("frame-landscape") || this.e.b("close-landscape")) {
            this.j = false;
        }
        this.l.a("frame-landscape");
        this.k.a("frame-portrait");
        this.H.a("close-landscape");
        this.G.a("close-portrait");
        return true;
    }

    protected Point b(String str) {
        com.chartboost.sdk.Libraries.e.a aVarA = this.e.a(str).a("offset");
        return aVarA.c() ? new Point(aVarA.f("x"), aVarA.f("y")) : new Point(0, 0);
    }

    public void a(ViewGroup.LayoutParams layoutParams, com.chartboost.sdk.Libraries.j jVar, float f) {
        layoutParams.width = (int) ((jVar.b() / jVar.g()) * f);
        layoutParams.height = (int) ((jVar.c() / jVar.g()) * f);
    }

    @Override // com.chartboost.sdk.f
    public void d() {
        super.d();
        this.l.d();
        this.k.d();
        this.H.d();
        this.G.d();
        this.l = null;
        this.k = null;
        this.H = null;
        this.G = null;
    }
}
