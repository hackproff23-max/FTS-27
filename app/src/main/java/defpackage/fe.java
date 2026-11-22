package defpackage;

import java.util.Vector;

/* JADX INFO: loaded from: classes.dex */
public class fe implements ey {
    private static final bm a = d.a(fe.class);
    private Vector b;

    public fe() {
        if (a.b()) {
            a.b("AlternativeImpl()");
        }
        this.b = new Vector();
    }

    public fe(Vector vector) {
        if (a.b()) {
            a.b("AlternativeImpl(Vector)");
        }
        this.b = vector;
    }

    private fh b(int i) {
        return (fh) this.b.elementAt(i);
    }

    public static void b() {
    }

    @Override // defpackage.ey
    public final int a() {
        int i = 0;
        int size = this.b.size();
        if (size != 0) {
            for (int i2 = 0; i2 < size; i2++) {
                if (!b(i2).f()) {
                    i++;
                }
            }
        }
        return i;
    }

    @Override // defpackage.ey
    public final fd a(int i) {
        int i2 = -1;
        int i3 = -1;
        while (i2 != i) {
            i3++;
            if (i3 > this.b.size()) {
                return null;
            }
            if (!b(i3).f()) {
                i2++;
            }
        }
        fh fhVarB = b(i3);
        if (i3 + 1 < this.b.size() && b(i3 + 1).f()) {
            b(i3 + 1);
        }
        if (i3 == 0 || !b(i3 - 1).f()) {
            return fhVarB;
        }
        b(i3 - 1);
        return fhVarB;
    }

    public String toString() {
        if (this.b.size() == 0) {
            return "";
        }
        StringBuffer stringBuffer = new StringBuffer("");
        for (int i = 0; i < this.b.size(); i++) {
            stringBuffer.append(this.b.elementAt(i));
        }
        return stringBuffer.toString();
    }
}
