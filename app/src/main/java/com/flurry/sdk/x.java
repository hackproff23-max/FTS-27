package com.flurry.sdk;

import android.text.TextUtils;
import com.flurry.android.impl.ads.protocol.v13.FrequencyCapResponseInfo;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.TreeSet;

/* JADX INFO: loaded from: classes.dex */
public class x {
    private static final String a = x.class.getSimpleName();
    private final fy<ab> b = new fy<ab>() { // from class: com.flurry.sdk.x.1
        @Override // com.flurry.sdk.fy
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public void notify(ab abVar) {
            if (ab.a.RESUME.equals(abVar.a)) {
                x.this.d();
            }
        }
    };
    private final fy<ay> c = new fy<ay>() { // from class: com.flurry.sdk.x.2
        @Override // com.flurry.sdk.fy
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public void notify(ay ayVar) {
            x.this.a(ayVar.a);
        }
    };
    private final TreeSet<ap> d = new TreeSet<>();
    private final String e;

    public x(String str) {
        this.e = str;
        fz.a().a("com.flurry.android.sdk.AssetCacheManagerStatusEvent", this.b);
        fz.a().a("com.flurry.android.impl.ads.FreqCapEvent", this.c);
    }

    public synchronized void a() {
        this.d.clear();
        fz.a().a(this.b);
        fz.a().a(this.c);
    }

    public synchronized int b() {
        e();
        return this.d.size();
    }

    public synchronized void a(Collection<ap> collection) {
        if (collection != null) {
            this.d.addAll(collection);
        }
    }

    public synchronized List<ap> c() {
        ArrayList arrayList;
        arrayList = new ArrayList();
        ap apVarPollFirst = this.d.pollFirst();
        if (apVarPollFirst != null) {
            arrayList.add(apVarPollFirst);
            String str = apVarPollFirst.a().groupId;
            if (!TextUtils.isEmpty(str)) {
                Iterator<ap> it = this.d.iterator();
                while (it.hasNext()) {
                    ap next = it.next();
                    if (!str.equals(next.a().groupId)) {
                        break;
                    }
                    arrayList.add(next);
                    it.remove();
                }
            }
        }
        return arrayList;
    }

    public synchronized void a(String str) {
        if (!TextUtils.isEmpty(str)) {
            Iterator<ap> it = this.d.iterator();
            while (it.hasNext()) {
                ap next = it.next();
                if (next.a().groupId.equals(str)) {
                    gd.a(3, a, "Removed grouped ad unit -- adspace: " + next.p());
                    it.remove();
                }
            }
        }
    }

    public synchronized void a(az azVar) {
        if (azVar != null) {
            Iterator<ap> it = this.d.iterator();
            while (it.hasNext()) {
                ap next = it.next();
                List<FrequencyCapResponseInfo> list = next.a().frequencyCapResponseInfoList;
                if (list != null) {
                    for (FrequencyCapResponseInfo frequencyCapResponseInfo : list) {
                        if (azVar.b().equals(frequencyCapResponseInfo.capType) && azVar.c().equals(frequencyCapResponseInfo.id)) {
                            gd.a(3, a, "Removed frequency capped ad unit -- adspace: " + next.p());
                            it.remove();
                        }
                    }
                }
            }
        }
    }

    private synchronized void e() {
        Iterator<ap> it = this.d.iterator();
        while (it.hasNext()) {
            ap next = it.next();
            if (!hp.a(next.a().expiration)) {
                gd.a(3, a, "Removed expired ad unit -- adspace: " + next.p());
                it.remove();
            }
        }
    }

    public synchronized void d() {
        final ArrayList arrayList = new ArrayList(this.d);
        fp.a().b(new hq() { // from class: com.flurry.sdk.x.3
            @Override // com.flurry.sdk.hq
            public void safeRun() {
                i.a().l().a(arrayList);
            }
        });
    }
}
