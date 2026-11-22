package com.google.android.gms.tagmanager;

import java.util.Map;

/* JADX INFO: loaded from: classes.dex */
class cc extends aj {
    private static final String ID = com.google.android.gms.internal.a.PLATFORM.toString();
    private static final com.google.android.gms.internal.d.a asj = di.u("Android");

    public cc() {
        super(ID, new String[0]);
    }

    @Override // com.google.android.gms.tagmanager.aj
    public com.google.android.gms.internal.d.a B(Map<String, com.google.android.gms.internal.d.a> map) {
        return asj;
    }

    @Override // com.google.android.gms.tagmanager.aj
    public boolean pe() {
        return true;
    }
}
