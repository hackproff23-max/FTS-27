package defpackage;

import android.bluetooth.BluetoothDevice;
import android.content.Context;
import com.unity3d.ads.android.properties.UnityAdsConstants;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/* JADX INFO: loaded from: classes.dex */
public final class ep {
    public static final String a;
    public static final String b;
    public static final int c;
    private static final Constructor<?> d;
    private static final Method e;
    private static final Method f;
    private static final Method g;
    private static final Method h;
    private static final Class<?> i;
    private Object j;
    private a k;

    public interface a {
        private /* synthetic */ eq a;

        default a(eq eqVar) {
            this.a = eqVar;
        }

        final default void a() {
            if (eq.d.c()) {
                eq.d.c("BluetoothHeadsetOEM reflected onServiceConnected()");
            }
            synchronized (this.a.l) {
                if (this.a.h) {
                    this.a.f();
                } else {
                    eq.c(this.a);
                    this.a.l.notify();
                }
            }
        }

        final default void b() {
            if (eq.d.c()) {
                eq.d.c("BluetoothHeadsetOEM reflected onServiceDisconnected()");
            }
            synchronized (this.a.l) {
                this.a.f();
                if (!this.a.h) {
                    eq.c(this.a);
                    this.a.l.notify();
                }
            }
        }
    }

    static {
        new er();
        Class<?> clsA = er.a("android.bluetooth.BluetoothHeadset");
        Class<?> clsA2 = er.a("android.bluetooth.BluetoothHeadset$ServiceListener");
        d = er.a(clsA, (Class<?>[]) new Class[]{Context.class, clsA2});
        if (er.b(clsA, "getState", new Class[0]) == null) {
            er.a(clsA, "getState", (Class<?>[]) new Class[]{BluetoothDevice.class});
        }
        g = er.a(clsA, "getCurrentHeadset", (Class<?>[]) new Class[0]);
        er.a(clsA, "connectHeadset", (Class<?>[]) new Class[]{BluetoothDevice.class});
        if (er.b(clsA, "disconnectHeadset", new Class[0]) == null) {
            er.a(clsA, "disconnectHeadset", (Class<?>[]) new Class[]{BluetoothDevice.class});
        }
        e = er.a(clsA, "startVoiceRecognition", (Class<?>[]) new Class[0]);
        f = er.a(clsA, "stopVoiceRecognition", (Class<?>[]) new Class[0]);
        h = er.a(clsA, UnityAdsConstants.UNITY_ADS_WEBVIEW_API_CLOSE, (Class<?>[]) new Class[0]);
        er.a(clsA, "getPriority", (Class<?>[]) new Class[]{BluetoothDevice.class});
        er.a(clsA, "setPriority", (Class<?>[]) new Class[]{BluetoothDevice.class, Integer.TYPE});
        i = clsA2;
        er.a(clsA, "ACTION_STATE_CHANGED");
        a = (String) er.a(clsA, "ACTION_AUDIO_STATE_CHANGED");
        er.a(clsA, "EXTRA_STATE");
        b = (String) er.a(clsA, "EXTRA_AUDIO_STATE");
        er.a(clsA, "STATE_ERROR");
        er.a(clsA, "STATE_DISCONNECTED");
        er.a(clsA, "STATE_CONNECTING");
        er.a(clsA, "STATE_CONNECTED");
        er.a(clsA, "AUDIO_STATE_DISCONNECTED");
        c = ((Integer) er.a(clsA, "AUDIO_STATE_CONNECTED")).intValue();
        er.a(clsA, "PRIORITY_OFF");
        er.a(clsA, "PRIORITY_UNDEFINED", (Object) (-1));
    }

    public ep(Context context, a aVar) {
        this.k = aVar;
        try {
            this.j = d.newInstance(context, Proxy.newProxyInstance(getClass().getClassLoader(), new Class[]{i}, new InvocationHandler() { // from class: ep.1
                @Override // java.lang.reflect.InvocationHandler
                public final Object invoke(Object obj, Method method, Object[] objArr) throws Throwable {
                    if (method.getName().equals("onServiceConnected")) {
                        if (ep.this.k == null) {
                            return null;
                        }
                        ep.this.k.a();
                        return null;
                    }
                    if (!method.getName().equals("onServiceDisconnected") || ep.this.k == null) {
                        return null;
                    }
                    ep.this.k.b();
                    return null;
                }
            }));
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }

    public final BluetoothDevice a() {
        try {
            if (g != null) {
                return (BluetoothDevice) g.invoke(this.j, new Object[0]);
            }
        } catch (Exception e2) {
            e2.printStackTrace();
        }
        return null;
    }

    public final boolean b() {
        try {
            return ((Boolean) e.invoke(this.j, new Object[0])).booleanValue();
        } catch (Exception e2) {
            e2.printStackTrace();
            return false;
        }
    }

    public final boolean c() {
        try {
            return ((Boolean) f.invoke(this.j, new Object[0])).booleanValue();
        } catch (Exception e2) {
            e2.printStackTrace();
            return false;
        }
    }

    public final void d() {
        try {
            h.invoke(this.j, new Object[0]);
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }
}
