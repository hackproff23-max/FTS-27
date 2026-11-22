package com.google.android.gms.internal;

import android.os.Parcel;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import java.util.ArrayList;
import java.util.HashMap;

/* JADX INFO: loaded from: classes.dex */
public abstract class kr {

    public static class a<I, O> implements SafeParcelable {
        public static final kt CREATOR = new kt();
        private final int CK;
        protected final int NL;
        protected final boolean NM;
        protected final int NN;
        protected final boolean NO;
        protected final String NP;
        protected final int NQ;
        protected final Class<? extends kr> NR;
        protected final String NS;
        private kv NT;
        private b<I, O> NU;

        a(int i, int i2, boolean z, int i3, boolean z2, String str, int i4, String str2, km kmVar) {
            this.CK = i;
            this.NL = i2;
            this.NM = z;
            this.NN = i3;
            this.NO = z2;
            this.NP = str;
            this.NQ = i4;
            if (str2 == null) {
                this.NR = null;
                this.NS = null;
            } else {
                this.NR = ky.class;
                this.NS = str2;
            }
            if (kmVar == null) {
                this.NU = null;
            } else {
                this.NU = (b<I, O>) kmVar.hG();
            }
        }

        protected a(int i, boolean z, int i2, boolean z2, String str, int i3, Class<? extends kr> cls, b<I, O> bVar) {
            this.CK = 1;
            this.NL = i;
            this.NM = z;
            this.NN = i2;
            this.NO = z2;
            this.NP = str;
            this.NQ = i3;
            this.NR = cls;
            if (cls == null) {
                this.NS = null;
            } else {
                this.NS = cls.getCanonicalName();
            }
            this.NU = bVar;
        }

        public static a a(String str, int i, b<?, ?> bVar, boolean z) {
            return new a(bVar.hI(), z, bVar.hJ(), false, str, i, null, bVar);
        }

        public static <T extends kr> a<T, T> a(String str, int i, Class<T> cls) {
            return new a<>(11, false, 11, false, str, i, cls, null);
        }

        public static <T extends kr> a<ArrayList<T>, ArrayList<T>> b(String str, int i, Class<T> cls) {
            return new a<>(11, true, 11, true, str, i, cls, null);
        }

        public static a<Integer, Integer> i(String str, int i) {
            return new a<>(0, false, 0, false, str, i, null, null);
        }

        public static a<Double, Double> j(String str, int i) {
            return new a<>(4, false, 4, false, str, i, null, null);
        }

        public static a<Boolean, Boolean> k(String str, int i) {
            return new a<>(6, false, 6, false, str, i, null, null);
        }

        public static a<String, String> l(String str, int i) {
            return new a<>(7, false, 7, false, str, i, null, null);
        }

        public static a<ArrayList<String>, ArrayList<String>> m(String str, int i) {
            return new a<>(7, true, 7, true, str, i, null, null);
        }

        public void a(kv kvVar) {
            this.NT = kvVar;
        }

        public I convertBack(O output) {
            return this.NU.convertBack(output);
        }

        @Override // android.os.Parcelable
        public int describeContents() {
            kt ktVar = CREATOR;
            return 0;
        }

        public int getVersionCode() {
            return this.CK;
        }

        public int hI() {
            return this.NL;
        }

        public int hJ() {
            return this.NN;
        }

        public a<I, O> hN() {
            return new a<>(this.CK, this.NL, this.NM, this.NN, this.NO, this.NP, this.NQ, this.NS, hV());
        }

        public boolean hO() {
            return this.NM;
        }

        public boolean hP() {
            return this.NO;
        }

        public String hQ() {
            return this.NP;
        }

        public int hR() {
            return this.NQ;
        }

        public Class<? extends kr> hS() {
            return this.NR;
        }

        String hT() {
            if (this.NS == null) {
                return null;
            }
            return this.NS;
        }

        public boolean hU() {
            return this.NU != null;
        }

        km hV() {
            if (this.NU == null) {
                return null;
            }
            return km.a(this.NU);
        }

        public HashMap<String, a<?, ?>> hW() {
            jx.i(this.NS);
            jx.i(this.NT);
            return this.NT.bg(this.NS);
        }

        public String toString() {
            StringBuilder sb = new StringBuilder();
            sb.append("Field\n");
            sb.append("            versionCode=").append(this.CK).append('\n');
            sb.append("                 typeIn=").append(this.NL).append('\n');
            sb.append("            typeInArray=").append(this.NM).append('\n');
            sb.append("                typeOut=").append(this.NN).append('\n');
            sb.append("           typeOutArray=").append(this.NO).append('\n');
            sb.append("        outputFieldName=").append(this.NP).append('\n');
            sb.append("      safeParcelFieldId=").append(this.NQ).append('\n');
            sb.append("       concreteTypeName=").append(hT()).append('\n');
            if (hS() != null) {
                sb.append("     concreteType.class=").append(hS().getCanonicalName()).append('\n');
            }
            sb.append("          converterName=").append(this.NU == null ? "null" : this.NU.getClass().getCanonicalName()).append('\n');
            return sb.toString();
        }

        @Override // android.os.Parcelable
        public void writeToParcel(Parcel out, int flags) {
            kt ktVar = CREATOR;
            kt.a(this, out, flags);
        }
    }

    public interface b<I, O> {
        I convertBack(O o);

        int hI();

        int hJ();
    }

    private void a(StringBuilder sb, a aVar, Object obj) {
        if (aVar.hI() == 11) {
            sb.append(aVar.hS().cast(obj).toString());
        } else {
            if (aVar.hI() != 7) {
                sb.append(obj);
                return;
            }
            sb.append("\"");
            sb.append(li.bh((String) obj));
            sb.append("\"");
        }
    }

    private void a(StringBuilder sb, a aVar, ArrayList<Object> arrayList) {
        sb.append("[");
        int size = arrayList.size();
        for (int i = 0; i < size; i++) {
            if (i > 0) {
                sb.append(",");
            }
            Object obj = arrayList.get(i);
            if (obj != null) {
                a(sb, aVar, obj);
            }
        }
        sb.append("]");
    }

    /* JADX WARN: Multi-variable type inference failed */
    protected <O, I> I a(a<I, O> aVar, Object obj) {
        return ((a) aVar).NU != null ? aVar.convertBack(obj) : obj;
    }

    protected boolean a(a aVar) {
        if (aVar.hJ() == 11) {
            return aVar.hP() ? bf(aVar.hQ()) : be(aVar.hQ());
        }
        return bd(aVar.hQ());
    }

    protected Object b(a aVar) {
        String strHQ = aVar.hQ();
        if (aVar.hS() == null) {
            return bc(aVar.hQ());
        }
        jx.a(bc(aVar.hQ()) == null, "Concrete field shouldn't be value object: %s", aVar.hQ());
        HashMap<String, Object> mapHM = aVar.hP() ? hM() : hL();
        if (mapHM != null) {
            return mapHM.get(strHQ);
        }
        try {
            return getClass().getMethod("get" + Character.toUpperCase(strHQ.charAt(0)) + strHQ.substring(1), new Class[0]).invoke(this, new Object[0]);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    protected abstract Object bc(String str);

    protected abstract boolean bd(String str);

    protected boolean be(String str) {
        throw new UnsupportedOperationException("Concrete types not supported");
    }

    protected boolean bf(String str) {
        throw new UnsupportedOperationException("Concrete type arrays not supported");
    }

    public abstract HashMap<String, a<?, ?>> hK();

    public HashMap<String, Object> hL() {
        return null;
    }

    public HashMap<String, Object> hM() {
        return null;
    }

    public String toString() {
        HashMap<String, a<?, ?>> mapHK = hK();
        StringBuilder sb = new StringBuilder(100);
        for (String str : mapHK.keySet()) {
            a<?, ?> aVar = mapHK.get(str);
            if (a(aVar)) {
                Object objA = a(aVar, b(aVar));
                if (sb.length() == 0) {
                    sb.append("{");
                } else {
                    sb.append(",");
                }
                sb.append("\"").append(str).append("\":");
                if (objA != null) {
                    switch (aVar.hJ()) {
                        case 8:
                            sb.append("\"").append(lb.d((byte[]) objA)).append("\"");
                            break;
                        case 9:
                            sb.append("\"").append(lb.e((byte[]) objA)).append("\"");
                            break;
                        case 10:
                            lj.a(sb, (HashMap) objA);
                            break;
                        default:
                            if (aVar.hO()) {
                                a(sb, (a) aVar, (ArrayList<Object>) objA);
                            } else {
                                a(sb, aVar, objA);
                            }
                            break;
                    }
                } else {
                    sb.append("null");
                }
            }
        }
        if (sb.length() > 0) {
            sb.append("}");
        } else {
            sb.append("{}");
        }
        return sb.toString();
    }
}
