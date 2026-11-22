package com.google.android.gms.internal;

import android.app.Activity;
import android.content.Intent;
import android.os.IBinder;
import android.os.RemoteException;

/* JADX INFO: loaded from: classes.dex */
@ey
public final class et extends com.google.android.gms.dynamic.g<ep> {
    private static final et td = new et();

    private static final class a extends Exception {
        public a(String str) {
            super(str);
        }
    }

    private et() {
        super("com.google.android.gms.ads.InAppPurchaseManagerCreatorImpl");
    }

    private static boolean c(Activity activity) throws a {
        Intent intent = activity.getIntent();
        if (intent.hasExtra("com.google.android.gms.ads.internal.purchase.useClientJar")) {
            return intent.getBooleanExtra("com.google.android.gms.ads.internal.purchase.useClientJar", false);
        }
        throw new a("InAppPurchaseManager requires the useClientJar flag in intent extras.");
    }

    public static eo e(Activity activity) {
        eo eoVarF;
        try {
            if (c(activity)) {
                gr.S("Using AdOverlay from the client jar.");
                eoVarF = new ee(activity);
            } else {
                eoVarF = td.f(activity);
            }
            return eoVarF;
        } catch (a e) {
            gr.W(e.getMessage());
            return null;
        }
    }

    private eo f(Activity activity) {
        try {
            return eo.a.w(L(activity).c(com.google.android.gms.dynamic.e.k(activity)));
        } catch (RemoteException e) {
            gr.d("Could not create remote InAppPurchaseManager.", e);
            return null;
        } catch (com.google.android.gms.dynamic.g.a e2) {
            gr.d("Could not create remote InAppPurchaseManager.", e2);
            return null;
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.google.android.gms.dynamic.g
    /* JADX INFO: renamed from: A, reason: merged with bridge method [inline-methods] */
    public ep d(IBinder iBinder) {
        return ep.a.x(iBinder);
    }
}
