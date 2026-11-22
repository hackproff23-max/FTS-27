package com.google.android.gms.games.leaderboard;

import android.os.Bundle;

/* JADX INFO: loaded from: classes.dex */
public final class LeaderboardScoreBufferHeader {
    private final Bundle DJ;

    public static final class Builder {
        private Builder() {
        }
    }

    public LeaderboardScoreBufferHeader(Bundle bundle) {
        this.DJ = bundle == null ? new Bundle() : bundle;
    }

    public Bundle mI() {
        return this.DJ;
    }
}
