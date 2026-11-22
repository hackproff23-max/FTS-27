package com.applovin.impl.sdk;

import org.json.JSONObject;

/* JADX INFO: loaded from: classes.dex */
class bw implements o {
    final /* synthetic */ bv a;

    bw(bv bvVar) {
        this.a = bvVar;
    }

    @Override // com.applovin.impl.sdk.o
    public void a(int i) {
        this.a.g.d("TaskReportReward", "Failed to report reward for ad: " + this.a.a.getAdIdNumber() + " - error code: " + i);
    }

    @Override // com.applovin.impl.sdk.o
    public void a(JSONObject jSONObject, int i) {
        this.a.g.d("TaskReportReward", "Reported reward successfully for ad: " + this.a.a.getAdIdNumber());
    }
}
