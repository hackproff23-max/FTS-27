package com.google.android.gms.internal;

import android.os.Parcel;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;

/* JADX INFO: loaded from: classes.dex */
public class hh implements SafeParcelable {
    final int CK;
    public final String CX;
    final hp CY;
    public final int CZ;
    public final byte[] Da;
    public static final int CV = Integer.parseInt("-1");
    public static final hi CREATOR = new hi();
    private static final hp CW = new hp.a("SsbContext").G(true).at("blob").fG();

    hh(int i, String str, hp hpVar, int i2, byte[] bArr) {
        jx.b(i2 == CV || ho.P(i2) != null, "Invalid section type " + i2);
        this.CK = i;
        this.CX = str;
        this.CY = hpVar;
        this.CZ = i2;
        this.Da = bArr;
        String strFE = fE();
        if (strFE != null) {
            throw new IllegalArgumentException(strFE);
        }
    }

    public hh(String str, hp hpVar) {
        this(1, str, hpVar, CV, null);
    }

    public hh(String str, hp hpVar, String str2) {
        this(1, str, hpVar, ho.as(str2), null);
    }

    public hh(byte[] bArr, hp hpVar) {
        this(1, null, hpVar, CV, bArr);
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        hi hiVar = CREATOR;
        return 0;
    }

    public String fE() {
        if (this.CZ != CV && ho.P(this.CZ) == null) {
            return "Invalid section type " + this.CZ;
        }
        if (this.CX == null || this.Da == null) {
            return null;
        }
        return "Both content and blobContent set";
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel dest, int flags) {
        hi hiVar = CREATOR;
        hi.a(this, dest, flags);
    }
}
