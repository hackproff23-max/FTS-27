package com.google.android.gms.games.multiplayer.turnbased;

import com.google.android.gms.common.data.DataHolder;
import com.google.android.gms.common.data.g;

/* JADX INFO: loaded from: classes.dex */
public final class TurnBasedMatchBuffer extends g<TurnBasedMatch> {
    public TurnBasedMatchBuffer(DataHolder dataHolder) {
        super(dataHolder);
    }

    @Override // com.google.android.gms.common.data.g
    protected String ha() {
        return "external_match_id";
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.google.android.gms.common.data.g
    /* JADX INFO: renamed from: m, reason: merged with bridge method [inline-methods] */
    public TurnBasedMatch f(int i, int i2) {
        return new TurnBasedMatchRef(this.JG, i, i2);
    }
}
