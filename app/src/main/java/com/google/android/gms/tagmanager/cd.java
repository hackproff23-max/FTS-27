package com.google.android.gms.tagmanager;

import java.util.Iterator;
import java.util.Map;

/* JADX INFO: loaded from: classes.dex */
abstract class cd extends aj {
    private static final String arp = com.google.android.gms.internal.b.ARG0.toString();
    private static final String ask = com.google.android.gms.internal.b.ARG1.toString();

    public cd(String str) {
        super(str, arp, ask);
    }

    @Override // com.google.android.gms.tagmanager.aj
    public com.google.android.gms.internal.d.a B(Map<String, com.google.android.gms.internal.d.a> map) {
        Iterator<com.google.android.gms.internal.d.a> it = map.values().iterator();
        while (it.hasNext()) {
            if (it.next() == di.rb()) {
                return di.u(false);
            }
        }
        com.google.android.gms.internal.d.a aVar = map.get(arp);
        com.google.android.gms.internal.d.a aVar2 = map.get(ask);
        return di.u(Boolean.valueOf((aVar == null || aVar2 == null) ? false : a(aVar, aVar2, map)));
    }

    protected abstract boolean a(com.google.android.gms.internal.d.a aVar, com.google.android.gms.internal.d.a aVar2, Map<String, com.google.android.gms.internal.d.a> map);

    @Override // com.google.android.gms.tagmanager.aj
    public boolean pe() {
        return true;
    }
}
