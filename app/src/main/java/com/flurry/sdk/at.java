package com.flurry.sdk;

import android.text.TextUtils;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public class at {
    private static int a = 1;
    private int b;
    private long c;
    private String d;
    private List<as> e;

    public static class a implements gx<at> {
        private final as.a a;

        public a(as.a aVar) {
            this.a = aVar;
        }

        @Override // com.flurry.sdk.gx
        public void a(OutputStream outputStream, at atVar) throws IOException {
            if (outputStream != null && atVar != null && this.a != null) {
                DataOutputStream dataOutputStream = new DataOutputStream(outputStream) { // from class: com.flurry.sdk.at.a.1
                    @Override // java.io.FilterOutputStream, java.io.OutputStream, java.io.Closeable, java.lang.AutoCloseable
                    public void close() {
                    }
                };
                dataOutputStream.writeInt(atVar.b);
                dataOutputStream.writeLong(atVar.c);
                dataOutputStream.writeUTF(atVar.d == null ? "" : atVar.d);
                dataOutputStream.writeShort(atVar.e.size());
                Iterator it = atVar.e.iterator();
                while (it.hasNext()) {
                    this.a.a((OutputStream) dataOutputStream, (as) it.next());
                }
                dataOutputStream.flush();
            }
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // com.flurry.sdk.gx
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public at b(InputStream inputStream) throws IOException {
            Object[] objArr = 0;
            if (inputStream == null || this.a == null) {
                return null;
            }
            DataInputStream dataInputStream = new DataInputStream(inputStream) { // from class: com.flurry.sdk.at.a.2
                @Override // java.io.FilterInputStream, java.io.InputStream, java.io.Closeable, java.lang.AutoCloseable
                public void close() {
                }
            };
            at atVar = new at();
            atVar.b = dataInputStream.readInt();
            atVar.c = dataInputStream.readLong();
            String utf = dataInputStream.readUTF();
            atVar.d = utf.equals("") ? null : utf;
            atVar.e = new ArrayList();
            short s = dataInputStream.readShort();
            for (short s2 = 0; s2 < s; s2 = (short) (s2 + 1)) {
                atVar.e.add(this.a.b(dataInputStream));
            }
            return atVar;
        }
    }

    private at() {
    }

    public at(String str) {
        int i = a;
        a = i + 1;
        this.b = i;
        this.c = fd.a().c();
        this.d = str;
        this.e = new ArrayList();
    }

    public void a(as asVar) {
        this.e.add(asVar);
    }

    public int a() {
        return this.b;
    }

    public String b() {
        return this.d;
    }

    public long c() {
        return this.c;
    }

    public List<as> d() {
        return this.e;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof at)) {
            return false;
        }
        at atVar = (at) obj;
        if (this.b == atVar.b && this.c == atVar.c && TextUtils.equals(this.d, atVar.d)) {
            if (this.e == atVar.e) {
                return true;
            }
            if (this.e != null && this.e.equals(atVar.e)) {
                return true;
            }
        }
        return false;
    }

    public int hashCode() {
        int iHashCode = (int) (((long) (17 ^ this.b)) ^ this.c);
        if (this.d != null) {
            iHashCode ^= this.d.hashCode();
        }
        if (this.e != null) {
            return iHashCode ^ this.e.hashCode();
        }
        return iHashCode;
    }
}
