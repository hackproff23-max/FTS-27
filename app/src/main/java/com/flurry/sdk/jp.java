package com.flurry.sdk;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Map;

/* JADX INFO: loaded from: classes.dex */
public final class jp implements is {
    private final ja a;
    private final boolean b;

    public jp(ja jaVar, boolean z) {
        this.a = jaVar;
        this.b = z;
    }

    @Override // com.flurry.sdk.is
    public <T> ir<T> a(ia iaVar, jw<T> jwVar) {
        Type typeB = jwVar.b();
        if (!Map.class.isAssignableFrom(jwVar.a())) {
            return null;
        }
        Type[] typeArrB = iz.b(typeB, iz.e(typeB));
        return new a(iaVar, typeArrB[0], a(iaVar, typeArrB[0]), typeArrB[1], iaVar.a(jw.a(typeArrB[1])), this.a.a(jwVar));
    }

    private ir<?> a(ia iaVar, Type type) {
        return (type == Boolean.TYPE || type == Boolean.class) ? jv.f : iaVar.a(jw.a(type));
    }

    final class a<K, V> extends ir<Map<K, V>> {
        private final ir<K> b;
        private final ir<V> c;
        private final jf<? extends Map<K, V>> d;

        public a(ia iaVar, Type type, ir<K> irVar, Type type2, ir<V> irVar2, jf<? extends Map<K, V>> jfVar) {
            this.b = new ju(iaVar, irVar, type);
            this.c = new ju(iaVar, irVar2, type2);
            this.d = jfVar;
        }

        @Override // com.flurry.sdk.ir
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public Map<K, V> b(jx jxVar) throws IOException {
            jy jyVarF = jxVar.f();
            if (jyVarF == jy.NULL) {
                jxVar.j();
                return null;
            }
            Map<K, V> mapA = this.d.a();
            if (jyVarF == jy.BEGIN_ARRAY) {
                jxVar.a();
                while (jxVar.e()) {
                    jxVar.a();
                    K kB = this.b.b(jxVar);
                    if (mapA.put(kB, this.c.b(jxVar)) != null) {
                        throw new io("duplicate key: " + kB);
                    }
                    jxVar.b();
                }
                jxVar.b();
                return mapA;
            }
            jxVar.c();
            while (jxVar.e()) {
                jc.a.a(jxVar);
                K kB2 = this.b.b(jxVar);
                if (mapA.put(kB2, this.c.b(jxVar)) != null) {
                    throw new io("duplicate key: " + kB2);
                }
            }
            jxVar.d();
            return mapA;
        }

        @Override // com.flurry.sdk.ir
        public void a(jz jzVar, Map<K, V> map) throws IOException {
            int i = 0;
            if (map != null) {
                if (!jp.this.b) {
                    jzVar.d();
                    for (Map.Entry<K, V> entry : map.entrySet()) {
                        jzVar.a(String.valueOf(entry.getKey()));
                        this.c.a(jzVar, entry.getValue());
                    }
                    jzVar.e();
                    return;
                }
                ArrayList arrayList = new ArrayList(map.size());
                ArrayList arrayList2 = new ArrayList(map.size());
                boolean z = false;
                for (Map.Entry<K, V> entry2 : map.entrySet()) {
                    ig igVarA = this.b.a(entry2.getKey());
                    arrayList.add(igVarA);
                    arrayList2.add(entry2.getValue());
                    z = (igVarA.g() || igVarA.h()) | z;
                }
                if (z) {
                    jzVar.b();
                    while (i < arrayList.size()) {
                        jzVar.b();
                        jh.a((ig) arrayList.get(i), jzVar);
                        this.c.a(jzVar, (V) arrayList2.get(i));
                        jzVar.c();
                        i++;
                    }
                    jzVar.c();
                    return;
                }
                jzVar.d();
                while (i < arrayList.size()) {
                    jzVar.a(a((ig) arrayList.get(i)));
                    this.c.a(jzVar, (V) arrayList2.get(i));
                    i++;
                }
                jzVar.e();
                return;
            }
            jzVar.f();
        }

        private String a(ig igVar) {
            if (igVar.i()) {
                il ilVarM = igVar.m();
                if (ilVarM.p()) {
                    return String.valueOf(ilVarM.a());
                }
                if (ilVarM.o()) {
                    return Boolean.toString(ilVarM.f());
                }
                if (ilVarM.q()) {
                    return ilVarM.b();
                }
                throw new AssertionError();
            }
            if (igVar.j()) {
                return "null";
            }
            throw new AssertionError();
        }
    }
}
