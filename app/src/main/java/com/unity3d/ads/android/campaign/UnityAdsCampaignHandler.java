package com.unity3d.ads.android.campaign;

import com.unity3d.ads.android.UnityAdsDeviceLog;
import com.unity3d.ads.android.UnityAdsUtils;
import com.unity3d.ads.android.cache.IUnityAdsDownloadListener;
import com.unity3d.ads.android.cache.UnityAdsDownloader;
import com.unity3d.ads.android.properties.UnityAdsConstants;
import com.unity3d.ads.android.webapp.UnityAdsInstrumentation;
import java.util.ArrayList;
import java.util.HashMap;

/* JADX INFO: loaded from: classes.dex */
public class UnityAdsCampaignHandler implements IUnityAdsDownloadListener {
    private UnityAdsCampaign b;
    private ArrayList a = null;
    private IUnityAdsCampaignHandlerListener c = null;
    private long d = 0;
    private long e = 0;

    public UnityAdsCampaignHandler(UnityAdsCampaign unityAdsCampaign) {
        this.b = null;
        this.b = unityAdsCampaign;
    }

    public boolean hasDownloads() {
        return this.a != null && this.a.size() > 0;
    }

    public UnityAdsCampaign getCampaign() {
        return this.b;
    }

    public long getCachingDurationInMillis() {
        if (this.d <= 0 || this.e <= 0) {
            return 0L;
        }
        return this.e - this.d;
    }

    public void setListener(IUnityAdsCampaignHandlerListener iUnityAdsCampaignHandlerListener) {
        this.c = iUnityAdsCampaignHandlerListener;
    }

    @Override // com.unity3d.ads.android.cache.IUnityAdsDownloadListener
    public void onFileDownloadCompleted(String str) {
        if (a(str)) {
            UnityAdsDeviceLog.debug("Reporting campaign download completion: " + this.b.getCampaignId());
            HashMap map = new HashMap();
            map.put(UnityAdsConstants.UNITY_ADS_GOOGLE_ANALYTICS_EVENT_VALUE_KEY, "completed");
            map.put(UnityAdsConstants.UNITY_ADS_GOOGLE_ANALYTICS_EVENT_CACHINGDURATION_KEY, Long.valueOf(getCachingDurationInMillis()));
            UnityAdsInstrumentation.gaInstrumentationVideoCaching(this.b, map);
        }
    }

    @Override // com.unity3d.ads.android.cache.IUnityAdsDownloadListener
    public void onFileDownloadCancelled(String str) {
        if (a(str)) {
            UnityAdsDeviceLog.debug("Download cancelled: " + this.b.getCampaignId());
            HashMap map = new HashMap();
            map.put(UnityAdsConstants.UNITY_ADS_GOOGLE_ANALYTICS_EVENT_VALUE_KEY, UnityAdsConstants.UNITY_ADS_GOOGLE_ANALYTICS_EVENT_VIDEOCACHING_FAILED);
            UnityAdsInstrumentation.gaInstrumentationVideoCaching(this.b, map);
        }
    }

    public void initCampaign() {
        this.b.getVideoUrl();
        if (this.b.shouldCacheVideo().booleanValue() && !UnityAdsUtils.isFileInCache(this.b.getVideoFilename()) && UnityAdsUtils.canUseExternalStorage()) {
            if (!hasDownloads()) {
                UnityAdsDownloader.addListener(this);
            }
            b();
        } else if (this.b.shouldCacheVideo().booleanValue() && !a() && UnityAdsUtils.canUseExternalStorage()) {
            UnityAdsDeviceLog.debug("The file was not okay, redownloading");
            UnityAdsUtils.removeFile(this.b.getVideoFilename());
            UnityAdsDownloader.addListener(this);
            b();
        }
        if (this.c != null) {
            this.c.onCampaignHandled(this);
        }
    }

    public void downloadCampaign() {
        if (!UnityAdsUtils.isFileInCache(this.b.getVideoFilename()) && UnityAdsUtils.canUseExternalStorage()) {
            if (!hasDownloads()) {
                UnityAdsDownloader.addListener(this);
            }
            b();
            return;
        }
        this.b.getVideoFilename();
        if (!a() && UnityAdsUtils.canUseExternalStorage()) {
            if (!hasDownloads()) {
                UnityAdsDownloader.addListener(this);
            }
            UnityAdsUtils.removeFile(this.b.getVideoFilename());
            UnityAdsDownloader.addListener(this);
            b();
        }
    }

    public void clearData() {
        if (this.c != null) {
            this.c = null;
        }
        if (this.a != null) {
            this.a.clear();
        }
        if (this.b != null) {
            this.b.clearData();
            this.b = null;
        }
    }

    private boolean a(String str) {
        this.e = System.currentTimeMillis();
        if (this.a != null) {
            int i = 0;
            while (true) {
                if (i >= this.a.size()) {
                    i = -1;
                    break;
                }
                if (((String) this.a.get(i)).equals(str)) {
                    break;
                }
                i++;
            }
            if (i >= 0) {
                this.a.remove(i);
            }
        }
        if (this.a == null || this.a.size() != 0 || this.c == null) {
            return false;
        }
        UnityAdsDownloader.removeListener(this);
        return true;
    }

    private boolean a() {
        long sizeForLocalFile = UnityAdsUtils.getSizeForLocalFile(this.b.getVideoFilename());
        long videoFileExpectedSize = this.b.getVideoFileExpectedSize();
        UnityAdsDeviceLog.debug("localSize=" + sizeForLocalFile + ", expectedSize=" + videoFileExpectedSize);
        if (sizeForLocalFile == -1) {
            return false;
        }
        if (videoFileExpectedSize == -1) {
            return true;
        }
        return sizeForLocalFile > 0 && videoFileExpectedSize > 0 && sizeForLocalFile == videoFileExpectedSize;
    }

    private void b() {
        if (this.b != null) {
            if (this.a == null) {
                this.a = new ArrayList();
            }
            this.a.add(this.b.getVideoUrl());
            this.d = System.currentTimeMillis();
            HashMap map = new HashMap();
            map.put(UnityAdsConstants.UNITY_ADS_GOOGLE_ANALYTICS_EVENT_VALUE_KEY, "start");
            UnityAdsInstrumentation.gaInstrumentationVideoCaching(this.b, map);
            UnityAdsDownloader.addDownload(this.b);
        }
    }
}
