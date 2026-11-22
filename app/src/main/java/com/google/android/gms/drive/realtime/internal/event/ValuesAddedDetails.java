package com.google.android.gms.drive.realtime.internal.event;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;

/* JADX INFO: loaded from: classes.dex */
public class ValuesAddedDetails implements SafeParcelable {
    public static final Parcelable.Creator<ValuesAddedDetails> CREATOR = new h();
    final int CK;
    final int SO;
    final int SP;
    final String Tk;
    final int Tl;
    final int mIndex;

    ValuesAddedDetails(int versionCode, int index, int valueIndex, int valueCount, String movedFromId, int movedFromIndex) {
        this.CK = versionCode;
        this.mIndex = index;
        this.SO = valueIndex;
        this.SP = valueCount;
        this.Tk = movedFromId;
        this.Tl = movedFromIndex;
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel dest, int flags) {
        h.a(this, dest, flags);
    }
}
