package com.chartboost.sdk.impl;

import com.chartboost.sdk.Libraries.CBLogging;
import com.chartboost.sdk.Model.CBError;

/* JADX INFO: loaded from: classes.dex */
public class af extends ae {
    private static af b;
    private static String c = "CBRewardedVideo";

    private af() {
    }

    public static af h() {
        if (b == null) {
            b = new af();
        }
        return b;
    }

    @Override // com.chartboost.sdk.impl.ae
    protected boolean b(com.chartboost.sdk.Model.a aVar, com.chartboost.sdk.Libraries.e.a aVar2) {
        return true;
    }

    @Override // com.chartboost.sdk.impl.ae, com.chartboost.sdk.d
    protected com.chartboost.sdk.Model.a a(String str, boolean z) {
        return new com.chartboost.sdk.Model.a(com.chartboost.sdk.Model.a.c.REWARDED_VIDEO, z, str, false);
    }

    @Override // com.chartboost.sdk.d
    protected boolean d(com.chartboost.sdk.Model.a aVar) {
        return !be.b(aVar);
    }

    @Override // com.chartboost.sdk.impl.ae, com.chartboost.sdk.d
    protected ba e(com.chartboost.sdk.Model.a aVar) {
        ba baVar = new ba("/reward/get");
        baVar.a(l.a.HIGH);
        baVar.a(com.chartboost.sdk.Model.b.b);
        baVar.a("local-videos", h().g());
        return baVar;
    }

    @Override // com.chartboost.sdk.impl.ae, com.chartboost.sdk.d
    protected ba l(com.chartboost.sdk.Model.a aVar) {
        ba baVarL = super.l(aVar);
        baVarL.a("/reward/show");
        return baVarL;
    }

    @Override // com.chartboost.sdk.impl.ae, com.chartboost.sdk.d
    protected void i(com.chartboost.sdk.Model.a aVar) {
    }

    @Override // com.chartboost.sdk.d
    protected void g(com.chartboost.sdk.Model.a aVar) {
        if (!be.c(aVar.w())) {
            CBLogging.b(c, "Video not available in the cache, so cannot show rewarded video");
            a(aVar, CBError.CBImpressionError.VIDEO_UNAVAILABLE);
            be.b();
            return;
        }
        super.g(aVar);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.chartboost.sdk.d
    public void h(final com.chartboost.sdk.Model.a aVar) {
        final com.chartboost.sdk.Libraries.e.a aVarA = aVar.w().a("ux").a("pre-popup");
        if (aVarA.c() && aVarA.a("title").d() && aVarA.a("text").d() && aVarA.a("confirm").d() && aVarA.a("cancel").d() && d() != null) {
            a.post(new Runnable() { // from class: com.chartboost.sdk.impl.af.1
                @Override // java.lang.Runnable
                public void run() {
                    bm.a aVar2 = new bm.a();
                    aVar2.a(aVarA.e("title")).b(aVarA.e("text")).d(aVarA.e("confirm")).c(aVarA.e("cancel"));
                    aVar2.a(af.this.d(), new bm.b() { // from class: com.chartboost.sdk.impl.af.1.1
                        @Override // com.chartboost.sdk.impl.bm.b
                        public void a(bm bmVar) {
                            af.this.a(aVar, CBError.CBImpressionError.USER_CANCELLATION);
                        }

                        @Override // com.chartboost.sdk.impl.bm.b
                        public void a(bm bmVar, int i) {
                            if (i == 1) {
                                af.super.h(aVar);
                            } else {
                                af.this.a(aVar, CBError.CBImpressionError.USER_CANCELLATION);
                            }
                        }
                    });
                }
            });
        } else {
            super.h(aVar);
        }
    }

    protected void s(com.chartboost.sdk.Model.a aVar) {
        final com.chartboost.sdk.Libraries.e.a aVarA = aVar.w().a("ux").a("post-popup");
        if (aVarA.c() && aVarA.a("title").d() && aVarA.a("text").d() && aVarA.a("confirm").d() && d() != null && aVar.l) {
            a.post(new Runnable() { // from class: com.chartboost.sdk.impl.af.2
                @Override // java.lang.Runnable
                public void run() {
                    bm.a aVar2 = new bm.a();
                    aVar2.a(aVarA.e("title")).b(aVarA.e("text")).c(aVarA.e("confirm"));
                    aVar2.a(af.this.d(), new bm.b() { // from class: com.chartboost.sdk.impl.af.2.1
                        @Override // com.chartboost.sdk.impl.bm.b
                        public void a(bm bmVar, int i) {
                            CBLogging.c(af.c, "post-popup dismissed");
                        }
                    });
                }
            });
        }
    }

    @Override // com.chartboost.sdk.impl.ae, com.chartboost.sdk.d
    protected com.chartboost.sdk.d.a c() {
        return new com.chartboost.sdk.d.a() { // from class: com.chartboost.sdk.impl.af.3
            @Override // com.chartboost.sdk.d.a
            public void a(com.chartboost.sdk.Model.a aVar) {
                if (com.chartboost.sdk.b.d() != null) {
                    com.chartboost.sdk.b.d().didClickRewardedVideo(aVar.d);
                }
            }

            @Override // com.chartboost.sdk.d.a
            public void b(com.chartboost.sdk.Model.a aVar) {
                if (com.chartboost.sdk.b.d() != null) {
                    com.chartboost.sdk.b.d().didCloseRewardedVideo(aVar.d);
                }
            }

            @Override // com.chartboost.sdk.d.a
            public void c(com.chartboost.sdk.Model.a aVar) {
                af.this.s(aVar);
            }

            @Override // com.chartboost.sdk.d.a
            public void d(com.chartboost.sdk.Model.a aVar) {
                if (com.chartboost.sdk.b.d() != null) {
                    com.chartboost.sdk.b.d().didCacheRewardedVideo(aVar.d);
                }
            }

            @Override // com.chartboost.sdk.d.a
            public void a(com.chartboost.sdk.Model.a aVar, CBError.CBImpressionError cBImpressionError) {
                if (com.chartboost.sdk.b.d() != null) {
                    com.chartboost.sdk.b.d().didFailToLoadRewardedVideo(aVar.d, cBImpressionError);
                }
            }

            @Override // com.chartboost.sdk.d.a
            public void e(com.chartboost.sdk.Model.a aVar) {
                if (com.chartboost.sdk.b.d() != null) {
                    com.chartboost.sdk.b.d().didDisplayRewardedVideo(aVar.d);
                }
            }

            @Override // com.chartboost.sdk.d.a
            public boolean f(com.chartboost.sdk.Model.a aVar) {
                if (com.chartboost.sdk.b.d() != null) {
                    return com.chartboost.sdk.b.d().shouldDisplayRewardedVideo(aVar.d);
                }
                return true;
            }

            @Override // com.chartboost.sdk.d.a
            public boolean g(com.chartboost.sdk.Model.a aVar) {
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

    @Override // com.chartboost.sdk.impl.ae, com.chartboost.sdk.d
    public String e() {
        return "rewarded-video";
    }
}
