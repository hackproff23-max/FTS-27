package com.google.android.gms.games.internal.game;

import com.google.android.gms.common.data.DataBuffer;
import com.google.android.gms.common.data.DataHolder;

/* JADX INFO: loaded from: classes.dex */
public final class GameSearchSuggestionBuffer extends DataBuffer<GameSearchSuggestion> {
    public GameSearchSuggestionBuffer(DataHolder dataHolder) {
        super(dataHolder);
    }

    @Override // com.google.android.gms.common.data.DataBuffer
    /* JADX INFO: renamed from: ef, reason: merged with bridge method [inline-methods] */
    public GameSearchSuggestion get(int i) {
        return new GameSearchSuggestionRef(this.JG, i);
    }
}
