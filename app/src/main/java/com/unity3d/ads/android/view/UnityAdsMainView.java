package com.unity3d.ads.android.view;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Context;
import android.media.MediaPlayer;
import android.os.Build;
import android.util.AttributeSet;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;
import com.unity3d.ads.android.UnityAds;
import com.unity3d.ads.android.UnityAdsDeviceLog;
import com.unity3d.ads.android.campaign.UnityAdsCampaign;
import com.unity3d.ads.android.properties.UnityAdsConstants;
import com.unity3d.ads.android.properties.UnityAdsProperties;
import com.unity3d.ads.android.video.IUnityAdsVideoPlayerListener;
import com.unity3d.ads.android.video.UnityAdsVideoPlayView;
import com.unity3d.ads.android.webapp.IUnityAdsWebBridgeListener;
import com.unity3d.ads.android.webapp.IUnityAdsWebViewListener;
import com.unity3d.ads.android.webapp.UnityAdsInstrumentation;
import com.unity3d.ads.android.webapp.UnityAdsWebBridge;
import com.unity3d.ads.android.webapp.UnityAdsWebData;
import com.unity3d.ads.android.webapp.UnityAdsWebView;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import org.json.JSONArray;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes.dex */
@TargetApi(9)
public class UnityAdsMainView extends RelativeLayout implements IUnityAdsVideoPlayerListener, IUnityAdsWebViewListener {
    private IUnityAdsMainViewListener a;
    private IUnityAdsWebBridgeListener b;
    private UnityAdsMainViewState c;
    public UnityAdsVideoPlayView videoplayerview;
    public UnityAdsWebView webview;

    public enum UnityAdsMainViewAction {
        VideoStart,
        VideoEnd,
        VideoSkipped,
        BackButtonPressed,
        RequestRetryVideoPlay
    }

    public enum UnityAdsMainViewState {
        WebView,
        VideoPlayer
    }

    public UnityAdsMainView(Context context, IUnityAdsMainViewListener iUnityAdsMainViewListener, IUnityAdsWebBridgeListener iUnityAdsWebBridgeListener) {
        super(context);
        this.videoplayerview = null;
        this.webview = null;
        this.a = null;
        this.b = null;
        this.c = UnityAdsMainViewState.WebView;
        this.a = iUnityAdsMainViewListener;
        this.b = iUnityAdsWebBridgeListener;
        a();
    }

    public UnityAdsMainView(Context context) {
        super(context);
        this.videoplayerview = null;
        this.webview = null;
        this.a = null;
        this.b = null;
        this.c = UnityAdsMainViewState.WebView;
        a();
    }

    public UnityAdsMainView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.videoplayerview = null;
        this.webview = null;
        this.a = null;
        this.b = null;
        this.c = UnityAdsMainViewState.WebView;
        a();
    }

    public UnityAdsMainView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.videoplayerview = null;
        this.webview = null;
        this.a = null;
        this.b = null;
        this.c = UnityAdsMainViewState.WebView;
        a();
    }

    public void openAds(String str, JSONObject jSONObject) {
        if (UnityAdsProperties.getCurrentActivity() != null && (UnityAdsProperties.getCurrentActivity() instanceof UnityAdsFullscreenActivity)) {
            this.webview.setWebViewCurrentView(str, jSONObject);
            if (getParent() != null && ((ViewGroup) getParent()) != null) {
                ((ViewGroup) getParent()).removeView(this);
            }
            if (getParent() == null) {
                UnityAdsProperties.getCurrentActivity().addContentView(this, new FrameLayout.LayoutParams(-1, -1));
            }
            setViewState(UnityAdsMainViewState.WebView);
            return;
        }
        UnityAdsDeviceLog.error("Cannot open, wrong activity");
    }

    public void fixActivityAttachment() {
        if (getParent() != null && ((ViewGroup) getParent()) != null) {
            ((ViewGroup) getParent()).removeView(this);
        }
        UnityAdsProperties.getCurrentActivity().addContentView(this, new FrameLayout.LayoutParams(-1, -1));
    }

    public void closeAds(JSONObject jSONObject) {
        ViewGroup viewGroup;
        if (getParent() != null && (viewGroup = (ViewGroup) getParent()) != null) {
            viewGroup.removeView(this);
        }
        b();
        UnityAdsProperties.SELECTED_CAMPAIGN = null;
        this.webview.destroy();
        this.webview = null;
    }

    public void setViewState(UnityAdsMainViewState unityAdsMainViewState) {
        if (!this.c.equals(unityAdsMainViewState)) {
            this.c = unityAdsMainViewState;
            switch (a.a[unityAdsMainViewState.ordinal()]) {
                case 1:
                    a(this.webview);
                    addView(this.webview, new FrameLayout.LayoutParams(-1, -1));
                    b(this.webview);
                    break;
                case 2:
                    if (this.videoplayerview == null) {
                        this.videoplayerview = new UnityAdsVideoPlayView(UnityAdsProperties.getCurrentActivity().getBaseContext(), this);
                        this.videoplayerview.setLayoutParams(new FrameLayout.LayoutParams(-1, -1));
                        this.videoplayerview.setId(1002);
                        addView(this.videoplayerview);
                        bringChildToFront(this.webview);
                        b(this.webview);
                    }
                    break;
            }
        }
    }

    public UnityAdsMainViewState getViewState() {
        return this.c;
    }

    public void afterVideoPlaybackOperations() {
        if (this.videoplayerview != null) {
            this.videoplayerview.setKeepScreenOn(false);
        }
        b();
        setViewState(UnityAdsMainViewState.WebView);
        Activity currentActivity = UnityAdsProperties.getCurrentActivity();
        if (currentActivity != null) {
            currentActivity.setRequestedOrientation(-1);
        }
    }

    @Override // android.view.View, android.view.KeyEvent.Callback
    public boolean onKeyDown(int i, KeyEvent keyEvent) {
        switch (i) {
            case 4:
                onBackButtonClicked(this);
                return true;
            default:
                return false;
        }
    }

    @Override // android.view.ViewGroup, android.view.View
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        b(this);
    }

    private void a() {
        UnityAdsDeviceLog.entered();
        setId(1001);
        this.webview = new UnityAdsWebView(UnityAdsProperties.getCurrentActivity(), this, new UnityAdsWebBridge(this.b));
        this.webview.setId(1003);
        addView(this.webview, new FrameLayout.LayoutParams(-1, -1));
    }

    private void b() {
        UnityAdsDeviceLog.entered();
        if (this.videoplayerview != null) {
            this.videoplayerview.clearVideoPlayer();
        }
        a(this.videoplayerview);
        this.videoplayerview = null;
    }

    private static void a(View view) {
        if (view != null) {
            view.setFocusable(false);
            view.setFocusableInTouchMode(false);
            ViewGroup viewGroup = (ViewGroup) view.getParent();
            if (viewGroup != null) {
                viewGroup.removeView(view);
            }
        }
    }

    private static void b(View view) {
        if (view != null) {
            view.setFocusable(true);
            view.setFocusableInTouchMode(true);
            view.requestFocus();
        }
    }

    private void a(UnityAdsMainViewAction unityAdsMainViewAction) {
        if (this.a != null) {
            this.a.onMainViewAction(unityAdsMainViewAction);
        }
    }

    @Override // com.unity3d.ads.android.view.IUnityAdsViewListener
    public void onBackButtonClicked(View view) {
        UnityAdsDeviceLog.debug("Current state: " + this.c.toString());
        if (this.videoplayerview != null) {
            UnityAdsDeviceLog.debug("Seconds: " + this.videoplayerview.getSecondsUntilBackButtonAllowed());
        }
        if ((UnityAdsProperties.SELECTED_CAMPAIGN != null && UnityAdsProperties.SELECTED_CAMPAIGN.isViewed().booleanValue()) || this.c != UnityAdsMainViewState.VideoPlayer || ((this.c == UnityAdsMainViewState.VideoPlayer && this.videoplayerview != null && this.videoplayerview.getSecondsUntilBackButtonAllowed() == 0) || (this.c == UnityAdsMainViewState.VideoPlayer && UnityAdsWebData.getZoneManager().getCurrentZone().disableBackButtonForSeconds() == 0))) {
            a(UnityAdsMainViewAction.BackButtonPressed);
        } else {
            UnityAdsDeviceLog.debug("Prevented back-button");
        }
    }

    @Override // com.unity3d.ads.android.video.IUnityAdsVideoPlayerListener
    public void onVideoPlaybackStarted() {
        UnityAdsDeviceLog.entered();
        JSONObject jSONObject = new JSONObject();
        JSONObject jSONObject2 = new JSONObject();
        try {
            jSONObject.put("campaignId", UnityAdsProperties.SELECTED_CAMPAIGN.getCampaignId());
            jSONObject2.put(UnityAdsConstants.UNITY_ADS_TEXTKEY_KEY, UnityAdsConstants.UNITY_ADS_TEXTKEY_BUFFERING);
        } catch (Exception e) {
            UnityAdsDeviceLog.error("Could not create JSON");
        }
        a(UnityAdsMainViewAction.VideoStart);
        bringChildToFront(this.videoplayerview);
        int i = Build.VERSION.SDK_INT < 9 ? 0 : 6;
        if (UnityAdsWebData.getZoneManager().getCurrentZone().useDeviceOrientationForVideo()) {
            UnityAdsProperties.getCurrentActivity().setRequestedOrientation(0);
            i = -1;
        }
        Activity currentActivity = UnityAdsProperties.getCurrentActivity();
        if (currentActivity != null) {
            currentActivity.setRequestedOrientation(i);
        }
        b(this.videoplayerview);
        if (this.webview != null) {
            this.webview.sendNativeEventToWebApp(UnityAdsConstants.UNITY_ADS_NATIVEEVENT_HIDESPINNER, jSONObject2);
            this.webview.setWebViewCurrentView("completed", jSONObject);
        }
    }

    @Override // com.unity3d.ads.android.video.IUnityAdsVideoPlayerListener
    public void onEventPositionReached(UnityAdsWebData.UnityAdsVideoPosition unityAdsVideoPosition) {
        if (UnityAdsProperties.SELECTED_CAMPAIGN != null && !UnityAdsProperties.SELECTED_CAMPAIGN.getCampaignStatus().equals(UnityAdsCampaign.UnityAdsCampaignStatus.VIEWED)) {
            UnityAds.webdata.sendCampaignViewProgress(UnityAdsProperties.SELECTED_CAMPAIGN, unityAdsVideoPosition);
        }
    }

    @Override // android.media.MediaPlayer.OnCompletionListener
    public void onCompletion(MediaPlayer mediaPlayer) {
        UnityAdsDeviceLog.entered();
        afterVideoPlaybackOperations();
        onEventPositionReached(UnityAdsWebData.UnityAdsVideoPosition.End);
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("campaignId", UnityAdsProperties.SELECTED_CAMPAIGN.getCampaignId());
        } catch (Exception e) {
            UnityAdsDeviceLog.error("Could not create JSON");
        }
        this.webview.sendNativeEventToWebApp(UnityAdsConstants.UNITY_ADS_NATIVEEVENT_VIDEOCOMPLETED, jSONObject);
        a(UnityAdsMainViewAction.VideoEnd);
    }

    @Override // com.unity3d.ads.android.video.IUnityAdsVideoPlayerListener
    public void onVideoPlaybackError() {
        afterVideoPlaybackOperations();
        UnityAdsDeviceLog.entered();
        UnityAds.webdata.sendAnalyticsRequest(UnityAdsConstants.UNITY_ADS_ANALYTICS_EVENTTYPE_VIDEOERROR, UnityAdsProperties.SELECTED_CAMPAIGN);
        JSONObject jSONObject = new JSONObject();
        JSONObject jSONObject2 = new JSONObject();
        JSONObject jSONObject3 = new JSONObject();
        try {
            jSONObject.put(UnityAdsConstants.UNITY_ADS_TEXTKEY_KEY, UnityAdsConstants.UNITY_ADS_TEXTKEY_VIDEOPLAYBACKERROR);
            jSONObject2.put(UnityAdsConstants.UNITY_ADS_TEXTKEY_KEY, UnityAdsConstants.UNITY_ADS_TEXTKEY_BUFFERING);
            jSONObject3.put("campaignId", UnityAdsProperties.SELECTED_CAMPAIGN.getCampaignId());
        } catch (Exception e) {
            UnityAdsDeviceLog.error("Could not create JSON");
        }
        if (this.webview != null) {
            this.webview.setWebViewCurrentView("completed", jSONObject3);
            this.webview.sendNativeEventToWebApp(UnityAdsConstants.UNITY_ADS_NATIVEEVENT_SHOWERROR, jSONObject);
            this.webview.sendNativeEventToWebApp(UnityAdsConstants.UNITY_ADS_NATIVEEVENT_VIDEOCOMPLETED, jSONObject3);
            this.webview.sendNativeEventToWebApp(UnityAdsConstants.UNITY_ADS_NATIVEEVENT_HIDESPINNER, jSONObject2);
        }
        if (UnityAdsProperties.SELECTED_CAMPAIGN != null) {
            UnityAdsProperties.SELECTED_CAMPAIGN.setCampaignStatus(UnityAdsCampaign.UnityAdsCampaignStatus.VIEWED);
            UnityAdsProperties.SELECTED_CAMPAIGN = null;
        }
    }

    @Override // com.unity3d.ads.android.video.IUnityAdsVideoPlayerListener
    public void onVideoSkip() {
        HashMap map = new HashMap();
        map.put(UnityAdsConstants.UNITY_ADS_GOOGLE_ANALYTICS_EVENT_BUFFERINGDURATION_KEY, Long.valueOf(this.videoplayerview.getBufferingDuration()));
        map.put(UnityAdsConstants.UNITY_ADS_GOOGLE_ANALYTICS_EVENT_VALUE_KEY, UnityAdsConstants.UNITY_ADS_GOOGLE_ANALYTICS_EVENT_VIDEOABORT_SKIP);
        UnityAdsInstrumentation.gaInstrumentationVideoAbort(UnityAdsProperties.SELECTED_CAMPAIGN, map);
        afterVideoPlaybackOperations();
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("campaignId", UnityAdsProperties.SELECTED_CAMPAIGN.getCampaignId());
        } catch (Exception e) {
            UnityAdsDeviceLog.error("Could not create JSON");
        }
        this.webview.sendNativeEventToWebApp(UnityAdsConstants.UNITY_ADS_NATIVEEVENT_VIDEOCOMPLETED, jSONObject);
        a(UnityAdsMainViewAction.VideoSkipped);
    }

    @Override // com.unity3d.ads.android.video.IUnityAdsVideoPlayerListener
    public void onVideoHidden() {
        HashMap map = new HashMap();
        if (this.videoplayerview != null) {
            map.put(UnityAdsConstants.UNITY_ADS_GOOGLE_ANALYTICS_EVENT_BUFFERINGDURATION_KEY, Long.valueOf(this.videoplayerview.getBufferingDuration()));
        }
        map.put(UnityAdsConstants.UNITY_ADS_GOOGLE_ANALYTICS_EVENT_VALUE_KEY, UnityAdsConstants.UNITY_ADS_GOOGLE_ANALYTICS_EVENT_VIDEOABORT_HIDDEN);
        UnityAdsInstrumentation.gaInstrumentationVideoAbort(UnityAdsProperties.SELECTED_CAMPAIGN, map);
        if (this.videoplayerview != null) {
            this.videoplayerview.setKeepScreenOn(false);
            this.videoplayerview.hideVideo();
            this.videoplayerview = null;
        }
        setViewState(UnityAdsMainViewState.WebView);
        Activity currentActivity = UnityAdsProperties.getCurrentActivity();
        if (currentActivity != null) {
            currentActivity.setRequestedOrientation(-1);
        }
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("campaignId", UnityAdsProperties.SELECTED_CAMPAIGN.getCampaignId());
        } catch (Exception e) {
            UnityAdsDeviceLog.error("Could not create JSON");
        }
        this.webview.sendNativeEventToWebApp(UnityAdsConstants.UNITY_ADS_NATIVEEVENT_VIDEOCOMPLETED, jSONObject);
        a(UnityAdsMainViewAction.VideoSkipped);
    }

    @Override // com.unity3d.ads.android.webapp.IUnityAdsWebViewListener
    public void onWebAppLoaded() {
        try {
            ArrayList viewableVideoPlanCampaigns = UnityAds.webdata.getViewableVideoPlanCampaigns();
            JSONObject data = UnityAds.webdata.getData();
            JSONArray jSONArray = data.getJSONObject("data").getJSONArray(UnityAdsConstants.UNITY_ADS_CAMPAIGNS_KEY);
            JSONArray jSONArray2 = new JSONArray();
            for (int i = 0; i < jSONArray.length(); i++) {
                JSONObject jSONObject = jSONArray.getJSONObject(i);
                String string = jSONObject.getString("id");
                Iterator it = viewableVideoPlanCampaigns.iterator();
                while (it.hasNext()) {
                    if (((UnityAdsCampaign) it.next()).getCampaignId() == string) {
                        jSONArray2.put(jSONObject);
                    }
                }
            }
            data.getJSONObject("data").put(UnityAdsConstants.UNITY_ADS_CAMPAIGNS_KEY, jSONArray2);
            this.webview.initWebApp(data);
        } catch (Exception e) {
        }
    }
}
