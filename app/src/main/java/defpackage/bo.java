package defpackage;

import com.nuance.nmsp.client.sdk.oem.g;

/* JADX INFO: loaded from: classes.dex */
public abstract class bo {
    private static bo a = new g();

    public static String b() {
        return a.a();
    }

    public static byte[] b(byte[] bArr, byte[] bArr2) {
        return a.a(bArr, bArr2);
    }

    protected abstract String a();

    protected abstract byte[] a(byte[] bArr, byte[] bArr2);
}
