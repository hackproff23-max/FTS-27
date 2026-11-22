package com.flurry.sdk;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/* JADX INFO: loaded from: classes.dex */
public class ae {
    private String a;
    private an b;
    private long c;
    private long d;
    private ah e;
    private long f;
    private long g;

    public static class a implements gx<ae> {
        @Override // com.flurry.sdk.gx
        public void a(OutputStream outputStream, ae aeVar) throws IOException {
            if (outputStream != null && aeVar != null) {
                DataOutputStream dataOutputStream = new DataOutputStream(outputStream) { // from class: com.flurry.sdk.ae.a.1
                    @Override // java.io.FilterOutputStream, java.io.OutputStream, java.io.Closeable, java.lang.AutoCloseable
                    public void close() {
                    }
                };
                dataOutputStream.writeUTF(aeVar.a);
                dataOutputStream.writeInt(aeVar.b.a());
                dataOutputStream.writeLong(aeVar.c);
                dataOutputStream.writeLong(aeVar.d);
                dataOutputStream.writeInt(aeVar.e.a());
                dataOutputStream.writeLong(aeVar.f);
                dataOutputStream.writeLong(aeVar.g);
                dataOutputStream.flush();
            }
        }

        @Override // com.flurry.sdk.gx
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public ae b(InputStream inputStream) throws IOException {
            if (inputStream == null) {
                return null;
            }
            DataInputStream dataInputStream = new DataInputStream(inputStream) { // from class: com.flurry.sdk.ae.a.2
                @Override // java.io.FilterInputStream, java.io.InputStream, java.io.Closeable, java.lang.AutoCloseable
                public void close() {
                }
            };
            ae aeVar = new ae();
            aeVar.a = dataInputStream.readUTF();
            aeVar.b = an.a(dataInputStream.readInt());
            aeVar.c = dataInputStream.readLong();
            aeVar.d = dataInputStream.readLong();
            aeVar.e = ah.a(dataInputStream.readInt());
            aeVar.f = dataInputStream.readLong();
            aeVar.g = dataInputStream.readLong();
            return aeVar;
        }
    }

    private ae() {
    }

    public ae(String str, an anVar, long j) {
        this.a = str;
        this.b = anVar;
        this.c = System.currentTimeMillis();
        this.d = System.currentTimeMillis();
        this.e = ah.NONE;
        this.f = j;
        this.g = -1L;
    }

    public String a() {
        return this.a;
    }

    public synchronized ah b() {
        return this.e;
    }

    public synchronized void a(ah ahVar) {
        this.e = ahVar;
    }

    public long c() {
        return this.f;
    }

    public boolean d() {
        return this.f > 0 && System.currentTimeMillis() > this.f;
    }

    public synchronized void e() {
        this.d = System.currentTimeMillis();
    }

    public long f() {
        return this.c;
    }

    public synchronized void a(long j) {
        this.g = j;
    }

    public String toString() {
        return "url: " + this.a + ", type:" + this.b + ", creation:" + this.c + ", accessed:" + this.d + ", status: " + this.e + ", expiration: " + this.f + ", size: " + this.g;
    }
}
