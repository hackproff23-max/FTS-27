package defpackage;

/* JADX INFO: loaded from: classes.dex */
public final class fh implements fd {
    private String a;
    private long b;
    private long c;
    private double d;
    private boolean e;
    private boolean f;
    private boolean g;
    private boolean h;

    private fh(String str, long j, long j2, double d, boolean z) {
        this.e = true;
        this.f = false;
        this.g = false;
        this.h = false;
        this.a = str;
        this.b = j;
        this.c = j2;
        this.d = d;
        this.e = z;
        if (this.a.indexOf("\\*no-space-before") != -1) {
            this.g = true;
            int iIndexOf = this.a.indexOf("\\*no-space-before");
            if (iIndexOf + 17 == this.a.length()) {
                this.a = this.a.substring(0, iIndexOf);
            } else {
                this.a = this.a.substring(0, iIndexOf) + this.a.substring(iIndexOf + 17);
            }
        }
        if (this.a.indexOf("\\*no-space-after") != -1) {
            this.h = true;
            int iIndexOf2 = this.a.indexOf("\\*no-space-after");
            if (iIndexOf2 + 16 == this.a.length()) {
                this.a = this.a.substring(0, iIndexOf2);
            } else {
                this.a = this.a.substring(0, iIndexOf2) + this.a.substring(iIndexOf2 + 16);
            }
        }
        if (this.a.length() == 0 || fr.a(this.a).length() != 0) {
            return;
        }
        this.f = true;
    }

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    public fh(String str, long j, long j2, double d, boolean z, byte b) {
        this(str, j, j2, d, z);
        fd.a aVar = fd.a.a;
    }

    @Override // defpackage.fd
    public final double a() {
        return this.d;
    }

    public final void a(String str) {
        this.a = str;
    }

    public final String b() {
        return this.a;
    }

    public final long c() {
        return this.b;
    }

    public final long d() {
        return this.c;
    }

    public final boolean e() {
        return this.e;
    }

    public final boolean f() {
        return this.f;
    }

    public final boolean g() {
        return this.g;
    }

    public final boolean h() {
        return this.h;
    }

    public final String toString() {
        return this.a;
    }
}
