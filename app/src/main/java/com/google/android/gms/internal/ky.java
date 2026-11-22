package com.google.android.gms.internal;

import android.os.Bundle;
import android.os.Parcel;
import com.facebook.ads.InterstitialAd;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

/* JADX INFO: loaded from: classes.dex */
public class ky extends kr implements SafeParcelable {
    public static final kz CREATOR = new kz();
    private final int CK;
    private final kv NT;
    private final Parcel Oa;
    private final int Ob;
    private int Oc;
    private int Od;
    private final String mClassName;

    ky(int i, Parcel parcel, kv kvVar) {
        this.CK = i;
        this.Oa = (Parcel) jx.i(parcel);
        this.Ob = 2;
        this.NT = kvVar;
        if (this.NT == null) {
            this.mClassName = null;
        } else {
            this.mClassName = this.NT.ia();
        }
        this.Oc = 2;
    }

    private ky(SafeParcelable safeParcelable, kv kvVar, String str) {
        this.CK = 1;
        this.Oa = Parcel.obtain();
        safeParcelable.writeToParcel(this.Oa, 0);
        this.Ob = 1;
        this.NT = (kv) jx.i(kvVar);
        this.mClassName = (String) jx.i(str);
        this.Oc = 2;
    }

    public static <T extends kr & SafeParcelable> ky a(T t) {
        String canonicalName = t.getClass().getCanonicalName();
        return new ky(t, b(t), canonicalName);
    }

    /* JADX WARN: Multi-variable type inference failed */
    private static void a(kv kvVar, kr krVar) {
        Class<?> cls = krVar.getClass();
        if (kvVar.b(cls)) {
            return;
        }
        HashMap<String, kr.a<?, ?>> mapHK = krVar.hK();
        kvVar.a(cls, krVar.hK());
        Iterator<String> it = mapHK.keySet().iterator();
        while (it.hasNext()) {
            kr.a<?, ?> aVar = mapHK.get(it.next());
            Class<? extends kr> clsHS = aVar.hS();
            if (clsHS != null) {
                try {
                    a(kvVar, clsHS.newInstance());
                } catch (IllegalAccessException e) {
                    throw new IllegalStateException("Could not access object of type " + aVar.hS().getCanonicalName(), e);
                } catch (InstantiationException e2) {
                    throw new IllegalStateException("Could not instantiate an object of type " + aVar.hS().getCanonicalName(), e2);
                }
            }
        }
    }

    private void a(StringBuilder sb, int i, Object obj) {
        switch (i) {
            case 0:
            case 1:
            case 2:
            case 3:
            case 4:
            case 5:
            case 6:
                sb.append(obj);
                return;
            case 7:
                sb.append("\"").append(li.bh(obj.toString())).append("\"");
                return;
            case 8:
                sb.append("\"").append(lb.d((byte[]) obj)).append("\"");
                return;
            case 9:
                sb.append("\"").append(lb.e((byte[]) obj));
                sb.append("\"");
                return;
            case 10:
                lj.a(sb, (HashMap) obj);
                return;
            case 11:
                throw new IllegalArgumentException("Method does not accept concrete type.");
            default:
                throw new IllegalArgumentException("Unknown type = " + i);
        }
    }

    private void a(StringBuilder sb, kr.a<?, ?> aVar, Parcel parcel, int i) {
        switch (aVar.hJ()) {
            case 0:
                b(sb, aVar, a(aVar, Integer.valueOf(com.google.android.gms.common.internal.safeparcel.a.g(parcel, i))));
                return;
            case 1:
                b(sb, aVar, a(aVar, com.google.android.gms.common.internal.safeparcel.a.k(parcel, i)));
                return;
            case 2:
                b(sb, aVar, a(aVar, Long.valueOf(com.google.android.gms.common.internal.safeparcel.a.i(parcel, i))));
                return;
            case 3:
                b(sb, aVar, a(aVar, Float.valueOf(com.google.android.gms.common.internal.safeparcel.a.l(parcel, i))));
                return;
            case 4:
                b(sb, aVar, a(aVar, Double.valueOf(com.google.android.gms.common.internal.safeparcel.a.m(parcel, i))));
                return;
            case 5:
                b(sb, aVar, a(aVar, com.google.android.gms.common.internal.safeparcel.a.n(parcel, i)));
                return;
            case 6:
                b(sb, aVar, a(aVar, Boolean.valueOf(com.google.android.gms.common.internal.safeparcel.a.c(parcel, i))));
                return;
            case 7:
                b(sb, aVar, a(aVar, com.google.android.gms.common.internal.safeparcel.a.o(parcel, i)));
                return;
            case 8:
            case 9:
                b(sb, aVar, a(aVar, com.google.android.gms.common.internal.safeparcel.a.r(parcel, i)));
                return;
            case 10:
                b(sb, aVar, a(aVar, g(com.google.android.gms.common.internal.safeparcel.a.q(parcel, i))));
                return;
            case 11:
                throw new IllegalArgumentException("Method does not accept concrete type.");
            default:
                throw new IllegalArgumentException("Unknown field out type = " + aVar.hJ());
        }
    }

    private void a(StringBuilder sb, String str, kr.a<?, ?> aVar, Parcel parcel, int i) {
        sb.append("\"").append(str).append("\":");
        if (aVar.hU()) {
            a(sb, aVar, parcel, i);
        } else {
            b(sb, aVar, parcel, i);
        }
    }

    private void a(StringBuilder sb, HashMap<String, kr.a<?, ?>> map, Parcel parcel) {
        HashMap<Integer, Map.Entry<String, kr.a<?, ?>>> mapB = b(map);
        sb.append('{');
        int iG = com.google.android.gms.common.internal.safeparcel.a.G(parcel);
        boolean z = false;
        while (parcel.dataPosition() < iG) {
            int iF = com.google.android.gms.common.internal.safeparcel.a.F(parcel);
            Map.Entry<String, kr.a<?, ?>> entry = mapB.get(Integer.valueOf(com.google.android.gms.common.internal.safeparcel.a.aH(iF)));
            if (entry != null) {
                if (z) {
                    sb.append(",");
                }
                a(sb, entry.getKey(), entry.getValue(), parcel, iF);
                z = true;
            }
        }
        if (parcel.dataPosition() != iG) {
            throw new com.google.android.gms.common.internal.safeparcel.a.C0039a("Overread allowed size end=" + iG, parcel);
        }
        sb.append('}');
    }

    private static kv b(kr krVar) {
        kv kvVar = new kv(krVar.getClass());
        a(kvVar, krVar);
        kvVar.hY();
        kvVar.hX();
        return kvVar;
    }

    private static HashMap<Integer, Map.Entry<String, kr.a<?, ?>>> b(HashMap<String, kr.a<?, ?>> map) {
        HashMap<Integer, Map.Entry<String, kr.a<?, ?>>> map2 = new HashMap<>();
        for (Map.Entry<String, kr.a<?, ?>> entry : map.entrySet()) {
            map2.put(Integer.valueOf(entry.getValue().hR()), entry);
        }
        return map2;
    }

    private void b(StringBuilder sb, kr.a<?, ?> aVar, Parcel parcel, int i) {
        if (aVar.hP()) {
            sb.append("[");
            switch (aVar.hJ()) {
                case 0:
                    la.a(sb, com.google.android.gms.common.internal.safeparcel.a.u(parcel, i));
                    break;
                case 1:
                    la.a(sb, com.google.android.gms.common.internal.safeparcel.a.w(parcel, i));
                    break;
                case 2:
                    la.a(sb, com.google.android.gms.common.internal.safeparcel.a.v(parcel, i));
                    break;
                case 3:
                    la.a(sb, com.google.android.gms.common.internal.safeparcel.a.x(parcel, i));
                    break;
                case 4:
                    la.a(sb, com.google.android.gms.common.internal.safeparcel.a.y(parcel, i));
                    break;
                case 5:
                    la.a(sb, com.google.android.gms.common.internal.safeparcel.a.z(parcel, i));
                    break;
                case 6:
                    la.a(sb, com.google.android.gms.common.internal.safeparcel.a.t(parcel, i));
                    break;
                case 7:
                    la.a(sb, com.google.android.gms.common.internal.safeparcel.a.A(parcel, i));
                    break;
                case 8:
                case 9:
                case 10:
                    throw new UnsupportedOperationException("List of type BASE64, BASE64_URL_SAFE, or STRING_MAP is not supported");
                case 11:
                    Parcel[] parcelArrE = com.google.android.gms.common.internal.safeparcel.a.E(parcel, i);
                    int length = parcelArrE.length;
                    for (int i2 = 0; i2 < length; i2++) {
                        if (i2 > 0) {
                            sb.append(",");
                        }
                        parcelArrE[i2].setDataPosition(0);
                        a(sb, aVar.hW(), parcelArrE[i2]);
                    }
                    break;
                default:
                    throw new IllegalStateException("Unknown field type out.");
            }
            sb.append("]");
            return;
        }
        switch (aVar.hJ()) {
            case 0:
                sb.append(com.google.android.gms.common.internal.safeparcel.a.g(parcel, i));
                return;
            case 1:
                sb.append(com.google.android.gms.common.internal.safeparcel.a.k(parcel, i));
                return;
            case 2:
                sb.append(com.google.android.gms.common.internal.safeparcel.a.i(parcel, i));
                return;
            case 3:
                sb.append(com.google.android.gms.common.internal.safeparcel.a.l(parcel, i));
                return;
            case 4:
                sb.append(com.google.android.gms.common.internal.safeparcel.a.m(parcel, i));
                return;
            case 5:
                sb.append(com.google.android.gms.common.internal.safeparcel.a.n(parcel, i));
                return;
            case 6:
                sb.append(com.google.android.gms.common.internal.safeparcel.a.c(parcel, i));
                return;
            case 7:
                sb.append("\"").append(li.bh(com.google.android.gms.common.internal.safeparcel.a.o(parcel, i))).append("\"");
                return;
            case 8:
                sb.append("\"").append(lb.d(com.google.android.gms.common.internal.safeparcel.a.r(parcel, i))).append("\"");
                return;
            case 9:
                sb.append("\"").append(lb.e(com.google.android.gms.common.internal.safeparcel.a.r(parcel, i)));
                sb.append("\"");
                return;
            case 10:
                Bundle bundleQ = com.google.android.gms.common.internal.safeparcel.a.q(parcel, i);
                Set<String> setKeySet = bundleQ.keySet();
                setKeySet.size();
                sb.append("{");
                boolean z = true;
                for (String str : setKeySet) {
                    if (!z) {
                        sb.append(",");
                    }
                    sb.append("\"").append(str).append("\"");
                    sb.append(InterstitialAd.SEPARATOR);
                    sb.append("\"").append(li.bh(bundleQ.getString(str))).append("\"");
                    z = false;
                }
                sb.append("}");
                return;
            case 11:
                Parcel parcelD = com.google.android.gms.common.internal.safeparcel.a.D(parcel, i);
                parcelD.setDataPosition(0);
                a(sb, aVar.hW(), parcelD);
                return;
            default:
                throw new IllegalStateException("Unknown field type out");
        }
    }

    private void b(StringBuilder sb, kr.a<?, ?> aVar, Object obj) {
        if (aVar.hO()) {
            b(sb, aVar, (ArrayList<?>) obj);
        } else {
            a(sb, aVar.hI(), obj);
        }
    }

    private void b(StringBuilder sb, kr.a<?, ?> aVar, ArrayList<?> arrayList) {
        sb.append("[");
        int size = arrayList.size();
        for (int i = 0; i < size; i++) {
            if (i != 0) {
                sb.append(",");
            }
            a(sb, aVar.hI(), arrayList.get(i));
        }
        sb.append("]");
    }

    public static HashMap<String, String> g(Bundle bundle) {
        HashMap<String, String> map = new HashMap<>();
        for (String str : bundle.keySet()) {
            map.put(str, bundle.getString(str));
        }
        return map;
    }

    @Override // com.google.android.gms.internal.kr
    protected Object bc(String str) {
        throw new UnsupportedOperationException("Converting to JSON does not require this method.");
    }

    @Override // com.google.android.gms.internal.kr
    protected boolean bd(String str) {
        throw new UnsupportedOperationException("Converting to JSON does not require this method.");
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        kz kzVar = CREATOR;
        return 0;
    }

    public int getVersionCode() {
        return this.CK;
    }

    @Override // com.google.android.gms.internal.kr
    public HashMap<String, kr.a<?, ?>> hK() {
        if (this.NT == null) {
            return null;
        }
        return this.NT.bg(this.mClassName);
    }

    public Parcel ic() {
        switch (this.Oc) {
            case 0:
                this.Od = com.google.android.gms.common.internal.safeparcel.b.H(this.Oa);
                com.google.android.gms.common.internal.safeparcel.b.H(this.Oa, this.Od);
                this.Oc = 2;
                break;
            case 1:
                com.google.android.gms.common.internal.safeparcel.b.H(this.Oa, this.Od);
                this.Oc = 2;
                break;
        }
        return this.Oa;
    }

    kv id() {
        switch (this.Ob) {
            case 0:
                return null;
            case 1:
                return this.NT;
            case 2:
                return this.NT;
            default:
                throw new IllegalStateException("Invalid creation type: " + this.Ob);
        }
    }

    @Override // com.google.android.gms.internal.kr
    public String toString() {
        jx.b(this.NT, "Cannot convert to JSON on client side.");
        Parcel parcelIc = ic();
        parcelIc.setDataPosition(0);
        StringBuilder sb = new StringBuilder(100);
        a(sb, this.NT.bg(this.mClassName), parcelIc);
        return sb.toString();
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel out, int flags) {
        kz kzVar = CREATOR;
        kz.a(this, out, flags);
    }
}
