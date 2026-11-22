package com.google.android.gms.drive.internal;

import android.os.RemoteException;
import com.google.android.gms.common.api.BaseImplementation;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.drive.Drive;
import com.google.android.gms.drive.DriveApi;
import com.google.android.gms.drive.DriveId;
import com.google.android.gms.drive.DriveResource;
import com.google.android.gms.drive.Metadata;
import com.google.android.gms.drive.MetadataBuffer;
import com.google.android.gms.drive.MetadataChangeSet;
import com.google.android.gms.drive.events.ChangeListener;
import java.util.ArrayList;
import java.util.Set;

/* JADX INFO: loaded from: classes.dex */
public class y implements DriveResource {
    protected final DriveId Oj;

    private static class a extends com.google.android.gms.drive.internal.c {
        private final BaseImplementation.b<DriveApi.MetadataBufferResult> Ea;

        public a(BaseImplementation.b<DriveApi.MetadataBufferResult> bVar) {
            this.Ea = bVar;
        }

        @Override // com.google.android.gms.drive.internal.c, com.google.android.gms.drive.internal.af
        public void a(OnListParentsResponse onListParentsResponse) throws RemoteException {
            this.Ea.b(new p.f(Status.Kw, new MetadataBuffer(onListParentsResponse.iT(), null), false));
        }

        @Override // com.google.android.gms.drive.internal.c, com.google.android.gms.drive.internal.af
        public void n(Status status) throws RemoteException {
            this.Ea.b(new p.f(status, null, false));
        }
    }

    private static class b extends com.google.android.gms.drive.internal.c {
        private final BaseImplementation.b<DriveResource.MetadataResult> Ea;

        public b(BaseImplementation.b<DriveResource.MetadataResult> bVar) {
            this.Ea = bVar;
        }

        @Override // com.google.android.gms.drive.internal.c, com.google.android.gms.drive.internal.af
        public void a(OnMetadataResponse onMetadataResponse) throws RemoteException {
            this.Ea.b(new c(Status.Kw, new m(onMetadataResponse.iU())));
        }

        @Override // com.google.android.gms.drive.internal.c, com.google.android.gms.drive.internal.af
        public void n(Status status) throws RemoteException {
            this.Ea.b(new c(status, null));
        }
    }

    private static class c implements DriveResource.MetadataResult {
        private final Status Eb;
        private final Metadata Qv;

        public c(Status status, Metadata metadata) {
            this.Eb = status;
            this.Qv = metadata;
        }

        @Override // com.google.android.gms.drive.DriveResource.MetadataResult
        public Metadata getMetadata() {
            return this.Qv;
        }

        @Override // com.google.android.gms.common.api.Result
        public Status getStatus() {
            return this.Eb;
        }
    }

    private abstract class d extends q<DriveResource.MetadataResult> {
        private d(GoogleApiClient googleApiClient) {
            super(googleApiClient);
        }

        @Override // com.google.android.gms.common.api.BaseImplementation.AbstractPendingResult
        /* JADX INFO: renamed from: u, reason: merged with bridge method [inline-methods] */
        public DriveResource.MetadataResult c(Status status) {
            return new c(status, null);
        }
    }

    protected y(DriveId driveId) {
        this.Oj = driveId;
    }

    @Override // com.google.android.gms.drive.DriveResource
    public PendingResult<Status> addChangeListener(GoogleApiClient apiClient, ChangeListener listener) {
        return ((r) apiClient.a(Drive.DQ)).a(apiClient, this.Oj, 1, listener);
    }

    @Override // com.google.android.gms.drive.DriveResource
    public PendingResult<Status> addChangeSubscription(GoogleApiClient apiClient) {
        return ((r) apiClient.a(Drive.DQ)).a(apiClient, this.Oj, 1);
    }

    @Override // com.google.android.gms.drive.DriveResource
    public DriveId getDriveId() {
        return this.Oj;
    }

    @Override // com.google.android.gms.drive.DriveResource
    public PendingResult<DriveResource.MetadataResult> getMetadata(GoogleApiClient apiClient) {
        return apiClient.a(new d(apiClient) { // from class: com.google.android.gms.drive.internal.y.1
            /* JADX INFO: Access modifiers changed from: protected */
            @Override // com.google.android.gms.common.api.BaseImplementation.a
            public void a(r rVar) throws RemoteException {
                rVar.iG().a(new GetMetadataRequest(y.this.Oj), new b(this));
            }
        });
    }

    @Override // com.google.android.gms.drive.DriveResource
    public PendingResult<DriveApi.MetadataBufferResult> listParents(GoogleApiClient apiClient) {
        return apiClient.a(new p.g(apiClient) { // from class: com.google.android.gms.drive.internal.y.2
            /* JADX INFO: Access modifiers changed from: protected */
            @Override // com.google.android.gms.common.api.BaseImplementation.a
            public void a(r rVar) throws RemoteException {
                rVar.iG().a(new ListParentsRequest(y.this.Oj), new a(this));
            }
        });
    }

    @Override // com.google.android.gms.drive.DriveResource
    public PendingResult<Status> removeChangeListener(GoogleApiClient apiClient, ChangeListener listener) {
        return ((r) apiClient.a(Drive.DQ)).b(apiClient, this.Oj, 1, listener);
    }

    @Override // com.google.android.gms.drive.DriveResource
    public PendingResult<Status> removeChangeSubscription(GoogleApiClient apiClient) {
        return ((r) apiClient.a(Drive.DQ)).b(apiClient, this.Oj, 1);
    }

    @Override // com.google.android.gms.drive.DriveResource
    public PendingResult<Status> setParents(GoogleApiClient apiClient, Set<DriveId> parentIds) {
        if (parentIds == null) {
            throw new IllegalArgumentException("ParentIds must be provided.");
        }
        if (parentIds.isEmpty()) {
            throw new IllegalArgumentException("ParentIds must contain at least one parent.");
        }
        final ArrayList arrayList = new ArrayList(parentIds);
        return apiClient.b(new q.a(apiClient) { // from class: com.google.android.gms.drive.internal.y.3
            /* JADX INFO: Access modifiers changed from: protected */
            @Override // com.google.android.gms.common.api.BaseImplementation.a
            public void a(r rVar) throws RemoteException {
                rVar.iG().a(new SetResourceParentsRequest(y.this.Oj, arrayList), new bg(this));
            }
        });
    }

    @Override // com.google.android.gms.drive.DriveResource
    public PendingResult<DriveResource.MetadataResult> updateMetadata(GoogleApiClient apiClient, final MetadataChangeSet changeSet) {
        if (changeSet == null) {
            throw new IllegalArgumentException("ChangeSet must be provided.");
        }
        return apiClient.b(new d(apiClient) { // from class: com.google.android.gms.drive.internal.y.4
            /* JADX INFO: Access modifiers changed from: protected */
            @Override // com.google.android.gms.common.api.BaseImplementation.a
            public void a(r rVar) throws RemoteException {
                changeSet.iz().setContext(rVar.getContext());
                rVar.iG().a(new UpdateMetadataRequest(y.this.Oj, changeSet.iz()), new b(this));
            }
        });
    }
}
