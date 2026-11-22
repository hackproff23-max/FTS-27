package com.nuance.nmsp.client.sdk.oem;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Build;
import com.google.android.vending.expansion.downloader.Constants;
import defpackage.bk;
import defpackage.bm;
import java.util.UUID;

/* JADX INFO: loaded from: classes.dex */
public class c implements bk {
    private static final bm a = defpackage.d.a(c.class);
    private static bk b;
    private Context c;

    private c() {
        this.c = null;
    }

    public c(Context context) {
        this.c = null;
        this.c = context;
        context.getSystemService("phone");
    }

    public static synchronized bk e() {
        if (b == null) {
            b = new c();
        }
        return b;
    }

    @Override // defpackage.bk
    public final String a() {
        return Build.MODEL;
    }

    @Override // defpackage.bk
    public final String b() {
        return Build.DEVICE;
    }

    @Override // defpackage.bk
    public final String c() {
        return "Android";
    }

    @Override // defpackage.bk
    public final String d() {
        return Build.VERSION.RELEASE;
    }

    public final String f() {
        SharedPreferences sharedPreferences = this.c.getSharedPreferences("nuance_sdk_pref", 0);
        String string = sharedPreferences.getString("NUANCE_NMSP_UID", "");
        if (string.length() == 0) {
            string = UUID.randomUUID().toString();
            if (string != null) {
                string = string.replaceAll(Constants.FILENAME_SEQUENCE_SEPARATOR, "");
            }
            SharedPreferences.Editor editorEdit = sharedPreferences.edit();
            editorEdit.putString("NUANCE_NMSP_UID", string);
            if (!editorEdit.commit()) {
                a.e("Storing nuance sdk uid has failed");
            }
        }
        return string == null ? "" : string;
    }
}
