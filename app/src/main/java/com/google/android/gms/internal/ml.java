package com.google.android.gms.internal;

import android.os.RemoteException;
import com.google.android.gms.common.api.BaseImplementation;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.fitness.RecordingApi;
import com.google.android.gms.fitness.data.DataSource;
import com.google.android.gms.fitness.data.DataType;
import com.google.android.gms.fitness.data.Subscription;
import com.google.android.gms.fitness.result.ListSubscriptionsResult;

/* JADX INFO: loaded from: classes.dex */
public class ml implements RecordingApi {

    private static class a extends ma.a {
        private final BaseImplementation.b<ListSubscriptionsResult> Ea;

        private a(BaseImplementation.b<ListSubscriptionsResult> bVar) {
            this.Ea = bVar;
        }

        @Override // com.google.android.gms.internal.ma
        public void a(ListSubscriptionsResult listSubscriptionsResult) {
            this.Ea.b(listSubscriptionsResult);
        }
    }

    private PendingResult<ListSubscriptionsResult> a(GoogleApiClient googleApiClient, final com.google.android.gms.fitness.request.m mVar) {
        return googleApiClient.a(new lu.a<ListSubscriptionsResult>(googleApiClient) { // from class: com.google.android.gms.internal.ml.1
            /* JADX INFO: Access modifiers changed from: protected */
            @Override // com.google.android.gms.common.api.BaseImplementation.a
            public void a(lu luVar) throws RemoteException {
                luVar.jM().a(mVar, new a(this), luVar.getContext().getPackageName());
            }

            /* JADX INFO: Access modifiers changed from: protected */
            @Override // com.google.android.gms.common.api.BaseImplementation.AbstractPendingResult
            /* JADX INFO: renamed from: y, reason: merged with bridge method [inline-methods] */
            public ListSubscriptionsResult c(Status status) {
                return ListSubscriptionsResult.F(status);
            }
        });
    }

    public PendingResult<Status> a(GoogleApiClient googleApiClient, final com.google.android.gms.fitness.request.af afVar) {
        return googleApiClient.a(new lu.c(googleApiClient) { // from class: com.google.android.gms.internal.ml.2
            /* JADX INFO: Access modifiers changed from: protected */
            @Override // com.google.android.gms.common.api.BaseImplementation.a
            public void a(lu luVar) throws RemoteException {
                luVar.jM().a(afVar, new lu.b(this), luVar.getContext().getPackageName());
            }
        });
    }

    public PendingResult<Status> a(GoogleApiClient googleApiClient, final com.google.android.gms.fitness.request.aj ajVar) {
        return googleApiClient.b(new lu.c(googleApiClient) { // from class: com.google.android.gms.internal.ml.3
            /* JADX INFO: Access modifiers changed from: protected */
            @Override // com.google.android.gms.common.api.BaseImplementation.a
            public void a(lu luVar) throws RemoteException {
                luVar.jM().a(ajVar, new lu.b(this), luVar.getContext().getPackageName());
            }
        });
    }

    @Override // com.google.android.gms.fitness.RecordingApi
    public PendingResult<ListSubscriptionsResult> listSubscriptions(GoogleApiClient client) {
        return a(client, new com.google.android.gms.fitness.request.m.a().ka());
    }

    @Override // com.google.android.gms.fitness.RecordingApi
    public PendingResult<ListSubscriptionsResult> listSubscriptions(GoogleApiClient client, DataType dataType) {
        return a(client, new com.google.android.gms.fitness.request.m.a().c(dataType).ka());
    }

    @Override // com.google.android.gms.fitness.RecordingApi
    public PendingResult<Status> subscribe(GoogleApiClient client, DataSource dataSource) {
        return a(client, new com.google.android.gms.fitness.request.af.a().b(new Subscription.a().b(dataSource).jK()).kp());
    }

    @Override // com.google.android.gms.fitness.RecordingApi
    public PendingResult<Status> subscribe(GoogleApiClient client, DataType dataType) {
        return a(client, new com.google.android.gms.fitness.request.af.a().b(new Subscription.a().b(dataType).jK()).kp());
    }

    @Override // com.google.android.gms.fitness.RecordingApi
    public PendingResult<Status> unsubscribe(GoogleApiClient client, DataSource dataSource) {
        return a(client, new com.google.android.gms.fitness.request.aj.a().d(dataSource).kq());
    }

    @Override // com.google.android.gms.fitness.RecordingApi
    public PendingResult<Status> unsubscribe(GoogleApiClient client, DataType dataType) {
        return a(client, new com.google.android.gms.fitness.request.aj.a().d(dataType).kq());
    }

    @Override // com.google.android.gms.fitness.RecordingApi
    public PendingResult<Status> unsubscribe(GoogleApiClient client, Subscription subscription) {
        return subscription.getDataType() == null ? unsubscribe(client, subscription.getDataSource()) : unsubscribe(client, subscription.getDataType());
    }
}
