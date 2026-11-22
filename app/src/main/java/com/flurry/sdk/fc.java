package com.flurry.sdk;

import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public class fc {
    private static final String a = fc.class.getSimpleName();
    private boolean b;
    private long c;
    private final List<ez> d = new ArrayList();

    public static class a implements gx<fc> {
        @Override // com.flurry.sdk.gx
        public void a(OutputStream outputStream, fc fcVar) throws IOException {
            throw new UnsupportedOperationException("Serialization not supported");
        }

        @Override // com.flurry.sdk.gx
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public fc b(InputStream inputStream) throws IOException {
            if (inputStream == null) {
                return null;
            }
            DataInputStream dataInputStream = new DataInputStream(inputStream) { // from class: com.flurry.sdk.fc.a.1
                @Override // java.io.FilterInputStream, java.io.InputStream, java.io.Closeable, java.lang.AutoCloseable
                public void close() {
                }
            };
            fc fcVar = new fc();
            dataInputStream.readUTF();
            dataInputStream.readUTF();
            fcVar.b = dataInputStream.readBoolean();
            fcVar.c = dataInputStream.readLong();
            while (true) {
                int unsignedShort = dataInputStream.readUnsignedShort();
                if (unsignedShort != 0) {
                    byte[] bArr = new byte[unsignedShort];
                    dataInputStream.readFully(bArr);
                    fcVar.d.add(0, new ez(bArr));
                } else {
                    return fcVar;
                }
            }
        }
    }

    public boolean a() {
        return this.b;
    }

    public long b() {
        return this.c;
    }

    public List<ez> c() {
        return Collections.unmodifiableList(this.d);
    }
}
