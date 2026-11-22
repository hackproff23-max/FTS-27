package com.google.android.gms.fitness.request;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.fitness.data.DataType;

/* JADX INFO: loaded from: classes.dex */
public class m implements SafeParcelable {
    public static final Parcelable.Creator<m> CREATOR = new n();
    private final int CK;
    private final DataType TM;

    public static class a {
        private DataType TM;

        public a c(DataType dataType) {
            this.TM = dataType;
            return this;
        }

        public m ka() {
            return new m(this);
        }
    }

    m(int i, DataType dataType) {
        this.CK = i;
        this.TM = dataType;
    }

    private m(a aVar) {
        this.CK = 1;
        this.TM = aVar.TM;
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public DataType getDataType() {
        return this.TM;
    }

    int getVersionCode() {
        return this.CK;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int flags) {
        n.a(this, parcel, flags);
    }
}
