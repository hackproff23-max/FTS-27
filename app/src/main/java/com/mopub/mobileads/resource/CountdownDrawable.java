package com.mopub.mobileads.resource;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import com.mopub.common.util.Dips;

/* JADX INFO: loaded from: classes.dex */
public class CountdownDrawable extends CircleDrawable implements TextDrawable {
    private static final float TEXT_SIZE_SP = 18.0f;
    private String mSecondsRemaining = "";
    private final Paint mTextPaint = new Paint();
    private Rect mTextRect;
    private final float textSizePixels;

    public CountdownDrawable(Context context) {
        this.textSizePixels = Dips.dipsToFloatPixels(TEXT_SIZE_SP, context);
        this.mTextPaint.setTextSize(this.textSizePixels);
        this.mTextPaint.setAntiAlias(true);
        this.mTextPaint.setColor(-1);
        this.mTextPaint.setStyle(Paint.Style.FILL);
        this.mTextPaint.setTextAlign(Paint.Align.LEFT);
        this.mTextRect = new Rect();
    }

    @Override // com.mopub.mobileads.resource.CircleDrawable, android.graphics.drawable.Drawable
    public void draw(Canvas canvas) {
        super.draw(canvas);
        String text = String.valueOf(this.mSecondsRemaining);
        this.mTextPaint.getTextBounds(text, 0, text.length(), this.mTextRect);
        int x = getCenterX() - (this.mTextRect.width() / 2);
        int y = getCenterY() + (this.mTextRect.height() / 2);
        canvas.drawText(text, x, y, this.mTextPaint);
    }

    @Override // com.mopub.mobileads.resource.TextDrawable
    public void updateText(String text) {
        if (!this.mSecondsRemaining.equals(text)) {
            this.mSecondsRemaining = text;
            invalidateSelf();
        }
    }
}
