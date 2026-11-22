package com.flurry.sdk;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public class cy {
    private int a;
    private List<dg> b;
    private boolean c;
    private boolean d;

    private cy() {
    }

    public static cy a(cy cyVar, cy cyVar2) {
        if (cyVar == null || cyVar2 == null) {
            return null;
        }
        List<dg> listA = cyVar.a();
        List<dg> listA2 = cyVar2.a();
        if (listA == null || listA.isEmpty() || listA2 == null || listA2.isEmpty()) {
            return null;
        }
        dg dgVar = listA.get(0);
        dg dgVar2 = listA2.get(0);
        di diVarC = dgVar.c();
        di diVarC2 = dgVar2.c();
        if (diVarC == null || diVarC2 == null) {
            return null;
        }
        if (!db.Wrapper.equals(diVarC.a()) || (!db.InLine.equals(diVarC2.a()) && !db.Wrapper.equals(diVarC2.a()))) {
            return null;
        }
        List<dj> listG = diVarC.g();
        List<dj> listG2 = diVarC2.g();
        if (listG2 == null || listG2.isEmpty()) {
            return null;
        }
        ArrayList arrayList = new ArrayList(1);
        if (listG == null || listG.isEmpty()) {
            arrayList.addAll(listG2);
        } else {
            dj djVar = listG.get(0);
            dj djVar2 = listG2.get(0);
            dk dkVarD = djVar.d();
            dk dkVarD2 = djVar2.d();
            if (dkVarD == null || dkVarD2 == null) {
                return null;
            }
            dk dkVarA = dk.a(dkVarD, dkVarD2);
            if (dkVarA == null) {
                return null;
            }
            dj.a aVar = new dj.a();
            aVar.a(djVar2.a());
            aVar.a(djVar2.b());
            aVar.a(djVar2.c());
            aVar.a(dkVarA);
            arrayList.add(aVar.a());
        }
        di.a aVar2 = new di.a();
        aVar2.a(db.Wrapper);
        aVar2.a(diVarC2.b());
        aVar2.a(diVarC.c());
        ArrayList arrayList2 = new ArrayList();
        List<String> listD = diVarC.d();
        if (listD != null) {
            arrayList2.addAll(listD);
        }
        List<String> listD2 = diVarC2.d();
        if (listD2 != null) {
            Iterator<String> it = listD2.iterator();
            while (it.hasNext()) {
                if (arrayList2.contains(it.next())) {
                    return null;
                }
            }
            arrayList2.addAll(listD2);
        }
        aVar2.a(arrayList2);
        ArrayList arrayList3 = new ArrayList();
        List<String> listE = diVarC.e();
        if (listE != null) {
            arrayList3.addAll(listE);
        }
        List<String> listE2 = diVarC2.e();
        if (listE2 != null) {
            arrayList3.addAll(listE2);
        }
        aVar2.b(arrayList3);
        ArrayList arrayList4 = new ArrayList();
        List<String> listF = diVarC.f();
        if (listF != null) {
            arrayList4.addAll(listF);
        }
        List<String> listF2 = diVarC2.f();
        if (listF2 != null) {
            arrayList4.addAll(listF2);
        }
        aVar2.c(arrayList4);
        aVar2.d(arrayList);
        di diVarA = aVar2.a();
        dg.a aVar3 = new dg.a();
        aVar3.a(dgVar.a());
        aVar3.a(dgVar.b());
        aVar3.a(diVarA);
        dg dgVarA = aVar3.a();
        ArrayList arrayList5 = new ArrayList(1);
        arrayList5.add(dgVarA);
        a aVar4 = new a();
        aVar4.a(arrayList5);
        aVar4.a(cyVar.b());
        aVar4.a(db.InLine.equals(diVarC2.a()));
        return aVar4.b();
    }

    public List<dg> a() {
        return this.b;
    }

    public int b() {
        return this.a;
    }

    public static class a {
        private cy a = new cy();

        public a a(List<dg> list) {
            this.a.b = list;
            return this;
        }

        public a a(int i) {
            this.a.a = i;
            return this;
        }

        public a a(boolean z) {
            this.a.c = z;
            return this;
        }

        public a a() {
            this.a.d = true;
            return this;
        }

        public cy b() {
            return this.a;
        }
    }

    public boolean c() {
        return this.c;
    }

    public boolean d() {
        return this.d;
    }

    public String e() {
        di diVarC;
        List<String> listD;
        List<dg> listA = a();
        if (listA == null || listA.isEmpty() || (diVarC = listA.get(0).c()) == null || (listD = diVarC.d()) == null || listD.isEmpty()) {
            return null;
        }
        return listD.get(listD.size() - 1);
    }

    public String f() {
        di diVarC;
        List<dj> listG;
        dk dkVarD;
        dl dlVarE;
        List<dg> listA = a();
        if (listA == null || listA.isEmpty() || (diVarC = listA.get(0).c()) == null || (listG = diVarC.g()) == null || listG.isEmpty() || (dkVarD = listG.get(0).d()) == null || (dlVarE = dkVarD.e()) == null || dlVarE.a() == null) {
            return null;
        }
        return dlVarE.a();
    }

    public List<String> g() {
        di diVarC;
        List<dg> listA = a();
        if (listA == null || listA.isEmpty() || (diVarC = listA.get(0).c()) == null) {
            return null;
        }
        return diVarC.f();
    }

    public List<String> h() {
        di diVarC;
        List<dg> listA = a();
        if (listA == null || listA.isEmpty() || (diVarC = listA.get(0).c()) == null) {
            return null;
        }
        return diVarC.e();
    }

    public List<String> a(df dfVar) {
        di diVarC;
        List<dj> listG;
        dk dkVarD;
        fu<df, String> fuVarD;
        ArrayList arrayList = new ArrayList();
        List<dg> listA = a();
        if (listA != null && !listA.isEmpty() && (diVarC = listA.get(0).c()) != null && (listG = diVarC.g()) != null && !listG.isEmpty() && (dkVarD = listG.get(0).d()) != null && (fuVarD = dkVarD.d()) != null) {
            arrayList.addAll(fuVarD.a(dfVar));
        }
        return arrayList;
    }

    public String i() {
        List<String> listA = a(df.ClickThrough);
        if (listA == null || listA.size() <= 0) {
            return null;
        }
        return listA.get(0);
    }

    public List<String> a(de deVar) {
        di diVarC;
        List<dj> listG;
        dk dkVarD;
        fu<de, String> fuVarC;
        List<dg> listA = a();
        if (listA == null || listA.isEmpty() || (diVarC = listA.get(0).c()) == null || (listG = diVarC.g()) == null || listG.isEmpty() || (dkVarD = listG.get(0).d()) == null || (fuVarC = dkVarD.c()) == null) {
            return null;
        }
        return fuVarC.a(deVar);
    }
}
