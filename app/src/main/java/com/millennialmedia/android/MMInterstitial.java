package com.millennialmedia.android;

import android.content.Context;

/* JADX INFO: loaded from: classes.dex */
public final class MMInterstitial implements MMAd {
    private static final String TAG = "MMInterstitial";
    MMAdImpl adImpl;
    int externalId;

    public MMInterstitial(Context context) {
        this.adImpl = new MMInterstitialAdImpl(context.getApplicationContext());
        this.adImpl.adType = "i";
    }

    public void fetch() {
        if (this.adImpl != null && this.adImpl.requestListener != null) {
            fetch(this.adImpl.mmRequest, this.adImpl.requestListener);
        } else {
            fetchInternal();
        }
    }

    public void fetch(MMRequest request) {
        if (this.adImpl != null && this.adImpl.requestListener != null) {
            fetch(request, this.adImpl.requestListener);
        } else {
            fetchInternal();
        }
    }

    public void fetch(MMRequest request, RequestListener listener) {
        if (this.adImpl != null) {
            this.adImpl.mmRequest = request;
            this.adImpl.requestListener = listener;
        }
        fetchInternal();
    }

    private void fetchInternal() {
        if (isAdAvailable()) {
            MMLog.d(TAG, "Ad already fetched and ready for display...");
            MMSDK.Event.requestFailed(this.adImpl, new MMException(17));
        } else {
            MMLog.d(TAG, "Fetching new ad...");
            this.adImpl.requestAd();
        }
    }

    public boolean isAdAvailable() {
        if (!MMSDK.isUiThread()) {
            MMLog.e(TAG, MMException.getErrorCodeMessage(3));
            return false;
        }
        try {
            MMAdImplController.assignAdViewController(this.adImpl);
            return this.adImpl.controller != null && this.adImpl.controller.isAdAvailable(this.adImpl) == 0;
        } catch (Exception e) {
            MMLog.e(TAG, "There was an exception checking for a cached ad. ", e);
            e.printStackTrace();
            return false;
        }
    }

    public boolean display() {
        return display(false);
    }

    public boolean display(boolean throwError) {
        if (!MMSDK.isUiThread()) {
            MMLog.e(TAG, MMException.getErrorCodeMessage(3));
            return false;
        }
        try {
            int error = displayInternal();
            if (error == 0 || !throwError) {
                return error == 0;
            }
            throw new MMException(error);
        } catch (Exception e) {
            if (throwError) {
                throw new MMException(e);
            }
            return false;
        }
    }

    int displayInternal() {
        try {
            MMAdImplController.assignAdViewController(this.adImpl);
            if (this.adImpl.controller != null) {
                return this.adImpl.controller.display(this.adImpl);
            }
        } catch (Exception e) {
            MMLog.e(TAG, "There was an exception displaying a cached ad. ", e);
            e.printStackTrace();
        }
        return 100;
    }

    class MMInterstitialAdImpl extends MMAdImpl {
        public MMInterstitialAdImpl(Context context) {
            super(context);
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        @Override // com.millennialmedia.android.MMAdImpl
        public MMInterstitial getCallingAd() {
            return MMInterstitial.this;
        }
    }

    @Override // com.millennialmedia.android.MMAd
    public void setApid(String apid) {
        this.adImpl.setApid(apid);
    }

    @Override // com.millennialmedia.android.MMAd
    public String getApid() {
        return this.adImpl.getApid();
    }

    @Override // com.millennialmedia.android.MMAd
    public void setListener(RequestListener listener) {
        this.adImpl.setListener(listener);
    }

    @Override // com.millennialmedia.android.MMAd
    public RequestListener getListener() {
        return this.adImpl.getListener();
    }

    @Override // com.millennialmedia.android.MMAd
    public void setIgnoresDensityScaling(boolean ignoresDensityScaling) {
        this.adImpl.setIgnoresDensityScaling(ignoresDensityScaling);
    }

    @Override // com.millennialmedia.android.MMAd
    public boolean getIgnoresDensityScaling() {
        return this.adImpl.getIgnoresDensityScaling();
    }

    @Override // com.millennialmedia.android.MMAd
    public void setMMRequest(MMRequest request) {
        this.adImpl.setMMRequest(request);
    }

    @Override // com.millennialmedia.android.MMAd
    public MMRequest getMMRequest() {
        return this.adImpl.getMMRequest();
    }
}
