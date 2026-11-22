package defpackage;

/* JADX INFO: loaded from: classes.dex */
public class bx {
    private static final bm a = d.a(bx.class);

    public static String a(String str) {
        if (a.a()) {
            a.a("Escaping XML reserved tokens (&, <, >, \" and ') of: " + str);
        }
        int i = 0;
        StringBuffer stringBuffer = new StringBuffer(str);
        while (i < stringBuffer.length()) {
            char cCharAt = stringBuffer.charAt(i);
            if (cCharAt == '&') {
                int i2 = i + 1;
                stringBuffer.insert(i2, "amp;");
                i = i2 + 4;
            } else if (cCharAt == '<') {
                stringBuffer.deleteCharAt(i);
                stringBuffer.insert(i, "&lt;");
                i += 4;
            } else if (cCharAt == '>') {
                stringBuffer.deleteCharAt(i);
                stringBuffer.insert(i, "&gt;");
                i += 4;
            } else if (cCharAt == '\"') {
                stringBuffer.deleteCharAt(i);
                stringBuffer.insert(i, "&quot;");
                i += 6;
            } else if (cCharAt == '\'') {
                stringBuffer.deleteCharAt(i);
                stringBuffer.insert(i, "&apos;");
                i += 6;
            } else {
                i++;
            }
        }
        String string = stringBuffer.toString();
        if (a.a()) {
            a.a("Final output: " + string);
        }
        return string;
    }

    public static boolean a() {
        return false;
    }

    public static boolean a(bi biVar) {
        short sA = biVar.a();
        return sA == bi.d.a() || sA == bi.e.a() || sA == bi.f.a() || sA == bi.g.a();
    }

    public static boolean b() {
        return false;
    }

    public static boolean b(bi biVar) {
        short sA = biVar.a();
        return sA == bi.a.a() || sA == bi.c.a() || sA == bi.b.a();
    }

    public static bi c(bi biVar) {
        bi biVar2;
        if (biVar == bi.b || biVar == bi.c) {
            biVar2 = bi.a;
        } else {
            biVar2 = (biVar == bi.e || biVar == bi.f || biVar == bi.h || biVar == bi.g) ? bi.d : biVar;
        }
        if (a.c()) {
            a.c("adjustCodecForBluetooth() " + ((int) biVar.a()) + " -> " + ((int) biVar2.a()));
        }
        return biVar2;
    }
}
