package com.millennialmedia.android;

import android.app.Activity;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.os.Parcel;
import android.os.Parcelable;
import android.support.v4.view.MotionEventCompat;
import android.support.v4.view.ViewCompat;
import android.text.TextUtils;
import android.view.TouchDelegate;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.ScaleAnimation;
import android.view.animation.TranslateAnimation;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import java.lang.ref.WeakReference;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.StatusLine;

/* JADX INFO: loaded from: classes.dex */
class AdViewOverlayView extends MMLayout {
    private static final String MRAID_CLOSE_BUTTON_DESRIPTION = "mraidCloseButton";
    private static final String TAG = "AdViewOverlayView";
    private Button mraidCloseButton;
    CloseTopDrawable mraidCloseDrawable;
    WeakReference<AdViewOverlayActivity> overlayActivityRef;
    private ProgressBar progressBar;
    private boolean progressDone;
    OverlaySettings settings;

    private static final class NonConfigurationInstance {
        MMAdImplController controller;
        boolean progressDone;
        OverlaySettings settings;

        private NonConfigurationInstance() {
        }
    }

    AdViewOverlayView(AdViewOverlayActivity overlayActivity, OverlaySettings settingsIn) {
        RelativeLayout.LayoutParams params;
        super(overlayActivity.activity);
        this.overlayActivityRef = new WeakReference<>(overlayActivity);
        this.adImpl = new AdViewOverlayViewMMAdImpl(overlayActivity.activity);
        setId(15062);
        this.adImpl.adType = "i";
        this.settings = settingsIn;
        NonConfigurationInstance nonConfigurationInstance = null;
        if (overlayActivity.activity instanceof Activity) {
            nonConfigurationInstance = (NonConfigurationInstance) overlayActivity.activity.getLastNonConfigurationInstance();
            if (nonConfigurationInstance != null) {
                this.progressDone = nonConfigurationInstance.progressDone;
                this.adImpl.controller = nonConfigurationInstance.controller;
                this.settings = nonConfigurationInstance.settings;
                if (this.adImpl != null && this.adImpl.controller != null && this.adImpl.controller.webView != null) {
                    addView(this.adImpl.controller.webView);
                }
                MMLog.d(TAG, "Restoring configurationinstance w/ controller= " + nonConfigurationInstance.controller);
            } else {
                MMLog.d(TAG, "Null configurationinstance ");
            }
        }
        float scale = overlayActivity.activity.getResources().getDisplayMetrics().density;
        if (this.settings.height != 0 && this.settings.width != 0) {
            params = new RelativeLayout.LayoutParams((int) (this.settings.width * scale), (int) (this.settings.height * scale));
        } else {
            params = new RelativeLayout.LayoutParams(-1, -1);
        }
        params.addRule(13);
        setLayoutParams(params);
        Integer scaledPadding = Integer.valueOf((int) (0.0625f * scale * this.settings.shouldResizeOverlay));
        setPadding(scaledPadding.intValue(), scaledPadding.intValue(), scaledPadding.intValue(), scaledPadding.intValue());
        this.mraidCloseButton = initMRaidCloseButton(overlayActivity.activity, scale);
        if (this.settings.isExpanded() && !this.settings.hasExpandUrl()) {
            this.adImpl.linkForExpansionId = this.settings.creatorAdImplId;
        }
        MMAdImplController.assignAdViewController(this.adImpl);
        if (this.mraidCloseButton != null) {
            addView(this.mraidCloseButton);
        }
        if (!this.progressDone && !this.settings.isExpanded() && !this.settings.isFromInterstitial()) {
            initProgressBar();
        }
        if (this.settings.getIsTransparent()) {
            if (this.adImpl != null && this.adImpl.controller != null && this.adImpl.controller.webView != null) {
                this.adImpl.controller.webView.setBackgroundColor(0);
            }
            setBackgroundColor(0);
        } else {
            if (this.adImpl != null && this.adImpl.controller != null && this.adImpl.controller.webView != null) {
                this.adImpl.controller.webView.setBackgroundColor(-1);
            }
            setBackgroundColor(-1);
        }
        if (this.settings.enableHardwareAccel() && this.adImpl != null && this.adImpl.controller != null && this.adImpl.controller.webView != null) {
            this.adImpl.controller.webView.enableHardwareAcceleration();
        }
        if (nonConfigurationInstance == null) {
            animateView();
        }
        setUseCustomClose(this.settings.getUseCustomClose());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void initProgressBar() {
        AdViewOverlayActivity overlayActivity = this.overlayActivityRef.get();
        if (overlayActivity != null) {
            this.progressBar = new ProgressBar(overlayActivity.activity);
            this.progressBar.setIndeterminate(true);
            this.progressBar.setVisibility(0);
            RelativeLayout.LayoutParams progParams = new RelativeLayout.LayoutParams(-2, -2);
            progParams.addRule(13);
            addView(this.progressBar, progParams);
        }
    }

    private Button initMRaidCloseButton(Context context, float scale) {
        Button mraidCloseButton = new Button(context);
        mraidCloseButton.setId(301);
        mraidCloseButton.setContentDescription(MRAID_CLOSE_BUTTON_DESRIPTION);
        this.mraidCloseDrawable = new CloseTopDrawable(true, scale);
        mraidCloseButton.setOnClickListener(new View.OnClickListener() { // from class: com.millennialmedia.android.AdViewOverlayView.1
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                MMLog.v(AdViewOverlayView.TAG, "Close button clicked.");
                AdViewOverlayView.this.finishOverlayWithAnimation();
            }
        });
        RelativeLayout.LayoutParams closeButParams = getCloseAreaParams(scale);
        mraidCloseButton.setLayoutParams(closeButParams);
        mraidCloseButton.post(new SetCloseButtonTouchDelegateRunnable(mraidCloseButton, closeButParams.topMargin, closeButParams.leftMargin, closeButParams.bottomMargin, closeButParams.rightMargin));
        return mraidCloseButton;
    }

    private RelativeLayout.LayoutParams getCloseAreaParams(float scale) {
        int closeHeight = (int) ((50.0f * scale) + 0.5f);
        RelativeLayout.LayoutParams closeButParams = new RelativeLayout.LayoutParams(closeHeight, closeHeight);
        closeButParams.addRule(11);
        closeButParams.addRule(10);
        return closeButParams;
    }

    private void animateView() {
        Animation animation;
        if (this.settings.getTransition().equals("slideup")) {
            animation = new TranslateAnimation(1, 0.0f, 1, 0.0f, 1, 1.0f, 1, 0.0f);
            MMLog.v(TAG, "Translate up");
        } else if (this.settings.getTransition().equals("slidedown")) {
            animation = new TranslateAnimation(1, 0.0f, 1, 0.0f, 1, -1.0f, 1, 0.0f);
            MMLog.v(TAG, "Translate down");
        } else if (this.settings.getTransition().equals("explode")) {
            animation = new ScaleAnimation(1.1f, 0.9f, 0.1f, 0.9f, 1, 0.5f, 1, 0.5f);
            MMLog.v(TAG, "Explode");
        } else {
            return;
        }
        animation.setDuration(this.settings.getTransitionDurationInMillis());
        startAnimation(animation);
    }

    static class SavedState extends View.BaseSavedState {
        public static final Parcelable.Creator<SavedState> CREATOR = new Parcelable.Creator<SavedState>() { // from class: com.millennialmedia.android.AdViewOverlayView.SavedState.1
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // android.os.Parcelable.Creator
            public SavedState createFromParcel(Parcel in) {
                return new SavedState(in);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // android.os.Parcelable.Creator
            public SavedState[] newArray(int size) {
                return new SavedState[size];
            }
        };
        public Object customInlineLayoutParams;
        String gson;

        SavedState(Parcelable superState) {
            super(superState);
        }

        private SavedState(Parcel in) {
            super(in);
            this.gson = in.readString();
        }

        @Override // android.view.View.BaseSavedState, android.view.AbsSavedState, android.os.Parcelable
        public void writeToParcel(Parcel out, int flags) {
            super.writeToParcel(out, flags);
            out.writeString(this.gson);
        }
    }

    void injectJS(String jsString) {
        if (this.adImpl.controller != null) {
            this.adImpl.controller.loadUrl(jsString);
        }
    }

    Object getNonConfigurationInstance() {
        NonConfigurationInstance nonConfigurationInstance = new NonConfigurationInstance();
        if (this.adImpl != null) {
            MMLog.d(TAG, "Saving getNonConfigurationInstance for " + this.adImpl);
            if (this.adImpl.controller != null && this.adImpl.controller.webView != null) {
                this.adImpl.controller.webView.removeFromParent();
            }
            nonConfigurationInstance.controller = this.adImpl.controller;
        }
        nonConfigurationInstance.progressDone = this.progressDone;
        nonConfigurationInstance.settings = this.settings;
        return nonConfigurationInstance;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void removeProgressBar() {
        if (!this.progressDone && this.progressBar != null) {
            this.progressDone = true;
            this.progressBar.setVisibility(8);
            removeView(this.progressBar);
            this.progressBar = null;
        }
    }

    private static class OverlayWebViewClientListener extends MMAdImpl.BasicWebViewClientListener {
        OverlayWebViewClientListener(MMAdImpl adImpl) {
            super(adImpl);
        }

        @Override // com.millennialmedia.android.MMAdImpl.BasicWebViewClientListener, com.millennialmedia.android.MMWebViewClient.MMWebViewClientListener
        public void onPageFinished(String url) {
            super.onPageFinished(url);
            MMAdImpl adImpl = this.adImplRef.get();
            if (adImpl != null) {
                adImpl.removeProgressBar();
            }
        }
    }

    class AdViewOverlayViewMMAdImpl extends MMLayout.MMLayoutMMAdImpl {
        public AdViewOverlayViewMMAdImpl(Context context) {
            super(context);
            this.mmWebViewClientListener = new OverlayWebViewClientListener(this);
        }

        @Override // com.millennialmedia.android.MMAdImpl
        void removeProgressBar() {
            AdViewOverlayView.this.removeProgressBar();
        }

        @Override // com.millennialmedia.android.MMAdImpl
        boolean isExpandingToUrl() {
            return AdViewOverlayView.this.settings.hasExpandUrl() && !AdViewOverlayView.this.settings.hasLoadedExpandUrl();
        }

        @Override // com.millennialmedia.android.MMAdImpl
        MMWebViewClient getMMWebViewClient() {
            MMLog.d(AdViewOverlayView.TAG, "Returning a client for user: OverlayWebViewClient, adimpl=" + AdViewOverlayView.this.adImpl);
            if (AdViewOverlayView.this.adImpl.linkForExpansionId != 0 || AdViewOverlayView.this.settings.hasExpandUrl()) {
                BannerExpandedWebViewClient bannerExpandedWebViewClient = new BannerExpandedWebViewClient(this.mmWebViewClientListener, new OverlayRedirectionListenerImpl(this));
                this.mmWebViewClient = bannerExpandedWebViewClient;
                return bannerExpandedWebViewClient;
            }
            InterstitialWebViewClient interstitialWebViewClient = new InterstitialWebViewClient(this.mmWebViewClientListener, new OverlayRedirectionListenerImpl(this));
            this.mmWebViewClient = interstitialWebViewClient;
            return interstitialWebViewClient;
        }
    }

    static class OverlayRedirectionListenerImpl extends MMAdImpl.MMAdImplRedirectionListenerImpl {
        public OverlayRedirectionListenerImpl(MMAdImpl adImpl) {
            super(adImpl);
        }

        @Override // com.millennialmedia.android.HttpRedirection.RedirectionListenerImpl, com.millennialmedia.android.HttpRedirection.Listener
        public boolean isExpandingToUrl() {
            MMAdImpl adImpl = this.adImplRef.get();
            if (adImpl == null || !(adImpl instanceof AdViewOverlayViewMMAdImpl)) {
                return false;
            }
            return adImpl.isExpandingToUrl();
        }
    }

    @Override // com.millennialmedia.android.MMLayout
    void closeAreaTouched() {
        post(new Runnable() { // from class: com.millennialmedia.android.AdViewOverlayView.2
            @Override // java.lang.Runnable
            public void run() {
                AdViewOverlayView.this.finishOverlayWithAnimation();
            }
        });
    }

    void finishOverlayWithAnimation() {
        MMLog.d(TAG, "Ad overlay closed");
        Activity activity = (Activity) getContext();
        if (activity != null) {
            AlphaAnimation animation = new AlphaAnimation(1.0f, 0.0f);
            animation.setAnimationListener(new AnimationListener(this));
            animation.setFillEnabled(true);
            animation.setFillBefore(true);
            animation.setFillAfter(true);
            animation.setDuration(400L);
            startAnimation(animation);
        }
    }

    private static class AnimationListener implements Animation.AnimationListener {
        private WeakReference<AdViewOverlayView> overlayRef;

        public AnimationListener(AdViewOverlayView videoView) {
            this.overlayRef = new WeakReference<>(videoView);
        }

        @Override // android.view.animation.Animation.AnimationListener
        public void onAnimationStart(Animation animation) {
            AdViewOverlayView overlayView = this.overlayRef.get();
            if (overlayView != null && overlayView.mraidCloseButton != null) {
                overlayView.mraidCloseButton.setVisibility(8);
            }
        }

        @Override // android.view.animation.Animation.AnimationListener
        public void onAnimationEnd(Animation animation) {
            AdViewOverlayView overlayView = this.overlayRef.get();
            if (overlayView != null) {
                Activity activity = (Activity) overlayView.getContext();
                MMLog.d(AdViewOverlayView.TAG, "Finishing overlay this is in w/ anim finishOverLayWithAnim()");
                activity.finish();
            }
        }

        @Override // android.view.animation.Animation.AnimationListener
        public void onAnimationRepeat(Animation animation) {
        }
    }

    boolean attachWebViewToLink() {
        return (this.adImpl == null || this.adImpl.linkForExpansionId == 0 || !MMAdImplController.attachWebViewFromOverlay(this.adImpl)) ? false : true;
    }

    private static class SetCloseButtonTouchDelegateRunnable implements Runnable {
        int bottom;
        private final Button closeButton;
        int left;
        int right;
        int top;

        SetCloseButtonTouchDelegateRunnable(Button closeButton, int topMargin, int leftMargin, int bottomMargin, int rightMargin) {
            this.closeButton = closeButton;
            this.top = topMargin;
            this.left = leftMargin;
            this.bottom = bottomMargin;
            this.right = rightMargin;
        }

        @Override // java.lang.Runnable
        public void run() {
            Rect delegateArea = new Rect();
            this.closeButton.getHitRect(delegateArea);
            delegateArea.top += this.top;
            delegateArea.right += this.right;
            delegateArea.bottom += this.bottom;
            delegateArea.left += this.left;
            TouchDelegate expandedArea = new TouchDelegate(delegateArea, this.closeButton);
            if (View.class.isInstance(this.closeButton.getParent())) {
                ((View) this.closeButton.getParent()).setTouchDelegate(expandedArea);
            }
        }
    }

    private static class CloseDrawable extends Drawable {
        protected boolean enabled;
        protected final Paint paint = new Paint();

        CloseDrawable(boolean enabled) {
            this.enabled = true;
            this.enabled = enabled;
            this.paint.setAntiAlias(true);
            this.paint.setStyle(Paint.Style.STROKE);
        }

        @Override // android.graphics.drawable.Drawable
        public void draw(Canvas canvas) {
            Rect bounds = copyBounds();
            int width = bounds.right - bounds.left;
            int height = bounds.bottom - bounds.top;
            float strokeWidth = width / 6.0f;
            this.paint.setStrokeWidth(strokeWidth);
            int grayScale = this.enabled ? 255 : 80;
            this.paint.setARGB(MotionEventCompat.ACTION_MASK, grayScale, grayScale, grayScale);
            canvas.drawLine(strokeWidth / 2.0f, strokeWidth / 2.0f, width - (strokeWidth / 2.0f), height - (strokeWidth / 2.0f), this.paint);
            canvas.drawLine(width - (strokeWidth / 2.0f), strokeWidth / 2.0f, strokeWidth / 2.0f, height - (strokeWidth / 2.0f), this.paint);
        }

        @Override // android.graphics.drawable.Drawable
        public int getOpacity() {
            return -3;
        }

        @Override // android.graphics.drawable.Drawable
        public void setAlpha(int alpha) {
        }

        @Override // android.graphics.drawable.Drawable
        public void setColorFilter(ColorFilter cf) {
        }
    }

    private static class CloseTopDrawable extends CloseDrawable {
        final float dist;
        final float scale;

        CloseTopDrawable(boolean enabled, float scale) {
            super(enabled);
            this.scale = scale;
            this.dist = 4.0f * scale;
            this.paint.setColor(ViewCompat.MEASURED_STATE_MASK);
        }

        @Override // com.millennialmedia.android.AdViewOverlayView.CloseDrawable, android.graphics.drawable.Drawable
        public void draw(Canvas canvas) {
            Rect bounds = copyBounds();
            int width = bounds.right - bounds.left;
            float strokeWidth = width / 10.0f;
            float cx = bounds.right - (this.scale * 20.0f);
            float cy = bounds.top + (this.scale * 20.0f);
            this.paint.setStrokeWidth(strokeWidth);
            this.paint.setColor(ViewCompat.MEASURED_STATE_MASK);
            this.paint.setStyle(Paint.Style.STROKE);
            canvas.drawCircle(cx, cy, 12.0f * this.scale, this.paint);
            this.paint.setColor(-1);
            this.paint.setStyle(Paint.Style.FILL_AND_STROKE);
            canvas.drawCircle(cx, cy, this.scale * 10.0f, this.paint);
            this.paint.setColor(ViewCompat.MEASURED_STATE_MASK);
            canvas.drawCircle(cx, cy, 7.0f * this.scale, this.paint);
            this.paint.setColor(-1);
            this.paint.setStrokeWidth(strokeWidth / 2.0f);
            this.paint.setStyle(Paint.Style.STROKE);
            canvas.drawLine(cx - this.dist, cy - this.dist, cx + this.dist, cy + this.dist, this.paint);
            canvas.drawLine(cx + this.dist, cy - this.dist, cx - this.dist, cy + this.dist, this.paint);
        }
    }

    void setUseCustomClose(boolean isHideDrawable) {
        this.settings.setUseCustomClose(isHideDrawable);
        this.mraidCloseButton.setBackgroundDrawable(isHideDrawable ? null : this.mraidCloseDrawable);
    }

    @Override // com.millennialmedia.android.MMLayout
    void addInlineVideo() {
        super.addInlineVideo();
        bringMraidCloseToFront();
    }

    void bringMraidCloseToFront() {
        if (this.mraidCloseButton != null) {
            this.mraidCloseButton.bringToFront();
        }
    }

    @Override // com.millennialmedia.android.MMLayout
    void repositionVideoLayout() {
        removeView(this.inlineVideoLayout);
        addView(this.inlineVideoLayout, this.inlineVideoView.getCustomLayoutParams());
        bringMraidCloseToFront();
    }

    @Override // com.millennialmedia.android.MMLayout
    void fullScreenVideoLayout() {
        removeView(this.inlineVideoLayout);
        addView(this.inlineVideoLayout, new RelativeLayout.LayoutParams(-1, -1));
        bringMraidCloseToFront();
    }

    void inlineConfigChange() {
        if (this.inlineVideoView != null && this.inlineVideoLayout != null) {
            this.inlineVideoLayout.setLayoutParams(this.inlineVideoView.getCustomLayoutParams());
            bringMraidCloseToFront();
        }
    }

    void getWebContent(String urlToLoad) {
        FetchWebViewContentTask task = new FetchWebViewContentTask(this, urlToLoad);
        task.execute(new Void[0]);
    }

    private static class FetchWebViewContentTask extends AsyncTask<Void, Void, String> {
        private WeakReference<AdViewOverlayView> _overlayViewRef;
        private String baseUrl;
        private boolean cancelVideo;

        public FetchWebViewContentTask(AdViewOverlayView view, String baseUrl) {
            this.baseUrl = baseUrl;
            this._overlayViewRef = new WeakReference<>(view);
        }

        @Override // android.os.AsyncTask
        protected void onPreExecute() {
            AdViewOverlayView view = this._overlayViewRef.get();
            if (view != null && view.progressBar == null) {
                view.initProgressBar();
            }
            super.onPreExecute();
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // android.os.AsyncTask
        public String doInBackground(Void... arg0) {
            HttpEntity httpEntity;
            this.cancelVideo = true;
            if (!TextUtils.isEmpty(this.baseUrl)) {
                try {
                    HttpResponse httpResponse = new HttpGetRequest().get(this.baseUrl);
                    if (httpResponse != null) {
                        StatusLine statusLine = httpResponse.getStatusLine();
                        if (httpResponse != null && statusLine != null && statusLine.getStatusCode() != 404 && (httpEntity = httpResponse.getEntity()) != null) {
                            String webContent = HttpGetRequest.convertStreamToString(httpEntity.getContent());
                            this.cancelVideo = false;
                            return webContent;
                        }
                    }
                } catch (Exception e) {
                    MMLog.e(AdViewOverlayView.TAG, "Unable to get weboverlay", e);
                }
            }
            return null;
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // android.os.AsyncTask
        public void onPostExecute(String webContent) {
            AdViewOverlayView view = this._overlayViewRef.get();
            if (view != null) {
                if (this.cancelVideo) {
                    AdViewOverlayActivity overlayActivity = view.overlayActivityRef.get();
                    if (overlayActivity == null) {
                        view.removeProgressBar();
                    } else {
                        overlayActivity.finish();
                    }
                }
                if (webContent != null && view.adImpl != null && view.adImpl.controller != null) {
                    view.adImpl.controller.setWebViewContent(webContent, this.baseUrl);
                }
            }
        }
    }

    void removeSelfAndAll() {
        removeAllViews();
        ViewParent parent = getParent();
        if (parent != null && (parent instanceof ViewGroup)) {
            ((ViewGroup) parent).removeView(this);
        }
    }

    void killWebView() {
        BridgeMMSpeechkit.releaseSpeechKit();
        if (this.adImpl != null && this.adImpl.controller != null && this.adImpl.controller.webView != null) {
            this.adImpl.controller.webView.clearFocus();
            this.adImpl.controller.webView.setMraidViewableHidden();
            if (this.adImpl.adType == "i") {
                this.adImpl.controller.webView.setMraidHidden();
            }
            this.adImpl.controller.webView.onPauseWebView();
        }
    }
}
