package com.millennialmedia.android;

import android.util.Log;

/* JADX INFO: loaded from: classes.dex */
public interface RequestListener {
    void MMAdOverlayClosed(MMAd mMAd);

    void MMAdOverlayLaunched(MMAd mMAd);

    void MMAdRequestIsCaching(MMAd mMAd);

    void onSingleTap(MMAd mMAd);

    void requestCompleted(MMAd mMAd);

    void requestFailed(MMAd mMAd, MMException mMException);

    public static class RequestListenerImpl implements RequestListener {
        @Override // com.millennialmedia.android.RequestListener
        public void MMAdOverlayLaunched(MMAd mmAd) {
            Log.i(MMSDK.SDKLOG, "Millennial Media Ad View overlay launched");
        }

        @Override // com.millennialmedia.android.RequestListener
        public void MMAdOverlayClosed(MMAd mmAd) {
            Log.i(MMSDK.SDKLOG, "Millennial Media Ad View overlay closed");
        }

        @Override // com.millennialmedia.android.RequestListener
        public void MMAdRequestIsCaching(MMAd mmAd) {
            Log.i(MMSDK.SDKLOG, "Millennial Media Ad View caching request");
        }

        @Override // com.millennialmedia.android.RequestListener
        public void requestCompleted(MMAd mmAd) {
            Log.i(MMSDK.SDKLOG, "Ad request succeeded");
        }

        @Override // com.millennialmedia.android.RequestListener
        public void requestFailed(MMAd mmAd, MMException error) {
            Log.i(MMSDK.SDKLOG, String.format("Ad request failed with error: %d %s.", Integer.valueOf(error.getCode()), error.getMessage()));
        }

        @Override // com.millennialmedia.android.RequestListener
        public void onSingleTap(MMAd mmAd) {
            Log.i(MMSDK.SDKLOG, "Ad tapped");
        }
    }
}
