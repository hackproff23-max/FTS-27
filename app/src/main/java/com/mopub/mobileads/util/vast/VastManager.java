package com.mopub.mobileads.util.vast;

import android.content.Context;
import android.view.Display;
import android.view.WindowManager;
import com.mopub.common.CacheService;
import com.mopub.common.logging.MoPubLog;
import com.mopub.common.util.AsyncTasks;
import com.mopub.mobileads.VastVideoDownloadTask;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public class VastManager implements VastXmlManagerAggregator.VastXmlManagerAggregatorListener {
    private static final double AREA_WEIGHT = 60.0d;
    private static final double ASPECT_RATIO_WEIGHT = 40.0d;
    private int mScreenArea;
    private double mScreenAspectRatio;
    private VastManagerListener mVastManagerListener;
    private VastXmlManagerAggregator mVastXmlManagerAggregator;
    private static final List<String> VIDEO_MIME_TYPES = Arrays.asList("video/mp4", "video/3gpp");
    private static final List<String> COMPANION_IMAGE_MIME_TYPES = Arrays.asList("image/jpeg", "image/png", "image/bmp", "image/gif");

    public interface VastManagerListener {
        void onVastVideoConfigurationPrepared(VastVideoConfiguration vastVideoConfiguration);
    }

    public VastManager(Context context) {
        initializeScreenDimensions(context);
    }

    public void prepareVastVideoConfiguration(String vastXml, VastManagerListener vastManagerListener) {
        if (this.mVastXmlManagerAggregator == null) {
            this.mVastManagerListener = vastManagerListener;
            this.mVastXmlManagerAggregator = new VastXmlManagerAggregator(this);
            try {
                AsyncTasks.safeExecuteOnExecutor(this.mVastXmlManagerAggregator, vastXml);
            } catch (Exception e) {
                MoPubLog.d("Failed to aggregate vast xml", e);
                this.mVastManagerListener.onVastVideoConfigurationPrepared(null);
            }
        }
    }

    public void cancel() {
        if (this.mVastXmlManagerAggregator != null) {
            this.mVastXmlManagerAggregator.cancel(true);
            this.mVastXmlManagerAggregator = null;
        }
    }

    @Override // com.mopub.mobileads.util.vast.VastXmlManagerAggregator.VastXmlManagerAggregatorListener
    public void onAggregationComplete(List<VastXmlManager> vastXmlManagers) {
        this.mVastXmlManagerAggregator = null;
        if (vastXmlManagers == null) {
            this.mVastManagerListener.onVastVideoConfigurationPrepared(null);
            return;
        }
        final VastVideoConfiguration vastVideoConfiguration = createVastVideoConfigurationFromXml(vastXmlManagers);
        if (updateDiskMediaFileUrl(vastVideoConfiguration)) {
            this.mVastManagerListener.onVastVideoConfigurationPrepared(vastVideoConfiguration);
            return;
        }
        VastVideoDownloadTask vastVideoDownloadTask = new VastVideoDownloadTask(new VastVideoDownloadTask.VastVideoDownloadTaskListener() { // from class: com.mopub.mobileads.util.vast.VastManager.1
            @Override // com.mopub.mobileads.VastVideoDownloadTask.VastVideoDownloadTaskListener
            public void onComplete(boolean success) {
                if (!success || !VastManager.this.updateDiskMediaFileUrl(vastVideoConfiguration)) {
                    VastManager.this.mVastManagerListener.onVastVideoConfigurationPrepared(null);
                } else {
                    VastManager.this.mVastManagerListener.onVastVideoConfigurationPrepared(vastVideoConfiguration);
                }
            }
        });
        try {
            AsyncTasks.safeExecuteOnExecutor(vastVideoDownloadTask, vastVideoConfiguration.getNetworkMediaFileUrl());
        } catch (Exception e) {
            MoPubLog.d("Failed to download vast video", e);
            this.mVastManagerListener.onVastVideoConfigurationPrepared(null);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean updateDiskMediaFileUrl(VastVideoConfiguration vastVideoConfiguration) {
        String networkMediaFileUrl = vastVideoConfiguration.getNetworkMediaFileUrl();
        if (!CacheService.containsKeyDiskCache(networkMediaFileUrl)) {
            return false;
        }
        String filePathDiskCache = CacheService.getFilePathDiskCache(networkMediaFileUrl);
        vastVideoConfiguration.setDiskMediaFileUrl(filePathDiskCache);
        return true;
    }

    private void initializeScreenDimensions(Context context) {
        Display display = ((WindowManager) context.getSystemService("window")).getDefaultDisplay();
        int x = display.getWidth();
        int y = display.getHeight();
        int screenWidth = Math.max(x, y);
        int screenHeight = Math.min(x, y);
        this.mScreenAspectRatio = ((double) screenWidth) / ((double) screenHeight);
        this.mScreenArea = screenWidth * screenHeight;
    }

    private VastVideoConfiguration createVastVideoConfigurationFromXml(List<VastXmlManager> xmlManagers) {
        VastVideoConfiguration vastVideoConfiguration = new VastVideoConfiguration();
        List<VastXmlManager.MediaXmlManager> mediaXmlManagers = new ArrayList<>();
        List<VastXmlManager.ImageCompanionAdXmlManager> companionXmlManagers = new ArrayList<>();
        for (VastXmlManager xmlManager : xmlManagers) {
            vastVideoConfiguration.addImpressionTrackers(xmlManager.getImpressionTrackers());
            vastVideoConfiguration.addAbsoluteTrackers(xmlManager.getAbsoluteProgressTrackers());
            vastVideoConfiguration.addFractionalTrackers(xmlManager.getFractionalProgressTrackers());
            vastVideoConfiguration.addCompleteTrackers(xmlManager.getVideoCompleteTrackers());
            vastVideoConfiguration.addCloseTrackers(xmlManager.getVideoCloseTrackers());
            vastVideoConfiguration.addClickTrackers(xmlManager.getClickTrackers());
            if (vastVideoConfiguration.getClickThroughUrl() == null) {
                vastVideoConfiguration.setClickThroughUrl(xmlManager.getClickThroughUrl());
            }
            mediaXmlManagers.addAll(xmlManager.getMediaXmlManagers());
            companionXmlManagers.addAll(xmlManager.getCompanionAdXmlManagers());
        }
        vastVideoConfiguration.setNetworkMediaFileUrl(getBestMediaFileUrl(mediaXmlManagers));
        vastVideoConfiguration.setVastCompanionAd(getBestCompanionAd(companionXmlManagers));
        return vastVideoConfiguration;
    }

    String getBestMediaFileUrl(List<VastXmlManager.MediaXmlManager> managers) {
        List<VastXmlManager.MediaXmlManager> mediaXmlManagers = new ArrayList<>(managers);
        double bestMediaFitness = Double.POSITIVE_INFINITY;
        String bestMediaFileUrl = null;
        Iterator<VastXmlManager.MediaXmlManager> xmlManagerIterator = mediaXmlManagers.iterator();
        while (xmlManagerIterator.hasNext()) {
            VastXmlManager.MediaXmlManager mediaXmlManager = xmlManagerIterator.next();
            String mediaType = mediaXmlManager.getType();
            String mediaUrl = mediaXmlManager.getMediaUrl();
            if (!VIDEO_MIME_TYPES.contains(mediaType) || mediaUrl == null) {
                xmlManagerIterator.remove();
            } else {
                Integer mediaWidth = mediaXmlManager.getWidth();
                Integer mediaHeight = mediaXmlManager.getHeight();
                if (mediaWidth != null && mediaWidth.intValue() > 0 && mediaHeight != null && mediaHeight.intValue() > 0) {
                    double mediaFitness = calculateFitness(mediaWidth.intValue(), mediaHeight.intValue());
                    if (mediaFitness < bestMediaFitness) {
                        bestMediaFitness = mediaFitness;
                        bestMediaFileUrl = mediaUrl;
                    }
                }
            }
        }
        if (bestMediaFileUrl == null && !mediaXmlManagers.isEmpty()) {
            String bestMediaFileUrl2 = mediaXmlManagers.get(0).getMediaUrl();
            return bestMediaFileUrl2;
        }
        return bestMediaFileUrl;
    }

    VastCompanionAd getBestCompanionAd(List<VastXmlManager.ImageCompanionAdXmlManager> managers) {
        List<VastXmlManager.ImageCompanionAdXmlManager> companionXmlManagers = new ArrayList<>(managers);
        double bestCompanionFitness = Double.POSITIVE_INFINITY;
        VastXmlManager.ImageCompanionAdXmlManager bestCompanionXmlManager = null;
        Iterator<VastXmlManager.ImageCompanionAdXmlManager> xmlManagerIterator = companionXmlManagers.iterator();
        while (xmlManagerIterator.hasNext()) {
            VastXmlManager.ImageCompanionAdXmlManager companionXmlManager = xmlManagerIterator.next();
            String imageType = companionXmlManager.getType();
            String imageUrl = companionXmlManager.getImageUrl();
            if (!COMPANION_IMAGE_MIME_TYPES.contains(imageType) || imageUrl == null) {
                xmlManagerIterator.remove();
            } else {
                Integer imageWidth = companionXmlManager.getWidth();
                Integer imageHeight = companionXmlManager.getHeight();
                if (imageWidth != null && imageWidth.intValue() > 0 && imageHeight != null && imageHeight.intValue() > 0) {
                    double companionFitness = calculateFitness(imageWidth.intValue(), imageHeight.intValue());
                    if (companionFitness < bestCompanionFitness) {
                        bestCompanionFitness = companionFitness;
                        bestCompanionXmlManager = companionXmlManager;
                    }
                }
            }
        }
        if (bestCompanionXmlManager == null && !companionXmlManagers.isEmpty()) {
            VastXmlManager.ImageCompanionAdXmlManager bestCompanionXmlManager2 = companionXmlManagers.get(0);
            bestCompanionXmlManager = bestCompanionXmlManager2;
        }
        if (bestCompanionXmlManager != null) {
            return new VastCompanionAd(bestCompanionXmlManager.getWidth(), bestCompanionXmlManager.getHeight(), bestCompanionXmlManager.getImageUrl(), bestCompanionXmlManager.getClickThroughUrl(), new ArrayList(bestCompanionXmlManager.getClickTrackers()));
        }
        return null;
    }

    private double calculateFitness(int width, int height) {
        double mediaAspectRatio = ((double) width) / ((double) height);
        int mediaArea = width * height;
        double aspectRatioRatio = mediaAspectRatio / this.mScreenAspectRatio;
        double areaRatio = ((double) mediaArea) / ((double) this.mScreenArea);
        return (ASPECT_RATIO_WEIGHT * Math.abs(Math.log(aspectRatioRatio))) + (AREA_WEIGHT * Math.abs(Math.log(areaRatio)));
    }

    @Deprecated
    int getScreenArea() {
        return this.mScreenArea;
    }

    @Deprecated
    double getScreenAspectRatio() {
        return this.mScreenAspectRatio;
    }
}
