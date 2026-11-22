package com.mopub.mobileads.resource;

import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;

/* JADX INFO: loaded from: classes.dex */
public abstract class CircleDrawable extends Drawable {
    private final Paint mPaint = new Paint();

    public CircleDrawable() {
        this.mPaint.setAntiAlias(true);
        this.mPaint.setStrokeWidth(3.0f);
        this.mPaint.setColor(-1);
        this.mPaint.setStyle(Paint.Style.STROKE);
    }

    @Override // android.graphics.drawable.Drawable
    public void draw(Canvas canvas) {
        canvas.drawCircle(getCenterX(), getCenterY(), getRadius(), this.mPaint);
    }

    @Override // android.graphics.drawable.Drawable
    public void setAlpha(int i) {
    }

    @Override // android.graphics.drawable.Drawable
    public void setColorFilter(ColorFilter colorFilter) {
    }

    @Override // android.graphics.drawable.Drawable
    public int getOpacity() {
        return 0;
    }

    protected Paint getPaint() {
        return this.mPaint;
    }

    protected int getCenterX() {
        return getBounds().width() / 2;
    }

    protected int getCenterY() {
        return getBounds().height() / 2;
    }

    protected int getRadius() {
        return Math.min(getCenterX(), getCenterY());
    }
}
