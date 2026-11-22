package com.google.android.gms.games.internal.player;

import android.net.Uri;
import android.os.Parcel;
import com.google.android.gms.common.data.DataHolder;
import com.google.android.gms.common.data.d;

/* JADX INFO: loaded from: classes.dex */
public final class MostRecentGameInfoRef extends d implements MostRecentGameInfo {
    private final PlayerColumnNames Xv;

    public MostRecentGameInfoRef(DataHolder holder, int dataRow, PlayerColumnNames columnNames) {
        super(holder, dataRow);
        this.Xv = columnNames;
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    @Override // com.google.android.gms.common.data.d
    public boolean equals(Object obj) {
        return MostRecentGameInfoEntity.a(this, obj);
    }

    @Override // com.google.android.gms.common.data.d
    public int hashCode() {
        return MostRecentGameInfoEntity.a(this);
    }

    @Override // com.google.android.gms.games.internal.player.MostRecentGameInfo
    public Uri mA() {
        return aR(this.Xv.adi);
    }

    @Override // com.google.android.gms.games.internal.player.MostRecentGameInfo
    public Uri mB() {
        return aR(this.Xv.adj);
    }

    @Override // com.google.android.gms.common.data.Freezable
    /* JADX INFO: renamed from: mC, reason: merged with bridge method [inline-methods] */
    public MostRecentGameInfo freeze() {
        return new MostRecentGameInfoEntity(this);
    }

    @Override // com.google.android.gms.games.internal.player.MostRecentGameInfo
    public String mw() {
        return getString(this.Xv.ade);
    }

    @Override // com.google.android.gms.games.internal.player.MostRecentGameInfo
    public String mx() {
        return getString(this.Xv.adf);
    }

    @Override // com.google.android.gms.games.internal.player.MostRecentGameInfo
    public long my() {
        return getLong(this.Xv.adg);
    }

    @Override // com.google.android.gms.games.internal.player.MostRecentGameInfo
    public Uri mz() {
        return aR(this.Xv.adh);
    }

    public String toString() {
        return MostRecentGameInfoEntity.b(this);
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel dest, int flags) {
        ((MostRecentGameInfoEntity) freeze()).writeToParcel(dest, flags);
    }
}
