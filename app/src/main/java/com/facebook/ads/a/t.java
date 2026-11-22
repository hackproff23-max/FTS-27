package com.facebook.ads.a;

import android.content.Context;
import android.os.Looper;
import com.mopub.common.GpsHelper;
import java.lang.reflect.Method;

/* JADX INFO: loaded from: classes.dex */
public class t {
    private final String a;
    private final boolean b;

    private t(String str, boolean z) {
        this.a = str;
        this.b = z;
    }

    public static t a(Context context, p.a aVar) {
        Object objA;
        if (Looper.myLooper() == Looper.getMainLooper()) {
            throw new IllegalStateException("Cannot get advertising info on main thread.");
        }
        if (aVar != null && !ag.a(aVar.b)) {
            return new t(aVar.b, aVar.c);
        }
        Method methodA = p.a("com.google.android.gms.common.GooglePlayServicesUtil", "isGooglePlayServicesAvailable", Context.class);
        if (methodA == null) {
            return null;
        }
        Object objA2 = p.a((Object) null, methodA, context);
        if (objA2 == null || ((Integer) objA2).intValue() != 0) {
            return null;
        }
        Method methodA2 = p.a("com.google.android.gms.ads.identifier.AdvertisingIdClient", "getAdvertisingIdInfo", Context.class);
        if (methodA2 != null && (objA = p.a((Object) null, methodA2, context)) != null) {
            Method methodA3 = p.a(objA.getClass(), "getId", new Class[0]);
            Method methodA4 = p.a(objA.getClass(), GpsHelper.IS_LIMIT_AD_TRACKING_ENABLED_KEY, new Class[0]);
            if (methodA3 == null || methodA4 == null) {
                return null;
            }
            return new t((String) p.a(objA, methodA3, new Object[0]), ((Boolean) p.a(objA, methodA4, new Object[0])).booleanValue());
        }
        return null;
    }

    public String a() {
        return this.a;
    }

    public boolean b() {
        return this.b;
    }
}
