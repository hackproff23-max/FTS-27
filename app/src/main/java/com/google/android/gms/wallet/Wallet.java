package com.google.android.gms.wallet;

import android.app.Activity;
import android.content.Context;
import android.os.Looper;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.BaseImplementation;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.Scope;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.internal.jg;
import com.google.android.gms.internal.jx;
import com.google.android.gms.internal.pv;
import com.google.android.gms.internal.qg;
import com.google.android.gms.internal.qh;
import com.google.android.gms.internal.qj;
import com.google.android.gms.internal.qk;
import com.google.android.gms.wallet.wobs.r;
import com.mopub.nativeads.MoPubNativeAdPositioning;
import java.util.Locale;

/* JADX INFO: loaded from: classes.dex */
public final class Wallet {
    private static final Api.c<qh> DQ = new Api.c<>();
    private static final Api.b<qh, WalletOptions> DR = new Api.b<qh, WalletOptions>() { // from class: com.google.android.gms.wallet.Wallet.1
        @Override // com.google.android.gms.common.api.Api.b
        public qh a(Context context, Looper looper, jg jgVar, WalletOptions walletOptions, GoogleApiClient.ConnectionCallbacks connectionCallbacks, GoogleApiClient.OnConnectionFailedListener onConnectionFailedListener) {
            jx.b(context instanceof Activity, "An Activity must be used for Wallet APIs");
            Activity activity = (Activity) context;
            if (walletOptions == null) {
                walletOptions = new WalletOptions();
            }
            return new qh(activity, looper, connectionCallbacks, onConnectionFailedListener, walletOptions.environment, jgVar.getAccountName(), walletOptions.theme);
        }

        @Override // com.google.android.gms.common.api.Api.b
        public int getPriority() {
            return MoPubNativeAdPositioning.MoPubClientPositioning.NO_REPEAT;
        }
    };
    public static final Api<WalletOptions> API = new Api<>(DR, DQ, new Scope[0]);
    public static final Payments Payments = new qg();
    public static final r avT = new qk();
    public static final pv avU = new qj();

    public static final class WalletOptions implements Api.ApiOptions.HasOptions {
        public final int environment;
        public final int theme;

        public static final class Builder {
            private int avV = 0;
            private int mTheme = 0;

            public WalletOptions build() {
                return new WalletOptions(this);
            }

            public Builder setEnvironment(int environment) {
                if (environment != 0 && environment != 2 && environment != 1) {
                    throw new IllegalArgumentException(String.format(Locale.US, "Invalid environment value %d", Integer.valueOf(environment)));
                }
                this.avV = environment;
                return this;
            }

            public Builder setTheme(int theme) {
                if (theme != 0 && theme != 1) {
                    throw new IllegalArgumentException(String.format(Locale.US, "Invalid theme value %d", Integer.valueOf(theme)));
                }
                this.mTheme = theme;
                return this;
            }
        }

        private WalletOptions() {
            this(new Builder());
        }

        private WalletOptions(Builder builder) {
            this.environment = builder.avV;
            this.theme = builder.mTheme;
        }
    }

    public static abstract class a<R extends Result> extends BaseImplementation.a<R, qh> {
        public a(GoogleApiClient googleApiClient) {
            super(Wallet.DQ, googleApiClient);
        }
    }

    public static abstract class b extends a<Status> {
        public b(GoogleApiClient googleApiClient) {
            super(googleApiClient);
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // com.google.android.gms.common.api.BaseImplementation.AbstractPendingResult
        /* JADX INFO: renamed from: b, reason: merged with bridge method [inline-methods] */
        public Status c(Status status) {
            return status;
        }
    }

    private Wallet() {
    }

    @Deprecated
    public static void changeMaskedWallet(GoogleApiClient googleApiClient, String googleTransactionId, String merchantTransactionId, int requestCode) {
        Payments.changeMaskedWallet(googleApiClient, googleTransactionId, merchantTransactionId, requestCode);
    }

    @Deprecated
    public static void checkForPreAuthorization(GoogleApiClient googleApiClient, int requestCode) {
        Payments.checkForPreAuthorization(googleApiClient, requestCode);
    }

    @Deprecated
    public static void loadFullWallet(GoogleApiClient googleApiClient, FullWalletRequest request, int requestCode) {
        Payments.loadFullWallet(googleApiClient, request, requestCode);
    }

    @Deprecated
    public static void loadMaskedWallet(GoogleApiClient googleApiClient, MaskedWalletRequest request, int requestCode) {
        Payments.loadMaskedWallet(googleApiClient, request, requestCode);
    }

    @Deprecated
    public static void notifyTransactionStatus(GoogleApiClient googleApiClient, NotifyTransactionStatusRequest request) {
        Payments.notifyTransactionStatus(googleApiClient, request);
    }
}
