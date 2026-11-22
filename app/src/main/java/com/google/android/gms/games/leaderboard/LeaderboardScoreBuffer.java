package com.google.android.gms.games.leaderboard;

import com.google.android.gms.common.data.DataBuffer;
import com.google.android.gms.common.data.DataHolder;

/* JADX INFO: loaded from: classes.dex */
public final class LeaderboardScoreBuffer extends DataBuffer<LeaderboardScore> {
    private final LeaderboardScoreBufferHeader adr;

    public LeaderboardScoreBuffer(DataHolder dataHolder) {
        super(dataHolder);
        this.adr = new LeaderboardScoreBufferHeader(dataHolder.gV());
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.google.android.gms.common.data.DataBuffer
    public LeaderboardScore get(int position) {
        return new LeaderboardScoreRef(this.JG, position);
    }

    public LeaderboardScoreBufferHeader mH() {
        return this.adr;
    }
}
