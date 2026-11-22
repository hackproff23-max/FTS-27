package com.google.android.gms.fitness.request;

import android.os.Parcel;
import android.os.Parcelable;
import com.facebook.internal.ServerProtocol;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.fitness.data.DataType;
import com.google.android.gms.internal.jv;
import com.google.android.gms.internal.jx;
import com.google.android.gms.internal.la;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public class DataSourcesRequest implements SafeParcelable {
    public static final Parcelable.Creator<DataSourcesRequest> CREATOR = new h();
    private final int CK;
    private final List<DataType> TY;
    private final List<Integer> VS;
    private final boolean VT;

    public static class Builder {
        private DataType[] VU = new DataType[0];
        private int[] VV = {0, 1};
        private boolean VT = false;

        public DataSourcesRequest build() {
            jx.a(this.VU.length > 0, "Must add at least one data type");
            jx.a(this.VV.length > 0, "Must add at least one data source type");
            return new DataSourcesRequest(this);
        }

        public Builder setDataSourceTypes(int... dataSourceTypes) {
            this.VV = dataSourceTypes;
            return this;
        }

        public Builder setDataTypes(DataType... dataTypes) {
            this.VU = dataTypes;
            return this;
        }
    }

    DataSourcesRequest(int versionCode, List<DataType> dataTypes, List<Integer> dataSourceTypes, boolean includeDbOnlySources) {
        this.CK = versionCode;
        this.TY = dataTypes;
        this.VS = dataSourceTypes;
        this.VT = includeDbOnlySources;
    }

    private DataSourcesRequest(Builder builder) {
        this.CK = 2;
        this.TY = la.b(builder.VU);
        this.VS = Arrays.asList(la.a(builder.VV));
        this.VT = builder.VT;
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public List<DataType> getDataTypes() {
        return Collections.unmodifiableList(this.TY);
    }

    int getVersionCode() {
        return this.CK;
    }

    public boolean jY() {
        return this.VT;
    }

    List<Integer> jZ() {
        return this.VS;
    }

    public String toString() {
        jv.a aVarA = jv.h(this).a("dataTypes", this.TY).a("sourceTypes", this.VS);
        if (this.VT) {
            aVarA.a("includeDbOnlySources", ServerProtocol.DIALOG_RETURN_SCOPES_TRUE);
        }
        return aVarA.toString();
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int flags) {
        h.a(this, parcel, flags);
    }
}
