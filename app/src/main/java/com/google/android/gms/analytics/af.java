package com.google.android.gms.analytics;

import android.text.TextUtils;
import java.util.HashMap;
import java.util.Map;

/* JADX INFO: loaded from: classes.dex */
public class af {
    private final Map<String, Integer> BO = new HashMap();
    private final Map<String, String> BP = new HashMap();
    private final boolean BQ;
    private final String BR;

    public af(String str, boolean z) {
        this.BQ = z;
        this.BR = str;
    }

    public void e(String str, int i) {
        if (this.BQ) {
            Integer num = this.BO.get(str);
            if (num == null) {
                num = 0;
            }
            this.BO.put(str, Integer.valueOf(num.intValue() + i));
        }
    }

    public String fg() {
        if (!this.BQ) {
            return "";
        }
        StringBuilder sb = new StringBuilder();
        sb.append(this.BR);
        for (String str : this.BO.keySet()) {
            sb.append("&").append(str).append("=").append(this.BO.get(str));
        }
        for (String str2 : this.BP.keySet()) {
            sb.append("&").append(str2).append("=").append(this.BP.get(str2));
        }
        return sb.toString();
    }

    public void g(String str, String str2) {
        if (!this.BQ || TextUtils.isEmpty(str)) {
            return;
        }
        this.BP.put(str, str2);
    }
}
