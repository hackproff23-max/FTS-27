package com.google.android.gms.tagmanager;

import com.facebook.internal.ServerProtocol;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

/* JADX INFO: loaded from: classes.dex */
class di {
    private static final Object auc = null;
    private static Long aud = new Long(0);
    private static Double aue = new Double(0.0d);
    private static dh auf = dh.z(0);
    private static String aug = new String("");
    private static Boolean auh = new Boolean(false);
    private static List<Object> aui = new ArrayList(0);
    private static Map<Object, Object> auj = new HashMap();
    private static com.google.android.gms.internal.d.a auk = u(aug);

    public static com.google.android.gms.internal.d.a cZ(String str) {
        com.google.android.gms.internal.d.a aVar = new com.google.android.gms.internal.d.a();
        aVar.type = 5;
        aVar.gA = str;
        return aVar;
    }

    private static dh da(String str) {
        try {
            return dh.cY(str);
        } catch (NumberFormatException e) {
            bh.T("Failed to convert '" + str + "' to a number.");
            return auf;
        }
    }

    private static Long db(String str) {
        dh dhVarDa = da(str);
        return dhVarDa == auf ? aud : Long.valueOf(dhVarDa.longValue());
    }

    private static Double dc(String str) {
        dh dhVarDa = da(str);
        return dhVarDa == auf ? aue : Double.valueOf(dhVarDa.doubleValue());
    }

    private static Boolean dd(String str) {
        if (ServerProtocol.DIALOG_RETURN_SCOPES_TRUE.equalsIgnoreCase(str)) {
            return Boolean.TRUE;
        }
        return "false".equalsIgnoreCase(str) ? Boolean.FALSE : auh;
    }

    private static double getDouble(Object o) {
        if (o instanceof Number) {
            return ((Number) o).doubleValue();
        }
        bh.T("getDouble received non-Number");
        return 0.0d;
    }

    public static String j(com.google.android.gms.internal.d.a aVar) {
        return p(o(aVar));
    }

    public static dh k(com.google.android.gms.internal.d.a aVar) {
        return q(o(aVar));
    }

    public static Long l(com.google.android.gms.internal.d.a aVar) {
        return r(o(aVar));
    }

    public static Double m(com.google.android.gms.internal.d.a aVar) {
        return s(o(aVar));
    }

    public static Boolean n(com.google.android.gms.internal.d.a aVar) {
        return t(o(aVar));
    }

    public static Object o(com.google.android.gms.internal.d.a aVar) {
        int i = 0;
        if (aVar == null) {
            return auc;
        }
        switch (aVar.type) {
            case 1:
                return aVar.gv;
            case 2:
                ArrayList arrayList = new ArrayList(aVar.gw.length);
                com.google.android.gms.internal.d.a[] aVarArr = aVar.gw;
                int length = aVarArr.length;
                while (i < length) {
                    Object objO = o(aVarArr[i]);
                    if (objO == auc) {
                        return auc;
                    }
                    arrayList.add(objO);
                    i++;
                }
                return arrayList;
            case 3:
                if (aVar.gx.length != aVar.gy.length) {
                    bh.T("Converting an invalid value to object: " + aVar.toString());
                    return auc;
                }
                HashMap map = new HashMap(aVar.gy.length);
                while (i < aVar.gx.length) {
                    Object objO2 = o(aVar.gx[i]);
                    Object objO3 = o(aVar.gy[i]);
                    if (objO2 == auc || objO3 == auc) {
                        return auc;
                    }
                    map.put(objO2, objO3);
                    i++;
                }
                return map;
            case 4:
                bh.T("Trying to convert a macro reference to object");
                return auc;
            case 5:
                bh.T("Trying to convert a function id to object");
                return auc;
            case 6:
                return Long.valueOf(aVar.gB);
            case 7:
                StringBuffer stringBuffer = new StringBuffer();
                com.google.android.gms.internal.d.a[] aVarArr2 = aVar.gD;
                int length2 = aVarArr2.length;
                while (i < length2) {
                    String strJ = j(aVarArr2[i]);
                    if (strJ == aug) {
                        return auc;
                    }
                    stringBuffer.append(strJ);
                    i++;
                }
                return stringBuffer.toString();
            case 8:
                return Boolean.valueOf(aVar.gC);
            default:
                bh.T("Failed to convert a value of type: " + aVar.type);
                return auc;
        }
    }

    public static String p(Object obj) {
        return obj == null ? aug : obj.toString();
    }

    public static dh q(Object obj) {
        if (obj instanceof dh) {
            return (dh) obj;
        }
        if (w(obj)) {
            return dh.z(x(obj));
        }
        return v(obj) ? dh.a(Double.valueOf(getDouble(obj))) : da(p(obj));
    }

    public static Object qV() {
        return auc;
    }

    public static Long qW() {
        return aud;
    }

    public static Double qX() {
        return aue;
    }

    public static Boolean qY() {
        return auh;
    }

    public static dh qZ() {
        return auf;
    }

    public static Long r(Object obj) {
        return w(obj) ? Long.valueOf(x(obj)) : db(p(obj));
    }

    public static String ra() {
        return aug;
    }

    public static com.google.android.gms.internal.d.a rb() {
        return auk;
    }

    public static Double s(Object obj) {
        return v(obj) ? Double.valueOf(getDouble(obj)) : dc(p(obj));
    }

    public static Boolean t(Object obj) {
        return obj instanceof Boolean ? (Boolean) obj : dd(p(obj));
    }

    public static com.google.android.gms.internal.d.a u(Object obj) {
        boolean z = false;
        com.google.android.gms.internal.d.a aVar = new com.google.android.gms.internal.d.a();
        if (obj instanceof com.google.android.gms.internal.d.a) {
            return (com.google.android.gms.internal.d.a) obj;
        }
        if (obj instanceof String) {
            aVar.type = 1;
            aVar.gv = (String) obj;
        } else if (obj instanceof List) {
            aVar.type = 2;
            List list = (List) obj;
            ArrayList arrayList = new ArrayList(list.size());
            Iterator it = list.iterator();
            boolean z2 = false;
            while (it.hasNext()) {
                com.google.android.gms.internal.d.a aVarU = u(it.next());
                if (aVarU == auk) {
                    return auk;
                }
                boolean z3 = z2 || aVarU.gF;
                arrayList.add(aVarU);
                z2 = z3;
            }
            aVar.gw = (com.google.android.gms.internal.d.a[]) arrayList.toArray(new com.google.android.gms.internal.d.a[0]);
            z = z2;
        } else if (obj instanceof Map) {
            aVar.type = 3;
            Set<Map.Entry> setEntrySet = ((Map) obj).entrySet();
            ArrayList arrayList2 = new ArrayList(setEntrySet.size());
            ArrayList arrayList3 = new ArrayList(setEntrySet.size());
            boolean z4 = false;
            for (Map.Entry entry : setEntrySet) {
                com.google.android.gms.internal.d.a aVarU2 = u(entry.getKey());
                com.google.android.gms.internal.d.a aVarU3 = u(entry.getValue());
                if (aVarU2 == auk || aVarU3 == auk) {
                    return auk;
                }
                boolean z5 = z4 || aVarU2.gF || aVarU3.gF;
                arrayList2.add(aVarU2);
                arrayList3.add(aVarU3);
                z4 = z5;
            }
            aVar.gx = (com.google.android.gms.internal.d.a[]) arrayList2.toArray(new com.google.android.gms.internal.d.a[0]);
            aVar.gy = (com.google.android.gms.internal.d.a[]) arrayList3.toArray(new com.google.android.gms.internal.d.a[0]);
            z = z4;
        } else if (v(obj)) {
            aVar.type = 1;
            aVar.gv = obj.toString();
        } else if (w(obj)) {
            aVar.type = 6;
            aVar.gB = x(obj);
        } else {
            if (!(obj instanceof Boolean)) {
                bh.T("Converting to Value from unknown object type: " + (obj == null ? "null" : obj.getClass().toString()));
                return auk;
            }
            aVar.type = 8;
            aVar.gC = ((Boolean) obj).booleanValue();
        }
        aVar.gF = z;
        return aVar;
    }

    private static boolean v(Object obj) {
        return (obj instanceof Double) || (obj instanceof Float) || ((obj instanceof dh) && ((dh) obj).qQ());
    }

    private static boolean w(Object obj) {
        return (obj instanceof Byte) || (obj instanceof Short) || (obj instanceof Integer) || (obj instanceof Long) || ((obj instanceof dh) && ((dh) obj).qR());
    }

    private static long x(Object obj) {
        if (obj instanceof Number) {
            return ((Number) obj).longValue();
        }
        bh.T("getInt64 received non-Number");
        return 0L;
    }
}
