package com.google.android.gms.drive.internal;

import android.os.RemoteException;
import com.google.android.gms.common.api.BaseImplementation;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.drive.DriveApi;
import com.google.android.gms.drive.DriveContents;
import com.google.android.gms.drive.DriveFile;
import com.google.android.gms.drive.DriveFolder;
import com.google.android.gms.drive.DriveId;
import com.google.android.gms.drive.ExecutionOptions;
import com.google.android.gms.drive.MetadataChangeSet;
import com.google.android.gms.drive.query.Filters;
import com.google.android.gms.drive.query.Query;
import com.google.android.gms.drive.query.SearchableField;

/* JADX INFO: loaded from: classes.dex */
public class v extends y implements DriveFolder {

    private static class a extends com.google.android.gms.drive.internal.c {
        private final BaseImplementation.b<DriveFolder.DriveFileResult> Ea;

        public a(BaseImplementation.b<DriveFolder.DriveFileResult> bVar) {
            this.Ea = bVar;
        }

        @Override // com.google.android.gms.drive.internal.c, com.google.android.gms.drive.internal.af
        public void a(OnDriveIdResponse onDriveIdResponse) throws RemoteException {
            this.Ea.b(new c(Status.Kw, new t(onDriveIdResponse.getDriveId())));
        }

        @Override // com.google.android.gms.drive.internal.c, com.google.android.gms.drive.internal.af
        public void n(Status status) throws RemoteException {
            this.Ea.b(new c(status, null));
        }
    }

    private static class b extends com.google.android.gms.drive.internal.c {
        private final BaseImplementation.b<DriveFolder.DriveFolderResult> Ea;

        public b(BaseImplementation.b<DriveFolder.DriveFolderResult> bVar) {
            this.Ea = bVar;
        }

        @Override // com.google.android.gms.drive.internal.c, com.google.android.gms.drive.internal.af
        public void a(OnDriveIdResponse onDriveIdResponse) throws RemoteException {
            this.Ea.b(new e(Status.Kw, new v(onDriveIdResponse.getDriveId())));
        }

        @Override // com.google.android.gms.drive.internal.c, com.google.android.gms.drive.internal.af
        public void n(Status status) throws RemoteException {
            this.Ea.b(new e(status, null));
        }
    }

    private static class c implements DriveFolder.DriveFileResult {
        private final Status Eb;
        private final DriveFile Qn;

        public c(Status status, DriveFile driveFile) {
            this.Eb = status;
            this.Qn = driveFile;
        }

        @Override // com.google.android.gms.drive.DriveFolder.DriveFileResult
        public DriveFile getDriveFile() {
            return this.Qn;
        }

        @Override // com.google.android.gms.common.api.Result
        public Status getStatus() {
            return this.Eb;
        }
    }

    static abstract class d extends q<DriveFolder.DriveFileResult> {
        d(GoogleApiClient googleApiClient) {
            super(googleApiClient);
        }

        @Override // com.google.android.gms.common.api.BaseImplementation.AbstractPendingResult
        /* JADX INFO: renamed from: r, reason: merged with bridge method [inline-methods] */
        public DriveFolder.DriveFileResult c(Status status) {
            return new c(status, null);
        }
    }

    private static class e implements DriveFolder.DriveFolderResult {
        private final Status Eb;
        private final DriveFolder Qo;

        public e(Status status, DriveFolder driveFolder) {
            this.Eb = status;
            this.Qo = driveFolder;
        }

        @Override // com.google.android.gms.drive.DriveFolder.DriveFolderResult
        public DriveFolder getDriveFolder() {
            return this.Qo;
        }

        @Override // com.google.android.gms.common.api.Result
        public Status getStatus() {
            return this.Eb;
        }
    }

    static abstract class f extends q<DriveFolder.DriveFolderResult> {
        f(GoogleApiClient googleApiClient) {
            super(googleApiClient);
        }

        @Override // com.google.android.gms.common.api.BaseImplementation.AbstractPendingResult
        /* JADX INFO: renamed from: s, reason: merged with bridge method [inline-methods] */
        public DriveFolder.DriveFolderResult c(Status status) {
            return new e(status, null);
        }
    }

    public v(DriveId driveId) {
        super(driveId);
    }

    private PendingResult<DriveFolder.DriveFileResult> a(GoogleApiClient googleApiClient, final MetadataChangeSet metadataChangeSet, final int i, final int i2, final ExecutionOptions executionOptions) {
        ExecutionOptions.a(googleApiClient, executionOptions);
        return googleApiClient.b(new d(googleApiClient) { // from class: com.google.android.gms.drive.internal.v.1
            /* JADX INFO: Access modifiers changed from: protected */
            @Override // com.google.android.gms.common.api.BaseImplementation.a
            public void a(r rVar) throws RemoteException {
                metadataChangeSet.iz().setContext(rVar.getContext());
                rVar.iG().a(new CreateFileRequest(v.this.getDriveId(), metadataChangeSet.iz(), i, i2, executionOptions), new a(this));
            }
        });
    }

    private PendingResult<DriveFolder.DriveFileResult> a(GoogleApiClient googleApiClient, MetadataChangeSet metadataChangeSet, DriveContents driveContents, ExecutionOptions executionOptions) {
        int requestId;
        if (driveContents == null) {
            requestId = 1;
        } else {
            if (!(driveContents instanceof s)) {
                throw new IllegalArgumentException("Only DriveContents obtained from the Drive API are accepted.");
            }
            if (driveContents.getDriveId() != null) {
                throw new IllegalArgumentException("Only DriveContents obtained through DriveApi.newDriveContents are accepted for file creation.");
            }
            if (driveContents.it()) {
                throw new IllegalArgumentException("DriveContents are already closed.");
            }
            requestId = driveContents.ir().getRequestId();
            driveContents.is();
        }
        if (metadataChangeSet == null) {
            throw new IllegalArgumentException("MetadataChangeSet must be provided.");
        }
        if (DriveFolder.MIME_TYPE.equals(metadataChangeSet.getMimeType())) {
            throw new IllegalArgumentException("May not create folders (mimetype: application/vnd.google-apps.folder) using this method. Use DriveFolder.createFolder() instead.");
        }
        return a(googleApiClient, metadataChangeSet, requestId, 0, executionOptions);
    }

    @Override // com.google.android.gms.drive.DriveFolder
    public PendingResult<DriveFolder.DriveFileResult> createFile(GoogleApiClient apiClient, MetadataChangeSet changeSet, DriveContents driveContents) {
        return createFile(apiClient, changeSet, driveContents, null);
    }

    @Override // com.google.android.gms.drive.DriveFolder
    public PendingResult<DriveFolder.DriveFileResult> createFile(GoogleApiClient apiClient, MetadataChangeSet changeSet, DriveContents driveContents, ExecutionOptions executionOptions) {
        if (executionOptions == null) {
            executionOptions = new ExecutionOptions.Builder().build();
        }
        if (executionOptions.ix() != 0) {
            throw new IllegalStateException("May not set a conflict strategy for calls to createFile.");
        }
        return a(apiClient, changeSet, driveContents, executionOptions);
    }

    @Override // com.google.android.gms.drive.DriveFolder
    public PendingResult<DriveFolder.DriveFolderResult> createFolder(GoogleApiClient apiClient, final MetadataChangeSet changeSet) {
        if (changeSet == null) {
            throw new IllegalArgumentException("MetadataChangeSet must be provided.");
        }
        if (changeSet.getMimeType() == null || changeSet.getMimeType().equals(DriveFolder.MIME_TYPE)) {
            return apiClient.b(new f(apiClient) { // from class: com.google.android.gms.drive.internal.v.2
                /* JADX INFO: Access modifiers changed from: protected */
                @Override // com.google.android.gms.common.api.BaseImplementation.a
                public void a(r rVar) throws RemoteException {
                    changeSet.iz().setContext(rVar.getContext());
                    rVar.iG().a(new CreateFolderRequest(v.this.getDriveId(), changeSet.iz()), new b(this));
                }
            });
        }
        throw new IllegalArgumentException("The mimetype must be of type application/vnd.google-apps.folder");
    }

    @Override // com.google.android.gms.drive.DriveFolder
    public PendingResult<DriveApi.MetadataBufferResult> listChildren(GoogleApiClient apiClient) {
        return queryChildren(apiClient, null);
    }

    @Override // com.google.android.gms.drive.DriveFolder
    public PendingResult<DriveApi.MetadataBufferResult> queryChildren(GoogleApiClient apiClient, Query query) {
        Query.Builder builderAddFilter = new Query.Builder().addFilter(Filters.in(SearchableField.PARENTS, getDriveId()));
        if (query != null) {
            if (query.getFilter() != null) {
                builderAddFilter.addFilter(query.getFilter());
            }
            builderAddFilter.setPageToken(query.getPageToken());
            builderAddFilter.setSortOrder(query.getSortOrder());
        }
        return new p().query(apiClient, builderAddFilter.build());
    }
}
