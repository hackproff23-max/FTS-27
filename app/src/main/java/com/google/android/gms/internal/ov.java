package com.google.android.gms.internal;

import android.os.Parcel;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;

/* JADX INFO: loaded from: classes.dex */
public class ov implements SafeParcelable {
    public static final ow CREATOR = new ow();
    public final int ang;
    public final int anh;
    public final String ani;
    public final String anj;
    public final boolean ank;
    public final String anl;
    public final String packageName;
    public final int versionCode;

    public ov(int i, String str, int i2, int i3, String str2, String str3, boolean z, String str4) {
        this.versionCode = i;
        this.packageName = str;
        this.ang = i2;
        this.anh = i3;
        this.ani = str2;
        this.anj = str3;
        this.ank = z;
        this.anl = str4;
    }

    @Deprecated
    public ov(String str, int i, int i2, String str2, String str3, boolean z) {
        this.versionCode = 1;
        this.packageName = (String) jx.i(str);
        this.ang = i;
        this.anh = i2;
        this.anl = null;
        this.ani = str2;
        this.anj = str3;
        this.ank = z;
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public boolean equals(Object object) {
        if (this == object) {
            return true;
        }
        if (!(object instanceof ov)) {
            return false;
        }
        ov ovVar = (ov) object;
        return this.packageName.equals(ovVar.packageName) && this.ang == ovVar.ang && this.anh == ovVar.anh && jv.equal(this.anl, ovVar.anl) && jv.equal(this.ani, ovVar.ani) && jv.equal(this.anj, ovVar.anj) && this.ank == ovVar.ank;
    }

    public int hashCode() {
        return jv.hashCode(this.packageName, Integer.valueOf(this.ang), Integer.valueOf(this.anh), this.ani, this.anj, Boolean.valueOf(this.ank));
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("PlayLoggerContext[");
        sb.append("package=").append(this.packageName).append(',');
        sb.append("versionCode=").append(this.versionCode).append(',');
        sb.append("logSource=").append(this.anh).append(',');
        sb.append("logSourceName=").append(this.anl).append(',');
        sb.append("uploadAccount=").append(this.ani).append(',');
        sb.append("loggingId=").append(this.anj).append(',');
        sb.append("logAndroidId=").append(this.ank);
        sb.append("]");
        return sb.toString();
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel out, int flags) {
        ow.a(this, out, flags);
    }
}
