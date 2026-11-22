package com.google.android.gms.tagmanager;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

/* JADX INFO: loaded from: classes.dex */
class dm {
    private static bz<com.google.android.gms.internal.d.a> a(bz<com.google.android.gms.internal.d.a> bzVar) {
        try {
            return new bz<>(di.u(dg(di.j(bzVar.getObject()))), bzVar.pX());
        } catch (UnsupportedEncodingException e) {
            bh.b("Escape URI: unsupported encoding", e);
            return bzVar;
        }
    }

    private static bz<com.google.android.gms.internal.d.a> a(bz<com.google.android.gms.internal.d.a> bzVar, int i) {
        if (!q(bzVar.getObject())) {
            bh.T("Escaping can only be applied to strings.");
            return bzVar;
        }
        switch (i) {
            case 12:
                return a(bzVar);
            default:
                bh.T("Unsupported Value Escaping: " + i);
                return bzVar;
        }
    }

    static bz<com.google.android.gms.internal.d.a> a(bz<com.google.android.gms.internal.d.a> bzVar, int... iArr) {
        for (int i : iArr) {
            bzVar = a(bzVar, i);
        }
        return bzVar;
    }

    static String dg(String str) throws UnsupportedEncodingException {
        return URLEncoder.encode(str, "UTF-8").replaceAll("\\+", "%20");
    }

    private static boolean q(com.google.android.gms.internal.d.a aVar) {
        return di.o(aVar) instanceof String;
    }
}
