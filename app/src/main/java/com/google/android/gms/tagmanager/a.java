package com.google.android.gms.tagmanager;

import android.content.Context;
import android.os.Process;
import com.google.android.gms.ads.identifier.AdvertisingIdClient;
import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import com.google.android.gms.common.GooglePlayServicesRepairableException;
import com.google.android.gms.internal.ld;
import com.google.android.gms.internal.lf;
import java.io.IOException;

/* JADX INFO: loaded from: classes.dex */
class a {
    private static a aqa;
    private static Object xO = new Object();
    private volatile long apW;
    private volatile long apX;
    private volatile long apY;
    private InterfaceC0176a apZ;
    private volatile boolean mClosed;
    private final Context mContext;
    private final ld wb;
    private final Thread wu;
    private volatile AdvertisingIdClient.Info xQ;

    /* JADX INFO: renamed from: com.google.android.gms.tagmanager.a$a, reason: collision with other inner class name */
    public interface InterfaceC0176a {
        AdvertisingIdClient.Info pd();
    }

    private a(Context context) {
        this(context, null, lf.m6if());
    }

    a(Context context, InterfaceC0176a interfaceC0176a, ld ldVar) {
        this.apW = 900000L;
        this.apX = 30000L;
        this.mClosed = false;
        this.apZ = new InterfaceC0176a() { // from class: com.google.android.gms.tagmanager.a.1
            @Override // com.google.android.gms.tagmanager.a.InterfaceC0176a
            public AdvertisingIdClient.Info pd() {
                try {
                    return AdvertisingIdClient.getAdvertisingIdInfo(a.this.mContext);
                } catch (GooglePlayServicesNotAvailableException e) {
                    bh.W("GooglePlayServicesNotAvailableException getting Advertising Id Info");
                    return null;
                } catch (GooglePlayServicesRepairableException e2) {
                    bh.W("GooglePlayServicesRepairableException getting Advertising Id Info");
                    return null;
                } catch (IOException e3) {
                    bh.W("IOException getting Ad Id Info");
                    return null;
                } catch (IllegalStateException e4) {
                    bh.W("IllegalStateException getting Advertising Id Info");
                    return null;
                } catch (Exception e5) {
                    bh.W("Unknown exception. Could not get the Advertising Id Info.");
                    return null;
                }
            }
        };
        this.wb = ldVar;
        if (context != null) {
            this.mContext = context.getApplicationContext();
        } else {
            this.mContext = context;
        }
        if (interfaceC0176a != null) {
            this.apZ = interfaceC0176a;
        }
        this.wu = new Thread(new Runnable() { // from class: com.google.android.gms.tagmanager.a.2
            @Override // java.lang.Runnable
            public void run() {
                a.this.pb();
            }
        });
    }

    static a W(Context context) {
        if (aqa == null) {
            synchronized (xO) {
                if (aqa == null) {
                    aqa = new a(context);
                    aqa.start();
                }
            }
        }
        return aqa;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void pb() {
        Process.setThreadPriority(10);
        while (!this.mClosed) {
            try {
                this.xQ = this.apZ.pd();
                Thread.sleep(this.apW);
            } catch (InterruptedException e) {
                bh.U("sleep interrupted in AdvertiserDataPoller thread; continuing");
            }
        }
    }

    private void pc() {
        if (this.wb.currentTimeMillis() - this.apY < this.apX) {
            return;
        }
        interrupt();
        this.apY = this.wb.currentTimeMillis();
    }

    void interrupt() {
        this.wu.interrupt();
    }

    public boolean isLimitAdTrackingEnabled() {
        pc();
        if (this.xQ == null) {
            return true;
        }
        return this.xQ.isLimitAdTrackingEnabled();
    }

    public String pa() {
        pc();
        if (this.xQ == null) {
            return null;
        }
        return this.xQ.getId();
    }

    void start() {
        this.wu.start();
    }
}
