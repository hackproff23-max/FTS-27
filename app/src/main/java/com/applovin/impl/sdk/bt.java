package com.applovin.impl.sdk;

import org.json.JSONObject;

/* JADX INFO: loaded from: classes.dex */
abstract class bt extends ba implements o {
    private int a;
    private final o b;
    private bd c;

    private bt(String str, int i, AppLovinSdkImpl appLovinSdkImpl) {
        super(str, appLovinSdkImpl);
        this.c = null;
        this.a = i;
        this.b = new bu(this, appLovinSdkImpl, str);
    }

    bt(String str, bd bdVar, AppLovinSdkImpl appLovinSdkImpl) {
        this(str, ((Integer) appLovinSdkImpl.a(bdVar)).intValue(), appLovinSdkImpl);
    }

    static /* synthetic */ int b(bt btVar, int i) {
        int i2 = btVar.a - i;
        btVar.a = i2;
        return i2;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void d() {
        if (this.c != null) {
            be settingsManager = this.f.getSettingsManager();
            settingsManager.a(this.c, this.c.c());
            settingsManager.b();
        }
    }

    public void a(int i) {
    }

    public void a(bd bdVar) {
        this.c = bdVar;
    }

    protected abstract void a(n nVar, o oVar);

    public void a(JSONObject jSONObject, int i) {
    }

    @Override // java.lang.Runnable
    public void run() {
        a(this.f.getConnectionManager(), this.b);
    }
}
