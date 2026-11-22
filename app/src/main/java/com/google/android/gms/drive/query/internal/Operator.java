package com.google.android.gms.drive.query.internal;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;

/* JADX INFO: loaded from: classes.dex */
public class Operator implements SafeParcelable {
    final int CK;
    final String mTag;
    public static final Parcelable.Creator<Operator> CREATOR = new l();
    public static final Operator Sw = new Operator("=");
    public static final Operator Sx = new Operator("<");
    public static final Operator Sy = new Operator("<=");
    public static final Operator Sz = new Operator(">");
    public static final Operator SA = new Operator(">=");
    public static final Operator SB = new Operator("and");
    public static final Operator SC = new Operator("or");
    public static final Operator SD = new Operator("not");
    public static final Operator SE = new Operator("contains");

    Operator(int versionCode, String tag) {
        this.CK = versionCode;
        this.mTag = tag;
    }

    private Operator(String tag) {
        this(1, tag);
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj != null && getClass() == obj.getClass()) {
            Operator operator = (Operator) obj;
            if (this.mTag == null) {
                return operator.mTag == null;
            }
            return this.mTag.equals(operator.mTag);
        }
        return false;
    }

    public String getTag() {
        return this.mTag;
    }

    public int hashCode() {
        return (this.mTag == null ? 0 : this.mTag.hashCode()) + 31;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel out, int flags) {
        l.a(this, out, flags);
    }
}
