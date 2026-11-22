package com.applovin.impl.sdk;

import android.content.Context;
import android.os.Build;
import android.util.Log;
import com.applovin.sdk.AppLovinAdService;
import com.applovin.sdk.AppLovinLogger;
import com.applovin.sdk.AppLovinSdk;
import com.applovin.sdk.AppLovinSdkSettings;
import com.applovin.sdk.AppLovinTargetingData;
import java.io.PrintWriter;
import java.io.StringWriter;

/* JADX INFO: loaded from: classes.dex */
public class AppLovinSdkImpl extends AppLovinSdk {
    public static final String FULL_VERSION = "5.4.3";
    private String a;
    private AppLovinSdkSettings b;
    private Context c;
    private AppLovinLogger d;
    private bn e;
    private be f;
    private n g;
    private bg h;
    private v i;
    private l j;
    private e k;
    private boolean l = true;
    private boolean m = false;
    private boolean n = false;
    private boolean o = false;
    private boolean p = false;

    private static boolean g() {
        return (Build.VERSION.RELEASE.startsWith("1.") || Build.VERSION.RELEASE.startsWith("2.0") || Build.VERSION.RELEASE.startsWith("2.1")) ? false : true;
    }

    bn a() {
        return this.e;
    }

    Object a(bd bdVar) {
        return this.f.a(bdVar);
    }

    void a(boolean z) {
        this.l = false;
        this.m = z;
        this.n = true;
    }

    bg b() {
        return this.h;
    }

    boolean c() {
        return this.l;
    }

    boolean d() {
        return this.n;
    }

    void e() {
        this.l = true;
        this.e.a(new bm(this), 0L);
    }

    void f() {
        this.f.d();
        this.f.b();
        this.h.a();
    }

    @Override // com.applovin.sdk.AppLovinSdk
    public AppLovinAdService getAdService() {
        return this.k;
    }

    @Override // com.applovin.sdk.AppLovinSdk
    public Context getApplicationContext() {
        return this.c;
    }

    public n getConnectionManager() {
        return this.g;
    }

    public v getFileManager() {
        return this.i;
    }

    @Override // com.applovin.sdk.AppLovinSdk
    public AppLovinLogger getLogger() {
        return this.d;
    }

    @Override // com.applovin.sdk.AppLovinSdk
    public String getSdkKey() {
        return this.a;
    }

    @Override // com.applovin.sdk.AppLovinSdk
    public AppLovinSdkSettings getSettings() {
        return this.b;
    }

    public be getSettingsManager() {
        return this.f;
    }

    @Override // com.applovin.sdk.AppLovinSdk
    public AppLovinTargetingData getTargetingData() {
        return this.j;
    }

    @Override // com.applovin.sdk.AppLovinSdk
    public boolean hasCriticalErrors() {
        return this.o || this.p;
    }

    @Override // com.applovin.sdk.AppLovinSdk
    protected void initialize(String str, AppLovinSdkSettings appLovinSdkSettings, Context context) {
        this.a = str;
        this.b = appLovinSdkSettings;
        this.c = context;
        try {
            j jVar = new j();
            this.d = jVar;
            this.f = new be(this);
            this.e = new bn(this);
            this.g = new n(this);
            this.h = new bg(this);
            this.i = new v(this);
            this.k = new e(this);
            this.j = new l(this);
            if (!g()) {
                this.o = true;
                Log.e(AppLovinLogger.SDK_TAG, "Unable to initalize AppLovin SDK: Android SDK version has to be at least level 8");
            }
            if (str == null || str.length() < 1) {
                this.p = true;
                Log.e(AppLovinLogger.SDK_TAG, "Unable to find AppLovin SDK key. Please add     meta-data android:name=\"applovin.sdk.key\" android:value=\"YOUR_SDK_KEY_HERE\" into AndroidManifest.xml.");
                StringWriter stringWriter = new StringWriter();
                new Throwable("").printStackTrace(new PrintWriter(stringWriter));
                Log.e(AppLovinLogger.SDK_TAG, "Called with an invalid SDK key from: " + stringWriter.toString());
            }
            if (hasCriticalErrors()) {
                a(false);
                return;
            }
            jVar.a(this.f);
            if (appLovinSdkSettings instanceof at) {
                jVar.a(((at) appLovinSdkSettings).a());
            }
            this.f.c();
            if (((Boolean) this.f.a(bb.b)).booleanValue()) {
                this.f.a(appLovinSdkSettings);
                this.f.b();
            }
            e();
        } catch (Throwable th) {
            Log.e(AppLovinLogger.SDK_TAG, "Failed to load AppLovin SDK, ad serving will be disabled", th);
            a(false);
        }
    }

    @Override // com.applovin.sdk.AppLovinSdk
    public void initializeSdk() {
    }

    @Override // com.applovin.sdk.AppLovinSdk
    public boolean isEnabled() {
        return this.m;
    }

    @Override // com.applovin.sdk.AppLovinSdk
    public void setPluginVersion(String str) {
        if (str == null) {
            throw new IllegalArgumentException("No version specified");
        }
        this.f.a(bb.F, str);
        this.f.b();
    }
}
