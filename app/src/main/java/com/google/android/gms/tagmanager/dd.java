package com.google.android.gms.tagmanager;

import java.util.Map;

/* JADX INFO: loaded from: classes.dex */
abstract class dd extends cd {
    public dd(String str) {
        super(str);
    }

    @Override // com.google.android.gms.tagmanager.cd
    protected boolean a(com.google.android.gms.internal.d.a aVar, com.google.android.gms.internal.d.a aVar2, Map<String, com.google.android.gms.internal.d.a> map) {
        String strJ = di.j(aVar);
        String strJ2 = di.j(aVar2);
        if (strJ == di.ra() || strJ2 == di.ra()) {
            return false;
        }
        return a(strJ, strJ2, map);
    }

    protected abstract boolean a(String str, String str2, Map<String, com.google.android.gms.internal.d.a> map);
}
