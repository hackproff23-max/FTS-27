package com.google.android.gms.common.api;

import com.google.android.gms.internal.jx;
import java.util.concurrent.TimeUnit;

/* JADX INFO: loaded from: classes.dex */
public final class BatchResult implements Result {
    private final Status Eb;
    private final PendingResult<?>[] JC;

    BatchResult(Status status, PendingResult<?>[] pendingResults) {
        this.Eb = status;
        this.JC = pendingResults;
    }

    @Override // com.google.android.gms.common.api.Result
    public Status getStatus() {
        return this.Eb;
    }

    public <R extends Result> R take(BatchResultToken<R> batchResultToken) {
        jx.b(batchResultToken.mId < this.JC.length, "The result token does not belong to this batch");
        return (R) this.JC[batchResultToken.mId].await(0L, TimeUnit.MILLISECONDS);
    }
}
