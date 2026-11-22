package com.google.android.gms.drive.metadata.internal;

import android.os.Bundle;
import com.google.android.gms.common.data.DataHolder;

/* JADX INFO: loaded from: classes.dex */
public class l extends com.google.android.gms.drive.metadata.a<String> {
    public l(String str, int i) {
        super(str, i);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.google.android.gms.drive.metadata.a
    public void a(Bundle bundle, String str) {
        bundle.putString(getName(), str);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.google.android.gms.drive.metadata.a
    /* JADX INFO: renamed from: i, reason: merged with bridge method [inline-methods] */
    public String c(DataHolder dataHolder, int i, int i2) {
        return dataHolder.c(getName(), i, i2);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.google.android.gms.drive.metadata.a
    /* JADX INFO: renamed from: p, reason: merged with bridge method [inline-methods] */
    public String i(Bundle bundle) {
        return bundle.getString(getName());
    }
}
