package com.flurry.sdk;

import com.facebook.internal.ServerProtocol;
import com.google.android.gms.games.quest.Quests;
import java.io.Closeable;
import java.io.EOFException;
import java.io.IOException;
import java.io.Reader;

/* JADX INFO: loaded from: classes.dex */
public class jx implements Closeable {
    private static final char[] a = ")]}'\n".toCharArray();
    private final Reader b;
    private long j;
    private int k;
    private String l;
    private int n;
    private String[] o;
    private int[] p;
    private boolean c = false;
    private final char[] d = new char[1024];
    private int e = 0;
    private int f = 0;
    private int g = 0;
    private int h = 0;
    private int i = 0;
    private int[] m = new int[32];

    static {
        jc.a = new jc() { // from class: com.flurry.sdk.jx.1
            @Override // com.flurry.sdk.jc
            public void a(jx jxVar) throws IOException {
                if (!(jxVar instanceof jn)) {
                    int iO = jxVar.i;
                    if (iO == 0) {
                        iO = jxVar.o();
                    }
                    if (iO == 13) {
                        jxVar.i = 9;
                        return;
                    } else if (iO == 12) {
                        jxVar.i = 8;
                        return;
                    } else {
                        if (iO == 14) {
                            jxVar.i = 10;
                            return;
                        }
                        throw new IllegalStateException("Expected a name but was " + jxVar.f() + "  at line " + jxVar.v() + " column " + jxVar.w() + " path " + jxVar.q());
                    }
                }
                ((jn) jxVar).o();
            }
        };
    }

    public jx(Reader reader) {
        this.n = 0;
        int[] iArr = this.m;
        int i = this.n;
        this.n = i + 1;
        iArr[i] = 6;
        this.o = new String[32];
        this.p = new int[32];
        if (reader == null) {
            throw new NullPointerException("in == null");
        }
        this.b = reader;
    }

    public final void a(boolean z) {
        this.c = z;
    }

    public final boolean p() {
        return this.c;
    }

    public void a() throws IOException {
        int iO = this.i;
        if (iO == 0) {
            iO = o();
        }
        if (iO == 3) {
            a(1);
            this.p[this.n - 1] = 0;
            this.i = 0;
            return;
        }
        throw new IllegalStateException("Expected BEGIN_ARRAY but was " + f() + " at line " + v() + " column " + w() + " path " + q());
    }

    public void b() throws IOException {
        int iO = this.i;
        if (iO == 0) {
            iO = o();
        }
        if (iO == 4) {
            this.n--;
            this.i = 0;
            return;
        }
        throw new IllegalStateException("Expected END_ARRAY but was " + f() + " at line " + v() + " column " + w() + " path " + q());
    }

    public void c() throws IOException {
        int iO = this.i;
        if (iO == 0) {
            iO = o();
        }
        if (iO == 1) {
            a(3);
            this.i = 0;
            return;
        }
        throw new IllegalStateException("Expected BEGIN_OBJECT but was " + f() + " at line " + v() + " column " + w() + " path " + q());
    }

    public void d() throws IOException {
        int iO = this.i;
        if (iO == 0) {
            iO = o();
        }
        if (iO == 2) {
            this.n--;
            this.o[this.n] = null;
            this.i = 0;
            return;
        }
        throw new IllegalStateException("Expected END_OBJECT but was " + f() + " at line " + v() + " column " + w() + " path " + q());
    }

    public boolean e() throws IOException {
        int iO = this.i;
        if (iO == 0) {
            iO = o();
        }
        return (iO == 2 || iO == 4) ? false : true;
    }

    public jy f() throws IOException {
        int iO = this.i;
        if (iO == 0) {
            iO = o();
        }
        switch (iO) {
            case 1:
                return jy.BEGIN_OBJECT;
            case 2:
                return jy.END_OBJECT;
            case 3:
                return jy.BEGIN_ARRAY;
            case 4:
                return jy.END_ARRAY;
            case 5:
            case 6:
                return jy.BOOLEAN;
            case 7:
                return jy.NULL;
            case 8:
            case 9:
            case 10:
            case 11:
                return jy.STRING;
            case 12:
            case 13:
            case 14:
                return jy.NAME;
            case 15:
            case 16:
                return jy.NUMBER;
            case 17:
                return jy.END_DOCUMENT;
            default:
                throw new AssertionError();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public int o() throws IOException {
        int i = this.m[this.n - 1];
        if (i == 1) {
            this.m[this.n - 1] = 2;
        } else if (i == 2) {
            switch (b(true)) {
                case 44:
                    break;
                case 59:
                    x();
                    break;
                case 93:
                    this.i = 4;
                    return 4;
                default:
                    throw b("Unterminated array");
            }
        } else {
            if (i == 3 || i == 5) {
                this.m[this.n - 1] = 4;
                if (i == 5) {
                    switch (b(true)) {
                        case 44:
                            break;
                        case 59:
                            x();
                            break;
                        case 125:
                            this.i = 2;
                            return 2;
                        default:
                            throw b("Unterminated object");
                    }
                }
                int iB = b(true);
                switch (iB) {
                    case 34:
                        this.i = 13;
                        return 13;
                    case 39:
                        x();
                        this.i = 12;
                        return 12;
                    case 125:
                        if (i != 5) {
                            this.i = 2;
                            return 2;
                        }
                        throw b("Expected name");
                    default:
                        x();
                        this.e--;
                        if (a((char) iB)) {
                            this.i = 14;
                            return 14;
                        }
                        throw b("Expected name");
                }
            }
            if (i == 4) {
                this.m[this.n - 1] = 5;
                switch (b(true)) {
                    case 58:
                        break;
                    case 59:
                    case 60:
                    default:
                        throw b("Expected ':'");
                    case 61:
                        x();
                        if ((this.e < this.f || b(1)) && this.d[this.e] == '>') {
                            this.e++;
                        }
                        break;
                }
            } else if (i == 6) {
                if (this.c) {
                    A();
                }
                this.m[this.n - 1] = 7;
            } else if (i == 7) {
                if (b(false) == -1) {
                    this.i = 17;
                    return 17;
                }
                x();
                this.e--;
            } else if (i == 8) {
                throw new IllegalStateException("JsonReader is closed");
            }
        }
        switch (b(true)) {
            case 34:
                if (this.n == 1) {
                    x();
                }
                this.i = 9;
                return 9;
            case 39:
                x();
                this.i = 8;
                return 8;
            case 44:
            case 59:
                break;
            case 91:
                this.i = 3;
                return 3;
            case 93:
                if (i == 1) {
                    this.i = 4;
                    return 4;
                }
                break;
            case 123:
                this.i = 1;
                return 1;
            default:
                this.e--;
                if (this.n == 1) {
                    x();
                }
                int iR = r();
                if (iR == 0) {
                    int iS = s();
                    if (iS == 0) {
                        if (!a(this.d[this.e])) {
                            throw b("Expected value");
                        }
                        x();
                        this.i = 10;
                        return 10;
                    }
                    return iS;
                }
                return iR;
        }
        if (i == 1 || i == 2) {
            x();
            this.e--;
            this.i = 7;
            return 7;
        }
        throw b("Unexpected value");
    }

    private int r() throws IOException {
        String str;
        String str2;
        int i;
        char c = this.d[this.e];
        if (c == 't' || c == 'T') {
            str = ServerProtocol.DIALOG_RETURN_SCOPES_TRUE;
            str2 = "TRUE";
            i = 5;
        } else if (c == 'f' || c == 'F') {
            str = "false";
            str2 = "FALSE";
            i = 6;
        } else {
            if (c != 'n' && c != 'N') {
                return 0;
            }
            str = "null";
            str2 = "NULL";
            i = 7;
        }
        int length = str.length();
        for (int i2 = 1; i2 < length; i2++) {
            if (this.e + i2 >= this.f && !b(i2 + 1)) {
                return 0;
            }
            char c2 = this.d[this.e + i2];
            if (c2 != str.charAt(i2) && c2 != str2.charAt(i2)) {
                return 0;
            }
        }
        if ((this.e + length < this.f || b(length + 1)) && a(this.d[this.e + length])) {
            return 0;
        }
        this.e += length;
        this.i = i;
        return i;
    }

    /* JADX WARN: Code duplicated, block: B:22:0x0046  */
    /* JADX WARN: Code duplicated, block: B:28:0x0054 A[RETURN, SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:29:0x0056 A[DONT_INVERT] */
    /* JADX WARN: Code duplicated, block: B:30:0x0058  */
    /* JADX WARN: Code duplicated, block: B:32:0x0064  */
    /* JADX WARN: Code duplicated, block: B:34:0x0067  */
    /* JADX WARN: Code duplicated, block: B:36:0x006d  */
    /* JADX WARN: Code duplicated, block: B:38:0x0070  */
    /* JADX WARN: Code duplicated, block: B:40:0x0076  */
    /* JADX WARN: Code duplicated, block: B:46:0x0082  */
    /* JADX WARN: Code duplicated, block: B:48:0x0085  */
    /* JADX WARN: Code duplicated, block: B:90:0x006b A[SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:91:0x0074 A[SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:93:0x0089 A[SYNTHETIC] */
    private int s() throws IOException {
        char c;
        char c2;
        boolean z;
        boolean z2;
        char[] cArr = this.d;
        int i = this.e;
        long j = 0;
        boolean z3 = false;
        boolean z4 = true;
        char c3 = 0;
        int i2 = 0;
        int i3 = this.f;
        int i4 = i;
        while (true) {
            if (i4 + i2 == i3) {
                if (i2 == cArr.length) {
                    return 0;
                }
                if (b(i2 + 1)) {
                    i4 = this.e;
                    i3 = this.f;
                    c = cArr[i4 + i2];
                    switch (c) {
                        case '+':
                            if (c3 == 5) {
                                c2 = 6;
                                z = z4;
                                z2 = z3;
                                continue;
                                continue;
                                i2++;
                                z3 = z2;
                                z4 = z;
                                c3 = c2;
                            } else {
                                return 0;
                            }
                            break;
                        case '-':
                            if (c3 == 0) {
                                c2 = 1;
                                boolean z5 = z4;
                                z2 = true;
                                z = z5;
                                continue;
                                continue;
                            } else if (c3 == 5) {
                                c2 = 6;
                                z = z4;
                                z2 = z3;
                            } else {
                                return 0;
                            }
                            i2++;
                            z3 = z2;
                            z4 = z;
                            c3 = c2;
                            break;
                        case '.':
                            if (c3 == 2) {
                                c2 = 3;
                                z = z4;
                                z2 = z3;
                                continue;
                                continue;
                                i2++;
                                z3 = z2;
                                z4 = z;
                                c3 = c2;
                            } else {
                                return 0;
                            }
                            break;
                        case 'E':
                        case Quests.SELECT_COMPLETED_UNCLAIMED /* 101 */:
                            if (c3 != 2) {
                            }
                            c2 = 5;
                            z = z4;
                            z2 = z3;
                            continue;
                            continue;
                            i2++;
                            z3 = z2;
                            z4 = z;
                            c3 = c2;
                            break;
                        default:
                            if (c >= '0') {
                            }
                            if (a(c)) {
                                return 0;
                            }
                            break;
                    }
                }
            } else {
                c = cArr[i4 + i2];
                switch (c) {
                    case '+':
                        if (c3 == 5) {
                            c2 = 6;
                            z = z4;
                            z2 = z3;
                            continue;
                            continue;
                            i2++;
                            z3 = z2;
                            z4 = z;
                            c3 = c2;
                        } else {
                            return 0;
                        }
                        break;
                    case '-':
                        if (c3 == 0) {
                            c2 = 1;
                            boolean z6 = z4;
                            z2 = true;
                            z = z6;
                            continue;
                            continue;
                        } else if (c3 == 5) {
                            c2 = 6;
                            z = z4;
                            z2 = z3;
                        } else {
                            return 0;
                        }
                        i2++;
                        z3 = z2;
                        z4 = z;
                        c3 = c2;
                        break;
                    case '.':
                        if (c3 == 2) {
                            c2 = 3;
                            z = z4;
                            z2 = z3;
                            continue;
                            continue;
                            i2++;
                            z3 = z2;
                            z4 = z;
                            c3 = c2;
                        } else {
                            return 0;
                        }
                        break;
                    case 'E':
                    case Quests.SELECT_COMPLETED_UNCLAIMED /* 101 */:
                        if (c3 != 2 || c3 == 4) {
                            c2 = 5;
                            z = z4;
                            z2 = z3;
                            continue;
                            continue;
                            i2++;
                            z3 = z2;
                            z4 = z;
                            c3 = c2;
                        } else {
                            return 0;
                        }
                        break;
                    default:
                        if (c >= '0' || c > '9') {
                            if (a(c)) {
                                return 0;
                            }
                        } else {
                            if (c3 == 1 || c3 == 0) {
                                j = -(c - '0');
                                c2 = 2;
                                z = z4;
                                z2 = z3;
                            } else if (c3 == 2) {
                                if (j == 0) {
                                    return 0;
                                }
                                long j2 = (10 * j) - ((long) (c - '0'));
                                boolean z7 = (j > -922337203685477580L || (j == -922337203685477580L && j2 < j)) & z4;
                                z2 = z3;
                                j = j2;
                                char c4 = c3;
                                z = z7;
                                c2 = c4;
                            } else if (c3 == 3) {
                                c2 = 4;
                                z = z4;
                                z2 = z3;
                            } else if (c3 == 5 || c3 == 6) {
                                c2 = 7;
                                z = z4;
                                z2 = z3;
                            } else {
                                c2 = c3;
                                z = z4;
                                z2 = z3;
                            }
                            i2++;
                            z3 = z2;
                            z4 = z;
                            c3 = c2;
                        }
                        break;
                }
            }
        }
        if (c3 == 2 && z4 && (j != Long.MIN_VALUE || z3)) {
            if (!z3) {
                j = -j;
            }
            this.j = j;
            this.e += i2;
            this.i = 15;
            return 15;
        }
        if (c3 == 2 || c3 == 4 || c3 == 7) {
            this.k = i2;
            this.i = 16;
            return 16;
        }
        return 0;
    }

    private boolean a(char c) throws IOException {
        switch (c) {
            case '#':
            case '/':
            case ';':
            case '=':
            case '\\':
                x();
            case '\t':
            case '\n':
            case '\f':
            case '\r':
            case ' ':
            case ',':
            case ':':
            case '[':
            case ']':
            case '{':
            case '}':
                return false;
            default:
                return true;
        }
    }

    public String g() throws IOException {
        String strB;
        int iO = this.i;
        if (iO == 0) {
            iO = o();
        }
        if (iO == 14) {
            strB = t();
        } else if (iO == 12) {
            strB = b('\'');
        } else if (iO == 13) {
            strB = b('\"');
        } else {
            throw new IllegalStateException("Expected a name but was " + f() + " at line " + v() + " column " + w() + " path " + q());
        }
        this.i = 0;
        this.o[this.n - 1] = strB;
        return strB;
    }

    public String h() throws IOException {
        String str;
        int iO = this.i;
        if (iO == 0) {
            iO = o();
        }
        if (iO == 10) {
            str = t();
        } else if (iO == 8) {
            str = b('\'');
        } else if (iO == 9) {
            str = b('\"');
        } else if (iO == 11) {
            str = this.l;
            this.l = null;
        } else if (iO == 15) {
            str = Long.toString(this.j);
        } else if (iO == 16) {
            str = new String(this.d, this.e, this.k);
            this.e += this.k;
        } else {
            throw new IllegalStateException("Expected a string but was " + f() + " at line " + v() + " column " + w() + " path " + q());
        }
        this.i = 0;
        int[] iArr = this.p;
        int i = this.n - 1;
        iArr[i] = iArr[i] + 1;
        return str;
    }

    public boolean i() throws IOException {
        int iO = this.i;
        if (iO == 0) {
            iO = o();
        }
        if (iO == 5) {
            this.i = 0;
            int[] iArr = this.p;
            int i = this.n - 1;
            iArr[i] = iArr[i] + 1;
            return true;
        }
        if (iO == 6) {
            this.i = 0;
            int[] iArr2 = this.p;
            int i2 = this.n - 1;
            iArr2[i2] = iArr2[i2] + 1;
            return false;
        }
        throw new IllegalStateException("Expected a boolean but was " + f() + " at line " + v() + " column " + w() + " path " + q());
    }

    public void j() throws IOException {
        int iO = this.i;
        if (iO == 0) {
            iO = o();
        }
        if (iO == 7) {
            this.i = 0;
            int[] iArr = this.p;
            int i = this.n - 1;
            iArr[i] = iArr[i] + 1;
            return;
        }
        throw new IllegalStateException("Expected null but was " + f() + " at line " + v() + " column " + w() + " path " + q());
    }

    public double k() throws IOException {
        int iO = this.i;
        if (iO == 0) {
            iO = o();
        }
        if (iO == 15) {
            this.i = 0;
            int[] iArr = this.p;
            int i = this.n - 1;
            iArr[i] = iArr[i] + 1;
            return this.j;
        }
        if (iO == 16) {
            this.l = new String(this.d, this.e, this.k);
            this.e += this.k;
        } else if (iO == 8 || iO == 9) {
            this.l = b(iO == 8 ? '\'' : '\"');
        } else if (iO == 10) {
            this.l = t();
        } else if (iO != 11) {
            throw new IllegalStateException("Expected a double but was " + f() + " at line " + v() + " column " + w() + " path " + q());
        }
        this.i = 11;
        double d = Double.parseDouble(this.l);
        if (!this.c && (Double.isNaN(d) || Double.isInfinite(d))) {
            throw new ka("JSON forbids NaN and infinities: " + d + " at line " + v() + " column " + w() + " path " + q());
        }
        this.l = null;
        this.i = 0;
        int[] iArr2 = this.p;
        int i2 = this.n - 1;
        iArr2[i2] = iArr2[i2] + 1;
        return d;
    }

    public long l() throws IOException {
        int iO = this.i;
        if (iO == 0) {
            iO = o();
        }
        if (iO == 15) {
            this.i = 0;
            int[] iArr = this.p;
            int i = this.n - 1;
            iArr[i] = iArr[i] + 1;
            return this.j;
        }
        if (iO == 16) {
            this.l = new String(this.d, this.e, this.k);
            this.e += this.k;
        } else if (iO == 8 || iO == 9) {
            this.l = b(iO == 8 ? '\'' : '\"');
            try {
                long j = Long.parseLong(this.l);
                this.i = 0;
                int[] iArr2 = this.p;
                int i2 = this.n - 1;
                iArr2[i2] = iArr2[i2] + 1;
                return j;
            } catch (NumberFormatException e) {
            }
        } else {
            throw new IllegalStateException("Expected a long but was " + f() + " at line " + v() + " column " + w() + " path " + q());
        }
        this.i = 11;
        double d = Double.parseDouble(this.l);
        long j2 = (long) d;
        if (j2 != d) {
            throw new NumberFormatException("Expected a long but was " + this.l + " at line " + v() + " column " + w() + " path " + q());
        }
        this.l = null;
        this.i = 0;
        int[] iArr3 = this.p;
        int i3 = this.n - 1;
        iArr3[i3] = iArr3[i3] + 1;
        return j2;
    }

    private String b(char c) throws IOException {
        char[] cArr = this.d;
        StringBuilder sb = new StringBuilder();
        do {
            int i = this.e;
            int i2 = this.f;
            int i3 = i;
            while (i3 < i2) {
                int i4 = i3 + 1;
                char c2 = cArr[i3];
                if (c2 == c) {
                    this.e = i4;
                    sb.append(cArr, i, (i4 - i) - 1);
                    return sb.toString();
                }
                if (c2 == '\\') {
                    this.e = i4;
                    sb.append(cArr, i, (i4 - i) - 1);
                    sb.append(z());
                    i = this.e;
                    i2 = this.f;
                    i4 = i;
                } else if (c2 == '\n') {
                    this.g++;
                    this.h = i4;
                }
                i3 = i4;
            }
            sb.append(cArr, i, i3 - i);
            this.e = i3;
        } while (b(1));
        throw b("Unterminated string");
    }

    private String t() throws IOException {
        String string;
        StringBuilder sb = null;
        int i = 0;
        while (true) {
            if (this.e + i < this.f) {
                switch (this.d[this.e + i]) {
                    case '\t':
                    case '\n':
                    case '\f':
                    case '\r':
                    case ' ':
                    case ',':
                    case ':':
                    case '[':
                    case ']':
                    case '{':
                    case '}':
                        break;
                    case '#':
                    case '/':
                    case ';':
                    case '=':
                    case '\\':
                        x();
                        break;
                    default:
                        i++;
                        continue;
                }
            } else if (i < this.d.length) {
                if (b(i + 1)) {
                }
            } else {
                if (sb == null) {
                    sb = new StringBuilder();
                }
                sb.append(this.d, this.e, i);
                this.e = i + this.e;
                if (b(1)) {
                    i = 0;
                } else {
                    i = 0;
                }
            }
        }
        if (sb == null) {
            string = new String(this.d, this.e, i);
        } else {
            sb.append(this.d, this.e, i);
            string = sb.toString();
        }
        this.e = i + this.e;
        return string;
    }

    private void c(char c) throws IOException {
        char[] cArr = this.d;
        do {
            int i = this.e;
            int i2 = this.f;
            int i3 = i;
            while (i3 < i2) {
                int i4 = i3 + 1;
                char c2 = cArr[i3];
                if (c2 == c) {
                    this.e = i4;
                    return;
                }
                if (c2 == '\\') {
                    this.e = i4;
                    z();
                    i4 = this.e;
                    i2 = this.f;
                } else if (c2 == '\n') {
                    this.g++;
                    this.h = i4;
                }
                i3 = i4;
            }
            this.e = i3;
        } while (b(1));
        throw b("Unterminated string");
    }

    private void u() throws IOException {
        do {
            int i = 0;
            while (this.e + i < this.f) {
                switch (this.d[this.e + i]) {
                    case '\t':
                    case '\n':
                    case '\f':
                    case '\r':
                    case ' ':
                    case ',':
                    case ':':
                    case '[':
                    case ']':
                    case '{':
                    case '}':
                        break;
                    case '#':
                    case '/':
                    case ';':
                    case '=':
                    case '\\':
                        x();
                        break;
                    default:
                        i++;
                        break;
                }
                this.e = i + this.e;
                return;
            }
            this.e = i + this.e;
        } while (b(1));
    }

    public int m() throws IOException {
        int iO = this.i;
        if (iO == 0) {
            iO = o();
        }
        if (iO == 15) {
            int i = (int) this.j;
            if (this.j != i) {
                throw new NumberFormatException("Expected an int but was " + this.j + " at line " + v() + " column " + w() + " path " + q());
            }
            this.i = 0;
            int[] iArr = this.p;
            int i2 = this.n - 1;
            iArr[i2] = iArr[i2] + 1;
            return i;
        }
        if (iO == 16) {
            this.l = new String(this.d, this.e, this.k);
            this.e += this.k;
        } else if (iO == 8 || iO == 9) {
            this.l = b(iO == 8 ? '\'' : '\"');
            try {
                int i3 = Integer.parseInt(this.l);
                this.i = 0;
                int[] iArr2 = this.p;
                int i4 = this.n - 1;
                iArr2[i4] = iArr2[i4] + 1;
                return i3;
            } catch (NumberFormatException e) {
            }
        } else {
            throw new IllegalStateException("Expected an int but was " + f() + " at line " + v() + " column " + w() + " path " + q());
        }
        this.i = 11;
        double d = Double.parseDouble(this.l);
        int i5 = (int) d;
        if (i5 != d) {
            throw new NumberFormatException("Expected an int but was " + this.l + " at line " + v() + " column " + w() + " path " + q());
        }
        this.l = null;
        this.i = 0;
        int[] iArr3 = this.p;
        int i6 = this.n - 1;
        iArr3[i6] = iArr3[i6] + 1;
        return i5;
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public void close() throws IOException {
        this.i = 0;
        this.m[0] = 8;
        this.n = 1;
        this.b.close();
    }

    public void n() throws IOException {
        int i = 0;
        do {
            int iO = this.i;
            if (iO == 0) {
                iO = o();
            }
            if (iO == 3) {
                a(1);
                i++;
            } else if (iO == 1) {
                a(3);
                i++;
            } else if (iO == 4 || iO == 2) {
                this.n--;
                i--;
            } else if (iO == 14 || iO == 10) {
                u();
            } else if (iO == 8 || iO == 12) {
                c('\'');
            } else if (iO == 9 || iO == 13) {
                c('\"');
            } else if (iO == 16) {
                this.e += this.k;
            }
            this.i = 0;
        } while (i != 0);
        int[] iArr = this.p;
        int i2 = this.n - 1;
        iArr[i2] = iArr[i2] + 1;
        this.o[this.n - 1] = "null";
    }

    private void a(int i) {
        if (this.n == this.m.length) {
            int[] iArr = new int[this.n * 2];
            int[] iArr2 = new int[this.n * 2];
            String[] strArr = new String[this.n * 2];
            System.arraycopy(this.m, 0, iArr, 0, this.n);
            System.arraycopy(this.p, 0, iArr2, 0, this.n);
            System.arraycopy(this.o, 0, strArr, 0, this.n);
            this.m = iArr;
            this.p = iArr2;
            this.o = strArr;
        }
        int[] iArr3 = this.m;
        int i2 = this.n;
        this.n = i2 + 1;
        iArr3[i2] = i;
    }

    private boolean b(int i) throws IOException {
        char[] cArr = this.d;
        this.h -= this.e;
        if (this.f != this.e) {
            this.f -= this.e;
            System.arraycopy(cArr, this.e, cArr, 0, this.f);
        } else {
            this.f = 0;
        }
        this.e = 0;
        do {
            int i2 = this.b.read(cArr, this.f, cArr.length - this.f);
            if (i2 == -1) {
                return false;
            }
            this.f = i2 + this.f;
            if (this.g == 0 && this.h == 0 && this.f > 0 && cArr[0] == 65279) {
                this.e++;
                this.h++;
                i++;
            }
        } while (this.f < i);
        return true;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public int v() {
        return this.g + 1;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public int w() {
        return (this.e - this.h) + 1;
    }

    private int b(boolean z) throws IOException {
        char[] cArr = this.d;
        int i = this.e;
        int i2 = this.f;
        while (true) {
            if (i == i2) {
                this.e = i;
                if (b(1)) {
                    i = this.e;
                    i2 = this.f;
                } else {
                    if (z) {
                        throw new EOFException("End of input at line " + v() + " column " + w());
                    }
                    return -1;
                }
            }
            int i3 = i + 1;
            char c = cArr[i];
            if (c == '\n') {
                this.g++;
                this.h = i3;
                i = i3;
            } else if (c == ' ' || c == '\r') {
                i = i3;
            } else if (c == '\t') {
                i = i3;
            } else if (c == '/') {
                this.e = i3;
                if (i3 == i2) {
                    this.e--;
                    boolean zB = b(2);
                    this.e++;
                    if (!zB) {
                        return c;
                    }
                }
                x();
                switch (cArr[this.e]) {
                    case '*':
                        this.e++;
                        if (!a("*/")) {
                            throw b("Unterminated comment");
                        }
                        i = this.e + 2;
                        i2 = this.f;
                        break;
                        break;
                    case '/':
                        this.e++;
                        y();
                        i = this.e;
                        i2 = this.f;
                        break;
                    default:
                        return c;
                }
            } else if (c == '#') {
                this.e = i3;
                x();
                y();
                i = this.e;
                i2 = this.f;
            } else {
                this.e = i3;
                return c;
            }
        }
    }

    private void x() throws IOException {
        if (!this.c) {
            throw b("Use JsonReader.setLenient(true) to accept malformed JSON");
        }
    }

    private void y() throws IOException {
        char c;
        do {
            if (this.e < this.f || b(1)) {
                char[] cArr = this.d;
                int i = this.e;
                this.e = i + 1;
                c = cArr[i];
                if (c == '\n') {
                    this.g++;
                    this.h = this.e;
                    return;
                }
            } else {
                return;
            }
        } while (c != '\r');
    }

    private boolean a(String str) throws IOException {
        while (true) {
            if (this.e + str.length() > this.f && !b(str.length())) {
                return false;
            }
            if (this.d[this.e] == '\n') {
                this.g++;
                this.h = this.e + 1;
            } else {
                for (int i = 0; i < str.length(); i++) {
                    if (this.d[this.e + i] == str.charAt(i)) {
                    }
                }
                return true;
            }
            this.e++;
        }
    }

    public String toString() {
        return getClass().getSimpleName() + " at line " + v() + " column " + w();
    }

    public String q() {
        StringBuilder sbAppend = new StringBuilder().append('$');
        int i = this.n;
        for (int i2 = 0; i2 < i; i2++) {
            switch (this.m[i2]) {
                case 1:
                case 2:
                    sbAppend.append('[').append(this.p[i2]).append(']');
                    break;
                case 3:
                case 4:
                case 5:
                    sbAppend.append('.');
                    if (this.o[i2] != null) {
                        sbAppend.append(this.o[i2]);
                    }
                    break;
            }
        }
        return sbAppend.toString();
    }

    private char z() throws IOException {
        int i;
        if (this.e == this.f && !b(1)) {
            throw b("Unterminated escape sequence");
        }
        char[] cArr = this.d;
        int i2 = this.e;
        this.e = i2 + 1;
        char c = cArr[i2];
        switch (c) {
            case '\n':
                this.g++;
                this.h = this.e;
                return c;
            case 'b':
                return '\b';
            case 'f':
                return '\f';
            case 'n':
                return '\n';
            case 'r':
                return '\r';
            case 't':
                return '\t';
            case 'u':
                if (this.e + 4 > this.f && !b(4)) {
                    throw b("Unterminated escape sequence");
                }
                int i3 = this.e;
                int i4 = i3 + 4;
                char c2 = 0;
                for (int i5 = i3; i5 < i4; i5++) {
                    char c3 = this.d[i5];
                    char c4 = (char) (c2 << 4);
                    if (c3 >= '0' && c3 <= '9') {
                        i = c3 - '0';
                    } else if (c3 >= 'a' && c3 <= 'f') {
                        i = (c3 - 'a') + 10;
                    } else if (c3 >= 'A' && c3 <= 'F') {
                        i = (c3 - 'A') + 10;
                    } else {
                        throw new NumberFormatException("\\u" + new String(this.d, this.e, 4));
                    }
                    c2 = (char) (c4 + i);
                }
                this.e += 4;
                return c2;
            default:
                return c;
        }
    }

    private IOException b(String str) throws IOException {
        throw new ka(str + " at line " + v() + " column " + w() + " path " + q());
    }

    private void A() throws IOException {
        b(true);
        this.e--;
        if (this.e + a.length <= this.f || b(a.length)) {
            for (int i = 0; i < a.length; i++) {
                if (this.d[this.e + i] != a[i]) {
                    return;
                }
            }
            this.e += a.length;
        }
    }
}
