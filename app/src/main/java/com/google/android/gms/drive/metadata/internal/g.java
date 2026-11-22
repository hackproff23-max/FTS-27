package com.google.android.gms.drive.metadata.internal;

import android.os.Bundle;
import com.google.android.gms.common.data.DataHolder;

/* JADX INFO: loaded from: classes.dex */
public class g extends com.google.android.gms.drive.metadata.a<Long> {
    public g(String str, int i) {
        super(str, i);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.google.android.gms.drive.metadata.a
    public void a(Bundle bundle, Long l) {
        bundle.putLong(getName(), l.longValue());
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.google.android.gms.drive.metadata.a
    /* JADX INFO: renamed from: h, reason: merged with bridge method [inline-methods] */
    public Long c(DataHolder dataHolder, int i, int i2) {
        return Long.valueOf(dataHolder.a(getName(), i, i2));
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.google.android.gms.drive.metadata.a
    /* JADX INFO: renamed from: m, reason: merged with bridge method [inline-methods] */
    public Long i(Bundle bundle) {
        return Long.valueOf(bundle.getLong(getName()));
    }
}
