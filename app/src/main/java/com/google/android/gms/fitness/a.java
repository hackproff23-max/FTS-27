package com.google.android.gms.fitness;

import com.google.android.gms.common.Scopes;
import java.util.HashSet;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public final class a {
    public static String bt(String str) {
        if (str.equals(Scopes.FITNESS_ACTIVITY_READ)) {
            return Scopes.FITNESS_ACTIVITY_READ_WRITE;
        }
        if (str.equals(Scopes.FITNESS_LOCATION_READ)) {
            return Scopes.FITNESS_LOCATION_READ_WRITE;
        }
        return str.equals(Scopes.FITNESS_BODY_READ) ? Scopes.FITNESS_BODY_READ_WRITE : str;
    }

    public static String[] e(List<String> list) {
        HashSet hashSet = new HashSet(list.size());
        for (String str : list) {
            String strBt = bt(str);
            if (strBt.equals(str) || !list.contains(strBt)) {
                hashSet.add(str);
            }
        }
        return (String[]) hashSet.toArray(new String[hashSet.size()]);
    }
}
