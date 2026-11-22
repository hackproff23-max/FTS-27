package com.google.android.gms.drive.query.internal;

import com.google.android.gms.drive.metadata.MetadataField;
import com.google.android.gms.drive.metadata.internal.MetadataBundle;
import java.util.Set;

/* JADX INFO: loaded from: classes.dex */
class e {
    static MetadataField<?> b(MetadataBundle metadataBundle) {
        Set<MetadataField<?>> setJa = metadataBundle.ja();
        if (setJa.size() != 1) {
            throw new IllegalArgumentException("bundle should have exactly 1 populated field");
        }
        return setJa.iterator().next();
    }
}
