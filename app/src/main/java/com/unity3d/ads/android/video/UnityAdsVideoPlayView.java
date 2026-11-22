package com.unity3d.ads.android.video;

import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.Rect;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.support.v4.internal.view.SupportMenu;
import android.support.v4.view.ViewCompat;
import android.util.AttributeSet;
import android.view.KeyEvent;
import android.view.TouchDelegate;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.VideoView;
import com.google.android.gms.games.GamesActivityResultCodes;
import com.google.android.gms.games.GamesStatusCodes;
import com.unity3d.ads.android.UnityAdsDeviceLog;
import com.unity3d.ads.android.UnityAdsUtils;
import com.unity3d.ads.android.properties.UnityAdsConstants;
import com.unity3d.ads.android.properties.UnityAdsProperties;
import com.unity3d.ads.android.view.UnityAdsBufferingView;
import com.unity3d.ads.android.view.UnityAdsMuteVideoButton;
import com.unity3d.ads.android.webapp.UnityAdsInstrumentation;
import com.unity3d.ads.android.webapp.UnityAdsWebData;
import com.unity3d.ads.android.zone.UnityAdsZone;
import java.util.HashMap;
import java.util.Map;
import java.util.Timer;

/* JADX INFO: loaded from: classes.dex */
@TargetApi(9)
public class UnityAdsVideoPlayView extends RelativeLayout {
    private RelativeLayout a;
    private TextView b;
    private RelativeLayout c;
    private TextView d;
    private long e;
    private RelativeLayout f;
    private TextView g;
    private RelativeLayout h;
    private long i;
    private long j;
    private long k;
    private IUnityAdsVideoPlayerListener l;
    private Timer m;
    private VideoView n;
    private String o;
    private UnityAdsBufferingView p;
    private UnityAdsVideoPausedView q;
    private UnityAdsMuteVideoButton r;
    private boolean s;
    private Map t;
    private boolean u;
    private boolean v;
    private boolean w;
    private MediaPlayer x;
    private boolean y;
    private float z;

    static /* synthetic */ void C(UnityAdsVideoPlayView unityAdsVideoPlayView) {
        if (unityAdsVideoPlayView.p == null) {
            unityAdsVideoPlayView.p = new UnityAdsBufferingView(unityAdsVideoPlayView.getContext());
        }
        if (unityAdsVideoPlayView.p == null || unityAdsVideoPlayView.p.getParent() != null) {
            return;
        }
        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(-2, -2);
        layoutParams.addRule(13);
        unityAdsVideoPlayView.addView(unityAdsVideoPlayView.p, layoutParams);
    }

    static /* synthetic */ boolean E(UnityAdsVideoPlayView unityAdsVideoPlayView) {
        unityAdsVideoPlayView.u = true;
        return true;
    }

    static /* synthetic */ void c(UnityAdsVideoPlayView unityAdsVideoPlayView) {
        if (unityAdsVideoPlayView.q == null) {
            unityAdsVideoPlayView.q = new UnityAdsVideoPausedView(unityAdsVideoPlayView.getContext());
        }
        if (unityAdsVideoPlayView.q == null || unityAdsVideoPlayView.q.getParent() != null) {
            return;
        }
        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(-1, -1);
        layoutParams.addRule(13);
        unityAdsVideoPlayView.addView(unityAdsVideoPlayView.q, layoutParams);
    }

    static /* synthetic */ boolean f(UnityAdsVideoPlayView unityAdsVideoPlayView) {
        unityAdsVideoPlayView.w = true;
        return true;
    }

    static /* synthetic */ void h(UnityAdsVideoPlayView unityAdsVideoPlayView) {
        AudioManager audioManager = (AudioManager) UnityAdsProperties.getCurrentActivity().getSystemService("audio");
        if (audioManager != null) {
            int streamVolume = audioManager.getStreamVolume(3);
            int streamMaxVolume = audioManager.getStreamMaxVolume(3);
            float f = 1.0f / streamMaxVolume;
            unityAdsVideoPlayView.z = streamVolume * f;
            UnityAdsDeviceLog.debug("Storing volume: " + streamVolume + ", " + streamMaxVolume + ", " + f + ", " + unityAdsVideoPlayView.z);
        }
    }

    static /* synthetic */ boolean j(UnityAdsVideoPlayView unityAdsVideoPlayView) {
        unityAdsVideoPlayView.s = true;
        return true;
    }

    static /* synthetic */ void t(UnityAdsVideoPlayView unityAdsVideoPlayView) {
        if (unityAdsVideoPlayView.c != null) {
            unityAdsVideoPlayView.c.setVisibility(0);
            unityAdsVideoPlayView.c.setClickable(true);
            unityAdsVideoPlayView.c.setBackgroundColor(33554431);
            unityAdsVideoPlayView.c.setFocusable(true);
            unityAdsVideoPlayView.d.setText("Skip video");
            unityAdsVideoPlayView.c.requestFocus();
            Rect rect = new Rect();
            unityAdsVideoPlayView.c.getHitRect(rect);
            int i = rect.bottom - rect.top;
            rect.bottom += i * 2;
            rect.right = (i * 2) + rect.right;
            ((View) unityAdsVideoPlayView.c.getParent()).setTouchDelegate(new TouchDelegate(rect, unityAdsVideoPlayView.c));
        }
    }

    public UnityAdsVideoPlayView(Context context, IUnityAdsVideoPlayerListener iUnityAdsVideoPlayerListener) {
        super(context);
        this.a = null;
        this.b = null;
        this.c = null;
        this.d = null;
        this.e = 0L;
        this.f = null;
        this.g = null;
        this.h = null;
        this.i = 0L;
        this.j = 0L;
        this.k = 0L;
        this.m = null;
        this.n = null;
        this.o = null;
        this.p = null;
        this.q = null;
        this.r = null;
        this.s = false;
        this.t = new HashMap();
        this.u = false;
        this.v = false;
        this.w = false;
        this.x = null;
        this.y = false;
        this.z = 0.5f;
        this.l = iUnityAdsVideoPlayerListener;
        e();
    }

    public UnityAdsVideoPlayView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.a = null;
        this.b = null;
        this.c = null;
        this.d = null;
        this.e = 0L;
        this.f = null;
        this.g = null;
        this.h = null;
        this.i = 0L;
        this.j = 0L;
        this.k = 0L;
        this.m = null;
        this.n = null;
        this.o = null;
        this.p = null;
        this.q = null;
        this.r = null;
        this.s = false;
        this.t = new HashMap();
        this.u = false;
        this.v = false;
        this.w = false;
        this.x = null;
        this.y = false;
        this.z = 0.5f;
        e();
    }

    public UnityAdsVideoPlayView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.a = null;
        this.b = null;
        this.c = null;
        this.d = null;
        this.e = 0L;
        this.f = null;
        this.g = null;
        this.h = null;
        this.i = 0L;
        this.j = 0L;
        this.k = 0L;
        this.m = null;
        this.n = null;
        this.o = null;
        this.p = null;
        this.q = null;
        this.r = null;
        this.s = false;
        this.t = new HashMap();
        this.u = false;
        this.v = false;
        this.w = false;
        this.x = null;
        this.y = false;
        this.z = 0.5f;
        e();
    }

    public void playVideo(String str) {
        if (str != null) {
            this.s = false;
            this.o = str;
            UnityAdsDeviceLog.debug("Playing video from: " + this.o);
            this.n.setOnErrorListener(new a(this));
            try {
                this.n.setVideoPath(this.o);
                if (!this.v) {
                    this.b.setText(new StringBuilder().append(Math.round(Math.ceil(this.n.getDuration() / 1000))).toString());
                    this.i = System.currentTimeMillis();
                    c();
                }
            } catch (Exception e) {
                UnityAdsDeviceLog.error("For some reason the device failed to play the video, a crash was prevented.");
                b();
            }
        }
    }

    public void pauseVideo() {
        d();
        if (this.n != null && this.n.isPlaying()) {
            UnityAdsUtils.runOnUiThread(new d(this));
        }
    }

    public void hideVideo() {
        d();
    }

    public void clearVideoPlayer() {
        UnityAdsDeviceLog.entered();
        setKeepScreenOn(false);
        setOnClickListener(null);
        setOnFocusChangeListener(null);
        k();
        if (this.a != null && this.a.getParent() != null) {
            this.a.removeAllViews();
            removeView(this.a);
        }
        i();
        j();
        d();
        this.n.stopPlayback();
        this.n.setOnCompletionListener(null);
        this.n.setOnPreparedListener(null);
        this.n.setOnErrorListener(null);
        removeAllViews();
        this.c = null;
        this.d = null;
        this.h = null;
        this.p = null;
        this.a = null;
        this.b = null;
        this.g = null;
        this.f = null;
    }

    public long getBufferingDuration() {
        if (this.j == 0) {
            this.j = System.currentTimeMillis();
        }
        return this.j - this.i;
    }

    public int getSecondsUntilBackButtonAllowed() {
        UnityAdsZone currentZone = UnityAdsWebData.getZoneManager().getCurrentZone();
        if (currentZone.disableBackButtonForSeconds() <= 0 || this.k <= 0) {
            return (currentZone.allowVideoSkipInSeconds() <= 0 || this.k > 0) ? 0 : 1;
        }
        int iRound = Math.round((currentZone.disableBackButtonForSeconds() * 1000) - (System.currentTimeMillis() - this.k));
        if (iRound < 0) {
            return 0;
        }
        return iRound;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void b() {
        this.v = true;
        d();
        if (this.l != null) {
            this.l.onVideoPlaybackError();
        }
        UnityAdsInstrumentation.gaInstrumentationVideoError(UnityAdsProperties.SELECTED_CAMPAIGN, null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void c() {
        UnityAdsUtils.runOnUiThread(new e(this));
        if (this.m == null) {
            this.m = new Timer();
            this.m.scheduleAtFixedRate(new l(this, (byte) 0), 500L, 500L);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void d() {
        if (this.m != null) {
            this.m.cancel();
            this.m.purge();
            this.m = null;
        }
    }

    private void e() {
        if (UnityAdsWebData.getZoneManager().getCurrentZone().muteVideoSounds()) {
            this.y = true;
        }
        UnityAdsDeviceLog.debug("Creating custom view");
        setBackgroundColor(ViewCompat.MEASURED_STATE_MASK);
        this.w = false;
        this.n = new f(this, getContext());
        this.n.setId(GamesStatusCodes.STATUS_ACHIEVEMENT_UNKNOWN);
        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(-1, -1);
        layoutParams.addRule(13);
        this.n.setLayoutParams(layoutParams);
        addView(this.n, layoutParams);
        this.n.setClickable(true);
        this.n.setOnCompletionListener(new g(this));
        this.n.setOnPreparedListener(new h(this));
        this.h = new RelativeLayout(getContext());
        this.h.setId(3100);
        RelativeLayout.LayoutParams layoutParams2 = new RelativeLayout.LayoutParams(-2, -2);
        layoutParams2.addRule(10);
        layoutParams2.addRule(11);
        layoutParams2.topMargin = 3;
        layoutParams2.rightMargin = 3;
        this.h.setLayoutParams(layoutParams2);
        TextView textView = new TextView(getContext());
        textView.setTextColor(-1);
        textView.setText("Buffering...");
        textView.setId(3103);
        this.h.addView(textView);
        addView(this.h);
        this.a = new RelativeLayout(getContext());
        this.a.setId(GamesStatusCodes.STATUS_ACHIEVEMENT_NOT_INCREMENTAL);
        RelativeLayout.LayoutParams layoutParams3 = new RelativeLayout.LayoutParams(-2, -2);
        layoutParams3.addRule(12);
        layoutParams3.addRule(11);
        layoutParams3.bottomMargin = 3;
        layoutParams3.rightMargin = 3;
        this.a.setLayoutParams(layoutParams3);
        TextView textView2 = new TextView(getContext());
        textView2.setTextColor(-1);
        textView2.setText("This video ends in ");
        textView2.setId(GamesActivityResultCodes.RESULT_RECONNECT_REQUIRED);
        this.b = new TextView(getContext());
        this.b.setTextColor(-1);
        this.b.setText("00");
        this.b.setId(GamesActivityResultCodes.RESULT_SIGN_IN_FAILED);
        RelativeLayout.LayoutParams layoutParams4 = new RelativeLayout.LayoutParams(-2, -2);
        layoutParams4.addRule(1, GamesActivityResultCodes.RESULT_RECONNECT_REQUIRED);
        layoutParams4.leftMargin = 1;
        this.b.setLayoutParams(layoutParams4);
        TextView textView3 = new TextView(getContext());
        textView3.setTextColor(-1);
        textView3.setText("seconds.");
        RelativeLayout.LayoutParams layoutParams5 = new RelativeLayout.LayoutParams(-2, -2);
        layoutParams5.addRule(1, GamesActivityResultCodes.RESULT_SIGN_IN_FAILED);
        layoutParams5.leftMargin = 4;
        textView3.setLayoutParams(layoutParams5);
        this.a.addView(textView2);
        this.a.addView(this.b);
        this.a.addView(textView3);
        addView(this.a);
        if (UnityAdsProperties.UNITY_DEVELOPER_INTERNAL_TEST.booleanValue()) {
            this.f = new RelativeLayout(getContext());
            RelativeLayout.LayoutParams layoutParams6 = new RelativeLayout.LayoutParams(-2, -2);
            layoutParams6.addRule(14);
            layoutParams6.addRule(15);
            this.f.setLayoutParams(layoutParams6);
            this.g = new TextView(getContext());
            this.g.setTextColor(SupportMenu.CATEGORY_MASK);
            this.g.setBackgroundColor(ViewCompat.MEASURED_STATE_MASK);
            this.g.setText("INTERNAL UNITY TEST BUILD\nDO NOT USE IN PRODUCTION");
            this.f.addView(this.g);
            addView(this.f);
        }
        if (f()) {
            this.e = getSkipDuration();
            g();
        }
        setOnClickListener(new i(this));
        setOnFocusChangeListener(new j(this));
        RelativeLayout.LayoutParams layoutParams7 = new RelativeLayout.LayoutParams(-2, -2);
        layoutParams7.addRule(12);
        layoutParams7.addRule(9);
        this.r = new UnityAdsMuteVideoButton(getContext());
        this.r.setLayoutParams(layoutParams7);
        if (this.y) {
            this.r.setState(UnityAdsMuteVideoButton.UnityAdsMuteVideoButtonState.Muted);
        }
        this.r.setOnClickListener(new k(this));
        addView(this.r);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static boolean f() {
        return UnityAdsWebData.getZoneManager().getCurrentZone().allowVideoSkipInSeconds() > 0;
    }

    private long getSkipDuration() {
        if (f()) {
            return UnityAdsWebData.getZoneManager().getCurrentZone().allowVideoSkipInSeconds();
        }
        return 0L;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void g() {
        this.c = new RelativeLayout(getContext());
        this.c.setId(3010);
        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(-2, -2);
        layoutParams.addRule(10);
        layoutParams.addRule(9);
        layoutParams.topMargin = 5;
        layoutParams.leftMargin = 5;
        this.c.setLayoutParams(layoutParams);
        this.c.setOnClickListener(new b(this));
        this.d = new TextView(getContext());
        this.d.setTextColor(-1);
        this.d.setText("You can skip this video in " + this.e + " seconds");
        this.d.setId(10010);
        this.c.addView(this.d);
        addView(this.c);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void h() {
        if (this.c != null) {
            this.c.setClickable(false);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void i() {
        if (this.p != null && this.p.getParent() != null) {
            removeView(this.p);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void j() {
        if (this.q != null && this.q.getParent() != null) {
            removeView(this.q);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void k() {
        if (this.c != null && this.c.getParent() != null) {
            h();
            this.c.setVisibility(4);
        }
    }

    @Override // android.view.View, android.view.KeyEvent.Callback
    public boolean onKeyDown(int i, KeyEvent keyEvent) {
        switch (i) {
            case 4:
                UnityAdsDeviceLog.entered();
                long jDisableBackButtonForSeconds = UnityAdsWebData.getZoneManager().getCurrentZone().disableBackButtonForSeconds();
                if (jDisableBackButtonForSeconds == 0 || (jDisableBackButtonForSeconds > 0 && getSecondsUntilBackButtonAllowed() == 0)) {
                    clearVideoPlayer();
                    long bufferingDuration = getBufferingDuration();
                    HashMap map = new HashMap();
                    map.put(UnityAdsConstants.UNITY_ADS_GOOGLE_ANALYTICS_EVENT_BUFFERINGDURATION_KEY, Long.valueOf(bufferingDuration));
                    map.put(UnityAdsConstants.UNITY_ADS_GOOGLE_ANALYTICS_EVENT_VALUE_KEY, UnityAdsConstants.UNITY_ADS_GOOGLE_ANALYTICS_EVENT_VIDEOABORT_BACK);
                    UnityAdsInstrumentation.gaInstrumentationVideoAbort(UnityAdsProperties.SELECTED_CAMPAIGN, map);
                }
                if (this.l != null) {
                    this.l.onBackButtonClicked(this);
                }
                return true;
            default:
                return false;
        }
    }

    @Override // android.view.ViewGroup, android.view.View
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        j();
    }
}
