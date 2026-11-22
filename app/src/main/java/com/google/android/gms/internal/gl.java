package com.google.android.gms.internal;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.text.TextUtils;
import android.view.MotionEvent;
import java.util.Map;

/* JADX INFO: loaded from: classes.dex */
@ey
public final class gl {
    private final Context mContext;
    private int mState;
    private final float rz;
    private String wH;
    private float wI;
    private float wJ;
    private float wK;

    public gl(Context context) {
        this.mState = 0;
        this.mContext = context;
        this.rz = context.getResources().getDisplayMetrics().density;
    }

    public gl(Context context, String str) {
        this(context);
        this.wH = str;
    }

    private void a(int i, float f, float f2) {
        if (i == 0) {
            this.mState = 0;
            this.wI = f;
            this.wJ = f2;
            this.wK = f2;
            return;
        }
        if (this.mState != -1) {
            if (i != 2) {
                if (i == 1 && this.mState == 4) {
                    showDialog();
                    return;
                }
                return;
            }
            if (f2 > this.wJ) {
                this.wJ = f2;
            } else if (f2 < this.wK) {
                this.wK = f2;
            }
            if (this.wJ - this.wK > 30.0f * this.rz) {
                this.mState = -1;
                return;
            }
            if (this.mState == 0 || this.mState == 2) {
                if (f - this.wI >= 50.0f * this.rz) {
                    this.wI = f;
                    this.mState++;
                }
            } else if ((this.mState == 1 || this.mState == 3) && f - this.wI <= (-50.0f) * this.rz) {
                this.wI = f;
                this.mState++;
            }
            if (this.mState == 1 || this.mState == 3) {
                if (f > this.wI) {
                    this.wI = f;
                }
            } else {
                if (this.mState != 2 || f >= this.wI) {
                    return;
                }
                this.wI = f;
            }
        }
    }

    private void showDialog() {
        final String strTrim;
        if (TextUtils.isEmpty(this.wH)) {
            strTrim = "No debug information";
        } else {
            Uri uriBuild = new Uri.Builder().encodedQuery(this.wH).build();
            StringBuilder sb = new StringBuilder();
            Map<String, String> mapC = gi.c(uriBuild);
            for (String str : mapC.keySet()) {
                sb.append(str).append(" = ").append(mapC.get(str)).append("\n\n");
            }
            strTrim = sb.toString().trim();
            if (TextUtils.isEmpty(strTrim)) {
                strTrim = "No debug information";
            }
        }
        AlertDialog.Builder builder = new AlertDialog.Builder(this.mContext);
        builder.setMessage(strTrim);
        builder.setTitle("Ad Information");
        builder.setPositiveButton("Share", new DialogInterface.OnClickListener() { // from class: com.google.android.gms.internal.gl.1
            @Override // android.content.DialogInterface.OnClickListener
            public void onClick(DialogInterface dialog, int which) {
                gl.this.mContext.startActivity(Intent.createChooser(new Intent("android.intent.action.SEND").setType("text/plain").putExtra("android.intent.extra.TEXT", strTrim), "Share via"));
            }
        });
        builder.setNegativeButton("Close", new DialogInterface.OnClickListener() { // from class: com.google.android.gms.internal.gl.2
            @Override // android.content.DialogInterface.OnClickListener
            public void onClick(DialogInterface dialog, int which) {
            }
        });
        builder.create().show();
    }

    public void Q(String str) {
        this.wH = str;
    }

    public void c(MotionEvent motionEvent) {
        int historySize = motionEvent.getHistorySize();
        for (int i = 0; i < historySize; i++) {
            a(motionEvent.getActionMasked(), motionEvent.getHistoricalX(0, i), motionEvent.getHistoricalY(0, i));
        }
        a(motionEvent.getActionMasked(), motionEvent.getX(), motionEvent.getY());
    }
}
