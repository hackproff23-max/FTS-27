package com.flurry.sdk;

import android.content.Context;
import android.net.Uri;
import android.text.TextUtils;

/* JADX INFO: loaded from: classes.dex */
public class ee {
    public static ec a(Context context, r rVar, String str, ec.a aVar, boolean z, boolean z2) {
        if (context == null || rVar == null) {
            return null;
        }
        if (TextUtils.isEmpty(str)) {
            return new eb(context, rVar, aVar, z);
        }
        if (cv.g(str)) {
            ds dsVar = ds.VIDEO_AD_TYPE_CLIPS;
            if (rVar.k().q()) {
                dsVar = ds.VIDEO_AD_TYPE_MRAID;
            }
            new dr();
            dq dqVarA = dr.a(context, dsVar, rVar, aVar);
            dqVarA.setVideoUri(Uri.parse(str));
            return dqVarA;
        }
        if (z2) {
            return new eh(context, str, rVar, aVar, z);
        }
        return null;
    }
}
