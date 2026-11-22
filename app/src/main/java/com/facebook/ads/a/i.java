package com.facebook.ads.a;

import android.R;
import android.content.Context;
import android.content.Intent;
import android.graphics.Camera;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.graphics.drawable.GradientDrawable;
import android.net.Uri;
import android.os.Handler;
import android.support.v4.view.ViewCompat;
import android.text.TextPaint;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.Transformation;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RatingBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.facebook.ads.Ad;
import com.facebook.ads.AdError;
import com.facebook.ads.AdListener;
import com.facebook.ads.AdSize;
import com.facebook.ads.AdView;
import com.facebook.ads.ImpressionListener;
import com.facebook.ads.NativeAd;
import java.util.Arrays;

/* JADX INFO: loaded from: classes.dex */
public class i extends af implements AdListener, d, x {
    private final AdView a;
    private ProgressBar b;
    private DisplayMetrics c;
    private ac d;
    private AdListener e;
    private boolean f;
    private RelativeLayout g;
    private TextView h;
    private boolean i;
    private ViewGroup j;
    private View k;
    private View l;
    private ah m;

    /* JADX INFO: renamed from: com.facebook.ads.a.i$5, reason: invalid class name */
    class AnonymousClass5 implements Animation.AnimationListener {
        final /* synthetic */ int a;
        final /* synthetic */ int b;

        AnonymousClass5(int i, int i2) {
            this.a = i;
            this.b = i2;
        }

        @Override // android.view.animation.Animation.AnimationListener
        public void onAnimationEnd(Animation animation) {
            new Handler().postDelayed(new Runnable() { // from class: com.facebook.ads.a.i.5.1
                @Override // java.lang.Runnable
                public void run() {
                    if (i.this.f) {
                        i.this.f = false;
                        Animation animation2 = new Animation() { // from class: com.facebook.ads.a.i.5.1.1
                            @Override // android.view.animation.Animation
                            protected void applyTransformation(float f, Transformation transformation) {
                                int i = (int) (AnonymousClass5.this.a + ((AnonymousClass5.this.b - AnonymousClass5.this.a) * f));
                                i.this.g.getLayoutParams().width = i;
                                i.this.g.requestLayout();
                                i.this.h.getLayoutParams().width = i - AnonymousClass5.this.b;
                                i.this.h.requestLayout();
                            }

                            @Override // android.view.animation.Animation
                            public boolean willChangeBounds() {
                                return true;
                            }
                        };
                        animation2.setDuration(300L);
                        animation2.setFillAfter(true);
                        i.this.g.startAnimation(animation2);
                    }
                }
            }, 3000L);
        }

        @Override // android.view.animation.Animation.AnimationListener
        public void onAnimationRepeat(Animation animation) {
        }

        @Override // android.view.animation.Animation.AnimationListener
        public void onAnimationStart(Animation animation) {
        }
    }

    public i(AdView adView, String str, AdSize adSize) {
        super(adView.getContext());
        this.f = false;
        this.i = false;
        this.a = adView;
        if (adSize == null || adSize != AdSize.RECTANGLE_HEIGHT_250) {
            throw new IllegalArgumentException("adSize");
        }
        Context context = adView.getContext();
        this.c = context.getResources().getDisplayMetrics();
        setMinWidth(Math.round(300.0f * this.c.density));
        setMaxWidth(Math.round(360.0f * this.c.density));
        setLayoutParams(new RelativeLayout.LayoutParams(-1, Math.round(250.0f * this.c.density)));
        setGravity(17);
        this.b = new ProgressBar(context, null, R.attr.progressBarStyleSmall);
        this.b.setLayoutParams(new RelativeLayout.LayoutParams(-2, -2));
        addView(this.b);
        this.d = new ac(context, str, AdSize.RECTANGLE_HEIGHT_250, n.NATIVE_250, true);
        this.d.setAdListener(this);
    }

    private View a(View view) {
        TextView textView = new TextView(getContext());
        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(-1, -1);
        layoutParams.addRule(3, view.getId());
        layoutParams.setMargins(Math.round(5.0f * this.c.density), Math.round(this.c.density * 10.0f), Math.round(0.0f), Math.round(this.c.density * 10.0f));
        textView.setTextColor(-11643291);
        textView.setLayoutParams(layoutParams);
        textView.setText(this.d.getAdBody());
        textView.setTextSize(10.0f);
        textView.setEllipsize(TextUtils.TruncateAt.END);
        textView.setMaxLines(3);
        textView.setGravity(16);
        return textView;
    }

    private View a(ViewGroup viewGroup, View view) {
        RelativeLayout relativeLayout = new RelativeLayout(getContext());
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(0, -1);
        layoutParams.weight = 1.0f;
        relativeLayout.setLayoutParams(layoutParams);
        View viewB = b(viewGroup, view);
        relativeLayout.addView(viewB);
        View viewA = a(viewB);
        relativeLayout.addView(viewA);
        this.m = new ah(viewB, viewA).a(Math.round(60.0f * this.c.density)).a();
        return relativeLayout;
    }

    private View b(ViewGroup viewGroup, View view) {
        LinearLayout linearLayout = new LinearLayout(getContext());
        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(-1, -1);
        layoutParams.addRule(10);
        layoutParams.setMargins(Math.round(5.0f * this.c.density), Math.round(this.c.density * 10.0f), Math.round(0.0f), Math.round(this.c.density * 10.0f));
        linearLayout.setLayoutParams(layoutParams);
        linearLayout.setOrientation(1);
        linearLayout.addView(c(viewGroup, view));
        linearLayout.addView(i());
        return linearLayout;
    }

    private View c(ViewGroup viewGroup, View view) {
        TextView textView = new TextView(getContext());
        textView.setLayoutParams(new LinearLayout.LayoutParams(-1, -1, 1.0f));
        textView.setText(this.d.getAdTitle());
        textView.setTypeface(Typeface.DEFAULT_BOLD);
        textView.setTextColor(ViewCompat.MEASURED_STATE_MASK);
        textView.setTextSize(16.0f);
        textView.setEllipsize(TextUtils.TruncateAt.END);
        textView.setSingleLine(true);
        TextPaint paint = textView.getPaint();
        String adTitle = this.d.getAdTitle();
        String strSubstring = adTitle.substring(0, Math.min(20, adTitle.length()));
        if (adTitle.length() > 20) {
            strSubstring = strSubstring + "…";
        }
        if (paint.measureText(strSubstring) >= getMeasuredWidth() - (185.0f * this.c.density)) {
            viewGroup.removeView(view);
        }
        return textView;
    }

    private ViewGroup e() {
        RelativeLayout relativeLayout = new RelativeLayout(getContext());
        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(-1, Math.round(190.0f * this.c.density));
        layoutParams.addRule(10);
        relativeLayout.setLayoutParams(layoutParams);
        ImageView imageView = new ImageView(getContext());
        relativeLayout.addView(imageView);
        imageView.setLayoutParams(new RelativeLayout.LayoutParams(-1, -1));
        imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
        new w(imageView).a(this).execute(this.d.getAdCoverImage().getUrl());
        this.l = k();
        relativeLayout.addView(this.l);
        return relativeLayout;
    }

    private View f() {
        RelativeLayout relativeLayout = new RelativeLayout(getContext());
        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(-1, Math.round(60.0f * this.c.density));
        layoutParams.addRule(12);
        relativeLayout.setLayoutParams(layoutParams);
        relativeLayout.addView(g());
        return relativeLayout;
    }

    private View g() {
        LinearLayout linearLayout = new LinearLayout(getContext());
        linearLayout.setLayoutParams(new RelativeLayout.LayoutParams(-1, -1));
        linearLayout.setBackgroundColor(-1);
        linearLayout.setOrientation(0);
        View viewH = h();
        linearLayout.addView(viewH);
        linearLayout.addView(a(linearLayout, viewH));
        View viewJ = j();
        linearLayout.addView(viewJ);
        this.d.unregisterView();
        this.d.registerViewForInteraction(this, Arrays.asList(viewH, viewJ));
        return linearLayout;
    }

    private View h() {
        ImageView imageView = new ImageView(getContext());
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(Math.round(this.c.density * 50.0f), Math.round(this.c.density * 50.0f));
        layoutParams.setMargins(Math.round(this.c.density * 5.0f), Math.round(this.c.density * 5.0f), Math.round(this.c.density * 5.0f), Math.round(this.c.density * 5.0f));
        imageView.setLayoutParams(layoutParams);
        NativeAd.downloadAndDisplayImage(this.d.getAdIcon(), imageView);
        return imageView;
    }

    private View i() {
        NativeAd.Rating adStarRating = this.d.getAdStarRating();
        if (adStarRating == null || adStarRating.getValue() < 3.5d) {
            TextView textView = new TextView(getContext());
            textView.setText(this.d.getAdSocialContext());
            textView.setTextColor(ViewCompat.MEASURED_STATE_MASK);
            textView.setTextSize(12.0f);
            textView.setEllipsize(TextUtils.TruncateAt.END);
            textView.setSingleLine(true);
            return textView;
        }
        RatingBar ratingBar = new RatingBar(getContext(), null, R.attr.ratingBarStyleSmall);
        ratingBar.setLayoutParams(new LinearLayout.LayoutParams(-2, -2));
        ratingBar.setStepSize(0.1f);
        ratingBar.setIsIndicator(true);
        ratingBar.setNumStars((int) adStarRating.getScale());
        ratingBar.setRating((float) adStarRating.getValue());
        return ratingBar;
    }

    private View j() {
        Button button = new Button(getContext(), null, R.attr.borderlessButtonStyle);
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(Math.round(100.0f * this.c.density), Math.round(40.0f * this.c.density));
        layoutParams.setMargins(Math.round(this.c.density * 10.0f), Math.round(this.c.density * 10.0f), Math.round(this.c.density * 10.0f), Math.round(this.c.density * 10.0f));
        button.setLayoutParams(layoutParams);
        button.setTextColor(-1);
        button.setText(this.d.getAdCallToAction());
        button.setTextSize(14.0f);
        button.setGravity(17);
        GradientDrawable gradientDrawable = new GradientDrawable();
        gradientDrawable.setColor(-7617003);
        gradientDrawable.setCornerRadius(5.0f * this.c.density);
        button.setBackgroundDrawable(gradientDrawable);
        return button;
    }

    private View k() {
        this.g = new RelativeLayout(getContext());
        NativeAd.Image adChoicesIcon = this.d.getAdChoicesIcon();
        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(Math.round((adChoicesIcon.getWidth() + 4) * this.c.density), Math.round((adChoicesIcon.getHeight() + 2) * this.c.density));
        layoutParams.addRule(10);
        layoutParams.addRule(11);
        this.g.setLayoutParams(layoutParams);
        GradientDrawable gradientDrawable = new GradientDrawable();
        gradientDrawable.setColor(-1291845632);
        gradientDrawable.setCornerRadii(new float[]{0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, Math.round(this.c.density * 8.0f), Math.round(this.c.density * 8.0f)});
        this.g.setBackgroundDrawable(gradientDrawable);
        this.g.setOnTouchListener(new View.OnTouchListener() { // from class: com.facebook.ads.a.i.1
            @Override // android.view.View.OnTouchListener
            public boolean onTouch(View view, MotionEvent motionEvent) {
                if (motionEvent.getAction() != 0) {
                    return false;
                }
                if (i.this.f) {
                    Intent intent = new Intent();
                    intent.setAction("android.intent.action.VIEW");
                    intent.addCategory("android.intent.category.BROWSABLE");
                    intent.setData(Uri.parse(i.this.d.getAdChoicesLinkUrl()));
                    i.this.getContext().startActivity(intent);
                } else {
                    i.this.m();
                }
                return true;
            }
        });
        ImageView imageView = new ImageView(getContext());
        this.g.addView(imageView);
        RelativeLayout.LayoutParams layoutParams2 = new RelativeLayout.LayoutParams(Math.round(adChoicesIcon.getWidth() * this.c.density), Math.round(adChoicesIcon.getHeight() * this.c.density));
        layoutParams2.addRule(9);
        layoutParams2.addRule(15, -1);
        layoutParams2.setMargins(Math.round(4.0f * this.c.density), Math.round(this.c.density * 2.0f), Math.round(this.c.density * 2.0f), Math.round(this.c.density * 2.0f));
        imageView.setLayoutParams(layoutParams2);
        NativeAd.downloadAndDisplayImage(adChoicesIcon, imageView);
        this.h = new TextView(getContext());
        this.g.addView(this.h);
        RelativeLayout.LayoutParams layoutParams3 = new RelativeLayout.LayoutParams(0, -2);
        layoutParams3.addRule(11, imageView.getId());
        layoutParams3.addRule(15, -1);
        this.h.setLayoutParams(layoutParams3);
        this.h.setSingleLine();
        this.h.setText("AdChoices");
        this.h.setTextSize(10.0f);
        this.h.setTextColor(-4341303);
        this.f = false;
        return this.g;
    }

    private void l() {
        Animation animation = new Animation() { // from class: com.facebook.ads.a.i.2
            private boolean b = false;
            private boolean c = false;

            @Override // android.view.animation.Animation
            protected void applyTransformation(float f, Transformation transformation) {
                double d = 3.141592653589793d * ((double) f);
                float f2 = (float) ((180.0d * d) / 3.141592653589793d);
                if (f >= 0.5f) {
                    f2 -= 180.0f;
                    if (!this.b) {
                        this.b = true;
                        i.this.j.setVisibility(0);
                        i.this.k.setVisibility(0);
                    }
                }
                if (this.c) {
                    f2 = -f2;
                }
                Camera camera = new Camera();
                Matrix matrix = transformation.getMatrix();
                camera.save();
                camera.translate(0.0f, 0.0f, (float) (Math.sin(d) * 150.0d));
                camera.rotateY(f2);
                camera.getMatrix(matrix);
                camera.restore();
                int width = i.this.getWidth() / 2;
                int height = i.this.getHeight() / 2;
                matrix.preTranslate(-width, -height);
                matrix.postTranslate(width, height);
            }
        };
        animation.setDuration(300L);
        animation.setFillAfter(true);
        animation.setAnimationListener(new Animation.AnimationListener() { // from class: com.facebook.ads.a.i.3
            @Override // android.view.animation.Animation.AnimationListener
            public void onAnimationEnd(Animation animation2) {
                i.this.removeView(i.this.getChildAt(0));
                i.this.removeView(i.this.getChildAt(0));
            }

            @Override // android.view.animation.Animation.AnimationListener
            public void onAnimationRepeat(Animation animation2) {
            }

            @Override // android.view.animation.Animation.AnimationListener
            public void onAnimationStart(Animation animation2) {
            }
        });
        startAnimation(animation);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void m() {
        Paint paint = new Paint();
        paint.setTextSize(this.h.getTextSize());
        int iRound = Math.round(paint.measureText("AdChoices") + (4.0f * this.c.density));
        final int width = this.g.getWidth();
        final int i = iRound + width;
        this.f = true;
        Animation animation = new Animation() { // from class: com.facebook.ads.a.i.4
            @Override // android.view.animation.Animation
            protected void applyTransformation(float f, Transformation transformation) {
                int i2 = (int) (width + ((i - width) * f));
                i.this.g.getLayoutParams().width = i2;
                i.this.g.requestLayout();
                i.this.h.getLayoutParams().width = i2 - width;
                i.this.h.requestLayout();
            }

            @Override // android.view.animation.Animation
            public boolean willChangeBounds() {
                return true;
            }
        };
        animation.setAnimationListener(new AnonymousClass5(i, width));
        animation.setDuration(300L);
        animation.setFillAfter(true);
        this.g.startAnimation(animation);
    }

    @Override // com.facebook.ads.a.d
    public void a() {
        this.d.disableAutoRefresh();
    }

    @Override // com.facebook.ads.a.d
    public void b() {
        this.d.loadAd();
    }

    @Override // com.facebook.ads.a.d
    public void c() {
        this.d.destroy();
    }

    @Override // com.facebook.ads.a.x
    public void d() {
        if (this.i) {
            this.j.setVisibility(4);
            this.k.setVisibility(4);
            addView(this.j);
            addView(this.k);
            l();
        } else {
            this.i = true;
            removeView(this.b);
            setGravity(0);
            addView(this.j);
            addView(this.k);
        }
        this.e.onAdLoaded(this.a);
    }

    @Override // com.facebook.ads.AdListener
    public void onAdClicked(Ad ad) {
        this.e.onAdClicked(this.a);
    }

    @Override // com.facebook.ads.AdListener
    public void onAdLoaded(Ad ad) {
        if (this.d == null || this.d != ad) {
            return;
        }
        this.j = e();
        this.k = f();
    }

    @Override // com.facebook.ads.AdListener
    public void onError(Ad ad, AdError adError) {
        this.e.onError(this.a, adError);
        removeView(this.b);
    }

    @Override // android.view.View
    protected void onWindowVisibilityChanged(int i) {
        super.onWindowVisibilityChanged(i);
        this.d.onWindowVisibilityChanged(i);
        if (i == 0) {
            if (this.m != null) {
                this.m.a();
            }
            if (this.j == null || this.l == null) {
                return;
            }
            this.j.removeView(this.l);
            this.l = k();
            this.j.addView(this.l);
        }
    }

    @Override // com.facebook.ads.a.d
    public void setAdListener(AdListener adListener) {
        this.e = adListener;
    }

    @Override // com.facebook.ads.a.d
    public void setImpressionListener(ImpressionListener impressionListener) {
        this.d.setImpressionListener(impressionListener);
    }
}
