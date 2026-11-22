package com.flurry.sdk;

import java.io.IOException;
import java.lang.reflect.Field;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public final class jb implements is, Cloneable {
    public static final jb a = new jb();
    private boolean e;
    private double b = -1.0d;
    private int c = 136;
    private boolean d = true;
    private List<hw> f = Collections.emptyList();
    private List<hw> g = Collections.emptyList();

    /* JADX INFO: Access modifiers changed from: protected */
    /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
    public jb clone() {
        try {
            return (jb) super.clone();
        } catch (CloneNotSupportedException e) {
            throw new AssertionError();
        }
    }

    @Override // com.flurry.sdk.is
    public <T> ir<T> a(final ia iaVar, final jw<T> jwVar) {
        Class<? super T> clsA = jwVar.a();
        final boolean zA = a((Class<?>) clsA, true);
        final boolean zA2 = a((Class<?>) clsA, false);
        if (zA || zA2) {
            return new ir<T>() { // from class: com.flurry.sdk.jb.1
                private ir<T> f;

                @Override // com.flurry.sdk.ir
                public T b(jx jxVar) throws IOException {
                    if (!zA2) {
                        return a().b(jxVar);
                    }
                    jxVar.n();
                    return null;
                }

                @Override // com.flurry.sdk.ir
                public void a(jz jzVar, T t) throws IOException {
                    if (zA) {
                        jzVar.f();
                    } else {
                        a().a(jzVar, t);
                    }
                }

                private ir<T> a() {
                    ir<T> irVar = this.f;
                    if (irVar != null) {
                        return irVar;
                    }
                    ir<T> irVarA = iaVar.a(jb.this, jwVar);
                    this.f = irVarA;
                    return irVarA;
                }
            };
        }
        return null;
    }

    public boolean a(Field field, boolean z) {
        it itVar;
        if ((this.c & field.getModifiers()) != 0) {
            return true;
        }
        if ((this.b == -1.0d || a((iw) field.getAnnotation(iw.class), (ix) field.getAnnotation(ix.class))) && !field.isSynthetic()) {
            if (this.e && ((itVar = (it) field.getAnnotation(it.class)) == null || (!z ? itVar.b() : itVar.a()))) {
                return true;
            }
            if ((this.d || !b(field.getType())) && !a(field.getType())) {
                List<hw> list = z ? this.f : this.g;
                if (!list.isEmpty()) {
                    hx hxVar = new hx(field);
                    Iterator<hw> it = list.iterator();
                    while (it.hasNext()) {
                        if (it.next().a(hxVar)) {
                            return true;
                        }
                    }
                }
                return false;
            }
            return true;
        }
        return true;
    }

    public boolean a(Class<?> cls, boolean z) {
        if (this.b != -1.0d && !a((iw) cls.getAnnotation(iw.class), (ix) cls.getAnnotation(ix.class))) {
            return true;
        }
        if ((this.d || !b(cls)) && !a(cls)) {
            Iterator<hw> it = (z ? this.f : this.g).iterator();
            while (it.hasNext()) {
                if (it.next().a(cls)) {
                    return true;
                }
            }
            return false;
        }
        return true;
    }

    private boolean a(Class<?> cls) {
        return !Enum.class.isAssignableFrom(cls) && (cls.isAnonymousClass() || cls.isLocalClass());
    }

    private boolean b(Class<?> cls) {
        return cls.isMemberClass() && !c(cls);
    }

    private boolean c(Class<?> cls) {
        return (cls.getModifiers() & 8) != 0;
    }

    private boolean a(iw iwVar, ix ixVar) {
        return a(iwVar) && a(ixVar);
    }

    private boolean a(iw iwVar) {
        return iwVar == null || iwVar.a() <= this.b;
    }

    private boolean a(ix ixVar) {
        return ixVar == null || ixVar.a() > this.b;
    }
}
