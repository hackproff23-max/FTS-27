package com.google.android.gms.internal;

import android.content.Context;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

/* JADX INFO: loaded from: classes.dex */
@ey
public final class gp extends gf {
    private final Context mContext;
    private final String mG;
    private final String vf;
    private String wl;

    public gp(Context context, String str, String str2) {
        this.wl = null;
        this.mContext = context;
        this.mG = str;
        this.vf = str2;
    }

    public gp(Context context, String str, String str2, String str3) {
        this.wl = null;
        this.mContext = context;
        this.mG = str;
        this.vf = str2;
        this.wl = str3;
    }

    @Override // com.google.android.gms.internal.gf
    public void cx() {
        try {
            gr.V("Pinging URL: " + this.vf);
            HttpURLConnection httpURLConnection = (HttpURLConnection) new URL(this.vf).openConnection();
            try {
                if (this.wl == null) {
                    gi.a(this.mContext, this.mG, true, httpURLConnection);
                } else {
                    gi.a(this.mContext, this.mG, true, httpURLConnection, this.wl);
                }
                int responseCode = httpURLConnection.getResponseCode();
                if (responseCode < 200 || responseCode >= 300) {
                    gr.W("Received non-success response code " + responseCode + " from pinging URL: " + this.vf);
                }
            } finally {
                httpURLConnection.disconnect();
            }
        } catch (IOException e) {
            gr.W("Error while pinging URL: " + this.vf + ". " + e.getMessage());
        } catch (IndexOutOfBoundsException e2) {
            gr.W("Error while parsing ping URL: " + this.vf + ". " + e2.getMessage());
        }
    }

    @Override // com.google.android.gms.internal.gf
    public void onStop() {
    }
}
