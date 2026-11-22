package com.flurry.sdk;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.UUID;

/* JADX INFO: loaded from: classes.dex */
public class gq {
    private static final String b = gq.class.getSimpleName();
    String a;
    private byte[] c;

    public static class a implements gx<gq> {
        @Override // com.flurry.sdk.gx
        public void a(OutputStream outputStream, gq gqVar) throws IOException {
            if (outputStream != null && gqVar != null) {
                DataOutputStream dataOutputStream = new DataOutputStream(outputStream) { // from class: com.flurry.sdk.gq.a.1
                    @Override // java.io.FilterOutputStream, java.io.OutputStream, java.io.Closeable, java.lang.AutoCloseable
                    public void close() {
                    }
                };
                dataOutputStream.writeShort(gqVar.c.length);
                dataOutputStream.write(gqVar.c);
                dataOutputStream.writeShort(0);
                dataOutputStream.flush();
            }
        }

        @Override // com.flurry.sdk.gx
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public gq b(InputStream inputStream) throws IOException {
            if (inputStream == null) {
                return null;
            }
            DataInputStream dataInputStream = new DataInputStream(inputStream) { // from class: com.flurry.sdk.gq.a.2
                @Override // java.io.FilterInputStream, java.io.InputStream, java.io.Closeable, java.lang.AutoCloseable
                public void close() {
                }
            };
            gq gqVar = new gq();
            int i = dataInputStream.readShort();
            if (i == 0) {
                return null;
            }
            gqVar.c = new byte[i];
            dataInputStream.readFully(gqVar.c);
            if (dataInputStream.readUnsignedShort() == 0) {
            }
            return gqVar;
        }
    }

    private gq() {
        this.a = null;
        this.c = null;
    }

    public gq(byte[] bArr) {
        this.a = null;
        this.c = null;
        this.a = UUID.randomUUID().toString();
        this.c = bArr;
    }

    public String a() {
        return this.a;
    }

    public byte[] b() {
        return this.c;
    }

    public static String a(String str) {
        return ".yflurrydatasenderblock." + str;
    }
}
