package com.google.android.gms.wearable.internal;

import com.google.android.gms.common.data.DataHolder;
import com.google.android.gms.wearable.DataItemAsset;

/* JADX INFO: loaded from: classes.dex */
public class k extends com.google.android.gms.common.data.d implements DataItemAsset {
    public k(DataHolder dataHolder, int i) {
        super(dataHolder, i);
    }

    @Override // com.google.android.gms.wearable.DataItemAsset
    public String getDataItemKey() {
        return getString("asset_key");
    }

    @Override // com.google.android.gms.wearable.DataItemAsset
    public String getId() {
        return getString("asset_id");
    }

    @Override // com.google.android.gms.common.data.Freezable
    /* JADX INFO: renamed from: ro, reason: merged with bridge method [inline-methods] */
    public DataItemAsset freeze() {
        return new i(this);
    }
}
