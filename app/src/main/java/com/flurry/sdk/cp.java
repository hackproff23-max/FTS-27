package com.flurry.sdk;

import com.facebook.ads.InterstitialAd;
import com.flurry.android.impl.ads.protocol.v13.AdUnit;
import java.util.Iterator;
import java.util.Map;

/* JADX INFO: loaded from: classes.dex */
public class cp extends gi {
    private static final String a = cp.class.getSimpleName();

    public String a(a aVar, String str) {
        String strA = a(str);
        while (strA != null) {
            str = a(aVar, str, strA);
            strA = a(str);
        }
        return str;
    }

    private String a(a aVar, String str, String str2) {
        String str3;
        r rVarB = aVar.c().b();
        at atVarF = aVar.c().f();
        AdUnit adUnitA = aVar.c().c().a();
        if (a("fids", str2)) {
            StringBuilder sb = new StringBuilder();
            boolean z = true;
            for (Map.Entry<fl, byte[]> entry : fe.a().h().entrySet()) {
                if (!z) {
                    sb.append(",");
                }
                sb.append(entry.getKey().d).append(InterstitialAd.SEPARATOR);
                if (entry.getKey().e) {
                    sb.append(new String(entry.getValue()));
                } else {
                    sb.append(hp.a(entry.getValue()));
                }
                z = false;
            }
            gd.a(3, a, "Replacing param fids with: " + sb.toString());
            return str.replace(str2, hp.c(sb.toString()));
        }
        if (a("sid", str2)) {
            String strValueOf = String.valueOf(fd.a().c());
            gd.a(3, a, "Replacing param sid with: " + strValueOf);
            return str.replace(str2, hp.c(strValueOf));
        }
        if (a("lid", str2)) {
            String strValueOf2 = String.valueOf(atVarF.a());
            gd.a(3, a, "Replacing param lid with: " + strValueOf2);
            return str.replace(str2, hp.c(strValueOf2));
        }
        if (a("guid", str2)) {
            String strB = atVarF.b();
            gd.a(3, a, "Replacing param guid with: " + strB);
            return str.replace(str2, hp.c(strB));
        }
        if (a("ats", str2)) {
            String strValueOf3 = String.valueOf(System.currentTimeMillis());
            gd.a(3, a, "Replacing param ats with: " + strValueOf3);
            return str.replace(str2, hp.c(strValueOf3));
        }
        if (a("apik", str2)) {
            String strD = fp.a().d();
            gd.a(3, a, "Replacing param apik with: " + strD);
            return str.replace(str2, hp.c(strD));
        }
        if (a("hid", str2)) {
            String str4 = adUnitA.adSpace;
            gd.a(3, a, "Replacing param hid with: " + str4);
            return str.replace(str2, hp.c(str4));
        }
        if (a("eso", str2)) {
            String string = Long.toString(System.currentTimeMillis() - fd.a().c());
            gd.a(3, a, "Replacing param eso with: " + string);
            return str.replace(str2, hp.c(string));
        }
        if (a("uc", str2)) {
            String str5 = "";
            e eVarL = rVarB.l();
            if (eVarL == null) {
                str3 = "";
            } else {
                Iterator<Map.Entry<String, String>> it = eVarL.getUserCookies().entrySet().iterator();
                while (true) {
                    str3 = str5;
                    if (!it.hasNext()) {
                        break;
                    }
                    Map.Entry<String, String> next = it.next();
                    str5 = str3 + "c_" + hp.c(next.getKey()) + "=" + hp.c(next.getValue()) + "&";
                }
            }
            gd.a(3, a, "Replacing param uc with: " + str3);
            String strReplace = str.replace(str2, str3);
            if (str3.equals("") && strReplace.length() > 0) {
                return strReplace.substring(0, strReplace.length() - 1);
            }
            return strReplace;
        }
        gd.a(3, a, "Unknown param: " + str2);
        return str.replace(str2, "");
    }
}
