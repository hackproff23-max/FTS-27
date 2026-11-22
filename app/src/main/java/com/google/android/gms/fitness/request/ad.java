package com.google.android.gms.fitness.request;

import android.os.IBinder;
import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;

/* JADX INFO: loaded from: classes.dex */
public class ad implements SafeParcelable {
    public static final Parcelable.Creator<ad> CREATOR = new ae();
    private final int CK;
    private final l Wm;

    ad(int i, IBinder iBinder) {
        this.CK = i;
        this.Wm = l.a.aB(iBinder);
    }

    public ad(BleScanCallback bleScanCallback) {
        this.CK = 1;
        this.Wm = a.C0061a.jP().b(bleScanCallback);
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    int getVersionCode() {
        return this.CK;
    }

    public IBinder km() {
        return this.Wm.asBinder();
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int flags) {
        ae.a(this, parcel, flags);
    }
}
