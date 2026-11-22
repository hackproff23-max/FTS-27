package com.google.android.gms.games.appcontent;

import com.google.android.gms.common.data.DataHolder;
import com.google.android.gms.common.data.g;
import java.util.ArrayList;

/* JADX INFO: loaded from: classes.dex */
public final class AppContentSectionBuffer extends g<AppContentSection> {
    private final ArrayList<DataHolder> XX;

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.google.android.gms.common.data.g
    /* JADX INFO: renamed from: h, reason: merged with bridge method [inline-methods] */
    public AppContentSection f(int i, int i2) {
        return new AppContentSectionRef(this.XX, i, i2);
    }

    @Override // com.google.android.gms.common.data.g
    protected String ha() {
        return "section_id";
    }

    @Override // com.google.android.gms.common.data.g
    protected String hc() {
        return "card_id";
    }

    @Override // com.google.android.gms.common.data.DataBuffer, com.google.android.gms.common.api.Releasable
    public void release() {
        super.release();
        int size = this.XX.size();
        for (int i = 1; i < size; i++) {
            DataHolder dataHolder = this.XX.get(i);
            if (dataHolder != null) {
                dataHolder.close();
            }
        }
    }
}
