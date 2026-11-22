package com.google.android.gms.wearable.internal;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public class aw implements SafeParcelable {
    public static final Parcelable.Creator<aw> CREATOR = new ax();
    public final long ayc;
    public final List<an> aye;
    public final int statusCode;
    public final int versionCode;

    aw(int i, int i2, long j, List<an> list) {
        this.versionCode = i;
        this.statusCode = i2;
        this.ayc = j;
        this.aye = list;
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel out, int flags) {
        ax.a(this, out, flags);
    }
}
