package com.unity3d.ads.android.cache;

import com.unity3d.ads.android.UnityAdsDeviceLog;
import com.unity3d.ads.android.UnityAdsUtils;
import com.unity3d.ads.android.campaign.IUnityAdsCampaignHandlerListener;
import com.unity3d.ads.android.campaign.UnityAdsCampaign;
import com.unity3d.ads.android.campaign.UnityAdsCampaignHandler;
import com.unity3d.ads.android.properties.UnityAdsConstants;
import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;

/* JADX INFO: loaded from: classes.dex */
public class UnityAdsCacheManager implements IUnityAdsCampaignHandlerListener {
    private IUnityAdsCacheListener a = null;
    private ArrayList b = null;
    private ArrayList c = null;
    private int d = 0;
    private int e = 0;

    public UnityAdsCacheManager() {
        if (UnityAdsUtils.canUseExternalStorage()) {
            UnityAdsDeviceLog.debug("External storagedir: " + UnityAdsUtils.getCacheDirectory() + " created with result: " + UnityAdsUtils.createCacheDir());
        } else {
            UnityAdsDeviceLog.info("Could not create cache, no external memory present");
        }
    }

    public void setDownloadListener(IUnityAdsCacheListener iUnityAdsCacheListener) {
        this.a = iUnityAdsCacheListener;
    }

    public boolean hasDownloadingHandlers() {
        return this.b != null && this.b.size() > 0;
    }

    public void initCache(ArrayList arrayList) {
        updateCache(arrayList);
    }

    public void updateCache(ArrayList arrayList) {
        File[] fileArrListFiles;
        if (this.a != null) {
            this.a.onCampaignUpdateStarted();
        }
        this.d = 0;
        if (arrayList != null) {
            UnityAdsDeviceLog.debug(arrayList.toString());
        }
        if (UnityAdsUtils.getCacheDirectory() != null && (fileArrListFiles = new File(UnityAdsUtils.getCacheDirectory()).listFiles()) != null) {
            for (File file : fileArrListFiles) {
                UnityAdsDeviceLog.debug("Checking file: " + file.getName());
                if (!file.getName().equals(UnityAdsConstants.PENDING_REQUESTS_FILENAME) && !file.getName().equals(UnityAdsConstants.CACHE_MANIFEST_FILENAME) && !UnityAdsUtils.isFileRequiredByCampaigns(file.getName(), arrayList)) {
                    UnityAdsUtils.removeFile(file.getName());
                }
            }
        }
        if (arrayList != null) {
            this.e = arrayList.size();
            UnityAdsDeviceLog.debug("Updating cache: Going through active campaigns: " + this.e);
            Iterator it = arrayList.iterator();
            while (it.hasNext()) {
                UnityAdsCampaignHandler unityAdsCampaignHandler = new UnityAdsCampaignHandler((UnityAdsCampaign) it.next());
                if (this.c == null) {
                    this.c = new ArrayList();
                }
                this.c.add(unityAdsCampaignHandler);
                unityAdsCampaignHandler.setListener(this);
                unityAdsCampaignHandler.initCampaign();
                if (unityAdsCampaignHandler.hasDownloads()) {
                    if (this.b == null) {
                        this.b = new ArrayList();
                    }
                    this.b.add(unityAdsCampaignHandler);
                }
            }
        }
    }

    public void clearData() {
        if (this.a != null) {
            this.a = null;
        }
        if (this.b != null) {
            for (UnityAdsCampaignHandler unityAdsCampaignHandler : this.b) {
                unityAdsCampaignHandler.setListener(null);
                unityAdsCampaignHandler.clearData();
            }
            this.b.clear();
            this.b = null;
        }
        if (this.c != null) {
            for (UnityAdsCampaignHandler unityAdsCampaignHandler2 : this.c) {
                unityAdsCampaignHandler2.setListener(null);
                unityAdsCampaignHandler2.clearData();
            }
            this.c.clear();
            this.c = null;
        }
    }

    public boolean isCampaignCached(UnityAdsCampaign unityAdsCampaign) {
        return UnityAdsUtils.isFileInCache(unityAdsCampaign.getVideoFilename());
    }

    public void cacheNextVideo(UnityAdsCampaign unityAdsCampaign) {
        new UnityAdsCampaignHandler(unityAdsCampaign).downloadCampaign();
    }

    @Override // com.unity3d.ads.android.campaign.IUnityAdsCampaignHandlerListener
    public void onCampaignHandled(UnityAdsCampaignHandler unityAdsCampaignHandler) {
        this.d++;
        if (this.b != null) {
            this.b.remove(unityAdsCampaignHandler);
        }
        if (this.c != null) {
            this.c.remove(unityAdsCampaignHandler);
        }
        this.a.onCampaignReady(unityAdsCampaignHandler);
        if (this.d == this.e) {
            this.a.onAllCampaignsReady();
        }
    }
}
