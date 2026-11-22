package com.google.android.gms.internal;

import android.app.Activity;
import android.content.ComponentCallbacks;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.res.Configuration;
import android.graphics.Rect;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.RemoteException;
import android.util.DisplayMetrics;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.ViewSwitcher;
import com.google.android.gms.common.GooglePlayServicesUtil;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
@ey
public class u extends bd.a implements cb, ce, cg, cs, ds, dv, ej, ez.a, fc.a, gc, t, w {
    private final cy lA;
    private final b lB;
    private final ab lC;
    private final ae lD;
    private boolean lE;
    private final ComponentCallbacks lF;
    private av lz;

    @ey
    private static final class a extends ViewSwitcher {
        private final gl lJ;

        public a(Context context) {
            super(context);
            this.lJ = new gl(context);
        }

        @Override // android.view.ViewGroup
        public boolean onInterceptTouchEvent(MotionEvent event) {
            this.lJ.c(event);
            return false;
        }
    }

    @ey
    static class b {
        public final a lK;
        public final String lL;
        public final Context lM;
        public final k lN;
        public final gs lO;
        public bc lP;
        public gf lQ;
        public gf lR;
        public ay lS;
        public fy lT;
        public fy.a lU;
        public fz lV;
        public bf lW;
        public er lX;
        public en lY;
        public by lZ;
        public bz ma;
        public br mb;
        public List<String> mc;
        public ek md;
        public gd me = null;
        public View mf = null;
        public int mg = 0;
        public boolean mh = false;
        public boolean mi = false;
        private HashSet<fz> mj = null;

        public b(Context context, ay ayVar, String str, gs gsVar) {
            if (ayVar.oq) {
                this.lK = null;
            } else {
                this.lK = new a(context);
                this.lK.setMinimumWidth(ayVar.widthPixels);
                this.lK.setMinimumHeight(ayVar.heightPixels);
                this.lK.setVisibility(4);
            }
            this.lS = ayVar;
            this.lL = str;
            this.lM = context;
            this.lO = gsVar;
            this.lN = new k(new x(this));
        }

        public void a(HashSet<fz> hashSet) {
            this.mj = hashSet;
        }

        public HashSet<fz> ay() {
            return this.mj;
        }
    }

    public u(Context context, ay ayVar, String str, cy cyVar, gs gsVar) {
        this(new b(context, ayVar, str, gsVar), cyVar, null);
    }

    u(b bVar, cy cyVar, ab abVar) {
        this.lF = new ComponentCallbacks() { // from class: com.google.android.gms.internal.u.1
            @Override // android.content.ComponentCallbacks
            public void onConfigurationChanged(Configuration newConfig) {
                if (u.this.lB == null || u.this.lB.lT == null || u.this.lB.lT.se == null) {
                    return;
                }
                u.this.lB.lT.se.cb();
            }

            @Override // android.content.ComponentCallbacks
            public void onLowMemory() {
            }
        };
        this.lB = bVar;
        this.lA = cyVar;
        this.lC = abVar == null ? new ab(this) : abVar;
        this.lD = new ae();
        gi.q(this.lB.lM);
        ga.a(this.lB.lM, this.lB.lO);
        ad();
    }

    private fh.a a(av avVar, Bundle bundle) {
        PackageInfo packageInfo;
        ApplicationInfo applicationInfo = this.lB.lM.getApplicationInfo();
        try {
            packageInfo = this.lB.lM.getPackageManager().getPackageInfo(applicationInfo.packageName, 0);
        } catch (PackageManager.NameNotFoundException e) {
            packageInfo = null;
        }
        Bundle bundle2 = null;
        if (!this.lB.lS.oq && this.lB.lK.getParent() != null) {
            int[] iArr = new int[2];
            this.lB.lK.getLocationOnScreen(iArr);
            int i = iArr[0];
            int i2 = iArr[1];
            DisplayMetrics displayMetrics = this.lB.lM.getResources().getDisplayMetrics();
            int width = this.lB.lK.getWidth();
            int height = this.lB.lK.getHeight();
            int i3 = 0;
            if (this.lB.lK.isShown() && i + width > 0 && i2 + height > 0 && i <= displayMetrics.widthPixels && i2 <= displayMetrics.heightPixels) {
                i3 = 1;
            }
            bundle2 = new Bundle(5);
            bundle2.putInt("x", i);
            bundle2.putInt("y", i2);
            bundle2.putInt("width", width);
            bundle2.putInt("height", height);
            bundle2.putInt("visible", i3);
        }
        String strDf = ga.df();
        this.lB.lV = new fz(strDf, this.lB.lL);
        this.lB.lV.e(avVar);
        return new fh.a(bundle2, avVar, this.lB.lS, this.lB.lL, applicationInfo, packageInfo, strDf, ga.vY, this.lB.lO, ga.a(this.lB.lM, this, strDf), this.lB.mc, bundle, ga.dl());
    }

    private gu a(v vVar) {
        gu guVarA;
        if (this.lB.lS.oq) {
            gu guVarA2 = gu.a(this.lB.lM, this.lB.lS, false, false, this.lB.lN, this.lB.lO);
            guVarA2.dD().a(this, null, this, this, true, this, this, vVar);
            return guVarA2;
        }
        View nextView = this.lB.lK.getNextView();
        if (nextView instanceof gu) {
            guVarA = (gu) nextView;
            guVarA.a(this.lB.lM, this.lB.lS);
        } else {
            if (nextView != null) {
                this.lB.lK.removeView(nextView);
            }
            guVarA = gu.a(this.lB.lM, this.lB.lS, false, false, this.lB.lN, this.lB.lO);
            if (this.lB.lS.or == null) {
                c(guVarA);
            }
        }
        guVarA.dD().a(this, this, this, this, false, this, vVar);
        return guVarA;
    }

    private void a(int i) {
        gr.W("Failed to load ad: " + i);
        if (this.lB.lP != null) {
            try {
                this.lB.lP.onAdFailedToLoad(i);
            } catch (RemoteException e) {
                gr.d("Could not call AdListener.onAdFailedToLoad().", e);
            }
        }
    }

    private void ad() {
        if (Build.VERSION.SDK_INT < 14 || this.lB == null || this.lB.lM == null) {
            return;
        }
        this.lB.lM.registerComponentCallbacks(this.lF);
    }

    private void ae() {
        if (Build.VERSION.SDK_INT < 14 || this.lB == null || this.lB.lM == null) {
            return;
        }
        this.lB.lM.unregisterComponentCallbacks(this.lF);
    }

    private void ao() {
        gr.U("Ad closing.");
        if (this.lB.lP != null) {
            try {
                this.lB.lP.onAdClosed();
            } catch (RemoteException e) {
                gr.d("Could not call AdListener.onAdClosed().", e);
            }
        }
    }

    private void ap() {
        gr.U("Ad leaving application.");
        if (this.lB.lP != null) {
            try {
                this.lB.lP.onAdLeftApplication();
            } catch (RemoteException e) {
                gr.d("Could not call AdListener.onAdLeftApplication().", e);
            }
        }
    }

    private void aq() {
        gr.U("Ad opening.");
        if (this.lB.lP != null) {
            try {
                this.lB.lP.onAdOpened();
            } catch (RemoteException e) {
                gr.d("Could not call AdListener.onAdOpened().", e);
            }
        }
    }

    private void ar() {
        gr.U("Ad finished loading.");
        if (this.lB.lP != null) {
            try {
                this.lB.lP.onAdLoaded();
            } catch (RemoteException e) {
                gr.d("Could not call AdListener.onAdLoaded().", e);
            }
        }
    }

    private void as() {
        try {
            if (!(this.lB.lT.vI instanceof bt) || this.lB.lZ == null) {
                return;
            }
            this.lB.lZ.a((bt) this.lB.lT.vI);
        } catch (RemoteException e) {
            gr.d("Could not call OnAppInstallAdLoadedListener.onAppInstallAdLoaded().", e);
        }
    }

    private void at() {
        try {
            if (!(this.lB.lT.vI instanceof bu) || this.lB.ma == null) {
                return;
            }
            this.lB.ma.a((bu) this.lB.lT.vI);
        } catch (RemoteException e) {
            gr.d("Could not call OnContentAdLoadedListener.onContentAdLoaded().", e);
        }
    }

    private void ax() {
        if (this.lB.lT != null) {
            if (this.lB.mg == 0) {
                this.lB.lT.se.destroy();
            }
            this.lB.lT = null;
            this.lB.mi = false;
        }
    }

    private boolean b(fy fyVar) {
        if (fyVar.tW) {
            try {
                View view = (View) com.google.android.gms.dynamic.e.f(fyVar.qQ.getView());
                View nextView = this.lB.lK.getNextView();
                if (nextView != null) {
                    this.lB.lK.removeView(nextView);
                }
                try {
                    c(view);
                } catch (Throwable th) {
                    gr.d("Could not add mediation view to view hierarchy.", th);
                    return false;
                }
            } catch (RemoteException e) {
                gr.d("Could not get View from mediation adapter.", e);
                return false;
            }
        } else if (fyVar.vF != null) {
            fyVar.se.a(fyVar.vF);
            this.lB.lK.removeAllViews();
            this.lB.lK.setMinimumWidth(fyVar.vF.widthPixels);
            this.lB.lK.setMinimumHeight(fyVar.vF.heightPixels);
            c(fyVar.se);
        }
        if (this.lB.lK.getChildCount() > 1) {
            this.lB.lK.showNext();
        }
        if (this.lB.lT != null) {
            View nextView2 = this.lB.lK.getNextView();
            if (nextView2 instanceof gu) {
                ((gu) nextView2).a(this.lB.lM, this.lB.lS);
            } else if (nextView2 != null) {
                this.lB.lK.removeView(nextView2);
            }
            if (this.lB.lT.qQ != null) {
                try {
                    this.lB.lT.qQ.destroy();
                } catch (RemoteException e2) {
                    gr.W("Could not destroy previous mediation adapter.");
                }
            }
        }
        this.lB.lK.setVisibility(0);
        return true;
    }

    private void c(View view) {
        this.lB.lK.addView(view, new ViewGroup.LayoutParams(-2, -2));
    }

    private void e(boolean z) {
        if (this.lB.lT == null) {
            gr.W("Ad state was null when trying to ping impression URLs.");
            return;
        }
        gr.S("Pinging Impression URLs.");
        this.lB.lV.cW();
        if (this.lB.lT.qx != null) {
            gi.a(this.lB.lM, this.lB.lO.wS, this.lB.lT.qx);
        }
        if (this.lB.lT.vE != null && this.lB.lT.vE.qx != null) {
            cw.a(this.lB.lM, this.lB.lO.wS, this.lB.lT, this.lB.lL, z, this.lB.lT.vE.qx);
        }
        if (this.lB.lT.qP == null || this.lB.lT.qP.qs == null) {
            return;
        }
        cw.a(this.lB.lM, this.lB.lO.wS, this.lB.lT, this.lB.lL, z, this.lB.lT.qP.qs);
    }

    Bundle a(an anVar) {
        String strAT;
        if (anVar == null) {
            return null;
        }
        if (anVar.be()) {
            anVar.wakeup();
        }
        ak akVarBc = anVar.bc();
        if (akVarBc != null) {
            strAT = akVarBc.aT();
            gr.S("In AdManger: loadAd, " + akVarBc.toString());
        } else {
            strAT = null;
        }
        if (strAT == null) {
            return null;
        }
        Bundle bundle = new Bundle(1);
        bundle.putString("fingerprint", strAT);
        bundle.putInt("v", 1);
        return bundle;
    }

    @Override // com.google.android.gms.internal.bd
    public void a(ay ayVar) {
        jx.aU("setAdSize must be called on the main UI thread.");
        this.lB.lS = ayVar;
        if (this.lB.lT != null && this.lB.mg == 0) {
            this.lB.lT.se.a(ayVar);
        }
        if (this.lB.lK.getChildCount() > 1) {
            this.lB.lK.removeView(this.lB.lK.getNextView());
        }
        this.lB.lK.setMinimumWidth(ayVar.widthPixels);
        this.lB.lK.setMinimumHeight(ayVar.heightPixels);
        this.lB.lK.requestLayout();
    }

    @Override // com.google.android.gms.internal.bd
    public void a(bc bcVar) {
        jx.aU("setAdListener must be called on the main UI thread.");
        this.lB.lP = bcVar;
    }

    @Override // com.google.android.gms.internal.bd
    public void a(bf bfVar) {
        jx.aU("setAppEventListener must be called on the main UI thread.");
        this.lB.lW = bfVar;
    }

    @Override // com.google.android.gms.internal.bd
    public void a(br brVar) {
        jx.aU("setOnCustomRenderedAdLoadedListener must be called on the main UI thread.");
        this.lB.mb = brVar;
    }

    @Override // com.google.android.gms.internal.bd
    public void a(en enVar) {
        jx.aU("setInAppPurchaseListener must be called on the main UI thread.");
        this.lB.lY = enVar;
    }

    @Override // com.google.android.gms.internal.bd
    public void a(er erVar, String str) {
        jx.aU("setPlayStorePurchaseParams must be called on the main UI thread.");
        this.lB.md = new ek(str);
        this.lB.lX = erVar;
        if (ga.dj() || erVar == null) {
            return;
        }
        new ec(this.lB.lM, this.lB.lX, this.lB.md).start();
    }

    @Override // com.google.android.gms.internal.ez.a
    public void a(fy.a aVar) {
        gu guVarA;
        this.lB.lQ = null;
        this.lB.lU = aVar;
        a((List<String>) null);
        if (aVar.vK.ug) {
            guVarA = null;
        } else {
            final v vVar = new v();
            guVarA = a(vVar);
            vVar.a(new v.b(aVar, guVarA));
            guVarA.setOnTouchListener(new View.OnTouchListener() { // from class: com.google.android.gms.internal.u.2
                @Override // android.view.View.OnTouchListener
                public boolean onTouch(View v, MotionEvent event) {
                    vVar.av();
                    return false;
                }
            });
            guVarA.setOnClickListener(new View.OnClickListener() { // from class: com.google.android.gms.internal.u.3
                @Override // android.view.View.OnClickListener
                public void onClick(View v) {
                    vVar.av();
                }
            });
        }
        if (aVar.lS != null) {
            this.lB.lS = aVar.lS;
        }
        if (aVar.errorCode != -2) {
            a(new fy(aVar, guVarA, null, null, null, null, null));
            return;
        }
        if (!aVar.vK.tW && aVar.vK.uf) {
            bo boVar = new bo(this, aVar.vK.sg != null ? Uri.parse(aVar.vK.sg).buildUpon().query(null).build().toString() : null, aVar.vK.tU);
            try {
                if (this.lB.mb != null) {
                    this.lB.mg = 1;
                    this.lB.mb.a(boVar);
                    return;
                }
            } catch (RemoteException e) {
                gr.d("Could not call the onCustomRenderedAdLoadedListener.", e);
            }
        }
        this.lB.mg = 0;
        this.lB.lR = fc.a(this.lB.lM, this, aVar, guVarA, this.lA, this);
    }

    @Override // com.google.android.gms.internal.fc.a
    public void a(fy fyVar) {
        int i;
        int i2;
        this.lB.lR = null;
        boolean z = fyVar.vI != null;
        if (fyVar.errorCode != -2 && fyVar.errorCode != 3) {
            ga.b(this.lB.ay());
        }
        if (fyVar.errorCode == -1) {
            return;
        }
        if (a(fyVar, z)) {
            gr.S("Ad refresh scheduled.");
        }
        if (fyVar.errorCode == 3 && fyVar.vE != null && fyVar.vE.qy != null) {
            gr.S("Pinging no fill URLs.");
            cw.a(this.lB.lM, this.lB.lO.wS, fyVar, this.lB.lL, false, fyVar.vE.qy);
        }
        if (fyVar.errorCode != -2) {
            a(fyVar.errorCode);
            return;
        }
        if (!this.lB.lS.oq && !z && this.lB.mg == 0) {
            if (!b(fyVar)) {
                a(0);
                return;
            } else if (this.lB.lK != null) {
                this.lB.lK.lJ.Q(fyVar.ub);
            }
        }
        if (this.lB.lT != null && this.lB.lT.qS != null) {
            this.lB.lT.qS.a((cs) null);
        }
        if (fyVar.qS != null) {
            fyVar.qS.a(this);
        }
        this.lD.d(this.lB.lT);
        this.lB.lT = fyVar;
        this.lB.lV.j(fyVar.vG);
        this.lB.lV.k(fyVar.vH);
        this.lB.lV.v(this.lB.lS.oq);
        this.lB.lV.w(fyVar.tW);
        if (!this.lB.lS.oq && !z && this.lB.mg == 0) {
            e(false);
        }
        if (this.lB.me == null) {
            this.lB.me = new gd(this.lB.lL);
        }
        if (fyVar.vE != null) {
            i2 = fyVar.vE.qB;
            i = fyVar.vE.qC;
        } else {
            i = 0;
            i2 = 0;
        }
        this.lB.me.d(i2, i);
        if (this.lB.mg != 0) {
            if (this.lB.mf == null || fyVar.vD == null) {
                return;
            }
            this.lD.a(this.lB.lM, this.lB.lS, this.lB.lT, this.lB.mf, this.lB.lO);
            return;
        }
        if (!this.lB.lS.oq && fyVar.se != null && (fyVar.se.dD().dN() || fyVar.vD != null)) {
            af afVarA = this.lD.a(this.lB.lS, this.lB.lT);
            if (fyVar.se.dD().dN() && afVarA != null) {
                afVarA.a(new aa(fyVar.se));
            }
        }
        if (this.lB.lT.se != null) {
            this.lB.lT.se.cb();
            this.lB.lT.se.dD().dO();
        }
        if (z) {
            bv.a aVar = fyVar.vI;
            if ((aVar instanceof bu) && this.lB.ma != null) {
                at();
            } else {
                if (!(aVar instanceof bt) || this.lB.lZ == null) {
                    gr.W("No matching listener for retrieved native ad template.");
                    a(0);
                    return;
                }
                as();
            }
        }
        ar();
    }

    @Override // com.google.android.gms.internal.ce
    public void a(String str, ArrayList<String> arrayList) {
        ed edVar = new ed(str, arrayList, this.lB.lM, this.lB.lO.wS);
        if (this.lB.lY != null) {
            try {
                this.lB.lY.a(edVar);
                return;
            } catch (RemoteException e) {
                gr.W("Could not start In-App purchase.");
                return;
            }
        }
        gr.W("InAppPurchaseListener is not set. Try to launch default purchase flow.");
        if (GooglePlayServicesUtil.isGooglePlayServicesAvailable(this.lB.lM) != 0) {
            gr.W("Google Play Service unavailable, cannot launch default purchase flow.");
            return;
        }
        if (this.lB.lX == null) {
            gr.W("PlayStorePurchaseListener is not set.");
            return;
        }
        if (this.lB.md == null) {
            gr.W("PlayStorePurchaseVerifier is not initialized.");
            return;
        }
        if (this.lB.mh) {
            gr.W("An in-app purchase request is already in progress, abort");
            return;
        }
        this.lB.mh = true;
        try {
            if (this.lB.lX.isValidPurchase(str)) {
                ee.a(this.lB.lM, this.lB.lO.wV, new ea(this.lB.lM, this.lB.md, edVar, this));
            } else {
                this.lB.mh = false;
            }
        } catch (RemoteException e2) {
            gr.W("Could not start In-App purchase.");
            this.lB.mh = false;
        }
    }

    @Override // com.google.android.gms.internal.ej
    public void a(String str, boolean z, int i, final Intent intent, ef efVar) {
        try {
            if (this.lB.lX != null) {
                this.lB.lX.a(new eg(this.lB.lM, str, z, i, intent, efVar));
            }
        } catch (RemoteException e) {
            gr.W("Fail to invoke PlayStorePurchaseListener.");
        }
        gq.wR.postDelayed(new Runnable() { // from class: com.google.android.gms.internal.u.4
            @Override // java.lang.Runnable
            public void run() {
                if (ei.d(intent) == 0 && u.this.lB.lT != null && u.this.lB.lT.se != null && u.this.lB.lT.se.dC() != null) {
                    u.this.lB.lT.se.dC().close();
                }
                u.this.lB.mh = false;
            }
        }, 500L);
    }

    @Override // com.google.android.gms.internal.gc
    public void a(HashSet<fz> hashSet) {
        this.lB.a(hashSet);
    }

    public void a(List<String> list) {
        jx.aU("setNativeTemplates must be called on the main UI thread.");
        this.lB.mc = list;
    }

    @Override // com.google.android.gms.internal.bd
    public boolean a(av avVar) {
        jx.aU("loadAd must be called on the main UI thread.");
        if (this.lB.lQ != null || this.lB.lR != null) {
            if (this.lz != null) {
                gr.W("Aborting last ad request since another ad request is already in progress. The current request object will still be cached for future refreshes.");
            }
            this.lz = avVar;
            return false;
        }
        if (this.lB.lS.oq && this.lB.lT != null) {
            gr.W("An interstitial is already loading. Aborting.");
            return false;
        }
        if (!au()) {
            return false;
        }
        gr.U("Starting ad request.");
        if (!avVar.og) {
            gr.U("Use AdRequest.Builder.addTestDevice(\"" + gq.v(this.lB.lM) + "\") to get test ads on this device.");
        }
        Bundle bundleA = a(ga.dc().l(this.lB.lM));
        this.lC.cancel();
        this.lB.mg = 0;
        this.lB.lQ = ez.a(this.lB.lM, a(avVar, bundleA), this.lB.lN, this);
        return true;
    }

    boolean a(fy fyVar, boolean z) {
        av avVar;
        boolean z2 = false;
        if (this.lz != null) {
            avVar = this.lz;
            this.lz = null;
        } else {
            avVar = fyVar.tL;
            if (avVar.extras != null) {
                z2 = avVar.extras.getBoolean("_noRefresh", false);
            }
        }
        boolean z3 = z2 | z;
        if (this.lB.lS.oq) {
            if (this.lB.mg == 0) {
                gi.a(fyVar.se);
            }
        } else if (!z3 && this.lB.mg == 0) {
            if (fyVar.qA > 0) {
                this.lC.a(avVar, fyVar.qA);
            } else if (fyVar.vE != null && fyVar.vE.qA > 0) {
                this.lC.a(avVar, fyVar.vE.qA);
            } else if (!fyVar.tW && fyVar.errorCode == 2) {
                this.lC.c(avVar);
            }
        }
        return this.lC.aD();
    }

    @Override // com.google.android.gms.internal.bd
    public com.google.android.gms.dynamic.d ab() {
        jx.aU("getAdFrame must be called on the main UI thread.");
        return com.google.android.gms.dynamic.e.k(this.lB.lK);
    }

    @Override // com.google.android.gms.internal.bd
    public ay ac() {
        jx.aU("getAdSize must be called on the main UI thread.");
        return this.lB.lS;
    }

    @Override // com.google.android.gms.internal.dv
    public void af() {
        ap();
    }

    @Override // com.google.android.gms.internal.ds
    public void ag() {
        this.lD.d(this.lB.lT);
        if (this.lB.lS.oq) {
            ax();
        }
        this.lE = false;
        ao();
        this.lB.lV.cY();
    }

    @Override // com.google.android.gms.internal.ds
    public void ah() {
        if (this.lB.lS.oq) {
            e(false);
        }
        this.lE = true;
        aq();
    }

    @Override // com.google.android.gms.internal.cs
    public void ai() {
        onAdClicked();
    }

    @Override // com.google.android.gms.internal.cs
    public void aj() {
        ag();
    }

    @Override // com.google.android.gms.internal.cs
    public void ak() {
        af();
    }

    @Override // com.google.android.gms.internal.cs
    public void al() {
        ah();
    }

    @Override // com.google.android.gms.internal.cs
    public void am() {
        if (this.lB.lT != null) {
            gr.W("Mediation adapter " + this.lB.lT.qR + " refreshed, but mediation adapters should never refresh.");
        }
        e(true);
        ar();
    }

    @Override // com.google.android.gms.internal.bd
    public void an() {
        jx.aU("recordManualImpression must be called on the main UI thread.");
        if (this.lB.lT == null) {
            gr.W("Ad state was null when trying to ping manual tracking URLs.");
            return;
        }
        gr.S("Pinging manual tracking URLs.");
        if (this.lB.lT.tY != null) {
            gi.a(this.lB.lM, this.lB.lO.wS, this.lB.lT.tY);
        }
    }

    public boolean au() {
        boolean z = true;
        if (!gi.a(this.lB.lM.getPackageManager(), this.lB.lM.getPackageName(), "android.permission.INTERNET")) {
            if (!this.lB.lS.oq) {
                gq.a(this.lB.lK, this.lB.lS, "Missing internet permission in AndroidManifest.xml.", "Missing internet permission in AndroidManifest.xml. You must have the following declaration: <uses-permission android:name=\"android.permission.INTERNET\" />");
            }
            z = false;
        }
        if (!gi.p(this.lB.lM)) {
            if (!this.lB.lS.oq) {
                gq.a(this.lB.lK, this.lB.lS, "Missing AdActivity with android:configChanges in AndroidManifest.xml.", "Missing AdActivity with android:configChanges in AndroidManifest.xml. You must have the following declaration within the <application> element: <activity android:name=\"com.google.android.gms.ads.AdActivity\" android:configChanges=\"keyboard|keyboardHidden|orientation|screenLayout|uiMode|screenSize|smallestScreenSize\" />");
            }
            z = false;
        }
        if (!z && !this.lB.lS.oq) {
            this.lB.lK.setVisibility(0);
        }
        return z;
    }

    @Override // com.google.android.gms.internal.w
    public void av() {
        if (this.lB.lT == null) {
            gr.W("Ad state was null when trying to ping click URLs.");
            return;
        }
        gr.S("Pinging click URLs.");
        this.lB.lV.cX();
        if (this.lB.lT.qw != null) {
            gi.a(this.lB.lM, this.lB.lO.wS, this.lB.lT.qw);
        }
        if (this.lB.lT.vE == null || this.lB.lT.vE.qw == null) {
            return;
        }
        cw.a(this.lB.lM, this.lB.lO.wS, this.lB.lT, this.lB.lL, false, this.lB.lT.vE.qw);
    }

    @Override // com.google.android.gms.internal.w
    public void aw() {
        e(false);
    }

    @Override // com.google.android.gms.internal.w
    public void b(View view) {
        this.lB.mf = view;
        a(new fy(this.lB.lU, null, null, null, null, null, null));
    }

    public void b(av avVar) {
        Object parent = this.lB.lK.getParent();
        if ((parent instanceof View) && ((View) parent).isShown() && gi.dt() && !this.lE) {
            a(avVar);
        } else {
            gr.U("Ad is not visible. Not refreshing ad.");
            this.lC.c(avVar);
        }
    }

    @Override // com.google.android.gms.internal.cg
    public void d(boolean z) {
        this.lB.mi = z;
    }

    @Override // com.google.android.gms.internal.bd
    public void destroy() {
        jx.aU("destroy must be called on the main UI thread.");
        ae();
        this.lB.lP = null;
        this.lB.lW = null;
        this.lB.lX = null;
        this.lB.lY = null;
        this.lB.mb = null;
        this.lC.cancel();
        this.lD.stop();
        stopLoading();
        if (this.lB.lK != null) {
            this.lB.lK.removeAllViews();
        }
        if (this.lB.lT != null && this.lB.lT.se != null) {
            this.lB.lT.se.destroy();
        }
        if (this.lB.lT == null || this.lB.lT.qQ == null) {
            return;
        }
        try {
            this.lB.lT.qQ.destroy();
        } catch (RemoteException e) {
            gr.W("Could not destroy mediation adapter.");
        }
    }

    @Override // com.google.android.gms.internal.bd
    public String getMediationAdapterClassName() {
        if (this.lB.lT != null) {
            return this.lB.lT.qR;
        }
        return null;
    }

    @Override // com.google.android.gms.internal.bd
    public boolean isReady() {
        jx.aU("isLoaded must be called on the main UI thread.");
        return this.lB.lQ == null && this.lB.lR == null && this.lB.lT != null;
    }

    @Override // com.google.android.gms.internal.t
    public void onAdClicked() {
        av();
    }

    @Override // com.google.android.gms.internal.cb
    public void onAppEvent(String name, String info) {
        if (this.lB.lW != null) {
            try {
                this.lB.lW.onAppEvent(name, info);
            } catch (RemoteException e) {
                gr.d("Could not call the AppEventListener.", e);
            }
        }
    }

    @Override // com.google.android.gms.internal.bd
    public void pause() {
        jx.aU("pause must be called on the main UI thread.");
        if (this.lB.lT != null && this.lB.mg == 0) {
            gi.a(this.lB.lT.se);
        }
        if (this.lB.lT != null && this.lB.lT.qQ != null) {
            try {
                this.lB.lT.qQ.pause();
            } catch (RemoteException e) {
                gr.W("Could not pause mediation adapter.");
            }
        }
        this.lD.pause();
        this.lC.pause();
    }

    @Override // com.google.android.gms.internal.bd
    public void resume() {
        jx.aU("resume must be called on the main UI thread.");
        if (this.lB.lT != null && this.lB.mg == 0) {
            gi.b(this.lB.lT.se);
        }
        if (this.lB.lT != null && this.lB.lT.qQ != null) {
            try {
                this.lB.lT.qQ.resume();
            } catch (RemoteException e) {
                gr.W("Could not resume mediation adapter.");
            }
        }
        this.lC.resume();
        this.lD.resume();
    }

    @Override // com.google.android.gms.internal.bd
    public void showInterstitial() {
        jx.aU("showInterstitial must be called on the main UI thread.");
        if (!this.lB.lS.oq) {
            gr.W("Cannot call showInterstitial on a banner ad.");
            return;
        }
        if (this.lB.lT == null) {
            gr.W("The interstitial has not loaded.");
            return;
        }
        if (this.lB.mg != 1) {
            if (this.lB.lT.se.dH()) {
                gr.W("The interstitial is already showing.");
                return;
            }
            this.lB.lT.se.z(true);
            if (this.lB.lT.se.dD().dN() || this.lB.lT.vD != null) {
                af afVarA = this.lD.a(this.lB.lS, this.lB.lT);
                if (this.lB.lT.se.dD().dN() && afVarA != null) {
                    afVarA.a(new aa(this.lB.lT.se));
                }
            }
            if (this.lB.lT.tW) {
                try {
                    this.lB.lT.qQ.showInterstitial();
                    return;
                } catch (RemoteException e) {
                    gr.d("Could not show interstitial.", e);
                    ax();
                    return;
                }
            }
            y yVar = new y(this.lB.mi, false);
            if (this.lB.lM instanceof Activity) {
                Window window = ((Activity) this.lB.lM).getWindow();
                Rect rect = new Rect();
                Rect rect2 = new Rect();
                window.getDecorView().getGlobalVisibleRect(rect);
                window.getDecorView().getWindowVisibleDisplayFrame(rect2);
                if (rect.bottom != 0 && rect2.bottom != 0) {
                    yVar = new y(this.lB.mi, rect.top == rect2.top);
                }
            }
            dp.a(this.lB.lM, new dr(this, this, this, this.lB.lT.se, this.lB.lT.orientation, this.lB.lO, this.lB.lT.ub, yVar));
        }
    }

    @Override // com.google.android.gms.internal.bd
    public void stopLoading() {
        jx.aU("stopLoading must be called on the main UI thread.");
        if (this.lB.lT != null && this.lB.mg == 0) {
            this.lB.lT.se.stopLoading();
            this.lB.lT = null;
        }
        if (this.lB.lQ != null) {
            this.lB.lQ.cancel();
        }
        if (this.lB.lR != null) {
            this.lB.lR.cancel();
        }
    }
}
