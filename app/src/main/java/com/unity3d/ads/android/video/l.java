package com.unity3d.ads.android.video;

import android.os.PowerManager;
import com.unity3d.ads.android.UnityAdsDeviceLog;
import com.unity3d.ads.android.UnityAdsUtils;
import com.unity3d.ads.android.webapp.UnityAdsWebData;
import java.util.TimerTask;

/* JADX INFO: compiled from: UnityAdsVideoPlayView.java */
/* JADX INFO: loaded from: classes.dex */
final class l extends TimerTask {
    final /* synthetic */ UnityAdsVideoPlayView a;
    private Float b;
    private Float c;
    private Float d;
    private int e;
    private boolean f;
    private boolean g;

    private l(UnityAdsVideoPlayView unityAdsVideoPlayView) {
        this.a = unityAdsVideoPlayView;
        this.b = Float.valueOf(0.0f);
        this.c = Float.valueOf(0.0f);
        this.d = Float.valueOf(0.01f);
        this.e = 1;
        this.f = false;
        this.g = false;
    }

    /* synthetic */ l(UnityAdsVideoPlayView unityAdsVideoPlayView, byte b) {
        this(unityAdsVideoPlayView);
    }

    @Override // java.util.TimerTask, java.lang.Runnable
    public final void run() {
        int duration;
        int bufferPercentage = 0;
        if (this.a.n == null || this.a.b == null) {
            this.a.d();
            return;
        }
        if (!((PowerManager) this.a.getContext().getSystemService("power")).isScreenOn()) {
            this.a.pauseVideo();
        }
        this.c = this.b;
        try {
            this.b = Float.valueOf(this.a.n.getCurrentPosition());
        } catch (Exception e) {
            UnityAdsDeviceLog.error("Could not get videoView currentPosition");
            if (this.c.floatValue() > 0.0f) {
                this.b = this.c;
            } else {
                this.b = Float.valueOf(0.01f);
            }
        }
        Float.valueOf(0.0f);
        Boolean bool = true;
        try {
            duration = this.a.n.getDuration();
        } catch (Exception e2) {
            UnityAdsDeviceLog.error("Could not get videoView duration");
            bool = false;
            duration = 1;
        }
        if (bool.booleanValue()) {
            this.e = duration;
        }
        Float fValueOf = Float.valueOf(this.b.floatValue() / this.e);
        if (this.b.floatValue() > this.c.floatValue()) {
            this.f = true;
            this.g = false;
            UnityAdsVideoPlayView unityAdsVideoPlayView = this.a;
            UnityAdsVideoPlayView unityAdsVideoPlayView2 = this.a;
            UnityAdsUtils.runOnUiThread(new c(unityAdsVideoPlayView, 4, UnityAdsVideoPlayView.f(), this.d.floatValue() <= 0.0f));
        } else {
            this.g = true;
            UnityAdsUtils.runOnUiThread(new c(this.a, 0, true, true));
        }
        UnityAdsUtils.runOnUiThread(new m(this));
        UnityAdsVideoPlayView unityAdsVideoPlayView3 = this.a;
        if (UnityAdsVideoPlayView.f() && this.a.e > 0 && this.d.floatValue() > 0.0f && this.e / 1000 > this.a.e) {
            this.d = Float.valueOf((this.a.e * 1000) - this.b.floatValue());
            if (this.d.floatValue() < 0.0f) {
                this.d = Float.valueOf(0.0f);
            }
            if (this.d.floatValue() == 0.0f) {
                UnityAdsUtils.runOnUiThread(new n(this));
            } else {
                UnityAdsUtils.runOnUiThread(new o(this));
            }
        } else if (this.f && this.e / 1000 <= this.a.e) {
            UnityAdsUtils.runOnUiThread(new p(this));
        }
        if (fValueOf.floatValue() > 0.25d && !this.a.t.containsKey(UnityAdsWebData.UnityAdsVideoPosition.FirstQuartile)) {
            this.a.l.onEventPositionReached(UnityAdsWebData.UnityAdsVideoPosition.FirstQuartile);
            this.a.t.put(UnityAdsWebData.UnityAdsVideoPosition.FirstQuartile, true);
        }
        if (fValueOf.floatValue() > 0.5d && !this.a.t.containsKey(UnityAdsWebData.UnityAdsVideoPosition.MidPoint)) {
            this.a.l.onEventPositionReached(UnityAdsWebData.UnityAdsVideoPosition.MidPoint);
            this.a.t.put(UnityAdsWebData.UnityAdsVideoPosition.MidPoint, true);
        }
        if (fValueOf.floatValue() > 0.75d && !this.a.t.containsKey(UnityAdsWebData.UnityAdsVideoPosition.ThirdQuartile)) {
            this.a.l.onEventPositionReached(UnityAdsWebData.UnityAdsVideoPosition.ThirdQuartile);
            this.a.t.put(UnityAdsWebData.UnityAdsVideoPosition.ThirdQuartile, true);
        }
        try {
            bufferPercentage = this.a.n.getBufferPercentage();
        } catch (Exception e3) {
            UnityAdsDeviceLog.error("Could not get videoView buffering percentage");
        }
        if (!this.f && this.a.i > 0 && System.currentTimeMillis() - this.a.i > 20000) {
            cancel();
            UnityAdsUtils.runOnUiThread(new q(this));
        }
        if (this.a.n != null && bufferPercentage < 15 && this.a.n.getParent() == null) {
            UnityAdsUtils.runOnUiThread(new r(this));
        }
        if (this.a.s && this.f) {
            UnityAdsUtils.runOnUiThread(new s(this));
        }
    }
}
