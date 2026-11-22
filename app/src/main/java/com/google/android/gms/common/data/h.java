package com.google.android.gms.common.data;

import java.util.NoSuchElementException;

/* JADX INFO: loaded from: classes.dex */
public class h<T> extends c<T> {
    private T Lt;

    public h(DataBuffer<T> dataBuffer) {
        super(dataBuffer);
    }

    @Override // com.google.android.gms.common.data.c, java.util.Iterator
    public T next() {
        if (!hasNext()) {
            throw new NoSuchElementException("Cannot advance the iterator beyond " + this.KX);
        }
        this.KX++;
        if (this.KX == 0) {
            this.Lt = this.KW.get(0);
            if (!(this.Lt instanceof d)) {
                throw new IllegalStateException("DataBuffer reference of type " + this.Lt.getClass() + " is not movable");
            }
        } else {
            ((d) this.Lt).as(this.KX);
        }
        return this.Lt;
    }
}
