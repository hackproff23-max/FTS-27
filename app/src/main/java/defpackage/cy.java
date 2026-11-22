package defpackage;

import com.nuance.nmsp.client.sdk.oem.f;

/* JADX INFO: loaded from: classes.dex */
public abstract class cy {
    private static final bm b = d.a(cy.class);
    protected String a;
    private String c;
    private short d;
    private bn e;
    private bi f;
    private bi g;

    protected cy(String str, short s, String str2, bi biVar, bi biVar2) {
        if (b.b()) {
            b.b("in NMSPManager() gateway IP [" + str + "] Port [" + ((int) s) + "]");
        }
        String str3 = null;
        if (str == null) {
            str3 = " gatewayIP is null";
        } else if (str.length() == 0) {
            str3 = " gatewayIP is empty";
        }
        str3 = s <= 0 ? " gatewayPort should be greater than 0" : str3;
        if (str3 != null) {
            b.e("NMSPManager " + IllegalArgumentException.class.getName() + str3);
            throw new IllegalArgumentException(str3);
        }
        this.c = str;
        this.d = s;
        this.a = str2;
        this.f = biVar;
        this.g = biVar2;
        this.e = new f();
    }

    public static String g() throws RuntimeException {
        throw new RuntimeException("Unsupported");
    }

    public final void a(bi biVar) {
        this.f = biVar;
    }

    public final String b() {
        return this.c;
    }

    public final void b(bi biVar) {
        this.g = biVar;
    }

    public final short c() {
        return this.d;
    }

    public final bn c_() {
        return this.e;
    }

    public final String d() {
        return this.a;
    }

    public final bi e() {
        return this.f;
    }

    public final bi f() {
        return this.g;
    }
}
