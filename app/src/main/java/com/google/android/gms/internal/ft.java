package com.google.android.gms.internal;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

/* JADX INFO: loaded from: classes.dex */
@ey
public final class ft {
    private String pD;
    private String uJ;
    private String uK;
    private List<String> uL;
    private String uM;
    private String uN;
    private List<String> uO;
    private List<String> uo;
    private long uP = -1;
    private boolean uQ = false;
    private final long uR = -1;
    private long uS = -1;
    private int mOrientation = -1;
    private boolean uT = false;
    private boolean uU = false;
    private boolean uV = false;
    private boolean uW = true;

    static String a(Map<String, List<String>> map, String str) {
        List<String> list = map.get(str);
        if (list == null || list.isEmpty()) {
            return null;
        }
        return list.get(0);
    }

    static long b(Map<String, List<String>> map, String str) {
        List<String> list = map.get(str);
        if (list != null && !list.isEmpty()) {
            String str2 = list.get(0);
            try {
                return (long) (Float.parseFloat(str2) * 1000.0f);
            } catch (NumberFormatException e) {
                gr.W("Could not parse float from " + str + " header: " + str2);
            }
        }
        return -1L;
    }

    static List<String> c(Map<String, List<String>> map, String str) {
        String str2;
        List<String> list = map.get(str);
        if (list == null || list.isEmpty() || (str2 = list.get(0)) == null) {
            return null;
        }
        return Arrays.asList(str2.trim().split("\\s+"));
    }

    private boolean d(Map<String, List<String>> map, String str) {
        List<String> list = map.get(str);
        return (list == null || list.isEmpty() || !Boolean.valueOf(list.get(0)).booleanValue()) ? false : true;
    }

    private void f(Map<String, List<String>> map) {
        this.uJ = a(map, "X-Afma-Ad-Size");
    }

    private void g(Map<String, List<String>> map) {
        List<String> listC = c(map, "X-Afma-Click-Tracking-Urls");
        if (listC != null) {
            this.uL = listC;
        }
    }

    private void h(Map<String, List<String>> map) {
        List<String> list = map.get("X-Afma-Debug-Dialog");
        if (list == null || list.isEmpty()) {
            return;
        }
        this.uM = list.get(0);
    }

    private void i(Map<String, List<String>> map) {
        List<String> listC = c(map, "X-Afma-Tracking-Urls");
        if (listC != null) {
            this.uO = listC;
        }
    }

    private void j(Map<String, List<String>> map) {
        long jB = b(map, "X-Afma-Interstitial-Timeout");
        if (jB != -1) {
            this.uP = jB;
        }
    }

    private void k(Map<String, List<String>> map) {
        this.uN = a(map, "X-Afma-ActiveView");
    }

    private void l(Map<String, List<String>> map) {
        this.uU |= d(map, "X-Afma-Native");
    }

    private void m(Map<String, List<String>> map) {
        this.uT |= d(map, "X-Afma-Custom-Rendering-Allowed");
    }

    private void n(Map<String, List<String>> map) {
        this.uQ |= d(map, "X-Afma-Mediation");
    }

    private void o(Map<String, List<String>> map) {
        List<String> listC = c(map, "X-Afma-Manual-Tracking-Urls");
        if (listC != null) {
            this.uo = listC;
        }
    }

    private void p(Map<String, List<String>> map) {
        long jB = b(map, "X-Afma-Refresh-Rate");
        if (jB != -1) {
            this.uS = jB;
        }
    }

    private void q(Map<String, List<String>> map) {
        List<String> list = map.get("X-Afma-Orientation");
        if (list == null || list.isEmpty()) {
            return;
        }
        String str = list.get(0);
        if ("portrait".equalsIgnoreCase(str)) {
            this.mOrientation = gi.dv();
        } else if ("landscape".equalsIgnoreCase(str)) {
            this.mOrientation = gi.du();
        }
    }

    private void r(Map<String, List<String>> map) {
        List<String> list = map.get("X-Afma-Use-HTTPS");
        if (list == null || list.isEmpty()) {
            return;
        }
        this.uV = Boolean.valueOf(list.get(0)).booleanValue();
    }

    private void s(Map<String, List<String>> map) {
        List<String> list = map.get("X-Afma-Content-Url-Opted-Out");
        if (list == null || list.isEmpty()) {
            return;
        }
        this.uW = Boolean.valueOf(list.get(0)).booleanValue();
    }

    public void a(String str, Map<String, List<String>> map, String str2) {
        this.uK = str;
        this.pD = str2;
        e(map);
    }

    public void e(Map<String, List<String>> map) {
        f(map);
        g(map);
        h(map);
        i(map);
        j(map);
        n(map);
        o(map);
        p(map);
        q(map);
        k(map);
        r(map);
        m(map);
        l(map);
        s(map);
    }

    public fj i(long j) {
        return new fj(this.uK, this.pD, this.uL, this.uO, this.uP, this.uQ, -1L, this.uo, this.uS, this.mOrientation, this.uJ, j, this.uM, this.uN, this.uT, this.uU, this.uV, this.uW);
    }
}
