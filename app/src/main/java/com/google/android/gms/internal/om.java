package com.google.android.gms.internal;

import android.content.Context;
import android.os.Bundle;
import android.os.IBinder;
import android.os.Looper;
import android.os.RemoteException;
import com.google.android.gms.common.GooglePlayServicesUtil;
import com.google.android.gms.common.api.GoogleApiClient;

/* JADX INFO: loaded from: classes.dex */
public class om extends jl<ok> {
    public om(Context context, Looper looper, GoogleApiClient.ConnectionCallbacks connectionCallbacks, GoogleApiClient.OnConnectionFailedListener onConnectionFailedListener) {
        super(context, looper, connectionCallbacks, onConnectionFailedListener, (String[]) null);
    }

    @Override // com.google.android.gms.internal.jl
    protected void a(jt jtVar, jl.e eVar) throws RemoteException {
        jtVar.a(eVar, GooglePlayServicesUtil.GOOGLE_PLAY_SERVICES_VERSION_CODE, getContext().getPackageName(), new Bundle());
    }

    @Override // com.google.android.gms.internal.jl
    /* JADX INFO: renamed from: bH, reason: merged with bridge method [inline-methods] */
    public ok l(IBinder iBinder) {
        return ok.a.bG(iBinder);
    }

    @Override // com.google.android.gms.internal.jl
    protected String bK() {
        return "com.google.android.gms.panorama.service.START";
    }

    @Override // com.google.android.gms.internal.jl
    protected String bL() {
        return "com.google.android.gms.panorama.internal.IPanoramaService";
    }
}
