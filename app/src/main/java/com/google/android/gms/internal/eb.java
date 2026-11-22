package com.google.android.gms.internal;

import android.content.Context;
import android.os.Bundle;
import android.os.IBinder;
import com.firsttouchgames.util.IabHelper;

/* JADX INFO: loaded from: classes.dex */
@ey
public class eb {
    private final Context mContext;
    private Object sC;

    public eb(Context context) {
        this.mContext = context;
    }

    public Bundle a(String str, String str2, String str3) {
        try {
            Class<?> clsLoadClass = this.mContext.getClassLoader().loadClass("com.android.vending.billing.IInAppBillingService");
            return (Bundle) clsLoadClass.getDeclaredMethod("getBuyIntent", Integer.TYPE, String.class, String.class, String.class, String.class).invoke(clsLoadClass.cast(this.sC), 3, str, str2, IabHelper.ITEM_TYPE_INAPP, str3);
        } catch (Exception e) {
            gr.d("IInAppBillingService is not available, please add com.android.vending.billing.IInAppBillingService to project.", e);
            return null;
        }
    }

    public int c(String str, String str2) {
        try {
            Class<?> clsLoadClass = this.mContext.getClassLoader().loadClass("com.android.vending.billing.IInAppBillingService");
            return ((Integer) clsLoadClass.getDeclaredMethod("consumePurchase", Integer.TYPE, String.class, String.class).invoke(clsLoadClass.cast(this.sC), 3, str, str2)).intValue();
        } catch (Exception e) {
            gr.d("IInAppBillingService is not available, please add com.android.vending.billing.IInAppBillingService to project.", e);
            return 5;
        }
    }

    public Bundle d(String str, String str2) {
        try {
            Class<?> clsLoadClass = this.mContext.getClassLoader().loadClass("com.android.vending.billing.IInAppBillingService");
            return (Bundle) clsLoadClass.getDeclaredMethod("getPurchases", Integer.TYPE, String.class, String.class, String.class).invoke(clsLoadClass.cast(this.sC), 3, str, IabHelper.ITEM_TYPE_INAPP, str2);
        } catch (Exception e) {
            gr.d("IInAppBillingService is not available, please add com.android.vending.billing.IInAppBillingService to project.", e);
            return null;
        }
    }

    public void destroy() {
        this.sC = null;
    }

    public void t(IBinder iBinder) {
        try {
            this.sC = this.mContext.getClassLoader().loadClass("com.android.vending.billing.IInAppBillingService$Stub").getDeclaredMethod("asInterface", IBinder.class).invoke(null, iBinder);
        } catch (Exception e) {
            gr.W("IInAppBillingService is not available, please add com.android.vending.billing.IInAppBillingService to project.");
        }
    }
}
