package com.google.android.gms.tagmanager;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/* JADX INFO: loaded from: classes.dex */
class da<K, V> implements k<K, V> {
    private final Map<K, V> atM = new HashMap();
    private final int atN;
    private final l.a<K, V> atO;
    private int atP;

    da(int i, l.a<K, V> aVar) {
        this.atN = i;
        this.atO = aVar;
    }

    @Override // com.google.android.gms.tagmanager.k
    public synchronized void e(K k, V v) {
        try {
            if (k == null || v == null) {
                throw new NullPointerException("key == null || value == null");
            }
            this.atP += this.atO.sizeOf(k, v);
            if (this.atP > this.atN) {
                Iterator<Map.Entry<K, V>> it = this.atM.entrySet().iterator();
                while (it.hasNext()) {
                    Map.Entry<K, V> next = it.next();
                    this.atP -= this.atO.sizeOf(next.getKey(), next.getValue());
                    it.remove();
                    if (this.atP <= this.atN) {
                        break;
                    }
                }
            }
            this.atM.put(k, v);
        } catch (Throwable th) {
            throw th;
        }
    }

    @Override // com.google.android.gms.tagmanager.k
    public synchronized V get(K key) {
        return this.atM.get(key);
    }
}
