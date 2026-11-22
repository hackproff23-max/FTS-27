package com.google.android.gms.internal;

import java.util.Map;

/* JADX INFO: loaded from: classes.dex */
@ey
public final class ca implements cd {
    private final cb pP;

    public ca(cb cbVar) {
        this.pP = cbVar;
    }

    @Override // com.google.android.gms.internal.cd
    public void a(gu guVar, Map<String, String> map) {
        String str = map.get("name");
        if (str == null) {
            gr.W("App event with no name parameter.");
        } else {
            this.pP.onAppEvent(str, map.get("info"));
        }
    }
}
