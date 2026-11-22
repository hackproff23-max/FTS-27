package com.unity3d.ads.android.webapp;

import android.webkit.WebChromeClient;
import android.webkit.WebStorage;
import com.unity3d.ads.android.UnityAdsDeviceLog;
import java.io.File;

/* JADX INFO: compiled from: UnityAdsWebView.java */
/* JADX INFO: loaded from: classes.dex */
final class j extends WebChromeClient {
    final /* synthetic */ UnityAdsWebView a;

    private j(UnityAdsWebView unityAdsWebView) {
        this.a = unityAdsWebView;
    }

    /* synthetic */ j(UnityAdsWebView unityAdsWebView, byte b) {
        this(unityAdsWebView);
    }

    @Override // android.webkit.WebChromeClient
    public final void onConsoleMessage(String str, int i, String str2) {
        File file;
        try {
            file = new File(str2);
        } catch (Exception e) {
            UnityAdsDeviceLog.error("Could not handle sourceId: " + e.getMessage());
            file = null;
        }
        if (file != null && file.getName() != null) {
            str2 = file.getName();
        }
        UnityAdsDeviceLog.debug("JavaScript (sourceId=" + str2 + ", line=" + i + "): " + str);
    }

    public final void onReachedMaxAppCacheSize(long j, long j2, WebStorage.QuotaUpdater quotaUpdater) {
        quotaUpdater.updateQuota(2 * j);
    }
}
