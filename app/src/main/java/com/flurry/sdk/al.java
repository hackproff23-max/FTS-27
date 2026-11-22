package com.flurry.sdk;

import android.os.FileObserver;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.Closeable;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;

/* JADX INFO: loaded from: classes.dex */
public class al {
    private static final String a = al.class.getSimpleName();
    private final String b;
    private final long c;
    private final boolean d;
    private FileObserver e;
    private kb f;

    public class b implements Closeable {
        private final kb.c b;
        private final InputStream c;
        private final GZIPInputStream d;
        private final BufferedInputStream e;
        private boolean f;

        private b(kb.c cVar, boolean z) throws IOException {
            if (cVar == null) {
                throw new IllegalArgumentException("Snapshot cannot be null");
            }
            this.b = cVar;
            this.c = this.b.a(0);
            if (this.c == null) {
                throw new IOException("Snapshot inputstream is null");
            }
            if (z) {
                this.d = new GZIPInputStream(this.c);
                if (this.d == null) {
                    throw new IOException("Gzip inputstream is null");
                }
                this.e = new BufferedInputStream(this.d);
                return;
            }
            this.d = null;
            this.e = new BufferedInputStream(this.c);
        }

        protected void finalize() throws Throwable {
            super.finalize();
            close();
        }

        public InputStream a() {
            return this.e;
        }

        @Override // java.io.Closeable, java.lang.AutoCloseable
        public void close() {
            if (!this.f) {
                this.f = true;
                hp.a(this.e);
                hp.a(this.d);
                hp.a(this.c);
                hp.a(this.b);
            }
        }
    }

    public class c implements Closeable {
        final /* synthetic */ al a;
        private final kb.a b;
        private final OutputStream c;
        private final GZIPOutputStream d;
        private final a e;
        private boolean f;

        private c(al alVar, kb.a aVar, boolean z) throws IOException {
            this.a = alVar;
            if (aVar == null) {
                throw new IllegalArgumentException("Editor cannot be null");
            }
            this.b = aVar;
            this.c = this.b.a(0);
            if (this.c == null) {
                throw new IOException("Editor outputstream is null");
            }
            if (z) {
                this.d = new GZIPOutputStream(this.c);
                if (this.d == null) {
                    throw new IOException("Gzip outputstream is null");
                }
                this.e = new a(this.d);
                return;
            }
            this.d = null;
            this.e = new a(this.c);
        }

        protected void finalize() throws Throwable {
            super.finalize();
            close();
        }

        public OutputStream a() {
            return this.e;
        }

        @Override // java.io.Closeable, java.lang.AutoCloseable
        public void close() {
            if (!this.f) {
                this.f = true;
                hp.a(this.e);
                hp.a(this.d);
                hp.a(this.c);
                if (this.b != null) {
                    try {
                        if (this.e != null ? this.e.a() : true) {
                            this.b.b();
                        } else {
                            this.b.a();
                        }
                    } catch (IOException e) {
                        gd.a(3, al.a, "Exception closing editor for cache: " + this.a.b, e);
                    }
                }
            }
        }
    }

    static class a extends BufferedOutputStream {
        private boolean a;

        private a(OutputStream outputStream) {
            super(outputStream);
            this.a = false;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public boolean a() {
            return this.a;
        }

        @Override // java.io.FilterOutputStream, java.io.OutputStream, java.io.Closeable, java.lang.AutoCloseable
        public void close() throws IOException {
            try {
                super.close();
            } catch (IOException e) {
                this.a = true;
                throw e;
            }
        }

        @Override // java.io.BufferedOutputStream, java.io.FilterOutputStream, java.io.OutputStream, java.io.Flushable
        public void flush() throws IOException {
            try {
                super.flush();
            } catch (IOException e) {
                this.a = true;
                throw e;
            }
        }

        @Override // java.io.BufferedOutputStream, java.io.FilterOutputStream, java.io.OutputStream
        public void write(int i) throws IOException {
            try {
                super.write(i);
            } catch (IOException e) {
                this.a = true;
                throw e;
            }
        }

        @Override // java.io.FilterOutputStream, java.io.OutputStream
        public void write(byte[] bArr) throws IOException {
            try {
                super.write(bArr);
            } catch (IOException e) {
                this.a = true;
                throw e;
            }
        }

        @Override // java.io.BufferedOutputStream, java.io.FilterOutputStream, java.io.OutputStream
        public void write(byte[] bArr, int i, int i2) throws IOException {
            try {
                super.write(bArr, i, i2);
            } catch (IOException e) {
                this.a = true;
                throw e;
            }
        }
    }

    public al(String str, long j, boolean z) {
        if (str == null || str.length() == 0) {
            throw new IllegalArgumentException("The cache must have a name");
        }
        this.b = str;
        this.c = j;
        this.d = z;
    }

    protected void finalize() throws Throwable {
        super.finalize();
        b();
    }

    public void a() {
        try {
            File file = new File(ct.a(this.b), "canary");
            if (!ho.a(file) || (!file.exists() && !file.createNewFile())) {
                throw new IOException("Could not create canary file.");
            }
            this.e = new FileObserver(file.getAbsolutePath()) { // from class: com.flurry.sdk.al.1
                @Override // android.os.FileObserver
                public void onEvent(int i, String str) {
                    if ((i & 2048) != 0 || (i & 1024) != 0) {
                        fp.a().f().post(new hq() { // from class: com.flurry.sdk.al.1.1
                            @Override // com.flurry.sdk.hq
                            public void safeRun() {
                                if (al.this.f != null) {
                                    al.this.b();
                                    al.this.a();
                                }
                            }
                        });
                    }
                }
            };
            this.e.startWatching();
            this.f = kb.a(ct.a(this.b), 1, 1, this.c);
        } catch (IOException e) {
            gd.a(3, a, "Could not open cache: " + this.b);
        }
    }

    public void b() {
        if (this.e != null) {
            this.e.stopWatching();
            this.e = null;
        }
        hp.a(this.f);
    }

    public b a(String str) {
        b bVar;
        if (this.f == null || str == null) {
            return null;
        }
        try {
            kb.c cVarA = this.f.a(ct.c(str));
            bVar = cVarA != null ? new b(cVarA, this.d) : null;
        } catch (IOException e) {
            gd.a(3, a, "Exception during getReader for cache: " + this.b + " key: " + str, e);
            hp.a((Closeable) null);
            bVar = null;
        }
        return bVar;
    }

    public c b(String str) {
        c cVar;
        if (this.f == null || str == null) {
            return null;
        }
        try {
            kb.a aVarB = this.f.b(ct.c(str));
            cVar = aVarB != null ? new c(aVarB, this.d) : null;
        } catch (IOException e) {
            gd.a(3, a, "Exception during getWriter for cache: " + this.b + " key: " + str, e);
            hp.a((Closeable) null);
            cVar = null;
        }
        return cVar;
    }

    public boolean c(String str) {
        if (this.f == null || str == null) {
            return false;
        }
        try {
            return this.f.c(ct.c(str));
        } catch (IOException e) {
            gd.a(3, a, "Exception during remove for cache: " + this.b + " key: " + str, e);
            return false;
        }
    }

    public boolean d(String str) {
        boolean z = false;
        if (this.f != null && str != null) {
            try {
                try {
                    kb.c cVarA = this.f.a(ct.c(str));
                    z = cVarA != null;
                    hp.a(cVarA);
                } catch (IOException e) {
                    gd.a(3, a, "Exception during exists for cache: " + this.b, e);
                    hp.a((Closeable) null);
                }
            } catch (Throwable th) {
                hp.a((Closeable) null);
                throw th;
            }
        }
        return z;
    }

    public void c() {
        d();
        a();
    }

    public void d() {
        if (this.f != null) {
            try {
                this.f.a();
            } catch (IOException e) {
                gd.a(3, a, "Exception during delete for cache: " + this.b, e);
            }
        }
    }
}
