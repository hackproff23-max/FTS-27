package com.google.android.gms.drive.metadata.internal;

import android.content.Context;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.drive.internal.w;
import com.google.android.gms.drive.metadata.MetadataField;
import com.google.android.gms.internal.jv;
import com.google.android.gms.internal.jx;
import com.google.android.gms.internal.ln;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

/* JADX INFO: loaded from: classes.dex */
public final class MetadataBundle implements SafeParcelable {
    public static final Parcelable.Creator<MetadataBundle> CREATOR = new h();
    final int CK;
    final Bundle Ri;

    MetadataBundle(int versionCode, Bundle valueBundle) {
        this.CK = versionCode;
        this.Ri = (Bundle) jx.i(valueBundle);
        this.Ri.setClassLoader(getClass().getClassLoader());
        ArrayList arrayList = new ArrayList();
        for (String str : this.Ri.keySet()) {
            if (e.bm(str) == null) {
                arrayList.add(str);
                w.o("MetadataBundle", "Ignored unknown metadata field in bundle: " + str);
            }
        }
        Iterator it = arrayList.iterator();
        while (it.hasNext()) {
            this.Ri.remove((String) it.next());
        }
    }

    private MetadataBundle(Bundle valueBundle) {
        this(1, valueBundle);
    }

    public static <T> MetadataBundle a(MetadataField<T> metadataField, T t) {
        MetadataBundle metadataBundleIZ = iZ();
        metadataBundleIZ.b(metadataField, t);
        return metadataBundleIZ;
    }

    public static MetadataBundle a(MetadataBundle metadataBundle) {
        return new MetadataBundle(new Bundle(metadataBundle.Ri));
    }

    public static MetadataBundle iZ() {
        return new MetadataBundle(new Bundle());
    }

    public <T> T a(MetadataField<T> metadataField) {
        return metadataField.h(this.Ri);
    }

    public <T> void b(MetadataField<T> metadataField, T t) {
        if (e.bm(metadataField.getName()) == null) {
            throw new IllegalArgumentException("Unregistered field: " + metadataField.getName());
        }
        metadataField.a(t, this.Ri);
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof MetadataBundle)) {
            return false;
        }
        MetadataBundle metadataBundle = (MetadataBundle) obj;
        Set<String> setKeySet = this.Ri.keySet();
        if (!setKeySet.equals(metadataBundle.Ri.keySet())) {
            return false;
        }
        for (String str : setKeySet) {
            if (!jv.equal(this.Ri.get(str), metadataBundle.Ri.get(str))) {
                return false;
            }
        }
        return true;
    }

    public int hashCode() {
        int iHashCode = 1;
        Iterator<String> it = this.Ri.keySet().iterator();
        while (true) {
            int i = iHashCode;
            if (!it.hasNext()) {
                return i;
            }
            iHashCode = this.Ri.get(it.next()).hashCode() + (i * 31);
        }
    }

    public Set<MetadataField<?>> ja() {
        HashSet hashSet = new HashSet();
        Iterator<String> it = this.Ri.keySet().iterator();
        while (it.hasNext()) {
            hashSet.add(e.bm(it.next()));
        }
        return hashSet;
    }

    public void setContext(Context context) {
        com.google.android.gms.common.data.a aVar = (com.google.android.gms.common.data.a) a(ln.RI);
        if (aVar != null) {
            aVar.a(context.getCacheDir());
        }
    }

    public String toString() {
        return "MetadataBundle [values=" + this.Ri + "]";
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel dest, int flags) {
        h.a(this, dest, flags);
    }
}
