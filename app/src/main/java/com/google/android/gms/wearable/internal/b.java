package com.google.android.gms.wearable.internal;

import android.content.IntentFilter;
import android.os.IBinder;
import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;

/* JADX INFO: loaded from: classes.dex */
public class b implements SafeParcelable {
    public static final Parcelable.Creator<b> CREATOR = new c();
    final int CK;
    public final ae axv;
    public final IntentFilter[] axw;

    b(int i, IBinder iBinder, IntentFilter[] intentFilterArr) {
        this.CK = i;
        if (iBinder != null) {
            this.axv = ae.a.bY(iBinder);
        } else {
            this.axv = null;
        }
        this.axw = intentFilterArr;
    }

    public b(bb bbVar) {
        this.CK = 1;
        this.axv = bbVar;
        this.axw = bbVar.rs();
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
        c.a(this, dest, flags);
    }
}
