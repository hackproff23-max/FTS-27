package com.google.android.gms.internal;

import android.content.Intent;
import android.os.Bundle;
import com.firsttouchgames.util.IabHelper;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes.dex */
@ey
public final class ei {
    public static String D(String str) {
        if (str == null) {
            return null;
        }
        try {
            return new JSONObject(str).getString("developerPayload");
        } catch (JSONException e) {
            gr.W("Fail to parse purchase data");
            return null;
        }
    }

    public static String E(String str) {
        if (str == null) {
            return null;
        }
        try {
            return new JSONObject(str).getString("purchaseToken");
        } catch (JSONException e) {
            gr.W("Fail to parse purchase data");
            return null;
        }
    }

    public static int b(Bundle bundle) {
        Object obj = bundle.get(IabHelper.RESPONSE_CODE);
        if (obj == null) {
            gr.W("Bundle with null response code, assuming OK (known issue)");
            return 0;
        }
        if (obj instanceof Integer) {
            return ((Integer) obj).intValue();
        }
        if (obj instanceof Long) {
            return (int) ((Long) obj).longValue();
        }
        gr.W("Unexpected type for intent response code. " + obj.getClass().getName());
        return 5;
    }

    public static int d(Intent intent) {
        Object obj = intent.getExtras().get(IabHelper.RESPONSE_CODE);
        if (obj == null) {
            gr.W("Intent with no response code, assuming OK (known issue)");
            return 0;
        }
        if (obj instanceof Integer) {
            return ((Integer) obj).intValue();
        }
        if (obj instanceof Long) {
            return (int) ((Long) obj).longValue();
        }
        gr.W("Unexpected type for intent response code. " + obj.getClass().getName());
        return 5;
    }

    public static String e(Intent intent) {
        if (intent == null) {
            return null;
        }
        return intent.getStringExtra(IabHelper.RESPONSE_INAPP_PURCHASE_DATA);
    }

    public static String f(Intent intent) {
        if (intent == null) {
            return null;
        }
        return intent.getStringExtra(IabHelper.RESPONSE_INAPP_SIGNATURE);
    }
}
