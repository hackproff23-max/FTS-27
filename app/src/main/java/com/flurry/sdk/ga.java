package com.flurry.sdk;

import com.mopub.nativeads.MoPubNativeAdPositioning;
import java.util.Comparator;

/* JADX INFO: loaded from: classes.dex */
public class ga implements Comparator<Runnable> {
    private static final String a = ga.class.getSimpleName();

    @Override // java.util.Comparator
    /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
    public int compare(Runnable runnable, Runnable runnable2) {
        int iA = a(runnable);
        int iA2 = a(runnable2);
        if (iA < iA2) {
            return -1;
        }
        if (iA > iA2) {
            return 1;
        }
        return 0;
    }

    private int a(Runnable runnable) {
        int i;
        if (runnable == null) {
            return MoPubNativeAdPositioning.MoPubClientPositioning.NO_REPEAT;
        }
        if (runnable instanceof gb) {
            hr hrVar = (hr) ((gb) runnable).a();
            if (hrVar == null) {
                i = Integer.MAX_VALUE;
            } else {
                i = hrVar.i();
            }
            return i;
        }
        if (runnable instanceof hr) {
            return ((hr) runnable).i();
        }
        gd.a(6, a, "Unknown runnable class: " + runnable.getClass().getName());
        return MoPubNativeAdPositioning.MoPubClientPositioning.NO_REPEAT;
    }
}
