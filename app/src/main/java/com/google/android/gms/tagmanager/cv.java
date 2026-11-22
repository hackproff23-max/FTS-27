package com.google.android.gms.tagmanager;

import android.os.Build;
import java.util.Map;

/* JADX INFO: loaded from: classes.dex */
class cv extends aj {
    private static final String ID = com.google.android.gms.internal.a.SDK_VERSION.toString();

    public cv() {
        super(ID, new String[0]);
    }

    @Override // com.google.android.gms.tagmanager.aj
    public com.google.android.gms.internal.d.a B(Map<String, com.google.android.gms.internal.d.a> map) {
        return di.u(Integer.valueOf(Build.VERSION.SDK_INT));
    }

    @Override // com.google.android.gms.tagmanager.aj
    public boolean pe() {
        return true;
    }
}
