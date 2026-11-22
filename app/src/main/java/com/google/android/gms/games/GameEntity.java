package com.google.android.gms.games;

import android.database.CharArrayBuffer;
import android.net.Uri;
import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.games.internal.GamesDowngradeableSafeParcel;
import com.google.android.gms.internal.jv;
import com.google.android.gms.internal.le;

/* JADX INFO: loaded from: classes.dex */
public final class GameEntity extends GamesDowngradeableSafeParcel implements Game {
    public static final Parcelable.Creator<GameEntity> CREATOR = new GameEntityCreatorCompat();
    private final int CK;
    private final String Fo;
    private final String OS;
    private final String UO;
    private final String WA;
    private final String WB;
    private final String WC;
    private final Uri WD;
    private final Uri WE;
    private final Uri WF;
    private final boolean WG;
    private final boolean WH;
    private final String WI;
    private final int WJ;
    private final int WK;
    private final int WL;
    private final boolean WM;
    private final boolean WN;
    private final String WO;
    private final String WP;
    private final String WQ;
    private final boolean WR;
    private final boolean WS;
    private final boolean WT;
    private final String WU;

    static final class GameEntityCreatorCompat extends GameEntityCreator {
        GameEntityCreatorCompat() {
        }

        @Override // com.google.android.gms.games.GameEntityCreator, android.os.Parcelable.Creator
        /* JADX INFO: renamed from: cl */
        public GameEntity createFromParcel(Parcel parcel) {
            if (GameEntity.c(GameEntity.ht()) || GameEntity.aW(GameEntity.class.getCanonicalName())) {
                return super.createFromParcel(parcel);
            }
            String string = parcel.readString();
            String string2 = parcel.readString();
            String string3 = parcel.readString();
            String string4 = parcel.readString();
            String string5 = parcel.readString();
            String string6 = parcel.readString();
            String string7 = parcel.readString();
            Uri uri = string7 == null ? null : Uri.parse(string7);
            String string8 = parcel.readString();
            Uri uri2 = string8 == null ? null : Uri.parse(string8);
            String string9 = parcel.readString();
            return new GameEntity(5, string, string2, string3, string4, string5, string6, uri, uri2, string9 == null ? null : Uri.parse(string9), parcel.readInt() > 0, parcel.readInt() > 0, parcel.readString(), parcel.readInt(), parcel.readInt(), parcel.readInt(), false, false, null, null, null, false, false, false, null);
        }
    }

    GameEntity(int versionCode, String applicationId, String displayName, String primaryCategory, String secondaryCategory, String description, String developerName, Uri iconImageUri, Uri hiResImageUri, Uri featuredImageUri, boolean playEnabledGame, boolean instanceInstalled, String instancePackageName, int gameplayAclStatus, int achievementTotalCount, int leaderboardCount, boolean realTimeEnabled, boolean turnBasedEnabled, String iconImageUrl, String hiResImageUrl, String featuredImageUrl, boolean muted, boolean identitySharingConfirmed, boolean snapshotsEnabled, String themeColor) {
        this.CK = versionCode;
        this.Fo = applicationId;
        this.OS = displayName;
        this.WA = primaryCategory;
        this.WB = secondaryCategory;
        this.UO = description;
        this.WC = developerName;
        this.WD = iconImageUri;
        this.WO = iconImageUrl;
        this.WE = hiResImageUri;
        this.WP = hiResImageUrl;
        this.WF = featuredImageUri;
        this.WQ = featuredImageUrl;
        this.WG = playEnabledGame;
        this.WH = instanceInstalled;
        this.WI = instancePackageName;
        this.WJ = gameplayAclStatus;
        this.WK = achievementTotalCount;
        this.WL = leaderboardCount;
        this.WM = realTimeEnabled;
        this.WN = turnBasedEnabled;
        this.WR = muted;
        this.WS = identitySharingConfirmed;
        this.WT = snapshotsEnabled;
        this.WU = themeColor;
    }

    public GameEntity(Game game) {
        this.CK = 5;
        this.Fo = game.getApplicationId();
        this.WA = game.getPrimaryCategory();
        this.WB = game.getSecondaryCategory();
        this.UO = game.getDescription();
        this.WC = game.getDeveloperName();
        this.OS = game.getDisplayName();
        this.WD = game.getIconImageUri();
        this.WO = game.getIconImageUrl();
        this.WE = game.getHiResImageUri();
        this.WP = game.getHiResImageUrl();
        this.WF = game.getFeaturedImageUri();
        this.WQ = game.getFeaturedImageUrl();
        this.WG = game.ky();
        this.WH = game.kA();
        this.WI = game.kB();
        this.WJ = game.kC();
        this.WK = game.getAchievementTotalCount();
        this.WL = game.getLeaderboardCount();
        this.WM = game.isRealTimeMultiplayerEnabled();
        this.WN = game.isTurnBasedMultiplayerEnabled();
        this.WR = game.isMuted();
        this.WS = game.kz();
        this.WT = game.areSnapshotsEnabled();
        this.WU = game.getThemeColor();
    }

    static int a(Game game) {
        return jv.hashCode(game.getApplicationId(), game.getDisplayName(), game.getPrimaryCategory(), game.getSecondaryCategory(), game.getDescription(), game.getDeveloperName(), game.getIconImageUri(), game.getHiResImageUri(), game.getFeaturedImageUri(), Boolean.valueOf(game.ky()), Boolean.valueOf(game.kA()), game.kB(), Integer.valueOf(game.kC()), Integer.valueOf(game.getAchievementTotalCount()), Integer.valueOf(game.getLeaderboardCount()), Boolean.valueOf(game.isRealTimeMultiplayerEnabled()), Boolean.valueOf(game.isTurnBasedMultiplayerEnabled()), Boolean.valueOf(game.isMuted()), Boolean.valueOf(game.kz()), Boolean.valueOf(game.areSnapshotsEnabled()), game.getThemeColor());
    }

    static boolean a(Game game, Object obj) {
        if (!(obj instanceof Game)) {
            return false;
        }
        if (game == obj) {
            return true;
        }
        Game game2 = (Game) obj;
        if (jv.equal(game2.getApplicationId(), game.getApplicationId()) && jv.equal(game2.getDisplayName(), game.getDisplayName()) && jv.equal(game2.getPrimaryCategory(), game.getPrimaryCategory()) && jv.equal(game2.getSecondaryCategory(), game.getSecondaryCategory()) && jv.equal(game2.getDescription(), game.getDescription()) && jv.equal(game2.getDeveloperName(), game.getDeveloperName()) && jv.equal(game2.getIconImageUri(), game.getIconImageUri()) && jv.equal(game2.getHiResImageUri(), game.getHiResImageUri()) && jv.equal(game2.getFeaturedImageUri(), game.getFeaturedImageUri()) && jv.equal(Boolean.valueOf(game2.ky()), Boolean.valueOf(game.ky())) && jv.equal(Boolean.valueOf(game2.kA()), Boolean.valueOf(game.kA())) && jv.equal(game2.kB(), game.kB()) && jv.equal(Integer.valueOf(game2.kC()), Integer.valueOf(game.kC())) && jv.equal(Integer.valueOf(game2.getAchievementTotalCount()), Integer.valueOf(game.getAchievementTotalCount())) && jv.equal(Integer.valueOf(game2.getLeaderboardCount()), Integer.valueOf(game.getLeaderboardCount())) && jv.equal(Boolean.valueOf(game2.isRealTimeMultiplayerEnabled()), Boolean.valueOf(game.isRealTimeMultiplayerEnabled()))) {
            if (jv.equal(Boolean.valueOf(game2.isTurnBasedMultiplayerEnabled()), Boolean.valueOf(game.isTurnBasedMultiplayerEnabled() && jv.equal(Boolean.valueOf(game2.isMuted()), Boolean.valueOf(game.isMuted())) && jv.equal(Boolean.valueOf(game2.kz()), Boolean.valueOf(game.kz())))) && jv.equal(Boolean.valueOf(game2.areSnapshotsEnabled()), Boolean.valueOf(game.areSnapshotsEnabled())) && jv.equal(game2.getThemeColor(), game.getThemeColor())) {
                return true;
            }
        }
        return false;
    }

    static String b(Game game) {
        return jv.h(game).a("ApplicationId", game.getApplicationId()).a("DisplayName", game.getDisplayName()).a("PrimaryCategory", game.getPrimaryCategory()).a("SecondaryCategory", game.getSecondaryCategory()).a("Description", game.getDescription()).a("DeveloperName", game.getDeveloperName()).a("IconImageUri", game.getIconImageUri()).a("IconImageUrl", game.getIconImageUrl()).a("HiResImageUri", game.getHiResImageUri()).a("HiResImageUrl", game.getHiResImageUrl()).a("FeaturedImageUri", game.getFeaturedImageUri()).a("FeaturedImageUrl", game.getFeaturedImageUrl()).a("PlayEnabledGame", Boolean.valueOf(game.ky())).a("InstanceInstalled", Boolean.valueOf(game.kA())).a("InstancePackageName", game.kB()).a("AchievementTotalCount", Integer.valueOf(game.getAchievementTotalCount())).a("LeaderboardCount", Integer.valueOf(game.getLeaderboardCount())).a("RealTimeMultiplayerEnabled", Boolean.valueOf(game.isRealTimeMultiplayerEnabled())).a("TurnBasedMultiplayerEnabled", Boolean.valueOf(game.isTurnBasedMultiplayerEnabled())).a("AreSnapshotsEnabled", Boolean.valueOf(game.areSnapshotsEnabled())).a("ThemeColor", game.getThemeColor()).toString();
    }

    @Override // com.google.android.gms.games.Game
    public boolean areSnapshotsEnabled() {
        return this.WT;
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
    public Game freeze() {
        return this;
    }

    @Override // com.google.android.gms.games.Game
    public int getAchievementTotalCount() {
        return this.WK;
    }

    @Override // com.google.android.gms.games.Game
    public String getApplicationId() {
        return this.Fo;
    }

    @Override // com.google.android.gms.games.Game
    public String getDescription() {
        return this.UO;
    }

    @Override // com.google.android.gms.games.Game
    public void getDescription(CharArrayBuffer dataOut) {
        le.b(this.UO, dataOut);
    }

    @Override // com.google.android.gms.games.Game
    public String getDeveloperName() {
        return this.WC;
    }

    @Override // com.google.android.gms.games.Game
    public void getDeveloperName(CharArrayBuffer dataOut) {
        le.b(this.WC, dataOut);
    }

    @Override // com.google.android.gms.games.Game
    public String getDisplayName() {
        return this.OS;
    }

    @Override // com.google.android.gms.games.Game
    public void getDisplayName(CharArrayBuffer dataOut) {
        le.b(this.OS, dataOut);
    }

    @Override // com.google.android.gms.games.Game
    public Uri getFeaturedImageUri() {
        return this.WF;
    }

    @Override // com.google.android.gms.games.Game
    public String getFeaturedImageUrl() {
        return this.WQ;
    }

    @Override // com.google.android.gms.games.Game
    public Uri getHiResImageUri() {
        return this.WE;
    }

    @Override // com.google.android.gms.games.Game
    public String getHiResImageUrl() {
        return this.WP;
    }

    @Override // com.google.android.gms.games.Game
    public Uri getIconImageUri() {
        return this.WD;
    }

    @Override // com.google.android.gms.games.Game
    public String getIconImageUrl() {
        return this.WO;
    }

    @Override // com.google.android.gms.games.Game
    public int getLeaderboardCount() {
        return this.WL;
    }

    @Override // com.google.android.gms.games.Game
    public String getPrimaryCategory() {
        return this.WA;
    }

    @Override // com.google.android.gms.games.Game
    public String getSecondaryCategory() {
        return this.WB;
    }

    @Override // com.google.android.gms.games.Game
    public String getThemeColor() {
        return this.WU;
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

    @Override // com.google.android.gms.games.Game
    public boolean isMuted() {
        return this.WR;
    }

    @Override // com.google.android.gms.games.Game
    public boolean isRealTimeMultiplayerEnabled() {
        return this.WM;
    }

    @Override // com.google.android.gms.games.Game
    public boolean isTurnBasedMultiplayerEnabled() {
        return this.WN;
    }

    @Override // com.google.android.gms.games.Game
    public boolean kA() {
        return this.WH;
    }

    @Override // com.google.android.gms.games.Game
    public String kB() {
        return this.WI;
    }

    @Override // com.google.android.gms.games.Game
    public int kC() {
        return this.WJ;
    }

    @Override // com.google.android.gms.games.Game
    public boolean ky() {
        return this.WG;
    }

    @Override // com.google.android.gms.games.Game
    public boolean kz() {
        return this.WS;
    }

    public String toString() {
        return b(this);
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel dest, int flags) {
        if (!hu()) {
            GameEntityCreator.a(this, dest, flags);
            return;
        }
        dest.writeString(this.Fo);
        dest.writeString(this.OS);
        dest.writeString(this.WA);
        dest.writeString(this.WB);
        dest.writeString(this.UO);
        dest.writeString(this.WC);
        dest.writeString(this.WD == null ? null : this.WD.toString());
        dest.writeString(this.WE == null ? null : this.WE.toString());
        dest.writeString(this.WF != null ? this.WF.toString() : null);
        dest.writeInt(this.WG ? 1 : 0);
        dest.writeInt(this.WH ? 1 : 0);
        dest.writeString(this.WI);
        dest.writeInt(this.WJ);
        dest.writeInt(this.WK);
        dest.writeInt(this.WL);
    }
}
