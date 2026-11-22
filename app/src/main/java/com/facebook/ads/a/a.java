package com.facebook.ads.a;

import android.app.ActivityManager;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Environment;
import android.os.StatFs;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/* JADX INFO: loaded from: classes.dex */
public class a {
    private static volatile float[] d;
    private static volatile float[] e;
    private static SensorManager a = null;
    private static Sensor b = null;
    private static Sensor c = null;
    private static Map f = new ConcurrentHashMap();
    private static String[] g = {"x", "y", "z"};

    /* JADX INFO: renamed from: com.facebook.ads.a.a$a, reason: collision with other inner class name */
    private static class C0016a implements SensorEventListener {
        private C0016a() {
        }

        @Override // android.hardware.SensorEventListener
        public void onAccuracyChanged(Sensor sensor, int i) {
        }

        @Override // android.hardware.SensorEventListener
        public void onSensorChanged(SensorEvent sensorEvent) {
            if (sensorEvent.sensor == a.b) {
                float[] unused = a.d = sensorEvent.values;
            } else if (sensorEvent.sensor == a.c) {
                float[] unused2 = a.e = sensorEvent.values;
            }
            a.a(this);
        }
    }

    public static Map a() {
        HashMap map = new HashMap();
        map.putAll(f);
        a(map);
        return map;
    }

    /* JADX WARN: Code duplicated, block: B:10:0x0020 A[Catch: all -> 0x005f, TRY_ENTER, TryCatch #0 {, blocks: (B:4:0x0003, B:6:0x0010, B:10:0x0020, B:12:0x0024, B:13:0x002d, B:15:0x0031, B:16:0x003a, B:18:0x003e, B:19:0x004c, B:21:0x0050), top: B:26:0x0003 }] */
    /* JADX WARN: Code duplicated, block: B:12:0x0024 A[Catch: all -> 0x005f, TryCatch #0 {, blocks: (B:4:0x0003, B:6:0x0010, B:10:0x0020, B:12:0x0024, B:13:0x002d, B:15:0x0031, B:16:0x003a, B:18:0x003e, B:19:0x004c, B:21:0x0050), top: B:26:0x0003 }] */
    /* JADX WARN: Code duplicated, block: B:15:0x0031 A[Catch: all -> 0x005f, TryCatch #0 {, blocks: (B:4:0x0003, B:6:0x0010, B:10:0x0020, B:12:0x0024, B:13:0x002d, B:15:0x0031, B:16:0x003a, B:18:0x003e, B:19:0x004c, B:21:0x0050), top: B:26:0x0003 }] */
    /* JADX WARN: Code duplicated, block: B:18:0x003e A[Catch: all -> 0x005f, TryCatch #0 {, blocks: (B:4:0x0003, B:6:0x0010, B:10:0x0020, B:12:0x0024, B:13:0x002d, B:15:0x0031, B:16:0x003a, B:18:0x003e, B:19:0x004c, B:21:0x0050), top: B:26:0x0003 }] */
    /* JADX WARN: Code duplicated, block: B:21:0x0050 A[Catch: all -> 0x005f, TRY_LEAVE, TryCatch #0 {, blocks: (B:4:0x0003, B:6:0x0010, B:10:0x0020, B:12:0x0024, B:13:0x002d, B:15:0x0031, B:16:0x003a, B:18:0x003e, B:19:0x004c, B:21:0x0050), top: B:26:0x0003 }] */
    public static synchronized void a(Context context) {
        b(context);
        c(context);
        d(context);
        if (a == null) {
            a = (SensorManager) context.getSystemService("sensor");
            if (a != null) {
                if (b == null) {
                    b = a.getDefaultSensor(1);
                }
                if (c == null) {
                    c = a.getDefaultSensor(4);
                }
                if (b != null) {
                    a.registerListener(new C0016a(), b, 3);
                }
                if (c != null) {
                    a.registerListener(new C0016a(), c, 3);
                }
            }
        } else {
            if (b == null) {
                b = a.getDefaultSensor(1);
            }
            if (c == null) {
                c = a.getDefaultSensor(4);
            }
            if (b != null) {
                a.registerListener(new C0016a(), b, 3);
            }
            if (c != null) {
                a.registerListener(new C0016a(), c, 3);
            }
        }
    }

    public static synchronized void a(C0016a c0016a) {
        if (a != null) {
            a.unregisterListener(c0016a);
        }
    }

    private static void a(Map map) {
        float[] fArr = d;
        float[] fArr2 = e;
        if (fArr != null) {
            int iMin = Math.min(g.length, fArr.length);
            for (int i = 0; i < iMin; i++) {
                map.put("accelerometer_" + g[i], Float.valueOf(fArr[i]));
            }
        }
        if (fArr2 != null) {
            int iMin2 = Math.min(g.length, fArr2.length);
            for (int i2 = 0; i2 < iMin2; i2++) {
                map.put("rotation_" + g[i2], Float.valueOf(fArr2[i2]));
            }
        }
    }

    private static void b(Context context) {
        ActivityManager.MemoryInfo memoryInfo = new ActivityManager.MemoryInfo();
        ((ActivityManager) context.getSystemService("activity")).getMemoryInfo(memoryInfo);
        f.put("available_memory", String.valueOf(memoryInfo.availMem));
    }

    private static void c(Context context) {
        StatFs statFs = new StatFs(Environment.getDataDirectory().getPath());
        f.put("free_space", Long.valueOf(((long) statFs.getAvailableBlocks()) * statFs.getBlockSize()));
    }

    private static void d(Context context) {
        Intent intentRegisterReceiver = context.registerReceiver(null, new IntentFilter("android.intent.action.BATTERY_CHANGED"));
        if (intentRegisterReceiver == null) {
            return;
        }
        int intExtra = intentRegisterReceiver.getIntExtra("level", -1);
        int intExtra2 = intentRegisterReceiver.getIntExtra("scale", -1);
        int intExtra3 = intentRegisterReceiver.getIntExtra("status", -1);
        boolean z = intExtra3 == 2 || intExtra3 == 5;
        f.put("battery", Float.valueOf(intExtra2 > 0 ? (intExtra / intExtra2) * 100.0f : 0.0f));
        f.put("charging", Integer.valueOf(z ? 1 : 0));
    }
}
