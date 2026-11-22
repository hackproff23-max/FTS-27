package com.google.android.gms.internal;

import android.app.KeyguardManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Rect;
import android.os.PowerManager;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.ViewTreeObserver;
import android.view.WindowManager;
import com.facebook.appevents.AppEventsConstants;
import com.facebook.internal.ServerProtocol;
import com.flurry.android.AdCreative;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes.dex */
@ey
public final class af implements ViewTreeObserver.OnGlobalLayoutListener, ViewTreeObserver.OnScrollChangedListener {
    private final Object mH;
    private final WeakReference<fy> mK;
    private WeakReference<ViewTreeObserver> mL;
    private final WeakReference<View> mM;
    private final ad mN;
    private final Context mO;
    private final ah mP;
    private boolean mQ;
    private final WindowManager mR;
    private final PowerManager mS;
    private final KeyguardManager mT;
    private ag mU;
    private boolean mV;
    private final BlockingQueue<Runnable> mW;
    private long mX;
    private boolean mY;
    private boolean mZ;
    private boolean my;
    private BroadcastReceiver na;
    private final HashSet<ac> nb;

    public af(Context context, ay ayVar, fy fyVar, View view, gs gsVar) {
        this(ayVar, fyVar, gsVar, view, new aj(context, gsVar));
    }

    public af(ay ayVar, fy fyVar, gs gsVar, final View view, ah ahVar) {
        this.mH = new Object();
        this.my = false;
        this.mV = false;
        this.mW = new ArrayBlockingQueue(2);
        this.mX = Long.MIN_VALUE;
        this.nb = new HashSet<>();
        this.mK = new WeakReference<>(fyVar);
        this.mM = new WeakReference<>(view);
        this.mL = new WeakReference<>(null);
        this.mY = true;
        this.mN = new ad(UUID.randomUUID().toString(), gsVar, ayVar.op, fyVar.vD);
        this.mP = ahVar;
        this.mR = (WindowManager) view.getContext().getSystemService("window");
        this.mS = (PowerManager) view.getContext().getApplicationContext().getSystemService("power");
        this.mT = (KeyguardManager) view.getContext().getSystemService("keyguard");
        this.mO = view.getContext().getApplicationContext();
        a(ahVar);
        this.mP.a(new ah.a() { // from class: com.google.android.gms.internal.af.1
            @Override // com.google.android.gms.internal.ah.a
            public void aR() {
                af.this.mQ = true;
                af.this.d(view);
                af.this.aI();
            }
        });
        b(this.mP);
        try {
            final JSONObject jSONObjectE = e(view);
            this.mW.add(new Runnable() { // from class: com.google.android.gms.internal.af.2
                @Override // java.lang.Runnable
                public void run() {
                    af.this.a(jSONObjectE);
                }
            });
        } catch (Throwable th) {
        }
        this.mW.add(new Runnable() { // from class: com.google.android.gms.internal.af.3
            @Override // java.lang.Runnable
            public void run() {
                af.this.g(false);
            }
        });
        gr.S("Tracking ad unit: " + this.mN.aH());
    }

    protected int a(int i, DisplayMetrics displayMetrics) {
        return (int) (i / displayMetrics.density);
    }

    protected void a(View view, Map<String, String> map) {
        g(false);
    }

    public void a(ac acVar) {
        this.nb.add(acVar);
    }

    public void a(ag agVar) {
        synchronized (this.mH) {
            this.mU = agVar;
        }
    }

    protected void a(ah ahVar) {
        ahVar.f("https://googleads.g.doubleclick.net/mads/static/sdk/native/sdk-core-v40.html");
    }

    protected void a(JSONObject jSONObject) {
        try {
            JSONArray jSONArray = new JSONArray();
            JSONObject jSONObject2 = new JSONObject();
            jSONArray.put(jSONObject);
            jSONObject2.put("units", jSONArray);
            this.mP.a("AFMA_updateActiveView", jSONObject2);
        } catch (Throwable th) {
            gr.b("Skipping active view message.", th);
        }
    }

    protected boolean a(Map<String, String> map) {
        if (map == null) {
            return false;
        }
        String str = map.get("hashCode");
        return !TextUtils.isEmpty(str) && str.equals(this.mN.aH());
    }

    protected void aI() {
        synchronized (this.mH) {
            if (this.na != null) {
                return;
            }
            IntentFilter intentFilter = new IntentFilter();
            intentFilter.addAction("android.intent.action.SCREEN_ON");
            intentFilter.addAction("android.intent.action.SCREEN_OFF");
            this.na = new BroadcastReceiver() { // from class: com.google.android.gms.internal.af.4
                @Override // android.content.BroadcastReceiver
                public void onReceive(Context context, Intent intent) {
                    af.this.g(false);
                }
            };
            this.mO.registerReceiver(this.na, intentFilter);
        }
    }

    protected void aJ() {
        synchronized (this.mH) {
            if (this.na != null) {
                this.mO.unregisterReceiver(this.na);
                this.na = null;
            }
        }
    }

    public void aK() {
        synchronized (this.mH) {
            if (this.mY) {
                this.mZ = true;
                try {
                    a(aQ());
                } catch (JSONException e) {
                    gr.b("JSON Failure while processing active view data.", e);
                }
                gr.S("Untracking ad unit: " + this.mN.aH());
            }
        }
    }

    protected void aL() {
        if (this.mU != null) {
            this.mU.a(this);
        }
    }

    public boolean aM() {
        boolean z;
        synchronized (this.mH) {
            z = this.mY;
        }
        return z;
    }

    protected void aN() {
        View view = this.mM.get();
        if (view == null) {
            return;
        }
        ViewTreeObserver viewTreeObserver = this.mL.get();
        ViewTreeObserver viewTreeObserver2 = view.getViewTreeObserver();
        if (viewTreeObserver2 != viewTreeObserver) {
            this.mL = new WeakReference<>(viewTreeObserver2);
            viewTreeObserver2.addOnScrollChangedListener(this);
            viewTreeObserver2.addOnGlobalLayoutListener(this);
        }
    }

    protected void aO() {
        ViewTreeObserver viewTreeObserver = this.mL.get();
        if (viewTreeObserver == null || !viewTreeObserver.isAlive()) {
            return;
        }
        viewTreeObserver.removeOnScrollChangedListener(this);
        viewTreeObserver.removeGlobalOnLayoutListener(this);
    }

    protected JSONObject aP() throws JSONException {
        JSONObject jSONObject = new JSONObject();
        jSONObject.put("afmaVersion", this.mN.aF()).put("activeViewJSON", this.mN.aG()).put("timestamp", ga.dc().dd().elapsedRealtime()).put("adFormat", this.mN.aE()).put("hashCode", this.mN.aH());
        return jSONObject;
    }

    protected JSONObject aQ() throws JSONException {
        JSONObject jSONObjectAP = aP();
        jSONObjectAP.put("doneReasonCode", "u");
        return jSONObjectAP;
    }

    protected void b(ah ahVar) {
        ahVar.a("/updateActiveView", new cd() { // from class: com.google.android.gms.internal.af.5
            @Override // com.google.android.gms.internal.cd
            public void a(gu guVar, Map<String, String> map) {
                if (af.this.a(map)) {
                    af.this.a(guVar, map);
                }
            }
        });
        ahVar.a("/untrackActiveViewUnit", new cd() { // from class: com.google.android.gms.internal.af.6
            @Override // com.google.android.gms.internal.cd
            public void a(gu guVar, Map<String, String> map) {
                if (af.this.a(map)) {
                    gr.S("Received request to untrack: " + af.this.mN.aH());
                    af.this.destroy();
                }
            }
        });
        ahVar.a("/visibilityChanged", new cd() { // from class: com.google.android.gms.internal.af.7
            @Override // com.google.android.gms.internal.cd
            public void a(gu guVar, Map<String, String> map) {
                if (af.this.a(map) && map.containsKey("isVisible")) {
                    af.this.f(Boolean.valueOf(AppEventsConstants.EVENT_PARAM_VALUE_YES.equals(map.get("isVisible")) || ServerProtocol.DIALOG_RETURN_SCOPES_TRUE.equals(map.get("isVisible"))).booleanValue());
                }
            }
        });
        ahVar.a("/viewabilityChanged", cc.pQ);
    }

    protected void d(View view) {
        ArrayList arrayList = new ArrayList();
        this.mW.drainTo(arrayList);
        Iterator it = arrayList.iterator();
        while (it.hasNext()) {
            ((Runnable) it.next()).run();
        }
    }

    protected void destroy() {
        synchronized (this.mH) {
            aO();
            aJ();
            this.mY = false;
            try {
                this.mP.destroy();
            } catch (Throwable th) {
            }
            aL();
        }
    }

    protected JSONObject e(View view) throws JSONException {
        int[] iArr = new int[2];
        view.getLocationOnScreen(iArr);
        view.getLocationInWindow(new int[2]);
        JSONObject jSONObjectAP = aP();
        DisplayMetrics displayMetrics = view.getContext().getResources().getDisplayMetrics();
        Rect rect = new Rect();
        rect.left = iArr[0];
        rect.top = iArr[1];
        rect.right = rect.left + view.getWidth();
        rect.bottom = rect.top + view.getHeight();
        Rect rect2 = new Rect();
        rect2.right = this.mR.getDefaultDisplay().getWidth();
        rect2.bottom = this.mR.getDefaultDisplay().getHeight();
        Rect rect3 = new Rect();
        boolean globalVisibleRect = view.getGlobalVisibleRect(rect3, null);
        Rect rect4 = new Rect();
        boolean localVisibleRect = view.getLocalVisibleRect(rect4);
        Rect rect5 = new Rect();
        view.getHitRect(rect5);
        jSONObjectAP.put("viewBox", new JSONObject().put(AdCreative.kAlignmentTop, a(rect2.top, displayMetrics)).put(AdCreative.kAlignmentBottom, a(rect2.bottom, displayMetrics)).put(AdCreative.kAlignmentLeft, a(rect2.left, displayMetrics)).put(AdCreative.kAlignmentRight, a(rect2.right, displayMetrics))).put("adBox", new JSONObject().put(AdCreative.kAlignmentTop, a(rect.top, displayMetrics)).put(AdCreative.kAlignmentBottom, a(rect.bottom, displayMetrics)).put(AdCreative.kAlignmentLeft, a(rect.left, displayMetrics)).put(AdCreative.kAlignmentRight, a(rect.right, displayMetrics))).put("globalVisibleBox", new JSONObject().put(AdCreative.kAlignmentTop, a(rect3.top, displayMetrics)).put(AdCreative.kAlignmentBottom, a(rect3.bottom, displayMetrics)).put(AdCreative.kAlignmentLeft, a(rect3.left, displayMetrics)).put(AdCreative.kAlignmentRight, a(rect3.right, displayMetrics))).put("globalVisibleBoxVisible", globalVisibleRect).put("localVisibleBox", new JSONObject().put(AdCreative.kAlignmentTop, a(rect4.top, displayMetrics)).put(AdCreative.kAlignmentBottom, a(rect4.bottom, displayMetrics)).put(AdCreative.kAlignmentLeft, a(rect4.left, displayMetrics)).put(AdCreative.kAlignmentRight, a(rect4.right, displayMetrics))).put("localVisibleBoxVisible", localVisibleRect).put("hitBox", new JSONObject().put(AdCreative.kAlignmentTop, a(rect5.top, displayMetrics)).put(AdCreative.kAlignmentBottom, a(rect5.bottom, displayMetrics)).put(AdCreative.kAlignmentLeft, a(rect5.left, displayMetrics)).put(AdCreative.kAlignmentRight, a(rect5.right, displayMetrics))).put("windowVisibility", view.getWindowVisibility()).put("screenDensity", displayMetrics.density).put("isVisible", f(view)).put("isStopped", this.mV).put("isPaused", this.my);
        if (ll.im()) {
            jSONObjectAP.put("isAttachedToWindow", view.isAttachedToWindow());
        }
        return jSONObjectAP;
    }

    protected void f(boolean z) {
        Iterator<ac> it = this.nb.iterator();
        while (it.hasNext()) {
            it.next().a(this, z);
        }
    }

    protected boolean f(View view) {
        return view.getVisibility() == 0 && view.isShown() && this.mS.isScreenOn() && !this.mT.inKeyguardRestrictedInputMode();
    }

    protected void g(boolean z) {
        synchronized (this.mH) {
            if (this.mQ && this.mY) {
                long jElapsedRealtime = ga.dc().dd().elapsedRealtime();
                if (!z || this.mX + 200 <= jElapsedRealtime) {
                    this.mX = jElapsedRealtime;
                    fy fyVar = this.mK.get();
                    View view = this.mM.get();
                    if (view == null || fyVar == null) {
                        aK();
                        return;
                    }
                    try {
                        a(e(view));
                    } catch (JSONException e) {
                        gr.a("Active view update failed.", e);
                    }
                    aN();
                    aL();
                }
            }
        }
    }

    @Override // android.view.ViewTreeObserver.OnGlobalLayoutListener
    public void onGlobalLayout() {
        g(false);
    }

    @Override // android.view.ViewTreeObserver.OnScrollChangedListener
    public void onScrollChanged() {
        g(true);
    }

    public void pause() {
        synchronized (this.mH) {
            this.my = true;
            g(false);
            this.mP.pause();
        }
    }

    public void resume() {
        synchronized (this.mH) {
            this.mP.resume();
            this.my = false;
            g(false);
        }
    }

    public void stop() {
        synchronized (this.mH) {
            this.mV = true;
            g(false);
            this.mP.pause();
        }
    }
}
