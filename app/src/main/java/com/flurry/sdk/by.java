package com.flurry.sdk;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import com.inmobi.commons.InMobi;
import com.inmobi.monetization.IMErrorCode;
import com.inmobi.monetization.IMInterstitial;
import com.inmobi.monetization.IMInterstitialListener;
import java.lang.reflect.Method;
import java.util.Collections;
import java.util.Map;

/* JADX INFO: loaded from: classes.dex */
public class by extends bd {
    private static final String b = by.class.getSimpleName();
    private static final Method c = e();
    private final String d;
    private IMInterstitial e;
    private a f;

    public by(Context context, r rVar, Bundle bundle) {
        super(context, rVar);
        this.d = bundle.getString("com.flurry.inmobi.MY_APP_ID");
        InMobi.initialize((Activity) c(), this.d);
    }

    @Override // com.flurry.sdk.en
    public void a() {
        this.e = new IMInterstitial((Activity) c(), this.d);
        this.f = new a();
        a(this.e, this.f);
        this.e.loadInterstitial();
    }

    private void a(IMInterstitial iMInterstitial, a aVar) {
        if (iMInterstitial != null) {
            try {
                if (c != null) {
                    c.invoke(iMInterstitial, aVar);
                }
            } catch (Exception e) {
                gd.a(3, b, "InMobi set listener failed.");
            }
        }
    }

    private static Method e() {
        Method[] methods = IMInterstitial.class.getMethods();
        int length = methods.length;
        for (int i = 0; i < length; i++) {
            Method method = methods[i];
            String name = method.getName();
            if (name.equals("setIMInterstitialListener") || name.equals("setImInterstitialListener")) {
                return method;
            }
        }
        return null;
    }

    final class a implements IMInterstitialListener {
        private a() {
        }

        public void onInterstitialFailed(IMInterstitial iMInterstitial, IMErrorCode iMErrorCode) {
            by.this.d(Collections.emptyMap());
            gd.a(3, by.b, "InMobi imAdView ad request failed. ErrorCode  = " + iMErrorCode.toString());
        }

        public void onInterstitialLoaded(IMInterstitial iMInterstitial) {
            gd.a(3, by.b, "InMobi Interstitial ad request completed.");
            if (IMInterstitial.State.READY.equals(iMInterstitial.getState())) {
                by.this.a(Collections.emptyMap());
                iMInterstitial.show();
            }
        }

        public void onDismissInterstitialScreen(IMInterstitial iMInterstitial) {
            by.this.c(Collections.emptyMap());
            gd.a(3, by.b, "InMobi Interstitial ad dismissed.");
        }

        public void onShowInterstitialScreen(IMInterstitial iMInterstitial) {
            gd.a(3, by.b, "InMobi Interstitial ad shown.");
        }

        public void onLeaveApplication(IMInterstitial iMInterstitial) {
            by.this.b(Collections.emptyMap());
            gd.a(3, by.b, "InMobi onLeaveApplication");
        }

        public void onInterstitialInteraction(IMInterstitial iMInterstitial, Map<String, String> map) {
            by.this.b(Collections.emptyMap());
            gd.a(3, by.b, "InMobi onBannerInteraction");
        }
    }
}
