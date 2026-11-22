package com.google.android.gms.internal;

import android.os.Parcel;
import android.os.Parcelable;

/* JADX INFO: loaded from: classes.dex */
public class ha implements Parcelable {

    @Deprecated
    public static final Parcelable.Creator<ha> CREATOR = new Parcelable.Creator<ha>() { // from class: com.google.android.gms.internal.ha.1
        @Override // android.os.Parcelable.Creator
        @Deprecated
        /* JADX INFO: renamed from: I, reason: merged with bridge method [inline-methods] */
        public ha[] newArray(int i) {
            return new ha[i];
        }

        @Override // android.os.Parcelable.Creator
        @Deprecated
        /* JADX INFO: renamed from: k, reason: merged with bridge method [inline-methods] */
        public ha createFromParcel(Parcel parcel) {
            return new ha(parcel);
        }
    };
    private String CE;
    private String CF;
    private String mValue;

    @Deprecated
    public ha() {
    }

    @Deprecated
    ha(Parcel parcel) {
        readFromParcel(parcel);
    }

    public ha(String str, String str2, String str3) {
        this.CE = str;
        this.CF = str2;
        this.mValue = str3;
    }

    @Deprecated
    private void readFromParcel(Parcel in) {
        this.CE = in.readString();
        this.CF = in.readString();
        this.mValue = in.readString();
    }

    @Override // android.os.Parcelable
    @Deprecated
    public int describeContents() {
        return 0;
    }

    public String getId() {
        return this.CE;
    }

    public String getValue() {
        return this.mValue;
    }

    @Override // android.os.Parcelable
    @Deprecated
    public void writeToParcel(Parcel out, int flags) {
        out.writeString(this.CE);
        out.writeString(this.CF);
        out.writeString(this.mValue);
    }
}
