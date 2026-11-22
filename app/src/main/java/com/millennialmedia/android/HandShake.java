package com.millennialmedia.android;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ResolveInfo;
import android.net.Uri;
import android.os.Build;
import android.os.Handler;
import android.os.Looper;
import android.text.TextUtils;
import android.view.WindowManager;
import android.webkit.URLUtil;
import android.widget.Toast;
import com.facebook.appevents.AppEventsConstants;
import com.facebook.share.internal.ShareConstants;
import com.google.android.gms.games.GamesStatusCodes;
import com.millennialmedia.google.gson.Gson;
import com.unity3d.ads.android.properties.UnityAdsConstants;
import java.io.IOException;
import java.lang.ref.WeakReference;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import org.apache.http.HttpResponse;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes.dex */
class HandShake {
    static final String BASE_URL = "http://androidsdk.ads.mp.mydas.mobi/";
    static final String BASE_URL_PATH = "getAd.php5?";
    private static final String HANDSHAKE_FALLBACK_URL = "http://ads.mp.mydas.mobi/appConfigServlet?apid=";
    private static final String HANDSHAKE_HTTPS_SCHEME = "https://";
    private static final String HANDSHAKE_HTTP_SCHEME = "http://";
    private static final String HANDSHAKE_URL_HOST = "ads.mp.mydas.mobi/";
    private static final String HANDSHAKE_URL_OVERRIDE_PARMS = "?apid=";
    private static final String HANDSHAKE_URL_PARMS = "appConfigServlet?apid=";
    private static final String KEY_CACHED_VIDEOS = "handshake_cachedvideos5.0";
    private static final String TAG = "HandShake";
    private static boolean forceRefresh;
    private static HandShake sharedInstance;
    long adRefreshSecs;
    private WeakReference<Context> appContextRef;
    DTOCachedVideo[] cachedVideos;
    private WeakReference<Context> contextRef;
    String endSessionURL;
    boolean hardwareAccelerationEnabled;
    private long lastHandShake;
    String mmdid;
    String mmjsUrl;
    private String noVideosToCacheURL;
    NuanceCredentials nuanceCredentials;
    private String schemesList;
    String startSessionURL;
    static String apid = MMSDK.DEFAULT_BANNER_APID;
    private static String handShakeURL = "https://ads.mp.mydas.mobi/appConfigServlet?apid=";
    private static String adUrl = "http://androidsdk.ads.mp.mydas.mobi/getAd.php5?";
    private final Handler handler = new Handler(Looper.getMainLooper());
    private final LinkedHashMap<String, AdTypeHandShake> adTypeHandShakes = new LinkedHashMap<>();
    private final ArrayList<Scheme> schemes = new ArrayList<>();
    private long deferredViewTimeout = 3600000;
    boolean kill = false;
    private long handShakeCallback = 86400000;
    long creativeCacheTimeout = 259200000;
    private final Runnable updateHandShakeRunnable = new Runnable() { // from class: com.millennialmedia.android.HandShake.2
        @Override // java.lang.Runnable
        public void run() {
            Context context = (Context) HandShake.this.contextRef.get();
            if (context == null) {
                context = (Context) HandShake.this.appContextRef.get();
            }
            if (context != null) {
                HandShake.sharedHandShake(context);
            }
        }
    };

    static void setAdUrl(String url) {
        if (!TextUtils.isEmpty(url)) {
            if (!url.endsWith("/")) {
                adUrl = url + "/" + BASE_URL_PATH;
            } else {
                adUrl = url + BASE_URL_PATH;
            }
        }
    }

    static String getAdUrl() {
        return (TextUtils.isEmpty(adUrl) || !URLUtil.isHttpUrl(adUrl.replace(BASE_URL_PATH, ""))) ? "http://androidsdk.ads.mp.mydas.mobi/getAd.php5?" : adUrl;
    }

    static synchronized HandShake sharedHandShake(Context context) {
        HandShake handShake;
        if (apid == null) {
            MMLog.e(TAG, "No apid set for the handshake.");
            handShake = null;
        } else {
            if (sharedInstance == null) {
                sharedInstance = new HandShake(context);
            } else if (System.currentTimeMillis() - sharedInstance.lastHandShake > sharedInstance.handShakeCallback) {
                MMLog.d(TAG, "Handshake expired, requesting new handshake from the server.");
                sharedInstance = new HandShake(context);
            }
            handShake = sharedInstance;
        }
        return handShake;
    }

    static synchronized void setHandShakeURL(Context context, String url) {
        if (setHandShakeURL(url)) {
            forceRefresh = true;
            sharedInstance = new HandShake(context);
        }
    }

    static synchronized boolean setHandShakeURL(String url) {
        boolean z;
        if (TextUtils.isEmpty(url)) {
            z = false;
        } else {
            if (url.startsWith(HANDSHAKE_HTTP_SCHEME)) {
                url = url.replaceFirst(HANDSHAKE_HTTP_SCHEME, HANDSHAKE_HTTPS_SCHEME);
            }
            handShakeURL = url + HANDSHAKE_URL_OVERRIDE_PARMS;
            z = true;
        }
        return z;
    }

    synchronized boolean canRequestVideo(Context context, String adType) {
        AdTypeHandShake adTypeHandShake;
        adTypeHandShake = this.adTypeHandShakes.get(adType);
        return adTypeHandShake != null ? adTypeHandShake.canRequestVideo(context) : true;
    }

    synchronized boolean canDisplayCachedAd(String adType, long cachedTime) {
        AdTypeHandShake adTypeHandShake;
        adTypeHandShake = this.adTypeHandShakes.get(adType);
        return adTypeHandShake != null ? adTypeHandShake.canDisplayCachedAd(cachedTime) : true;
    }

    synchronized void updateLastVideoViewedTime(Context context, String adType) {
        AdTypeHandShake adTypeHandShake = this.adTypeHandShakes.get(adType);
        if (adTypeHandShake != null) {
            adTypeHandShake.updateLastVideoViewedTime(context, adType);
        }
    }

    synchronized boolean isAdTypeDownloading(String adType) {
        AdTypeHandShake adTypeHandShake;
        adTypeHandShake = this.adTypeHandShakes.get(adType);
        return adTypeHandShake != null ? adTypeHandShake.downloading : false;
    }

    synchronized void lockAdTypeDownload(String adType) {
        AdTypeHandShake adTypeHandShake = this.adTypeHandShakes.get(adType);
        if (adTypeHandShake != null) {
            adTypeHandShake.downloading = true;
        }
    }

    synchronized void unlockAdTypeDownload(String adType) {
        AdTypeHandShake adTypeHandShake = this.adTypeHandShakes.get(adType);
        if (adTypeHandShake != null) {
            adTypeHandShake.downloading = false;
        }
    }

    void setMMdid(Context context, String newMMdid) {
        setMMdid(context, newMMdid, true);
    }

    /* JADX WARN: Code duplicated, block: B:11:0x001b A[Catch: all -> 0x0035, TRY_LEAVE, TryCatch #0 {, blocks: (B:4:0x0003, B:6:0x0009, B:8:0x0011, B:9:0x0014, B:11:0x001b, B:14:0x0032), top: B:19:0x0003 }] */
    /* JADX WARN: Code duplicated, block: B:14:0x0032 A[Catch: all -> 0x0035, TRY_ENTER, TRY_LEAVE, TryCatch #0 {, blocks: (B:4:0x0003, B:6:0x0009, B:8:0x0011, B:9:0x0014, B:11:0x001b, B:14:0x0032), top: B:19:0x0003 }] */
    synchronized void setMMdid(Context context, String newMMdid, boolean persist) {
        if (newMMdid == null) {
            this.mmdid = newMMdid;
            MMSDK.setMMdid(this.mmdid);
            if (persist) {
                SharedPreferences settings = context.getSharedPreferences("MillennialMediaSettings", 0);
                SharedPreferences.Editor editor = settings.edit();
                editor.putString("handshake_mmdid", this.mmdid);
                editor.commit();
            }
        } else {
            if (newMMdid.length() == 0 || newMMdid.equals("NULL")) {
                this.mmdid = null;
            } else {
                this.mmdid = newMMdid;
            }
            MMSDK.setMMdid(this.mmdid);
            if (persist) {
                SharedPreferences settings2 = context.getSharedPreferences("MillennialMediaSettings", 0);
                SharedPreferences.Editor editor2 = settings2.edit();
                editor2.putString("handshake_mmdid", this.mmdid);
                editor2.commit();
            }
        }
        throw th;
    }

    private HandShake() {
    }

    private HandShake(Context context) {
        this.contextRef = new WeakReference<>(context);
        this.appContextRef = new WeakReference<>(context.getApplicationContext());
        if (forceRefresh || !loadHandShake(context) || System.currentTimeMillis() - this.lastHandShake > this.handShakeCallback) {
            forceRefresh = false;
            this.lastHandShake = System.currentTimeMillis();
            requestHandshake(false);
        }
    }

    private void requestHandshake(final boolean isInitialize) {
        Context context = this.contextRef.get();
        if (context != null) {
            SharedPreferences mmSettings = context.getSharedPreferences("MillennialMediaSettings", 0);
            String handShakeUrl = mmSettings.getString("handShakeUrl", null);
            if (handShakeUrl != null) {
                setHandShakeURL(handShakeUrl);
            }
        }
        Utils.ThreadUtils.execute(new Runnable() { // from class: com.millennialmedia.android.HandShake.1
            @Override // java.lang.Runnable
            public void run() {
                Context tempContext = (Context) HandShake.this.contextRef.get();
                if (tempContext == null) {
                    tempContext = (Context) HandShake.this.appContextRef.get();
                }
                if (tempContext == null) {
                    return;
                }
                boolean isFirstLaunch = false;
                try {
                    try {
                        try {
                            StringBuilder handshakeUrlBuilder = new StringBuilder();
                            Map<String, String> deviceValuesMap = new TreeMap<>();
                            deviceValuesMap.put("ua", "Android:" + Build.MODEL);
                            isFirstLaunch = HandShake.this.isFirstLaunch(tempContext);
                            if (isFirstLaunch) {
                                deviceValuesMap.put("firstlaunch", AppEventsConstants.EVENT_PARAM_VALUE_YES);
                            }
                            if (isInitialize) {
                                deviceValuesMap.put(UnityAdsConstants.UNITY_ADS_WEBVIEW_JS_INIT, AppEventsConstants.EVENT_PARAM_VALUE_YES);
                            }
                            MMSDK.insertUrlCommonValues(tempContext, deviceValuesMap);
                            for (Map.Entry<String, String> entry : deviceValuesMap.entrySet()) {
                                handshakeUrlBuilder.append(String.format("&%s=%s", entry.getKey(), URLEncoder.encode(entry.getValue(), "UTF-8")));
                            }
                            String handshakeUrl = HandShake.handShakeURL + HandShake.apid + handshakeUrlBuilder.toString();
                            MMLog.v(HandShake.TAG, String.format("Performing handshake: %s", handshakeUrl));
                            HttpResponse httpResponse = null;
                            try {
                                httpResponse = new HttpGetRequest(GamesStatusCodes.STATUS_ACHIEVEMENT_UNLOCK_FAILURE).get(handshakeUrl);
                            } catch (IOException ioe) {
                                MMLog.e(HandShake.TAG, "Could not get a handshake. ", ioe);
                            }
                            if (httpResponse == null || httpResponse.getStatusLine().getStatusCode() != 200) {
                                try {
                                    String unused = HandShake.handShakeURL = HandShake.handShakeURL.replaceFirst(HandShake.HANDSHAKE_HTTPS_SCHEME, HandShake.HANDSHAKE_HTTP_SCHEME);
                                    String handshakeUrl2 = HandShake.handShakeURL + HandShake.apid + handshakeUrlBuilder.toString();
                                    MMLog.v(HandShake.TAG, String.format("Performing handshake (HTTP Fallback): %s", handshakeUrl2));
                                    httpResponse = new HttpGetRequest().get(handshakeUrl2);
                                } catch (IOException ioe2) {
                                    MMLog.e(HandShake.TAG, "Could not get a handshake. ", ioe2);
                                }
                            }
                            if (httpResponse == null || httpResponse.getStatusLine().getStatusCode() != 200) {
                                try {
                                    String handshakeUrl3 = HandShake.HANDSHAKE_FALLBACK_URL + HandShake.apid + handshakeUrlBuilder.toString();
                                    MMLog.v(HandShake.TAG, "Performing handshake (HTTP Fallback Original): " + handshakeUrl3);
                                    httpResponse = new HttpGetRequest().get(handshakeUrl3);
                                } catch (IOException ioe3) {
                                    MMLog.e(HandShake.TAG, "Could not get a handshake. ", ioe3);
                                }
                            }
                            if (httpResponse == null || httpResponse.getStatusLine().getStatusCode() != 200) {
                                isFirstLaunch = false;
                            } else {
                                HandShake.this.deserializeFromObj(HandShake.this.parseJson(HttpGetRequest.convertStreamToString(httpResponse.getEntity().getContent())));
                                HandShake.this.saveHandShake(tempContext);
                                HandShake.this.handler.postDelayed(HandShake.this.updateHandShakeRunnable, HandShake.this.handShakeCallback);
                                MMLog.v(HandShake.TAG, "Obtained a new handshake");
                            }
                        } catch (Exception e) {
                            MMLog.e(HandShake.TAG, "Could not get a handshake. ", e);
                            isFirstLaunch = false;
                        }
                    } catch (IOException ioe4) {
                        MMLog.e(HandShake.TAG, "Could not get a handshake. ", ioe4);
                        isFirstLaunch = false;
                    }
                } finally {
                    if (isFirstLaunch) {
                        HandShake.this.sentFirstLaunch(tempContext);
                    }
                }
            }
        });
    }

    void sendInitRequest() {
        requestHandshake(true);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean isFirstLaunch(Context tempContext) {
        if (tempContext == null) {
            return false;
        }
        SharedPreferences settings = tempContext.getSharedPreferences("MillennialMediaSettings", 0);
        return settings.getBoolean("firstlaunchHandshake", true);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void sentFirstLaunch(Context tempContext) {
        if (tempContext != null) {
            SharedPreferences settings = tempContext.getSharedPreferences("MillennialMediaSettings", 0);
            SharedPreferences.Editor editor = settings.edit();
            editor.putBoolean("firstlaunchHandshake", false);
            editor.commit();
        }
    }

    synchronized String getSchemesList(Context context) {
        if (this.schemesList == null && this.schemes.size() > 0) {
            StringBuilder stringBuilder = new StringBuilder();
            for (Scheme scheme : this.schemes) {
                if (scheme.checkAvailability(context)) {
                    if (stringBuilder.length() > 0) {
                        stringBuilder.append("," + scheme.id);
                    } else {
                        stringBuilder.append(Integer.toString(scheme.id));
                    }
                }
            }
            if (stringBuilder.length() > 0) {
                this.schemesList = stringBuilder.toString();
            }
        }
        return this.schemesList;
    }

    synchronized JSONArray getSchemesJSONArray(Context context) {
        JSONArray array;
        array = new JSONArray();
        if (this.schemes.size() > 0) {
            for (Scheme scheme : this.schemes) {
                if (scheme.checkAvailability(context)) {
                    try {
                        JSONObject schemeObject = new JSONObject();
                        schemeObject.put("scheme", scheme.scheme);
                        schemeObject.put("schemeid", scheme.id);
                        array.put(schemeObject);
                    } catch (JSONException e) {
                        MMLog.e(TAG, "Json error getting scheme", e);
                    }
                }
            }
        }
        return array;
    }

    static class NuanceCredentials {
        String appID;
        String appKey;
        int port;
        String server;
        String sessionID;

        private NuanceCredentials() {
        }

        public String toString() {
            return "Credentials: appid=" + this.appID + " server=" + this.server + " port=" + this.port + " appKey=" + this.appKey + "sessionID=" + this.sessionID;
        }
    }

    private class Scheme {
        int id;
        String scheme;

        Scheme() {
        }

        Scheme(String scheme, int id) {
            this.scheme = scheme;
            this.id = id;
        }

        boolean checkAvailability(Context context) {
            Intent intent;
            if (this.scheme.contains("://")) {
                intent = new Intent("android.intent.action.VIEW", Uri.parse(this.scheme));
            } else {
                intent = new Intent("android.intent.action.VIEW", Uri.parse(this.scheme + "://"));
            }
            List<ResolveInfo> list = context.getPackageManager().queryIntentActivities(intent, 65536);
            return list.size() > 0;
        }

        void deserializeFromObj(JSONObject schemeObject) {
            if (schemeObject != null) {
                this.scheme = schemeObject.optString("scheme", null);
                this.id = schemeObject.optInt("schemeid");
            }
        }
    }

    private class AdTypeHandShake {
        boolean downloading;
        long lastVideo = 0;
        long videoInterval = 0;

        AdTypeHandShake() {
        }

        boolean canRequestVideo(Context context) {
            long time = System.currentTimeMillis();
            MMLog.d(HandShake.TAG, "canRequestVideo() Current Time:" + time + " Last Video: " + (this.lastVideo / 1000) + "  Diff: " + ((time - this.lastVideo) / 1000) + "  Video interval: " + (this.videoInterval / 1000));
            return System.currentTimeMillis() - this.lastVideo > this.videoInterval;
        }

        boolean canDisplayCachedAd(long cachedTime) {
            return System.currentTimeMillis() - cachedTime < HandShake.this.deferredViewTimeout;
        }

        void updateLastVideoViewedTime(Context context, String adType) {
            this.lastVideo = System.currentTimeMillis();
            save(context, adType);
        }

        void deserializeFromObj(JSONObject adTypeObject) {
            if (adTypeObject != null) {
                this.videoInterval = adTypeObject.optLong("videointerval") * 1000;
            }
        }

        boolean load(SharedPreferences settings, String adType) {
            boolean settingsFound = false;
            if (settings.contains("handshake_lastvideo_" + adType)) {
                this.lastVideo = settings.getLong("handshake_lastvideo_" + adType, this.lastVideo);
                settingsFound = true;
            }
            if (settings.contains("handshake_videointerval_" + adType)) {
                this.videoInterval = settings.getLong("handshake_videointerval_" + adType, this.videoInterval);
                return true;
            }
            return settingsFound;
        }

        void loadLastVideo(Context context, String adType) {
            SharedPreferences settings = context.getSharedPreferences("MillennialMediaSettings", 0);
            if (settings != null && settings.contains("handshake_lastvideo_" + adType)) {
                this.lastVideo = settings.getLong("handshake_lastvideo_" + adType, this.lastVideo);
            }
        }

        void save(SharedPreferences.Editor editor, String adType) {
            editor.putLong("handshake_lastvideo_" + adType, this.lastVideo);
            editor.putLong("handshake_videointerval_" + adType, this.videoInterval);
        }

        void save(Context context, String adType) {
            SharedPreferences settings = context.getSharedPreferences("MillennialMediaSettings", 0);
            SharedPreferences.Editor editor = settings.edit();
            save(editor, adType);
            editor.commit();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public JSONObject parseJson(String jsonString) {
        MMLog.d(TAG, String.format("JSON String: %s", jsonString));
        if (jsonString != null) {
            try {
                JSONObject jsonObject = new JSONObject(jsonString);
                MMLog.v(TAG, jsonObject.toString());
                if (jsonObject.has("mmishake")) {
                    return jsonObject.getJSONObject("mmishake");
                }
            } catch (JSONException e) {
                MMLog.e(TAG, "Error parsing json", e);
            }
        }
        return null;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void deserializeFromObj(JSONObject handShakeObject) {
        Context context = this.contextRef.get();
        if (context == null) {
            context = this.appContextRef.get();
        }
        if (context == null) {
            MMLog.e(TAG, "No context for handshake");
            return;
        }
        if (handShakeObject != null) {
            try {
                JSONArray jsonArray = handShakeObject.optJSONArray("errors");
                if (jsonArray != null) {
                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject jsonObject = jsonArray.optJSONObject(i);
                        if (jsonObject != null) {
                            final String message = jsonObject.optString(ShareConstants.WEB_DIALOG_PARAM_MESSAGE, null);
                            String type = jsonObject.optString(UnityAdsConstants.UNITY_ADS_ANALYTICS_QUERYPARAM_EVENTTYPE_KEY, null);
                            if (message != null && type != null) {
                                if (type.equalsIgnoreCase("log")) {
                                    MMLog.e(TAG, message);
                                } else if (type.equalsIgnoreCase("prompt")) {
                                    final Context toastContext = context;
                                    this.handler.post(new Runnable() { // from class: com.millennialmedia.android.HandShake.3
                                        @Override // java.lang.Runnable
                                        public void run() {
                                            try {
                                                Toast.makeText(toastContext, "Error: " + message, 1).show();
                                            } catch (WindowManager.BadTokenException e) {
                                                MMLog.e(HandShake.TAG, "Error with toast token", e);
                                            }
                                        }
                                    });
                                }
                            }
                        }
                    }
                }
                JSONObject jsonObject2 = handShakeObject.optJSONObject("adtypes");
                if (jsonObject2 != null) {
                    String[] adTypes = MMAdImpl.getAdTypes();
                    for (int i2 = 0; i2 < adTypes.length; i2++) {
                        JSONObject adTypeObject = jsonObject2.optJSONObject(adTypes[i2]);
                        if (adTypeObject != null) {
                            AdTypeHandShake adTypeHandShake = new AdTypeHandShake();
                            adTypeHandShake.deserializeFromObj(adTypeObject);
                            adTypeHandShake.loadLastVideo(context, adTypes[i2]);
                            this.adTypeHandShakes.put(adTypes[i2], adTypeHandShake);
                        }
                    }
                }
                synchronized (this) {
                    JSONArray jsonArray2 = handShakeObject.optJSONArray("schemes");
                    if (jsonArray2 != null) {
                        if (this.schemes != null && this.schemes.size() > 0) {
                            this.schemes.removeAll(this.schemes);
                        }
                        for (int i3 = 0; i3 < jsonArray2.length(); i3++) {
                            JSONObject jsonObject3 = jsonArray2.optJSONObject(i3);
                            if (jsonObject3 != null) {
                                Scheme scheme = new Scheme();
                                scheme.deserializeFromObj(jsonObject3);
                                this.schemes.add(scheme);
                            }
                        }
                    }
                }
                this.adRefreshSecs = handShakeObject.optLong("adrefresh", 0L);
                this.deferredViewTimeout = handShakeObject.optLong("deferredviewtimeout", 3600L) * 1000;
                this.kill = handShakeObject.optBoolean("kill");
                setAdUrl(handShakeObject.optString("baseURL"));
                this.handShakeCallback = handShakeObject.optLong("handshakecallback", 86400L) * 1000;
                this.creativeCacheTimeout = handShakeObject.optLong("creativeCacheTimeout", 259200L) * 1000;
                this.hardwareAccelerationEnabled = handShakeObject.optBoolean("hardwareAccelerationEnabled");
                this.startSessionURL = handShakeObject.optString("startSessionURL");
                this.endSessionURL = handShakeObject.optString("endSessionURL");
                String gsonNuance = handShakeObject.optString("nuanceCredentials");
                this.nuanceCredentials = (NuanceCredentials) new Gson().fromJson(gsonNuance, NuanceCredentials.class);
                this.mmjsUrl = handShakeObject.optString("mmjs");
                handleCachedVideos(handShakeObject, context);
                if (!TextUtils.isEmpty(this.mmjsUrl) && !MRaid.isMRaidUpdated(context, this.mmjsUrl)) {
                    MRaid.downloadMraidJs(this.appContextRef.get(), this.mmjsUrl);
                } else {
                    MMLog.w(TAG, "Not downloading MMJS - (" + this.mmjsUrl + ")");
                }
            } catch (Exception e) {
                MMLog.e(TAG, "Error deserializing handshake", e);
            }
        }
    }

    private void handleCachedVideos(JSONObject handShakeObject, Context context) {
        JSONArray jsonArray = handShakeObject.optJSONArray("cachedVideos");
        if (jsonArray != null) {
            this.cachedVideos = (DTOCachedVideo[]) new Gson().fromJson(jsonArray.toString(), DTOCachedVideo[].class);
            MMLog.d(TAG, this.cachedVideos.toString());
        }
        this.noVideosToCacheURL = handShakeObject.optString("noVideosToCacheURL");
        if (this.cachedVideos != null) {
            PreCacheWorker.preCacheVideos(this.cachedVideos, context, this.noVideosToCacheURL);
        }
    }

    private boolean loadHandShake(Context context) {
        boolean settingsFound = false;
        SharedPreferences settings = context.getSharedPreferences("MillennialMediaSettings", 0);
        if (settings == null) {
            return false;
        }
        if (settings.contains("handshake_deferredviewtimeout")) {
            this.deferredViewTimeout = settings.getLong("handshake_deferredviewtimeout", this.deferredViewTimeout);
            settingsFound = true;
        }
        if (settings.contains("handshake_baseUrl")) {
            adUrl = settings.getString("handshake_baseUrl", adUrl);
            settingsFound = true;
        }
        if (settings.contains("handshake_callback")) {
            this.handShakeCallback = settings.getLong("handshake_callback", this.handShakeCallback);
            settingsFound = true;
        }
        if (settings.contains("handshake_hardwareAccelerationEnabled")) {
            this.hardwareAccelerationEnabled = settings.getBoolean("handshake_hardwareAccelerationEnabled", false);
            settingsFound = true;
        }
        if (settings.contains("handshake_startSessionURL")) {
            this.startSessionURL = settings.getString("handshake_startSessionURL", "");
            settingsFound = true;
        }
        if (settings.contains("handshake_endSessionURL")) {
            this.endSessionURL = settings.getString("handshake_endSessionURL", "");
            settingsFound = true;
        }
        if (settings.contains("handshake_nuanceCredentials")) {
            String gsonNuance = settings.getString("handshake_nuanceCredentials", "");
            this.nuanceCredentials = (NuanceCredentials) new Gson().fromJson(gsonNuance, NuanceCredentials.class);
            settingsFound = true;
        }
        if (settings.contains("handshake_mmdid")) {
            setMMdid(context, settings.getString("handshake_mmdid", this.mmdid), false);
            settingsFound = true;
        }
        if (settings.contains("handshake_creativecachetimeout")) {
            this.creativeCacheTimeout = settings.getLong("handshake_creativecachetimeout", this.creativeCacheTimeout);
            settingsFound = true;
        }
        if (settings.contains("handshake_mmjs")) {
            this.mmjsUrl = settings.getString("handshake_mmjs", this.mmjsUrl);
            settingsFound = true;
        }
        String[] adTypes = MMAdImpl.getAdTypes();
        for (int i = 0; i < adTypes.length; i++) {
            AdTypeHandShake adTypeHandShake = new AdTypeHandShake();
            if (adTypeHandShake.load(settings, adTypes[i])) {
                settingsFound = true;
                this.adTypeHandShakes.put(adTypes[i], adTypeHandShake);
            }
        }
        synchronized (this) {
            if (settings.contains("handshake_schemes")) {
                String schemesStr = settings.getString("handshake_schemes", "");
                if (schemesStr.length() > 0) {
                    String[] schemeStrings = schemesStr.split("\n");
                    for (String str : schemeStrings) {
                        String[] parts = str.split("\t");
                        if (parts.length >= 2) {
                            Scheme scheme = new Scheme(parts[0], Integer.parseInt(parts[1]));
                            this.schemes.add(scheme);
                        }
                    }
                    settingsFound = true;
                }
            }
        }
        if (settings.contains(KEY_CACHED_VIDEOS)) {
            String savedVideos = settings.getString(KEY_CACHED_VIDEOS, "");
            if (savedVideos.length() > 0) {
                this.cachedVideos = (DTOCachedVideo[]) new Gson().fromJson(savedVideos, DTOCachedVideo[].class);
            }
        }
        if (settings.contains("handshake_lasthandshake")) {
            this.lastHandShake = settings.getLong("handshake_lasthandshake", this.lastHandShake);
            settingsFound = true;
        }
        if (settingsFound) {
            MMLog.d(TAG, "Handshake successfully loaded from shared preferences.");
            if (System.currentTimeMillis() - this.lastHandShake < this.handShakeCallback) {
                this.handler.postDelayed(this.updateHandShakeRunnable, this.handShakeCallback - (System.currentTimeMillis() - this.lastHandShake));
            }
            this.noVideosToCacheURL = settings.getString("handshake_novideostocacheurl", "");
            if (this.cachedVideos != null) {
                PreCacheWorker.preCacheVideos(this.cachedVideos, context, this.noVideosToCacheURL);
            }
        }
        return settingsFound;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void saveHandShake(Context context) {
        SharedPreferences settings = context.getSharedPreferences("MillennialMediaSettings", 0);
        SharedPreferences.Editor editor = settings.edit();
        editor.putLong("handshake_deferredviewtimeout", this.deferredViewTimeout);
        editor.putBoolean("handshake_kill", this.kill);
        editor.putString("handshake_baseUrl", adUrl);
        editor.putLong("handshake_callback", this.handShakeCallback);
        editor.putBoolean("handshake_hardwareAccelerationEnabled", this.hardwareAccelerationEnabled);
        editor.putString("handshake_startSessionURL", this.startSessionURL);
        if (this.nuanceCredentials != null) {
            editor.putString("handshake_nuanceCredentials", new Gson().toJson(this.nuanceCredentials));
        }
        editor.putString("handshake_endSessionURL", this.endSessionURL);
        editor.putLong("handshake_creativecaetimeout", this.creativeCacheTimeout);
        editor.putString("handshake_mmjs", this.mmjsUrl);
        for (String adType : this.adTypeHandShakes.keySet()) {
            AdTypeHandShake adTypeHandShake = this.adTypeHandShakes.get(adType);
            adTypeHandShake.save(editor, adType);
        }
        synchronized (this) {
            StringBuilder stringBuilder = new StringBuilder();
            for (int i = 0; i < this.schemes.size(); i++) {
                Scheme scheme = this.schemes.get(i);
                if (i > 0) {
                    stringBuilder.append("\n");
                }
                stringBuilder.append(scheme.scheme + "\t" + scheme.id);
            }
            editor.putString("handshake_schemes", stringBuilder.toString());
        }
        if (this.cachedVideos != null) {
            editor.putString(KEY_CACHED_VIDEOS, new Gson().toJson(this.cachedVideos));
        }
        editor.putString("handshake_novideostocacheurl", this.noVideosToCacheURL);
        editor.putLong("handshake_lasthandshake", this.lastHandShake);
        editor.commit();
    }

    void startSession() {
        if (!TextUtils.isEmpty(this.startSessionURL)) {
            Utils.HttpUtils.executeUrl(this.startSessionURL);
        }
    }

    void endSession() {
        if (!TextUtils.isEmpty(this.endSessionURL)) {
            Utils.HttpUtils.executeUrl(this.endSessionURL);
        }
    }
}
