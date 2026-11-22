package com.google.android.gms.internal;

import com.facebook.appevents.AppEventsConstants;
import com.mopub.common.AdType;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Map;

/* JADX INFO: loaded from: classes.dex */
@ey
public final class ci implements cd {
    private final ce qc;
    private final v qd;

    public ci(ce ceVar, v vVar) {
        this.qc = ceVar;
        this.qd = vVar;
    }

    private static boolean b(Map<String, String> map) {
        return AppEventsConstants.EVENT_PARAM_VALUE_YES.equals(map.get("custom_close"));
    }

    private static int c(Map<String, String> map) {
        String str = map.get("o");
        if (str != null) {
            if ("p".equalsIgnoreCase(str)) {
                return gi.dv();
            }
            if ("l".equalsIgnoreCase(str)) {
                return gi.du();
            }
        }
        return -1;
    }

    @Override // com.google.android.gms.internal.cd
    public void a(gu guVar, Map<String, String> map) {
        String str = map.get("a");
        if (str == null) {
            gr.W("Action missing from an open GMSG.");
            return;
        }
        if (this.qd != null && !this.qd.az()) {
            this.qd.d(map.get("u"));
            return;
        }
        gv gvVarDD = guVar.dD();
        if ("expand".equalsIgnoreCase(str)) {
            if (guVar.dH()) {
                gr.W("Cannot expand WebView that is already expanded.");
                return;
            } else {
                gvVarDD.a(b(map), c(map));
                return;
            }
        }
        if ("webapp".equalsIgnoreCase(str)) {
            String str2 = map.get("u");
            if (str2 != null) {
                gvVarDD.a(b(map), c(map), str2);
                return;
            } else {
                gvVarDD.a(b(map), c(map), map.get(AdType.HTML), map.get("baseurl"));
                return;
            }
        }
        if (!"in_app_purchase".equalsIgnoreCase(str)) {
            gvVarDD.a(new Cdo(map.get("i"), map.get("u"), map.get("m"), map.get("p"), map.get("c"), map.get("f"), map.get("e")));
            return;
        }
        String str3 = map.get("product_id");
        String str4 = map.get("report_urls");
        if (this.qc != null) {
            if (str4 == null || str4.isEmpty()) {
                this.qc.a(str3, new ArrayList<>());
            } else {
                this.qc.a(str3, new ArrayList<>(Arrays.asList(str4.split(" "))));
            }
        }
    }
}
