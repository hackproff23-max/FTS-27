package defpackage;

import java.util.Hashtable;

/* JADX INFO: loaded from: classes.dex */
public final class cl implements cg {
    private final cm a;
    private final Hashtable b = new Hashtable();
    private boolean c = false;

    public interface a {
        void a(String str);
    }

    public interface b {
        default b() {
        }
    }

    private cl(cm cmVar, a aVar) {
        this.a = cmVar;
        this.a.c.a(cmVar, aVar);
    }

    static cl a(cj cjVar, String str) {
        if (str == null) {
            throw new IllegalArgumentException("eventName is null.");
        }
        return new cl(new ck(cjVar, str), null);
    }

    static cl a(cm cmVar, String str) {
        if (str == null) {
            throw new IllegalArgumentException("eventName is null.");
        }
        return new cl(new ci(cmVar, str), null);
    }

    public static cl a(cm cmVar, String str, a aVar) {
        if (str == null) {
            throw new IllegalArgumentException("eventName is null.");
        }
        return new cl(new cj(cmVar, str), aVar);
    }

    static cl a(String str, String str2, String str3, int i, ch chVar, String str4) {
        if (str4 == null) {
            throw new IllegalArgumentException("eventName is null.");
        }
        return new cl(new ck(str, str2, str3, i, chVar, str4), null);
    }

    @Override // defpackage.cg
    public final ce a() {
        return a(null);
    }

    public final ce a(b bVar) {
        if (!this.c) {
            this.a.a(this.b, bVar);
            this.c = true;
        }
        return this.a;
    }

    @Override // defpackage.cg
    public final cg a(String str, int i) throws cf {
        if (this.c) {
            throw new cf("SessionEvent is alreadt committed.");
        }
        this.b.put(str, new Integer(i));
        return this;
    }

    @Override // defpackage.cg
    public final cg a(String str, String str2) throws cf {
        if (this.c) {
            throw new cf("SessionEvent is alreadt committed.");
        }
        this.b.put(str, str2);
        return this;
    }

    @Override // defpackage.cg
    public final cg a(String str, boolean z) throws cf {
        if (this.c) {
            throw new cf("SessionEvent is alreadt committed.");
        }
        this.b.put(str, new Boolean(z));
        return this;
    }

    public final ce b() {
        if (this.a instanceof cj) {
            ((cj) this.a).a = true;
        }
        if (!this.c) {
            this.a.a(this.b, null);
            this.c = true;
        }
        return this.a;
    }
}
