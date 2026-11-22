package com.google.android.gms.games.leaderboard;

import com.google.android.gms.common.data.DataHolder;
import com.google.android.gms.common.data.g;

/* JADX INFO: loaded from: classes.dex */
public final class LeaderboardBuffer extends g<Leaderboard> {
    public LeaderboardBuffer(DataHolder dataHolder) {
        super(dataHolder);
    }

    @Override // com.google.android.gms.common.data.g
    protected String ha() {
        return "external_leaderboard_id";
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.google.android.gms.common.data.g
    /* JADX INFO: renamed from: j, reason: merged with bridge method [inline-methods] */
    public Leaderboard f(int i, int i2) {
        return new LeaderboardRef(this.JG, i, i2);
    }
}
