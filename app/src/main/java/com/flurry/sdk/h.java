package com.flurry.sdk;

import android.text.TextUtils;
import com.facebook.internal.ServerProtocol;
import com.flurry.android.impl.ads.protocol.v13.AdFrame;
import com.flurry.android.impl.ads.protocol.v13.Callback;
import com.unity3d.ads.android.properties.UnityAdsConstants;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

/* JADX INFO: loaded from: classes.dex */
public class h {
    private static final String a = h.class.getSimpleName();
    private static Map<String, au> b = Collections.unmodifiableMap(new HashMap<String, au>() { // from class: com.flurry.sdk.h.1
        {
            put(UnityAdsConstants.UNITY_ADS_WEBVIEW_API_PLAYVIDEO, au.AC_MRAID_PLAY_VIDEO);
            put(UnityAdsConstants.UNITY_ADS_WEBVIEW_API_OPEN, au.AC_MRAID_OPEN);
            put("expand", au.AC_MRAID_DO_EXPAND);
            put("collapse", au.AC_MRAID_DO_COLLAPSE);
        }
    });
    private static Set<au> c = Collections.unmodifiableSet(new HashSet<au>() { // from class: com.flurry.sdk.h.2
        {
            add(au.AC_NOTIFY_USER);
            add(au.AC_NEXT_FRAME);
            add(au.AC_CLOSE_AD);
            add(au.AC_MRAID_DO_EXPAND);
            add(au.AC_MRAID_DO_COLLAPSE);
            add(au.AC_VERIFY_URL);
        }
    });
    private final fy<c> d = new fy<c>() { // from class: com.flurry.sdk.h.3
        @Override // com.flurry.sdk.fy
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public void notify(c cVar) {
            gd.a(3, h.a, "Detected event was fired :" + cVar.a + " for adSpace:" + cVar.a.e().adSpace);
            h.this.a(cVar);
        }
    };

    public void a() {
        gd.a(3, a, "Unregister Event Handler ");
        fz.a().a(this.d);
    }

    public void b() {
        gd.a(3, a, "Registered Event Handler ");
        fz.a().a("com.flurry.android.impl.ads.AdEvent", this.d);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(c cVar) {
        b bVar = cVar.a;
        String strA = bVar.a.a();
        List<a> listA = a(bVar.d(), bVar);
        f.a().a(strA, 1);
        i.a().n().a(bVar);
        if (listA.isEmpty()) {
            for (Map.Entry<String, au> entry : b.entrySet()) {
                if (entry.getKey().equals(bVar.a.a())) {
                    listA.add(new a(entry.getValue(), bVar.b, bVar));
                }
            }
        }
        switch (bVar.a) {
            case EV_RENDER_FAILED:
                h(bVar, listA);
                break;
            case EV_RENDERED:
                a(bVar, listA);
                break;
            case EV_VIDEO_START:
                b(bVar, listA);
                break;
            case EV_VIDEO_FIRST_QUARTILE:
                c(bVar, listA);
                break;
            case EV_VIDEO_MIDPOINT:
                d(bVar, listA);
                break;
            case EV_VIDEO_THIRD_QUARTILE:
                e(bVar, listA);
                break;
            case EV_VIDEO_COMPLETED:
                f(bVar, listA);
                break;
            case EV_CLICKED:
                g(bVar, listA);
                break;
            case EV_USER_CONFIRMED:
                g(bVar);
                break;
            case EV_AD_WILL_CLOSE:
                j(bVar, listA);
                break;
            case EV_PRIVACY:
                for (a aVar : listA) {
                    if (aVar.a().equals(au.AC_DIRECT_OPEN)) {
                        aVar.a("is_privacy", ServerProtocol.DIALOG_RETURN_SCOPES_TRUE);
                    }
                }
                break;
            case EV_AD_CLOSED:
                c(bVar);
                break;
            case EV_REQUEST_AD_COLLAPSE:
                c(bVar);
                break;
            case INTERNAL_EV_AD_OPENED:
                a(bVar);
                break;
            case INTERNAL_EV_APP_EXIT:
                b(bVar);
                break;
            case EV_NATIVE_IMPRESSION:
                d(bVar);
                break;
            default:
                gd.a(3, a, "Event not handled: " + bVar.a + " for adSpace:" + bVar.c().p());
                break;
        }
        a(cVar, listA);
    }

    private void a(b bVar, List<a> list) {
        dt dtVarM = bVar.c().m();
        if (!dtVarM.c()) {
            cz.d(bVar.c(), bVar.a.a(), bVar.d().adGuid);
            dtVarM.b(true);
            bVar.c().a(dtVarM);
        }
    }

    private void b(b bVar, List<a> list) {
        cz.e(bVar.c(), bVar.a.a(), bVar.d().adGuid);
        dt dtVarM = bVar.c().m();
        dtVarM.a(true);
        bVar.c().a(dtVarM);
    }

    private void c(b bVar, List<a> list) {
        cz.f(bVar.c(), bVar.a.a(), bVar.d().adGuid);
        dt dtVarM = bVar.c().m();
        dtVarM.c(true);
        bVar.c().a(dtVarM);
    }

    private void d(b bVar, List<a> list) {
        cz.g(bVar.c(), bVar.a.a(), bVar.d().adGuid);
        dt dtVarM = bVar.c().m();
        dtVarM.d(true);
        bVar.c().a(dtVarM);
    }

    private void e(b bVar, List<a> list) {
        cz.h(bVar.c(), bVar.a.a(), bVar.d().adGuid);
        dt dtVarM = bVar.c().m();
        dtVarM.e(true);
        bVar.c().a(dtVarM);
    }

    private void f(b bVar, List<a> list) {
        cz.i(bVar.c(), bVar.a.a(), bVar.d().adGuid);
        i.a().l().a(bVar.b());
        i.a().l().g();
        gd.a(3, a, "initLayout onVideoCompleted " + bVar.a());
        if (bVar.e().rewardable) {
            gd.a(3, a, "Ad unit is rewardable, onVideoCompleted listener will fire");
            gd.a(3, a, "Firing onVideoCompleted, adObject=" + bVar.b());
            d dVar = new d();
            dVar.a = bVar.b();
            dVar.b = d.a.kOnVideoCompleted;
            dVar.b();
            return;
        }
        gd.a(3, a, "Ad unit is not rewardable, onVideoCompleted listener will not fire");
    }

    private void d(b bVar) {
        gd.a(3, a, "Firing onAdImpressionLogged, adObject=" + bVar.b());
        d dVar = new d();
        dVar.a = bVar.b();
        dVar.b = d.a.kOnImpressionLogged;
        dVar.b();
    }

    private void g(b bVar, List<a> list) {
        bVar.c().p();
        gd.a(3, a, "Firing onClicked, adObject=" + bVar.b());
        d dVar = new d();
        dVar.a = bVar.b();
        dVar.b = d.a.kOnClicked;
        dVar.b();
        ap apVarC = bVar.c();
        cy cyVarG = apVarC.g();
        if (cyVarG != null) {
            dt dtVarM = bVar.c().m();
            String strI = cyVarG.i();
            if (dtVarM != null && !TextUtils.isEmpty(strI)) {
                apVarC.a(dtVarM);
                i.a().o().a(bVar.a(), strI, true, bVar.b());
            }
            if (dtVarM != null && !dtVarM.h()) {
                dtVarM.g(true);
                apVarC.a(dtVarM);
                cz.c(bVar.c(), bVar.a.a(), bVar.d().adGuid);
            }
        }
    }

    private void h(b bVar, List<a> list) {
        boolean zF = f(bVar);
        if (bVar.b.remove("preRender") == null && !zF) {
            e(bVar);
        } else {
            i(bVar, list);
        }
        cz.b(bVar.c(), bVar.a.a(), bVar.d().adGuid);
        if (bVar.c().l()) {
            d();
        }
        i.a().l().c(bVar.c());
        i.a().l().a(bVar.b());
        i.a().l().g();
    }

    private void i(b bVar, List<a> list) {
        boolean z;
        Iterator<a> it = list.iterator();
        while (true) {
            if (!it.hasNext()) {
                z = true;
                break;
            }
            if (au.AC_NEXT_AD_UNIT.equals(it.next().a())) {
                z = false;
                break;
            }
        }
        if (z) {
            gd.a(3, a, "Firing onFetchFailed, adObject=" + bVar.b());
            d dVar = new d();
            dVar.a = bVar.b();
            dVar.b = d.a.kOnFetchFailed;
            dVar.b();
        }
    }

    private void e(b bVar) {
        gd.a(3, a, "Firing onRenderFailed, adObject=" + bVar.b());
        d dVar = new d();
        dVar.a = bVar.b();
        dVar.b = d.a.kOnRenderFailed;
        dVar.b();
    }

    public void a(b bVar) {
        gd.a(3, a, "Firing onOpen, adObject=" + bVar.b());
        d dVar = new d();
        dVar.a = bVar.b();
        dVar.b = d.a.kOnOpen;
        dVar.b();
    }

    public void b(b bVar) {
        gd.a(3, a, "Firing onAppExit, adObject=" + bVar.b());
        d dVar = new d();
        dVar.a = bVar.b();
        dVar.b = d.a.kOnAppExit;
        dVar.b();
        d();
    }

    private void d() {
        ea eaVar = new ea();
        eaVar.e = ea.a.CLOSE_ACTIVITY;
        eaVar.b();
    }

    public void c(b bVar) {
        gd.a(3, a, "Firing onClose, adObject=" + bVar.b());
        d dVar = new d();
        dVar.a = bVar.b();
        dVar.b = d.a.kOnClose;
        dVar.b();
        d();
    }

    private boolean f(b bVar) {
        boolean z = bVar.b.remove("binding_3rd_party") != null;
        if (bVar.e().adFrames.get(0).binding == 4) {
            return true;
        }
        return z;
    }

    private void j(b bVar, List<a> list) {
        boolean z;
        cz.a(bVar.c(), bVar.a.a(), bVar.d().adGuid);
        Iterator<a> it = list.iterator();
        while (true) {
            if (!it.hasNext()) {
                z = false;
                break;
            }
            if (c.contains(it.next().a())) {
                z = true;
                break;
            }
        }
        if (!z) {
            list.add(0, new a(au.AC_CLOSE_AD, Collections.emptyMap(), bVar));
            i.a().l().a(bVar.b());
            i.a().l().g();
        }
    }

    public List<a> a(AdFrame adFrame, b bVar) {
        ArrayList arrayList = new ArrayList();
        List<Callback> list = adFrame.callbacks;
        String strA = bVar.a.a();
        for (Callback callback : list) {
            if (callback.event.equals(strA)) {
                for (String str : callback.actions) {
                    HashMap map = new HashMap();
                    int iIndexOf = str.indexOf(63);
                    if (iIndexOf != -1) {
                        String strSubstring = str.substring(0, iIndexOf);
                        String strSubstring2 = str.substring(iIndexOf + 1);
                        if (strSubstring2.contains("%{eventParams}")) {
                            strSubstring2 = strSubstring2.replace("%{eventParams}", "");
                            map.putAll(bVar.b);
                        }
                        map.putAll(hp.h(strSubstring2));
                        str = strSubstring;
                    }
                    arrayList.add(new a(a.c(str), map, bVar));
                }
            }
        }
        return arrayList;
    }

    private void a(c cVar, List<a> list) {
        a aVar;
        a aVar2 = null;
        for (a aVar3 : list) {
            if (aVar3.a().equals(au.AC_LOG_EVENT)) {
                aVar3.a("__sendToServer", ServerProtocol.DIALOG_RETURN_SCOPES_TRUE);
                aVar = aVar3;
            } else {
                aVar = aVar2;
            }
            if (aVar3.a().equals(au.AC_LOAD_AD_COMPONENTS)) {
                for (Map.Entry<String, String> entry : aVar3.c().b.entrySet()) {
                    aVar3.a(entry.getKey(), entry.getValue());
                }
            }
            gd.d(a, aVar3.toString());
            i.a().o().a(aVar3, cVar.b + 1);
            aVar2 = aVar;
        }
        if (aVar2 == null) {
            HashMap map = new HashMap();
            map.put("__sendToServer", "false");
            a aVar4 = new a(au.AC_LOG_EVENT, map, cVar.a);
            gd.d(a, aVar4.toString());
            i.a().o().a(aVar4, cVar.b + 1);
        }
    }

    private void g(b bVar) {
        i.a().l().a(bVar.b());
        i.a().l().g();
    }
}
