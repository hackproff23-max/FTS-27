package com.flurry.sdk;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/* JADX INFO: loaded from: classes.dex */
public class gz<T> implements gx<T> {
    private final String a;
    private final int b;
    private final ha<T> c;

    public gz(String str, int i, ha<T> haVar) {
        this.a = str;
        this.b = i;
        this.c = haVar;
    }

    @Override // com.flurry.sdk.gx
    public void a(OutputStream outputStream, T t) throws IOException {
        if (outputStream != null && this.c != null) {
            DataOutputStream dataOutputStream = new DataOutputStream(outputStream) { // from class: com.flurry.sdk.gz.1
                @Override // java.io.FilterOutputStream, java.io.OutputStream, java.io.Closeable, java.lang.AutoCloseable
                public void close() {
                }
            };
            dataOutputStream.writeUTF(this.a);
            dataOutputStream.writeInt(this.b);
            gx<T> gxVarA = this.c.a(this.b);
            if (gxVarA == null) {
                throw new IOException("No serializer for version: " + this.b);
            }
            gxVarA.a(dataOutputStream, t);
            dataOutputStream.flush();
        }
    }

    @Override // com.flurry.sdk.gx
    public T b(InputStream inputStream) throws IOException {
        if (inputStream == null || this.c == null) {
            return null;
        }
        DataInputStream dataInputStream = new DataInputStream(inputStream) { // from class: com.flurry.sdk.gz.2
            @Override // java.io.FilterInputStream, java.io.InputStream, java.io.Closeable, java.lang.AutoCloseable
            public void close() {
            }
        };
        String utf = dataInputStream.readUTF();
        if (!this.a.equals(utf)) {
            throw new IOException("Signature: " + utf + " is invalid");
        }
        int i = dataInputStream.readInt();
        gx<T> gxVarA = this.c.a(i);
        if (gxVarA == null) {
            throw new IOException("No serializer for version: " + i);
        }
        return gxVarA.b(dataInputStream);
    }
}
