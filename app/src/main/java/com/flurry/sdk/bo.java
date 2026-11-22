package com.flurry.sdk;

import android.content.Context;
import android.text.TextUtils;
import com.flurry.android.impl.ads.protocol.v13.AdFrame;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

/* JADX INFO: loaded from: classes.dex */
public final class bo implements eo {
    private static final String a = bo.class.getSimpleName();
    private static final Map<String, eo> b = a();

    @Override // com.flurry.sdk.eo
    public en a(Context context, r rVar) {
        eo eoVarA;
        if (context == null || rVar == null) {
            return null;
        }
        if (rVar.k().a() == null || rVar.k().b() == null) {
            return null;
        }
        List<AdFrame> list = rVar.k().a().adFrames;
        if (list == null || list.isEmpty()) {
            return null;
        }
        AdFrame adFrame = list.get(0);
        if (adFrame == null) {
            return null;
        }
        String str = adFrame.content;
        if (!TextUtils.isEmpty(str) && (eoVarA = a(str.toUpperCase(Locale.US))) != null) {
            gd.a(3, a, "Creating ad network takeover launcher: " + eoVarA.getClass().getSimpleName() + " for type: " + str);
            en enVarA = eoVarA.a(context, rVar);
            if (enVarA == null) {
                gd.b(a, "Cannot create ad network takeover launcher for type: " + str);
                return enVarA;
            }
            return enVarA;
        }
        return null;
    }

    private static Map<String, eo> a() {
        HashMap map = new HashMap();
        map.put("AdMob".toUpperCase(Locale.US), new bt());
        map.put("Millennial Media".toUpperCase(Locale.US), new bz());
        map.put("InMobi".toUpperCase(Locale.US), new bv());
        map.put("Facebook Audience Network".toUpperCase(Locale.US), new bq());
        return Collections.unmodifiableMap(map);
    }

    private static eo a(String str) {
        return b.get(str);
    }
}
