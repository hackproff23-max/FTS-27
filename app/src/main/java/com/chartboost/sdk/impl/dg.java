package com.chartboost.sdk.impl;

import java.util.HashMap;
import java.util.Map;

/* JADX INFO: loaded from: classes.dex */
abstract class dg<K, V> extends db<K, V, Map<K, V>> {
    public static <K, V> a<K, V> b() {
        return new a<>();
    }

    public static class a<K, V> {
        private db.h.a a = db.h.a.STABLE;
        private final Map<K, V> b = new HashMap();

        a() {
        }

        public dg<K, V> a() {
            return new b(this.b, this.a);
        }
    }

    public static <K, V> dg<K, V> c() {
        return b().a();
    }

    protected dg(Map<? extends K, ? extends V> map, db.h.a aVar) {
        super(map, aVar);
    }

    static class b<K, V> extends dg<K, V> {
        b(Map<? extends K, ? extends V> map, db.h.a aVar) {
            super(map, aVar);
        }

        @Override // com.chartboost.sdk.impl.db
        public <N extends Map<? extends K, ? extends V>> Map<K, V> a(N n) {
            return new HashMap(n);
        }
    }
}
