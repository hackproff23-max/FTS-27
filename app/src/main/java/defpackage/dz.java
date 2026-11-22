package defpackage;

import android.support.v4.media.TransportMediator;
import com.unity3d.ads.android.properties.UnityAdsConstants;

/* JADX INFO: loaded from: classes.dex */
public class dz extends dt {
    private static final bm a = d.a(dz.class);

    public dz(dv dvVar) {
        super((short) 515);
        if (a.b()) {
            a.b("PDXQueryParameter()");
        }
        a("name", dvVar.b(), (short) 193);
        if (dvVar.c() == 127) {
            a(UnityAdsConstants.UNITY_ADS_ANALYTICS_QUERYPARAM_EVENTTYPE_KEY, 5);
        } else {
            a(UnityAdsConstants.UNITY_ADS_ANALYTICS_QUERYPARAM_EVENTTYPE_KEY, dvVar.c());
        }
        switch (dvVar.c()) {
            case 1:
                a("buffer_id", ((dq) dvVar).a());
                break;
            case 2:
                a("text", ((ee) dvVar).a(), (short) 193);
                break;
            case 3:
                a("text", (String) null, (short) 193);
                break;
            case 4:
                a("data", (byte[]) null, (short) 193);
                break;
            case 5:
                a("dict", ((dr) dvVar).a(), (short) 224);
                break;
            case 6:
            case 7:
            case 8:
                a("dict", ((ec) dvVar).a(), (short) 224);
                break;
            case TransportMediator.KEYCODE_MEDIA_PAUSE /* 127 */:
                a("dict", ((ed) dvVar).d(), (short) 224);
                break;
            default:
                a.e("PDXQueryParameter() Unknown parameter type: " + ((int) dvVar.c()) + ". ");
                break;
        }
    }
}
