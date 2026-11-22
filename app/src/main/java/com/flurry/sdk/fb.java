package com.flurry.sdk;

import android.annotation.TargetApi;
import android.app.Activity;
import android.app.ActivityManager;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.os.StatFs;
import android.os.SystemClock;
import com.flurry.android.FlurryEventRecordStatus;
import com.google.android.vending.expansion.downloader.IDownloaderClient;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

/* JADX INFO: loaded from: classes.dex */
public class fb implements hf, hh.a {
    private File j;
    private fw<List<ez>> k;
    private boolean l;
    private long m;
    private boolean n;
    private String o;
    private byte p;
    private Long q;
    private static final String f = fb.class.getSimpleName();
    static int a = 100;
    static int b = 10;
    static int c = 1000;
    static int d = 160000;
    static int e = 50;
    private final AtomicInteger g = new AtomicInteger(0);
    private final AtomicInteger h = new AtomicInteger(0);
    private final AtomicInteger i = new AtomicInteger(0);
    private final List<ez> r = new ArrayList();
    private final Map<String, List<String>> s = new HashMap();
    private final Map<String, String> t = new HashMap();
    private final Map<String, ev> u = new HashMap();
    private final List<ew> v = new ArrayList();
    private boolean w = true;
    private int x = 0;
    private final List<eu> y = new ArrayList();
    private int z = 0;
    private int A = 0;
    private final er B = new er();
    private final fy<ff> C = new fy<ff>() { // from class: com.flurry.sdk.fb.1
        @Override // com.flurry.sdk.fy
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public void notify(ff ffVar) {
            fp.a().b(new hq() { // from class: com.flurry.sdk.fb.1.1
                @Override // com.flurry.sdk.hq
                public void safeRun() {
                    fb.this.b(true);
                }
            });
        }
    };

    @Override // com.flurry.sdk.hf
    public void a(Context context) {
        hg hgVarA = hg.a();
        this.n = ((Boolean) hgVarA.a("LogEvents")).booleanValue();
        hgVarA.a("LogEvents", (hh.a) this);
        gd.a(4, f, "initSettings, LogEvents = " + this.n);
        this.o = (String) hgVarA.a("UserId");
        hgVarA.a("UserId", (hh.a) this);
        gd.a(4, f, "initSettings, UserId = " + this.o);
        this.p = ((Byte) hgVarA.a("Gender")).byteValue();
        hgVarA.a("Gender", (hh.a) this);
        gd.a(4, f, "initSettings, Gender = " + ((int) this.p));
        this.q = (Long) hgVarA.a("Age");
        hgVarA.a("Age", (hh.a) this);
        gd.a(4, f, "initSettings, BirthDate = " + this.q);
        this.j = context.getFileStreamPath(m());
        this.k = new fw<>(context.getFileStreamPath(n()), ".yflurryreport.", 1, new ha<List<ez>>() { // from class: com.flurry.sdk.fb.2
            @Override // com.flurry.sdk.ha
            public gx<List<ez>> a(int i) {
                return new gw(new ez.a());
            }
        });
        d(context);
        a(true);
        fp.a().b(new hq() { // from class: com.flurry.sdk.fb.3
            @Override // com.flurry.sdk.hq
            public void safeRun() {
                fb.this.k();
            }
        });
        fp.a().b(new hq() { // from class: com.flurry.sdk.fb.4
            @Override // com.flurry.sdk.hq
            public void safeRun() {
                fb.this.o();
            }
        });
        if (fe.a().c()) {
            fp.a().b(new hq() { // from class: com.flurry.sdk.fb.5
                @Override // com.flurry.sdk.hq
                public void safeRun() {
                    fb.this.b(true);
                }
            });
        } else {
            fz.a().a("com.flurry.android.sdk.IdProviderFinishedEvent", this.C);
        }
    }

    @Override // com.flurry.sdk.hf
    public synchronized void b(Context context) {
    }

    @Override // com.flurry.sdk.hf
    public synchronized void c(Context context) {
        a(false);
        a(fd.a().e());
        fp.a().b(new hq() { // from class: com.flurry.sdk.fb.6
            @Override // com.flurry.sdk.hq
            public void safeRun() {
                fb.this.p();
            }
        });
        if (fe.a().c()) {
            fp.a().b(new hq() { // from class: com.flurry.sdk.fb.7
                @Override // com.flurry.sdk.hq
                public void safeRun() {
                    fb.this.r.add(fb.this.c());
                    fb.this.e();
                }
            });
        }
    }

    @Override // com.flurry.sdk.hf
    public synchronized void a() {
        fz.a().a(this.C);
        if (fe.a().c()) {
            fp.a().b(new hq() { // from class: com.flurry.sdk.fb.8
                @Override // com.flurry.sdk.hq
                public void safeRun() {
                    fb.this.b(false);
                }
            });
        }
        hg.a().b("Gender", (hh.a) this);
        hg.a().b("UserId", (hh.a) this);
        hg.a().b("Age", (hh.a) this);
        hg.a().b("LogEvents", (hh.a) this);
    }

    @Override // com.flurry.sdk.hh.a
    public void a(String str, Object obj) {
        switch (str) {
            case "LogEvents":
                this.n = ((Boolean) obj).booleanValue();
                gd.a(4, f, "onSettingUpdate, LogEvents = " + this.n);
                break;
            case "UserId":
                this.o = (String) obj;
                gd.a(4, f, "onSettingUpdate, UserId = " + this.o);
                break;
            case "Gender":
                this.p = ((Byte) obj).byteValue();
                gd.a(4, f, "onSettingUpdate, Gender = " + ((int) this.p));
                break;
            case "Age":
                this.q = (Long) obj;
                gd.a(4, f, "onSettingUpdate, Birthdate = " + this.q);
                break;
            default:
                gd.a(6, f, "onSettingUpdate internal error!");
                break;
        }
    }

    public void b() {
        this.l = true;
    }

    private void d(Context context) {
        Bundle extras;
        if ((context instanceof Activity) && (extras = ((Activity) context).getIntent().getExtras()) != null) {
            gd.a(3, f, "Launch Options Bundle is present " + extras.toString());
            for (String str : extras.keySet()) {
                if (str != null) {
                    Object obj = extras.get(str);
                    String string = obj != null ? obj.toString() : "null";
                    this.s.put(str, new ArrayList(Arrays.asList(string)));
                    gd.a(3, f, "Launch options Key: " + str + ". Its value: " + string);
                }
            }
        }
    }

    @TargetApi(IDownloaderClient.STATE_FAILED_CANCELED)
    private void a(boolean z) {
        boolean z2;
        int intExtra;
        int i = -1;
        if (z) {
            this.t.put("boot.time", Long.toString(System.currentTimeMillis() - SystemClock.elapsedRealtime()));
            StatFs statFs = new StatFs(Environment.getRootDirectory().getAbsolutePath());
            StatFs statFs2 = new StatFs(Environment.getExternalStorageDirectory().getAbsolutePath());
            if (Build.VERSION.SDK_INT >= 18) {
                this.t.put("disk.size.total.internal", Long.toString(statFs.getAvailableBlocksLong()));
                this.t.put("disk.size.available.internal", Long.toString(statFs.getAvailableBlocksLong()));
                this.t.put("disk.size.total.external", Long.toString(statFs2.getAvailableBlocksLong()));
                this.t.put("disk.size.available.external", Long.toString(statFs2.getAvailableBlocksLong()));
            } else {
                this.t.put("disk.size.total.internal", Long.toString(statFs.getAvailableBlocks()));
                this.t.put("disk.size.available.internal", Long.toString(statFs.getAvailableBlocks()));
                this.t.put("disk.size.total.external", Long.toString(statFs2.getAvailableBlocks()));
                this.t.put("disk.size.available.external", Long.toString(statFs2.getAvailableBlocks()));
            }
            this.t.put("carrier.name", fk.a().c());
            this.t.put("carrier.details", fk.a().d());
        }
        ActivityManager activityManager = (ActivityManager) fp.a().c().getSystemService("activity");
        ActivityManager.MemoryInfo memoryInfo = new ActivityManager.MemoryInfo();
        activityManager.getMemoryInfo(memoryInfo);
        this.t.put("memory.available" + (z ? ".start" : ".end"), Long.toString(memoryInfo.availMem));
        if (Build.VERSION.SDK_INT >= 16) {
            this.t.put("memory.total" + (z ? ".start" : ".end"), Long.toString(memoryInfo.availMem));
        }
        Intent intentRegisterReceiver = fp.a().c().registerReceiver(null, new IntentFilter("android.intent.action.BATTERY_CHANGED"));
        if (intentRegisterReceiver != null) {
            int intExtra2 = intentRegisterReceiver.getIntExtra("status", -1);
            z2 = intExtra2 == 2 || intExtra2 == 5;
            int intExtra3 = intentRegisterReceiver.getIntExtra("level", -1);
            intExtra = intentRegisterReceiver.getIntExtra("scale", -1);
            i = intExtra3;
        } else {
            z2 = false;
            intExtra = -1;
        }
        float f2 = i / intExtra;
        this.t.put("battery.charging" + (z ? ".start" : ".end"), Boolean.toString(z2));
        this.t.put("battery.remaining" + (z ? ".start" : ".end"), Float.toString(f2));
    }

    private synchronized void a(long j) {
        for (ew ewVar : this.v) {
            if (ewVar.a() && !ewVar.b()) {
                ewVar.a(j);
            }
        }
    }

    synchronized ez c() {
        ez ezVar;
        fa faVar = new fa();
        faVar.a(fm.a().e());
        faVar.a(fd.a().c());
        faVar.b(fd.a().e());
        faVar.c(fd.a().g());
        faVar.a(this.t);
        faVar.b(fg.a().c());
        faVar.c(fg.a().d());
        faVar.a(fd.a().h().a());
        faVar.b(hn.j());
        faVar.d(f());
        faVar.a(fh.a().e());
        faVar.c(j());
        faVar.a(this.p);
        faVar.a(this.q);
        faVar.b(i());
        faVar.a(g());
        faVar.a(this.w);
        faVar.b(h());
        faVar.d(this.z);
        try {
            ezVar = new ez(faVar);
        } catch (IOException e2) {
            gd.a(5, f, "Error creating analytics session report: " + e2);
            ezVar = null;
        }
        if (ezVar == null) {
            gd.e(f, "New session report wasn't created");
        }
        return ezVar;
    }

    public synchronized void d() {
        this.A++;
    }

    public synchronized FlurryEventRecordStatus a(String str, Map<String, String> map, boolean z) {
        FlurryEventRecordStatus flurryEventRecordStatus;
        FlurryEventRecordStatus flurryEventRecordStatus2 = FlurryEventRecordStatus.kFlurryEventRecorded;
        long jElapsedRealtime = SystemClock.elapsedRealtime() - fd.a().d();
        String strB = hp.b(str);
        if (strB.length() == 0) {
            flurryEventRecordStatus = FlurryEventRecordStatus.kFlurryEventFailed;
        } else {
            ev evVar = this.u.get(strB);
            if (evVar == null) {
                if (this.u.size() < a) {
                    ev evVar2 = new ev();
                    evVar2.a = 1;
                    this.u.put(strB, evVar2);
                    gd.e(f, "Event count started: " + strB);
                    flurryEventRecordStatus = flurryEventRecordStatus2;
                } else {
                    gd.e(f, "Too many different events. Event not counted: " + strB);
                    flurryEventRecordStatus = FlurryEventRecordStatus.kFlurryEventUniqueCountExceeded;
                }
            } else {
                evVar.a++;
                gd.e(f, "Event count incremented: " + strB);
                flurryEventRecordStatus = FlurryEventRecordStatus.kFlurryEventRecorded;
            }
            if (this.n && this.v.size() < c && this.x < d) {
                Map<String, String> mapEmptyMap = map == null ? Collections.emptyMap() : map;
                if (mapEmptyMap.size() > b) {
                    gd.e(f, "MaxEventParams exceeded: " + mapEmptyMap.size());
                    flurryEventRecordStatus = FlurryEventRecordStatus.kFlurryEventParamsCountExceeded;
                } else {
                    ew ewVar = new ew(q(), strB, mapEmptyMap, jElapsedRealtime, z);
                    if (ewVar.d() + this.x <= d) {
                        this.v.add(ewVar);
                        this.x = ewVar.d() + this.x;
                        flurryEventRecordStatus = FlurryEventRecordStatus.kFlurryEventRecorded;
                    } else {
                        this.x = d;
                        this.w = false;
                        gd.e(f, "Event Log size exceeded. No more event details logged.");
                        flurryEventRecordStatus = FlurryEventRecordStatus.kFlurryEventLogCountExceeded;
                    }
                }
            } else {
                this.w = false;
            }
        }
        return flurryEventRecordStatus;
    }

    public synchronized void a(String str, Map<String, String> map) {
        for (ew ewVar : this.v) {
            if (ewVar.a(str)) {
                long jElapsedRealtime = SystemClock.elapsedRealtime() - fd.a().d();
                if (map != null && map.size() > 0 && this.x < d) {
                    int iD = this.x - ewVar.d();
                    HashMap map2 = new HashMap(ewVar.c());
                    ewVar.a(map);
                    if (ewVar.d() + iD <= d) {
                        if (ewVar.c().size() > b) {
                            gd.e(f, "MaxEventParams exceeded on endEvent: " + ewVar.c().size());
                            ewVar.b(map2);
                        } else {
                            this.x = iD + ewVar.d();
                        }
                    } else {
                        ewVar.b(map2);
                        this.w = false;
                        this.x = d;
                        gd.e(f, "Event Log size exceeded. No more event details logged.");
                    }
                }
                ewVar.a(jElapsedRealtime);
                break;
            }
        }
    }

    /* JADX WARN: Code duplicated, block: B:13:0x0059  */
    public synchronized void a(String str, String str2, String str3, Throwable th) {
        boolean z;
        int i = 0;
        synchronized (this) {
            if (str == null) {
                z = false;
            } else if ("uncaught".equals(str)) {
                z = true;
            } else {
                z = false;
            }
            this.z++;
            if (this.y.size() < e) {
                eu euVar = new eu(r(), Long.valueOf(System.currentTimeMillis()).longValue(), str, str2, str3, th);
                this.y.add(euVar);
                gd.e(f, "Error logged: " + euVar.c());
            } else if (z) {
                while (true) {
                    int i2 = i;
                    if (i2 >= this.y.size()) {
                        break;
                    }
                    eu euVar2 = this.y.get(i2);
                    if (euVar2.c() == null || "uncaught".equals(euVar2.c())) {
                        i = i2 + 1;
                    } else {
                        this.y.set(i2, new eu(r(), Long.valueOf(System.currentTimeMillis()).longValue(), str, str2, str3, th));
                        break;
                    }
                }
            } else {
                gd.e(f, "Max errors logged. No more errors logged.");
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public synchronized void b(boolean z) {
        byte[] bArrA;
        try {
            if (!z) {
                if (!this.r.isEmpty()) {
                }
            }
            bArrA = new ex(fp.a().d(), fm.a().e(), this.l, fe.a().e(), this.m, fd.a().c(), this.r, fe.a().h(), this.B.a(false), this.s, fr.a().c(), System.currentTimeMillis()).a();
        } catch (Exception e2) {
            gd.e(f, "Exception while generating report: " + e2);
            bArrA = null;
        }
        gd.a(3, f, "generating agent report");
        if (bArrA == null) {
            gd.e(f, "Error generating report");
        } else {
            gd.a(3, f, "generated report of size " + bArrA.length + " with " + this.r.size() + " reports.");
            eq.a().c().b(bArrA, fp.a().d(), "" + fq.a());
        }
        l();
    }

    public synchronized void e() {
        gd.a(4, f, "Saving persistent agent data.");
        this.k.a(this.r);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public synchronized void k() {
        gd.a(4, f, "Loading persistent session report data.");
        List<ez> listA = this.k.a();
        if (listA != null) {
            this.r.addAll(listA);
        } else if (this.j.exists()) {
            gd.a(4, f, "Legacy persistent agent data found, converting.");
            fc fcVarA = et.a(this.j);
            if (fcVarA != null) {
                boolean zA = fcVarA.a();
                long jB = fcVarA.b();
                if (jB <= 0) {
                    jB = fd.a().c();
                }
                this.l = zA;
                this.m = jB;
                p();
                List<ez> listC = fcVarA.c();
                if (listC != null) {
                    this.r.addAll(listC);
                }
            }
            this.j.delete();
            e();
        }
    }

    private void l() {
        this.r.clear();
        this.k.b();
    }

    private String m() {
        return ".flurryagent." + Integer.toString(fp.a().d().hashCode(), 16);
    }

    private String n() {
        return ".yflurryreport." + Long.toString(hp.i(fp.a().d()), 16);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void o() {
        SharedPreferences sharedPreferences = fp.a().c().getSharedPreferences("FLURRY_SHARED_PREFERENCES", 0);
        this.l = sharedPreferences.getBoolean("com.flurry.sdk.previous_successful_report", false);
        this.m = sharedPreferences.getLong("com.flurry.sdk.initial_run_time", fd.a().c());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void p() {
        SharedPreferences.Editor editorEdit = fp.a().c().getSharedPreferences("FLURRY_SHARED_PREFERENCES", 0).edit();
        editorEdit.putBoolean("com.flurry.sdk.previous_successful_report", this.l);
        editorEdit.putLong("com.flurry.sdk.initial_run_time", this.m);
        editorEdit.commit();
    }

    private int q() {
        return this.g.incrementAndGet();
    }

    private int r() {
        return this.h.incrementAndGet();
    }

    String f() {
        return this.o == null ? "" : this.o;
    }

    List<ew> g() {
        return this.v;
    }

    List<eu> h() {
        return this.y;
    }

    Map<String, ev> i() {
        return this.u;
    }

    int j() {
        return this.A;
    }
}
