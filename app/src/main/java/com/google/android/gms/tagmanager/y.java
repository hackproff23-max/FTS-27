package com.google.android.gms.tagmanager;

import android.content.Context;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

/* JADX INFO: loaded from: classes.dex */
class y implements aq {
    private static y arl;
    private static final Object xO = new Object();
    private cg aqC;
    private String arm;
    private String arn;
    private ar aro;

    private y(Context context) {
        this(as.Z(context), new cw());
    }

    y(ar arVar, cg cgVar) {
        this.aro = arVar;
        this.aqC = cgVar;
    }

    public static aq X(Context context) {
        y yVar;
        synchronized (xO) {
            if (arl == null) {
                arl = new y(context);
            }
            yVar = arl;
        }
        return yVar;
    }

    @Override // com.google.android.gms.tagmanager.aq
    public boolean cB(String str) {
        if (!this.aqC.fe()) {
            bh.W("Too many urls sent too quickly with the TagManagerSender, rate limiting invoked.");
            return false;
        }
        if (this.arm != null && this.arn != null) {
            try {
                str = this.arm + "?" + this.arn + "=" + URLEncoder.encode(str, "UTF-8");
                bh.V("Sending wrapped url hit: " + str);
            } catch (UnsupportedEncodingException e) {
                bh.d("Error wrapping URL for testing.", e);
                return false;
            }
        }
        this.aro.cE(str);
        return true;
    }
}
