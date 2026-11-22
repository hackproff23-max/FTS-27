package com.google.android.gms.drive.metadata.internal;

import android.os.Bundle;
import com.google.android.gms.common.data.DataHolder;
import java.util.Date;

/* JADX INFO: loaded from: classes.dex */
public class d extends com.google.android.gms.drive.metadata.d<Date> {
    public d(String str, int i) {
        super(str, i);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.google.android.gms.drive.metadata.a
    public void a(Bundle bundle, Date date) {
        bundle.putLong(getName(), date.getTime());
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.google.android.gms.drive.metadata.a
    /* JADX INFO: renamed from: f, reason: merged with bridge method [inline-methods] */
    public Date c(DataHolder dataHolder, int i, int i2) {
        return new Date(dataHolder.a(getName(), i, i2));
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.google.android.gms.drive.metadata.a
    /* JADX INFO: renamed from: k, reason: merged with bridge method [inline-methods] */
    public Date i(Bundle bundle) {
        return new Date(bundle.getLong(getName()));
    }
}
