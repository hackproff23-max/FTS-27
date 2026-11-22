package com.google.android.gms.tagmanager;

import java.util.Map;

/* JADX INFO: loaded from: classes.dex */
class bj extends aj {
    private static final String ID = com.google.android.gms.internal.a.LOWERCASE_STRING.toString();
    private static final String arp = com.google.android.gms.internal.b.ARG0.toString();

    public bj() {
        super(ID, arp);
    }

    @Override // com.google.android.gms.tagmanager.aj
    public com.google.android.gms.internal.d.a B(Map<String, com.google.android.gms.internal.d.a> map) {
        return di.u(di.j(map.get(arp)).toLowerCase());
    }

    @Override // com.google.android.gms.tagmanager.aj
    public boolean pe() {
        return true;
    }
}
