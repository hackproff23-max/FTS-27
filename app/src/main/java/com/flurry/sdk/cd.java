package com.flurry.sdk;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/* JADX INFO: loaded from: classes.dex */
public class cd extends gn {
    private String a;
    private String b;
    private boolean c;

    public static class b implements gx<cd> {
        @Override // com.flurry.sdk.gx
        public void a(OutputStream outputStream, cd cdVar) throws IOException {
            throw new UnsupportedOperationException("Serialization not supported");
        }

        @Override // com.flurry.sdk.gx
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public cd b(InputStream inputStream) throws IOException {
            if (inputStream == null) {
                return null;
            }
            DataInputStream dataInputStream = new DataInputStream(inputStream) { // from class: com.flurry.sdk.cd.b.1
                @Override // java.io.FilterInputStream, java.io.InputStream, java.io.Closeable, java.lang.AutoCloseable
                public void close() {
                }
            };
            cd cdVar = new cd();
            cdVar.a = dataInputStream.readUTF();
            cdVar.b = dataInputStream.readUTF();
            cdVar.a(dataInputStream.readUTF());
            cdVar.a(dataInputStream.readLong());
            cdVar.c = dataInputStream.readBoolean();
            cdVar.a(dataInputStream.readBoolean());
            cdVar.a(dataInputStream.readInt());
            return cdVar;
        }
    }

    public static class a implements gx<cd> {
        @Override // com.flurry.sdk.gx
        public void a(OutputStream outputStream, cd cdVar) throws IOException {
            if (outputStream != null && cdVar != null) {
                DataOutputStream dataOutputStream = new DataOutputStream(outputStream) { // from class: com.flurry.sdk.cd.a.1
                    @Override // java.io.FilterOutputStream, java.io.OutputStream, java.io.Closeable, java.lang.AutoCloseable
                    public void close() {
                    }
                };
                dataOutputStream.writeLong(cdVar.d());
                dataOutputStream.writeBoolean(cdVar.e());
                dataOutputStream.writeInt(cdVar.f());
                dataOutputStream.writeUTF(cdVar.g());
                dataOutputStream.writeUTF(cdVar.h());
                dataOutputStream.writeUTF(cdVar.a);
                dataOutputStream.writeUTF(cdVar.b);
                dataOutputStream.writeBoolean(cdVar.c);
                dataOutputStream.flush();
            }
        }

        @Override // com.flurry.sdk.gx
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public cd b(InputStream inputStream) throws IOException {
            if (inputStream == null) {
                return null;
            }
            DataInputStream dataInputStream = new DataInputStream(inputStream) { // from class: com.flurry.sdk.cd.a.2
                @Override // java.io.FilterInputStream, java.io.InputStream, java.io.Closeable, java.lang.AutoCloseable
                public void close() {
                }
            };
            cd cdVar = new cd();
            cdVar.a(dataInputStream.readLong());
            cdVar.a(dataInputStream.readBoolean());
            cdVar.a(dataInputStream.readInt());
            cdVar.b(dataInputStream.readUTF());
            cdVar.c(dataInputStream.readUTF());
            cdVar.a = dataInputStream.readUTF();
            cdVar.b = dataInputStream.readUTF();
            cdVar.c = dataInputStream.readBoolean();
            return cdVar;
        }
    }

    private cd() {
    }

    public cd(String str, String str2, String str3, long j, boolean z) {
        a(str3);
        a(j);
        this.a = str;
        this.b = str2;
        this.c = z;
    }

    public String a() {
        return this.a;
    }

    public String b() {
        return this.b;
    }

    public boolean c() {
        return this.c;
    }
}
