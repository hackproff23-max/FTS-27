package com.flurry.sdk;

import android.text.TextUtils;
import com.flurry.android.impl.ads.protocol.v13.AdFrame;
import com.flurry.android.impl.ads.protocol.v13.NativeAsset;
import com.flurry.android.impl.ads.protocol.v13.NativeAssetType;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes.dex */
public class ac {
    private static final String[] a = new String[0];

    public static List<String> a(ap apVar, int i) {
        if (apVar == null) {
            return Collections.emptyList();
        }
        AdFrame adFrameB = apVar.b(i);
        if (adFrameB == null) {
            return Collections.emptyList();
        }
        if (ag.STREAM_ONLY.equals(ag.a(adFrameB.cachingEnum))) {
            return Collections.emptyList();
        }
        ArrayList arrayList = new ArrayList();
        if (adFrameB.cacheWhitelistedAssets != null && adFrameB.cacheWhitelistedAssets.size() > 0) {
            for (String str : adFrameB.cacheWhitelistedAssets) {
                if (!TextUtils.isEmpty(str)) {
                    arrayList.add(str);
                }
            }
        } else if (adFrameB.binding == 7) {
            for (NativeAsset nativeAsset : apVar.i()) {
                if (NativeAssetType.IMAGE.equals(nativeAsset.type)) {
                    String str2 = nativeAsset.value;
                    if (!TextUtils.isEmpty(str2) && !a(adFrameB.cacheBlacklistedAssets, str2)) {
                        arrayList.add(str2);
                    }
                }
            }
        } else {
            String strB = b(apVar, i);
            if (!TextUtils.isEmpty(strB) && !a(adFrameB.cacheBlacklistedAssets, strB)) {
                arrayList.add(strB);
            }
            String strA = a(adFrameB);
            if (!TextUtils.isEmpty(strA) && !a(adFrameB.cacheBlacklistedAssets, strA)) {
                arrayList.add(strA);
            }
            for (String str3 : a) {
                String strA2 = a(adFrameB, str3);
                if (!TextUtils.isEmpty(strA2) && !a(adFrameB.cacheBlacklistedAssets, strA2)) {
                    arrayList.add(strA2);
                }
            }
        }
        return arrayList;
    }

    private static String b(ap apVar, int i) {
        cy cyVarD = apVar.d(i);
        if (cyVarD != null) {
            return cyVarD.f();
        }
        return null;
    }

    private static String a(AdFrame adFrame) {
        if (adFrame == null || adFrame.display == null || adFrame.binding != 3) {
            return null;
        }
        return adFrame.display;
    }

    private static String a(AdFrame adFrame, String str) {
        if (adFrame == null || TextUtils.isEmpty(str) || adFrame.content == null) {
            return null;
        }
        String strA = a(adFrame.content, str);
        if (TextUtils.isEmpty(strA)) {
            return null;
        }
        return strA;
    }

    private static boolean a(List<String> list, String str) {
        if (list != null && !TextUtils.isEmpty(str)) {
            Iterator<String> it = list.iterator();
            while (it.hasNext()) {
                if (str.equals(it.next())) {
                    return true;
                }
            }
            return false;
        }
        return false;
    }

    private static String a(String str, String str2) {
        if (TextUtils.isEmpty(str) || TextUtils.isEmpty(str2)) {
            return null;
        }
        String str3 = "";
        try {
            Iterator it = Arrays.asList(str2.split("\\s*-\\s*")).iterator();
            String str4 = str;
            while (it.hasNext()) {
                String string = new JSONObject(str4).getString((String) it.next());
                str4 = !TextUtils.isEmpty(string) ? string : str4;
                str3 = string;
            }
            return str3;
        } catch (JSONException e) {
            return null;
        }
    }
}
