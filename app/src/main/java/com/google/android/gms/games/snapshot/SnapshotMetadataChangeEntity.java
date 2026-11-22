package com.google.android.gms.games.snapshot;

import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Parcel;
import com.google.android.gms.common.data.a;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.internal.jx;

/* JADX INFO: loaded from: classes.dex */
public final class SnapshotMetadataChangeEntity extends SnapshotMetadataChange implements SafeParcelable {
    public static final SnapshotMetadataChangeCreator CREATOR = new SnapshotMetadataChangeCreator();
    private final int CK;
    private final String UO;
    private final Uri afd;
    private final Long afe;
    private a aff;

    SnapshotMetadataChangeEntity() {
        this(4, null, null, null, null);
    }

    SnapshotMetadataChangeEntity(int versionCode, String description, Long playedTimeMillis, a coverImage, Uri coverImageUri) {
        this.CK = versionCode;
        this.UO = description;
        this.afe = playedTimeMillis;
        this.aff = coverImage;
        this.afd = coverImageUri;
        if (this.aff != null) {
            jx.a(this.afd == null, "Cannot set both a URI and an image");
        } else if (this.afd != null) {
            jx.a(this.aff == null, "Cannot set both a URI and an image");
        }
    }

    SnapshotMetadataChangeEntity(String description, Long playedTimeMillis, a coverImage, Uri coverImageUri) {
        this(4, description, playedTimeMillis, coverImage, coverImageUri);
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    @Override // com.google.android.gms.games.snapshot.SnapshotMetadataChange
    public Bitmap getCoverImage() {
        if (this.aff == null) {
            return null;
        }
        return this.aff.gT();
    }

    public Uri getCoverImageUri() {
        return this.afd;
    }

    @Override // com.google.android.gms.games.snapshot.SnapshotMetadataChange
    public String getDescription() {
        return this.UO;
    }

    @Override // com.google.android.gms.games.snapshot.SnapshotMetadataChange
    public Long getPlayedTimeMillis() {
        return this.afe;
    }

    public int getVersionCode() {
        return this.CK;
    }

    @Override // com.google.android.gms.games.snapshot.SnapshotMetadataChange
    public a mT() {
        return this.aff;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel out, int flags) {
        SnapshotMetadataChangeCreator.a(this, out, flags);
    }
}
