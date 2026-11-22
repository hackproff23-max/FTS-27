package com.google.android.gms.tagmanager;

import android.content.Context;
import com.google.android.gms.analytics.GoogleAnalytics;
import com.google.android.gms.analytics.Logger;
import com.google.android.gms.analytics.Tracker;

/* JADX INFO: loaded from: classes.dex */
class df {
    private Context mContext;
    private Tracker yO;
    private GoogleAnalytics yQ;

    static class a implements Logger {
        a() {
        }

        private static int fP(int i) {
            switch (i) {
                case 2:
                    return 0;
                case 3:
                case 4:
                    return 1;
                case 5:
                    return 2;
                case 6:
                default:
                    return 3;
            }
        }

        @Override // com.google.android.gms.analytics.Logger
        public void error(Exception exception) {
            bh.b("", exception);
        }

        @Override // com.google.android.gms.analytics.Logger
        public void error(String message) {
            bh.T(message);
        }

        @Override // com.google.android.gms.analytics.Logger
        public int getLogLevel() {
            return fP(bh.getLogLevel());
        }

        @Override // com.google.android.gms.analytics.Logger
        public void info(String message) {
            bh.U(message);
        }

        @Override // com.google.android.gms.analytics.Logger
        public void setLogLevel(int logLevel) {
            bh.W("GA uses GTM logger. Please use TagManager.setLogLevel(int) instead.");
        }

        @Override // com.google.android.gms.analytics.Logger
        public void verbose(String message) {
            bh.V(message);
        }

        @Override // com.google.android.gms.analytics.Logger
        public void warn(String message) {
            bh.W(message);
        }
    }

    df(Context context) {
        this.mContext = context;
    }

    private synchronized void cX(String str) {
        if (this.yQ == null) {
            this.yQ = GoogleAnalytics.getInstance(this.mContext);
            this.yQ.setLogger(new a());
            this.yO = this.yQ.newTracker(str);
        }
    }

    public Tracker cW(String str) {
        cX(str);
        return this.yO;
    }
}
