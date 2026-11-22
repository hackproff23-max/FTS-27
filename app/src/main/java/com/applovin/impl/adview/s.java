package com.applovin.impl.adview;

import android.content.Context;
import android.view.View;
import com.applovin.sdk.AppLovinSdk;

/* JADX INFO: loaded from: classes.dex */
public abstract class s extends View {
    protected final AppLovinSdk a;
    protected final Context b;

    s(AppLovinSdk appLovinSdk, Context context) {
        super(context);
        this.b = context;
        this.a = appLovinSdk;
    }

    public static s a(AppLovinSdk appLovinSdk, Context context, t tVar) {
        return tVar.equals(t.WhiteXOnTransparentGrey) ? new aj(appLovinSdk, context) : new ak(appLovinSdk, context);
    }

    public abstract void a(int i);
}
