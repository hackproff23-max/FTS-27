package com.applovin.impl.sdk;

import android.content.Context;
import android.content.SharedPreferences;
import com.applovin.sdk.AppLovinAdType;
import com.applovin.sdk.AppLovinLogger;
import com.applovin.sdk.AppLovinSdkSettings;
import java.util.Arrays;
import java.util.Iterator;
import java.util.Map;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes.dex */
class be {
    private final AppLovinSdkImpl a;
    private final AppLovinLogger b;
    private final Context c;
    private final Object[] d = new Object[bb.b()];

    be(AppLovinSdkImpl appLovinSdkImpl) {
        this.a = appLovinSdkImpl;
        this.b = appLovinSdkImpl.getLogger();
        this.c = appLovinSdkImpl.getApplicationContext();
    }

    private static bd a(String str) {
        for (bd bdVar : bb.a()) {
            if (bdVar.b().equals(str)) {
                return bdVar;
            }
        }
        return null;
    }

    private static Object a(String str, JSONObject jSONObject, Object obj) {
        if (obj instanceof Boolean) {
            return Boolean.valueOf(jSONObject.getBoolean(str));
        }
        if (obj instanceof Float) {
            return Float.valueOf((float) jSONObject.getDouble(str));
        }
        if (obj instanceof Integer) {
            return Integer.valueOf(jSONObject.getInt(str));
        }
        if (obj instanceof Long) {
            return Long.valueOf(jSONObject.getLong(str));
        }
        if (obj instanceof String) {
            return jSONObject.getString(str);
        }
        throw new RuntimeException("SDK Error: unknown value type: " + obj.getClass());
    }

    private String e() {
        return "com.applovin.sdk." + cd.a(this.a.getSdkKey()) + ".";
    }

    public SharedPreferences a() {
        if (this.c == null) {
            throw new IllegalArgumentException("No context specified");
        }
        return this.c.getSharedPreferences("com.applovin.sdk.1", 0);
    }

    public Object a(bd bdVar) {
        Object objA;
        if (bdVar == null) {
            throw new IllegalArgumentException("No setting type specified");
        }
        synchronized (this.d) {
            Object obj = this.d[bdVar.a()];
            objA = obj != null ? bdVar.a(obj) : bdVar.c();
        }
        return objA;
    }

    public void a(bd bdVar, Object obj) {
        if (bdVar == null) {
            throw new IllegalArgumentException("No setting type specified");
        }
        if (obj == null) {
            throw new IllegalArgumentException("No new value specified");
        }
        synchronized (this.d) {
            this.d[bdVar.a()] = obj;
        }
        this.b.d("SettingsManager", "Setting update: " + bdVar.b() + " set to \"" + obj + "\"");
    }

    void a(AppLovinSdkSettings appLovinSdkSettings) {
        boolean z;
        boolean z2 = false;
        this.b.i("SettingsManager", "Loading user-defined settings...");
        if (appLovinSdkSettings == null) {
            return;
        }
        synchronized (this.d) {
            this.d[bb.j.a()] = Boolean.valueOf(appLovinSdkSettings.isVerboseLoggingEnabled());
            long bannerAdRefreshSeconds = appLovinSdkSettings.getBannerAdRefreshSeconds();
            if (bannerAdRefreshSeconds >= 0) {
                this.d[bb.A.a()] = Long.valueOf(bannerAdRefreshSeconds > 0 ? Math.max(30L, bannerAdRefreshSeconds) : 0L);
                this.d[bb.z.a()] = true;
            } else if (bannerAdRefreshSeconds == -1) {
                this.d[bb.z.a()] = false;
            }
            String autoPreloadSizes = appLovinSdkSettings.getAutoPreloadSizes();
            if (autoPreloadSizes == null) {
                autoPreloadSizes = "NONE";
            }
            Object[] objArr = this.d;
            int iA = bb.J.a();
            if (autoPreloadSizes.equals("NONE")) {
                autoPreloadSizes = "";
            }
            objArr[iA] = autoPreloadSizes;
            String autoPreloadTypes = appLovinSdkSettings.getAutoPreloadTypes();
            if (autoPreloadTypes == null) {
                autoPreloadTypes = "NONE";
            }
            if (autoPreloadTypes.equals("NONE")) {
                z = false;
            } else {
                String[] strArrSplit = autoPreloadTypes.split(",");
                z = false;
                for (String str : strArrSplit) {
                    if (str.equals(AppLovinAdType.REGULAR.getLabel())) {
                        z = true;
                    } else if (str.equals(AppLovinAdType.INCENTIVIZED.getLabel()) || str.contains("INCENT") || str.contains("REWARD")) {
                        z2 = true;
                    }
                }
            }
            if (!z) {
                this.d[bb.J.a()] = "";
            }
            this.d[bb.K.a()] = Boolean.valueOf(z2);
            if (appLovinSdkSettings instanceof at) {
                for (Map.Entry entry : ((at) appLovinSdkSettings).b().entrySet()) {
                    this.d[((bd) entry.getKey()).a()] = entry.getValue();
                }
            }
        }
    }

    void a(JSONObject jSONObject) {
        this.b.d("SettingsManager", "Loading settings from JSON array...");
        synchronized (this.d) {
            try {
                Iterator<String> itKeys = jSONObject.keys();
                while (itKeys.hasNext()) {
                    String next = itKeys.next();
                    if (next != null && next.length() > 0) {
                        try {
                            try {
                                bd bdVarA = a(next);
                                if (bdVarA != null) {
                                    Object objA = a(next, jSONObject, bdVarA.c());
                                    this.d[bdVarA.a()] = objA;
                                    this.b.d("SettingsManager", "Setting update: " + bdVarA.b() + " set to \"" + objA + "\"");
                                } else {
                                    this.b.w("SettingsManager", "Unknown setting recieved: " + next);
                                }
                            } catch (Throwable th) {
                                this.b.e("SettingsManager", "Unable to convert setting object ", th);
                            }
                        } catch (JSONException e) {
                            this.b.e("SettingsManager", "Unable to parse JSON settings array", e);
                        }
                    }
                }
            } catch (Throwable th2) {
                throw th2;
            }
        }
    }

    void b() {
        if (this.c == null) {
            throw new IllegalArgumentException("No context specified");
        }
        this.b.i("SettingsManager", "Saving settings with the application...");
        String strE = e();
        SharedPreferences.Editor editorEdit = a().edit();
        synchronized (this.d) {
            for (bd bdVar : bb.a()) {
                Object obj = this.d[bdVar.a()];
                if (obj != null) {
                    String str = strE + bdVar.b();
                    if (obj instanceof Boolean) {
                        editorEdit.putBoolean(str, ((Boolean) obj).booleanValue());
                    } else if (obj instanceof Float) {
                        editorEdit.putFloat(str, ((Float) obj).floatValue());
                    } else if (obj instanceof Integer) {
                        editorEdit.putInt(str, ((Integer) obj).intValue());
                    } else if (obj instanceof Long) {
                        editorEdit.putLong(str, ((Long) obj).longValue());
                    } else {
                        if (!(obj instanceof String)) {
                            throw new RuntimeException("SDK Error: unknown value: " + obj.getClass());
                        }
                        editorEdit.putString(str, (String) obj);
                    }
                }
            }
        }
        editorEdit.commit();
        this.b.d("SettingsManager", "Settings saved with the application.");
    }

    void c() {
        Object objValueOf;
        if (this.c == null) {
            throw new IllegalArgumentException("No context specified");
        }
        this.b.i("SettingsManager", "Loading settings saved with the application...");
        String strE = e();
        SharedPreferences sharedPreferencesA = a();
        synchronized (this.d) {
            for (bd bdVar : bb.a()) {
                try {
                    String str = strE + bdVar.b();
                    Object objC = bdVar.c();
                    if (objC instanceof Boolean) {
                        objValueOf = Boolean.valueOf(sharedPreferencesA.getBoolean(str, ((Boolean) objC).booleanValue()));
                    } else if (objC instanceof Float) {
                        objValueOf = Float.valueOf(sharedPreferencesA.getFloat(str, ((Float) objC).floatValue()));
                    } else if (objC instanceof Integer) {
                        objValueOf = Integer.valueOf(sharedPreferencesA.getInt(str, ((Integer) objC).intValue()));
                    } else if (objC instanceof Long) {
                        objValueOf = Long.valueOf(sharedPreferencesA.getLong(str, ((Long) objC).longValue()));
                    } else {
                        if (!(objC instanceof String)) {
                            throw new RuntimeException("SDK Error: unknown value: " + objC.getClass());
                        }
                        objValueOf = sharedPreferencesA.getString(str, (String) objC);
                    }
                    this.d[bdVar.a()] = objValueOf;
                } catch (Exception e) {
                    this.b.e("SettingsManager", "Unable to load \"" + bdVar.b() + "\"", e);
                }
            }
        }
    }

    void d() {
        synchronized (this.d) {
            Arrays.fill(this.d, (Object) null);
        }
        SharedPreferences.Editor editorEdit = a().edit();
        editorEdit.clear();
        editorEdit.commit();
    }
}
