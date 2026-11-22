package com.firsttouchgames.fts15;

import android.provider.Settings;
import android.util.Log;
import com.chartboost.sdk.CBLocation;
import com.chartboost.sdk.Chartboost;
import com.chartboost.sdk.ChartboostDelegate;
import com.chartboost.sdk.Model.CBError;

/* JADX INFO: loaded from: classes.dex */
public class ChartboostManager {
    static final String TAG = "Chartboost";
    private static ChartboostDelegate chartBoostDelegate = new ChartboostDelegate() { // from class: com.firsttouchgames.fts15.ChartboostManager.1
        @Override // com.chartboost.sdk.ChartboostDelegate, com.chartboost.sdk.a
        public boolean shouldDisplayInterstitial(String location) {
            return true;
        }

        @Override // com.chartboost.sdk.ChartboostDelegate, com.chartboost.sdk.a
        public boolean shouldRequestInterstitial(String location) {
            return true;
        }

        @Override // com.chartboost.sdk.ChartboostDelegate, com.chartboost.sdk.a
        public void didCacheInterstitial(String location) {
            boolean unused = ChartboostManager.mbIsAvailable = true;
            Log.d(ChartboostManager.TAG, "Did cache interstitial");
        }

        @Override // com.chartboost.sdk.ChartboostDelegate, com.chartboost.sdk.a
        public void didFailToLoadInterstitial(String location, CBError.CBImpressionError callback) {
            boolean unused = ChartboostManager.mbIsAvailable = false;
            Log.d(ChartboostManager.TAG, "Did fail to load insterstitial");
        }

        @Override // com.chartboost.sdk.ChartboostDelegate, com.chartboost.sdk.a
        public void didDismissInterstitial(String location) {
            boolean unused = ChartboostManager.mbIsAvailable = false;
            boolean unused2 = ChartboostManager.mbIsDisplayed = false;
            Chartboost.cacheInterstitial(location);
        }

        @Override // com.chartboost.sdk.ChartboostDelegate, com.chartboost.sdk.a
        public void didCloseInterstitial(String location) {
            boolean unused = ChartboostManager.mbIsDisplayed = false;
            Log.d(ChartboostManager.TAG, "Did close interstitial");
        }

        @Override // com.chartboost.sdk.ChartboostDelegate, com.chartboost.sdk.a
        public void didClickInterstitial(String location) {
            Log.d(ChartboostManager.TAG, "Did click interstitial");
        }

        @Override // com.chartboost.sdk.ChartboostDelegate, com.chartboost.sdk.a
        public void didDisplayInterstitial(String location) {
            boolean unused = ChartboostManager.mbIsDisplayed = true;
            Log.d(ChartboostManager.TAG, "Did show interstitial");
        }

        @Override // com.chartboost.sdk.ChartboostDelegate, com.chartboost.sdk.a
        public void didFailToRecordClick(String sURL, CBError.CBClickError callback) {
        }

        @Override // com.chartboost.sdk.ChartboostDelegate, com.chartboost.sdk.a
        public boolean shouldDisplayRewardedVideo(String location) {
            return true;
        }

        @Override // com.chartboost.sdk.ChartboostDelegate, com.chartboost.sdk.a
        public void didCacheRewardedVideo(String location) {
            Log.d(ChartboostManager.TAG, "didCacheRewardedVideo");
        }

        @Override // com.chartboost.sdk.ChartboostDelegate, com.chartboost.sdk.a
        public void didFailToLoadRewardedVideo(String location, CBError.CBImpressionError error) {
            Log.d(ChartboostManager.TAG, "didFailToLoadRewardedVideo: " + error.name());
            AdSupport.SetStatusIdle();
        }

        @Override // com.chartboost.sdk.ChartboostDelegate
        public void didDismissRewardedVideo(String location) {
            Log.d(ChartboostManager.TAG, "didDismissRewardedVideo");
        }

        @Override // com.chartboost.sdk.ChartboostDelegate, com.chartboost.sdk.a
        public void didCloseRewardedVideo(String location) {
            Log.d(ChartboostManager.TAG, "didCloseRewardedVideo");
            AdSupport.SetStatusIdle();
            if (!Chartboost.hasRewardedVideo(CBLocation.LOCATION_DEFAULT)) {
                Chartboost.cacheRewardedVideo(CBLocation.LOCATION_DEFAULT);
            }
        }

        @Override // com.chartboost.sdk.ChartboostDelegate, com.chartboost.sdk.a
        public void didClickRewardedVideo(String location) {
            Log.d(ChartboostManager.TAG, "didClickRewardedVideo");
        }

        @Override // com.chartboost.sdk.ChartboostDelegate, com.chartboost.sdk.a
        public void didCompleteRewardedVideo(String location, int iReward) {
            Log.d(ChartboostManager.TAG, "didCompleteRewardedVideo");
            AdSupport.RewardUserFromZone();
        }

        @Override // com.chartboost.sdk.ChartboostDelegate, com.chartboost.sdk.a
        public void didDisplayRewardedVideo(String location) {
            Log.d(ChartboostManager.TAG, "didDisplayRewardedVideo");
        }

        @Override // com.chartboost.sdk.ChartboostDelegate, com.chartboost.sdk.a
        public void willDisplayVideo(String location) {
            Log.d(ChartboostManager.TAG, "willDisplayVideo");
            AdSupport.SetStatusPlaying();
        }
    };
    private static MainActivity mActivity;
    private static boolean mbIsAvailable;
    private static boolean mbIsDisplayed;
    private static boolean mbIsInitialised;

    public static void OnCreate(MainActivity activity, String appID, String appSignature) {
        mActivity = activity;
        Chartboost.startWithAppId(mActivity, appID, appSignature);
        Chartboost.setDelegate(chartBoostDelegate);
        Chartboost.onCreate(mActivity);
        mbIsDisplayed = false;
        mbIsAvailable = false;
        mbIsInitialised = true;
        String android_id = Settings.Secure.getString(mActivity.getBaseContext().getContentResolver(), "android_id");
        Log.e(TAG, android_id);
    }

    public static boolean IsInitialised() {
        return mbIsInitialised;
    }

    public static void CacheInterstitial() {
        Chartboost.cacheInterstitial(CBLocation.LOCATION_DEFAULT);
    }

    public static boolean ShowInterstitial() {
        if (mbIsAvailable) {
            Chartboost.showInterstitial(CBLocation.LOCATION_DEFAULT);
            return true;
        }
        CacheInterstitial();
        return false;
    }

    public static void onStart() {
        Chartboost.onStart(mActivity);
    }

    public static void onResume() {
        Chartboost.onResume(mActivity);
    }

    public static void onPause() {
        Chartboost.onPause(mActivity);
    }

    public static void onStop() {
        Chartboost.onStop(mActivity);
    }

    public static void onDestroy() {
        Chartboost.onDestroy(mActivity);
        mbIsInitialised = false;
    }

    public static boolean onBackPressed() {
        return Chartboost.onBackPressed();
    }

    public static boolean IsDisplayed() {
        return mbIsDisplayed;
    }

    public static boolean IsAvailable() {
        return mbIsAvailable;
    }
}
