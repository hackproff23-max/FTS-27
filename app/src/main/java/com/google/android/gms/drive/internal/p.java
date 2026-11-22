package com.google.android.gms.drive.internal;

import android.os.RemoteException;
import com.google.android.gms.common.api.BaseImplementation;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.drive.CreateFileActivityBuilder;
import com.google.android.gms.drive.Drive;
import com.google.android.gms.drive.DriveApi;
import com.google.android.gms.drive.DriveContents;
import com.google.android.gms.drive.DriveFile;
import com.google.android.gms.drive.DriveFolder;
import com.google.android.gms.drive.DriveId;
import com.google.android.gms.drive.MetadataBuffer;
import com.google.android.gms.drive.OpenFileActivityBuilder;
import com.google.android.gms.drive.query.Query;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public class p implements DriveApi {

    static class a implements DriveApi.DriveContentsResult {
        private final Status Eb;
        private final DriveContents Om;

        public a(Status status, DriveContents driveContents) {
            this.Eb = status;
            this.Om = driveContents;
        }

        @Override // com.google.android.gms.drive.DriveApi.DriveContentsResult
        public DriveContents getDriveContents() {
            return this.Om;
        }

        @Override // com.google.android.gms.common.api.Result
        public Status getStatus() {
            return this.Eb;
        }
    }

    static abstract class b extends q<DriveApi.DriveContentsResult> {
        b(GoogleApiClient googleApiClient) {
            super(googleApiClient);
        }

        @Override // com.google.android.gms.common.api.BaseImplementation.AbstractPendingResult
        /* JADX INFO: renamed from: o, reason: merged with bridge method [inline-methods] */
        public DriveApi.DriveContentsResult c(Status status) {
            return new a(status, null);
        }
    }

    static class c extends com.google.android.gms.drive.internal.c {
        private final BaseImplementation.b<DriveApi.DriveIdResult> Ea;

        public c(BaseImplementation.b<DriveApi.DriveIdResult> bVar) {
            this.Ea = bVar;
        }

        @Override // com.google.android.gms.drive.internal.c, com.google.android.gms.drive.internal.af
        public void a(OnDriveIdResponse onDriveIdResponse) throws RemoteException {
            this.Ea.b(new d(Status.Kw, onDriveIdResponse.getDriveId()));
        }

        @Override // com.google.android.gms.drive.internal.c, com.google.android.gms.drive.internal.af
        public void a(OnMetadataResponse onMetadataResponse) throws RemoteException {
            this.Ea.b(new d(Status.Kw, new m(onMetadataResponse.iU()).getDriveId()));
        }

        @Override // com.google.android.gms.drive.internal.c, com.google.android.gms.drive.internal.af
        public void n(Status status) throws RemoteException {
            this.Ea.b(new d(status, null));
        }
    }

    private static class d implements DriveApi.DriveIdResult {
        private final Status Eb;
        private final DriveId Oj;

        public d(Status status, DriveId driveId) {
            this.Eb = status;
            this.Oj = driveId;
        }

        @Override // com.google.android.gms.drive.DriveApi.DriveIdResult
        public DriveId getDriveId() {
            return this.Oj;
        }

        @Override // com.google.android.gms.common.api.Result
        public Status getStatus() {
            return this.Eb;
        }
    }

    static abstract class e extends q<DriveApi.DriveIdResult> {
        e(GoogleApiClient googleApiClient) {
            super(googleApiClient);
        }

        @Override // com.google.android.gms.common.api.BaseImplementation.AbstractPendingResult
        /* JADX INFO: renamed from: p, reason: merged with bridge method [inline-methods] */
        public DriveApi.DriveIdResult c(Status status) {
            return new d(status, null);
        }
    }

    static class f implements DriveApi.MetadataBufferResult {
        private final Status Eb;
        private final MetadataBuffer PI;
        private final boolean PJ;

        public f(Status status, MetadataBuffer metadataBuffer, boolean z) {
            this.Eb = status;
            this.PI = metadataBuffer;
            this.PJ = z;
        }

        @Override // com.google.android.gms.drive.DriveApi.MetadataBufferResult
        public MetadataBuffer getMetadataBuffer() {
            return this.PI;
        }

        @Override // com.google.android.gms.common.api.Result
        public Status getStatus() {
            return this.Eb;
        }
    }

    static abstract class g extends q<DriveApi.MetadataBufferResult> {
        g(GoogleApiClient googleApiClient) {
            super(googleApiClient);
        }

        @Override // com.google.android.gms.common.api.BaseImplementation.AbstractPendingResult
        /* JADX INFO: renamed from: q, reason: merged with bridge method [inline-methods] */
        public DriveApi.MetadataBufferResult c(Status status) {
            return new f(status, null, false);
        }
    }

    private static class h extends com.google.android.gms.drive.internal.c {
        private final BaseImplementation.b<DriveApi.DriveContentsResult> Ea;

        public h(BaseImplementation.b<DriveApi.DriveContentsResult> bVar) {
            this.Ea = bVar;
        }

        @Override // com.google.android.gms.drive.internal.c, com.google.android.gms.drive.internal.af
        public void a(OnContentsResponse onContentsResponse) throws RemoteException {
            this.Ea.b(new a(Status.Kw, new s(onContentsResponse.iL())));
        }

        @Override // com.google.android.gms.drive.internal.c, com.google.android.gms.drive.internal.af
        public void n(Status status) throws RemoteException {
            this.Ea.b(new a(status, null));
        }
    }

    private static class i extends com.google.android.gms.drive.internal.c {
        private final BaseImplementation.b<DriveApi.MetadataBufferResult> Ea;

        public i(BaseImplementation.b<DriveApi.MetadataBufferResult> bVar) {
            this.Ea = bVar;
        }

        @Override // com.google.android.gms.drive.internal.c, com.google.android.gms.drive.internal.af
        public void a(OnListEntriesResponse onListEntriesResponse) throws RemoteException {
            this.Ea.b(new f(Status.Kw, new MetadataBuffer(onListEntriesResponse.iR(), null), onListEntriesResponse.iS()));
        }

        @Override // com.google.android.gms.drive.internal.c, com.google.android.gms.drive.internal.af
        public void n(Status status) throws RemoteException {
            this.Ea.b(new f(status, null, false));
        }
    }

    static class j extends q.a {
        j(GoogleApiClient googleApiClient, Status status) {
            super(googleApiClient);
            b(status);
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // com.google.android.gms.common.api.BaseImplementation.a
        public void a(r rVar) {
        }
    }

    public PendingResult<DriveApi.DriveContentsResult> a(GoogleApiClient googleApiClient, final int i2) {
        return googleApiClient.a(new b(googleApiClient) { // from class: com.google.android.gms.drive.internal.p.2
            /* JADX INFO: Access modifiers changed from: protected */
            @Override // com.google.android.gms.common.api.BaseImplementation.a
            public void a(r rVar) throws RemoteException {
                rVar.iG().a(new CreateContentsRequest(i2), new h(this));
            }
        });
    }

    @Override // com.google.android.gms.drive.DriveApi
    public PendingResult<Status> cancelPendingActions(GoogleApiClient apiClient, List<String> trackingTags) {
        return ((r) apiClient.a(Drive.DQ)).cancelPendingActions(apiClient, trackingTags);
    }

    @Override // com.google.android.gms.drive.DriveApi
    public PendingResult<DriveApi.DriveIdResult> fetchDriveId(GoogleApiClient apiClient, final String resourceId) {
        return apiClient.a(new e(apiClient) { // from class: com.google.android.gms.drive.internal.p.3
            /* JADX INFO: Access modifiers changed from: protected */
            @Override // com.google.android.gms.common.api.BaseImplementation.a
            public void a(r rVar) throws RemoteException {
                rVar.iG().a(new GetMetadataRequest(DriveId.bi(resourceId)), new c(this));
            }
        });
    }

    @Override // com.google.android.gms.drive.DriveApi
    public DriveFolder getAppFolder(GoogleApiClient apiClient) {
        if (!apiClient.isConnected()) {
            throw new IllegalStateException("Client must be connected");
        }
        DriveId driveIdII = ((r) apiClient.a(Drive.DQ)).iI();
        if (driveIdII != null) {
            return new v(driveIdII);
        }
        return null;
    }

    @Override // com.google.android.gms.drive.DriveApi
    public DriveFile getFile(GoogleApiClient apiClient, DriveId driveId) {
        if (driveId == null) {
            throw new IllegalArgumentException("Id must be provided.");
        }
        if (apiClient.isConnected()) {
            return new t(driveId);
        }
        throw new IllegalStateException("Client must be connected");
    }

    @Override // com.google.android.gms.drive.DriveApi
    public DriveFolder getFolder(GoogleApiClient apiClient, DriveId driveId) {
        if (driveId == null) {
            throw new IllegalArgumentException("Id must be provided.");
        }
        if (apiClient.isConnected()) {
            return new v(driveId);
        }
        throw new IllegalStateException("Client must be connected");
    }

    @Override // com.google.android.gms.drive.DriveApi
    public DriveFolder getRootFolder(GoogleApiClient apiClient) {
        if (apiClient.isConnected()) {
            return new v(((r) apiClient.a(Drive.DQ)).iH());
        }
        throw new IllegalStateException("Client must be connected");
    }

    @Override // com.google.android.gms.drive.DriveApi
    public CreateFileActivityBuilder newCreateFileActivityBuilder() {
        return new CreateFileActivityBuilder();
    }

    @Override // com.google.android.gms.drive.DriveApi
    public PendingResult<DriveApi.DriveContentsResult> newDriveContents(GoogleApiClient apiClient) {
        return a(apiClient, DriveFile.MODE_WRITE_ONLY);
    }

    @Override // com.google.android.gms.drive.DriveApi
    public OpenFileActivityBuilder newOpenFileActivityBuilder() {
        return new OpenFileActivityBuilder();
    }

    @Override // com.google.android.gms.drive.DriveApi
    public PendingResult<DriveApi.MetadataBufferResult> query(GoogleApiClient apiClient, final Query query) {
        if (query == null) {
            throw new IllegalArgumentException("Query must be provided.");
        }
        return apiClient.a(new g(apiClient) { // from class: com.google.android.gms.drive.internal.p.1
            /* JADX INFO: Access modifiers changed from: protected */
            @Override // com.google.android.gms.common.api.BaseImplementation.a
            public void a(r rVar) throws RemoteException {
                rVar.iG().a(new QueryRequest(query), new i(this));
            }
        });
    }

    @Override // com.google.android.gms.drive.DriveApi
    public PendingResult<Status> requestSync(GoogleApiClient apiClient) {
        return apiClient.b(new q.a(apiClient) { // from class: com.google.android.gms.drive.internal.p.4
            /* JADX INFO: Access modifiers changed from: protected */
            @Override // com.google.android.gms.common.api.BaseImplementation.a
            public void a(r rVar) throws RemoteException {
                rVar.iG().a(new bg(this));
            }
        });
    }
}
