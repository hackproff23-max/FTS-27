package com.google.android.gms.internal;

import com.google.android.gms.ads.doubleclick.AppEventListener;

/* JADX INFO: loaded from: classes.dex */
@ey
public final class ba extends bf.a {
    private final AppEventListener os;

    public ba(AppEventListener appEventListener) {
        this.os = appEventListener;
    }

    @Override // com.google.android.gms.internal.bf
    public void onAppEvent(String name, String info) {
        this.os.onAppEvent(name, info);
    }
}
