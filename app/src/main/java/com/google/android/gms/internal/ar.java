package com.google.android.gms.internal;

import android.support.v4.view.MotionEventCompat;
import java.security.MessageDigest;

/* JADX INFO: loaded from: classes.dex */
public class ar extends ao {
    private MessageDigest nZ;

    byte[] a(String[] strArr) {
        byte[] bArr = new byte[strArr.length];
        for (int i = 0; i < strArr.length; i++) {
            bArr[i] = e(aq.o(strArr[i]));
        }
        return bArr;
    }

    byte e(int i) {
        return (byte) ((((i & MotionEventCompat.ACTION_MASK) ^ ((65280 & i) >> 8)) ^ ((16711680 & i) >> 16)) ^ (((-16777216) & i) >> 24));
    }

    @Override // com.google.android.gms.internal.ao
    public byte[] l(String str) {
        byte[] bArr;
        byte[] bArrA = a(str.split(" "));
        this.nZ = bf();
        synchronized (this.mH) {
            if (this.nZ == null) {
                bArr = new byte[0];
            } else {
                this.nZ.reset();
                this.nZ.update(bArrA);
                byte[] bArrDigest = this.nZ.digest();
                bArr = new byte[bArrDigest.length <= 4 ? bArrDigest.length : 4];
                System.arraycopy(bArrDigest, 0, bArr, 0, bArr.length);
            }
        }
        return bArr;
    }
}
