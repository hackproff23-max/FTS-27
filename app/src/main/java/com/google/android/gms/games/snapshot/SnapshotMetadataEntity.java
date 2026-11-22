package com.google.android.gms.games.snapshot;

import android.database.CharArrayBuffer;
import android.net.Uri;
import android.os.Parcel;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.games.Game;
import com.google.android.gms.games.GameEntity;
import com.google.android.gms.games.Player;
import com.google.android.gms.games.PlayerEntity;
import com.google.android.gms.internal.jv;
import com.google.android.gms.internal.le;

/* JADX INFO: loaded from: classes.dex */
public final class SnapshotMetadataEntity implements SafeParcelable, SnapshotMetadata {
    public static final SnapshotMetadataEntityCreator CREATOR = new SnapshotMetadataEntityCreator();
    private final int CK;
    private final String OH;
    private final String UO;
    private final String YB;
    private final GameEntity acs;
    private final Uri afd;
    private final PlayerEntity afg;
    private final String afh;
    private final long afi;
    private final long afj;
    private final float afk;
    private final String afl;
    private final boolean afm;

    SnapshotMetadataEntity(int versionCode, GameEntity game, PlayerEntity owner, String snapshotId, Uri coverImageUri, String coverImageUrl, String title, String description, long lastModifiedTimestamp, long playedTime, float coverImageAspectRatio, String uniqueName, boolean changePending) {
        this.CK = versionCode;
        this.acs = game;
        this.afg = owner;
        this.YB = snapshotId;
        this.afd = coverImageUri;
        this.afh = coverImageUrl;
        this.afk = coverImageAspectRatio;
        this.OH = title;
        this.UO = description;
        this.afi = lastModifiedTimestamp;
        this.afj = playedTime;
        this.afl = uniqueName;
        this.afm = changePending;
    }

    public SnapshotMetadataEntity(SnapshotMetadata snapshotMetadata) {
        this.CK = 4;
        this.acs = new GameEntity(snapshotMetadata.getGame());
        this.afg = new PlayerEntity(snapshotMetadata.getOwner());
        this.YB = snapshotMetadata.getSnapshotId();
        this.afd = snapshotMetadata.getCoverImageUri();
        this.afh = snapshotMetadata.getCoverImageUrl();
        this.afk = snapshotMetadata.getCoverImageAspectRatio();
        this.OH = snapshotMetadata.getTitle();
        this.UO = snapshotMetadata.getDescription();
        this.afi = snapshotMetadata.getLastModifiedTimestamp();
        this.afj = snapshotMetadata.getPlayedTime();
        this.afl = snapshotMetadata.getUniqueName();
        this.afm = snapshotMetadata.hasChangePending();
    }

    static int a(SnapshotMetadata snapshotMetadata) {
        return jv.hashCode(snapshotMetadata.getGame(), snapshotMetadata.getOwner(), snapshotMetadata.getSnapshotId(), snapshotMetadata.getCoverImageUri(), Float.valueOf(snapshotMetadata.getCoverImageAspectRatio()), snapshotMetadata.getTitle(), snapshotMetadata.getDescription(), Long.valueOf(snapshotMetadata.getLastModifiedTimestamp()), Long.valueOf(snapshotMetadata.getPlayedTime()), snapshotMetadata.getUniqueName(), Boolean.valueOf(snapshotMetadata.hasChangePending()));
    }

    static boolean a(SnapshotMetadata snapshotMetadata, Object obj) {
        if (!(obj instanceof SnapshotMetadata)) {
            return false;
        }
        if (snapshotMetadata == obj) {
            return true;
        }
        SnapshotMetadata snapshotMetadata2 = (SnapshotMetadata) obj;
        return jv.equal(snapshotMetadata2.getGame(), snapshotMetadata.getGame()) && jv.equal(snapshotMetadata2.getOwner(), snapshotMetadata.getOwner()) && jv.equal(snapshotMetadata2.getSnapshotId(), snapshotMetadata.getSnapshotId()) && jv.equal(snapshotMetadata2.getCoverImageUri(), snapshotMetadata.getCoverImageUri()) && jv.equal(Float.valueOf(snapshotMetadata2.getCoverImageAspectRatio()), Float.valueOf(snapshotMetadata.getCoverImageAspectRatio())) && jv.equal(snapshotMetadata2.getTitle(), snapshotMetadata.getTitle()) && jv.equal(snapshotMetadata2.getDescription(), snapshotMetadata.getDescription()) && jv.equal(Long.valueOf(snapshotMetadata2.getLastModifiedTimestamp()), Long.valueOf(snapshotMetadata.getLastModifiedTimestamp())) && jv.equal(Long.valueOf(snapshotMetadata2.getPlayedTime()), Long.valueOf(snapshotMetadata.getPlayedTime())) && jv.equal(snapshotMetadata2.getUniqueName(), snapshotMetadata.getUniqueName()) && jv.equal(Boolean.valueOf(snapshotMetadata2.hasChangePending()), Boolean.valueOf(snapshotMetadata.hasChangePending()));
    }

    static String b(SnapshotMetadata snapshotMetadata) {
        return jv.h(snapshotMetadata).a("Game", snapshotMetadata.getGame()).a("Owner", snapshotMetadata.getOwner()).a("SnapshotId", snapshotMetadata.getSnapshotId()).a("CoverImageUri", snapshotMetadata.getCoverImageUri()).a("CoverImageUrl", snapshotMetadata.getCoverImageUrl()).a("CoverImageAspectRatio", Float.valueOf(snapshotMetadata.getCoverImageAspectRatio())).a("Description", snapshotMetadata.getDescription()).a("LastModifiedTimestamp", Long.valueOf(snapshotMetadata.getLastModifiedTimestamp())).a("PlayedTime", Long.valueOf(snapshotMetadata.getPlayedTime())).a("UniqueName", snapshotMetadata.getUniqueName()).a("ChangePending", Boolean.valueOf(snapshotMetadata.hasChangePending())).toString();
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public boolean equals(Object obj) {
        return a(this, obj);
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.google.android.gms.common.data.Freezable
    public SnapshotMetadata freeze() {
        return this;
    }

    @Override // com.google.android.gms.games.snapshot.SnapshotMetadata
    public float getCoverImageAspectRatio() {
        return this.afk;
    }

    @Override // com.google.android.gms.games.snapshot.SnapshotMetadata
    public Uri getCoverImageUri() {
        return this.afd;
    }

    @Override // com.google.android.gms.games.snapshot.SnapshotMetadata
    public String getCoverImageUrl() {
        return this.afh;
    }

    @Override // com.google.android.gms.games.snapshot.SnapshotMetadata
    public String getDescription() {
        return this.UO;
    }

    @Override // com.google.android.gms.games.snapshot.SnapshotMetadata
    public void getDescription(CharArrayBuffer dataOut) {
        le.b(this.UO, dataOut);
    }

    @Override // com.google.android.gms.games.snapshot.SnapshotMetadata
    public Game getGame() {
        return this.acs;
    }

    @Override // com.google.android.gms.games.snapshot.SnapshotMetadata
    public long getLastModifiedTimestamp() {
        return this.afi;
    }

    @Override // com.google.android.gms.games.snapshot.SnapshotMetadata
    public Player getOwner() {
        return this.afg;
    }

    @Override // com.google.android.gms.games.snapshot.SnapshotMetadata
    public long getPlayedTime() {
        return this.afj;
    }

    @Override // com.google.android.gms.games.snapshot.SnapshotMetadata
    public String getSnapshotId() {
        return this.YB;
    }

    @Override // com.google.android.gms.games.snapshot.SnapshotMetadata
    public String getTitle() {
        return this.OH;
    }

    @Override // com.google.android.gms.games.snapshot.SnapshotMetadata
    public String getUniqueName() {
        return this.afl;
    }

    public int getVersionCode() {
        return this.CK;
    }

    @Override // com.google.android.gms.games.snapshot.SnapshotMetadata
    public boolean hasChangePending() {
        return this.afm;
    }

    public int hashCode() {
        return a(this);
    }

    @Override // com.google.android.gms.common.data.Freezable
    public boolean isDataValid() {
        return true;
    }

    public String toString() {
        return b(this);
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel out, int flags) {
        SnapshotMetadataEntityCreator.a(this, out, flags);
    }
}
