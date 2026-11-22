package com.millennialmedia.android;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import java.util.Map;

/* JADX INFO: loaded from: classes.dex */
class BridgeMMInterstitial extends MMJSObject {
    private static final String CLOSE = "close";
    private static final String EXPAND_TO_EXTERNAL_BROWSER = "expandToExternalBrowser";
    private static final String EXPAND_WITH_PROPERTIES = "expandWithProperties";
    private static final String OPEN = "open";
    private static final String SET_ORIENTATION = "setOrientation";
    private static final String TAG = BridgeMMInterstitial.class.getName();
    private static final String USE_CUSTOM_CLOSE = "useCustomClose";

    BridgeMMInterstitial() {
    }

    public MMJSResponse close(Map<String, String> arguments) {
        MMWebView mmWebView = this.mmWebViewRef.get();
        if (mmWebView == null) {
            return null;
        }
        MMLayout layout = mmWebView.getMMLayout();
        layout.closeAreaTouched();
        return MMJSResponse.responseWithSuccess();
    }

    public MMJSResponse open(Map<String, String> arguments) {
        String url = arguments.get("url");
        Context context = this.contextRef.get();
        if (url == null || context == null) {
            return null;
        }
        Intent intent = new Intent("android.intent.action.VIEW", Uri.parse(url));
        long adImplId = getAdImplId(arguments.get("PROPERTY_EXPANDING"));
        MMSDK.Event.intentStarted(context, MMSDK.Event.INTENT_EXTERNAL_BROWSER, adImplId);
        Utils.IntentUtils.startActivity(context, intent);
        return MMJSResponse.responseWithSuccess();
    }

    public MMJSResponse expandToExternalBrowser(Map<String, String> arguments) {
        return open(arguments);
    }

    public MMJSResponse expandWithProperties(Map<String, String> arguments) {
        String isBanner = arguments.get("PROPERTY_BANNER_TYPE");
        if (isBanner != null && !Boolean.parseBoolean(isBanner)) {
            return MMJSResponse.responseWithError("Cannot expand a non banner ad");
        }
        String url = arguments.get("url");
        String transparent = arguments.get("transparent");
        String useCustomClose = arguments.get(USE_CUSTOM_CLOSE);
        String transition = arguments.get("transition");
        String orientation = arguments.get("orientation");
        String transitionDuration = arguments.get("transitionDuration");
        String height = arguments.get("height");
        String width = arguments.get("width");
        String modal = arguments.get("modal");
        String creatorAdImplId = arguments.get("PROPERTY_EXPANDING");
        String allowOrientationChange = arguments.get("allowOrientationChange");
        Context context = this.contextRef.get();
        if (context != null) {
            OverlaySettings settings = new OverlaySettings();
            if (url != null) {
                settings.urlToLoad = url;
            }
            if (creatorAdImplId != null) {
                settings.creatorAdImplId = (int) Float.parseFloat(creatorAdImplId);
            }
            if (transparent != null) {
                settings.setIsTransparent(Boolean.parseBoolean(transparent));
            }
            if (useCustomClose != null) {
                settings.setUseCustomClose(Boolean.parseBoolean(useCustomClose));
            }
            if (transition != null) {
                settings.setTransition(transition);
            }
            if (allowOrientationChange != null) {
                settings.allowOrientationChange = Boolean.parseBoolean(allowOrientationChange);
            }
            if (orientation == null) {
                orientation = arguments.get("forceOrientation");
            }
            if (orientation != null) {
                settings.orientation = orientation;
            }
            if (height != null) {
                settings.height = (int) Float.parseFloat(height);
            }
            if (width != null) {
                settings.width = (int) Float.parseFloat(width);
            }
            if (modal != null) {
                settings.modal = Boolean.parseBoolean(modal);
            }
            if (transitionDuration != null) {
                try {
                    long transTimeMillis = Long.parseLong(transitionDuration) * 1000;
                    settings.setTransitionDurationInMillis(transTimeMillis);
                } catch (Exception e) {
                    MMLog.e(TAG, "Problem converting transitionDuration", e);
                }
            }
            Utils.IntentUtils.startAdViewOverlayActivity(context, getExpandExtrasIntent(url, settings));
            long adImplId = getAdImplId(arguments.get("PROPERTY_EXPANDING"));
            MMSDK.Event.overlayOpenedBroadCast(context, adImplId);
            return MMJSResponse.responseWithSuccess();
        }
        return null;
    }

    private Intent getExpandExtrasIntent(String url, OverlaySettings settings) {
        Intent intent = new Intent();
        if (url != null) {
            intent.setData(Uri.parse(url));
        }
        intent.putExtra("settings", settings);
        intent.putExtra("internalId", settings.creatorAdImplId);
        return intent;
    }

    public MMJSResponse useCustomClose(Map<String, String> arguments) {
        AdViewOverlayView overlayView;
        MMWebView mmWebView = this.mmWebViewRef.get();
        String useCustomClose = arguments.get(USE_CUSTOM_CLOSE);
        if (useCustomClose == null || mmWebView == null || (overlayView = mmWebView.getAdViewOverlayView()) == null) {
            return null;
        }
        overlayView.setUseCustomClose(Boolean.parseBoolean(useCustomClose));
        return MMJSResponse.responseWithSuccess();
    }

    public MMJSResponse setOrientation(Map<String, String> arguments) {
        MMJSResponse response = setForceOrientation(arguments);
        if (response == null || !isForcingOrientation(response)) {
            return setAllowOrientationChange(arguments);
        }
        return response;
    }

    private boolean isForcingOrientation(MMJSResponse response) {
        if (response.result != 1 || !(response.response instanceof String)) {
            return false;
        }
        String result = (String) response.response;
        return result.contains("portrait") || result.contains("landscape");
    }

    private MMJSResponse setAllowOrientationChange(Map<String, String> arguments) {
        AdViewOverlayActivity overlayActivity;
        String allowOrientationChange = arguments.get("allowOrientationChange");
        if (allowOrientationChange == null || (overlayActivity = getBaseActivity()) == null) {
            return null;
        }
        boolean canOrientationChange = Boolean.parseBoolean(allowOrientationChange);
        overlayActivity.setAllowOrientationChange(canOrientationChange);
        return MMJSResponse.responseWithSuccess();
    }

    private MMJSResponse setForceOrientation(Map<String, String> arguments) {
        String forceOrientation = arguments.get("forceOrientation");
        AdViewOverlayActivity overlayActivity = getBaseActivity();
        if (overlayActivity != null) {
            if (!"none".equals(forceOrientation)) {
                if ("portrait".equals(forceOrientation)) {
                    overlayActivity.setRequestedOrientationPortrait();
                    return MMJSResponse.responseWithSuccess("portrait");
                }
                if ("landscape".equals(forceOrientation)) {
                    overlayActivity.setRequestedOrientationLandscape();
                    return MMJSResponse.responseWithSuccess("landscape");
                }
            } else if ("none".equals(forceOrientation)) {
                overlayActivity.setAllowOrientationChange(true);
                return MMJSResponse.responseWithSuccess("none");
            }
        }
        return null;
    }

    @Override // com.millennialmedia.android.MMJSObject
    MMJSResponse executeCommand(String name, Map<String, String> arguments) {
        if ("close".equals(name)) {
            MMJSResponse response = close(arguments);
            return response;
        }
        if (EXPAND_TO_EXTERNAL_BROWSER.equals(name)) {
            MMJSResponse response2 = expandToExternalBrowser(arguments);
            return response2;
        }
        if (EXPAND_WITH_PROPERTIES.equals(name)) {
            MMJSResponse response3 = expandWithProperties(arguments);
            return response3;
        }
        if ("open".equals(name)) {
            MMJSResponse response4 = open(arguments);
            return response4;
        }
        if (SET_ORIENTATION.equals(name)) {
            MMJSResponse response5 = setOrientation(arguments);
            return response5;
        }
        if (!USE_CUSTOM_CLOSE.equals(name)) {
            return null;
        }
        MMJSResponse response6 = useCustomClose(arguments);
        return response6;
    }
}
