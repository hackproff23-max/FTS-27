package com.millennialmedia.android;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Parcelable;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.view.Window;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.lang.reflect.Method;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Map;
import java.util.Random;

/* JADX INFO: loaded from: classes.dex */
public final class MMAdView extends MMLayout implements View.OnClickListener, Animation.AnimationListener {
    static final int DEFAULT_RESIZE_PARAM_VALUES = -50;
    private static final String TAG = "MMAdView";
    public static final int TRANSITION_DOWN = 3;
    public static final int TRANSITION_FADE = 1;
    public static final int TRANSITION_NONE = 0;
    public static final int TRANSITION_RANDOM = 4;
    public static final int TRANSITION_UP = 2;
    int height;
    int oldHeight;
    int oldWidth;
    ImageView refreshAnimationimageView;
    int transitionType;
    ResizeView view;
    int width;

    @Override // com.millennialmedia.android.MMLayout
    public /* bridge */ /* synthetic */ void addBlackView() {
        super.addBlackView();
    }

    @Override // com.millennialmedia.android.MMLayout, com.millennialmedia.android.MMAd
    public /* bridge */ /* synthetic */ String getApid() {
        return super.getApid();
    }

    @Override // com.millennialmedia.android.MMLayout, com.millennialmedia.android.MMAd
    public /* bridge */ /* synthetic */ boolean getIgnoresDensityScaling() {
        return super.getIgnoresDensityScaling();
    }

    @Override // com.millennialmedia.android.MMLayout, com.millennialmedia.android.MMAd
    public /* bridge */ /* synthetic */ RequestListener getListener() {
        return super.getListener();
    }

    @Override // com.millennialmedia.android.MMLayout, com.millennialmedia.android.MMAd
    public /* bridge */ /* synthetic */ MMRequest getMMRequest() {
        return super.getMMRequest();
    }

    @Override // com.millennialmedia.android.MMLayout, android.view.View
    public /* bridge */ /* synthetic */ boolean onTouchEvent(MotionEvent x0) {
        return super.onTouchEvent(x0);
    }

    @Override // com.millennialmedia.android.MMLayout, com.millennialmedia.android.InlineVideoView.TransparentFix
    public /* bridge */ /* synthetic */ void removeBlackView() {
        super.removeBlackView();
    }

    @Override // com.millennialmedia.android.MMLayout, com.millennialmedia.android.MMAd
    public /* bridge */ /* synthetic */ void setApid(String x0) {
        super.setApid(x0);
    }

    @Override // com.millennialmedia.android.MMLayout, com.millennialmedia.android.MMAd
    public /* bridge */ /* synthetic */ void setIgnoresDensityScaling(boolean x0) {
        super.setIgnoresDensityScaling(x0);
    }

    @Override // com.millennialmedia.android.MMLayout, com.millennialmedia.android.MMAd
    public /* bridge */ /* synthetic */ void setListener(RequestListener x0) {
        super.setListener(x0);
    }

    @Override // com.millennialmedia.android.MMLayout, com.millennialmedia.android.MMAd
    public /* bridge */ /* synthetic */ void setMMRequest(MMRequest x0) {
        super.setMMRequest(x0);
    }

    public MMAdView(Context context) {
        super(context);
        this.transitionType = 4;
        this.height = 0;
        this.width = 0;
        this.oldHeight = DEFAULT_RESIZE_PARAM_VALUES;
        this.oldWidth = DEFAULT_RESIZE_PARAM_VALUES;
        this.adImpl = new MMAdViewMMAdImpl(context);
        init(context);
    }

    @Deprecated
    public MMAdView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    @Deprecated
    public MMAdView(Context context, AttributeSet attrs, int defStyle) throws Throwable {
        super(context, attrs, defStyle);
        this.transitionType = 4;
        this.height = 0;
        this.width = 0;
        this.oldHeight = DEFAULT_RESIZE_PARAM_VALUES;
        this.oldWidth = DEFAULT_RESIZE_PARAM_VALUES;
        if (!isInEditMode()) {
            MMLog.d(TAG, "Creating MMAdView from XML layout.");
            this.adImpl = new MMAdViewMMAdImpl(context);
            if (attrs != null) {
                setApid(attrs.getAttributeValue("http://millennialmedia.com/android/schema", "apid"));
                this.adImpl.ignoreDensityScaling = attrs.getAttributeBooleanValue("http://millennialmedia.com/android/schema", "ignoreDensityScaling", false);
                String heightIn = attrs.getAttributeValue("http://millennialmedia.com/android/schema", "height");
                String widthIn = attrs.getAttributeValue("http://millennialmedia.com/android/schema", "width");
                try {
                    if (!TextUtils.isEmpty(heightIn)) {
                        this.height = Integer.parseInt(heightIn);
                    }
                    if (!TextUtils.isEmpty(widthIn)) {
                        this.width = Integer.parseInt(widthIn);
                    }
                } catch (NumberFormatException nfe) {
                    MMLog.e(TAG, "Error reading attrs file from xml", nfe);
                }
                if (this.adImpl.mmRequest != null) {
                    this.adImpl.mmRequest.age = attrs.getAttributeValue("http://millennialmedia.com/android/schema", MMRequest.KEY_AGE);
                    this.adImpl.mmRequest.children = attrs.getAttributeValue("http://millennialmedia.com/android/schema", MMRequest.KEY_CHILDREN);
                    this.adImpl.mmRequest.education = attrs.getAttributeValue("http://millennialmedia.com/android/schema", MMRequest.KEY_EDUCATION);
                    this.adImpl.mmRequest.ethnicity = attrs.getAttributeValue("http://millennialmedia.com/android/schema", MMRequest.KEY_ETHNICITY);
                    this.adImpl.mmRequest.gender = attrs.getAttributeValue("http://millennialmedia.com/android/schema", MMRequest.KEY_GENDER);
                    this.adImpl.mmRequest.income = attrs.getAttributeValue("http://millennialmedia.com/android/schema", MMRequest.KEY_INCOME);
                    this.adImpl.mmRequest.keywords = attrs.getAttributeValue("http://millennialmedia.com/android/schema", MMRequest.KEY_KEYWORDS);
                    this.adImpl.mmRequest.marital = attrs.getAttributeValue("http://millennialmedia.com/android/schema", MMRequest.KEY_MARITAL_STATUS);
                    this.adImpl.mmRequest.politics = attrs.getAttributeValue("http://millennialmedia.com/android/schema", MMRequest.KEY_POLITICS);
                    this.adImpl.mmRequest.vendor = attrs.getAttributeValue("http://millennialmedia.com/android/schema", MMRequest.KEY_VENDOR);
                    this.adImpl.mmRequest.zip = attrs.getAttributeValue("http://millennialmedia.com/android/schema", MMRequest.KEY_ZIP_CODE);
                }
                this.goalId = attrs.getAttributeValue("http://millennialmedia.com/android/schema", "goalId");
            }
            init(context);
            return;
        }
        initEclipseAd(context);
    }

    @Override // android.view.View
    public void setBackgroundColor(int color) {
        super.setBackgroundColor(color);
        if (this.adImpl != null && this.adImpl.controller != null && this.adImpl.controller.webView != null) {
            this.adImpl.controller.webView.setBackgroundColor(color);
        }
    }

    private void init(Context context) {
        setBackgroundColor(0);
        this.adImpl.adType = "b";
        setOnClickListener(this);
        setFocusable(true);
        this.refreshAnimationimageView = new ImageView(context);
        this.refreshAnimationimageView.setScaleType(ImageView.ScaleType.FIT_XY);
        this.refreshAnimationimageView.setVisibility(8);
        addView(this.refreshAnimationimageView, new RelativeLayout.LayoutParams(-2, -2));
        setLayoutParams(new RelativeLayout.LayoutParams(-2, -2));
    }

    /* JADX WARN: Code duplicated, block: B:23:0x008e A[Catch: Exception -> 0x00d0, TRY_LEAVE, TryCatch #0 {Exception -> 0x00d0, blocks: (B:21:0x0089, B:23:0x008e), top: B:53:0x0089 }] */
    /* JADX WARN: Code duplicated, block: B:34:0x00ae A[Catch: Exception -> 0x00b2, TRY_LEAVE, TryCatch #6 {Exception -> 0x00b2, blocks: (B:32:0x00a9, B:34:0x00ae), top: B:60:0x00a9 }] */
    /* JADX WARN: Code duplicated, block: B:43:0x00c3 A[Catch: Exception -> 0x00c7, TRY_LEAVE, TryCatch #4 {Exception -> 0x00c7, blocks: (B:41:0x00be, B:43:0x00c3), top: B:57:0x00be }] */
    private void initEclipseAd(Context context) throws Throwable {
        String str;
        String str2;
        Exception e;
        ImageView logoForXML = new ImageView(context);
        InputStream is = null;
        OutputStream out = null;
        try {
            try {
                String dir = System.getProperty("java.io.tmpdir");
                if (dir != null && !dir.endsWith(File.separator)) {
                    dir = dir + File.separator;
                }
                File file = new File(dir + "millenial355jns6u3l1nmedia.png");
                if (!file.exists()) {
                    HttpURLConnection conn = (HttpURLConnection) new URL("http://images.millennialmedia.com/9513/192134.gif").openConnection();
                    conn.setDoOutput(true);
                    conn.setConnectTimeout(10000);
                    conn.connect();
                    is = conn.getInputStream();
                    OutputStream out2 = new FileOutputStream(file);
                    try {
                        byte[] buffer = new byte[1024];
                        while (true) {
                            int bytesRead = is.read(buffer);
                            if (bytesRead <= 0) {
                                break;
                            } else {
                                out2.write(buffer, 0, bytesRead);
                            }
                            MMLog.e(str2, str, e);
                            addView(logoForXML);
                        }
                        out = out2;
                    } catch (Exception e2) {
                        e = e2;
                        out = out2;
                        MMLog.e(TAG, "Error with eclipse xml image display: ", e);
                        if (is != null) {
                            try {
                                is.close();
                                if (out != null) {
                                    out.close();
                                }
                            } catch (Exception e3) {
                                e = e3;
                                str2 = TAG;
                                str = "Error closing file";
                                MMLog.e(str2, str, e);
                            }
                        } else if (out != null) {
                            out.close();
                        }
                        addView(logoForXML);
                    } catch (Throwable th) {
                        th = th;
                        out = out2;
                        if (is != null) {
                            try {
                                is.close();
                                if (out != null) {
                                    out.close();
                                }
                            } catch (Exception e4) {
                                MMLog.e(TAG, "Error closing file", e4);
                                throw th;
                            }
                        } else if (out != null) {
                            out.close();
                        }
                        throw th;
                    }
                }
                Bitmap bmp = BitmapFactory.decodeFile(file.getAbsolutePath());
                if (logoForXML != null && bmp != null) {
                    logoForXML.setImageBitmap(bmp);
                }
                if (is != null) {
                    try {
                        is.close();
                        if (out != null) {
                            out.close();
                        }
                    } catch (Exception e5) {
                        e = e5;
                        str2 = TAG;
                        str = "Error closing file";
                        MMLog.e(str2, str, e);
                    }
                } else if (out != null) {
                    out.close();
                }
            } catch (Exception e6) {
                e = e6;
            }
            addView(logoForXML);
        } catch (Throwable th2) {
            th = th2;
        }
    }

    @Override // android.view.View.OnClickListener
    @Deprecated
    public void onClick(View v) {
        MMLog.d(TAG, "On click for " + v.getId() + " view, " + v + " adimpl" + this.adImpl);
        onTouchEvent(MotionEvent.obtain(0L, System.currentTimeMillis(), 1, 0.0f, 0.0f, 0));
    }

    @Override // android.widget.RelativeLayout, android.view.ViewGroup, android.view.View
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
        Utils.ThreadUtils.execute(new Runnable() { // from class: com.millennialmedia.android.MMAdView.1
            @Override // java.lang.Runnable
            public void run() {
                float density = MMAdView.this.getContext().getResources().getDisplayMetrics().density;
                if (MMAdView.this.width <= 0) {
                    MMAdView.this.width = (int) (MMAdView.this.getWidth() / density);
                }
                if (MMAdView.this.height <= 0) {
                    MMAdView.this.height = (int) (MMAdView.this.getHeight() / density);
                }
            }
        });
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public void setTransitionType(int type) {
        this.transitionType = type;
    }

    @Override // com.millennialmedia.android.MMLayout
    void closeAreaTouched() {
        this.adImpl.unresizeToDefault();
    }

    private static class MMAdViewWebViewClientListener extends MMAdImpl.BasicWebViewClientListener {
        MMAdViewWebViewClientListener(MMAdImpl adImpl) {
            super(adImpl);
        }

        @Override // com.millennialmedia.android.MMAdImpl.BasicWebViewClientListener, com.millennialmedia.android.MMWebViewClient.MMWebViewClientListener
        public void onPageFinished(String url) {
            super.onPageFinished(url);
            MMAdImpl adImpl = this.adImplRef.get();
            if (adImpl != null && adImpl.isTransitionAnimated()) {
                adImpl.animateTransition();
            }
        }
    }

    class MMAdViewMMAdImpl extends MMLayout.MMLayoutMMAdImpl {
        public MMAdViewMMAdImpl(Context context) {
            super(context);
            this.mmWebViewClientListener = new MMAdViewWebViewClientListener(this);
        }

        @Override // com.millennialmedia.android.MMAdImpl
        String getRequestFailedAction() {
            return MMBroadcastReceiver.ACTION_GETAD_FAILED;
        }

        @Override // com.millennialmedia.android.MMAdImpl
        String getRequestCompletedAction() {
            return MMBroadcastReceiver.ACTION_GETAD_SUCCEEDED;
        }

        @Override // com.millennialmedia.android.MMAdImpl
        String getReqType() {
            return "getad";
        }

        @Override // com.millennialmedia.android.MMAdImpl
        boolean isTransitionAnimated() {
            return MMAdView.this.transitionType != 0;
        }

        @Override // com.millennialmedia.android.MMAdImpl
        void prepareTransition(Bitmap bitmap) {
            MMAdView.this.refreshAnimationimageView.setImageBitmap(bitmap);
            MMAdView.this.refreshAnimationimageView.setVisibility(0);
            MMAdView.this.refreshAnimationimageView.bringToFront();
        }

        @Override // com.millennialmedia.android.MMAdImpl
        public boolean isBanner() {
            return true;
        }

        @Override // com.millennialmedia.android.MMAdImpl
        public boolean hasCachedVideoSupport() {
            return false;
        }

        @Override // com.millennialmedia.android.MMAdImpl
        void insertUrlAdMetaValues(Map<String, String> paramsMap) {
            if (MMAdView.this.height > 0) {
                paramsMap.put(MMRequest.KEY_HEIGHT, String.valueOf(MMAdView.this.height));
            }
            if (MMAdView.this.width > 0) {
                paramsMap.put(MMRequest.KEY_WIDTH, String.valueOf(MMAdView.this.width));
            }
            super.insertUrlAdMetaValues(paramsMap);
        }

        @Override // com.millennialmedia.android.MMAdImpl
        boolean isLifecycleObservable() {
            return MMAdView.this.getWindowToken() != null;
        }

        @Override // com.millennialmedia.android.MMAdImpl
        void animateTransition() {
            Animation animation;
            if (MMAdView.this.refreshAnimationimageView.getDrawable() != null) {
                int type = MMAdView.this.transitionType;
                if (type == 4) {
                    Random r = new Random();
                    type = r.nextInt(4);
                }
                switch (type) {
                    case 2:
                        float height = MMAdView.this.getHeight();
                        animation = new TranslateAnimation(0.0f, 0.0f, 0.0f, -height);
                        break;
                    case 3:
                        float height2 = MMAdView.this.getHeight();
                        animation = new TranslateAnimation(0.0f, 0.0f, 0.0f, height2);
                        break;
                    default:
                        animation = new AlphaAnimation(1.0f, 0.0f);
                        break;
                }
                animation.setDuration(1000L);
                animation.setAnimationListener(MMAdView.this);
                animation.setFillEnabled(true);
                animation.setFillBefore(true);
                animation.setFillAfter(true);
                final Animation animFinal = animation;
                MMSDK.runOnUiThread(new Runnable() { // from class: com.millennialmedia.android.MMAdView.MMAdViewMMAdImpl.1
                    @Override // java.lang.Runnable
                    public void run() {
                        MMAdView.this.refreshAnimationimageView.startAnimation(animFinal);
                    }
                });
            }
        }
    }

    @Override // android.view.animation.Animation.AnimationListener
    @Deprecated
    public void onAnimationStart(Animation animation) {
    }

    @Override // android.view.animation.Animation.AnimationListener
    @Deprecated
    public void onAnimationEnd(Animation animation) {
        this.refreshAnimationimageView.setVisibility(8);
    }

    @Override // android.view.animation.Animation.AnimationListener
    @Deprecated
    public void onAnimationRepeat(Animation animation) {
    }

    public void getAd() {
        if (this.adImpl != null && this.adImpl.requestListener != null) {
            getAd(this.adImpl.requestListener);
        } else {
            getAdInternal();
        }
    }

    public void getAd(RequestListener listener) {
        if (this.adImpl != null) {
            this.adImpl.requestListener = listener;
        }
        getAdInternal();
    }

    private void getAdInternal() {
        if (this.adImpl != null) {
            this.adImpl.requestAd();
        }
    }

    @Override // com.millennialmedia.android.MMLayout, android.view.View
    public void onWindowFocusChanged(boolean windowInFocus) {
        super.onWindowFocusChanged(windowInFocus);
        if (windowInFocus && this.adImpl != null && this.adImpl.controller != null) {
            if (this.adImpl.controller.webView == null) {
                this.adImpl.controller.webView = MMAdImplController.getWebViewFromExistingAdImpl(this.adImpl);
            }
            MMWebView webView = this.adImpl.controller.webView;
            if (webView != null && !webView.isCurrentParent(this.adImpl.internalId) && !webView.mraidState.equals("expanded")) {
                webView.removeFromParent();
                addView(webView);
            }
        }
    }

    class ResizeView extends View {
        public ResizeView(Context context) {
            super(context);
        }

        @Override // android.view.View
        protected Parcelable onSaveInstanceState() {
            MMLog.d(MMAdView.TAG, "onSaveInstanceState");
            attachToContext(MMAdView.this);
            return super.onSaveInstanceState();
        }

        @Override // android.view.View
        protected void onRestoreInstanceState(Parcelable state) {
            MMLog.d(MMAdView.TAG, "onRestoreInstanceState");
            MMAdView.this.attachToWindow(MMAdView.this);
            super.onRestoreInstanceState(state);
        }

        synchronized void attachToContext(View view) {
            MMAdView.this.detachFromParent(view);
            if (getParent() != null && (getParent() instanceof ViewGroup)) {
                ((ViewGroup) getParent()).addView(view);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public synchronized void detachFromParent(View view) {
        if (view != null) {
            ViewParent parent = getParent();
            if (parent != null && (parent instanceof ViewGroup)) {
                ViewGroup group = (ViewGroup) parent;
                if (view.getParent() != null) {
                    group.removeView(view);
                }
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public synchronized void attachToWindow(View view) {
        View decorView;
        detachFromParent(view);
        Context context = getContext();
        if (context != null && (context instanceof Activity)) {
            Activity activity = (Activity) context;
            Window win = activity.getWindow();
            if (win != null && (decorView = win.getDecorView()) != null && (decorView instanceof ViewGroup)) {
                ViewGroup group = (ViewGroup) decorView;
                group.addView(view);
            }
        }
    }

    synchronized void handleMraidResize(DTOResizeParameters resizeParams) {
        ViewParent adViewParent;
        this.refreshAnimationimageView.setImageBitmap(null);
        if (MMSDK.hasSetTranslationMethod()) {
            if (this.view == null) {
                this.view = new ResizeView(getContext());
                this.view.setId(304025022);
                this.view.setLayoutParams(new RelativeLayout.LayoutParams(1, 1));
                this.view.setBackgroundColor(0);
            }
            ViewParent parent = this.view.getParent();
            if (parent == null && (adViewParent = getParent()) != null && (adViewParent instanceof ViewGroup)) {
                ((ViewGroup) adViewParent).addView(this.view);
            }
            BannerBounds bounds = new BannerBounds(resizeParams);
            if (!resizeParams.allowOffScreen) {
                bounds.calculateOnScreenBounds();
            }
            int[] location = new int[2];
            getLocationInWindow(location);
            attachToWindow(this);
            int[] locAfterAttach = new int[2];
            getLocationInWindow(locAfterAttach);
            setUnresizeParameters();
            int xOld = location[0] - locAfterAttach[0];
            int yOld = location[1] - locAfterAttach[1];
            ViewGroup.LayoutParams layoutParams = getLayoutParams();
            bounds.modifyLayoutParams(layoutParams);
            callSetTranslationX(bounds.translationX + xOld);
            callSetTranslationY(bounds.translationY + yOld);
            setCloseArea(resizeParams.customClosePosition);
        }
    }

    synchronized void handleUnresize() {
        if (MMSDK.hasSetTranslationMethod()) {
            removeCloseTouchDelegate();
            if (!hasDefaultResizeParams()) {
                ViewGroup.LayoutParams params = getLayoutParams();
                params.width = this.oldWidth;
                params.height = this.oldHeight;
                callSetTranslationX(0);
                callSetTranslationY(0);
                this.oldWidth = DEFAULT_RESIZE_PARAM_VALUES;
                this.oldHeight = DEFAULT_RESIZE_PARAM_VALUES;
            }
            if (this.view != null) {
                this.isResizing = true;
                this.view.attachToContext(this);
                ViewParent parent = getParent();
                if (parent != null && (parent instanceof ViewGroup)) {
                    ViewGroup group = (ViewGroup) parent;
                    if (this.view.getParent() != null) {
                        group.removeView(this.view);
                    }
                }
                this.isResizing = false;
            }
        }
    }

    private void callSetTranslationX(int translationX) {
        try {
            Method method = View.class.getMethod("setTranslationX", Float.TYPE);
            method.invoke(this, Integer.valueOf(translationX));
        } catch (Exception e) {
            MMLog.e(TAG, "Unable to call setTranslationX", e);
        }
    }

    private void callSetTranslationY(int translationY) {
        try {
            Method method = View.class.getMethod("setTranslationY", Float.TYPE);
            method.invoke(this, Integer.valueOf(translationY));
        } catch (Exception e) {
            MMLog.e(TAG, "Unable to call setTranslationY", e);
        }
    }

    private void setUnresizeParameters() {
        if (hasDefaultResizeParams()) {
            ViewGroup.LayoutParams oldParams = getLayoutParams();
            this.oldWidth = oldParams.width;
            this.oldHeight = oldParams.height;
            if (this.oldWidth <= 0) {
                this.oldWidth = getWidth();
            }
            if (this.oldHeight <= 0) {
                this.oldHeight = getHeight();
            }
        }
    }

    private boolean hasDefaultResizeParams() {
        return this.oldWidth == DEFAULT_RESIZE_PARAM_VALUES && this.oldHeight == DEFAULT_RESIZE_PARAM_VALUES;
    }

    class BannerBounds {
        DTOResizeParameters resizeParams;
        int translationX;
        int translationY;

        BannerBounds(DTOResizeParameters resizeParams) {
            this.resizeParams = resizeParams;
            this.translationX = resizeParams.offsetX;
            this.translationY = resizeParams.offsetY;
        }

        void calculateOnScreenBounds() {
            calculateXBounds();
            calculateYBounds();
        }

        private void calculateXBounds() {
            int[] loc = new int[2];
            MMAdView.this.getLocationInWindow(loc);
            int xLoc = loc[0];
            BoundsResult resultX = calculateBounds(xLoc, this.resizeParams.offsetX, this.resizeParams.width, this.resizeParams.xMax);
            this.resizeParams.width = resultX.size;
            this.translationX = resultX.translation;
        }

        private void calculateYBounds() {
            int[] loc = new int[2];
            MMAdView.this.getLocationInWindow(loc);
            int yLoc = loc[1];
            BoundsResult resultY = calculateBounds(yLoc, this.resizeParams.offsetY, this.resizeParams.height, this.resizeParams.yMax);
            this.resizeParams.height = resultY.size;
            this.translationY = resultY.translation;
        }

        private BoundsResult calculateBounds(int screenPos, int offset, int size, int max) {
            int newStart = screenPos;
            int newSize = size;
            if (screenPos + size + offset > max) {
                newStart = (max - size) + offset;
                if (newStart < 0) {
                    newStart = 0;
                    newSize = max;
                } else if (newStart + size > max) {
                    newStart = max - size;
                }
            } else if (offset > 0) {
                newStart = offset;
            }
            BoundsResult result = new BoundsResult();
            result.translation = newStart - screenPos;
            result.size = newSize;
            return result;
        }

        private class BoundsResult {
            int size;
            int translation;

            private BoundsResult() {
            }
        }

        ViewGroup.LayoutParams modifyLayoutParams(ViewGroup.LayoutParams oldParams) {
            oldParams.width = this.resizeParams.width;
            oldParams.height = this.resizeParams.height;
            return oldParams;
        }
    }
}
