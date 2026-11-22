package com.unity3d.ads.android.video;

import com.unity3d.ads.android.properties.UnityAdsConstants;
import com.unity3d.ads.android.properties.UnityAdsProperties;
import com.unity3d.ads.android.webapp.UnityAdsInstrumentation;
import com.unity3d.ads.android.webapp.UnityAdsWebData;
import java.util.HashMap;

/* JADX INFO: compiled from: UnityAdsVideoPlayView.java */
/* JADX INFO: loaded from: classes.dex */
final class s implements Runnable {
    final /* synthetic */ l a;

    s(l lVar) {
        this.a = lVar;
    }

    @Override // java.lang.Runnable
    public final void run() {
        this.a.a.i();
        if (!this.a.a.u) {
            if (this.a.a.l != null) {
                UnityAdsVideoPlayView.E(this.a.a);
                this.a.a.l.onVideoPlaybackStarted();
                this.a.a.j = System.currentTimeMillis();
                this.a.a.k = System.currentTimeMillis();
                long j = this.a.a.j - this.a.a.i;
                HashMap map = new HashMap();
                map.put(UnityAdsConstants.UNITY_ADS_GOOGLE_ANALYTICS_EVENT_BUFFERINGDURATION_KEY, Long.valueOf(j));
                UnityAdsInstrumentation.gaInstrumentationVideoPlay(UnityAdsProperties.SELECTED_CAMPAIGN, map);
            }
            if (!this.a.a.t.containsKey(UnityAdsWebData.UnityAdsVideoPosition.Start)) {
                this.a.a.t.put(UnityAdsWebData.UnityAdsVideoPosition.Start, true);
                this.a.a.l.onEventPositionReached(UnityAdsWebData.UnityAdsVideoPosition.Start);
            }
        }
    }
}
