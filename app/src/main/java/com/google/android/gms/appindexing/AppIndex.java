package com.google.android.gms.appindexing;

import com.google.android.gms.common.api.Api;
import com.google.android.gms.internal.hc;
import com.google.android.gms.internal.hz;

/* JADX INFO: loaded from: classes.dex */
public final class AppIndex {
    public static final Api<Api.ApiOptions.NoOptions> API = hc.CI;
    public static final Api<Api.ApiOptions.NoOptions> APP_INDEX_API = hc.CI;
    public static final AppIndexApi AppIndexApi = new hz();

    private AppIndex() {
    }
}
