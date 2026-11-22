package com.flurry.sdk;

import java.io.EOFException;
import java.io.IOException;
import java.io.Writer;

/* JADX INFO: loaded from: classes.dex */
public final class jh {
    public static ig a(jx jxVar) throws ik {
        boolean z = true;
        try {
            jxVar.f();
            z = false;
            return jv.P.b(jxVar);
        } catch (ka e) {
            throw new io(e);
        } catch (EOFException e2) {
            if (z) {
                return ii.a;
            }
            throw new io(e2);
        } catch (IOException e3) {
            throw new ih(e3);
        } catch (NumberFormatException e4) {
            throw new io(e4);
        }
    }

    public static void a(ig igVar, jz jzVar) throws IOException {
        jv.P.a(jzVar, igVar);
    }

    public static Writer a(Appendable appendable) {
        return appendable instanceof Writer ? (Writer) appendable : new a(appendable);
    }

    static final class a extends Writer {
        private final Appendable a;
        private final C0032a b;

        private a(Appendable appendable) {
            this.b = new C0032a();
            this.a = appendable;
        }

        @Override // java.io.Writer
        public void write(char[] cArr, int i, int i2) throws IOException {
            this.b.a = cArr;
            this.a.append(this.b, i, i + i2);
        }

        @Override // java.io.Writer
        public void write(int i) throws IOException {
            this.a.append((char) i);
        }

        @Override // java.io.Writer, java.io.Flushable
        public void flush() {
        }

        @Override // java.io.Writer, java.io.Closeable, java.lang.AutoCloseable
        public void close() {
        }

        /* JADX INFO: renamed from: com.flurry.sdk.jh$a$a, reason: collision with other inner class name */
        static class C0032a implements CharSequence {
            char[] a;

            C0032a() {
            }

            @Override // java.lang.CharSequence
            public int length() {
                return this.a.length;
            }

            @Override // java.lang.CharSequence
            public char charAt(int i) {
                return this.a[i];
            }

            @Override // java.lang.CharSequence
            public CharSequence subSequence(int i, int i2) {
                return new String(this.a, i, i2 - i);
            }
        }
    }
}
