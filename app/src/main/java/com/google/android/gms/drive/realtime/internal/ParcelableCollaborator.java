package com.google.android.gms.drive.realtime.internal;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;

/* JADX INFO: loaded from: classes.dex */
public class ParcelableCollaborator implements SafeParcelable {
    public static final Parcelable.Creator<ParcelableCollaborator> CREATOR = new p();
    final int CK;
    final String OS;
    final boolean SH;
    final boolean SI;
    final String SJ;
    final String SK;
    final String SL;
    final String vZ;

    ParcelableCollaborator(int versionCode, boolean isMe, boolean isAnonymous, String sessionId, String userId, String displayName, String color, String photoUrl) {
        this.CK = versionCode;
        this.SH = isMe;
        this.SI = isAnonymous;
        this.vZ = sessionId;
        this.SJ = userId;
        this.OS = displayName;
        this.SK = color;
        this.SL = photoUrl;
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof ParcelableCollaborator) {
            return this.vZ.equals(((ParcelableCollaborator) obj).vZ);
        }
        return false;
    }

    public int hashCode() {
        return this.vZ.hashCode();
    }

    public String toString() {
        return "Collaborator [isMe=" + this.SH + ", isAnonymous=" + this.SI + ", sessionId=" + this.vZ + ", userId=" + this.SJ + ", displayName=" + this.OS + ", color=" + this.SK + ", photoUrl=" + this.SL + "]";
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel dest, int flags) {
        p.a(this, dest, flags);
    }
}
