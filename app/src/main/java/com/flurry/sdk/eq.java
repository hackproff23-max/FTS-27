package com.flurry.sdk;

import android.content.Context;
import com.flurry.android.FlurryEventRecordStatus;
import java.util.Map;

/* JADX INFO: loaded from: classes.dex */
public class eq implements gg {
    private ey a;

    public static synchronized eq a() {
        return (eq) fp.a().a(eq.class);
    }

    @Override // com.flurry.sdk.gg
    public void a(Context context) {
        hb.a((Class<? extends hf>) fb.class);
        this.a = new ey();
    }

    @Override // com.flurry.sdk.gg
    public void b() {
        if (this.a != null) {
            this.a.a();
            this.a = null;
        }
        hb.b((Class<? extends hf>) fb.class);
    }

    public ey c() {
        return this.a;
    }

    public void d() {
        fb fbVarF = f();
        if (fbVarF != null) {
            fbVarF.b();
        }
    }

    public FlurryEventRecordStatus a(String str) {
        fb fbVarF = f();
        FlurryEventRecordStatus flurryEventRecordStatus = FlurryEventRecordStatus.kFlurryEventFailed;
        if (fbVarF != null) {
            return fbVarF.a(str, null, false);
        }
        return flurryEventRecordStatus;
    }

    public FlurryEventRecordStatus a(String str, Map<String, String> map) {
        fb fbVarF = f();
        FlurryEventRecordStatus flurryEventRecordStatus = FlurryEventRecordStatus.kFlurryEventFailed;
        if (fbVarF != null) {
            return fbVarF.a(str, map, false);
        }
        return flurryEventRecordStatus;
    }

    public FlurryEventRecordStatus a(String str, boolean z) {
        fb fbVarF = f();
        FlurryEventRecordStatus flurryEventRecordStatus = FlurryEventRecordStatus.kFlurryEventFailed;
        if (fbVarF != null) {
            return fbVarF.a(str, null, z);
        }
        return flurryEventRecordStatus;
    }

    public FlurryEventRecordStatus a(String str, Map<String, String> map, boolean z) {
        fb fbVarF = f();
        FlurryEventRecordStatus flurryEventRecordStatus = FlurryEventRecordStatus.kFlurryEventFailed;
        if (fbVarF != null) {
            return fbVarF.a(str, map, z);
        }
        return flurryEventRecordStatus;
    }

    public void b(String str) {
        fb fbVarF = f();
        if (fbVarF != null) {
            fbVarF.a(str, (Map<String, String>) null);
        }
    }

    public void b(String str, Map<String, String> map) {
        fb fbVarF = f();
        if (fbVarF != null) {
            fbVarF.a(str, map);
        }
    }

    @Deprecated
    public void a(String str, String str2, String str3) {
        StackTraceElement[] stackTraceElementArr;
        StackTraceElement[] stackTrace = Thread.currentThread().getStackTrace();
        if (stackTrace == null || stackTrace.length <= 2) {
            stackTraceElementArr = stackTrace;
        } else {
            stackTraceElementArr = new StackTraceElement[stackTrace.length - 2];
            System.arraycopy(stackTrace, 2, stackTraceElementArr, 0, stackTraceElementArr.length);
        }
        Throwable th = new Throwable(str2);
        th.setStackTrace(stackTraceElementArr);
        fb fbVarF = f();
        if (fbVarF != null) {
            fbVarF.a(str, str2, str3, th);
        }
    }

    public void a(String str, String str2, Throwable th) {
        fb fbVarF = f();
        if (fbVarF != null) {
            fbVarF.a(str, str2, th.getClass().getName(), th);
        }
    }

    public void c(String str) {
        fb fbVarF = f();
        if (fbVarF != null) {
            fbVarF.a(str, null, false);
        }
    }

    public void c(String str, Map<String, String> map) {
        fb fbVarF = f();
        if (fbVarF != null) {
            fbVarF.a(str, map, false);
        }
    }

    public void e() {
        fb fbVarF = f();
        if (fbVarF != null) {
            fbVarF.d();
        }
    }

    private fb f() {
        return a(hc.a().e());
    }

    private fb a(hb hbVar) {
        if (hbVar == null) {
            return null;
        }
        return (fb) hbVar.c(fb.class);
    }
}
