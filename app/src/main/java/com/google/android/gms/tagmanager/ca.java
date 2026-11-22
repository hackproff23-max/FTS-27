package com.google.android.gms.tagmanager;

import android.os.Build;
import java.util.Map;

/* JADX INFO: loaded from: classes.dex */
class ca extends aj {
    private static final String ID = com.google.android.gms.internal.a.OS_VERSION.toString();

    public ca() {
        super(ID, new String[0]);
    }

    @Override // com.google.android.gms.tagmanager.aj
    public com.google.android.gms.internal.d.a B(Map<String, com.google.android.gms.internal.d.a> map) {
        return di.u(Build.VERSION.RELEASE);
    }

    @Override // com.google.android.gms.tagmanager.aj
    public boolean pe() {
        return true;
    }
}
