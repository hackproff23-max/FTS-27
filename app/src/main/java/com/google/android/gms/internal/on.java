package com.google.android.gms.internal;

import android.content.Intent;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.panorama.PanoramaApi;

/* JADX INFO: loaded from: classes.dex */
class on implements PanoramaApi.PanoramaResult {
    private final Status Eb;
    private final Intent amR;

    public on(Status status, Intent intent) {
        this.Eb = (Status) jx.i(status);
        this.amR = intent;
    }

    @Override // com.google.android.gms.common.api.Result
    public Status getStatus() {
        return this.Eb;
    }

    @Override // com.google.android.gms.panorama.PanoramaApi.PanoramaResult
    public Intent getViewerIntent() {
        return this.amR;
    }
}
