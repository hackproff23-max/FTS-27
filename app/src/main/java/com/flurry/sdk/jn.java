package com.flurry.sdk;

import java.io.IOException;
import java.io.Reader;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/* JADX INFO: loaded from: classes.dex */
public final class jn extends jx {
    private static final Reader a = new Reader() { // from class: com.flurry.sdk.jn.1
        @Override // java.io.Reader
        public int read(char[] cArr, int i, int i2) throws IOException {
            throw new AssertionError();
        }

        @Override // java.io.Reader, java.io.Closeable, java.lang.AutoCloseable
        public void close() throws IOException {
            throw new AssertionError();
        }
    };
    private static final Object b = new Object();
    private final List<Object> c;

    @Override // com.flurry.sdk.jx
    public void a() throws IOException {
        a(jy.BEGIN_ARRAY);
        this.c.add(((id) r()).iterator());
    }

    @Override // com.flurry.sdk.jx
    public void b() throws IOException {
        a(jy.END_ARRAY);
        s();
        s();
    }

    @Override // com.flurry.sdk.jx
    public void c() throws IOException {
        a(jy.BEGIN_OBJECT);
        this.c.add(((ij) r()).o().iterator());
    }

    @Override // com.flurry.sdk.jx
    public void d() throws IOException {
        a(jy.END_OBJECT);
        s();
        s();
    }

    @Override // com.flurry.sdk.jx
    public boolean e() throws IOException {
        jy jyVarF = f();
        return (jyVarF == jy.END_OBJECT || jyVarF == jy.END_ARRAY) ? false : true;
    }

    @Override // com.flurry.sdk.jx
    public jy f() throws IOException {
        if (this.c.isEmpty()) {
            return jy.END_DOCUMENT;
        }
        Object objR = r();
        if (objR instanceof Iterator) {
            boolean z = this.c.get(this.c.size() - 2) instanceof ij;
            Iterator it = (Iterator) objR;
            if (!it.hasNext()) {
                return z ? jy.END_OBJECT : jy.END_ARRAY;
            }
            if (z) {
                return jy.NAME;
            }
            this.c.add(it.next());
            return f();
        }
        if (objR instanceof ij) {
            return jy.BEGIN_OBJECT;
        }
        if (objR instanceof id) {
            return jy.BEGIN_ARRAY;
        }
        if (objR instanceof il) {
            il ilVar = (il) objR;
            if (ilVar.q()) {
                return jy.STRING;
            }
            if (ilVar.o()) {
                return jy.BOOLEAN;
            }
            if (ilVar.p()) {
                return jy.NUMBER;
            }
            throw new AssertionError();
        }
        if (objR instanceof ii) {
            return jy.NULL;
        }
        if (objR == b) {
            throw new IllegalStateException("JsonReader is closed");
        }
        throw new AssertionError();
    }

    private Object r() {
        return this.c.get(this.c.size() - 1);
    }

    private Object s() {
        return this.c.remove(this.c.size() - 1);
    }

    private void a(jy jyVar) throws IOException {
        if (f() != jyVar) {
            throw new IllegalStateException("Expected " + jyVar + " but was " + f());
        }
    }

    @Override // com.flurry.sdk.jx
    public String g() throws IOException {
        a(jy.NAME);
        Map.Entry entry = (Map.Entry) ((Iterator) r()).next();
        this.c.add(entry.getValue());
        return (String) entry.getKey();
    }

    @Override // com.flurry.sdk.jx
    public String h() throws IOException {
        jy jyVarF = f();
        if (jyVarF != jy.STRING && jyVarF != jy.NUMBER) {
            throw new IllegalStateException("Expected " + jy.STRING + " but was " + jyVarF);
        }
        return ((il) s()).b();
    }

    @Override // com.flurry.sdk.jx
    public boolean i() throws IOException {
        a(jy.BOOLEAN);
        return ((il) s()).f();
    }

    @Override // com.flurry.sdk.jx
    public void j() throws IOException {
        a(jy.NULL);
        s();
    }

    @Override // com.flurry.sdk.jx
    public double k() throws IOException {
        jy jyVarF = f();
        if (jyVarF != jy.NUMBER && jyVarF != jy.STRING) {
            throw new IllegalStateException("Expected " + jy.NUMBER + " but was " + jyVarF);
        }
        double dC = ((il) r()).c();
        if (!p() && (Double.isNaN(dC) || Double.isInfinite(dC))) {
            throw new NumberFormatException("JSON forbids NaN and infinities: " + dC);
        }
        s();
        return dC;
    }

    @Override // com.flurry.sdk.jx
    public long l() throws IOException {
        jy jyVarF = f();
        if (jyVarF != jy.NUMBER && jyVarF != jy.STRING) {
            throw new IllegalStateException("Expected " + jy.NUMBER + " but was " + jyVarF);
        }
        long jD = ((il) r()).d();
        s();
        return jD;
    }

    @Override // com.flurry.sdk.jx
    public int m() throws IOException {
        jy jyVarF = f();
        if (jyVarF != jy.NUMBER && jyVarF != jy.STRING) {
            throw new IllegalStateException("Expected " + jy.NUMBER + " but was " + jyVarF);
        }
        int iE = ((il) r()).e();
        s();
        return iE;
    }

    @Override // com.flurry.sdk.jx, java.io.Closeable, java.lang.AutoCloseable
    public void close() throws IOException {
        this.c.clear();
        this.c.add(b);
    }

    @Override // com.flurry.sdk.jx
    public void n() throws IOException {
        if (f() == jy.NAME) {
            g();
        } else {
            s();
        }
    }

    @Override // com.flurry.sdk.jx
    public String toString() {
        return getClass().getSimpleName();
    }

    public void o() throws IOException {
        a(jy.NAME);
        Map.Entry entry = (Map.Entry) ((Iterator) r()).next();
        this.c.add(entry.getValue());
        this.c.add(new il((String) entry.getKey()));
    }
}
