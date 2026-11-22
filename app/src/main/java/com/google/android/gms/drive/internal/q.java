package com.google.android.gms.drive.internal;

import com.google.android.gms.common.api.BaseImplementation;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.drive.Drive;

/* JADX INFO: loaded from: classes.dex */
abstract class q<R extends Result> extends BaseImplementation.a<R, r> {

    static abstract class a extends q<Status> {
        a(GoogleApiClient googleApiClient) {
            super(googleApiClient);
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // com.google.android.gms.common.api.BaseImplementation.AbstractPendingResult
        /* JADX INFO: renamed from: b, reason: merged with bridge method [inline-methods] */
        public Status c(Status status) {
            return status;
        }
    }

    public q(GoogleApiClient googleApiClient) {
        super(Drive.DQ, googleApiClient);
    }
}
