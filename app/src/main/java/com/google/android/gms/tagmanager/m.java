package com.google.android.gms.tagmanager;

import java.util.Map;

/* JADX INFO: loaded from: classes.dex */
class m extends aj {
    private static final String ID = com.google.android.gms.internal.a.CONSTANT.toString();
    private static final String VALUE = com.google.android.gms.internal.b.VALUE.toString();

    public m() {
        super(ID, VALUE);
    }

    public static String ph() {
        return ID;
    }

    public static String pi() {
        return VALUE;
    }

    @Override // com.google.android.gms.tagmanager.aj
    public com.google.android.gms.internal.d.a B(Map<String, com.google.android.gms.internal.d.a> map) {
        return map.get(VALUE);
    }

    @Override // com.google.android.gms.tagmanager.aj
    public boolean pe() {
        return true;
    }
}
