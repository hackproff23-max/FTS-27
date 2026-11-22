package com.flurry.sdk;

import java.io.IOException;
import java.lang.reflect.Type;
import java.lang.reflect.TypeVariable;

/* JADX INFO: loaded from: classes.dex */
final class ju<T> extends ir<T> {
    private final ia a;
    private final ir<T> b;
    private final Type c;

    ju(ia iaVar, ir<T> irVar, Type type) {
        this.a = iaVar;
        this.b = irVar;
        this.c = type;
    }

    @Override // com.flurry.sdk.ir
    public T b(jx jxVar) throws IOException {
        return this.b.b(jxVar);
    }

    @Override // com.flurry.sdk.ir
    public void a(jz jzVar, T t) throws IOException {
        ir<T> irVarA = this.b;
        Type typeA = a(this.c, t);
        if (typeA != this.c) {
            irVarA = this.a.a(jw.a(typeA));
            if ((irVarA instanceof jr.a) && !(this.b instanceof jr.a)) {
                irVarA = this.b;
            }
        }
        irVarA.a(jzVar, t);
    }

    private Type a(Type type, Object obj) {
        if (obj == null) {
            return type;
        }
        if (type == Object.class || (type instanceof TypeVariable) || (type instanceof Class)) {
            return obj.getClass();
        }
        return type;
    }
}
