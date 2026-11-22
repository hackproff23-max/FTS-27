package defpackage;

/* JADX INFO: loaded from: classes.dex */
public class eb extends dt implements el {
    private static final bm a = d.a(eb.class);

    public eb(byte[] bArr) {
        super((short) 29189, bArr);
    }

    @Override // defpackage.el
    public final String g() {
        a.b("PDXQueryRetry.getName()");
        String strF = f("name");
        return strF != null ? strF : "";
    }

    @Override // defpackage.el
    public final int h() {
        a.b("PDXQueryRetry.getCause()");
        try {
            return d("cause");
        } catch (Exception e) {
            return 0;
        }
    }

    @Override // defpackage.el
    public final String i() {
        String strF;
        a.b("PDXQueryRetry.getPrompt()");
        try {
            strF = f("prompt");
        } catch (Exception e) {
            strF = "";
        }
        return strF != null ? strF : "";
    }
}
