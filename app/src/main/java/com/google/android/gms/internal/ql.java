package com.google.android.gms.internal;

import com.google.android.gms.wearable.Asset;
import com.google.android.gms.wearable.DataMap;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

/* JADX INFO: loaded from: classes.dex */
public final class ql {

    public static class a {
        public final qm ayo;
        public final List<Asset> ayp;

        public a(qm qmVar, List<Asset> list) {
            this.ayo = qmVar;
            this.ayp = list;
        }
    }

    private static int a(String str, qm.a.C0126a[] c0126aArr) {
        int i = 14;
        for (qm.a.C0126a c0126a : c0126aArr) {
            if (i != 14) {
                if (c0126a.type != i) {
                    throw new IllegalArgumentException("The ArrayList elements should all be the same type, but ArrayList with key " + str + " contains items of type " + i + " and " + c0126a.type);
                }
            } else if (c0126a.type == 9 || c0126a.type == 2 || c0126a.type == 6) {
                i = c0126a.type;
            } else if (c0126a.type != 14) {
                throw new IllegalArgumentException("Unexpected TypedValue type: " + c0126a.type + " for key " + str);
            }
        }
        return i;
    }

    static int a(List<Asset> list, Asset asset) {
        list.add(asset);
        return list.size() - 1;
    }

    public static a a(DataMap dataMap) {
        qm qmVar = new qm();
        ArrayList arrayList = new ArrayList();
        qmVar.ayq = a(dataMap, arrayList);
        return new a(qmVar, arrayList);
    }

    private static qm.a.C0126a a(List<Asset> list, Object obj) {
        int i;
        int i2 = 0;
        qm.a.C0126a c0126a = new qm.a.C0126a();
        if (obj == null) {
            c0126a.type = 14;
            return c0126a;
        }
        c0126a.ayu = new qm.a.C0126a.C0127a();
        if (obj instanceof String) {
            c0126a.type = 2;
            c0126a.ayu.ayw = (String) obj;
        } else if (obj instanceof Integer) {
            c0126a.type = 6;
            c0126a.ayu.ayA = ((Integer) obj).intValue();
        } else if (obj instanceof Long) {
            c0126a.type = 5;
            c0126a.ayu.ayz = ((Long) obj).longValue();
        } else if (obj instanceof Double) {
            c0126a.type = 3;
            c0126a.ayu.ayx = ((Double) obj).doubleValue();
        } else if (obj instanceof Float) {
            c0126a.type = 4;
            c0126a.ayu.ayy = ((Float) obj).floatValue();
        } else if (obj instanceof Boolean) {
            c0126a.type = 8;
            c0126a.ayu.ayC = ((Boolean) obj).booleanValue();
        } else if (obj instanceof Byte) {
            c0126a.type = 7;
            c0126a.ayu.ayB = ((Byte) obj).byteValue();
        } else if (obj instanceof byte[]) {
            c0126a.type = 1;
            c0126a.ayu.ayv = (byte[]) obj;
        } else if (obj instanceof String[]) {
            c0126a.type = 11;
            c0126a.ayu.ayF = (String[]) obj;
        } else if (obj instanceof long[]) {
            c0126a.type = 12;
            c0126a.ayu.ayG = (long[]) obj;
        } else if (obj instanceof float[]) {
            c0126a.type = 15;
            c0126a.ayu.ayH = (float[]) obj;
        } else if (obj instanceof Asset) {
            c0126a.type = 13;
            c0126a.ayu.ayI = a(list, (Asset) obj);
        } else if (obj instanceof DataMap) {
            c0126a.type = 9;
            DataMap dataMap = (DataMap) obj;
            Set<String> setKeySet = dataMap.keySet();
            qm.a[] aVarArr = new qm.a[setKeySet.size()];
            Iterator<String> it = setKeySet.iterator();
            while (true) {
                int i3 = i2;
                if (!it.hasNext()) {
                    break;
                }
                String next = it.next();
                aVarArr[i3] = new qm.a();
                aVarArr[i3].name = next;
                aVarArr[i3].ays = a(list, dataMap.get(next));
                i2 = i3 + 1;
            }
            c0126a.ayu.ayD = aVarArr;
        } else {
            if (!(obj instanceof ArrayList)) {
                throw new RuntimeException("newFieldValueFromValue: unexpected value " + obj.getClass().getSimpleName());
            }
            c0126a.type = 10;
            ArrayList arrayList = (ArrayList) obj;
            qm.a.C0126a[] c0126aArr = new qm.a.C0126a[arrayList.size()];
            Object obj2 = null;
            int size = arrayList.size();
            int i4 = 0;
            int i5 = 14;
            while (i4 < size) {
                Object obj3 = arrayList.get(i4);
                qm.a.C0126a c0126aA = a(list, obj3);
                if (c0126aA.type != 14 && c0126aA.type != 2 && c0126aA.type != 6 && c0126aA.type != 9) {
                    throw new IllegalArgumentException("The only ArrayList element types supported by DataBundleUtil are String, Integer, Bundle, and null, but this ArrayList contains a " + obj3.getClass());
                }
                if (i5 == 14 && c0126aA.type != 14) {
                    i = c0126aA.type;
                } else {
                    if (c0126aA.type != i5) {
                        throw new IllegalArgumentException("ArrayList elements must all be of the sameclass, but this one contains a " + obj2.getClass() + " and a " + obj3.getClass());
                    }
                    obj3 = obj2;
                    i = i5;
                }
                c0126aArr[i4] = c0126aA;
                i4++;
                i5 = i;
                obj2 = obj3;
            }
            c0126a.ayu.ayE = c0126aArr;
        }
        return c0126a;
    }

    public static DataMap a(a aVar) {
        DataMap dataMap = new DataMap();
        for (qm.a aVar2 : aVar.ayo.ayq) {
            a(aVar.ayp, dataMap, aVar2.name, aVar2.ays);
        }
        return dataMap;
    }

    private static ArrayList a(List<Asset> list, qm.a.C0126a.C0127a c0127a, int i) {
        ArrayList arrayList = new ArrayList(c0127a.ayE.length);
        for (qm.a.C0126a c0126a : c0127a.ayE) {
            if (c0126a.type == 14) {
                arrayList.add(null);
            } else if (i == 9) {
                DataMap dataMap = new DataMap();
                qm.a[] aVarArr = c0126a.ayu.ayD;
                for (qm.a aVar : aVarArr) {
                    a(list, dataMap, aVar.name, aVar.ays);
                }
                arrayList.add(dataMap);
            } else if (i == 2) {
                arrayList.add(c0126a.ayu.ayw);
            } else {
                if (i != 6) {
                    throw new IllegalArgumentException("Unexpected typeOfArrayList: " + i);
                }
                arrayList.add(Integer.valueOf(c0126a.ayu.ayA));
            }
        }
        return arrayList;
    }

    private static void a(List<Asset> list, DataMap dataMap, String str, qm.a.C0126a c0126a) {
        int i = c0126a.type;
        if (i == 14) {
            dataMap.putString(str, null);
            return;
        }
        qm.a.C0126a.C0127a c0127a = c0126a.ayu;
        if (i == 1) {
            dataMap.putByteArray(str, c0127a.ayv);
            return;
        }
        if (i == 11) {
            dataMap.putStringArray(str, c0127a.ayF);
            return;
        }
        if (i == 12) {
            dataMap.putLongArray(str, c0127a.ayG);
            return;
        }
        if (i == 15) {
            dataMap.putFloatArray(str, c0127a.ayH);
            return;
        }
        if (i == 2) {
            dataMap.putString(str, c0127a.ayw);
            return;
        }
        if (i == 3) {
            dataMap.putDouble(str, c0127a.ayx);
            return;
        }
        if (i == 4) {
            dataMap.putFloat(str, c0127a.ayy);
            return;
        }
        if (i == 5) {
            dataMap.putLong(str, c0127a.ayz);
            return;
        }
        if (i == 6) {
            dataMap.putInt(str, c0127a.ayA);
            return;
        }
        if (i == 7) {
            dataMap.putByte(str, (byte) c0127a.ayB);
            return;
        }
        if (i == 8) {
            dataMap.putBoolean(str, c0127a.ayC);
            return;
        }
        if (i == 13) {
            if (list == null) {
                throw new RuntimeException("populateBundle: unexpected type for: " + str);
            }
            dataMap.putAsset(str, list.get((int) c0127a.ayI));
            return;
        }
        if (i == 9) {
            DataMap dataMap2 = new DataMap();
            for (qm.a aVar : c0127a.ayD) {
                a(list, dataMap2, aVar.name, aVar.ays);
            }
            dataMap.putDataMap(str, dataMap2);
            return;
        }
        if (i != 10) {
            throw new RuntimeException("populateBundle: unexpected type " + i);
        }
        int iA = a(str, c0127a.ayE);
        ArrayList<Integer> arrayListA = a(list, c0127a, iA);
        if (iA == 14) {
            dataMap.putStringArrayList(str, arrayListA);
            return;
        }
        if (iA == 9) {
            dataMap.putDataMapArrayList(str, arrayListA);
        } else if (iA == 2) {
            dataMap.putStringArrayList(str, arrayListA);
        } else {
            if (iA != 6) {
                throw new IllegalStateException("Unexpected typeOfArrayList: " + iA);
            }
            dataMap.putIntegerArrayList(str, arrayListA);
        }
    }

    private static qm.a[] a(DataMap dataMap, List<Asset> list) {
        Set<String> setKeySet = dataMap.keySet();
        qm.a[] aVarArr = new qm.a[setKeySet.size()];
        int i = 0;
        Iterator<String> it = setKeySet.iterator();
        while (true) {
            int i2 = i;
            if (!it.hasNext()) {
                return aVarArr;
            }
            String next = it.next();
            Object obj = dataMap.get(next);
            aVarArr[i2] = new qm.a();
            aVarArr[i2].name = next;
            aVarArr[i2].ays = a(list, obj);
            i = i2 + 1;
        }
    }
}
