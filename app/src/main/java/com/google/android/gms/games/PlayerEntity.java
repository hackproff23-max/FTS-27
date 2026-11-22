package com.google.android.gms.games;

import android.database.CharArrayBuffer;
import android.net.Uri;
import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.games.internal.GamesDowngradeableSafeParcel;
import com.google.android.gms.games.internal.player.MostRecentGameInfo;
import com.google.android.gms.games.internal.player.MostRecentGameInfoEntity;
import com.google.android.gms.internal.je;
import com.google.android.gms.internal.jv;
import com.google.android.gms.internal.le;

/* JADX INFO: loaded from: classes.dex */
public final class PlayerEntity extends GamesDowngradeableSafeParcel implements Player {
    public static final Parcelable.Creator<PlayerEntity> CREATOR = new PlayerEntityCreatorCompat();
    private final int CK;
    private final String OH;
    private final String OS;
    private final Uri WD;
    private final Uri WE;
    private final String WO;
    private final String WP;
    private final String Xh;
    private final long Xi;
    private final int Xj;
    private final long Xk;
    private final MostRecentGameInfoEntity Xl;
    private final PlayerLevelInfo Xm;
    private final boolean Xn;

    static final class PlayerEntityCreatorCompat extends PlayerEntityCreator {
        PlayerEntityCreatorCompat() {
        }

        @Override // com.google.android.gms.games.PlayerEntityCreator, android.os.Parcelable.Creator
        /* JADX INFO: renamed from: cm */
        public PlayerEntity createFromParcel(Parcel parcel) {
            if (PlayerEntity.c(PlayerEntity.ht()) || PlayerEntity.aW(PlayerEntity.class.getCanonicalName())) {
                return super.createFromParcel(parcel);
            }
            String string = parcel.readString();
            String string2 = parcel.readString();
            String string3 = parcel.readString();
            String string4 = parcel.readString();
            return new PlayerEntity(11, string, string2, string3 == null ? null : Uri.parse(string3), string4 == null ? null : Uri.parse(string4), parcel.readLong(), -1, -1L, null, null, null, null, null, true);
        }
    }

    PlayerEntity(int versionCode, String playerId, String displayName, Uri iconImageUri, Uri hiResImageUri, long retrievedTimestamp, int isInCircles, long lastPlayedWithTimestamp, String iconImageUrl, String hiResImageUrl, String title, MostRecentGameInfoEntity mostRecentGameInfo, PlayerLevelInfo playerLevelInfo, boolean isProfileVisible) {
        this.CK = versionCode;
        this.Xh = playerId;
        this.OS = displayName;
        this.WD = iconImageUri;
        this.WO = iconImageUrl;
        this.WE = hiResImageUri;
        this.WP = hiResImageUrl;
        this.Xi = retrievedTimestamp;
        this.Xj = isInCircles;
        this.Xk = lastPlayedWithTimestamp;
        this.OH = title;
        this.Xn = isProfileVisible;
        this.Xl = mostRecentGameInfo;
        this.Xm = playerLevelInfo;
    }

    public PlayerEntity(Player player) {
        this.CK = 11;
        this.Xh = player.getPlayerId();
        this.OS = player.getDisplayName();
        this.WD = player.getIconImageUri();
        this.WO = player.getIconImageUrl();
        this.WE = player.getHiResImageUri();
        this.WP = player.getHiResImageUrl();
        this.Xi = player.getRetrievedTimestamp();
        this.Xj = player.kE();
        this.Xk = player.getLastPlayedWithTimestamp();
        this.OH = player.getTitle();
        this.Xn = player.isProfileVisible();
        MostRecentGameInfo mostRecentGameInfoKF = player.kF();
        this.Xl = mostRecentGameInfoKF == null ? null : new MostRecentGameInfoEntity(mostRecentGameInfoKF);
        this.Xm = player.getLevelInfo();
        je.f(this.Xh);
        je.f(this.OS);
        je.K(this.Xi > 0);
    }

    static boolean a(Player player, Object obj) {
        if (!(obj instanceof Player)) {
            return false;
        }
        if (player == obj) {
            return true;
        }
        Player player2 = (Player) obj;
        return jv.equal(player2.getPlayerId(), player.getPlayerId()) && jv.equal(player2.getDisplayName(), player.getDisplayName()) && jv.equal(player2.getIconImageUri(), player.getIconImageUri()) && jv.equal(player2.getHiResImageUri(), player.getHiResImageUri()) && jv.equal(Long.valueOf(player2.getRetrievedTimestamp()), Long.valueOf(player.getRetrievedTimestamp())) && jv.equal(player2.getTitle(), player.getTitle()) && jv.equal(player2.getLevelInfo(), player.getLevelInfo());
    }

    static int b(Player player) {
        return jv.hashCode(player.getPlayerId(), player.getDisplayName(), player.getIconImageUri(), player.getHiResImageUri(), Long.valueOf(player.getRetrievedTimestamp()), player.getTitle(), player.getLevelInfo());
    }

    static String c(Player player) {
        return jv.h(player).a("PlayerId", player.getPlayerId()).a("DisplayName", player.getDisplayName()).a("IconImageUri", player.getIconImageUri()).a("IconImageUrl", player.getIconImageUrl()).a("HiResImageUri", player.getHiResImageUri()).a("HiResImageUrl", player.getHiResImageUrl()).a("RetrievedTimestamp", Long.valueOf(player.getRetrievedTimestamp())).a("Title", player.getTitle()).a("LevelInfo", player.getLevelInfo()).toString();
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
    public Player freeze() {
        return this;
    }

    @Override // com.google.android.gms.games.Player
    public String getDisplayName() {
        return this.OS;
    }

    @Override // com.google.android.gms.games.Player
    public void getDisplayName(CharArrayBuffer dataOut) {
        le.b(this.OS, dataOut);
    }

    @Override // com.google.android.gms.games.Player
    public Uri getHiResImageUri() {
        return this.WE;
    }

    @Override // com.google.android.gms.games.Player
    public String getHiResImageUrl() {
        return this.WP;
    }

    @Override // com.google.android.gms.games.Player
    public Uri getIconImageUri() {
        return this.WD;
    }

    @Override // com.google.android.gms.games.Player
    public String getIconImageUrl() {
        return this.WO;
    }

    @Override // com.google.android.gms.games.Player
    public long getLastPlayedWithTimestamp() {
        return this.Xk;
    }

    @Override // com.google.android.gms.games.Player
    public PlayerLevelInfo getLevelInfo() {
        return this.Xm;
    }

    @Override // com.google.android.gms.games.Player
    public String getPlayerId() {
        return this.Xh;
    }

    @Override // com.google.android.gms.games.Player
    public long getRetrievedTimestamp() {
        return this.Xi;
    }

    @Override // com.google.android.gms.games.Player
    public String getTitle() {
        return this.OH;
    }

    @Override // com.google.android.gms.games.Player
    public void getTitle(CharArrayBuffer dataOut) {
        le.b(this.OH, dataOut);
    }

    public int getVersionCode() {
        return this.CK;
    }

    @Override // com.google.android.gms.games.Player
    public boolean hasHiResImage() {
        return getHiResImageUri() != null;
    }

    @Override // com.google.android.gms.games.Player
    public boolean hasIconImage() {
        return getIconImageUri() != null;
    }

    public int hashCode() {
        return b(this);
    }

    @Override // com.google.android.gms.common.data.Freezable
    public boolean isDataValid() {
        return true;
    }

    @Override // com.google.android.gms.games.Player
    public boolean isProfileVisible() {
        return this.Xn;
    }

    @Override // com.google.android.gms.games.Player
    public int kE() {
        return this.Xj;
    }

    @Override // com.google.android.gms.games.Player
    public MostRecentGameInfo kF() {
        return this.Xl;
    }

    public String toString() {
        return c(this);
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel dest, int flags) {
        if (!hu()) {
            PlayerEntityCreator.a(this, dest, flags);
            return;
        }
        dest.writeString(this.Xh);
        dest.writeString(this.OS);
        dest.writeString(this.WD == null ? null : this.WD.toString());
        dest.writeString(this.WE != null ? this.WE.toString() : null);
        dest.writeLong(this.Xi);
    }
}
