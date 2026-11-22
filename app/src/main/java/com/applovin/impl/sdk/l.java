package com.applovin.impl.sdk;

import android.content.Context;
import android.content.SharedPreferences;
import android.location.Location;
import com.applovin.sdk.AppLovinTargetingData;
import com.millennialmedia.android.MMRequest;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

/* JADX INFO: loaded from: classes.dex */
class l implements AppLovinTargetingData {
    private final Context a;

    l(AppLovinSdkImpl appLovinSdkImpl) {
        if (appLovinSdkImpl == null) {
            throw new IllegalArgumentException("No sdk specified");
        }
        this.a = appLovinSdkImpl.getApplicationContext();
    }

    private static String a(String[] strArr) {
        if (strArr == null || strArr.length <= 0) {
            return "";
        }
        StringBuilder sb = new StringBuilder();
        for (String str : strArr) {
            if (cd.c(str)) {
                sb.append(cd.b(str));
                sb.append(",");
            }
        }
        if (sb.length() > 0) {
            sb.setLength(sb.length() - 1);
        }
        return sb.toString();
    }

    private void a(String str, String str2) {
        if (cd.c(str)) {
            SharedPreferences.Editor editorEdit = this.a.getSharedPreferences("applovin.sdk.targeting", 0).edit();
            editorEdit.putString(str, cd.b(str2));
            editorEdit.commit();
        }
    }

    Map a() {
        HashMap map = new HashMap();
        Map<String, ?> all = this.a.getSharedPreferences("applovin.sdk.targeting", 0).getAll();
        if (all != null && all.size() > 0) {
            for (Map.Entry<String, ?> entry : all.entrySet()) {
                map.put(entry.getKey(), String.valueOf(entry.getValue()));
            }
        }
        return map;
    }

    @Override // com.applovin.sdk.AppLovinTargetingData
    public void clearData() {
        SharedPreferences.Editor editorEdit = this.a.getSharedPreferences("applovin.sdk.targeting", 0).edit();
        editorEdit.clear();
        editorEdit.commit();
    }

    @Override // com.applovin.sdk.AppLovinTargetingData
    public void putExtra(String str, String str2) {
        if (cd.c(str) && cd.c(str2)) {
            a("ex_" + str, str2);
        }
    }

    @Override // com.applovin.sdk.AppLovinTargetingData
    public void setBirthYear(int i) {
        if (i >= 9999 || i <= 1900) {
            return;
        }
        a("yob", Integer.toString(i));
    }

    @Override // com.applovin.sdk.AppLovinTargetingData
    public void setCarrier(String str) {
        if (cd.c(str)) {
            a("carrier", str);
        }
    }

    @Override // com.applovin.sdk.AppLovinTargetingData
    public void setCountry(String str) {
        if (cd.c(str) && str.length() == 2) {
            a("country", str.toUpperCase(Locale.ENGLISH));
        }
    }

    @Override // com.applovin.sdk.AppLovinTargetingData
    public void setGender(char c) {
        String str;
        if (c == 'm') {
            str = "m";
        } else {
            str = c == 'f' ? "f" : "u";
        }
        a(MMRequest.KEY_GENDER, str);
    }

    @Override // com.applovin.sdk.AppLovinTargetingData
    public void setInterests(String... strArr) {
        if (strArr == null || strArr.length <= 0) {
            return;
        }
        a("interests", a(strArr));
    }

    @Override // com.applovin.sdk.AppLovinTargetingData
    public void setKeywords(String... strArr) {
        if (strArr == null || strArr.length <= 0) {
            return;
        }
        a(MMRequest.KEY_KEYWORDS, a(strArr));
    }

    @Override // com.applovin.sdk.AppLovinTargetingData
    public void setLanguage(String str) {
        if (cd.c(str)) {
            a("language", str.toLowerCase(Locale.ENGLISH));
        }
    }

    @Override // com.applovin.sdk.AppLovinTargetingData
    public void setLocation(Location location) {
        if (location != null) {
            a("lat", Double.toString(location.getLatitude()));
            a("lon", Double.toString(location.getLongitude()));
        }
    }
}
