package com.unity3d.ads.android.webapp;

import android.content.Intent;
import android.net.Uri;
import android.webkit.JavascriptInterface;
import com.unity3d.ads.android.UnityAdsDeviceLog;
import com.unity3d.ads.android.properties.UnityAdsProperties;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes.dex */
public class UnityAdsWebBridge {
    private IUnityAdsWebBridgeListener a;

    private static b a(String str) {
        for (b bVar : b.values()) {
            if (bVar.toString().equals(str)) {
                return bVar;
            }
        }
        return null;
    }

    public UnityAdsWebBridge(IUnityAdsWebBridgeListener iUnityAdsWebBridgeListener) {
        this.a = null;
        this.a = iUnityAdsWebBridgeListener;
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    @JavascriptInterface
    public boolean handleWebEvent(String str, String str2) {
        JSONObject jSONObject;
        b bVarA;
        JSONObject jSONObject2 = null;
        UnityAdsDeviceLog.debug(str + ", " + str2);
        if (this.a == null || str2 == null) {
            return false;
        }
        try {
            jSONObject = new JSONObject(str2);
            try {
                jSONObject2 = jSONObject.getJSONObject("data");
            } catch (Exception e) {
                e = e;
                UnityAdsDeviceLog.error("Error while parsing parameters: " + e.getMessage());
            }
        } catch (Exception e2) {
            e = e2;
            jSONObject = null;
        }
        if (jSONObject == null || str == null || (bVarA = a(str)) == null) {
            return false;
        }
        switch (bVarA) {
            case PlayVideo:
                this.a.onPlayVideo(jSONObject2);
                return true;
            case PauseVideo:
                this.a.onPauseVideo(jSONObject2);
                return true;
            case CloseView:
                this.a.onCloseAdsView(jSONObject2);
                return true;
            case LoadComplete:
                this.a.onWebAppLoadComplete(jSONObject2);
                return true;
            case InitComplete:
                this.a.onWebAppInitComplete(jSONObject2);
                return true;
            case PlayStore:
                this.a.onOpenPlayStore(jSONObject2);
                return true;
            case NavigateTo:
                if (jSONObject2.has("clickUrl")) {
                    try {
                        String string = jSONObject2.getString("clickUrl");
                        if (string != null) {
                            try {
                                Intent intent = new Intent("android.intent.action.VIEW");
                                intent.setData(Uri.parse(string));
                                UnityAdsProperties.getCurrentActivity().startActivity(intent);
                            } catch (Exception e3) {
                                UnityAdsDeviceLog.error("Could not start activity for opening URL: " + string + ", maybe malformed URL?");
                            }
                        }
                    } catch (Exception e4) {
                        UnityAdsDeviceLog.error("Error fetching clickUrl");
                        return false;
                    }
                    break;
                }
                return true;
            default:
                return true;
        }
    }
}
