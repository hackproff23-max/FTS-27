package com.google.android.gms.internal;

import android.content.Context;
import android.os.IBinder;
import android.os.Looper;
import android.os.RemoteException;
import com.google.android.gms.common.GooglePlayServicesUtil;
import com.google.android.gms.common.api.GoogleApiClient;

/* JADX INFO: loaded from: classes.dex */
public class kg extends jl<ki> {
    public kg(Context context, Looper looper, GoogleApiClient.ConnectionCallbacks connectionCallbacks, GoogleApiClient.OnConnectionFailedListener onConnectionFailedListener, String... strArr) {
        super(context, looper, connectionCallbacks, onConnectionFailedListener, strArr);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.google.android.gms.internal.jl
    /* JADX INFO: renamed from: T, reason: merged with bridge method [inline-methods] */
    public ki l(IBinder iBinder) {
        return ki.a.V(iBinder);
    }

    @Override // com.google.android.gms.internal.jl
    protected void a(jt jtVar, jl.e eVar) throws RemoteException {
        jtVar.j(eVar, GooglePlayServicesUtil.GOOGLE_PLAY_SERVICES_VERSION_CODE, getContext().getPackageName());
    }

    @Override // com.google.android.gms.internal.jl
    public String bK() {
        return "com.google.android.gms.common.service.START";
    }

    @Override // com.google.android.gms.internal.jl
    protected String bL() {
        return "com.google.android.gms.common.internal.service.ICommonService";
    }
}
