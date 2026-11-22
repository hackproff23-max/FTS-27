package com.google.android.gms.internal;

import com.google.android.gms.common.data.DataHolder;
import com.google.android.gms.drive.metadata.internal.AppVisibleCustomProperties;
import java.util.Arrays;
import java.util.Collections;

/* JADX INFO: loaded from: classes.dex */
public class lo extends com.google.android.gms.drive.metadata.internal.j<AppVisibleCustomProperties> {
    public lo(int i) {
        super("customProperties", Collections.singleton("customProperties"), Arrays.asList("customPropertiesExtra"), i);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.google.android.gms.drive.metadata.a
    /* JADX INFO: renamed from: l, reason: merged with bridge method [inline-methods] */
    public AppVisibleCustomProperties c(DataHolder dataHolder, int i, int i2) {
        return (AppVisibleCustomProperties) dataHolder.gV().getSparseParcelableArray("customPropertiesExtra").get(i, AppVisibleCustomProperties.Rd);
    }
}
