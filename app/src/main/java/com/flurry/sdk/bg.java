package com.flurry.sdk;

import android.content.Context;
import android.text.TextUtils;
import java.util.Iterator;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public final class bg implements bi {
    private static final String a = bg.class.getSimpleName();

    @Override // com.flurry.sdk.bi
    public boolean a(Context context, bm bmVar) {
        List<bf> listB;
        if (bmVar == null) {
            return false;
        }
        String strA = bmVar.a();
        if (TextUtils.isEmpty(strA) || (listB = bmVar.b()) == null) {
            return false;
        }
        boolean z = true;
        String packageName = context.getPackageName();
        Iterator<bf> it = listB.iterator();
        while (true) {
            boolean z2 = z;
            if (!it.hasNext()) {
                return z2;
            }
            z = !a(strA, packageName, it.next()) ? false : z2;
        }
    }

    private boolean a(String str, String str2, bf bfVar) {
        boolean z = false;
        if (!TextUtils.isEmpty(str) && !TextUtils.isEmpty(str2) && bfVar != null) {
            try {
                if (!TextUtils.isEmpty(bfVar.c())) {
                    Class.forName(bfVar.c());
                    z = true;
                }
            } catch (ClassNotFoundException e) {
                gd.a(6, a, "failed to find third party ad provider api with exception: ", e);
            } catch (ExceptionInInitializerError e2) {
                gd.a(6, a, "failed to initialize third party ad provider api with exception: ", e2);
            } catch (LinkageError e3) {
                gd.a(6, a, "failed to link third party ad provider api with exception: ", e3);
            }
            if (!z) {
                gd.b(a, str + ": package=\"" + str2 + "\": apk should include ad provider library with name=\"" + bfVar.a() + "\" and version=\"" + bfVar.b() + "\" or higher");
            } else {
                gd.a(3, a, str + ": package=\"" + str2 + "\": apk has ad provider library with name=\"" + bfVar.a() + "\" and version=\"" + bfVar.b() + "\" or higher");
            }
        }
        return z;
    }
}
