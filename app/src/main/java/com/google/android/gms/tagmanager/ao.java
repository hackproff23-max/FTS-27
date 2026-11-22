package com.google.android.gms.tagmanager;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Map;

/* JADX INFO: loaded from: classes.dex */
class ao extends aj {
    private static final String ID = com.google.android.gms.internal.a.HASH.toString();
    private static final String arp = com.google.android.gms.internal.b.ARG0.toString();
    private static final String arv = com.google.android.gms.internal.b.ALGORITHM.toString();
    private static final String arr = com.google.android.gms.internal.b.INPUT_FORMAT.toString();

    public ao() {
        super(ID, arp);
    }

    private byte[] d(String str, byte[] bArr) throws NoSuchAlgorithmException {
        MessageDigest messageDigest = MessageDigest.getInstance(str);
        messageDigest.update(bArr);
        return messageDigest.digest();
    }

    @Override // com.google.android.gms.tagmanager.aj
    public com.google.android.gms.internal.d.a B(Map<String, com.google.android.gms.internal.d.a> map) {
        byte[] bArrCo;
        com.google.android.gms.internal.d.a aVar = map.get(arp);
        if (aVar == null || aVar == di.rb()) {
            return di.rb();
        }
        String strJ = di.j(aVar);
        com.google.android.gms.internal.d.a aVar2 = map.get(arv);
        String strJ2 = aVar2 == null ? "MD5" : di.j(aVar2);
        com.google.android.gms.internal.d.a aVar3 = map.get(arr);
        String strJ3 = aVar3 == null ? "text" : di.j(aVar3);
        if ("text".equals(strJ3)) {
            bArrCo = strJ.getBytes();
        } else {
            if (!"base16".equals(strJ3)) {
                bh.T("Hash: unknown input format: " + strJ3);
                return di.rb();
            }
            bArrCo = j.co(strJ);
        }
        try {
            return di.u(j.d(d(strJ2, bArrCo)));
        } catch (NoSuchAlgorithmException e) {
            bh.T("Hash: unknown algorithm: " + strJ2);
            return di.rb();
        }
    }

    @Override // com.google.android.gms.tagmanager.aj
    public boolean pe() {
        return true;
    }
}
