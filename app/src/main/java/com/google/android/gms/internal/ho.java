package com.google.android.gms.internal;

import java.util.HashMap;
import java.util.Map;

/* JADX INFO: loaded from: classes.dex */
public class ho {
    private static final String[] Df = {"text1", "text2", "icon", "intent_action", "intent_data", "intent_data_id", "intent_extra_data", "suggest_large_icon", "intent_activity"};
    private static final Map<String, Integer> Dg = new HashMap(Df.length);

    static {
        for (int i = 0; i < Df.length; i++) {
            Dg.put(Df[i], Integer.valueOf(i));
        }
    }

    public static String P(int i) {
        if (i < 0 || i >= Df.length) {
            return null;
        }
        return Df[i];
    }

    public static int as(String str) {
        Integer num = Dg.get(str);
        if (num == null) {
            throw new IllegalArgumentException("[" + str + "] is not a valid global search section name");
        }
        return num.intValue();
    }

    public static int fF() {
        return Df.length;
    }
}
