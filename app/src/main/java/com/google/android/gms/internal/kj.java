package com.google.android.gms.internal;

import java.util.LinkedHashMap;
import java.util.Map;

/* JADX INFO: loaded from: classes.dex */
public class kj<K, V> {
    private int NA;
    private int NB;
    private int NC;
    private final LinkedHashMap<K, V> Nw;
    private int Nx;
    private int Ny;
    private int Nz;
    private int size;

    public kj(int i) {
        if (i <= 0) {
            throw new IllegalArgumentException("maxSize <= 0");
        }
        this.Nx = i;
        this.Nw = new LinkedHashMap<>(0, 0.75f, true);
    }

    private int c(K k, V v) {
        int iSizeOf = sizeOf(k, v);
        if (iSizeOf < 0) {
            throw new IllegalStateException("Negative size: " + k + "=" + v);
        }
        return iSizeOf;
    }

    protected V create(K key) {
        return null;
    }

    protected void entryRemoved(boolean evicted, K key, V oldValue, V newValue) {
    }

    public final void evictAll() {
        trimToSize(-1);
    }

    public final V get(K k) {
        V vPut;
        if (k == null) {
            throw new NullPointerException("key == null");
        }
        synchronized (this) {
            V v = this.Nw.get(k);
            if (v != null) {
                this.NB++;
                return v;
            }
            this.NC++;
            V vCreate = create(k);
            if (vCreate == null) {
                return null;
            }
            synchronized (this) {
                this.Nz++;
                vPut = this.Nw.put(k, vCreate);
                if (vPut != null) {
                    this.Nw.put(k, vPut);
                } else {
                    this.size += c(k, vCreate);
                }
            }
            if (vPut != null) {
                entryRemoved(false, k, vCreate, vPut);
                return vPut;
            }
            trimToSize(this.Nx);
            return vCreate;
        }
    }

    public final V put(K key, V value) {
        V vPut;
        if (key == null || value == null) {
            throw new NullPointerException("key == null || value == null");
        }
        synchronized (this) {
            this.Ny++;
            this.size += c(key, value);
            vPut = this.Nw.put(key, value);
            if (vPut != null) {
                this.size -= c(key, vPut);
            }
        }
        if (vPut != null) {
            entryRemoved(false, key, vPut, value);
        }
        trimToSize(this.Nx);
        return vPut;
    }

    public final synchronized int size() {
        return this.size;
    }

    protected int sizeOf(K key, V value) {
        return 1;
    }

    public final synchronized String toString() {
        String str;
        synchronized (this) {
            int i = this.NB + this.NC;
            str = String.format("LruCache[maxSize=%d,hits=%d,misses=%d,hitRate=%d%%]", Integer.valueOf(this.Nx), Integer.valueOf(this.NB), Integer.valueOf(this.NC), Integer.valueOf(i != 0 ? (this.NB * 100) / i : 0));
        }
        return str;
    }

    public void trimToSize(int maxSize) {
        K key;
        V value;
        while (true) {
            synchronized (this) {
                if (this.size < 0 || (this.Nw.isEmpty() && this.size != 0)) {
                    break;
                }
                if (this.size <= maxSize || this.Nw.isEmpty()) {
                    return;
                }
                Map.Entry<K, V> next = this.Nw.entrySet().iterator().next();
                key = next.getKey();
                value = next.getValue();
                this.Nw.remove(key);
                this.size -= c(key, value);
                this.NA++;
            }
            entryRemoved(true, key, value, null);
        }
        throw new IllegalStateException(getClass().getName() + ".sizeOf() is reporting inconsistent results!");
    }
}
