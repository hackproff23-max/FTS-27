package com.google.android.gms.internal;

import android.content.Context;
import android.os.Looper;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.Scope;
import com.mopub.nativeads.MoPubNativeAdPositioning;

/* JADX INFO: loaded from: classes.dex */
public final class kc {
    public static final Api.c<kg> DQ = new Api.c<>();
    private static final Api.b<kg, Api.ApiOptions.NoOptions> DR = new Api.b<kg, Api.ApiOptions.NoOptions>() { // from class: com.google.android.gms.internal.kc.1
        @Override // com.google.android.gms.common.api.Api.b
        /* JADX INFO: renamed from: c, reason: merged with bridge method [inline-methods] */
        public kg a(Context context, Looper looper, jg jgVar, Api.ApiOptions.NoOptions noOptions, GoogleApiClient.ConnectionCallbacks connectionCallbacks, GoogleApiClient.OnConnectionFailedListener onConnectionFailedListener) {
            return new kg(context, looper, connectionCallbacks, onConnectionFailedListener, new String[0]);
        }

        @Override // com.google.android.gms.common.api.Api.b
        public int getPriority() {
            return MoPubNativeAdPositioning.MoPubClientPositioning.NO_REPEAT;
        }
    };
    public static final Api<Api.ApiOptions.NoOptions> API = new Api<>(DR, DQ, new Scope[0]);
    public static final kd Nu = new ke();
}
