package defpackage;

/* JADX INFO: loaded from: classes.dex */
public final class cz {
    private String a;
    private Object b;
    private a c;

    public static class a extends bw {
        public static final a a = new a(0);
        public static final a b = new a(1);
        public static final a c = new a(2);
        public static final a d = new a(3);
        public static final a e = new a(4);
        public static final a f = new a(5);
        public static final a g = new a(6);
        public static final a h = new a(7);
        public static final a i = new a(8);
        public static final a j = new a(9);

        private a(short s) {
            super(s);
        }

        public final String toString() {
            if (a(b)) {
                return "nmsp";
            }
            if (a(c)) {
                return "app";
            }
            if (a(d)) {
                return "nss";
            }
            if (a(e)) {
                return "slog";
            }
            if (a(f)) {
                return "nsslog";
            }
            if (a(g)) {
                return "gwlog";
            }
            if (a(h)) {
                return "svsp";
            }
            if (a(i)) {
                return "sip";
            }
            if (a(j)) {
                return "sdp";
            }
            if (a(a)) {
                return "sdk";
            }
            return null;
        }
    }

    public cz(String str, a aVar) {
        this.a = str;
        this.b = new byte[0];
        this.c = aVar;
    }

    public cz(String str, Object obj, a aVar) {
        this.a = str;
        this.b = obj;
        this.c = aVar;
    }

    public final String a() {
        return this.a;
    }

    public final byte[] b() {
        return this.b instanceof byte[] ? (byte[]) this.b : "THIS IS NOT A STRING PARAMETER!!!".getBytes();
    }

    public final Object c() {
        return this.b;
    }

    public final a d() {
        return this.c;
    }

    /* JADX INFO: renamed from: e, reason: merged with bridge method [inline-methods] */
    public final cz clone() {
        Object obj = this.b;
        if (this.b instanceof byte[]) {
            byte[] bArr = (byte[]) this.b;
            byte[] bArr2 = new byte[bArr.length];
            System.arraycopy(bArr, 0, bArr2, 0, bArr.length);
            obj = bArr2;
        }
        return new cz(this.a, obj, this.c);
    }
}
