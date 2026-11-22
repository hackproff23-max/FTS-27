package com.google.android.gms.common.api;

import com.google.android.gms.common.data.DataHolder;

/* JADX INFO: loaded from: classes.dex */
public abstract class a<L> implements d.b<L> {
    private final DataHolder JG;

    protected a(DataHolder dataHolder) {
        this.JG = dataHolder;
    }

    protected abstract void a(L l, DataHolder dataHolder);

    @Override // com.google.android.gms.common.api.d.b
    public final void c(L l) {
        a(l, this.JG);
    }

    @Override // com.google.android.gms.common.api.d.b
    public void gG() {
        if (this.JG != null) {
            this.JG.close();
        }
    }
}
