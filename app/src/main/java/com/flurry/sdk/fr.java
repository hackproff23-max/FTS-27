package com.flurry.sdk;

import java.util.HashMap;
import java.util.Map;

/* JADX INFO: loaded from: classes.dex */
public class fr {
    private static fr a;
    private static final String b = fr.class.getSimpleName();
    private static final HashMap<String, Map<String, String>> c = new HashMap<>();

    public static synchronized fr a() {
        if (a == null) {
            a = new fr();
        }
        return a;
    }

    public static synchronized void b() {
        a = null;
    }

    /* JADX WARN: Code duplicated, block: B:11:0x002e A[Catch: all -> 0x0069, TRY_ENTER, TryCatch #1 {, blocks: (B:5:0x0005, B:6:0x000a, B:8:0x0010, B:11:0x002e, B:12:0x0035, B:22:0x0068, B:13:0x0036, B:15:0x003e, B:17:0x0046, B:18:0x0064, B:26:0x006c, B:27:0x0071), top: B:32:0x0005, inners: #0 }] */
    /* JADX WARN: Code duplicated, block: B:26:0x006c A[Catch: all -> 0x0066, TRY_ENTER, TryCatch #0 {, blocks: (B:13:0x0036, B:15:0x003e, B:17:0x0046, B:18:0x0064, B:26:0x006c, B:27:0x0071), top: B:30:0x0036, outer: #1 }] */
    /* JADX WARN: Code duplicated, block: B:30:0x0036 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:8:0x0010 A[Catch: all -> 0x0069, TRY_LEAVE, TryCatch #1 {, blocks: (B:5:0x0005, B:6:0x000a, B:8:0x0010, B:11:0x002e, B:12:0x0035, B:22:0x0068, B:13:0x0036, B:15:0x003e, B:17:0x0046, B:18:0x0064, B:26:0x006c, B:27:0x0071), top: B:32:0x0005, inners: #0 }] */
    public synchronized void a(String str, String str2, Map<String, String> map) {
        if (map == null) {
            map = new HashMap<>();
            if (map.size() >= 10) {
                gd.e(b, "MaxOriginParams exceeded: " + map.size());
            } else {
                map.put("flurryOriginVersion", str2);
                synchronized (c) {
                    if (c.size() < 10 && !c.containsKey(str)) {
                        gd.e(b, "MaxOrigins exceeded: " + c.size());
                    } else {
                        c.put(str, map);
                    }
                }
            }
        } else if (map.size() >= 10) {
            gd.e(b, "MaxOriginParams exceeded: " + map.size());
        } else {
            map.put("flurryOriginVersion", str2);
            synchronized (c) {
                if (c.size() < 10) {
                    c.put(str, map);
                } else {
                    c.put(str, map);
                }
            }
        }
        throw th;
    }

    public synchronized HashMap<String, Map<String, String>> c() {
        HashMap<String, Map<String, String>> map;
        synchronized (c) {
            map = new HashMap<>(c);
        }
        return map;
    }
}
