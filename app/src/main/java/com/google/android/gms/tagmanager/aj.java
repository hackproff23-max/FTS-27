package com.google.android.gms.tagmanager;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/* JADX INFO: loaded from: classes.dex */
abstract class aj {
    private final Set<String> art;
    private final String aru;

    public aj(String str, String... strArr) {
        this.aru = str;
        this.art = new HashSet(strArr.length);
        for (String str2 : strArr) {
            this.art.add(str2);
        }
    }

    public abstract com.google.android.gms.internal.d.a B(Map<String, com.google.android.gms.internal.d.a> map);

    boolean a(Set<String> set) {
        return set.containsAll(this.art);
    }

    public String pI() {
        return this.aru;
    }

    public Set<String> pJ() {
        return this.art;
    }

    public abstract boolean pe();
}
