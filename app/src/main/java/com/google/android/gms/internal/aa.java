package com.google.android.gms.internal;

import com.facebook.appevents.AppEventsConstants;
import java.util.HashMap;

/* JADX INFO: loaded from: classes.dex */
@ey
class aa implements ac {
    private gu mt;

    public aa(gu guVar) {
        this.mt = guVar;
    }

    @Override // com.google.android.gms.internal.ac
    public void a(af afVar, boolean z) {
        HashMap map = new HashMap();
        map.put("isVisible", z ? AppEventsConstants.EVENT_PARAM_VALUE_YES : AppEventsConstants.EVENT_PARAM_VALUE_NO);
        this.mt.a("onAdVisibilityChanged", map);
    }
}
