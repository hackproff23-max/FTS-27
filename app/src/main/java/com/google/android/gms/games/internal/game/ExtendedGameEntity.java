package com.google.android.gms.games.internal.game;

import android.os.Parcel;
import com.google.android.gms.games.Game;
import com.google.android.gms.games.GameEntity;
import com.google.android.gms.games.internal.GamesDowngradeableSafeParcel;
import com.google.android.gms.games.snapshot.SnapshotMetadata;
import com.google.android.gms.games.snapshot.SnapshotMetadataEntity;
import com.google.android.gms.internal.jv;
import java.util.ArrayList;

/* JADX INFO: loaded from: classes.dex */
public final class ExtendedGameEntity extends GamesDowngradeableSafeParcel implements ExtendedGame {
    public static final ExtendedGameEntityCreator CREATOR = new ExtendedGameEntityCreatorCompat();
    private final int CK;
    private final String acA;
    private final ArrayList<GameBadgeEntity> acB;
    private final SnapshotMetadataEntity acC;
    private final GameEntity acs;
    private final int act;
    private final boolean acu;
    private final int acv;
    private final long acw;
    private final long acx;
    private final String acy;
    private final long acz;

    static final class ExtendedGameEntityCreatorCompat extends ExtendedGameEntityCreator {
        ExtendedGameEntityCreatorCompat() {
        }

        @Override // com.google.android.gms.games.internal.game.ExtendedGameEntityCreator, android.os.Parcelable.Creator
        /* JADX INFO: renamed from: cu, reason: merged with bridge method [inline-methods] */
        public ExtendedGameEntity createFromParcel(Parcel parcel) {
            if (ExtendedGameEntity.c(ExtendedGameEntity.ht()) || ExtendedGameEntity.aW(ExtendedGameEntity.class.getCanonicalName())) {
                return super.createFromParcel(parcel);
            }
            GameEntity gameEntityCreateFromParcel = GameEntity.CREATOR.createFromParcel(parcel);
            int i = parcel.readInt();
            boolean z = parcel.readInt() == 1;
            int i2 = parcel.readInt();
            long j = parcel.readLong();
            long j2 = parcel.readLong();
            String string = parcel.readString();
            long j3 = parcel.readLong();
            String string2 = parcel.readString();
            int i3 = parcel.readInt();
            ArrayList arrayList = new ArrayList(i3);
            for (int i4 = 0; i4 < i3; i4++) {
                arrayList.add(GameBadgeEntity.CREATOR.createFromParcel(parcel));
            }
            return new ExtendedGameEntity(2, gameEntityCreateFromParcel, i, z, i2, j, j2, string, j3, string2, arrayList, null);
        }
    }

    ExtendedGameEntity(int versionCode, GameEntity game, int availability, boolean owned, int achievementUnlockedCount, long lastPlayedServerTimestamp, long priceMicros, String formattedPrice, long fullPriceMicros, String formattedFullPrice, ArrayList<GameBadgeEntity> badges, SnapshotMetadataEntity snapshot) {
        this.CK = versionCode;
        this.acs = game;
        this.act = availability;
        this.acu = owned;
        this.acv = achievementUnlockedCount;
        this.acw = lastPlayedServerTimestamp;
        this.acx = priceMicros;
        this.acy = formattedPrice;
        this.acz = fullPriceMicros;
        this.acA = formattedFullPrice;
        this.acB = badges;
        this.acC = snapshot;
    }

    public ExtendedGameEntity(ExtendedGame extendedGame) {
        this.CK = 2;
        Game game = extendedGame.getGame();
        this.acs = game == null ? null : new GameEntity(game);
        this.act = extendedGame.lZ();
        this.acu = extendedGame.ma();
        this.acv = extendedGame.mb();
        this.acw = extendedGame.mc();
        this.acx = extendedGame.md();
        this.acy = extendedGame.me();
        this.acz = extendedGame.mf();
        this.acA = extendedGame.mg();
        SnapshotMetadata snapshotMetadataMh = extendedGame.mh();
        this.acC = snapshotMetadataMh != null ? new SnapshotMetadataEntity(snapshotMetadataMh) : null;
        ArrayList<GameBadge> arrayListLY = extendedGame.lY();
        int size = arrayListLY.size();
        this.acB = new ArrayList<>(size);
        for (int i = 0; i < size; i++) {
            this.acB.add((GameBadgeEntity) arrayListLY.get(i).freeze());
        }
    }

    static int a(ExtendedGame extendedGame) {
        return jv.hashCode(extendedGame.getGame(), Integer.valueOf(extendedGame.lZ()), Boolean.valueOf(extendedGame.ma()), Integer.valueOf(extendedGame.mb()), Long.valueOf(extendedGame.mc()), Long.valueOf(extendedGame.md()), extendedGame.me(), Long.valueOf(extendedGame.mf()), extendedGame.mg());
    }

    static boolean a(ExtendedGame extendedGame, Object obj) {
        if (!(obj instanceof ExtendedGame)) {
            return false;
        }
        if (extendedGame == obj) {
            return true;
        }
        ExtendedGame extendedGame2 = (ExtendedGame) obj;
        return jv.equal(extendedGame2.getGame(), extendedGame.getGame()) && jv.equal(Integer.valueOf(extendedGame2.lZ()), Integer.valueOf(extendedGame.lZ())) && jv.equal(Boolean.valueOf(extendedGame2.ma()), Boolean.valueOf(extendedGame.ma())) && jv.equal(Integer.valueOf(extendedGame2.mb()), Integer.valueOf(extendedGame.mb())) && jv.equal(Long.valueOf(extendedGame2.mc()), Long.valueOf(extendedGame.mc())) && jv.equal(Long.valueOf(extendedGame2.md()), Long.valueOf(extendedGame.md())) && jv.equal(extendedGame2.me(), extendedGame.me()) && jv.equal(Long.valueOf(extendedGame2.mf()), Long.valueOf(extendedGame.mf())) && jv.equal(extendedGame2.mg(), extendedGame.mg());
    }

    static String b(ExtendedGame extendedGame) {
        return jv.h(extendedGame).a("Game", extendedGame.getGame()).a("Availability", Integer.valueOf(extendedGame.lZ())).a("Owned", Boolean.valueOf(extendedGame.ma())).a("AchievementUnlockedCount", Integer.valueOf(extendedGame.mb())).a("LastPlayedServerTimestamp", Long.valueOf(extendedGame.mc())).a("PriceMicros", Long.valueOf(extendedGame.md())).a("FormattedPrice", extendedGame.me()).a("FullPriceMicros", Long.valueOf(extendedGame.mf())).a("FormattedFullPrice", extendedGame.mg()).a("Snapshot", extendedGame.mh()).toString();
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public boolean equals(Object obj) {
        return a(this, obj);
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

    @Override // com.google.android.gms.games.internal.game.ExtendedGame
    public ArrayList<GameBadge> lY() {
        return new ArrayList<>(this.acB);
    }

    @Override // com.google.android.gms.games.internal.game.ExtendedGame
    public int lZ() {
        return this.act;
    }

    @Override // com.google.android.gms.games.internal.game.ExtendedGame
    public boolean ma() {
        return this.acu;
    }

    @Override // com.google.android.gms.games.internal.game.ExtendedGame
    public int mb() {
        return this.acv;
    }

    @Override // com.google.android.gms.games.internal.game.ExtendedGame
    public long mc() {
        return this.acw;
    }

    @Override // com.google.android.gms.games.internal.game.ExtendedGame
    public long md() {
        return this.acx;
    }

    @Override // com.google.android.gms.games.internal.game.ExtendedGame
    public String me() {
        return this.acy;
    }

    @Override // com.google.android.gms.games.internal.game.ExtendedGame
    public long mf() {
        return this.acz;
    }

    @Override // com.google.android.gms.games.internal.game.ExtendedGame
    public String mg() {
        return this.acA;
    }

    @Override // com.google.android.gms.games.internal.game.ExtendedGame
    public SnapshotMetadata mh() {
        return this.acC;
    }

    @Override // com.google.android.gms.games.internal.game.ExtendedGame
    /* JADX INFO: renamed from: mi, reason: merged with bridge method [inline-methods] */
    public GameEntity getGame() {
        return this.acs;
    }

    @Override // com.google.android.gms.common.data.Freezable
    /* JADX INFO: renamed from: mj, reason: merged with bridge method [inline-methods] */
    public ExtendedGame freeze() {
        return this;
    }

    public String toString() {
        return b(this);
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel dest, int flags) {
        if (!hu()) {
            ExtendedGameEntityCreator.a(this, dest, flags);
            return;
        }
        this.acs.writeToParcel(dest, flags);
        dest.writeInt(this.act);
        dest.writeInt(this.acu ? 1 : 0);
        dest.writeInt(this.acv);
        dest.writeLong(this.acw);
        dest.writeLong(this.acx);
        dest.writeString(this.acy);
        dest.writeLong(this.acz);
        dest.writeString(this.acA);
        int size = this.acB.size();
        dest.writeInt(size);
        for (int i = 0; i < size; i++) {
            this.acB.get(i).writeToParcel(dest, flags);
        }
    }
}
