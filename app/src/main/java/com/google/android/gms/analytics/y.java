package com.google.android.gms.analytics;

import java.util.SortedSet;
import java.util.TreeSet;

/* JADX INFO: loaded from: classes.dex */
class y {
    private static final y zV = new y();
    private SortedSet<a> zS = new TreeSet();
    private StringBuilder zT = new StringBuilder();
    private boolean zU = false;

    public enum a {
        MAP_BUILDER_SET,
        MAP_BUILDER_SET_ALL,
        MAP_BUILDER_GET,
        MAP_BUILDER_SET_CAMPAIGN_PARAMS,
        BLANK_04,
        BLANK_05,
        BLANK_06,
        BLANK_07,
        BLANK_08,
        GET,
        SET,
        SEND,
        BLANK_12,
        BLANK_13,
        BLANK_14,
        BLANK_15,
        BLANK_16,
        BLANK_17,
        BLANK_18,
        BLANK_19,
        BLANK_20,
        BLANK_21,
        BLANK_22,
        BLANK_23,
        BLANK_24,
        BLANK_25,
        BLANK_26,
        BLANK_27,
        BLANK_28,
        BLANK_29,
        SET_EXCEPTION_PARSER,
        GET_EXCEPTION_PARSER,
        CONSTRUCT_TRANSACTION,
        CONSTRUCT_EXCEPTION,
        CONSTRUCT_RAW_EXCEPTION,
        CONSTRUCT_TIMING,
        CONSTRUCT_SOCIAL,
        BLANK_37,
        BLANK_38,
        GET_TRACKER,
        GET_DEFAULT_TRACKER,
        SET_DEFAULT_TRACKER,
        SET_APP_OPT_OUT,
        GET_APP_OPT_OUT,
        DISPATCH,
        SET_DISPATCH_PERIOD,
        BLANK_46,
        REPORT_UNCAUGHT_EXCEPTIONS,
        SET_AUTO_ACTIVITY_TRACKING,
        SET_SESSION_TIMEOUT,
        CONSTRUCT_EVENT,
        CONSTRUCT_ITEM,
        BLANK_52,
        BLANK_53,
        SET_DRY_RUN,
        GET_DRY_RUN,
        SET_LOGGER,
        SET_FORCE_LOCAL_DISPATCH,
        GET_TRACKER_NAME,
        CLOSE_TRACKER,
        EASY_TRACKER_ACTIVITY_START,
        EASY_TRACKER_ACTIVITY_STOP,
        CONSTRUCT_APP_VIEW
    }

    private y() {
    }

    public static y eK() {
        return zV;
    }

    public synchronized void D(boolean z) {
        this.zU = z;
    }

    public synchronized void a(a aVar) {
        if (!this.zU) {
            this.zS.add(aVar);
            this.zT.append("ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789-_".charAt(aVar.ordinal()));
        }
    }

    public synchronized String eL() {
        StringBuilder sb;
        sb = new StringBuilder();
        int i = 6;
        int iOrdinal = 0;
        while (this.zS.size() > 0) {
            a aVarFirst = this.zS.first();
            this.zS.remove(aVarFirst);
            int iOrdinal2 = aVarFirst.ordinal();
            while (iOrdinal2 >= i) {
                sb.append("ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789-_".charAt(iOrdinal));
                i += 6;
                iOrdinal = 0;
            }
            iOrdinal += 1 << (aVarFirst.ordinal() % 6);
        }
        if (iOrdinal > 0 || sb.length() == 0) {
            sb.append("ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789-_".charAt(iOrdinal));
        }
        this.zS.clear();
        return sb.toString();
    }

    public synchronized String eM() {
        String string;
        if (this.zT.length() > 0) {
            this.zT.insert(0, ".");
        }
        string = this.zT.toString();
        this.zT = new StringBuilder();
        return string;
    }
}
