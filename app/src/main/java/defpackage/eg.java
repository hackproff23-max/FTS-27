package defpackage;

/* JADX INFO: loaded from: classes.dex */
public interface eg {

    public static class a {
        private String a;

        private a(String str) {
            this.a = str;
        }

        /* synthetic */ a(String str, byte b) {
            this(str);
        }

        public final String toString() {
            return this.a;
        }
    }

    static {
        byte b = 0;
        new a("ABORT_END", b);
        new a("ABORT_BACK", b);
        new a("ABORT_NEW", b);
        new a("STOPPED_TOO_SOON", b);
        new a("PREEMPTED", b);
    }

    void a() throws da, db;

    void a(bj.j jVar) throws da, db;
}
