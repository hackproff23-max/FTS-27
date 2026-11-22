package com.chartboost.sdk.impl;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import android.os.Handler;
import com.chartboost.sdk.Libraries.CBUtility;

/* JADX INFO: loaded from: classes.dex */
public final class br extends bo {
    private static Handler a = CBUtility.e();
    private float b;
    private long c;
    private Paint d;
    private Paint e;
    private Path f;
    private Path g;
    private RectF h;
    private RectF i;
    private Runnable j;

    static /* synthetic */ float a(br brVar, float f) {
        float f2 = brVar.b + f;
        brVar.b = f2;
        return f2;
    }

    static /* synthetic */ float b(br brVar, float f) {
        float f2 = brVar.b - f;
        brVar.b = f2;
        return f2;
    }

    public br(Context context) {
        super(context);
        this.j = new Runnable() { // from class: com.chartboost.sdk.impl.br.1
            @Override // java.lang.Runnable
            public void run() {
                float f = br.this.getContext().getResources().getDisplayMetrics().density;
                br.a(br.this, 60.0f * f * 0.016666668f);
                float height = br.this.getHeight() - (f * 9.0f);
                if (br.this.b > height) {
                    br.b(br.this, height * 2.0f);
                }
                if (br.this.getWindowVisibility() == 0) {
                    br.this.invalidate();
                }
            }
        };
        a(context);
    }

    private void a(Context context) {
        float f = context.getResources().getDisplayMetrics().density;
        this.b = 0.0f;
        this.c = (long) (System.nanoTime() / 1000000.0d);
        this.d = new Paint();
        this.d.setColor(-1);
        this.d.setStyle(Paint.Style.STROKE);
        this.d.setStrokeWidth(f * 3.0f);
        this.d.setAntiAlias(true);
        this.e = new Paint();
        this.e.setColor(-1);
        this.e.setStyle(Paint.Style.FILL);
        this.e.setAntiAlias(true);
        this.f = new Path();
        this.g = new Path();
        this.i = new RectF();
        this.h = new RectF();
    }

    @Override // com.chartboost.sdk.impl.bo
    protected void a(Canvas canvas) {
        float f = getContext().getResources().getDisplayMetrics().density;
        this.h.set(0.0f, 0.0f, getWidth(), getHeight());
        this.h.inset(1.5f * f, 1.5f * f);
        float height = getHeight() / 2.0f;
        canvas.drawRoundRect(this.h, height, height, this.d);
        this.i.set(this.h);
        this.i.inset(3.0f * f, f * 3.0f);
        float fHeight = this.i.height() / 2.0f;
        this.f.reset();
        this.f.addRoundRect(this.i, fHeight, fHeight, Path.Direction.CW);
        float fHeight2 = this.i.height();
        this.g.reset();
        this.g.moveTo(0.0f, fHeight2);
        this.g.lineTo(fHeight2, fHeight2);
        this.g.lineTo(fHeight2 * 2.0f, 0.0f);
        this.g.lineTo(fHeight2, 0.0f);
        this.g.close();
        canvas.save();
        boolean z = true;
        try {
            canvas.clipPath(this.f);
        } catch (UnsupportedOperationException e) {
            z = false;
        }
        if (z) {
            float f2 = -fHeight2;
            float f3 = this.b;
            while (true) {
                f2 += f3;
                if (f2 >= this.i.width() + fHeight2) {
                    break;
                }
                float f4 = this.i.left + f2;
                canvas.save();
                canvas.translate(f4, this.i.top);
                canvas.drawPath(this.g, this.e);
                canvas.restore();
                f3 = 2.0f * fHeight2;
            }
        }
        canvas.restore();
        long jMax = Math.max(0L, 16 - (((long) (System.nanoTime() / 1000000.0d)) - this.c));
        a.removeCallbacks(this.j);
        a.postDelayed(this.j, jMax);
    }

    @Override // android.view.View
    protected void onWindowVisibilityChanged(int visibility) {
        super.onWindowVisibilityChanged(visibility);
        a.removeCallbacks(this.j);
        if (visibility == 0) {
            a.post(this.j);
        }
    }

    @Override // android.view.View
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        a.removeCallbacks(this.j);
        a.post(this.j);
    }

    @Override // com.chartboost.sdk.impl.bo, android.view.View
    protected void onDetachedFromWindow() {
        a.removeCallbacks(this.j);
        super.onDetachedFromWindow();
    }
}
