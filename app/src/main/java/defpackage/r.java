package defpackage;

import com.nuance.nmdp.speechkit.util.pdx.PdxValue;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/* JADX INFO: loaded from: classes.dex */
public final class r {
    private final int a;
    private final int b;
    private final String c;
    private final byte[] d;
    private final Map<String, r> e;
    private final List<r> f;

    public r(PdxValue pdxValue) {
        this.a = pdxValue.getType();
        switch (this.a) {
            case 0:
                this.b = 0;
                this.c = ((PdxValue.String) pdxValue).get();
                this.d = null;
                this.f = null;
                this.e = null;
                break;
            case 1:
                this.b = ((PdxValue.Integer) pdxValue).get();
                this.c = null;
                this.d = null;
                this.f = null;
                this.e = null;
                break;
            case 2:
                this.b = 0;
                this.c = null;
                this.d = null;
                this.f = null;
                this.e = new HashMap();
                for (Map.Entry<String, PdxValue> entry : ((PdxValue.Dictionary) pdxValue).getEntries()) {
                    this.e.put(entry.getKey(), new r(entry.getValue()));
                }
                break;
            case 3:
                PdxValue.Sequence sequence = (PdxValue.Sequence) pdxValue;
                this.b = 0;
                this.c = null;
                this.d = null;
                this.f = new ArrayList(sequence.size());
                this.e = null;
                Iterator<PdxValue> it = sequence.getValues().iterator();
                while (it.hasNext()) {
                    this.f.add(new r(it.next()));
                }
                break;
            case 4:
                this.b = 0;
                this.c = null;
                this.d = ((PdxValue.Bytes) pdxValue).get();
                this.f = null;
                this.e = null;
                break;
            default:
                this.b = 0;
                this.c = null;
                this.d = null;
                this.f = null;
                this.e = null;
                break;
        }
    }

    public final Map<String, r> a() {
        return this.e;
    }

    public final List<r> b() {
        return this.f;
    }

    public final String c() {
        return this.c;
    }

    public final int d() {
        return this.b;
    }

    public final byte[] e() {
        return this.d;
    }

    public final int f() {
        return this.a;
    }
}
