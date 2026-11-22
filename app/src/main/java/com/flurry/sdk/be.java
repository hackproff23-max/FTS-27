package com.flurry.sdk;

import android.annotation.TargetApi;
import android.content.ComponentName;
import android.content.Context;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager;
import android.text.TextUtils;
import android.util.SparseArray;
import java.util.Iterator;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public final class be implements bi {
    private static final String a = be.class.getSimpleName();
    private static final SparseArray<String> b = a();

    @Override // com.flurry.sdk.bi
    public boolean a(Context context, bm bmVar) {
        List<ActivityInfo> listE;
        if (bmVar == null) {
            return false;
        }
        String strA = bmVar.a();
        if (TextUtils.isEmpty(strA) || (listE = bmVar.e()) == null) {
            return false;
        }
        boolean z = true;
        PackageManager packageManager = context.getPackageManager();
        String packageName = context.getPackageName();
        Iterator<ActivityInfo> it = listE.iterator();
        while (true) {
            boolean z2 = z;
            if (!it.hasNext()) {
                return z2;
            }
            z = !a(strA, packageManager, packageName, it.next()) ? false : z2;
        }
    }

    private boolean a(String str, PackageManager packageManager, String str2, ActivityInfo activityInfo) {
        boolean z;
        if (TextUtils.isEmpty(str) || packageManager == null || TextUtils.isEmpty(str2) || activityInfo == null || TextUtils.isEmpty(activityInfo.name)) {
            return false;
        }
        ActivityInfo activityInfoA = cn.a(packageManager, new ComponentName(str2, activityInfo.name));
        if (activityInfoA == null) {
            gd.b(a, str + ": package=\"" + str2 + "\": AndroidManifest.xml should include activity node with android:name=\"" + activityInfo.name + "\"");
            return false;
        }
        gd.a(3, a, str + ": package=\"" + str2 + "\": AndroidManifest.xml has activity node with android:name=\"" + activityInfo.name + "\"");
        int i = activityInfo.configChanges;
        if (i == 0) {
            z = true;
        } else {
            int iA = cn.a(activityInfoA);
            SparseArray<String> sparseArrayB = b();
            z = true;
            for (int i2 = 0; i2 < sparseArrayB.size(); i2++) {
                int iKeyAt = sparseArrayB.keyAt(i2);
                if ((iKeyAt & i) != 0) {
                    if ((iKeyAt & iA) == 0) {
                        gd.b(a, str + ": package=\"" + str2 + "\": AndroidManifest.xml activity node with android:name=\"" + activityInfo.name + "\" should include android:configChanges value=\"" + sparseArrayB.valueAt(i2) + "\"");
                        z = false;
                    } else {
                        gd.a(3, a, str + ": package=\"" + str2 + "\": AndroidManifest.xml activity node with android:name=\"" + activityInfo.name + "\" has android:configChanges value=\"" + sparseArrayB.valueAt(i2) + "\"");
                    }
                }
            }
        }
        return z;
    }

    @TargetApi(13)
    private static SparseArray<String> a() {
        SparseArray<String> sparseArray = new SparseArray<>();
        sparseArray.append(1, "mcc");
        sparseArray.append(2, "mnc");
        sparseArray.append(4, "locale");
        sparseArray.append(8, "touchscreen");
        sparseArray.append(16, "keyboard");
        sparseArray.append(32, "keyboardHidden");
        sparseArray.append(64, "navigation");
        sparseArray.append(128, "orientation");
        sparseArray.append(256, "screenLayout");
        sparseArray.append(512, "uiMode");
        sparseArray.append(1024, "screenSize");
        sparseArray.append(2048, "smallestScreenSize");
        return sparseArray;
    }

    private static SparseArray<String> b() {
        return b;
    }
}
