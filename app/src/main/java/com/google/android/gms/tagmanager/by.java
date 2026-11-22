package com.google.android.gms.tagmanager;

import java.util.Map;

/* JADX INFO: loaded from: classes.dex */
abstract class by extends cd {
    public by(String str) {
        super(str);
    }

    @Override // com.google.android.gms.tagmanager.cd
    protected boolean a(com.google.android.gms.internal.d.a aVar, com.google.android.gms.internal.d.a aVar2, Map<String, com.google.android.gms.internal.d.a> map) {
        dh dhVarK = di.k(aVar);
        dh dhVarK2 = di.k(aVar2);
        if (dhVarK == di.qZ() || dhVarK2 == di.qZ()) {
            return false;
        }
        return a(dhVarK, dhVarK2, map);
    }

    protected abstract boolean a(dh dhVar, dh dhVar2, Map<String, com.google.android.gms.internal.d.a> map);
}
