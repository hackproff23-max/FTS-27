package com.chartboost.sdk.impl;

import android.content.Context;
import android.view.View;
import android.view.animation.TranslateAnimation;
import android.widget.RelativeLayout;
import com.chartboost.sdk.Libraries.CBUtility;

/* JADX INFO: loaded from: classes.dex */
public abstract class an extends RelativeLayout {
    protected ai a;
    private ao b;
    private a c;

    public enum a {
        TOP,
        BOTTOM,
        LEFT,
        RIGHT
    }

    protected abstract View a();

    protected abstract int b();

    public an(Context context, ai aiVar) {
        super(context);
        this.a = aiVar;
        this.c = a.BOTTOM;
        a(context);
    }

    public void a(a aVar) {
        if (aVar == null) {
            throw new IllegalArgumentException("the given side cannot be null");
        }
        this.c = aVar;
        RelativeLayout.LayoutParams layoutParams = null;
        setClickable(false);
        int iB = b();
        switch (this.c) {
            case TOP:
                layoutParams = new RelativeLayout.LayoutParams(-1, CBUtility.a(iB, getContext()));
                layoutParams.addRule(10);
                this.b.b(1);
                break;
            case BOTTOM:
                layoutParams = new RelativeLayout.LayoutParams(-1, CBUtility.a(iB, getContext()));
                layoutParams.addRule(12);
                this.b.b(4);
                break;
            case LEFT:
                layoutParams = new RelativeLayout.LayoutParams(CBUtility.a(iB, getContext()), -1);
                layoutParams.addRule(9);
                this.b.b(8);
                break;
            case RIGHT:
                layoutParams = new RelativeLayout.LayoutParams(CBUtility.a(iB, getContext()), -1);
                layoutParams.addRule(11);
                this.b.b(2);
                break;
        }
        setLayoutParams(layoutParams);
    }

    private void a(Context context) {
        Context context2 = getContext();
        setGravity(17);
        this.b = new ao(context2);
        this.b.a(-1);
        this.b.setBackgroundColor(-855638017);
        addView(this.b, new RelativeLayout.LayoutParams(-1, -1));
        addView(a(), new RelativeLayout.LayoutParams(-1, -1));
    }

    public void a(boolean z) {
        a(z, 250L);
    }

    private void a(final boolean z, long j) {
        TranslateAnimation translateAnimation;
        this.a.t = z;
        if (z && getVisibility() == 0) {
            return;
        }
        if (z || getVisibility() != 8) {
            Runnable runnable = new Runnable() { // from class: com.chartboost.sdk.impl.an.1
                @Override // java.lang.Runnable
                public void run() {
                    if (!z) {
                        an.this.setVisibility(8);
                        an.this.clearAnimation();
                    }
                    an.this.a.h.remove(Integer.valueOf(hashCode()));
                }
            };
            if (z) {
                setVisibility(0);
            }
            float fA = CBUtility.a(b(), getContext());
            switch (this.c) {
                case TOP:
                    translateAnimation = new TranslateAnimation(0.0f, 0.0f, z ? -fA : 0.0f, z ? 0.0f : -fA);
                    break;
                case BOTTOM:
                    float f = z ? fA : 0.0f;
                    if (z) {
                        fA = 0.0f;
                    }
                    translateAnimation = new TranslateAnimation(0.0f, 0.0f, f, fA);
                    break;
                case LEFT:
                    translateAnimation = new TranslateAnimation(z ? -fA : 0.0f, z ? 0.0f : -fA, 0.0f, 0.0f);
                    break;
                case RIGHT:
                    float f2 = z ? fA : 0.0f;
                    if (z) {
                        fA = 0.0f;
                    }
                    translateAnimation = new TranslateAnimation(f2, fA, 0.0f, 0.0f);
                    break;
                default:
                    translateAnimation = null;
                    break;
            }
            translateAnimation.setDuration(j);
            translateAnimation.setFillAfter(!z);
            startAnimation(translateAnimation);
            this.a.h.put(Integer.valueOf(hashCode()), runnable);
            com.chartboost.sdk.f.a.postDelayed(runnable, j);
        }
    }
}
