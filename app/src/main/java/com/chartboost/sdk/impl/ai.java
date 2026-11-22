package com.chartboost.sdk.impl;

import android.content.Context;
import android.graphics.Point;
import android.view.MotionEvent;
import android.view.View;
import android.widget.RelativeLayout;
import com.chartboost.sdk.Libraries.CBLogging;
import com.chartboost.sdk.Libraries.CBUtility;
import com.chartboost.sdk.Model.CBError;
import com.unity3d.ads.android.properties.UnityAdsConstants;

/* JADX INFO: loaded from: classes.dex */
public class ai extends ah {
    protected com.chartboost.sdk.Libraries.j A;
    protected com.chartboost.sdk.Libraries.j B;
    protected com.chartboost.sdk.Libraries.j C;
    protected boolean D;
    protected boolean E;
    protected boolean F;
    private boolean L;
    private boolean M;
    private boolean N;
    private boolean O;
    private boolean P;
    protected b k;
    protected int l;
    protected String m;
    protected int n;
    protected int o;
    protected boolean p;
    protected boolean q;
    protected boolean r;
    protected boolean s;
    protected boolean t;
    protected int u;
    protected com.chartboost.sdk.Libraries.j v;
    protected com.chartboost.sdk.Libraries.j w;
    protected com.chartboost.sdk.Libraries.j x;
    protected com.chartboost.sdk.Libraries.j y;
    protected com.chartboost.sdk.Libraries.j z;

    protected enum b {
        REWARD_OFFER,
        VIDEO_PLAYING,
        POST_VIDEO
    }

    public class a extends ah.a {
        private bl i;
        private ap j;
        private am k;
        private ag l;
        private ak m;
        private bl n;

        private a(Context context) {
            super(context);
            if (ai.this.f.e == com.chartboost.sdk.Model.a.d.INTERSTITIAL_REWARD_VIDEO) {
                this.k = new am(context, ai.this);
                this.k.setVisibility(8);
                addView(this.k);
            }
            this.j = new ap(context, ai.this);
            this.j.setVisibility(8);
            addView(this.j);
            this.l = new ag(context, ai.this);
            this.l.setVisibility(8);
            addView(this.l);
            if (ai.this.f.e == com.chartboost.sdk.Model.a.d.INTERSTITIAL_REWARD_VIDEO) {
                this.m = new ak(context, ai.this);
                this.m.setVisibility(8);
                addView(this.m);
            }
            this.i = new bl(getContext()) { // from class: com.chartboost.sdk.impl.ai.a.1
                @Override // com.chartboost.sdk.impl.bl
                protected void a(MotionEvent motionEvent) {
                    if (ai.this.f.e == com.chartboost.sdk.Model.a.d.INTERSTITIAL_REWARD_VIDEO) {
                        a.this.m.a(false);
                    }
                    if (ai.this.k == b.VIDEO_PLAYING) {
                        a.this.d(false);
                    }
                    com.chartboost.sdk.Tracking.a.c(ai.this.m, ai.this.f.p());
                    a.this.c(true);
                }
            };
            this.i.setVisibility(8);
            addView(this.i);
            this.n = new bl(getContext()) { // from class: com.chartboost.sdk.impl.ai.a.2
                @Override // com.chartboost.sdk.impl.bl
                protected void a(MotionEvent motionEvent) {
                    a.this.g();
                }
            };
            this.n.setVisibility(8);
            addView(this.n);
            if (ai.this.I.a("progress").c("background-color") && ai.this.I.a("progress").c("border-color") && ai.this.I.a("progress").c("progress-color") && ai.this.I.a("progress").c("radius")) {
                ai.this.D = true;
                al alVarC = this.j.c();
                alVarC.a(com.chartboost.sdk.f.a(ai.this.I.a("progress").e("background-color")));
                alVarC.b(com.chartboost.sdk.f.a(ai.this.I.a("progress").e("border-color")));
                alVarC.c(com.chartboost.sdk.f.a(ai.this.I.a("progress").e("progress-color")));
                alVarC.b(ai.this.I.a("progress").a("radius").j());
            }
            if (ai.this.I.a("video-controls-background").c("color")) {
                this.j.a(com.chartboost.sdk.f.a(ai.this.I.a("video-controls-background").e("color")));
            }
            if (ai.this.f.e == com.chartboost.sdk.Model.a.d.INTERSTITIAL_REWARD_VIDEO && ai.this.r) {
                this.l.a(ai.this.I.a("post-video-toaster").e("title"), ai.this.I.a("post-video-toaster").e("tagline"));
            }
            if (ai.this.f.e == com.chartboost.sdk.Model.a.d.INTERSTITIAL_REWARD_VIDEO && ai.this.q) {
                this.k.a(ai.this.I.a("confirmation").e("text"), com.chartboost.sdk.f.a(ai.this.I.a("confirmation").e("color")));
            }
            if (ai.this.f.e == com.chartboost.sdk.Model.a.d.INTERSTITIAL_REWARD_VIDEO && ai.this.s) {
                this.m.a(ai.this.I.a("post-video-reward-toaster").a("position").equals("inside-top") ? an.a.TOP : an.a.BOTTOM);
                this.m.a(ai.this.I.a("post-video-reward-toaster").e("text"));
                if (ai.this.A.e()) {
                    this.m.a(ai.this.C);
                }
            }
            if (ai.this.e.a("video-click-button").b()) {
                this.j.d();
            }
            this.j.c(ai.this.I.i("video-progress-timer-enabled"));
            if (ai.this.F || ai.this.E) {
                this.c.setVisibility(4);
            }
            ai.this.m = ai.this.e.a(ai.this.a().b() ? "video-portrait" : "video-landscape").e("id");
            this.j.a(be.a(ai.this.m));
        }

        @Override // com.chartboost.sdk.impl.aj.a
        protected void d() {
            super.d();
            if (ai.this.k == b.REWARD_OFFER && (!ai.this.q || ai.this.m())) {
                c(false);
            } else {
                a(ai.this.k, false);
            }
        }

        public void e() {
            d(true);
            this.j.h();
            ai.this.l++;
            if (ai.this.l <= 1 && !ai.this.P) {
                ai.this.w();
                if (ai.this.n >= 1) {
                    ai.this.f.f();
                }
            }
        }

        @Override // com.chartboost.sdk.impl.ah.a, com.chartboost.sdk.impl.aj.a, com.chartboost.sdk.f.a
        protected void a(int i, int i2) {
            super.a(i, i2);
            a(ai.this.k, false);
            boolean zB = ai.this.a().b();
            RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(-2, -2);
            RelativeLayout.LayoutParams layoutParams2 = new RelativeLayout.LayoutParams(-1, -1);
            RelativeLayout.LayoutParams layoutParams3 = new RelativeLayout.LayoutParams(-1, -1);
            RelativeLayout.LayoutParams layoutParams4 = new RelativeLayout.LayoutParams(-1, -1);
            RelativeLayout.LayoutParams layoutParams5 = (RelativeLayout.LayoutParams) this.f.getLayoutParams();
            ai.this.a(layoutParams, zB ? ai.this.w : ai.this.v, 1.0f);
            Point pointB = ai.this.b(zB ? "replay-portrait" : "replay-landscape");
            int iRound = Math.round(((layoutParams5.leftMargin + (layoutParams5.width / 2.0f)) + pointB.x) - (layoutParams.width / 2.0f));
            int iRound2 = Math.round((((layoutParams5.height / 2.0f) + layoutParams5.topMargin) + pointB.y) - (layoutParams.height / 2.0f));
            layoutParams.leftMargin = Math.min(Math.max(0, iRound), i - layoutParams.width);
            layoutParams.topMargin = Math.min(Math.max(0, iRound2), i2 - layoutParams.height);
            this.i.bringToFront();
            if (zB) {
                this.i.a(ai.this.w);
            } else {
                this.i.a(ai.this.v);
            }
            RelativeLayout.LayoutParams layoutParams6 = (RelativeLayout.LayoutParams) this.b.getLayoutParams();
            if (!ai.this.r()) {
                layoutParams2.width = layoutParams6.width;
                layoutParams2.height = layoutParams6.height;
                layoutParams2.leftMargin = layoutParams6.leftMargin;
                layoutParams2.topMargin = layoutParams6.topMargin;
                layoutParams3.width = layoutParams6.width;
                layoutParams3.height = layoutParams6.height;
                layoutParams3.leftMargin = layoutParams6.leftMargin;
                layoutParams3.topMargin = layoutParams6.topMargin;
            } else {
                RelativeLayout.LayoutParams layoutParams7 = new RelativeLayout.LayoutParams(-2, -2);
                com.chartboost.sdk.Libraries.j jVar = zB ? ai.this.G : ai.this.H;
                ai.this.a(layoutParams7, jVar, 1.0f);
                layoutParams7.leftMargin = 0;
                layoutParams7.topMargin = 0;
                layoutParams7.addRule(11);
                this.n.setLayoutParams(layoutParams7);
                this.n.a(jVar);
            }
            layoutParams4.width = layoutParams6.width;
            layoutParams4.height = 72;
            layoutParams4.leftMargin = layoutParams6.leftMargin;
            layoutParams4.topMargin = (layoutParams6.height + layoutParams6.topMargin) - 72;
            if (ai.this.f.e == com.chartboost.sdk.Model.a.d.INTERSTITIAL_REWARD_VIDEO) {
                this.k.setLayoutParams(layoutParams2);
            }
            this.j.setLayoutParams(layoutParams3);
            this.l.setLayoutParams(layoutParams4);
            this.i.setLayoutParams(layoutParams);
            if (ai.this.f.e == com.chartboost.sdk.Model.a.d.INTERSTITIAL_REWARD_VIDEO) {
                this.k.a();
            }
            this.j.a();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void c(boolean z) {
            if (ai.this.k != b.VIDEO_PLAYING) {
                if (ai.this.q) {
                    com.chartboost.sdk.Tracking.a.b("integrated", ai.this.J);
                    a(b.REWARD_OFFER, z);
                    return;
                }
                a(b.VIDEO_PLAYING, z);
                if (ai.this.l < 1 && ai.this.I.a("timer").c("delay")) {
                    Object[] objArr = new Object[1];
                    objArr[0] = ai.this.p ? "visible" : UnityAdsConstants.UNITY_ADS_GOOGLE_ANALYTICS_EVENT_VIDEOABORT_HIDDEN;
                    CBLogging.c("InterstitialVideoViewProtocol", String.format("controls starting %s, setting timer", objArr));
                    this.j.a(ai.this.p);
                    ai.this.a(this.j, new Runnable() { // from class: com.chartboost.sdk.impl.ai.a.3
                        @Override // java.lang.Runnable
                        public void run() {
                            Object[] objArr2 = new Object[1];
                            objArr2[0] = ai.this.p ? UnityAdsConstants.UNITY_ADS_GOOGLE_ANALYTICS_EVENT_VIDEOABORT_HIDDEN : "shown";
                            CBLogging.c("InterstitialVideoViewProtocol", String.format("controls %s automatically from timer", objArr2));
                            a.this.j.a(!ai.this.p, true);
                            ai.this.h.remove(Integer.valueOf(a.this.j.hashCode()));
                        }
                    }, Math.round(1000.0d * ai.this.I.a("timer").g("delay")));
                } else {
                    this.j.a(!ai.this.p);
                }
                com.chartboost.sdk.Tracking.a.a(ai.this.m, ai.this.J, ai.this.l);
                this.j.e();
                if (ai.this.l <= 1) {
                    ai.this.f.g();
                }
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void d(boolean z) {
            this.j.f();
            com.chartboost.sdk.Tracking.a.d(ai.this.m, ai.this.J);
            if (ai.this.k == b.VIDEO_PLAYING && z) {
                if (ai.this.l < 1 && ai.this.I.c("post-video-reward-toaster") && ai.this.s && ai.this.A.e() && ai.this.B.e()) {
                    e(true);
                }
                a(b.POST_VIDEO, true);
                if (CBUtility.c().b()) {
                    requestLayout();
                }
            }
        }

        private void e(boolean z) {
            if (z) {
                this.m.a(true);
            } else {
                this.m.setVisibility(0);
            }
            com.chartboost.sdk.f.a.postDelayed(new Runnable() { // from class: com.chartboost.sdk.impl.ai.a.4
                @Override // java.lang.Runnable
                public void run() {
                    a.this.m.a(false);
                }
            }, 2500L);
        }

        private void a(b bVar, boolean z) {
            ai.this.k = bVar;
            switch (bVar) {
                case REWARD_OFFER:
                    ai.this.a(!ai.this.r(), this.b, z);
                    if (ai.this.f.e == com.chartboost.sdk.Model.a.d.INTERSTITIAL_REWARD_VIDEO) {
                        ai.this.a(true, (View) this.k, z);
                    }
                    ai.this.a(false, (View) this.j, z);
                    ai.this.a(false, (View) this.i, z);
                    ai.this.a(false, (View) this.l, z);
                    this.b.setEnabled(false);
                    this.i.setEnabled(false);
                    this.j.setEnabled(false);
                    break;
                case VIDEO_PLAYING:
                    ai.this.a(false, (View) this.b, z);
                    if (ai.this.f.e == com.chartboost.sdk.Model.a.d.INTERSTITIAL_REWARD_VIDEO) {
                        ai.this.a(false, (View) this.k, z);
                    }
                    ai.this.a(true, (View) this.j, z);
                    ai.this.a(false, (View) this.i, z);
                    ai.this.a(false, (View) this.l, z);
                    this.b.setEnabled(true);
                    this.i.setEnabled(false);
                    this.j.setEnabled(true);
                    break;
                case POST_VIDEO:
                    ai.this.a(true, (View) this.b, z);
                    if (ai.this.f.e == com.chartboost.sdk.Model.a.d.INTERSTITIAL_REWARD_VIDEO) {
                        ai.this.a(false, (View) this.k, z);
                    }
                    ai.this.a(false, (View) this.j, z);
                    ai.this.a(true, (View) this.i, z);
                    ai.this.a(ai.this.B.e() && ai.this.A.e() && ai.this.r, this.l, z);
                    this.i.setEnabled(true);
                    this.b.setEnabled(true);
                    this.j.setEnabled(false);
                    if (ai.this.t) {
                        e(false);
                    }
                    break;
            }
            boolean zF = f();
            bl blVarB = b(true);
            blVarB.setEnabled(zF);
            ai.this.a(zF, blVarB, z);
            bl blVarB2 = b(false);
            blVarB2.setEnabled(false);
            ai.this.a(false, (View) blVarB2, z);
            if (ai.this.F || ai.this.E) {
                ai.this.a(!ai.this.r(), this.c, z);
            }
            ai.this.a(!ai.this.r(), this.f, z);
            a(bVar != b.REWARD_OFFER);
        }

        protected boolean f() {
            if (ai.this.k == b.VIDEO_PLAYING && ai.this.l < 1) {
                float fA = ai.this.e.a("close-" + (ai.this.a().b() ? "portrait" : "landscape")).a("delay").a(-1.0f);
                int iRound = fA >= 0.0f ? Math.round(fA * 1000.0f) : -1;
                ai.this.u = iRound;
                if (iRound < 0) {
                    return false;
                }
                if (iRound > this.j.b().d()) {
                    return false;
                }
            }
            return true;
        }

        @Override // com.chartboost.sdk.impl.ah.a, com.chartboost.sdk.impl.aj.a, com.chartboost.sdk.f.a
        public void b() {
            ai.this.l();
            super.b();
        }

        @Override // com.chartboost.sdk.impl.aj.a
        protected void g() {
            if (ai.this.k == b.VIDEO_PLAYING && ai.this.I.a("cancel-popup").c("title") && ai.this.I.a("cancel-popup").c("text") && ai.this.I.a("cancel-popup").c("cancel") && ai.this.I.a("cancel-popup").c("confirm")) {
                this.j.g();
                if (ai.this.l < 1) {
                    ai.this.n();
                    return;
                }
            }
            if (ai.this.k == b.VIDEO_PLAYING) {
                d(false);
                this.j.h();
                if (ai.this.l < 1) {
                    ai.this.l++;
                    ai.this.w();
                    ai.this.f.f();
                }
            }
            com.chartboost.sdk.f.a.post(new Runnable() { // from class: com.chartboost.sdk.impl.ai.a.5
                @Override // java.lang.Runnable
                public void run() {
                    ai.this.h();
                }
            });
            com.chartboost.sdk.Tracking.a.b(ai.this.m, ai.this.J, ai.this.u);
        }

        @Override // com.chartboost.sdk.impl.ah.a
        protected void a(float f, float f2) {
            if ((!ai.this.p || ai.this.k != b.VIDEO_PLAYING) && ai.this.k != b.REWARD_OFFER) {
                h();
                com.chartboost.sdk.Tracking.a.a("insterstitial", ai.this.m, ai.this.f.p(), (int) f, (int) f2);
            }
        }

        protected void h() {
            if (ai.this.k == b.VIDEO_PLAYING) {
                d(false);
            }
            ai.this.a((String) null, (com.chartboost.sdk.Libraries.e.a) null);
        }

        protected void i() {
            com.chartboost.sdk.Tracking.a.d("integrated", ai.this.J, true);
            ai.this.q = false;
            c(true);
        }

        public bl b(boolean z) {
            return (!(ai.this.r() && z) && (ai.this.r() || z)) ? this.g : this.n;
        }
    }

    public ai(com.chartboost.sdk.Model.a aVar) {
        super(aVar);
        this.k = b.REWARD_OFFER;
        this.L = true;
        this.M = false;
        this.N = false;
        this.n = 0;
        this.o = 0;
        this.O = false;
        this.P = false;
        this.t = false;
        this.u = 0;
        this.D = false;
        this.E = false;
        this.F = false;
        this.k = b.REWARD_OFFER;
        this.v = new com.chartboost.sdk.Libraries.j(this);
        this.w = new com.chartboost.sdk.Libraries.j(this);
        this.x = new com.chartboost.sdk.Libraries.j(this);
        this.y = new com.chartboost.sdk.Libraries.j(this);
        this.z = new com.chartboost.sdk.Libraries.j(this);
        this.A = new com.chartboost.sdk.Libraries.j(this);
        this.B = new com.chartboost.sdk.Libraries.j(this);
        this.C = new com.chartboost.sdk.Libraries.j(this);
        this.l = 0;
    }

    public boolean m() {
        return this.f.e == com.chartboost.sdk.Model.a.d.INTERSTITIAL_VIDEO;
    }

    public void n() {
        bm.a aVar = new bm.a();
        aVar.a(this.I.a("cancel-popup").e("title")).b(this.I.a("cancel-popup").e("text")).d(this.I.a("cancel-popup").e("confirm")).c(this.I.a("cancel-popup").e("cancel"));
        aVar.a(e().getContext(), new bm.b() { // from class: com.chartboost.sdk.impl.ai.1
            @Override // com.chartboost.sdk.impl.bm.b
            public void a(bm bmVar) {
                a aVarE = ai.this.e();
                if (aVarE != null) {
                    aVarE.j.e();
                }
            }

            @Override // com.chartboost.sdk.impl.bm.b
            public void a(bm bmVar, int i) {
                a aVarE = ai.this.e();
                if (i == 1) {
                    if (aVarE != null) {
                        aVarE.j.e();
                    }
                } else {
                    if (aVarE != null) {
                        aVarE.d(false);
                        aVarE.j.h();
                    }
                    ai.this.h();
                }
            }
        });
    }

    @Override // com.chartboost.sdk.impl.ah, com.chartboost.sdk.f
    protected com.chartboost.sdk.f.a b(Context context) {
        return new a(context);
    }

    @Override // com.chartboost.sdk.f
    public boolean j() {
        if (e().b(true).getVisibility() != 4 && e().b(true).getVisibility() != 8) {
            e().g();
        }
        return true;
    }

    @Override // com.chartboost.sdk.f
    public void k() {
        super.k();
        if (this.k == b.VIDEO_PLAYING && this.M) {
            e().j.b().a(this.n);
            if (!this.N) {
                e().j.e();
            }
        }
        this.N = false;
        this.M = false;
    }

    @Override // com.chartboost.sdk.f
    public void l() {
        super.l();
        if (this.k == b.VIDEO_PLAYING && !this.M) {
            if (!e().j.i()) {
                this.N = true;
            }
            this.M = true;
            e().j.g();
            if (this.l < 1 && this.O) {
                x();
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void w() {
        com.chartboost.sdk.Libraries.h.c().b(Integer.toString(this.f.hashCode()));
    }

    private void x() {
        float f = this.n;
        float f2 = this.o;
        ba baVar = new ba("/api/video-complete");
        baVar.b((ba.c) null);
        baVar.a("location", (Object) this.f.d);
        baVar.a("reward", (Object) this.f.w().e("reward"));
        baVar.a("currency-name", (Object) this.f.w().e("currency-name"));
        baVar.a("ad_id", (Object) this.f.p());
        baVar.a("total_time", Float.valueOf(f2 / 1000.0f));
        baVar.a("playback_time", Float.valueOf(f / 1000.0f));
        baVar.a("force_close", (Object) true);
        com.chartboost.sdk.Libraries.h.c().a(Integer.toString(this.f.hashCode()), baVar.t());
    }

    @Override // com.chartboost.sdk.impl.ah, com.chartboost.sdk.impl.aj, com.chartboost.sdk.f
    public boolean a(com.chartboost.sdk.Libraries.e.a aVar) {
        if (!super.a(aVar)) {
            return false;
        }
        if (this.e.b("video-landscape") || this.e.b("replay-landscape")) {
            this.j = false;
        }
        this.v.a("replay-landscape");
        this.w.a("replay-portrait");
        this.z.a("video-click-button");
        this.A.a("post-video-reward-icon");
        this.B.a("post-video-button");
        this.x.a("video-confirmation-button");
        this.y.a("video-confirmation-icon");
        this.C.a("post-video-reward-icon");
        this.p = aVar.a("ux").i("video-controls-togglable");
        this.E = aVar.a("fullscreen").b() ? false : aVar.a("fullscreen").m();
        this.F = aVar.a("preroll_popup_fullscreen").b() ? false : aVar.a("preroll_popup_fullscreen").m();
        if (this.f.e == com.chartboost.sdk.Model.a.d.INTERSTITIAL_REWARD_VIDEO && this.I.a("post-video-toaster").c("title") && this.I.a("post-video-toaster").c("tagline")) {
            this.r = true;
        }
        if (this.f.e == com.chartboost.sdk.Model.a.d.INTERSTITIAL_REWARD_VIDEO && this.I.a("confirmation").c("text") && this.I.a("confirmation").c("color")) {
            this.q = true;
        }
        if (this.f.e == com.chartboost.sdk.Model.a.d.INTERSTITIAL_REWARD_VIDEO && this.I.c("post-video-reward-toaster")) {
            this.s = true;
        }
        return true;
    }

    @Override // com.chartboost.sdk.f
    protected void i() {
        if (this.q && (!this.x.e() || !this.y.e())) {
            this.q = false;
        }
        if (this.L) {
            super.i();
        } else {
            a(CBError.CBImpressionError.ERROR_DISPLAYING_VIEW);
        }
    }

    @Override // com.chartboost.sdk.impl.ah, com.chartboost.sdk.impl.aj, com.chartboost.sdk.f
    public void d() {
        super.d();
        this.v.d();
        this.w.d();
        this.z.d();
        this.A.d();
        this.B.d();
        this.x.d();
        this.y.d();
        this.C.d();
        this.v = null;
        this.w = null;
        this.z = null;
        this.A = null;
        this.B = null;
        this.x = null;
        this.y = null;
        this.C = null;
    }

    public boolean o() {
        return this.k == b.VIDEO_PLAYING;
    }

    @Override // com.chartboost.sdk.f
    /* JADX INFO: renamed from: p, reason: merged with bridge method [inline-methods] */
    public a e() {
        return (a) super.e();
    }

    protected void q() {
        this.f.r();
    }

    protected boolean r() {
        if (this.k == b.POST_VIDEO) {
            return false;
        }
        boolean zB = CBUtility.c().b();
        if (this.k == b.REWARD_OFFER) {
            return this.F || zB;
        }
        if (this.k == b.VIDEO_PLAYING) {
            return this.E || zB;
        }
        return zB && this.k != b.POST_VIDEO;
    }

    public boolean s() {
        return this.O;
    }

    public void a(boolean z) {
        this.O = z;
    }

    public void t() {
        this.P = true;
        be.b(this.m);
        a(CBError.CBImpressionError.ERROR_PLAYING_VIDEO);
    }

    public int u() {
        return this.o;
    }

    public int v() {
        return this.n;
    }
}
