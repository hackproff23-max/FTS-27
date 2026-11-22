package com.facebook.ads.a;

import android.os.AsyncTask;
import android.util.Log;
import java.net.URLEncoder;
import java.util.Iterator;
import java.util.Map;
import org.apache.http.client.methods.HttpGet;

/* JADX INFO: loaded from: classes.dex */
public class ad extends AsyncTask {
    private static final String a = ad.class.getSimpleName();
    private Map b;

    public ad() {
        this.b = null;
    }

    public ad(Map map) {
        this.b = map;
    }

    private String a(String str, String str2, String str3) {
        if (ag.a(str) || ag.a(str2) || ag.a(str3)) {
            return str;
        }
        return str + (str.contains("?") ? "&" : "?") + str2 + "=" + URLEncoder.encode(str3);
    }

    private boolean a(String str) {
        try {
            return p.b().execute(new HttpGet(str)).getStatusLine().getStatusCode() == 200;
        } catch (Exception e) {
            Log.e(a, "Error opening url: " + str, e);
            c.a(b.a(e));
            return false;
        }
    }

    private String b(String str) {
        try {
            return a(str, "analog", p.a(a.a()));
        } catch (Exception e) {
            c.a(b.a(e));
            return str;
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // android.os.AsyncTask
    /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
    public Void doInBackground(String... strArr) {
        String str;
        String str2 = strArr[0];
        if (!ag.a(str2) && !str2.equals("#")) {
            String strB = b(str2);
            if (this.b != null) {
                Iterator it = this.b.keySet().iterator();
                while (true) {
                    str = strB;
                    if (!it.hasNext()) {
                        break;
                    }
                    String str3 = (String) it.next();
                    strB = a(str, str3, (String) this.b.get(str3));
                }
            } else {
                str = strB;
            }
            int i = 1;
            while (true) {
                int i2 = i + 1;
                if (i > 2 || a(str)) {
                    break;
                }
                i = i2;
            }
        }
        return null;
    }
}
