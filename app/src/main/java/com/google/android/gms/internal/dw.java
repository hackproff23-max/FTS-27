package com.google.android.gms.internal;

import android.app.Activity;
import android.content.Intent;
import android.os.IBinder;
import android.os.RemoteException;

/* JADX INFO: loaded from: classes.dex */
@ey
public final class dw extends com.google.android.gms.dynamic.g<dy> {
    private static final dw sy = new dw();

    private static final class a extends Exception {
        public a(String str) {
            super(str);
        }
    }

    private dw() {
        super("com.google.android.gms.ads.AdOverlayCreatorImpl");
    }

    public static dx b(Activity activity) {
        dx dxVarD;
        try {
            if (c(activity)) {
                gr.S("Using AdOverlay from the client jar.");
                dxVarD = new dp(activity);
            } else {
                dxVarD = sy.d(activity);
            }
            return dxVarD;
        } catch (a e) {
            gr.W(e.getMessage());
            return null;
        }
    }

    private static boolean c(Activity activity) throws a {
        Intent intent = activity.getIntent();
        if (intent.hasExtra("com.google.android.gms.ads.internal.overlay.useClientJar")) {
            return intent.getBooleanExtra("com.google.android.gms.ads.internal.overlay.useClientJar", false);
        }
        throw new a("Ad overlay requires the useClientJar flag in intent extras.");
    }

    private dx d(Activity activity) {
        try {
            return dx.a.r(L(activity).b(com.google.android.gms.dynamic.e.k(activity)));
        } catch (RemoteException e) {
            gr.d("Could not create remote AdOverlay.", e);
            return null;
        } catch (com.google.android.gms.dynamic.g.a e2) {
            gr.d("Could not create remote AdOverlay.", e2);
            return null;
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.google.android.gms.dynamic.g
    /* JADX INFO: renamed from: q, reason: merged with bridge method [inline-methods] */
    public dy d(IBinder iBinder) {
        return dy.a.s(iBinder);
    }
}
