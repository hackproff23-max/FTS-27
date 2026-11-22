package com.flurry.sdk;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.LayerDrawable;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.PathShape;
import android.os.Build;
import android.support.v4.view.ViewCompat;
import android.view.View;
import android.widget.TextView;

/* JADX INFO: loaded from: classes.dex */
public class ep {
    private static int a;
    private static int b;
    private Path c;
    private PathShape d;
    private ShapeDrawable e;
    private TextView f;
    private int g;
    private float h;
    private RectF i;
    private final float j;

    public ep(Context context, int i, int i2) {
        this.c = null;
        this.d = null;
        this.e = null;
        this.f = null;
        this.g = 0;
        this.h = 0.0f;
        this.i = null;
        this.j = -90.0f;
        a = hn.b(2);
        b = hn.b(1);
        this.g = a(i, i2);
        a(context);
    }

    public ep() {
        this.c = null;
        this.d = null;
        this.e = null;
        this.f = null;
        this.g = 0;
        this.h = 0.0f;
        this.i = null;
        this.j = -90.0f;
        a = 3;
        b = 1;
    }

    public void a(int i) {
        if (this.f != null) {
            e(i);
            f(i);
        }
    }

    public void b(final int i) {
        this.h = 360.0f / (i / 1000);
        fp.a().a(new Runnable() { // from class: com.flurry.sdk.ep.1
            @Override // java.lang.Runnable
            public void run() {
                ep.this.d(i);
            }
        });
    }

    private void a(Context context) {
        this.f = new TextView(context);
        this.f.setTextColor(-1);
        this.f.setTypeface(Typeface.MONOSPACE);
        this.f.setTextSize(1, 12.0f);
        this.f.setGravity(17);
    }

    public View a() {
        return this.f;
    }

    /* JADX INFO: Access modifiers changed from: private */
    @SuppressLint({"NewApi"})
    public void d(int i) {
        Drawable drawableC = c(i);
        if (Build.VERSION.SDK_INT >= 16) {
            this.f.setBackground(drawableC);
        } else {
            this.f.setBackgroundDrawable(drawableC);
        }
    }

    public Drawable c(int i) {
        if (0 != 0) {
            return null;
        }
        this.i = new RectF();
        this.i.set(a, a, this.g - a, this.g - a);
        this.c = new Path();
        this.c.arcTo(this.i, -90.0f, ((-i) * this.h) + 1.0f, false);
        this.d = new PathShape(this.c, this.g, this.g);
        this.e = new ShapeDrawable(this.d);
        this.e.setIntrinsicHeight(this.g * 2);
        this.e.setIntrinsicWidth(this.g * 2);
        this.e.getPaint().setStyle(Paint.Style.STROKE);
        this.e.getPaint().setColor(-1);
        this.e.getPaint().setStrokeWidth(b);
        this.e.getPaint().setAntiAlias(true);
        GradientDrawable gradientDrawable = new GradientDrawable();
        gradientDrawable.setColor(ViewCompat.MEASURED_STATE_MASK);
        gradientDrawable.setShape(1);
        gradientDrawable.setAlpha(178);
        return new LayerDrawable(new Drawable[]{gradientDrawable, this.e});
    }

    private void e(final int i) {
        fp.a().a(new Runnable() { // from class: com.flurry.sdk.ep.2
            @Override // java.lang.Runnable
            public void run() {
                ep.this.d(i);
            }
        });
    }

    private void f(final int i) {
        fp.a().a(new Runnable() { // from class: com.flurry.sdk.ep.3
            @Override // java.lang.Runnable
            public void run() {
                ep.this.f.setText(Integer.toString(i));
            }
        });
    }

    private int a(int i, int i2) {
        if (i < i2) {
            return i / 2;
        }
        return i2 / 2;
    }
}
