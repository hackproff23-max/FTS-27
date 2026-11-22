package com.facebook.ads.a;

import android.content.Context;
import android.content.pm.PackageManager;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import org.json.JSONArray;

/* JADX INFO: loaded from: classes.dex */
public class h {
    public static Collection a(JSONArray jSONArray) {
        if (jSONArray == null || jSONArray.length() == 0) {
            return null;
        }
        HashSet hashSet = new HashSet();
        for (int i = 0; i < jSONArray.length(); i++) {
            hashSet.add(jSONArray.optString(i));
        }
        return hashSet;
    }

    public static boolean a(Context context, e eVar) {
        Collection collectionB;
        boolean z;
        g gVarA = eVar.a();
        if (gVarA == null || gVarA == g.NONE || (collectionB = eVar.b()) == null || collectionB.isEmpty()) {
            return false;
        }
        Iterator it = collectionB.iterator();
        while (true) {
            if (!it.hasNext()) {
                z = false;
                break;
            }
            if (a(context, (String) it.next())) {
                z = true;
                break;
            }
        }
        if (gVarA == g.INSTALLED) {
            return z;
        }
        if (gVarA == g.NOT_INSTALLED) {
            return z ? false : true;
        }
        return false;
    }

    public static boolean a(Context context, String str) {
        if (ag.a(str)) {
            return false;
        }
        try {
            context.getPackageManager().getPackageInfo(str, 0);
            return true;
        } catch (PackageManager.NameNotFoundException e) {
            return false;
        } catch (RuntimeException e2) {
            return false;
        }
    }
}
