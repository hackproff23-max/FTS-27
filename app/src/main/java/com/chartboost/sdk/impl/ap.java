package com.chartboost.sdk.impl;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Point;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Handler;
import android.support.v4.view.ViewCompat;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.chartboost.sdk.Libraries.CBUtility;

/* JADX INFO: loaded from: classes.dex */
public final class ap extends RelativeLayout implements MediaPlayer.OnCompletionListener, MediaPlayer.OnErrorListener, MediaPlayer.OnPreparedListener {
    private static final CharSequence a = "00:00";
    private static Handler l = CBUtility.e();
    private RelativeLayout b;
    private ao c;
    private ao d;
    private bl e;
    private TextView f;
    private al g;
    private bh h;
    private ai i;
    private boolean j;
    private boolean k;
    private Runnable m;
    private Runnable n;
    private Runnable o;

    public ap(Context context, ai aiVar) {
        super(context);
        this.j = false;
        this.k = false;
        this.m = new Runnable() { // from class: com.chartboost.sdk.impl.ap.2
            @Override // java.lang.Runnable
            public void run() {
                ap.this.d(false);
            }
        };
        this.n = new Runnable() { // from class: com.chartboost.sdk.impl.ap.3
            @Override // java.lang.Runnable
            public void run() {
                if (ap.this.c != null) {
                    ap.this.c.setVisibility(8);
                }
                if (ap.this.i.D) {
                    ap.this.g.setVisibility(8);
                }
                ap.this.d.setVisibility(8);
                if (ap.this.e != null) {
                    ap.this.e.setEnabled(false);
                }
            }
        };
        this.o = new Runnable() { // from class: com.chartboost.sdk.impl.ap.4
            private int b = 0;

            @Override // java.lang.Runnable
            public void run() {
                if (ap.this.h.b().e()) {
                    int iD = ap.this.h.b().d();
                    if (iD > 0) {
                        ap.this.i.n = iD;
                        float f = ap.this.i.n;
                        if (ap.this.h.b().e() && f / 1000.0f > 0.0f && !ap.this.i.s()) {
                            ap.this.i.q();
                            ap.this.i.a(true);
                        }
                    }
                    float fC = iD / ap.this.h.b().c();
                    if (ap.this.i.D) {
                        ap.this.g.a(fC);
                    }
                    int i = iD / 1000;
                    if (this.b != i) {
                        this.b = i;
                        ap.this.f.setText(String.format("%02d:%02d", Integer.valueOf(i / 60), Integer.valueOf(i % 60)));
                    }
                }
                ai.a aVarE = ap.this.i.e();
                if (aVarE.f()) {
                    bl blVarB = aVarE.b(true);
                    if (blVarB.getVisibility() == 8) {
                        ap.this.i.a(true, (View) blVarB);
                        blVarB.setEnabled(true);
                    }
                }
                ap.l.removeCallbacks(ap.this.o);
                ap.l.postDelayed(ap.this.o, 16L);
            }
        };
        this.i = aiVar;
        a(context);
    }

    private void a(Context context) {
        Context context2 = getContext();
        com.chartboost.sdk.Libraries.e.a aVarG = this.i.g();
        float f = getContext().getResources().getDisplayMetrics().density;
        int iRound = Math.round(f * 10.0f);
        this.h = new bh(context2);
        this.i.e().a(this.h);
        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(-1, -2);
        layoutParams.addRule(13);
        addView(this.h, layoutParams);
        this.b = new RelativeLayout(context2);
        if (aVarG.c() && aVarG.a("video-click-button").c()) {
            this.c = new ao(context2);
            this.c.setVisibility(8);
            this.e = new bl(context2) { // from class: com.chartboost.sdk.impl.ap.1
                @Override // com.chartboost.sdk.impl.bl
                protected void a(MotionEvent motionEvent) {
                    ap.this.i.a((String) null, com.chartboost.sdk.Libraries.e.a(com.chartboost.sdk.Libraries.e.a("paused", 1)));
                    com.chartboost.sdk.Tracking.a.a("install-button", ap.this.i.m, ap.this.i.J, Math.round(motionEvent.getX()), Math.round(motionEvent.getY()));
                }
            };
            this.e.a(ImageView.ScaleType.FIT_CENTER);
            com.chartboost.sdk.Libraries.j jVar = this.i.z;
            Point pointB = this.i.b("video-click-button");
            LinearLayout.LayoutParams layoutParams2 = new LinearLayout.LayoutParams(-2, -2);
            layoutParams2.leftMargin = Math.round(pointB.x / jVar.g());
            layoutParams2.topMargin = Math.round(pointB.y / jVar.g());
            this.i.a(layoutParams2, jVar, 1.0f);
            this.e.a(jVar);
            this.c.addView(this.e, layoutParams2);
            RelativeLayout.LayoutParams layoutParams3 = new RelativeLayout.LayoutParams(-1, Math.round(layoutParams2.height + (10.0f * f)));
            layoutParams3.addRule(10);
            this.b.addView(this.c, layoutParams3);
        }
        this.d = new ao(context2);
        this.d.setVisibility(8);
        RelativeLayout.LayoutParams layoutParams4 = new RelativeLayout.LayoutParams(-1, Math.round(32.5f * f));
        layoutParams4.addRule(12);
        this.b.addView(this.d, layoutParams4);
        this.d.setGravity(16);
        this.d.setPadding(iRound, iRound, iRound, iRound);
        this.f = new TextView(context2);
        this.f.setTextColor(-1);
        this.f.setTextSize(2, 11.0f);
        this.f.setText(a);
        this.f.setPadding(0, 0, iRound, 0);
        this.f.setSingleLine();
        this.f.measure(0, 0);
        int measuredWidth = this.f.getMeasuredWidth();
        this.f.setGravity(17);
        this.d.addView(this.f, new LinearLayout.LayoutParams(measuredWidth, -1));
        this.g = new al(context2);
        this.g.setVisibility(8);
        LinearLayout.LayoutParams layoutParams5 = new LinearLayout.LayoutParams(-1, Math.round(10.0f * f));
        layoutParams5.setMargins(0, CBUtility.a(1, getContext()), 0, 0);
        this.d.addView(this.g, layoutParams5);
        RelativeLayout.LayoutParams layoutParams6 = new RelativeLayout.LayoutParams(-1, -1);
        layoutParams6.addRule(6, this.h.getId());
        layoutParams6.addRule(8, this.h.getId());
        layoutParams6.addRule(5, this.h.getId());
        layoutParams6.addRule(7, this.h.getId());
        addView(this.b, layoutParams6);
        a();
    }

    @Override // android.view.View
    public void setEnabled(boolean enabled) {
        super.setEnabled(enabled);
        if (this.e != null) {
            this.e.setEnabled(enabled);
        }
        if (enabled) {
            a(false);
        }
    }

    @Override // android.view.ViewGroup, android.view.View
    public void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        l.removeCallbacks(this.o);
    }

    @Override // android.view.View
    @SuppressLint({"ClickableViewAccessibility"})
    public boolean onTouchEvent(MotionEvent e) {
        if (!this.h.b().e() || e.getActionMasked() != 0) {
            return false;
        }
        if (this.i != null) {
            com.chartboost.sdk.Tracking.a.e(this.i.m, this.i.J, this.j);
        }
        d(true);
        return true;
    }

    @Override // android.media.MediaPlayer.OnCompletionListener
    public void onCompletion(MediaPlayer arg0) {
        this.i.n = this.h.b().c();
        if (this.i.e() != null) {
            this.i.e().e();
        }
    }

    @Override // android.media.MediaPlayer.OnPreparedListener
    public void onPrepared(MediaPlayer mp) {
        this.i.o = this.h.b().c();
        this.i.e().a(true);
    }

    @Override // android.media.MediaPlayer.OnErrorListener
    public boolean onError(MediaPlayer mp, int what, int extra) {
        this.i.t();
        return false;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void d(boolean z) {
        a(!this.j, z);
    }

    protected void a(boolean z, boolean z2) {
        l.removeCallbacks(this.m);
        l.removeCallbacks(this.n);
        if (this.i.p && this.i.o() && z != this.j) {
            this.j = z;
            AlphaAnimation alphaAnimation = this.j ? new AlphaAnimation(0.0f, 1.0f) : new AlphaAnimation(1.0f, 0.0f);
            alphaAnimation.setDuration(z2 ? 100L : 200L);
            alphaAnimation.setFillAfter(true);
            if (!this.k && this.c != null) {
                this.c.setVisibility(0);
                this.c.startAnimation(alphaAnimation);
                if (this.e != null) {
                    this.e.setEnabled(true);
                }
            }
            if (this.i.D) {
                this.g.setVisibility(0);
            }
            this.d.setVisibility(0);
            this.d.startAnimation(alphaAnimation);
            if (this.j) {
                l.postDelayed(this.m, 3000L);
            } else {
                l.postDelayed(this.n, alphaAnimation.getDuration());
            }
        }
    }

    public void a(boolean z) {
        l.removeCallbacks(this.m);
        l.removeCallbacks(this.n);
        if (z) {
            if (!this.k && this.c != null) {
                this.c.setVisibility(0);
            }
            if (this.i.D) {
                this.g.setVisibility(0);
            }
            this.d.setVisibility(0);
            if (this.e != null) {
                this.e.setEnabled(true);
            }
        } else {
            if (this.c != null) {
                this.c.clearAnimation();
                this.c.setVisibility(8);
            }
            this.d.clearAnimation();
            if (this.i.D) {
                this.g.setVisibility(8);
            }
            this.d.setVisibility(8);
            if (this.e != null) {
                this.e.setEnabled(false);
            }
        }
        this.j = z;
    }

    public void b(boolean z) {
        setBackgroundColor(z ? ViewCompat.MEASURED_STATE_MASK : 0);
        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(-1, -1);
        if (!z) {
            layoutParams.addRule(6, this.h.getId());
            layoutParams.addRule(8, this.h.getId());
            layoutParams.addRule(5, this.h.getId());
            layoutParams.addRule(7, this.h.getId());
        }
        this.b.setLayoutParams(layoutParams);
        if (this.c != null) {
            this.c.setGravity(19);
            this.c.requestLayout();
        }
    }

    public void a() {
        b(CBUtility.c().b());
    }

    public bh.a b() {
        return this.h.b();
    }

    public al c() {
        return this.g;
    }

    public void a(int i) {
        if (this.c != null) {
            this.c.setBackgroundColor(i);
        }
        this.d.setBackgroundColor(i);
    }

    public void d() {
        if (this.c != null) {
            this.c.setVisibility(8);
        }
        this.k = true;
        if (this.e != null) {
            this.e.setEnabled(false);
        }
    }

    public void c(boolean z) {
        this.f.setVisibility(z ? 0 : 8);
    }

    public void a(String str) {
        this.h.b().a((MediaPlayer.OnCompletionListener) this);
        this.h.b().a((MediaPlayer.OnErrorListener) this);
        this.h.b().a((MediaPlayer.OnPreparedListener) this);
        this.h.b().a(Uri.parse(str));
    }

    public void e() {
        if (!this.h.a()) {
            l.postDelayed(new Runnable() { // from class: com.chartboost.sdk.impl.ap.5
                @Override // java.lang.Runnable
                public void run() {
                    ap.this.h.setVisibility(0);
                }
            }, 250L);
        }
        this.h.b().a();
        l.removeCallbacks(this.o);
        l.postDelayed(this.o, 16L);
    }

    public void f() {
        if (this.h.b().e()) {
            this.i.n = this.h.b().d();
            this.h.b().b();
        }
        if (this.i.e().b.getVisibility() == 0) {
            this.i.e().b.postInvalidate();
        }
        l.removeCallbacks(this.o);
    }

    public void g() {
        if (this.h.b().e()) {
            this.i.n = this.h.b().d();
        }
        this.h.b().b();
        l.removeCallbacks(this.o);
    }

    public void h() {
        if (!this.h.a()) {
            this.h.setVisibility(8);
            invalidate();
        }
    }

    public boolean i() {
        return this.h.b().e();
    }
}
