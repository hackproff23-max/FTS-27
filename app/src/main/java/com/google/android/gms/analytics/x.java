package com.google.android.gms.analytics;

import android.content.Context;
import android.os.Process;
import android.text.TextUtils;
import com.facebook.appevents.AppEventsConstants;
import com.google.android.gms.internal.ha;
import com.google.android.vending.expansion.downloader.Constants;
import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/* JADX INFO: loaded from: classes.dex */
class x extends Thread implements f {
    private static x zM;
    private volatile boolean mClosed;
    private final Context mContext;
    private final LinkedBlockingQueue<Runnable> zJ;
    private volatile boolean zK;
    private volatile String zL;
    private volatile ak zN;
    private final Lock zO;
    private final List<ha> zP;

    private class a implements Runnable {
        private a() {
        }

        @Override // java.lang.Runnable
        public void run() {
            x.this.zN.dQ();
        }
    }

    private class b implements Runnable {
        private b() {
        }

        @Override // java.lang.Runnable
        public void run() {
            x.this.zN.dispatch();
        }
    }

    private class c implements Runnable {
        private c() {
        }

        @Override // java.lang.Runnable
        public void run() {
            x.this.zN.dW();
        }
    }

    private class d implements Runnable {
        private final Map<String, String> zR;

        d(Map<String, String> map) {
            this.zR = new HashMap(map);
            String str = map.get("&ht");
            if (str != null) {
                try {
                    Long.valueOf(str);
                } catch (NumberFormatException e) {
                    str = null;
                }
            }
            if (str == null) {
                this.zR.put("&ht", Long.toString(System.currentTimeMillis()));
            }
        }

        private String v(Map<String, String> map) {
            return (!map.containsKey("useSecure") || an.f(map.get("useSecure"), true)) ? "https:" : "http:";
        }

        private void w(Map<String, String> map) {
            q qVarW = com.google.android.gms.analytics.a.w(x.this.mContext);
            an.a(map, "&adid", qVarW);
            an.a(map, "&ate", qVarW);
        }

        private void x(Map<String, String> map) {
            g gVarDZ = g.dZ();
            an.a(map, "&an", gVarDZ);
            an.a(map, "&av", gVarDZ);
            an.a(map, "&aid", gVarDZ);
            an.a(map, "&aiid", gVarDZ);
            map.put("&v", AppEventsConstants.EVENT_PARAM_VALUE_YES);
        }

        private boolean y(Map<String, String> map) {
            if (map.get("&sf") == null) {
                return false;
            }
            double dA = an.a(map.get("&sf"), 100.0d);
            if (dA < 100.0d && x.ah(map.get("&cid")) % 10000 >= dA * 100.0d) {
                ae.V(String.format("%s hit sampled out", map.get("&t") == null ? "unknown" : map.get("&t")));
                return true;
            }
            return false;
        }

        @Override // java.lang.Runnable
        public void run() {
            w(this.zR);
            if (TextUtils.isEmpty(this.zR.get("&cid"))) {
                this.zR.put("&cid", k.el().getValue("&cid"));
            }
            if (GoogleAnalytics.getInstance(x.this.mContext).getAppOptOut() || y(this.zR)) {
                return;
            }
            if (!TextUtils.isEmpty(x.this.zL)) {
                y.eK().D(true);
                this.zR.putAll(new HitBuilders.HitBuilder().setCampaignParamsFromUrl(x.this.zL).build());
                y.eK().D(false);
                x.this.zL = null;
            }
            x(this.zR);
            x.this.zN.b(ac.z(this.zR), Long.valueOf(this.zR.get("&ht")).longValue(), v(this.zR), x.this.zP);
        }
    }

    private x(Context context) {
        super("GAThread");
        this.zJ = new LinkedBlockingQueue<>();
        this.zK = false;
        this.mClosed = false;
        this.zP = new ArrayList();
        if (context != null) {
            this.mContext = context.getApplicationContext();
        } else {
            this.mContext = context;
        }
        this.zP.add(new ha("appendVersion", "&_v".substring(1), "ma4.0.4"));
        this.zO = new ReentrantLock();
        start();
    }

    static x A(Context context) {
        if (zM == null) {
            zM = new x(context);
        }
        return zM;
    }

    static String B(Context context) {
        String str = null;
        try {
            FileInputStream fileInputStreamOpenFileInput = context.openFileInput("gaInstallData");
            byte[] bArr = new byte[8192];
            int i = fileInputStreamOpenFileInput.read(bArr, 0, 8192);
            if (fileInputStreamOpenFileInput.available() > 0) {
                ae.T("Too much campaign data, ignoring it.");
                fileInputStreamOpenFileInput.close();
                context.deleteFile("gaInstallData");
            } else {
                fileInputStreamOpenFileInput.close();
                context.deleteFile("gaInstallData");
                if (i <= 0) {
                    ae.W("Campaign file is empty.");
                } else {
                    String str2 = new String(bArr, 0, i);
                    ae.U("Campaign found: " + str2);
                    str = str2;
                }
            }
        } catch (FileNotFoundException e) {
            ae.U("No campaign data found.");
        } catch (IOException e2) {
            ae.T("Error reading campaign data.");
            context.deleteFile("gaInstallData");
        }
        return str;
    }

    static int ah(String str) {
        int i = 1;
        if (!TextUtils.isEmpty(str)) {
            i = 0;
            for (int length = str.length() - 1; length >= 0; length--) {
                char cCharAt = str.charAt(length);
                i = ((i << 6) & 268435455) + cCharAt + (cCharAt << 14);
                int i2 = 266338304 & i;
                if (i2 != 0) {
                    i ^= i2 >> 21;
                }
            }
        }
        return i;
    }

    private void b(Runnable runnable) {
        this.zJ.add(runnable);
    }

    private String g(Throwable th) {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        PrintStream printStream = new PrintStream(byteArrayOutputStream);
        th.printStackTrace(printStream);
        printStream.flush();
        return new String(byteArrayOutputStream.toByteArray());
    }

    @Override // com.google.android.gms.analytics.f
    public void dQ() {
        b(new a());
    }

    @Override // com.google.android.gms.analytics.f
    public void dW() {
        b(new c());
    }

    @Override // com.google.android.gms.analytics.f
    public LinkedBlockingQueue<Runnable> dX() {
        return this.zJ;
    }

    @Override // com.google.android.gms.analytics.f
    public void dY() {
        init();
        ArrayList arrayList = new ArrayList();
        this.zJ.drainTo(arrayList);
        this.zO.lock();
        try {
            this.zK = true;
            Iterator it = arrayList.iterator();
            while (it.hasNext()) {
                try {
                    ((Runnable) it.next()).run();
                } catch (Throwable th) {
                    ae.T("Error dispatching all events on exit, giving up: " + g(th));
                    this.zO.unlock();
                }
            }
            this.zO.unlock();
        } catch (Throwable th2) {
            this.zO.unlock();
            throw th2;
        }
    }

    @Override // com.google.android.gms.analytics.f
    public void dispatch() {
        b(new b());
    }

    @Override // com.google.android.gms.analytics.f
    public Thread getThread() {
        return this;
    }

    protected synchronized void init() {
        if (this.zN == null) {
            this.zN = new w(this.mContext, this);
            this.zN.eB();
        }
    }

    @Override // java.lang.Thread, java.lang.Runnable
    public void run() {
        Process.setThreadPriority(10);
        try {
            Thread.sleep(Constants.ACTIVE_THREAD_WATCHDOG);
        } catch (InterruptedException e) {
            ae.W("sleep interrupted in GAThread initialize");
        }
        try {
            init();
            this.zL = B(this.mContext);
            ae.V("Initialized GA Thread");
        } catch (Throwable th) {
            ae.T("Error initializing the GAThread: " + g(th));
            ae.T("Google Analytics will not start up.");
            this.zK = true;
        }
        while (!this.mClosed) {
            try {
                try {
                    Runnable runnableTake = this.zJ.take();
                    this.zO.lock();
                    try {
                        if (!this.zK) {
                            runnableTake.run();
                        }
                        this.zO.unlock();
                    } catch (Throwable th2) {
                        this.zO.unlock();
                        throw th2;
                    }
                } catch (InterruptedException e2) {
                    ae.U(e2.toString());
                }
            } catch (Throwable th3) {
                ae.T("Error on GAThread: " + g(th3));
                ae.T("Google Analytics is shutting down.");
                this.zK = true;
            }
        }
    }

    @Override // com.google.android.gms.analytics.f
    public void u(Map<String, String> map) {
        b(new d(map));
    }
}
