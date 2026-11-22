package com.google.android.gms.common.data;

import android.os.Bundle;
import com.google.android.gms.common.api.Releasable;
import java.util.Iterator;

/* JADX INFO: loaded from: classes.dex */
public abstract class DataBuffer<T> implements Releasable, Iterable<T> {
    protected final DataHolder JG;

    protected DataBuffer(DataHolder dataHolder) {
        this.JG = dataHolder;
        if (this.JG != null) {
            this.JG.e(this);
        }
    }

    @Deprecated
    public final void close() {
        release();
    }

    public int describeContents() {
        return 0;
    }

    public Bundle gV() {
        return this.JG.gV();
    }

    public abstract T get(int i);

    public int getCount() {
        if (this.JG == null) {
            return 0;
        }
        return this.JG.getCount();
    }

    @Deprecated
    public boolean isClosed() {
        if (this.JG == null) {
            return true;
        }
        return this.JG.isClosed();
    }

    @Override // java.lang.Iterable
    public Iterator<T> iterator() {
        return new c(this);
    }

    @Override // com.google.android.gms.common.api.Releasable
    public void release() {
        if (this.JG != null) {
            this.JG.close();
        }
    }

    public Iterator<T> singleRefIterator() {
        return new h(this);
    }
}
