package com.nuance.nmsp.client.sdk.oem;

import defpackage.bo;
import java.util.UUID;
import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;

/* JADX INFO: loaded from: classes.dex */
public final class g extends bo {
    @Override // defpackage.bo
    protected final String a() {
        return UUID.randomUUID().toString();
    }

    @Override // defpackage.bo
    protected final byte[] a(byte[] bArr, byte[] bArr2) {
        if (bArr == null || bArr2 == null) {
            return new byte[0];
        }
        try {
            SecretKeySpec secretKeySpec = new SecretKeySpec(bArr, "HmacSHA1");
            Mac mac = Mac.getInstance("HmacSHA1");
            mac.init(secretKeySpec);
            return mac.doFinal(bArr2);
        } catch (Exception e) {
            return new byte[0];
        }
    }
}
