package com.google.android.gms.common.api;

import android.os.Looper;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public final class Batch extends BaseImplementation.AbstractPendingResult<BatchResult> {
    private boolean JA;
    private boolean JB;
    private final PendingResult<?>[] JC;
    private int Jz;
    private final Object mH;

    public static final class Builder {
        private List<PendingResult<?>> JE = new ArrayList();
        private Looper JF;

        public Builder(GoogleApiClient googleApiClient) {
            this.JF = googleApiClient.getLooper();
        }

        public <R extends Result> BatchResultToken<R> add(PendingResult<R> pendingResult) {
            BatchResultToken<R> batchResultToken = new BatchResultToken<>(this.JE.size());
            this.JE.add(pendingResult);
            return batchResultToken;
        }

        public Batch build() {
            return new Batch(this.JE, this.JF);
        }
    }

    private Batch(List<PendingResult<?>> pendingResultList, Looper looper) {
        super(new BaseImplementation.CallbackHandler(looper));
        this.mH = new Object();
        this.Jz = pendingResultList.size();
        this.JC = new PendingResult[this.Jz];
        int i = 0;
        while (true) {
            int i2 = i;
            if (i2 >= pendingResultList.size()) {
                return;
            }
            PendingResult<?> pendingResult = pendingResultList.get(i2);
            this.JC[i2] = pendingResult;
            pendingResult.a(new PendingResult.a() { // from class: com.google.android.gms.common.api.Batch.1
                @Override // com.google.android.gms.common.api.PendingResult.a
                public void m(Status status) {
                    synchronized (Batch.this.mH) {
                        if (Batch.this.isCanceled()) {
                            return;
                        }
                        if (status.isCanceled()) {
                            Batch.this.JB = true;
                        } else if (!status.isSuccess()) {
                            Batch.this.JA = true;
                        }
                        Batch.b(Batch.this);
                        if (Batch.this.Jz == 0) {
                            if (Batch.this.JB) {
                                Batch.super.cancel();
                            } else {
                                Batch.this.b(new BatchResult(Batch.this.JA ? new Status(13) : Status.Kw, Batch.this.JC));
                            }
                        }
                    }
                }
            });
            i = i2 + 1;
        }
    }

    static /* synthetic */ int b(Batch batch) {
        int i = batch.Jz;
        batch.Jz = i - 1;
        return i;
    }

    @Override // com.google.android.gms.common.api.BaseImplementation.AbstractPendingResult, com.google.android.gms.common.api.PendingResult
    public void cancel() {
        super.cancel();
        for (PendingResult<?> pendingResult : this.JC) {
            pendingResult.cancel();
        }
    }

    @Override // com.google.android.gms.common.api.BaseImplementation.AbstractPendingResult
    /* JADX INFO: renamed from: createFailedResult, reason: merged with bridge method [inline-methods] */
    public BatchResult c(Status status) {
        return new BatchResult(status, this.JC);
    }
}
