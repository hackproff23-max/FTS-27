package com.google.android.gms.wearable;

import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.data.DataHolder;
import com.google.android.gms.common.data.g;
import com.google.android.gms.wearable.internal.h;

/* JADX INFO: loaded from: classes.dex */
public class DataEventBuffer extends g<DataEvent> implements Result {
    private final Status Eb;

    public DataEventBuffer(DataHolder dataHolder) {
        super(dataHolder);
        this.Eb = new Status(dataHolder.getStatusCode());
    }

    @Override // com.google.android.gms.common.api.Result
    public Status getStatus() {
        return this.Eb;
    }

    @Override // com.google.android.gms.common.data.g
    protected String ha() {
        return "path";
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.google.android.gms.common.data.g
    /* JADX INFO: renamed from: q, reason: merged with bridge method [inline-methods] */
    public DataEvent f(int i, int i2) {
        return new h(this.JG, i, i2);
    }
}
