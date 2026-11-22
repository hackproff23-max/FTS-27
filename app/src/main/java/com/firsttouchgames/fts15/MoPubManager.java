package com.firsttouchgames.fts15;

import android.graphics.Paint;
import android.os.Build;
import android.util.Log;
import com.mopub.mobileads.MoPubErrorCode;
import com.mopub.mobileads.MoPubInterstitial;
import com.mopub.mobileads.MoPubView;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/* JADX INFO: loaded from: classes.dex */
public class MoPubManager {
    private static final String LOG_TAG = "MoPubManager";
    private static MainActivity mActivity;
    private static MoPubView mAdBanner;
    private static MoPubInterstitial mAdInterstitial;
    private static boolean mbLoadedBannerAd = false;
    private static boolean mbLoadingBannerAd = false;
    private static boolean mbDisplayedBannerAd = false;
    private static boolean mbDisplayedInterstitialAd = false;
    private static int miLoadAttemptCounter = 0;
    static MoPubView.BannerAdListener m_MoPubListener = new MoPubView.BannerAdListener() { // from class: com.firsttouchgames.fts15.MoPubManager.1
        @Override // com.mopub.mobileads.MoPubView.BannerAdListener
        public void onBannerLoaded(MoPubView banner) {
            Log.d(MoPubManager.LOG_TAG, "onBannerLoaded()");
            if (!MoPubManager.mbDisplayedBannerAd) {
                boolean unused = MoPubManager.mbLoadedBannerAd = false;
            } else {
                banner.setVisibility(0);
                boolean unused2 = MoPubManager.mbLoadedBannerAd = true;
            }
            boolean unused3 = MoPubManager.mbLoadingBannerAd = false;
        }

        @Override // com.mopub.mobileads.MoPubView.BannerAdListener
        public void onBannerFailed(MoPubView banner, MoPubErrorCode errorCode) {
            Log.d(MoPubManager.LOG_TAG, "onBannerFailed()");
            boolean unused = MoPubManager.mbLoadedBannerAd = false;
            boolean unused2 = MoPubManager.mbLoadingBannerAd = false;
        }

        @Override // com.mopub.mobileads.MoPubView.BannerAdListener
        public void onBannerClicked(MoPubView banner) {
            Log.d(MoPubManager.LOG_TAG, "onBannerClicked()");
        }

        @Override // com.mopub.mobileads.MoPubView.BannerAdListener
        public void onBannerExpanded(MoPubView banner) {
            Log.d(MoPubManager.LOG_TAG, "onBannerExpanded()");
        }

        @Override // com.mopub.mobileads.MoPubView.BannerAdListener
        public void onBannerCollapsed(MoPubView banner) {
            Log.d(MoPubManager.LOG_TAG, "onBannerCollapsed()");
        }
    };
    static MoPubInterstitial.InterstitialAdListener m_MoPubInterstitialListener = new MoPubInterstitial.InterstitialAdListener() { // from class: com.firsttouchgames.fts15.MoPubManager.2
        @Override // com.mopub.mobileads.MoPubInterstitial.InterstitialAdListener
        public void onInterstitialFailed(MoPubInterstitial interstitial, MoPubErrorCode errorCode) {
            Log.d(MoPubManager.LOG_TAG, "onInterstitialFailed() error: " + errorCode.toString());
            boolean unused = MoPubManager.mbDisplayedInterstitialAd = false;
            MoPubManager.access$404();
            if (MoPubManager.miLoadAttemptCounter < 2) {
                MoPubManager.mAdInterstitial.load();
            } else {
                int unused2 = MoPubManager.miLoadAttemptCounter = 0;
            }
        }

        @Override // com.mopub.mobileads.MoPubInterstitial.InterstitialAdListener
        public void onInterstitialLoaded(MoPubInterstitial interstitial) {
            Log.d(MoPubManager.LOG_TAG, "onInterstitialLoaded()");
            int unused = MoPubManager.miLoadAttemptCounter = 0;
        }

        @Override // com.mopub.mobileads.MoPubInterstitial.InterstitialAdListener
        public void onInterstitialShown(MoPubInterstitial interstitial) {
            Log.d(MoPubManager.LOG_TAG, "onInterstitialShown()");
            boolean unused = MoPubManager.mbDisplayedInterstitialAd = true;
        }

        @Override // com.mopub.mobileads.MoPubInterstitial.InterstitialAdListener
        public void onInterstitialClicked(MoPubInterstitial interstitial) {
            Log.d(MoPubManager.LOG_TAG, "onInterstitialClicked()");
        }

        @Override // com.mopub.mobileads.MoPubInterstitial.InterstitialAdListener
        public void onInterstitialDismissed(MoPubInterstitial interstitial) {
            Log.d(MoPubManager.LOG_TAG, "onInterstitialDismissed()");
            boolean unused = MoPubManager.mbDisplayedInterstitialAd = false;
            interstitial.load();
        }
    };

    static /* synthetic */ int access$404() {
        int i = miLoadAttemptCounter + 1;
        miLoadAttemptCounter = i;
        return i;
    }

    public static void Setup(MoPubView moPubView, MainActivity activity) {
        mActivity = activity;
        mAdBanner = moPubView;
        mAdBanner.setVisibility(4);
        mAdBanner.setAdUnitId("6f37a9e5f3e7448e820818db34f552fd");
        mAdBanner.setBannerAdListener(m_MoPubListener);
        mAdInterstitial = new MoPubInterstitial(mActivity, "aaae6f807e2b47b0b9a4acf48d48f749");
        mAdInterstitial.setInterstitialAdListener(m_MoPubInterstitialListener);
        mAdInterstitial.load();
        if (Build.VERSION.SDK_INT >= 11) {
            try {
                Method setLayerTypeMethod = mAdBanner.getClass().getMethod("setLayerType", Integer.TYPE, Paint.class);
                if (setLayerTypeMethod != null) {
                    setLayerTypeMethod.invoke(mAdBanner, 1, null);
                }
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (IllegalArgumentException e2) {
                e2.printStackTrace();
            } catch (NoSuchMethodException e3) {
                e3.printStackTrace();
            } catch (InvocationTargetException e4) {
                e4.printStackTrace();
            }
        }
    }

    public static void LoadBannerAd() {
        mbDisplayedBannerAd = true;
        if (mAdBanner != null) {
            mActivity.runOnUiThread(new Thread(new Runnable() { // from class: com.firsttouchgames.fts15.MoPubManager.3
                @Override // java.lang.Runnable
                public void run() throws Throwable {
                    if (MoPubManager.mbLoadedBannerAd) {
                        MoPubManager.mAdBanner.setVisibility(0);
                    } else if (!MoPubManager.mbLoadingBannerAd) {
                        MoPubManager.mAdBanner.loadAd();
                        boolean unused = MoPubManager.mbLoadingBannerAd = true;
                    }
                }
            }));
        }
    }

    public static void UnloadAd() {
        mbLoadedBannerAd = false;
        mbLoadingBannerAd = false;
        RemoveBannerAd();
    }

    public static boolean IsBannerAdDisplaying() {
        return mAdBanner.getVisibility() == 0;
    }

    public static int GetBannerAdHeight() {
        if (IsBannerAdDisplaying()) {
            return mAdBanner.getAdHeight();
        }
        return 0;
    }

    public static void RemoveBannerAd() {
        mbDisplayedBannerAd = false;
        if (mAdBanner != null) {
            mActivity.runOnUiThread(new Thread(new Runnable() { // from class: com.firsttouchgames.fts15.MoPubManager.4
                @Override // java.lang.Runnable
                public void run() {
                    MoPubManager.mAdBanner.setVisibility(4);
                }
            }));
        }
    }

    public static void LoadFullscreenAd() {
        mActivity.runOnUiThread(new Thread(new Runnable() { // from class: com.firsttouchgames.fts15.MoPubManager.5
            @Override // java.lang.Runnable
            public void run() {
                MoPubManager.mAdInterstitial.load();
            }
        }));
    }

    public static boolean DisplayFullscreenAd() {
        if (mAdInterstitial.isReady()) {
            mActivity.runOnUiThread(new Thread(new Runnable() { // from class: com.firsttouchgames.fts15.MoPubManager.6
                @Override // java.lang.Runnable
                public void run() {
                    MoPubManager.mAdInterstitial.show();
                }
            }));
            return true;
        }
        LoadFullscreenAd();
        return false;
    }

    public static boolean IsFullscreenDisplaying() {
        return mbDisplayedInterstitialAd;
    }

    public static boolean IsFullscreenAvailable() {
        if (mAdInterstitial.isReady()) {
            return true;
        }
        LoadFullscreenAd();
        return false;
    }

    public static void Destroy() {
        mbDisplayedBannerAd = false;
        mbLoadedBannerAd = false;
        mbLoadingBannerAd = false;
        mbDisplayedInterstitialAd = false;
        mActivity.runOnUiThread(new Thread(new Runnable() { // from class: com.firsttouchgames.fts15.MoPubManager.7
            @Override // java.lang.Runnable
            public void run() {
                MoPubManager.mAdBanner.destroy();
                MoPubManager.mAdInterstitial.destroy();
            }
        }));
    }
}
