package com.flurry.sdk;

/* JADX INFO: loaded from: classes.dex */
public class d extends fx {
    public r a;
    public a b;
    public av c;

    public enum a {
        kOnFetched,
        kOnFetchFailed,
        kOnRendered,
        kOnRenderFailed,
        kOnOpen,
        kOnClose,
        kOnAppExit,
        kOnClicked,
        kOnClickFailed,
        kOnImpressionLogged,
        kOnVideoCompleted
    }

    public d() {
        super("com.flurry.android.impl.ads.AdStateEvent");
        this.c = av.kUnknown;
    }
}
