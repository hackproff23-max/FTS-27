package com.google.android.gms.games.internal.game;

import com.google.android.gms.common.data.DataHolder;
import com.google.android.gms.common.data.g;

/* JADX INFO: loaded from: classes.dex */
public final class ExtendedGameBuffer extends g<ExtendedGame> {
    public ExtendedGameBuffer(DataHolder dataHolder) {
        super(dataHolder);
    }

    @Override // com.google.android.gms.common.data.g
    protected String ha() {
        return "external_game_id";
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.google.android.gms.common.data.g
    /* JADX INFO: renamed from: i, reason: merged with bridge method [inline-methods] */
    public ExtendedGame f(int i, int i2) {
        return new ExtendedGameRef(this.JG, i, i2);
    }
}
