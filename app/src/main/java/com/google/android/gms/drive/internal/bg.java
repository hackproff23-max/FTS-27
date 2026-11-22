package com.google.android.gms.drive.internal;

import android.os.RemoteException;
import com.google.android.gms.common.api.BaseImplementation;
import com.google.android.gms.common.api.Status;

/* JADX INFO: loaded from: classes.dex */
public class bg extends c {
    private final BaseImplementation.b<Status> Ea;

    public bg(BaseImplementation.b<Status> bVar) {
        this.Ea = bVar;
    }

    @Override // com.google.android.gms.drive.internal.c, com.google.android.gms.drive.internal.af
    public void n(Status status) throws RemoteException {
        this.Ea.b(status);
    }

    @Override // com.google.android.gms.drive.internal.c, com.google.android.gms.drive.internal.af
    public void onSuccess() throws RemoteException {
        this.Ea.b(Status.Kw);
    }
}
