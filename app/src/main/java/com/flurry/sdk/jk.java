package com.flurry.sdk;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.Collection;
import java.util.Iterator;

/* JADX INFO: loaded from: classes.dex */
public final class jk implements is {
    private final ja a;

    public jk(ja jaVar) {
        this.a = jaVar;
    }

    @Override // com.flurry.sdk.is
    public <T> ir<T> a(ia iaVar, jw<T> jwVar) {
        Type typeB = jwVar.b();
        Class<? super T> clsA = jwVar.a();
        if (!Collection.class.isAssignableFrom(clsA)) {
            return null;
        }
        Type typeA = iz.a(typeB, (Class<?>) clsA);
        return new a(iaVar, typeA, iaVar.a(jw.a(typeA)), this.a.a(jwVar));
    }

    static final class a<E> extends ir<Collection<E>> {
        private final ir<E> a;
        private final jf<? extends Collection<E>> b;

        public a(ia iaVar, Type type, ir<E> irVar, jf<? extends Collection<E>> jfVar) {
            this.a = new ju(iaVar, irVar, type);
            this.b = jfVar;
        }

        @Override // com.flurry.sdk.ir
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public Collection<E> b(jx jxVar) throws IOException {
            if (jxVar.f() == jy.NULL) {
                jxVar.j();
                return null;
            }
            Collection<E> collectionA = this.b.a();
            jxVar.a();
            while (jxVar.e()) {
                collectionA.add(this.a.b(jxVar));
            }
            jxVar.b();
            return collectionA;
        }

        @Override // com.flurry.sdk.ir
        public void a(jz jzVar, Collection<E> collection) throws IOException {
            if (collection == null) {
                jzVar.f();
                return;
            }
            jzVar.b();
            Iterator<E> it = collection.iterator();
            while (it.hasNext()) {
                this.a.a(jzVar, it.next());
            }
            jzVar.c();
        }
    }
}
