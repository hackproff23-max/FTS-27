package com.facebook.ads.a;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.view.View;

/* JADX INFO: loaded from: classes.dex */
public class v extends View {
    private Paint a;
    private Paint b;
    private Paint c;

    public v(Context context) {
        super(context);
        this.a = new Paint();
        this.a.setColor(-3355444);
        this.a.setStyle(Paint.Style.STROKE);
        this.a.setStrokeWidth(3.0f);
        this.a.setAntiAlias(true);
        this.b = new Paint();
        this.b.setColor(-1287371708);
        this.b.setStyle(Paint.Style.FILL);
        this.b.setAntiAlias(true);
        this.c = new Paint();
        this.c.setColor(-1);
        this.c.setStyle(Paint.Style.STROKE);
        this.c.setStrokeWidth(6.0f);
        this.c.setAntiAlias(true);
    }

    @Override // android.view.View
    protected void onDraw(Canvas canvas) {
        int iMin = Math.min(canvas.getWidth(), canvas.getHeight());
        int i = iMin / 2;
        int i2 = iMin / 2;
        int i3 = (i * 2) / 3;
        canvas.drawCircle(i, i2, i3, this.a);
        canvas.drawCircle(i, i2, i3 - 2, this.b);
        int i4 = iMin / 3;
        int i5 = iMin / 3;
        canvas.drawLine(i4, i5, i4 * 2, i5 * 2, this.c);
        canvas.drawLine(i4 * 2, i5, i4, i5 * 2, this.c);
        super.onDraw(canvas);
    }
}
