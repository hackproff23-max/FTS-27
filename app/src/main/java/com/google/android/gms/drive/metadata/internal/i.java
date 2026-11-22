package com.google.android.gms.drive.metadata.internal;

import android.os.Bundle;
import android.os.Parcelable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

/* JADX INFO: loaded from: classes.dex */
public class i<T extends Parcelable> extends com.google.android.gms.drive.metadata.b<T> {
    public i(String str, int i) {
        super(str, Collections.emptySet(), Collections.singleton(str), i);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.google.android.gms.drive.metadata.a
    public void a(Bundle bundle, Collection<T> collection) {
        bundle.putParcelableArrayList(getName(), new ArrayList<>(collection));
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.google.android.gms.drive.metadata.a
    /* JADX INFO: renamed from: n, reason: merged with bridge method [inline-methods] */
    public Collection<T> i(Bundle bundle) {
        return bundle.getParcelableArrayList(getName());
    }
}
