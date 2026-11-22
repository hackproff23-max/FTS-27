package com.google.android.gms.drive;

import android.os.Parcel;
import android.os.Parcelable;
import android.util.Base64;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.drive.internal.ak;
import com.google.android.gms.drive.internal.w;
import com.google.android.gms.internal.jx;
import com.google.android.gms.internal.qv;
import com.google.android.gms.internal.qw;

/* JADX INFO: loaded from: classes.dex */
public class DriveId implements SafeParcelable {
    public static final Parcelable.Creator<DriveId> CREATOR = new c();
    final int CK;
    final String Ot;
    final long Ou;
    final long Ov;
    private volatile String Ow;

    DriveId(int versionCode, String resourceId, long sqlId, long databaseInstanceId) {
        this.Ow = null;
        this.CK = versionCode;
        this.Ot = resourceId;
        jx.L(!"".equals(resourceId));
        jx.L((resourceId == null && sqlId == -1) ? false : true);
        this.Ou = sqlId;
        this.Ov = databaseInstanceId;
    }

    public DriveId(String resourceId, long sqlId, long databaseInstanceId) {
        this(1, resourceId, sqlId, databaseInstanceId);
    }

    public static DriveId bi(String str) {
        jx.i(str);
        return new DriveId(str, -1L, -1L);
    }

    public static DriveId decodeFromString(String s) {
        jx.b(s.startsWith("DriveId:"), "Invalid DriveId: " + s);
        return f(Base64.decode(s.substring("DriveId:".length()), 10));
    }

    static DriveId f(byte[] bArr) {
        try {
            ak akVarG = ak.g(bArr);
            return new DriveId(akVarG.versionCode, "".equals(akVarG.QG) ? null : akVarG.QG, akVarG.QH, akVarG.QI);
        } catch (qv e) {
            throw new IllegalArgumentException();
        }
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public final String encodeToString() {
        if (this.Ow == null) {
            this.Ow = "DriveId:" + Base64.encodeToString(iu(), 10);
        }
        return this.Ow;
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof DriveId)) {
            return false;
        }
        DriveId driveId = (DriveId) obj;
        if (driveId.Ov != this.Ov) {
            w.o("DriveId", "Attempt to compare invalid DriveId detected. Has local storage been cleared?");
            return false;
        }
        if (driveId.Ou == -1 && this.Ou == -1) {
            return driveId.Ot.equals(this.Ot);
        }
        return driveId.Ou == this.Ou;
    }

    public String getResourceId() {
        return this.Ot;
    }

    public int hashCode() {
        return this.Ou == -1 ? this.Ot.hashCode() : (String.valueOf(this.Ov) + String.valueOf(this.Ou)).hashCode();
    }

    final byte[] iu() {
        ak akVar = new ak();
        akVar.versionCode = this.CK;
        akVar.QG = this.Ot == null ? "" : this.Ot;
        akVar.QH = this.Ou;
        akVar.QI = this.Ov;
        return qw.f(akVar);
    }

    public String toString() {
        return encodeToString();
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel out, int flags) {
        c.a(this, out, flags);
    }
}
