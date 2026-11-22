package com.flurry.sdk;

import java.io.IOException;

/* JADX INFO: loaded from: classes.dex */
final class iq<T> extends ir<T> {
    private final in<T> a;
    private final Cif<T> b;
    private final ia c;
    private final jw<T> d;
    private final is e;
    private ir<T> f;

    private iq(in<T> inVar, Cif<T> cif, ia iaVar, jw<T> jwVar, is isVar) {
        this.a = inVar;
        this.b = cif;
        this.c = iaVar;
        this.d = jwVar;
        this.e = isVar;
    }

    @Override // com.flurry.sdk.ir
    public T b(jx jxVar) throws IOException {
        if (this.b == null) {
            return a().b(jxVar);
        }
        ig igVarA = jh.a(jxVar);
        if (igVarA.j()) {
            return null;
        }
        return this.b.b(igVarA, this.d.b(), this.c.a);
    }

    @Override // com.flurry.sdk.ir
    public void a(jz jzVar, T t) throws IOException {
        if (this.a == null) {
            a().a(jzVar, t);
        } else if (t == null) {
            jzVar.f();
        } else {
            jh.a(this.a.a(t, this.d.b(), this.c.b), jzVar);
        }
    }

    private ir<T> a() {
        ir<T> irVar = this.f;
        if (irVar != null) {
            return irVar;
        }
        ir<T> irVarA = this.c.a(this.e, this.d);
        this.f = irVarA;
        return irVarA;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static is a(jw<?> jwVar, Object obj) {
        return new a(obj, jwVar, false, null);
    }

    static class a implements is {
        private final jw<?> a;
        private final boolean b;
        private final Class<?> c;
        private final in<?> d;
        private final Cif<?> e;

        private a(Object obj, jw<?> jwVar, boolean z, Class<?> cls) {
            this.d = obj instanceof in ? (in) obj : null;
            this.e = obj instanceof Cif ? (Cif) obj : null;
            iy.a((this.d == null && this.e == null) ? false : true);
            this.a = jwVar;
            this.b = z;
            this.c = cls;
        }

        @Override // com.flurry.sdk.is
        public <T> ir<T> a(ia iaVar, jw<T> jwVar) {
            boolean zIsAssignableFrom;
            if (this.a != null) {
                zIsAssignableFrom = this.a.equals(jwVar) || (this.b && this.a.b() == jwVar.a());
            } else {
                zIsAssignableFrom = this.c.isAssignableFrom(jwVar.a());
            }
            if (zIsAssignableFrom) {
                return new iq(this.d, this.e, iaVar, jwVar, this);
            }
            return null;
        }
    }
}
