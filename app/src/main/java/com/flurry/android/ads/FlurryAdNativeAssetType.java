package com.flurry.android.ads;

/* JADX INFO: loaded from: classes.dex */
public enum FlurryAdNativeAssetType {
    TEXT(1),
    IMAGE(2);

    private int a;

    FlurryAdNativeAssetType(int i) {
        this.a = i;
    }

    public int getValue() {
        return this.a;
    }
}
