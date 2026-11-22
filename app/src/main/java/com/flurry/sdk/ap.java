package com.flurry.sdk;

import com.flurry.android.impl.ads.protocol.v13.AdFrame;
import com.flurry.android.impl.ads.protocol.v13.AdUnit;
import com.flurry.android.impl.ads.protocol.v13.FrequencyCapResponseInfo;
import com.flurry.android.impl.ads.protocol.v13.FrequencyCapType;
import com.flurry.android.impl.ads.protocol.v13.NativeAdInfo;
import com.flurry.android.impl.ads.protocol.v13.NativeAsset;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/* JADX INFO: loaded from: classes.dex */
public final class ap implements Comparable<ap> {
    private static int a;
    private final int b;
    private final AdUnit c;
    private int g;
    private String j;
    private final List<aq> d = new ArrayList();
    private final Map<String, ar> e = new HashMap();
    private final LinkedList<ek> f = new LinkedList<>();
    private boolean h = false;
    private boolean i = false;

    public ap(AdUnit adUnit) {
        int i = 0;
        int i2 = a + 1;
        a = i2;
        this.b = i2;
        this.c = adUnit;
        while (true) {
            int i3 = i;
            if (i3 < adUnit.adFrames.size()) {
                this.d.add(new aq());
                String str = adUnit.adFrames.get(i3).adGuid;
                this.e.put(str, new ar(str));
                i = i3 + 1;
            } else {
                return;
            }
        }
    }

    @Override // java.lang.Comparable
    /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
    public int compareTo(ap apVar) {
        if (apVar == null) {
            throw new NullPointerException("another cannot be null");
        }
        if (this.b > apVar.b) {
            return 1;
        }
        if (this.b < apVar.b) {
            return -1;
        }
        return 0;
    }

    public AdUnit a() {
        return this.c;
    }

    public at b() {
        return a(e());
    }

    public at a(String str) {
        return this.e.get(str).a();
    }

    public int c() {
        return this.g;
    }

    public void a(int i) {
        this.g = i;
    }

    public AdFrame b(int i) {
        List<AdFrame> list = this.c.adFrames;
        if (list == null || list.size() < i) {
            return null;
        }
        return list.get(i);
    }

    public ax d() {
        return c(c());
    }

    public ax c(int i) {
        for (ax axVar : ax.values()) {
            if (axVar.toString().equals(h(i))) {
                return axVar;
            }
        }
        return ax.UNKNOWN;
    }

    private String h(int i) {
        AdFrame adFrameB = b(i);
        if (adFrameB != null) {
            return adFrameB.adSpaceLayout.format;
        }
        return null;
    }

    public String e() {
        return this.c.adFrames.get(this.g).adGuid;
    }

    public FrequencyCapResponseInfo f() {
        List<FrequencyCapResponseInfo> list = this.c.frequencyCapResponseInfoList;
        if (list == null) {
            return null;
        }
        for (FrequencyCapResponseInfo frequencyCapResponseInfo : list) {
            if (FrequencyCapType.STREAM.equals(frequencyCapResponseInfo.capType)) {
                return frequencyCapResponseInfo;
            }
        }
        return null;
    }

    public cy g() {
        return d(this.g);
    }

    public cy d(int i) {
        if (i < 0 || i > this.d.size()) {
            return null;
        }
        return this.d.get(i).a();
    }

    public void a(int i, cy cyVar) {
        if (i >= 0 && i <= this.d.size()) {
            this.d.get(i).a(cyVar);
        }
    }

    public NativeAdInfo h() {
        return this.c.nativeAdInfo;
    }

    public List<NativeAsset> i() {
        return this.c.nativeAdInfo != null ? this.c.nativeAdInfo.assets : Collections.emptyList();
    }

    public AdFrame j() {
        if (this.c != null) {
            return b(this.g);
        }
        return null;
    }

    public ax k() {
        return this.c != null ? c(this.g) : ax.UNKNOWN;
    }

    public boolean l() {
        return k().equals(ax.TAKEOVER);
    }

    public dt m() {
        return e(this.g);
    }

    public dt e(int i) {
        if (i < 0 || i > this.d.size()) {
            return null;
        }
        return this.d.get(i).b();
    }

    public void a(dt dtVar) {
        a(this.g, dtVar);
    }

    public void a(int i, dt dtVar) {
        if (i >= 0 && i <= this.d.size()) {
            this.d.get(i).a(dtVar);
        }
    }

    public List<String> f(int i) {
        return (i < 0 || i > this.d.size()) ? Collections.emptyList() : this.d.get(i).c();
    }

    public void a(int i, List<String> list) {
        if (i >= 0 && i <= this.d.size()) {
            this.d.get(i).a(list);
        }
    }

    public boolean n() {
        List<AdFrame> list = this.c.adFrames;
        for (int i = 0; i < list.size(); i++) {
            ag agVarA = ag.a(list.get(i).cachingEnum);
            if ((ag.CACHE_ONLY.equals(agVarA) || ag.CACHE_OR_STREAM.equals(agVarA)) && g(i)) {
                return true;
            }
        }
        return false;
    }

    public boolean o() {
        List<AdFrame> list = this.c.adFrames;
        for (int i = 0; i < list.size(); i++) {
            if (ag.CACHE_ONLY.equals(ag.a(list.get(i).cachingEnum)) && g(i)) {
                return true;
            }
        }
        return false;
    }

    public boolean g(int i) {
        return !f(i).isEmpty();
    }

    public String p() {
        return this.c.adSpace;
    }

    public boolean q() {
        return this.h;
    }

    public void a(boolean z) {
        this.h = z;
    }

    public boolean r() {
        return this.i;
    }

    public void b(boolean z) {
        this.i = z;
    }

    public synchronized ek s() {
        ek ekVarPop;
        synchronized (this.f) {
            ekVarPop = this.f.size() > 0 ? this.f.pop() : null;
        }
        return ekVarPop;
    }

    public synchronized ek t() {
        ek ekVarPeek;
        synchronized (this.f) {
            ekVarPeek = this.f.size() > 0 ? this.f.peek() : null;
        }
        return ekVarPeek;
    }

    public void u() {
        synchronized (this.f) {
            this.f.clear();
        }
        a(0);
    }

    public void a(ek ekVar) {
        synchronized (this.f) {
            if (b(ekVar)) {
                this.f.push(ekVar);
            }
        }
    }

    private boolean b(ek ekVar) {
        ek ekVarPeek;
        return this.f.size() <= 0 || (ekVarPeek = this.f.peek()) == null || !ekVarPeek.equals(ekVar);
    }

    public void b(String str) {
        this.j = str;
    }

    public String v() {
        return this.j;
    }
}
