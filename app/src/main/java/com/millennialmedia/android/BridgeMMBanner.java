package com.millennialmedia.android;

import android.content.Context;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import java.util.Map;

/* JADX INFO: loaded from: classes.dex */
class BridgeMMBanner extends MMJSObject {
    static final String RESIZE = "resize";

    BridgeMMBanner() {
    }

    public MMJSResponse resize(Map<String, String> arguments) {
        MMWebView mmWebView = this.mmWebViewRef.get();
        if (mmWebView != null) {
            if (mmWebView.isMraidResized()) {
                return MMJSResponse.responseWithError("State is currently resized");
            }
            String widthArg = arguments.get("width");
            String heightArg = arguments.get("height");
            int width = 0;
            int height = 0;
            if (!TextUtils.isEmpty(widthArg)) {
                width = (int) Float.parseFloat(widthArg);
            }
            if (!TextUtils.isEmpty(heightArg)) {
                height = (int) Float.parseFloat(heightArg);
            }
            String customClosePosition = arguments.get("customClosePosition");
            String offsetXArg = arguments.get("offsetX");
            String offsetYArg = arguments.get("offsetY");
            int offsetY = 0;
            int offsetX = 0;
            if (!TextUtils.isEmpty(offsetYArg)) {
                offsetY = (int) Float.parseFloat(offsetYArg);
            }
            if (!TextUtils.isEmpty(offsetXArg)) {
                offsetX = (int) Float.parseFloat(offsetXArg);
            }
            boolean allowOffScreen = Boolean.parseBoolean(arguments.get("allowOffscreen"));
            Context context = mmWebView.getContext();
            DisplayMetrics metrics = context.getResources().getDisplayMetrics();
            int xMax = getScreenWidth(context);
            int yMax = getScreenHeight(context);
            DTOResizeParameters resizeParams = new DTOResizeParameters(metrics.density, width, height, customClosePosition, offsetX, offsetY, allowOffScreen, xMax, yMax);
            mmWebView.setMraidResize(resizeParams);
            return MMJSResponse.responseWithSuccess();
        }
        return null;
    }

    int getScreenHeight(Context context) {
        return Integer.parseInt(MMSDK.getDpiHeight(context));
    }

    int getScreenWidth(Context context) {
        return Integer.parseInt(MMSDK.getDpiWidth(context));
    }

    @Override // com.millennialmedia.android.MMJSObject
    MMJSResponse executeCommand(String name, Map<String, String> arguments) {
        if (!RESIZE.equals(name)) {
            return null;
        }
        MMJSResponse response = resize(arguments);
        return response;
    }
}
