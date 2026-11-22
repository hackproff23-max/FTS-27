package com.google.android.gms.wearable;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.IBinder;
import android.util.Log;
import com.google.android.gms.common.GooglePlayServicesUtil;
import com.google.android.gms.common.data.DataHolder;
import com.google.android.gms.wearable.internal.ae;
import com.google.android.gms.wearable.internal.ai;
import com.google.android.gms.wearable.internal.al;

/* JADX INFO: loaded from: classes.dex */
public abstract class WearableListenerService extends Service implements DataApi.DataListener, MessageApi.MessageListener, NodeApi.NodeListener {
    public static final String BIND_LISTENER_INTENT_ACTION = "com.google.android.gms.wearable.BIND_LISTENER";
    private String CS;
    private IBinder Nk;
    private Handler axn;
    private boolean axp;
    private volatile int Pj = -1;
    private Object axo = new Object();

    private class a extends ae.a {
        private a() {
        }

        @Override // com.google.android.gms.wearable.internal.ae
        public void a(final ai aiVar) {
            if (Log.isLoggable("WearableLS", 3)) {
                Log.d("WearableLS", "onMessageReceived: " + aiVar);
            }
            WearableListenerService.this.rl();
            synchronized (WearableListenerService.this.axo) {
                if (WearableListenerService.this.axp) {
                    return;
                }
                WearableListenerService.this.axn.post(new Runnable() { // from class: com.google.android.gms.wearable.WearableListenerService.a.2
                    @Override // java.lang.Runnable
                    public void run() {
                        WearableListenerService.this.onMessageReceived(aiVar);
                    }
                });
            }
        }

        @Override // com.google.android.gms.wearable.internal.ae
        public void a(final al alVar) {
            if (Log.isLoggable("WearableLS", 3)) {
                Log.d("WearableLS", "onPeerConnected: " + WearableListenerService.this.CS + ": " + alVar);
            }
            WearableListenerService.this.rl();
            synchronized (WearableListenerService.this.axo) {
                if (WearableListenerService.this.axp) {
                    return;
                }
                WearableListenerService.this.axn.post(new Runnable() { // from class: com.google.android.gms.wearable.WearableListenerService.a.3
                    @Override // java.lang.Runnable
                    public void run() {
                        WearableListenerService.this.onPeerConnected(alVar);
                    }
                });
            }
        }

        @Override // com.google.android.gms.wearable.internal.ae
        public void aa(final DataHolder dataHolder) {
            if (Log.isLoggable("WearableLS", 3)) {
                Log.d("WearableLS", "onDataItemChanged: " + WearableListenerService.this.CS + ": " + dataHolder);
            }
            WearableListenerService.this.rl();
            synchronized (WearableListenerService.this.axo) {
                if (WearableListenerService.this.axp) {
                    dataHolder.close();
                } else {
                    WearableListenerService.this.axn.post(new Runnable() { // from class: com.google.android.gms.wearable.WearableListenerService.a.1
                        @Override // java.lang.Runnable
                        public void run() {
                            DataEventBuffer dataEventBuffer = new DataEventBuffer(dataHolder);
                            try {
                                WearableListenerService.this.onDataChanged(dataEventBuffer);
                            } finally {
                                dataEventBuffer.release();
                            }
                        }
                    });
                }
            }
        }

        @Override // com.google.android.gms.wearable.internal.ae
        public void b(final al alVar) {
            if (Log.isLoggable("WearableLS", 3)) {
                Log.d("WearableLS", "onPeerDisconnected: " + WearableListenerService.this.CS + ": " + alVar);
            }
            WearableListenerService.this.rl();
            synchronized (WearableListenerService.this.axo) {
                if (WearableListenerService.this.axp) {
                    return;
                }
                WearableListenerService.this.axn.post(new Runnable() { // from class: com.google.android.gms.wearable.WearableListenerService.a.4
                    @Override // java.lang.Runnable
                    public void run() {
                        WearableListenerService.this.onPeerDisconnected(alVar);
                    }
                });
            }
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
    public void rl() throws SecurityException {
        int callingUid = Binder.getCallingUid();
        if (callingUid == this.Pj) {
            return;
        }
        if (!GooglePlayServicesUtil.b(getPackageManager(), GooglePlayServicesUtil.GOOGLE_PLAY_SERVICES_PACKAGE) || !bh(callingUid)) {
            throw new SecurityException("Caller is not GooglePlayServices");
        }
        this.Pj = callingUid;
    }

    @Override // android.app.Service
    public final IBinder onBind(Intent intent) {
        if (BIND_LISTENER_INTENT_ACTION.equals(intent.getAction())) {
            return this.Nk;
        }
        return null;
    }

    @Override // android.app.Service
    public void onCreate() {
        super.onCreate();
        if (Log.isLoggable("WearableLS", 3)) {
            Log.d("WearableLS", "onCreate: " + getPackageName());
        }
        this.CS = getPackageName();
        HandlerThread handlerThread = new HandlerThread("WearableListenerService");
        handlerThread.start();
        this.axn = new Handler(handlerThread.getLooper());
        this.Nk = new a();
    }

    @Override // com.google.android.gms.wearable.DataApi.DataListener
    public void onDataChanged(DataEventBuffer dataEvents) {
    }

    @Override // android.app.Service
    public void onDestroy() {
        synchronized (this.axo) {
            this.axp = true;
            this.axn.getLooper().quit();
        }
        super.onDestroy();
    }

    @Override // com.google.android.gms.wearable.MessageApi.MessageListener
    public void onMessageReceived(MessageEvent messageEvent) {
    }

    @Override // com.google.android.gms.wearable.NodeApi.NodeListener
    public void onPeerConnected(Node peer) {
    }

    @Override // com.google.android.gms.wearable.NodeApi.NodeListener
    public void onPeerDisconnected(Node peer) {
    }
}
