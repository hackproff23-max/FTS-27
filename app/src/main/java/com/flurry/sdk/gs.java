package com.flurry.sdk;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/* JADX INFO: loaded from: classes.dex */
public class gs {
    private String a;

    public static class a implements gx<gs> {
        @Override // com.flurry.sdk.gx
        public void a(OutputStream outputStream, gs gsVar) throws IOException {
            if (outputStream != null && gsVar != null) {
                DataOutputStream dataOutputStream = new DataOutputStream(outputStream) { // from class: com.flurry.sdk.gs.a.1
                    @Override // java.io.FilterOutputStream, java.io.OutputStream, java.io.Closeable, java.lang.AutoCloseable
                    public void close() {
                    }
                };
                dataOutputStream.writeUTF(gsVar.a);
                dataOutputStream.flush();
            }
        }

        @Override // com.flurry.sdk.gx
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public gs b(InputStream inputStream) throws IOException {
            if (inputStream == null) {
                return null;
            }
            DataInputStream dataInputStream = new DataInputStream(inputStream) { // from class: com.flurry.sdk.gs.a.2
                @Override // java.io.FilterInputStream, java.io.InputStream, java.io.Closeable, java.lang.AutoCloseable
                public void close() {
                }
            };
            gs gsVar = new gs();
            gsVar.a = dataInputStream.readUTF();
            return gsVar;
        }
    }

    private gs() {
    }

    public gs(String str) {
        this.a = str;
    }

    public String a() {
        return this.a;
    }
}
