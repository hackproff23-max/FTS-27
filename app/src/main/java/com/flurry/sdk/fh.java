package com.flurry.sdk;

import android.content.Context;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.Looper;
import android.text.TextUtils;

/* JADX INFO: loaded from: classes.dex */
public class fh implements hh.a {
    private static fh a;
    private static final String b = fh.class.getSimpleName();
    private boolean g;
    private Location h;
    private Location l;
    private final int c = 3;
    private final long d = 10000;
    private final long e = 90000;
    private final long f = 0;
    private long i = 0;
    private boolean m = false;
    private int n = 0;
    private fy<hj> o = new fy<hj>() { // from class: com.flurry.sdk.fh.1
        @Override // com.flurry.sdk.fy
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public void notify(hj hjVar) {
            if (fh.this.i > 0 && fh.this.i < System.currentTimeMillis()) {
                gd.a(4, fh.b, "No location received in 90 seconds , stopping LocationManager");
                fh.this.h();
            }
        }
    };
    private LocationManager j = (LocationManager) fp.a().c().getSystemService("location");
    private a k = new a();

    static /* synthetic */ int c(fh fhVar) {
        int i = fhVar.n + 1;
        fhVar.n = i;
        return i;
    }

    public static synchronized fh a() {
        if (a == null) {
            a = new fh();
        }
        return a;
    }

    public static void b() {
        if (a != null) {
            hg.a().b("ReportLocation", (hh.a) a);
            hg.a().b("ExplicitLocation", (hh.a) a);
        }
        a = null;
    }

    private fh() {
        hg hgVarA = hg.a();
        this.g = ((Boolean) hgVarA.a("ReportLocation")).booleanValue();
        hgVarA.a("ReportLocation", (hh.a) this);
        gd.a(4, b, "initSettings, ReportLocation = " + this.g);
        this.h = (Location) hgVarA.a("ExplicitLocation");
        hgVarA.a("ExplicitLocation", (hh.a) this);
        gd.a(4, b, "initSettings, ExplicitLocation = " + this.h);
    }

    public synchronized void c() {
        gd.a(4, b, "Location update requested");
        if (this.n < 3) {
            g();
        }
    }

    public synchronized void d() {
        gd.a(4, b, "Stop update location requested");
        h();
    }

    public Location e() {
        String strJ;
        Location location = null;
        if (this.h != null) {
            return this.h;
        }
        if (this.g) {
            Context contextC = fp.a().c();
            if (!a(contextC) && !b(contextC)) {
                return null;
            }
            if (a(contextC)) {
                strJ = i();
            } else {
                strJ = b(contextC) ? j() : null;
            }
            if (strJ != null) {
                Location locationB = b(strJ);
                if (locationB != null) {
                    this.l = locationB;
                }
                location = this.l;
            }
        }
        gd.a(4, b, "getLocation() = " + location);
        return location;
    }

    private void g() {
        if (!this.m && this.g && this.h == null) {
            Context contextC = fp.a().c();
            if (contextC.checkCallingOrSelfPermission("android.permission.ACCESS_FINE_LOCATION") == 0 || contextC.checkCallingOrSelfPermission("android.permission.ACCESS_COARSE_LOCATION") == 0) {
                this.n = 0;
                String strJ = null;
                if (a(contextC)) {
                    strJ = i();
                } else if (b(contextC)) {
                    strJ = j();
                }
                a(strJ);
                this.l = b(strJ);
                this.i = System.currentTimeMillis() + 90000;
                k();
                this.m = true;
                gd.a(4, b, "LocationProvider started");
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void h() {
        if (this.m) {
            this.j.removeUpdates(this.k);
            this.n = 0;
            this.i = 0L;
            l();
            this.m = false;
            gd.a(4, b, "LocationProvider stopped");
        }
    }

    private String i() {
        return "passive";
    }

    private String j() {
        return "network";
    }

    private boolean a(Context context) {
        return context.checkCallingOrSelfPermission("android.permission.ACCESS_FINE_LOCATION") == 0;
    }

    private boolean b(Context context) {
        return context.checkCallingOrSelfPermission("android.permission.ACCESS_COARSE_LOCATION") == 0;
    }

    private void a(String str) {
        if (!TextUtils.isEmpty(str)) {
            this.j.requestLocationUpdates(str, 10000L, 0.0f, this.k, Looper.getMainLooper());
        }
    }

    private Location b(String str) {
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        return this.j.getLastKnownLocation(str);
    }

    private void k() {
        gd.a(4, b, "Register location timer");
        hk.a().a(this.o);
    }

    private void l() {
        gd.a(4, b, "Unregister location timer");
        hk.a().b(this.o);
    }

    class a implements LocationListener {
        public a() {
        }

        @Override // android.location.LocationListener
        public void onStatusChanged(String str, int i, Bundle bundle) {
        }

        @Override // android.location.LocationListener
        public void onProviderEnabled(String str) {
        }

        @Override // android.location.LocationListener
        public void onProviderDisabled(String str) {
        }

        @Override // android.location.LocationListener
        public void onLocationChanged(Location location) {
            if (location != null) {
                fh.this.l = location;
            }
            if (fh.c(fh.this) >= 3) {
                gd.a(4, fh.b, "Max location reports reached, stopping");
                fh.this.h();
            }
        }
    }

    @Override // com.flurry.sdk.hh.a
    public void a(String str, Object obj) {
        switch (str) {
            case "ReportLocation":
                this.g = ((Boolean) obj).booleanValue();
                gd.a(4, b, "onSettingUpdate, ReportLocation = " + this.g);
                break;
            case "ExplicitLocation":
                this.h = (Location) obj;
                gd.a(4, b, "onSettingUpdate, ExplicitLocation = " + this.h);
                break;
            default:
                gd.a(6, b, "LocationProvider internal error! Had to be LocationCriteria, ReportLocation or ExplicitLocation key.");
                break;
        }
    }
}
