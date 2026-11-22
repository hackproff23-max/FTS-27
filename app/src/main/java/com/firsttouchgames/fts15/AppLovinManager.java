package com.firsttouchgames.fts15;

import android.util.Log;
import com.applovin.adview.AppLovinInterstitialAd;
import com.applovin.adview.AppLovinInterstitialAdDialog;
import com.applovin.sdk.AppLovinAd;
import com.applovin.sdk.AppLovinAdDisplayListener;
import com.applovin.sdk.AppLovinAdLoadListener;
import com.applovin.sdk.AppLovinAdSize;
import com.applovin.sdk.AppLovinSdk;

/* JADX INFO: loaded from: classes.dex */
public class AppLovinManager {
    static final String TAG = "AppLovinManager";
    private static MainActivity mActivity;
    private static boolean mIsDisplaying;
    private static AppLovinAd mCachedAd = null;
    private static AppLovinAdDisplayListener appLovinAdDisplayDelegate = new AppLovinAdDisplayListener() { // from class: com.firsttouchgames.fts15.AppLovinManager.1
        @Override // com.applovin.sdk.AppLovinAdDisplayListener
        public void adDisplayed(AppLovinAd arg0) {
            Log.d(AppLovinManager.TAG, "adDisplayed");
            boolean unused = AppLovinManager.mIsDisplaying = true;
        }

        @Override // com.applovin.sdk.AppLovinAdDisplayListener
        public void adHidden(AppLovinAd arg0) {
            Log.d(AppLovinManager.TAG, "adHidden");
            AppLovinManager.CacheInterstitial();
            boolean unused = AppLovinManager.mIsDisplaying = false;
        }
    };

    public static void Initialise(MainActivity activity) {
        mActivity = activity;
        AppLovinSdk.initializeSdk(activity);
        mIsDisplaying = false;
        CacheInterstitial();
    }

    public static boolean IsInitialised() {
        return mActivity != null;
    }

    public static void CacheInterstitial() {
        if (mActivity != null) {
            AppLovinSdk.getInstance(mActivity).getAdService().loadNextAd(AppLovinAdSize.INTERSTITIAL, new AppLovinAdLoadListener() { // from class: com.firsttouchgames.fts15.AppLovinManager.2
                @Override // com.applovin.sdk.AppLovinAdLoadListener
                public void adReceived(AppLovinAd ad) {
                    Log.d(AppLovinManager.TAG, "adReceived");
                    AppLovinAd unused = AppLovinManager.mCachedAd = ad;
                }

                @Override // com.applovin.sdk.AppLovinAdLoadListener
                public void failedToReceiveAd(int iErrorCode) {
                    Log.d(AppLovinManager.TAG, "failedToReceiveAd, error code: " + iErrorCode);
                }
            });
        }
    }

    public static void ShowInterstitial() {
        AppLovinInterstitialAdDialog adDialog = AppLovinInterstitialAd.create(AppLovinSdk.getInstance(mActivity), mActivity);
        adDialog.setAdDisplayListener(appLovinAdDisplayDelegate);
        adDialog.showAndRender(mCachedAd);
    }

    public static boolean IsAvailable() {
        return mCachedAd != null;
    }

    public static boolean IsDisplaying() {
        return mIsDisplaying;
    }
}
