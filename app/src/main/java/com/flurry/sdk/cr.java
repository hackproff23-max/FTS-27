package com.flurry.sdk;

import com.flurry.android.AdCreative;
import com.flurry.android.impl.ads.protocol.v13.AdFrame;
import com.flurry.android.impl.ads.protocol.v13.AdReportedId;
import com.flurry.android.impl.ads.protocol.v13.AdSpaceLayout;
import com.flurry.android.impl.ads.protocol.v13.AdUnit;
import com.flurry.android.impl.ads.protocol.v13.Callback;
import com.flurry.android.impl.ads.protocol.v13.FrequencyCapRequestInfo;
import com.flurry.android.impl.ads.protocol.v13.Location;
import com.flurry.android.impl.ads.protocol.v13.ScreenOrientationType;
import com.flurry.android.impl.ads.protocol.v13.SdkAdEvent;
import com.flurry.android.impl.ads.protocol.v13.SdkAdLog;
import com.flurry.android.impl.ads.protocol.v13.StreamInfo;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

/* JADX INFO: loaded from: classes.dex */
public class cr {
    private static final AtomicInteger a = new AtomicInteger(0);

    public static int a() {
        return a.incrementAndGet();
    }

    public static AdCreative a(AdSpaceLayout adSpaceLayout) {
        if (adSpaceLayout == null) {
            return null;
        }
        return new AdCreative(adSpaceLayout.adHeight, adSpaceLayout.adWidth, adSpaceLayout.format, adSpaceLayout.fix, adSpaceLayout.alignment);
    }

    public static AdCreative a(AdUnit adUnit) {
        AdSpaceLayout adSpaceLayout;
        if (adUnit == null) {
            return null;
        }
        List<AdFrame> list = adUnit.adFrames;
        if (list == null || list.isEmpty()) {
            return null;
        }
        AdFrame adFrame = list.get(0);
        if (adFrame != null && (adSpaceLayout = adFrame.adSpaceLayout) != null) {
            return a(adSpaceLayout);
        }
        return null;
    }

    public static ScreenOrientationType b() {
        int iJ = hn.j();
        if (iJ == 1) {
            return ScreenOrientationType.PORTRAIT;
        }
        if (iJ == 2) {
            return ScreenOrientationType.LANDSCAPE;
        }
        return ScreenOrientationType.UNKNOWN;
    }

    public static Location c() {
        Location location = new Location();
        android.location.Location locationE = fh.a().e();
        if (locationE != null) {
            double latitude = (float) locationE.getLatitude();
            double longitude = (float) locationE.getLongitude();
            location.lat = (float) hp.a(latitude, 3);
            location.lon = (float) hp.a(longitude, 3);
        }
        return location;
    }

    public static List<String> d() {
        ArrayList arrayList = new ArrayList();
        HashMap<String, Map<String, String>> mapC = fr.a().c();
        if (mapC != null && !mapC.isEmpty()) {
            Iterator<String> it = mapC.keySet().iterator();
            while (it.hasNext()) {
                arrayList.add(it.next());
            }
        }
        return arrayList;
    }

    public static List<AdReportedId> e() {
        ArrayList arrayList = new ArrayList();
        for (Map.Entry<fl, byte[]> entry : fe.a().h().entrySet()) {
            AdReportedId adReportedId = new AdReportedId();
            adReportedId.type = entry.getKey().d;
            if (entry.getKey().e) {
                adReportedId.id = new String(entry.getValue());
            } else {
                adReportedId.id = hp.b(entry.getValue());
            }
            arrayList.add(adReportedId);
        }
        return arrayList;
    }

    public static List<FrequencyCapRequestInfo> f() {
        ArrayList arrayList = new ArrayList();
        i.a().k().b();
        for (az azVar : i.a().k().a()) {
            FrequencyCapRequestInfo frequencyCapRequestInfo = new FrequencyCapRequestInfo();
            frequencyCapRequestInfo.capType = azVar.b();
            frequencyCapRequestInfo.id = azVar.c();
            frequencyCapRequestInfo.expirationTime = azVar.e();
            frequencyCapRequestInfo.serveTime = azVar.d();
            frequencyCapRequestInfo.lastViewedTime = azVar.k();
            frequencyCapRequestInfo.streamCapDurationMillis = azVar.f();
            frequencyCapRequestInfo.views = azVar.j();
            frequencyCapRequestInfo.capRemaining = azVar.g();
            frequencyCapRequestInfo.totalCap = azVar.h();
            frequencyCapRequestInfo.capDurationType = azVar.i();
            arrayList.add(frequencyCapRequestInfo);
        }
        return arrayList;
    }

    public static List<StreamInfo> g() {
        ArrayList arrayList = new ArrayList();
        i.a().n().b();
        for (bb bbVar : i.a().n().a()) {
            StreamInfo streamInfo = new StreamInfo();
            streamInfo.adId = bbVar.a();
            streamInfo.lastEvent = bbVar.e();
            streamInfo.renderedTime = bbVar.c();
            arrayList.add(streamInfo);
        }
        return arrayList;
    }

    public static List<SdkAdLog> a(List<at> list) {
        ArrayList arrayList = new ArrayList();
        if (list == null) {
            return arrayList;
        }
        for (at atVar : list) {
            SdkAdLog sdkAdLog = new SdkAdLog();
            sdkAdLog.sessionId = atVar.c();
            sdkAdLog.adLogGUID = atVar.b() == null ? "" : atVar.b();
            ArrayList arrayList2 = new ArrayList();
            synchronized (atVar) {
                for (as asVar : atVar.d()) {
                    if (asVar.b()) {
                        SdkAdEvent sdkAdEvent = new SdkAdEvent();
                        sdkAdEvent.type = asVar.a();
                        sdkAdEvent.timeOffset = asVar.c();
                        Map<String, String> mapD = asVar.d();
                        HashMap map = new HashMap();
                        map.putAll(mapD);
                        sdkAdEvent.params = map;
                        arrayList2.add(sdkAdEvent);
                    }
                }
            }
            sdkAdLog.sdkAdEvents = arrayList2;
            if (arrayList2.size() > 0) {
                arrayList.add(sdkAdLog);
            }
        }
        return arrayList;
    }

    public static List<a> a(AdFrame adFrame, b bVar) {
        ArrayList arrayList = new ArrayList();
        List<Callback> list = adFrame.callbacks;
        String strA = bVar.a.a();
        for (Callback callback : list) {
            if (callback.event.equals(strA)) {
                for (String str : callback.actions) {
                    HashMap map = new HashMap();
                    String string = str.toString();
                    int iIndexOf = string.indexOf(63);
                    if (iIndexOf != -1) {
                        String strSubstring = string.substring(0, iIndexOf);
                        String strSubstring2 = string.substring(iIndexOf + 1);
                        if (strSubstring2.contains("%{eventParams}")) {
                            strSubstring2 = strSubstring2.replace("%{eventParams}", "");
                            map.putAll(bVar.b);
                        }
                        map.putAll(hp.h(strSubstring2));
                        string = strSubstring;
                    }
                    arrayList.add(new a(a.c(string), map, bVar));
                }
            }
        }
        return arrayList;
    }
}
