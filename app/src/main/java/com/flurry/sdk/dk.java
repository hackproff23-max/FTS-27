package com.flurry.sdk;

/* JADX INFO: loaded from: classes.dex */
public class dk {
    private int a;
    private int b;
    private fu<de, String> c;
    private fu<df, String> d;
    private dl e;

    private dk() {
    }

    public static dk a(dk dkVar, dk dkVar2) {
        if (dkVar == null || dkVar2 == null) {
            return null;
        }
        fu<de, String> fuVar = new fu<>();
        fu<de, String> fuVarC = dkVar.c();
        if (fuVarC != null) {
            fuVar.a(fuVarC);
        }
        fu<de, String> fuVarC2 = dkVar2.c();
        if (fuVarC2 != null) {
            fuVar.a(fuVarC2);
        }
        fu<df, String> fuVar2 = new fu<>();
        fu<df, String> fuVarD = dkVar.d();
        if (fuVarD != null) {
            fuVar2.a(fuVarD);
        }
        fu<df, String> fuVarD2 = dkVar2.d();
        if (fuVarD2 != null) {
            fuVar2.a(fuVarD2);
        }
        a aVar = new a();
        aVar.a(dkVar2.a());
        aVar.b(dkVar2.b());
        aVar.a(fuVar);
        aVar.b(fuVar2);
        aVar.a(dkVar2.e());
        return aVar.a();
    }

    public int a() {
        return this.a;
    }

    public int b() {
        return this.b;
    }

    public fu<de, String> c() {
        return this.c;
    }

    public fu<df, String> d() {
        return this.d;
    }

    public dl e() {
        return this.e;
    }

    public static class a {
        private dk a = new dk();

        public a a(int i) {
            this.a.a = i;
            return this;
        }

        public a b(int i) {
            this.a.b = i;
            return this;
        }

        public a a(fu<de, String> fuVar) {
            this.a.c = fuVar;
            return this;
        }

        public a b(fu<df, String> fuVar) {
            this.a.d = fuVar;
            return this;
        }

        public a a(dl dlVar) {
            this.a.e = dlVar;
            return this;
        }

        public dk a() {
            return this.a;
        }
    }
}
