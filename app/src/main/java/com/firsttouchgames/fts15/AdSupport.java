package com.firsttouchgames.fts15;

import android.app.Activity;
import android.util.Log;
import com.applovin.adview.AppLovinIncentivizedInterstitial;
import com.applovin.sdk.AppLovinAd;
import com.applovin.sdk.AppLovinAdLoadListener;
import com.applovin.sdk.AppLovinAdRewardListener;
import com.applovin.sdk.AppLovinAdVideoPlaybackListener;
import com.chartboost.sdk.CBLocation;
import com.chartboost.sdk.Chartboost;
import com.flurry.android.ads.FlurryAdErrorType;
import com.flurry.android.ads.FlurryAdInterstitial;
import com.flurry.android.ads.FlurryAdInterstitialListener;
import com.jirbo.adcolony.AdColony;
import com.jirbo.adcolony.AdColonyAd;
import com.jirbo.adcolony.AdColonyAdListener;
import com.jirbo.adcolony.AdColonyV4VCAd;
import com.jirbo.adcolony.AdColonyV4VCListener;
import com.jirbo.adcolony.AdColonyV4VCReward;
import com.unity3d.ads.android.IUnityAdsListener;
import com.unity3d.ads.android.UnityAds;
import java.util.HashMap;
import java.util.Map;

/* JADX INFO: loaded from: classes.dex */
public class AdSupport {
    public static final int AD_STATE_FINISHED = 3;
    public static final int AD_STATE_IDLE = 0;
    public static final int AD_STATE_OFFERING = 1;
    public static final int AD_STATE_PLAYING = 2;
    private static final String LOG_TAG = "AdSupport";
    private static AppLovinIncentivizedInterstitial mAppLovinVideo;
    private static MainActivity m_Act;
    static int iStatus = 0;
    public static int miZone = 0;
    private static boolean m_bAdColonyInitialised = false;
    private static boolean m_bApplifierImpactInitialised = false;
    private static boolean m_bFlurrydAdsInitialised = false;
    private static boolean m_bAppLovinInitialised = false;
    private static boolean m_bChartboostInitialised = false;
    public static String[] m_strAdColonyZoneIDs = {"vze008223fe3924c2396", "vzfaaffeca06414b05ba", "vz5c6501cd82f64b0bae", "vz300248945ea84229b0", "vz2689752fc35b4d8fb2", "vz1050437bd36446a5a4", "vzdb100f85502a4407b5"};
    public static String[] m_strFlurryAdSpaces = {"FTS15MainMenu", "FTS15Shop", "FTS15FullTime", "FTS15SeasonHub", "FTS15MatchTickets", "FTS15HelpText", "FTS15PauseMenu"};
    private static FlurryAdInterstitial[] mFlurryAd = {null, null, null, null, null, null, null};
    private static int[] m_iRewards = {30, 30, 30, 30, 30, 30, 30};
    static AdColonyAdListener m_sListerner = new AdColonyAdListener() { // from class: com.firsttouchgames.fts15.AdSupport.1
        @Override // com.jirbo.adcolony.AdColonyAdListener
        public void onAdColonyAdAttemptFinished(AdColonyAd arg0) {
            AdSupport.iStatus = 3;
        }

        @Override // com.jirbo.adcolony.AdColonyAdListener
        public void onAdColonyAdStarted(AdColonyAd arg0) {
            AdSupport.iStatus = 2;
        }
    };
    static AdColonyV4VCListener m_AdColonyListener = new AdColonyV4VCListener() { // from class: com.firsttouchgames.fts15.AdSupport.2
        @Override // com.jirbo.adcolony.AdColonyV4VCListener
        public void onAdColonyV4VCReward(AdColonyV4VCReward arg0) {
            int iReward = arg0.amount();
            if (iReward > 0) {
                AdSupport.RewardUser(iReward);
                AdSupport.SetStatus(3);
            } else {
                AdSupport.SetStatus(0);
            }
        }
    };
    static IUnityAdsListener m_UnityAdsListener = new IUnityAdsListener() { // from class: com.firsttouchgames.fts15.AdSupport.3
        @Override // com.unity3d.ads.android.IUnityAdsListener
        public void onShow() {
            Log.d(AdSupport.LOG_TAG, "onShow()");
            AdSupport.iStatus = 2;
        }

        @Override // com.unity3d.ads.android.IUnityAdsListener
        public void onHide() {
            Log.d(AdSupport.LOG_TAG, "onHide()");
            AdSupport.iStatus = 0;
        }

        @Override // com.unity3d.ads.android.IUnityAdsListener
        public void onVideoStarted() {
            Log.d(AdSupport.LOG_TAG, "onVideoStarted()");
        }

        @Override // com.unity3d.ads.android.IUnityAdsListener
        public void onVideoCompleted(String itemKey, boolean bSkipped) {
            if (bSkipped) {
                Log.d(AdSupport.LOG_TAG, "onVideoCompleted() - video skipped");
                AdSupport.iStatus = 0;
            } else {
                Log.d(AdSupport.LOG_TAG, "onVideoCompleted()");
                AdSupport.RewardUser(AdSupport.m_iRewards[AdSupport.miZone]);
                AdSupport.iStatus = 3;
            }
        }

        @Override // com.unity3d.ads.android.IUnityAdsListener
        public void onFetchCompleted() {
            Log.d(AdSupport.LOG_TAG, "onFetchCompleted()");
        }

        @Override // com.unity3d.ads.android.IUnityAdsListener
        public void onFetchFailed() {
            Log.d(AdSupport.LOG_TAG, "onFetchFailed()");
        }
    };
    static AppLovinAdLoadListener m_AppLovinVideoAdListener = new AppLovinAdLoadListener() { // from class: com.firsttouchgames.fts15.AdSupport.4
        @Override // com.applovin.sdk.AppLovinAdLoadListener
        public void adReceived(AppLovinAd ad) {
            Log.d(AdSupport.LOG_TAG, "AppLovin: Cached video ad");
        }

        @Override // com.applovin.sdk.AppLovinAdLoadListener
        public void failedToReceiveAd(int errorCode) {
            Log.d(AdSupport.LOG_TAG, "AppLovin: Failed to cache video ad");
        }
    };
    static AppLovinAdRewardListener m_AppLovinRewardListener = new AppLovinAdRewardListener() { // from class: com.firsttouchgames.fts15.AdSupport.5
        @Override // com.applovin.sdk.AppLovinAdRewardListener
        public void userRewardVerified(AppLovinAd arg0, Map response) {
            AdSupport.RewardUser(AdSupport.m_iRewards[AdSupport.miZone]);
            Log.d(AdSupport.LOG_TAG, "AppLovin: Reward received");
        }

        @Override // com.applovin.sdk.AppLovinAdRewardListener
        public void userOverQuota(AppLovinAd arg0, Map response) {
            AdSupport.iStatus = 0;
        }

        @Override // com.applovin.sdk.AppLovinAdRewardListener
        public void userRewardRejected(AppLovinAd arg0, Map response) {
            AdSupport.iStatus = 0;
            Log.d(AdSupport.LOG_TAG, "AppLovin: Reward rejected");
        }

        @Override // com.applovin.sdk.AppLovinAdRewardListener
        public void validationRequestFailed(AppLovinAd ad, int errorCode) {
            AdSupport.iStatus = 0;
        }

        @Override // com.applovin.sdk.AppLovinAdRewardListener
        public void userDeclinedToViewAd(AppLovinAd ad) {
            AdSupport.mAppLovinVideo.preload(AdSupport.m_AppLovinVideoAdListener);
            AdSupport.iStatus = 0;
            Log.d(AdSupport.LOG_TAG, "AppLovin: User declined");
        }
    };
    static AppLovinAdVideoPlaybackListener m_AppLovinPlaybackListener = new AppLovinAdVideoPlaybackListener() { // from class: com.firsttouchgames.fts15.AdSupport.6
        @Override // com.applovin.sdk.AppLovinAdVideoPlaybackListener
        public void videoPlaybackBegan(AppLovinAd ad) {
            AdSupport.iStatus = 2;
            Log.d(AdSupport.LOG_TAG, "AppLovin: Video ad began");
        }

        @Override // com.applovin.sdk.AppLovinAdVideoPlaybackListener
        public void videoPlaybackEnded(AppLovinAd ad, double percentViewed, boolean fullyWatched) {
            AdSupport.iStatus = 3;
            AdSupport.mAppLovinVideo.preload(AdSupport.m_AppLovinVideoAdListener);
            Log.d(AdSupport.LOG_TAG, "AppLovin: Video ad ended");
        }
    };
    private static FlurryAdInterstitialListener m_flurryListener = new FlurryAdInterstitialListener() { // from class: com.firsttouchgames.fts15.AdSupport.9
        @Override // com.flurry.android.ads.FlurryAdInterstitialListener
        public void onClicked(FlurryAdInterstitial adInterstitial) {
        }

        @Override // com.flurry.android.ads.FlurryAdInterstitialListener
        public void onVideoCompleted(FlurryAdInterstitial adInterstitial) {
            AdSupport.iStatus = 3;
            AdSupport.RewardUser(AdSupport.m_iRewards[AdSupport.miZone]);
        }

        @Override // com.flurry.android.ads.FlurryAdInterstitialListener
        public void onClose(FlurryAdInterstitial adInterstitial) {
            AdSupport.iStatus = 3;
            adInterstitial.fetchAd();
        }

        @Override // com.flurry.android.ads.FlurryAdInterstitialListener
        public void onFetched(FlurryAdInterstitial adInterstitial) {
            Log.d(AdSupport.LOG_TAG, "Flurry onFetched():");
        }

        @Override // com.flurry.android.ads.FlurryAdInterstitialListener
        public void onRendered(FlurryAdInterstitial adInterstitial) {
        }

        @Override // com.flurry.android.ads.FlurryAdInterstitialListener
        public void onDisplay(FlurryAdInterstitial adInterstitial) {
        }

        @Override // com.flurry.android.ads.FlurryAdInterstitialListener
        public void onAppExit(FlurryAdInterstitial adInterstitial) {
        }

        @Override // com.flurry.android.ads.FlurryAdInterstitialListener
        public void onError(FlurryAdInterstitial adInterstitial, FlurryAdErrorType adErrorType, int errorCode) {
            if (errorCode == 20) {
                Log.d(AdSupport.LOG_TAG, "Flurry - No ads available");
            } else {
                Log.d(AdSupport.LOG_TAG, "Flurry - Error " + errorCode);
            }
        }
    };

    public void OnCreate(MainActivity act) {
        AdColony.configure(act, "version:1.0,store:google", "app813523c2c97a42b795", m_strAdColonyZoneIDs[0], m_strAdColonyZoneIDs[1], m_strAdColonyZoneIDs[2], m_strAdColonyZoneIDs[3], m_strAdColonyZoneIDs[4], m_strAdColonyZoneIDs[5], m_strAdColonyZoneIDs[6]);
        m_bAdColonyInitialised = true;
        AdColony.addV4VCListener(m_AdColonyListener);
        UnityAds.setDebugMode(false);
        UnityAds.setTestMode(false);
        UnityAds.init(act, "15554", m_UnityAdsListener);
        m_bApplifierImpactInitialised = true;
        iStatus = 0;
        m_Act = act;
    }

    public static void OnStart() {
        for (int iAdSpace = 0; iAdSpace < m_strFlurryAdSpaces.length; iAdSpace++) {
            mFlurryAd[iAdSpace] = new FlurryAdInterstitial(m_Act, m_strFlurryAdSpaces[iAdSpace]);
            mFlurryAd[iAdSpace].setListener(m_flurryListener);
            mFlurryAd[iAdSpace].fetchAd();
        }
        m_bFlurrydAdsInitialised = true;
    }

    public static void OnPause() {
        AdColony.pause();
    }

    public static void OnStop() {
    }

    public static void OnResume(Activity act) {
        AdColony.resume(act);
        UnityAds.changeActivity(act);
    }

    public static void InitialiseAdColony() {
        m_bAdColonyInitialised = true;
    }

    public static void InitialiseFlurryAds() {
        m_bFlurrydAdsInitialised = true;
    }

    public static void InitialiseApplifierImpact() {
        m_bApplifierImpactInitialised = true;
    }

    public static void InitialiseAppLovin() {
        if (!m_bAppLovinInitialised) {
            m_bAppLovinInitialised = true;
            mAppLovinVideo = AppLovinIncentivizedInterstitial.create(m_Act);
            mAppLovinVideo.preload(m_AppLovinVideoAdListener);
        }
    }

    public static void InitialiseChartboost() {
        if (!m_bChartboostInitialised) {
            m_bChartboostInitialised = true;
            Chartboost.cacheRewardedVideo(CBLocation.LOCATION_DEFAULT);
        }
    }

    public static void ShutdownAdColony() {
        m_bAdColonyInitialised = false;
    }

    public static void ShutdownFlurryAds() {
        m_bFlurrydAdsInitialised = false;
    }

    public static void ShutdownApplifierImpact() {
        m_bApplifierImpactInitialised = false;
    }

    public static void ShutdownAppLovin() {
        m_bAppLovinInitialised = false;
    }

    public static void ShutdownChartboost() {
        m_bChartboostInitialised = false;
    }

    public static void PlayAdColonyVideo(final int iZone) {
        m_Act.runOnUiThread(new Thread(new Runnable() { // from class: com.firsttouchgames.fts15.AdSupport.7
            @Override // java.lang.Runnable
            public void run() {
                AdColonyV4VCAd ad = new AdColonyV4VCAd(AdSupport.m_strAdColonyZoneIDs[iZone]).withListener(AdSupport.m_sListerner).withConfirmationDialog().withResultsDialog();
                if (ad.isReady()) {
                    AdSupport.miZone = iZone;
                    ad.show();
                    AdSupport.iStatus = 2;
                }
            }
        }));
    }

    public static void PlayAdColonyVideo(final int iZone, final boolean bPrePopup, final boolean bPostPopUp) {
        m_Act.runOnUiThread(new Thread(new Runnable() { // from class: com.firsttouchgames.fts15.AdSupport.8
            @Override // java.lang.Runnable
            public void run() {
                AdColonyV4VCAd ad = new AdColonyV4VCAd(AdSupport.m_strAdColonyZoneIDs[iZone]).withListener(AdSupport.m_sListerner);
                if (bPrePopup) {
                    ad.withConfirmationDialog();
                }
                if (bPostPopUp) {
                    ad.withResultsDialog();
                }
                if (ad.isReady()) {
                    AdSupport.miZone = iZone;
                    ad.show();
                    AdSupport.iStatus = 2;
                }
            }
        }));
    }

    public static void FlurryDisplayAd(int iAdSpace) {
        Log.d(LOG_TAG, "Display Flury Ad");
        mFlurryAd[iAdSpace].displayAd();
    }

    public static void ShowApplifierImpact(int iZone) {
        if (UnityAds.canShowAds()) {
            miZone = iZone;
            HashMap<String, Object> properties = new HashMap<>();
            properties.put("noOfferScreen", true);
            UnityAds.show(properties);
        }
    }

    public static void DisplayAppLovinVideo(int iZone) {
        if (mAppLovinVideo != null) {
            miZone = iZone;
            mAppLovinVideo.show(m_Act, m_AppLovinRewardListener, m_AppLovinPlaybackListener);
        }
    }

    public static void ShowChartboostVideo(int iZone) {
        miZone = iZone;
        Chartboost.showRewardedVideo(CBLocation.LOCATION_DEFAULT);
    }

    public static void RewardUserFromZone() {
        JNI.rewardUser(m_iRewards[miZone]);
        miZone = 0;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void RewardUser(int iReward) {
        JNI.rewardUser(iReward);
        miZone = 0;
    }

    public static boolean IsAdColonyAvailable(int iZone) {
        if (!m_bAdColonyInitialised) {
            return false;
        }
        AdColonyV4VCAd ad = new AdColonyV4VCAd(m_strAdColonyZoneIDs[iZone]).withListener(m_sListerner);
        return ad.getAvailableViews() > 0;
    }

    public static boolean IsFlurryAdAvailable(int iAdSpace) {
        if (!m_bFlurrydAdsInitialised) {
            return false;
        }
        if (mFlurryAd[iAdSpace].isReady()) {
            return true;
        }
        mFlurryAd[iAdSpace].fetchAd();
        return false;
    }

    public static boolean IsApplifierImpactAvailable() {
        return m_bApplifierImpactInitialised && UnityAds.canShowAds();
    }

    public static boolean IsAppLovinVideoAvailable() {
        return m_bAppLovinInitialised && mAppLovinVideo.isAdReadyToDisplay();
    }

    public static boolean IsChartboostVideoAvailable() {
        return m_bChartboostInitialised && Chartboost.hasRewardedVideo(CBLocation.LOCATION_DEFAULT);
    }

    public static boolean AdOnScreen() {
        return iStatus == 2;
    }

    public static void SetStatusIdle() {
        iStatus = 0;
    }

    public static void SetStatusPlaying() {
        iStatus = 2;
    }

    public static void SetStatus(int iState) {
        iStatus = iState;
    }

    public static boolean IsAdColonyInitialised() {
        return m_bAdColonyInitialised;
    }

    public static boolean IsApplifierImpactInitialised() {
        return m_bApplifierImpactInitialised;
    }

    public static boolean IsFlurryAdsInitialised() {
        return m_bFlurrydAdsInitialised;
    }

    public static boolean IsAppLovinInitialised() {
        return m_bAppLovinInitialised;
    }

    public static boolean IsChartboostInitialised() {
        return m_bChartboostInitialised;
    }

    public static void setRewardAmount(int iZone, int iReward) {
        m_iRewards[iZone] = iReward;
    }
}
