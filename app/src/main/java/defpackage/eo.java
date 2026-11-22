package defpackage;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;

/* JADX INFO: loaded from: classes.dex */
public abstract class eo {
    private static final bm c = d.a(eo.class);
    protected Context a;
    protected eq b;
    private Object d = new Object();

    protected eo(Context context) {
        this.a = context;
        this.b = new eq(this.a);
    }

    public static eo a(Context context) {
        int i = em.a;
        if (i < 8 || em.b) {
            return new et(context);
        }
        return i < 9 ? new eu(context) : new es(context);
    }

    public final void a() {
        IntentFilter intentFilter = new IntentFilter(eq.a);
        BroadcastReceiver broadcastReceiver = new BroadcastReceiver() { // from class: eo.1
            @Override // android.content.BroadcastReceiver
            public final void onReceive(Context context, Intent intent) {
                int intExtra = intent.getIntExtra(eq.b, -1);
                if (eo.c.c()) {
                    eo.c.c("BluetoothHeadset BroadcastReceiver " + intExtra);
                }
                synchronized (eo.this.d) {
                    if (intExtra == eq.c) {
                        if (eo.c.c()) {
                            eo.c.c("BluetoothHeadset SCO connected. Notify wait lock.");
                        }
                        eo.this.d.notify();
                    }
                }
            }
        };
        synchronized (this.d) {
            this.a.registerReceiver(broadcastReceiver, intentFilter);
            long jCurrentTimeMillis = System.currentTimeMillis();
            d();
            try {
                this.d.wait(3000L);
            } catch (InterruptedException e) {
                if (c.e()) {
                    c.e("BluetoothHeadset mHeadsetScoSyncObj.wait() threw exception:" + e);
                }
            }
            if (c.c()) {
                c.c("BluetoothHeadset " + (System.currentTimeMillis() - jCurrentTimeMillis) + "ms to wait for SCO");
            }
        }
        this.a.unregisterReceiver(broadcastReceiver);
    }

    public final boolean b() {
        return this.b.a() != null;
    }

    public final void c() {
        if (this.b != null) {
            try {
                this.b.d();
            } catch (Throwable th) {
            }
            this.b = null;
        }
    }

    public abstract void d();

    public abstract void e();

    public abstract int f();

    public abstract int g();
}
