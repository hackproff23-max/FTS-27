package com.google.android.gms.drive.internal;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.drive.Contents;
import com.google.android.gms.drive.DriveId;
import com.google.android.gms.drive.ExecutionOptions;
import com.google.android.gms.drive.metadata.internal.MetadataBundle;

/* JADX INFO: loaded from: classes.dex */
public class CloseContentsAndUpdateMetadataRequest implements SafeParcelable {
    public static final Parcelable.Creator<CloseContentsAndUpdateMetadataRequest> CREATOR = new f();
    final int CK;
    final String Oy;
    final boolean Oz;
    final DriveId Pp;
    final MetadataBundle Pq;
    final Contents Pr;
    final int Ps;

    CloseContentsAndUpdateMetadataRequest(int versionCode, DriveId id, MetadataBundle metadataChangeSet, Contents contentsReference, boolean notifyOnCompletion, String trackingTag, int commitStrategy) {
        this.CK = versionCode;
        this.Pp = id;
        this.Pq = metadataChangeSet;
        this.Pr = contentsReference;
        this.Oz = notifyOnCompletion;
        this.Oy = trackingTag;
        this.Ps = commitStrategy;
    }

    public CloseContentsAndUpdateMetadataRequest(DriveId id, MetadataBundle metadataChangeSet, Contents contentsReference, ExecutionOptions executionOptions) {
        this(1, id, metadataChangeSet, contentsReference, executionOptions.iw(), executionOptions.iv(), executionOptions.ix());
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel dest, int flags) {
        f.a(this, dest, flags);
    }
}
