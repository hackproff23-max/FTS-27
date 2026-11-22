package com.google.android.gms.wearable.internal;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.internal.jx;
import com.google.android.gms.wearable.DataItemAsset;

/* JADX INFO: loaded from: classes.dex */
public class DataItemAssetParcelable implements SafeParcelable, DataItemAsset {
    public static final Parcelable.Creator<DataItemAssetParcelable> CREATOR = new j();
    private final String CE;
    final int CK;
    private final String KP;

    DataItemAssetParcelable(int versionCode, String id, String key) {
        this.CK = versionCode;
        this.CE = id;
        this.KP = key;
    }

    public DataItemAssetParcelable(DataItemAsset value) {
        this.CK = 1;
        this.CE = (String) jx.i(value.getId());
        this.KP = (String) jx.i(value.getDataItemKey());
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
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
        sb.append("DataItemAssetParcelable[");
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

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel dest, int flags) {
        j.a(this, dest, flags);
    }
}
