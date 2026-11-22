package com.flurry.sdk;

import android.text.TextUtils;
import com.flurry.android.impl.ads.protocol.v13.FrequencyCapType;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public final class ba {
    private static final String a = ba.class.getSimpleName();
    private final fu<String, az> b = new fu<>();

    public synchronized void a(az azVar) {
        if (azVar != null) {
            if (azVar.b() != null && !TextUtils.isEmpty(azVar.c())) {
                a(azVar.b(), azVar.c());
                if (azVar.g() != -1) {
                    this.b.a(azVar.c(), azVar);
                }
            }
        }
    }

    public synchronized void a(FrequencyCapType frequencyCapType, String str) {
        az next;
        if (frequencyCapType != null) {
            if (!TextUtils.isEmpty(str)) {
                Iterator<az> it = this.b.a(str).iterator();
                do {
                    if (!it.hasNext()) {
                        next = null;
                        break;
                    }
                    next = it.next();
                } while (!next.b().equals(frequencyCapType));
                if (next != null) {
                    this.b.b(str, next);
                }
            }
        }
    }

    public synchronized List<az> a(String str) {
        return new ArrayList(this.b.a(str));
    }

    public synchronized void b(String str) {
        this.b.b(str);
    }

    public synchronized List<az> a() {
        return new ArrayList(this.b.d());
    }

    public synchronized void b() {
        for (az azVar : a()) {
            if (a(azVar.e())) {
                gd.a(3, a, "expiring freq cap for id: " + azVar.c() + " capType:" + azVar.b() + " expiration: " + azVar.e() + " epoch" + System.currentTimeMillis());
                b(azVar.c());
            }
        }
    }

    public boolean a(long j) {
        return j <= System.currentTimeMillis();
    }
}
