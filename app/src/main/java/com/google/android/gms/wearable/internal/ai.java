package com.google.android.gms.wearable.internal;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.wearable.MessageEvent;

/* JADX INFO: loaded from: classes.dex */
public class ai implements SafeParcelable, MessageEvent {
    public static final Parcelable.Creator<ai> CREATOR = new aj();
    final int CK;
    private final byte[] aeA;
    private final String ahy;
    private final String axW;
    private final int ve;

    ai(int i, int i2, String str, byte[] bArr, String str2) {
        this.CK = i;
        this.ve = i2;
        this.axW = str;
        this.aeA = bArr;
        this.ahy = str2;
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    @Override // com.google.android.gms.wearable.MessageEvent
    public byte[] getData() {
        return this.aeA;
    }

    @Override // com.google.android.gms.wearable.MessageEvent
    public String getPath() {
        return this.axW;
    }

    @Override // com.google.android.gms.wearable.MessageEvent
    public int getRequestId() {
        return this.ve;
    }

    @Override // com.google.android.gms.wearable.MessageEvent
    public String getSourceNodeId() {
        return this.ahy;
    }

    public String toString() {
        return "MessageEventParcelable[" + this.ve + "," + this.axW + ", size=" + (this.aeA == null ? "null" : Integer.valueOf(this.aeA.length)) + "]";
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel dest, int flags) {
        aj.a(this, dest, flags);
    }
}
