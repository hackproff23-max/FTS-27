package com.chartboost.sdk.impl;

import java.util.Collection;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentMap;

/* JADX INFO: loaded from: classes.dex */
final class df<K, V> implements dh<K, V>, Map<K, V> {
    private final ConcurrentMap<K, V> a;
    private final dh<K, V> b;

    public static <K, V> Map<K, V> a(dh<K, V> dhVar) {
        return new df(dg.c(), dhVar);
    }

    df(ConcurrentMap<K, V> concurrentMap, dh<K, V> dhVar) {
        this.a = (ConcurrentMap) dc.a("map", concurrentMap);
        this.b = (dh) dc.a("function", dhVar);
    }

    @Override // java.util.Map
    public V get(Object key) {
        while (true) {
            V v = this.a.get(key);
            if (v == null) {
                V vA = this.b.a(key);
                if (vA == null) {
                    return null;
                }
                this.a.putIfAbsent(key, vA);
            } else {
                return v;
            }
        }
    }

    @Override // com.chartboost.sdk.impl.dh
    public V a(K k) {
        return get(k);
    }

    @Override // java.util.Map
    public int size() {
        return this.a.size();
    }

    @Override // java.util.Map
    public boolean isEmpty() {
        return this.a.isEmpty();
    }

    @Override // java.util.Map
    public boolean containsKey(Object key) {
        return this.a.containsKey(key);
    }

    @Override // java.util.Map
    public boolean containsValue(Object value) {
        return this.a.containsValue(value);
    }

    @Override // java.util.Map
    public V put(K key, V value) {
        return this.a.put(key, value);
    }

    @Override // java.util.Map
    public V remove(Object key) {
        return this.a.remove(key);
    }

    @Override // java.util.Map
    public void putAll(Map<? extends K, ? extends V> m) {
        this.a.putAll(m);
    }

    @Override // java.util.Map
    public void clear() {
        this.a.clear();
    }

    @Override // java.util.Map
    public Set<K> keySet() {
        return this.a.keySet();
    }

    @Override // java.util.Map
    public Collection<V> values() {
        return this.a.values();
    }

    @Override // java.util.Map
    public Set<Map.Entry<K, V>> entrySet() {
        return this.a.entrySet();
    }

    @Override // java.util.Map
    public boolean equals(Object o) {
        return this.a.equals(o);
    }

    @Override // java.util.Map
    public int hashCode() {
        return this.a.hashCode();
    }
}
