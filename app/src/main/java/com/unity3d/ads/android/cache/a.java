package com.unity3d.ads.android.cache;

/* JADX INFO: compiled from: UnityAdsDownloader.java */
/* JADX INFO: loaded from: classes.dex */
final /* synthetic */ class a {
    static final /* synthetic */ int[] a = new int[c.values().length];

    static {
        try {
            a[c.DownloadCompleted.ordinal()] = 1;
        } catch (NoSuchFieldError e) {
        }
        try {
            a[c.DownloadCancelled.ordinal()] = 2;
        } catch (NoSuchFieldError e2) {
        }
    }
}
