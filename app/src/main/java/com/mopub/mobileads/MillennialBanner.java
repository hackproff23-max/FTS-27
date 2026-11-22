package com.mopub.mobileads;

import android.content.Context;
import android.location.Location;
import android.util.Log;
import com.millennialmedia.android.MMAd;
import com.millennialmedia.android.MMAdView;
import com.millennialmedia.android.MMException;
import com.millennialmedia.android.MMRequest;
import com.millennialmedia.android.MMSDK;
import com.millennialmedia.android.RequestListener;
import java.util.Map;

/* JADX INFO: loaded from: classes.dex */
class MillennialBanner extends CustomEventBanner {
    public static final String AD_HEIGHT_KEY = "adHeight";
    public static final String AD_WIDTH_KEY = "adWidth";
    public static final String APID_KEY = "adUnitID";
    private CustomEventBanner.CustomEventBannerListener mBannerListener;
    private MMAdView mMillennialAdView;

    MillennialBanner() {
    }

    @Override // com.mopub.mobileads.CustomEventBanner
    protected void loadBanner(Context context, CustomEventBanner.CustomEventBannerListener customEventBannerListener, Map<String, Object> localExtras, Map<String, String> serverExtras) {
        this.mBannerListener = customEventBannerListener;
        if (extrasAreValid(serverExtras)) {
            String apid = serverExtras.get("adUnitID");
            int width = Integer.parseInt(serverExtras.get("adWidth"));
            int height = Integer.parseInt(serverExtras.get("adHeight"));
            MMSDK.initialize(context);
            this.mMillennialAdView = new MMAdView(context);
            this.mMillennialAdView.setListener(new MillennialBannerRequestListener());
            this.mMillennialAdView.setApid(apid);
            this.mMillennialAdView.setWidth(width);
            this.mMillennialAdView.setHeight(height);
            Location location = (Location) localExtras.get("location");
            if (location != null) {
                MMRequest.setUserLocation(location);
            }
            this.mMillennialAdView.setMMRequest(new MMRequest());
            this.mMillennialAdView.setId(MMSDK.getDefaultAdId());
            AdViewController.setShouldHonorServerDimensions(this.mMillennialAdView);
            this.mMillennialAdView.getAd();
            return;
        }
        this.mBannerListener.onBannerFailed(MoPubErrorCode.ADAPTER_CONFIGURATION_ERROR);
    }

    @Override // com.mopub.mobileads.CustomEventBanner
    protected void onInvalidate() {
        if (this.mMillennialAdView != null) {
            this.mMillennialAdView.setListener(null);
        }
    }

    private boolean extrasAreValid(Map<String, String> serverExtras) {
        try {
            Integer.parseInt(serverExtras.get("adWidth"));
            Integer.parseInt(serverExtras.get("adHeight"));
            return serverExtras.containsKey("adUnitID");
        } catch (NumberFormatException e) {
            return false;
        }
    }

    class MillennialBannerRequestListener implements RequestListener {
        MillennialBannerRequestListener() {
        }

        @Override // com.millennialmedia.android.RequestListener
        public void MMAdOverlayLaunched(MMAd mmAd) {
            Log.d("MoPub", "Millennial banner ad Launched.");
            MillennialBanner.this.mBannerListener.onBannerExpanded();
        }

        @Override // com.millennialmedia.android.RequestListener
        public void MMAdOverlayClosed(MMAd mmAd) {
            Log.d("MoPub", "Millennial banner ad closed.");
            MillennialBanner.this.mBannerListener.onBannerCollapsed();
        }

        @Override // com.millennialmedia.android.RequestListener
        public void MMAdRequestIsCaching(MMAd mmAd) {
        }

        @Override // com.millennialmedia.android.RequestListener
        public void requestCompleted(MMAd mmAd) {
            Log.d("MoPub", "Millennial banner ad loaded successfully. Showing ad...");
            MillennialBanner.this.mBannerListener.onBannerLoaded(MillennialBanner.this.mMillennialAdView);
        }

        @Override // com.millennialmedia.android.RequestListener
        public void requestFailed(MMAd mmAd, MMException e) {
            Log.d("MoPub", "Millennial banner ad failed to load.");
            MillennialBanner.this.mBannerListener.onBannerFailed(MoPubErrorCode.NETWORK_NO_FILL);
        }

        @Override // com.millennialmedia.android.RequestListener
        public void onSingleTap(MMAd mmAd) {
            MillennialBanner.this.mBannerListener.onBannerClicked();
        }
    }

    @Deprecated
    MMAdView getMMAdView() {
        return this.mMillennialAdView;
    }
}
