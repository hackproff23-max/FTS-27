package com.google.android.gms.tagmanager;

import android.util.Base64;
import java.util.Map;

/* JADX INFO: loaded from: classes.dex */
class ac extends aj {
    private static final String ID = com.google.android.gms.internal.a.ENCODE.toString();
    private static final String arp = com.google.android.gms.internal.b.ARG0.toString();
    private static final String arq = com.google.android.gms.internal.b.NO_PADDING.toString();
    private static final String arr = com.google.android.gms.internal.b.INPUT_FORMAT.toString();
    private static final String ars = com.google.android.gms.internal.b.OUTPUT_FORMAT.toString();

    public ac() {
        super(ID, arp);
    }

    @Override // com.google.android.gms.tagmanager.aj
    public com.google.android.gms.internal.d.a B(Map<String, com.google.android.gms.internal.d.a> map) {
        byte[] bArrDecode;
        String strEncodeToString;
        com.google.android.gms.internal.d.a aVar = map.get(arp);
        if (aVar == null || aVar == di.rb()) {
            return di.rb();
        }
        String strJ = di.j(aVar);
        com.google.android.gms.internal.d.a aVar2 = map.get(arr);
        String strJ2 = aVar2 == null ? "text" : di.j(aVar2);
        com.google.android.gms.internal.d.a aVar3 = map.get(ars);
        String strJ3 = aVar3 == null ? "base16" : di.j(aVar3);
        com.google.android.gms.internal.d.a aVar4 = map.get(arq);
        int i = (aVar4 == null || !di.n(aVar4).booleanValue()) ? 2 : 3;
        try {
            if ("text".equals(strJ2)) {
                bArrDecode = strJ.getBytes();
            } else if ("base16".equals(strJ2)) {
                bArrDecode = j.co(strJ);
            } else if ("base64".equals(strJ2)) {
                bArrDecode = Base64.decode(strJ, i);
            } else {
                if (!"base64url".equals(strJ2)) {
                    bh.T("Encode: unknown input format: " + strJ2);
                    return di.rb();
                }
                bArrDecode = Base64.decode(strJ, i | 8);
            }
            if ("base16".equals(strJ3)) {
                strEncodeToString = j.d(bArrDecode);
            } else if ("base64".equals(strJ3)) {
                strEncodeToString = Base64.encodeToString(bArrDecode, i);
            } else {
                if (!"base64url".equals(strJ3)) {
                    bh.T("Encode: unknown output format: " + strJ3);
                    return di.rb();
                }
                strEncodeToString = Base64.encodeToString(bArrDecode, i | 8);
            }
            return di.u(strEncodeToString);
        } catch (IllegalArgumentException e) {
            bh.T("Encode: invalid input:");
            return di.rb();
        }
    }

    @Override // com.google.android.gms.tagmanager.aj
    public boolean pe() {
        return true;
    }
}
