package defpackage;

import java.util.Hashtable;

/* JADX INFO: loaded from: classes.dex */
public class cm implements ce {
    private cm a;
    ck b;
    ch c;
    int d;
    private String e;
    private Long f;
    private Hashtable g;

    cm(String str, cm cmVar, ck ckVar, ch chVar) {
        this.e = str;
        this.a = cmVar;
        this.b = ckVar;
        this.c = chVar;
    }

    protected static boolean a(cw cwVar, String str, Object obj) {
        String str2;
        cq cqVar = new cq();
        if (obj instanceof byte[]) {
            str2 = "bin";
        } else if (obj instanceof Boolean) {
            str2 = "bool";
        } else if (obj instanceof Double) {
            str2 = "float";
        } else if (obj instanceof Integer) {
            str2 = "int";
        } else if (obj instanceof Long) {
            str2 = "long";
        } else {
            str2 = ((obj instanceof String) || (obj instanceof ce)) ? "str" : "unknown";
        }
        if (str2.compareTo("unknown") == 0) {
            return false;
        }
        cqVar.a("t", str2);
        cqVar.a("k", str);
        if (obj instanceof byte[]) {
            cqVar.a("v", (byte[]) obj);
        } else if (obj instanceof Boolean) {
            cqVar.a("v", ((Boolean) obj).toString());
        } else if (obj instanceof Double) {
            cqVar.a("v", ((Double) obj).toString());
        } else if (obj instanceof Integer) {
            cqVar.b("v", ((Integer) obj).intValue());
        } else if (obj instanceof Long) {
            cqVar.a("v", ((Long) obj).toString());
        } else if (obj instanceof String) {
            cqVar.b("v", (String) obj);
        } else {
            if (!(obj instanceof ce)) {
                return false;
            }
            cqVar.a("v", ((cm) obj).b.b() + "." + ((cm) obj).d);
        }
        cwVar.a(cqVar);
        return true;
    }

    @Override // defpackage.ce
    public final cg a(String str) {
        return cl.a(this, str);
    }

    protected cw a() {
        ct ctVar = new ct();
        a(ctVar, "RootParentId", this.b.b());
        if (this.a != null) {
            a(ctVar, "ParentSeqId", new Integer(this.a.d));
        }
        a(ctVar, "SeqId", new Integer(this.d));
        a(ctVar, "Name", this.e);
        a(ctVar, "Timestamp", this.f);
        return ctVar;
    }

    public final void a(Hashtable hashtable, cl.b bVar) {
        this.g = hashtable;
        this.f = Long.valueOf(System.currentTimeMillis());
        this.c.a(this, bVar);
    }

    protected void c() {
        this.d = this.b.d();
    }

    public final cd e() {
        return this.c;
    }

    public final ce f() {
        return this.a;
    }

    public final byte[] g() {
        cq cqVar = new cq();
        cw cwVarA = a();
        ct ctVar = new ct();
        for (String str : this.g.keySet()) {
            a(ctVar, str, this.g.get(str));
        }
        cqVar.a("meta", cwVarA);
        if (ctVar.a() > 0) {
            cqVar.a("attr", ctVar);
        }
        return cqVar.d();
    }
}
