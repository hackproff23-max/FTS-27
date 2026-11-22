package com.google.android.gms.games.internal.game;

import android.net.Uri;
import android.os.Parcel;
import com.google.android.gms.games.internal.GamesDowngradeableSafeParcel;
import com.google.android.gms.internal.jv;

/* JADX INFO: loaded from: classes.dex */
public final class GameBadgeEntity extends GamesDowngradeableSafeParcel implements GameBadge {
    public static final GameBadgeEntityCreator CREATOR = new GameBadgeEntityCreatorCompat();
    private final int CK;
    private int Gt;
    private String OH;
    private String UO;
    private Uri WD;

    static final class GameBadgeEntityCreatorCompat extends GameBadgeEntityCreator {
        GameBadgeEntityCreatorCompat() {
        }

        @Override // com.google.android.gms.games.internal.game.GameBadgeEntityCreator, android.os.Parcelable.Creator
        /* JADX INFO: renamed from: cv */
        public GameBadgeEntity createFromParcel(Parcel parcel) {
            if (GameBadgeEntity.c(GameBadgeEntity.ht()) || GameBadgeEntity.aW(GameBadgeEntity.class.getCanonicalName())) {
                return super.createFromParcel(parcel);
            }
            int i = parcel.readInt();
            String string = parcel.readString();
            String string2 = parcel.readString();
            String string3 = parcel.readString();
            return new GameBadgeEntity(1, i, string, string2, string3 == null ? null : Uri.parse(string3));
        }
    }

    GameBadgeEntity(int versionCode, int type, String title, String description, Uri iconImageUri) {
        this.CK = versionCode;
        this.Gt = type;
        this.OH = title;
        this.UO = description;
        this.WD = iconImageUri;
    }

    public GameBadgeEntity(GameBadge gameBadge) {
        this.CK = 1;
        this.Gt = gameBadge.getType();
        this.OH = gameBadge.getTitle();
        this.UO = gameBadge.getDescription();
        this.WD = gameBadge.getIconImageUri();
    }

    static int a(GameBadge gameBadge) {
        return jv.hashCode(Integer.valueOf(gameBadge.getType()), gameBadge.getTitle(), gameBadge.getDescription(), gameBadge.getIconImageUri());
    }

    static boolean a(GameBadge gameBadge, Object obj) {
        if (!(obj instanceof GameBadge)) {
            return false;
        }
        if (gameBadge == obj) {
            return true;
        }
        GameBadge gameBadge2 = (GameBadge) obj;
        return jv.equal(Integer.valueOf(gameBadge2.getType()), gameBadge.getTitle()) && jv.equal(gameBadge2.getDescription(), gameBadge.getIconImageUri());
    }

    static String b(GameBadge gameBadge) {
        return jv.h(gameBadge).a("Type", Integer.valueOf(gameBadge.getType())).a("Title", gameBadge.getTitle()).a("Description", gameBadge.getDescription()).a("IconImageUri", gameBadge.getIconImageUri()).toString();
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public boolean equals(Object obj) {
        return a(this, obj);
    }

    @Override // com.google.android.gms.games.internal.game.GameBadge
    public String getDescription() {
        return this.UO;
    }

    @Override // com.google.android.gms.games.internal.game.GameBadge
    public Uri getIconImageUri() {
        return this.WD;
    }

    @Override // com.google.android.gms.games.internal.game.GameBadge
    public String getTitle() {
        return this.OH;
    }

    @Override // com.google.android.gms.games.internal.game.GameBadge
    public int getType() {
        return this.Gt;
    }

    public int getVersionCode() {
        return this.CK;
    }

    public int hashCode() {
        return a(this);
    }

    @Override // com.google.android.gms.common.data.Freezable
    public boolean isDataValid() {
        return true;
    }

    @Override // com.google.android.gms.common.data.Freezable
    /* JADX INFO: renamed from: mk, reason: merged with bridge method [inline-methods] */
    public GameBadge freeze() {
        return this;
    }

    public String toString() {
        return b(this);
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel dest, int flags) {
        if (!hu()) {
            GameBadgeEntityCreator.a(this, dest, flags);
            return;
        }
        dest.writeInt(this.Gt);
        dest.writeString(this.OH);
        dest.writeString(this.UO);
        dest.writeString(this.WD == null ? null : this.WD.toString());
    }
}
