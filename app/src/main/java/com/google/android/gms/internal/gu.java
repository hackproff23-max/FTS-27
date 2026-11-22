package com.google.android.gms.internal;

import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.content.MutableContextWrapper;
import android.net.Uri;
import android.os.Build;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.webkit.DownloadListener;
import android.webkit.ValueCallback;
import android.webkit.WebSettings;
import android.webkit.WebView;
import com.facebook.internal.ServerProtocol;
import com.google.android.gms.drive.DriveFile;
import com.mopub.nativeads.MoPubNativeAdPositioning;
import java.util.HashMap;
import java.util.Map;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes.dex */
@ey
public class gu extends WebView implements DownloadListener {
    private final Object mH;
    private final WindowManager mR;
    private ay qI;
    private final gs qJ;
    private final k tl;
    private final gv wW;
    private final a wX;
    private dp wY;
    private boolean wZ;
    private boolean xa;
    private boolean xb;
    private boolean xc;

    @ey
    protected static class a extends MutableContextWrapper {
        private Context mO;
        private Activity xd;

        public a(Context context) {
            super(context);
            setBaseContext(context);
        }

        public Context dI() {
            return this.xd;
        }

        @Override // android.content.MutableContextWrapper
        public void setBaseContext(Context base) {
            this.mO = base.getApplicationContext();
            this.xd = base instanceof Activity ? (Activity) base : null;
            super.setBaseContext(this.mO);
        }

        @Override // android.content.ContextWrapper, android.content.Context
        public void startActivity(Intent intent) {
            if (this.xd != null) {
                this.xd.startActivity(intent);
            } else {
                intent.setFlags(DriveFile.MODE_READ_ONLY);
                this.mO.startActivity(intent);
            }
        }
    }

    protected gu(a aVar, ay ayVar, boolean z, boolean z2, k kVar, gs gsVar) {
        super(aVar);
        this.mH = new Object();
        this.wX = aVar;
        this.qI = ayVar;
        this.wZ = z;
        this.tl = kVar;
        this.qJ = gsVar;
        this.mR = (WindowManager) getContext().getSystemService("window");
        setBackgroundColor(0);
        WebSettings settings = getSettings();
        settings.setJavaScriptEnabled(true);
        settings.setSavePassword(false);
        settings.setSupportMultipleWindows(true);
        settings.setJavaScriptCanOpenWindowsAutomatically(true);
        gi.a(aVar, gsVar.wS, settings);
        if (Build.VERSION.SDK_INT >= 17) {
            go.a(getContext(), settings);
        } else if (Build.VERSION.SDK_INT >= 11) {
            gm.a(getContext(), settings);
        }
        setDownloadListener(this);
        if (Build.VERSION.SDK_INT >= 11) {
            this.wW = new gx(this, z2);
        } else {
            this.wW = new gv(this, z2);
        }
        setWebViewClient(this.wW);
        if (Build.VERSION.SDK_INT >= 14) {
            setWebChromeClient(new gy(this));
        } else if (Build.VERSION.SDK_INT >= 11) {
            setWebChromeClient(new gw(this));
        }
        dJ();
    }

    public static gu a(Context context, ay ayVar, boolean z, boolean z2, k kVar, gs gsVar) {
        return new gu(new a(context), ayVar, z, z2, kVar, gsVar);
    }

    private void dJ() {
        synchronized (this.mH) {
            if (this.wZ || this.qI.oq) {
                if (Build.VERSION.SDK_INT < 14) {
                    gr.S("Disabling hardware acceleration on an overlay.");
                    dK();
                } else {
                    gr.S("Enabling hardware acceleration on an overlay.");
                    dL();
                }
            } else if (Build.VERSION.SDK_INT < 18) {
                gr.S("Disabling hardware acceleration on an AdView.");
                dK();
            } else {
                gr.S("Enabling hardware acceleration on an AdView.");
                dL();
            }
        }
    }

    private void dK() {
        synchronized (this.mH) {
            if (!this.xa && Build.VERSION.SDK_INT >= 11) {
                gm.i(this);
            }
            this.xa = true;
        }
    }

    private void dL() {
        synchronized (this.mH) {
            if (this.xa && Build.VERSION.SDK_INT >= 11) {
                gm.j(this);
            }
            this.xa = false;
        }
    }

    protected void X(String str) {
        synchronized (this.mH) {
            if (isDestroyed()) {
                gr.W("The webview is destroyed. Ignoring action.");
            } else {
                loadUrl(str);
            }
        }
    }

    public void a(Context context, ay ayVar) {
        synchronized (this.mH) {
            this.wX.setBaseContext(context);
            this.wY = null;
            this.qI = ayVar;
            this.wZ = false;
            this.xc = false;
            gi.b(this);
            loadUrl("about:blank");
            this.wW.reset();
            setOnTouchListener(null);
            setOnClickListener(null);
        }
    }

    public void a(ay ayVar) {
        synchronized (this.mH) {
            this.qI = ayVar;
            requestLayout();
        }
    }

    public void a(dp dpVar) {
        synchronized (this.mH) {
            this.wY = dpVar;
        }
    }

    public void a(String str, Map<String, ?> map) {
        try {
            b(str, gi.t(map));
        } catch (JSONException e) {
            gr.W("Could not convert parameters to JSON.");
        }
    }

    public void a(String str, JSONObject jSONObject) {
        if (jSONObject == null) {
            jSONObject = new JSONObject();
        }
        String string = jSONObject.toString();
        StringBuilder sb = new StringBuilder();
        sb.append("javascript:" + str + "(");
        sb.append(string);
        sb.append(");");
        X(sb.toString());
    }

    public ay ac() {
        ay ayVar;
        synchronized (this.mH) {
            ayVar = this.qI;
        }
        return ayVar;
    }

    public void b(String str, JSONObject jSONObject) {
        if (jSONObject == null) {
            jSONObject = new JSONObject();
        }
        String string = jSONObject.toString();
        StringBuilder sb = new StringBuilder();
        sb.append("javascript:AFMA_ReceiveMessage('");
        sb.append(str);
        sb.append("'");
        sb.append(",");
        sb.append(string);
        sb.append(");");
        gr.V("Dispatching AFMA event: " + ((Object) sb));
        X(sb.toString());
    }

    public void cb() {
        if (dD().dN()) {
            DisplayMetrics displayMetrics = new DisplayMetrics();
            Display defaultDisplay = this.mR.getDefaultDisplay();
            defaultDisplay.getMetrics(displayMetrics);
            int iS = gi.s(getContext());
            float f = 160.0f / displayMetrics.densityDpi;
            try {
                b("onScreenInfoChanged", new JSONObject().put("width", Math.round(displayMetrics.widthPixels * f)).put("height", Math.round((displayMetrics.heightPixels - iS) * f)).put("density", displayMetrics.density).put("rotation", defaultDisplay.getRotation()));
            } catch (JSONException e) {
                gr.b("Error occured while obtaining screen information.", e);
            }
        }
    }

    public void ci() {
        HashMap map = new HashMap(1);
        map.put(ServerProtocol.FALLBACK_DIALOG_PARAM_VERSION, this.qJ.wS);
        a("onshow", map);
    }

    public void cj() {
        HashMap map = new HashMap(1);
        map.put(ServerProtocol.FALLBACK_DIALOG_PARAM_VERSION, this.qJ.wS);
        a("onhide", map);
    }

    public dp dC() {
        dp dpVar;
        synchronized (this.mH) {
            dpVar = this.wY;
        }
        return dpVar;
    }

    public gv dD() {
        return this.wW;
    }

    public boolean dE() {
        return this.xc;
    }

    public k dF() {
        return this.tl;
    }

    public gs dG() {
        return this.qJ;
    }

    public boolean dH() {
        boolean z;
        synchronized (this.mH) {
            z = this.wZ;
        }
        return z;
    }

    public Context dI() {
        return this.wX.dI();
    }

    @Override // android.webkit.WebView
    public void destroy() {
        synchronized (this.mH) {
            if (this.wY != null) {
                this.wY.close();
            }
            this.xb = true;
            super.destroy();
        }
    }

    @Override // android.webkit.WebView
    public void evaluateJavascript(String script, ValueCallback<String> resultCallback) {
        synchronized (this.mH) {
            if (!isDestroyed()) {
                super.evaluateJavascript(script, resultCallback);
                return;
            }
            gr.W("The webview is destroyed. Ignoring action.");
            if (resultCallback != null) {
                resultCallback.onReceiveValue(null);
            }
        }
    }

    public boolean isDestroyed() {
        boolean z;
        synchronized (this.mH) {
            z = this.xb;
        }
        return z;
    }

    @Override // android.webkit.DownloadListener
    public void onDownloadStart(String url, String userAgent, String contentDisposition, String mimeType, long size) {
        try {
            Intent intent = new Intent("android.intent.action.VIEW");
            intent.setDataAndType(Uri.parse(url), mimeType);
            getContext().startActivity(intent);
        } catch (ActivityNotFoundException e) {
            gr.S("Couldn't find an Activity to view url/mimetype: " + url + " / " + mimeType);
        }
    }

    @Override // android.webkit.WebView, android.widget.AbsoluteLayout, android.view.View
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int i = MoPubNativeAdPositioning.MoPubClientPositioning.NO_REPEAT;
        synchronized (this.mH) {
            if (isInEditMode() || this.wZ) {
                super.onMeasure(widthMeasureSpec, heightMeasureSpec);
                return;
            }
            int mode = View.MeasureSpec.getMode(widthMeasureSpec);
            int size = View.MeasureSpec.getSize(widthMeasureSpec);
            int mode2 = View.MeasureSpec.getMode(heightMeasureSpec);
            int size2 = View.MeasureSpec.getSize(heightMeasureSpec);
            int i2 = (mode == Integer.MIN_VALUE || mode == 1073741824) ? size : Integer.MAX_VALUE;
            if (mode2 == Integer.MIN_VALUE || mode2 == 1073741824) {
                i = size2;
            }
            if (this.qI.widthPixels > i2 || this.qI.heightPixels > i) {
                float f = this.wX.getResources().getDisplayMetrics().density;
                gr.W("Not enough space to show ad. Needs " + ((int) (this.qI.widthPixels / f)) + "x" + ((int) (this.qI.heightPixels / f)) + " dp, but only has " + ((int) (size / f)) + "x" + ((int) (size2 / f)) + " dp.");
                if (getVisibility() != 8) {
                    setVisibility(4);
                }
                setMeasuredDimension(0, 0);
            } else {
                if (getVisibility() != 8) {
                    setVisibility(0);
                }
                setMeasuredDimension(this.qI.widthPixels, this.qI.heightPixels);
            }
        }
    }

    @Override // android.webkit.WebView, android.view.View
    public boolean onTouchEvent(MotionEvent event) {
        if (this.tl != null) {
            this.tl.a(event);
        }
        return super.onTouchEvent(event);
    }

    public void q(boolean z) {
        synchronized (this.mH) {
            if (this.wY != null) {
                this.wY.q(z);
            } else {
                this.xc = z;
            }
        }
    }

    public void setContext(Context context) {
        this.wX.setBaseContext(context);
    }

    public void z(boolean z) {
        synchronized (this.mH) {
            this.wZ = z;
            dJ();
        }
    }
}
