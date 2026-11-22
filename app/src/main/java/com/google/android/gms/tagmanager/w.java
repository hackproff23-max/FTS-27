package com.google.android.gms.tagmanager;

import java.util.List;
import java.util.Map;

/* JADX INFO: loaded from: classes.dex */
class w extends dg {
    private static final String ID = com.google.android.gms.internal.a.DATA_LAYER_WRITE.toString();
    private static final String VALUE = com.google.android.gms.internal.b.VALUE.toString();
    private static final String ark = com.google.android.gms.internal.b.CLEAR_PERSISTENT_DATA_LAYER_PREFIX.toString();
    private final DataLayer aqn;

    public w(DataLayer dataLayer) {
        super(ID, VALUE);
        this.aqn = dataLayer;
    }

    private void a(com.google.android.gms.internal.d.a aVar) {
        String strJ;
        if (aVar == null || aVar == di.qV() || (strJ = di.j(aVar)) == di.ra()) {
            return;
        }
        this.aqn.cx(strJ);
    }

    private void b(com.google.android.gms.internal.d.a aVar) {
        if (aVar == null || aVar == di.qV()) {
            return;
        }
        Object objO = di.o(aVar);
        if (objO instanceof List) {
            for (Object obj : (List) objO) {
                if (obj instanceof Map) {
                    this.aqn.push((Map) obj);
                }
            }
        }
    }

    @Override // com.google.android.gms.tagmanager.dg
    public void D(Map<String, com.google.android.gms.internal.d.a> map) {
        b(map.get(VALUE));
        a(map.get(ark));
    }
}
