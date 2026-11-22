package com.google.android.gms.common.data;

import com.google.android.gms.internal.jx;
import java.util.Iterator;
import java.util.NoSuchElementException;

/* JADX INFO: loaded from: classes.dex */
public class c<T> implements Iterator<T> {
    protected final DataBuffer<T> KW;
    protected int KX = -1;

    public c(DataBuffer<T> dataBuffer) {
        this.KW = (DataBuffer) jx.i(dataBuffer);
    }

    @Override // java.util.Iterator
    public boolean hasNext() {
        return this.KX < this.KW.getCount() + (-1);
    }

    @Override // java.util.Iterator
    public T next() {
        if (!hasNext()) {
            throw new NoSuchElementException("Cannot advance the iterator beyond " + this.KX);
        }
        DataBuffer<T> dataBuffer = this.KW;
        int i = this.KX + 1;
        this.KX = i;
        return dataBuffer.get(i);
    }

    @Override // java.util.Iterator
    public void remove() {
        throw new UnsupportedOperationException("Cannot remove elements from a DataBufferIterator");
    }
}
