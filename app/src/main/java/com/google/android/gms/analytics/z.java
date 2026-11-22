package com.google.android.gms.analytics;

import android.content.Context;

/* JADX INFO: loaded from: classes.dex */
class z extends n<aa> {

    private static class a implements n.a<aa> {
        private final aa Bi = new aa();

        @Override // com.google.android.gms.analytics.n.a
        public void c(String str, int i) {
            if ("ga_dispatchPeriod".equals(str)) {
                this.Bi.Bk = i;
            } else {
                ae.W("int configuration name not recognized:  " + str);
            }
        }

        @Override // com.google.android.gms.analytics.n.a
        public void e(String str, String str2) {
        }

        @Override // com.google.android.gms.analytics.n.a
        public void e(String str, boolean z) {
            if (!"ga_dryRun".equals(str)) {
                ae.W("bool configuration name not recognized:  " + str);
            } else {
                this.Bi.Bl = z ? 1 : 0;
            }
        }

        @Override // com.google.android.gms.analytics.n.a
        /* JADX INFO: renamed from: eN, reason: merged with bridge method [inline-methods] */
        public aa er() {
            return this.Bi;
        }

        @Override // com.google.android.gms.analytics.n.a
        public void f(String str, String str2) {
            if ("ga_appName".equals(str)) {
                this.Bi.ya = str2;
                return;
            }
            if ("ga_appVersion".equals(str)) {
                this.Bi.yb = str2;
            } else if ("ga_logLevel".equals(str)) {
                this.Bi.Bj = str2;
            } else {
                ae.W("string configuration name not recognized:  " + str);
            }
        }
    }

    public z(Context context) {
        super(context, new a());
    }
}
