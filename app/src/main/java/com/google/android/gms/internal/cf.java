package com.google.android.gms.internal;

import com.facebook.appevents.AppEventsConstants;
import java.util.Map;

/* JADX INFO: loaded from: classes.dex */
@ey
public class cf implements cd {
    private final cg qa;

    public cf(cg cgVar) {
        this.qa = cgVar;
    }

    @Override // com.google.android.gms.internal.cd
    public void a(gu guVar, Map<String, String> map) {
        this.qa.d(AppEventsConstants.EVENT_PARAM_VALUE_YES.equals(map.get("transparentBackground")));
    }
}
