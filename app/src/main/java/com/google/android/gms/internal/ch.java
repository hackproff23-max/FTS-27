package com.google.android.gms.internal;

import com.unity3d.ads.android.properties.UnityAdsConstants;
import java.util.HashMap;
import java.util.Map;

/* JADX INFO: loaded from: classes.dex */
@ey
public class ch implements cd {
    static final Map<String, Integer> qb = new HashMap();

    static {
        qb.put("resize", 1);
        qb.put(UnityAdsConstants.UNITY_ADS_WEBVIEW_API_PLAYVIDEO, 2);
        qb.put("storePicture", 3);
        qb.put("createCalendarEvent", 4);
    }

    @Override // com.google.android.gms.internal.cd
    public void a(gu guVar, Map<String, String> map) {
        switch (qb.get(map.get("a")).intValue()) {
            case 1:
                new di(guVar, map).execute();
                break;
            case 2:
            default:
                gr.U("Unknown MRAID command called.");
                break;
            case 3:
                new dj(guVar, map).execute();
                break;
            case 4:
                new dh(guVar, map).execute();
                break;
        }
    }
}
