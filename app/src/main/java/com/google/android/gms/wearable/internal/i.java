package com.google.android.gms.wearable.internal;

import com.google.android.gms.wearable.DataItemAsset;

/* JADX INFO: loaded from: classes.dex */
public class i implements DataItemAsset {
    private final String CE;
    private final String KP;

    public i(DataItemAsset dataItemAsset) {
        this.CE = dataItemAsset.getId();
        this.KP = dataItemAsset.getDataItemKey();
    }

    @Override // com.google.android.gms.wearable.DataItemAsset
    public String getDataItemKey() {
        return this.KP;
    }

    @Override // com.google.android.gms.wearable.DataItemAsset
    public String getId() {
        return this.CE;
    }

    @Override // com.google.android.gms.common.data.Freezable
    public boolean isDataValid() {
        return true;
    }

    @Override // com.google.android.gms.common.data.Freezable
    /* JADX INFO: renamed from: ro, reason: merged with bridge method [inline-methods] */
    public DataItemAsset freeze() {
        return this;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("DataItemAssetEntity[");
        sb.append("@");
        sb.append(Integer.toHexString(hashCode()));
        if (this.CE == null) {
            sb.append(",noid");
        } else {
            sb.append(",");
            sb.append(this.CE);
        }
        sb.append(", key=");
        sb.append(this.KP);
        sb.append("]");
        return sb.toString();
    }
}
