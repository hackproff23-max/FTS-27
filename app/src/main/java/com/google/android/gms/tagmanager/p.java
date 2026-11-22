package com.google.android.gms.tagmanager;

import java.util.Map;

/* JADX INFO: loaded from: classes.dex */
class p extends aj {
    private static final String ID = com.google.android.gms.internal.a.CONTAINER_VERSION.toString();
    private final String TU;

    public p(String str) {
        super(ID, new String[0]);
        this.TU = str;
    }

    @Override // com.google.android.gms.tagmanager.aj
    public com.google.android.gms.internal.d.a B(Map<String, com.google.android.gms.internal.d.a> map) {
        return this.TU == null ? di.rb() : di.u(this.TU);
    }

    @Override // com.google.android.gms.tagmanager.aj
    public boolean pe() {
        return true;
    }
}
