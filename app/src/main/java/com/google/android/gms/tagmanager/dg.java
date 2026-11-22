package com.google.android.gms.tagmanager;

import java.util.Map;

/* JADX INFO: loaded from: classes.dex */
abstract class dg extends aj {
    public dg(String str, String... strArr) {
        super(str, strArr);
    }

    @Override // com.google.android.gms.tagmanager.aj
    public com.google.android.gms.internal.d.a B(Map<String, com.google.android.gms.internal.d.a> map) {
        D(map);
        return di.rb();
    }

    public abstract void D(Map<String, com.google.android.gms.internal.d.a> map);

    @Override // com.google.android.gms.tagmanager.aj
    public boolean pe() {
        return false;
    }
}
