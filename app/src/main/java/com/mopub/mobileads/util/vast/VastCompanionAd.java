package com.mopub.mobileads.util.vast;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public class VastCompanionAd implements Serializable {
    private static final long serialVersionUID = 0;
    private final String mClickThroughUrl;
    private final ArrayList<String> mClickTrackers;
    private final Integer mHeight;
    private final String mImageUrl;
    private final Integer mWidth;

    public VastCompanionAd(Integer width, Integer height, String imageUrl, String clickThroughUrl, ArrayList<String> clickTrackers) {
        this.mWidth = width;
        this.mHeight = height;
        this.mImageUrl = imageUrl;
        this.mClickThroughUrl = clickThroughUrl;
        this.mClickTrackers = clickTrackers;
    }

    public Integer getWidth() {
        return this.mWidth;
    }

    public Integer getHeight() {
        return this.mHeight;
    }

    public String getImageUrl() {
        return this.mImageUrl;
    }

    public String getClickThroughUrl() {
        return this.mClickThroughUrl;
    }

    public List<String> getClickTrackers() {
        return this.mClickTrackers;
    }
}
