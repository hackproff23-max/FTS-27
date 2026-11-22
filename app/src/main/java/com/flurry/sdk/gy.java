package com.flurry.sdk;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/* JADX INFO: loaded from: classes.dex */
public class gy implements gx<String> {
    @Override // com.flurry.sdk.gx
    public void a(OutputStream outputStream, String str) throws IOException {
        if (outputStream != null && str != null) {
            byte[] bytes = str.getBytes("utf-8");
            outputStream.write(bytes, 0, bytes.length);
        }
    }

    @Override // com.flurry.sdk.gx
    /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
    public String b(InputStream inputStream) throws IOException {
        if (inputStream == null) {
            return null;
        }
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        hp.a(inputStream, byteArrayOutputStream);
        return byteArrayOutputStream.toString();
    }
}
