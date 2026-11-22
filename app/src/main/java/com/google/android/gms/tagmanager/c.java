package com.google.android.gms.tagmanager;

import android.content.Context;
import java.util.Map;

/* JADX INFO: loaded from: classes.dex */
class c extends aj {
    private static final String ID = com.google.android.gms.internal.a.ADVERTISING_TRACKING_ENABLED.toString();
    private final a aqc;

    public c(Context context) {
        this(a.W(context));
    }

    c(a aVar) {
        super(ID, new String[0]);
        this.aqc = aVar;
    }

    @Override // com.google.android.gms.tagmanager.aj
    public com.google.android.gms.internal.d.a B(Map<String, com.google.android.gms.internal.d.a> map) {
        return di.u(Boolean.valueOf(!this.aqc.isLimitAdTrackingEnabled()));
    }

    @Override // com.google.android.gms.tagmanager.aj
    public boolean pe() {
        return false;
    }
}
