package com.unity3d.ads.android.view;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.RoundRectShape;
import android.util.AttributeSet;
import android.view.animation.AlphaAnimation;
import android.view.animation.AnimationSet;
import android.view.animation.ScaleAnimation;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.google.android.gms.games.GamesActivityResultCodes;
import com.unity3d.ads.android.UnityAdsDeviceLog;

/* JADX INFO: loaded from: classes.dex */
public class UnityAdsBufferingView extends LinearLayout {
    private TextView a;
    private ImageView b;
    private ImageView c;
    private ImageView d;

    public UnityAdsBufferingView(Context context) {
        super(context);
        this.a = null;
        this.b = null;
        this.c = null;
        this.d = null;
        a();
    }

    public UnityAdsBufferingView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.a = null;
        this.b = null;
        this.c = null;
        this.d = null;
        a();
    }

    private void a() {
        setOrientation(0);
        setPadding(10, 8, 10, 10);
        this.a = new TextView(getContext());
        this.a.setTextColor(-1);
        this.a.setText("Buffering");
        this.a.setId(10000);
        addView(this.a, new LinearLayout.LayoutParams(-2, -2));
        ShapeDrawable shapeDrawable = new ShapeDrawable(new RoundRectShape(new float[]{12.0f, 12.0f, 12.0f, 12.0f, 12.0f, 12.0f, 12.0f, 12.0f}, null, null));
        shapeDrawable.getPaint().setColor(-2013265920);
        shapeDrawable.getPaint().setStyle(Paint.Style.FILL);
        setBackgroundDrawable(shapeDrawable);
        LinearLayout linearLayout = new LinearLayout(getContext());
        linearLayout.setOrientation(0);
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(-2, -2);
        layoutParams.leftMargin = 3;
        this.b = a(GamesActivityResultCodes.RESULT_RECONNECT_REQUIRED);
        this.c = a(GamesActivityResultCodes.RESULT_SIGN_IN_FAILED);
        this.d = a(GamesActivityResultCodes.RESULT_LICENSE_FAILED);
        linearLayout.addView(this.b, layoutParams);
        linearLayout.addView(this.c, layoutParams);
        linearLayout.addView(this.d, layoutParams);
        LinearLayout.LayoutParams layoutParams2 = new LinearLayout.LayoutParams(-2, -2);
        layoutParams2.setMargins(3, 9, 0, 0);
        addView(linearLayout, layoutParams2);
    }

    private static AnimationSet b() {
        AnimationSet animationSet = new AnimationSet(false);
        ScaleAnimation scaleAnimation = new ScaleAnimation(1.0f, 0.0f, 1.0f, 0.0f, 8.0f, 8.0f);
        scaleAnimation.setRepeatCount(-1);
        scaleAnimation.setRepeatMode(2);
        scaleAnimation.setFillBefore(true);
        scaleAnimation.setFillAfter(true);
        scaleAnimation.setDuration(800L);
        AlphaAnimation alphaAnimation = new AlphaAnimation(1.0f, 0.0f);
        alphaAnimation.setRepeatCount(-1);
        alphaAnimation.setRepeatMode(2);
        alphaAnimation.setFillBefore(true);
        alphaAnimation.setFillAfter(true);
        alphaAnimation.setDuration(800L);
        animationSet.addAnimation(scaleAnimation);
        animationSet.addAnimation(alphaAnimation);
        return animationSet;
    }

    private ImageView a(int i) {
        Bitmap bitmapCreateBitmap = Bitmap.createBitmap(17, 17, Bitmap.Config.ARGB_4444);
        Canvas canvas = new Canvas(bitmapCreateBitmap);
        Paint paint = new Paint(1);
        paint.setColor(-1);
        canvas.drawCircle(8.0f, 8.0f, 8.0f, paint);
        ImageView imageView = new ImageView(getContext());
        imageView.setImageBitmap(bitmapCreateBitmap);
        imageView.setId(i);
        return imageView;
    }

    @Override // android.view.ViewGroup, android.view.View
    protected void onAttachedToWindow() {
        UnityAdsDeviceLog.entered();
        super.onAttachedToWindow();
        if (this.b != null) {
            this.b.setAnimation(b());
        }
        if (this.c != null) {
            this.c.setAnimation(b());
        }
        if (this.d != null) {
            this.d.setAnimation(b());
        }
    }

    @Override // android.view.ViewGroup, android.view.View
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        if (this.b != null) {
            this.b.clearAnimation();
        }
        if (this.c != null) {
            this.c.clearAnimation();
        }
        if (this.d != null) {
            this.d.clearAnimation();
        }
    }
}
