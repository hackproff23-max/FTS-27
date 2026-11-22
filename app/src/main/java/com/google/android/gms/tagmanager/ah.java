package com.google.android.gms.tagmanager;

import java.util.Map;

/* JADX INFO: loaded from: classes.dex */
class ah extends aj {
    private static final String ID = com.google.android.gms.internal.a.EVENT.toString();
    private final ct aqo;

    public ah(ct ctVar) {
        super(ID, new String[0]);
        this.aqo = ctVar;
    }

    @Override // com.google.android.gms.tagmanager.aj
    public com.google.android.gms.internal.d.a B(Map<String, com.google.android.gms.internal.d.a> map) {
        String strQE = this.aqo.qE();
        return strQE == null ? di.rb() : di.u(strQE);
    }

    @Override // com.google.android.gms.tagmanager.aj
    public boolean pe() {
        return false;
    }
}
