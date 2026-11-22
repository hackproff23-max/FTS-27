package com.flurry.sdk;

import android.text.TextUtils;
import com.flurry.android.impl.ads.protocol.v13.FrequencyCapResponseInfo;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public class bc {
    private static final String a = bc.class.getSimpleName();
    private final HashMap<String, bb> b = new HashMap<>();

    public synchronized void a(b bVar) {
        if (bVar != null) {
            FrequencyCapResponseInfo frequencyCapResponseInfoF = bVar.c().f();
            if (frequencyCapResponseInfoF != null) {
                String str = frequencyCapResponseInfoF.id;
                if (!TextUtils.isEmpty(str)) {
                    bb bbVar = this.b.get(str);
                    if (bbVar == null) {
                        bbVar = new bb(str, frequencyCapResponseInfoF.streamCapDurationMillis);
                        this.b.put(str, bbVar);
                    }
                    bbVar.a(bVar.a.a());
                    bbVar.b(System.currentTimeMillis());
                    if (aw.EV_RENDERED.equals(bVar.a)) {
                        bbVar.a(System.currentTimeMillis());
                    }
                    bbVar.b(bVar.a.a());
                }
            }
        }
    }

    public synchronized List<bb> a() {
        return new ArrayList(this.b.values());
    }

    public synchronized void b() {
        for (bb bbVar : a()) {
            if (System.currentTimeMillis() > bbVar.d() + bbVar.b()) {
                this.b.remove(bbVar.a());
            }
        }
    }
}
