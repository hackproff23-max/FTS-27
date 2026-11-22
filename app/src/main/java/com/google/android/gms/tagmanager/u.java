package com.google.android.gms.tagmanager;

import java.util.Map;

/* JADX INFO: loaded from: classes.dex */
class u extends aj {
    private static final String ID = com.google.android.gms.internal.a.CUSTOM_VAR.toString();
    private static final String NAME = com.google.android.gms.internal.b.NAME.toString();
    private static final String aqZ = com.google.android.gms.internal.b.DEFAULT_VALUE.toString();
    private final DataLayer aqn;

    public u(DataLayer dataLayer) {
        super(ID, NAME);
        this.aqn = dataLayer;
    }

    @Override // com.google.android.gms.tagmanager.aj
    public com.google.android.gms.internal.d.a B(Map<String, com.google.android.gms.internal.d.a> map) {
        Object obj = this.aqn.get(di.j(map.get(NAME)));
        if (obj != null) {
            return di.u(obj);
        }
        com.google.android.gms.internal.d.a aVar = map.get(aqZ);
        return aVar != null ? aVar : di.rb();
    }

    @Override // com.google.android.gms.tagmanager.aj
    public boolean pe() {
        return false;
    }
}
