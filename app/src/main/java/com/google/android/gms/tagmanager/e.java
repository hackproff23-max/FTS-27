package com.google.android.gms.tagmanager;

import android.content.Context;
import java.util.Map;

/* JADX INFO: loaded from: classes.dex */
class e extends aj {
    private static final String ID = com.google.android.gms.internal.a.ADWORDS_CLICK_REFERRER.toString();
    private static final String aqd = com.google.android.gms.internal.b.COMPONENT.toString();
    private static final String aqe = com.google.android.gms.internal.b.CONVERSION_ID.toString();
    private final Context lM;

    public e(Context context) {
        super(ID, aqe);
        this.lM = context;
    }

    @Override // com.google.android.gms.tagmanager.aj
    public com.google.android.gms.internal.d.a B(Map<String, com.google.android.gms.internal.d.a> map) {
        com.google.android.gms.internal.d.a aVar = map.get(aqe);
        if (aVar == null) {
            return di.rb();
        }
        String strJ = di.j(aVar);
        com.google.android.gms.internal.d.a aVar2 = map.get(aqd);
        String strF = ay.f(this.lM, strJ, aVar2 != null ? di.j(aVar2) : null);
        return strF != null ? di.u(strF) : di.rb();
    }

    @Override // com.google.android.gms.tagmanager.aj
    public boolean pe() {
        return true;
    }
}
