package com.google.android.gms.internal;

import android.os.RemoteException;
import com.google.android.gms.common.api.BaseImplementation;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.fitness.ConfigApi;
import com.google.android.gms.fitness.request.DataTypeCreateRequest;
import com.google.android.gms.fitness.result.DataTypeResult;

/* JADX INFO: loaded from: classes.dex */
public class mi implements ConfigApi {

    private static class a extends ly.a {
        private final BaseImplementation.b<DataTypeResult> Ea;

        private a(BaseImplementation.b<DataTypeResult> bVar) {
            this.Ea = bVar;
        }

        @Override // com.google.android.gms.internal.ly
        public void a(DataTypeResult dataTypeResult) {
            this.Ea.b(dataTypeResult);
        }
    }

    @Override // com.google.android.gms.fitness.ConfigApi
    public PendingResult<DataTypeResult> createCustomDataType(GoogleApiClient client, final DataTypeCreateRequest request) {
        return client.b(new lu.a<DataTypeResult>(client) { // from class: com.google.android.gms.internal.mi.1
            /* JADX INFO: Access modifiers changed from: protected */
            @Override // com.google.android.gms.common.api.BaseImplementation.a
            public void a(lu luVar) throws RemoteException {
                luVar.jM().a(request, new a(this), luVar.getContext().getPackageName());
            }

            /* JADX INFO: Access modifiers changed from: protected */
            @Override // com.google.android.gms.common.api.BaseImplementation.AbstractPendingResult
            /* JADX INFO: renamed from: w, reason: merged with bridge method [inline-methods] */
            public DataTypeResult c(Status status) {
                return DataTypeResult.E(status);
            }
        });
    }

    @Override // com.google.android.gms.fitness.ConfigApi
    public PendingResult<Status> disableFit(GoogleApiClient client) {
        return client.b(new lu.c(client) { // from class: com.google.android.gms.internal.mi.3
            /* JADX INFO: Access modifiers changed from: protected */
            @Override // com.google.android.gms.common.api.BaseImplementation.a
            public void a(lu luVar) throws RemoteException {
                luVar.jM().a(new lu.b(this), luVar.getContext().getPackageName());
            }
        });
    }

    @Override // com.google.android.gms.fitness.ConfigApi
    public PendingResult<DataTypeResult> readDataType(GoogleApiClient client, String dataTypeName) {
        final com.google.android.gms.fitness.request.j jVar = new com.google.android.gms.fitness.request.j(dataTypeName);
        return client.a(new lu.a<DataTypeResult>(client) { // from class: com.google.android.gms.internal.mi.2
            /* JADX INFO: Access modifiers changed from: protected */
            @Override // com.google.android.gms.common.api.BaseImplementation.a
            public void a(lu luVar) throws RemoteException {
                luVar.jM().a(jVar, new a(this), luVar.getContext().getPackageName());
            }

            /* JADX INFO: Access modifiers changed from: protected */
            @Override // com.google.android.gms.common.api.BaseImplementation.AbstractPendingResult
            /* JADX INFO: renamed from: w, reason: merged with bridge method [inline-methods] */
            public DataTypeResult c(Status status) {
                return DataTypeResult.E(status);
            }
        });
    }
}
