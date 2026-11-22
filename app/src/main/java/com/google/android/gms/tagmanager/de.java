package com.google.android.gms.tagmanager;

import java.util.Map;

/* JADX INFO: loaded from: classes.dex */
class de extends aj {
    private static final String ID = com.google.android.gms.internal.a.TIME.toString();

    public de() {
        super(ID, new String[0]);
    }

    @Override // com.google.android.gms.tagmanager.aj
    public com.google.android.gms.internal.d.a B(Map<String, com.google.android.gms.internal.d.a> map) {
        return di.u(Long.valueOf(System.currentTimeMillis()));
    }

    @Override // com.google.android.gms.tagmanager.aj
    public boolean pe() {
        return false;
    }
}
