package com.google.android.gms.analytics;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.text.TextUtils;
import com.facebook.appevents.AppEventsConstants;
import com.google.android.gms.internal.jx;
import com.google.android.gms.internal.ld;
import com.google.android.gms.internal.lf;
import com.mopub.nativeads.MoPubNativeAdPositioning;
import com.unity3d.ads.android.properties.UnityAdsConstants;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.Random;

/* JADX INFO: loaded from: classes.dex */
public class Tracker {
    private final TrackerHandler Ce;
    private final Map<String, String> Cf;
    private ah Cg;
    private final k Ch;
    private final ai Ci;
    private final g Cj;
    private boolean Ck;
    private a Cl;
    private am Cm;
    private ExceptionReporter Cn;
    private Context mContext;
    private final Map<String, String> rd;

    private class a implements GoogleAnalytics.a {
        private long Cs;
        private boolean Co = false;
        private int Cp = 0;
        private long Cq = -1;
        private boolean Cr = false;
        private ld wb = lf.m6if();

        public a() {
        }

        private void fq() {
            GoogleAnalytics googleAnalyticsEY = GoogleAnalytics.eY();
            if (googleAnalyticsEY == null) {
                ae.T("GoogleAnalytics isn't initialized for the Tracker!");
            } else if (this.Cq >= 0 || this.Co) {
                googleAnalyticsEY.a(Tracker.this.Cl);
            } else {
                googleAnalyticsEY.b(Tracker.this.Cl);
            }
        }

        public void enableAutoActivityTracking(boolean enabled) {
            this.Co = enabled;
            fq();
        }

        public long fn() {
            return this.Cq;
        }

        public boolean fo() {
            return this.Co;
        }

        public boolean fp() {
            boolean z = this.Cr;
            this.Cr = false;
            return z;
        }

        boolean fr() {
            return this.wb.elapsedRealtime() >= this.Cs + Math.max(1000L, this.Cq);
        }

        @Override // com.google.android.gms.analytics.GoogleAnalytics.a
        public void i(Activity activity) {
            y.eK().a(y.a.EASY_TRACKER_ACTIVITY_START);
            if (this.Cp == 0 && fr()) {
                this.Cr = true;
            }
            this.Cp++;
            if (this.Co) {
                Intent intent = activity.getIntent();
                if (intent != null) {
                    Tracker.this.setCampaignParamsOnNextHit(intent.getData());
                }
                HashMap map = new HashMap();
                map.put("&t", "screenview");
                y.eK().D(true);
                Tracker.this.set("&cd", Tracker.this.Cm != null ? Tracker.this.Cm.k(activity) : activity.getClass().getCanonicalName());
                Tracker.this.send(map);
                y.eK().D(false);
            }
        }

        @Override // com.google.android.gms.analytics.GoogleAnalytics.a
        public void j(Activity activity) {
            y.eK().a(y.a.EASY_TRACKER_ACTIVITY_STOP);
            this.Cp--;
            this.Cp = Math.max(0, this.Cp);
            if (this.Cp == 0) {
                this.Cs = this.wb.elapsedRealtime();
            }
        }

        public void setSessionTimeout(long sessionTimeout) {
            this.Cq = sessionTimeout;
            fq();
        }
    }

    Tracker(String trackingId, TrackerHandler handler, Context context) {
        this(trackingId, handler, k.el(), ai.fl(), g.dZ(), new ad("tracking"), context);
    }

    Tracker(String trackingId, TrackerHandler handler, k clientIdDefaultProvider, ai screenResolutionDefaultProvider, g appFieldsDefaultProvider, ah rateLimiter, Context context) {
        this.rd = new HashMap();
        this.Cf = new HashMap();
        this.Ce = handler;
        if (context != null) {
            this.mContext = context.getApplicationContext();
        }
        if (trackingId != null) {
            this.rd.put("&tid", trackingId);
        }
        this.rd.put("useSecure", AppEventsConstants.EVENT_PARAM_VALUE_YES);
        this.Ch = clientIdDefaultProvider;
        this.Ci = screenResolutionDefaultProvider;
        this.Cj = appFieldsDefaultProvider;
        this.rd.put("&a", Integer.toString(new Random().nextInt(MoPubNativeAdPositioning.MoPubClientPositioning.NO_REPEAT) + 1));
        this.Cg = rateLimiter;
        this.Cl = new a();
        enableAdvertisingIdCollection(false);
    }

    void a(am amVar) {
        ae.V("Loading Tracker config values.");
        this.Cm = amVar;
        if (this.Cm.ft()) {
            String strFu = this.Cm.fu();
            set("&tid", strFu);
            ae.V("[Tracker] trackingId loaded: " + strFu);
        }
        if (this.Cm.fv()) {
            String string = Double.toString(this.Cm.fw());
            set("&sf", string);
            ae.V("[Tracker] sample frequency loaded: " + string);
        }
        if (this.Cm.fx()) {
            setSessionTimeout(this.Cm.getSessionTimeout());
            ae.V("[Tracker] session timeout loaded: " + fn());
        }
        if (this.Cm.fy()) {
            enableAutoActivityTracking(this.Cm.fz());
            ae.V("[Tracker] auto activity tracking loaded: " + fo());
        }
        if (this.Cm.fA()) {
            if (this.Cm.fB()) {
                set("&aip", AppEventsConstants.EVENT_PARAM_VALUE_YES);
                ae.V("[Tracker] anonymize ip loaded: true");
            }
            ae.V("[Tracker] anonymize ip loaded: false");
        }
        enableExceptionReporting(this.Cm.fC());
    }

    public void enableAdvertisingIdCollection(boolean enabled) {
        if (!enabled) {
            this.rd.put("&ate", null);
            this.rd.put("&adid", null);
            return;
        }
        if (this.rd.containsKey("&ate")) {
            this.rd.remove("&ate");
        }
        if (this.rd.containsKey("&adid")) {
            this.rd.remove("&adid");
        }
    }

    public void enableAutoActivityTracking(boolean enabled) {
        this.Cl.enableAutoActivityTracking(enabled);
    }

    public void enableExceptionReporting(boolean enabled) {
        if (this.Ck == enabled) {
            return;
        }
        this.Ck = enabled;
        if (enabled) {
            this.Cn = new ExceptionReporter(this, Thread.getDefaultUncaughtExceptionHandler(), this.mContext);
            Thread.setDefaultUncaughtExceptionHandler(this.Cn);
            ae.V("Uncaught exceptions will be reported to Google Analytics.");
        } else {
            if (this.Cn != null) {
                Thread.setDefaultUncaughtExceptionHandler(this.Cn.et());
            } else {
                Thread.setDefaultUncaughtExceptionHandler(null);
            }
            ae.V("Uncaught exceptions will not be reported to Google Analytics.");
        }
    }

    long fn() {
        return this.Cl.fn();
    }

    boolean fo() {
        return this.Cl.fo();
    }

    public String get(String key) {
        y.eK().a(y.a.GET);
        if (TextUtils.isEmpty(key)) {
            return null;
        }
        if (this.rd.containsKey(key)) {
            return this.rd.get(key);
        }
        if (key.equals("&ul")) {
            return an.a(Locale.getDefault());
        }
        if (this.Ch != null && this.Ch.ac(key)) {
            return this.Ch.getValue(key);
        }
        if (this.Ci != null && this.Ci.ac(key)) {
            return this.Ci.getValue(key);
        }
        if (this.Cj == null || !this.Cj.ac(key)) {
            return null;
        }
        return this.Cj.getValue(key);
    }

    public void send(Map<String, String> params) {
        y.eK().a(y.a.SEND);
        HashMap map = new HashMap();
        map.putAll(this.rd);
        if (params != null) {
            map.putAll(params);
        }
        for (String str : this.Cf.keySet()) {
            if (!map.containsKey(str)) {
                map.put(str, this.Cf.get(str));
            }
        }
        this.Cf.clear();
        if (TextUtils.isEmpty((CharSequence) map.get("&tid"))) {
            ae.W(String.format("Missing tracking id (%s) parameter.", "&tid"));
        }
        String str2 = (String) map.get("&t");
        if (TextUtils.isEmpty(str2)) {
            ae.W(String.format("Missing hit type (%s) parameter.", "&t"));
            str2 = "";
        }
        if (this.Cl.fp()) {
            map.put("&sc", "start");
        }
        String lowerCase = str2.toLowerCase();
        if ("screenview".equals(lowerCase) || "pageview".equals(lowerCase) || "appview".equals(lowerCase) || TextUtils.isEmpty(lowerCase)) {
            int i = Integer.parseInt(this.rd.get("&a")) + 1;
            if (i >= Integer.MAX_VALUE) {
                i = 1;
            }
            this.rd.put("&a", Integer.toString(i));
        }
        if (lowerCase.equals("transaction") || lowerCase.equals(UnityAdsConstants.UNITY_ADS_REWARD_ITEM_KEY) || this.Cg.fe()) {
            this.Ce.u(map);
        } else {
            ae.W("Too many hits sent too quickly, rate limiting invoked.");
        }
    }

    public void set(String key, String value) {
        jx.b(key, (Object) "Key should be non-null");
        y.eK().a(y.a.SET);
        this.rd.put(key, value);
    }

    public void setAnonymizeIp(boolean anonymize) {
        set("&aip", an.E(anonymize));
    }

    public void setAppId(String appId) {
        set("&aid", appId);
    }

    public void setAppInstallerId(String appInstallerId) {
        set("&aiid", appInstallerId);
    }

    public void setAppName(String appName) {
        set("&an", appName);
    }

    public void setAppVersion(String appVersion) {
        set("&av", appVersion);
    }

    public void setCampaignParamsOnNextHit(Uri uri) {
        if (uri == null) {
            return;
        }
        String queryParameter = uri.getQueryParameter("referrer");
        if (TextUtils.isEmpty(queryParameter)) {
            return;
        }
        Uri uri2 = Uri.parse("http://hostname/?" + queryParameter);
        String queryParameter2 = uri2.getQueryParameter("utm_id");
        if (queryParameter2 != null) {
            this.Cf.put("&ci", queryParameter2);
        }
        String queryParameter3 = uri2.getQueryParameter("utm_campaign");
        if (queryParameter3 != null) {
            this.Cf.put("&cn", queryParameter3);
        }
        String queryParameter4 = uri2.getQueryParameter("utm_content");
        if (queryParameter4 != null) {
            this.Cf.put("&cc", queryParameter4);
        }
        String queryParameter5 = uri2.getQueryParameter("utm_medium");
        if (queryParameter5 != null) {
            this.Cf.put("&cm", queryParameter5);
        }
        String queryParameter6 = uri2.getQueryParameter("utm_source");
        if (queryParameter6 != null) {
            this.Cf.put("&cs", queryParameter6);
        }
        String queryParameter7 = uri2.getQueryParameter("utm_term");
        if (queryParameter7 != null) {
            this.Cf.put("&ck", queryParameter7);
        }
        String queryParameter8 = uri2.getQueryParameter("dclid");
        if (queryParameter8 != null) {
            this.Cf.put("&dclid", queryParameter8);
        }
        String queryParameter9 = uri2.getQueryParameter("gclid");
        if (queryParameter9 != null) {
            this.Cf.put("&gclid", queryParameter9);
        }
    }

    public void setClientId(String clientId) {
        set("&cid", clientId);
    }

    public void setEncoding(String encoding) {
        set("&de", encoding);
    }

    public void setHostname(String hostname) {
        set("&dh", hostname);
    }

    public void setLanguage(String language) {
        set("&ul", language);
    }

    public void setLocation(String location) {
        set("&dl", location);
    }

    public void setPage(String page) {
        set("&dp", page);
    }

    public void setReferrer(String referrer) {
        set("&dr", referrer);
    }

    public void setSampleRate(double sampleRate) {
        set("&sf", Double.toHexString(sampleRate));
    }

    public void setScreenColors(String screenColors) {
        set("&sd", screenColors);
    }

    public void setScreenName(String screenName) {
        set("&cd", screenName);
    }

    public void setScreenResolution(int width, int height) {
        if (width >= 0 || height >= 0) {
            set("&sr", width + "x" + height);
        } else {
            ae.W("Invalid width or height. The values should be non-negative.");
        }
    }

    public void setSessionTimeout(long sessionTimeout) {
        this.Cl.setSessionTimeout(1000 * sessionTimeout);
    }

    public void setTitle(String title) {
        set("&dt", title);
    }

    public void setUseSecure(boolean useSecure) {
        set("useSecure", an.E(useSecure));
    }

    public void setViewportSize(String viewportSize) {
        set("&vp", viewportSize);
    }
}
