package com.google.android.gms.games.internal.game;

import com.google.android.gms.common.data.DataBuffer;

/* JADX INFO: loaded from: classes.dex */
public final class GameBadgeBuffer extends DataBuffer<GameBadge> {
    @Override // com.google.android.gms.common.data.DataBuffer
    /* JADX INFO: renamed from: ec, reason: merged with bridge method [inline-methods] */
    public GameBadge get(int i) {
        return new GameBadgeRef(this.JG, i);
    }
}
