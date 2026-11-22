package com.flurry.android;

/* JADX INFO: loaded from: classes.dex */
public enum FlurryAdSize {
    BANNER_TOP(1),
    BANNER_BOTTOM(2),
    FULLSCREEN(3);

    private int a;

    FlurryAdSize(int i) {
        this.a = i;
    }

    public int getValue() {
        return this.a;
    }
}
