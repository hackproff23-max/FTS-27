package com.chartboost.sdk;

import android.app.Activity;
import android.content.Context;
import android.text.TextUtils;
import com.chartboost.sdk.Libraries.CBLogging;
import com.chartboost.sdk.Model.CBError;
import com.chartboost.sdk.impl.ai;
import com.chartboost.sdk.impl.ba;
import com.chartboost.sdk.impl.bc;
import com.chartboost.sdk.impl.bq;
import com.facebook.share.internal.ShareConstants;
import com.unity3d.ads.android.properties.UnityAdsConstants;

/* JADX INFO: loaded from: classes.dex */
public final class c {
    private static final String c = c.class.getSimpleName();
    private static c d;
    public com.chartboost.sdk.Model.a.InterfaceC0008a a = new com.chartboost.sdk.Model.a.InterfaceC0008a() { // from class: com.chartboost.sdk.c.2
        @Override // com.chartboost.sdk.Model.a.InterfaceC0008a
        public void a(com.chartboost.sdk.Model.a aVar) {
            boolean z;
            synchronized (c.this) {
                z = aVar.f;
            }
            if (aVar.b == com.chartboost.sdk.Model.a.b.LOADING) {
                aVar.b = com.chartboost.sdk.Model.a.b.LOADED;
                if (z) {
                    aVar.q().a(aVar);
                }
            }
            if (!z) {
                aVar.q().g(aVar);
            }
            aVar.q().o(aVar);
        }

        @Override // com.chartboost.sdk.Model.a.InterfaceC0008a
        public void b(com.chartboost.sdk.Model.a aVar) {
            e eVarG;
            if (aVar.b == com.chartboost.sdk.Model.a.b.DISPLAYED && (eVarG = Chartboost.g()) != null) {
                eVarG.b(aVar);
            }
            com.chartboost.sdk.Tracking.a.c(aVar.q().e(), aVar.d, aVar.p());
        }

        @Override // com.chartboost.sdk.Model.a.InterfaceC0008a
        public void a(com.chartboost.sdk.Model.a aVar, String str, com.chartboost.sdk.Libraries.e.a aVar2) {
            e eVarG;
            aVar.q().b().a(aVar);
            if (aVar.a() && aVar.b == com.chartboost.sdk.Model.a.b.DISPLAYED && (eVarG = Chartboost.g()) != null) {
                eVarG.b(aVar);
            }
            if (!TextUtils.isEmpty(str)) {
                ba baVarD = c.this.d();
                baVarD.a(ShareConstants.WEB_DIALOG_PARAM_TO, aVar.w());
                baVarD.a("cgn", aVar.w());
                baVarD.a("creative", aVar.w());
                baVarD.a("ad_id", aVar.w());
                baVarD.a("cgn", aVar2);
                baVarD.a("creative", aVar2);
                baVarD.a(UnityAdsConstants.UNITY_ADS_ANALYTICS_QUERYPARAM_EVENTTYPE_KEY, aVar2);
                baVarD.a("more_type", aVar2);
                if (aVar.d()) {
                    baVarD.a("retarget_reinstall", Boolean.valueOf(aVar.e()));
                }
                aVar.n = baVarD;
                c.this.b(aVar, str, null);
            } else {
                c.this.b.a(aVar, false, str, CBError.CBClickError.URI_INVALID, null);
            }
            com.chartboost.sdk.Tracking.a.b(aVar.q().e(), aVar.d, aVar.p());
        }

        @Override // com.chartboost.sdk.Model.a.InterfaceC0008a
        public void a(com.chartboost.sdk.Model.a aVar, CBError.CBImpressionError cBImpressionError) {
            aVar.q().a(aVar, cBImpressionError);
        }

        @Override // com.chartboost.sdk.Model.a.InterfaceC0008a
        public void c(com.chartboost.sdk.Model.a aVar) {
            aVar.l = true;
            if (aVar.c == com.chartboost.sdk.Model.a.c.REWARDED_VIDEO && com.chartboost.sdk.b.d() != null) {
                com.chartboost.sdk.b.d().didCompleteRewardedVideo(aVar.d, aVar.w().f("reward"));
            }
            c.b(aVar);
        }

        @Override // com.chartboost.sdk.Model.a.InterfaceC0008a
        public void d(com.chartboost.sdk.Model.a aVar) {
            aVar.m = true;
        }
    };
    public bc.a b = new bc.a() { // from class: com.chartboost.sdk.c.3
        @Override // com.chartboost.sdk.impl.bc.a
        public void a(com.chartboost.sdk.Model.a aVar, boolean z, String str, CBError.CBClickError cBClickError, b bVar) {
            if (aVar != null) {
                aVar.o = false;
                if (aVar.a()) {
                    aVar.b = com.chartboost.sdk.Model.a.b.DISMISSING;
                }
            }
            if (!z) {
                if (com.chartboost.sdk.b.d() != null) {
                    com.chartboost.sdk.b.d().didFailToRecordClick(str, cBClickError);
                }
            } else if (aVar != null && aVar.n != null) {
                aVar.n.a(true);
                aVar.n.s();
            } else if (bVar != null) {
                bVar.a();
            }
        }
    };
    private bc e = bc.a(this.b);

    public interface a {
        void a(boolean z);
    }

    public interface b {
        void a();
    }

    private c() {
    }

    public static c a() {
        if (d == null) {
            d = new c();
        }
        return d;
    }

    public final void a(com.chartboost.sdk.Model.a aVar, String str, b bVar) {
        this.e.a(aVar, str, Chartboost.getHostActivity(), bVar);
    }

    public final void b(final com.chartboost.sdk.Model.a aVar, final String str, final b bVar) {
        com.chartboost.sdk.b.c = new a() { // from class: com.chartboost.sdk.c.1
            @Override // com.chartboost.sdk.c.a
            public void a(final boolean z) {
                Chartboost.a(new Runnable() { // from class: com.chartboost.sdk.c.1.1
                    @Override // java.lang.Runnable
                    public void run() {
                        if (z) {
                            c.this.a(aVar, str, bVar);
                        } else {
                            c.this.b.a(aVar, false, str, CBError.CBClickError.AGE_GATE_FAILURE, bVar);
                        }
                    }
                });
            }
        };
        if (!com.chartboost.sdk.b.q()) {
            a(aVar, str, bVar);
        } else if (com.chartboost.sdk.b.d() != null) {
            com.chartboost.sdk.b.d().didPauseClickForConfirmation();
            if (aVar != null) {
                aVar.o = false;
            }
        }
    }

    protected final boolean b() {
        if (c() == null) {
            return false;
        }
        this.a.b(c());
        return true;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static synchronized void b(com.chartboost.sdk.Model.a aVar) {
        ai aiVar = aVar.l() != null ? (ai) aVar.x() : null;
        ba baVar = new ba("/api/video-complete");
        baVar.a("location", (Object) aVar.d);
        baVar.a("reward", (Object) aVar.w().e("reward"));
        baVar.a("currency-name", (Object) aVar.w().e("currency-name"));
        baVar.a("ad_id", (Object) aVar.p());
        baVar.a("force_close", (Object) false);
        if (aiVar != null) {
            float fV = aiVar.v();
            float fU = aiVar.u();
            CBLogging.a(aVar.q().getClass().getSimpleName(), String.format("TotalDuration: %f PlaybackTime: %f", Float.valueOf(fU), Float.valueOf(fV)));
            baVar.a("total_time", Float.valueOf(fU / 1000.0f));
            if (fV < 0.0f) {
                baVar.a("playback_time", Float.valueOf(fU / 1000.0f));
            } else {
                baVar.a("playback_time", Float.valueOf(fV / 1000.0f));
            }
        }
        baVar.a(true);
        baVar.s();
    }

    protected final com.chartboost.sdk.Model.a c() {
        e eVarG = Chartboost.g();
        bq bqVarD = eVarG == null ? null : eVarG.d();
        if (bqVarD == null) {
            return null;
        }
        return bqVarD.h();
    }

    public ba d() {
        ba baVar = new ba("/api/click");
        Context contextE = Chartboost.e();
        if (contextE == null) {
            contextE = Chartboost.getValidContext();
        }
        baVar.b(contextE);
        return baVar;
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    public final boolean a(Activity activity, com.chartboost.sdk.Model.a aVar) {
        if (aVar != null) {
            switch (aVar.b) {
                case LOADING:
                    if (aVar.i) {
                        Chartboost.a(aVar);
                    }
                    break;
                case CACHED:
                case LOADED:
                    Chartboost.a(aVar);
                    break;
                case DISPLAYED:
                    if (!aVar.h()) {
                        if (com.chartboost.sdk.b.a() == Chartboost.CBFramework.CBFrameworkAir && !(activity instanceof CBImpressionActivity)) {
                            return false;
                        }
                        e eVarG = Chartboost.g();
                        if (eVarG != null) {
                            CBLogging.b(c, "Error onActivityStart " + aVar.b.name());
                            eVarG.d(aVar);
                        }
                    }
                    break;
            }
        }
        return true;
    }
}
