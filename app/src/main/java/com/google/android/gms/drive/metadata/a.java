package com.google.android.gms.drive.metadata;

import android.os.Bundle;
import com.google.android.gms.common.data.DataHolder;
import com.google.android.gms.drive.metadata.internal.MetadataBundle;
import com.google.android.gms.internal.jx;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

/* JADX INFO: loaded from: classes.dex */
public abstract class a<T> implements MetadataField<T> {
    private final String QY;
    private final Set<String> QZ;
    private final Set<String> Ra;
    private final int Rb;

    protected a(String str, int i) {
        this.QY = (String) jx.b(str, (Object) "fieldName");
        this.QZ = Collections.singleton(str);
        this.Ra = Collections.emptySet();
        this.Rb = i;
    }

    protected a(String str, Collection<String> collection, Collection<String> collection2, int i) {
        this.QY = (String) jx.b(str, (Object) "fieldName");
        this.QZ = Collections.unmodifiableSet(new HashSet(collection));
        this.Ra = Collections.unmodifiableSet(new HashSet(collection2));
        this.Rb = i;
    }

    @Override // com.google.android.gms.drive.metadata.MetadataField
    public final T a(DataHolder dataHolder, int i, int i2) {
        if (b(dataHolder, i, i2)) {
            return c(dataHolder, i, i2);
        }
        return null;
    }

    protected abstract void a(Bundle bundle, T t);

    @Override // com.google.android.gms.drive.metadata.MetadataField
    public final void a(DataHolder dataHolder, MetadataBundle metadataBundle, int i, int i2) {
        jx.b(dataHolder, "dataHolder");
        jx.b(metadataBundle, "bundle");
        metadataBundle.b(this, a(dataHolder, i, i2));
    }

    @Override // com.google.android.gms.drive.metadata.MetadataField
    public final void a(T t, Bundle bundle) {
        jx.b(bundle, "bundle");
        if (t == null) {
            bundle.putString(getName(), null);
        } else {
            a(bundle, t);
        }
    }

    protected boolean b(DataHolder dataHolder, int i, int i2) {
        Iterator<String> it = this.QZ.iterator();
        while (it.hasNext()) {
            if (dataHolder.h(it.next(), i, i2)) {
                return false;
            }
        }
        return true;
    }

    protected abstract T c(DataHolder dataHolder, int i, int i2);

    @Override // com.google.android.gms.drive.metadata.MetadataField
    public final String getName() {
        return this.QY;
    }

    @Override // com.google.android.gms.drive.metadata.MetadataField
    public final T h(Bundle bundle) {
        jx.b(bundle, "bundle");
        if (bundle.get(getName()) != null) {
            return i(bundle);
        }
        return null;
    }

    protected abstract T i(Bundle bundle);

    public String toString() {
        return this.QY;
    }
}
