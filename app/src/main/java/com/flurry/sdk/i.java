package com.flurry.sdk;

import android.content.Context;
import android.support.v4.media.session.PlaybackStateCompat;
import com.flurry.android.impl.ads.protocol.v13.Configuration;
import java.io.File;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/* JADX INFO: loaded from: classes.dex */
public class i implements gg {
    private static final String a = i.class.getSimpleName();
    private final fy<fs> b = new fy<fs>() { // from class: com.flurry.sdk.i.1
        @Override // com.flurry.sdk.fy
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public void notify(fs fsVar) {
            if (fs.a.kPaused.equals(fsVar.b)) {
                i.this.f.b(fsVar.a);
                i.this.g.a(fsVar.a);
            } else if (fs.a.kResumed.equals(fsVar.b)) {
                i.this.f.c(fsVar.a);
                i.this.g.b(fsVar.a);
            } else if (fs.a.kDestroyed.equals(fsVar.b)) {
                i.this.f.d(fsVar.a);
                i.this.g.c(fsVar.a);
            }
        }
    };
    private final fy<cg> c = new fy<cg>() { // from class: com.flurry.sdk.i.2
        @Override // com.flurry.sdk.fy
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public void notify(cg cgVar) {
            synchronized (i.this) {
                if (i.this.r == null) {
                    i.this.r = cgVar.a;
                    i.this.a(((long) i.this.r.cacheSizeMb) * PlaybackStateCompat.ACTION_PLAY_FROM_MEDIA_ID * 1204, ((long) i.this.r.maxAssetSizeKb) * PlaybackStateCompat.ACTION_PLAY_FROM_MEDIA_ID);
                    cz.a(i.this.r.maxBitRateKbps);
                    i.this.h.a(i.this.r.sdkAssetUrl);
                    i.this.h.c();
                }
            }
        }
    };
    private n d;
    private y e;
    private p f;
    private v g;
    private k h;
    private cf i;
    private ce j;
    private m k;
    private ba l;
    private aa m;
    private File n;
    private File o;
    private fw<List<az>> p;
    private fw<List<ae>> q;
    private Configuration r;

    public static synchronized i a() {
        return (i) fp.a().a(i.class);
    }

    @Override // com.flurry.sdk.gg
    public void a(Context context) {
        hb.a((Class<? extends hf>) cm.class);
        this.d = new n();
        this.e = new y();
        this.f = new p();
        this.g = new v();
        this.h = new k();
        this.i = new cf();
        this.j = new ce();
        this.k = new m();
        this.l = new ba();
        this.m = new aa();
        this.r = null;
        fz.a().a("com.flurry.android.sdk.ActivityLifecycleEvent", this.b);
        fz.a().a("com.flurry.android.sdk.AdConfigurationEvent", this.c);
        this.n = context.getFileStreamPath(w());
        this.o = context.getFileStreamPath(x());
        this.p = new fw<>(context.getFileStreamPath(y()), ".yflurryfreqcap.", 2, new ha<List<az>>() { // from class: com.flurry.sdk.i.3
            @Override // com.flurry.sdk.ha
            public gx<List<az>> a(int i) {
                return new gw(new az.a());
            }
        });
        this.q = new fw<>(context.getFileStreamPath(z()), ".yflurrycachedasset", 1, new ha<List<ae>>() { // from class: com.flurry.sdk.i.4
            @Override // com.flurry.sdk.ha
            public gx<List<ae>> a(int i) {
                return new gw(new ae.a());
            }
        });
        fp.a().b(new hq() { // from class: com.flurry.sdk.i.5
            @Override // com.flurry.sdk.hq
            public void safeRun() {
                i.this.A();
            }
        });
    }

    @Override // com.flurry.sdk.gg
    public void b() {
        fz.a().a(this.b);
        fz.a().a(this.c);
        if (this.e != null) {
            this.e.a();
            this.e = null;
        }
        this.f = null;
        this.g = null;
        this.h = null;
        this.i = null;
        if (this.d != null) {
            this.d.a();
            this.d = null;
        }
        if (this.j != null) {
            this.j.b();
            this.j = null;
        }
        this.k = null;
        this.r = null;
        hb.b((Class<? extends hf>) cm.class);
    }

    public y c() {
        return this.e;
    }

    public p d() {
        return this.f;
    }

    public v e() {
        return this.g;
    }

    public k f() {
        return this.h;
    }

    public cf g() {
        return this.i;
    }

    public n h() {
        return this.d;
    }

    public ce i() {
        return this.j;
    }

    public m j() {
        return this.k;
    }

    public ba k() {
        return this.l;
    }

    public aa l() {
        return this.m;
    }

    public Configuration m() {
        return this.r;
    }

    public bc n() {
        cm cmVarV = v();
        if (cmVarV != null) {
            return cmVarV.b();
        }
        return null;
    }

    public g o() {
        cm cmVarV = v();
        if (cmVarV != null) {
            return cmVarV.c();
        }
        return null;
    }

    public ef p() {
        cm cmVarV = v();
        if (cmVarV != null) {
            return cmVarV.d();
        }
        return null;
    }

    public eo q() {
        cm cmVarV = v();
        if (cmVarV != null) {
            return cmVarV.e();
        }
        return null;
    }

    public void a(String str, aw awVar, boolean z, Map<String, String> map) {
        cm cmVarV = v();
        if (cmVarV != null) {
            cmVarV.a(str, awVar, z, map);
        }
    }

    public void r() {
        cm cmVarV = v();
        if (cmVarV != null) {
            cmVarV.h();
        }
    }

    public String s() {
        cm cmVarV = v();
        if (cmVarV != null) {
            return cmVarV.i();
        }
        return null;
    }

    private cm v() {
        return a(hc.a().e());
    }

    private cm a(hb hbVar) {
        if (hbVar == null) {
            return null;
        }
        return (cm) hbVar.c(cm.class);
    }

    public at a(String str) {
        cm cmVarV = v();
        if (cmVarV != null) {
            return cmVarV.a(str);
        }
        return null;
    }

    private String w() {
        return ".flurryfreqcap." + Integer.toString(fp.a().d().hashCode(), 16);
    }

    private String x() {
        return ".flurrycachedasset." + Integer.toString(fp.a().d().hashCode(), 16);
    }

    private String y() {
        return ".yflurryfreqcap." + Long.toString(hp.i(fp.a().d()), 16);
    }

    private String z() {
        return ".yflurrycachedasset" + Long.toString(hp.i(fp.a().d()), 16);
    }

    public synchronized void t() {
        gd.a(4, a, "Saving FreqCap data.");
        this.l.b();
        this.p.a(this.l.a());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public synchronized void A() {
        gd.a(4, a, "Loading FreqCap data.");
        List<az> listA = this.p.a();
        if (listA != null) {
            Iterator<az> it = listA.iterator();
            while (it.hasNext()) {
                this.l.a(it.next());
            }
        } else if (this.n.exists()) {
            gd.a(4, a, "Legacy FreqCap data found, converting.");
            List<az> listA2 = l.a(this.n);
            if (listA2 != null) {
                Iterator<az> it2 = listA2.iterator();
                while (it2.hasNext()) {
                    this.l.a(it2.next());
                }
            }
            this.l.b();
            this.n.delete();
            t();
        }
        this.l.b();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public synchronized void a(long j, long j2) {
        if (!this.m.a()) {
            gd.a(3, a, "Precaching: initing from FlurryAdModule");
            this.m.a(j, j2);
            this.m.e();
            fp.a().b(new hq() { // from class: com.flurry.sdk.i.6
                @Override // com.flurry.sdk.hq
                public void safeRun() {
                    i.this.B();
                }
            });
        }
    }

    public synchronized void u() {
        if (this.m.a()) {
            gd.a(4, a, "Saving CachedAsset data.");
            this.q.a(this.m.d());
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public synchronized void B() {
        if (this.m.a()) {
            gd.a(4, a, "Loading CachedAsset data.");
            List<ae> listA = this.q.a();
            if (listA != null) {
                Iterator<ae> it = listA.iterator();
                while (it.hasNext()) {
                    this.m.a(it.next());
                }
            } else if (this.o.exists()) {
                gd.a(4, a, "Legacy CachedAsset data found, deleting.");
                this.o.delete();
            }
        }
    }
}
