package com.nuance.nmsp.client.sdk.oem;

import android.content.Context;
import defpackage.bm;
import defpackage.cz;
import defpackage.eo;
import java.util.Vector;

/* JADX INFO: loaded from: classes.dex */
public final class b {
    private bm a = defpackage.d.a(getClass());
    private Context b;
    private boolean c;

    public b(Vector vector) {
        int i = 0;
        this.b = null;
        this.c = false;
        if (vector == null) {
            return;
        }
        while (true) {
            int i2 = i;
            if (i2 >= vector.size()) {
                return;
            }
            cz czVar = (cz) vector.get(i2);
            String strA = czVar.a();
            if (czVar.d() == cz.a.a) {
                if (strA.equals("Android_Context")) {
                    this.b = (Context) czVar.c();
                    if (this.a.b()) {
                        this.a.b("NMSP_DEFINES_ANDROID_CONTEXT is passed in as" + this.b);
                    }
                } else if (strA.equals("Disable_Bluetooth") && new String(czVar.b()).equalsIgnoreCase("TRUE")) {
                    if (this.a.b()) {
                        this.a.b("Disable_Bluetooth is true.");
                    }
                    this.c = true;
                }
            }
            i = i2 + 1;
        }
    }

    public final boolean a() {
        if (this.c) {
            return false;
        }
        if (this.b == null) {
            if (!this.a.e()) {
                return false;
            }
            this.a.e("ANDROID_CONTEXT parameter is not passed in!!!");
            return false;
        }
        eo eoVarA = eo.a(this.b);
        boolean zB = eoVarA.b();
        eoVarA.c();
        return zB;
    }
}
