package com.google.android.gms.internal;

import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.ResultCallback;
import java.util.concurrent.TimeUnit;

/* JADX INFO: loaded from: classes.dex */
class me<T extends Result> implements PendingResult<T> {
    private final T UU;

    me(T t) {
        this.UU = t;
    }

    @Override // com.google.android.gms.common.api.PendingResult
    public void a(PendingResult.a aVar) {
        aVar.m(this.UU.getStatus());
    }

    @Override // com.google.android.gms.common.api.PendingResult
    public T await() {
        return this.UU;
    }

    @Override // com.google.android.gms.common.api.PendingResult
    public T await(long time, TimeUnit units) {
        return this.UU;
    }

    @Override // com.google.android.gms.common.api.PendingResult
    public void cancel() {
    }

    @Override // com.google.android.gms.common.api.PendingResult
    public boolean isCanceled() {
        return false;
    }

    @Override // com.google.android.gms.common.api.PendingResult
    public void setResultCallback(ResultCallback<T> callback) {
        callback.onResult(this.UU);
    }

    @Override // com.google.android.gms.common.api.PendingResult
    public void setResultCallback(ResultCallback<T> callback, long time, TimeUnit units) {
        callback.onResult(this.UU);
    }
}
