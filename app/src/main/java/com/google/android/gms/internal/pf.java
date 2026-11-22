package com.google.android.gms.internal;

import android.os.Parcel;
import com.google.android.gms.plus.model.moments.ItemScope;
import com.google.android.gms.plus.model.moments.Moment;
import com.unity3d.ads.android.properties.UnityAdsConstants;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

/* JADX INFO: loaded from: classes.dex */
public final class pf extends ks implements Moment {
    public static final pg CREATOR = new pg();
    private static final HashMap<String, kr.a<?, ?>> aom = new HashMap<>();
    String CE;
    final int CK;
    final Set<Integer> aon;
    String apa;
    pd api;
    pd apj;
    String vc;

    static {
        aom.put("id", kr.a.l("id", 2));
        aom.put("result", kr.a.a("result", 4, pd.class));
        aom.put("startDate", kr.a.l("startDate", 5));
        aom.put("target", kr.a.a("target", 6, pd.class));
        aom.put(UnityAdsConstants.UNITY_ADS_ANALYTICS_QUERYPARAM_EVENTTYPE_KEY, kr.a.l(UnityAdsConstants.UNITY_ADS_ANALYTICS_QUERYPARAM_EVENTTYPE_KEY, 7));
    }

    public pf() {
        this.CK = 1;
        this.aon = new HashSet();
    }

    pf(Set<Integer> set, int i, String str, pd pdVar, String str2, pd pdVar2, String str3) {
        this.aon = set;
        this.CK = i;
        this.CE = str;
        this.api = pdVar;
        this.apa = str2;
        this.apj = pdVar2;
        this.vc = str3;
    }

    public pf(Set<Integer> set, String str, pd pdVar, String str2, pd pdVar2, String str3) {
        this.aon = set;
        this.CK = 1;
        this.CE = str;
        this.api = pdVar;
        this.apa = str2;
        this.apj = pdVar2;
        this.vc = str3;
    }

    @Override // com.google.android.gms.internal.kr
    protected boolean a(kr.a aVar) {
        return this.aon.contains(Integer.valueOf(aVar.hR()));
    }

    @Override // com.google.android.gms.internal.kr
    protected Object b(kr.a aVar) {
        switch (aVar.hR()) {
            case 2:
                return this.CE;
            case 3:
            default:
                throw new IllegalStateException("Unknown safe parcelable id=" + aVar.hR());
            case 4:
                return this.api;
            case 5:
                return this.apa;
            case 6:
                return this.apj;
            case 7:
                return this.vc;
        }
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        pg pgVar = CREATOR;
        return 0;
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof pf)) {
            return false;
        }
        if (this == obj) {
            return true;
        }
        pf pfVar = (pf) obj;
        for (kr.a<?, ?> aVar : aom.values()) {
            if (a(aVar)) {
                if (pfVar.a(aVar) && b(aVar).equals(pfVar.b(aVar))) {
                }
                return false;
            }
            if (pfVar.a(aVar)) {
                return false;
            }
        }
        return true;
    }

    @Override // com.google.android.gms.plus.model.moments.Moment
    public String getId() {
        return this.CE;
    }

    @Override // com.google.android.gms.plus.model.moments.Moment
    public ItemScope getResult() {
        return this.api;
    }

    @Override // com.google.android.gms.plus.model.moments.Moment
    public String getStartDate() {
        return this.apa;
    }

    @Override // com.google.android.gms.plus.model.moments.Moment
    public ItemScope getTarget() {
        return this.apj;
    }

    @Override // com.google.android.gms.plus.model.moments.Moment
    public String getType() {
        return this.vc;
    }

    @Override // com.google.android.gms.internal.kr
    public HashMap<String, kr.a<?, ?>> hK() {
        return aom;
    }

    @Override // com.google.android.gms.plus.model.moments.Moment
    public boolean hasId() {
        return this.aon.contains(2);
    }

    @Override // com.google.android.gms.plus.model.moments.Moment
    public boolean hasResult() {
        return this.aon.contains(4);
    }

    @Override // com.google.android.gms.plus.model.moments.Moment
    public boolean hasStartDate() {
        return this.aon.contains(5);
    }

    @Override // com.google.android.gms.plus.model.moments.Moment
    public boolean hasTarget() {
        return this.aon.contains(6);
    }

    @Override // com.google.android.gms.plus.model.moments.Moment
    public boolean hasType() {
        return this.aon.contains(7);
    }

    public int hashCode() {
        int iHashCode = 0;
        Iterator<kr.a<?, ?>> it = aom.values().iterator();
        while (true) {
            int i = iHashCode;
            if (!it.hasNext()) {
                return i;
            }
            kr.a<?, ?> next = it.next();
            if (a(next)) {
                iHashCode = b(next).hashCode() + i + next.hR();
            } else {
                iHashCode = i;
            }
        }
    }

    @Override // com.google.android.gms.common.data.Freezable
    public boolean isDataValid() {
        return true;
    }

    @Override // com.google.android.gms.common.data.Freezable
    /* JADX INFO: renamed from: oJ, reason: merged with bridge method [inline-methods] */
    public pf freeze() {
        return this;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel out, int flags) {
        pg pgVar = CREATOR;
        pg.a(this, out, flags);
    }
}
