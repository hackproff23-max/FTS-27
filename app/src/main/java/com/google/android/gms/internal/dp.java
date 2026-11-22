package com.google.android.gms.internal;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.view.ViewCompat;
import android.support.v4.view.accessibility.AccessibilityEventCompat;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.view.Window;
import android.webkit.WebChromeClient;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;
import com.google.android.gms.ads.AdActivity;
import com.google.android.gms.drive.DriveFile;

/* JADX INFO: loaded from: classes.dex */
@ey
public class dp extends dx.a {
    private static final int rL = Color.argb(0, 0, 0, 0);
    private gu mo;
    private final Activity nB;
    private dr rM;
    private dt rN;
    private c rO;
    private du rP;
    private boolean rQ;
    private FrameLayout rS;
    private WebChromeClient.CustomViewCallback rT;
    private RelativeLayout rX;
    private boolean rR = false;
    private boolean rU = false;
    private boolean rV = false;
    private boolean rW = false;

    @ey
    private static final class a extends Exception {
        public a(String str) {
            super(str);
        }
    }

    @ey
    private static final class b extends RelativeLayout {
        private final gl lJ;

        public b(Context context, String str) {
            super(context);
            this.lJ = new gl(context, str);
        }

        @Override // android.view.ViewGroup
        public boolean onInterceptTouchEvent(MotionEvent event) {
            this.lJ.c(event);
            return false;
        }
    }

    @ey
    private static final class c {
        public final int index;
        public final ViewGroup.LayoutParams rZ;
        public final ViewGroup sa;

        public c(gu guVar) throws a {
            this.rZ = guVar.getLayoutParams();
            ViewParent parent = guVar.getParent();
            if (!(parent instanceof ViewGroup)) {
                throw new a("Could not get the parent of the WebView for an overlay.");
            }
            this.sa = (ViewGroup) parent;
            this.index = this.sa.indexOfChild(guVar);
            this.sa.removeView(guVar);
            guVar.z(true);
        }
    }

    public dp(Activity activity) {
        this.nB = activity;
    }

    private static RelativeLayout.LayoutParams a(int i, int i2, int i3, int i4) {
        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(i3, i4);
        layoutParams.setMargins(i, i2, 0, 0);
        layoutParams.addRule(10);
        layoutParams.addRule(9);
        return layoutParams;
    }

    public static void a(Context context, dr drVar) {
        Intent intent = new Intent();
        intent.setClassName(context, AdActivity.CLASS_NAME);
        intent.putExtra("com.google.android.gms.ads.internal.overlay.useClientJar", drVar.lO.wV);
        dr.a(intent, drVar);
        if (!ll.in()) {
            intent.addFlags(AccessibilityEventCompat.TYPE_GESTURE_DETECTION_END);
        }
        if (!(context instanceof Activity)) {
            intent.addFlags(DriveFile.MODE_READ_ONLY);
        }
        context.startActivity(intent);
    }

    @Override // com.google.android.gms.internal.dx
    public void X() {
        this.rQ = true;
    }

    public void a(View view, WebChromeClient.CustomViewCallback customViewCallback) {
        this.rS = new FrameLayout(this.nB);
        this.rS.setBackgroundColor(ViewCompat.MEASURED_STATE_MASK);
        this.rS.addView(view, -1, -1);
        this.nB.setContentView(this.rS);
        X();
        this.rT = customViewCallback;
        this.rR = true;
    }

    public void b(int i, int i2, int i3, int i4) {
        if (this.rN != null) {
            this.rN.setLayoutParams(a(i, i2, i3, i4));
        }
    }

    public void c(int i, int i2, int i3, int i4) {
        if (this.rN == null) {
            this.rN = new dt(this.nB, this.mo);
            this.rX.addView(this.rN, 0, a(i, i2, i3, i4));
            this.mo.dD().A(false);
        }
    }

    public dt ce() {
        return this.rN;
    }

    public void cf() {
        if (this.rM != null && this.rR) {
            setRequestedOrientation(this.rM.orientation);
        }
        if (this.rS != null) {
            this.nB.setContentView(this.rX);
            X();
            this.rS.removeAllViews();
            this.rS = null;
        }
        if (this.rT != null) {
            this.rT.onCustomViewHidden();
            this.rT = null;
        }
        this.rR = false;
    }

    public void cg() {
        this.rX.removeView(this.rP);
        p(true);
    }

    void ch() {
        if (!this.nB.isFinishing() || this.rV) {
            return;
        }
        this.rV = true;
        if (this.nB.isFinishing()) {
            if (this.mo != null) {
                cj();
                this.rX.removeView(this.mo);
                if (this.rO != null) {
                    this.mo.z(false);
                    this.rO.sa.addView(this.mo, this.rO.index, this.rO.rZ);
                }
            }
            if (this.rM == null || this.rM.sd == null) {
                return;
            }
            this.rM.sd.ag();
        }
    }

    void ci() {
        this.mo.ci();
    }

    void cj() {
        this.mo.cj();
    }

    public void close() {
        this.nB.finish();
    }

    @Override // com.google.android.gms.internal.dx
    public void onCreate(Bundle savedInstanceState) {
        this.rU = savedInstanceState != null ? savedInstanceState.getBoolean("com.google.android.gms.ads.internal.overlay.hasResumed", false) : false;
        try {
            this.rM = dr.b(this.nB.getIntent());
            if (this.rM == null) {
                throw new a("Could not get info for ad overlay.");
            }
            if (this.rM.sn != null) {
                this.rW = this.rM.sn.mi;
            } else {
                this.rW = false;
            }
            if (savedInstanceState == null) {
                if (this.rM.sd != null) {
                    this.rM.sd.ah();
                }
                if (this.rM.sk != 1 && this.rM.sc != null) {
                    this.rM.sc.onAdClicked();
                }
            }
            switch (this.rM.sk) {
                case 1:
                    r(false);
                    return;
                case 2:
                    this.rO = new c(this.rM.se);
                    r(false);
                    return;
                case 3:
                    r(true);
                    return;
                case 4:
                    if (this.rU) {
                        this.nB.finish();
                        return;
                    } else {
                        if (dm.a(this.nB, this.rM.sb, this.rM.sj)) {
                            return;
                        }
                        this.nB.finish();
                        return;
                    }
                default:
                    throw new a("Could not determine ad overlay type.");
            }
        } catch (a e) {
            gr.W(e.getMessage());
            this.nB.finish();
        }
    }

    @Override // com.google.android.gms.internal.dx
    public void onDestroy() {
        if (this.rN != null) {
            this.rN.destroy();
        }
        if (this.mo != null) {
            this.rX.removeView(this.mo);
        }
        ch();
    }

    @Override // com.google.android.gms.internal.dx
    public void onPause() {
        if (this.rN != null) {
            this.rN.pause();
        }
        cf();
        if (this.mo != null && (!this.nB.isFinishing() || this.rO == null)) {
            gi.a(this.mo);
        }
        ch();
    }

    @Override // com.google.android.gms.internal.dx
    public void onRestart() {
    }

    @Override // com.google.android.gms.internal.dx
    public void onResume() {
        if (this.rM != null && this.rM.sk == 4) {
            if (this.rU) {
                this.nB.finish();
            } else {
                this.rU = true;
            }
        }
        if (this.mo != null) {
            gi.b(this.mo);
        }
    }

    @Override // com.google.android.gms.internal.dx
    public void onSaveInstanceState(Bundle outBundle) {
        outBundle.putBoolean("com.google.android.gms.ads.internal.overlay.hasResumed", this.rU);
    }

    @Override // com.google.android.gms.internal.dx
    public void onStart() {
    }

    @Override // com.google.android.gms.internal.dx
    public void onStop() {
        ch();
    }

    public void p(boolean z) {
        this.rP = new du(this.nB, z ? 50 : 32);
        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(-2, -2);
        layoutParams.addRule(10);
        layoutParams.addRule(z ? 11 : 9);
        this.rP.q(this.rM.sh);
        this.rX.addView(this.rP, layoutParams);
    }

    public void q(boolean z) {
        if (this.rP != null) {
            this.rP.q(z);
        }
    }

    void r(boolean z) throws a {
        if (!this.rQ) {
            this.nB.requestWindowFeature(1);
        }
        Window window = this.nB.getWindow();
        if (!this.rW || this.rM.sn.ms) {
            window.setFlags(1024, 1024);
        }
        setRequestedOrientation(this.rM.orientation);
        if (Build.VERSION.SDK_INT >= 11) {
            gr.S("Enabling hardware acceleration on the AdActivity window.");
            gm.a(window);
        }
        this.rX = new b(this.nB, this.rM.sm);
        if (this.rW) {
            this.rX.setBackgroundColor(rL);
        } else {
            this.rX.setBackgroundColor(ViewCompat.MEASURED_STATE_MASK);
        }
        this.nB.setContentView(this.rX);
        X();
        boolean zDN = this.rM.se.dD().dN();
        if (z) {
            this.mo = gu.a(this.nB, this.rM.se.ac(), true, zDN, null, this.rM.lO);
            this.mo.dD().a(null, null, this.rM.sf, this.rM.sj, true, this.rM.sl, this.rM.se.dD().dM());
            this.mo.dD().a(new gv.a() { // from class: com.google.android.gms.internal.dp.1
                @Override // com.google.android.gms.internal.gv.a
                public void a(gu guVar) {
                    guVar.ci();
                }
            });
            if (this.rM.rH != null) {
                this.mo.loadUrl(this.rM.rH);
            } else {
                if (this.rM.si == null) {
                    throw new a("No URL or HTML to display in ad overlay.");
                }
                this.mo.loadDataWithBaseURL(this.rM.sg, this.rM.si, "text/html", "UTF-8", null);
            }
        } else {
            this.mo = this.rM.se;
            this.mo.setContext(this.nB);
        }
        this.mo.a(this);
        ViewParent parent = this.mo.getParent();
        if (parent != null && (parent instanceof ViewGroup)) {
            ((ViewGroup) parent).removeView(this.mo);
        }
        if (this.rW) {
            this.mo.setBackgroundColor(rL);
        }
        this.rX.addView(this.mo, -1, -1);
        if (!z) {
            ci();
        }
        p(zDN);
        if (this.mo.dE()) {
            q(true);
        }
    }

    public void setRequestedOrientation(int requestedOrientation) {
        this.nB.setRequestedOrientation(requestedOrientation);
    }
}
