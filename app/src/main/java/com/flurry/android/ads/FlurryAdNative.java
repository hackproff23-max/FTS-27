package com.flurry.android.ads;

import android.content.Context;
import android.text.TextUtils;
import android.view.View;
import com.flurry.android.impl.ads.protocol.v13.NativeAsset;
import com.flurry.sdk.d;
import com.flurry.sdk.fp;
import com.flurry.sdk.fy;
import com.flurry.sdk.fz;
import com.flurry.sdk.gd;
import com.flurry.sdk.hq;
import com.flurry.sdk.i;
import com.flurry.sdk.w;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public final class FlurryAdNative {
    private static final String a = FlurryAdNative.class.getSimpleName();
    private w c;
    private FlurryAdNativeListener e;
    private final List<FlurryAdNativeAsset> b = new ArrayList();
    private final fy<d> d = new fy<d>() { // from class: com.flurry.android.ads.FlurryAdNative.1
        @Override // com.flurry.sdk.fy
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public void notify(final d dVar) {
            if (dVar.a == FlurryAdNative.this.c && dVar.b != null) {
                if (d.a.kOnFetched.equals(dVar.b)) {
                    FlurryAdNative.this.a();
                }
                final FlurryAdNativeListener flurryAdNativeListener = FlurryAdNative.this.e;
                if (flurryAdNativeListener != null) {
                    fp.a().a(new hq() { // from class: com.flurry.android.ads.FlurryAdNative.1.1
                        @Override // com.flurry.sdk.hq
                        public void safeRun() {
                            switch (AnonymousClass2.a[dVar.b.ordinal()]) {
                                case 1:
                                    flurryAdNativeListener.onFetched(FlurryAdNative.this);
                                    break;
                                case 2:
                                    flurryAdNativeListener.onError(FlurryAdNative.this, FlurryAdErrorType.FETCH, dVar.c.a());
                                    break;
                                case 3:
                                    flurryAdNativeListener.onShowFullscreen(FlurryAdNative.this);
                                    break;
                                case 4:
                                    flurryAdNativeListener.onCloseFullscreen(FlurryAdNative.this);
                                    break;
                                case 5:
                                    flurryAdNativeListener.onAppExit(FlurryAdNative.this);
                                    break;
                                case 6:
                                    flurryAdNativeListener.onClicked(FlurryAdNative.this);
                                    break;
                                case 7:
                                    flurryAdNativeListener.onError(FlurryAdNative.this, FlurryAdErrorType.CLICK, dVar.c.a());
                                    break;
                                case 8:
                                    flurryAdNativeListener.onImpressionLogged(FlurryAdNative.this);
                                    break;
                            }
                        }
                    });
                }
            }
        }
    };

    /* JADX INFO: renamed from: com.flurry.android.ads.FlurryAdNative$2, reason: invalid class name */
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
                a[d.a.kOnOpen.ordinal()] = 3;
            } catch (NoSuchFieldError e3) {
            }
            try {
                a[d.a.kOnClose.ordinal()] = 4;
            } catch (NoSuchFieldError e4) {
            }
            try {
                a[d.a.kOnAppExit.ordinal()] = 5;
            } catch (NoSuchFieldError e5) {
            }
            try {
                a[d.a.kOnClicked.ordinal()] = 6;
            } catch (NoSuchFieldError e6) {
            }
            try {
                a[d.a.kOnClickFailed.ordinal()] = 7;
            } catch (NoSuchFieldError e7) {
            }
            try {
                a[d.a.kOnImpressionLogged.ordinal()] = 8;
            } catch (NoSuchFieldError e8) {
            }
        }
    }

    public FlurryAdNative(Context context, String str) {
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
            this.c = new w(context, str);
            ArrayList arrayList = new ArrayList();
            arrayList.add(1);
            this.c.a(arrayList);
            fz.a().a("com.flurry.android.impl.ads.AdStateEvent", this.d);
        } catch (Throwable th) {
            gd.a(a, "Exception: ", th);
        }
    }

    public void destroy() {
        try {
            fz.a().b("com.flurry.android.impl.ads.AdStateEvent", this.d);
            this.e = null;
            if (this.c != null) {
                this.c.a();
                this.c = null;
            }
        } catch (Throwable th) {
            gd.a(a, "Exception: ", th);
        }
    }

    public void setListener(FlurryAdNativeListener flurryAdNativeListener) {
        try {
            this.e = flurryAdNativeListener;
        } catch (Throwable th) {
            gd.a(a, "Exception: ", th);
        }
    }

    public void setTargeting(FlurryAdTargeting flurryAdTargeting) {
        try {
            this.c.a(flurryAdTargeting);
        } catch (Throwable th) {
            gd.a(a, "Exception: ", th);
        }
    }

    public void setSupportedStyles(List<Integer> list) {
        try {
            this.c.a(list);
        } catch (Throwable th) {
            gd.a(a, "Exception: ", th);
        }
    }

    public void fetchAd() {
        try {
            this.c.v();
        } catch (Throwable th) {
            gd.a(a, "Exception: ", th);
        }
    }

    public boolean isReady() {
        try {
            return this.c.u();
        } catch (Throwable th) {
            gd.a(a, "Exception: ", th);
            return false;
        }
    }

    public void setTrackingView(View view) {
        try {
            this.c.a(view);
        } catch (Throwable th) {
            gd.a(a, "Exception: ", th);
        }
    }

    public void removeTrackingView() {
        try {
            this.c.w();
        } catch (Throwable th) {
            gd.a(a, "Exception: ", th);
        }
    }

    public int getStyle() {
        try {
            return this.c.x();
        } catch (Throwable th) {
            gd.a(a, "Exception: ", th);
            return 0;
        }
    }

    public FlurryAdNativeAsset getAsset(String str) {
        FlurryAdNativeAsset next;
        if (i.a() != null && !TextUtils.isEmpty(str)) {
            try {
                synchronized (this.b) {
                    try {
                        Iterator<FlurryAdNativeAsset> it = this.b.iterator();
                        while (it.hasNext()) {
                            next = it.next();
                            if (str.equals(next.getName())) {
                            }
                        }
                        next = null;
                    } catch (Throwable th) {
                        throw th;
                    }
                }
                return next;
            } catch (Throwable th2) {
                gd.a(a, "Exception: ", th2);
                return null;
            }
        }
        return null;
    }

    public List<FlurryAdNativeAsset> getAssetList() {
        if (i.a() == null) {
            return Collections.emptyList();
        }
        try {
            ArrayList arrayList = new ArrayList();
            synchronized (this.b) {
                arrayList.addAll(this.b);
            }
            return Collections.unmodifiableList(arrayList);
        } catch (Throwable th) {
            gd.a(a, "Exception: ", th);
            return Collections.emptyList();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a() {
        if (this.c != null) {
            synchronized (this.b) {
                Iterator<NativeAsset> it = this.c.y().iterator();
                while (it.hasNext()) {
                    this.b.add(new FlurryAdNativeAsset(it.next(), this.c.d()));
                }
            }
        }
    }
}
