package com.google.android.gms.analytics;

import android.content.Context;
import android.content.Intent;
import com.google.android.gms.common.GooglePlayServicesUtil;
import com.google.android.gms.internal.ha;
import com.google.android.gms.internal.ld;
import com.google.android.gms.internal.lf;
import com.google.android.vending.expansion.downloader.Constants;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.ConcurrentLinkedQueue;

/* JADX INFO: loaded from: classes.dex */
class w implements ak, com.google.android.gms.analytics.c.b, com.google.android.gms.analytics.c.InterfaceC0035c {
    private final Context mContext;
    private ld wb;
    private com.google.android.gms.analytics.d yU;
    private final f yV;
    private boolean yX;
    private volatile long zh;
    private volatile a zi;
    private volatile com.google.android.gms.analytics.b zj;
    private com.google.android.gms.analytics.d zk;
    private final GoogleAnalytics zl;
    private final Queue<d> zm;
    private volatile int zn;
    private volatile Timer zo;
    private volatile Timer zp;
    private volatile Timer zq;
    private boolean zr;
    private boolean zs;
    private boolean zt;
    private long zu;

    private enum a {
        CONNECTING,
        CONNECTED_SERVICE,
        CONNECTED_LOCAL,
        BLOCKED,
        PENDING_CONNECTION,
        PENDING_DISCONNECT,
        DISCONNECTED
    }

    private class b extends TimerTask {
        private b() {
        }

        @Override // java.util.TimerTask, java.lang.Runnable
        public void run() {
            if (w.this.zi != a.CONNECTED_SERVICE || !w.this.zm.isEmpty() || w.this.zh + w.this.zu >= w.this.wb.elapsedRealtime()) {
                w.this.zq.schedule(w.this.new b(), w.this.zu);
            } else {
                ae.V("Disconnecting due to inactivity");
                w.this.cJ();
            }
        }
    }

    private class c extends TimerTask {
        private c() {
        }

        @Override // java.util.TimerTask, java.lang.Runnable
        public void run() {
            if (w.this.zi == a.CONNECTING) {
                w.this.eE();
            }
        }
    }

    private static class d {
        private final Map<String, String> zF;
        private final long zG;
        private final String zH;
        private final List<ha> zI;

        public d(Map<String, String> map, long j, String str, List<ha> list) {
            this.zF = map;
            this.zG = j;
            this.zH = str;
            this.zI = list;
        }

        public Map<String, String> eH() {
            return this.zF;
        }

        public long eI() {
            return this.zG;
        }

        public List<ha> eJ() {
            return this.zI;
        }

        public String getPath() {
            return this.zH;
        }

        public String toString() {
            StringBuilder sb = new StringBuilder();
            sb.append("PATH: ");
            sb.append(this.zH);
            if (this.zF != null) {
                sb.append("  PARAMS: ");
                for (Map.Entry<String, String> entry : this.zF.entrySet()) {
                    sb.append(entry.getKey());
                    sb.append("=");
                    sb.append(entry.getValue());
                    sb.append(",  ");
                }
            }
            return sb.toString();
        }
    }

    private class e extends TimerTask {
        private e() {
        }

        @Override // java.util.TimerTask, java.lang.Runnable
        public void run() {
            w.this.eF();
        }
    }

    w(Context context, f fVar) {
        this(context, fVar, null, GoogleAnalytics.getInstance(context));
    }

    w(Context context, f fVar, com.google.android.gms.analytics.d dVar, GoogleAnalytics googleAnalytics) {
        this.zm = new ConcurrentLinkedQueue();
        this.zu = 300000L;
        this.zk = dVar;
        this.mContext = context;
        this.yV = fVar;
        this.zl = googleAnalytics;
        this.wb = lf.m6if();
        this.zn = 0;
        this.zi = a.DISCONNECTED;
    }

    private Timer a(Timer timer) {
        if (timer == null) {
            return null;
        }
        timer.cancel();
        return null;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public synchronized void cJ() {
        if (this.zj != null && this.zi == a.CONNECTED_SERVICE) {
            this.zi = a.PENDING_DISCONNECT;
            this.zj.disconnect();
        }
    }

    private void eA() {
        this.zo = a(this.zo);
        this.zp = a(this.zp);
        this.zq = a(this.zq);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    public synchronized void eC() {
        if (Thread.currentThread().equals(this.yV.getThread())) {
            if (this.zr) {
                dQ();
            }
            switch (this.zi) {
                case CONNECTED_LOCAL:
                    while (!this.zm.isEmpty()) {
                        d dVarPoll = this.zm.poll();
                        ae.V("Sending hit to store  " + dVarPoll);
                        this.yU.a(dVarPoll.eH(), dVarPoll.eI(), dVarPoll.getPath(), dVarPoll.eJ());
                    }
                    if (this.yX) {
                        eD();
                    }
                    break;
                case CONNECTED_SERVICE:
                    while (!this.zm.isEmpty()) {
                        d dVarPeek = this.zm.peek();
                        ae.V("Sending hit to service   " + dVarPeek);
                        if (this.zl.isDryRunEnabled()) {
                            ae.V("Dry run enabled. Hit not actually sent to service.");
                        } else {
                            this.zj.a(dVarPeek.eH(), dVarPeek.eI(), dVarPeek.getPath(), dVarPeek.eJ());
                        }
                        this.zm.poll();
                    }
                    this.zh = this.wb.elapsedRealtime();
                    break;
                case DISCONNECTED:
                    ae.V("Need to reconnect");
                    if (!this.zm.isEmpty()) {
                        eF();
                    }
                    break;
                case BLOCKED:
                    ae.V("Blocked. Dropping hits.");
                    this.zm.clear();
                    break;
            }
        } else {
            this.yV.dX().add(new Runnable() { // from class: com.google.android.gms.analytics.w.1
                @Override // java.lang.Runnable
                public void run() {
                    w.this.eC();
                }
            });
        }
    }

    private void eD() {
        this.yU.dispatch();
        this.yX = false;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public synchronized void eE() {
        if (this.zi != a.CONNECTED_LOCAL) {
            if (this.mContext == null || !GooglePlayServicesUtil.GOOGLE_PLAY_SERVICES_PACKAGE.equals(this.mContext.getPackageName())) {
                eA();
                ae.V("falling back to local store");
                if (this.zk != null) {
                    this.yU = this.zk;
                } else {
                    v vVarEu = v.eu();
                    vVarEu.a(this.mContext, this.yV);
                    this.yU = vVarEu.ex();
                }
                this.zi = a.CONNECTED_LOCAL;
                eC();
            } else {
                this.zi = a.BLOCKED;
                this.zj.disconnect();
                ae.W("Attempted to fall back to local store from service.");
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public synchronized void eF() {
        if (this.zt || this.zj == null || this.zi == a.CONNECTED_LOCAL) {
            ae.W("client not initialized.");
            eE();
        } else {
            try {
                this.zn++;
                a(this.zp);
                this.zi = a.CONNECTING;
                this.zp = new Timer("Failed Connect");
                this.zp.schedule(new c(), 3000L);
                ae.V("connecting to Analytics service");
                this.zj.connect();
            } catch (SecurityException e2) {
                ae.W("security exception on connectToService");
                eE();
            }
        }
    }

    private void eG() {
        this.zo = a(this.zo);
        this.zo = new Timer("Service Reconnect");
        this.zo.schedule(new e(), Constants.ACTIVE_THREAD_WATCHDOG);
    }

    @Override // com.google.android.gms.analytics.c.InterfaceC0035c
    public synchronized void a(int i, Intent intent) {
        this.zi = a.PENDING_CONNECTION;
        if (this.zn < 2) {
            ae.W("Service unavailable (code=" + i + "), will retry.");
            eG();
        } else {
            ae.W("Service unavailable (code=" + i + "), using local store.");
            eE();
        }
    }

    @Override // com.google.android.gms.analytics.ak
    public void b(Map<String, String> map, long j, String str, List<ha> list) {
        ae.V("putHit called");
        this.zm.add(new d(map, j, str, list));
        eC();
    }

    @Override // com.google.android.gms.analytics.ak
    public void dQ() {
        ae.V("clearHits called");
        this.zm.clear();
        switch (this.zi) {
            case CONNECTED_LOCAL:
                this.yU.l(0L);
                this.zr = false;
                break;
            case CONNECTED_SERVICE:
                this.zj.dQ();
                this.zr = false;
                break;
            default:
                this.zr = true;
                break;
        }
    }

    @Override // com.google.android.gms.analytics.ak
    public synchronized void dW() {
        if (!this.zt) {
            ae.V("setForceLocalDispatch called.");
            this.zt = true;
            switch (this.zi) {
                case CONNECTED_SERVICE:
                    cJ();
                    break;
                case CONNECTING:
                    this.zs = true;
                    break;
            }
        }
    }

    @Override // com.google.android.gms.analytics.ak
    public void dispatch() {
        switch (this.zi) {
            case CONNECTED_LOCAL:
                eD();
                break;
            case CONNECTED_SERVICE:
                break;
            default:
                this.yX = true;
                break;
        }
    }

    @Override // com.google.android.gms.analytics.ak
    public void eB() {
        if (this.zj != null) {
            return;
        }
        this.zj = new com.google.android.gms.analytics.c(this.mContext, this, this);
        eF();
    }

    @Override // com.google.android.gms.analytics.c.b
    public synchronized void onConnected() {
        this.zp = a(this.zp);
        this.zn = 0;
        ae.V("Connected to service");
        this.zi = a.CONNECTED_SERVICE;
        if (this.zs) {
            cJ();
            this.zs = false;
        } else {
            eC();
            this.zq = a(this.zq);
            this.zq = new Timer("disconnect check");
            this.zq.schedule(new b(), this.zu);
        }
    }

    @Override // com.google.android.gms.analytics.c.b
    public synchronized void onDisconnected() {
        if (this.zi == a.BLOCKED) {
            ae.V("Service blocked.");
            eA();
        } else if (this.zi == a.PENDING_DISCONNECT) {
            ae.V("Disconnected from service");
            eA();
            this.zi = a.DISCONNECTED;
        } else {
            ae.V("Unexpected disconnect.");
            this.zi = a.PENDING_CONNECTION;
            if (this.zn < 2) {
                eG();
            } else {
                eE();
            }
        }
    }
}
