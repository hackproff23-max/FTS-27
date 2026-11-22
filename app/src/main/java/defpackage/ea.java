package defpackage;

/* JADX INFO: loaded from: classes.dex */
public class ea extends dt implements cv {
    private static final bm a = d.a(ea.class);

    public ea(byte[] bArr) {
        super((short) 29185, bArr);
    }

    public final boolean g() {
        boolean z = true;
        try {
            if (a("final_response")) {
                if (d("final_response") == 0) {
                    z = false;
                }
            } else if (a.c()) {
                a.c("final_response does not exist. ");
            }
        } catch (Exception e) {
        }
        return z;
    }
}
