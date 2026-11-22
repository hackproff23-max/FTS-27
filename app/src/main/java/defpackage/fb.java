package defpackage;

/* JADX INFO: loaded from: classes.dex */
public class fb {
    private static final bm a = d.a(fb.class);

    private fb() {
    }

    /* JADX WARN: Code duplicated, block: B:29:0x008a A[Catch: Exception -> 0x009f, TryCatch #0 {Exception -> 0x009f, blocks: (B:15:0x004d, B:17:0x0050, B:19:0x0061, B:21:0x0069, B:23:0x0071, B:25:0x0079, B:26:0x0080, B:27:0x0085, B:29:0x008a, B:31:0x0092, B:32:0x0099), top: B:37:0x004d }] */
    /* JADX WARN: Code duplicated, block: B:31:0x0092 A[Catch: Exception -> 0x009f, TryCatch #0 {Exception -> 0x009f, blocks: (B:15:0x004d, B:17:0x0050, B:19:0x0061, B:21:0x0069, B:23:0x0071, B:25:0x0079, B:26:0x0080, B:27:0x0085, B:29:0x008a, B:31:0x0092, B:32:0x0099), top: B:37:0x004d }] */
    public static fa a(byte[] bArr) throws IllegalArgumentException {
        fi fjVar;
        if (a.b()) {
            a.b("DictationResultImpl(buffer [size: " + bArr.length + "] )");
            if (a.a()) {
                a.a(bArr);
            }
        }
        if (bArr == null || bArr.length < 4) {
            if (a.e()) {
                a.e("Cannot parse dictation results: The buffer length is too small to be containing any results.");
            }
            throw new IllegalArgumentException("Cannot parse dictation results: The buffer length is too small to be containing any results.");
        }
        try {
            if (bArr.length > 20) {
                String strA = fr.a(bArr, 0, 20, "ISO-8859-1");
                if (strA.startsWith("<?xml") || (strA.indexOf("<?") >= 0 && strA.indexOf("xml") >= 0)) {
                    if (a.b()) {
                        a.b("Detected xml results. Using xml parser.");
                    }
                    fjVar = new fm(bArr);
                } else {
                    if (a.b()) {
                        a.b("Detected binary results. Using binary parser.");
                    }
                    fjVar = new fj(bArr);
                }
            } else {
                if (a.b()) {
                    a.b("Detected binary results. Using binary parser.");
                }
                fjVar = new fj(bArr);
            }
            return fjVar.a();
        } catch (Exception e) {
            throw new IllegalArgumentException("Cannot parse dictation results: illegal format buffer.");
        }
    }
}
