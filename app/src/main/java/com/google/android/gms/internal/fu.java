package com.google.android.gms.internal;

import com.facebook.appevents.AppEventsConstants;
import com.facebook.internal.ServerProtocol;
import com.unity3d.ads.android.properties.UnityAdsConstants;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

/* JADX INFO: loaded from: classes.dex */
@ey
class fu {
    private int tq;
    private final List<String> uX;
    private final List<String> uY;
    private final String uZ;
    private final String va;
    private final String vb;
    private final String vc;
    private final boolean vd;
    private final int ve;
    private String vf;

    public fu(int i, Map<String, String> map) {
        this.vf = map.get("url");
        this.va = map.get("base_uri");
        this.vb = map.get("post_parameters");
        this.vd = parseBoolean(map.get("drt_include"));
        this.uZ = map.get("activation_overlay_url");
        this.uY = J(map.get("check_packages"));
        this.ve = parseInt(map.get("request_id"));
        this.vc = map.get(UnityAdsConstants.UNITY_ADS_ANALYTICS_QUERYPARAM_EVENTTYPE_KEY);
        this.uX = J(map.get("errors"));
        this.tq = i;
    }

    private List<String> J(String str) {
        if (str == null) {
            return null;
        }
        return Arrays.asList(str.split(","));
    }

    private static boolean parseBoolean(String bool) {
        return bool != null && (bool.equals(AppEventsConstants.EVENT_PARAM_VALUE_YES) || bool.equals(ServerProtocol.DIALOG_RETURN_SCOPES_TRUE));
    }

    private int parseInt(String i) {
        if (i == null) {
            return 0;
        }
        return Integer.parseInt(i);
    }

    public List<String> cT() {
        return this.uX;
    }

    public String cU() {
        return this.vb;
    }

    public boolean cV() {
        return this.vd;
    }

    public int getErrorCode() {
        return this.tq;
    }

    public String getType() {
        return this.vc;
    }

    public String getUrl() {
        return this.vf;
    }

    public void setUrl(String url) {
        this.vf = url;
    }
}
