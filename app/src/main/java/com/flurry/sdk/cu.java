package com.flurry.sdk;

import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import com.flurry.android.FlurryFullscreenTakeoverActivity;

/* JADX INFO: loaded from: classes.dex */
public class cu {
    private static final String a = cu.class.getSimpleName();

    public static boolean a(Context context, String str, boolean z, r rVar, boolean z2, boolean z3) {
        boolean zB = false;
        if (cv.d(str)) {
            zB = a(context, str);
        }
        if (!zB && cv.f(str)) {
            zB = b(context, str);
        }
        if (!zB) {
            if (z) {
                return a(context, str, z3);
            }
            return a(context, rVar, str, z2, z3);
        }
        return zB;
    }

    public static boolean a(Context context, String str) {
        if (context == null || TextUtils.isEmpty(str) || !cv.d(str)) {
            return false;
        }
        return a(context, new Intent("android.intent.action.VIEW").setData(Uri.parse(str)));
    }

    public static boolean b(Context context, String str) {
        Intent intentA = cs.a(context, str);
        if (intentA == null) {
            return false;
        }
        return a(context, intentA);
    }

    public static boolean a(Context context, String str, r rVar, boolean z) {
        Intent launchIntentForPackage = fp.a().e().getLaunchIntentForPackage(str);
        return (launchIntentForPackage == null || !hp.a(launchIntentForPackage)) ? a(context, "https://play.google.com/store/apps/details?id=" + str, false, rVar, true, z) : a(context, launchIntentForPackage);
    }

    public static boolean c(Context context, String str) {
        Intent intentA = cs.a(context, str);
        if (intentA == null || !hp.a(intentA)) {
            return false;
        }
        return a(context, intentA);
    }

    public static boolean a(Context context, r rVar, String str, boolean z, boolean z2) {
        if (context == null) {
            return false;
        }
        Intent intent = new Intent(context, (Class<?>) FlurryFullscreenTakeoverActivity.class);
        intent.putExtra(FlurryFullscreenTakeoverActivity.EXTRA_KEY_AD_OBJECT_LEGACY, rVar instanceof u);
        intent.putExtra(FlurryFullscreenTakeoverActivity.EXTRA_KEY_AD_OBJECT_ID, rVar.d());
        intent.putExtra("url", str);
        intent.putExtra(FlurryFullscreenTakeoverActivity.EXTRA_KEY_CLOSE_AD, z);
        intent.putExtra(FlurryFullscreenTakeoverActivity.EXTRA_KEY_SEND_Y_COOKIES, z2);
        return a(context, intent);
    }

    public static boolean a(Context context, r rVar, boolean z, boolean z2) {
        if (context == null) {
            return false;
        }
        return a(context, rVar, null, z, z2);
    }

    public static boolean a(Context context, String str, boolean z) {
        if (context == null || TextUtils.isEmpty(str)) {
            return false;
        }
        Intent data = new Intent("android.intent.action.VIEW").setData(Uri.parse(str));
        if (z) {
            Bundle bundle = new Bundle();
            bundle.putString("Cookie", i.a().h().d());
            data.putExtra("com.android.browser.headers", bundle);
        }
        return a(context, data);
    }

    private static boolean a(Context context, Intent intent) {
        if (context == null || intent == null) {
            return false;
        }
        try {
            context.startActivity(intent);
            return true;
        } catch (ActivityNotFoundException e) {
            return false;
        }
    }
}
