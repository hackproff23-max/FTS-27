package com.google.android.gms.internal;

import android.os.Parcel;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

/* JADX INFO: loaded from: classes.dex */
public class kv implements SafeParcelable {
    public static final kw CREATOR = new kw();
    private final int CK;
    private final HashMap<String, HashMap<String, kr.a<?, ?>>> NV;
    private final ArrayList<a> NW;
    private final String NX;

    public static class a implements SafeParcelable {
        public static final kx CREATOR = new kx();
        final ArrayList<b> NY;
        final String className;
        final int versionCode;

        a(int i, String str, ArrayList<b> arrayList) {
            this.versionCode = i;
            this.className = str;
            this.NY = arrayList;
        }

        a(String str, HashMap<String, kr.a<?, ?>> map) {
            this.versionCode = 1;
            this.className = str;
            this.NY = a(map);
        }

        private static ArrayList<b> a(HashMap<String, kr.a<?, ?>> map) {
            if (map == null) {
                return null;
            }
            ArrayList<b> arrayList = new ArrayList<>();
            for (String str : map.keySet()) {
                arrayList.add(new b(str, map.get(str)));
            }
            return arrayList;
        }

        @Override // android.os.Parcelable
        public int describeContents() {
            kx kxVar = CREATOR;
            return 0;
        }

        HashMap<String, kr.a<?, ?>> ib() {
            HashMap<String, kr.a<?, ?>> map = new HashMap<>();
            int size = this.NY.size();
            for (int i = 0; i < size; i++) {
                b bVar = this.NY.get(i);
                map.put(bVar.fv, bVar.NZ);
            }
            return map;
        }

        @Override // android.os.Parcelable
        public void writeToParcel(Parcel out, int flags) {
            kx kxVar = CREATOR;
            kx.a(this, out, flags);
        }
    }

    public static class b implements SafeParcelable {
        public static final ku CREATOR = new ku();
        final kr.a<?, ?> NZ;
        final String fv;
        final int versionCode;

        b(int i, String str, kr.a<?, ?> aVar) {
            this.versionCode = i;
            this.fv = str;
            this.NZ = aVar;
        }

        b(String str, kr.a<?, ?> aVar) {
            this.versionCode = 1;
            this.fv = str;
            this.NZ = aVar;
        }

        @Override // android.os.Parcelable
        public int describeContents() {
            ku kuVar = CREATOR;
            return 0;
        }

        @Override // android.os.Parcelable
        public void writeToParcel(Parcel out, int flags) {
            ku kuVar = CREATOR;
            ku.a(this, out, flags);
        }
    }

    kv(int i, ArrayList<a> arrayList, String str) {
        this.CK = i;
        this.NW = null;
        this.NV = c(arrayList);
        this.NX = (String) jx.i(str);
        hX();
    }

    public kv(Class<? extends kr> cls) {
        this.CK = 1;
        this.NW = null;
        this.NV = new HashMap<>();
        this.NX = cls.getCanonicalName();
    }

    private static HashMap<String, HashMap<String, kr.a<?, ?>>> c(ArrayList<a> arrayList) {
        HashMap<String, HashMap<String, kr.a<?, ?>>> map = new HashMap<>();
        int size = arrayList.size();
        for (int i = 0; i < size; i++) {
            a aVar = arrayList.get(i);
            map.put(aVar.className, aVar.ib());
        }
        return map;
    }

    public void a(Class<? extends kr> cls, HashMap<String, kr.a<?, ?>> map) {
        this.NV.put(cls.getCanonicalName(), map);
    }

    public boolean b(Class<? extends kr> cls) {
        return this.NV.containsKey(cls.getCanonicalName());
    }

    public HashMap<String, kr.a<?, ?>> bg(String str) {
        return this.NV.get(str);
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        kw kwVar = CREATOR;
        return 0;
    }

    int getVersionCode() {
        return this.CK;
    }

    public void hX() {
        Iterator<String> it = this.NV.keySet().iterator();
        while (it.hasNext()) {
            HashMap<String, kr.a<?, ?>> map = this.NV.get(it.next());
            Iterator<String> it2 = map.keySet().iterator();
            while (it2.hasNext()) {
                map.get(it2.next()).a(this);
            }
        }
    }

    public void hY() {
        for (String str : this.NV.keySet()) {
            HashMap<String, kr.a<?, ?>> map = this.NV.get(str);
            HashMap<String, kr.a<?, ?>> map2 = new HashMap<>();
            for (String str2 : map.keySet()) {
                map2.put(str2, map.get(str2).hN());
            }
            this.NV.put(str, map2);
        }
    }

    ArrayList<a> hZ() {
        ArrayList<a> arrayList = new ArrayList<>();
        for (String str : this.NV.keySet()) {
            arrayList.add(new a(str, this.NV.get(str)));
        }
        return arrayList;
    }

    public String ia() {
        return this.NX;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (String str : this.NV.keySet()) {
            sb.append(str).append(":\n");
            HashMap<String, kr.a<?, ?>> map = this.NV.get(str);
            for (String str2 : map.keySet()) {
                sb.append("  ").append(str2).append(": ");
                sb.append(map.get(str2));
            }
        }
        return sb.toString();
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel out, int flags) {
        kw kwVar = CREATOR;
        kw.a(this, out, flags);
    }
}
