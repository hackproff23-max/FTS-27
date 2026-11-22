package com.google.android.gms.internal;

import android.content.Context;
import android.os.DeadObjectException;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.BaseImplementation;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.fitness.Fitness;

/* JADX INFO: loaded from: classes.dex */
public interface lu extends Api.a {

    public static abstract class a<R extends Result> extends BaseImplementation.a<R, lu> {
        public a(GoogleApiClient googleApiClient) {
            super(Fitness.DQ, googleApiClient);
        }
    }

    public static class b extends md.a {
        private final BaseImplementation.b<Status> Ea;

        public b(BaseImplementation.b<Status> bVar) {
            this.Ea = bVar;
        }

        @Override // com.google.android.gms.internal.md
        public void j(Status status) {
            this.Ea.b(status);
        }
    }

    public static abstract class c extends a<Status> {
        c(GoogleApiClient googleApiClient) {
            super(googleApiClient);
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // com.google.android.gms.common.api.BaseImplementation.AbstractPendingResult
        /* JADX INFO: renamed from: b, reason: merged with bridge method [inline-methods] */
        public Status c(Status status) {
            jx.L(!status.isSuccess());
            return status;
        }
    }

    Context getContext();

    lz jM() throws DeadObjectException;
}
