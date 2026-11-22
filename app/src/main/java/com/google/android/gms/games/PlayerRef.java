package com.google.android.gms.games;

import android.database.CharArrayBuffer;
import android.net.Uri;
import android.os.Parcel;
import com.google.android.gms.common.data.DataHolder;
import com.google.android.gms.common.data.d;
import com.google.android.gms.games.internal.player.MostRecentGameInfo;
import com.google.android.gms.games.internal.player.MostRecentGameInfoRef;
import com.google.android.gms.games.internal.player.PlayerColumnNames;

/* JADX INFO: loaded from: classes.dex */
public final class PlayerRef extends d implements Player {
    private final PlayerLevelInfo Xm;
    private final PlayerColumnNames Xv;
    private final MostRecentGameInfoRef Xw;

    public PlayerRef(DataHolder holder, int dataRow) {
        this(holder, dataRow, null);
    }

    public PlayerRef(DataHolder holder, int dataRow, String prefix) {
        super(holder, dataRow);
        this.Xv = new PlayerColumnNames(prefix);
        this.Xw = new MostRecentGameInfoRef(holder, dataRow, this.Xv);
        if (!kG()) {
            this.Xm = null;
            return;
        }
        int integer = getInteger(this.Xv.acV);
        int integer2 = getInteger(this.Xv.acY);
        PlayerLevel playerLevel = new PlayerLevel(integer, getLong(this.Xv.acW), getLong(this.Xv.acX));
        this.Xm = new PlayerLevelInfo(getLong(this.Xv.acU), getLong(this.Xv.ada), playerLevel, integer != integer2 ? new PlayerLevel(integer2, getLong(this.Xv.acX), getLong(this.Xv.acZ)) : playerLevel);
    }

    private boolean kG() {
        return (aS(this.Xv.acU) || getLong(this.Xv.acU) == -1) ? false : true;
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    @Override // com.google.android.gms.common.data.d
    public boolean equals(Object obj) {
        return PlayerEntity.a(this, obj);
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.google.android.gms.common.data.Freezable
    public Player freeze() {
        return new PlayerEntity(this);
    }

    @Override // com.google.android.gms.games.Player
    public String getDisplayName() {
        return getString(this.Xv.acM);
    }

    @Override // com.google.android.gms.games.Player
    public void getDisplayName(CharArrayBuffer dataOut) {
        a(this.Xv.acM, dataOut);
    }

    @Override // com.google.android.gms.games.Player
    public Uri getHiResImageUri() {
        return aR(this.Xv.acP);
    }

    @Override // com.google.android.gms.games.Player
    public String getHiResImageUrl() {
        return getString(this.Xv.acQ);
    }

    @Override // com.google.android.gms.games.Player
    public Uri getIconImageUri() {
        return aR(this.Xv.acN);
    }

    @Override // com.google.android.gms.games.Player
    public String getIconImageUrl() {
        return getString(this.Xv.acO);
    }

    @Override // com.google.android.gms.games.Player
    public long getLastPlayedWithTimestamp() {
        if (!aQ(this.Xv.acT) || aS(this.Xv.acT)) {
            return -1L;
        }
        return getLong(this.Xv.acT);
    }

    @Override // com.google.android.gms.games.Player
    public PlayerLevelInfo getLevelInfo() {
        return this.Xm;
    }

    @Override // com.google.android.gms.games.Player
    public String getPlayerId() {
        return getString(this.Xv.acL);
    }

    @Override // com.google.android.gms.games.Player
    public long getRetrievedTimestamp() {
        return getLong(this.Xv.acR);
    }

    @Override // com.google.android.gms.games.Player
    public String getTitle() {
        return getString(this.Xv.adb);
    }

    @Override // com.google.android.gms.games.Player
    public void getTitle(CharArrayBuffer dataOut) {
        a(this.Xv.adb, dataOut);
    }

    @Override // com.google.android.gms.games.Player
    public boolean hasHiResImage() {
        return getHiResImageUri() != null;
    }

    @Override // com.google.android.gms.games.Player
    public boolean hasIconImage() {
        return getIconImageUri() != null;
    }

    @Override // com.google.android.gms.common.data.d
    public int hashCode() {
        return PlayerEntity.b(this);
    }

    @Override // com.google.android.gms.games.Player
    public boolean isProfileVisible() {
        return getBoolean(this.Xv.add);
    }

    @Override // com.google.android.gms.games.Player
    public int kE() {
        return getInteger(this.Xv.acS);
    }

    @Override // com.google.android.gms.games.Player
    public MostRecentGameInfo kF() {
        if (aS(this.Xv.ade)) {
            return null;
        }
        return this.Xw;
    }

    public String toString() {
        return PlayerEntity.c(this);
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel dest, int flags) {
        ((PlayerEntity) freeze()).writeToParcel(dest, flags);
    }
}
