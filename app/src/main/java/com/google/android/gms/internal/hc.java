package com.google.android.gms.internal;

import android.content.Context;
import android.os.Looper;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.Scope;
import com.mopub.nativeads.MoPubNativeAdPositioning;

/* JADX INFO: loaded from: classes.dex */
public final class hc {
    public static final Api.c<hx> CG = new Api.c<>();
    private static final Api.b<hx, Api.ApiOptions.NoOptions> CH = new Api.b<hx, Api.ApiOptions.NoOptions>() { // from class: com.google.android.gms.internal.hc.1
        @Override // com.google.android.gms.common.api.Api.b
        public hx a(Context context, Looper looper, jg jgVar, Api.ApiOptions.NoOptions noOptions, GoogleApiClient.ConnectionCallbacks connectionCallbacks, GoogleApiClient.OnConnectionFailedListener onConnectionFailedListener) {
            return new hx(context, looper, connectionCallbacks, onConnectionFailedListener);
        }

        @Override // com.google.android.gms.common.api.Api.b
        public int getPriority() {
            return MoPubNativeAdPositioning.MoPubClientPositioning.NO_REPEAT;
        }
    };
    public static final Api<Api.ApiOptions.NoOptions> CI = new Api<>(CH, CG, new Scope[0]);
    public static final ht CJ = new hz();
}
