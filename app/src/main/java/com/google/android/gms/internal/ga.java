package com.google.android.gms.internal;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.content.res.Resources;
import android.os.Bundle;
import android.os.Parcelable;
import com.google.android.gms.common.GooglePlayServicesUtil;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;

/* JADX INFO: loaded from: classes.dex */
@ey
public class ga implements ck.b {
    private static final ga vX = new ga();
    public static final String vY = vX.vZ;
    private Context mContext;
    private gs qJ;
    private ld wb;
    private String wl;
    private final Object mH = new Object();
    private BigInteger wc = BigInteger.ONE;
    private final HashSet<fz> wd = new HashSet<>();
    private final HashMap<String, gd> we = new HashMap<>();
    private boolean wf = false;
    private boolean uV = true;
    private boolean wg = false;
    private boolean uW = true;
    private am nE = null;
    private an wh = null;
    private al nF = null;
    private LinkedList<Thread> wi = new LinkedList<>();
    private boolean wj = false;
    private Bundle wk = bn.by();
    private ex nG = null;
    public final String vZ = gi.dx();
    private final gb wa = new gb(this.vZ);

    private ga() {
    }

    public static Bundle a(Context context, gc gcVar, String str) {
        return vX.b(context, gcVar, str);
    }

    public static void a(Context context, gs gsVar) {
        vX.b(context, gsVar);
    }

    public static void a(Context context, boolean z) {
        vX.b(context, z);
    }

    public static void b(HashSet<fz> hashSet) {
        vX.c(hashSet);
    }

    public static Bundle bN() {
        return vX.dp();
    }

    public static String c(int i, String str) {
        return vX.d(i, str);
    }

    public static ga dc() {
        return vX;
    }

    public static String df() {
        return vX.dg();
    }

    public static gb dh() {
        return vX.di();
    }

    public static boolean dj() {
        return vX.dk();
    }

    public static boolean dl() {
        return vX.dm();
    }

    public static String dn() {
        return vX.m5do();
    }

    public static void e(Throwable th) {
        vX.f(th);
    }

    @Override // com.google.android.gms.internal.ck.b
    public void a(Bundle bundle) {
        synchronized (this.mH) {
            this.wj = true;
            this.wk = bundle;
            while (!this.wi.isEmpty()) {
                ex.a(this.mContext, this.wi.remove(0), this.qJ);
            }
        }
    }

    public void a(fz fzVar) {
        synchronized (this.mH) {
            this.wd.add(fzVar);
        }
    }

    public void a(String str, gd gdVar) {
        synchronized (this.mH) {
            this.we.put(str, gdVar);
        }
    }

    public void a(Thread thread) {
        synchronized (this.mH) {
            if (this.wj) {
                ex.a(this.mContext, thread, this.qJ);
            } else {
                this.wi.add(thread);
            }
        }
    }

    public Bundle b(Context context, gc gcVar, String str) {
        Bundle bundle;
        synchronized (this.mH) {
            bundle = new Bundle();
            bundle.putBundle("app", this.wa.b(context, str));
            Bundle bundle2 = new Bundle();
            for (String str2 : this.we.keySet()) {
                bundle2.putBundle(str2, this.we.get(str2).toBundle());
            }
            bundle.putBundle("slots", bundle2);
            ArrayList<? extends Parcelable> arrayList = new ArrayList<>();
            Iterator<fz> it = this.wd.iterator();
            while (it.hasNext()) {
                arrayList.add(it.next().toBundle());
            }
            bundle.putParcelableArrayList("ads", arrayList);
            gcVar.a(this.wd);
            this.wd.clear();
        }
        return bundle;
    }

    public void b(Context context, gs gsVar) {
        synchronized (this.mH) {
            if (!this.wg) {
                this.mContext = context.getApplicationContext();
                this.qJ = gsVar;
                this.uV = gg.o(context);
                iy.H(context);
                ck.a(context, this);
                a(Thread.currentThread());
                this.wl = gi.c(context, gsVar.wS);
                this.wb = new lf();
                this.wg = true;
            }
        }
    }

    public void b(Context context, boolean z) {
        synchronized (this.mH) {
            if (z != this.uV) {
                this.uV = z;
                gg.a(context, z);
            }
        }
    }

    public void c(HashSet<fz> hashSet) {
        synchronized (this.mH) {
            this.wd.addAll(hashSet);
        }
    }

    public String d(int i, String str) {
        Resources resources = this.qJ.wV ? this.mContext.getResources() : GooglePlayServicesUtil.getRemoteResource(this.mContext);
        return resources == null ? str : resources.getString(i);
    }

    public ld dd() {
        ld ldVar;
        synchronized (this.mH) {
            ldVar = this.wb;
        }
        return ldVar;
    }

    public boolean de() {
        boolean z;
        synchronized (this.mH) {
            z = this.uW;
        }
        return z;
    }

    public String dg() {
        String string;
        synchronized (this.mH) {
            string = this.wc.toString();
            this.wc = this.wc.add(BigInteger.ONE);
        }
        return string;
    }

    public gb di() {
        gb gbVar;
        synchronized (this.mH) {
            gbVar = this.wa;
        }
        return gbVar;
    }

    public boolean dk() {
        boolean z;
        synchronized (this.mH) {
            z = this.wf;
            this.wf = true;
        }
        return z;
    }

    public boolean dm() {
        boolean z;
        synchronized (this.mH) {
            z = this.uV;
        }
        return z;
    }

    /* JADX INFO: renamed from: do, reason: not valid java name */
    public String m5do() {
        String str;
        synchronized (this.mH) {
            str = this.wl;
        }
        return str;
    }

    public Bundle dp() {
        Bundle bundle;
        synchronized (this.mH) {
            bundle = this.wk;
        }
        return bundle;
    }

    public void f(Throwable th) {
        if (this.wg) {
            new ex(this.mContext, this.qJ, null, null).b(th);
        }
    }

    /* JADX WARN: Code duplicated, block: B:23:0x0042 A[Catch: all -> 0x002c, TryCatch #0 {, blocks: (B:11:0x0022, B:13:0x0026, B:15:0x002a, B:20:0x002f, B:21:0x003e, B:23:0x0042, B:24:0x0049, B:26:0x004d, B:27:0x0065, B:28:0x006c), top: B:30:0x0022 }] */
    /* JADX WARN: Code duplicated, block: B:26:0x004d A[Catch: all -> 0x002c, TryCatch #0 {, blocks: (B:11:0x0022, B:13:0x0026, B:15:0x002a, B:20:0x002f, B:21:0x003e, B:23:0x0042, B:24:0x0049, B:26:0x004d, B:27:0x0065, B:28:0x006c), top: B:30:0x0022 }] */
    public an l(Context context) {
        an anVar = null;
        if (bN().getBoolean(bn.pp.getKey(), false) && ll.ij() && !de()) {
            synchronized (this.mH) {
                if (this.nE != null) {
                    if (this.nF == null) {
                        this.nF = new al();
                    }
                    if (this.wh == null) {
                        this.wh = new an(this.nE, this.nF, this.wk, new ex(this.mContext, this.qJ, null, null));
                    }
                    this.wh.ba();
                    anVar = this.wh;
                } else if (context instanceof Activity) {
                    this.nE = new am((Application) context.getApplicationContext(), (Activity) context);
                    if (this.nF == null) {
                        this.nF = new al();
                    }
                    if (this.wh == null) {
                        this.wh = new an(this.nE, this.nF, this.wk, new ex(this.mContext, this.qJ, null, null));
                    }
                    this.wh.ba();
                    anVar = this.wh;
                }
            }
        }
        return anVar;
    }

    public void x(boolean z) {
        synchronized (this.mH) {
            this.uW = z;
        }
    }
}
