package com.google.android.gms.drive.query.internal;

import android.os.Parcel;
import com.google.android.gms.drive.metadata.MetadataField;
import com.google.android.gms.drive.metadata.SearchableMetadataField;
import com.google.android.gms.drive.metadata.internal.MetadataBundle;

/* JADX INFO: loaded from: classes.dex */
public class HasFilter<T> extends AbstractFilter {
    public static final g CREATOR = new g();
    final int CK;
    final MetadataBundle Si;
    final MetadataField<T> Sj;

    HasFilter(int i, MetadataBundle metadataBundle) {
        this.CK = i;
        this.Si = metadataBundle;
        this.Sj = (MetadataField<T>) e.b(metadataBundle);
    }

    public HasFilter(SearchableMetadataField<T> field, T value) {
        this(1, MetadataBundle.a(field, value));
    }

    @Override // com.google.android.gms.drive.query.Filter
    public <F> F a(f<F> fVar) {
        return fVar.d(this.Sj, getValue());
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public T getValue() {
        return (T) this.Si.a(this.Sj);
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel out, int flags) {
        g.a(this, out, flags);
    }
}
