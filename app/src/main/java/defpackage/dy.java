package defpackage;

/* JADX INFO: loaded from: classes.dex */
public class dy extends dt implements ek {
    private static final bm a = d.a(dy.class);

    public dy(byte[] bArr) {
        super((short) 29186, bArr);
    }

    @Override // defpackage.ek
    public final int g() {
        a.b("PDXQueryError.getError()");
        return d("error");
    }

    @Override // defpackage.ek
    public final String h() {
        a.b("PDXQueryError.getDescription()");
        String strF = f("description");
        return strF != null ? strF : "";
    }
}
