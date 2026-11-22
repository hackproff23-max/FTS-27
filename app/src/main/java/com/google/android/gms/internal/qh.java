package com.google.android.gms.internal;

import android.accounts.Account;
import android.app.Activity;
import android.app.PendingIntent;
import android.content.Intent;
import android.content.IntentSender;
import android.os.Bundle;
import android.os.IBinder;
import android.os.Looper;
import android.os.RemoteException;
import android.text.TextUtils;
import android.util.Log;
import com.google.android.gms.auth.GoogleAuthUtil;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GooglePlayServicesUtil;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.wallet.FullWallet;
import com.google.android.gms.wallet.FullWalletRequest;
import com.google.android.gms.wallet.MaskedWallet;
import com.google.android.gms.wallet.MaskedWalletRequest;
import com.google.android.gms.wallet.NotifyTransactionStatusRequest;
import com.google.android.gms.wallet.WalletConstants;

/* JADX INFO: loaded from: classes.dex */
public class qh extends jl<qc> {
    private final String DZ;
    private final int avV;
    private final int mTheme;
    private final Activity nB;

    private static class a extends qf.a {
        private a() {
        }

        @Override // com.google.android.gms.internal.qf
        public void a(int i, FullWallet fullWallet, Bundle bundle) {
        }

        @Override // com.google.android.gms.internal.qf
        public void a(int i, MaskedWallet maskedWallet, Bundle bundle) {
        }

        @Override // com.google.android.gms.internal.qf
        public void a(int i, boolean z, Bundle bundle) {
        }

        @Override // com.google.android.gms.internal.qf
        public void a(Status status, py pyVar, Bundle bundle) {
        }

        @Override // com.google.android.gms.internal.qf
        public void i(int i, Bundle bundle) {
        }
    }

    final class b extends a {
        private final int My;

        public b(int i) {
            super();
            this.My = i;
        }

        @Override // com.google.android.gms.internal.qh.a, com.google.android.gms.internal.qf
        public void a(int i, FullWallet fullWallet, Bundle bundle) {
            int i2;
            ConnectionResult connectionResult = new ConnectionResult(i, bundle != null ? (PendingIntent) bundle.getParcelable("com.google.android.gms.wallet.EXTRA_PENDING_INTENT") : null);
            if (connectionResult.hasResolution()) {
                try {
                    connectionResult.startResolutionForResult(qh.this.nB, this.My);
                    return;
                } catch (IntentSender.SendIntentException e) {
                    Log.w("WalletClientImpl", "Exception starting pending intent", e);
                    return;
                }
            }
            Intent intent = new Intent();
            if (connectionResult.isSuccess()) {
                i2 = -1;
                intent.putExtra(WalletConstants.EXTRA_FULL_WALLET, fullWallet);
            } else {
                i2 = i == 408 ? 0 : 1;
                intent.putExtra(WalletConstants.EXTRA_ERROR_CODE, i);
            }
            PendingIntent pendingIntentCreatePendingResult = qh.this.nB.createPendingResult(this.My, intent, 1073741824);
            if (pendingIntentCreatePendingResult == null) {
                Log.w("WalletClientImpl", "Null pending result returned for onFullWalletLoaded");
                return;
            }
            try {
                pendingIntentCreatePendingResult.send(i2);
            } catch (PendingIntent.CanceledException e2) {
                Log.w("WalletClientImpl", "Exception setting pending result", e2);
            }
        }

        @Override // com.google.android.gms.internal.qh.a, com.google.android.gms.internal.qf
        public void a(int i, MaskedWallet maskedWallet, Bundle bundle) {
            int i2;
            ConnectionResult connectionResult = new ConnectionResult(i, bundle != null ? (PendingIntent) bundle.getParcelable("com.google.android.gms.wallet.EXTRA_PENDING_INTENT") : null);
            if (connectionResult.hasResolution()) {
                try {
                    connectionResult.startResolutionForResult(qh.this.nB, this.My);
                    return;
                } catch (IntentSender.SendIntentException e) {
                    Log.w("WalletClientImpl", "Exception starting pending intent", e);
                    return;
                }
            }
            Intent intent = new Intent();
            if (connectionResult.isSuccess()) {
                i2 = -1;
                intent.putExtra(WalletConstants.EXTRA_MASKED_WALLET, maskedWallet);
            } else {
                i2 = i == 408 ? 0 : 1;
                intent.putExtra(WalletConstants.EXTRA_ERROR_CODE, i);
            }
            PendingIntent pendingIntentCreatePendingResult = qh.this.nB.createPendingResult(this.My, intent, 1073741824);
            if (pendingIntentCreatePendingResult == null) {
                Log.w("WalletClientImpl", "Null pending result returned for onMaskedWalletLoaded");
                return;
            }
            try {
                pendingIntentCreatePendingResult.send(i2);
            } catch (PendingIntent.CanceledException e2) {
                Log.w("WalletClientImpl", "Exception setting pending result", e2);
            }
        }

        @Override // com.google.android.gms.internal.qh.a, com.google.android.gms.internal.qf
        public void a(int i, boolean z, Bundle bundle) {
            Intent intent = new Intent();
            intent.putExtra(WalletConstants.EXTRA_IS_USER_PREAUTHORIZED, z);
            PendingIntent pendingIntentCreatePendingResult = qh.this.nB.createPendingResult(this.My, intent, 1073741824);
            if (pendingIntentCreatePendingResult == null) {
                Log.w("WalletClientImpl", "Null pending result returned for onPreAuthorizationDetermined");
                return;
            }
            try {
                pendingIntentCreatePendingResult.send(-1);
            } catch (PendingIntent.CanceledException e) {
                Log.w("WalletClientImpl", "Exception setting pending result", e);
            }
        }

        @Override // com.google.android.gms.internal.qh.a, com.google.android.gms.internal.qf
        public void i(int i, Bundle bundle) {
            jx.b(bundle, "Bundle should not be null");
            ConnectionResult connectionResult = new ConnectionResult(i, (PendingIntent) bundle.getParcelable("com.google.android.gms.wallet.EXTRA_PENDING_INTENT"));
            if (connectionResult.hasResolution()) {
                try {
                    connectionResult.startResolutionForResult(qh.this.nB, this.My);
                    return;
                } catch (IntentSender.SendIntentException e) {
                    Log.w("WalletClientImpl", "Exception starting pending intent", e);
                    return;
                }
            }
            Log.e("WalletClientImpl", "Create Wallet Objects confirmation UI will not be shown connection result: " + connectionResult);
            Intent intent = new Intent();
            intent.putExtra(WalletConstants.EXTRA_ERROR_CODE, WalletConstants.ERROR_CODE_UNKNOWN);
            PendingIntent pendingIntentCreatePendingResult = qh.this.nB.createPendingResult(this.My, intent, 1073741824);
            if (pendingIntentCreatePendingResult == null) {
                Log.w("WalletClientImpl", "Null pending result returned for onWalletObjectsCreated");
                return;
            }
            try {
                pendingIntentCreatePendingResult.send(1);
            } catch (PendingIntent.CanceledException e2) {
                Log.w("WalletClientImpl", "Exception setting pending result", e2);
            }
        }
    }

    public qh(Activity activity, Looper looper, GoogleApiClient.ConnectionCallbacks connectionCallbacks, GoogleApiClient.OnConnectionFailedListener onConnectionFailedListener, int i, String str, int i2) {
        super(activity, looper, connectionCallbacks, onConnectionFailedListener, new String[0]);
        this.nB = activity;
        this.avV = i;
        this.DZ = str;
        this.mTheme = i2;
    }

    public static Bundle a(int i, String str, String str2, int i2) {
        Bundle bundle = new Bundle();
        bundle.putInt("com.google.android.gms.wallet.EXTRA_ENVIRONMENT", i);
        bundle.putString("androidPackageName", str);
        if (!TextUtils.isEmpty(str2)) {
            bundle.putParcelable("com.google.android.gms.wallet.EXTRA_BUYER_ACCOUNT", new Account(str2, GoogleAuthUtil.GOOGLE_ACCOUNT_TYPE));
        }
        bundle.putInt("com.google.android.gms.wallet.EXTRA_THEME", i2);
        return bundle;
    }

    private Bundle rf() {
        return a(this.avV, this.nB.getPackageName(), this.DZ, this.mTheme);
    }

    @Override // com.google.android.gms.internal.jl
    protected void a(jt jtVar, jl.e eVar) throws RemoteException {
        jtVar.k(eVar, GooglePlayServicesUtil.GOOGLE_PLAY_SERVICES_VERSION_CODE, getContext().getPackageName());
    }

    public void a(FullWalletRequest fullWalletRequest, int i) {
        b bVar = new b(i);
        try {
            hw().a(fullWalletRequest, rf(), bVar);
        } catch (RemoteException e) {
            Log.e("WalletClientImpl", "RemoteException getting full wallet", e);
            bVar.a(8, (FullWallet) null, Bundle.EMPTY);
        }
    }

    public void a(MaskedWalletRequest maskedWalletRequest, int i) {
        Bundle bundleRf = rf();
        b bVar = new b(i);
        try {
            hw().a(maskedWalletRequest, bundleRf, bVar);
        } catch (RemoteException e) {
            Log.e("WalletClientImpl", "RemoteException getting masked wallet", e);
            bVar.a(8, (MaskedWallet) null, Bundle.EMPTY);
        }
    }

    public void a(NotifyTransactionStatusRequest notifyTransactionStatusRequest) {
        try {
            hw().a(notifyTransactionStatusRequest, rf());
        } catch (RemoteException e) {
        }
    }

    @Override // com.google.android.gms.internal.jl
    protected String bK() {
        return "com.google.android.gms.wallet.service.BIND";
    }

    @Override // com.google.android.gms.internal.jl
    protected String bL() {
        return "com.google.android.gms.wallet.internal.IOwService";
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.google.android.gms.internal.jl
    /* JADX INFO: renamed from: bV, reason: merged with bridge method [inline-methods] */
    public qc l(IBinder iBinder) {
        return qc.a.bR(iBinder);
    }

    public void d(String str, String str2, int i) {
        Bundle bundleRf = rf();
        b bVar = new b(i);
        try {
            hw().a(str, str2, bundleRf, bVar);
        } catch (RemoteException e) {
            Log.e("WalletClientImpl", "RemoteException changing masked wallet", e);
            bVar.a(8, (MaskedWallet) null, Bundle.EMPTY);
        }
    }

    public void gk(int i) {
        Bundle bundleRf = rf();
        b bVar = new b(i);
        try {
            hw().a(bundleRf, bVar);
        } catch (RemoteException e) {
            Log.e("WalletClientImpl", "RemoteException during checkForPreAuthorization", e);
            bVar.a(8, false, Bundle.EMPTY);
        }
    }
}
