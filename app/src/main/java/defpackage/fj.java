package defpackage;

import java.io.UnsupportedEncodingException;
import java.util.Hashtable;
import java.util.Vector;

/* JADX INFO: loaded from: classes.dex */
public class fj implements fi {
    private static final bm a = d.a(fj.class);
    private byte[] b;
    private String c;
    private AnonymousClass1 d;

    /* JADX INFO: renamed from: fj$1, reason: invalid class name */
    static /* synthetic */ class AnonymousClass1 {
        public int a;
        public int b;
        public int c;
        public int d;
        public int e;

        private AnonymousClass1() {
            this.a = -1;
            this.b = -1;
            this.c = -1;
            this.d = -1;
            this.e = -1;
        }

        /* synthetic */ AnonymousClass1(byte b) {
            this();
        }
    }

    class a {
        public fg a;
        public int b;
        public int c;

        private a() {
            this.a = new fg();
            new Vector();
            this.b = 0;
            this.c = 0;
        }

        /* synthetic */ a(byte b) {
            this();
        }
    }

    class b {
        public int a;
        public int b;
        public fh c;

        private b() {
            this.a = 0;
            this.b = 0;
            this.c = null;
        }

        /* synthetic */ b(byte b) {
            this();
        }
    }

    public fj(byte[] bArr) {
        String strA;
        this.c = "Cp1252";
        this.d = null;
        this.b = bArr;
        AnonymousClass1 anonymousClass1 = new AnonymousClass1((byte) 0);
        int iA = a(2, b());
        anonymousClass1.e = iA;
        int iB = b(iA + 4, a(iA));
        anonymousClass1.d = iB;
        int iA2 = a(iB + 2, e(iB));
        anonymousClass1.b = a(3, iA2, bArr.length);
        anonymousClass1.c = a(4, iA2, bArr.length);
        anonymousClass1.a = a(1, iA2, bArr.length);
        this.d = anonymousClass1;
        AnonymousClass1 anonymousClass2 = this.d;
        if (anonymousClass2.c == -1) {
            strA = "Cp1252";
        } else {
            int i = anonymousClass2.c + 4;
            strA = fr.a(this.b, i + 4, ((int) fr.c(this.b, i)) - 1, "Cp1252");
            if (!a(strA == "Windows-1252" ? "Cp1252" : strA)) {
                strA = "Cp1252";
            }
        }
        this.c = strA;
    }

    private int a(int i) {
        int iB = fr.b(this.b, i);
        if (a.a()) {
            a.a("Number of sentences: " + iB);
        }
        return iB;
    }

    private int a(int i, int i2) {
        int i3 = 0;
        while (i < this.b.length && i3 < i2) {
            if (this.b[i] == 0) {
                i3++;
            }
            i++;
        }
        return i;
    }

    private int a(int i, int i2, int i3) {
        int i4 = i2;
        while (i4 < i3) {
            long jC = fr.c(this.b, i4);
            if (a.a()) {
                a.a("Received extension id=" + jC);
            }
            if (jC == i) {
                return i4;
            }
            int i5 = i4 + 4;
            long jC2 = fr.c(this.b, i5);
            if (a.a()) {
                a.a("Received payload size: " + jC2);
            }
            i4 = i5 + ((int) jC2) + 4;
        }
        return -1;
    }

    /* JADX WARN: Multi-variable type inference failed */
    private a a(int i, int i2, Vector vector) {
        int iB = b(i);
        int i3 = i + 4;
        fg fgVar = new fg();
        a aVar = new a(0 == true ? 1 : 0);
        for (int i4 = 0; i4 < iB; i4++) {
            b bVarA = a(i3, i2, 2, vector);
            fgVar.a(bVarA.c);
            i3 = bVarA.a;
            i2 = bVarA.b;
            if (a.a()) {
                fh fhVar = bVarA.c;
                a.a("Extracted word: startTime [" + fhVar.c() + "] endTime [" + fhVar.d() + "] content [" + fhVar.b() + "]");
            }
        }
        aVar.b = i3;
        aVar.c = i2;
        aVar.a = fgVar;
        return aVar;
    }

    private b a(int i, int i2, int i3, Vector vector) {
        long jC;
        long jC2;
        int i4;
        b bVar = new b((byte) 0);
        int i5 = i + 2;
        String str = (String) vector.elementAt(fr.a(this.b, i) - 1);
        if (i3 == 4) {
            jC = d(i5);
            int i6 = i5 + i3;
            jC2 = d(i6);
            i4 = i6 + i3;
        } else {
            jC = c(i5);
            int i7 = i5 + i3;
            jC2 = c(i7);
            i4 = i7 + i3;
        }
        double dA = 0.0d;
        if (i2 != -1) {
            dA = fr.a(this.b, i2);
            i2 += 2;
        }
        bVar.c = new fh(str, jC, jC2, dA, true, (byte) 0);
        bVar.a = i4;
        bVar.b = i2;
        return bVar;
    }

    private Vector a(AnonymousClass1 anonymousClass1, Vector vector) {
        int i = 0;
        boolean z = anonymousClass1.a != -1;
        int i2 = z ? anonymousClass1.a : anonymousClass1.e;
        boolean z2 = anonymousClass1.b != -1;
        if (z) {
            i2 = i2 + 4 + 4;
        }
        int iA = a(i2);
        int i3 = i2 + 4;
        int i4 = anonymousClass1.b;
        if (z2) {
            i4 = i4 + 4 + 4 + 4 + 4;
        }
        Vector vector2 = new Vector();
        int i5 = i4;
        int i6 = i3;
        while (i < iA) {
            a aVarB = z ? b(i6, i5, vector) : a(i6, i5, vector);
            if (a.a()) {
                a.a("Number of words in Sentence " + i + ": [" + aVarB.a.a() + "]");
            }
            vector2.addElement(aVarB.a);
            int i7 = aVarB.b;
            int i8 = aVarB.c;
            if (a.a()) {
                a.a("Extracted sentence: [" + aVarB.a + "]");
            }
            i++;
            i6 = i7;
            i5 = i8;
        }
        return vector2;
    }

    private static void a(Hashtable hashtable) {
        for (int i = 0; hashtable.remove("CFD" + i) != null; i++) {
        }
        if (hashtable.containsKey("IAL")) {
            hashtable.put("InputAudioLength", hashtable.get("IAL"));
        }
    }

    private static void a(Hashtable hashtable, Vector vector) {
        int i = 0;
        while (true) {
            int i2 = i;
            if (i2 >= vector.size()) {
                return;
            }
            String str = (String) hashtable.get("CFD" + i2);
            if (str != null) {
                try {
                    ((fg) vector.elementAt(i2)).a(Double.parseDouble(str));
                } catch (NumberFormatException e) {
                }
            }
            i = i2 + 1;
        }
    }

    private static boolean a(String str) {
        try {
            new byte[1][0] = 20;
            return true;
        } catch (UnsupportedEncodingException e) {
            if (a.d()) {
                a.d(str + " character encoding is not available in your VM. Using the default one.");
            }
            return false;
        }
    }

    private int b() {
        int iA = fr.a(this.b, 0);
        if (a.a()) {
            a.a("Number of words: " + iA);
        }
        return iA;
    }

    private int b(int i) {
        return fr.b(this.b, i);
    }

    private int b(int i, int i2) {
        int i3 = 0;
        while (i3 < i2) {
            int i4 = i + 4;
            for (int i5 = 0; i5 < b(i); i5++) {
                i4 += 6;
            }
            i3++;
            i = i4;
        }
        return i;
    }

    /* JADX WARN: Multi-variable type inference failed */
    private a b(int i, int i2, Vector vector) {
        int iB = b(i);
        int i3 = i + 4;
        a aVar = new a(0 == true ? 1 : 0);
        for (int i4 = 0; i4 < iB; i4++) {
            b bVarA = a(i3, i2, 4, vector);
            aVar.a.a(bVarA.c);
            i3 = bVarA.a;
            i2 = bVarA.b;
            if (a.a()) {
                fh fhVar = bVarA.c;
                a.a("Extracted word: startTime [" + fhVar.c() + "] endTime [" + fhVar.d() + "] content [" + fhVar.b() + "]");
            }
        }
        aVar.b = i3;
        aVar.c = i2;
        return aVar;
    }

    private int c(int i) {
        return fr.a(this.b, i);
    }

    private Vector c(int i, int i2) {
        Vector vector = new Vector();
        for (int i3 = 0; i3 < i; i3++) {
            int iD = fr.d(this.b, i2);
            String strA = fr.a(this.b, i2, iD, this.c.equals("Cp1252") ? "Windows-1252" : this.c);
            vector.addElement(strA);
            if (a.a()) {
                a.a("Added a word to the list: [" + strA + "] offset [" + i2 + "] len [" + iD + "]");
            }
            i2 += iD + 1;
        }
        return vector;
    }

    private long d(int i) {
        return fr.c(this.b, i);
    }

    private int e(int i) {
        int iA = fr.a(this.b, i);
        if (a.a()) {
            a.a("Number of key-value pairs: " + iA);
        }
        return iA;
    }

    private Hashtable f(int i) {
        Hashtable hashtable = new Hashtable();
        Vector vectorC = c(e(i), i + 2);
        if (a.a()) {
            a.a("Extracted " + vectorC.size() + " words from the set of key-value pairs.");
        }
        for (int i2 = 0; i2 < vectorC.size(); i2++) {
            String str = (String) vectorC.elementAt(i2);
            int iIndexOf = str.indexOf(61);
            if (iIndexOf == -1) {
                throw new IllegalArgumentException("Received an invalid key-value pair: " + ((String) vectorC.elementAt(i2)));
            }
            hashtable.put(str.substring(0, iIndexOf), str.substring(iIndexOf + 1, str.length()));
        }
        return hashtable;
    }

    @Override // defpackage.fi
    public final fa a() {
        a.b("Unpacking DNS binary version 3.2 results.");
        Vector vectorC = c(b(), 2);
        if (a.b()) {
            a.b("Found " + vectorC.size() + " in word list");
            if (a.a()) {
                for (int i = 0; i < vectorC.size(); i++) {
                    a.a(vectorC.elementAt(i).toString());
                }
            }
        }
        Vector vectorA = a(this.d, vectorC);
        if (a.b()) {
            a.b("Found " + vectorA.size() + " in n-best list");
            if (a.a()) {
                for (int i2 = 0; i2 < vectorA.size(); i2++) {
                    a.a(vectorA.elementAt(i2).toString());
                }
            }
        }
        Hashtable hashtableF = f(this.d.d);
        a(hashtableF, vectorA);
        a(hashtableF);
        return new ff(vectorA);
    }
}
