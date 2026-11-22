package com.flurry.sdk;

import android.content.Context;
import android.content.pm.PackageManager;
import android.text.TextUtils;
import java.util.Iterator;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public final class bl implements bi {
    private static final String a = bl.class.getSimpleName();

    @Override // com.flurry.sdk.bi
    public boolean a(Context context, bm bmVar) {
        List<String> listD;
        if (bmVar == null) {
            return false;
        }
        String strA = bmVar.a();
        if (TextUtils.isEmpty(strA) || (listD = bmVar.d()) == null) {
            return false;
        }
        boolean z = true;
        PackageManager packageManager = context.getPackageManager();
        String packageName = context.getPackageName();
        Iterator<String> it = listD.iterator();
        while (true) {
            boolean z2 = z;
            if (!it.hasNext()) {
                return z2;
            }
            z = !a(strA, packageManager, packageName, it.next()) ? false : z2;
        }
    }

    private boolean a(String str, PackageManager packageManager, String str2, String str3) {
        if (TextUtils.isEmpty(str) || packageManager == null || TextUtils.isEmpty(str2) || TextUtils.isEmpty(str3)) {
            return false;
        }
        if (-1 == packageManager.checkPermission(str3, str2)) {
            gd.b(a, str + ": package=\"" + str2 + "\": AndroidManifest.xml should include uses-permission node with android:name=\"" + str3 + "\"");
            return false;
        }
        gd.a(3, a, str + ": package=\"" + str2 + "\": AndroidManifest.xml has uses-permission node with android:name=\"" + str3 + "\"");
        return true;
    }
}
