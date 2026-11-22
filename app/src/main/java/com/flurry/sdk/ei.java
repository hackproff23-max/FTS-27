package com.flurry.sdk;

import android.content.Context;
import com.flurry.android.impl.ads.protocol.v13.AdFrame;
import com.flurry.android.impl.ads.protocol.v13.AdUnit;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/* JADX INFO: loaded from: classes.dex */
public final class ei implements ef {
    static final String a = ei.class.getSimpleName();
    private static final Map<String, ef> b = a();

    @Override // com.flurry.sdk.ef
    public ec b(Context context, r rVar) {
        String strA;
        if (context == null || rVar == null || (strA = a(rVar.k().a())) == null) {
            return null;
        }
        ef efVarA = a(strA);
        if (efVarA == null) {
            gd.e(a, "Cannot create ad banner for type: " + strA);
            return null;
        }
        gd.a(3, a, "Creating ad banner for type: " + strA);
        return efVarA.b(context, rVar);
    }

    private static Map<String, ef> a() {
        HashMap map = new HashMap();
        map.put("FLURRY", new a());
        map.put("THIRD_PARTY", new bn());
        return Collections.unmodifiableMap(map);
    }

    private static ef a(String str) {
        return b.get(str);
    }

    private static String a(AdUnit adUnit) {
        if (adUnit == null) {
            return null;
        }
        List<AdFrame> list = adUnit.adFrames;
        if (list == null || list.isEmpty()) {
            return null;
        }
        AdFrame adFrame = list.get(0);
        if (adFrame == null) {
            return null;
        }
        int i = adFrame.binding;
        if (adUnit.combinable == 1 || i == 2 || i == 1 || i == 3) {
            return "FLURRY";
        }
        if (i == 4) {
            return "THIRD_PARTY";
        }
        return null;
    }

    static final class a implements ef {
        private ec.a a;

        private a() {
            this.a = new ec.a() { // from class: com.flurry.sdk.ei.a.1
                @Override // com.flurry.sdk.ec.a
                public void a() {
                    a.this.a();
                }

                @Override // com.flurry.sdk.ec.a
                public void b() {
                    a.this.a();
                }

                @Override // com.flurry.sdk.ec.a
                public void c() {
                    a.this.a();
                }
            };
        }

        @Override // com.flurry.sdk.ef
        public ec b(Context context, r rVar) {
            return new eb(context, rVar, this.a, false);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void a() {
            ea eaVar = new ea();
            eaVar.e = ea.a.CLOSE_ACTIVITY;
            eaVar.b();
        }
    }
}
