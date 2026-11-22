package com.facebook.ads.a;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.json.JSONArray;

/* JADX INFO: loaded from: classes.dex */
public class c {
    private static List a = new ArrayList();

    public static String a() {
        synchronized (a) {
            if (a.isEmpty()) {
                return "";
            }
            ArrayList arrayList = new ArrayList(a);
            a.clear();
            JSONArray jSONArray = new JSONArray();
            Iterator it = arrayList.iterator();
            while (it.hasNext()) {
                jSONArray.put(((b) it.next()).a());
            }
            return jSONArray.toString();
        }
    }

    public static void a(b bVar) {
        synchronized (a) {
            a.add(bVar);
        }
    }
}
