package com.google.android.gms.wearable.internal;

import android.net.Uri;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import android.util.Log;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.wearable.DataItem;
import com.google.android.gms.wearable.DataItemAsset;
import java.util.HashMap;
import java.util.Map;

/* JADX INFO: loaded from: classes.dex */
public class m implements SafeParcelable, DataItem {
    public static final Parcelable.Creator<m> CREATOR = new n();
    final int CK;
    private byte[] aeA;
    private final Map<String, DataItemAsset> axH;
    private final Uri mUri;

    m(int i, Uri uri, Bundle bundle, byte[] bArr) {
        this.CK = i;
        this.mUri = uri;
        HashMap map = new HashMap();
        bundle.setClassLoader(DataItemAssetParcelable.class.getClassLoader());
        for (String str : bundle.keySet()) {
            map.put(str, (DataItemAssetParcelable) bundle.getParcelable(str));
        }
        this.axH = map;
        this.aeA = bArr;
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    @Override // com.google.android.gms.wearable.DataItem
    public Map<String, DataItemAsset> getAssets() {
        return this.axH;
    }

    @Override // com.google.android.gms.wearable.DataItem
    public byte[] getData() {
        return this.aeA;
    }

    @Override // com.google.android.gms.wearable.DataItem
    public Uri getUri() {
        return this.mUri;
    }

    @Override // com.google.android.gms.common.data.Freezable
    public boolean isDataValid() {
        return true;
    }

    @Override // com.google.android.gms.wearable.DataItem
    /* JADX INFO: renamed from: m, reason: merged with bridge method [inline-methods] */
    public m setData(byte[] bArr) {
        this.aeA = bArr;
        return this;
    }

    public Bundle rk() {
        Bundle bundle = new Bundle();
        bundle.setClassLoader(DataItemAssetParcelable.class.getClassLoader());
        for (Map.Entry<String, DataItemAsset> entry : this.axH.entrySet()) {
            bundle.putParcelable(entry.getKey(), new DataItemAssetParcelable(entry.getValue()));
        }
        return bundle;
    }

    @Override // com.google.android.gms.common.data.Freezable
    /* JADX INFO: renamed from: rq, reason: merged with bridge method [inline-methods] */
    public m freeze() {
        return this;
    }

    public String toString() {
        return toString(Log.isLoggable("DataItem", 3));
    }

    public String toString(boolean verbose) {
        StringBuilder sb = new StringBuilder("DataItemParcelable[");
        sb.append("@");
        sb.append(Integer.toHexString(hashCode()));
        sb.append(",dataSz=" + (this.aeA == null ? "null" : Integer.valueOf(this.aeA.length)));
        sb.append(", numAssets=" + this.axH.size());
        sb.append(", uri=" + this.mUri);
        if (!verbose) {
            sb.append("]");
            return sb.toString();
        }
        sb.append("]\n  assets: ");
        for (String str : this.axH.keySet()) {
            sb.append("\n    " + str + ": " + this.axH.get(str));
        }
        sb.append("\n  ]");
        return sb.toString();
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel dest, int flags) {
        n.a(this, dest, flags);
    }
}
