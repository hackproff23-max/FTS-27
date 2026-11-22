package defpackage;

import java.util.Vector;

/* JADX INFO: loaded from: classes.dex */
public class ei {
    private static final bm a = d.a(ei.class);

    private ei() {
    }

    public static eh a(df dfVar, dk dkVar, Vector vector) {
        if (a.b()) {
            a.b("createNMASResource");
        }
        if (dfVar == null) {
            a.e("manager is null");
            throw new NullPointerException("manager can not be null!");
        }
        if (dkVar == null) {
            a.e("nmasListener is null");
            throw new NullPointerException("nmasListener can not be null!");
        }
        if (vector != null) {
            int i = 0;
            while (true) {
                int i2 = i;
                if (i2 >= vector.size()) {
                    break;
                }
                cz czVar = (cz) vector.elementAt(i2);
                if (czVar.d() == cz.a.c || czVar.d() == cz.a.d || czVar.d() == cz.a.e || czVar.d() == cz.a.f || czVar.d() == cz.a.g || czVar.d() == cz.a.h || czVar.d() == cz.a.i || czVar.d() == cz.a.j) {
                    a.e("IllegalArgumentException Parameter type: " + czVar.d() + " not allowed. ");
                    throw new IllegalArgumentException("Parameter type: " + czVar.d() + " not allowed. ");
                }
                i = i2 + 1;
            }
        }
        return new dp((dl) dfVar, dkVar, vector);
    }
}
