package com.google.android.gms.common.api;

import com.google.android.gms.common.data.DataHolder;

/* JADX INFO: loaded from: classes.dex */
public abstract class b implements Releasable, Result {
    protected final Status Eb;
    protected final DataHolder JG;

    protected b(DataHolder dataHolder) {
        this.Eb = new Status(dataHolder.getStatusCode());
        this.JG = dataHolder;
    }

    @Override // com.google.android.gms.common.api.Result
    public Status getStatus() {
        return this.Eb;
    }

    @Override // com.google.android.gms.common.api.Releasable
    public void release() {
        if (this.JG != null) {
            this.JG.close();
        }
    }
}
