package com.unity3d.ads.android.video;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.view.animation.AlphaAnimation;
import android.view.animation.AnimationSet;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.unity3d.ads.android.properties.UnityAdsProperties;

/* JADX INFO: loaded from: classes.dex */
public class UnityAdsVideoPausedView extends RelativeLayout {
    private static float e;
    private ImageView a;
    private ImageView b;
    private ImageView c;
    private TextView d;

    public UnityAdsVideoPausedView(Context context) {
        super(context);
        this.a = null;
        this.b = null;
        this.c = null;
        this.d = null;
        a();
    }

    public UnityAdsVideoPausedView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.a = null;
        this.b = null;
        this.c = null;
        this.d = null;
        a();
    }

    public UnityAdsVideoPausedView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.a = null;
        this.b = null;
        this.c = null;
        this.d = null;
        a();
    }

    public static void initScreenMetrics(Activity activity) {
        e = activity.getResources().getDisplayMetrics().density;
    }

    private void a() {
        setBackgroundColor(-1073741824);
        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(-2, -2);
        layoutParams.addRule(13);
        DisplayMetrics displayMetrics = UnityAdsProperties.getCurrentActivity().getResources().getDisplayMetrics();
        Bitmap bitmapCreateBitmap = Bitmap.createBitmap(Math.round(113.0f * displayMetrics.density), Math.round(113.0f * displayMetrics.density), Bitmap.Config.ARGB_4444);
        Canvas canvas = new Canvas(bitmapCreateBitmap);
        Paint paint = new Paint(1);
        paint.setColor(-1);
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(5.0f);
        canvas.drawCircle(Math.round(56.0f * displayMetrics.density), Math.round(56.0f * displayMetrics.density), Math.round(displayMetrics.density * 53.0f), paint);
        ImageView imageView = new ImageView(getContext());
        imageView.setImageBitmap(bitmapCreateBitmap);
        imageView.setId(60006);
        this.b = imageView;
        addView(this.b, layoutParams);
        RelativeLayout.LayoutParams layoutParams2 = new RelativeLayout.LayoutParams(-2, -2);
        layoutParams2.addRule(13);
        DisplayMetrics displayMetrics2 = UnityAdsProperties.getCurrentActivity().getResources().getDisplayMetrics();
        Bitmap bitmapCreateBitmap2 = Bitmap.createBitmap(Math.round(101.0f * displayMetrics2.density), Math.round(101.0f * displayMetrics2.density), Bitmap.Config.ARGB_4444);
        Canvas canvas2 = new Canvas(bitmapCreateBitmap2);
        Paint paint2 = new Paint(1);
        paint2.setColor(-1879048192);
        canvas2.drawCircle(Math.round(50.0f * displayMetrics2.density), Math.round(50.0f * displayMetrics2.density), Math.round(displayMetrics2.density * 50.0f), paint2);
        ImageView imageView2 = new ImageView(getContext());
        imageView2.setImageBitmap(bitmapCreateBitmap2);
        imageView2.setId(60001);
        this.a = imageView2;
        addView(this.a, layoutParams2);
        RelativeLayout.LayoutParams layoutParams3 = new RelativeLayout.LayoutParams(-2, -2);
        layoutParams3.addRule(15);
        layoutParams3.addRule(5, 60001);
        layoutParams3.leftMargin = Math.round(31.0f * e);
        DisplayMetrics displayMetrics3 = UnityAdsProperties.getCurrentActivity().getResources().getDisplayMetrics();
        Bitmap bitmapCreateBitmap3 = Bitmap.createBitmap(Math.round(47.0f * displayMetrics3.density), Math.round(51.0f * displayMetrics3.density), Bitmap.Config.ARGB_4444);
        Canvas canvas3 = new Canvas(bitmapCreateBitmap3);
        Paint paint3 = new Paint(1);
        paint3.setColor(-1);
        paint3.setStyle(Paint.Style.FILL);
        float[] fArr = {0.0f, 0.0f, 47.0f * displayMetrics3.density, 25.0f * displayMetrics3.density, 0.0f, displayMetrics3.density * 51.0f, 0.0f, 0.0f};
        canvas3.drawVertices(Canvas.VertexMode.TRIANGLES, 8, fArr, 0, null, 0, null, 0, null, 0, 0, paint3);
        Path path = new Path();
        path.moveTo(fArr[0], fArr[1]);
        path.lineTo(fArr[2], fArr[3]);
        path.lineTo(fArr[4], fArr[5]);
        canvas3.drawPath(path, paint3);
        ImageView imageView3 = new ImageView(getContext());
        imageView3.setImageBitmap(bitmapCreateBitmap3);
        imageView3.setId(60002);
        this.c = imageView3;
        addView(this.c, layoutParams3);
        this.d = new TextView(getContext());
        this.d.setTextColor(-1);
        this.d.setText("Video paused. Tap screen to continue watching.");
        this.d.setId(60003);
        RelativeLayout.LayoutParams layoutParams4 = new RelativeLayout.LayoutParams(-2, -2);
        layoutParams4.addRule(14);
        layoutParams4.addRule(12);
        layoutParams4.bottomMargin = Math.round(20.0f * e);
        addView(this.d, layoutParams4);
    }

    @Override // android.view.ViewGroup, android.view.View
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        if (this.b != null) {
            ImageView imageView = this.b;
            AnimationSet animationSet = new AnimationSet(false);
            AlphaAnimation alphaAnimation = new AlphaAnimation(1.0f, 0.0f);
            alphaAnimation.setRepeatCount(-1);
            alphaAnimation.setRepeatMode(2);
            alphaAnimation.setFillBefore(true);
            alphaAnimation.setFillAfter(true);
            alphaAnimation.setDuration(800L);
            animationSet.addAnimation(alphaAnimation);
            imageView.setAnimation(animationSet);
        }
    }

    @Override // android.view.ViewGroup, android.view.View
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        if (this.b != null) {
            this.b.clearAnimation();
        }
    }
}
