package com.google.android.gms.drive.metadata.internal;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.drive.metadata.CustomPropertyKey;
import com.google.android.gms.internal.jx;
import com.unity3d.ads.android.properties.UnityAdsConstants;

/* JADX INFO: loaded from: classes.dex */
public class CustomProperty implements SafeParcelable {
    public static final Parcelable.Creator<CustomProperty> CREATOR = new c();
    final int CK;
    final CustomPropertyKey Rg;
    final String mValue;

    CustomProperty(int versionCode, CustomPropertyKey key, String value) {
        this.CK = versionCode;
        jx.b(key, UnityAdsConstants.UNITY_ADS_REWARD_ITEMKEY_KEY);
        this.Rg = key;
        this.mValue = value;
    }

    public CustomProperty(CustomPropertyKey key, String value) {
        this(1, key, value);
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public String getValue() {
        return this.mValue;
    }

    public CustomPropertyKey iX() {
        return this.Rg;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel dest, int flags) {
        c.a(this, dest, flags);
    }
}
