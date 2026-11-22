package com.google.android.gms.internal;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import com.google.android.gms.appindexing.Action;
import com.unity3d.ads.android.properties.UnityAdsConstants;
import java.util.ArrayList;

/* JADX INFO: loaded from: classes.dex */
public class hy {
    private static hh a(String str, mv.c cVar) {
        return new hh(qw.f(cVar), new hp.a(str).G(true).au(str).at("blob").fG());
    }

    public static hr a(Action action, String str, long j, String str2, int i) {
        Bundle bundle = new Bundle();
        bundle.putAll(action.fI());
        Bundle bundle2 = bundle.getBundle("object");
        Uri uri = bundle2.containsKey("id") ? Uri.parse(bundle2.getString("id")) : null;
        String string = bundle2.getString("name");
        String string2 = bundle2.getString(UnityAdsConstants.UNITY_ADS_ANALYTICS_QUERYPARAM_EVENTTYPE_KEY);
        Intent intentA = hz.a(str2, Uri.parse(bundle2.getString("url")));
        hd.a aVarA = hr.a(intentA, string, uri, string2, null);
        aVarA.a(a(".private:action", d(action.fI())));
        aVarA.a(aw(str));
        return new hr(hr.a(str2, intentA), j, i, null, aVarA.fD());
    }

    private static hh aw(String str) {
        return new hh(str, new hp.a(".private:actionId").G(true).au(".private:actionId").at("blob").fG());
    }

    static mv.c d(Bundle bundle) {
        mv.c cVar = new mv.c();
        ArrayList arrayList = new ArrayList();
        for (String str : bundle.keySet()) {
            Object obj = bundle.get(str);
            mv.b bVar = new mv.b();
            bVar.name = str;
            bVar.afz = new mv.d();
            if (obj instanceof String) {
                bVar.afz.NJ = (String) obj;
            } else if (obj instanceof Bundle) {
                bVar.afz.afE = d((Bundle) obj);
            } else {
                Log.e("AppDataSearchClient", "Unsupported value: " + obj);
            }
            arrayList.add(bVar);
        }
        if (bundle.containsKey(UnityAdsConstants.UNITY_ADS_ANALYTICS_QUERYPARAM_EVENTTYPE_KEY)) {
            cVar.type = bundle.getString(UnityAdsConstants.UNITY_ADS_ANALYTICS_QUERYPARAM_EVENTTYPE_KEY);
        }
        cVar.afA = (mv.b[]) arrayList.toArray(new mv.b[arrayList.size()]);
        return cVar;
    }
}
