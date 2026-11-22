package com.flurry.sdk;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/* JADX INFO: loaded from: classes.dex */
public class gj {
    private final HashMap<String, String> a = new HashMap<>();

    public void a(String str, String str2) {
        if (str != null) {
            this.a.put(str, str2);
        }
    }

    public String a(String str) {
        if (str == null) {
            return null;
        }
        return this.a.get(str);
    }

    public int a() {
        return this.a.size();
    }

    public String b() {
        StringBuilder sb = new StringBuilder();
        Set<Map.Entry<String, String>> setEntrySet = this.a.entrySet();
        if (setEntrySet.size() > 0) {
            for (Map.Entry<String, String> entry : setEntrySet) {
                sb.append(hp.c(entry.getKey()));
                sb.append("=");
                sb.append(hp.c(entry.getValue()));
                sb.append("&");
            }
            sb.deleteCharAt(sb.length() - 1);
        }
        return sb.toString();
    }

    public boolean equals(Object obj) {
        if (obj == null || !(obj instanceof gj)) {
            return false;
        }
        gj gjVar = (gj) obj;
        if (gjVar.a() != a()) {
            return false;
        }
        for (Map.Entry<String, String> entry : this.a.entrySet()) {
            String value = entry.getValue();
            String strA = gjVar.a(entry.getKey());
            if (value != strA && (value == null || !value.equals(strA))) {
                return false;
            }
        }
        return true;
    }
}
