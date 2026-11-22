package com.google.android.gms.games.request;

import com.google.android.gms.common.data.DataHolder;
import com.google.android.gms.common.data.g;

/* JADX INFO: loaded from: classes.dex */
public final class GameRequestBuffer extends g<GameRequest> {
    public GameRequestBuffer(DataHolder dataHolder) {
        super(dataHolder);
    }

    @Override // com.google.android.gms.common.data.g
    protected String ha() {
        return "external_request_id";
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.google.android.gms.common.data.g
    /* JADX INFO: renamed from: o, reason: merged with bridge method [inline-methods] */
    public GameRequest f(int i, int i2) {
        return new GameRequestRef(this.JG, i, i2);
    }
}
