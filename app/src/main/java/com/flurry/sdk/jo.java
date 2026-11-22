package com.flurry.sdk;

import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public final class jo extends jz {
    private static final Writer a = new Writer() { // from class: com.flurry.sdk.jo.1
        @Override // java.io.Writer
        public void write(char[] cArr, int i, int i2) {
            throw new AssertionError();
        }

        @Override // java.io.Writer, java.io.Flushable
        public void flush() throws IOException {
            throw new AssertionError();
        }

        @Override // java.io.Writer, java.io.Closeable, java.lang.AutoCloseable
        public void close() throws IOException {
            throw new AssertionError();
        }
    };
    private static final il b = new il("closed");
    private final List<ig> c;
    private String d;
    private ig e;

    public jo() {
        super(a);
        this.c = new ArrayList();
        this.e = ii.a;
    }

    public ig a() {
        if (!this.c.isEmpty()) {
            throw new IllegalStateException("Expected one JSON element but was " + this.c);
        }
        return this.e;
    }

    private ig j() {
        return this.c.get(this.c.size() - 1);
    }

    private void a(ig igVar) {
        if (this.d != null) {
            if (!igVar.j() || i()) {
                ((ij) j()).a(this.d, igVar);
            }
            this.d = null;
            return;
        }
        if (this.c.isEmpty()) {
            this.e = igVar;
            return;
        }
        ig igVarJ = j();
        if (igVarJ instanceof id) {
            ((id) igVarJ).a(igVar);
            return;
        }
        throw new IllegalStateException();
    }

    @Override // com.flurry.sdk.jz
    public jz b() throws IOException {
        id idVar = new id();
        a(idVar);
        this.c.add(idVar);
        return this;
    }

    @Override // com.flurry.sdk.jz
    public jz c() throws IOException {
        if (this.c.isEmpty() || this.d != null) {
            throw new IllegalStateException();
        }
        if (j() instanceof id) {
            this.c.remove(this.c.size() - 1);
            return this;
        }
        throw new IllegalStateException();
    }

    @Override // com.flurry.sdk.jz
    public jz d() throws IOException {
        ij ijVar = new ij();
        a(ijVar);
        this.c.add(ijVar);
        return this;
    }

    @Override // com.flurry.sdk.jz
    public jz e() throws IOException {
        if (this.c.isEmpty() || this.d != null) {
            throw new IllegalStateException();
        }
        if (j() instanceof ij) {
            this.c.remove(this.c.size() - 1);
            return this;
        }
        throw new IllegalStateException();
    }

    @Override // com.flurry.sdk.jz
    public jz a(String str) throws IOException {
        if (this.c.isEmpty() || this.d != null) {
            throw new IllegalStateException();
        }
        if (j() instanceof ij) {
            this.d = str;
            return this;
        }
        throw new IllegalStateException();
    }

    @Override // com.flurry.sdk.jz
    public jz b(String str) throws IOException {
        if (str == null) {
            return f();
        }
        a(new il(str));
        return this;
    }

    @Override // com.flurry.sdk.jz
    public jz f() throws IOException {
        a(ii.a);
        return this;
    }

    @Override // com.flurry.sdk.jz
    public jz a(boolean z) throws IOException {
        a(new il(Boolean.valueOf(z)));
        return this;
    }

    @Override // com.flurry.sdk.jz
    public jz a(long j) throws IOException {
        a(new il(Long.valueOf(j)));
        return this;
    }

    @Override // com.flurry.sdk.jz
    public jz a(Number number) throws IOException {
        if (number == null) {
            return f();
        }
        if (!g()) {
            double dDoubleValue = number.doubleValue();
            if (Double.isNaN(dDoubleValue) || Double.isInfinite(dDoubleValue)) {
                throw new IllegalArgumentException("JSON forbids NaN and infinities: " + number);
            }
        }
        a(new il(number));
        return this;
    }

    @Override // com.flurry.sdk.jz, java.io.Flushable
    public void flush() throws IOException {
    }

    @Override // com.flurry.sdk.jz, java.io.Closeable, java.lang.AutoCloseable
    public void close() throws IOException {
        if (!this.c.isEmpty()) {
            throw new IOException("Incomplete document");
        }
        this.c.add(b);
    }
}
