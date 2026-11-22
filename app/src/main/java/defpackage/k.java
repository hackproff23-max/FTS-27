package defpackage;

import android.os.Build;
import android.support.v4.view.MotionEventCompat;
import com.nuance.nmdp.speechkit.SpeechKit;
import com.nuance.nmdp.speechkit.recognitionresult.b;
import java.util.Calendar;
import java.util.Locale;

/* JADX INFO: loaded from: classes.dex */
public final class k {
    private static String[][] t = {new String[]{"NVC_ASR_CMD", "NVC_DATA_UPLOAD_CMD", "NVC_LOG_REVISION_CMD", "NVC_RESET_USER_PROFILE_CMD", "NVC_APPSERVER_CMD", "NVC_GET_DICTATION_LANGUAGE", "NVC_TTS_CMD"}, new String[]{"DRAGON_NLU_ASR_CMD", "DRAGON_NLU_DATA_UPLOAD_CMD", "DRAGON_NLU_LOG_REVISION_CMD", "DRAGON_NLU_RESET_USER_PROFILE_CMD", "DRAGON_NLU_APPSERVER_CMD", "ACADIA_GET_DICTATION_LANGUAGE_V2", "DRAGON_NLU_TTS_CMD"}};
    private static int u = 420611619;
    private static int v = 418947094;
    private static int w = 1434515842;
    private final String a;
    private final String b;
    private final byte[] c;
    private final String d;
    private final int e;
    private final boolean f;
    private final Object g;
    private final String h;
    private final b i;
    private String j = null;
    private boolean k = false;
    private String l;
    private String m;
    private String n;
    private String o;
    private String p;
    private String q;
    private String r;
    private SpeechKit.CmdSetType s;

    public k(Object obj, String str, String str2, int i, String str3, boolean z, String str4, byte[] bArr, SpeechKit.CmdSetType cmdSetType) {
        this.a = str4;
        this.b = str;
        this.c = bArr;
        this.d = str2;
        this.e = i;
        this.f = z;
        this.g = obj;
        this.i = new b(obj);
        this.h = this.i.c();
        this.l = str3;
        this.s = cmdSetType;
        b(this.s);
    }

    private void b(SpeechKit.CmdSetType cmdSetType) {
        this.m = t[cmdSetType.getIndex()][0];
        this.n = t[cmdSetType.getIndex()][1];
        this.o = t[cmdSetType.getIndex()][2];
        this.p = t[cmdSetType.getIndex()][3];
        this.q = t[cmdSetType.getIndex()][4];
        String[][] strArr = t;
        cmdSetType.getIndex();
        this.r = t[cmdSetType.getIndex()][6];
    }

    public static String n() {
        return aw.b();
    }

    public static String q() {
        return aw.a();
    }

    public final SpeechKit.CmdSetType A() {
        return this.s;
    }

    public final Object a() {
        return this.g;
    }

    public final void a(SpeechKit.CmdSetType cmdSetType) {
        this.s = cmdSetType;
        b(this.s);
    }

    public final byte[] a(byte[] bArr) {
        int[] iArr = {26, 233, 231, 106, 177, 47, 122, 185, 154, 158, 116, 173, 122, 152, 94, 156, 182, 175, 122, 187, 114, 37, 234, 222, 155, 36, 165, 106, 215, 171, 41, 93};
        byte[] bArr2 = new byte[96];
        b bVar = this.i;
        int i = Calendar.getInstance().get(13);
        b bVar2 = this.i;
        int i2 = i + Calendar.getInstance().get(14) + 1793583386;
        byte[] bArrF = this.i.f();
        byte[] bArrG = this.i.g();
        int[] iArr2 = new int[bArrF.length];
        int[] iArr3 = new int[bArrG.length];
        for (int i3 = 0; i3 < iArr2.length; i3++) {
            iArr2[i3] = bArrF[i3];
            if (iArr2[i3] < 0) {
                iArr2[i3] = iArr2[i3] + 256;
            }
        }
        for (int i4 = 0; i4 < iArr3.length; i4++) {
            iArr3[i4] = bArrG[i4];
            if (iArr3[i4] < 0) {
                iArr3[i4] = iArr3[i4] + 256;
            }
        }
        int i5 = i2;
        long j = 0;
        int[] iArr4 = iArr2;
        for (int i6 = 0; i6 < 32; i6++) {
            i5 = ((i5 * (u - v)) + w) - u;
            if (iArr4 == null) {
                j = i5;
            } else if (i6 >= iArr4.length) {
                iArr4 = null;
            } else {
                j = (j >> 8) + ((long) (iArr[i6] + iArr4[i6] + (i5 & MotionEventCompat.ACTION_MASK)));
            }
            bArr2[i6] = (byte) j;
        }
        long j2 = 0;
        int i7 = i5;
        int[] iArr5 = iArr3;
        for (int i8 = 0; i8 < 64; i8++) {
            i7 = ((i7 * (u - v)) + w) - u;
            if (iArr5 == null) {
                j2 = i7;
            } else if (i8 >= iArr5.length || i8 >= iArr.length) {
                iArr5 = null;
            } else {
                j2 = (j2 >> 8) + ((long) (iArr[i8] + iArr5[i8] + (i7 & MotionEventCompat.ACTION_MASK)));
            }
            bArr2[i8 + 32] = (byte) j2;
        }
        long j3 = (((u - v) * i7) + w) - u;
        long j4 = j3 == 0 ? 1L : j3 & 127;
        for (int i9 = 0; i9 < bArr2.length; i9++) {
            while (true) {
                j4 = ((((j4 >> 6) ^ (((j4 >> 1) ^ j4) ^ (j4 >> 3))) & 1) << 6) | (j4 >> 1);
                if (j4 <= bArr2.length && j4 >= 0) {
                    break;
                }
            }
            if (j4 > 26) {
                bArr[((int) j4) + 3] = bArr2[i9];
            } else {
                bArr[((int) j4) - 1] = bArr2[i9];
            }
        }
        bArr[26] = (byte) (i2 & MotionEventCompat.ACTION_MASK);
        bArr[27] = (byte) ((i2 >> 8) & MotionEventCompat.ACTION_MASK);
        bArr[28] = (byte) ((i2 >> 16) & MotionEventCompat.ACTION_MASK);
        bArr[29] = (byte) ((i2 >> 24) & MotionEventCompat.ACTION_MASK);
        return bArr;
    }

    public final String b() {
        return this.a;
    }

    public final byte[] c() {
        return this.c;
    }

    public final String d() {
        return this.d;
    }

    public final String e() {
        return this.b;
    }

    public final int f() {
        return this.e;
    }

    public final String g() {
        return this.l;
    }

    public final boolean h() {
        return this.f;
    }

    public final String i() {
        String strA = this.i.a();
        return strA == null ? "unknown" : strA;
    }

    public final String j() {
        String string;
        b bVar = this.i;
        Locale locale = Locale.getDefault();
        if (locale == null || (string = locale.toString()) == null || string.length() <= 0) {
            string = null;
        }
        return string == null ? "unknown" : string;
    }

    public final String k() {
        b bVar = this.i;
        String str = Build.MODEL;
        return str == null ? "unknown" : str;
    }

    public final String l() {
        b bVar = this.i;
        String str = Build.VERSION.RELEASE;
        return str == null ? "unknown" : str;
    }

    public final String m() {
        String country;
        b bVar = this.i;
        Locale locale = Locale.getDefault();
        if (locale == null || (country = locale.getCountry()) == null || country.length() <= 0) {
            country = null;
        }
        return country == null ? "unknown" : country;
    }

    public final String o() {
        String strB = this.i.b();
        return strB == null ? "unknown" : strB;
    }

    public final String p() {
        return this.h == null ? "unknown" : this.h;
    }

    public final bi r() {
        return this.i.e();
    }

    public final bi s() {
        return this.i.d();
    }

    public final String t() {
        if (!this.k) {
            this.k = true;
            try {
                String str = this.d;
                int i = this.e;
                this.j = cy.g();
            } catch (RuntimeException e) {
                b bVar = this.i;
                this.j = null;
            }
        }
        return this.j;
    }

    public final String u() {
        return this.m;
    }

    public final String v() {
        return this.n;
    }

    public final String w() {
        return this.o;
    }

    public final String x() {
        return this.p;
    }

    public final String y() {
        return this.q;
    }

    public final String z() {
        return this.r;
    }
}
