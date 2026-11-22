package com.google.android.gms.internal;

import android.content.Intent;
import android.os.Bundle;
import android.os.IBinder;
import android.os.Parcel;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;

/* JADX INFO: loaded from: classes.dex */
@ey
public final class dr implements SafeParcelable {
    public static final dq CREATOR = new dq();
    public final gs lO;
    public final int orientation;
    public final String rH;
    public final Cdo sb;
    public final t sc;
    public final ds sd;
    public final gu se;
    public final cb sf;
    public final String sg;
    public final boolean sh;
    public final String si;
    public final dv sj;
    public final int sk;
    public final ce sl;
    public final String sm;
    public final y sn;
    public final int versionCode;

    dr(int i, Cdo cdo, IBinder iBinder, IBinder iBinder2, IBinder iBinder3, IBinder iBinder4, String str, boolean z, String str2, IBinder iBinder5, int i2, int i3, String str3, gs gsVar, IBinder iBinder6, String str4, y yVar) {
        this.versionCode = i;
        this.sb = cdo;
        this.sc = (t) com.google.android.gms.dynamic.e.f(com.google.android.gms.dynamic.d.a.ap(iBinder));
        this.sd = (ds) com.google.android.gms.dynamic.e.f(com.google.android.gms.dynamic.d.a.ap(iBinder2));
        this.se = (gu) com.google.android.gms.dynamic.e.f(com.google.android.gms.dynamic.d.a.ap(iBinder3));
        this.sf = (cb) com.google.android.gms.dynamic.e.f(com.google.android.gms.dynamic.d.a.ap(iBinder4));
        this.sg = str;
        this.sh = z;
        this.si = str2;
        this.sj = (dv) com.google.android.gms.dynamic.e.f(com.google.android.gms.dynamic.d.a.ap(iBinder5));
        this.orientation = i2;
        this.sk = i3;
        this.rH = str3;
        this.lO = gsVar;
        this.sl = (ce) com.google.android.gms.dynamic.e.f(com.google.android.gms.dynamic.d.a.ap(iBinder6));
        this.sm = str4;
        this.sn = yVar;
    }

    public dr(Cdo cdo, t tVar, ds dsVar, dv dvVar, gs gsVar) {
        this.versionCode = 4;
        this.sb = cdo;
        this.sc = tVar;
        this.sd = dsVar;
        this.se = null;
        this.sf = null;
        this.sg = null;
        this.sh = false;
        this.si = null;
        this.sj = dvVar;
        this.orientation = -1;
        this.sk = 4;
        this.rH = null;
        this.lO = gsVar;
        this.sl = null;
        this.sm = null;
        this.sn = null;
    }

    public dr(t tVar, ds dsVar, cb cbVar, dv dvVar, gu guVar, boolean z, int i, String str, gs gsVar, ce ceVar) {
        this.versionCode = 4;
        this.sb = null;
        this.sc = tVar;
        this.sd = dsVar;
        this.se = guVar;
        this.sf = cbVar;
        this.sg = null;
        this.sh = z;
        this.si = null;
        this.sj = dvVar;
        this.orientation = i;
        this.sk = 3;
        this.rH = str;
        this.lO = gsVar;
        this.sl = ceVar;
        this.sm = null;
        this.sn = null;
    }

    public dr(t tVar, ds dsVar, cb cbVar, dv dvVar, gu guVar, boolean z, int i, String str, String str2, gs gsVar, ce ceVar) {
        this.versionCode = 4;
        this.sb = null;
        this.sc = tVar;
        this.sd = dsVar;
        this.se = guVar;
        this.sf = cbVar;
        this.sg = str2;
        this.sh = z;
        this.si = str;
        this.sj = dvVar;
        this.orientation = i;
        this.sk = 3;
        this.rH = null;
        this.lO = gsVar;
        this.sl = ceVar;
        this.sm = null;
        this.sn = null;
    }

    public dr(t tVar, ds dsVar, dv dvVar, gu guVar, int i, gs gsVar, String str, y yVar) {
        this.versionCode = 4;
        this.sb = null;
        this.sc = tVar;
        this.sd = dsVar;
        this.se = guVar;
        this.sf = null;
        this.sg = null;
        this.sh = false;
        this.si = null;
        this.sj = dvVar;
        this.orientation = i;
        this.sk = 1;
        this.rH = null;
        this.lO = gsVar;
        this.sl = null;
        this.sm = str;
        this.sn = yVar;
    }

    public dr(t tVar, ds dsVar, dv dvVar, gu guVar, boolean z, int i, gs gsVar) {
        this.versionCode = 4;
        this.sb = null;
        this.sc = tVar;
        this.sd = dsVar;
        this.se = guVar;
        this.sf = null;
        this.sg = null;
        this.sh = z;
        this.si = null;
        this.sj = dvVar;
        this.orientation = i;
        this.sk = 2;
        this.rH = null;
        this.lO = gsVar;
        this.sl = null;
        this.sm = null;
        this.sn = null;
    }

    public static void a(Intent intent, dr drVar) {
        Bundle bundle = new Bundle(1);
        bundle.putParcelable("com.google.android.gms.ads.inernal.overlay.AdOverlayInfo", drVar);
        intent.putExtra("com.google.android.gms.ads.inernal.overlay.AdOverlayInfo", bundle);
    }

    public static dr b(Intent intent) {
        try {
            Bundle bundleExtra = intent.getBundleExtra("com.google.android.gms.ads.inernal.overlay.AdOverlayInfo");
            bundleExtra.setClassLoader(dr.class.getClassLoader());
            return (dr) bundleExtra.getParcelable("com.google.android.gms.ads.inernal.overlay.AdOverlayInfo");
        } catch (Exception e) {
            return null;
        }
    }

    IBinder ck() {
        return com.google.android.gms.dynamic.e.k(this.sc).asBinder();
    }

    IBinder cl() {
        return com.google.android.gms.dynamic.e.k(this.sd).asBinder();
    }

    IBinder cm() {
        return com.google.android.gms.dynamic.e.k(this.se).asBinder();
    }

    IBinder cn() {
        return com.google.android.gms.dynamic.e.k(this.sf).asBinder();
    }

    IBinder co() {
        return com.google.android.gms.dynamic.e.k(this.sl).asBinder();
    }

    IBinder cp() {
        return com.google.android.gms.dynamic.e.k(this.sj).asBinder();
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel out, int flags) {
        dq.a(this, out, flags);
    }
}
