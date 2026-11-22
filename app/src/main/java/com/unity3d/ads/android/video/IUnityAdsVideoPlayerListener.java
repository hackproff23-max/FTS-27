package com.unity3d.ads.android.video;

import android.media.MediaPlayer;
import com.unity3d.ads.android.view.IUnityAdsViewListener;
import com.unity3d.ads.android.webapp.UnityAdsWebData;

/* JADX INFO: loaded from: classes.dex */
public interface IUnityAdsVideoPlayerListener extends MediaPlayer.OnCompletionListener, IUnityAdsViewListener {
    void onEventPositionReached(UnityAdsWebData.UnityAdsVideoPosition unityAdsVideoPosition);

    void onVideoHidden();

    void onVideoPlaybackError();

    void onVideoPlaybackStarted();

    void onVideoSkip();
}
