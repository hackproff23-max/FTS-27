package com.google.android.gms.ads.identifier;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.RemoteException;
import android.util.Log;
import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import com.google.android.gms.common.GooglePlayServicesRepairableException;
import com.google.android.gms.common.GooglePlayServicesUtil;
import com.google.android.gms.internal.jx;
import com.google.android.gms.internal.s;
import java.io.IOException;
import java.lang.ref.WeakReference;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

/* JADX INFO: loaded from: classes.dex */
public class AdvertisingIdClient {
    com.google.android.gms.common.a ln;
    s lo;
    boolean lp;
    Object lq;
    a lr;
    final long ls;
    private final Context mContext;

    public static final class Info {
        private final String lx;
        private final boolean ly;

        public Info(String advertisingId, boolean limitAdTrackingEnabled) {
            this.lx = advertisingId;
            this.ly = limitAdTrackingEnabled;
        }

        public String getId() {
            return this.lx;
        }

        public boolean isLimitAdTrackingEnabled() {
            return this.ly;
        }

        public String toString() {
            return "{" + this.lx + "}" + this.ly;
        }
    }

    static class a extends Thread {
        private WeakReference<AdvertisingIdClient> lt;
        private long lu;
        CountDownLatch lv = new CountDownLatch(1);
        boolean lw = false;

        public a(AdvertisingIdClient advertisingIdClient, long j) {
            this.lt = new WeakReference<>(advertisingIdClient);
            this.lu = j;
            start();
        }

        private void disconnect() {
            AdvertisingIdClient advertisingIdClient = this.lt.get();
            if (advertisingIdClient != null) {
                advertisingIdClient.finish();
                this.lw = true;
            }
        }

        public boolean aa() {
            return this.lw;
        }

        public void cancel() {
            this.lv.countDown();
        }

        @Override // java.lang.Thread, java.lang.Runnable
        public void run() {
            try {
                if (this.lv.await(this.lu, TimeUnit.MILLISECONDS)) {
                    return;
                }
                disconnect();
            } catch (InterruptedException e) {
                disconnect();
            }
        }
    }

    public AdvertisingIdClient(Context context) {
        this(context, 30000L);
    }

    public AdvertisingIdClient(Context context, long timeoutInMillis) {
        this.lq = new Object();
        jx.i(context);
        this.mContext = context;
        this.lp = false;
        this.ls = timeoutInMillis;
    }

    private void Z() {
        synchronized (this.lq) {
            if (this.lr != null) {
                this.lr.cancel();
                try {
                    this.lr.join();
                } catch (InterruptedException e) {
                }
            }
            if (this.ls > 0) {
                this.lr = new a(this, this.ls);
            }
        }
    }

    static s a(Context context, com.google.android.gms.common.a aVar) throws IOException {
        try {
            return s.a.b(aVar.gs());
        } catch (InterruptedException e) {
            throw new IOException("Interrupted exception");
        }
    }

    public static Info getAdvertisingIdInfo(Context context) throws GooglePlayServicesRepairableException, IllegalStateException, GooglePlayServicesNotAvailableException, IOException {
        AdvertisingIdClient advertisingIdClient = new AdvertisingIdClient(context, -1L);
        try {
            advertisingIdClient.b(false);
            return advertisingIdClient.getInfo();
        } finally {
            advertisingIdClient.finish();
        }
    }

    static com.google.android.gms.common.a h(Context context) throws GooglePlayServicesRepairableException, GooglePlayServicesNotAvailableException, IOException {
        try {
            context.getPackageManager().getPackageInfo(GooglePlayServicesUtil.GOOGLE_PLAY_STORE_PACKAGE, 0);
            try {
                GooglePlayServicesUtil.C(context);
                com.google.android.gms.common.a aVar = new com.google.android.gms.common.a();
                Intent intent = new Intent("com.google.android.gms.ads.identifier.service.START");
                intent.setPackage(GooglePlayServicesUtil.GOOGLE_PLAY_SERVICES_PACKAGE);
                if (context.bindService(intent, aVar, 1)) {
                    return aVar;
                }
                throw new IOException("Connection failure");
            } catch (GooglePlayServicesNotAvailableException e) {
                throw new IOException(e);
            }
        } catch (PackageManager.NameNotFoundException e2) {
            throw new GooglePlayServicesNotAvailableException(9);
        }
    }

    protected void b(boolean z) throws GooglePlayServicesRepairableException, IllegalStateException, GooglePlayServicesNotAvailableException, IOException {
        jx.aV("Calling this from your main thread can lead to deadlock");
        synchronized (this) {
            if (this.lp) {
                finish();
            }
            this.ln = h(this.mContext);
            this.lo = a(this.mContext, this.ln);
            this.lp = true;
            if (z) {
                Z();
            }
        }
    }

    protected void finalize() throws Throwable {
        finish();
        super.finalize();
    }

    public void finish() {
        jx.aV("Calling this from your main thread can lead to deadlock");
        synchronized (this) {
            if (this.mContext == null || this.ln == null) {
                return;
            }
            try {
                if (this.lp) {
                    this.mContext.unbindService(this.ln);
                }
            } catch (IllegalArgumentException e) {
                Log.i("AdvertisingIdClient", "AdvertisingIdClient unbindService failed.", e);
            }
            this.lp = false;
            this.lo = null;
            this.ln = null;
        }
    }

    public Info getInfo() throws IOException {
        Info info;
        jx.aV("Calling this from your main thread can lead to deadlock");
        synchronized (this) {
            if (this.lp) {
                jx.i(this.ln);
                jx.i(this.lo);
                info = new Info(this.lo.getId(), this.lo.c(true));
            } else {
                synchronized (this.lq) {
                    if (this.lr == null || !this.lr.aa()) {
                        throw new IOException("AdvertisingIdClient is not connected.");
                    }
                }
                try {
                    b(false);
                    if (!this.lp) {
                        throw new IOException("AdvertisingIdClient cannot reconnect.");
                    }
                    jx.i(this.ln);
                    jx.i(this.lo);
                    try {
                        info = new Info(this.lo.getId(), this.lo.c(true));
                    } catch (RemoteException e) {
                        Log.i("AdvertisingIdClient", "GMS remote exception ", e);
                        throw new IOException("Remote exception");
                    }
                } catch (Exception e2) {
                    throw new IOException("AdvertisingIdClient cannot reconnect.", e2);
                }
            }
            throw th;
        }
        Z();
        return info;
    }

    public void start() throws GooglePlayServicesRepairableException, IllegalStateException, GooglePlayServicesNotAvailableException, IOException {
        b(true);
    }
}
