package com.google.android.gms.wearable;

import android.content.Context;
import android.os.Looper;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.Scope;
import com.google.android.gms.internal.jg;
import com.google.android.gms.wearable.internal.ah;
import com.google.android.gms.wearable.internal.ak;
import com.google.android.gms.wearable.internal.ay;
import com.google.android.gms.wearable.internal.ba;
import com.mopub.nativeads.MoPubNativeAdPositioning;

/* JADX INFO: loaded from: classes.dex */
public class Wearable {
    public static final DataApi DataApi = new com.google.android.gms.wearable.internal.f();
    public static final MessageApi MessageApi = new ah();
    public static final NodeApi NodeApi = new ak();
    public static final b axl = new com.google.android.gms.wearable.internal.e();
    public static final f axm = new ay();
    public static final Api.c<ba> DQ = new Api.c<>();
    private static final Api.b<ba, WearableOptions> DR = new Api.b<ba, WearableOptions>() { // from class: com.google.android.gms.wearable.Wearable.1
        @Override // com.google.android.gms.common.api.Api.b
        public ba a(Context context, Looper looper, jg jgVar, WearableOptions wearableOptions, GoogleApiClient.ConnectionCallbacks connectionCallbacks, GoogleApiClient.OnConnectionFailedListener onConnectionFailedListener) {
            if (wearableOptions == null) {
                new WearableOptions(new WearableOptions.Builder());
            }
            return new ba(context, looper, connectionCallbacks, onConnectionFailedListener);
        }

        @Override // com.google.android.gms.common.api.Api.b
        public int getPriority() {
            return MoPubNativeAdPositioning.MoPubClientPositioning.NO_REPEAT;
        }
    };
    public static final Api<WearableOptions> API = new Api<>(DR, DQ, new Scope[0]);

    public static final class WearableOptions implements Api.ApiOptions.Optional {

        public static class Builder {
            public WearableOptions build() {
                return new WearableOptions(this);
            }
        }

        private WearableOptions(Builder builder) {
        }
    }

    private Wearable() {
    }
}
