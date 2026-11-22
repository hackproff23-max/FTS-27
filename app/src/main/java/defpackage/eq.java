package defpackage;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.Context;
import android.os.Looper;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public final class eq {
    public static String a;
    public static String b;
    public static int c;
    private static final bm d = d.a(eo.class);
    private Context e;
    private boolean f;
    private ep g;
    private boolean h;
    private boolean i = false;
    private Object j = null;
    private Object l = new Object();
    private final er k = new er();

    public eq(Context context) {
        BluetoothAdapter defaultAdapter;
        this.g = null;
        this.h = false;
        this.e = context;
        if (em.a <= 10) {
            this.f = true;
            a = ep.a;
            b = ep.b;
            c = ep.c;
            ep.a aVar = new ep.a(this);
            synchronized (this.l) {
                long jCurrentTimeMillis = System.currentTimeMillis();
                this.g = new ep(this.e, aVar);
                try {
                    this.l.wait(1000L);
                } catch (InterruptedException e) {
                    if (d.e()) {
                        d.e("BluetoothHeadsetOEM reflected mHeadsetSyncObj.wait() threw exception:" + e);
                    }
                }
                if (!this.i) {
                    if (d.e()) {
                        d.e("BluetoothHeadsetOEM reflected service NOT connected in time. Gave up!!!");
                    }
                    this.h = true;
                    f();
                } else if (d.c()) {
                    d.c("BluetoothHeadsetOEM reflected service connection took " + (System.currentTimeMillis() - jCurrentTimeMillis) + "ms");
                }
            }
            return;
        }
        this.f = false;
        er erVar = this.k;
        Class<?> clsA = er.a("android.bluetooth.BluetoothHeadset");
        er erVar2 = this.k;
        a = (String) er.a(clsA, "ACTION_AUDIO_STATE_CHANGED");
        er erVar3 = this.k;
        b = (String) er.a(clsA, "EXTRA_STATE");
        er erVar4 = this.k;
        c = ((Integer) er.a(clsA, "STATE_AUDIO_CONNECTED")).intValue();
        if (em.a == 11 || em.a == 12 || em.a == 13) {
            final Object[] objArr = new Object[2];
            new Thread(new Runnable() { // from class: eq.1
                @Override // java.lang.Runnable
                public final void run() {
                    Looper.prepare();
                    objArr[1] = BluetoothAdapter.getDefaultAdapter();
                    objArr[0] = new Boolean(true);
                    Looper.loop();
                }
            }).start();
            while (objArr[0] == null) {
                try {
                    Thread.sleep(10L);
                } catch (Exception e2) {
                }
            }
            defaultAdapter = (BluetoothAdapter) objArr[1];
        } else {
            defaultAdapter = BluetoothAdapter.getDefaultAdapter();
        }
        er erVar5 = this.k;
        Class<?> clsA2 = er.a("android.bluetooth.BluetoothProfile$ServiceListener");
        Object objNewProxyInstance = Proxy.newProxyInstance(getClass().getClassLoader(), new Class[]{clsA2}, new InvocationHandler() { // from class: eq.2
            @Override // java.lang.reflect.InvocationHandler
            public final Object invoke(Object obj, Method method, Object[] objArr2) throws Throwable {
                if (!method.getName().equals("onServiceConnected")) {
                    if (!method.getName().equals("onServiceDisconnected")) {
                        return null;
                    }
                    if (eq.d.c()) {
                        eq.d.c("BluetoothHeadsetOEM native onServiceDisconnected()");
                    }
                    synchronized (eq.this.l) {
                        if (!eq.this.h) {
                            eq.c(eq.this);
                            eq.this.l.notify();
                        }
                    }
                    return null;
                }
                if (eq.d.c()) {
                    eq.d.c("BluetoothHeadsetOEM native onServiceConnected()");
                }
                synchronized (eq.this.l) {
                    if (eq.this.h) {
                        eq.this.a(objArr2[1]);
                    } else {
                        eq.c(eq.this);
                        eq.this.j = objArr2[1];
                        eq.this.l.notify();
                    }
                }
                return null;
            }
        });
        synchronized (this.l) {
            long jCurrentTimeMillis2 = System.currentTimeMillis();
            er erVar6 = this.k;
            Class<?> clsA3 = er.a("android.bluetooth.BluetoothProfile");
            er erVar7 = this.k;
            Class<?> clsA4 = er.a("android.bluetooth.BluetoothAdapter");
            try {
                er erVar8 = this.k;
                if (!((Boolean) er.a(clsA4, "isEnabled", (Class<?>[]) new Class[0]).invoke(defaultAdapter, new Object[0])).booleanValue()) {
                    return;
                }
            } catch (Exception e3) {
                e3.printStackTrace();
            }
            er erVar9 = this.k;
            Method methodA = er.a(clsA4, "getProfileProxy", (Class<?>[]) new Class[]{Context.class, clsA2, Integer.TYPE});
            try {
                er erVar10 = this.k;
                methodA.invoke(defaultAdapter, context, objNewProxyInstance, er.a(clsA3, "HEADSET"));
            } catch (Exception e4) {
                e4.printStackTrace();
            }
            try {
                this.l.wait(1000L);
            } catch (InterruptedException e5) {
                if (d.e()) {
                    d.e("BluetoothHeadsetOEM native mHeadsetSyncObj.wait() threw exception:" + e5);
                }
            }
            if (!this.i) {
                if (d.e()) {
                    d.e("BluetoothHeadsetOEM native service NOT connected in time. Gave up!!!");
                }
                this.h = true;
            } else if (d.c()) {
                d.c("BluetoothHeadsetOEM native service connection took " + (System.currentTimeMillis() - jCurrentTimeMillis2) + "ms");
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(Object obj) {
        er erVar = this.k;
        Class<?> clsA = er.a("android.bluetooth.BluetoothProfile");
        er erVar2 = this.k;
        Class<?> clsA2 = er.a("android.bluetooth.BluetoothAdapter");
        er erVar3 = this.k;
        Method methodB = er.b(clsA2, "closeProfileProxy", Integer.TYPE, clsA);
        BluetoothAdapter defaultAdapter = BluetoothAdapter.getDefaultAdapter();
        try {
            er erVar4 = this.k;
            methodB.invoke(defaultAdapter, (Integer) er.a(clsA, "HEADSET"), obj);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    static /* synthetic */ boolean c(eq eqVar) {
        eqVar.i = true;
        return true;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void f() {
        if (this.g != null) {
            this.g.d();
            this.g = null;
        }
    }

    public final BluetoothDevice a() {
        List list;
        if (this.f && this.g != null) {
            return this.g.a();
        }
        if (this.f || this.j == null) {
            return null;
        }
        er erVar = this.k;
        Class<?> clsA = er.a("android.bluetooth.BluetoothProfile");
        er erVar2 = this.k;
        try {
            list = (List) er.b(clsA, "getConnectedDevices", new Class[0]).invoke(this.j, new Object[0]);
        } catch (Exception e) {
            e.printStackTrace();
            list = null;
        }
        if (list == null || list.size() <= 0) {
            return null;
        }
        return (BluetoothDevice) list.get(0);
    }

    public final boolean b() {
        if (this.f) {
            if (this.g == null) {
                return false;
            }
            return this.g.b();
        }
        if (!d.e()) {
            return false;
        }
        d.e("BluetoothHeadsetOEM startVoiceRecognition() called on native headset!!!");
        return false;
    }

    public final boolean c() {
        if (this.f) {
            if (this.g == null) {
                return false;
            }
            return this.g.c();
        }
        if (!d.e()) {
            return false;
        }
        d.e("BluetoothHeadsetOEM stopVoiceRecognition() called on native headset!!!");
        return false;
    }

    public final void d() {
        if (this.f) {
            f();
        } else if (this.j != null) {
            a(this.j);
            this.j = null;
        }
    }
}
