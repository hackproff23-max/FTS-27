package defpackage;

import java.io.IOException;

/* JADX INFO: loaded from: classes.dex */
public class fm implements fi {
    private static final bm a = d.a(fm.class);
    private byte[] b;

    public fm(byte[] bArr) {
        this.b = bArr;
    }

    @Override // defpackage.fi
    public final fa a() {
        a.b("Unpacking XML dictation results.");
        try {
            fp fpVar = new fp(this.b);
            fpVar.a();
            if (fpVar.b()) {
                return fpVar.c();
            }
            if (a.b()) {
                a.b("Could not parse XML dictation results: " + fpVar.d() + ". Trying to parse NLSML results.");
            }
            fn fnVar = new fn(this.b);
            fnVar.a();
            if (fnVar.b()) {
                return fnVar.c();
            }
            String str = "Could not parse XML neither NLSML dictation results. Error from XML Parser: " + fpVar.d() + ". Error from NLSML Parser: " + fnVar.d();
            if (a.e()) {
                a.e(str);
            }
            throw new IllegalArgumentException(str);
        } catch (IOException e) {
            if (a.e()) {
                a.a("Received IOException while parsing XML/NLSML.", e);
            }
            throw new IllegalArgumentException("Received IOException while parsing XML/NLSML.", e);
        }
    }
}
