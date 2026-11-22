package com.google.android.gms.internal;

import android.app.Activity;
import android.content.Context;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import com.flurry.android.AdCreative;
import com.google.android.gms.ads.AdSize;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes.dex */
@ey
public class di {
    static final Set<String> rk = new HashSet(Arrays.asList("top-left", "top-right", "top-center", AdCreative.kAlignmentCenter, "bottom-left", "bottom-right", "bottom-center"));
    private final Context mContext;
    private final gu mo;
    private final Map<String, String> rd;
    private int li = -1;
    private int lj = -1;
    private int rl = 0;
    private int rm = 0;
    private boolean rn = true;
    private String ro = "top-right";

    public di(gu guVar, Map<String, String> map) {
        this.mo = guVar;
        this.rd = map;
        this.mContext = guVar.dI();
    }

    private void bQ() {
        int[] iArrT = gi.t(this.mContext);
        if (!TextUtils.isEmpty(this.rd.get("width"))) {
            int iM = gi.M(this.rd.get("width"));
            if (b(iM, iArrT[0])) {
                this.li = iM;
            }
        }
        if (!TextUtils.isEmpty(this.rd.get("height"))) {
            int iM2 = gi.M(this.rd.get("height"));
            if (c(iM2, iArrT[1])) {
                this.lj = iM2;
            }
        }
        if (!TextUtils.isEmpty(this.rd.get("offsetX"))) {
            this.rl = gi.M(this.rd.get("offsetX"));
        }
        if (!TextUtils.isEmpty(this.rd.get("offsetY"))) {
            this.rm = gi.M(this.rd.get("offsetY"));
        }
        if (!TextUtils.isEmpty(this.rd.get("allowOffscreen"))) {
            this.rn = Boolean.parseBoolean(this.rd.get("allowOffscreen"));
        }
        String str = this.rd.get("customClosePosition");
        if (TextUtils.isEmpty(str) || !rk.contains(str)) {
            return;
        }
        this.ro = str;
    }

    boolean b(int i, int i2) {
        return i >= 50 && i < i2;
    }

    boolean bR() {
        return this.li > -1 && this.lj > -1;
    }

    void bS() {
        try {
            this.mo.b("onSizeChanged", new JSONObject().put("x", this.rl).put("y", this.rm).put("width", this.li).put("height", this.lj));
        } catch (JSONException e) {
            gr.b("Error occured while dispatching size change.", e);
        }
    }

    void bT() {
        try {
            this.mo.b("onStateChanged", new JSONObject().put("state", "resized"));
        } catch (JSONException e) {
            gr.b("Error occured while dispatching state change.", e);
        }
    }

    boolean c(int i, int i2) {
        return i >= 50 && i < i2;
    }

    public void execute() {
        gr.U("PLEASE IMPLEMENT mraid.resize()");
        if (this.mContext == null) {
            gr.W("Not an activity context. Cannot resize.");
            return;
        }
        if (this.mo.ac().oq) {
            gr.W("Is interstitial. Cannot resize an interstitial.");
            return;
        }
        if (this.mo.dH()) {
            gr.W("Is expanded. Cannot resize an expanded banner.");
            return;
        }
        bQ();
        if (!bR()) {
            gr.W("Invalid width and height options. Cannot resize.");
            return;
        }
        WindowManager windowManager = (WindowManager) this.mContext.getSystemService("window");
        DisplayMetrics displayMetrics = new DisplayMetrics();
        windowManager.getDefaultDisplay().getMetrics(displayMetrics);
        int iA = gq.a(displayMetrics, this.li) + 16;
        int iA2 = gq.a(displayMetrics, this.lj) + 16;
        ViewParent parent = this.mo.getParent();
        if (parent != null && (parent instanceof ViewGroup)) {
            ((ViewGroup) parent).removeView(this.mo);
        }
        LinearLayout linearLayout = new LinearLayout(this.mContext);
        linearLayout.setBackgroundColor(0);
        PopupWindow popupWindow = new PopupWindow(this.mContext);
        popupWindow.setHeight(iA2);
        popupWindow.setWidth(iA);
        popupWindow.setClippingEnabled(!this.rn);
        popupWindow.setContentView(linearLayout);
        linearLayout.addView(this.mo, -1, -1);
        popupWindow.showAtLocation(((Activity) this.mContext).getWindow().getDecorView(), 0, this.rl, this.rm);
        this.mo.a(new ay(this.mContext, new AdSize(this.li, this.lj)));
        bS();
        bT();
    }
}
