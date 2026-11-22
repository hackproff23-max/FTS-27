package com.google.android.gms.panorama;

import android.content.Context;
import android.os.Looper;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.Scope;
import com.google.android.gms.internal.jg;
import com.google.android.gms.internal.ol;
import com.google.android.gms.internal.om;
import com.mopub.nativeads.MoPubNativeAdPositioning;

/* JADX INFO: loaded from: classes.dex */
public final class Panorama {
    public static final Api.c<om> DQ = new Api.c<>();
    static final Api.b<om, Api.ApiOptions.NoOptions> DR = new Api.b<om, Api.ApiOptions.NoOptions>() { // from class: com.google.android.gms.panorama.Panorama.1
        @Override // com.google.android.gms.common.api.Api.b
        /* JADX INFO: renamed from: f, reason: merged with bridge method [inline-methods] */
        public om a(Context context, Looper looper, jg jgVar, Api.ApiOptions.NoOptions noOptions, GoogleApiClient.ConnectionCallbacks connectionCallbacks, GoogleApiClient.OnConnectionFailedListener onConnectionFailedListener) {
            return new om(context, looper, connectionCallbacks, onConnectionFailedListener);
        }

        @Override // com.google.android.gms.common.api.Api.b
        public int getPriority() {
            return MoPubNativeAdPositioning.MoPubClientPositioning.NO_REPEAT;
        }
    };
    public static final Api<Api.ApiOptions.NoOptions> API = new Api<>(DR, DQ, new Scope[0]);
    public static final PanoramaApi PanoramaApi = new ol();

    private Panorama() {
    }
}
