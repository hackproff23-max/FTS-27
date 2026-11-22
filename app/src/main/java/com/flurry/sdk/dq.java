package com.flurry.sdk;

import android.content.Context;
import android.content.res.Configuration;
import android.net.Uri;
import android.widget.RelativeLayout;
import java.io.File;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/* JADX INFO: loaded from: classes.dex */
public abstract class dq extends ec implements dx {
    private static final String b = dq.class.getSimpleName();
    protected du a;
    private boolean c;
    private boolean d;

    protected abstract int getViewParams();

    protected dq(Context context, r rVar, ec.a aVar) {
        super(context, rVar, aVar);
        this.c = true;
        this.d = false;
        this.a = new du(context);
        this.a.a(this);
        k();
        showProgressDialog();
    }

    @Override // com.flurry.sdk.ec
    protected void onViewLoadTimeout() {
        a(aw.EV_AD_WILL_CLOSE, Collections.emptyMap());
    }

    public void setAutoPlay(boolean z) {
        gd.a(3, b, "Video setAutoPlay: " + z);
        this.c = z;
    }

    public void a(int i) {
        if (this.a != null) {
            if (this.a.a()) {
                dismissProgressDialog();
                this.a.a(i);
            } else {
                showProgressDialog();
            }
            this.a.b(getViewParams());
            this.d = false;
        }
    }

    public void c() {
        if (this.a != null) {
            gd.a(3, b, "Video pause: ");
            i();
            j();
            this.a.b();
            this.d = true;
        }
    }

    public void d() {
        if (this.a != null) {
            gd.a(3, b, "Video suspend: ");
            c();
            this.a.d();
        }
    }

    public void setVideoUri(Uri uri) {
        gd.a(3, b, "Video set video uri: " + uri);
        if (this.a != null) {
            dt dtVarM = getAdController().m();
            this.a.a(uri, dtVarM.a() > this.a.f() ? dtVarM.a() : this.a.f());
        }
    }

    @Override // com.flurry.sdk.dx
    public void b(String str) {
        gd.a(3, b, "Video Prepared: " + str);
        if (this.a != null) {
            this.a.b(getViewParams());
        }
        if (this.d) {
            dismissProgressDialog();
            return;
        }
        dt dtVarM = getAdController().m();
        int iA = dtVarM.a();
        if (this.a != null && (this.c || iA > 3)) {
            a(iA);
        }
        a(aw.EV_RENDERED, Collections.emptyMap());
        dtVarM.h(true);
        dismissProgressDialog();
    }

    public void a(String str, float f, float f2) {
        a(f, f2);
        if (this.a != null) {
            this.a.b(getViewParams());
        }
    }

    @Override // com.flurry.sdk.dx
    public void a(String str, int i, int i2, int i3) {
        gd.a(3, b, "Video Error: " + str);
        if (this.a != null) {
            this.a.d();
        }
        onViewError();
        HashMap map = new HashMap();
        map.put("errorCode", Integer.toString(av.kVideoPlaybackError.a()));
        map.put("frameworkError", Integer.toString(i2));
        map.put("implError", Integer.toString(i3));
        a(aw.EV_RENDER_FAILED, map);
        dismissProgressDialog();
        l();
    }

    public void a(String str) {
        gd.a(3, b, "Video Completed: " + str);
        boolean zG = g();
        a(aw.EV_VIDEO_COMPLETED, Collections.emptyMap());
        l();
        if (zG) {
            h();
        }
    }

    private boolean g() {
        return getAdFrameIndex() == getAdUnit().adFrames.size() + (-1);
    }

    private void h() {
        ea eaVar = new ea();
        eaVar.e = ea.a.CLOSE_ACTIVITY;
        eaVar.b();
    }

    public void a() {
        gd.a(3, b, "Video Close clicked: ");
        a(aw.EV_AD_WILL_CLOSE, Collections.emptyMap());
        onViewClose();
    }

    @Override // com.flurry.sdk.dx
    public void e() {
        gd.a(3, b, "Video More Info clicked: ");
        a(aw.EV_CLICKED, Collections.emptyMap());
    }

    public void b() {
        gd.a(3, b, "Video Play clicked: ");
        a(0);
    }

    protected void a(aw awVar, Map<String, String> map) {
        co.a(awVar, map, getContext(), getAdObject(), getAdController(), 0);
    }

    protected Uri c(String str) {
        Uri uri = null;
        try {
            gd.a(3, b, "Precaching: Getting video from cache: " + str);
            File fileA = i.a().l().a(getAdObject(), str);
            if (fileA != null) {
                uri = Uri.parse("file://" + fileA.getAbsolutePath());
            }
        } catch (Exception e) {
            gd.a(3, b, "Precaching: Error accessing cached file.", e);
        }
        if (uri == null) {
            gd.a(3, b, "Precaching: Error using cached file. Loading with url: " + str);
            return Uri.parse(str);
        }
        return uri;
    }

    @Override // com.flurry.sdk.ec
    public void initLayout() {
        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(-2, -2);
        layoutParams.addRule(13);
        addView(this.a.e(), layoutParams);
        showProgressDialog();
    }

    @Override // com.flurry.sdk.ec
    public void cleanupLayout() {
        d();
        dismissProgressDialog();
        if (this.a != null) {
            this.a.j();
            this.a = null;
        }
    }

    private void a(float f, float f2) {
        if (this.a != null) {
            dt dtVarM = getAdController().m();
            if (f2 >= 0.0f && !dtVarM.b()) {
                dtVarM.a(true);
                a(aw.EV_VIDEO_START, Collections.emptyMap());
            }
            float f3 = f2 / f;
            if (f3 >= 0.25f && !dtVarM.d()) {
                dtVarM.c(true);
                a(aw.EV_VIDEO_FIRST_QUARTILE, Collections.emptyMap());
            }
            if (f3 >= 0.5f && !dtVarM.e()) {
                dtVarM.d(true);
                a(aw.EV_VIDEO_MIDPOINT, Collections.emptyMap());
            }
            if (f3 >= 0.75f && !dtVarM.f()) {
                dtVarM.e(true);
                a(aw.EV_VIDEO_THIRD_QUARTILE, Collections.emptyMap());
            }
        }
    }

    private void i() {
        dt dtVarM = getAdController().m();
        int iC = this.a.c();
        if (iC > 0) {
            dtVarM.a(iC);
            getAdController().a(dtVarM);
        }
    }

    private void j() {
        getAdController().m().b(getViewParams());
    }

    private void k() {
        if (cn.a(getContext())) {
            setOrientation(4);
        } else {
            setOrientation(6);
        }
    }

    private void l() {
        setOrientation(4);
    }

    @Override // android.view.View
    protected void onConfigurationChanged(Configuration configuration) {
        super.onConfigurationChanged(configuration);
        k();
    }

    @Override // com.flurry.sdk.ec
    public void onActivityResume() {
        super.onActivityResume();
        int iA = getAdController().m().a();
        if (this.a != null) {
            if (this.c || iA > 3) {
                a(iA);
            }
        }
    }

    @Override // com.flurry.sdk.ec
    public void onActivityPause() {
        super.onActivityPause();
        c();
    }

    @Override // com.flurry.sdk.ec
    public void onActivityStop() {
        super.onActivityStop();
        d();
    }

    @Override // com.flurry.sdk.ec
    public void onActivityDestroy() {
        super.onActivityDestroy();
        i.a().l().a(getAdObject());
        i.a().l().g();
    }
}
