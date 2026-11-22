package com.millennialmedia.android;

import android.content.Context;
import android.content.Intent;
import android.content.pm.ResolveInfo;
import android.net.Uri;
import android.os.Build;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.WindowManager;
import com.facebook.internal.ServerProtocol;
import com.facebook.share.internal.ShareConstants;
import com.mopub.common.Constants;
import java.net.URLEncoder;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes.dex */
class BridgeMMDevice extends MMJSObject {
    private static final String CALL = "call";
    private static final String COMPOSE_EMAIL = "composeEmail";
    private static final String COMPOSE_SMS = "composeSms";
    private static final String ENABLE_HARDWARE_ACCEL = "enableHardwareAcceleration";
    private static final String GET_AVAIL_SCHEMES = "getAvailableSchemes";
    private static final String GET_INFO = "getInfo";
    private static final String GET_LOCATION = "getLocation";
    private static final String GET_ORIENTATION = "getOrientation";
    private static final String IS_SCHEME_AVAIL = "isSchemeAvailable";
    private static final String OPEN_APP_STORE = "openAppStore";
    private static final String OPEN_URL = "openUrl";
    private static final String SET_MMDID = "setMMDID";
    private static final String SHOW_MAP = "showMap";
    private static final String TAG = "BridgeMMDevice";
    private static final String TWEET = "tweet";

    BridgeMMDevice() {
    }

    public MMJSResponse setMMDID(Map<String, String> arguments) {
        String mmdid = arguments.get("mmdid");
        Context context = this.contextRef.get();
        if (context == null) {
            return null;
        }
        HandShake handShake = HandShake.sharedHandShake(context);
        handShake.setMMdid(context, mmdid);
        return MMJSResponse.responseWithSuccess("MMDID is set");
    }

    public MMJSResponse enableHardwareAcceleration(Map<String, String> arguments) {
        MMLog.d(TAG, "hardware accel call" + arguments);
        String enabled = arguments.get("enabled");
        MMWebView webView = this.mmWebViewRef.get();
        if (webView != null && webView != null) {
            boolean enable = Boolean.parseBoolean(enabled);
            if (enable) {
                webView.enableHardwareAcceleration();
            } else {
                webView.disableAllAcceleration();
            }
            return MMJSResponse.responseWithSuccess();
        }
        return null;
    }

    public MMJSResponse getAvailableSchemes(Map<String, String> arguments) {
        Context context = this.contextRef.get();
        if (context == null) {
            return null;
        }
        HandShake handShake = HandShake.sharedHandShake(context);
        MMJSResponse response = new MMJSResponse();
        response.result = 1;
        response.response = handShake.getSchemesJSONArray(context);
        return response;
    }

    public MMJSResponse isSchemeAvailable(Map<String, String> arguments) {
        String scheme = arguments.get("scheme");
        if (!scheme.contains(com.facebook.ads.InterstitialAd.SEPARATOR)) {
            scheme = scheme + com.facebook.ads.InterstitialAd.SEPARATOR;
        }
        Context context = this.contextRef.get();
        if (scheme != null && context != null) {
            Intent intent = new Intent("android.intent.action.VIEW", Uri.parse(scheme));
            List<ResolveInfo> list = context.getPackageManager().queryIntentActivities(intent, 65536);
            if (list.size() > 0) {
                return MMJSResponse.responseWithSuccess(scheme);
            }
        }
        return MMJSResponse.responseWithError(scheme);
    }

    public MMJSResponse getOrientation(Map<String, String> arguments) {
        Context context = this.contextRef.get();
        if (context != null) {
            int orientation = context.getResources().getConfiguration().orientation;
            if (orientation == 0) {
                Display display = ((WindowManager) context.getSystemService("window")).getDefaultDisplay();
                orientation = display.getOrientation();
            }
            MMJSResponse response = new MMJSResponse();
            response.result = 1;
            switch (orientation) {
                case 2:
                    response.response = "landscape";
                    return response;
                default:
                    response.response = "portrait";
                    return response;
            }
        }
        return null;
    }

    public MMJSResponse getInfo(Map<String, String> arguments) {
        Context context = this.contextRef.get();
        if (context == null) {
            return null;
        }
        MMJSResponse response = new MMJSResponse();
        response.result = 1;
        response.response = getDeviceInfo(context);
        return response;
    }

    static JSONObject getDeviceInfo(Context context) {
        JSONObject jsonObject = null;
        try {
            JSONObject jsonObject2 = new JSONObject();
            try {
                jsonObject2.put("sdkVersion", MMSDK.VERSION);
                jsonObject2.put("connection", MMSDK.getConnectionType(context));
                jsonObject2.put("platform", "Android");
                if (Build.VERSION.RELEASE != null) {
                    jsonObject2.put(ServerProtocol.FALLBACK_DIALOG_PARAM_VERSION, Build.VERSION.RELEASE);
                }
                if (Build.MODEL != null) {
                    jsonObject2.put("device", Build.MODEL);
                }
                jsonObject2.put("mmdid", MMSDK.getMMdid(context));
                DisplayMetrics metrics = context.getResources().getDisplayMetrics();
                jsonObject2.put("density", new Float(metrics.density));
                jsonObject2.put("height", new Integer(metrics.heightPixels));
                jsonObject2.put("width", new Integer(metrics.widthPixels));
                Locale locale = Locale.getDefault();
                if (locale != null) {
                    jsonObject2.put("language", locale.getLanguage());
                    jsonObject2.put("country", locale.getCountry());
                }
                JSONObject jsonCookieObject = new JSONObject();
                try {
                    jsonCookieObject.put("name", "MAC-ID");
                    jsonCookieObject.put("path", "/");
                    jsonCookieObject.put("value", MMSDK.macId);
                    JSONArray jsonCookieArray = new JSONArray();
                    try {
                        jsonCookieArray.put(jsonCookieObject);
                        jsonObject2.put("cookies", jsonCookieArray);
                        return jsonObject2;
                    } catch (JSONException e) {
                        e = e;
                        jsonObject = jsonObject2;
                        MMLog.e(TAG, "Bridge getting deviceInfo json exception: ", e);
                        return jsonObject;
                    }
                } catch (JSONException e2) {
                    e = e2;
                    jsonObject = jsonObject2;
                }
            } catch (JSONException e3) {
                e = e3;
                jsonObject = jsonObject2;
            }
        } catch (JSONException e4) {
            e = e4;
        }
    }

    public MMJSResponse getLocation(Map<String, String> arguments) {
        if (MMRequest.location != null) {
            JSONObject jsonObject = null;
            try {
                JSONObject jsonObject2 = new JSONObject();
                try {
                    jsonObject2.put("lat", Double.toString(MMRequest.location.getLatitude()));
                    jsonObject2.put("long", Double.toString(MMRequest.location.getLongitude()));
                    if (MMRequest.location.hasAccuracy()) {
                        jsonObject2.put("ha", Float.toString(MMRequest.location.getAccuracy()));
                        jsonObject2.put("va", Float.toString(MMRequest.location.getAccuracy()));
                    }
                    if (MMRequest.location.hasSpeed()) {
                        jsonObject2.put("spd", Float.toString(MMRequest.location.getSpeed()));
                    }
                    if (MMRequest.location.hasBearing()) {
                        jsonObject2.put("brg", Float.toString(MMRequest.location.getBearing()));
                    }
                    if (MMRequest.location.hasAltitude()) {
                        jsonObject2.put("alt", Double.toString(MMRequest.location.getAltitude()));
                    }
                    jsonObject2.put("tslr", Long.toString(MMRequest.location.getTime()));
                    jsonObject = jsonObject2;
                } catch (JSONException e) {
                    e = e;
                    jsonObject = jsonObject2;
                    MMLog.e(TAG, "Bridge getLocation json exception: ", e);
                }
            } catch (JSONException e2) {
                e = e2;
            }
            MMJSResponse response = new MMJSResponse();
            response.result = 1;
            response.response = jsonObject;
            return response;
        }
        return MMJSResponse.responseWithError("location object has not been set");
    }

    public MMJSResponse showMap(Map<String, String> arguments) {
        Context context = this.contextRef.get();
        String location = arguments.get("location");
        if (context == null || location == null) {
            return null;
        }
        MMLog.d(TAG, String.format("Launching Google Maps: %s", location));
        Intent intent = new Intent("android.intent.action.VIEW", Uri.parse("geo:" + location));
        Utils.IntentUtils.startActivity(context, intent);
        long adImplId = getAdImplId(arguments.get("PROPERTY_EXPANDING"));
        MMSDK.Event.intentStarted(context, MMSDK.Event.INTENT_MAPS, adImplId);
        return MMJSResponse.responseWithSuccess("Map successfully opened");
    }

    public MMJSResponse call(Map<String, String> arguments) {
        Intent intent;
        Context context = this.contextRef.get();
        String number = arguments.get("number");
        if (context != null && number != null) {
            MMLog.d(TAG, String.format("Dialing Phone: %s", number));
            if (Boolean.parseBoolean(arguments.get("dial")) && context.checkCallingOrSelfPermission("android.permission.CALL_PHONE") == 0) {
                intent = new Intent("android.intent.action.CALL", Uri.parse("tel:" + number));
            } else {
                intent = new Intent("android.intent.action.VIEW", Uri.parse("tel:" + number));
            }
            Utils.IntentUtils.startActivity(context, intent);
            long adImplId = getAdImplId(arguments.get("PROPERTY_EXPANDING"));
            MMSDK.Event.intentStarted(context, MMSDK.Event.INTENT_PHONE_CALL, adImplId);
            return MMJSResponse.responseWithSuccess();
        }
        return null;
    }

    public MMJSResponse composeSms(Map<String, String> arguments) {
        Context context = this.contextRef.get();
        String number = arguments.get("number");
        String message = arguments.get(ShareConstants.WEB_DIALOG_PARAM_MESSAGE);
        if (context == null || number == null) {
            return null;
        }
        MMLog.d(TAG, String.format("Creating sms: %s", number));
        Intent intent = new Intent("android.intent.action.VIEW", Uri.parse("sms:" + number));
        if (message != null) {
            intent.putExtra("sms_body", message);
        }
        Utils.IntentUtils.startActivity(context, intent);
        long adImplId = getAdImplId(arguments.get("PROPERTY_EXPANDING"));
        MMSDK.Event.intentStarted(context, MMSDK.Event.INTENT_TXT_MESSAGE, adImplId);
        return MMJSResponse.responseWithSuccess("SMS Sent");
    }

    public MMJSResponse composeEmail(Map<String, String> arguments) {
        Context context = this.contextRef.get();
        String recipients = arguments.get("recipient");
        String subject = arguments.get("subject");
        String message = arguments.get(ShareConstants.WEB_DIALOG_PARAM_MESSAGE);
        if (context == null) {
            return null;
        }
        MMLog.d(TAG, "Creating email");
        Intent emailIntent = new Intent("android.intent.action.SEND");
        emailIntent.setType("plain/text");
        if (recipients != null) {
            emailIntent.putExtra("android.intent.extra.EMAIL", recipients.split(","));
        }
        if (subject != null) {
            emailIntent.putExtra("android.intent.extra.SUBJECT", subject);
        }
        if (message != null) {
            emailIntent.putExtra("android.intent.extra.TEXT", message);
        }
        Utils.IntentUtils.startActivity(context, emailIntent);
        long adImplId = getAdImplId(arguments.get("PROPERTY_EXPANDING"));
        MMSDK.Event.intentStarted(context, "email", adImplId);
        return MMJSResponse.responseWithSuccess();
    }

    public MMJSResponse openUrl(Map<String, String> arguments) {
        Context context = this.contextRef.get();
        String url = arguments.get("url");
        if (context == null || url == null) {
            return MMJSResponse.responseWithError("URL could not be opened");
        }
        MMLog.d(TAG, String.format("Opening: %s", url));
        Intent intent = new Intent("android.intent.action.VIEW", Uri.parse(url));
        if (intent.getScheme().startsWith(Constants.HTTP) || intent.getScheme().startsWith(Constants.HTTPS)) {
            long adImplId = getAdImplId(arguments.get("PROPERTY_EXPANDING"));
            MMSDK.Event.intentStarted(context, MMSDK.Event.INTENT_EXTERNAL_BROWSER, adImplId);
        }
        Utils.IntentUtils.startActivity(context, intent);
        return MMJSResponse.responseWithSuccess("Overlay opened");
    }

    public MMJSResponse openAppStore(Map<String, String> arguments) {
        Context context = this.contextRef.get();
        String id = arguments.get("appId");
        String referrer = arguments.get("referrer");
        if (context != null && id != null) {
            MMLog.d(TAG, String.format("Opening marketplace: %s", id));
            Intent intent = new Intent("android.intent.action.VIEW");
            if (Build.MANUFACTURER.equals("Amazon")) {
                intent.setData(Uri.parse(String.format("amzn://apps/android?p=%s", id)));
            } else if (referrer != null) {
                intent.setData(Uri.parse(String.format("market://details?id=%s&referrer=%s", id, URLEncoder.encode(referrer))));
            } else {
                intent.setData(Uri.parse("market://details?id=" + id));
            }
            long adImplId = getAdImplId(arguments.get("PROPERTY_EXPANDING"));
            MMSDK.Event.intentStarted(context, MMSDK.Event.INTENT_MARKET, adImplId);
            Utils.IntentUtils.startActivity(context, intent);
            return MMJSResponse.responseWithSuccess();
        }
        return null;
    }

    public MMJSResponse tweet(Map<String, String> arguments) {
        return null;
    }

    @Override // com.millennialmedia.android.MMJSObject
    MMJSResponse executeCommand(String name, Map<String, String> arguments) {
        if ("call".equals(name)) {
            MMJSResponse response = call(arguments);
            return response;
        }
        if (COMPOSE_EMAIL.equals(name)) {
            MMJSResponse response2 = composeEmail(arguments);
            return response2;
        }
        if (COMPOSE_SMS.equals(name)) {
            MMJSResponse response3 = composeSms(arguments);
            return response3;
        }
        if (ENABLE_HARDWARE_ACCEL.equals(name)) {
            MMJSResponse response4 = enableHardwareAcceleration(arguments);
            return response4;
        }
        if (GET_AVAIL_SCHEMES.equals(name)) {
            MMJSResponse response5 = getAvailableSchemes(arguments);
            return response5;
        }
        if (GET_INFO.equals(name)) {
            MMJSResponse response6 = getInfo(arguments);
            return response6;
        }
        if (GET_LOCATION.equals(name)) {
            MMJSResponse response7 = getLocation(arguments);
            return response7;
        }
        if (GET_ORIENTATION.equals(name)) {
            MMJSResponse response8 = getOrientation(arguments);
            return response8;
        }
        if (IS_SCHEME_AVAIL.equals(name)) {
            MMJSResponse response9 = isSchemeAvailable(arguments);
            return response9;
        }
        if ("openAppStore".equals(name)) {
            MMJSResponse response10 = openAppStore(arguments);
            return response10;
        }
        if (OPEN_URL.equals(name)) {
            MMJSResponse response11 = openUrl(arguments);
            return response11;
        }
        if (SET_MMDID.equals(name)) {
            MMJSResponse response12 = setMMDID(arguments);
            return response12;
        }
        if (SHOW_MAP.equals(name)) {
            MMJSResponse response13 = showMap(arguments);
            return response13;
        }
        if (!TWEET.equals(name)) {
            return null;
        }
        MMJSResponse response14 = tweet(arguments);
        return response14;
    }
}
