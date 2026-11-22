package com.flurry.sdk;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager;
import android.util.SparseArray;
import android.util.SparseIntArray;
import com.flurry.android.impl.ads.protocol.v13.ScreenOrientationType;

/* JADX INFO: loaded from: classes.dex */
public final class cn {
    private static final String a = cn.class.getSimpleName();
    private static final SparseArray<SparseIntArray> b = c();

    public static boolean a(Context context) {
        boolean z = (context.getResources().getConfiguration().screenLayout & 15) >= 3;
        gd.b(a, "isTablet " + z);
        return z;
    }

    public static void a(Activity activity, int i) {
        if (activity == null) {
            gd.b(a, "Context is null. Cannot set requested orientation.");
        } else if (a((Context) activity)) {
            gd.b(a, "setOrientation SCREEN_ORIENTATION_SENSOR");
            activity.setRequestedOrientation(4);
        } else {
            gd.b(a, "setOrientation " + i);
            activity.setRequestedOrientation(i);
        }
    }

    public static int a() {
        return 7;
    }

    public static int b() {
        return 6;
    }

    public static boolean a(Activity activity, int i, boolean z) {
        if (activity == null) {
            return false;
        }
        if (!b(activity)) {
            int iB = b(activity, i);
            if (-1 == iB) {
                gd.a(5, a, "cannot set requested orientation without restarting activity, requestedOrientation = " + i);
                gd.b(a, "cannot set requested orientation without restarting activity. It is recommended to add keyboardHidden|orientation|screenSize for android:configChanges attribute for activity: " + activity.getComponentName().getClassName());
                return false;
            }
            i = iB;
            z = true;
        }
        activity.setRequestedOrientation(i);
        if (z) {
            return true;
        }
        activity.setRequestedOrientation(-1);
        return true;
    }

    public static ActivityInfo a(PackageManager packageManager, ComponentName componentName) {
        if (packageManager == null || componentName == null) {
            return null;
        }
        try {
            return packageManager.getActivityInfo(componentName, 0);
        } catch (PackageManager.NameNotFoundException e) {
            gd.a(5, a, "cannot find info for activity: " + componentName);
            return null;
        }
    }

    public static ActivityInfo a(Activity activity) {
        if (activity == null) {
            return null;
        }
        return a(activity.getPackageManager(), activity.getComponentName());
    }

    public static boolean b(Activity activity) {
        int iA = a(a(activity));
        return ((iA & 128) == 0 || (iA & 1024) == 0) ? false : true;
    }

    @TargetApi(13)
    public static int a(ActivityInfo activityInfo) {
        if (activityInfo == null) {
            return 0;
        }
        int i = activityInfo.configChanges;
        if (activityInfo.applicationInfo.targetSdkVersion < 13) {
            return i | 3072;
        }
        return i;
    }

    public static int b(Activity activity, int i) {
        if (activity == null) {
            return -1;
        }
        return a(activity, i, activity.getResources().getConfiguration().orientation);
    }

    public static int a(Activity activity, int i, int i2) {
        int i3;
        if (activity == null) {
            return -1;
        }
        SparseIntArray sparseIntArray = b.get(i2);
        if (sparseIntArray == null) {
            i3 = -1;
        } else {
            i3 = sparseIntArray.get(i, -1);
        }
        return i3;
    }

    public static int a(Activity activity, ScreenOrientationType screenOrientationType) {
        int i = 0;
        if (ScreenOrientationType.PORTRAIT.equals(screenOrientationType)) {
            i = 1;
        } else if (ScreenOrientationType.LANDSCAPE.equals(screenOrientationType)) {
            i = 2;
        }
        return a(activity, -1, i);
    }

    private static SparseArray<SparseIntArray> c() {
        SparseArray<SparseIntArray> sparseArray = new SparseArray<>();
        sparseArray.put(1, d());
        sparseArray.put(2, e());
        return sparseArray;
    }

    private static SparseIntArray d() {
        int iA = a();
        SparseIntArray sparseIntArray = new SparseIntArray();
        sparseIntArray.put(-1, iA);
        sparseIntArray.put(2, iA);
        sparseIntArray.put(3, iA);
        sparseIntArray.put(4, iA);
        sparseIntArray.put(1, 1);
        sparseIntArray.put(5, 5);
        sparseIntArray.put(7, 7);
        sparseIntArray.put(9, 9);
        sparseIntArray.put(10, 7);
        return sparseIntArray;
    }

    private static SparseIntArray e() {
        int iB = b();
        SparseIntArray sparseIntArray = new SparseIntArray();
        sparseIntArray.put(-1, iB);
        sparseIntArray.put(2, iB);
        sparseIntArray.put(3, iB);
        sparseIntArray.put(4, iB);
        sparseIntArray.put(0, 0);
        sparseIntArray.put(5, 5);
        sparseIntArray.put(6, 6);
        sparseIntArray.put(8, 8);
        sparseIntArray.put(10, 6);
        return sparseIntArray;
    }
}
