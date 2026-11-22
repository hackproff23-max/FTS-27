package com.flurry.sdk;

import java.io.IOException;
import java.lang.reflect.Array;
import java.lang.reflect.GenericArrayType;
import java.lang.reflect.Type;
import java.util.ArrayList;

/* JADX INFO: loaded from: classes.dex */
public final class jj<E> extends ir<Object> {
    public static final is a = new is() { // from class: com.flurry.sdk.jj.1
        @Override // com.flurry.sdk.is
        public <T> ir<T> a(ia iaVar, jw<T> jwVar) {
            Type typeB = jwVar.b();
            if (!(typeB instanceof GenericArrayType) && (!(typeB instanceof Class) || !((Class) typeB).isArray())) {
                return null;
            }
            Type typeG = iz.g(typeB);
            return new jj(iaVar, iaVar.a(jw.a(typeG)), iz.e(typeG));
        }
    };
    private final Class<E> b;
    private final ir<E> c;

    public jj(ia iaVar, ir<E> irVar, Class<E> cls) {
        this.c = new ju(iaVar, irVar, cls);
        this.b = cls;
    }

    @Override // com.flurry.sdk.ir
    public Object b(jx jxVar) throws IOException {
        if (jxVar.f() == jy.NULL) {
            jxVar.j();
            return null;
        }
        ArrayList arrayList = new ArrayList();
        jxVar.a();
        while (jxVar.e()) {
            arrayList.add(this.c.b(jxVar));
        }
        jxVar.b();
        Object objNewInstance = Array.newInstance((Class<?>) this.b, arrayList.size());
        for (int i = 0; i < arrayList.size(); i++) {
            Array.set(objNewInstance, i, arrayList.get(i));
        }
        return objNewInstance;
    }

    @Override // com.flurry.sdk.ir
    public void a(jz jzVar, Object obj) throws IOException {
        if (obj == null) {
            jzVar.f();
            return;
        }
        jzVar.b();
        int length = Array.getLength(obj);
        for (int i = 0; i < length; i++) {
            this.c.a(jzVar, (E) Array.get(obj, i));
        }
        jzVar.c();
    }
}
