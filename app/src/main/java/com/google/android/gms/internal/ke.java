package com.google.android.gms.internal;

import android.os.RemoteException;
import com.google.android.gms.common.api.BaseImplementation;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.api.Status;

/* JADX INFO: loaded from: classes.dex */
public final class ke implements kd {

    private static class a extends kb {
        private final BaseImplementation.b<Status> Ea;

        public a(BaseImplementation.b<Status> bVar) {
            this.Ea = bVar;
        }

        @Override // com.google.android.gms.internal.kb, com.google.android.gms.internal.kh
        public void aI(int i) throws RemoteException {
            this.Ea.b(new Status(i));
        }
    }

    @Override // com.google.android.gms.internal.kd
    public PendingResult<Status> c(GoogleApiClient googleApiClient) {
        return new kf.a(googleApiClient) { // from class: com.google.android.gms.internal.ke.1
            /* JADX INFO: Access modifiers changed from: protected */
            @Override // com.google.android.gms.common.api.BaseImplementation.a
            public void a(kg kgVar) throws RemoteException {
                kgVar.hw().a(new a(this));
            }
        }.gE();
    }
}
