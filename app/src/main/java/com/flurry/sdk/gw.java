package com.flurry.sdk;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public class gw<T> implements gx<List<T>> {
    gx<T> a;

    public gw(gx<T> gxVar) {
        if (gxVar == null) {
            throw new IllegalArgumentException("recordSerializer cannot be null");
        }
        this.a = gxVar;
    }

    @Override // com.flurry.sdk.gx
    public void a(OutputStream outputStream, List<T> list) throws IOException {
        if (outputStream != null) {
            DataOutputStream dataOutputStream = new DataOutputStream(outputStream) { // from class: com.flurry.sdk.gw.1
                @Override // java.io.FilterOutputStream, java.io.OutputStream, java.io.Closeable, java.lang.AutoCloseable
                public void close() {
                }
            };
            int size = list != null ? list.size() : 0;
            dataOutputStream.writeInt(size);
            for (int i = 0; i < size; i++) {
                this.a.a(outputStream, list.get(i));
            }
            dataOutputStream.flush();
        }
    }

    @Override // com.flurry.sdk.gx
    /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
    public List<T> b(InputStream inputStream) throws IOException {
        if (inputStream == null) {
            return null;
        }
        int i = new DataInputStream(inputStream) { // from class: com.flurry.sdk.gw.2
            @Override // java.io.FilterInputStream, java.io.InputStream, java.io.Closeable, java.lang.AutoCloseable
            public void close() {
            }
        }.readInt();
        ArrayList arrayList = new ArrayList(i);
        for (int i2 = 0; i2 < i; i2++) {
            T tB = this.a.b(inputStream);
            if (tB == null) {
                throw new IOException("Missing record.");
            }
            arrayList.add(tB);
        }
        return arrayList;
    }
}
