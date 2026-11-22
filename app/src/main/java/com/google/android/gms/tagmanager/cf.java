package com.google.android.gms.tagmanager;

import java.util.Map;

/* JADX INFO: loaded from: classes.dex */
class cf extends aj {
    private static final String ID = com.google.android.gms.internal.a.RANDOM.toString();
    private static final String asu = com.google.android.gms.internal.b.MIN.toString();
    private static final String asv = com.google.android.gms.internal.b.MAX.toString();

    public cf() {
        super(ID, new String[0]);
    }

    /* JADX WARN: Code duplicated, block: B:18:0x005c  */
    @Override // com.google.android.gms.tagmanager.aj
    public com.google.android.gms.internal.d.a B(Map<String, com.google.android.gms.internal.d.a> map) {
        double dDoubleValue;
        double d;
        com.google.android.gms.internal.d.a aVar = map.get(asu);
        com.google.android.gms.internal.d.a aVar2 = map.get(asv);
        if (aVar == null || aVar == di.rb() || aVar2 == null || aVar2 == di.rb()) {
            dDoubleValue = 2.147483647E9d;
            d = 0.0d;
        } else {
            dh dhVarK = di.k(aVar);
            dh dhVarK2 = di.k(aVar2);
            if (dhVarK == di.qZ() || dhVarK2 == di.qZ()) {
                dDoubleValue = 2.147483647E9d;
                d = 0.0d;
            } else {
                double dDoubleValue2 = dhVarK.doubleValue();
                dDoubleValue = dhVarK2.doubleValue();
                if (dDoubleValue2 <= dDoubleValue) {
                    d = dDoubleValue2;
                } else {
                    dDoubleValue = 2.147483647E9d;
                    d = 0.0d;
                }
            }
        }
        return di.u(Long.valueOf(Math.round(((dDoubleValue - d) * Math.random()) + d)));
    }

    @Override // com.google.android.gms.tagmanager.aj
    public boolean pe() {
        return false;
    }
}
