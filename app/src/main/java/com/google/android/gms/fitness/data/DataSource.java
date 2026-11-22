package com.google.android.gms.fitness.data;

import android.content.Context;
import android.content.Intent;
import android.os.Parcel;
import android.os.Parcelable;
import com.facebook.ads.InterstitialAd;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.internal.jx;
import com.google.android.gms.internal.mg;

/* JADX INFO: loaded from: classes.dex */
public class DataSource implements SafeParcelable {
    public static final Parcelable.Creator<DataSource> CREATOR = new g();
    public static final String EXTRA_DATA_SOURCE = "vnd.google.fitness.data_source";
    public static final int TYPE_DERIVED = 1;
    public static final int TYPE_RAW = 0;
    private final int CK;
    private final int Gt;
    private final DataType TM;
    private final Device Ul;
    private final a Um;
    private final String Un;
    private final boolean Uo;
    private final String Up;
    private final String mName;

    public static final class Builder {
        private DataType TM;
        private Device Ul;
        private a Um;
        private String mName;
        private int Gt = -1;
        private String Un = "";
        private boolean Uo = false;

        public DataSource build() {
            jx.a(this.TM != null, "Must set data type");
            jx.a(this.Gt >= 0, "Must set data source type");
            return new DataSource(this);
        }

        public Builder setAppPackageName(Context appContext) {
            return setAppPackageName(appContext.getPackageName());
        }

        public Builder setAppPackageName(String packageName) {
            this.Um = new a(packageName, null, null);
            return this;
        }

        public Builder setDataType(DataType dataType) {
            this.TM = dataType;
            return this;
        }

        public Builder setDevice(Device device) {
            this.Ul = device;
            return this;
        }

        public Builder setName(String name) {
            this.mName = name;
            return this;
        }

        public Builder setObfuscated(boolean isObfuscated) {
            this.Uo = isObfuscated;
            return this;
        }

        public Builder setStreamName(String streamName) {
            jx.b(streamName != null, "Must specify a valid stream name");
            this.Un = streamName;
            return this;
        }

        public Builder setType(int type) {
            this.Gt = type;
            return this;
        }
    }

    DataSource(int versionCode, DataType dataType, String name, int type, Device device, a application, String streamName, boolean isObfuscated) {
        this.CK = versionCode;
        this.TM = dataType;
        this.Gt = type;
        this.mName = name;
        this.Ul = device;
        this.Um = application;
        this.Un = streamName;
        this.Uo = isObfuscated;
        this.Up = jy();
    }

    private DataSource(Builder builder) {
        this.CK = 3;
        this.TM = builder.TM;
        this.Gt = builder.Gt;
        this.mName = builder.mName;
        this.Ul = builder.Ul;
        this.Um = builder.Um;
        this.Un = builder.Un;
        this.Uo = builder.Uo;
        this.Up = jy();
    }

    private boolean a(DataSource dataSource) {
        return this.Up.equals(dataSource.Up);
    }

    public static DataSource extract(Intent intent) {
        if (intent == null) {
            return null;
        }
        return (DataSource) com.google.android.gms.common.internal.safeparcel.c.a(intent, EXTRA_DATA_SOURCE, CREATOR);
    }

    private String getTypeString() {
        switch (this.Gt) {
            case 0:
                return "raw";
            case 1:
                return "derived";
            default:
                throw new IllegalArgumentException("invalid type value");
        }
    }

    private String jy() {
        StringBuilder sb = new StringBuilder();
        sb.append(getTypeString());
        sb.append(InterstitialAd.SEPARATOR).append(this.TM.getName());
        if (this.Um != null) {
            sb.append(InterstitialAd.SEPARATOR).append(this.Um.getPackageName());
        }
        if (this.Ul != null) {
            sb.append(InterstitialAd.SEPARATOR).append(this.Ul.getStreamIdentifier());
        }
        if (this.Un != null) {
            sb.append(InterstitialAd.SEPARATOR).append(this.Un);
        }
        return sb.toString();
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public boolean equals(Object that) {
        return this == that || ((that instanceof DataSource) && a((DataSource) that));
    }

    public String getAppPackageName() {
        if (this.Um == null) {
            return null;
        }
        return this.Um.getPackageName();
    }

    public DataType getDataType() {
        return this.TM;
    }

    public Device getDevice() {
        return this.Ul;
    }

    public String getName() {
        return this.mName;
    }

    public String getStreamIdentifier() {
        return this.Up;
    }

    public String getStreamName() {
        return this.Un;
    }

    public int getType() {
        return this.Gt;
    }

    int getVersionCode() {
        return this.CK;
    }

    public int hashCode() {
        return this.Up.hashCode();
    }

    public DataSource jA() {
        return new DataSource(3, this.TM, this.mName, this.Gt, this.Ul == null ? null : this.Ul.jE(), this.Um == null ? null : this.Um.jl(), mg.bw(this.Un), this.Uo);
    }

    public a jx() {
        return this.Um;
    }

    public boolean jz() {
        return this.Uo;
    }

    public String toDebugString() {
        String str;
        StringBuilder sbAppend = new StringBuilder().append(this.Gt == 0 ? "r" : "d").append(InterstitialAd.SEPARATOR).append(this.TM.jB());
        if (this.Um == null) {
            str = "";
        } else {
            str = this.Um.equals(a.TT) ? ":gms" : InterstitialAd.SEPARATOR + this.Um.getPackageName();
        }
        return sbAppend.append(str).append(this.Ul != null ? InterstitialAd.SEPARATOR + this.Ul.getModel() + InterstitialAd.SEPARATOR + this.Ul.getUid() : "").append(this.Un != null ? InterstitialAd.SEPARATOR + this.Un : "").toString();
    }

    public String toString() {
        StringBuilder sb = new StringBuilder("DataSource{");
        sb.append(getTypeString());
        if (this.mName != null) {
            sb.append(InterstitialAd.SEPARATOR).append(this.mName);
        }
        if (this.Um != null) {
            sb.append(InterstitialAd.SEPARATOR).append(this.Um);
        }
        if (this.Ul != null) {
            sb.append(InterstitialAd.SEPARATOR).append(this.Ul);
        }
        if (this.Un != null) {
            sb.append(InterstitialAd.SEPARATOR).append(this.Un);
        }
        sb.append(InterstitialAd.SEPARATOR).append(this.TM);
        return sb.append("}").toString();
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int flags) {
        g.a(mg.c(this), parcel, flags);
    }
}
