package com.unity3d.ads.android.data;

import android.content.ComponentName;
import android.content.ServiceConnection;
import android.os.IBinder;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

/* JADX INFO: compiled from: UnityAdsAdvertisingId.java */
/* JADX INFO: loaded from: classes.dex */
final class b implements ServiceConnection {
    boolean a;
    final /* synthetic */ UnityAdsAdvertisingId b;
    private final BlockingQueue c;

    private b(UnityAdsAdvertisingId unityAdsAdvertisingId) {
        this.b = unityAdsAdvertisingId;
        this.a = false;
        this.c = new LinkedBlockingQueue();
    }

    /* synthetic */ b(UnityAdsAdvertisingId unityAdsAdvertisingId, byte b) {
        this(unityAdsAdvertisingId);
    }

    @Override // android.content.ServiceConnection
    public final void onServiceConnected(ComponentName componentName, IBinder iBinder) {
        try {
            this.c.put(iBinder);
        } catch (InterruptedException e) {
        }
    }

    @Override // android.content.ServiceConnection
    public final void onServiceDisconnected(ComponentName componentName) {
    }

    public final IBinder getBinder() {
        if (this.a) {
            throw new IllegalStateException();
        }
        this.a = true;
        return (IBinder) this.c.take();
    }
}
