package com.mopub.mobileads;

import android.support.annotation.NonNull;
import com.mopub.common.Preconditions;
import java.io.Serializable;

/* JADX INFO: loaded from: classes.dex */
public class VastTracker implements Serializable {
    private static final long serialVersionUID = 0;
    private boolean mCalled;
    private final String mTrackingUrl;

    public VastTracker(@NonNull String trackingUrl) {
        Preconditions.checkNotNull(trackingUrl);
        this.mTrackingUrl = trackingUrl;
    }

    @NonNull
    public String getTrackingUrl() {
        return this.mTrackingUrl;
    }

    public void setTracked() {
        this.mCalled = true;
    }

    public boolean isTracked() {
        return this.mCalled;
    }
}
