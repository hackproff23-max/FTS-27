package com.flurry.sdk;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/* JADX INFO: loaded from: classes.dex */
public class gt implements gx<byte[]> {
    @Override // com.flurry.sdk.gx
    public void a(OutputStream outputStream, byte[] bArr) throws IOException {
        if (outputStream != null && bArr != null) {
            outputStream.write(bArr, 0, bArr.length);
        }
    }

    @Override // com.flurry.sdk.gx
    /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
    public byte[] b(InputStream inputStream) throws IOException {
        if (inputStream == null) {
            return null;
        }
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        hp.a(inputStream, byteArrayOutputStream);
        return byteArrayOutputStream.toByteArray();
    }
}
