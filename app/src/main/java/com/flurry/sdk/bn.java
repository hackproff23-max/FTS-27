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
public final class bn implements ef {
    private static final String a = bn.class.getSimpleName();
    private static final Map<String, ef> b = a();

    @Override // com.flurry.sdk.ef
    public ec b(Context context, r rVar) {
        ef efVarA;
        if (context == null || rVar == null) {
            return null;
        }
        if (rVar.k().a() == null) {
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
        if (!TextUtils.isEmpty(str) && (efVarA = a(str.toUpperCase(Locale.US))) != null) {
            gd.a(3, a, "Creating ad network view for type: " + str);
            ec ecVarB = efVarA.b(context, rVar);
            if (ecVarB == null) {
                gd.b(a, "Cannot create ad network view for type: " + str);
                return null;
            }
            return ecVarB;
        }
        return null;
    }

    private static Map<String, ef> a() {
        HashMap map = new HashMap();
        map.put("AdMob".toUpperCase(Locale.US), new bt());
        map.put("Millennial Media".toUpperCase(Locale.US), new bz());
        map.put("InMobi".toUpperCase(Locale.US), new bv());
        map.put("Facebook Audience Network".toUpperCase(Locale.US), new bq());
        return Collections.unmodifiableMap(map);
    }

    private static ef a(String str) {
        return b.get(str);
    }
}
