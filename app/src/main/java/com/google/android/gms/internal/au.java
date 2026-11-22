package com.google.android.gms.internal;

import android.content.Context;
import android.os.IBinder;
import android.os.RemoteException;
import com.google.android.gms.common.GooglePlayServicesUtil;

/* JADX INFO: loaded from: classes.dex */
@ey
public final class au extends com.google.android.gms.dynamic.g<be> {
    private static final au oc = new au();

    private au() {
        super("com.google.android.gms.ads.AdManagerCreatorImpl");
    }

    public static bd a(Context context, ay ayVar, String str, cx cxVar) {
        bd bdVarB;
        if (GooglePlayServicesUtil.isGooglePlayServicesAvailable(context) == 0 && (bdVarB = oc.b(context, ayVar, str, cxVar)) != null) {
            return bdVarB;
        }
        gr.S("Using AdManager from the client jar.");
        return new u(context, ayVar, str, cxVar, new gs(GooglePlayServicesUtil.GOOGLE_PLAY_SERVICES_VERSION_CODE, GooglePlayServicesUtil.GOOGLE_PLAY_SERVICES_VERSION_CODE, true));
    }

    private bd b(Context context, ay ayVar, String str, cx cxVar) {
        try {
            return bd.a.f(L(context).a(com.google.android.gms.dynamic.e.k(context), ayVar, str, cxVar, GooglePlayServicesUtil.GOOGLE_PLAY_SERVICES_VERSION_CODE));
        } catch (RemoteException e) {
            gr.d("Could not create remote AdManager.", e);
            return null;
        } catch (com.google.android.gms.dynamic.g.a e2) {
            gr.d("Could not create remote AdManager.", e2);
            return null;
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.google.android.gms.dynamic.g
    /* JADX INFO: renamed from: c, reason: merged with bridge method [inline-methods] */
    public be d(IBinder iBinder) {
        return be.a.g(iBinder);
    }
}
