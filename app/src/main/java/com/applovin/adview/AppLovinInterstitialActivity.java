package com.applovin.adview;

import android.R;
import android.app.Activity;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.view.ViewCompat;
import android.util.Log;
import android.view.Display;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.LinearInterpolator;
import android.view.animation.ScaleAnimation;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.applovin.impl.adview.AppLovinVideoView;
import com.applovin.impl.adview.ad;
import com.applovin.impl.adview.s;
import com.applovin.impl.adview.u;
import com.applovin.impl.sdk.AppLovinAdInternal;
import com.applovin.impl.sdk.AppLovinSdkImpl;
import com.applovin.impl.sdk.bf;
import com.applovin.impl.sdk.cd;
import com.applovin.sdk.AppLovinAd;
import com.applovin.sdk.AppLovinAdDisplayListener;
import com.applovin.sdk.AppLovinAdSize;
import com.applovin.sdk.AppLovinAdVideoPlaybackListener;
import com.applovin.sdk.AppLovinLogger;
import java.util.UUID;

/* JADX INFO: loaded from: classes.dex */
public class AppLovinInterstitialActivity extends Activity implements u {
    public static final String KEY_WRAPPER_ID = "com.applovin.interstitial.wrapper_id";
    public static volatile ad lastKnownWrapper = null;
    private volatile UUID A;
    private AppLovinAdView a;
    private ad b;
    private AppLovinLogger d;
    private bf e;
    private AppLovinSdkImpl f;
    private Handler r;
    private FrameLayout s;
    private AppLovinVideoView t;
    private s u;
    private View v;
    private s w;
    private View x;
    private TextView y;
    private int z;
    private volatile boolean c = false;
    private volatile AppLovinAdInternal g = cd.a();
    private volatile boolean h = false;
    private volatile boolean i = false;
    private volatile boolean j = false;
    private volatile boolean k = false;
    private volatile boolean l = false;
    private volatile boolean m = false;
    private volatile boolean n = false;
    private volatile boolean o = false;
    private volatile boolean p = false;
    private boolean q = false;

    private static int a(Display display) {
        if (display.getWidth() == display.getHeight()) {
            return 3;
        }
        return display.getWidth() < display.getHeight() ? 1 : 2;
    }

    private void a(float f) {
        float f2 = 1.0f;
        float f3 = 0.0f;
        if (!this.e.d().equals("left_to_right")) {
            f3 = 1.0f;
            f2 = 0.0f;
        }
        a(f3, f2, f);
    }

    private void a(float f, float f2, float f3) {
        try {
            ScaleAnimation scaleAnimation = new ScaleAnimation(f, f2, 1.0f, 1.0f);
            scaleAnimation.setDuration(cd.b(f3));
            scaleAnimation.setInterpolator(new LinearInterpolator());
            scaleAnimation.setAnimationListener(new e(this));
            this.x.startAnimation(scaleAnimation);
        } catch (Throwable th) {
            this.x.setVisibility(8);
        }
    }

    private void a(float f, float f2, int i) {
        float f3;
        float f4;
        float f5 = f2 / f;
        if (this.e.d().equals("left_to_right")) {
            f3 = f5;
            f4 = 1.0f;
        } else {
            f3 = 1.0f - f5;
            f4 = 0.0f;
        }
        a(f3, f4, i - cd.a(1.0f));
        a((int) Math.floor(cd.a(i)));
    }

    private void a(int i) {
        a(i, this.A);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(int i, UUID uuid) {
        if (uuid.equals(this.A)) {
            if (i <= 0) {
                this.y.setVisibility(8);
                return;
            }
            this.y.setVisibility(0);
            int i2 = i - 1;
            this.y.setText(Integer.toString(i2));
            this.r.postDelayed(new f(this, i2, uuid), 1000L);
        }
    }

    private void a(long j, s sVar) {
        this.r.postDelayed(new q(this, sVar), j);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(AppLovinAd appLovinAd) {
        AppLovinAdDisplayListener appLovinAdDisplayListenerD = this.b.d();
        if (appLovinAdDisplayListenerD != null) {
            appLovinAdDisplayListenerD.adDisplayed(appLovinAd);
        }
        this.i = true;
    }

    private void a(AppLovinAd appLovinAd, double d, boolean z) {
        this.l = true;
        AppLovinAdVideoPlaybackListener appLovinAdVideoPlaybackListenerC = this.b.c();
        if (appLovinAdVideoPlaybackListenerC != null) {
            appLovinAdVideoPlaybackListenerC.videoPlaybackEnded(appLovinAd, d, z);
        }
    }

    private boolean a() {
        if (this.b == null || this.e == null || this.e.a()) {
            return true;
        }
        if (this.e.c() && this.n) {
            return true;
        }
        return this.e.b() && this.p;
    }

    private int b(int i) {
        return cd.a(this, i);
    }

    private void b() {
        SharedPreferences.Editor editorEdit = u().edit();
        editorEdit.putBoolean("com.applovin.interstitial.should_resume_video", false);
        editorEdit.putInt("com.applovin.interstitial.last_video_position", 0);
        editorEdit.commit();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void b(AppLovinAd appLovinAd) {
        c(appLovinAd);
        dismiss();
    }

    private void c() {
        this.z = com.applovin.impl.sdk.m.a(this).x;
        Uri uriFromFile = Uri.fromFile(this.f.getFileManager().a(this.g.getVideoFilename(), this, false));
        this.t = new AppLovinVideoView(this);
        this.t.setOnPreparedListener(new b(this));
        this.t.setOnCompletionListener(new i(this));
        this.t.setOnErrorListener(new j(this));
        this.t.setVideoURI(uriFromFile);
        this.t.setLayoutParams(new FrameLayout.LayoutParams(-1, -1, 17));
        this.s.addView(this.t);
        setContentView(this.s);
        n();
        k();
        l();
        m();
        f();
    }

    private void c(AppLovinAd appLovinAd) {
        AppLovinAdDisplayListener appLovinAdDisplayListenerD;
        if (this.j) {
            return;
        }
        this.j = true;
        if (this.b == null || (appLovinAdDisplayListenerD = this.b.d()) == null) {
            return;
        }
        appLovinAdDisplayListenerD.adHidden(appLovinAd);
    }

    private void d() {
        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(-1, -1);
        this.s = new FrameLayout(this);
        this.s.setLayoutParams(layoutParams);
        this.s.setBackgroundColor(ViewCompat.MEASURED_STATE_MASK);
        this.r = new Handler();
    }

    private void d(AppLovinAd appLovinAd) {
        if (this.k) {
            return;
        }
        this.k = true;
        AppLovinAdVideoPlaybackListener appLovinAdVideoPlaybackListenerC = this.b.c();
        if (appLovinAdVideoPlaybackListenerC != null) {
            appLovinAdVideoPlaybackListenerC.videoPlaybackBegan(appLovinAd);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void e() {
        if (this.e.k()) {
            finish();
        } else {
            h();
        }
    }

    private void f() {
        d(this.g);
        this.t.start();
        a(cd.a(this.g.getCountdownLength()));
        p();
    }

    private void g() {
        this.u = s.a(this.f, this, this.g.getCloseStyle());
        this.u.setVisibility(8);
        this.u.setOnClickListener(new l(this));
        int iB = b(this.e.m());
        FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(iB, iB, 53);
        this.u.a(iB);
        int iB2 = b(this.e.o());
        int iB3 = b(this.e.q());
        layoutParams.setMargins(0, iB2, iB3, 0);
        this.s.addView(this.u, layoutParams);
        if (this.e.r() > 0) {
            int iB4 = b(new bf(this.f).r());
            this.v = new View(this);
            this.v.setBackgroundColor(0);
            this.v.setVisibility(8);
            FrameLayout.LayoutParams layoutParams2 = new FrameLayout.LayoutParams(iB + iB4, iB4 + iB, 53);
            layoutParams2.setMargins(0, iB2 - b(5), iB3 - b(5), 0);
            this.v.setOnClickListener(new m(this));
            this.s.addView(this.v, layoutParams2);
            this.v.bringToFront();
        }
        this.w = s.a(this.f, this, this.g.getCloseStyle());
        this.w.setVisibility(8);
        this.w.setOnClickListener(new n(this));
        FrameLayout.LayoutParams layoutParams3 = new FrameLayout.LayoutParams(iB, iB, 51);
        layoutParams3.setMargins(iB3, iB2, 0, 0);
        this.w.a(iB);
        this.s.addView(this.w, layoutParams3);
        this.w.bringToFront();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void h() {
        runOnUiThread(new o(this));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean i() {
        return this.e.r() > 0 && this.v != null;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void j() {
        runOnUiThread(new p(this));
    }

    private void k() {
        if (this.g.getVideoCloseDelay() >= 0.0f) {
            a(cd.c(this.g.getVideoCloseDelay()), (!this.q || this.w == null) ? this.u : this.w);
        }
    }

    private void l() {
        this.x = new View(this);
        this.x.setBackgroundColor(o());
        FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(this.z, b(this.e.g()), 81);
        if (!this.e.h() || this.g.getCountdownLength() <= 0) {
            return;
        }
        this.s.addView(this.x, layoutParams);
        this.x.bringToFront();
    }

    private void m() {
        this.y = new TextView(this);
        this.y.setTextColor(o());
        this.y.setTextSize(0, b(22) * 0.8f);
        this.y.setText(Integer.toString(this.g.getCountdownLength()));
        FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(-2, -2, 83);
        layoutParams.setMargins(b(10), 0, this.x.getHeight() + b(3), 0);
        if (!this.e.i() || this.g.getCountdownLength() <= 0) {
            return;
        }
        this.s.addView(this.y, layoutParams);
        this.y.bringToFront();
    }

    private void n() {
        this.A = UUID.randomUUID();
    }

    private int o() {
        return Color.parseColor(this.e.e());
    }

    private void p() {
        a(this.g.getCountdownLength() + 1);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void q() {
        this.t.stopPlayback();
        s();
    }

    private void r() {
        if (this.c) {
            return;
        }
        if (this.a == null) {
            exitWithError("AdView was null");
            return;
        }
        this.a.setAdDisplayListener(new g(this));
        this.a.setAdClickListener(new h(this));
        this.g = (AppLovinAdInternal) this.b.b();
        d();
        g();
        if (this.g.getVideoFilename() != null) {
            c();
        } else {
            this.h = true;
            this.f.getLogger().e("AppLovinInterstitialActivity", "Video file was missing or null - please make sure your app has the WRITE_EXTERNAL_STORAGE permission!");
            s();
        }
        this.u.bringToFront();
        if (i()) {
            this.v.bringToFront();
        }
        if (this.w != null) {
            this.w.bringToFront();
        }
        this.a.renderAd(this.g);
        this.b.a(true);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void s() {
        FrameLayout frameLayout = new FrameLayout(this);
        frameLayout.setLayoutParams(new FrameLayout.LayoutParams(-1, -1));
        frameLayout.setBackgroundColor(-1157627904);
        frameLayout.addView(this.a);
        if (this.w != null) {
            this.s.removeView(this.w);
        }
        if (i()) {
            this.s.removeView(this.v);
            frameLayout.addView(this.v);
            this.v.bringToFront();
        }
        this.s.removeView(this.u);
        frameLayout.addView(this.u);
        setContentView(frameLayout);
        this.u.bringToFront();
        if (this.g.getPoststitialCloseDelay() > 0.0f) {
            a(cd.c(this.g.getPoststitialCloseDelay()), this.u);
        } else {
            this.u.setVisibility(0);
        }
        this.p = true;
        t();
    }

    private void t() {
        double currentPosition = 100.0d;
        if (this.l) {
            return;
        }
        if (!this.m) {
            if (this.t != null) {
                currentPosition = 100.0d * (((double) this.t.getCurrentPosition()) / ((double) this.t.getDuration()));
            } else {
                Log.e("AppLovinInterstitialActivity", "No video view detected on video end");
                currentPosition = 0.0d;
            }
        }
        a(this.g, currentPosition, currentPosition > 95.0d);
    }

    private SharedPreferences u() {
        return getSharedPreferences("com.applovin.interstitial.sharedpreferences", 0);
    }

    @Override // com.applovin.impl.adview.u
    public void dismiss() {
        b();
        t();
        if (this.b != null) {
            if (this.g != null) {
                c(this.g);
            }
            this.b.a(false);
            this.b.g();
        }
        finish();
    }

    public void exitWithError(String str) {
        try {
            Log.e("AppLovinInterstitialActivity", "Failed to properly render an Interstitial Activity, due to error: " + str, new Throwable("Initialized = " + ad.a + "; CleanedUp = " + ad.b));
            c(cd.a());
        } catch (Exception e) {
            Log.e("AppLovinInterstitialActivity", "Failed to show a video ad due to error:", e);
        }
        finish();
    }

    @Override // android.app.Activity
    public void onBackPressed() {
        if (a()) {
            this.d.d("AppLovinInterstitialActivity", "Back button was pressed; forwarding to Android for handling...");
            super.onBackPressed();
            return;
        }
        try {
            if (this.q && this.w != null && this.w.getVisibility() == 0 && this.w.getAlpha() > 0.0f && !this.n) {
                this.d.d("AppLovinInterstitialActivity", "Back button was pressed; forwarding as a click to skip button.");
                this.w.performClick();
            } else if (this.u == null || this.u.getVisibility() != 0 || this.u.getAlpha() <= 0.0f) {
                this.d.d("AppLovinInterstitialActivity", "Back button was pressed, but was not eligible for dismissal.");
            } else {
                this.d.d("AppLovinInterstitialActivity", "Back button was pressed; forwarding as a click to close button.");
                this.u.performClick();
            }
        } catch (Exception e) {
            super.onBackPressed();
        }
    }

    @Override // android.app.Activity
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        requestWindowFeature(1);
        try {
            getWindow().setFlags(1024, 1024);
        } catch (Throwable th) {
        }
        setTheme(R.style.Theme.Translucent.NoTitleBar.Fullscreen);
        String stringExtra = getIntent().getStringExtra(KEY_WRAPPER_ID);
        if (stringExtra == null || stringExtra.isEmpty()) {
            exitWithError("Wrapper ID is null");
        } else {
            this.b = ad.a(stringExtra);
            if (this.b == null && lastKnownWrapper != null) {
                this.b = lastKnownWrapper;
            }
            if (this.b != null) {
                AppLovinAd appLovinAdB = this.b.b();
                this.f = (AppLovinSdkImpl) this.b.a();
                this.d = this.b.a().getLogger();
                this.e = new bf(this.b.a());
                if (appLovinAdB != null) {
                    Display defaultDisplay = ((WindowManager) getSystemService("window")).getDefaultDisplay();
                    int iA = a(defaultDisplay);
                    int rotation = defaultDisplay.getRotation();
                    boolean z = (iA == 2 && rotation == 0) || (iA == 2 && rotation == 2) || ((iA == 1 && rotation == 1) || (iA == 1 && rotation == 3));
                    if (this.b.f() == AppLovinAdInternal.AdTarget.ACTIVITY_PORTRAIT) {
                        if (z) {
                            if (rotation != 1 && rotation != 3) {
                                this.c = true;
                                setRequestedOrientation(1);
                            }
                        } else if (rotation != 0 && rotation != 2) {
                            this.c = true;
                            setRequestedOrientation(1);
                        }
                    } else if (z) {
                        if (rotation != 0 && rotation != 2) {
                            this.c = true;
                            setRequestedOrientation(0);
                        }
                    } else if (rotation != 1 && rotation != 3) {
                        this.c = true;
                        setRequestedOrientation(0);
                    }
                    this.a = new AppLovinAdView(this.f, AppLovinAdSize.INTERSTITIAL, this);
                    this.a.setAutoDestroy(false);
                    this.b.a(this);
                    this.q = this.e.s();
                } else {
                    exitWithError("No current ad found.");
                }
            } else {
                exitWithError("Wrapper is null; initialized state: " + Boolean.toString(ad.a));
            }
        }
        SharedPreferences.Editor editorEdit = u().edit();
        editorEdit.putBoolean("com.applovin.interstitial.should_resume_video", false);
        editorEdit.commit();
        b();
        r();
    }

    @Override // android.app.Activity
    protected void onDestroy() {
        try {
            if (this.a != null) {
                this.a.destroy();
            }
            if (this.t != null) {
                this.t.pause();
                this.t.stopPlayback();
            }
        } catch (Throwable th) {
            this.d.w("AppLovinInterstitialActivity", "Unable to destroy video view", th);
        }
        super.onDestroy();
    }

    @Override // android.app.Activity
    protected void onPause() {
        if (!this.c && !this.h) {
            SharedPreferences.Editor editorEdit = u().edit();
            editorEdit.putInt("com.applovin.interstitial.last_video_position", this.t.getCurrentPosition());
            editorEdit.putBoolean("com.applovin.interstitial.should_resume_video", true);
            editorEdit.commit();
            this.x.clearAnimation();
            this.s.removeView(this.x);
            this.s.removeView(this.y);
            this.t.pause();
        }
        this.b.a(false);
        super.onPause();
    }

    @Override // android.app.Activity
    protected void onResume() {
        super.onResume();
        this.b.a(true);
        SharedPreferences sharedPreferencesU = u();
        if (sharedPreferencesU.getBoolean("com.applovin.interstitial.should_resume_video", false)) {
            if (this.t != null) {
                int duration = this.t.getDuration();
                int i = sharedPreferencesU.getInt("com.applovin.interstitial.last_video_position", duration);
                l();
                m();
                n();
                this.t.seekTo(i);
                this.t.start();
                a(duration, i, duration - i);
            }
            if (this.u == null || !this.e.j()) {
                dismiss();
            } else {
                this.d.d("AppLovinInterstitialActivity", "Fading in close button due to app resume.");
                a(0L, (!this.q || this.w == null) ? this.u : this.w);
            }
        }
    }
}
