package com.google.android.gms.analytics;

import android.content.Context;

/* JADX INFO: loaded from: classes.dex */
class al extends n<am> {

    private static class a implements n.a<am> {
        private final am Cu = new am();

        @Override // com.google.android.gms.analytics.n.a
        public void c(String str, int i) {
            if ("ga_sessionTimeout".equals(str)) {
                this.Cu.Cx = i;
            } else {
                ae.W("int configuration name not recognized:  " + str);
            }
        }

        @Override // com.google.android.gms.analytics.n.a
        public void e(String str, String str2) {
            this.Cu.CB.put(str, str2);
        }

        @Override // com.google.android.gms.analytics.n.a
        public void e(String str, boolean z) {
            if ("ga_autoActivityTracking".equals(str)) {
                this.Cu.Cy = z ? 1 : 0;
                return;
            }
            if ("ga_anonymizeIp".equals(str)) {
                this.Cu.Cz = z ? 1 : 0;
            } else if (!"ga_reportUncaughtExceptions".equals(str)) {
                ae.W("bool configuration name not recognized:  " + str);
            } else {
                this.Cu.CA = z ? 1 : 0;
            }
        }

        @Override // com.google.android.gms.analytics.n.a
        public void f(String str, String str2) {
            if ("ga_trackingId".equals(str)) {
                this.Cu.Cv = str2;
                return;
            }
            if (!"ga_sampleFrequency".equals(str)) {
                ae.W("string configuration name not recognized:  " + str);
                return;
            }
            try {
                this.Cu.Cw = Double.parseDouble(str2);
            } catch (NumberFormatException e) {
                ae.T("Error parsing ga_sampleFrequency value: " + str2);
            }
        }

        @Override // com.google.android.gms.analytics.n.a
        /* JADX INFO: renamed from: fs, reason: merged with bridge method [inline-methods] */
        public am er() {
            return this.Cu;
        }
    }

    public al(Context context) {
        super(context, new a());
    }
}
