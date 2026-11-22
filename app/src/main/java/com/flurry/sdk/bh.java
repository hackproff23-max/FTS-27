package com.flurry.sdk;

import android.content.Context;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public final class bh implements bi {
    private final List<bi> a;

    public bh() {
        ArrayList arrayList = new ArrayList();
        arrayList.add(new bg());
        arrayList.add(new bl());
        arrayList.add(new be());
        arrayList.add(new bk());
        this.a = Collections.unmodifiableList(arrayList);
    }

    @Override // com.flurry.sdk.bi
    public boolean a(Context context, bm bmVar) {
        if (context == null || bmVar == null) {
            return false;
        }
        boolean z = true;
        Iterator<bi> it = this.a.iterator();
        while (true) {
            boolean z2 = z;
            if (it.hasNext()) {
                z = !it.next().a(context, bmVar) ? false : z2;
            } else {
                return z2;
            }
        }
    }
}
