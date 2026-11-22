package com.flurry.sdk;

import java.io.EOFException;
import java.io.IOException;
import java.io.Reader;
import java.io.Writer;
import java.lang.reflect.Type;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/* JADX INFO: loaded from: classes.dex */
public final class ia {
    final ie a;
    final im b;
    private final ThreadLocal<Map<jw<?>, a<?>>> c;
    private final Map<jw<?>, ir<?>> d;
    private final List<is> e;
    private final ja f;
    private final boolean g;
    private final boolean h;
    private final boolean i;
    private final boolean j;

    public ia() {
        this(jb.a, hy.IDENTITY, Collections.emptyMap(), false, false, false, true, false, false, ip.DEFAULT, Collections.emptyList());
    }

    ia(jb jbVar, hz hzVar, Map<Type, ic<?>> map, boolean z, boolean z2, boolean z3, boolean z4, boolean z5, boolean z6, ip ipVar, List<is> list) {
        this.c = new ThreadLocal<>();
        this.d = Collections.synchronizedMap(new HashMap());
        this.a = new ie() { // from class: com.flurry.sdk.ia.1
        };
        this.b = new im() { // from class: com.flurry.sdk.ia.2
        };
        this.f = new ja(map);
        this.g = z;
        this.i = z3;
        this.h = z4;
        this.j = z5;
        ArrayList arrayList = new ArrayList();
        arrayList.add(jv.Q);
        arrayList.add(jq.a);
        arrayList.add(jbVar);
        arrayList.addAll(list);
        arrayList.add(jv.x);
        arrayList.add(jv.m);
        arrayList.add(jv.g);
        arrayList.add(jv.i);
        arrayList.add(jv.k);
        arrayList.add(jv.a(Long.TYPE, Long.class, a(ipVar)));
        arrayList.add(jv.a(Double.TYPE, Double.class, a(z6)));
        arrayList.add(jv.a(Float.TYPE, Float.class, b(z6)));
        arrayList.add(jv.r);
        arrayList.add(jv.t);
        arrayList.add(jv.z);
        arrayList.add(jv.B);
        arrayList.add(jv.a(BigDecimal.class, jv.v));
        arrayList.add(jv.a(BigInteger.class, jv.w));
        arrayList.add(jv.D);
        arrayList.add(jv.F);
        arrayList.add(jv.J);
        arrayList.add(jv.O);
        arrayList.add(jv.H);
        arrayList.add(jv.d);
        arrayList.add(jl.a);
        arrayList.add(jv.M);
        arrayList.add(jt.a);
        arrayList.add(js.a);
        arrayList.add(jv.K);
        arrayList.add(jj.a);
        arrayList.add(jv.R);
        arrayList.add(jv.b);
        arrayList.add(new jk(this.f));
        arrayList.add(new jp(this.f, z2));
        arrayList.add(new jm(this.f));
        arrayList.add(new jr(this.f, hzVar, jbVar));
        this.e = Collections.unmodifiableList(arrayList);
    }

    private ir<Number> a(boolean z) {
        return z ? jv.p : new ir<Number>() { // from class: com.flurry.sdk.ia.3
            @Override // com.flurry.sdk.ir
            /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
            public Double b(jx jxVar) throws IOException {
                if (jxVar.f() != jy.NULL) {
                    return Double.valueOf(jxVar.k());
                }
                jxVar.j();
                return null;
            }

            @Override // com.flurry.sdk.ir
            public void a(jz jzVar, Number number) throws IOException {
                if (number == null) {
                    jzVar.f();
                    return;
                }
                ia.this.a(number.doubleValue());
                jzVar.a(number);
            }
        };
    }

    private ir<Number> b(boolean z) {
        return z ? jv.o : new ir<Number>() { // from class: com.flurry.sdk.ia.4
            @Override // com.flurry.sdk.ir
            /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
            public Float b(jx jxVar) throws IOException {
                if (jxVar.f() != jy.NULL) {
                    return Float.valueOf((float) jxVar.k());
                }
                jxVar.j();
                return null;
            }

            @Override // com.flurry.sdk.ir
            public void a(jz jzVar, Number number) throws IOException {
                if (number == null) {
                    jzVar.f();
                    return;
                }
                ia.this.a(number.floatValue());
                jzVar.a(number);
            }
        };
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(double d) {
        if (Double.isNaN(d) || Double.isInfinite(d)) {
            throw new IllegalArgumentException(d + " is not a valid double value as per JSON specification. To override this behavior, use GsonBuilder.serializeSpecialFloatingPointValues() method.");
        }
    }

    private ir<Number> a(ip ipVar) {
        return ipVar == ip.DEFAULT ? jv.n : new ir<Number>() { // from class: com.flurry.sdk.ia.5
            @Override // com.flurry.sdk.ir
            /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
            public Number b(jx jxVar) throws IOException {
                if (jxVar.f() != jy.NULL) {
                    return Long.valueOf(jxVar.l());
                }
                jxVar.j();
                return null;
            }

            @Override // com.flurry.sdk.ir
            public void a(jz jzVar, Number number) throws IOException {
                if (number == null) {
                    jzVar.f();
                } else {
                    jzVar.b(number.toString());
                }
            }
        };
    }

    public <T> ir<T> a(jw<T> jwVar) {
        Map map;
        ir<T> irVarA = (ir) this.d.get(jwVar);
        if (irVarA == null) {
            Map<jw<?>, a<?>> map2 = this.c.get();
            boolean z = false;
            if (map2 == null) {
                HashMap map3 = new HashMap();
                this.c.set(map3);
                map = map3;
                z = true;
            } else {
                map = map2;
            }
            irVarA = (a) map.get(jwVar);
            if (irVarA == null) {
                try {
                    a aVar = new a();
                    map.put(jwVar, aVar);
                    Iterator<is> it = this.e.iterator();
                    while (it.hasNext()) {
                        irVarA = it.next().a(this, jwVar);
                        if (irVarA != null) {
                            aVar.a((ir) irVarA);
                            this.d.put(jwVar, irVarA);
                            map.remove(jwVar);
                            if (z) {
                                this.c.remove();
                            }
                        }
                    }
                    throw new IllegalArgumentException("GSON cannot handle " + jwVar);
                } catch (Throwable th) {
                    map.remove(jwVar);
                    if (z) {
                        this.c.remove();
                    }
                    throw th;
                }
            }
        }
        return irVarA;
    }

    public <T> ir<T> a(is isVar, jw<T> jwVar) {
        boolean z = false;
        for (is isVar2 : this.e) {
            if (!z) {
                if (isVar2 == isVar) {
                    z = true;
                }
            } else {
                ir<T> irVarA = isVar2.a(this, jwVar);
                if (irVarA != null) {
                    return irVarA;
                }
            }
        }
        throw new IllegalArgumentException("GSON cannot serialize " + jwVar);
    }

    public <T> ir<T> a(Class<T> cls) {
        return a(jw.b(cls));
    }

    public void a(Object obj, Appendable appendable) throws ih {
        if (obj != null) {
            a(obj, obj.getClass(), appendable);
        } else {
            a((ig) ii.a, appendable);
        }
    }

    public void a(Object obj, Type type, Appendable appendable) throws ih {
        try {
            a(obj, type, a(jh.a(appendable)));
        } catch (IOException e) {
            throw new ih(e);
        }
    }

    public void a(Object obj, Type type, jz jzVar) throws ih {
        ir irVarA = a(jw.a(type));
        boolean zG = jzVar.g();
        jzVar.b(true);
        boolean zH = jzVar.h();
        jzVar.c(this.h);
        boolean zI = jzVar.i();
        jzVar.d(this.g);
        try {
            try {
                irVarA.a(jzVar, obj);
                jzVar.b(zG);
                jzVar.c(zH);
                jzVar.d(zI);
            } catch (IOException e) {
                throw new ih(e);
            }
        } catch (Throwable th) {
            jzVar.b(zG);
            jzVar.c(zH);
            jzVar.d(zI);
            throw th;
        }
    }

    public void a(ig igVar, Appendable appendable) throws ih {
        try {
            a(igVar, a(jh.a(appendable)));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private jz a(Writer writer) throws IOException {
        if (this.i) {
            writer.write(")]}'\n");
        }
        jz jzVar = new jz(writer);
        if (this.j) {
            jzVar.c("  ");
        }
        jzVar.d(this.g);
        return jzVar;
    }

    public void a(ig igVar, jz jzVar) throws ih {
        boolean zG = jzVar.g();
        jzVar.b(true);
        boolean zH = jzVar.h();
        jzVar.c(this.h);
        boolean zI = jzVar.i();
        jzVar.d(this.g);
        try {
            try {
                jh.a(igVar, jzVar);
                jzVar.b(zG);
                jzVar.c(zH);
                jzVar.d(zI);
            } catch (IOException e) {
                throw new ih(e);
            }
        } catch (Throwable th) {
            jzVar.b(zG);
            jzVar.c(zH);
            jzVar.d(zI);
            throw th;
        }
    }

    public <T> T a(Reader reader, Class<T> cls) throws ih, io {
        jx jxVar = new jx(reader);
        Object objA = a(jxVar, cls);
        a(objA, jxVar);
        return (T) jg.a((Class) cls).cast(objA);
    }

    private static void a(Object obj, jx jxVar) {
        if (obj != null) {
            try {
                if (jxVar.f() != jy.END_DOCUMENT) {
                    throw new ih("JSON document was not fully consumed.");
                }
            } catch (ka e) {
                throw new io(e);
            } catch (IOException e2) {
                throw new ih(e2);
            }
        }
    }

    public <T> T a(jx jxVar, Type type) throws ih, io {
        boolean z = true;
        boolean zP = jxVar.p();
        jxVar.a(true);
        try {
            try {
                jxVar.f();
                z = false;
                T tB = a(jw.a(type)).b(jxVar);
                jxVar.a(zP);
                return tB;
            } catch (EOFException e) {
                if (!z) {
                    throw new io(e);
                }
                jxVar.a(zP);
                return null;
            } catch (IOException e2) {
                throw new io(e2);
            } catch (IllegalStateException e3) {
                throw new io(e3);
            }
        } catch (Throwable th) {
            jxVar.a(zP);
            throw th;
        }
    }

    static class a<T> extends ir<T> {
        private ir<T> a;

        a() {
        }

        public void a(ir<T> irVar) {
            if (this.a != null) {
                throw new AssertionError();
            }
            this.a = irVar;
        }

        @Override // com.flurry.sdk.ir
        public T b(jx jxVar) throws IOException {
            if (this.a == null) {
                throw new IllegalStateException();
            }
            return this.a.b(jxVar);
        }

        @Override // com.flurry.sdk.ir
        public void a(jz jzVar, T t) throws IOException {
            if (this.a == null) {
                throw new IllegalStateException();
            }
            this.a.a(jzVar, t);
        }
    }

    public String toString() {
        return "{serializeNulls:" + this.g + "factories:" + this.e + ",instanceCreators:" + this.f + "}";
    }
}
