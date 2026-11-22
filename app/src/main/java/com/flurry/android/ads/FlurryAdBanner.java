package com.flurry.android.ads;

import android.content.Context;
import android.text.TextUtils;
import android.view.ViewGroup;
import com.flurry.sdk.d;
import com.flurry.sdk.fp;
import com.flurry.sdk.fy;
import com.flurry.sdk.fz;
import com.flurry.sdk.gd;
import com.flurry.sdk.hq;
import com.flurry.sdk.i;
import com.flurry.sdk.q;

/* JADX INFO: loaded from: classes.dex */
public final class FlurryAdBanner {
    private static final String a = FlurryAdBanner.class.getSimpleName();
    private q b;
    private final fy<d> c = new fy<d>() { // from class: com.flurry.android.ads.FlurryAdBanner.1
        @Override // com.flurry.sdk.fy
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public void notify(final d dVar) {
            final FlurryAdBannerListener flurryAdBannerListener;
            if (dVar.a == FlurryAdBanner.this.b && dVar.b != null && (flurryAdBannerListener = FlurryAdBanner.this.d) != null) {
                fp.a().a(new hq() { // from class: com.flurry.android.ads.FlurryAdBanner.1.1
                    @Override // com.flurry.sdk.hq
                    public void safeRun() {
                        switch (AnonymousClass2.a[dVar.b.ordinal()]) {
                            case 1:
                                flurryAdBannerListener.onFetched(FlurryAdBanner.this);
                                break;
                            case 2:
                                flurryAdBannerListener.onError(FlurryAdBanner.this, FlurryAdErrorType.FETCH, dVar.c.a());
                                break;
                            case 3:
                                flurryAdBannerListener.onRendered(FlurryAdBanner.this);
                                break;
                            case 4:
                                flurryAdBannerListener.onError(FlurryAdBanner.this, FlurryAdErrorType.RENDER, dVar.c.a());
                                break;
                            case 5:
                                flurryAdBannerListener.onShowFullscreen(FlurryAdBanner.this);
                                break;
                            case 6:
                                flurryAdBannerListener.onCloseFullscreen(FlurryAdBanner.this);
                                break;
                            case 7:
                                flurryAdBannerListener.onAppExit(FlurryAdBanner.this);
                                break;
                            case 8:
                                flurryAdBannerListener.onClicked(FlurryAdBanner.this);
                                break;
                            case 9:
                                flurryAdBannerListener.onVideoCompleted(FlurryAdBanner.this);
                                break;
                            case 10:
                                flurryAdBannerListener.onError(FlurryAdBanner.this, FlurryAdErrorType.CLICK, dVar.c.a());
                                break;
                        }
                    }
                });
            }
        }
    };
    private FlurryAdBannerListener d;

    /* JADX INFO: renamed from: com.flurry.android.ads.FlurryAdBanner$2, reason: invalid class name */
    static /* synthetic */ class AnonymousClass2 {
        static final /* synthetic */ int[] a = new int[d.a.values().length];

        static {
            try {
                a[d.a.kOnFetched.ordinal()] = 1;
            } catch (NoSuchFieldError e) {
            }
            try {
                a[d.a.kOnFetchFailed.ordinal()] = 2;
            } catch (NoSuchFieldError e2) {
            }
            try {
                a[d.a.kOnRendered.ordinal()] = 3;
            } catch (NoSuchFieldError e3) {
            }
            try {
                a[d.a.kOnRenderFailed.ordinal()] = 4;
            } catch (NoSuchFieldError e4) {
            }
            try {
                a[d.a.kOnOpen.ordinal()] = 5;
            } catch (NoSuchFieldError e5) {
            }
            try {
                a[d.a.kOnClose.ordinal()] = 6;
            } catch (NoSuchFieldError e6) {
            }
            try {
                a[d.a.kOnAppExit.ordinal()] = 7;
            } catch (NoSuchFieldError e7) {
            }
            try {
                a[d.a.kOnClicked.ordinal()] = 8;
            } catch (NoSuchFieldError e8) {
            }
            try {
                a[d.a.kOnVideoCompleted.ordinal()] = 9;
            } catch (NoSuchFieldError e9) {
            }
            try {
                a[d.a.kOnClickFailed.ordinal()] = 10;
            } catch (NoSuchFieldError e10) {
            }
        }
    }

    public FlurryAdBanner(Context context, ViewGroup viewGroup, String str) {
        if (fp.a() == null) {
            throw new IllegalStateException("Flurry SDK must be initialized before starting a session");
        }
        if (context == null) {
            throw new IllegalArgumentException("Context cannot be null!");
        }
        if (TextUtils.isEmpty(str)) {
            throw new IllegalArgumentException("Ad space must be specified!");
        }
        try {
            if (i.a() == null) {
                throw new IllegalStateException("Could not find FlurryAds module. Please make sure the library is included.");
            }
            this.b = new q(context, viewGroup, str);
            fz.a().a("com.flurry.android.impl.ads.AdStateEvent", this.c);
        } catch (Throwable th) {
            gd.a(a, "Exception: ", th);
        }
    }

    public void destroy() {
        try {
            fz.a().b("com.flurry.android.impl.ads.AdStateEvent", this.c);
            this.d = null;
            if (this.b != null) {
                this.b.a();
                this.b = null;
            }
        } catch (Throwable th) {
            gd.a(a, "Exception: ", th);
        }
    }

    public void setListener(FlurryAdBannerListener flurryAdBannerListener) {
        try {
            this.d = flurryAdBannerListener;
        } catch (Throwable th) {
            gd.a(a, "Exception: ", th);
        }
    }

    public void setTargeting(FlurryAdTargeting flurryAdTargeting) {
        try {
            this.b.a(flurryAdTargeting);
        } catch (Throwable th) {
            gd.a(a, "Exception: ", th);
        }
    }

    public void fetchAd() {
        try {
            this.b.v();
        } catch (Throwable th) {
            gd.a(a, "Exception: ", th);
        }
    }

    public void displayAd() {
        try {
            this.b.w();
        } catch (Throwable th) {
            gd.a(a, "Exception: ", th);
        }
    }

    public void fetchAndDisplayAd() {
        try {
            this.b.x();
        } catch (Throwable th) {
            gd.a(a, "Exception: ", th);
        }
    }

    public boolean isReady() {
        try {
            return this.b.u();
        } catch (Throwable th) {
            gd.a(a, "Exception: ", th);
            return false;
        }
    }
}
