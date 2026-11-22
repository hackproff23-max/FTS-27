package com.mopub.mobileads.resource;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Point;

/* JADX INFO: loaded from: classes.dex */
public class CloseButtonDrawable extends CircleDrawable {
    private Point bottomLeftPoint;
    private Point bottomRightPoint;
    private Point centerPoint;
    private final Paint closeButtonPaint = new Paint(getPaint());
    private int mDisplacement;
    private Point topLeftPoint;
    private Point topRightPoint;

    public CloseButtonDrawable() {
        this.closeButtonPaint.setStrokeWidth(4.5f);
        this.closeButtonPaint.setStrokeCap(Paint.Cap.ROUND);
    }

    @Override // com.mopub.mobileads.resource.CircleDrawable, android.graphics.drawable.Drawable
    public void draw(Canvas canvas) {
        super.draw(canvas);
        this.mDisplacement = (int) ((0.5f * getRadius()) / ((float) Math.sqrt(2.0d)));
        this.centerPoint = new Point(getCenterX(), getCenterY());
        this.bottomLeftPoint = new Point(this.centerPoint);
        this.bottomLeftPoint.offset(-this.mDisplacement, this.mDisplacement);
        this.topLeftPoint = new Point(this.centerPoint);
        this.topLeftPoint.offset(-this.mDisplacement, -this.mDisplacement);
        this.topRightPoint = new Point(this.centerPoint);
        this.topRightPoint.offset(this.mDisplacement, -this.mDisplacement);
        this.bottomRightPoint = new Point(this.centerPoint);
        this.bottomRightPoint.offset(this.mDisplacement, this.mDisplacement);
        canvas.drawLine(this.bottomLeftPoint.x, this.bottomLeftPoint.y, this.topRightPoint.x, this.topRightPoint.y, this.closeButtonPaint);
        canvas.drawLine(this.topLeftPoint.x, this.topLeftPoint.y, this.bottomRightPoint.x, this.bottomRightPoint.y, this.closeButtonPaint);
    }
}
