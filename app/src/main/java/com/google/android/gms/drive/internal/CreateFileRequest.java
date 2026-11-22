package com.google.android.gms.drive.internal;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.drive.Contents;
import com.google.android.gms.drive.DriveId;
import com.google.android.gms.drive.ExecutionOptions;
import com.google.android.gms.drive.metadata.internal.MetadataBundle;
import com.google.android.gms.internal.jx;

/* JADX INFO: loaded from: classes.dex */
public class CreateFileRequest implements SafeParcelable {
    public static final Parcelable.Creator<CreateFileRequest> CREATOR = new k();
    final int CK;
    final String Oy;
    final boolean PA;
    final int PB;
    final int PC;
    final Contents Pr;
    final MetadataBundle Px;
    final Integer Py;
    final DriveId Pz;

    CreateFileRequest(int versionCode, DriveId parentDriveId, MetadataBundle metadata, Contents contentsReference, Integer fileType, boolean sendEventOnCompletion, String trackingTag, int createStrategy, int openContentsRequestId) {
        if (contentsReference != null && openContentsRequestId != 0) {
            jx.b(contentsReference.getRequestId() == openContentsRequestId, "inconsistent contents reference");
        }
        if ((fileType == null || fileType.intValue() == 0) && contentsReference == null && openContentsRequestId == 0) {
            throw new IllegalArgumentException("Need a valid contents");
        }
        this.CK = versionCode;
        this.Pz = (DriveId) jx.i(parentDriveId);
        this.Px = (MetadataBundle) jx.i(metadata);
        this.Pr = contentsReference;
        this.Py = fileType;
        this.Oy = trackingTag;
        this.PB = createStrategy;
        this.PA = sendEventOnCompletion;
        this.PC = openContentsRequestId;
    }

    public CreateFileRequest(DriveId parentDriveId, MetadataBundle metadata, int openContentsRequestId, int fileType, ExecutionOptions executionOptions) {
        this(2, parentDriveId, metadata, null, Integer.valueOf(fileType), executionOptions.iw(), executionOptions.iv(), executionOptions.ix(), openContentsRequestId);
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel dest, int flags) {
        k.a(this, dest, flags);
    }
}
