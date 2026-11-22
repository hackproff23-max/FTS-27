package com.google.android.gms.tagmanager;

import java.util.Map;

/* JADX INFO: loaded from: classes.dex */
class dc extends dd {
    private static final String ID = com.google.android.gms.internal.a.STARTS_WITH.toString();

    public dc() {
        super(ID);
    }

    @Override // com.google.android.gms.tagmanager.dd
    protected boolean a(String str, String str2, Map<String, com.google.android.gms.internal.d.a> map) {
        return str.startsWith(str2);
    }
}
