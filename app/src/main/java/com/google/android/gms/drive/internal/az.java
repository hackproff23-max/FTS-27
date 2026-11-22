package com.google.android.gms.drive.internal;

import android.os.RemoteException;
import com.google.android.gms.common.api.BaseImplementation;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.drive.DriveApi;
import com.google.android.gms.drive.DriveFile;

/* JADX INFO: loaded from: classes.dex */
class az extends c {
    private final BaseImplementation.b<DriveApi.DriveContentsResult> Ea;
    private final DriveFile.DownloadProgressListener QU;

    az(BaseImplementation.b<DriveApi.DriveContentsResult> bVar, DriveFile.DownloadProgressListener downloadProgressListener) {
        this.Ea = bVar;
        this.QU = downloadProgressListener;
    }

    @Override // com.google.android.gms.drive.internal.c, com.google.android.gms.drive.internal.af
    public void a(OnContentsResponse onContentsResponse) throws RemoteException {
        this.Ea.b(new p.a(onContentsResponse.iM() ? new Status(-1) : Status.Kw, new s(onContentsResponse.iL())));
    }

    @Override // com.google.android.gms.drive.internal.c, com.google.android.gms.drive.internal.af
    public void a(OnDownloadProgressResponse onDownloadProgressResponse) throws RemoteException {
        if (this.QU != null) {
            this.QU.onProgress(onDownloadProgressResponse.iO(), onDownloadProgressResponse.iP());
        }
    }

    @Override // com.google.android.gms.drive.internal.c, com.google.android.gms.drive.internal.af
    public void n(Status status) throws RemoteException {
        this.Ea.b(new p.a(status, null));
    }
}
