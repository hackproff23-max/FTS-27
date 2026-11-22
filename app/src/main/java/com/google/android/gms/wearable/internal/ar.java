package com.google.android.gms.wearable.internal;

import android.os.IBinder;
import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;

/* JADX INFO: loaded from: classes.dex */
public class ar implements SafeParcelable {
    public static final Parcelable.Creator<ar> CREATOR = new as();
    final int CK;
    public final ae axv;

    ar(int i, IBinder iBinder) {
        this.CK = i;
        if (iBinder != null) {
            this.axv = ae.a.bY(iBinder);
        } else {
            this.axv = null;
        }
    }

    public ar(ae aeVar) {
        this.CK = 1;
        this.axv = aeVar;
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    IBinder rm() {
        if (this.axv == null) {
            return null;
        }
        return this.axv.asBinder();
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel dest, int flags) {
        as.a(this, dest, flags);
    }
}
