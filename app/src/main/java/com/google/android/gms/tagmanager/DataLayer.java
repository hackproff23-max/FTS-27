package com.google.android.gms.tagmanager;

import com.google.android.gms.location.LocationRequest;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.locks.ReentrantLock;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/* JADX INFO: loaded from: classes.dex */
public class DataLayer {
    public static final String EVENT_KEY = "event";
    public static final Object OBJECT_NOT_PRESENT = new Object();
    static final String[] aqQ = "gtm.lifetime".toString().split("\\.");
    private static final Pattern aqR = Pattern.compile("(\\d+)\\s*([smhd]?)");
    private final ConcurrentHashMap<b, Integer> aqS;
    private final Map<String, Object> aqT;
    private final ReentrantLock aqU;
    private final LinkedList<Map<String, Object>> aqV;
    private final c aqW;
    private final CountDownLatch aqX;

    static final class a {
        public final String KP;
        public final Object wF;

        a(String str, Object obj) {
            this.KP = str;
            this.wF = obj;
        }

        public boolean equals(Object o) {
            if (!(o instanceof a)) {
                return false;
            }
            a aVar = (a) o;
            return this.KP.equals(aVar.KP) && this.wF.equals(aVar.wF);
        }

        public int hashCode() {
            return Arrays.hashCode(new Integer[]{Integer.valueOf(this.KP.hashCode()), Integer.valueOf(this.wF.hashCode())});
        }

        public String toString() {
            return "Key: " + this.KP + " value: " + this.wF.toString();
        }
    }

    interface b {
        void C(Map<String, Object> map);
    }

    interface c {

        public interface a {
            void h(List<a> list);
        }

        void a(a aVar);

        void a(List<a> list, long j);

        void cz(String str);
    }

    DataLayer() {
        this(new c() { // from class: com.google.android.gms.tagmanager.DataLayer.1
            @Override // com.google.android.gms.tagmanager.DataLayer.c
            public void a(c.a aVar) {
                aVar.h(new ArrayList());
            }

            @Override // com.google.android.gms.tagmanager.DataLayer.c
            public void a(List<a> list, long j) {
            }

            @Override // com.google.android.gms.tagmanager.DataLayer.c
            public void cz(String str) {
            }
        });
    }

    DataLayer(c persistentStore) {
        this.aqW = persistentStore;
        this.aqS = new ConcurrentHashMap<>();
        this.aqT = new HashMap();
        this.aqU = new ReentrantLock();
        this.aqV = new LinkedList<>();
        this.aqX = new CountDownLatch(1);
        pv();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void E(Map<String, Object> map) {
        this.aqU.lock();
        try {
            this.aqV.offer(map);
            if (this.aqU.getHoldCount() == 1) {
                pw();
            }
            F(map);
        } finally {
            this.aqU.unlock();
        }
    }

    private void F(Map<String, Object> map) {
        Long lG = G(map);
        if (lG == null) {
            return;
        }
        List<a> listI = I(map);
        listI.remove("gtm.lifetime");
        this.aqW.a(listI, lG.longValue());
    }

    private Long G(Map<String, Object> map) {
        Object objH = H(map);
        if (objH == null) {
            return null;
        }
        return cy(objH.toString());
    }

    private Object H(Map<String, Object> map) {
        String[] strArr = aqQ;
        int length = strArr.length;
        int i = 0;
        Object obj = map;
        while (i < length) {
            String str = strArr[i];
            if (!(obj instanceof Map)) {
                return null;
            }
            i++;
            obj = ((Map) obj).get(str);
        }
        return obj;
    }

    private List<a> I(Map<String, Object> map) {
        ArrayList arrayList = new ArrayList();
        a(map, "", arrayList);
        return arrayList;
    }

    private void J(Map<String, Object> map) {
        synchronized (this.aqT) {
            for (String str : map.keySet()) {
                a(c(str, map.get(str)), this.aqT);
            }
        }
        K(map);
    }

    private void K(Map<String, Object> map) {
        Iterator<b> it = this.aqS.keySet().iterator();
        while (it.hasNext()) {
            it.next().C(map);
        }
    }

    private void a(Map<String, Object> map, String str, Collection<a> collection) {
        for (Map.Entry<String, Object> entry : map.entrySet()) {
            String str2 = str + (str.length() == 0 ? "" : ".") + entry.getKey();
            if (entry.getValue() instanceof Map) {
                a((Map) entry.getValue(), str2, collection);
            } else if (!str2.equals("gtm.lifetime")) {
                collection.add(new a(str2, entry.getValue()));
            }
        }
    }

    static Long cy(String str) {
        long j;
        Matcher matcher = aqR.matcher(str);
        if (!matcher.matches()) {
            bh.U("unknown _lifetime: " + str);
            return null;
        }
        try {
            j = Long.parseLong(matcher.group(1));
        } catch (NumberFormatException e) {
            bh.W("illegal number in _lifetime value: " + str);
            j = 0;
        }
        if (j <= 0) {
            bh.U("non-positive _lifetime: " + str);
            return null;
        }
        String strGroup = matcher.group(2);
        if (strGroup.length() == 0) {
            return Long.valueOf(j);
        }
        switch (strGroup.charAt(0)) {
            case 'd':
                return Long.valueOf(j * 1000 * 60 * 60 * 24);
            case LocationRequest.PRIORITY_LOW_POWER /* 104 */:
                return Long.valueOf(j * 1000 * 60 * 60);
            case 'm':
                return Long.valueOf(j * 1000 * 60);
            case 's':
                return Long.valueOf(j * 1000);
            default:
                bh.W("unknown units in _lifetime: " + str);
                return null;
        }
    }

    public static List<Object> listOf(Object... objects) {
        ArrayList arrayList = new ArrayList();
        for (Object obj : objects) {
            arrayList.add(obj);
        }
        return arrayList;
    }

    public static Map<String, Object> mapOf(Object... objects) {
        if (objects.length % 2 != 0) {
            throw new IllegalArgumentException("expected even number of key-value pairs");
        }
        HashMap map = new HashMap();
        int i = 0;
        while (true) {
            int i2 = i;
            if (i2 >= objects.length) {
                return map;
            }
            if (!(objects[i2] instanceof String)) {
                throw new IllegalArgumentException("key is not a string: " + objects[i2]);
            }
            map.put((String) objects[i2], objects[i2 + 1]);
            i = i2 + 2;
        }
    }

    private void pv() {
        this.aqW.a(new c.a() { // from class: com.google.android.gms.tagmanager.DataLayer.2
            @Override // com.google.android.gms.tagmanager.DataLayer.c.a
            public void h(List<a> list) {
                for (a aVar : list) {
                    DataLayer.this.E(DataLayer.this.c(aVar.KP, aVar.wF));
                }
                DataLayer.this.aqX.countDown();
            }
        });
    }

    private void pw() {
        int i = 0;
        do {
            int i2 = i;
            Map<String, Object> mapPoll = this.aqV.poll();
            if (mapPoll == null) {
                return;
            }
            J(mapPoll);
            i = i2 + 1;
        } while (i <= 500);
        this.aqV.clear();
        throw new RuntimeException("Seems like an infinite loop of pushing to the data layer");
    }

    void a(b bVar) {
        this.aqS.put(bVar, 0);
    }

    void a(Map<String, Object> map, Map<String, Object> map2) {
        for (String str : map.keySet()) {
            Object obj = map.get(str);
            if (obj instanceof List) {
                if (!(map2.get(str) instanceof List)) {
                    map2.put(str, new ArrayList());
                }
                b((List) obj, (List) map2.get(str));
            } else if (obj instanceof Map) {
                if (!(map2.get(str) instanceof Map)) {
                    map2.put(str, new HashMap());
                }
                a((Map<String, Object>) obj, (Map<String, Object>) map2.get(str));
            } else {
                map2.put(str, obj);
            }
        }
    }

    void b(List<Object> list, List<Object> list2) {
        while (list2.size() < list.size()) {
            list2.add(null);
        }
        int i = 0;
        while (true) {
            int i2 = i;
            if (i2 >= list.size()) {
                return;
            }
            Object obj = list.get(i2);
            if (obj instanceof List) {
                if (!(list2.get(i2) instanceof List)) {
                    list2.set(i2, new ArrayList());
                }
                b((List) obj, (List) list2.get(i2));
            } else if (obj instanceof Map) {
                if (!(list2.get(i2) instanceof Map)) {
                    list2.set(i2, new HashMap());
                }
                a((Map<String, Object>) obj, (Map<String, Object>) list2.get(i2));
            } else if (obj != OBJECT_NOT_PRESENT) {
                list2.set(i2, obj);
            }
            i = i2 + 1;
        }
    }

    Map<String, Object> c(String str, Object obj) {
        HashMap map = new HashMap();
        String[] strArrSplit = str.toString().split("\\.");
        int i = 0;
        HashMap map2 = map;
        while (i < strArrSplit.length - 1) {
            HashMap map3 = new HashMap();
            map2.put(strArrSplit[i], map3);
            i++;
            map2 = map3;
        }
        map2.put(strArrSplit[strArrSplit.length - 1], obj);
        return map;
    }

    void cx(String str) {
        push(str, null);
        this.aqW.cz(str);
    }

    public Object get(String key) {
        synchronized (this.aqT) {
            Map<String, Object> map = this.aqT;
            String[] strArrSplit = key.split("\\.");
            int length = strArrSplit.length;
            Object obj = map;
            int i = 0;
            while (i < length) {
                String str = strArrSplit[i];
                if (!(obj instanceof Map)) {
                    return null;
                }
                Object obj2 = ((Map) obj).get(str);
                if (obj2 == null) {
                    return null;
                }
                i++;
                obj = obj2;
            }
            return obj;
        }
    }

    public void push(String key, Object value) {
        push(c(key, value));
    }

    public void push(Map<String, Object> update) {
        try {
            this.aqX.await();
        } catch (InterruptedException e) {
            bh.W("DataLayer.push: unexpected InterruptedException");
        }
        E(update);
    }

    public void pushEvent(String eventName, Map<String, Object> update) {
        HashMap map = new HashMap(update);
        map.put("event", eventName);
        push(map);
    }

    public String toString() {
        String string;
        synchronized (this.aqT) {
            StringBuilder sb = new StringBuilder();
            for (Map.Entry<String, Object> entry : this.aqT.entrySet()) {
                sb.append(String.format("{\n\tKey: %s\n\tValue: %s\n}\n", entry.getKey(), entry.getValue()));
            }
            string = sb.toString();
        }
        return string;
    }
}
