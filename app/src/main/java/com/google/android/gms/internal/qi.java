package com.google.android.gms.internal;

import android.app.Activity;
import android.os.IBinder;
import android.os.RemoteException;
import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import com.google.android.gms.common.GooglePlayServicesUtil;
import com.google.android.gms.wallet.fragment.WalletFragmentOptions;

/* JADX INFO: loaded from: classes.dex */
public class qi extends com.google.android.gms.dynamic.g<qd> {
    private static qi awH;

    protected qi() {
        super("com.google.android.gms.wallet.dynamite.WalletDynamiteCreatorImpl");
    }

    public static qa a(Activity activity, com.google.android.gms.dynamic.c cVar, WalletFragmentOptions walletFragmentOptions, qb qbVar) throws GooglePlayServicesNotAvailableException {
        int iIsGooglePlayServicesAvailable = GooglePlayServicesUtil.isGooglePlayServicesAvailable(activity);
        if (iIsGooglePlayServicesAvailable != 0) {
            throw new GooglePlayServicesNotAvailableException(iIsGooglePlayServicesAvailable);
        }
        try {
            return rg().L(activity).a(com.google.android.gms.dynamic.e.k(activity), cVar, walletFragmentOptions, qbVar);
        } catch (RemoteException e) {
            throw new RuntimeException(e);
        } catch (com.google.android.gms.dynamic.g.a e2) {
            throw new RuntimeException(e2);
        }
    }

    private static qi rg() {
        if (awH == null) {
            awH = new qi();
        }
        return awH;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.google.android.gms.dynamic.g
    /* JADX INFO: renamed from: bW, reason: merged with bridge method [inline-methods] */
    public qd d(IBinder iBinder) {
        return qd.a.bS(iBinder);
    }
}
