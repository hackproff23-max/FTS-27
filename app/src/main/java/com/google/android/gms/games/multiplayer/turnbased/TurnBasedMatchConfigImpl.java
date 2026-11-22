package com.google.android.gms.games.multiplayer.turnbased;

import android.os.Bundle;

/* JADX INFO: loaded from: classes.dex */
public final class TurnBasedMatchConfigImpl extends TurnBasedMatchConfig {
    private final int adW;
    private final Bundle aem;
    private final String[] aeo;
    private final int aew;

    TurnBasedMatchConfigImpl(TurnBasedMatchConfig.Builder builder) {
        this.adW = builder.adW;
        this.aew = builder.aew;
        this.aem = builder.aem;
        this.aeo = (String[]) builder.ael.toArray(new String[builder.ael.size()]);
    }

    @Override // com.google.android.gms.games.multiplayer.turnbased.TurnBasedMatchConfig
    public Bundle getAutoMatchCriteria() {
        return this.aem;
    }

    @Override // com.google.android.gms.games.multiplayer.turnbased.TurnBasedMatchConfig
    public String[] getInvitedPlayerIds() {
        return this.aeo;
    }

    @Override // com.google.android.gms.games.multiplayer.turnbased.TurnBasedMatchConfig
    public int getVariant() {
        return this.adW;
    }

    @Override // com.google.android.gms.games.multiplayer.turnbased.TurnBasedMatchConfig
    public int mO() {
        return this.aew;
    }
}
