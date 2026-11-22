package com.chartboost.sdk.impl;

import com.chartboost.sdk.Libraries.CBLogging;
import com.chartboost.sdk.Model.CBError;
import com.facebook.internal.AnalyticsEvents;
import com.mopub.common.AdType;
import org.json.JSONArray;

/* JADX INFO: loaded from: classes.dex */
public class ae extends com.chartboost.sdk.d {
    private static final String b = ae.class.getSimpleName();
    private static ae c;

    protected ae() {
    }

    public static ae f() {
        if (c == null) {
            c = new ae();
        }
        return c;
    }

    protected boolean b(com.chartboost.sdk.Model.a aVar, com.chartboost.sdk.Libraries.e.a aVar2) {
        return aVar2.a("media-type").equals(AnalyticsEvents.PARAMETER_SHARE_DIALOG_CONTENT_VIDEO);
    }

    @Override // com.chartboost.sdk.d
    protected void a(com.chartboost.sdk.Model.a aVar, com.chartboost.sdk.Libraries.e.a aVar2) {
        if (b(aVar, aVar2) && !be.c(aVar2)) {
            CBLogging.b(b, "Video is unavailable so cannot show the video impression");
            a(aVar, CBError.CBImpressionError.VIDEO_UNAVAILABLE);
            if (aVar.f) {
                aVar.a(aVar2);
                be.a(aVar);
            }
            be.b();
            return;
        }
        if (aVar2.c() && aVar2.a("videos").c()) {
            be.a(aVar2.a("videos"));
        }
        super.a(aVar, aVar2);
    }

    @Override // com.chartboost.sdk.d
    public void r(com.chartboost.sdk.Model.a aVar) {
        if (!aVar.w().b()) {
            super.a(aVar, aVar.w());
        } else if (!c(aVar.d) && !aVar.q) {
            a(e(aVar), aVar);
        }
    }

    @Override // com.chartboost.sdk.d
    protected com.chartboost.sdk.Model.a a(String str, boolean z) {
        return new com.chartboost.sdk.Model.a(com.chartboost.sdk.Model.a.c.INTERSTITIAL, z, str, false);
    }

    @Override // com.chartboost.sdk.d
    protected ba e(com.chartboost.sdk.Model.a aVar) {
        ba baVar = new ba("/interstitial/get");
        baVar.a(l.a.HIGH);
        baVar.a(com.chartboost.sdk.Model.b.b);
        baVar.a("local-videos", g());
        return baVar;
    }

    @Override // com.chartboost.sdk.d
    protected void i(com.chartboost.sdk.Model.a aVar) {
        if (aVar.e != com.chartboost.sdk.Model.a.d.INTERSTITIAL_VIDEO) {
            super.i(aVar);
        }
    }

    @Override // com.chartboost.sdk.d
    protected com.chartboost.sdk.d.a c() {
        return new com.chartboost.sdk.d.a() { // from class: com.chartboost.sdk.impl.ae.1
            @Override // com.chartboost.sdk.d.a
            public void a(com.chartboost.sdk.Model.a aVar) {
                if (com.chartboost.sdk.b.d() != null) {
                    com.chartboost.sdk.b.d().didClickInterstitial(aVar.d);
                }
            }

            @Override // com.chartboost.sdk.d.a
            public void b(com.chartboost.sdk.Model.a aVar) {
                if (com.chartboost.sdk.b.d() != null) {
                    com.chartboost.sdk.b.d().didCloseInterstitial(aVar.d);
                }
            }

            @Override // com.chartboost.sdk.d.a
            public void c(com.chartboost.sdk.Model.a aVar) {
                if (com.chartboost.sdk.b.d() == null || aVar == null) {
                    return;
                }
                if (aVar.e == com.chartboost.sdk.Model.a.d.INTERSTITIAL_VIDEO || aVar.e == com.chartboost.sdk.Model.a.d.INTERSTITIAL_REWARD_VIDEO) {
                    com.chartboost.sdk.b.d().didDismissInterstitial(aVar.d);
                }
            }

            @Override // com.chartboost.sdk.d.a
            public void d(com.chartboost.sdk.Model.a aVar) {
                if (com.chartboost.sdk.b.d() != null) {
                    com.chartboost.sdk.b.d().didCacheInterstitial(aVar.d);
                }
            }

            @Override // com.chartboost.sdk.d.a
            public void a(com.chartboost.sdk.Model.a aVar, CBError.CBImpressionError cBImpressionError) {
                if (com.chartboost.sdk.b.d() != null) {
                    com.chartboost.sdk.b.d().didFailToLoadInterstitial(aVar.d, cBImpressionError);
                }
            }

            @Override // com.chartboost.sdk.d.a
            public void e(com.chartboost.sdk.Model.a aVar) {
                if (com.chartboost.sdk.b.d() != null) {
                    com.chartboost.sdk.b.d().didDisplayInterstitial(aVar.d);
                }
            }

            @Override // com.chartboost.sdk.d.a
            public boolean f(com.chartboost.sdk.Model.a aVar) {
                if (com.chartboost.sdk.b.d() != null) {
                    return com.chartboost.sdk.b.d().shouldDisplayInterstitial(aVar.d);
                }
                return true;
            }

            @Override // com.chartboost.sdk.d.a
            public boolean g(com.chartboost.sdk.Model.a aVar) {
                if (com.chartboost.sdk.b.d() != null) {
                    return com.chartboost.sdk.b.d().shouldRequestInterstitial(aVar.d);
                }
                return true;
            }

            @Override // com.chartboost.sdk.d.a
            public boolean h(com.chartboost.sdk.Model.a aVar) {
                if (com.chartboost.sdk.b.d() != null) {
                    return com.chartboost.sdk.b.r();
                }
                return true;
            }
        };
    }

    @Override // com.chartboost.sdk.d
    protected ba l(com.chartboost.sdk.Model.a aVar) {
        return new ba("/interstitial/show");
    }

    public JSONArray g() {
        JSONArray jSONArray = new JSONArray();
        String[] strArrC = be.c();
        if (strArrC != null) {
            for (String str : strArrC) {
                jSONArray.put(str);
            }
        }
        return jSONArray;
    }

    @Override // com.chartboost.sdk.d
    public String e() {
        return AdType.INTERSTITIAL;
    }
}
