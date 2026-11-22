package com.google.android.gms.drive.realtime.internal.event;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;

/* JADX INFO: loaded from: classes.dex */
public class ValuesRemovedDetails implements SafeParcelable {
    public static final Parcelable.Creator<ValuesRemovedDetails> CREATOR = new i();
    final int CK;
    final int SO;
    final int SP;
    final String Tm;
    final int Tn;
    final int mIndex;

    ValuesRemovedDetails(int versionCode, int index, int valueIndex, int valueCount, String movedToId, int movedToIndex) {
        this.CK = versionCode;
        this.mIndex = index;
        this.SO = valueIndex;
        this.SP = valueCount;
        this.Tm = movedToId;
        this.Tn = movedToIndex;
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel dest, int flags) {
        i.a(this, dest, flags);
    }
}
