package com.flurry.sdk;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.text.TextUtils;
import com.facebook.internal.AnalyticsEvents;
import com.facebook.internal.ServerProtocol;
import com.flurry.android.impl.ads.protocol.v13.AdFrame;
import com.google.android.vending.expansion.downloader.Constants;
import com.google.android.vending.expansion.downloader.IDownloaderClient;
import java.util.Collections;
import java.util.Map;

/* JADX INFO: loaded from: classes.dex */
public class g {
    private static final String b = g.class.getSimpleName();
    cp a = new cp();
    private boolean c;

    public void a() {
        this.c = b((String) null);
    }

    public void a(a aVar, int i) {
        aw awVar = null;
        if (aVar.c() != null) {
            awVar = aVar.c().a;
        }
        gd.a(3, b, "performAction:action=" + aVar.toString());
        if (i > 10) {
            gd.a(5, b, "Maximum depth for event/action loop exceeded when performing action:" + aVar.toString());
            return;
        }
        switch (AnonymousClass2.a[aVar.a().ordinal()]) {
            case 1:
                a(aVar);
                break;
            case 2:
                k(aVar);
                break;
            case 3:
                j(aVar);
                break;
            case 4:
                b(aVar);
                break;
            case 5:
                c(aVar);
                break;
            case 6:
                b(aVar, i);
                break;
            case 7:
                d(aVar);
                break;
            case 8:
                e(aVar);
                break;
            case 9:
                b();
                break;
            case 10:
                f(aVar);
                break;
            case 11:
                g(aVar);
                break;
            case 12:
                c(aVar, i);
                break;
            case 13:
                d(aVar, i);
                break;
            case 14:
                h(aVar);
                break;
            case 15:
                n(aVar);
                break;
            case 16:
                e(aVar, i);
                break;
            case 17:
                m(aVar);
                break;
            case IDownloaderClient.STATE_FAILED_CANCELED /* 18 */:
                l(aVar);
                break;
            default:
                gd.a(5, b, "Unknown action:" + aVar.a() + ",triggered by:" + awVar);
                break;
        }
        i(aVar);
    }

    /* JADX INFO: renamed from: com.flurry.sdk.g$2, reason: invalid class name */
    static /* synthetic */ class AnonymousClass2 {
        static final /* synthetic */ int[] a = new int[au.values().length];

        static {
            try {
                a[au.AC_DIRECT_OPEN.ordinal()] = 1;
            } catch (NoSuchFieldError e) {
            }
            try {
                a[au.AC_MRAID_PLAY_VIDEO.ordinal()] = 2;
            } catch (NoSuchFieldError e2) {
            }
            try {
                a[au.AC_MRAID_OPEN.ordinal()] = 3;
            } catch (NoSuchFieldError e3) {
            }
            try {
                a[au.AC_DELETE.ordinal()] = 4;
            } catch (NoSuchFieldError e4) {
            }
            try {
                a[au.AC_PROCESS_REDIRECT.ordinal()] = 5;
            } catch (NoSuchFieldError e5) {
            }
            try {
                a[au.AC_VERIFY_URL.ordinal()] = 6;
            } catch (NoSuchFieldError e6) {
            }
            try {
                a[au.AC_LAUNCH_PACKAGE.ordinal()] = 7;
            } catch (NoSuchFieldError e7) {
            }
            try {
                a[au.AC_SEND_URL_ASYNC.ordinal()] = 8;
            } catch (NoSuchFieldError e8) {
            }
            try {
                a[au.AC_SEND_AD_LOGS.ordinal()] = 9;
            } catch (NoSuchFieldError e9) {
            }
            try {
                a[au.AC_LOG_EVENT.ordinal()] = 10;
            } catch (NoSuchFieldError e10) {
            }
            try {
                a[au.AC_NEXT_FRAME.ordinal()] = 11;
            } catch (NoSuchFieldError e11) {
            }
            try {
                a[au.AC_NEXT_AD_UNIT.ordinal()] = 12;
            } catch (NoSuchFieldError e12) {
            }
            try {
                a[au.AC_CHECK_CAP.ordinal()] = 13;
            } catch (NoSuchFieldError e13) {
            }
            try {
                a[au.AC_UPDATE_VIEW_COUNT.ordinal()] = 14;
            } catch (NoSuchFieldError e14) {
            }
            try {
                a[au.AC_CLOSE_AD.ordinal()] = 15;
            } catch (NoSuchFieldError e15) {
            }
            try {
                a[au.AC_NOTIFY_USER.ordinal()] = 16;
            } catch (NoSuchFieldError e16) {
            }
            try {
                a[au.AC_MRAID_DO_EXPAND.ordinal()] = 17;
            } catch (NoSuchFieldError e17) {
            }
            try {
                a[au.AC_MRAID_DO_COLLAPSE.ordinal()] = 18;
            } catch (NoSuchFieldError e18) {
            }
        }
    }

    private void i(a aVar) {
        String str = aVar.c().b.get("requiresCallComplete");
        if (!TextUtils.isEmpty(str) && str.equals(ServerProtocol.DIALOG_RETURN_SCOPES_TRUE)) {
            gd.a(3, b, "Fire call complete");
            ed edVar = new ed();
            edVar.b = aVar;
            edVar.a = ed.a.CALL_COMPLETE;
            edVar.b();
        }
    }

    private void j(a aVar) {
        Context contextA = aVar.c().a();
        r rVarB = aVar.c().b();
        ap apVarC = aVar.c().c();
        boolean zO = o(aVar);
        String strA = aVar.a("url");
        if (!TextUtils.isEmpty(strA)) {
            if (cv.d(strA)) {
                cu.a(contextA, strA);
                return;
            }
            boolean zEquals = ServerProtocol.DIALOG_RETURN_SCOPES_TRUE.equals(aVar.a(AnalyticsEvents.PARAMETER_SHARE_DIALOG_SHOW_NATIVE));
            boolean z = !ServerProtocol.DIALOG_RETURN_SCOPES_TRUE.equals(aVar.a("is_privacy"));
            if (zEquals) {
                gd.a(2, b, "Explictly instructed to use native browser");
                cu.a(contextA, this.a.a(aVar, strA), zO);
                return;
            }
            apVarC.a(true);
            if (apVarC.r()) {
                a(rVarB, strA, z, zO);
                return;
            } else {
                a(contextA, strA, zEquals ? false : true, rVarB, z, zO);
                return;
            }
        }
        gd.a(6, b, "failed to perform directOpen action: no url in " + aVar.c().a);
    }

    private void k(a aVar) {
        Context contextA = aVar.c().a();
        r rVarB = aVar.c().b();
        ap apVarC = aVar.c().c();
        boolean zO = o(aVar);
        String strA = aVar.a("url");
        if (!TextUtils.isEmpty(strA)) {
            if (cv.d(strA)) {
                cu.a(contextA, strA);
                return;
            }
            boolean zEquals = ServerProtocol.DIALOG_RETURN_SCOPES_TRUE.equals(aVar.a(AnalyticsEvents.PARAMETER_SHARE_DIALOG_SHOW_NATIVE));
            boolean z = !ServerProtocol.DIALOG_RETURN_SCOPES_TRUE.equals(aVar.a("is_privacy"));
            if (zEquals) {
                gd.a(2, b, "Explictly instructed to use native browser");
                cu.a(contextA, this.a.a(aVar, strA), zO);
                return;
            }
            apVarC.a(true);
            if (apVarC.r()) {
                a(rVarB, strA, z, zO);
                return;
            } else {
                cu.a(contextA, rVarB, strA, z, zO);
                return;
            }
        }
        gd.a(6, b, "failed to perform directOpen action: no url in " + aVar.c().a);
    }

    private void l(a aVar) {
        gd.a(3, b, "closing ad");
        ed edVar = new ed();
        edVar.b = aVar;
        edVar.c = 0;
        edVar.a = ed.a.DO_COLLAPSE;
        edVar.b();
    }

    private void m(a aVar) {
        gd.a(3, b, "expanding ad");
        ed edVar = new ed();
        edVar.b = aVar;
        edVar.c = 0;
        edVar.a = ed.a.DO_EXPAND;
        edVar.b();
    }

    private void e(a aVar, int i) {
        gd.a(3, b, "notify user");
        ed edVar = new ed();
        edVar.b = aVar;
        edVar.c = i;
        edVar.a = ed.a.SHOW_VIDEO_DIALOG;
        edVar.b();
    }

    private void n(a aVar) {
        gd.a(3, b, "closing ad");
        ed edVar = new ed();
        edVar.b = aVar;
        edVar.c = 0;
        edVar.a = ed.a.CLOSE_AD;
        edVar.b();
    }

    void a(a aVar) {
        Context contextA = aVar.c().a();
        r rVarB = aVar.c().b();
        ap apVarC = aVar.c().c();
        boolean zO = o(aVar);
        String strA = aVar.a("url");
        if (!TextUtils.isEmpty(strA)) {
            if (cv.d(strA)) {
                cu.a(contextA, strA);
                return;
            }
            boolean zEquals = ServerProtocol.DIALOG_RETURN_SCOPES_TRUE.equals(aVar.a(AnalyticsEvents.PARAMETER_SHARE_DIALOG_SHOW_NATIVE));
            boolean z = !ServerProtocol.DIALOG_RETURN_SCOPES_TRUE.equals(aVar.a("is_privacy"));
            if (zEquals) {
                gd.a(2, b, "Explictly instructed to use native browser");
                cu.a(contextA, this.a.a(aVar, strA), zO);
                return;
            }
            String strA2 = this.a.a(aVar, strA);
            if (apVarC.r()) {
                a(rVarB, strA2, z, zO);
                return;
            } else {
                a(contextA, strA2, zEquals ? false : true, rVarB, z, zO);
                return;
            }
        }
        gd.a(6, b, "failed to perform directOpen action: no url in " + aVar.c().a);
    }

    private boolean o(a aVar) {
        String strA = aVar.a("sendYCookies");
        if (TextUtils.isEmpty(strA)) {
            return false;
        }
        try {
            return Boolean.parseBoolean(strA);
        } catch (Exception e) {
            gd.a(6, b, "caught Exception with sendYCookies parameter in onProcessRedirect:" + strA);
            return false;
        }
    }

    void b(a aVar) {
        r rVarB = aVar.c().b();
        String strA = aVar.a("groupId");
        if (!TextUtils.isEmpty(strA)) {
            rVarB.a(strA);
        }
    }

    void c(a aVar) {
        boolean z;
        boolean z2;
        Context contextA = aVar.c().a();
        r rVarB = aVar.c().b();
        String strA = aVar.a("url");
        if (!TextUtils.isEmpty(strA)) {
            String strA2 = aVar.a("sendYCookies");
            if (TextUtils.isEmpty(strA2)) {
                z = false;
            } else {
                try {
                    z = Boolean.parseBoolean(strA2);
                } catch (Exception e) {
                    gd.a(6, b, "caught Exception with sendYCookies parameter in onProcessRedirect:" + strA2);
                    z = false;
                }
            }
            String strA3 = aVar.a(AnalyticsEvents.PARAMETER_SHARE_DIALOG_SHOW_NATIVE);
            if (TextUtils.isEmpty(strA3)) {
                z2 = false;
            } else {
                try {
                    z2 = Boolean.parseBoolean(strA3);
                } catch (Exception e2) {
                    gd.a(6, b, "caught Exception with useNative parameter in onProcessRedirect:" + strA3);
                    z2 = false;
                }
            }
            if (!TextUtils.isEmpty(strA)) {
                String strA4 = cv.a(this.a.a(aVar, strA));
                if (!TextUtils.isEmpty(strA4)) {
                    a(contextA, strA4, !z2, rVarB, true, z);
                }
            }
        }
    }

    void b(a aVar, int i) {
        Context contextA = aVar.c().a();
        r rVarB = aVar.c().b();
        ap apVarC = aVar.c().c();
        String strA = aVar.a("url");
        if (!TextUtils.isEmpty(strA)) {
            aw awVar = a(strA) ? aw.EV_URL_VERIFIED : aw.EV_URL_NOT_VERIFIED;
            f.a().a(awVar.a(), 1);
            co.a(awVar, Collections.emptyMap(), contextA, rVarB, apVarC, i + 1);
        }
    }

    void d(a aVar) {
        Context contextA = aVar.c().a();
        boolean zO = o(aVar);
        String strA = aVar.a("package");
        if (!TextUtils.isEmpty(strA)) {
            cu.a(contextA, strA, aVar.c().b(), zO);
        }
    }

    void e(a aVar) {
        String strA = aVar.a("url");
        if (!TextUtils.isEmpty(strA)) {
            long jCurrentTimeMillis = Constants.WATCHDOG_WAKE_TIMER + System.currentTimeMillis();
            String strA2 = aVar.a("expirationTimeEpochSeconds");
            if (!TextUtils.isEmpty(strA2)) {
                try {
                    jCurrentTimeMillis = System.currentTimeMillis() + (Long.parseLong(strA2) * 1000);
                } catch (Exception e) {
                    gd.a(6, b, "caught Exception with expirationTime parameter in onSendUrlAsync:" + strA2);
                }
            }
            boolean z = false;
            String strA3 = aVar.a("sendYCookies");
            if (!TextUtils.isEmpty(strA3)) {
                try {
                    z = Boolean.parseBoolean(strA3);
                } catch (Exception e2) {
                    gd.a(6, b, "caught Exception with sendYCookies parameter in onSendUrlAsync:" + strA3);
                }
            }
            i.a().i().b(new cd(aVar.c().a.a(), aVar.c().d().adGuid, this.a.a(aVar, strA), jCurrentTimeMillis, z));
        }
    }

    void b() {
        i.a().r();
    }

    void f(a aVar) {
        boolean z = aVar.b().containsKey("__sendToServer") && aVar.a("__sendToServer").equals(ServerProtocol.DIALOG_RETURN_SCOPES_TRUE);
        aVar.b("__sendToServer");
        String strE = aVar.c().c().e();
        aw awVar = aVar.c().a;
        Map<String, String> mapB = aVar.b();
        gd.a(3, b, "onLogEvent(" + strE + ", " + awVar + ", " + z + ", " + mapB + ")");
        i.a().a(strE, awVar, z, mapB);
    }

    void g(a aVar) {
        ap apVarC = aVar.c().c();
        int iC = apVarC.c() + 1;
        String strA = aVar.a("offset");
        if (strA != null) {
            switch (strA) {
                case "next":
                    iC = apVarC.c() + 1;
                    break;
                case "current":
                    return;
                default:
                    try {
                        iC = Integer.parseInt(strA);
                        break;
                    } catch (Exception e) {
                        gd.a(6, b, "caught: " + e.getMessage());
                        break;
                    }
                    break;
            }
        }
        f(aVar, iC);
    }

    private void f(a aVar, int i) {
        r rVarB = aVar.c().b();
        ap apVarC = aVar.c().c();
        boolean zO = o(aVar);
        gd.a(3, b, "goToFrame: triggering event = " + aVar.c().a());
        if (i != apVarC.c() && i < apVarC.a().adFrames.size()) {
            gd.a(3, b, "goToFrame: currentIndex = " + apVarC.c() + " and go to index: " + i);
            AdFrame adFrame = apVarC.a().adFrames.get(i);
            ax axVarK = apVarC.k();
            String str = adFrame.adSpaceLayout.format;
            if (!str.equalsIgnoreCase(axVarK.toString())) {
                gd.a(3, b, "goToFrame: Moving now from " + axVarK.toString() + " to format " + str);
                if (str.equalsIgnoreCase(ax.TAKEOVER.toString())) {
                    apVarC.a(i);
                    cu.a(aVar.c().a(), rVarB, true, zO);
                    return;
                }
                return;
            }
            gd.a(3, b, "goToFrame: Already a takeover Ad, just move to next frame. " + axVarK.toString() + " to format " + str);
            apVarC.a(i);
            a(rVarB, (String) null, true, zO);
        }
    }

    void c(a aVar, int i) {
        if (i > 10) {
            gd.a(5, b, "Maximum depth for event/action loop exceeded when performing action:" + aVar.toString());
            return;
        }
        String strA = aVar.a("delay");
        long j = 30;
        if (!TextUtils.isEmpty(strA)) {
            try {
                j = Long.parseLong(strA);
            } catch (Exception e) {
                gd.a(6, b, "caught Exception with delay parameter in nextAdUnit:" + strA);
            }
        }
        aVar.c().b().a(aVar.c().c(), j * 1000);
    }

    void d(a aVar, int i) {
        aw awVar;
        Context contextA = aVar.c().a();
        String strA = aVar.a("idHash");
        if (!TextUtils.isEmpty(strA)) {
            ba baVarK = i.a().k();
            for (az azVar : baVarK.a(strA)) {
                aw awVar2 = aw.EV_CAP_NOT_EXHAUSTED;
                if (azVar != null && baVarK.a(azVar.e())) {
                    gd.a(4, b, "Discarding expired frequency cap info for id=" + strA);
                    baVarK.a(azVar.b(), strA);
                    azVar = null;
                }
                if (azVar == null || azVar.j() < azVar.g()) {
                    awVar = awVar2;
                } else {
                    gd.a(4, b, "Frequency cap exhausted for id=" + strA);
                    awVar = aw.EV_CAP_EXHAUSTED;
                }
                f.a().a(awVar.a(), 1);
                co.a(awVar, Collections.emptyMap(), contextA, aVar.c().b(), aVar.c().c(), i + 1);
            }
        }
    }

    void h(a aVar) {
        String strA = aVar.a("idHash");
        if (!TextUtils.isEmpty(strA)) {
            for (az azVar : i.a().k().a(strA)) {
                azVar.a();
                gd.a(4, b, "updateViewCount:capType=" + azVar.b() + ",id=" + azVar.c() + ",capRemaining=" + azVar.g() + ",totalCap=" + azVar.h() + ",views=" + azVar.j());
                if (azVar.j() >= azVar.g()) {
                    String str = aVar.c().e().adSpace;
                    if (azVar.j() > azVar.g()) {
                        gd.a(6, b, "FlurryAdAction: !! rendering a capped object for id: " + azVar.c() + " for adspace: " + str);
                    } else {
                        gd.a(4, b, "FlurryAdAction: hit cap for id: " + azVar.c() + " for adspace: " + str);
                    }
                    ay ayVar = new ay();
                    ayVar.a = azVar;
                    ayVar.b();
                }
            }
        }
    }

    boolean a(String str) {
        Intent launchIntentForPackage = fp.a().e().getLaunchIntentForPackage(str);
        return launchIntentForPackage != null && hp.a(launchIntentForPackage);
    }

    public void a(final Context context, final String str, final boolean z, final r rVar, final boolean z2, final boolean z3) {
        if (context == null) {
            gd.a(5, b, "Unable to launch url, null context");
        } else {
            fp.a().b(new hq() { // from class: com.flurry.sdk.g.1
                @Override // com.flurry.sdk.hq
                public void safeRun() {
                    if (!TextUtils.isEmpty(str)) {
                        String strA = cv.a(str);
                        if (!TextUtils.isEmpty(strA)) {
                            boolean zC = false;
                            if (cv.d(strA)) {
                                zC = cu.a(context, strA);
                            }
                            if (!zC && cv.f(strA)) {
                                zC = cu.b(context, strA);
                            }
                            if (!zC && cv.e(strA)) {
                                zC = cu.c(context, strA);
                            }
                            if (zC && z2) {
                                g.this.a(context, rVar);
                                return;
                            }
                            ap apVarK = rVar.k();
                            if (!zC && apVarK.r()) {
                                g.this.a(rVar, strA, z2, z3);
                                return;
                            } else if (!zC && z) {
                                cu.a(context, rVar, strA, z2, z3);
                                return;
                            } else {
                                cu.a(context, strA, z3);
                                return;
                            }
                        }
                        return;
                    }
                    gd.a(6, g.b, "Failed to launch: " + str);
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(Context context, r rVar) {
        ap apVarK = rVar.k();
        if (apVarK.l()) {
            ea eaVar = new ea();
            co.a(aw.INTERNAL_EV_APP_EXIT, Collections.emptyMap(), context, rVar, apVarK, 0);
            eaVar.e = ea.a.CLOSE_ACTIVITY;
            eaVar.b();
            return;
        }
        co.a(aw.INTERNAL_EV_APP_EXIT, Collections.emptyMap(), context, rVar, apVarK, 0);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(r rVar, String str, boolean z, boolean z2) {
        ea eaVar = new ea();
        eaVar.e = ea.a.RELOAD_ACTIVITY;
        eaVar.a = rVar;
        eaVar.b = str;
        eaVar.c = z;
        eaVar.d = z2;
        eaVar.b();
    }

    public void a(Context context, String str, boolean z, r rVar) {
        if (context == null) {
            gd.a(5, b, "Cannot process redirect, null context");
        } else {
            a(context, str, z, rVar, false, false);
        }
    }

    boolean b(String str) {
        String packageName = fp.a().c().getPackageName();
        if (str == null) {
            str = "market://details?id=" + packageName;
        }
        return a(str, "android.intent.action.VIEW");
    }

    boolean a(String str, String str2) {
        Intent intent = new Intent(str2);
        intent.setData(Uri.parse(str));
        return hp.a(intent);
    }
}
