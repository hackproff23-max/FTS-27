package com.google.android.gms.location;

import android.content.Context;
import android.os.Looper;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.BaseImplementation;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.Scope;
import com.google.android.gms.internal.jg;
import com.google.android.gms.internal.jx;
import com.google.android.gms.internal.nf;
import com.google.android.gms.internal.ng;
import com.google.android.gms.internal.nk;
import com.mopub.nativeads.MoPubNativeAdPositioning;

/* JADX INFO: loaded from: classes.dex */
public class LocationServices {
    private static final Api.c<nk> DQ = new Api.c<>();
    private static final Api.b<nk, Api.ApiOptions.NoOptions> DR = new Api.b<nk, Api.ApiOptions.NoOptions>() { // from class: com.google.android.gms.location.LocationServices.1
        @Override // com.google.android.gms.common.api.Api.b
        /* JADX INFO: renamed from: e, reason: merged with bridge method [inline-methods] */
        public nk a(Context context, Looper looper, jg jgVar, Api.ApiOptions.NoOptions noOptions, GoogleApiClient.ConnectionCallbacks connectionCallbacks, GoogleApiClient.OnConnectionFailedListener onConnectionFailedListener) {
            return new nk(context, looper, context.getPackageName(), connectionCallbacks, onConnectionFailedListener, "locationServices", jgVar.getAccountName());
        }

        @Override // com.google.android.gms.common.api.Api.b
        public int getPriority() {
            return MoPubNativeAdPositioning.MoPubClientPositioning.NO_REPEAT;
        }
    };
    public static final Api<Api.ApiOptions.NoOptions> API = new Api<>(DR, DQ, new Scope[0]);
    public static FusedLocationProviderApi FusedLocationApi = new nf();
    public static GeofencingApi GeofencingApi = new ng();

    public static abstract class a<R extends Result> extends BaseImplementation.a<R, nk> {
        public a(GoogleApiClient googleApiClient) {
            super(LocationServices.DQ, googleApiClient);
        }
    }

    private LocationServices() {
    }

    public static nk f(GoogleApiClient googleApiClient) {
        jx.b(googleApiClient != null, "GoogleApiClient parameter is required.");
        nk nkVar = (nk) googleApiClient.a(DQ);
        jx.a(nkVar != null, "GoogleApiClient is not configured to use the LocationServices.API Api. Pass thisinto GoogleApiClient.Builder#addApi() to use this feature.");
        return nkVar;
    }
}
