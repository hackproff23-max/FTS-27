package com.google.android.gms.drive.events;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.Handler;
import android.os.IBinder;
import android.os.Looper;
import android.os.Message;
import android.os.RemoteException;
import com.google.android.gms.common.GooglePlayServicesUtil;
import com.google.android.gms.drive.internal.OnEventResponse;
import com.google.android.gms.drive.internal.ag;
import com.google.android.gms.drive.internal.w;
import com.google.android.vending.expansion.downloader.Constants;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

/* JADX INFO: loaded from: classes.dex */
public abstract class DriveEventService extends Service implements ChangeListener, CompletionListener {
    public static final String ACTION_HANDLE_EVENT = "com.google.android.gms.drive.events.HANDLE_EVENT";
    private CountDownLatch Pg;
    a Ph;
    boolean Pi;
    int Pj;
    private final String mName;

    final class a extends Handler {
        a() {
        }

        /* JADX INFO: Access modifiers changed from: private */
        public Message b(OnEventResponse onEventResponse) {
            return obtainMessage(1, onEventResponse);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public Message iE() {
            return obtainMessage(2);
        }

        @Override // android.os.Handler
        public void handleMessage(Message msg) {
            w.m("DriveEventService", "handleMessage message type:" + msg.what);
            switch (msg.what) {
                case 1:
                    DriveEventService.this.a((OnEventResponse) msg.obj);
                    break;
                case 2:
                    getLooper().quit();
                    break;
                default:
                    w.o("DriveEventService", "Unexpected message type:" + msg.what);
                    break;
            }
        }
    }

    final class b extends ag.a {
        b() {
        }

        @Override // com.google.android.gms.drive.internal.ag
        public void c(OnEventResponse onEventResponse) throws RemoteException {
            synchronized (DriveEventService.this) {
                w.m("DriveEventService", "onEvent: " + onEventResponse);
                DriveEventService.this.iD();
                if (DriveEventService.this.Ph != null) {
                    DriveEventService.this.Ph.sendMessage(DriveEventService.this.Ph.b(onEventResponse));
                } else {
                    w.p("DriveEventService", "Receiving event before initialize is completed.");
                }
            }
        }
    }

    protected DriveEventService() {
        this("DriveEventService");
    }

    protected DriveEventService(String name) {
        this.Pi = false;
        this.Pj = -1;
        this.mName = name;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(OnEventResponse onEventResponse) {
        DriveEvent driveEventIQ = onEventResponse.iQ();
        w.m("DriveEventService", "handleEventMessage: " + driveEventIQ);
        try {
            switch (driveEventIQ.getType()) {
                case 1:
                    onChange((ChangeEvent) driveEventIQ);
                    break;
                case 2:
                    onCompletion((CompletionEvent) driveEventIQ);
                    break;
                default:
                    w.o(this.mName, "Unhandled event: " + driveEventIQ);
                    break;
            }
        } catch (Exception e) {
            w.a(this.mName, e, "Error handling event: " + driveEventIQ);
        }
    }

    private boolean bh(int i) {
        String[] packagesForUid = getPackageManager().getPackagesForUid(i);
        if (packagesForUid == null) {
            return false;
        }
        for (String str : packagesForUid) {
            if (GooglePlayServicesUtil.GOOGLE_PLAY_SERVICES_PACKAGE.equals(str)) {
                return true;
            }
        }
        return false;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void iD() throws SecurityException {
        int callingUid = getCallingUid();
        if (callingUid == this.Pj) {
            return;
        }
        if (!GooglePlayServicesUtil.b(getPackageManager(), GooglePlayServicesUtil.GOOGLE_PLAY_SERVICES_PACKAGE) || !bh(callingUid)) {
            throw new SecurityException("Caller is not GooglePlayServices");
        }
        this.Pj = callingUid;
    }

    protected int getCallingUid() {
        return Binder.getCallingUid();
    }

    /* JADX WARN: Type inference failed for: r1v3, types: [com.google.android.gms.drive.events.DriveEventService$1] */
    @Override // android.app.Service
    public final synchronized IBinder onBind(Intent intent) {
        IBinder iBinderAsBinder;
        if (ACTION_HANDLE_EVENT.equals(intent.getAction())) {
            if (this.Ph == null && !this.Pi) {
                this.Pi = true;
                final CountDownLatch countDownLatch = new CountDownLatch(1);
                this.Pg = new CountDownLatch(1);
                new Thread() { // from class: com.google.android.gms.drive.events.DriveEventService.1
                    @Override // java.lang.Thread, java.lang.Runnable
                    public void run() {
                        try {
                            Looper.prepare();
                            DriveEventService.this.Ph = DriveEventService.this.new a();
                            DriveEventService.this.Pi = false;
                            countDownLatch.countDown();
                            w.m("DriveEventService", "Bound and starting loop");
                            Looper.loop();
                            w.m("DriveEventService", "Finished loop");
                        } finally {
                            if (DriveEventService.this.Pg != null) {
                                DriveEventService.this.Pg.countDown();
                            }
                        }
                    }
                }.start();
                try {
                    if (!countDownLatch.await(Constants.ACTIVE_THREAD_WATCHDOG, TimeUnit.MILLISECONDS)) {
                        w.p("DriveEventService", "Failed to synchronously initialize event handler.");
                    }
                } catch (InterruptedException e) {
                    throw new RuntimeException("Unable to start event handler", e);
                }
            }
            iBinderAsBinder = new b().asBinder();
        } else {
            iBinderAsBinder = null;
        }
        return iBinderAsBinder;
    }

    @Override // com.google.android.gms.drive.events.ChangeListener
    public void onChange(ChangeEvent event) {
        w.o(this.mName, "Unhandled change event: " + event);
    }

    @Override // com.google.android.gms.drive.events.CompletionListener
    public void onCompletion(CompletionEvent event) {
        w.o(this.mName, "Unhandled completion event: " + event);
    }

    @Override // android.app.Service
    public synchronized void onDestroy() {
        w.m("DriveEventService", "onDestroy");
        if (this.Ph != null) {
            this.Ph.sendMessage(this.Ph.iE());
            this.Ph = null;
            try {
                if (!this.Pg.await(Constants.ACTIVE_THREAD_WATCHDOG, TimeUnit.MILLISECONDS)) {
                    w.o("DriveEventService", "Failed to synchronously quit event handler. Will quit itself");
                }
            } catch (InterruptedException e) {
            }
            this.Pg = null;
        }
        super.onDestroy();
    }

    @Override // android.app.Service
    public boolean onUnbind(Intent intent) {
        return true;
    }
}
