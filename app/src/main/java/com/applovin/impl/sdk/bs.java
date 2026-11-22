package com.applovin.impl.sdk;

import com.applovin.sdk.AppLovinAd;
import com.applovin.sdk.AppLovinAdLoadListener;
import com.applovin.sdk.AppLovinAdSize;
import com.applovin.sdk.AppLovinAdType;
import com.facebook.internal.AnalyticsEvents;
import com.mopub.common.AdType;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Collection;
import java.util.HashSet;
import java.util.Locale;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes.dex */
class bs extends ba implements cc {
    private static volatile int b = 0;
    private static volatile int c = 0;
    private final Collection a;
    private final JSONObject d;
    private final AppLovinAdLoadListener i;
    private final v j;
    private c k;

    bs(JSONObject jSONObject, AppLovinAdLoadListener appLovinAdLoadListener, AppLovinSdkImpl appLovinSdkImpl) {
        super("RenderAd", appLovinSdkImpl);
        this.k = new c(AppLovinAdSize.INTERSTITIAL, AppLovinAdType.REGULAR);
        this.d = jSONObject;
        this.i = appLovinAdLoadListener;
        this.a = e();
        this.j = appLovinSdkImpl.getFileManager();
    }

    private float a(String str, AppLovinAdType appLovinAdType, int i) {
        if (appLovinAdType.equals(AppLovinAdType.INCENTIVIZED)) {
            return 0.5f;
        }
        return (appLovinAdType.equals(AppLovinAdType.REGULAR) && str != null && i == -1) ? 0.5f : 0.0f;
    }

    private com.applovin.impl.adview.t a(int i) {
        return i == 1 ? com.applovin.impl.adview.t.WhiteXOnTransparentGrey : com.applovin.impl.adview.t.WhiteXOnOpaqueBlack;
    }

    private com.applovin.impl.adview.t a(String str) {
        return str != null ? com.applovin.impl.adview.t.WhiteXOnTransparentGrey : com.applovin.impl.adview.t.WhiteXOnOpaqueBlack;
    }

    private String a(String str, AppLovinAdType appLovinAdType) {
        boolean zEquals = AppLovinAdType.INCENTIVIZED.equals(appLovinAdType);
        String str2 = "alvideo" + (zEquals ? c : b) + (zEquals ? "a" : "") + str.substring(str.lastIndexOf(".") + 1, str.length());
        File fileA = this.j.a(str2, this.f.getApplicationContext(), false);
        this.f.getFileManager().a(fileA);
        if (!a(fileA, str)) {
            return null;
        }
        if (zEquals) {
            c = (c + 1) % 4;
            return str2;
        }
        b = (b + 1) % 4;
        return str2;
    }

    private String a(String str, String str2) {
        File fileA = this.j.a(str2.replace("/", "_"), this.f.getApplicationContext(), true);
        if (fileA == null) {
            return null;
        }
        if (fileA.exists()) {
            this.g.d(this.e, "Loaded " + str2 + " from cache: file://" + fileA.getAbsolutePath());
            return "file://" + fileA.getAbsolutePath();
        }
        if (a(fileA, str + str2)) {
            return "file://" + fileA.getAbsolutePath();
        }
        return null;
    }

    private void a(JSONObject jSONObject) throws JSONException {
        int i;
        float fA;
        com.applovin.impl.adview.t tVarA;
        String string;
        String string2;
        int i2 = 0;
        String string3 = jSONObject.getString(AdType.HTML);
        AppLovinAdSize appLovinAdSizeFromString = jSONObject.has("size") ? AppLovinAdSize.fromString(jSONObject.getString("size")) : AppLovinAdSize.BANNER;
        String strA = null;
        if (string3 == null || string3.length() <= 0) {
            this.g.e(this.e, "No HTML received for requested ad");
            d();
            return;
        }
        String strB = b(string3);
        AppLovinAdInternal.AdTarget adTargetValueOf = jSONObject.has("ad_target") ? AppLovinAdInternal.AdTarget.valueOf(jSONObject.getString("ad_target").toUpperCase(Locale.ENGLISH)) : AppLovinAdInternal.AdTarget.DEFAULT;
        AppLovinAdType appLovinAdTypeFromString = jSONObject.has("ad_type") ? AppLovinAdType.fromString(jSONObject.getString("ad_type").toUpperCase(Locale.ENGLISH)) : AppLovinAdType.REGULAR;
        this.k = new c(appLovinAdSizeFromString, appLovinAdTypeFromString);
        if (jSONObject.has(AnalyticsEvents.PARAMETER_SHARE_DIALOG_CONTENT_VIDEO) && (string2 = jSONObject.getString(AnalyticsEvents.PARAMETER_SHARE_DIALOG_CONTENT_VIDEO)) != null && !string2.isEmpty()) {
            strA = a(string2, appLovinAdTypeFromString);
        }
        long j = jSONObject.has("ad_id") ? jSONObject.getLong("ad_id") : -1L;
        if (jSONObject.has("countdown_length")) {
            try {
                i = jSONObject.getInt("countdown_length");
            } catch (JSONException e) {
                i = 0;
            }
        } else {
            i = 0;
        }
        if (jSONObject.has("close_delay")) {
            try {
                i2 = jSONObject.getInt("close_delay");
            } catch (JSONException e2) {
            }
        }
        if (jSONObject.has("close_delay_graphic")) {
            try {
                fA = jSONObject.getInt("close_delay_graphic");
            } catch (JSONException e3) {
                fA = a(strA, appLovinAdTypeFromString, i2);
            }
        } else {
            fA = a(strA, appLovinAdTypeFromString, i2);
        }
        if (jSONObject.has("close_style")) {
            try {
                tVarA = a(jSONObject.getInt("close_style"));
            } catch (JSONException e4) {
                tVarA = a(strA);
            }
        } else {
            tVarA = a(strA);
        }
        if (jSONObject.has("clcodes")) {
            try {
                string = ((JSONArray) jSONObject.get("clcodes")).getString(0);
            } catch (JSONException e5) {
                string = "";
            }
        } else {
            string = "";
        }
        a(new AppLovinAdInternal.Builder().setHtml(strB).setSize(appLovinAdSizeFromString).setType(appLovinAdTypeFromString).setVideoFilename(strA).setTarget(adTargetValueOf).setCloseStyle(tVarA).setVideoCloseDelay(i2).setPoststitialCloseDelay(fA).setCountdownLength(i).setCurrentAdIdNumber(j).setClCode(string).create());
    }

    /* JADX WARN: Code duplicated, block: B:102:0x011c A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:104:0x0117 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:116:0x0121 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:127:? A[SYNTHETIC] */
    private boolean a(File file, String str) throws Throwable {
        HttpURLConnection httpURLConnection;
        ByteArrayOutputStream byteArrayOutputStream;
        ByteArrayOutputStream byteArrayOutputStream2;
        InputStream inputStream = null;
        this.g.d(this.e, "Starting caching of " + str + " into " + file.getAbsoluteFile());
        try {
            byteArrayOutputStream = new ByteArrayOutputStream();
            try {
                HttpURLConnection httpURLConnection2 = (HttpURLConnection) new URL(str).openConnection();
                try {
                    httpURLConnection2.setConnectTimeout(((Integer) this.f.a(bb.t)).intValue());
                    httpURLConnection2.setReadTimeout(((Integer) this.f.a(bb.v)).intValue());
                    httpURLConnection2.setDefaultUseCaches(true);
                    httpURLConnection2.setUseCaches(true);
                    httpURLConnection2.setAllowUserInteraction(false);
                    httpURLConnection2.setInstanceFollowRedirects(true);
                    inputStream = httpURLConnection2.getInputStream();
                    byte[] bArr = new byte[1024];
                    while (true) {
                        int i = inputStream.read(bArr, 0, bArr.length);
                        if (i < 0) {
                            break;
                        }
                        try {
                            byteArrayOutputStream.write(bArr, 0, i);
                        } catch (Exception e) {
                            try {
                                byteArrayOutputStream.close();
                            } catch (Exception e2) {
                            }
                            d();
                            if (inputStream != null) {
                                try {
                                    inputStream.close();
                                } catch (Exception e3) {
                                }
                            }
                            if (byteArrayOutputStream != null) {
                                try {
                                    byteArrayOutputStream.close();
                                } catch (Exception e4) {
                                }
                            }
                            if (httpURLConnection2 != null) {
                                try {
                                    httpURLConnection2.disconnect();
                                } catch (Exception e5) {
                                }
                            }
                            return false;
                        }
                    }
                    if (!this.j.a(byteArrayOutputStream, file)) {
                        d();
                    }
                    this.g.d(this.e, "Caching completed for " + file);
                    if (inputStream != null) {
                        try {
                            inputStream.close();
                        } catch (Exception e6) {
                        }
                    }
                    if (byteArrayOutputStream != null) {
                        try {
                            byteArrayOutputStream.close();
                        } catch (Exception e7) {
                        }
                    }
                    if (httpURLConnection2 != null) {
                        try {
                            httpURLConnection2.disconnect();
                        } catch (Exception e8) {
                        }
                    }
                    return true;
                } catch (IOException e9) {
                    byteArrayOutputStream2 = byteArrayOutputStream;
                    e = e9;
                    httpURLConnection = httpURLConnection2;
                    try {
                        this.g.e(this.e, "Failed to cache \"" + str + "\" into \"" + file.getAbsolutePath() + "\"", e);
                        if (inputStream != null) {
                            try {
                                inputStream.close();
                            } catch (Exception e10) {
                            }
                        }
                        if (byteArrayOutputStream2 != null) {
                            try {
                                byteArrayOutputStream2.close();
                            } catch (Exception e11) {
                            }
                        }
                        if (httpURLConnection != null) {
                            try {
                                httpURLConnection.disconnect();
                            } catch (Exception e12) {
                            }
                        }
                        return false;
                    } catch (Throwable th) {
                        th = th;
                        byteArrayOutputStream = byteArrayOutputStream2;
                        if (inputStream != null) {
                            try {
                                inputStream.close();
                            } catch (Exception e13) {
                            }
                        }
                        if (byteArrayOutputStream != null) {
                            try {
                                byteArrayOutputStream.close();
                            } catch (Exception e14) {
                            }
                        }
                        if (httpURLConnection != null) {
                            throw th;
                        }
                        try {
                            httpURLConnection.disconnect();
                            throw th;
                        } catch (Exception e15) {
                            throw th;
                        }
                    }
                } catch (Throwable th2) {
                    httpURLConnection = httpURLConnection2;
                    th = th2;
                    if (inputStream != null) {
                        inputStream.close();
                    }
                    if (byteArrayOutputStream != null) {
                        byteArrayOutputStream.close();
                    }
                    if (httpURLConnection != null) {
                        throw th;
                    }
                    httpURLConnection.disconnect();
                    throw th;
                }
            } catch (IOException e16) {
                e = e16;
                httpURLConnection = null;
                byteArrayOutputStream2 = byteArrayOutputStream;
            } catch (Throwable th3) {
                th = th3;
                httpURLConnection = null;
            }
        } catch (IOException e17) {
            e = e17;
            httpURLConnection = null;
            byteArrayOutputStream2 = null;
        } catch (Throwable th4) {
            th = th4;
            httpURLConnection = null;
            byteArrayOutputStream = null;
        }
    }

    private String b(String str) {
        return ((Boolean) this.f.a(bb.H)).booleanValue() ? c(str) : str;
    }

    private String c(String str) {
        StringBuilder sb = new StringBuilder(str);
        for (String str2 : ((String) this.f.a(bb.I)).split(",")) {
            int i = 0;
            int iIndexOf = 0;
            while (iIndexOf < sb.length() && (iIndexOf = sb.indexOf(str2, i)) != -1) {
                int length = sb.length();
                i = iIndexOf;
                while (!this.a.contains(Character.valueOf(sb.charAt(i))) && i < length) {
                    i++;
                }
                if (i <= iIndexOf || i == length) {
                    this.g.d(this.e, "Unable to cache resource; ad HTML is invalid.");
                } else {
                    String strA = a(str2, sb.substring(str2.length() + iIndexOf, i));
                    if (strA != null) {
                        sb.replace(iIndexOf, i, strA);
                    }
                }
            }
        }
        return sb.toString();
    }

    private Collection e() {
        HashSet hashSet = new HashSet();
        for (char c2 : ((String) this.f.a(bb.ap)).toCharArray()) {
            hashSet.add(Character.valueOf(c2));
        }
        hashSet.add('\"');
        return hashSet;
    }

    void a(AppLovinAd appLovinAd) {
        if (this.i != null) {
            this.i.adReceived(appLovinAd);
        }
    }

    void d() {
        try {
            if (this.i != null) {
                if (this.i instanceof u) {
                    ((u) this.i).a(this.k, -6);
                } else {
                    this.i.failedToReceiveAd(-6);
                }
            }
        } catch (Throwable th) {
            this.g.e(this.e, "Unable process a failure to receive an ad", th);
        }
    }

    @Override // com.applovin.impl.sdk.cc
    public String f() {
        return "tRA";
    }

    @Override // java.lang.Runnable
    public void run() {
        this.g.d(this.e, "Rendering ad...");
        try {
            a(this.d);
        } catch (IllegalArgumentException e) {
            this.g.e(this.e, "Ad response is not valid", e);
            d();
        } catch (JSONException e2) {
            this.g.e(this.e, "Unable to parse ad service response", e2);
            d();
        } catch (Exception e3) {
            this.g.e(this.e, "Unable to render ad", e3);
            d();
        }
    }
}
