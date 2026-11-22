package com.google.android.gms.common;

import android.R;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.text.TextUtils;
import android.util.Base64;
import android.util.Log;
import android.util.TypedValue;
import com.google.android.gms.drive.DriveFile;
import com.google.android.gms.internal.jf;
import com.google.android.gms.internal.jh;
import com.google.android.gms.internal.jo;
import com.google.android.gms.internal.jx;
import com.google.android.gms.internal.lc;
import com.google.android.gms.internal.ll;
import java.io.InputStream;
import java.util.Arrays;
import java.util.NoSuchElementException;
import java.util.Scanner;

/* JADX INFO: loaded from: classes.dex */
public final class GooglePlayServicesUtil {
    public static final String GMS_ERROR_DIALOG = "GooglePlayServicesErrorDialog";
    public static final String GOOGLE_PLAY_SERVICES_PACKAGE = "com.google.android.gms";
    public static final int GOOGLE_PLAY_SERVICES_VERSION_CODE = 6587000;
    public static final String GOOGLE_PLAY_STORE_PACKAGE = "com.android.vending";
    public static boolean Jg = false;
    public static boolean Jh = false;
    private static int Ji = -1;
    private static final Object Jj = new Object();

    private static class a extends Handler {
        private final Context mO;

        a(Context context) {
            super(Looper.myLooper() == null ? Looper.getMainLooper() : Looper.myLooper());
            this.mO = context.getApplicationContext();
        }

        @Override // android.os.Handler
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case 1:
                    int iIsGooglePlayServicesAvailable = GooglePlayServicesUtil.isGooglePlayServicesAvailable(this.mO);
                    if (GooglePlayServicesUtil.isUserRecoverableError(iIsGooglePlayServicesAvailable)) {
                        GooglePlayServicesUtil.a(iIsGooglePlayServicesAvailable, this.mO);
                    }
                    break;
                default:
                    Log.w("GooglePlayServicesUtil", "Don't know how to handle this message: " + msg.what);
                    break;
            }
        }
    }

    private GooglePlayServicesUtil() {
    }

    public static void C(Context context) throws GooglePlayServicesRepairableException, GooglePlayServicesNotAvailableException {
        int iIsGooglePlayServicesAvailable = isGooglePlayServicesAvailable(context);
        if (iIsGooglePlayServicesAvailable != 0) {
            Intent intentAj = aj(iIsGooglePlayServicesAvailable);
            Log.e("GooglePlayServicesUtil", "GooglePlayServices not available due to error " + iIsGooglePlayServicesAvailable);
            if (intentAj != null) {
                throw new GooglePlayServicesRepairableException(iIsGooglePlayServicesAvailable, "Google Play Services not available", intentAj);
            }
            throw new GooglePlayServicesNotAvailableException(iIsGooglePlayServicesAvailable);
        }
    }

    private static void D(Context context) {
        ApplicationInfo applicationInfo = null;
        try {
            applicationInfo = context.getPackageManager().getApplicationInfo(context.getPackageName(), 128);
        } catch (PackageManager.NameNotFoundException e) {
            Log.wtf("GooglePlayServicesUtil", "This should never happen.", e);
        }
        Bundle bundle = applicationInfo.metaData;
        if (bundle == null) {
            throw new IllegalStateException("A required meta-data tag in your app's AndroidManifest.xml does not exist.  You must have the following declaration within the <application> element:     <meta-data android:name=\"com.google.android.gms.version\" android:value=\"@integer/google_play_services_version\" />");
        }
        int i = bundle.getInt("com.google.android.gms.version");
        if (i != 6587000) {
            throw new IllegalStateException("The meta-data tag in your app's AndroidManifest.xml does not have the right value.  Expected 6587000 but found " + i + ".  You must have the following declaration within the <application> element:     <meta-data android:name=\"com.google.android.gms.version\" android:value=\"@integer/google_play_services_version\" />");
        }
    }

    private static void E(Context context) {
        a aVar = new a(context);
        aVar.sendMessageDelayed(aVar.obtainMessage(1), 120000L);
    }

    private static String F(Context context) {
        ApplicationInfo applicationInfo;
        String str = context.getApplicationInfo().name;
        if (!TextUtils.isEmpty(str)) {
            return str;
        }
        String packageName = context.getPackageName();
        PackageManager packageManager = context.getApplicationContext().getPackageManager();
        try {
            applicationInfo = packageManager.getApplicationInfo(context.getPackageName(), 0);
        } catch (PackageManager.NameNotFoundException e) {
            applicationInfo = null;
        }
        return applicationInfo != null ? packageManager.getApplicationLabel(applicationInfo).toString() : packageName;
    }

    /* JADX WARN: Code duplicated, block: B:39:0x0116  */
    private static Dialog a(int i, Activity activity, Fragment fragment, int i2, DialogInterface.OnCancelListener onCancelListener) {
        AlertDialog.Builder builder;
        if (lc.K(activity) && i == 2) {
            i = 42;
        }
        if (ll.ij()) {
            TypedValue typedValue = new TypedValue();
            activity.getTheme().resolveAttribute(R.attr.alertDialogTheme, typedValue, true);
            if ("Theme.Dialog.Alert".equals(activity.getResources().getResourceEntryName(typedValue.resourceId))) {
                builder = new AlertDialog.Builder(activity, 5);
            } else {
                builder = null;
            }
        } else {
            builder = null;
        }
        if (builder == null) {
            builder = new AlertDialog.Builder(activity);
        }
        builder.setMessage(b(activity, i));
        if (onCancelListener != null) {
            builder.setOnCancelListener(onCancelListener);
        }
        Intent intentAj = aj(i);
        jh jhVar = fragment == null ? new jh(activity, intentAj, i2) : new jh(fragment, intentAj, i2);
        String strC = c(activity, i);
        if (strC != null) {
            builder.setPositiveButton(strC, jhVar);
        }
        switch (i) {
            case 0:
                return null;
            case 1:
                return builder.setTitle(com.google.android.gms.R.string.common_google_play_services_install_title).create();
            case 2:
                return builder.setTitle(com.google.android.gms.R.string.common_google_play_services_update_title).create();
            case 3:
                return builder.setTitle(com.google.android.gms.R.string.common_google_play_services_enable_title).create();
            case 4:
            case 6:
                return builder.create();
            case 5:
                Log.e("GooglePlayServicesUtil", "An invalid account was specified when connecting. Please provide a valid account.");
                return builder.setTitle(com.google.android.gms.R.string.common_google_play_services_invalid_account_title).create();
            case 7:
                Log.e("GooglePlayServicesUtil", "Network error occurred. Please retry request later.");
                return builder.setTitle(com.google.android.gms.R.string.common_google_play_services_network_error_title).create();
            case 8:
                Log.e("GooglePlayServicesUtil", "Internal error occurred. Please see logs for detailed information");
                return builder.create();
            case 9:
                Log.e("GooglePlayServicesUtil", "Google Play services is invalid. Cannot recover.");
                return builder.setTitle(com.google.android.gms.R.string.common_google_play_services_unsupported_title).create();
            case 10:
                Log.e("GooglePlayServicesUtil", "Developer error occurred. Please see logs for detailed information");
                return builder.create();
            case 11:
                Log.e("GooglePlayServicesUtil", "The application is not licensed to the user.");
                return builder.create();
            case 42:
                return builder.setTitle(com.google.android.gms.R.string.common_android_wear_update_title).create();
            default:
                Log.e("GooglePlayServicesUtil", "Unexpected error code " + i);
                return builder.create();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void a(int i, Context context) {
        Notification notificationBuild;
        Resources resources = context.getResources();
        String strD = d(context, i);
        String string = resources.getString(com.google.android.gms.R.string.common_google_play_services_error_notification_requested_by_msg, F(context));
        PendingIntent errorPendingIntent = getErrorPendingIntent(i, context, 0);
        if (lc.K(context)) {
            jx.K(ll.ik());
            notificationBuild = new Notification.Builder(context).setSmallIcon(com.google.android.gms.R.drawable.common_ic_googleplayservices).setPriority(2).setAutoCancel(true).setStyle(new Notification.BigTextStyle().bigText(strD + " " + string)).addAction(com.google.android.gms.R.drawable.common_full_open_on_phone, resources.getString(com.google.android.gms.R.string.common_open_on_phone), errorPendingIntent).build();
        } else {
            Notification notification = new Notification(R.drawable.stat_sys_warning, resources.getString(com.google.android.gms.R.string.common_google_play_services_notification_ticker), System.currentTimeMillis());
            notification.flags |= 16;
            notification.setLatestEventInfo(context, strD, string, errorPendingIntent);
            notificationBuild = notification;
        }
        ((NotificationManager) context.getSystemService("notification")).notify(ak(i), notificationBuild);
    }

    public static boolean a(PackageManager packageManager, PackageInfo packageInfo) {
        if (packageInfo == null) {
            return false;
        }
        if (c(packageManager)) {
            return a(packageInfo, true) != null;
        }
        boolean z = a(packageInfo, false) != null;
        if (z || a(packageInfo, true) == null) {
            return z;
        }
        Log.w("GooglePlayServicesUtil", "Test-keys aren't accepted on this build.");
        return z;
    }

    public static boolean a(Resources resources) {
        if (resources == null) {
            return false;
        }
        return (ll.ig() && ((resources.getConfiguration().screenLayout & 15) > 3)) || b(resources);
    }

    private static byte[] a(PackageInfo packageInfo, boolean z) {
        if (packageInfo.signatures.length != 1) {
            Log.w("GooglePlayServicesUtil", "Package has more than one signature.");
            return null;
        }
        byte[] byteArray = packageInfo.signatures[0].toByteArray();
        if ((z ? b.gu() : b.gv()).contains(byteArray)) {
            return byteArray;
        }
        if (Log.isLoggable("GooglePlayServicesUtil", 2)) {
            Log.v("GooglePlayServicesUtil", "Signature not valid.  Found: \n" + Base64.encodeToString(byteArray, 0));
        }
        return null;
    }

    private static byte[] a(PackageInfo packageInfo, byte[]... bArr) {
        if (packageInfo.signatures.length != 1) {
            Log.w("GooglePlayServicesUtil", "Package has more than one signature.");
            return null;
        }
        byte[] byteArray = packageInfo.signatures[0].toByteArray();
        for (byte[] bArr2 : bArr) {
            if (Arrays.equals(bArr2, byteArray)) {
                return bArr2;
            }
        }
        if (Log.isLoggable("GooglePlayServicesUtil", 2)) {
            Log.v("GooglePlayServicesUtil", "Signature not valid.  Found: \n" + Base64.encodeToString(byteArray, 0));
        }
        return null;
    }

    public static Intent aj(int i) {
        switch (i) {
            case 1:
            case 2:
                return jo.ba(GOOGLE_PLAY_SERVICES_PACKAGE);
            case 3:
                return jo.aY(GOOGLE_PLAY_SERVICES_PACKAGE);
            case 42:
                return jo.hE();
            default:
                return null;
        }
    }

    private static int ak(int i) {
        switch (i) {
            case 1:
            case 3:
                return 10436;
            case 2:
            default:
                return 39789;
        }
    }

    public static String b(Context context, int i) {
        Resources resources = context.getResources();
        switch (i) {
            case 1:
                return a(context.getResources()) ? resources.getString(com.google.android.gms.R.string.common_google_play_services_install_text_tablet) : resources.getString(com.google.android.gms.R.string.common_google_play_services_install_text_phone);
            case 2:
                return resources.getString(com.google.android.gms.R.string.common_google_play_services_update_text);
            case 3:
                return resources.getString(com.google.android.gms.R.string.common_google_play_services_enable_text);
            case 5:
                return resources.getString(com.google.android.gms.R.string.common_google_play_services_invalid_account_text);
            case 7:
                return resources.getString(com.google.android.gms.R.string.common_google_play_services_network_error_text);
            case 9:
                return resources.getString(com.google.android.gms.R.string.common_google_play_services_unsupported_text);
            case 42:
                return resources.getString(com.google.android.gms.R.string.common_android_wear_update_text);
            default:
                return resources.getString(com.google.android.gms.R.string.common_google_play_services_unknown_issue);
        }
    }

    public static boolean b(PackageManager packageManager) {
        synchronized (Jj) {
            if (Ji == -1) {
                try {
                    if (a(packageManager.getPackageInfo(GOOGLE_PLAY_SERVICES_PACKAGE, 64), b.Jc[1]) != null) {
                        Ji = 1;
                    } else {
                        Ji = 0;
                    }
                } catch (PackageManager.NameNotFoundException e) {
                    Ji = 0;
                }
            }
        }
        return Ji != 0;
    }

    public static boolean b(PackageManager packageManager, String str) {
        try {
            return a(packageManager, packageManager.getPackageInfo(str, 64));
        } catch (PackageManager.NameNotFoundException e) {
            if (Log.isLoggable("GooglePlayServicesUtil", 3)) {
                Log.d("GooglePlayServicesUtil", "Package manager can't find package " + str + ", defaulting to false");
            }
            return false;
        }
    }

    private static boolean b(Resources resources) {
        Configuration configuration = resources.getConfiguration();
        return ll.ii() && (configuration.screenLayout & 15) <= 3 && configuration.smallestScreenWidthDp >= 600;
    }

    public static String c(Context context, int i) {
        Resources resources = context.getResources();
        switch (i) {
            case 1:
                return resources.getString(com.google.android.gms.R.string.common_google_play_services_install_button);
            case 2:
            case 42:
                return resources.getString(com.google.android.gms.R.string.common_google_play_services_update_button);
            case 3:
                return resources.getString(com.google.android.gms.R.string.common_google_play_services_enable_button);
            default:
                return resources.getString(R.string.ok);
        }
    }

    public static boolean c(PackageManager packageManager) {
        return b(packageManager) || !gw();
    }

    public static String d(Context context, int i) {
        Resources resources = context.getResources();
        switch (i) {
            case 1:
                return resources.getString(com.google.android.gms.R.string.common_google_play_services_notification_needs_installation_title);
            case 2:
                return resources.getString(com.google.android.gms.R.string.common_google_play_services_notification_needs_update_title);
            case 3:
                return resources.getString(com.google.android.gms.R.string.common_google_play_services_needs_enabling_title);
            case 5:
                return resources.getString(com.google.android.gms.R.string.common_google_play_services_invalid_account_text);
            case 7:
                return resources.getString(com.google.android.gms.R.string.common_google_play_services_network_error_text);
            case 9:
                return resources.getString(com.google.android.gms.R.string.common_google_play_services_unsupported_text);
            case 42:
                return resources.getString(com.google.android.gms.R.string.common_android_wear_notification_needs_update_text);
            default:
                return resources.getString(com.google.android.gms.R.string.common_google_play_services_unknown_issue);
        }
    }

    public static boolean e(Context context, int i) {
        if (i == 1) {
            try {
                if (context.getPackageManager().getApplicationInfo(GOOGLE_PLAY_SERVICES_PACKAGE, 8192).enabled) {
                    return true;
                }
            } catch (PackageManager.NameNotFoundException e) {
            }
        }
        return false;
    }

    public static Dialog getErrorDialog(int errorCode, Activity activity, int requestCode) {
        return getErrorDialog(errorCode, activity, requestCode, null);
    }

    public static Dialog getErrorDialog(int errorCode, Activity activity, int requestCode, DialogInterface.OnCancelListener cancelListener) {
        return a(errorCode, activity, null, requestCode, cancelListener);
    }

    public static PendingIntent getErrorPendingIntent(int errorCode, Context context, int requestCode) {
        Intent intentAj = aj(errorCode);
        if (intentAj == null) {
            return null;
        }
        return PendingIntent.getActivity(context, requestCode, intentAj, DriveFile.MODE_READ_ONLY);
    }

    public static String getErrorString(int errorCode) {
        switch (errorCode) {
            case 0:
                return "SUCCESS";
            case 1:
                return "SERVICE_MISSING";
            case 2:
                return "SERVICE_VERSION_UPDATE_REQUIRED";
            case 3:
                return "SERVICE_DISABLED";
            case 4:
                return "SIGN_IN_REQUIRED";
            case 5:
                return "INVALID_ACCOUNT";
            case 6:
                return "RESOLUTION_REQUIRED";
            case 7:
                return "NETWORK_ERROR";
            case 8:
                return "INTERNAL_ERROR";
            case 9:
                return "SERVICE_INVALID";
            case 10:
                return "DEVELOPER_ERROR";
            case 11:
                return "LICENSE_CHECK_FAILED";
            default:
                return "UNKNOWN_ERROR_CODE";
        }
    }

    public static String getOpenSourceSoftwareLicenseInfo(Context context) {
        try {
            InputStream inputStreamOpenInputStream = context.getContentResolver().openInputStream(new Uri.Builder().scheme("android.resource").authority(GOOGLE_PLAY_SERVICES_PACKAGE).appendPath("raw").appendPath("oss_notice").build());
            try {
                String next = new Scanner(inputStreamOpenInputStream).useDelimiter("\\A").next();
            } catch (NoSuchElementException e) {
                return null;
            } finally {
                if (inputStreamOpenInputStream != null) {
                    inputStreamOpenInputStream.close();
                }
            }
        } catch (Exception e2) {
            return null;
        }
    }

    public static Context getRemoteContext(Context context) {
        try {
            return context.createPackageContext(GOOGLE_PLAY_SERVICES_PACKAGE, 3);
        } catch (PackageManager.NameNotFoundException e) {
            return null;
        }
    }

    public static Resources getRemoteResource(Context context) {
        try {
            return context.getPackageManager().getResourcesForApplication(GOOGLE_PLAY_SERVICES_PACKAGE);
        } catch (PackageManager.NameNotFoundException e) {
            return null;
        }
    }

    public static boolean gw() {
        return Jg ? Jh : "user".equals(Build.TYPE);
    }

    public static int isGooglePlayServicesAvailable(Context context) {
        PackageManager packageManager = context.getPackageManager();
        if (!jf.Mv) {
            try {
                context.getResources().getString(com.google.android.gms.R.string.common_google_play_services_unknown_issue);
            } catch (Throwable th) {
                Log.e("GooglePlayServicesUtil", "The Google Play services resources were not found. Check your project configuration to ensure that the resources are included.");
            }
        }
        if (!jf.Mv) {
            D(context);
        }
        try {
            PackageInfo packageInfo = packageManager.getPackageInfo(GOOGLE_PLAY_SERVICES_PACKAGE, 64);
            if (lc.aU(packageInfo.versionCode)) {
                char c = gw() ? (char) 0 : (char) 1;
                if (a(packageInfo, b.Ix[c], b.IE[c], b.IC[c]) == null) {
                    Log.w("GooglePlayServicesUtil", "Google Play Services signature invalid on Glass.");
                    return 9;
                }
                String packageName = context.getPackageName();
                try {
                    PackageInfo packageInfo2 = packageManager.getPackageInfo(packageName, 64);
                    if (!a(packageManager, packageInfo2)) {
                        Log.w("GooglePlayServicesUtil", "Calling package " + packageInfo2.packageName + " signature invalid on Glass.");
                        return 9;
                    }
                } catch (PackageManager.NameNotFoundException e) {
                    Log.w("GooglePlayServicesUtil", "Could not get info for calling package: " + packageName);
                    return 9;
                }
            } else if (!lc.K(context)) {
                try {
                    byte[] bArrA = a(packageManager.getPackageInfo(GOOGLE_PLAY_STORE_PACKAGE, 64), b.Ix);
                    if (bArrA == null) {
                        Log.w("GooglePlayServicesUtil", "Google Play Store signature invalid.");
                        return 9;
                    }
                    if (a(packageInfo, bArrA) == null) {
                        Log.w("GooglePlayServicesUtil", "Google Play services signature invalid.");
                        return 9;
                    }
                } catch (PackageManager.NameNotFoundException e2) {
                    Log.w("GooglePlayServicesUtil", "Google Play Store is missing.");
                    return 9;
                }
            } else if (a(packageInfo, b.Ix) == null) {
                Log.w("GooglePlayServicesUtil", "Google Play services signature invalid.");
                return 9;
            }
            if (lc.aS(packageInfo.versionCode) < lc.aS(GOOGLE_PLAY_SERVICES_VERSION_CODE)) {
                Log.w("GooglePlayServicesUtil", "Google Play services out of date.  Requires 6587000 but found " + packageInfo.versionCode);
                return 2;
            }
            try {
                return !packageManager.getApplicationInfo(GOOGLE_PLAY_SERVICES_PACKAGE, 0).enabled ? 3 : 0;
            } catch (PackageManager.NameNotFoundException e3) {
                Log.wtf("GooglePlayServicesUtil", "Google Play services missing when getting application info.");
                e3.printStackTrace();
                return 1;
            }
        } catch (PackageManager.NameNotFoundException e4) {
            Log.w("GooglePlayServicesUtil", "Google Play services is missing.");
            return 1;
        }
    }

    public static boolean isGoogleSignedUid(PackageManager packageManager, int uid) {
        if (packageManager == null) {
            throw new SecurityException("Unknown error: invalid Package Manager");
        }
        String[] packagesForUid = packageManager.getPackagesForUid(uid);
        if (packagesForUid.length == 0 || !b(packageManager, packagesForUid[0])) {
            throw new SecurityException("Uid is not Google Signed");
        }
        return true;
    }

    public static boolean isUserRecoverableError(int errorCode) {
        switch (errorCode) {
            case 1:
            case 2:
            case 3:
            case 9:
                return true;
            case 4:
            case 5:
            case 6:
            case 7:
            case 8:
            default:
                return false;
        }
    }

    public static boolean showErrorDialogFragment(int errorCode, Activity activity, int requestCode) {
        return showErrorDialogFragment(errorCode, activity, requestCode, null);
    }

    public static boolean showErrorDialogFragment(int errorCode, Activity activity, int requestCode, DialogInterface.OnCancelListener cancelListener) {
        return showErrorDialogFragment(errorCode, activity, null, requestCode, cancelListener);
    }

    public static boolean showErrorDialogFragment(int errorCode, Activity activity, Fragment fragment, int requestCode, DialogInterface.OnCancelListener cancelListener) {
        boolean z = false;
        Dialog dialogA = a(errorCode, activity, fragment, requestCode, cancelListener);
        if (dialogA == null) {
            return false;
        }
        try {
            z = activity instanceof FragmentActivity;
        } catch (NoClassDefFoundError e) {
        }
        if (z) {
            SupportErrorDialogFragment.newInstance(dialogA, cancelListener).show(((FragmentActivity) activity).getSupportFragmentManager(), GMS_ERROR_DIALOG);
        } else {
            if (!ll.ig()) {
                throw new RuntimeException("This Activity does not support Fragments.");
            }
            ErrorDialogFragment.newInstance(dialogA, cancelListener).show(activity.getFragmentManager(), GMS_ERROR_DIALOG);
        }
        return true;
    }

    public static void showErrorNotification(int errorCode, Context context) {
        if (lc.K(context) && errorCode == 2) {
            errorCode = 42;
        }
        if (e(context, errorCode)) {
            E(context);
        } else {
            a(errorCode, context);
        }
    }
}
