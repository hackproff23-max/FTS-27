package com.google.android.gms.maps.internal;

import android.content.Context;
import android.os.Build;
import android.os.IBinder;
import android.os.RemoteException;
import android.util.Log;
import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import com.google.android.gms.common.GooglePlayServicesUtil;
import com.google.android.gms.internal.jx;
import com.google.android.gms.maps.model.RuntimeRemoteException;

/* JADX INFO: loaded from: classes.dex */
public class x {
    private static Context alI;
    private static c alJ;

    public static c S(Context context) throws GooglePlayServicesNotAvailableException {
        jx.i(context);
        if (alJ != null) {
            return alJ;
        }
        T(context);
        alJ = U(context);
        try {
            alJ.a(com.google.android.gms.dynamic.e.k(getRemoteContext(context).getResources()), GooglePlayServicesUtil.GOOGLE_PLAY_SERVICES_VERSION_CODE);
            return alJ;
        } catch (RemoteException e) {
            throw new RuntimeRemoteException(e);
        }
    }

    private static void T(Context context) throws GooglePlayServicesNotAvailableException {
        int iIsGooglePlayServicesAvailable = GooglePlayServicesUtil.isGooglePlayServicesAvailable(context);
        switch (iIsGooglePlayServicesAvailable) {
            case 0:
                return;
            default:
                throw new GooglePlayServicesNotAvailableException(iIsGooglePlayServicesAvailable);
        }
    }

    private static c U(Context context) {
        if (nY()) {
            Log.i(x.class.getSimpleName(), "Making Creator statically");
            return (c) c(nZ());
        }
        Log.i(x.class.getSimpleName(), "Making Creator dynamically");
        return c.a.aS((IBinder) a(getRemoteContext(context).getClassLoader(), "com.google.android.gms.maps.internal.CreatorImpl"));
    }

    private static <T> T a(ClassLoader classLoader, String str) {
        try {
            return (T) c(((ClassLoader) jx.i(classLoader)).loadClass(str));
        } catch (ClassNotFoundException e) {
            throw new IllegalStateException("Unable to find dynamic class " + str);
        }
    }

    private static <T> T c(Class<?> cls) {
        try {
            return (T) cls.newInstance();
        } catch (IllegalAccessException e) {
            throw new IllegalStateException("Unable to call the default constructor of " + cls.getName());
        } catch (InstantiationException e2) {
            throw new IllegalStateException("Unable to instantiate the dynamic class " + cls.getName());
        }
    }

    private static Context getRemoteContext(Context context) {
        if (alI == null) {
            if (nY()) {
                alI = context.getApplicationContext();
            } else {
                alI = GooglePlayServicesUtil.getRemoteContext(context);
            }
        }
        return alI;
    }

    public static boolean nY() {
        return false;
    }

    private static Class<?> nZ() {
        try {
            return Build.VERSION.SDK_INT < 15 ? Class.forName("com.google.android.gms.maps.internal.CreatorImplGmm6") : Class.forName("com.google.android.gms.maps.internal.CreatorImpl");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}
