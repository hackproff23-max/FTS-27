package com.google.android.gms.wearable.internal;

import com.google.android.gms.common.data.DataHolder;
import com.google.android.gms.wearable.DataEvent;
import com.google.android.gms.wearable.DataItem;

/* JADX INFO: loaded from: classes.dex */
public final class h extends com.google.android.gms.common.data.d implements DataEvent {
    private final int Ya;

    public h(DataHolder dataHolder, int i, int i2) {
        super(dataHolder, i);
        this.Ya = i2;
    }

    @Override // com.google.android.gms.wearable.DataEvent
    public DataItem getDataItem() {
        return new o(this.JG, this.KZ, this.Ya);
    }

    @Override // com.google.android.gms.wearable.DataEvent
    public int getType() {
        return getInteger("event_type");
    }

    @Override // com.google.android.gms.common.data.Freezable
    /* JADX INFO: renamed from: rn, reason: merged with bridge method [inline-methods] */
    public DataEvent freeze() {
        return new g(this);
    }
}
