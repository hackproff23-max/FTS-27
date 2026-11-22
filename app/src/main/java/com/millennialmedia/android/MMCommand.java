package com.millennialmedia.android;

import android.net.Uri;
import android.text.TextUtils;
import java.lang.ref.WeakReference;
import java.util.HashMap;
import java.util.Map;

/* JADX INFO: loaded from: classes.dex */
class MMCommand implements Runnable {
    private static final String MM_BANNER = "MMBanner";
    private static final String MM_CACHED_VIDEO = "MMCachedVideo";
    private static final String MM_CALENDAR = "MMCalendar";
    private static final String MM_DEVICE = "MMDevice";
    private static final String MM_INLINE_VIDEO = "MMInlineVideo";
    private static final String MM_INTERSTITIAL = "MMInterstitial";
    private static final String MM_MEDIA = "MMMedia";
    private static final String MM_NOTIFICATION = "MMNotification";
    private static final String MM_SPEECHKIT = "MMSpeechkit";
    private static final String TAG = "MMCommand";
    private Map<String, String> arguments;
    private String bridgeService;
    private String callback;
    private String serviceMethod;
    private WeakReference<MMWebView> webViewRef;

    static {
        ComponentRegistry.addBannerBridge(new BridgeMMBanner());
        ComponentRegistry.addCachedVideoBridge(new BridgeMMCachedVideo());
        ComponentRegistry.addCalendarBridge(new BridgeMMCalendar());
        ComponentRegistry.addDeviceBridge(new BridgeMMDevice());
        ComponentRegistry.addInlineVideoBridge(new BridgeMMInlineVideo());
        ComponentRegistry.addInterstitialBridge(new BridgeMMInterstitial());
        ComponentRegistry.addMediaBridge(new BridgeMMMedia());
        ComponentRegistry.addNotificationBridge(new BridgeMMNotification());
        ComponentRegistry.addSpeechkitBridge(new BridgeMMSpeechkit());
    }

    boolean isResizeCommand() {
        if (this.serviceMethod != null) {
            return "resize".equals(this.serviceMethod);
        }
        return false;
    }

    MMCommand(MMWebView webView, String uriString) {
        this.webViewRef = new WeakReference<>(webView);
        try {
            Uri uri = Uri.parse(uriString);
            String[] components = uri.getHost().split("\\.");
            if (components.length == 2) {
                this.bridgeService = components[0];
                this.serviceMethod = components[1];
                this.arguments = new HashMap();
                String queryString = uriString.substring(uriString.indexOf(63) + 1);
                for (String param : queryString.split("&")) {
                    String[] subComponents = param.split("=");
                    if (subComponents.length >= 2) {
                        this.arguments.put(Uri.decode(subComponents[0]), Uri.decode(subComponents[1]));
                        if (subComponents[0].equalsIgnoreCase("callback")) {
                            this.callback = Uri.decode(subComponents[1]);
                        } else {
                            continue;
                        }
                    }
                }
            }
        } catch (Exception e) {
            MMLog.e(TAG, String.format("Exception while executing javascript call %s ", uriString), e);
            e.printStackTrace();
        }
    }

    @Override // java.lang.Runnable
    public void run() {
        final MMWebView webViewCallback;
        final MMWebView webViewCallback2;
        MMJSResponse response = null;
        try {
            if (this.bridgeService != null && this.serviceMethod != null) {
                try {
                    MMWebView webView = this.webViewRef.get();
                    if (webView != null) {
                        MMJSObject service = getBridgeService(this.bridgeService);
                        if (service != null) {
                            service.setContext(webView.getContext());
                            service.setMMWebView(webView);
                            webView.updateArgumentsWithSettings(this.arguments);
                            response = service.executeCommand(this.serviceMethod, this.arguments);
                        } else {
                            response = MMJSResponse.responseWithError("Service: " + this.bridgeService + " does not exist.");
                        }
                    }
                } catch (Exception e) {
                    MMLog.e(TAG, "Exception while executing javascript call " + this.serviceMethod, e);
                    response = MMJSResponse.responseWithError("Unexpected exception, " + e.getClass().getName() + " received.");
                }
            } else {
                response = MMJSResponse.responseWithError("The service or service method was not defined.");
            }
            if (!TextUtils.isEmpty(this.callback) && (webViewCallback2 = this.webViewRef.get()) != null) {
                if (response == null) {
                    response = MMJSResponse.responseWithError(this.serviceMethod);
                }
                if (response.methodName == null) {
                    response.methodName = this.serviceMethod;
                }
                if (response.className == null) {
                    response.className = getBridgeStrippedClassName();
                }
                final MMJSResponse resp = response;
                MMSDK.runOnUiThread(new Runnable() { // from class: com.millennialmedia.android.MMCommand.1
                    @Override // java.lang.Runnable
                    public void run() {
                        if (MMCommand.this.serviceMethod.equals("expandWithProperties")) {
                            webViewCallback2.isExpanding = true;
                        }
                        String call = String.format("javascript:%s(%s);", MMCommand.this.callback, resp.toJSONString());
                        webViewCallback2.loadUrl(call);
                    }
                });
            }
        } catch (Throwable th) {
            if (!TextUtils.isEmpty(this.callback) && (webViewCallback = this.webViewRef.get()) != null) {
                if (response == null) {
                    response = MMJSResponse.responseWithError(this.serviceMethod);
                }
                if (response.methodName == null) {
                    response.methodName = this.serviceMethod;
                }
                if (response.className == null) {
                    response.className = getBridgeStrippedClassName();
                }
                final MMJSResponse resp2 = response;
                MMSDK.runOnUiThread(new Runnable() { // from class: com.millennialmedia.android.MMCommand.1
                    @Override // java.lang.Runnable
                    public void run() {
                        if (MMCommand.this.serviceMethod.equals("expandWithProperties")) {
                            webViewCallback.isExpanding = true;
                        }
                        String call = String.format("javascript:%s(%s);", MMCommand.this.callback, resp2.toJSONString());
                        webViewCallback.loadUrl(call);
                    }
                });
            }
            throw th;
        }
    }

    private String getBridgeStrippedClassName() {
        String preStripped = this.bridgeService;
        return preStripped.replaceFirst("Bridge", "");
    }

    private MMJSObject getBridgeService(String bridgeName) {
        if (bridgeName == null) {
            return null;
        }
        if (MM_BANNER.equals(bridgeName)) {
            MMJSObject mmjsObj = ComponentRegistry.getBannerBridge();
            return mmjsObj;
        }
        if (MM_CACHED_VIDEO.equals(bridgeName)) {
            MMJSObject mmjsObj2 = ComponentRegistry.getCachedVideoBridge();
            return mmjsObj2;
        }
        if (MM_CALENDAR.equals(bridgeName)) {
            MMJSObject mmjsObj3 = ComponentRegistry.getCalendarBridge();
            return mmjsObj3;
        }
        if (MM_DEVICE.equals(bridgeName)) {
            MMJSObject mmjsObj4 = ComponentRegistry.getDeviceBridge();
            return mmjsObj4;
        }
        if (MM_INLINE_VIDEO.equals(bridgeName)) {
            MMJSObject mmjsObj5 = ComponentRegistry.getInlineVideoBridge();
            return mmjsObj5;
        }
        if (MM_INTERSTITIAL.equals(bridgeName)) {
            MMJSObject mmjsObj6 = ComponentRegistry.getInterstitialBridge();
            return mmjsObj6;
        }
        if (MM_MEDIA.equals(bridgeName)) {
            MMJSObject mmjsObj7 = ComponentRegistry.getMediaBridge();
            return mmjsObj7;
        }
        if (MM_NOTIFICATION.equals(bridgeName)) {
            MMJSObject mmjsObj8 = ComponentRegistry.getNotificationBridge();
            return mmjsObj8;
        }
        if (!MM_SPEECHKIT.equals(bridgeName)) {
            return null;
        }
        MMJSObject mmjsObj9 = ComponentRegistry.getSpeechkitBridge();
        return mmjsObj9;
    }
}
