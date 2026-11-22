package com.millennialmedia.android;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.net.Uri;
import android.widget.RelativeLayout;
import com.facebook.internal.AnalyticsEvents;
import com.facebook.internal.ServerProtocol;
import com.mopub.common.Constants;
import java.lang.ref.WeakReference;
import java.util.Map;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes.dex */
abstract class MMAdImpl implements MMAd {
    static final String BANNER = "b";
    static final String INTERSTITIAL = "i";
    private static final String TAG = "MMAdImpl";
    private static long nextAdViewId = 1;
    String adType;
    WeakReference<Context> contextRef;
    MMAdImplController controller;
    long internalId;
    boolean isFinishing;
    long lastAdRequest;
    long linkForExpansionId;
    protected MMRequest mmRequest;
    MMWebViewClient mmWebViewClient;
    JSONObject obj;
    RequestListener requestListener;
    String userData;
    boolean ignoreDensityScaling = false;
    String apid = MMSDK.DEFAULT_APID;
    MMWebViewClient.MMWebViewClientListener mmWebViewClientListener = new BasicWebViewClientListener(this);

    abstract MMAd getCallingAd();

    static class BasicWebViewClientListener extends MMWebViewClient.MMWebViewClientListener {
        WeakReference<MMAdImpl> adImplRef;

        BasicWebViewClientListener(MMAdImpl adImpl) {
            this.adImplRef = new WeakReference<>(adImpl);
        }

        @Override // com.millennialmedia.android.MMWebViewClient.MMWebViewClientListener
        void onPageStarted(String url) {
            MMAdImpl adImpl = this.adImplRef.get();
            if (adImpl != null) {
                adImpl.setClickable(false);
            }
        }

        @Override // com.millennialmedia.android.MMWebViewClient.MMWebViewClientListener
        public void onPageFinished(String url) {
            MMAdImpl adImpl = this.adImplRef.get();
            if (adImpl != null) {
                adImpl.setClickable(true);
                if (adImpl.controller != null && adImpl.controller.webView != null) {
                    synchronized (adImpl.controller.webView) {
                        if (adImpl.controller.webView.hasWindowFocus()) {
                            adImpl.controller.webView.setMraidViewableVisible();
                        } else {
                            adImpl.controller.webView.setMraidViewableHidden();
                        }
                    }
                }
            }
        }
    }

    public MMAdImpl(Context context) {
        this.contextRef = new WeakReference<>(context);
        synchronized (MMAdImpl.class) {
            this.internalId = nextAdViewId;
            nextAdViewId++;
            MMLog.v(TAG, String.format("Assigning MMAdImpl internal id: %d", Long.valueOf(this.internalId)));
        }
    }

    @Override // com.millennialmedia.android.MMAd
    public void setApid(String apid) {
        if (apid != null && !apid.isEmpty()) {
            HandShake.apid = apid;
        }
        this.apid = apid;
    }

    @Override // com.millennialmedia.android.MMAd
    public String getApid() {
        return this.apid;
    }

    @Override // com.millennialmedia.android.MMAd
    public void setListener(RequestListener listener) {
        this.requestListener = listener;
    }

    @Override // com.millennialmedia.android.MMAd
    public RequestListener getListener() {
        return this.requestListener;
    }

    @Override // com.millennialmedia.android.MMAd
    public void setIgnoresDensityScaling(boolean ignoresDensityScaling) {
        this.ignoreDensityScaling = ignoresDensityScaling;
    }

    @Override // com.millennialmedia.android.MMAd
    public boolean getIgnoresDensityScaling() {
        return this.ignoreDensityScaling;
    }

    @Override // com.millennialmedia.android.MMAd
    public void setMMRequest(MMRequest request) {
        this.mmRequest = request;
    }

    @Override // com.millennialmedia.android.MMAd
    public MMRequest getMMRequest() {
        return this.mmRequest;
    }

    int getId() {
        return -1;
    }

    void requestAd() {
        MMAdImplController.assignAdViewController(this);
        if (this.controller != null) {
            this.controller.requestAd();
        }
    }

    boolean isRefreshable() {
        if (MMSDK.disableAdMinRefresh) {
            MMLog.d(TAG, "Minimum adrefresh time ignored");
            return true;
        }
        long currentTime = System.currentTimeMillis();
        int lastRequestSecs = (int) ((currentTime - this.lastAdRequest) / 1000);
        long adRefreshSecs = HandShake.sharedHandShake(getContext()).adRefreshSecs;
        if (lastRequestSecs >= adRefreshSecs) {
            this.lastAdRequest = currentTime;
            return true;
        }
        MMLog.d(TAG, String.format("Cannot request ad. Last ad request was %d seconds ago. Next ad can be requested in %d seconds.", Integer.valueOf(lastRequestSecs), Long.valueOf(adRefreshSecs - ((long) lastRequestSecs))));
        return false;
    }

    boolean isTransitionAnimated() {
        return false;
    }

    void prepareTransition(Bitmap bitmap) {
    }

    void animateTransition() {
    }

    Context getContext() {
        if (this.contextRef != null) {
            return this.contextRef.get();
        }
        return null;
    }

    String getCachedName() {
        if (this.adType == null || this.apid == null) {
            return null;
        }
        return this.adType + "_" + this.apid;
    }

    static String[] getAdTypes() {
        return new String[]{BANNER, INTERSTITIAL};
    }

    String getRequestFailedAction() {
        return MMBroadcastReceiver.ACTION_FETCH_FAILED;
    }

    String getRequestCompletedAction() {
        return MMBroadcastReceiver.ACTION_FETCH_SUCCEEDED;
    }

    MMWebViewClient getMMWebViewClient() {
        MMLog.d(TAG, "Returning a client for user: DefaultWebViewClient, adimpl=" + this);
        return new BannerWebViewClient(this.mmWebViewClientListener, new MMAdImplRedirectionListenerImpl(this));
    }

    String getReqType() {
        return "fetch";
    }

    public boolean hasCachedVideoSupport() {
        return true;
    }

    public boolean isBanner() {
        return false;
    }

    void insertUrlAdMetaValues(Map<String, String> paramsMap) {
        Context context = getContext();
        paramsMap.put("apid", this.apid);
        paramsMap.put("do", MMSDK.getOrientation(context));
        paramsMap.put("olock", MMSDK.getOrientationLocked(context));
        if (!hasCachedVideoSupport()) {
            paramsMap.put("cachedvideo", "false");
        }
        paramsMap.put("reqtype", getReqType());
        if (this.mmRequest != null) {
            this.mmRequest.getUrlParams(paramsMap);
        }
        if (HandShake.sharedHandShake(context).canRequestVideo(context, this.adType)) {
            paramsMap.put(AnalyticsEvents.PARAMETER_SHARE_DIALOG_CONTENT_VIDEO, ServerProtocol.DIALOG_RETURN_SCOPES_TRUE);
        } else {
            paramsMap.put(AnalyticsEvents.PARAMETER_SHARE_DIALOG_CONTENT_VIDEO, "false");
        }
        if (this.adType != null) {
            if (this.adType.equals(BANNER) || this.adType.equals(INTERSTITIAL)) {
                paramsMap.put("at", this.adType);
                return;
            } else {
                MMLog.e(TAG, "******* ERROR: INCORRECT AD TYPE IN MMADVIEW OBJECT PARAMETERS (" + this.adType + ") **********");
                return;
            }
        }
        MMLog.e(TAG, "******* SDK DEFAULTED TO MMBannerAdBottom. THIS MAY AFFECT THE ADS YOU RECIEVE!!! **********");
        paramsMap.put("at", BANNER);
    }

    void removeView(MMWebView mmWebView) {
    }

    void addView(MMWebView webView, RelativeLayout.LayoutParams webLayoutParams) {
    }

    void setClickable(boolean clickable) {
    }

    public String toString() {
        return "AdType[(" + this.adType + ") InternalId(" + this.internalId + ") LinkedId(" + this.linkForExpansionId + ") isFinishing(" + this.isFinishing + ")]";
    }

    boolean isUpdatingMraid() {
        return (this.controller == null || this.controller.webView == null || this.controller.webView.isExpanding) ? false : true;
    }

    static class MMAdImplRedirectionListenerImpl extends HttpRedirection.RedirectionListenerImpl {
        WeakReference<MMAdImpl> adImplRef;

        public MMAdImplRedirectionListenerImpl(MMAdImpl adImpl) {
            if (adImpl != null) {
                this.adImplRef = new WeakReference<>(adImpl);
                this.creatorAdImplInternalId = adImpl.internalId;
            }
        }

        @Override // com.millennialmedia.android.HttpRedirection.RedirectionListenerImpl, com.millennialmedia.android.HttpRedirection.Listener
        public void updateLastVideoViewedTime() {
            MMAdImpl adImpl = this.adImplRef.get();
            if (adImpl != null && adImpl.adType != null) {
                HandShake.sharedHandShake(adImpl.getContext()).updateLastVideoViewedTime(adImpl.getContext(), adImpl.adType);
            }
        }

        @Override // com.millennialmedia.android.HttpRedirection.RedirectionListenerImpl, com.millennialmedia.android.HttpRedirection.Listener
        public boolean isActivityStartable(Uri uri) {
            Context c;
            MMAdImpl adImpl = this.adImplRef.get();
            if (adImpl != null && (c = adImpl.getContext()) != null && (c instanceof Activity)) {
                Activity a = (Activity) c;
                if (a.isFinishing()) {
                    return false;
                }
            }
            return true;
        }

        @Override // com.millennialmedia.android.HttpRedirection.RedirectionListenerImpl, com.millennialmedia.android.HttpRedirection.Listener
        public void startingActivity(Uri destinationUri) {
            MMAdImpl adImpl;
            super.startingActivity(destinationUri);
            if ((destinationUri.getScheme().equalsIgnoreCase(Constants.HTTP) || destinationUri.getScheme().equalsIgnoreCase(Constants.HTTPS)) && (adImpl = this.adImplRef.get()) != null) {
                MMSDK.Event.overlayOpened(adImpl);
            }
        }
    }

    void unresizeToDefault() {
        if (this.controller != null) {
            this.controller.unresizeToDefault();
        }
    }

    boolean isLifecycleObservable() {
        return false;
    }

    void removeProgressBar() {
    }

    boolean isExpandingToUrl() {
        return false;
    }
}
