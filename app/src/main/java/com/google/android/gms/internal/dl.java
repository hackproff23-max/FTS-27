package com.google.android.gms.internal;

import android.content.Context;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.WindowManager;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes.dex */
@ey
public class dl {
    private final Context mContext;
    private final WindowManager mR;
    private final gu mo;
    private int rC;
    private final bl rx;
    DisplayMetrics ry;
    private float rz;
    int rA = -1;
    int rB = -1;
    private int rD = -1;
    private int rE = -1;
    private int[] rF = new int[2];

    public dl(gu guVar, Context context, bl blVar) {
        this.mo = guVar;
        this.mContext = context;
        this.rx = blVar;
        this.mR = (WindowManager) context.getSystemService("window");
        bV();
        bW();
        bX();
    }

    private void bV() {
        this.ry = new DisplayMetrics();
        Display defaultDisplay = this.mR.getDefaultDisplay();
        defaultDisplay.getMetrics(this.ry);
        this.rz = this.ry.density;
        this.rC = defaultDisplay.getRotation();
    }

    private void bX() {
        this.mo.getLocationOnScreen(this.rF);
        this.mo.measure(0, 0);
        float f = 160.0f / this.ry.densityDpi;
        this.rD = Math.round(this.mo.getMeasuredWidth() * f);
        this.rE = Math.round(f * this.mo.getMeasuredHeight());
    }

    private dk cd() {
        return new dk.a().l(this.rx.bo()).k(this.rx.bp()).m(this.rx.bt()).n(this.rx.bq()).o(this.rx.br()).bU();
    }

    void bW() {
        int iS = gi.s(this.mContext);
        float f = 160.0f / this.ry.densityDpi;
        this.rA = Math.round(this.ry.widthPixels * f);
        this.rB = Math.round((this.ry.heightPixels - iS) * f);
    }

    public void bY() {
        cb();
        cc();
        ca();
        bZ();
    }

    public void bZ() {
        if (gr.v(2)) {
            gr.U("Dispatching Ready Event.");
        }
        this.mo.b("onReadyEventReceived", new JSONObject());
    }

    public void ca() {
        try {
            this.mo.b("onDefaultPositionReceived", new JSONObject().put("x", this.rF[0]).put("y", this.rF[1]).put("width", this.rD).put("height", this.rE));
        } catch (JSONException e) {
            gr.b("Error occured while dispatching default position.", e);
        }
    }

    public void cb() {
        try {
            this.mo.b("onScreenInfoChanged", new JSONObject().put("width", this.rA).put("height", this.rB).put("density", this.rz).put("rotation", this.rC));
        } catch (JSONException e) {
            gr.b("Error occured while obtaining screen information.", e);
        }
    }

    public void cc() {
        this.mo.b("onDeviceFeaturesReceived", cd().toJson());
    }
}
