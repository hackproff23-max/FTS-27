package com.flurry.sdk;

import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import java.util.Iterator;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public final class bk implements bi {
    private static final String a = bk.class.getSimpleName();

    @Override // com.flurry.sdk.bi
    public boolean a(Context context, bm bmVar) {
        List<String> listC;
        if (bmVar == null) {
            return false;
        }
        String strA = bmVar.a();
        if (TextUtils.isEmpty(strA) || (listC = bmVar.c()) == null) {
            return false;
        }
        boolean z = true;
        Bundle bundleD = hm.d(context);
        String packageName = context.getPackageName();
        Iterator<String> it = listC.iterator();
        while (true) {
            boolean z2 = z;
            if (!it.hasNext()) {
                return z2;
            }
            z = !a(strA, packageName, bundleD, it.next()) ? false : z2;
        }
    }

    private boolean a(String str, String str2, Bundle bundle, String str3) {
        if (TextUtils.isEmpty(str) || TextUtils.isEmpty(str2) || bundle == null || TextUtils.isEmpty(str3)) {
            return false;
        }
        String string = bundle.getString(str3);
        if (TextUtils.isEmpty(string)) {
            gd.b(a, str + ": package=\"" + str2 + "\": AndroidManifest.xml should include meta-data node with android:name=\"" + str3 + "\" and not empty value for android:value");
            return false;
        }
        gd.a(3, a, str + ": package=\"" + str2 + "\": AndroidManifest.xml has meta-data node with android:name=\"" + str3 + "\" and android:value=\"" + string + "\"");
        return true;
    }
}
