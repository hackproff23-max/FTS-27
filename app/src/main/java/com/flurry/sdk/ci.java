package com.flurry.sdk;

import android.text.TextUtils;
import android.util.Pair;
import android.view.ViewGroup;
import com.flurry.android.impl.ads.protocol.v13.AdFrame;
import com.flurry.android.impl.ads.protocol.v13.AdReportedId;
import com.flurry.android.impl.ads.protocol.v13.AdRequest;
import com.flurry.android.impl.ads.protocol.v13.AdResponse;
import com.flurry.android.impl.ads.protocol.v13.AdUnit;
import com.flurry.android.impl.ads.protocol.v13.AdViewContainer;
import com.flurry.android.impl.ads.protocol.v13.AdViewType;
import com.flurry.android.impl.ads.protocol.v13.Configuration;
import com.flurry.android.impl.ads.protocol.v13.ConfigurationUnion;
import com.flurry.android.impl.ads.protocol.v13.FrequencyCapRequestInfo;
import com.flurry.android.impl.ads.protocol.v13.FrequencyCapResponseInfo;
import com.flurry.android.impl.ads.protocol.v13.Location;
import com.flurry.android.impl.ads.protocol.v13.NativeAdConfiguration;
import com.flurry.android.impl.ads.protocol.v13.StreamInfo;
import com.flurry.android.impl.ads.protocol.v13.TargetingOverride;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;

/* JADX INFO: loaded from: classes.dex */
public class ci {
    private static final String a = ci.class.getSimpleName();
    private final String d;
    private r g;
    private ap h;
    private x i;
    private List<ap> j;
    private final gh<AdRequest> b = new gh<>("ad request", new cl(AdRequest.class));
    private final gh<AdResponse> c = new gh<>("ad response", new cl(AdResponse.class));
    private final fy<ff> k = new fy<ff>() { // from class: com.flurry.sdk.ci.1
        @Override // com.flurry.sdk.fy
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public void notify(ff ffVar) {
            ci.this.e();
        }
    };
    private final List<Integer> e = Arrays.asList(0, 1, 2, 3, 4, 5);
    private a f = a.NONE;

    enum a {
        NONE,
        WAIT_FOR_REPORTED_IDS,
        BUILD_REQUEST,
        REQUEST,
        PREPROCESS
    }

    public ci(String str) {
        this.d = str;
        d();
    }

    public synchronized void a() {
        d();
    }

    public synchronized void a(r rVar, x xVar, ap apVar) {
        gd.a(3, a, "requestAd: adSpace = " + this.d);
        if (!a.NONE.equals(this.f)) {
            gd.a(3, a, "requestAds: request pending " + this.f);
        } else if (!fj.a().c()) {
            gd.a(5, a, "There is no network connectivity (requestAds will fail)");
            g();
        } else {
            this.g = rVar;
            this.h = apVar;
            this.i = xVar;
            i.a().k().b();
            if (fe.a().c()) {
                a(a.BUILD_REQUEST);
                fp.a().b(new hq() { // from class: com.flurry.sdk.ci.2
                    @Override // com.flurry.sdk.hq
                    public void safeRun() {
                        ci.this.a(ci.this.g, ci.this.h);
                    }
                });
            } else {
                gd.a(3, a, "No reported ids yet; waiting");
                a(a.WAIT_FOR_REPORTED_IDS);
            }
        }
    }

    public void b() {
        d();
    }

    private synchronized void d() {
        fn.a().a(this);
        a(a.NONE);
        this.i = null;
        this.g = null;
        this.h = null;
        this.j = null;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Code duplicated, block: B:13:0x0066 A[Catch: all -> 0x009d, TRY_ENTER, TryCatch #0 {, blocks: (B:4:0x0003, B:5:0x0005, B:7:0x0034, B:9:0x003c, B:10:0x0062, B:13:0x0066, B:15:0x006e, B:17:0x0078), top: B:22:0x0003 }] */
    /* JADX WARN: Code duplicated, block: B:15:0x006e A[Catch: all -> 0x009d, TryCatch #0 {, blocks: (B:4:0x0003, B:5:0x0005, B:7:0x0034, B:9:0x003c, B:10:0x0062, B:13:0x0066, B:15:0x006e, B:17:0x0078), top: B:22:0x0003 }] */
    public synchronized void a(a aVar) {
        if (aVar == null) {
            aVar = a.NONE;
            gd.a(3, a, "Setting state from " + this.f + " to " + aVar);
            if (!a.NONE.equals(this.f) && !a.NONE.equals(aVar)) {
                gd.a(3, a, "Adding request listeners for adspace: " + this.d);
                fz.a().a("com.flurry.android.sdk.IdProviderFinishedEvent", this.k);
            } else if (a.NONE.equals(aVar) && !a.NONE.equals(this.f)) {
                gd.a(3, a, "Removing request listeners for adspace: " + this.d);
                fz.a().a(this.k);
            }
            this.f = aVar;
        } else {
            gd.a(3, a, "Setting state from " + this.f + " to " + aVar);
            if (!a.NONE.equals(this.f)) {
                if (a.NONE.equals(aVar)) {
                    gd.a(3, a, "Removing request listeners for adspace: " + this.d);
                    fz.a().a(this.k);
                }
            } else if (a.NONE.equals(aVar)) {
                gd.a(3, a, "Removing request listeners for adspace: " + this.d);
                fz.a().a(this.k);
            }
            this.f = aVar;
        }
        throw th;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public synchronized void e() {
        if (a.WAIT_FOR_REPORTED_IDS.equals(this.f)) {
            gd.a(3, a, "Reported ids retrieved; request may continue");
            a(a.BUILD_REQUEST);
            fp.a().b(new hq() { // from class: com.flurry.sdk.ci.3
                @Override // com.flurry.sdk.hq
                public void safeRun() {
                    ci.this.a(ci.this.g, ci.this.h);
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public synchronized void a(r rVar, ap apVar) {
        AdViewType adViewType;
        boolean enableTestAds;
        Map<String, String> map;
        Location location;
        List<StreamInfo> listEmptyList;
        Map<String, String> map2;
        boolean z;
        try {
            if (a.BUILD_REQUEST.equals(this.f)) {
                a(a.REQUEST);
                ViewGroup viewGroupF = rVar.f();
                e eVarL = rVar.l();
                if (rVar instanceof q) {
                    adViewType = AdViewType.BANNER;
                } else if (rVar instanceof t) {
                    adViewType = AdViewType.INTERSTITIAL;
                } else if (rVar instanceof w) {
                    adViewType = AdViewType.NATIVE;
                } else {
                    adViewType = AdViewType.LEGACY;
                }
                Pair<Integer, Integer> pairC = hn.c(hn.j());
                int iIntValue = ((Integer) pairC.first).intValue();
                int iIntValue2 = ((Integer) pairC.second).intValue();
                Pair<Integer, Integer> pairK = hn.k();
                int iIntValue3 = ((Integer) pairK.first).intValue();
                int iIntValue4 = (viewGroupF == null || viewGroupF.getHeight() <= 0) ? ((Integer) pairK.second).intValue() : hn.a(viewGroupF.getHeight());
                int iA = (viewGroupF == null || viewGroupF.getWidth() <= 0) ? iIntValue3 : hn.a(viewGroupF.getWidth());
                AdViewContainer adViewContainer = new AdViewContainer();
                adViewContainer.screenHeight = iIntValue2;
                adViewContainer.screenWidth = iIntValue;
                adViewContainer.viewHeight = iIntValue4;
                adViewContainer.viewWidth = iA;
                adViewContainer.density = hn.e();
                adViewContainer.screenOrientation = cr.b();
                Location locationC = cr.c();
                Map<String, String> mapEmptyMap = Collections.emptyMap();
                TargetingOverride targetingOverride = new TargetingOverride();
                targetingOverride.personas = Collections.emptyList();
                targetingOverride.ageRange = -2;
                targetingOverride.gender = -2;
                if (eVarL == null) {
                    enableTestAds = false;
                    map = mapEmptyMap;
                    location = locationC;
                } else {
                    Location location2 = eVarL.getLocation();
                    if (location2 != null) {
                        locationC = location2;
                    }
                    Map<String, String> keywords = eVarL.getKeywords();
                    if (keywords != null) {
                        mapEmptyMap = keywords;
                    }
                    Integer gender = eVarL.getGender();
                    if (gender != null) {
                        targetingOverride.ageRange = gender.intValue();
                    }
                    Integer gender2 = eVarL.getGender();
                    if (gender2 != null) {
                        targetingOverride.gender = gender2.intValue();
                    }
                    enableTestAds = eVarL.getEnableTestAds();
                    map = mapEmptyMap;
                    location = locationC;
                }
                List<AdReportedId> listE = cr.e();
                List<FrequencyCapRequestInfo> listF = cr.f();
                if (AdViewType.STREAM.equals(AdViewType.STREAM)) {
                    listEmptyList = cr.g();
                } else {
                    listEmptyList = Collections.emptyList();
                }
                ArrayList arrayList = new ArrayList();
                if (eVarL != null) {
                    String fixedAdId = eVarL.getFixedAdId();
                    if (!TextUtils.isEmpty(fixedAdId)) {
                        arrayList.add("FLURRY_VIEWER");
                        arrayList.add(fixedAdId);
                    }
                }
                Map<String, String> mapEmptyMap2 = Collections.emptyMap();
                if (apVar == null) {
                    map2 = mapEmptyMap2;
                    z = false;
                } else {
                    AdUnit adUnitA = apVar.a();
                    boolean z2 = adUnitA.renderTime;
                    map2 = adUnitA.clientSideRtbPayload;
                    z = z2;
                }
                NativeAdConfiguration nativeAdConfiguration = new NativeAdConfiguration();
                List<Integer> listS = null;
                List<String> listT = null;
                if (rVar instanceof w) {
                    w wVar = (w) rVar;
                    listS = wVar.s();
                    listT = wVar.t();
                }
                if (listS == null) {
                    nativeAdConfiguration.requestedStyles = Collections.emptyList();
                } else {
                    nativeAdConfiguration.requestedStyles = listS;
                }
                if (listT == null) {
                    nativeAdConfiguration.requestedAssets = Collections.emptyList();
                } else {
                    nativeAdConfiguration.requestedAssets = listT;
                }
                String strB = i.a().h().b();
                String str = strB == null ? "" : strB;
                try {
                    AdRequest adRequest = new AdRequest();
                    adRequest.requestTime = System.currentTimeMillis();
                    adRequest.apiKey = fp.a().d();
                    adRequest.agentVersion = Integer.toString(fq.a());
                    adRequest.adViewType = adViewType;
                    adRequest.adSpaceName = this.d;
                    adRequest.sessionId = fd.a().c();
                    adRequest.adReportedIds = listE;
                    adRequest.location = location;
                    adRequest.testDevice = enableTestAds;
                    adRequest.bindings = this.e;
                    adRequest.adViewContainer = adViewContainer;
                    adRequest.locale = fg.a().c();
                    adRequest.timezone = fg.a().d();
                    adRequest.osVersion = fm.a().c();
                    adRequest.devicePlatform = fm.a().d();
                    adRequest.keywords = map;
                    adRequest.canDoSKAppStore = false;
                    adRequest.networkStatus = fd.a().h().a();
                    adRequest.frequencyCapRequestInfoList = listF;
                    adRequest.streamInfoList = listEmptyList;
                    adRequest.adTrackingEnabled = fe.a().e();
                    adRequest.preferredLanguage = Locale.getDefault().getLanguage();
                    adRequest.bcat = arrayList;
                    adRequest.userAgent = i.a().s();
                    adRequest.targetingOverride = targetingOverride;
                    adRequest.sendConfiguration = i.a().m() == null;
                    adRequest.origins = cr.d();
                    adRequest.renderTime = z;
                    adRequest.clientSideRtbPayload = map2;
                    adRequest.nativeAdConfiguration = nativeAdConfiguration;
                    adRequest.bCookie = str;
                    adRequest.appBundleId = hm.c(rVar.e());
                    byte[] bArrA = this.b.a(adRequest);
                    gk gkVar = new gk();
                    gkVar.a(j.a().g());
                    gkVar.a(20000);
                    gkVar.a(gl.a.kPost);
                    gkVar.a("Content-Type", "application/x-flurry");
                    gkVar.a("Accept", "application/x-flurry");
                    gkVar.a("FM-Checksum", Integer.toString(gh.c(bArrA)));
                    gkVar.a((gx) new gt());
                    gkVar.b(new gt());
                    gkVar.a(bArrA);
                    gkVar.a((gk.a) new gk.a<byte[], byte[]>() { // from class: com.flurry.sdk.ci.4
                        /* JADX WARN: Code duplicated, block: B:45:0x0131  */
                        @Override // com.flurry.sdk.gk.a
                        public void a(gk<byte[], byte[]> gkVar2, byte[] bArr) {
                            List<AdUnit> list;
                            Configuration configuration;
                            gd.a(3, ci.a, "AdRequest: HTTP status code is:" + gkVar2.e());
                            ci.this.j = new ArrayList();
                            List<AdUnit> listEmptyList2 = Collections.emptyList();
                            if (!gkVar2.c() || bArr == null) {
                                list = listEmptyList2;
                            } else {
                                AdResponse adResponse = null;
                                try {
                                    adResponse = (AdResponse) ci.this.c.d(bArr);
                                } catch (Exception e) {
                                    gd.a(5, ci.a, "Failed to decode ad response: " + e);
                                }
                                if (adResponse != null) {
                                    ConfigurationUnion configurationUnion = adResponse.configuration;
                                    if (configurationUnion != null && (configuration = configurationUnion.configuration) != null) {
                                        gd.a(3, ci.a, "Ad server responded with configuration.");
                                        cg cgVar = new cg();
                                        cgVar.a = configuration;
                                        fz.a().a(cgVar);
                                    }
                                    if (adResponse.frequencyCapResponseInfoList != null) {
                                        Iterator<FrequencyCapResponseInfo> it = adResponse.frequencyCapResponseInfoList.iterator();
                                        while (it.hasNext()) {
                                            i.a().k().a(new az(it.next()));
                                        }
                                    }
                                    if (adResponse.errors.size() > 0) {
                                        gd.b(ci.a, "Ad server responded with the following error(s):");
                                        Iterator<String> it2 = adResponse.errors.iterator();
                                        while (it2.hasNext()) {
                                            gd.b(ci.a, it2.next());
                                        }
                                    }
                                    list = adResponse.adUnits != null ? adResponse.adUnits : listEmptyList2;
                                    if (!TextUtils.isEmpty(ci.this.d) && list.size() == 0) {
                                        gd.b(ci.a, "Ad server responded but sent no ad units.");
                                    }
                                } else {
                                    list = listEmptyList2;
                                }
                            }
                            for (AdUnit adUnit : list) {
                                if (adUnit.adFrames.size() != 0) {
                                    ci.this.j.add(new ap(adUnit));
                                }
                            }
                            ci.this.a(a.PREPROCESS);
                            fp.a().b(new hq() { // from class: com.flurry.sdk.ci.4.1
                                @Override // com.flurry.sdk.hq
                                public void safeRun() {
                                    ci.this.f();
                                }
                            });
                        }
                    });
                    fn.a().a(this, gkVar);
                } catch (Exception e) {
                    gd.a(5, a, "Ad request failed with exception: " + e);
                    d();
                }
            }
        } catch (Throwable th) {
            throw th;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public synchronized void f() {
        if (a.PREPROCESS.equals(this.f)) {
            for (ap apVar : this.j) {
                AdUnit adUnitA = apVar.a();
                if (adUnitA.frequencyCapResponseInfoList != null) {
                    Iterator<FrequencyCapResponseInfo> it = adUnitA.frequencyCapResponseInfoList.iterator();
                    while (it.hasNext()) {
                        i.a().k().a(new az(it.next()));
                    }
                }
                List<AdFrame> list = adUnitA.adFrames;
                for (int i = 0; i < list.size(); i++) {
                    cy cyVarA = da.a(list.get(i).display);
                    if (cyVarA != null) {
                        apVar.a(i, cyVarA);
                        if (cyVarA.d()) {
                            break;
                        }
                    }
                }
                for (int i2 = 0; i2 < list.size(); i2++) {
                    apVar.a(i2, ac.a(apVar, i2));
                }
            }
            gd.a(3, a, "Handling ad response for adSpace: " + this.d + ", size: " + this.j.size());
            if (this.j.size() > 0) {
                if (this.i != null) {
                    this.i.a(this.j);
                }
                fp.a().b(new hq() { // from class: com.flurry.sdk.ci.5
                    @Override // com.flurry.sdk.hq
                    public void safeRun() {
                        i.a().l().a(ci.this.j);
                    }
                });
            }
            g();
            d();
        }
    }

    private void g() {
        cj cjVar = new cj();
        cjVar.a = this;
        cjVar.b = this.d;
        cjVar.c = this.j;
        fz.a().a(cjVar);
    }
}
