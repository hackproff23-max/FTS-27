package com.google.android.gms.internal;

import android.R;
import android.app.Activity;
import android.app.ActivityManager;
import android.app.KeyguardManager;
import android.content.Context;
import android.os.Bundle;
import android.os.PowerManager;
import android.os.Process;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.ValueCallback;
import android.webkit.WebView;
import android.widget.EditText;
import android.widget.TextView;
import java.util.List;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes.dex */
@ey
public class an extends Thread {
    private final am nE;
    private final al nF;
    private final ex nG;
    private final int nH;
    private final int nI;
    private final int nJ;
    private final int np;
    private final int nr;
    private boolean mStarted = false;
    private boolean nC = false;
    private boolean nD = false;
    private final Object mH = new Object();

    @ey
    class a {
        final int nQ;
        final int nR;

        a(int i, int i2) {
            this.nQ = i;
            this.nR = i2;
        }
    }

    public an(am amVar, al alVar, Bundle bundle, ex exVar) {
        this.nE = amVar;
        this.nF = alVar;
        this.nG = exVar;
        this.np = bundle.getInt(bn.pq.getKey());
        this.nI = bundle.getInt(bn.pr.getKey());
        this.nr = bundle.getInt(bn.ps.getKey());
        this.nJ = bundle.getInt(bn.pt.getKey());
        this.nH = bundle.getInt(bn.pu.getKey(), 10);
        setName("ContentFetchTask");
    }

    private void a(Activity activity) {
        if (activity == null) {
            return;
        }
        View viewFindViewById = null;
        if (activity.getWindow() != null && activity.getWindow().getDecorView() != null) {
            viewFindViewById = activity.getWindow().getDecorView().findViewById(R.id.content);
        }
        if (viewFindViewById != null) {
            g(viewFindViewById);
        }
    }

    private boolean a(final WebView webView, final ak akVar) {
        if (!ll.im()) {
            return false;
        }
        akVar.aW();
        webView.post(new Runnable() { // from class: com.google.android.gms.internal.an.2
            ValueCallback<String> nM = new ValueCallback<String>() { // from class: com.google.android.gms.internal.an.2.1
                @Override // android.webkit.ValueCallback
                /* JADX INFO: renamed from: k, reason: merged with bridge method [inline-methods] */
                public void onReceiveValue(String str) {
                    an.this.a(akVar, webView, str);
                }
            };

            @Override // java.lang.Runnable
            public void run() {
                if (webView.getSettings().getJavaScriptEnabled()) {
                    webView.evaluateJavascript("(function() { return  {text:document.body.innerText}})();", this.nM);
                }
            }
        });
        return true;
    }

    private boolean bb() {
        try {
            Context context = this.nE.getContext();
            if (context == null) {
                return false;
            }
            ActivityManager activityManager = (ActivityManager) context.getSystemService("activity");
            KeyguardManager keyguardManager = (KeyguardManager) context.getSystemService("keyguard");
            PowerManager powerManager = (PowerManager) context.getSystemService("power");
            if (activityManager == null || keyguardManager == null || powerManager == null) {
                return false;
            }
            List<ActivityManager.RunningAppProcessInfo> runningAppProcesses = activityManager.getRunningAppProcesses();
            if (runningAppProcesses == null) {
                return false;
            }
            for (ActivityManager.RunningAppProcessInfo runningAppProcessInfo : runningAppProcesses) {
                if (Process.myPid() == runningAppProcessInfo.pid) {
                    if (runningAppProcessInfo.importance != 100 || keyguardManager.inKeyguardRestrictedInputMode() || !powerManager.isScreenOn()) {
                        break;
                        break;
                        break;
                    }
                    return true;
                }
            }
            return false;
        } catch (Throwable th) {
            return false;
        }
    }

    a a(View view, ak akVar) {
        if (view == null) {
            return new a(0, 0);
        }
        if ((view instanceof TextView) && !(view instanceof EditText)) {
            akVar.i(((TextView) view).getText().toString());
            return new a(1, 0);
        }
        if ((view instanceof WebView) && !(view instanceof gu)) {
            akVar.aW();
            return a((WebView) view, akVar) ? new a(0, 1) : new a(0, 0);
        }
        if (!(view instanceof ViewGroup)) {
            return new a(0, 0);
        }
        ViewGroup viewGroup = (ViewGroup) view;
        int i = 0;
        int i2 = 0;
        for (int i3 = 0; i3 < viewGroup.getChildCount(); i3++) {
            a aVarA = a(viewGroup.getChildAt(i3), akVar);
            i2 += aVarA.nQ;
            i += aVarA.nR;
        }
        return new a(i2, i);
    }

    void a(ak akVar, WebView webView, String str) {
        akVar.aV();
        try {
            if (!TextUtils.isEmpty(str)) {
                String strOptString = new JSONObject(str).optString("text");
                if (TextUtils.isEmpty(webView.getTitle())) {
                    akVar.h(strOptString);
                } else {
                    akVar.h(webView.getTitle() + "\n" + strOptString);
                }
            }
            if (akVar.aS()) {
                this.nF.b(akVar);
            }
        } catch (JSONException e) {
            gr.S("Json string may be malformed.");
        } catch (Throwable th) {
            gr.a("Failed to get webview content.", th);
            this.nG.b(th);
        }
    }

    public void ba() {
        synchronized (this.mH) {
            if (this.mStarted) {
                gr.S("Content hash thread already started, quiting...");
            } else {
                this.mStarted = true;
                start();
            }
        }
    }

    public ak bc() {
        return this.nF.aZ();
    }

    public void bd() {
        synchronized (this.mH) {
            this.nC = true;
            gr.S("ContentFetchThread: paused, mPause = " + this.nC);
        }
    }

    public boolean be() {
        return this.nC;
    }

    boolean g(final View view) {
        if (view == null) {
            return false;
        }
        view.post(new Runnable() { // from class: com.google.android.gms.internal.an.1
            @Override // java.lang.Runnable
            public void run() {
                an.this.h(view);
            }
        });
        return true;
    }

    void h(View view) {
        try {
            ak akVar = new ak(this.np, this.nI, this.nr, this.nJ);
            a aVarA = a(view, akVar);
            akVar.aX();
            if (aVarA.nQ == 0 && aVarA.nR == 0) {
                return;
            }
            if (aVarA.nR == 0 && akVar.aY() == 0) {
                return;
            }
            if (aVarA.nR == 0 && this.nF.a(akVar)) {
                return;
            }
            this.nF.c(akVar);
        } catch (Exception e) {
            gr.b("Exception in fetchContentOnUIThread", e);
            this.nG.b(e);
        }
    }

    @Override // java.lang.Thread, java.lang.Runnable
    public void run() {
        while (!this.nD) {
            try {
                if (bb()) {
                    Activity activity = this.nE.getActivity();
                    if (activity == null) {
                        gr.S("ContentFetchThread: no activity");
                    } else {
                        a(activity);
                    }
                } else {
                    gr.S("ContentFetchTask: sleeping");
                    bd();
                }
                Thread.sleep(this.nH * 1000);
            } catch (Throwable th) {
                gr.b("Error in ContentFetchTask", th);
                this.nG.b(th);
            }
            synchronized (this.mH) {
                while (this.nC) {
                    try {
                        gr.S("ContentFetchTask: waiting");
                        this.mH.wait();
                    } catch (InterruptedException e) {
                    }
                }
            }
        }
    }

    public void wakeup() {
        synchronized (this.mH) {
            this.nC = false;
            this.mH.notifyAll();
            gr.S("ContentFetchThread: wakeup");
        }
    }
}
