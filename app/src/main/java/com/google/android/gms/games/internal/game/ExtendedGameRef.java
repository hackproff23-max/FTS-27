package com.google.android.gms.games.internal.game;

import android.os.Parcel;
import com.google.android.gms.common.data.DataHolder;
import com.google.android.gms.common.data.d;
import com.google.android.gms.games.Game;
import com.google.android.gms.games.GameRef;
import com.google.android.gms.games.snapshot.SnapshotMetadata;
import com.google.android.gms.games.snapshot.SnapshotMetadataRef;
import java.util.ArrayList;

/* JADX INFO: loaded from: classes.dex */
public class ExtendedGameRef extends d implements ExtendedGame {
    private final int Ya;
    private final SnapshotMetadataRef acD;
    private final GameRef acr;

    ExtendedGameRef(DataHolder holder, int dataRow, int numChildren) {
        super(holder, dataRow);
        this.acr = new GameRef(holder, dataRow);
        this.Ya = numChildren;
        if (!aQ("external_snapshot_id") || aS("external_snapshot_id")) {
            this.acD = null;
        } else {
            this.acD = new SnapshotMetadataRef(holder, dataRow);
        }
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    @Override // com.google.android.gms.common.data.d
    public boolean equals(Object obj) {
        return ExtendedGameEntity.a(this, obj);
    }

    @Override // com.google.android.gms.games.internal.game.ExtendedGame
    public Game getGame() {
        return this.acr;
    }

    @Override // com.google.android.gms.common.data.d
    public int hashCode() {
        return ExtendedGameEntity.a(this);
    }

    @Override // com.google.android.gms.games.internal.game.ExtendedGame
    public ArrayList<GameBadge> lY() {
        if (this.JG.c("badge_title", this.KZ, this.JG.au(this.KZ)) == null) {
            return new ArrayList<>(0);
        }
        ArrayList<GameBadge> arrayList = new ArrayList<>(this.Ya);
        for (int i = 0; i < this.Ya; i++) {
            arrayList.add(new GameBadgeRef(this.JG, this.KZ + i));
        }
        return arrayList;
    }

    @Override // com.google.android.gms.games.internal.game.ExtendedGame
    public int lZ() {
        return getInteger("availability");
    }

    @Override // com.google.android.gms.games.internal.game.ExtendedGame
    public boolean ma() {
        return getBoolean("owned");
    }

    @Override // com.google.android.gms.games.internal.game.ExtendedGame
    public int mb() {
        return getInteger("achievement_unlocked_count");
    }

    @Override // com.google.android.gms.games.internal.game.ExtendedGame
    public long mc() {
        return getLong("last_played_server_time");
    }

    @Override // com.google.android.gms.games.internal.game.ExtendedGame
    public long md() {
        return getLong("price_micros");
    }

    @Override // com.google.android.gms.games.internal.game.ExtendedGame
    public String me() {
        return getString("formatted_price");
    }

    @Override // com.google.android.gms.games.internal.game.ExtendedGame
    public long mf() {
        return getLong("full_price_micros");
    }

    @Override // com.google.android.gms.games.internal.game.ExtendedGame
    public String mg() {
        return getString("formatted_full_price");
    }

    @Override // com.google.android.gms.games.internal.game.ExtendedGame
    public SnapshotMetadata mh() {
        return this.acD;
    }

    @Override // com.google.android.gms.common.data.Freezable
    /* JADX INFO: renamed from: mj, reason: merged with bridge method [inline-methods] */
    public ExtendedGame freeze() {
        return new ExtendedGameEntity(this);
    }

    public String toString() {
        return ExtendedGameEntity.b(this);
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel dest, int flags) {
        ((ExtendedGameEntity) freeze()).writeToParcel(dest, flags);
    }
}
