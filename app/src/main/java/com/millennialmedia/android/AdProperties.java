package com.millennialmedia.android;

import android.content.Context;
import android.util.DisplayMetrics;
import android.view.View;
import com.facebook.internal.NativeProtocol;
import java.lang.ref.WeakReference;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes.dex */
class AdProperties {
    private static final String TAG = AdProperties.class.getName();
    WeakReference<Context> contextRef;

    AdProperties(Context context) {
        this.contextRef = new WeakReference<>(context);
    }

    public JSONObject getAdProperties(View adView) {
        JSONObject adProps = new JSONObject();
        try {
            adProps.put("screen", getScreen());
            adProps.put("ad", Utils.getViewDimensions(adView));
            adProps.put("do", MMSDK.getOrientation(getContext()));
            adProps.put("supports", getSupports());
            adProps.put("device", BridgeMMDevice.getDeviceInfo(getContext()));
            adProps.put(NativeProtocol.RESULT_ARGS_PERMISSIONS, getPermissions());
            adProps.put("maxSize", getScreen());
        } catch (JSONException e) {
            MMLog.e(TAG, "Error when building ad properties", e);
        }
        return adProps;
    }

    Context getContext() {
        return this.contextRef.get();
    }

    private JSONObject getPermissions() throws JSONException {
        JSONObject params = new JSONObject();
        params.put("android.permission.ACCESS_FINE_LOCATION", getContext().checkCallingOrSelfPermission("android.permission.ACCESS_FINE_LOCATION") == 0);
        return params;
    }

    private JSONObject getScreen() throws JSONException {
        JSONObject params = new JSONObject();
        params.put("height", getScreenDpiIndependentHeight());
        params.put("width", getScreenDpiIndependentWidth());
        return params;
    }

    String getScreenDpiIndependentHeight() {
        DisplayMetrics metrics = getContext().getResources().getDisplayMetrics();
        int height = (int) (metrics.heightPixels / metrics.density);
        return String.valueOf(height);
    }

    String getScreenDpiIndependentWidth() {
        DisplayMetrics metrics = getContext().getResources().getDisplayMetrics();
        int width = (int) (metrics.widthPixels / metrics.density);
        return String.valueOf(width);
    }

    String getAdDpiIndependentHeight() {
        return getScreenDpiIndependentHeight();
    }

    String getAdDpiIndependentWidth() {
        return getScreenDpiIndependentWidth();
    }

    private JSONObject getSupports() throws JSONException {
        JSONObject params = new JSONObject();
        Context context = getContext();
        params.put(MMSDK.Event.INTENT_TXT_MESSAGE, Boolean.parseBoolean(MMSDK.getSupportsSms(context)));
        params.put(MMSDK.Event.INTENT_PHONE_CALL, Boolean.parseBoolean(MMSDK.getSupportsTel(context)));
        params.put(MMSDK.Event.INTENT_CALENDAR_EVENT, MMSDK.getSupportsCalendar());
        params.put("storePicture", false);
        params.put("inlineVideo", true);
        return params;
    }
}
