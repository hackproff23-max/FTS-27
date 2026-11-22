package defpackage;

import java.util.Vector;

/* JADX INFO: loaded from: classes.dex */
public class dg {
    private static final bm a = d.a(dg.class);

    private dg() {
    }

    public static df a(String str, short s, String str2, byte[] bArr, String str3, bi biVar, bi biVar2, String str4, Vector vector, dh dhVar) {
        if (a.b()) {
            a.b("createManager");
        }
        if (str == null || str.length() == 0) {
            a.e("NullPointerException gatewayIP is NULL. ");
            throw new NullPointerException("gatewayIP must be provided!");
        }
        if (s <= 0) {
            a.e("IllegalArgumentException gatewayPort is invalid. ");
            throw new IllegalArgumentException("gatewayPort invalid value!");
        }
        if (str2 == null) {
            a.e("NullPointerException applicationId is NULL. ");
            throw new NullPointerException("Application id can not be null!");
        }
        if (bArr == null) {
            a.e("NullPointerException appKey is NULL. ");
            throw new NullPointerException("Application key can not be null!");
        }
        if (str3 == null) {
            a.e("NullPointerException uid is NULL. ");
            throw new NullPointerException("uid can not be null!");
        }
        if (biVar == null) {
            a.e("NullPointerException inputCodec is NULL. ");
            throw new NullPointerException("inputCodec can not be null!");
        }
        if (biVar2 == null) {
            a.e("NullPointerException outputCodec is NULL. ");
            throw new NullPointerException("outputCodec can not be null!");
        }
        if (dhVar == null) {
            a.e("NullPointerException managerListener is NULL. ");
            throw new NullPointerException("managerListener can not be null!");
        }
        if (vector != null) {
            int i = 0;
            while (true) {
                int i2 = i;
                if (i2 >= vector.size()) {
                    break;
                }
                cz czVar = (cz) vector.elementAt(i2);
                if (czVar.d() == cz.a.e || czVar.d() == cz.a.f || czVar.d() == cz.a.g || czVar.d() == cz.a.h || czVar.d() == cz.a.d || czVar.d() == cz.a.i || czVar.d() == cz.a.j) {
                    a.e("IllegalArgumentException Parameter type: " + czVar.d() + " not allowed. ");
                    throw new IllegalArgumentException("Parameter type: " + czVar.d() + " not allowed. ");
                }
                i = i2 + 1;
            }
        }
        return new dl(str, s, str2, bArr, str3, biVar, biVar2, str4, vector, dhVar);
    }
}
