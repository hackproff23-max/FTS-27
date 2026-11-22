package com.google.android.gms.internal;

import android.app.PendingIntent;
import android.os.RemoteException;
import com.google.android.gms.common.api.BaseImplementation;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.fitness.Fitness;
import com.google.android.gms.fitness.SensorsApi;
import com.google.android.gms.fitness.request.DataSourcesRequest;
import com.google.android.gms.fitness.request.OnDataPointListener;
import com.google.android.gms.fitness.request.SensorRequest;
import com.google.android.gms.fitness.result.DataSourcesResult;

/* JADX INFO: loaded from: classes.dex */
public class mm implements SensorsApi {

    private static abstract class a<R extends Result> extends BaseImplementation.a<R, lu> {
        public a(GoogleApiClient googleApiClient) {
            super(Fitness.DQ, googleApiClient);
        }
    }

    private interface b {
        void jO();
    }

    private static class c extends lx.a {
        private final BaseImplementation.b<DataSourcesResult> Ea;

        private c(BaseImplementation.b<DataSourcesResult> bVar) {
            this.Ea = bVar;
        }

        @Override // com.google.android.gms.internal.lx
        public void a(DataSourcesResult dataSourcesResult) {
            this.Ea.b(dataSourcesResult);
        }
    }

    private static class d extends md.a {
        private final BaseImplementation.b<Status> Ea;
        private final b Vu;

        private d(BaseImplementation.b<Status> bVar, b bVar2) {
            this.Ea = bVar;
            this.Vu = bVar2;
        }

        @Override // com.google.android.gms.internal.md
        public void j(Status status) {
            if (this.Vu != null && status.isSuccess()) {
                this.Vu.jO();
            }
            this.Ea.b(status);
        }
    }

    private PendingResult<Status> a(GoogleApiClient googleApiClient, final com.google.android.gms.fitness.request.o oVar) {
        return googleApiClient.a(new a<Status>(googleApiClient) { // from class: com.google.android.gms.internal.mm.2
            /* JADX INFO: Access modifiers changed from: protected */
            @Override // com.google.android.gms.common.api.BaseImplementation.a
            public void a(lu luVar) throws RemoteException {
                luVar.jM().a(oVar, new lu.b(this), luVar.getContext().getPackageName());
            }

            /* JADX INFO: Access modifiers changed from: protected */
            @Override // com.google.android.gms.common.api.BaseImplementation.AbstractPendingResult
            /* JADX INFO: renamed from: b, reason: merged with bridge method [inline-methods] */
            public Status c(Status status) {
                return status;
            }
        });
    }

    private PendingResult<Status> a(GoogleApiClient googleApiClient, final com.google.android.gms.fitness.request.q qVar, final b bVar) {
        return googleApiClient.b(new a<Status>(googleApiClient) { // from class: com.google.android.gms.internal.mm.4
            /* JADX INFO: Access modifiers changed from: protected */
            @Override // com.google.android.gms.common.api.BaseImplementation.a
            public void a(lu luVar) throws RemoteException {
                luVar.jM().a(qVar, new d(this, bVar), luVar.getContext().getPackageName());
            }

            /* JADX INFO: Access modifiers changed from: protected */
            @Override // com.google.android.gms.common.api.BaseImplementation.AbstractPendingResult
            /* JADX INFO: renamed from: b, reason: merged with bridge method [inline-methods] */
            public Status c(Status status) {
                return status;
            }
        });
    }

    @Override // com.google.android.gms.fitness.SensorsApi
    public PendingResult<Status> add(GoogleApiClient client, SensorRequest request, PendingIntent intent) {
        return a(client, new com.google.android.gms.fitness.request.o(request, null, intent));
    }

    @Override // com.google.android.gms.fitness.SensorsApi
    public PendingResult<Status> add(GoogleApiClient client, SensorRequest request, OnDataPointListener listener) {
        return a(client, new com.google.android.gms.fitness.request.o(request, com.google.android.gms.fitness.data.l.a.jG().a(listener), null));
    }

    @Override // com.google.android.gms.fitness.SensorsApi
    public PendingResult<DataSourcesResult> findDataSources(GoogleApiClient client, final DataSourcesRequest request) {
        return client.a(new a<DataSourcesResult>(client) { // from class: com.google.android.gms.internal.mm.1
            /* JADX INFO: Access modifiers changed from: protected */
            @Override // com.google.android.gms.common.api.BaseImplementation.a
            public void a(lu luVar) throws RemoteException {
                luVar.jM().a(request, new c(this), luVar.getContext().getPackageName());
            }

            /* JADX INFO: Access modifiers changed from: protected */
            @Override // com.google.android.gms.common.api.BaseImplementation.AbstractPendingResult
            /* JADX INFO: renamed from: z, reason: merged with bridge method [inline-methods] */
            public DataSourcesResult c(Status status) {
                return DataSourcesResult.D(status);
            }
        });
    }

    @Override // com.google.android.gms.fitness.SensorsApi
    public PendingResult<Status> remove(GoogleApiClient client, PendingIntent pendingIntent) {
        return a(client, new com.google.android.gms.fitness.request.q(null, pendingIntent), null);
    }

    @Override // com.google.android.gms.fitness.SensorsApi
    public PendingResult<Status> remove(GoogleApiClient client, final OnDataPointListener listener) {
        com.google.android.gms.fitness.data.l lVarB = com.google.android.gms.fitness.data.l.a.jG().b(listener);
        return lVarB == null ? new me(Status.Kw) : a(client, new com.google.android.gms.fitness.request.q(lVarB, null), new b() { // from class: com.google.android.gms.internal.mm.3
            @Override // com.google.android.gms.internal.mm.b
            public void jO() {
                com.google.android.gms.fitness.data.l.a.jG().c(listener);
            }
        });
    }
}
