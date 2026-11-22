package com.flurry.sdk;

import java.util.Map;
import java.util.TreeMap;

/* JADX INFO: loaded from: classes.dex */
public class f {
    private static f a;
    private static final String b = f.class.getSimpleName();
    private final TreeMap<String, Integer> c = new TreeMap<>();

    public static synchronized f a() {
        if (a == null) {
            a = new f();
        }
        return a;
    }

    public void a(String str, int i) {
        synchronized (this.c) {
            Integer num = this.c.get(str);
            TreeMap<String, Integer> treeMap = this.c;
            if (num != null) {
                i += num.intValue();
            }
            treeMap.put(str, Integer.valueOf(i));
        }
    }

    public void b() {
        gd.a(3, b, "========== PRINT COUNTERS ==========");
        synchronized (this.c) {
            for (Map.Entry<String, Integer> entry : this.c.entrySet()) {
                gd.a(3, b, entry.getKey() + " " + entry.getValue());
            }
        }
        gd.a(3, b, "========== END PRINT COUNTERS ==========");
    }
}
