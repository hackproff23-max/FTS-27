package com.mopub.mobileads.util.vast;

import com.mopub.mobileads.VastAbsoluteProgressTracker;
import com.mopub.mobileads.VastFractionalProgressTracker;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public class VastVideoConfiguration implements Serializable {
    private static final long serialVersionUID = 1;
    private String mClickThroughUrl;
    private String mDiskMediaFileUrl;
    private String mNetworkMediaFileUrl;
    private VastCompanionAd mVastCompanionAd;
    private final ArrayList<String> mImpressionTrackers = new ArrayList<>();
    private final ArrayList<VastFractionalProgressTracker> mFractionalTrackers = new ArrayList<>();
    private final ArrayList<VastAbsoluteProgressTracker> mAbsoluteTrackers = new ArrayList<>();
    private final ArrayList<String> mCompleteTrackers = new ArrayList<>();
    private final ArrayList<String> mCloseTrackers = new ArrayList<>();
    private final ArrayList<String> mClickTrackers = new ArrayList<>();

    public void addImpressionTrackers(List<String> impressionTrackers) {
        this.mImpressionTrackers.addAll(impressionTrackers);
    }

    public void addFractionalTrackers(List<VastFractionalProgressTracker> fractionalTrackers) {
        this.mFractionalTrackers.addAll(fractionalTrackers);
    }

    public void addAbsoluteTrackers(List<VastAbsoluteProgressTracker> absoluteTrackers) {
        this.mAbsoluteTrackers.addAll(absoluteTrackers);
    }

    public void addCompleteTrackers(List<String> completeTrackers) {
        this.mCompleteTrackers.addAll(completeTrackers);
    }

    public void addCloseTrackers(List<String> closeTrackers) {
        this.mCloseTrackers.addAll(closeTrackers);
    }

    public void addClickTrackers(List<String> clickTrackers) {
        this.mClickTrackers.addAll(clickTrackers);
    }

    public void setClickThroughUrl(String clickThroughUrl) {
        this.mClickThroughUrl = clickThroughUrl;
    }

    public void setNetworkMediaFileUrl(String networkMediaFileUrl) {
        this.mNetworkMediaFileUrl = networkMediaFileUrl;
    }

    public void setDiskMediaFileUrl(String diskMediaFileUrl) {
        this.mDiskMediaFileUrl = diskMediaFileUrl;
    }

    public void setVastCompanionAd(VastCompanionAd vastCompanionAd) {
        this.mVastCompanionAd = vastCompanionAd;
    }

    public List<String> getImpressionTrackers() {
        return this.mImpressionTrackers;
    }

    public ArrayList<VastAbsoluteProgressTracker> getAbsoluteTrackers() {
        return this.mAbsoluteTrackers;
    }

    public ArrayList<VastFractionalProgressTracker> getFractionalTrackers() {
        return this.mFractionalTrackers;
    }

    public List<String> getCompleteTrackers() {
        return this.mCompleteTrackers;
    }

    public List<String> getCloseTrackers() {
        return this.mCloseTrackers;
    }

    public List<String> getClickTrackers() {
        return this.mClickTrackers;
    }

    public String getClickThroughUrl() {
        return this.mClickThroughUrl;
    }

    public String getNetworkMediaFileUrl() {
        return this.mNetworkMediaFileUrl;
    }

    public String getDiskMediaFileUrl() {
        return this.mDiskMediaFileUrl;
    }

    public VastCompanionAd getVastCompanionAd() {
        return this.mVastCompanionAd;
    }
}
