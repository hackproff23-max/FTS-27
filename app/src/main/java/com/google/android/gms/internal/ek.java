package com.google.android.gms.internal;

import android.content.Intent;

/* JADX INFO: loaded from: classes.dex */
@ey
public class ek {
    private final String oK;

    public ek(String str) {
        this.oK = str;
    }

    public boolean a(String str, int i, Intent intent) {
        if (str == null || intent == null) {
            return false;
        }
        String strE = ei.e(intent);
        String strF = ei.f(intent);
        if (strE == null || strF == null) {
            return false;
        }
        if (!str.equals(ei.D(strE))) {
            gr.W("Developer payload not match.");
            return false;
        }
        if (this.oK == null || el.b(this.oK, strE, strF)) {
            return true;
        }
        gr.W("Fail to verify signature.");
        return false;
    }

    public String cC() {
        return gi.dx();
    }
}
