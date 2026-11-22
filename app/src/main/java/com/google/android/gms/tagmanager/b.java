package com.google.android.gms.tagmanager;

import android.content.Context;
import java.util.Map;

/* JADX INFO: loaded from: classes.dex */
class b extends aj {
    private static final String ID = com.google.android.gms.internal.a.ADVERTISER_ID.toString();
    private final a aqc;

    public b(Context context) {
        this(a.W(context));
    }

    b(a aVar) {
        super(ID, new String[0]);
        this.aqc = aVar;
    }

    @Override // com.google.android.gms.tagmanager.aj
    public com.google.android.gms.internal.d.a B(Map<String, com.google.android.gms.internal.d.a> map) {
        String strPa = this.aqc.pa();
        return strPa == null ? di.rb() : di.u(strPa);
    }

    @Override // com.google.android.gms.tagmanager.aj
    public boolean pe() {
        return false;
    }
}
