package com.flurry.android;

import android.R;
import android.app.Activity;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import com.flurry.sdk.aw;
import com.flurry.sdk.co;
import com.flurry.sdk.cx;
import com.flurry.sdk.ea;
import com.flurry.sdk.ec;
import com.flurry.sdk.ee;
import com.flurry.sdk.ek;
import com.flurry.sdk.fp;
import com.flurry.sdk.fy;
import com.flurry.sdk.fz;
import com.flurry.sdk.gd;
import com.flurry.sdk.hc;
import com.flurry.sdk.hq;
import com.flurry.sdk.i;
import com.flurry.sdk.r;
import java.util.Collections;
import java.util.Map;

/* JADX INFO: loaded from: classes.dex */
public final class FlurryFullscreenTakeoverActivity extends Activity {
    public static final String EXTRA_KEY_AD_OBJECT_ID = "ad_object_id";
    public static final String EXTRA_KEY_AD_OBJECT_LEGACY = "ad_object_legacy";
    public static final String EXTRA_KEY_CLOSE_AD = "close_ad";
    public static final String EXTRA_KEY_SEND_Y_COOKIES = "send_y_cookies";
    public static final String EXTRA_KEY_URL = "url";
    private static final String a = FlurryFullscreenTakeoverActivity.class.getSimpleName();
    private ViewGroup b;
    private ec c;
    private boolean d;
    private r e;
    private ek f;
    private boolean g = false;
    private fy<ea> h = new fy<ea>() { // from class: com.flurry.android.FlurryFullscreenTakeoverActivity.1
        @Override // com.flurry.sdk.fy
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public void notify(final ea eaVar) {
            fp.a().a(new hq() { // from class: com.flurry.android.FlurryFullscreenTakeoverActivity.1.1
                @Override // com.flurry.sdk.hq
                public void safeRun() {
                    switch (AnonymousClass3.a[eaVar.e.ordinal()]) {
                        case 1:
                            gd.a(3, FlurryFullscreenTakeoverActivity.a, "RELOAD_ACTIVITY Event was fired for adObject:" + eaVar.a.d() + " for url:" + eaVar.b + " and should Close Ad:" + eaVar.c);
                            FlurryFullscreenTakeoverActivity.this.f = new ek(eaVar.a, eaVar.b, eaVar.c, eaVar.d);
                            FlurryFullscreenTakeoverActivity.this.e = FlurryFullscreenTakeoverActivity.this.f.c();
                            if (FlurryFullscreenTakeoverActivity.this.e == null) {
                                gd.b(FlurryFullscreenTakeoverActivity.a, "Cannot launch Activity. No Ad Controller");
                                FlurryFullscreenTakeoverActivity.this.finish();
                            } else {
                                FlurryFullscreenTakeoverActivity.this.f();
                                FlurryFullscreenTakeoverActivity.this.e();
                                FlurryFullscreenTakeoverActivity.this.a(true);
                                FlurryFullscreenTakeoverActivity.this.d();
                            }
                            break;
                        case 2:
                            gd.a(FlurryFullscreenTakeoverActivity.a, "CLOSE_ACTIVITY Event was fired :");
                            FlurryFullscreenTakeoverActivity.this.finish();
                            break;
                    }
                }
            });
        }
    };
    private ec.a i = new ec.a() { // from class: com.flurry.android.FlurryFullscreenTakeoverActivity.2
        @Override // com.flurry.sdk.ec.a
        public void a() {
            gd.a(FlurryFullscreenTakeoverActivity.a, "onViewBack");
            if (FlurryFullscreenTakeoverActivity.this.f != null && FlurryFullscreenTakeoverActivity.this.f.b()) {
                FlurryFullscreenTakeoverActivity.this.e();
                FlurryFullscreenTakeoverActivity.this.finish();
            } else {
                FlurryFullscreenTakeoverActivity.this.removeViewState();
                FlurryFullscreenTakeoverActivity.this.loadViewState();
                FlurryFullscreenTakeoverActivity.this.a(true);
                FlurryFullscreenTakeoverActivity.this.d();
            }
        }

        @Override // com.flurry.sdk.ec.a
        public void b() {
            gd.a(FlurryFullscreenTakeoverActivity.a, "onViewClose");
            FlurryFullscreenTakeoverActivity.this.e();
            FlurryFullscreenTakeoverActivity.this.finish();
        }

        @Override // com.flurry.sdk.ec.a
        public void c() {
            gd.a(FlurryFullscreenTakeoverActivity.a, "onViewError");
            FlurryFullscreenTakeoverActivity.this.e();
            FlurryFullscreenTakeoverActivity.this.finish();
        }
    };

    @Override // android.app.Activity
    public void onCreate(Bundle bundle) {
        gd.a(3, a, "onCreate");
        setTheme(R.style.Theme.NoTitleBar.Fullscreen);
        super.onCreate(bundle);
        if (fp.a() == null) {
            gd.a(3, a, "Flurry core not initialized.");
            finish();
            return;
        }
        cx.a(getWindow());
        setVolumeControlStream(3);
        this.b = new RelativeLayout(this);
        c();
        f();
        a(true);
        fireEvent(aw.INTERNAL_EV_AD_OPENED, Collections.emptyMap(), 0);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(boolean z) {
        this.g = z;
    }

    private boolean b() {
        return this.g;
    }

    @Override // android.app.Activity
    public void onStart() {
        super.onStart();
        gd.a(3, a, "onStart");
        hc.a().b(this);
        registerActivityEvent();
        d();
        if (this.c != null) {
            this.c.onActivityStart();
        }
    }

    @Override // android.app.Activity
    protected void onRestart() {
        gd.a(3, a, "onRestart");
        super.onRestart();
        loadViewState();
    }

    @Override // android.app.Activity
    protected void onResume() {
        gd.a(3, a, "onActivityResume");
        super.onResume();
        if (this.c != null) {
            this.c.onActivityResume();
        }
    }

    @Override // android.app.Activity
    protected void onPause() {
        gd.a(3, a, "onPause");
        super.onPause();
        if (this.c != null) {
            this.c.onActivityPause();
        }
    }

    @Override // android.app.Activity
    public void onStop() {
        super.onStop();
        gd.a(3, a, "onStop");
        if (this.c != null) {
            this.c.onActivityStop();
        }
        a(false);
        unregisterActivityEvent();
        hc.a().c(this);
    }

    @Override // android.app.Activity, android.content.ComponentCallbacks
    public void onConfigurationChanged(Configuration configuration) {
        super.onConfigurationChanged(configuration);
        gd.a(3, a, "onConfigurationChanged");
        if (this.c != null) {
            this.c.onConfigurationChanged();
        }
    }

    @Override // android.app.Activity
    protected void onDestroy() {
        super.onDestroy();
        gd.a(3, a, "onDestroy");
        if (this.c != null) {
            this.c.onActivityDestroy();
        }
        if (this.e != null && this.e.k() != null) {
            this.e.k().u();
            this.e.k().b(false);
        }
        fireEvent(aw.EV_AD_CLOSED, Collections.emptyMap(), 0);
    }

    @Override // android.app.Activity
    public void finish() {
        synchronized (this) {
            if (!this.d) {
                this.d = true;
                super.finish();
            }
        }
    }

    @Override // android.app.Activity, android.view.KeyEvent.Callback
    public boolean onKeyUp(int i, KeyEvent keyEvent) {
        gd.a(3, a, "onKeyUp");
        if (i != 4 || this.c == null) {
            return super.onKeyUp(i, keyEvent);
        }
        this.c.onBackKey();
        return true;
    }

    private void c() {
        boolean booleanExtra = getIntent().getBooleanExtra(EXTRA_KEY_AD_OBJECT_LEGACY, false);
        int intExtra = getIntent().getIntExtra(EXTRA_KEY_AD_OBJECT_ID, 0);
        String stringExtra = getIntent().getStringExtra("url");
        boolean booleanExtra2 = getIntent().getBooleanExtra(EXTRA_KEY_CLOSE_AD, true);
        boolean booleanExtra3 = getIntent().getBooleanExtra(EXTRA_KEY_SEND_Y_COOKIES, false);
        i iVarA = i.a();
        this.e = booleanExtra ? iVarA.e().a(intExtra) : iVarA.d().a(intExtra);
        if (this.e == null) {
            gd.b(a, "Cannot launch Activity. No ad object.");
            finish();
        } else {
            this.f = new ek(this.e, stringExtra, booleanExtra2, booleanExtra3);
            this.e.k().b(true);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void d() {
        if (this.f == null) {
            finish();
            return;
        }
        gd.a(3, a, "Load View in Activity: " + this.f.toString());
        ec ecVarA = ee.a(this, this.f.c(), this.f.a(), this.i, this.f.d(), b());
        if (ecVarA != null) {
            e();
            a(ecVarA);
            this.c.initLayout();
        }
        a(false);
    }

    private void a(ec ecVar) {
        e();
        this.c = ecVar;
        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(-2, -2);
        layoutParams.addRule(13);
        this.b.addView(ecVar, layoutParams);
        setContentView(this.b);
    }

    public void registerActivityEvent() {
        fz.a().a("com.flurry.android.impl.ads.views.ActivityEvent", this.h);
    }

    public void unregisterActivityEvent() {
        fz.a().a(this.h);
    }

    /* JADX INFO: renamed from: com.flurry.android.FlurryFullscreenTakeoverActivity$3, reason: invalid class name */
    static /* synthetic */ class AnonymousClass3 {
        static final /* synthetic */ int[] a = new int[ea.a.values().length];

        static {
            try {
                a[ea.a.RELOAD_ACTIVITY.ordinal()] = 1;
            } catch (NoSuchFieldError e) {
            }
            try {
                a[ea.a.CLOSE_ACTIVITY.ordinal()] = 2;
            } catch (NoSuchFieldError e2) {
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void e() {
        if (this.c != null) {
            this.c.cleanupLayout();
            this.b.removeAllViews();
        }
    }

    public void fireEvent(aw awVar, Map<String, String> map, int i) {
        gd.a(a, "fireEvent(event=" + awVar + ",params=" + map + ")");
        co.a(awVar, map, this, this.e, this.e.k(), i);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void f() {
        if (this.f != null) {
            gd.a(a, "Save view state: " + this.f.toString());
            this.e.k().a(this.f);
        }
    }

    public void loadViewState() {
        this.f = this.e.k().t();
        if (this.f == null) {
            finish();
        } else {
            gd.a(a, "Load view state: " + this.f.toString());
        }
    }

    public void removeViewState() {
        if (this.e.k() != null) {
            gd.a(a, "Remove view state: " + this.e.k().s().toString());
        }
    }
}
