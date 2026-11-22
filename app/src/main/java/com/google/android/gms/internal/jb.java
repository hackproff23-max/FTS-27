package com.google.android.gms.internal;

import android.graphics.Canvas;
import android.graphics.Path;
import android.net.Uri;
import android.widget.ImageView;

/* JADX INFO: loaded from: classes.dex */
public final class jb extends ImageView {
    private Uri Mn;
    private int Mo;
    private int Mp;
    private a Mq;
    private int Mr;
    private float Ms;

    public interface a {
        Path g(int i, int i2);
    }

    public void aB(int i) {
        this.Mo = i;
    }

    public void g(Uri uri) {
        this.Mn = uri;
    }

    public int hj() {
        return this.Mo;
    }

    @Override // android.widget.ImageView, android.view.View
    protected void onDraw(Canvas canvas) {
        if (this.Mq != null) {
            canvas.clipPath(this.Mq.g(getWidth(), getHeight()));
        }
        super.onDraw(canvas);
        if (this.Mp != 0) {
            canvas.drawColor(this.Mp);
        }
    }

    @Override // android.widget.ImageView, android.view.View
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int measuredWidth;
        int measuredHeight;
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        switch (this.Mr) {
            case 1:
                measuredHeight = getMeasuredHeight();
                measuredWidth = (int) (measuredHeight * this.Ms);
                break;
            case 2:
                measuredWidth = getMeasuredWidth();
                measuredHeight = (int) (measuredWidth / this.Ms);
                break;
            default:
                return;
        }
        setMeasuredDimension(measuredWidth, measuredHeight);
    }
}
