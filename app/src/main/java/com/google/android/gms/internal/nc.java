package com.google.android.gms.internal;

import android.content.Context;
import android.os.Bundle;
import android.os.DeadObjectException;
import android.os.IBinder;
import android.os.Looper;
import android.os.RemoteException;
import com.google.android.gms.common.GooglePlayServicesUtil;
import com.google.android.gms.common.api.GoogleApiClient;

/* JADX INFO: loaded from: classes.dex */
public class nc extends jl<ni> {
    protected final np<ni> Ee;
    private final String agD;

    public nc(Context context, Looper looper, GoogleApiClient.ConnectionCallbacks connectionCallbacks, GoogleApiClient.OnConnectionFailedListener onConnectionFailedListener, String str) {
        super(context, looper, connectionCallbacks, onConnectionFailedListener, new String[0]);
        this.Ee = new np<ni>() { // from class: com.google.android.gms.internal.nc.1
            @Override // com.google.android.gms.internal.np
            public void dS() {
                nc.this.dS();
            }

            @Override // com.google.android.gms.internal.np
            /* JADX INFO: renamed from: nh, reason: merged with bridge method [inline-methods] */
            public ni hw() throws DeadObjectException {
                return nc.this.hw();
            }
        };
        this.agD = str;
    }

    @Override // com.google.android.gms.internal.jl
    protected void a(jt jtVar, jl.e eVar) throws RemoteException {
        Bundle bundle = new Bundle();
        bundle.putString("client_name", this.agD);
        jtVar.e(eVar, GooglePlayServicesUtil.GOOGLE_PLAY_SERVICES_VERSION_CODE, getContext().getPackageName(), bundle);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.google.android.gms.internal.jl
    /* JADX INFO: renamed from: aM, reason: merged with bridge method [inline-methods] */
    public ni l(IBinder iBinder) {
        return ni.a.aO(iBinder);
    }

    @Override // com.google.android.gms.internal.jl
    protected String bK() {
        return "com.google.android.location.internal.GoogleLocationManagerService.START";
    }

    @Override // com.google.android.gms.internal.jl
    protected String bL() {
        return "com.google.android.gms.location.internal.IGoogleLocationManagerService";
    }
}
