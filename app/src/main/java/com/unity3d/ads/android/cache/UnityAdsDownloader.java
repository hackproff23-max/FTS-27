package com.unity3d.ads.android.cache;

import android.annotation.TargetApi;
import com.unity3d.ads.android.UnityAdsDeviceLog;
import com.unity3d.ads.android.UnityAdsUtils;
import com.unity3d.ads.android.campaign.UnityAdsCampaign;
import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Vector;

/* JADX INFO: loaded from: classes.dex */
@TargetApi(9)
public class UnityAdsDownloader {
    private static ArrayList a = null;
    private static ArrayList b = null;
    private static boolean c = false;
    private static Vector d = null;

    static /* synthetic */ void a(b bVar) {
        if (d != null) {
            d.remove(bVar);
        }
    }

    static /* synthetic */ void a(c cVar, String str) {
        if (b != null) {
            for (IUnityAdsDownloadListener iUnityAdsDownloadListener : (ArrayList) b.clone()) {
                switch (a.a[cVar.ordinal()]) {
                    case 1:
                        iUnityAdsDownloadListener.onFileDownloadCompleted(str);
                        break;
                    case 2:
                        iUnityAdsDownloadListener.onFileDownloadCancelled(str);
                        break;
                }
            }
        }
    }

    public static void addDownload(UnityAdsCampaign unityAdsCampaign) {
        if (a == null) {
            a = new ArrayList();
        }
        if (!b(unityAdsCampaign.getVideoUrl())) {
            a.add(unityAdsCampaign);
        }
        if (!c) {
            c = true;
            b();
        }
    }

    public static void addListener(IUnityAdsDownloadListener iUnityAdsDownloadListener) {
        if (b == null) {
            b = new ArrayList();
        }
        if (!b.contains(iUnityAdsDownloadListener)) {
            b.add(iUnityAdsDownloadListener);
        }
    }

    public static void removeListener(IUnityAdsDownloadListener iUnityAdsDownloadListener) {
        if (b != null && b.contains(iUnityAdsDownloadListener)) {
            b.remove(iUnityAdsDownloadListener);
        }
    }

    public static void stopAllDownloads() {
        UnityAdsDeviceLog.entered();
        if (d != null) {
            Iterator it = d.iterator();
            while (it.hasNext()) {
                ((b) it.next()).cancel(true);
            }
        }
    }

    public static void clearData() {
        if (d != null) {
            d.clear();
            d = null;
        }
        c = false;
        if (b != null) {
            b.clear();
            b = null;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void b(UnityAdsCampaign unityAdsCampaign) {
        int i;
        if (a != null) {
            int i2 = 0;
            while (true) {
                i = i2;
                if (i >= a.size()) {
                    i = -1;
                    break;
                } else if (((UnityAdsCampaign) a.get(i)).equals(unityAdsCampaign)) {
                    break;
                } else {
                    i2 = i + 1;
                }
            }
            if (i >= 0) {
                a.remove(i);
            }
        }
    }

    private static boolean b(String str) {
        if (a != null) {
            for (UnityAdsCampaign unityAdsCampaign : a) {
                if (unityAdsCampaign != null && unityAdsCampaign.getVideoUrl() != null && unityAdsCampaign.getVideoUrl().equals(str)) {
                    return true;
                }
                if (unityAdsCampaign == null || unityAdsCampaign.getVideoUrl() == null) {
                    a.remove(unityAdsCampaign);
                }
            }
        }
        return false;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void b() {
        if (a != null && a.size() > 0) {
            UnityAdsCampaign unityAdsCampaign = (UnityAdsCampaign) a.get(0);
            UnityAdsDeviceLog.debug("Starting download for: " + unityAdsCampaign.getVideoFilename());
            if (unityAdsCampaign == null || unityAdsCampaign.getVideoUrl() == null || unityAdsCampaign.getVideoUrl().length() <= 0) {
                b(unityAdsCampaign);
                return;
            }
            b bVar = new b(unityAdsCampaign);
            if (d == null) {
                d = new Vector();
            }
            d.add(bVar);
            bVar.execute(unityAdsCampaign.getVideoUrl());
            return;
        }
        if (a != null) {
            c = false;
            UnityAdsDeviceLog.debug("All downloads completed.");
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static FileOutputStream c(String str) {
        try {
            return new FileOutputStream(new File(UnityAdsUtils.createCacheDir(), str));
        } catch (Exception e) {
            UnityAdsDeviceLog.error("Problems creating FOS: " + str);
            return null;
        }
    }
}
