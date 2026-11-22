package com.facebook.ads.a;

import android.os.Build;
import android.os.Handler;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;

/* JADX INFO: loaded from: classes.dex */
public class ah {
    private final a d;
    private final a e;
    private int a = 200;
    private int b = 500;
    private int c = 4000;
    private boolean f = "4.1.2".equals(Build.VERSION.RELEASE);

    private class a implements Animation.AnimationListener {
        private final View b;
        private boolean c = false;
        private Animation d;
        private Animation e;

        public a(View view) {
            this.b = view;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void a(Animation animation) {
            if (animation == this.d) {
                this.b.startAnimation(this.e);
            } else if (animation == this.e) {
                this.b.startAnimation(this.d);
            }
        }

        public a a(boolean z) {
            this.c = z;
            return this;
        }

        public void a() {
            this.d = new TranslateAnimation(0, 0.0f, 0, 0.0f, 0, 0.0f, 0, 0 - ah.this.a);
            this.d.setDuration(ah.this.b);
            this.d.setStartOffset(ah.this.c);
            this.d.setFillAfter(true);
            this.d.setAnimationListener(this);
            this.e = new TranslateAnimation(0, 0.0f, 0, 0.0f, 0, ah.this.a, 0, 0.0f);
            this.e.setDuration(ah.this.b);
            this.e.setStartOffset(ah.this.c);
            this.e.setFillAfter(true);
            this.e.setAnimationListener(this);
            this.b.startAnimation(this.c ? this.e : this.d);
        }

        @Override // android.view.animation.Animation.AnimationListener
        public void onAnimationEnd(Animation animation) {
            if (ah.this.f) {
                return;
            }
            a(animation);
        }

        @Override // android.view.animation.Animation.AnimationListener
        public void onAnimationRepeat(Animation animation) {
        }

        @Override // android.view.animation.Animation.AnimationListener
        public void onAnimationStart(final Animation animation) {
            if (ah.this.f) {
                new Handler().postDelayed(new Runnable() { // from class: com.facebook.ads.a.ah.a.1
                    @Override // java.lang.Runnable
                    public void run() {
                        a.this.a(animation);
                    }
                }, ah.this.c);
            }
        }
    }

    public ah(View view, View view2) {
        this.d = new a(view);
        this.e = new a(view2).a(true);
    }

    public ah a() {
        this.d.a();
        this.e.a();
        return this;
    }

    public ah a(int i) {
        this.a = i * 2;
        return this;
    }
}
