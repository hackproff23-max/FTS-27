package com.jirbo.adcolony;

import java.util.ArrayList;

/* JADX INFO: loaded from: classes.dex */
class ah {
    d a;
    boolean b = false;
    ArrayList<ag> c = new ArrayList<>();

    ah(d dVar) {
        this.a = dVar;
    }

    void a() {
        ADCData.c cVarC = k.c(new f("zone_state.txt"));
        if (cVarC != null) {
            this.c.clear();
            for (int i = 0; i < cVarC.i(); i++) {
                ADCData.g gVarB = cVarC.b(i);
                ag agVar = new ag();
                if (agVar.a(gVarB)) {
                    this.c.add(agVar);
                }
            }
        }
        for (String str : this.a.a.k) {
            a(str);
        }
    }

    void b() {
        l.a.b((Object) "Saving zone state...");
        this.b = false;
        ADCData.c cVar = new ADCData.c();
        for (String str : this.a.a.k) {
            cVar.a(a(str).a());
        }
        k.a(new f("zone_state.txt"), cVar);
        l.a.b((Object) "Saved zone state");
    }

    int c() {
        return this.c.size();
    }

    ag a(int i) {
        return this.c.get(i);
    }

    ag a(String str) {
        int size = this.c.size();
        for (int i = 0; i < size; i++) {
            ag agVar = this.c.get(i);
            if (agVar.a.equals(str)) {
                return agVar;
            }
        }
        this.b = true;
        ag agVar2 = new ag(str);
        this.c.add(agVar2);
        return agVar2;
    }

    void d() {
        if (this.b) {
            b();
        }
    }
}
