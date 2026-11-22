package com.chartboost.sdk.Model;

import android.text.TextUtils;
import com.chartboost.sdk.CBLocation;
import com.chartboost.sdk.Libraries.CBLogging;
import com.chartboost.sdk.Libraries.e;
import com.chartboost.sdk.f;
import com.chartboost.sdk.impl.ae;
import com.chartboost.sdk.impl.af;
import com.chartboost.sdk.impl.ah;
import com.chartboost.sdk.impl.ai;
import com.chartboost.sdk.impl.aw;
import com.chartboost.sdk.impl.ax;
import com.chartboost.sdk.impl.ba;
import com.chartboost.sdk.impl.bc;
import com.chartboost.sdk.impl.bq;
import com.chartboost.sdk.impl.bs;
import com.facebook.internal.AnalyticsEvents;
import com.facebook.share.internal.ShareConstants;
import com.unity3d.ads.android.properties.UnityAdsConstants;
import java.util.Date;

/* JADX INFO: loaded from: classes.dex */
public final class a {
    public Date a;
    public c c;
    public String d;
    public boolean f;
    public bq h;
    public boolean i;
    public ba n;
    public boolean p;
    private f u;
    private InterfaceC0008a v;
    private Runnable w;
    private Boolean t = null;
    public boolean j = false;
    public boolean k = false;
    public boolean l = false;
    public boolean m = false;
    public boolean q = false;
    public b b = b.LOADING;
    public boolean g = false;
    public boolean o = false;
    private e.a r = e.a.a;
    public d e = d.NONE;
    private boolean s = true;

    /* JADX INFO: renamed from: com.chartboost.sdk.Model.a$a, reason: collision with other inner class name */
    public interface InterfaceC0008a {
        void a(a aVar);

        void a(a aVar, CBError.CBImpressionError cBImpressionError);

        void a(a aVar, String str, e.a aVar2);

        void b(a aVar);

        void c(a aVar);

        void d(a aVar);
    }

    public enum b {
        LOADING,
        LOADED,
        DISPLAYED,
        CACHED,
        DISMISSING,
        NONE
    }

    public enum c {
        INTERSTITIAL,
        MORE_APPS,
        REWARDED_VIDEO,
        NONE
    }

    public enum d {
        INTERSTITIAL,
        INTERSTITIAL_VIDEO,
        INTERSTITIAL_REWARD_VIDEO,
        NONE
    }

    public a(c cVar, boolean z, String str, boolean z2) {
        this.p = false;
        this.f = z;
        this.p = true;
        this.c = cVar;
        this.i = z2;
        this.d = str;
        if (this.d == null) {
            this.d = CBLocation.LOCATION_DEFAULT;
        }
    }

    public void a(e.a aVar, InterfaceC0008a interfaceC0008a) {
        if (aVar == null) {
            aVar = e.a.a();
        }
        this.r = aVar;
        this.a = new Date();
        this.b = b.LOADING;
        this.v = interfaceC0008a;
        if (aVar.a(UnityAdsConstants.UNITY_ADS_ANALYTICS_QUERYPARAM_EVENTTYPE_KEY).equals(AnalyticsEvents.PARAMETER_SHARE_DIALOG_SHOW_NATIVE)) {
            switch (this.c) {
                case INTERSTITIAL:
                    if (aVar.a("media-type").equals(AnalyticsEvents.PARAMETER_SHARE_DIALOG_CONTENT_VIDEO)) {
                        this.e = d.INTERSTITIAL_VIDEO;
                        this.u = new ai(this);
                        this.s = false;
                    } else {
                        this.e = d.INTERSTITIAL;
                        this.u = new ah(this);
                    }
                    break;
                case REWARDED_VIDEO:
                    this.e = d.INTERSTITIAL_REWARD_VIDEO;
                    this.u = new ai(this);
                    this.s = false;
                    break;
                case MORE_APPS:
                    this.u = new ax(this);
                    this.s = false;
                    break;
            }
        } else {
            this.u = new bs(this);
        }
        this.u.a(aVar);
    }

    public boolean a() {
        return this.s;
    }

    public void b() {
        if (this.v != null) {
            this.p = true;
            this.v.b(this);
        }
    }

    public void c() {
        if (this.v != null) {
            this.v.a(this);
        }
    }

    public boolean a(String str, e.a aVar) {
        if (this.b != b.DISPLAYED || this.k) {
            return false;
        }
        if (str == null) {
            str = this.r.e(ShareConstants.WEB_DIALOG_PARAM_LINK);
        }
        String strE = this.r.e("deep-link");
        if (!TextUtils.isEmpty(strE)) {
            try {
                if (bc.a(strE)) {
                    try {
                        this.t = new Boolean(true);
                        str = strE;
                    } catch (Exception e) {
                        str = strE;
                    }
                } else {
                    this.t = new Boolean(false);
                }
            } catch (Exception e2) {
            }
        }
        if (this.o) {
            return false;
        }
        this.o = true;
        this.v.a(this, str, aVar);
        return true;
    }

    public boolean d() {
        return this.t != null;
    }

    public boolean e() {
        return this.t.booleanValue();
    }

    public void a(CBError.CBImpressionError cBImpressionError) {
        if (this.v != null) {
            this.v.a(this, cBImpressionError);
        }
    }

    public void f() {
        if (this.v != null) {
            this.v.c(this);
        }
    }

    public void g() {
        if (this.v != null) {
            this.v.d(this);
        }
    }

    public boolean h() {
        if (this.u != null) {
            this.u.b();
            if (this.u.e() != null) {
                return true;
            }
        } else {
            CBLogging.b("CBImpression", "reinitializing -- no view protocol exists!!");
        }
        CBLogging.e("CBImpression", "reinitializing -- view not yet created");
        return false;
    }

    public void i() {
        j();
        if (this.g) {
            if (this.u != null) {
                this.u.d();
            }
            this.u = null;
            CBLogging.b("CBImpression", "Destroying the view and view data");
        }
    }

    public void j() {
        if (this.h != null) {
            this.h.d();
            try {
                if (this.u != null && this.u.e() != null && this.u.e().getParent() != null) {
                    this.h.removeView(this.u.e());
                }
            } catch (Exception e) {
                CBLogging.b("CBImpression", "Exception raised while cleaning up views", e);
            }
            this.h = null;
        }
        if (this.u != null) {
            this.u.f();
        }
        CBLogging.b("CBImpression", "Destroying the view");
    }

    public CBError.CBImpressionError k() {
        if (this.u != null) {
            return this.u.c();
        }
        return null;
    }

    public f.a l() {
        if (this.u != null) {
            return this.u.e();
        }
        return null;
    }

    public void m() {
        if (this.u != null && this.u.e() != null) {
            this.u.e().setVisibility(8);
        }
    }

    public void a(Runnable runnable) {
        this.w = runnable;
    }

    public void n() {
        this.k = true;
    }

    public void o() {
        if (this.w != null) {
            this.w.run();
            this.w = null;
        }
        this.k = false;
        this.j = false;
    }

    public String p() {
        return this.r.e("ad_id");
    }

    public com.chartboost.sdk.d q() {
        switch (this.c) {
            case REWARDED_VIDEO:
                return af.h();
            case MORE_APPS:
                return aw.f();
            default:
                return ae.f();
        }
    }

    public void r() {
        q().j(this);
    }

    public boolean s() {
        if (this.u != null) {
            return this.u.j();
        }
        return false;
    }

    public void t() {
        this.o = false;
        if (this.u != null) {
            this.u.k();
        }
    }

    public void u() {
        this.o = false;
    }

    public void v() {
        if (this.u != null) {
            this.u.l();
        }
    }

    public e.a w() {
        return this.r == null ? e.a.a : this.r;
    }

    public void a(e.a aVar) {
        if (aVar == null) {
            aVar = e.a.a;
        }
        this.r = aVar;
    }

    public f x() {
        return this.u;
    }

    public boolean y() {
        return this.p;
    }
}
