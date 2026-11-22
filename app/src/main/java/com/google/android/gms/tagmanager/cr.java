package com.google.android.gms.tagmanager;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/* JADX INFO: loaded from: classes.dex */
class cr {

    public static class a {
        private final Map<String, com.google.android.gms.internal.d.a> asM;
        private final com.google.android.gms.internal.d.a asN;

        private a(Map<String, com.google.android.gms.internal.d.a> map, com.google.android.gms.internal.d.a aVar) {
            this.asM = map;
            this.asN = aVar;
        }

        public static b qk() {
            return new b();
        }

        public void a(String str, com.google.android.gms.internal.d.a aVar) {
            this.asM.put(str, aVar);
        }

        public Map<String, com.google.android.gms.internal.d.a> ql() {
            return Collections.unmodifiableMap(this.asM);
        }

        public com.google.android.gms.internal.d.a qm() {
            return this.asN;
        }

        public String toString() {
            return "Properties: " + ql() + " pushAfterEvaluate: " + this.asN;
        }
    }

    public static class b {
        private final Map<String, com.google.android.gms.internal.d.a> asM;
        private com.google.android.gms.internal.d.a asN;

        private b() {
            this.asM = new HashMap();
        }

        public b b(String str, com.google.android.gms.internal.d.a aVar) {
            this.asM.put(str, aVar);
            return this;
        }

        public b i(com.google.android.gms.internal.d.a aVar) {
            this.asN = aVar;
            return this;
        }

        public a qn() {
            return new a(this.asM, this.asN);
        }
    }

    public static class c {
        private final String TU;
        private final List<e> asO;
        private final Map<String, List<a>> asP;
        private final int asQ;

        private c(List<e> list, Map<String, List<a>> map, String str, int i) {
            this.asO = Collections.unmodifiableList(list);
            this.asP = Collections.unmodifiableMap(map);
            this.TU = str;
            this.asQ = i;
        }

        public static d qo() {
            return new d();
        }

        public String getVersion() {
            return this.TU;
        }

        public List<e> qp() {
            return this.asO;
        }

        public Map<String, List<a>> qq() {
            return this.asP;
        }

        public String toString() {
            return "Rules: " + qp() + "  Macros: " + this.asP;
        }
    }

    public static class d {
        private String TU;
        private final List<e> asO;
        private final Map<String, List<a>> asP;
        private int asQ;

        private d() {
            this.asO = new ArrayList();
            this.asP = new HashMap();
            this.TU = "";
            this.asQ = 0;
        }

        public d a(a aVar) {
            String strJ = di.j(aVar.ql().get(com.google.android.gms.internal.b.INSTANCE_NAME.toString()));
            List<a> arrayList = this.asP.get(strJ);
            if (arrayList == null) {
                arrayList = new ArrayList<>();
                this.asP.put(strJ, arrayList);
            }
            arrayList.add(aVar);
            return this;
        }

        public d a(e eVar) {
            this.asO.add(eVar);
            return this;
        }

        public d cO(String str) {
            this.TU = str;
            return this;
        }

        public d fO(int i) {
            this.asQ = i;
            return this;
        }

        public c qr() {
            return new c(this.asO, this.asP, this.TU, this.asQ);
        }
    }

    public static class e {
        private final List<a> asR;
        private final List<a> asS;
        private final List<a> asT;
        private final List<a> asU;
        private final List<a> asV;
        private final List<a> asW;
        private final List<String> asX;
        private final List<String> asY;
        private final List<String> asZ;
        private final List<String> ata;

        private e(List<a> list, List<a> list2, List<a> list3, List<a> list4, List<a> list5, List<a> list6, List<String> list7, List<String> list8, List<String> list9, List<String> list10) {
            this.asR = Collections.unmodifiableList(list);
            this.asS = Collections.unmodifiableList(list2);
            this.asT = Collections.unmodifiableList(list3);
            this.asU = Collections.unmodifiableList(list4);
            this.asV = Collections.unmodifiableList(list5);
            this.asW = Collections.unmodifiableList(list6);
            this.asX = Collections.unmodifiableList(list7);
            this.asY = Collections.unmodifiableList(list8);
            this.asZ = Collections.unmodifiableList(list9);
            this.ata = Collections.unmodifiableList(list10);
        }

        public static f qs() {
            return new f();
        }

        public List<String> qA() {
            return this.asZ;
        }

        public List<String> qB() {
            return this.ata;
        }

        public List<a> qC() {
            return this.asW;
        }

        public List<a> qt() {
            return this.asR;
        }

        public List<a> qu() {
            return this.asS;
        }

        public List<a> qv() {
            return this.asT;
        }

        public List<a> qw() {
            return this.asU;
        }

        public List<a> qx() {
            return this.asV;
        }

        public List<String> qy() {
            return this.asX;
        }

        public List<String> qz() {
            return this.asY;
        }

        public String toString() {
            return "Positive predicates: " + qt() + "  Negative predicates: " + qu() + "  Add tags: " + qv() + "  Remove tags: " + qw() + "  Add macros: " + qx() + "  Remove macros: " + qC();
        }
    }

    public static class f {
        private final List<a> asR;
        private final List<a> asS;
        private final List<a> asT;
        private final List<a> asU;
        private final List<a> asV;
        private final List<a> asW;
        private final List<String> asX;
        private final List<String> asY;
        private final List<String> asZ;
        private final List<String> ata;

        private f() {
            this.asR = new ArrayList();
            this.asS = new ArrayList();
            this.asT = new ArrayList();
            this.asU = new ArrayList();
            this.asV = new ArrayList();
            this.asW = new ArrayList();
            this.asX = new ArrayList();
            this.asY = new ArrayList();
            this.asZ = new ArrayList();
            this.ata = new ArrayList();
        }

        public f b(a aVar) {
            this.asR.add(aVar);
            return this;
        }

        public f c(a aVar) {
            this.asS.add(aVar);
            return this;
        }

        public f cP(String str) {
            this.asZ.add(str);
            return this;
        }

        public f cQ(String str) {
            this.ata.add(str);
            return this;
        }

        public f cR(String str) {
            this.asX.add(str);
            return this;
        }

        public f cS(String str) {
            this.asY.add(str);
            return this;
        }

        public f d(a aVar) {
            this.asT.add(aVar);
            return this;
        }

        public f e(a aVar) {
            this.asU.add(aVar);
            return this;
        }

        public f f(a aVar) {
            this.asV.add(aVar);
            return this;
        }

        public f g(a aVar) {
            this.asW.add(aVar);
            return this;
        }

        public e qD() {
            return new e(this.asR, this.asS, this.asT, this.asU, this.asV, this.asW, this.asX, this.asY, this.asZ, this.ata);
        }
    }

    public static class g extends Exception {
        public g(String str) {
            super(str);
        }
    }

    private static com.google.android.gms.internal.d.a a(int i, com.google.android.gms.internal.c.f fVar, com.google.android.gms.internal.d.a[] aVarArr, Set<Integer> set) throws g {
        int i2 = 0;
        if (set.contains(Integer.valueOf(i))) {
            cN("Value cycle detected.  Current value reference: " + i + ".  Previous value references: " + set + ".");
        }
        com.google.android.gms.internal.d.a aVar = (com.google.android.gms.internal.d.a) a(fVar.fG, i, "values");
        if (aVarArr[i] != null) {
            return aVarArr[i];
        }
        com.google.android.gms.internal.d.a aVarG = null;
        set.add(Integer.valueOf(i));
        switch (aVar.type) {
            case 1:
            case 5:
            case 6:
            case 8:
                aVarG = aVar;
                break;
            case 2:
                com.google.android.gms.internal.c.h hVarH = h(aVar);
                aVarG = g(aVar);
                aVarG.gw = new com.google.android.gms.internal.d.a[hVarH.gh.length];
                int[] iArr = hVarH.gh;
                int length = iArr.length;
                int i3 = 0;
                while (i2 < length) {
                    aVarG.gw[i3] = a(iArr[i2], fVar, aVarArr, set);
                    i2++;
                    i3++;
                }
                break;
            case 3:
                aVarG = g(aVar);
                com.google.android.gms.internal.c.h hVarH2 = h(aVar);
                if (hVarH2.gi.length != hVarH2.gj.length) {
                    cN("Uneven map keys (" + hVarH2.gi.length + ") and map values (" + hVarH2.gj.length + ")");
                }
                aVarG.gx = new com.google.android.gms.internal.d.a[hVarH2.gi.length];
                aVarG.gy = new com.google.android.gms.internal.d.a[hVarH2.gi.length];
                int[] iArr2 = hVarH2.gi;
                int length2 = iArr2.length;
                int i4 = 0;
                int i5 = 0;
                while (i4 < length2) {
                    aVarG.gx[i5] = a(iArr2[i4], fVar, aVarArr, set);
                    i4++;
                    i5++;
                }
                int[] iArr3 = hVarH2.gj;
                int length3 = iArr3.length;
                int i6 = 0;
                while (i2 < length3) {
                    aVarG.gy[i6] = a(iArr3[i2], fVar, aVarArr, set);
                    i2++;
                    i6++;
                }
                break;
            case 4:
                aVarG = g(aVar);
                aVarG.gz = di.j(a(h(aVar).gm, fVar, aVarArr, set));
                break;
            case 7:
                aVarG = g(aVar);
                com.google.android.gms.internal.c.h hVarH3 = h(aVar);
                aVarG.gD = new com.google.android.gms.internal.d.a[hVarH3.gl.length];
                int[] iArr4 = hVarH3.gl;
                int length4 = iArr4.length;
                int i7 = 0;
                while (i2 < length4) {
                    aVarG.gD[i7] = a(iArr4[i2], fVar, aVarArr, set);
                    i2++;
                    i7++;
                }
                break;
        }
        if (aVarG == null) {
            cN("Invalid value: " + aVar);
        }
        aVarArr[i] = aVarG;
        set.remove(Integer.valueOf(i));
        return aVarG;
    }

    private static a a(com.google.android.gms.internal.c.b bVar, com.google.android.gms.internal.c.f fVar, com.google.android.gms.internal.d.a[] aVarArr, int i) throws g {
        b bVarQk = a.qk();
        for (int i2 : bVar.fq) {
            com.google.android.gms.internal.c.e eVar = (com.google.android.gms.internal.c.e) a(fVar.fH, Integer.valueOf(i2).intValue(), "properties");
            String str = (String) a(fVar.fF, eVar.key, "keys");
            com.google.android.gms.internal.d.a aVar = (com.google.android.gms.internal.d.a) a(aVarArr, eVar.value, "values");
            if (com.google.android.gms.internal.b.PUSH_AFTER_EVALUATE.toString().equals(str)) {
                bVarQk.i(aVar);
            } else {
                bVarQk.b(str, aVar);
            }
        }
        return bVarQk.qn();
    }

    private static e a(com.google.android.gms.internal.c.g gVar, List<a> list, List<a> list2, List<a> list3, com.google.android.gms.internal.c.f fVar) {
        f fVarQs = e.qs();
        for (int i : gVar.fV) {
            fVarQs.b(list3.get(Integer.valueOf(i).intValue()));
        }
        for (int i2 : gVar.fW) {
            fVarQs.c(list3.get(Integer.valueOf(i2).intValue()));
        }
        for (int i3 : gVar.fX) {
            fVarQs.d(list.get(Integer.valueOf(i3).intValue()));
        }
        for (int i4 : gVar.fZ) {
            fVarQs.cP(fVar.fG[Integer.valueOf(i4).intValue()].gv);
        }
        for (int i5 : gVar.fY) {
            fVarQs.e(list.get(Integer.valueOf(i5).intValue()));
        }
        for (int i6 : gVar.ga) {
            fVarQs.cQ(fVar.fG[Integer.valueOf(i6).intValue()].gv);
        }
        for (int i7 : gVar.gb) {
            fVarQs.f(list2.get(Integer.valueOf(i7).intValue()));
        }
        for (int i8 : gVar.gd) {
            fVarQs.cR(fVar.fG[Integer.valueOf(i8).intValue()].gv);
        }
        for (int i9 : gVar.gc) {
            fVarQs.g(list2.get(Integer.valueOf(i9).intValue()));
        }
        for (int i10 : gVar.ge) {
            fVarQs.cS(fVar.fG[Integer.valueOf(i10).intValue()].gv);
        }
        return fVarQs.qD();
    }

    private static <T> T a(T[] tArr, int i, String str) throws g {
        if (i < 0 || i >= tArr.length) {
            cN("Index out of bounds detected: " + i + " in " + str);
        }
        return tArr[i];
    }

    public static c b(com.google.android.gms.internal.c.f fVar) throws g {
        com.google.android.gms.internal.d.a[] aVarArr = new com.google.android.gms.internal.d.a[fVar.fG.length];
        for (int i = 0; i < fVar.fG.length; i++) {
            a(i, fVar, aVarArr, new HashSet(0));
        }
        d dVarQo = c.qo();
        ArrayList arrayList = new ArrayList();
        for (int i2 = 0; i2 < fVar.fJ.length; i2++) {
            arrayList.add(a(fVar.fJ[i2], fVar, aVarArr, i2));
        }
        ArrayList arrayList2 = new ArrayList();
        for (int i3 = 0; i3 < fVar.fK.length; i3++) {
            arrayList2.add(a(fVar.fK[i3], fVar, aVarArr, i3));
        }
        ArrayList arrayList3 = new ArrayList();
        for (int i4 = 0; i4 < fVar.fI.length; i4++) {
            a aVarA = a(fVar.fI[i4], fVar, aVarArr, i4);
            dVarQo.a(aVarA);
            arrayList3.add(aVarA);
        }
        for (com.google.android.gms.internal.c.g gVar : fVar.fL) {
            dVarQo.a(a(gVar, arrayList, arrayList3, arrayList2, fVar));
        }
        dVarQo.cO(fVar.version);
        dVarQo.fO(fVar.fT);
        return dVarQo.qr();
    }

    public static void b(InputStream inputStream, OutputStream outputStream) throws IOException {
        byte[] bArr = new byte[1024];
        while (true) {
            int i = inputStream.read(bArr);
            if (i == -1) {
                return;
            } else {
                outputStream.write(bArr, 0, i);
            }
        }
    }

    private static void cN(String str) throws g {
        bh.T(str);
        throw new g(str);
    }

    public static com.google.android.gms.internal.d.a g(com.google.android.gms.internal.d.a aVar) {
        com.google.android.gms.internal.d.a aVar2 = new com.google.android.gms.internal.d.a();
        aVar2.type = aVar.type;
        aVar2.gE = (int[]) aVar.gE.clone();
        if (aVar.gF) {
            aVar2.gF = aVar.gF;
        }
        return aVar2;
    }

    private static com.google.android.gms.internal.c.h h(com.google.android.gms.internal.d.a aVar) throws g {
        if (((com.google.android.gms.internal.c.h) aVar.a(com.google.android.gms.internal.c.h.gf)) == null) {
            cN("Expected a ServingValue and didn't get one. Value is: " + aVar);
        }
        return (com.google.android.gms.internal.c.h) aVar.a(com.google.android.gms.internal.c.h.gf);
    }
}
