package com.mopub.mobileads.resource;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Point;

/* JADX INFO: loaded from: classes.dex */
public class LearnMoreDrawable extends CircleDrawable {
    private Point bottomLeftPoint;
    private Point centerPoint;
    private final Paint learnMorePaint = new Paint(getPaint());
    private Point leftBarbPoint;
    private int mBarbLength;
    private int mDisplacement;
    private Point rightBarbPoint;
    private Point topRightPoint;

    public LearnMoreDrawable() {
        this.learnMorePaint.setStrokeWidth(4.5f);
        this.learnMorePaint.setStrokeCap(Paint.Cap.ROUND);
    }

    @Override // com.mopub.mobileads.resource.CircleDrawable, android.graphics.drawable.Drawable
    public void draw(Canvas canvas) {
        super.draw(canvas);
        this.mDisplacement = (int) (((double) (0.5f * getRadius())) / Math.sqrt(2.0d));
        this.mBarbLength = (int) (1.5f * this.mDisplacement);
        this.centerPoint = new Point(getCenterX(), getCenterY());
        this.bottomLeftPoint = new Point(this.centerPoint);
        this.bottomLeftPoint.offset(-this.mDisplacement, this.mDisplacement);
        this.topRightPoint = new Point(this.centerPoint);
        this.topRightPoint.offset(this.mDisplacement, -this.mDisplacement);
        this.leftBarbPoint = new Point(this.topRightPoint);
        this.leftBarbPoint.offset(-this.mBarbLength, 0);
        this.rightBarbPoint = new Point(this.topRightPoint);
        this.rightBarbPoint.offset(0, this.mBarbLength);
        canvas.drawLine(this.bottomLeftPoint.x, this.bottomLeftPoint.y, this.topRightPoint.x, this.topRightPoint.y, this.learnMorePaint);
        canvas.drawLine(this.topRightPoint.x, this.topRightPoint.y, this.leftBarbPoint.x, this.leftBarbPoint.y, this.learnMorePaint);
        canvas.drawLine(this.topRightPoint.x, this.topRightPoint.y, this.rightBarbPoint.x, this.rightBarbPoint.y, this.learnMorePaint);
    }
}
