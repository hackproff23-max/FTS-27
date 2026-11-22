package com.flurry.sdk;

import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.Type;
import java.util.LinkedHashMap;
import java.util.Map;

/* JADX INFO: loaded from: classes.dex */
public final class jr implements is {
    private final ja a;
    private final hz b;
    private final jb c;

    public jr(ja jaVar, hz hzVar, jb jbVar) {
        this.a = jaVar;
        this.b = hzVar;
        this.c = jbVar;
    }

    public boolean a(Field field, boolean z) {
        return (this.c.a(field.getType(), z) || this.c.a(field, z)) ? false : true;
    }

    private String a(Field field) {
        iv ivVar = (iv) field.getAnnotation(iv.class);
        return ivVar == null ? this.b.a(field) : ivVar.a();
    }

    @Override // com.flurry.sdk.is
    public <T> ir<T> a(ia iaVar, jw<T> jwVar) {
        Class<? super T> clsA = jwVar.a();
        if (Object.class.isAssignableFrom(clsA)) {
            return new a(this.a.a(jwVar), a(iaVar, (jw<?>) jwVar, (Class<?>) clsA));
        }
        return null;
    }

    private b a(final ia iaVar, final Field field, String str, final jw<?> jwVar, boolean z, boolean z2) {
        final boolean zA = jg.a((Type) jwVar.a());
        return new b(str, z, z2) { // from class: com.flurry.sdk.jr.1
            final ir<?> a;

            {
                this.a = jr.this.a(iaVar, field, (jw<?>) jwVar);
            }

            @Override // com.flurry.sdk.jr.b
            void a(jz jzVar, Object obj) throws IllegalAccessException, IOException {
                new ju(iaVar, this.a, jwVar.b()).a(jzVar, field.get(obj));
            }

            @Override // com.flurry.sdk.jr.b
            void a(jx jxVar, Object obj) throws IllegalAccessException, IOException {
                Object objB = this.a.b(jxVar);
                if (objB != null || !zA) {
                    field.set(obj, objB);
                }
            }
        };
    }

    /* JADX INFO: Access modifiers changed from: private */
    public ir<?> a(ia iaVar, Field field, jw<?> jwVar) {
        ir<?> irVarA;
        iu iuVar = (iu) field.getAnnotation(iu.class);
        return (iuVar == null || (irVarA = jm.a(this.a, iaVar, jwVar, iuVar)) == null) ? iaVar.a(jwVar) : irVarA;
    }

    private Map<String, b> a(ia iaVar, jw<?> jwVar, Class<?> cls) {
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        if (cls.isInterface()) {
            return linkedHashMap;
        }
        Type typeB = jwVar.b();
        while (cls != Object.class) {
            for (Field field : cls.getDeclaredFields()) {
                boolean zA = a(field, true);
                boolean zA2 = a(field, false);
                if (zA || zA2) {
                    field.setAccessible(true);
                    b bVarA = a(iaVar, field, a(field), jw.a(iz.a(jwVar.b(), cls, field.getGenericType())), zA, zA2);
                    b bVar = (b) linkedHashMap.put(bVarA.g, bVarA);
                    if (bVar != null) {
                        throw new IllegalArgumentException(typeB + " declares multiple JSON fields named " + bVar.g);
                    }
                }
            }
            jwVar = jw.a(iz.a(jwVar.b(), cls, cls.getGenericSuperclass()));
            cls = jwVar.a();
        }
        return linkedHashMap;
    }

    static abstract class b {
        final String g;
        final boolean h;
        final boolean i;

        abstract void a(jx jxVar, Object obj) throws IllegalAccessException, IOException;

        abstract void a(jz jzVar, Object obj) throws IllegalAccessException, IOException;

        protected b(String str, boolean z, boolean z2) {
            this.g = str;
            this.h = z;
            this.i = z2;
        }
    }

    public static final class a<T> extends ir<T> {
        private final jf<T> a;
        private final Map<String, b> b;

        private a(jf<T> jfVar, Map<String, b> map) {
            this.a = jfVar;
            this.b = map;
        }

        @Override // com.flurry.sdk.ir
        public T b(jx jxVar) throws IOException {
            if (jxVar.f() == jy.NULL) {
                jxVar.j();
                return null;
            }
            T tA = this.a.a();
            try {
                jxVar.c();
                while (jxVar.e()) {
                    b bVar = this.b.get(jxVar.g());
                    if (bVar == null || !bVar.i) {
                        jxVar.n();
                    } else {
                        bVar.a(jxVar, tA);
                    }
                }
                jxVar.d();
                return tA;
            } catch (IllegalAccessException e) {
                throw new AssertionError(e);
            } catch (IllegalStateException e2) {
                throw new io(e2);
            }
        }

        @Override // com.flurry.sdk.ir
        public void a(jz jzVar, T t) throws IOException {
            if (t == null) {
                jzVar.f();
                return;
            }
            jzVar.d();
            try {
                for (b bVar : this.b.values()) {
                    if (bVar.h) {
                        jzVar.a(bVar.g);
                        bVar.a(jzVar, t);
                    }
                }
                jzVar.e();
            } catch (IllegalAccessException e) {
                throw new AssertionError();
            }
        }
    }
}
