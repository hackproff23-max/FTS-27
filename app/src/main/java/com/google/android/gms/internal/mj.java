package com.google.android.gms.internal;

import android.os.RemoteException;
import android.util.Log;
import com.google.android.gms.common.api.BaseImplementation;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.fitness.HistoryApi;
import com.google.android.gms.fitness.data.DataSet;
import com.google.android.gms.fitness.request.DataDeleteRequest;
import com.google.android.gms.fitness.request.DataReadRequest;
import com.google.android.gms.fitness.result.DataReadResult;

/* JADX INFO: loaded from: classes.dex */
public class mj implements HistoryApi {

    private static class a extends lw.a {
        private final BaseImplementation.b<DataReadResult> Ea;
        private int Vi;
        private DataReadResult Vj;

        private a(BaseImplementation.b<DataReadResult> bVar) {
            this.Vi = 0;
            this.Vj = null;
            this.Ea = bVar;
        }

        @Override // com.google.android.gms.internal.lw
        public void a(DataReadResult dataReadResult) {
            synchronized (this) {
                Log.v("Fitness", "Received batch result");
                if (this.Vj == null) {
                    this.Vj = dataReadResult;
                } else {
                    this.Vj.b(dataReadResult);
                }
                this.Vi++;
                if (this.Vi == this.Vj.kr()) {
                    this.Ea.b(this.Vj);
                }
            }
        }
    }

    @Override // com.google.android.gms.fitness.HistoryApi
    public PendingResult<Status> deleteData(GoogleApiClient client, final DataDeleteRequest request) {
        return client.a(new lu.c(client) { // from class: com.google.android.gms.internal.mj.2
            /* JADX INFO: Access modifiers changed from: protected */
            @Override // com.google.android.gms.common.api.BaseImplementation.a
            public void a(lu luVar) throws RemoteException {
                luVar.jM().a(request, new lu.b(this), luVar.getContext().getPackageName());
            }
        });
    }

    @Override // com.google.android.gms.fitness.HistoryApi
    public PendingResult<Status> insertData(GoogleApiClient client, final DataSet dataSet) {
        return client.a(new lu.c(client) { // from class: com.google.android.gms.internal.mj.1
            /* JADX INFO: Access modifiers changed from: protected */
            @Override // com.google.android.gms.common.api.BaseImplementation.a
            public void a(lu luVar) throws RemoteException {
                luVar.jM().a(new com.google.android.gms.fitness.request.e.a().b(dataSet).jU(), new lu.b(this), luVar.getContext().getPackageName());
            }
        });
    }

    @Override // com.google.android.gms.fitness.HistoryApi
    public PendingResult<DataReadResult> readData(GoogleApiClient client, final DataReadRequest request) {
        return client.a(new lu.a<DataReadResult>(client) { // from class: com.google.android.gms.internal.mj.3
            /* JADX INFO: Access modifiers changed from: protected */
            @Override // com.google.android.gms.common.api.BaseImplementation.a
            public void a(lu luVar) throws RemoteException {
                luVar.jM().a(request, new a(this), luVar.getContext().getPackageName());
            }

            /* JADX INFO: Access modifiers changed from: protected */
            @Override // com.google.android.gms.common.api.BaseImplementation.AbstractPendingResult
            /* JADX INFO: renamed from: x, reason: merged with bridge method [inline-methods] */
            public DataReadResult c(Status status) {
                return DataReadResult.a(status, request);
            }
        });
    }
}
