package com.google.android.gms.location;

import android.content.Context;
import android.os.Looper;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.BaseImplementation;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.Scope;
import com.google.android.gms.internal.jg;
import com.google.android.gms.internal.nb;
import com.google.android.gms.internal.nk;
import com.mopub.nativeads.MoPubNativeAdPositioning;

/* JADX INFO: loaded from: classes.dex */
public class ActivityRecognition {
    public static final String CLIENT_NAME = "activity_recognition";
    private static final Api.c<nk> DQ = new Api.c<>();
    private static final Api.b<nk, Api.ApiOptions.NoOptions> DR = new Api.b<nk, Api.ApiOptions.NoOptions>() { // from class: com.google.android.gms.location.ActivityRecognition.1
        @Override // com.google.android.gms.common.api.Api.b
        /* JADX INFO: renamed from: e, reason: merged with bridge method [inline-methods] */
        public nk a(Context context, Looper looper, jg jgVar, Api.ApiOptions.NoOptions noOptions, GoogleApiClient.ConnectionCallbacks connectionCallbacks, GoogleApiClient.OnConnectionFailedListener onConnectionFailedListener) {
            return new nk(context, looper, context.getPackageName(), connectionCallbacks, onConnectionFailedListener, ActivityRecognition.CLIENT_NAME);
        }

        @Override // com.google.android.gms.common.api.Api.b
        public int getPriority() {
            return MoPubNativeAdPositioning.MoPubClientPositioning.NO_REPEAT;
        }
    };
    public static final Api<Api.ApiOptions.NoOptions> API = new Api<>(DR, DQ, new Scope[0]);
    public static ActivityRecognitionApi ActivityRecognitionApi = new nb();

    public static abstract class a<R extends Result> extends BaseImplementation.a<R, nk> {
        public a(GoogleApiClient googleApiClient) {
            super(ActivityRecognition.DQ, googleApiClient);
        }
    }

    private ActivityRecognition() {
    }
}
