package com.flurry.sdk;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

/* JADX INFO: loaded from: classes.dex */
public class gh<ObjectType> {
    private static final String a = gh.class.getSimpleName();
    private static final byte[] b = {113, -92, -8, 125, 121, 107, -65, -61, -74, -114, -32, 0, -57, -87, -35, -56, -6, -52, 51, 126, -104, 49, 79, -52, 118, -84, 99, -52, -14, -126, -27, -64};
    private String c;
    private gx<ObjectType> d;

    public static void a(byte[] bArr) {
        if (bArr != null) {
            int length = bArr.length;
            int length2 = b.length;
            for (int i = 0; i < length; i++) {
                bArr[i] = (byte) ((bArr[i] ^ b[i % length2]) ^ ((i * 31) % 251));
            }
        }
    }

    public static void b(byte[] bArr) {
        a(bArr);
    }

    public static int c(byte[] bArr) {
        if (bArr == null) {
            return 0;
        }
        fv fvVar = new fv();
        fvVar.update(bArr);
        return fvVar.b();
    }

    public gh(String str, gx<ObjectType> gxVar) {
        this.c = str;
        this.d = gxVar;
    }

    public byte[] a(ObjectType objecttype) throws IOException {
        if (objecttype == null) {
            throw new IOException("Encoding: " + this.c + ": Nothing to encode");
        }
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        this.d.a(byteArrayOutputStream, objecttype);
        byte[] byteArray = byteArrayOutputStream.toByteArray();
        gd.a(3, a, "Encoding " + this.c + ": " + new String(byteArray));
        gv gvVar = new gv(new gt());
        ByteArrayOutputStream byteArrayOutputStream2 = new ByteArrayOutputStream();
        gvVar.a(byteArrayOutputStream2, byteArray);
        byte[] byteArray2 = byteArrayOutputStream2.toByteArray();
        a(byteArray2);
        return byteArray2;
    }

    public ObjectType d(byte[] bArr) throws IOException {
        if (bArr == null) {
            throw new IOException("Decoding: " + this.c + ": Nothing to decode");
        }
        b(bArr);
        byte[] bArr2 = (byte[]) new gv(new gt()).b(new ByteArrayInputStream(bArr));
        gd.a(3, a, "Decoding: " + this.c + ": " + new String(bArr2));
        return this.d.b(new ByteArrayInputStream(bArr2));
    }
}
