package com.jirbo.adcolony;

import android.os.Handler;
import android.os.Message;
import com.google.android.vending.expansion.downloader.Constants;

/* JADX INFO: loaded from: classes.dex */
class p implements Runnable {
    public static final int a = 5;
    public static final int b = 10;
    static String c = "MONITOR_MUTEX";
    static volatile p d;
    static volatile long e;

    p() {
    }

    static void a() {
        synchronized (c) {
            e = System.currentTimeMillis();
            if (d == null) {
                com.jirbo.adcolony.a.b("Creating ADC Monitor singleton.");
                d = new p();
                new Thread(d).start();
            }
        }
    }

    @Override // java.lang.Runnable
    public void run() {
        long j;
        com.jirbo.adcolony.a.a(com.jirbo.adcolony.a.n);
        l.a.b((Object) "ADC Monitor Started.");
        com.jirbo.adcolony.a.l.b();
        boolean z = false;
        while (!AdColony.activity().isFinishing()) {
            long jCurrentTimeMillis = System.currentTimeMillis();
            com.jirbo.adcolony.a.r = false;
            com.jirbo.adcolony.a.l.g();
            if (com.jirbo.adcolony.a.r) {
                j = 50;
            } else {
                j = z ? 2000 : 250;
            }
            int iCurrentTimeMillis = (int) ((System.currentTimeMillis() - e) / 1000);
            com.jirbo.adcolony.a.l.g();
            if (z) {
                if (iCurrentTimeMillis >= 10) {
                    break;
                }
                if (iCurrentTimeMillis < 5) {
                    com.jirbo.adcolony.a.l.b();
                    com.jirbo.adcolony.a.b("AdColony is active.");
                    z = false;
                }
            } else if (iCurrentTimeMillis >= 5) {
                com.jirbo.adcolony.a.b("AdColony is idle.");
                com.jirbo.adcolony.a.l.c();
                z = true;
            }
            a(j);
            long jCurrentTimeMillis2 = System.currentTimeMillis();
            if (jCurrentTimeMillis2 - jCurrentTimeMillis <= 3000 && jCurrentTimeMillis2 - jCurrentTimeMillis > 0) {
                v vVar = com.jirbo.adcolony.a.l.e;
                vVar.h = ((jCurrentTimeMillis2 - jCurrentTimeMillis) / 1000.0d) + vVar.h;
            }
        }
        synchronized (c) {
            d = null;
        }
        if (!z) {
            com.jirbo.adcolony.a.l.c();
        }
        if (AdColony.activity().isFinishing()) {
            com.jirbo.adcolony.a.s = true;
            a(Constants.ACTIVE_THREAD_WATCHDOG);
            if (com.jirbo.adcolony.a.s) {
                l.c.b((Object) "ADC.finishing, controller on_stop");
                com.jirbo.adcolony.a.l.d();
                aa.a();
            }
        }
        System.out.println("Exiting monitor");
    }

    void a(long j) {
        try {
            Thread.sleep(j);
        } catch (InterruptedException e2) {
        }
    }

    static class a extends Handler {
        a() {
            sendMessageDelayed(obtainMessage(), 1000L);
        }

        @Override // android.os.Handler
        public void handleMessage(Message m) {
            if (com.jirbo.adcolony.a.b().isFinishing()) {
                com.jirbo.adcolony.a.b("Monitor pinger exiting.");
                return;
            }
            if (com.jirbo.adcolony.a.b().hasWindowFocus()) {
                p.a();
            }
            sendMessageDelayed(obtainMessage(), 1000L);
        }
    }
}
