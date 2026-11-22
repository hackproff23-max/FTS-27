package com.google.android.gms.drive.internal;

import android.os.ParcelFileDescriptor;
import android.os.RemoteException;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.drive.Contents;
import com.google.android.gms.drive.DriveApi;
import com.google.android.gms.drive.DriveContents;
import com.google.android.gms.drive.DriveFile;
import com.google.android.gms.drive.DriveId;
import com.google.android.gms.drive.ExecutionOptions;
import com.google.android.gms.drive.MetadataChangeSet;
import com.google.android.gms.internal.jx;
import java.io.InputStream;
import java.io.OutputStream;

/* JADX INFO: loaded from: classes.dex */
public class s implements DriveContents {
    private final Contents PW;
    private boolean mClosed = false;
    private boolean PX = false;
    private boolean PY = false;

    public s(Contents contents) {
        this.PW = (Contents) jx.i(contents);
    }

    @Override // com.google.android.gms.drive.DriveContents
    public PendingResult<Status> commit(GoogleApiClient apiClient, MetadataChangeSet changeSet) {
        return commit(apiClient, changeSet, null);
    }

    @Override // com.google.android.gms.drive.DriveContents
    public PendingResult<Status> commit(GoogleApiClient apiClient, final MetadataChangeSet changeSet, final ExecutionOptions executionOptions) {
        if (executionOptions == null) {
            executionOptions = new ExecutionOptions.Builder().build();
        }
        if (this.PW.getMode() == 268435456) {
            throw new IllegalStateException("Cannot commit contents opened with MODE_READ_ONLY");
        }
        if (ExecutionOptions.ba(executionOptions.ix()) && !this.PW.ip()) {
            throw new IllegalStateException("DriveContents must be valid for conflict detection.");
        }
        ExecutionOptions.a(apiClient, executionOptions);
        if (it()) {
            throw new IllegalStateException("DriveContents already closed.");
        }
        if (getDriveId() == null) {
            throw new IllegalStateException("Only DriveContents obtained through DriveFile.open can be committed.");
        }
        if (changeSet == null) {
            changeSet = MetadataChangeSet.OE;
        }
        is();
        return apiClient.b(new q.a(apiClient) { // from class: com.google.android.gms.drive.internal.s.2
            /* JADX INFO: Access modifiers changed from: protected */
            @Override // com.google.android.gms.common.api.BaseImplementation.a
            public void a(r rVar) throws RemoteException {
                changeSet.iz().setContext(rVar.getContext());
                rVar.iG().a(new CloseContentsAndUpdateMetadataRequest(s.this.PW.getDriveId(), changeSet.iz(), s.this.PW, executionOptions), new bg(this));
            }
        });
    }

    @Override // com.google.android.gms.drive.DriveContents
    public void discard(GoogleApiClient apiClient) {
        if (it()) {
            throw new IllegalStateException("DriveContents already closed.");
        }
        is();
        ((AnonymousClass4) apiClient.b(new q.a(apiClient) { // from class: com.google.android.gms.drive.internal.s.4
            /* JADX INFO: Access modifiers changed from: protected */
            @Override // com.google.android.gms.common.api.BaseImplementation.a
            public void a(r rVar) throws RemoteException {
                rVar.iG().a(new CloseContentsRequest(s.this.PW, false), new bg(this));
            }
        })).setResultCallback(new ResultCallback<Status>() { // from class: com.google.android.gms.drive.internal.s.3
            @Override // com.google.android.gms.common.api.ResultCallback
            /* JADX INFO: renamed from: j, reason: merged with bridge method [inline-methods] */
            public void onResult(Status status) {
                if (status.isSuccess()) {
                    w.m("DriveContentsImpl", "Contents discarded");
                } else {
                    w.p("DriveContentsImpl", "Error discarding contents");
                }
            }
        });
    }

    @Override // com.google.android.gms.drive.DriveContents
    public DriveId getDriveId() {
        return this.PW.getDriveId();
    }

    @Override // com.google.android.gms.drive.DriveContents
    public InputStream getInputStream() {
        if (it()) {
            throw new IllegalStateException("Contents have been closed, cannot access the input stream.");
        }
        if (this.PW.getMode() != 268435456) {
            throw new IllegalStateException("getInputStream() can only be used with contents opened with MODE_READ_ONLY.");
        }
        if (this.PX) {
            throw new IllegalStateException("getInputStream() can only be called once per Contents instance.");
        }
        this.PX = true;
        return this.PW.getInputStream();
    }

    @Override // com.google.android.gms.drive.DriveContents
    public int getMode() {
        return this.PW.getMode();
    }

    @Override // com.google.android.gms.drive.DriveContents
    public OutputStream getOutputStream() {
        if (it()) {
            throw new IllegalStateException("Contents have been closed, cannot access the output stream.");
        }
        if (this.PW.getMode() != 536870912) {
            throw new IllegalStateException("getOutputStream() can only be used with contents opened with MODE_WRITE_ONLY.");
        }
        if (this.PY) {
            throw new IllegalStateException("getOutputStream() can only be called once per Contents instance.");
        }
        this.PY = true;
        return this.PW.getOutputStream();
    }

    @Override // com.google.android.gms.drive.DriveContents
    public ParcelFileDescriptor getParcelFileDescriptor() {
        if (it()) {
            throw new IllegalStateException("Contents have been closed, cannot access the output stream.");
        }
        return this.PW.getParcelFileDescriptor();
    }

    @Override // com.google.android.gms.drive.DriveContents
    public Contents ir() {
        return this.PW;
    }

    @Override // com.google.android.gms.drive.DriveContents
    public void is() {
        this.mClosed = true;
    }

    @Override // com.google.android.gms.drive.DriveContents
    public boolean it() {
        return this.mClosed;
    }

    @Override // com.google.android.gms.drive.DriveContents
    public PendingResult<DriveApi.DriveContentsResult> reopenForWrite(GoogleApiClient apiClient) {
        if (it()) {
            throw new IllegalStateException("DriveContents already closed.");
        }
        if (this.PW.getMode() != 268435456) {
            throw new IllegalStateException("reopenForWrite can only be used with DriveContents opened with MODE_READ_ONLY.");
        }
        is();
        return apiClient.a(new p.b(apiClient) { // from class: com.google.android.gms.drive.internal.s.1
            /* JADX INFO: Access modifiers changed from: protected */
            @Override // com.google.android.gms.common.api.BaseImplementation.a
            public void a(r rVar) throws RemoteException {
                rVar.iG().a(new OpenContentsRequest(s.this.getDriveId(), DriveFile.MODE_WRITE_ONLY, s.this.PW.getRequestId()), new az(this, null));
            }
        });
    }
}
