package com.facebook.ads.a;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Handler;
import com.facebook.ads.AdError;
import com.facebook.ads.AdSettings;
import com.facebook.ads.AdSize;
import com.facebook.ads.InterstitialAd;
import com.firsttouchgames.util.IabHelper;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/* JADX INFO: loaded from: classes.dex */
public class k {
    private static Map a = new ConcurrentHashMap();
    private static Map b = new ConcurrentHashMap();
    private final Context c;
    private final String d;
    private final AdSize e;
    private final n f;
    private final j.b g;
    private final o h;
    private final int i;
    private boolean j;
    private final b k;
    private boolean l;
    private final Handler p;
    private final Runnable q;
    private AsyncTask r;
    private int m = 30000;
    private boolean n = false;
    private volatile boolean o = false;
    private int s = 8;

    enum a {
        BANNER,
        INTERSTITIAL,
        NATIVE
    }

    private class b extends BroadcastReceiver {
        private b() {
        }

        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            if ("android.intent.action.SCREEN_OFF".equals(action)) {
                k.this.b(intent.getAction());
            } else if ("android.intent.action.SCREEN_ON".equals(action) && k.this.s == 0) {
                k.this.a(intent.getAction());
            }
        }
    }

    public k(Context context, String str, AdSize adSize, n nVar, boolean z, o oVar, int i, j.b bVar) {
        if (bVar == null) {
            throw new IllegalArgumentException("adViewRequestCallback");
        }
        this.c = context;
        this.d = str;
        this.e = adSize;
        this.f = nVar;
        this.j = z;
        this.h = oVar;
        this.i = i;
        this.g = bVar;
        this.k = new b();
        this.p = new Handler();
        this.q = new Runnable() { // from class: com.facebook.ads.a.k.1
            @Override // java.lang.Runnable
            public void run() {
                k.this.o = false;
                k.this.b();
            }
        };
        d();
    }

    private void a(long j) {
        a.put(h(), Long.valueOf(j));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void b(int i) {
        b.put(h(), Integer.valueOf(i));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public synchronized void b(String str) {
        if (this.o) {
            this.p.removeCallbacks(this.q);
            this.o = false;
        }
    }

    private void d() {
        if (this.j) {
            IntentFilter intentFilter = new IntentFilter("android.intent.action.SCREEN_ON");
            intentFilter.addAction("android.intent.action.SCREEN_OFF");
            this.c.registerReceiver(this.k, intentFilter);
            this.l = true;
        }
    }

    private void e() {
        if (this.l) {
            try {
                this.c.unregisterReceiver(this.k);
            } catch (Exception e) {
                c.a(com.facebook.ads.a.b.a(e));
            }
            this.l = false;
        }
    }

    private int f() {
        Integer numJ = j();
        if (numJ != null) {
            return numJ.intValue();
        }
        if (this.j) {
            return 20000;
        }
        return IabHelper.IABHELPER_ERROR_BASE;
    }

    private a g() {
        if (o.NATIVE == this.h) {
            return a.NATIVE;
        }
        return AdSize.INTERSTITIAL == this.e ? a.INTERSTITIAL : a.BANNER;
    }

    private String h() {
        return this.d + InterstitialAd.SEPARATOR + g().toString();
    }

    private Long i() {
        return (Long) a.get(h());
    }

    private Integer j() {
        return (Integer) b.get(h());
    }

    private boolean k() {
        if (this.c.checkCallingOrSelfPermission("android.permission.ACCESS_NETWORK_STATE") != 0) {
            return true;
        }
        NetworkInfo activeNetworkInfo = ((ConnectivityManager) this.c.getSystemService("connectivity")).getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }

    public void a() {
        this.j = false;
    }

    public void a(int i) {
        this.s = i;
        if (i != 0) {
            b("onWindowVisibilityChanged");
        } else if (this.n) {
            a("onWindowVisibilityChanged");
        }
    }

    public synchronized void a(String str) {
        if (this.j && this.m > 0 && !this.o) {
            this.p.postDelayed(this.q, this.m);
            this.o = true;
        }
    }

    public synchronized void b() {
        long jCurrentTimeMillis = System.currentTimeMillis();
        Long lI = i();
        if (lI == null || jCurrentTimeMillis >= lI.longValue() + ((long) f())) {
            if (this.o) {
                b((String) null);
            }
            if (this.r != null && this.r.getStatus() != AsyncTask.Status.FINISHED) {
                this.r.cancel(true);
            }
            if (k()) {
                a(jCurrentTimeMillis);
                this.r = new j(this.c, this.d, this.e, this.f, this.h, this.i, AdSettings.isTestMode(this.c), new j.b() { // from class: com.facebook.ads.a.k.2
                    @Override // com.facebook.ads.a.j.b
                    public void a(AdError adError) {
                        k.this.g.a(adError);
                        k.this.n = true;
                        k.this.a("onError");
                    }

                    @Override // com.facebook.ads.a.j.b
                    public void a(l lVar) {
                        k.this.m = lVar.a();
                        k.this.b(lVar.b());
                        k.this.g.a(lVar);
                        k.this.n = true;
                    }
                }).a();
            } else {
                this.m = 30000;
                this.g.a(AdError.NETWORK_ERROR);
                a("no network connection");
            }
        } else {
            this.g.a(AdError.LOAD_TOO_FREQUENTLY);
        }
    }

    public void c() {
        e();
        b("destroy");
    }
}
