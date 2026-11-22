package com.google.android.gms.ads.doubleclick;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdSize;
import com.google.android.gms.internal.bh;

/* JADX INFO: loaded from: classes.dex */
public final class PublisherAdView extends ViewGroup {
    private final bh ll;

    public PublisherAdView(Context context) {
        super(context);
        this.ll = new bh(this);
    }

    public PublisherAdView(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.ll = new bh(this, attrs, true);
    }

    public PublisherAdView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        this.ll = new bh(this, attrs, true);
    }

    public void destroy() {
        this.ll.destroy();
    }

    public AdListener getAdListener() {
        return this.ll.getAdListener();
    }

    public AdSize getAdSize() {
        return this.ll.getAdSize();
    }

    public AdSize[] getAdSizes() {
        return this.ll.getAdSizes();
    }

    public String getAdUnitId() {
        return this.ll.getAdUnitId();
    }

    public AppEventListener getAppEventListener() {
        return this.ll.getAppEventListener();
    }

    public String getMediationAdapterClassName() {
        return this.ll.getMediationAdapterClassName();
    }

    public void loadAd(PublisherAdRequest publisherAdRequest) {
        this.ll.a(publisherAdRequest.Y());
    }

    @Override // android.view.ViewGroup, android.view.View
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        View childAt = getChildAt(0);
        if (childAt == null || childAt.getVisibility() == 8) {
            return;
        }
        int measuredWidth = childAt.getMeasuredWidth();
        int measuredHeight = childAt.getMeasuredHeight();
        int i = ((right - left) - measuredWidth) / 2;
        int i2 = ((bottom - top) - measuredHeight) / 2;
        childAt.layout(i, i2, measuredWidth + i, measuredHeight + i2);
    }

    @Override // android.view.View
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int widthInPixels;
        int heightInPixels = 0;
        View childAt = getChildAt(0);
        AdSize adSize = getAdSize();
        if (childAt != null && childAt.getVisibility() != 8) {
            measureChild(childAt, widthMeasureSpec, heightMeasureSpec);
            widthInPixels = childAt.getMeasuredWidth();
            heightInPixels = childAt.getMeasuredHeight();
        } else if (adSize != null) {
            Context context = getContext();
            widthInPixels = adSize.getWidthInPixels(context);
            heightInPixels = adSize.getHeightInPixels(context);
        } else {
            widthInPixels = 0;
        }
        setMeasuredDimension(View.resolveSize(Math.max(widthInPixels, getSuggestedMinimumWidth()), widthMeasureSpec), View.resolveSize(Math.max(heightInPixels, getSuggestedMinimumHeight()), heightMeasureSpec));
    }

    public void pause() {
        this.ll.pause();
    }

    public void recordManualImpression() {
        this.ll.recordManualImpression();
    }

    public void resume() {
        this.ll.resume();
    }

    public void setAdListener(AdListener adListener) {
        this.ll.setAdListener(adListener);
    }

    public void setAdSizes(AdSize... adSizes) {
        if (adSizes == null || adSizes.length < 1) {
            throw new IllegalArgumentException("The supported ad sizes must contain at least one valid ad size.");
        }
        this.ll.a(adSizes);
    }

    public void setAdUnitId(String adUnitId) {
        this.ll.setAdUnitId(adUnitId);
    }

    public void setAppEventListener(AppEventListener appEventListener) {
        this.ll.setAppEventListener(appEventListener);
    }
}
