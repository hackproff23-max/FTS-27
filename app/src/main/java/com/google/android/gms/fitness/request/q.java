package com.google.android.gms.fitness.request;

import android.app.PendingIntent;
import android.os.IBinder;
import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;

/* JADX INFO: loaded from: classes.dex */
public class q implements SafeParcelable {
    public static final Parcelable.Creator<q> CREATOR = new r();
    private final int CK;
    private final com.google.android.gms.fitness.data.k VW;
    private final PendingIntent mPendingIntent;

    q(int i, IBinder iBinder, PendingIntent pendingIntent) {
        this.CK = i;
        this.VW = iBinder == null ? null : com.google.android.gms.fitness.data.k.a.aq(iBinder);
        this.mPendingIntent = pendingIntent;
    }

    public q(com.google.android.gms.fitness.data.k kVar, PendingIntent pendingIntent) {
        this.CK = 2;
        this.VW = kVar;
        this.mPendingIntent = pendingIntent;
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    int getVersionCode() {
        return this.CK;
    }

    public PendingIntent kb() {
        return this.mPendingIntent;
    }

    IBinder kg() {
        if (this.VW == null) {
            return null;
        }
        return this.VW.asBinder();
    }

    public String toString() {
        return String.format("SensorUnregistrationRequest{%s}", this.VW);
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int flags) {
        r.a(this, parcel, flags);
    }
}
