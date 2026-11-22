package com.google.android.gms.wearable.internal;

import com.google.android.gms.wearable.DataEvent;
import com.google.android.gms.wearable.DataItem;

/* JADX INFO: loaded from: classes.dex */
public class g implements DataEvent {
    private int Gt;
    private DataItem axE;

    public g(DataEvent dataEvent) {
        this.Gt = dataEvent.getType();
        this.axE = dataEvent.getDataItem().freeze();
    }

    @Override // com.google.android.gms.wearable.DataEvent
    public DataItem getDataItem() {
        return this.axE;
    }

    @Override // com.google.android.gms.wearable.DataEvent
    public int getType() {
        return this.Gt;
    }

    @Override // com.google.android.gms.common.data.Freezable
    public boolean isDataValid() {
        return true;
    }

    @Override // com.google.android.gms.common.data.Freezable
    /* JADX INFO: renamed from: rn, reason: merged with bridge method [inline-methods] */
    public DataEvent freeze() {
        return this;
    }
}
