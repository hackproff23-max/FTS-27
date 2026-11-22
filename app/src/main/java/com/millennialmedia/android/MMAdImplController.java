package com.millennialmedia.android;

import android.content.Context;
import android.os.Build;
import android.text.TextUtils;
import android.widget.RelativeLayout;
import com.facebook.internal.ServerProtocol;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.lang.ref.WeakReference;
import java.net.URLEncoder;
import java.util.Comparator;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.concurrent.ConcurrentHashMap;
import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;

/* JADX INFO: loaded from: classes.dex */
class MMAdImplController implements AdCache.AdCacheTaskListener {
    static final long NO_ID_RETURNED = -4;
    private static final String TAG = "MMAdImplController";
    private static final Map<Long, MMAdImplController> saveableControllers = new ConcurrentHashMap();
    private static final Map<Long, WeakReference<MMAdImplController>> weakUnsaveableAdRef = new ConcurrentHashMap();
    volatile WeakReference<MMAdImpl> adImplRef;
    volatile long linkedAdImplId;
    RequestAdRunnable requestAdRunnable;
    volatile MMWebView webView;

    private MMAdImplController(MMAdImpl adImpl) {
        MMLog.d(TAG, "**************** creating new controller.");
        this.adImplRef = new WeakReference<>(adImpl);
        if (adImpl.linkForExpansionId != 0) {
            linkForExpansion(adImpl);
            this.webView = getWebViewFromExistingAdImpl(adImpl);
        } else if (!(adImpl instanceof MMInterstitial.MMInterstitialAdImpl)) {
            if (adImpl.isBanner()) {
                this.webView = new MMWebView(adImpl.getContext().getApplicationContext(), adImpl.internalId);
                this.webView.requiresPreAdSizeFix = true;
            } else {
                this.webView = new MMWebView(adImpl.getContext(), adImpl.internalId);
            }
        }
    }

    void linkForExpansion(MMAdImpl expansionAdImpl) {
        MMAdImpl adImpl = getAdImplWithId(expansionAdImpl.linkForExpansionId);
        if (adImpl != null) {
            this.linkedAdImplId = expansionAdImpl.linkForExpansionId;
            adImpl.controller.linkedAdImplId = expansionAdImpl.internalId;
            adImpl.linkForExpansionId = expansionAdImpl.internalId;
        }
    }

    static synchronized void assignAdViewController(MMAdImpl adImpl) {
        if (adImpl.controller != null) {
            if (!saveableControllers.containsValue(adImpl.controller)) {
                if (adImpl.isLifecycleObservable()) {
                    saveableControllers.put(Long.valueOf(adImpl.internalId), adImpl.controller);
                    if (weakUnsaveableAdRef.containsKey(Long.valueOf(adImpl.internalId))) {
                        weakUnsaveableAdRef.remove(Long.valueOf(adImpl.internalId));
                    }
                } else if (!weakUnsaveableAdRef.containsKey(Long.valueOf(adImpl.internalId))) {
                    weakUnsaveableAdRef.put(Long.valueOf(adImpl.internalId), new WeakReference<>(adImpl.controller));
                }
            }
            MMLog.d(TAG, adImpl + " - Has a controller");
        } else {
            MMLog.d(TAG, "*****************************************assignAdViewController for " + adImpl);
            MMAdImplController controller = saveableControllers.get(Long.valueOf(adImpl.internalId));
            if (controller == null) {
                WeakReference<MMAdImplController> controllerRef = weakUnsaveableAdRef.get(Long.valueOf(adImpl.internalId));
                if (controllerRef != null) {
                    controller = controllerRef.get();
                }
                if (controller == null) {
                    controller = new MMAdImplController(adImpl);
                    if (adImpl.isLifecycleObservable()) {
                        saveableControllers.put(Long.valueOf(adImpl.internalId), controller);
                    } else {
                        weakUnsaveableAdRef.put(Long.valueOf(adImpl.internalId), new WeakReference<>(controller));
                    }
                }
            }
            adImpl.controller = controller;
            controller.adImplRef = new WeakReference<>(adImpl);
            if (controller.webView != null && !(adImpl instanceof MMInterstitial.MMInterstitialAdImpl)) {
                setupWebView(adImpl);
            }
        }
    }

    private static synchronized void setupWebView(MMAdImpl adImpl) {
        RelativeLayout.LayoutParams layoutParams;
        MMAdImplController controller = adImpl.controller;
        if (controller.webView != null) {
            controller.webView.setWebViewClient(adImpl.getMMWebViewClient());
            if (!controller.webView.isCurrentParent(adImpl.internalId)) {
                if (adImpl.isBanner()) {
                    layoutParams = new RelativeLayout.LayoutParams(-2, -2);
                    if (controller.webView.isMraidResized()) {
                        controller.webView.unresizeToDefault(adImpl);
                    }
                } else {
                    layoutParams = new RelativeLayout.LayoutParams(-2, -1);
                }
                controller.webView.removeFromParent();
                adImpl.addView(controller.webView, layoutParams);
            }
        }
    }

    static synchronized boolean attachWebViewFromOverlay(MMAdImpl overlayAdImpl) {
        boolean z = false;
        synchronized (MMAdImplController.class) {
            if (overlayAdImpl != null) {
                MMLog.d(TAG, "attachWebViewFromOverlay with " + overlayAdImpl);
                if (overlayAdImpl.controller != null && overlayAdImpl.controller.webView != null) {
                    overlayAdImpl.controller.webView.resetSpeechKit();
                }
                MMAdImpl bannerAdImpl = getAdImplWithId(overlayAdImpl.linkForExpansionId);
                if (bannerAdImpl != null && bannerAdImpl.controller != null) {
                    if (bannerAdImpl.controller.webView == null) {
                        if (overlayAdImpl.controller != null && overlayAdImpl.controller.webView != null) {
                            bannerAdImpl.controller.webView = overlayAdImpl.controller.webView;
                            overlayAdImpl.removeView(overlayAdImpl.controller.webView);
                            overlayAdImpl.controller.webView = null;
                            bannerAdImpl.controller.webView.setMraidDefault();
                            bannerAdImpl.controller.webView.setWebViewClient(bannerAdImpl.getMMWebViewClient());
                            z = true;
                        }
                    } else {
                        bannerAdImpl.controller.webView.setMraidDefault();
                        bannerAdImpl.controller.webView.setWebViewClient(bannerAdImpl.getMMWebViewClient());
                        z = true;
                    }
                }
            }
        }
        return z;
    }

    static synchronized MMWebView getWebViewFromExistingAdImpl(MMAdImpl requestorAdImpl) {
        MMWebView mmWebView;
        MMLog.i(TAG, "getWebViewFromExistingLayout(" + requestorAdImpl.internalId + " taking from " + requestorAdImpl.linkForExpansionId + ")");
        mmWebView = null;
        MMAdImpl holderAdImpl = getAdImplWithId(requestorAdImpl.linkForExpansionId);
        if (holderAdImpl != null && holderAdImpl.controller != null) {
            mmWebView = holderAdImpl.controller.webView;
            holderAdImpl.controller.webView = null;
        }
        return mmWebView;
    }

    static synchronized MMAdImpl getAdImplWithId(long internalId) {
        WeakReference<MMAdImplController> controllerRef;
        MMAdImpl mMAdImpl = null;
        synchronized (MMAdImplController.class) {
            if (internalId != NO_ID_RETURNED) {
                MMAdImplController controller = saveableControllers.get(Long.valueOf(internalId));
                if (controller == null && (controllerRef = weakUnsaveableAdRef.get(Long.valueOf(internalId))) != null) {
                    controller = controllerRef.get();
                }
                if (controller != null) {
                    mMAdImpl = controller.adImplRef.get();
                }
            }
        }
        return mMAdImpl;
    }

    static synchronized void removeAdViewController(MMAdImpl adImpl) {
        if (adImpl.controller != null) {
            if (adImpl.isLifecycleObservable()) {
                saveableControllers.put(Long.valueOf(adImpl.internalId), adImpl.controller);
                if (weakUnsaveableAdRef.get(Long.valueOf(adImpl.internalId)) != null) {
                    weakUnsaveableAdRef.remove(Long.valueOf(adImpl.internalId));
                }
            } else {
                weakUnsaveableAdRef.put(Long.valueOf(adImpl.internalId), new WeakReference<>(adImpl.controller));
            }
            MMLog.d(TAG, "****************RemoveAdviewcontroller - " + adImpl);
            if (adImpl.isFinishing) {
                saveableControllers.remove(Long.valueOf(adImpl.internalId));
                weakUnsaveableAdRef.remove(Long.valueOf(adImpl.internalId));
            }
            MMAdImplController controller = adImpl.controller;
            adImpl.controller = null;
            MMLog.d(TAG, "****************RemoveAdviewcontroller - controllers " + controllersToString());
            if (controller.webView != null) {
                MMLog.d(TAG, "****************RemoveAdviewcontroller - controller!=null, expanding=" + controller.webView.isExpanding);
                adImpl.removeView(controller.webView);
                controller.webView.isExpanding = false;
                if (adImpl.isFinishing && adImpl.linkForExpansionId == 0) {
                    controller.webView.loadData("<html></html>", "text/html", "UTF-8");
                    controller.webView.resetSpeechKit();
                    controller.webView = null;
                }
            }
        }
    }

    void requestAd() {
        MMAdImpl adImpl = this.adImplRef.get();
        if (adImpl == null) {
            MMLog.e(TAG, MMException.getErrorCodeMessage(25));
            MMSDK.Event.requestFailed(adImpl, new MMException(25));
            return;
        }
        if (!adImpl.isRefreshable()) {
            MMSDK.Event.requestFailed(adImpl, new MMException(16));
            return;
        }
        if (!MMSDK.isUiThread()) {
            MMLog.e(TAG, MMException.getErrorCodeMessage(3));
            MMSDK.Event.requestFailed(adImpl, new MMException(3));
        } else {
            if (HandShake.sharedHandShake(adImpl.getContext()).kill) {
                MMLog.i(TAG, "The server is no longer allowing ads.");
                MMSDK.Event.requestFailed(adImpl, new MMException(16));
                return;
            }
            try {
                MMLog.d(TAG, "adLayout - requestAd");
                requestAdInternal(adImpl);
            } catch (Exception e) {
                MMLog.e(TAG, "There was an exception with the ad request. ", e);
                e.printStackTrace();
            }
        }
    }

    private void requestAdInternal(MMAdImpl adImpl) {
        if (adImpl.apid == null) {
            MMException error = new MMException("MMAdView found with a null apid. New ad requests on this MMAdView are disabled until an apid has been assigned.", 1);
            MMLog.e(TAG, error.getMessage());
            MMSDK.Event.requestFailed(adImpl, error);
        } else if (adImpl.isBanner() || !isDownloadingCachedAd(adImpl)) {
            synchronized (this) {
                if (this.requestAdRunnable != null) {
                    MMLog.i(TAG, MMException.getErrorCodeMessage(12));
                    MMSDK.Event.requestFailed(adImpl, new MMException(12));
                } else {
                    this.requestAdRunnable = new RequestAdRunnable();
                    Utils.ThreadUtils.execute(this.requestAdRunnable);
                }
            }
        }
    }

    private synchronized boolean isDownloadingCachedAd(MMAdImpl adImpl) {
        boolean z = true;
        synchronized (this) {
            Context context = adImpl.getContext();
            if (HandShake.sharedHandShake(context).isAdTypeDownloading(adImpl.adType)) {
                MMLog.i(TAG, "There is a download in progress. Defering call for new ad");
                MMSDK.Event.requestFailed(adImpl, new MMException(12));
            } else {
                MMLog.d(TAG, "No download in progress.");
                CachedAd incompleteAd = AdCache.loadIncompleteDownload(context, adImpl.getCachedName());
                if (incompleteAd != null) {
                    MMLog.i(TAG, "Last ad wasn't fully downloaded. Download again.");
                    MMSDK.Event.fetchStartedCaching(adImpl);
                    AdCache.startDownloadTask(context, adImpl.getCachedName(), incompleteAd, this);
                } else {
                    MMLog.i(TAG, "No incomplete downloads.");
                    z = false;
                }
            }
        }
        return z;
    }

    void loadUrl(String url) {
        if (!TextUtils.isEmpty(url) && this.webView != null) {
            this.webView.loadUrl(url);
        }
    }

    @Override // com.millennialmedia.android.AdCache.AdCacheTaskListener
    public void downloadCompleted(CachedAd ad, boolean success) {
        MMAdImpl adImpl = this.adImplRef.get();
        if (adImpl == null) {
            MMLog.e(TAG, MMException.getErrorCodeMessage(25));
            return;
        }
        if (success) {
            AdCache.setNextCachedAd(adImpl.getContext(), adImpl.getCachedName(), ad.getId());
        }
        if (success) {
            MMSDK.Event.requestCompleted(adImpl);
        } else {
            MMSDK.Event.requestFailed(adImpl, new MMException(15));
        }
    }

    int checkReason(MMAdImpl adImpl, CachedAd ad) {
        if (ad.isExpired()) {
            MMLog.d(TAG, String.format("%s is expired.", ad.getId()));
            return 21;
        }
        if (!ad.isOnDisk(adImpl.getContext())) {
            MMLog.d(TAG, String.format("%s is not on disk.", ad.getId()));
            return 22;
        }
        if (!HandShake.sharedHandShake(adImpl.getContext()).canDisplayCachedAd(adImpl.adType, ad.deferredViewStart)) {
            MMLog.d(TAG, String.format("%s cannot be shown at this time.", ad.getId()));
            return 24;
        }
        return 100;
    }

    int isAdAvailable(MMAdImpl adImpl) {
        CachedAd ad = AdCache.loadNextCachedAd(adImpl.getContext(), adImpl.getCachedName());
        if (ad != null) {
            if (ad.canShow(adImpl.getContext(), adImpl, true)) {
                return 0;
            }
            return checkReason(adImpl, ad);
        }
        MMLog.i(TAG, "No next ad.");
        return 20;
    }

    int display(MMAdImpl adImpl) {
        CachedAd ad = AdCache.loadNextCachedAd(adImpl.getContext(), adImpl.getCachedName());
        if (ad != null) {
            if (ad.canShow(adImpl.getContext(), adImpl, true)) {
                MMSDK.Event.displayStarted(adImpl);
                AdCache.setNextCachedAd(adImpl.getContext(), adImpl.getCachedName(), null);
                ad.show(adImpl.getContext(), adImpl.internalId);
                HandShake.sharedHandShake(adImpl.getContext()).updateLastVideoViewedTime(adImpl.getContext(), adImpl.adType);
                return 0;
            }
            return checkReason(adImpl, ad);
        }
        return 20;
    }

    private class RequestAdRunnable implements Runnable {
        String adUrl;
        HttpMMHeaders mmHeaders;

        private RequestAdRunnable() {
        }

        boolean failWithErrorMessage(MMException mmError) {
            MMLog.e(MMAdImplController.TAG, mmError.getMessage());
            return fail(mmError);
        }

        boolean failWithInfoMessage(MMException mmError) {
            MMLog.i(MMAdImplController.TAG, mmError.getMessage());
            return fail(mmError);
        }

        boolean fail(MMException mmError) {
            MMAdImpl adImpl = null;
            if (MMAdImplController.this.adImplRef != null) {
                MMAdImpl adImpl2 = MMAdImplController.this.adImplRef.get();
                adImpl = adImpl2;
            }
            MMSDK.Event.requestFailed(adImpl, mmError);
            return false;
        }

        /* JADX WARN: Code duplicated, block: B:25:0x0089  */
        /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:28:0x009b -> B:32:0x0027). Please report as a decompilation issue!!! */
        @Override // java.lang.Runnable
        public void run() {
            try {
                try {
                    if (MMAdImplController.this.adImplRef == null) {
                        MMAdImplController.this.requestAdRunnable = null;
                    } else {
                        MMAdImpl adImpl = MMAdImplController.this.adImplRef.get();
                        if (adImpl == null || !MMSDK.isConnected(adImpl.getContext())) {
                            failWithInfoMessage(new MMException("No network available, can't call for ads.", 11));
                            MMAdImplController.this.requestAdRunnable = null;
                        } else if (isAdUrlBuildable()) {
                            try {
                                HttpResponse httpResponse = new HttpGetRequest().get(this.adUrl);
                                if (httpResponse == null) {
                                    failWithErrorMessage(new MMException("HTTP response is null.", 14));
                                    MMAdImplController.this.requestAdRunnable = null;
                                } else if (isHandledResponse(httpResponse)) {
                                    MMAdImplController.this.requestAdRunnable = null;
                                } else {
                                    MMAdImplController.this.requestAdRunnable = null;
                                }
                            } catch (Exception e) {
                                failWithErrorMessage(new MMException("Ad request HTTP error. " + e.getMessage(), 14));
                                MMAdImplController.this.requestAdRunnable = null;
                            }
                        } else {
                            MMAdImplController.this.requestAdRunnable = null;
                        }
                    }
                } catch (Exception e2) {
                    failWithInfoMessage(new MMException("Request not filled, can't call for ads.", 14));
                    MMAdImplController.this.requestAdRunnable = null;
                }
            } catch (Throwable th) {
                MMAdImplController.this.requestAdRunnable = null;
                throw th;
            }
        }

        private boolean isAdUrlBuildable() {
            this.adUrl = null;
            MMAdImpl adImpl = null;
            if (MMAdImplController.this.adImplRef != null) {
                MMAdImpl adImpl2 = MMAdImplController.this.adImplRef.get();
                adImpl = adImpl2;
            }
            try {
                if (adImpl != null) {
                    Map<String, String> paramsMap = new TreeMap<>((Comparator<? super String>) String.CASE_INSENSITIVE_ORDER);
                    adImpl.insertUrlAdMetaValues(paramsMap);
                    MMSDK.insertUrlCommonValues(adImpl.getContext(), paramsMap);
                    paramsMap.put("ua", adImpl.controller.getUserAgent());
                    StringBuilder adUrlBuilder = new StringBuilder(HandShake.getAdUrl());
                    Set<Map.Entry<String, String>> e = paramsMap.entrySet();
                    MMLog.d(MMAdImplController.TAG, e.toString());
                    for (Map.Entry<String, String> entry : paramsMap.entrySet()) {
                        adUrlBuilder.append(String.format("%s=%s&", entry.getKey(), URLEncoder.encode(entry.getValue(), "UTF-8")));
                    }
                    adUrlBuilder.delete(adUrlBuilder.length() - 1, adUrlBuilder.length());
                    this.adUrl = adUrlBuilder.toString();
                    MMLog.d(MMAdImplController.TAG, String.format("Calling for an advertisement: %s", this.adUrl));
                } else {
                    failWithInfoMessage(new MMException(25));
                }
                return true;
            } catch (UnsupportedEncodingException e2) {
                return failWithErrorMessage(new MMException(e2));
            }
        }

        private boolean isHandledResponse(HttpResponse httpResponse) {
            HttpEntity httpEntity = httpResponse.getEntity();
            if (httpEntity == null) {
                failWithInfoMessage(new MMException("Null HTTP entity", 14));
                return false;
            }
            if (httpEntity.getContentLength() == 0) {
                failWithInfoMessage(new MMException("Millennial ad return failed. Zero content length returned.", 14));
                return false;
            }
            saveMacId(httpResponse);
            Header httpHeader = httpEntity.getContentType();
            if (httpHeader != null && httpHeader.getValue() != null) {
                if (httpHeader.getValue().toLowerCase().startsWith("application/json")) {
                    isHandledJsonResponse(httpEntity);
                } else if (httpHeader.getValue().toLowerCase().startsWith("text/html")) {
                    Header xHeader = httpResponse.getFirstHeader("X-MM-Video");
                    this.mmHeaders = new HttpMMHeaders(httpResponse.getAllHeaders());
                    if (xHeader != null && xHeader.getValue().equalsIgnoreCase(ServerProtocol.DIALOG_RETURN_SCOPES_TRUE)) {
                        MMAdImpl adImpl = null;
                        if (MMAdImplController.this.adImplRef != null) {
                            MMAdImpl adImpl2 = MMAdImplController.this.adImplRef.get();
                            adImpl = adImpl2;
                        }
                        if (adImpl != null) {
                            Context context = adImpl.getContext();
                            HandShake.sharedHandShake(context).updateLastVideoViewedTime(context, adImpl.adType);
                        }
                    }
                    isHandledHtmlResponse(httpEntity);
                } else {
                    failWithInfoMessage(new MMException("Millennial ad return failed. Invalid (JSON/HTML expected) mime type returned.", 15));
                    return false;
                }
                return true;
            }
            failWithInfoMessage(new MMException("Millennial ad return failed. HTTP Header value null.", 15));
            return false;
        }

        private void saveMacId(HttpResponse httpResponse) {
            int endIndex;
            Header[] cookieHeaders = httpResponse.getHeaders("Set-Cookie");
            for (Header cookieHeader : cookieHeaders) {
                String value = cookieHeader.getValue();
                int index = value.indexOf("MAC-ID=");
                if (index >= 0 && (endIndex = value.indexOf(59, index)) > index) {
                    MMSDK.macId = value.substring(index + 7, endIndex);
                }
            }
        }

        private boolean isHandledJsonResponse(HttpEntity httpEntity) {
            MMAdImpl adImpl = null;
            if (MMAdImplController.this.adImplRef != null) {
                MMAdImpl adImpl2 = MMAdImplController.this.adImplRef.get();
                adImpl = adImpl2;
            }
            if (adImpl != null) {
                if (adImpl.isBanner()) {
                    return failWithErrorMessage(new MMException("Millennial ad return unsupported format.", 15));
                }
                try {
                    String json = HttpGetRequest.convertStreamToString(httpEntity.getContent());
                    VideoAd videoAd = (VideoAd) CachedAd.parseJSON(json);
                    if (videoAd != null && videoAd.isValid()) {
                        MMLog.i(MMAdImplController.TAG, "Cached video ad JSON received: " + videoAd.getId());
                        if (videoAd.isExpired()) {
                            MMLog.i(MMAdImplController.TAG, "New ad has expiration date in the past. Not downloading ad content.");
                            videoAd.delete(adImpl.getContext());
                            MMSDK.Event.requestFailed(adImpl, new MMException(15));
                        } else if (AdCache.loadNextCachedAd(adImpl.getContext(), adImpl.getCachedName()) != null) {
                            MMLog.i(MMAdImplController.TAG, "Previously fetched ad exists in the playback queue. Not downloading ad content.");
                            videoAd.delete(adImpl.getContext());
                            MMSDK.Event.requestFailed(adImpl, new MMException(17));
                        } else {
                            AdCache.save(adImpl.getContext(), videoAd);
                            if (!videoAd.isOnDisk(adImpl.getContext())) {
                                MMSDK.Event.logEvent(videoAd.cacheMissURL);
                                MMLog.d(MMAdImplController.TAG, "Downloading ad...");
                                MMSDK.Event.fetchStartedCaching(adImpl);
                                videoAd.downloadPriority = 3;
                                AdCache.startDownloadTask(adImpl.getContext(), adImpl.getCachedName(), videoAd, adImpl.controller);
                            } else {
                                MMLog.d(MMAdImplController.TAG, "Cached ad is valid. Moving it to the front of the queue.");
                                AdCache.setNextCachedAd(adImpl.getContext(), adImpl.getCachedName(), videoAd.getId());
                                MMSDK.Event.fetchStartedCaching(adImpl);
                                MMSDK.Event.requestCompleted(adImpl);
                            }
                        }
                    }
                } catch (IOException ioe) {
                    ioe.printStackTrace();
                    return failWithInfoMessage(new MMException("Millennial ad return failed. " + ioe, ioe));
                } catch (IllegalStateException illegalE) {
                    illegalE.printStackTrace();
                    return failWithInfoMessage(new MMException("Millennial ad return failed. Invalid response data.", illegalE));
                }
            }
            return true;
        }

        private boolean isHandledHtmlResponse(HttpEntity httpEntity) {
            MMAdImpl adImpl = null;
            try {
                if (MMAdImplController.this.adImplRef != null) {
                    adImpl = MMAdImplController.this.adImplRef.get();
                }
                if (adImpl != null) {
                    if (!adImpl.isBanner()) {
                        InterstitialAd interstitialAd = new InterstitialAd();
                        interstitialAd.content = HttpGetRequest.convertStreamToString(httpEntity.getContent());
                        interstitialAd.setId(adImpl.adType);
                        interstitialAd.adUrl = this.adUrl;
                        interstitialAd.mmHeaders = this.mmHeaders;
                        if (MMSDK.logLevel >= 5) {
                            MMLog.v(MMAdImplController.TAG, String.format("Received interstitial ad with url %s.", interstitialAd.adUrl));
                            MMLog.v(MMAdImplController.TAG, interstitialAd.content);
                        }
                        AdCache.save(adImpl.getContext(), interstitialAd);
                        AdCache.setNextCachedAd(adImpl.getContext(), adImpl.getCachedName(), interstitialAd.getId());
                        MMSDK.Event.fetchStartedCaching(adImpl);
                        MMSDK.Event.requestCompleted(adImpl);
                    } else {
                        if (adImpl.controller != null) {
                            adImpl.controller.setLastHeaders(this.mmHeaders);
                            adImpl.controller.setWebViewContent(HttpGetRequest.convertStreamToString(httpEntity.getContent()), this.adUrl);
                        }
                        MMSDK.Event.requestCompleted(adImpl);
                    }
                }
                return true;
            } catch (IOException ioe) {
                return failWithErrorMessage(new MMException("Exception raised in HTTP stream: " + ioe, ioe));
            }
        }
    }

    void setWebViewContent(String webContent, String url) {
        if (this.webView != null) {
            this.webView.setWebViewContent(webContent, url, this.adImplRef.get());
        }
    }

    void setLastHeaders(HttpMMHeaders lastHeaders) {
        if (this.webView != null) {
            this.webView.setLastHeaders(lastHeaders);
        }
    }

    HttpMMHeaders getLastHeaders() {
        if (this.webView == null) {
            return null;
        }
        return this.webView.getLastHeaders();
    }

    public String getDefaultUserAgentString(Context context) {
        return System.getProperty("http.agent");
    }

    String getUserAgent() {
        Context context;
        String userAgent = null;
        MMAdImpl adImpl = this.adImplRef.get();
        if (adImpl != null && (context = adImpl.getContext()) != null) {
            userAgent = getDefaultUserAgentString(context);
        }
        if (TextUtils.isEmpty(userAgent)) {
            return Build.MODEL;
        }
        return userAgent;
    }

    static String controllersToString() {
        return weakUnsaveableAdRef.toString() + " SAVED:" + saveableControllers.toString();
    }

    public String toString() {
        MMAdImpl adImpl = this.adImplRef.get();
        StringBuilder sb = new StringBuilder();
        if (adImpl != null) {
            sb.append(adImpl + "-LinkInC=" + this.linkedAdImplId);
        }
        return sb.toString() + " w/" + this.webView;
    }

    @Override // com.millennialmedia.android.AdCache.AdCacheTaskListener
    public void downloadStart(CachedAd ad) {
    }

    void unresizeToDefault() {
        if (this.webView != null) {
            this.webView.unresizeToDefault(this.adImplRef.get());
        }
    }

    void loadWebContent(String content, String adUrl) {
        MMAdImpl adImpl = this.adImplRef.get();
        if (adImpl != null && this.webView != null) {
            this.webView.setWebViewContent(content, adUrl, adImpl);
        }
    }

    static void destroyOtherInlineVideo(Context context) {
        MMAdImpl adImpl;
        MMAd ad;
        for (Map.Entry<Long, MMAdImplController> entry : saveableControllers.entrySet()) {
            MMAdImplController controller = entry.getValue();
            if (controller != null && (adImpl = controller.adImplRef.get()) != null && (ad = adImpl.getCallingAd()) != null && (ad instanceof MMLayout)) {
                MMLayout layout = (MMLayout) ad;
                layout.removeVideo();
            }
        }
    }
}
