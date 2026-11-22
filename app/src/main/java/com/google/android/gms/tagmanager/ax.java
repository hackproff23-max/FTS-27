package com.google.android.gms.tagmanager;

import android.content.Context;
import java.util.Map;

/* JADX INFO: loaded from: classes.dex */
class ax extends aj {
    private static final String ID = com.google.android.gms.internal.a.INSTALL_REFERRER.toString();
    private static final String aqd = com.google.android.gms.internal.b.COMPONENT.toString();
    private final Context lM;

    public ax(Context context) {
        super(ID, new String[0]);
        this.lM = context;
    }

    @Override // com.google.android.gms.tagmanager.aj
    public com.google.android.gms.internal.d.a B(Map<String, com.google.android.gms.internal.d.a> map) {
        String strE = ay.e(this.lM, map.get(aqd) != null ? di.j(map.get(aqd)) : null);
        return strE != null ? di.u(strE) : di.rb();
    }

    @Override // com.google.android.gms.tagmanager.aj
    public boolean pe() {
        return true;
    }
}
